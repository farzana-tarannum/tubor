(ns math2.solve
  (:require
   [clojure.spec.test.alpha :as stest]
   [clojure.test.check.generators :as gen2]
   [clojure.spec.gen.alpha :as gen]
   [clojure.spec.alpha :as s]
   [datascript.core :as d]
   [defun.core :refer [defun fun]]
   [clojure.walk :refer [postwalk]  :as w]))


(comment
  (s/def ::p+ (s/and int? (comp not pos-int?)) )

  (s/def ::p-
    (s/cat

     :p (s/and int? pos-int?)))

  (s/valid?   (s/+ ::p+) [true 3 true 3])

  (s/def ::bras
    (s/+
     (s/or
      :def (s/+ ::p+)
      :abc (s/cat
            :t ::p+
            :f (s/+ ::p-)))))

  (s/conform ::bras [true 3 true 3 false 3
                     ]))


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

(def mxml
  {:c second
   :sym (comp ffirst  vec (fn [n] (nth n 2)))
   :vec vec
   :m (fn [n] :m)
   :p (fn [n] :p)
   :syms (comp
          (fn [vv] (map (fn [[u v]]
                          (if (= v 1) u [:p u v] )) vv))
          vec
          (fn [n] (nth n 2)))
   })


(def mkeq1a
  (fun
   ([[con 0 _]]
    [:c con {1 0}])
   ([[1 1 e]]
    [:sym 1 {e 1}])
   ([[c2 1 e]]
    [[:m :c :sym] c2 {e 1}])
   ([[1 d e]]
    [[:p :sym :deg] 1 {e d}])
   ([[c2 [d & d1] [e & e1]]]
    [[:m :c :syms] c2
     (into {e d}
           (zipmap e1 d1))])
   ([[c2 d e]]
    [[:c :p :vec] c2 {e d}])
   ([[c2  [e & re]]]
    [[:m :c :syms] c2
     (into {e 1} (zipmap re (repeat 1)))])
   ([[c2  e]]
    [[:m :c :sym] c2 {e 1}])
   ([(e2 :guard symbol?)]
    [:sym 1 {e2 1}])
   ([con :guard number?]
    [:c con {1 0} ])
   ([pass]
    pass)))

(def mkeq2a
  (fn [l]
    (let [s (first l)]
      (cond
        (= (type :a) (type s)) ((s mxml) l)
        :else
        (let [[a b c] (vec (map (fn [m]
                                  ((m mxml) l))  s))]
          `[~a ~b ~@c])
        ))))
(def mk2
  (comp
   (fn [x] (map
            (comp
             (juxt (comp
                    mkeq2a
                    (fn [b]
                      (update b 1 (fn [e] (if (> e 0) e
                                              (* e -1)))))) second)
             mkeq1a)  x))
   eq2))





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
        (if (zero? (count z))
          (cons '+ res)
          (into  res (cons '- z))
          )
        ))))


#_(def f+
  (fn [ee]
    (loop [eqn (seq ee)
           z []
           flg []
           res []

           ]
      (if cnt
        (let [[x ] (first eqn)
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
        (if (zero? (count z))
          (cons '+ res)
          (into  res (cons '- z))
          )
        ))))


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




(def schema {:exp/word {:db/cardinality :db.cardinality/many
                        :db/valueType :db.type/ref}
             :exp/sen {:db/cardinality :db.cardinality/many
                       :db/valueType :db.type/ref}
             :eq/exp {:db/cardinality :db.cardinality/many
                      :db/valueType :db.type/ref}
             :eq/name {:db/unique :db.unique/identity}})

(def conn   (d/create-conn schema))


(comment
  (map

   [[:x 5]
    [[5 [2 1 3] [:z :x :y]] [-2 [:y :x]] :x 4]])


  #_(d/transact!
     conn
     [{:eq/exp [:zero :sen1]
       :eq/name :eq2}
      {:db/id :sen1
       :exp/name :e1
       :exp/sen [:w1]}
      {:db/id :w1
       :word/oder 1
       :word/name :a
       :exp/word [:x :y]}
      {:db/id :x
       :word/sign true
       :word/sym 'x
       :word/sub :w1
       :word/name :x
       :word/cof 1
       :word/deg 1}
      {:db/id :y
       :word/sign true
       :word/sym 'y
       :word/sub :w1
       :word/name :x
       :word/cof 1
       :word/deg 1}
      ])


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
    {:db/id (gensym)
     :word/sign (pos-int? c2)
     :word/sym (symbol? (name e))
     :word/sub? false
     :word/name e
     :word/cof c2
     :cof/num (if  (pos-int? c2) c2 (ve c2))
     :cof/dnm 1
     :sym/deg d}
    )
   ([[c2 [d & dr] [e & er]]]
    (let [a {:db/id (gensym)
             :word/sign (pos-int? c2)
             :word/sym (symbol? (name e))
             :word/sub? false
             :word/name e
             :word/cof c2
             :cof/num c2
             :cof/dnm 1
             :sym/deg d}
          fs (fn [d e]
               {:db/id (gensym)
                :word/sign (pos-int? 1)
                :word/sym (symbol? (name e))
                :word/sub? false
                :word/name e
                :word/cof 1
                :cof/num 1
                :cof/dnm 1
                :sym/deg d})]
      (cons
       a
       (map fs dr er)))

    )
   ([[c2  e]]
    [:np c2 1 e])
   ([(e2 :guard symbol?)]
    [:np1 1 1 e2])
   ([con :guard number?]
    [:c con 0 1])
   ([pass]
    pass)))



(defn board3 []
  (let [dd8 `[[x 5]
              [[5 [2 1 3] [z x y]] [-2 [y x]] x 4]]]
    [(
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
              (into [ (if (not
                           (or (= (* x x1) 1)
                               (= (* x x1) -1)))
                        (cond (= w :np1) :np
                              (= w :cp) :p
                              :else w)
                        w)
                     (* x x1)

                     ]
                    (if (int? z1)
                      [y z]
                      [
                       (vec (flatten [y y1]))
                       (vec (flatten [z z1]))]
                      )))
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
         [[5 [2 1 3] [z x y]] [-2 [y x]] x 4]]))



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





(comment
  ((:sym mxml) (mkeq1a 'x))
  ((comp
    e+
    e++
    (juxt (fn [xx] (vec (map first xx))) (fn [xx] (vec (map second xx))))
    mk2)
   `[[5 [2 1 3] [z x y]] [-2 [y x]] x 4]))

(defn board4 []
  (let [dd8 `[[x -5]
              [[5 [2 1 3] [z x y]] [-2 [y x]] x 4]]]
    [(
      (comp
       (fn [x] (cons :m x))
       (fn [nn]
         (map (comp
               (fn [e] [:b e])
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



     ((partial cons :m)
      (map
       (comp
        (fn [a] [:b a])
        e++
        (juxt (partial map first )
              (partial map second))
        mk2)
       dd8))


     ((comp
       e++
       (juxt (fn [xx] (vec (map first xx))) (fn [xx] (vec (map second xx))))
       mk2)
      (second dd8))

     (let [l (eq2 dd8)
           f1 (comp
               e++
               (juxt (fn [xx] (vec (map first xx))) (fn [xx] (vec (map second xx))))
               mk2)
           a (mk2 (first l))
           b (f1 (second l))]
       ((comp
         e++
         (juxt (fn [xx] (vec (map first xx))) (fn [xx] (vec (map second xx)))))
        (map (fn [[x y]]
               [[:m x [:b b]] y])  a)))

     ((comp second)
      (let [l (eq2 `[[x -5]
                     [[5 [2 1 3] [z x y]] [-2 [y x]] x 4]])
            f (comp mkeq1a)]
        ((juxt (partial map first) (partial map second))
         (for [[_ x e1] (map f (first l))
               [_ y e2] (map f (second l))
               ]
           (let [m (merge-with + e1 e2)
                 p (* x y)
                 k (first (keys m))
                 d (if (symbol? k) :sym :c)
                 fl [(cond (= (count m) 1) d

                           (= p 1) [:m  :syms]
                           :else [:m :c :syms] )
                     (js/Math.abs p) m]
                 ]
             [(mkeq2a fl) (if (> p 0) true false)]
             )))
        ))

     (
      (comp
       (fn [x] `[+
                 [:m ~(symbol (name 'x)) ~@x]
                 [:m 5 ~@x]])
       (fn [nn]
         (map (comp
               (fn [e] [:b e])
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
              (into [ (if (not
                           (or (= (* x x1) 1)
                               (= (* x x1) -1)))
                        (cond (= w :np1) :np
                              (= w :cp) :p
                              :else w)
                        w)
                     (* x x1)

                     ]
                    (if (int? z1)
                      [y z]
                      [
                       (vec (flatten [y y1]))
                       (vec (flatten [z z1]))]
                      )))
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
         [[5 [2 1 3] [z x y]] [-2 [y x]] x 4]]))



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
