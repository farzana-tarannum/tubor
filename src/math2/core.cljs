(ns ^:figwheel-hooks math2.core
  (:require [reagent.dom :as rdom]
            [reagent.core :as r]
            [clojure.string :as str]
            [math2.template :as t]
            [math2.file :as file]
            [math2.bdmap :as bmap]
            [math2.math :as math]
            [math2.gradient :as gradient]
            [react]))

;;(defn ^:before-load my-before-reload-callback []
;;    (println "BEFORE reload!!!"))


(defn ^:after-load my-after-reload-callback []
  (println "AFTER reload"))



(def functional-compiler (r/create-compiler {:function-components true}))


(defn render-simple []
  (rdom/render
   (math/template1)
   (js/document.getElementById "app") functional-compiler))

(render-simple)
