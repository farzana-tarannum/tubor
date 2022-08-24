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
