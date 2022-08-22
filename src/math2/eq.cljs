(ns math2.eq
  (:require
   [clojure.string :as str]
   [clojure.spec.test.alpha :as stest]
   [clojure.test.check.generators :as gen2]
   [clojure.spec.gen.alpha :as gen]
   [clojure.spec.alpha :as s]
   [defun.core :refer [defun fun]]))






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
