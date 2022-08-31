(ns math2.css
  (:require
   [clojure.string :as str]
   [clojure.spec.test.alpha :as stest]
   [clojure.test.check.generators :as gen2]
   [clojure.spec.gen.alpha :as gen]
   [clojure.spec.alpha :as s]
   [defun.core :refer [defun fun]]))


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
   :scale #{:rem :px :vh :vw :% :fr}))

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

(def size
  (comp
   not-space
   (juxt :size (comp name :scale))))




(def linear-gradient
  (comp
   (comp
    (f-add :linear-gradient)
    wrap
    not-space
    (partial interpose ",")
    (juxt (comp
           not-space
           (conjj (name :rad))
           vector
           :angle)
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
                           size
                           :size))))
           :colors)))))



(defn span [[row rspan]]
  (str/join "/"
            [row (+ rspan row)]))




(defn css [[sp color gradient & other]]
  (let [cell
        (comp
         (fn [n] (update n :font-size size))
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
        color (if (> (count color) 0)
                {:background-color (hsl color)}
                {})
        ]
    (comment (merge (cell span) g color (partial )))
    (merge
     (cell
      (s/conform :math7/span sp))
     color
     g
     (apply merge other))))
