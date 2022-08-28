(ns math2.svg
  (:require
   [clojure.string :as str]
   [clojure.spec.test.alpha :as stest]
   [clojure.test.check.generators :as gen2]
   [clojure.spec.gen.alpha :as gen]
   [clojure.spec.alpha :as s]
   [defun.core :refer [defun fun]]))


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

(defn fs [fnts]
  (let [wrap (fn [a]
               (str "'" a  "'"))]
    {:font-family "'Amstelvar VF'"
     :font-variation-settings
     (coma
      (map (fn [x y]
             (str x " " y))
           (map wrap ["wdth" "wght" "CNTR"  "opsz" "PWGT"
                      "PWDT" "GRAD" "XOPQ" "XTRA" "YOPQ"])
           fnts))}))



(defn flames
  ([id red yellow]
   [:filter
    {:id (name id)
     :height (np [300 :%])
     :width (np [120 :%])
     :y (np [-100 :%])
     :x (np [0 :%])
     }
    [:feTurbulence
     {:stitchTiles :stitch
      :result "noise"
      :numOctaves 1
      :baseFrequency 1
      :type :fractalNoise}]
    [:feOffset
     {:result "off1", :dy "0"}
     [:animate
      {:repeatCount "indefinite",
       :dur "6s",
       :to "-300",
       :from "0",
       :attributeName "dy",
       :attributeType :XML}]]
    [:feOffset
     {:result "off2", :dy "60", :in "noise"}
     [:animate
      {:repeatCount "indefinite",
       :dur "6s",
       :to "0",
       :from "300",
       :attributeName "dy",
       :attributeType "XML"}]
     ]
    [:feMerge
     {:result "scrolling-noise"}
     [:feMergeNode {:in "off1"}]
     [:feMergeNode {:in "off2"}]]
    [:feComponentTransfer
     {:result "brighter-noise"}
     [:feFuncA {:exponent "0.5", :amplitude "1", :type "gamma"}]]
    [:feComposite
     {:result "gradient-noise",
      :operator "in",
      :in2 "brighter-noise",
      :in "SourceGraphic"}]
    [:feComponentTransfer
     {:result "threshhold"}
     [:feFuncR {:tableValues "0 .2", :type "discrete"}]
     [:feFuncG {:tableValues "0 .2", :type "discrete"}]
     [:feFuncB {:tableValues "0 .2", :type "discrete"}]
     [:feFuncA {:tableValues "0 .2", :type "discrete"}]]
    [:feFlood {:result "yellow", :flood-color yellow}]
    [:feComposite
     {:result "yellow-threshhold",
      :operator "in",
      :in "yellow",
      :in2 "threshhold"}]
    [:feFlood {:result "red", :flood-color red}]
    [:feComponentTransfer
     {:result "exponent-gradient", :in "SourceGraphic"}
     [:feFuncA {:exponent "5", :type "gamma"}]]
    [:feComposite
     {:result "red-gradient",
      :operator "in",
      :in2 "exponent-gradient",
      :in "red"}]
    [:feComposite
     {:result "red-gradient-threshhold",
      :operator "in",
      :in "red-gradient",
      :in2 "threshhold"}]
    [:feMerge
     [:feMergeNode {:in "yellow-threshhold"}]
     [:feMergeNode {:in "red-gradient-threshhold"}]]])
  ([]
   (flames :flames "#f33" "#ff9" )))
