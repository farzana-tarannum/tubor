(ns ^:figwheel-hooks math2.core
  (:require [reagent.dom :as rdom]
            [reagent.core :as r]
            [clojure.string :as str]
            [math2.file :as file]
            [math2.math7 :as math7]
            [math2.physics11 :as p11]
            [math2.math100 :as m100]
            [math2.math :as mtemp]
            [math2.physics100 :as py100]
            [react]))

;;(defn ^:before-load my-before-reload-callback []
;;    (println "BEFORE reload!!!"))


(defn ^:after-load my-after-reload-callback []
  (println "AFTER reload"))



(def functional-compiler (r/create-compiler {:function-components true}))


(defn template []
  [:div
   #_[p11/template]
   #_[p11/template2]
   #_[m100/template]
   [m100/template2]
   #_[m100/template2-1]
   #_[m100/template3]
   #_[m100/template4]
   [m100/template5]
   #_[mtemp/template1]
   [m100/home-work]
   [m100/home-work2]
   #_[m100/home-work3]
   #_[m100/home-work5]
   #_[m100/home-work7]
   #_[m100/home-work7-1]
   [m100/home-work8]

   #_[m100/home-work9]
   #_[m100/home-work10]
   #_[m100/home-work11]
   [m100/home-work12]
   [m100/home-work13]
   [m100/home-work14]
   [m100/home-work15]
   [m100/home-work16]
   [m100/home-work17]
   [m100/home-work18]
   [m100/home-work19]
   [m100/home-work20]
   [m100/home-work21]
   [m100/banner1]
   [m100/banner2]
   #_[py100/template]
   ] )


(defn template3 []
  [:div
   [py100/template]] )


(defn render-simple []
  (rdom/render
   (template)
   (js/document.getElementById "app")
   functional-compiler))

(render-simple)
