(ns math2.solve
  (:require
   [defun.core :refer [defun fun]]
   [clojure.walk :refer [postwalk]  :as w]))


(defn eq2 [eq]
  (vec (postwalk
        (fn [x]
          (if (symbol? x)
            (symbol (name x))
            x))
        eq)))


(def cp (fn [c d e] `[:p ~e ~d]))
(def p (fn [c d e] `[:m ~c [:p ~e ~d]] ))
(def np (fn [c _ e] `[:m ~c ~e] ))
(def np1 (fn [c _ e] e))
(def c (fn [c _ e] c))


(def mkeq
  (fun
   ([[c2 d e]]
    [c2 d e])
   ([[c2  e]]
    [c2 1 e])
   ([(e2 :guard symbol?)]
    [:np1 1 1 e2])
   ([con :guard number?]
    [con 0 1])
   ))

(def mkeqx
  (fun
   ([[con 0 1]]
    (c con 0 1))
   ([[1 1 e]]
    (np1 1 1 e))
   ([[c2 1 e]]
    (np c2 1 e))
   ([[1 d e]]
    (cp 1 d e))
   ([[c2 d e]]
    (p c2 d e))))

(def mkeq1
  (fun
   ([[con 0 _]]
    [:c con 0 1])
   ([[1 1 e]]
    [:np1 1 1 e])
   ([[c2 1 e]]
    [:np c2 1 e])
   ([[1 d e]]
    [:cp 1 d e])
   ([[c2 d e]]
    [:p c2 d e])
   ([[c2  e]]
    [:np c2 1 e])
   ([(e2 :guard symbol?)]
    [:np1 1 1 e2])
   ([con :guard number?]
    [:c con 0 1])
   ([pass]
    pass)))

(def mkeq21
  (fun
   ([[:np1 co deg ee]]
    (np1 co deg ee))
   ([[:np co deg ee]]
    (np co deg ee))
   ([[:p co deg ee]]
    (p co deg ee))
   ([[:cp co deg ee]]
    (p co deg ee))
   ([[:c co deg ee]]
    (c co deg ee))
   ))

(def mkeq2
  (comp
   mkeq21
   mkeq1))

(def neg-eq
  (fn [e]
    (let [[a b c d] (mkeq1 e)]
      [a (* -1 b) c d])))


(def add-first
  (fn [dd]
    (let [[a & r] dd]
      [`[~(neg-eq a) ~@(map mkeq1  dd)]
       [(neg-eq a) (mkeq1 0)]])))

(def add-nth
  (fn [dd n]
    (let [a (get dd n)]
      [`[~(neg-eq a) ~@(map mkeq1  dd)]
       [(neg-eq a) (mkeq1 0)]])))


(def t3
  (fun [[[e f g] b]]
       [e b f g]))

(def simf
  (fn [acc e]
    (let [[_ c deg sm] (mkeq1 e)
          v (get acc [ deg sm])]
      (if v
        (assoc acc [ deg sm] (+ v c))
        (assoc acc [ deg sm] c)))))

(def t2
  (fn [acc e]
    (let [[f c deg sm] (mkeq1 e)
          v (get acc [f deg sm])]
      (if v
        (assoc acc [f deg sm] (+ v c))
        (assoc acc [f deg sm] c)))))

(def simplify
  (fn [dd]
    (vec
     (map
      (comp
       (fn [[a b]]
         (a b))
       (juxt
        (comp
         #(if (> % 1) (fn [a]
                        (vec (cons '+ a))) first)
         count) identity)
       #(map mkeq2 %)

       vec
       #(map (fun [[[e f g] b]]
                  [e b f g]) %)
       vec
       #(filter (fn [[u v]]
                  (if (= v 0) false true)) %)
       vec
       #(reduce t2  {} %))
      dd
      ))))





(def simplify3
  (fn [dd]
    (vec
     (map
      (comp
       (fn [[a b]]
         (a b))
       (juxt
        (comp
         #(if (> % 1) (fn [a]
                        (vec (cons '+ a))) first)
         count) identity)
       #(map mkeq2 %)

       vec
       #(map (fun [[[ f g] b]]
                  [f g b]) %)
       vec
       #(filter (fn [[u v]]
                  (if (= v 0) false true)) %)
       vec
       #(reduce simf  {} %))
      dd
      ))))



(def dd2 (eq2 `[[2 2 x] [4 2 y] [3  x] [2  y] -56 ]))
(def dd4 (eq2 `[ [5 x] [-2 y] 7]))
(def exp-eq
  (comp
   (fn [a]
     ['= a 0])
   vec
   (fn [a]
     (cons '+ a))))
(def inv (fn [[c d sym] ]
           [(* c -1) d sym]))

(def sq (fn [[c d sym] ]
          [(* c c) (* 2 d) sym]))

(def mul (fn [con [c d sym] [c1 d1 sym1]]
           (if (= d1 0)
             [(* c c1 con) d sym]
             [(* c c1 con) d sym])))


;; (fn [exp] (map mkeqx exp))
((comp
  vec
  (fn [exp] (map mkeqx exp))
  (fn [[a b]]
    [(sq a) (sq b)
     (mul 2 a b )] )
  (fn [exp] (map (fun [[[f g] b]]
                      [b f g]) exp))
  (fn [exp] (filter (fn [[u v]]
                      (if (= v 0) false true)) exp))
  (fn [exp] (reduce simf {} exp))
  (fn [exp] (conj exp
                  (inv (get exp 1))))
  vec
  (fn [exp] (map mkeq  exp)))
 dd4)

(def simplify2
  (fn [dd]
    (vec
     (map
      (comp
       (fn [[a b]]
         (a b))
       (juxt
        (comp
         #(if (> % 1) (fn [a]
                        (vec (cons '+ a))) first)
         count) identity)
       #(map mkeq2 %)
       vec
       #(map mkeq1 %)
       vec
       #(map (fun [[[e f g] b]]
                  (mkeq1 [b f g] )) %)
       vec
       #(filter (fn [[u v]]
                  (if (= v 0) false true)) %)
       vec
       #(reduce t2  {} %)
       #(map (fn [[a b c d]]
               [a (if (= (mod b 2) 0)
                    (/ b 2)
                    [b 2]) c d])
             %)
       )
      dd
      ))))

(defn e3g []
  [
   (exp-eq
    (map mkeq2 dd4))
   (cons '=
         (map
          (comp
           vec
           (fn [a]
             (cons '+ a))
           (fn [s] (map mkeq2 s)))
          (add-nth dd4 1)))
   (vec (cons '= (simplify (add-nth dd4 1))))
   (vec (cons '= (simplify2 (add-nth dd4 1))))

   (vec (cons '=
              (let [[a b]
                    (vec (simplify (add-nth dd4 1)))]
                [[:p [:b a] 2] [:p [:b b] 2]])))
   (exp-eq
    (map mkeq2 dd2))
   (let [four-ys (vec (cons '+
                            ((comp
                              vec
                              (fn [exp] (map mkeqx exp))
                              (fn [[a b]]
                                [(sq a) (sq b)
                                 (mul 2 a b)] )
                              (fn [exp] (map (fun [[[f g] b]]
                                                  [b f g]) exp))
                              (fn [exp] (filter (fn [[u v]]
                                                  (if (= v 0) false true)) exp))
                              (fn [exp] (reduce simf {} exp))
                              (fn [exp] (conj exp
                                              (inv (get exp 1))))
                              vec
                              (fn [exp] (map mkeq  exp)))
                             dd4)))]
     (postwalk
      (fun ([[:m 4 [:p y 2]]]
            four-ys)
           ([[:m 2 'y]]
            (first (simplify (add-nth dd4 1))))
           ([x] x))
      (exp-eq
       (map mkeq2 dd2))))

   (exp-eq
    (map mkeq2 (eq2 `[ [27 2 x ] [78 x]])))
   (eq2 '[= [:m x  [:b [+ [:m 27 x] 78]]] 0])
   (exp-eq (map mkeq2 (eq2 `[ [27 x] 78])))

   (exp-eq
    ((comp
      vec
      (fn [exp] (map mkeqx exp))
      (fn [exp] (map (fun [[[f g] b]]
                          [b f g]) exp))
      (fn [exp] (filter (fn [[u v]]
                          (if (= v 0) false true)) exp))
      (fn [exp] (reduce (fn [acc a]
                          (simf acc a)) {} exp))
      vec
      (fn [exp] (map mkeq  exp)))
     dd2))

   ])



(defn e3g1 []
  [
   (exp-eq
    (map mkeq2 dd4))
   (cons '=
         (map
          (comp
           vec
           (fn [a]
             (cons '+ a))
           (fn [s] (map mkeq2 s)))
          (add-nth dd4 1)))
   (vec (cons '= (simplify (add-nth dd4 1))))
   (vec (cons '= (simplify2 (add-nth dd4 1))))

   (vec (cons '=
              (let [[a b]
                    (vec (simplify (add-nth dd4 1)))]
                [[:p [:b a] 2] [:p [:b b] 2]])))
   (exp-eq
    (map mkeq2 dd2))
   (let [four-ys (vec (cons '+
                            ((comp
                              vec
                              (fn [exp] (map mkeqx exp))
                              (fn [[a b]]
                                [(sq a) (sq b)
                                 (mul 2 a b)] )
                              (fn [exp] (map (fun [[[f g] b]]
                                                  [b f g]) exp))
                              (fn [exp] (filter (fn [[u v]]
                                                  (if (= v 0) false true)) exp))
                              (fn [exp] (reduce simf {} exp))
                              (fn [exp] (conj exp
                                              (inv (get exp 1))))
                              vec
                              (fn [exp] (map mkeq  exp)))
                             dd4)))]
     (postwalk
      (fun ([[:m 4 [:p y 2]]]
            four-ys)
           ([[:m 2 'y]]
            (first (simplify (add-nth dd4 1))))
           ([x] x))
      (exp-eq
       (map mkeq2 dd2))))

   (exp-eq
    (map mkeq2 (eq2 `[ [27 2 x ] [78 x]])))
   (eq2 '[= [:m x  [:b [+ [:m 27 x] 78]]] 0])
   (exp-eq (map mkeq2 (eq2 `[ [27 x] 78])))

   (exp-eq
    ((comp
      vec
      (fn [exp] (map mkeqx exp))
      (fn [exp] (map (fun [[[f g] b]]
                          [b f g]) exp))
      (fn [exp] (filter (fn [[u v]]
                          (if (= v 0) false true)) exp))
      (fn [exp] (reduce (fn [acc a]
                          (simf acc a)) {} exp))
      vec
      (fn [exp] (map mkeq  exp)))
     dd2))

   ])


#_(into (conj (conj
               (mkeq [-2 'y]) )
            [-4 2 'y]))

#_(e3g)
