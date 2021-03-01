(ns ^:figwheel-hooks hello-bundler.core
  (:require [reagent.dom :as rdom]
            [reagent.core :as r]
            [clojure.string :as str]
            [hello-bundler.template :as t]
            [hello-bundler.file :as file]
            [hello-bundler.bdmap :as bmap]
            [hello-bundler.math :as math]
            [hello-bundler.gradient :as gradient]
            [react]))

;;(defn ^:before-load my-before-reload-callback []
;;    (println "BEFORE reload!!!"))


(defn ^:after-load my-after-reload-callback []
    (println "AFTER reload!!!"))


(def functional-compiler (r/create-compiler {:function-components true}))


(defn render-simple []
  (rdom/render
   (math/template1)
   (js/document.getElementById "app") functional-compiler))

(render-simple)
