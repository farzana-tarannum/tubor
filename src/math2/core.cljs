(ns ^:figwheel-hooks math2.core
  (:require [reagent.dom :as rdom]
            [reagent.core :as r]
            [clojure.string :as str]
            [math2.file :as file]
            [math2.math7 :as math7]
            [math2.physics11 :as p11]
            [math2.math100 :as m100]
            [math2.math19 :as m19]
            [math2.fraction :as frac]
            [math2.math :as mtemp]
            [math2.trig :as trig]
            [math2.bdmap :as bdmap]
            [math2.solve :as s]
            [clojure.walk :as w]
            [math2.transfrom :as trans]
            [defun.core :refer [defun fun]]
            [math2.math7 :as m7 :refer
             [grid hsl css space size path ve sec]]
            [math2.physics100 :as py100]
            [react]
            ))

;;(defn ^:before-load my-before-reload-callback []
;;    (println "BEFORE reload!!!"))


(defn ^:after-load my-after-reload-callback []
  (println "AFTER reload"))



(def functional-compiler (r/create-compiler {:function-components true}))





(defn math []
  [:div
   #_[m100/board2
      []
      []
      []]

   #_[m100/chem-rate]
   ;; airplane .5
   #_[m100/home-work3]
   #_[m19/home-work19]
   ;; pressure
   #_[m100/pressure]

   #_[m100/pressure4]

   #_[m100/pressure2]

   #_[m100/energy]
   #_[frac/frection]

   #_[m100/triangle2]

   #_[m100/triangle]

   #_[m100/home-pressure2]
   #_[m100/home-pressure]

   #_[m100/app]
   #_[m100/sine-wave]
   #_[m100/airplane]
   #_[m100/map-asia]
   #_[m100/index2]
   #_[m100/sine-wave2]
   #_[m100/sine-wave2]
   [trig/ladder]
   #_[m100/home-planets-banners]
   #_[m100/eng-tense]
   #_[m100/board2
    (s/board5)
    (let [[f1 f2] [7 -2]
          x 'x]
      [[[m7/x `[:m 8 [:p ~x 2]]] 1 1 :center :center]
       [[m7/x `[* ~f1 ~f2]] 2 2 :center :center]
       [[m7/x `[:m 7 ~x]] 2 1 :center :center]
       [[m7/x `[:m -16 ~x]] 1 2 :center :center]
       [[m7/x `[:m 1 ~x]]
        1 1 :flex-end :center]
       [[m7/x `[:m 8 ~x]]
        1 1 :center :flex-start]
       [f1
        2 2 :center :flex-start]
       [f2 2 2 :flex-end  :center]])
    [
     [m7/x `[[:m 108
              [:p y 2] ] 25]]]
    ]
   #_[m100/board3
    (s/board6)
    []

   []]
   #_[m100/map-asia]
   #_[m100/airplane]
   #_[m100/home-work22]
   #_[m100/chem-rate]
   ;; ;; airplane .5
   #_[m100/home-work3]
   #_[m100/home-work19]
   ;; ;; pressure
   #_[m100/pressure]

   #_[m100/pressure2]

   #_[m100/energy]
   ;;; [m100/frection]
   #_[m100/triangle]
    #_[m100/home-pressure2]
    #_[m100/home-pressure]

      ;; spring force
   #_[p11/template]
   ;; ;; chemistry
    #_[p11/template2]
   ;; ;; frictions
    #_[m100/template]
   ;; ;; spring force
   #_[m100/template2]
   ;; ;; trigono
    #_[m100/template2-1]
   ;; ;; extra
    #_[m100/template3]
   ;; ;; taibles
    #_[m100/template4]
   ;; ;; physics video
    #_[m100/template5]
    #_[mtemp/template1]
   ;; ;; robin hood
    #_[m100/home-work]
   ;; ;; momentum math
    #_[m100/home-work2]
   ;; ;; velocity
    #_[m100/home-work3]
   ;; ;; pyramid
    #_[m100/home-work5]
   ;; ;; garden math
    #_[m100/home-work7]
   ;; ;; quad algo
    #_[m100/home-work7-1]
   ;; ;; solving linear equation
    #_[m100/linear-equation]

   #_[m100/force-diagram]

   #_[m100/banner-qd]

   #_[m100/banner-factor-identities2]

   ;; ;; pyramid
   #_[m100/home-work10]
   ;; ;; boxes
   #_[m100/home-work11]
   ;; ;; accelaration
    #_[m100/home-work12]
   ;; ;; v + u at
     #_[m100/home-work13]
   ;; ;; clock
   #_    [m100/home-work14]
   ;; ;; line to curves
   #_[m100/home-work15]

   ;; ;; temp vs time latent heat
   #_[m100/home-work17]
    #_[m100/home-work18]
   ;; [m100/home-work19]
   ;; ;; temp
    #_[m100/home-work21]
    #_[m100/banner1]
   #_[m100/banner-brand]
    #_[m100/map-asia]
   #_[py100/template]
   ;; [py100/template]
   #_[m100/playgroud]
   [m100/home-planets-banners4]
   ;; [m100/home-work19]
   #_[py100/t2]
   #_[m100/map-family]
   #_[m100/map-family3]
   #_[m100/map-family2]
   #_[py100/template2]
   #_[py100/template]
   #_[m100/home-planets]
   #_[m100/chem-rate]
   #_[m100/chem-pop2]

   #_[m100/home-planets]
    #_[m100/home-work16]
   ;; ;; distillation
    #_[m100/chem-pop2]
   #_[m100/home-work22]
   ;; ;; hcl + haolh
   #_[m100/home-work9]
    #_[py100/template]
   #_[py100/template2]
    #_[py100/template3]

   ;; ;; distillation2
   #_[m100/home-work20]




   ])



(defn template []
  [:div



   #_[m100/home-planets-banners-exp]
   [m100/chem-mole]
   [m100/sine-wave2]
   [m100/sine-wave]

   [m100/app]
   [m100/home-planets-banners]
   (comment
     (/ (+  62000 (* 8 104))
        20000))


   [m100/board2
    (s/e3g)
    (let [[f1 f2] [7 -2]
          x 'x]
      [[[m7/x `[:m 8 [:p ~x 2]]] 1 1 :center :center]
       [[m7/x `[* ~f1 ~f2]] 2 2 :center :center]
       [[m7/x `[:m 7 ~x]] 2 1 :center :center]
       [[m7/x `[:m -16 ~x]] 1 2 :center :center]
       [[m7/x `[:m 1 ~x]]
        1 1 :flex-end :center]
       [[m7/x `[:m 8 ~x]]
        1 1 :center :flex-start]
       [f1
        2 2 :center :flex-start]
       [f2 2 2 :flex-end  :center]])
    [
     [m7/x `[[:m 108
              [:p y 2] ] 25]]]]

   #_[m100/home-planets-banners]
   [m100/eng-tense]

   #_(js/Math.pow 5 12)
   [m100/map-asia]
   [m100/airplane]
   [m100/home-work22]
   [m100/chem-rate]
   ;; airplane .5
   [m100/home-work3]
   [m100/home-work19]
   ;; pressure
   [m100/pressure]

   [m100/pressure2]

   [m100/energy]
   [m100/frection]
   [m100/triangle]
   [m100/home-pressure2]
   [m100/home-pressure]




   ;; spring force
   [p11/template]
   ;; chemistry
   [p11/template2]
   ;; frictions
   [m100/template]
   ;; spring force
   [m100/template2]
   ;; trigono
   [m100/template2-1]
   ;; extra
   [m100/template3]
   ;; taibles
   [m100/template4]
   ;; physics video
   [m100/template5]
   [mtemp/template1]
   ;; robin hood
   [m100/home-work]
   ;; momentum math
   [m100/home-work2]
   ;; velocity
   [m100/home-work3]
   ;; pyramid
   [m100/home-work5]
   ;; garden math
   [m100/home-work7]
   ;; quad algo
   [m100/home-work7-1]
   ;; solving linear equation
   [m100/linear-equation]

   [m100/force-diagram]

   [m100/banner-qd]

   [m100/banner-factor-identities2]

   ;; pyramid
   [m100/home-work10]
   ;; boxes
   [m100/home-work11]
   ;; accelaration
   [m100/home-work12]
   ;; v + u at
    [m100/home-work13]
   ;; clock
      [m100/home-work14]
   ;; line to curves
    [m100/home-work15]

   ;; temp vs time latent heat
   [m100/home-work17]
   [m100/home-work18]
   [m100/home-work19]
   ;; temp
   [m100/home-work21]
   [m100/banner1]
   [m100/banner-brand]
   [m100/map-asia]
   [py100/template]
   [py100/template]
   [m100/home-planets-banners]
   [m100/home-work19]
   [py100/t2]
   [m100/map-family]
   [m100/map-family3]
   [m100/map-family2]
   [py100/template2]
   [py100/template]
   [m100/home-planets]
   [m100/chem-rate]
   [m100/chem-pop2]

   [m100/home-planets]
   [m100/home-work16]
   ;; distillation
   [m100/chem-pop2]
   [m100/home-work22]
   ;; hcl + haolh
   [m100/home-work9]
   [py100/template]
   [py100/template2]
   [py100/template3]

   ;; distillation2
   [m100/home-work20]])


(defn template3 []
  [:div

   #_[m100/pressure]
   #_[m100/home-work19]
   #_[m100/home-work3]
   #_[m100/chem-rate]
   #_[m100/home-work22]
   #_[m100/airplane]
   #_[m100/map-asia]
   #_[m100/eng-tense]
   #_[m100/app]
   #_[m100/home-planets-banners]
   #_[m100/grammer-eng]

   #_[m100/home-work19]
   #_[py100/t2]
   #_[m100/map-family]
   #_[m100/map-family3]
   #_[m100/map-family2]
   #_[py100/template2]
   [py100/template3]
   #_[py100/template4]
   ])



(defn chemistry []
  [:div
   #_[py100/template3]
   #_[m100/app]
   #_[m100/home-planets-banners3]
   #_[m100/home-planets-banners2]
   #_[m100/home-planets-banners]
   #_[m100/home-planets-banners5]

   ;; alkane
   #_[m100/home-planets]
   #_[m100/chem-rate]
   #_[m100/chem-pop2]


   #_[m100/home-planets]
   #_[m100/home-work16]
   ;; distillation
   #_[m100/chem-pop2]
   #_[m100/home-work22]
   ;; hcl + haolh
   #_[m100/home-work9]
   ;; distillation2
   #_[m100/home-work20]

   ] )

(defn curveslasses []
  [:div
   #_[m19/home-work19]
   #_[trig/ladder]
   #_[trans/coordinates2]
   [trans/coordinates3]
   #_[frac/frection]
   #_[frac/frection2]
   ])



#_(template3)
(defn render-simple []
  (rdom/render
   (curveslasses)
   (js/document.getElementById "app")
   functional-compiler))
(render-simple)
