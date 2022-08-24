(ns math2.eqtest)



(comment
  (s/unform   (s/conform ::element
                         (eq2 `[= [:m g [:b t]]  [+ b [:m 2 a t]]])))

  (sx `[= [:m g [:b t]]  [+ b [:m 2 a t]]] 't '[+ t 3])

  (let [eq `[= [:m g [:b t]]  [+ b [:m 2 a t]]]
        x2 't
        tt '[+ t 3]]
    (let [r (vec (clojure.walk/postwalk
                  (fn [x]
                    (if (symbol? x)
                      (let [x1 (symbol (name x))]
                        (if (= x1 x2)
                          tt
                          x1))
                      x))
                  eq))]
      r))

  (s/valid? ::element '[:b 1 3])
  (x `[:t 3 5 ~(+ 3 4)])
  (expr (s/conform ::element '[+ 1 3]))
  (s/conform ::element
             '[:b 3]))

(comment

  (let [eq `[= [- [:m 3 x] [:m 2 y]] 7] ]
    (clojure.walk/postwalk (fn [x]
                             (if (symbol? x)
                               (symbol (name x))
                               x))
                           eq)))

(comment
  (s/conform ::element
             (vec (map
                   (fn [x]
                     (if (symbol? x)
                       (symbol (name x))
                       x))
                   `[= [- [:m 3 x] [:m 2 y]] 7])))
  (mx `[= [- [:m 3 x] [:m 2 y]] 7])
  (symbol (name `x)))

(comment
  (expr
   (s/conform ::element
              (clr (let [ay 1]
                     `(+ x ~(+ ay 2)))))
   )

  (expr (s/conform ::element 1)))

(comment
  (expr (s/conform ::element '(:m 2 x)))

  (expr (s/conform ::element '(:b 2))))

(comment (expr (s/conform ::element [:m 2 'x 3 4])))

(comment

  (s/conform ::element [:b [:b 3]]))

(comment
  (expr
   (s/conform ::element
              '(+ :alpha) x (- 5 2 4)
              ((- x ) 1))))
(comment
  (s/conform ::element
             '(= (- 2) (:c (:p x 3))
                 )))
(comment
  (expr
   (s/conform ::element
              '(+ 2 x 4
                  (:sq a)))))

(comment (expr
          (s/conform ::element
                     '(= 3 (+ 2 x 4
                              )))))


(comment

  (s/conform ::element
             1)

  (expr
   (s/conform ::element
              '(= 3 (+ 2 x 4
                       )))))

(comment
  (expr (s/conform ::element '(1 2))))



(comment
  (let [ b (m7/eq2 `[[1 [1] [x]] [-1 [1] [1]]])]
    ((fn [eqk1 eqk2]
       (let [f2 (fn [[a1 b1 c1]]
                  (fn [[a b c]]
                    [a (* b1 b)
                     (merge-with (fn [e d] (+ e d)) c c1)]))
             k (eq2 eqk1)
             k2 (eq2 eqk2)
             kf2 (map
                  (comp
                   f2
                   mkeq1a) k2)
             kf3 (mapcat
                  (fn [f]
                    (map
                     (comp
                      f
                      mkeq1a) k))
                  kf2)
             f (fn [l1]
                 (let [
                       l11 (map-indexed (fn [i [x y z]]
                                          (let [pos (fn [s] (if (> s 0) s (* -1 s)))
                                              f (fn [x] (if (< x 0) '- '+ ))]
                                          [y (f y) x (if (= i 0) y (pos y)) z]))
                                      l1)
                     l2 (map-indexed
                         (fn [i [_ y & _]] [i y]) l11)
                     ]

                   (map (fn [[[a _] [x y]]] [y a x])         (reverse (partition 2 1 l2)))))]

         (f kf3))) b b))

  (comment
    ((fn [[s b e :as l]]
       (let [[a b c] [:m b e]]
         (if (= (count e) 1)
           (if (= b 1) c [a b c])
           (if (= b 1) `[~a  ~@c] `[~a ~b ~@c])) ))
     (mkeq3a (nth kf3 1))))
  (comment
    (reduce
     (fn [e [s n _]]
       [s (mkeq2a (nth kf3 n)) e])
     (first (f kf3))
     (rest (f kf3))))
  )
