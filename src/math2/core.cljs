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
            [math2.solve :as s]
            [clojure.walk :as w]
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
   #_[m100/index2]
   #_[m100/sine-wave2]
   [m100/board3
    (s/board3)
    []
    []]
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
   ])


(comment



  [m7/x `[= [+ [:m 2 x] 1] y]]

  [m7/x
   (m7/eq2 `[= [+ [+ [:m 8 [:p x 2]] x] 6
                [:m 5 y] [:m 6 x y] [:m 9 [:p x 2] y]
                [:m 2 y] [:m 3 x y] [:m 2 [:p x 2] y]

                ] 0])
   ]

          #_(m7/x `[= [+ [:m 6 [:p y 2]] [:m 6 y] [:m [:p 3 2] [:b [- 1 3]]]] 0])


        #_(m7/x `[= [+ [:m 6 [:p y 2]] [:m 6 y] [* [- 2] [:p 3 2] ]] 0])


        #_(m7/x `[= [+ [:m 1 [:p y 2]] [:m 1 y] [- 3]] 0])

        [m7/x `[= [-  [:m 8 [:p x 2]]
                   [:m 9 x ] 14] 0]]




        #_[m7/x (m7/eq2 (w/postwalk
                       (fun ([[:m c [:p [:b [+ a b]] 2]]]
                             ['+ [:m c [:p a 2]] ['* 2 c a b] ['* c  [:p b 2]]])
                            ([y] y))
                       '[= 27 [+ [:p x 2] [:m 3 [:p [:b [+ [:m 2 y] 3]] 2]]]]))]


        #_[m7/x
         (m7/eq2 (w/postwalk (fun ([[:m a [:b c]]] c)
                                  ([y] y))
                             (m7/eq2 (w/postwalk
                                      (fun ([[:p [:b [+ a b]] 2]]
                                            [:b ['+ [:p a 2] ['* 2 a b] [:p b 2]]])
                                           ([y] y))
                                      '[= 27 [+ [:p x 2] [:m 3 [:p [:b [+ [:m 2 y] 3]] 2]]]]))))]
        (comment
          )
        (comment
          [m7/x

           (m7/eq2 `[= [- [+ [:p x 2] [:m 2 x] ] 3] y])]



          [m7/x
           `[= [+ [:m 2 x]  1 ] y]]




          [m7/x

           (m7/eq2 `[= [:p x 2]  4])]


          (comment
            [m7/x
             (m7/eq2 `[= [- 3 [:p x 2]]  [+ x 1]])]



            [m7/x
             (m7/eq2 `[= [+ [:p x 2] x [- 2]] 0])]


            [m7/x
             (m7/eq2 `[= [+ [:p x 2] x [- 2]] 0])]



            [m7/x
             (m7/eq2 `[= [+ [:p x 2] [:m x [:b [- 2 1]]] [* 2 [- 1]]] 0])]

            [m7/x
             (m7/eq2 `[= [+ [:p x 2] [- [:m 2 x] x] [* 2 [- 1]]] 0])]


            [m7/x
             (m7/eq2 `[= [- [:m x [:b [+ x 2]]] [:m 1 [:b [+ x 2]]]] 0])]


            [m7/x '[= [:m [:b [+ x 2]]  [:b [- x 1]]] 0] ]




            [m7/m  '[= [+ [- x 1 ] 1] 1]]
            [:div "or"]

            [m7/m  '[= [+ x 2] 0]]))


  #_[m7/x `[= [-   5] [+ [:p x 2]  x [- 5] 6 ]]]

  )

(comment


  [:div {:style {:background-color (hsl [3 20 20 1])
                 :width "100%"
                 :height "3px"}
         }]

  [m7/x
   (m7/eq2 `[= [- 3 [:p x 2]] [+ x 1]])]


  [m7/x
   (m7/eq2 `[=  [+ [- [:p x 2]] [- x ] 2 ] 0])]


  [m7/x
   (m7/eq2 `[=  [+ [- [:p x 2]] [- x [:m 2 x]] 2 ] 0])]


  [m7/x
   (m7/eq2 `[- [:m [- x] [:b [- x 1]]]  [:m 2 [:b [- x 1]]]])]



  [m7/x
   (m7/eq2 `[= [:m  [:b [- x 1]] ] 0])])

(comment
         #_[m7/x '[+ x 2]]



        #_[m7/x
         (m7/eq2 `[= [- 2 [:p x 2] ] x])]



          #_(w/postwalk (fn [x] x) '[:apply
                                 [= [+ x y] 1]
                                 [- x]])

        #_(m7/x
         (m7/eq2
          (map
           (fun ([(n :guard number?)] (conj `[+ [- x]] n))
                ([(m :guard vector?)] (conj m `[- x]))
                ([n] n))
           )))


        #_(comment
          [m7/x `[= [- [:m 2 [:p x 2]]
                     x [* 7 3]] 0]]



          [m7/x `[= [+ [:m 2 [:p x 2]]
                     [:m x [:b [- [* 2 3] 7]]] [* [- 7] 3]] 0]]



          [m7/x `[= [+ [:m 2 [:p x 2]]
                     [- [:m [* 2 3] x] [:m 7 x]] [* [- 7] 3]] 0]]



          [m7/x `[= [+ [:m [:m 2 x] [:b [+ x 3]]]
                     [:m [- 7] [:b [+ x 3]]]] 0]]


          [m7/x `[= [:m [:b [+ x 3]] [:b [- [:m 2 x] 7]]] 0]]



          #_[m7/x `[= [+ [:m x [:b [- [:m 2 x]
                                    7]]]
                       [:m 3 [:b [- [:m 2 x]
                                  7]]]] 0]])







        #_[m7/m '[= y [- 1 x]]]


        #_[m7/m '[= [+ [:m 2 [:p x 2]]
                   [:m x y]
                   [:p y 2]]
                22]]
        #_[m7/m '[= [+ [:m 2 [:p x 2]] [:m x [:b [- 1 x]]] [:p [:b [- 1 x]] 2]] 22]]


        #_[m7/m '[= [+ [:m 2 [:p x 2]] [- x [:p x 2]]
                   [:p [:b [- 1 x]] 2]] 22]]

        #_[m7/m '[= [- [:m 2 [:p x 2]]  [:m x [:b [- 1 2]]] [- 21]] 0]]


        #_[m7/m '[= [- [:m 2 [:p x 2]] x 21] 0]]

 )

(comment
  [m7/x `[= y [+ [- [:p x 2] [:m 3 x ]] 1]]]
  [m7/x `[= y [-  [:m 2 x] 5]]]
  [m7/x `[= [-  [:m 2 x ] 5] [+ [- [:p x 2] [:m 2 x ] [:m 2 x ] x]  1]]]
  [m7/x `[= 0 [+ [- [:p x 2] [:m 5 x] ]  6]]]

  [m7/x `[= 0 [:m [:b [- x 2]] [:b [- x 3]]]]]



  [m7/x `[= 0 [- x 3]]]
  [m7/x `[= 3 x]]
  [m7/x `[= y [-  [* 2 3 ] 5]]]
  [m7/x `[= y [-  [* 2 3 ] 5]]]
  [m7/x `[= y 1]]

  [:div ]

  [m7/x `[= 0 [- x 2]]]
  [m7/x `[= 2 x]]
  [m7/x `[= y -1]])


(defn chemistry []
  [:div

   #_[m100/app]
   #_[m100/home-planets-banners]

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
   (math)
   (js/document.getElementById "app")
   functional-compiler))
(render-simple)
