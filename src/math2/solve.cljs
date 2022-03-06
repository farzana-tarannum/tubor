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


(def p (fun
        ([c [d1 & d] [e1 & e]] (into [:m c
                                      [:p e1 d1]]
                                     (map
                                      (fn [a b]
                                        (if (= a 1)
                                            b
                                            [:p  b a]))
                                      d e)))
        ([c d e] `[:m ~c
                   [:p ~e ~d]])))
(def np (fun
         ([c _ [ e & e1]] `[:m ~c ~e ~@e1])
         ([c _ e] `[:m ~c ~e])))
(def np1 (fun
          ([_ _ [ e & e1]] `[:m  ~e ~@e1])
          ([_ _ e] e)))
(def c (fn [c _ e] c))

(comment


  (p [3 2] [2 2 3] ['x 'y 'z])
  )


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


(def mkeq1a
  (fun
   ([[con 0 _]]
    [:c con 0 1])
   ([[1 1 e]]
    [:np1 1 1 e])
   ([[c2 1 e]]
    [:np c2 1 e])
   ([[1 d e]]
    [:cp 1 d e])
   ([[c2 [d & d1] [e & e1]]]
    (into
     [[:p c2 d e]]
     (map (fn [d e] [:p c2 d e]) d1 e1)))
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
    (c co deg ee))))

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
      dd))))






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
      dd))))



(def dd2 (eq2 `[[2 2 x] [4 2 y] [3  x] [2  y] -56 ]))
(def dd4 (eq2 `[[5 x] [-2 y] 7]))


(def dd3 (eq2 `[[2 2 x] [4 2 y] [3  x] [2  y] -56 ]))

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


(def simplifyn
  (fn [dd]
    (fn [nn]
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
                 [a (if (= (mod b nn) 0)
                      (/ b nn)
                      [b nn]) c d])
               %)
         )
        dd)))))

(def simplify-exp
  (comp
   vec
   #(map (fun [[[e f g] b]]
              [e b f g]) %)
   vec
   #(filter (fn [[u v]]
              (if (= v 0) false true)) %)
   vec
   #(reduce t2  {} %)))

(defn mkml1 [d]
  (cons '=
         (map
         (comp
          vec
          (fn [a]
            (cons '+ a))
          (fn [s] (map mkeq2 s)))
         d)))

(defn e+ [d]
  (eq2 `[+ ~@d]))


(defn e= [d]
  ['= d 0])

(comment
  (p [2 2] [2 2] ['x 'y])
  (e+
   (map mkeq2
        (eq2 `[[[3 2] [2 2] [x y]] [4 2 y] [3 x] [2 y] -56])))

  (map
   (fn [[_ co p x]]
     (mkeq1 [(* co co) (+ p p) x]))
   (simplify-exp
    (first (add-nth dd4 1))))

  (
   (comp
    (fn [x]
      (if (> (count x) 1)
        `[:m ~@x]
        (first x))
      )
    #(map (fn [x]
            (cons '*
                  (map mkeq2 x))) %)
    #(partition-by
      (fn [a]
        (= (first a) :c)
        (> (second a) 1)) %)
    vec
    sort)
   (let [m `[~(mkeq1 2)
             ~@(simplify-exp
                (first (add-nth dd4 1)))]
         ]
     m))


  (map mkeq1 dd3)

  `[:m
    ~@(map mkeq2

           `[
             ~(mkeq1
               (apply *
                      (map (fn [[_ a _ _]]
                             a)
                           (let [m `[~(mkeq1 2)
                                     ~@(simplify-exp
                                        (first (add-nth dd4 1)))]
                                 ]
                             m))))

             ~@(map (fn [[a b c d]]
                      [a 1 c d])
                    (filter (fn [[a & _]]
                              (if (= a :c) false true))
                            (let [m `[~(mkeq1 2)
                                      ~@(simplify-exp
                                         (first (add-nth dd4 1)))]
                                  ]
                              m)))])]

  #_(simplify-exp
     (first (add-nth dd4 1)))

  #_(cons '=
          (map
           (comp
            vec
            (fn [a]
              (cons '+ a))
            (fn [s] (map mkeq2 s)))
           (add-nth dd4 1)))
  )






#_(vec (cons '= (simplify (add-nth dd4 1))))
#_(vec (cons '= (simplify2 (add-nth dd4 1))))
#_(vec (cons '= ((simplifyn (add-nth dd4 1)) 2)))

#_(vec (cons '=
             (let [[a b]
                   (vec (simplify (add-nth dd4 1)))]
               [[:p [:b a] 2] [:p [:b b] 2]])))


#_(vec (cons '=
           (let [[a b]
                 (vec (simplify (add-nth dd4 1)))]
             [[:p [:b a] 2] [:p [:b b] 2]])))



(defn e3g []
  [

   (e=
    (e+
     (map mkeq2
          (eq2 `[[[3 2] [2 2] [x y]] [4 2 y] [3 x] [2 y] -56]))))

   (e+
    (map
     (comp
      mkeq2
      (fun
       ([[:c b c d]]
        [:np b c 'x])
       ([[a b c d]]
            [a b
             ((comp
               vec
               flatten
               conj) [4 c])
             ((comp
               vec
               flatten
               conj) ['x d])]))
      mkeq1)
     (eq2 `[[[3 2] [2 2] [x y]] [4 2 y] [3 x] [2 y] -56])))

   (exp-eq
    (map mkeq2 dd4))
   (mkml1
    (add-nth dd4 1))

   (vec (cons '= (simplify (add-nth dd4 1))))

   (vec (cons '= ((simplifyn (add-nth dd4 1)) 2)))




   #_(first (add-nth dd4 1))
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
   #_(exp-eq
    (map mkeq2 dd4))









   #_(exp-eq
    (map mkeq2 dd2))
   #_(let [four-ys (vec (cons '+
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

   #_(exp-eq
    (map mkeq2 (eq2 `[ [27 2 x ] [78 x]])))
   #_(eq2 '[= [:m x  [:b [+ [:m 27 x] 78]]] 0])
   #_(exp-eq (map mkeq2 (eq2 `[ [27 x] 78])))

   #_(exp-eq
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

#_(sort-by
   (comp
    {:p 10
     :cp 15
     :np 20
     :np1 30
     :c 60}
    first)
   [a1 b1])

#_(
   (fn [[a b]]
     (for [[a1 b1 c1 d1] a
           [a2 b2 c2 d2] b]
       [(* b1 b2) [c1 c2] [d1 d2]]))
   (map
    (comp
     (fn [n] (map mkeq1 n))
     eq2)
    `[[x 5]
      [[5 2 z] [2 y] x 4]]))
(def e++
  (fn [[ee sq]]
    (loop [eqn (seq ee)
           cnt (seq sq)
           z []
           flg []
           res []

           ]
      (if cnt
        (let [xee (first eqn)
              x (first cnt)
              y (if (next cnt) (first (next cnt)) 0)
              t (if (or (< x 0) (< y 0))
                  false true)
              z (if (not t) (conj z xee) z)
              ]
          (recur (next eqn) (next cnt) (if (not t) z []) (conj flg t)
                 (if t
                   (conj (if (= (count z) 0)  res (conj res (vec (cons '- z))))  xee)
                   res)))
        res))))


#_(
   (comp
    e+
    #(map
      (comp
       mkeq2
       (fn [[a b]]
         (let [[w x y z] a
               [w1 x1 y1 z1] b]
           [ (if (> (* x x1) 1)
               (if (= w :np1) :np w)
               w)
            (* x x1) (if (int? z1)
                       y [y y1])
            (if (int? z1)
              z
              [z z1]) ])
         ))  %)
    (fn [[a b]]
      (for [x a
            y b]
        (vec
         (sort-by
          (comp
           {:p 10
            :cp 15
            :np 20
            :np1 30
            :c 60}
           first)
          [x y]))
        )))
   (map
    (comp
     (fn [n] (map mkeq1 n))
     eq2)
    `[[x 5]
      [[5 2 z] [-2 y] [-1 x] 4]]))

(defn board3 []
  (let [dd8 `[[x 5]
              [[5 [2 1 3] [z x y]] [-2 [y x]] x 4]]]
    [

     (
      (comp
       (fn [x] (cons :m x))
       (fn [nn]
         (map (comp
               (fn [e] [:b e])
               e+
               e++
               (juxt (fn [n] (map (comp
                                   mkeq2
                                   (fn [[a b c d]]
                                     [a (if (< b 0) (* b -1) b) c d])
                                   mkeq1) n))
                     (fn [n]
                       (map (comp
                             second
                             mkeq1) n)))
               eq2)
              nn)))
      dd8)


     (
      (comp
       (fn [x] `[+
                 [:m ~(symbol (name 'x)) ~@x]
                 [:m 5 ~@x]])
       (fn [nn]
         (map (comp
               (fn [e] [:b e])
               e+
               e++
               (juxt (fn [n] (map (comp
                                   mkeq2
                                   (fn [[a b c d]]
                                     [a (if (< b 0) (* b -1) b) c d])
                                   mkeq1) n))
                     (fn [n]
                       (map (comp
                             second
                             mkeq1) n)))
               eq2)
              nn))
       rest
       )
      dd8)





          ((comp
       #(cons '+ %)
       (fn [[ee sq]]
         (loop [eqn (seq ee)
                cnt (seq sq)
                z []
                flg []
                res []]
           (if cnt
             (let [xee (first eqn)
                   x (first cnt)
                   y (if (next cnt) (first (next cnt)) 0)
                   t (if (or (< x 0) (< y 0))
                       false true)
                   z (if (not t) (conj z xee) z)
                   ]
               (recur (next eqn) (next cnt) (if (not t) z []) (conj flg t)
                      (if t
                        (conj (if (= (count z) 0)  res (conj res (vec (cons '- z))))  xee)
                        res)))
             res)))
       (juxt
        #(vec (map first %))
        #(vec (map second %)))
       #(map
         (comp
          (juxt (comp
                 mkeq2
                 (fn [[a b c d]]
                   [a (if (< b 0) (* b -1) b) c d]) ) second)
          (fn [[a b]]
            (let [[w x y z] a
                  [w1 x1 y1 z1] b]
              [ (if (not
                     (or (= (* x x1) 1)
                         (= (* x x1) -1)))
                  (cond (= w :np1) :np
                        (= w :cp) :p
                        :else w)
                  w)
               (* x x1)
               (if (int? z1)
                 y (vec (flatten [y y1])))
               (if (int? z1)
                 z
                 (vec (flatten [z z1])))])
            ))  %)
       (fn [[a b]]
         (for [x a
               y b]
           (vec
            (sort-by
             (comp
              {:p 10
               :cp 15
               :np 20
               :np1 30
               :c 60}
              first)
             [x y]))
           )))
      (map
       (comp
        (fn [n] (map mkeq1 n))
        eq2)
       `[[x 5]
         [[5 [2 1 3] [z x y]] [-2 [y x]] x 4]] ))



     #_((comp
       (fn [eee]
         (map
          (comp
           (fn [[a b]]
             (let [[w x y z] a
                   [w1 x1 y1 z1] b]
               [ (if (not
                      (or (= (* x x1) 1)
                          (= (* x x1) -1)))
                   (cond (= w :np1) :np
                         (= w :cp) :p
                         :else w)
                   w)
                (* x x1)
                (if (int? z1)
                  y (flatten [y y1]))
                (if (int? z1)
                  z
                  (flatten [z z1]))])
             ))  eee))
       (fn [[a b]]
         (for [x a
               y b]
           (vec
            (sort-by
             (comp
              {:p 10
               :cp 15
               :np 20
               :np1 30
               :c 60}
              first)
             [x y]))
           )))
      (map
       (comp
        (fn [n] (map mkeq1 n))
        eq2)
       `[[x 5]
         [[5 [2 1 3] [z x y]] [-2 [y x]] x 4]] ))





     #_(e=
        (e+
         (map mkeq2
              (eq2 `[[[3 2] [2 2] [x y]] [4 2 y] [3 x] [2 y] -56]))))



     #_(e+
        (map
         (comp
          mkeq2
          (fun
           ([[:c b c d]]
            [:np b c 'x])
           ([[a b c d]]
            [a b
             ((comp
               vec
               flatten
               conj) [4 c])
             ((comp
               vec
               flatten
               conj) ['x d])]
            ))
          mkeq1)
         (eq2 `[[[3 2] [2 2] [x y]] [4 2 y] [3 x] [2 y] -56]))
        )

     #_(exp-eq
        (map mkeq2 dd4))
     #_(mkml1
        (add-nth dd4 1))

     #_(vec (cons '= (simplify (add-nth dd4 1))))

     #_(vec (cons '= ((simplifyn (add-nth dd4 1)) 2)))




     #_(first (add-nth dd4 1))
     #_(exp-eq
        (map mkeq2 dd2))
     #_(let [four-ys (vec (cons '+
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

     #_(exp-eq
        (map mkeq2 (eq2 `[ [27 2 x ] [78 x]])))
     #_(eq2 '[= [:m x  [:b [+ [:m 27 x] 78]]] 0])
     #_(exp-eq (map mkeq2 (eq2 `[ [27 x] 78])))

     #_(exp-eq
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

     ]))
