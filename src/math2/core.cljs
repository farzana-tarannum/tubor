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


   [m100/home-planets-banners]
   #_(js/Math.pow 5 12)
   #_[m100/map-asia]
   [m100/airplane]
   ;; airplane .5
   #_[m100/home-work3]
   ;; [m100/home-work19]
   ;; pressure
   ;; [m100/pressure]

   ;; [m100/pressure2]

    [m100/energy]
   ;; [m100/frection]
   ;; [m100/triangle]
   ;; [m100/home-pressure2]
   ;; [m100/home-pressure]




   ;; spring force
   ;; [p11/template]
   ;; chemistry
   ;; [p11/template2]
   ;; frictions
   ;; [m100/template]
   ;; spring force
   ;; [m100/template2]
   ;; trigono
   ;; [m100/template2-1]
   ;; extra
   ;; [m100/template3]
   ;; taibles
   ;; [m100/template4]
   ;; physics video
   ;; [m100/template5]
   ;; [mtemp/template1]
   ;; robin hood
   ;; [m100/home-work]
   ;; momentum math
   ;;  [m100/home-work2]
   ;; velocity
   ;; [m100/home-work3]
   ;; pyramid
   ;;   [m100/home-work5]
   ;; garden math
   ;; [m100/home-work7]
   ;; quad algo
        [m100/home-work7-1]
   ;; solving linear equation
   ;; [m100/linear-equation]

   ;; [m100/force-diagram]

   ;; [m100/banner-qd]

   ;; [m100/banner-factor-identities]

   ;; pyramid
   ;; [m100/home-work10]
   ;; boxes
   ;; [m100/home-work11]
   ;; accelaration
   ;;     [m100/home-work12]
   ;; v + u at
   ;; [m100/home-work13]
   ;; clock
   ;;   [m100/home-work14]
   ;; line to curves
   ;; [m100/home-work15]

   ;; temp vs time latent heat
   #_[m100/home-work17]
   #_[m100/home-work18]
   #_[m100/home-work19]
   ;; temp
   #_[m100/home-work21]
   #_[m100/banner1]
   #_[m100/banner-brand]
   #_[m100/map-asia]
      #_[py100/template]
    #_ [py100/template]

   ] )


(defn template3 []
  [:div
   #_[m100/home-planets-banners]
   #_[m100/home-work19]
   #_[py100/t2]
   #_[m100/map-family]
   #_[m100/map-family3]
   #_[m100/map-family2]
   #_[py100/template2]
   #_[py100/template]

   ] )



(defn chemistry []
  [:div
   ;; alkane
   #_[m100/home-planets]
   #_[m100/chem-rate]
   #_[m100/chem-pop2]
   #_[m100/chem-mole]

   #_[m100/home-planets]
   #_[m100/home-work16]
   ;; distillation
   #_[m100/chem-pop2]
   [m100/home-work22]
   ;; hcl + haolh
   #_[m100/home-work9]
   ;; distillation2
   #_[m100/home-work20]

   ] )


(defn render-simple []
  (rdom/render
   (template)
   (js/document.getElementById "app")
   functional-compiler))

(render-simple)
