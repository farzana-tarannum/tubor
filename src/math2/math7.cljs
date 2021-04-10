(ns math2.math7
  (:require
   [react]
   [clojure.string :as str]
   [clojure.spec.test.alpha :as stest]
   [clojure.test.check.generators :as gen2]
   [clojure.spec.gen.alpha :as gen]
   [clojure.spec.alpha :as s]
   [math2.file :as file]
   [defun.core :refer [defun fun]]
   [moment]))


(s/def :math7/point
  (s/cat
   :x number?
   :y number?))




(s/def :math7/path
  (s/cat
   :m :math7/point
   :d (s/+
       (s/alt
        :line (s/cat
               :l #{:l :L}
               :points (s/+ :math7/point))
        :curve-c (s/cat
                  :c #{:c :C}
                  :control-point1 :math7/point
                  :control-point2 :math7/point
                  :end-point :math7/point)
        :curve-q (s/cat
                  :q #{:q :Q}
                  :control-point :math7/point
                  :end-point :math7/point)

        :arc (s/cat
              :a #{:a :A}
              :r1 number?
              :r2 number?
              :angle number?
              :f1 boolean?
              :f2 boolean?
              :end :math7/point)))))



(comment
  (s/cat
   )
  (gen2/sample (gen2/choose 5  12)))


(def ve (fn [x] (* x -1)))
(def m-add (fn [m] (str "M " m)))
(def rel #{:l :c :a :q})
(def abs #{:L :C :A :Q})
(def space (fn [p] (str/join " " p)))

(def path
  (comp
   m-add
   space
   (partial mapcat identity)
   (juxt (comp
          (juxt :x :y)
          :m)
         (comp
          (partial
           mapcat
           (fun
            ([[:line {:l :l :points p}]]
             (cons (name :l)  (mapcat (juxt :x :y)   p)))
            ([[:line {:l :L :points p}]]
             (cons (name :L) (mapcat (juxt :x :y) p)))
            ([[:curve-c {:c :c
                         :control-point1 control-point1
                         :control-point2 control-point2
                         :end-point end-point}]]
             (cons (name :c)
                   (mapcat (juxt :x :y)  [control-point1 control-point2 end-point])))
            ([[:curve-c {:c :C
                         :control-point1 control-point1
                         :control-point2 control-point2
                         :end-point end-point}]]
             (cons (name :C)
                   (mapcat (juxt :x :y)  [control-point1 control-point2 end-point])))

            ([[:curve-q
               {:q :q :control-point control-point :end-point end-point}]]
             (cons (name :q) (mapcat (juxt :x :y)
                                     [control-point end-point])))
            ([[:curve-q
               {:q :Q :control-point control-point :end-point end-point}]]
             (cons (name :Q) (mapcat (juxt :x :y)
                                     [control-point end-point])))
            ([[:arc {:a :a :r1 r1 :r2 r2 :angle angle :f1 f1 :f2 f2 :end end}]]
             [(name :a)  r1 r2  angle (if f1 1 0) (if f2 1 0) (:x end) (:y end)])
            ([[:arc {:A :a :r1 r1 :r2 r2 :angle angle :f1 f1 :f2 f2 :end end}]]
             [(name :A)  r1 r2  angle (if f1 1 0) (if f2 1 0) (:x end) (:y end)])))
          :d))
   (partial s/conform :math7/path)))

(comment (interpose :l [1 2 3]))
(comment
  (path
   [1 3 :l 3 4 :L 3 5 6 3 6 3
    :c 23 23 2 2 3 2
    :a 2 3 3 true false 3 2
    :Q 3 2 -3 0
    :C 3 5 2 3 5 2
    :q 3 5 2 3]))
