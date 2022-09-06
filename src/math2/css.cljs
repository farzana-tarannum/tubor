(ns math2.css
  (:require
   [clojure.string :as str]
   [clojure.spec.test.alpha :as stest]
   [clojure.test.check.generators :as gen2]
   [clojure.spec.gen.alpha :as gen]
   [clojure.spec.alpha :as s]
   [defun.core :refer [defun fun]]))

(defn conjj [i]
  (fn [arr]
    (conj arr i)))

(def ve (fn [x] (* x -1)))
(def m-add (fn [m] (str "M " m)))

(def f-add (fn [m]
             (fn [st]
               (str (name m) st))))

(def l-add (fn [m]
             (fn [st]
               (str st (name m) ))))

(def sec (l-add :s))
(def rel #{:l :c :a :q})
(def abs #{:L :C :A :Q})
(def space (fn [p] (str/join " " p)))

(def sami-colon (fn [p] (str/join " ; " p)))
(def coma (fn [p] (str/join " , " p)))
(def not-space (fn [p] (str/join "" p)))


(def np (fn [p]
          (not-space
           (map
            (fn [aa]
              (if (keyword? aa) (name aa) aa))
            p))))

(defn wrap [a]
  (str "(" a  ")"))
(defn wrap' [a]
  (str "'" a  "'"))

(defn wrap-sami-colon [a]
  (str "\"" a "\""))

(defn url [i]
  (str "url" "(#" i  ")" ))


(defn frac? [s]
  (and (< s 1.001) (> s -0.001)))

(s/def :css/persent number?)

(s/def :css/hsl
  (s/cat
   :hue number?
   :saturation number?
   :lighness number?
   :opacity number?))


(comment
  (s/valid? :css/hsl [1 0 100 1]))

(s/def :css/size
  (s/cat
   :size number?
   :scale #{:rem :px :vh :vw :% :fr}))

(s/def :css/linear-gradient
  (s/cat
   :angle number?
   :colors
   (s/+ (s/cat
         :size :css/size
         :color :css/hsl))))


(s/def :css/flex
  (s/cat
   :justify-content #{:center :space-between :space-around :flex-start :flex-end}
   :align-items #{:center :space-between :space-around :flex-start :flex-end}
   :font-size :css/size
   :flex-direction (s/? #{:row :column})))

(comment (s/conform :css/flex [:center :center 1 :rem]))



(s/def :css/span
  (s/cat
   :grid-row (s/cat :row int? :span int?)
   :grid-column (s/cat :row int? :span int?)
   :flex :css/flex
   ))

(comment
  (s/conform :css/span [1 2 3 4 :center :center 1 :rem :row]))

(comment
  (s/def :css/cell
    (s/cat
     :span (s/spec :css/span)
     :color (s/spec :css/hsl)
     :linear-gradient (s/? (s/spec :css/linear-gradient)))))


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
         (partial s/conform :css/hsl)) a))

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
              (s/conform :css/linear-gradient gradient))}
            {})
        color (if (> (count color) 0)
                {:background-color (hsl color)}
                {})
        ]
    (merge
     (cell
      (s/conform :css/span sp))
     color
     g
     (apply merge other))))


(defn css2 [[sp color gradient & other]]
  (let [sz (juxt :size :scale)
        cell (comp
              (fn [n] (update n :font-size sz))
              (partial into {:display :flex})
              (juxt
               (comp
                (partial vector :grid-row)
                #_span
                (juxt :row :span)
                :grid-row)
               (comp
                (partial vector :grid-column)
                #_span
                (juxt :row :span)
                :grid-column)
               :flex)
              (fn [sp] (s/conform :css/span sp)))
        hslt2 (comp
               (fn [a]
                 [:hsla (vec a)])
               (juxt
                (comp
                 (fn [n] [n :rad])
                 :hue)
                (comp
                 (fn [n] [n :%])
                 :saturation)
                (comp
                 (fn [n] [n :%])
                 :lighness) :opacity)
               (partial s/conform :css/hsl))
        hslt (comp
              vec
              (partial cons :hlsa )
              (juxt
               (comp
                (fn [n] [n :rad])
                :hue)
               (comp
                (fn [n] [n :%])
                :saturation)
               (comp
                (fn [n] [n :%])
                :lighness) :opacity)
              (partial s/conform :css/hsl))

        g (if (> (count gradient) 0)
            {:background-image
             (linear-gradient
              (s/conform :css/linear-gradient gradient))}
            {})
        color (if (> (count color) 0)
                {:background-color (hslt2 color)}
                {})
        ]
    (merge
     (cell sp)
     color
     g
     (apply merge other))))




(defn transform [t]
  ((partial str/join " ")
   (map
    (comp
     (partial str/join "")
     (juxt
      (comp
       name
       first)
      (comp
       (fn [n]
         (str "(" n ")"))
       (fn [n]
         (if (vector? n)
           (str/join "," n)
           (str n)))
       second))) t)))

#_(transform [[:scale 3]
              [:transform [2 3]]])
#_(transform
   (rest
    (:background-color
     (css2
      [[4 4 1 12  :center :center 1.5 :rem :column]
       [2 70 90 .4] [] {:gap [1 :rem]
                        :z-index 2}]))))
