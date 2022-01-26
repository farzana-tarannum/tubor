(ns ^:figwheel-hooks math2.core
  (:require [reagent.dom :as rdom]
            [reagent.core :as r]
            [clojure.string :as str]
            [math2.file :as file]
            [math2.math7 :as math7]
            [math2.physics11 :as p11]
            [math2.math100 :as m100]
            [math2.math :as mtemp]
            [math2.bdmap :as bdmap]
            [clojure.walk :as w]
            [defun.core :refer [defun fun]]
            [math2.math7 :as m7 :refer
             [grid hsl css space size path ve sec]]
            [math2.physics100 :as py100]
            [react]))

;;(defn ^:before-load my-before-reload-callback []
;;    (println "BEFORE reload!!!"))


(defn ^:after-load my-after-reload-callback []
  (println "AFTER reload"))



(def functional-compiler (r/create-compiler {:function-components true}))






(defn template []
  [:div {:style {:display :flex
                 :justify-content :center}}


   #_[m100/home-planets-banners-exp]
   #_[m100/chem-mole]
   #_[m100/sine-wave2]
   #_[m100/sine-wave]

   #_[m100/app]
   #_[m100/home-planets-banners]
   (comment
     (/ (+  62000 (* 8 104))
        20000))
   #_[m100/grammer-eng]
   #_(+ 1 3 3 4 )
   #_[:div "50,67,72,75,46,47,55,81,87,94,83"]
   #_[:div "207"]
   #_[:div "54,56,57,59,63,58,69,70,73,6,75,21,13,49,44"]
   #_[m100/grammer-eng]
   #_[=
     27
     ~(let [cp (fn [c d e] `[:p ~e ~d])
            p (fn [c d e] `[:m ~c [:p ~e ~d]] )
            np (fn [c _ e] `[:m ~c ~e] )
            np1 (fn [c _ e] e)
            c (fn [c _ e] c)]


        (into ['+]
              (map (fn [c d ff e]
                     (ff c d e))
                   [ [1 2] 3]
                   [ 2 2]
                   [cp p]
                   ['x 'y])))]

#_(w/postwalk
        (fn [y]
          (if (= y 'y)
            (let [[_ _ r]
                  (m7/eq2
                   `[= x
                     ~(let [x 'y
                            p (fn [c d] `[:m ~c [:p ~x ~d]] )
                            np (fn [c _] `[:m ~c ~x] )
                            np1 (fn [c _] x)
                            c (fn [c _] c)]


                        (into ['+]
                              (map (fn [c d ff]
                                     (ff c d))
                                   [ 2 3]
                                   [ 1 0]
                                   [ np c])))])]
              [:b r])
            y))
        (m7/eq2
         `[=
           27
           ~(let [cp (fn [c d e] `[:p ~e ~d])
                  p (fn [c d e] `[:m ~c [:p ~e ~d]] )
                  np (fn [c _ e] `[:m ~c ~e] )
                  np1 (fn [c _ e] e)
                  c (fn [c _ e] c)]


              (into ['+]
                    (map (fn [c d ff e]
                           (ff c d e))
                         [ [1 2] 3]
                         [ 2 2]
                         [cp p]
                         ['x 'y])))]))




   [m100/board2
    (let [
          cp (fn [c d e] `[:p ~e ~d])
          p (fn [c d e] `[:m ~c [:p ~e ~d]] )
          np (fn [c _ e] `[:m ~c ~e] )
          np1 (fn [c _ e] e)
          c (fn [c _ e] c)
          mkeq1 (fun
                 ([[con 0 _]]
                  [:c con 0 1])
                 ([[1 1 e]]
                  [:np1 1 1 e])
                 ([[c2 1 e]]
                  [:np c2 1 e])
                 ([[1 d e]]
                  [:cp 1 d e])
                 ([[c2 d e]]
                  [:p c2 d e])
                 ([[c2  e]]
                  [:np c2 1 e])
                 ([(e2 :guard symbol?)]
                  [:np1 1 1 e2])
                 ([con :guard number?]
                  [:c con 0 1])
                 ([con]
                  con))
          mkeq2 (comp
                 (fun
                  ([[:np1 co deg ee]]
                   (np1 co deg ee))
                  ([[:np co deg ee]]
                   (np co deg ee))
                  ([[:p co deg ee]]
                   (p co deg ee))
                  ([[:cp co deg ee]]
                   (p co deg ee))
                  ([[:c co deg ee]]
                   (c co deg ee))
                  ) mkeq1)
          draw-eq (fn [exx]
                    (map mkeq2 exx))
          draw-eq2 (fn [[exx exx2]]
                     `[=
                       [+ ~@(map mkeq2 exx)]
                       [+ ~@(map mkeq2 exx2)]])
          neg-eq (fn [e]
                   (let [[a b c d] (mkeq1 e)]
                     [a (* -1 b) c d]))
          dd2 `[[2 2 x] [4 2 y] [3  x] [2  y] -56 ]
          dd3 `[= [+  ~@(map mkeq2 dd2)] 0]
          dd4 `[ [5 x] [-2 y] 7]
          dd5  ((fn [dd]
                  (let [[a & r] dd]
                    [`[~(neg-eq a) ~@dd]
                     [(neg-eq a) 0]]))
                dd4)
          dd6 `[  [-2 y] 7]
          a2 `[= [+  ~@(map mkeq2 dd4)] 0]
          a4 (draw-eq2 dd5)
          t1 (comp
              mkeq2 
              (fun [[[e f g] b]]
                   [e b f g]))
          t2 (fn [acc e]
               (let [[f c deg sm] (mkeq1 e)
                     v (get acc [f deg sm])]
                 (if v
                   (assoc acc [f deg sm] (+ v c))
                   (assoc acc [f deg sm] c))))
          add-first (fn [dd]
                      (let [[a & r] dd]
                        [`[~(neg-eq a) ~@(map mkeq1  dd)]
                         [(neg-eq a) 0]]))
          dd-cls (fn [ddd]
                   (map
                    t1
                    (filter (fn [[u v]]
                              (if (= v 0)
                                false true))
                            (reduce t2    {}  (m7/eq2 ddd)))))

          dd-cls-eq (fn [dd]
                      (m7/eq2 (let [[a b] (m7/eq2 (add-first dd))]
                                `[= [+ ~@(vec (dd-cls a))]
                                  ~(first (dd-cls b))]
                                )))
          a5  (dd-cls-eq dd4)
          a6  `[=
                ~(into ['+]
                       (map (fn [c d ff e]
                              (ff c d e))
                            (map (fn [x] [x -5])  [ -2 7])
                            [ 1 0]
                            [ np c]
                            [ 'y 1]))
                x]
          [_ a3 _] a6
          [_ aq1 aq2] a3 
          iden1  `[+ [:p [:b ~aq1] 2] [:m 2 [:b ~aq1] [:b ~aq2]]
                   [:p [:b ~aq2] 2]]
          idenab2 (fn [aq1 aq2]
                    (m7/eq2 
                     `[+ [:p [:b ~aq1] 2] [:m 2 [:b ~aq1] [:b ~aq2]]
                       [:p [:b ~aq2] 2]]))
          a7 (w/postwalk
              (fn [y]
                (if (= y 'x)
                  [:b a3] 
                  y))
              (m7/eq2 dd3))
          a8 (w/postwalk
              (fn [y]
                   (if (= y [:p [:b a3] 2])
                     [:b iden1]
                     y))
              (m7/eq2 a7))
          a9 (w/postwalk
              (fun ([[:m f1 [:p [:b [_ fq1 fq2]] 2]]]
                    [:m f1 [:b (idenab2 fq1 fq2)]])
                   ([y] y))
              (m7/eq2 a7))
          ]

      [
       dd3
       a2
       a4
       a5
       a6
       a7 
       a8
       a9
       ]) (let [[f1 f2] [7 -2]
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
   #_[m100/eng-tense]

   #_(js/Math.pow 5 12)
   #_[m100/map-asia]
   #_[m100/airplane]
   #_[m100/home-work22]
   #_[m100/chem-rate]
   ;; airplane .5
   #_[m100/home-work3]
   #_[m100/home-work19]
   ;; pressure
   #_[m100/pressure]

   ;; [m100/pressure2]

   #_[m100/energy]
   #_[m100/frection]
   #_[m100/triangle]
   #_[m100/home-pressure2]
   #_[m100/home-pressure]




   ;; spring force
   #_[p11/template]
   ;; chemistry
   #_[p11/template2]
   ;; frictions
   #_[m100/template]
   ;; spring force
   #_[m100/template2]
   ;; trigono
   #_[m100/template2-1]
   ;; extra
   #_[m100/template3]
   ;; taibles
   #_[m100/template4]
   ;; physics video
    #_[m100/template5]
   #_[mtemp/template1]
   ;; robin hood
   #_[m100/home-work]
   ;; momentum math
   #_[m100/home-work2]
   ;; velocity
   #_ [m100/home-work3]
   ;; pyramid
   #_   [m100/home-work5]
   ;; garden math
   #_[m100/home-work7]
   ;; quad algo
   #_[m100/home-work7-1]
   ;; solving linear equation
   #_[m100/linear-equation]

   ;; [m100/force-diagram]

   #_ [m100/banner-qd]

   #_[m100/banner-factor-identities2]

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
   #_[py100/template]

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
   ])



(defn chemistry []
  [:div
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


(defn render-simple []
  (rdom/render
   (template)
   (js/document.getElementById "app")
   functional-compiler))
(render-simple)
