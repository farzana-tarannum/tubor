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

(def f-add (fn [m]
             (fn [st]
               (str (name m) st))))
(def rel #{:l :c :a :q})
(def abs #{:L :C :A :Q})
(def space (fn [p] (str/join " " p)))
(def not-space (fn [p] (str/join "" p)))
(defn wrap [a]
  (str "(" a  ")"))



(comment
  (wrap "hello")
  (space [2 3 3]))

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

(defn persent? [s]
  (and (< s 100.001) (> s -0.001)))

(defn frac? [s]
  (and (< s 1.001) (> s -0.001)))

(s/def :math7/persent
  (s/and number? persent?))

(s/def :math7/hsl
  (s/cat
   :hue number?
   :saturation :math7/persent
   :lighness :math7/persent
   :opacity (s/and number? frac?)))


(comment
  (s/valid? :math7/hsl [1 0 100 1]))

(s/def :math7/size
  (s/cat
   :size number?
   :scale #{:rem :px :vh :vw :%}))

(s/def :math7/linear-gradient
  (s/cat
   :angle number?
   :colors
   (s/+ (s/cat
         :size :math7/size
         :color :math7/hsl))))


(s/def :math7/flex
  (s/cat
   :justify-content #{:center :space-between :space-around :flex-start :flex-end}
   :align-items #{:center :space-between :space-around :flex-start :flex-end}
   :font-size :math7/size
   :flex-direction (s/? #{:row :column})))

(comment (s/conform :math7/flex [:center :center 1 :rem]))



(s/def :math7/span
  (s/cat
   :grid-row (s/cat :row int? :span int?)
   :grid-column (s/cat :row int? :span int?)
   :flex :math7/flex
   ))

(comment
  (s/conform :math7/span [1 2 3 4 :center :center 1 :rem :row]))

(comment
  (s/def :math7/cell
    (s/cat
     :span (s/spec :math7/span)
     :color (s/spec :math7/hsl)
     :linear-gradient (s/? (s/spec :math7/linear-gradient)))))


(def hsla
  (comp
   (f-add :hsla)
   wrap
   not-space
   (partial interpose ",")
   (juxt (comp
          (fn [n] (str n "rad"))
          :hue)
         (comp
          (fn [n] (str n "%"))
          :saturation)
         (comp
          (fn [n] (str n "%"))
          :lighness) :opacity)))

(defn hsl [a]
  ((comp hsla
         (partial s/conform :math7/hsl)) a))



(def linear-gradient
  (comp
   (comp
    (f-add :linear-gradient)
    wrap
    not-space
    (partial interpose ",")
    (juxt :angle
          (comp
           not-space
           (partial interpose ",")
           (partial map (comp
                         space
                         (juxt
                          (comp
                           hsla
                           :color)
                          (comp
                           not-space
                           (juxt :size (comp name :scale))
                           :size))))
           :colors)))))



(defn span [[row rspan]]
  (str/join "/"
            [row (+ rspan row)]))




(defn css [[sp color gradient & other]]
  (let [cell
        (comp
         (fn [n] (update n :font-size
                         (comp
                          not-space
                          (juxt :size (comp name :scale)))))
         (partial into {:display :flex})
         (juxt
          (comp
           (partial vector :grid-row)
           span
           (juxt :row :span)
           :grid-row)
          (comp
           (partial vector :grid-column)
           span
           (juxt :row :span)
           :grid-column)
          :flex))
        g (if (> (count gradient) 0)
            {:background-image
             (linear-gradient
              (s/conform :math7/linear-gradient gradient))}
            {})
        color (if color {:background-color (hsl [1 70 70 1])}
                  {})
        ]
    (comment (cell sp))
    (comment (merge (cell span) g color (partial apply merge other)))
    (merge
     (cell (s/conform :math7/span sp))
     color
     g)))


(comment
  (comp
   (fn [n] (update n :font-size
                   (comp
                    not-space
                    (juxt :size (comp name :scale)))))
   (partial apply merge)
   (juxt (comp
          (partial into {:display :flex})
          (juxt
           (comp
            (partial vector :grid-row)
            span
            (juxt :row :span)
            :grid-row)
           (comp
            (partial vector :grid-column)
            span
            (juxt :row :span)
            :grid-column)
           :flex
           )
          :span)
         (comp
          :flex)
         (comp
          (partial vector :background-color)
          hsla
          :color)
         (comp
          (partial vector :background-image)
          linear-gradient
          :linear-gradient))
   (partial s/conform :math7/cell))
  (comment
    (s/conform :math7/cell [[1 2 3 4 1 :rem]
                            [1 70 70 1]
                            [2
                             1 :rem  1 70 70 1
                             2 :%  1 70 70 1
                             3 :%  1 70 70 1]])
    (s/valid? :math7/linear-gradient [2
                                      1 :rem  1 70 70 1
                                      2 :%  1 70 70 1
                                      3 :%  1 70 70 1]))
  (s/valid? :math7/cell
            [
             [1 70 70 1]
             [2
              1 :rem  1 70 70 1
              2 :%  1 70 70 1
              3 :%  1 70 70 1]])
  (partial into {})

  (partial vector :font-size)


  (let [v ]
    )
  (css
   [[1 2 3 4 :center :center 1 :rem ]
    [1 70 70 1]
    ])
  ((comp
    (fn [n] (update n :font-size
                    (comp
                     not-space
                     (juxt :size (comp name :scale)))))
    (partial into {:display :flex})
    (juxt
     (comp
      (partial vector :grid-row)
      span
      (juxt :row :span)
      :grid-row)
     (comp
      (partial vector :grid-column)
      span
      (juxt :row :span)
      :grid-column)
     :flex
     )
    )
   (s/conform :math7/span [1 2 3 4 :center :center 1 :rem :row]))
  (css [[1 2 3 4 :center :center 1 :rem :row]
        [1 70 70 1]
        []
        {}])
  (linear-gradient
   (s/conform :math7/linear-gradient [2
                                      1 :rem  1 70 70 1
                                      2 :%  1 70 70 1
                                      3 :%  1 70 70 1]))

  )
