(ns ^:figwheel-hooks math2.core
  (:require [reagent.dom :as rdom]
            [reagent.core :as r]
            [clojure.string :as str]
            [math2.file :as file]
            [math2.math7 :as math7]
            [math2.physics11 :as p11]
            [math2.math100 :as m100]
            [react]))

;;(defn ^:before-load my-before-reload-callback []
;;    (println "BEFORE reload!!!"))


(defn ^:after-load my-after-reload-callback []
  (println "AFTER reload"))



(def functional-compiler (r/create-compiler {:function-components true}))

(defn template []
  [:div
   [p11/template]
   [p11/template2]
   [m100/template]
   [m100/template2]
   [m100/template3]
   [m100/template4]
   ])


(defn render-simple []
  (rdom/render
   (template)
   (js/document.getElementById "app") functional-compiler))

(render-simple)
