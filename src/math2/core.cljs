(ns ^:figwheel-hooks math2.core
  (:require
   [reagent.dom :as rdom]
   [reagent.core :as r]
   [clojure.string :as str]
   [math2.file :as file]
   [math2.math7 :as math7]
   [math2.physics11 :as p11]
   [math2.math100 :as m100]
   [math2.math19 :as m19]
   [math2.frection :as frac]
   [math2.math :as mtemp]
   [math2.trig :as trig]
   [math2.stat :as stat]
   [math2.bdmap :as bdmap]
   [math2.solve :as s]
   [math2.teacher :as teacher]
   [clojure.walk :as w]
   [math2.transfrom :as trans]
   [math2.physics100 :as py100]))

;;(defn ^:before-load my-before-reload-callback []
;;    (println "BEFORE reload!!!"))


(defn ^:after-load my-after-reload-callback []
  (println "AFTER reload"))



(def functional-compiler (r/create-compiler {:function-components true}))



(defn render-simple []
  (rdom/render
   #_[m100/app]
   #_[m100/map-family2]
   [teacher/resume]
   (js/document.getElementById "app")
   functional-compiler))

(render-simple)
