(ns math2.solution
  (:require [math2.math7 :as m7 :refer [hsl]]))


(def drivative
  (let [d 'ε]
    [:div {:style
           (m7/css
            [[2 10  15 7
              :center :center  1.2 :rem :column]
             [1.5 70 80  0.5] []
             {:gap ".1rem"
              :color (hsl [0 30 60 1])
              :z-index 10}])}
     [:div {:style {:font-size "1.1rem"}} #_[:div time-str]]
     [m7/mx `[= y [:m 10 t]]]
     #_[m7/mx `[= ~(* 10 time-str) [* 10 ~(* 1 time-str)]]]


     [m7/mx `[= y [+ [:p x 2] [:m 5 x]  ]]]
     #_(= 'x )





     [m7/mx `[:a x [+ x ~d]]]


     [m7/mx `[= [:k y [+ t 2]] [+ [:p [:b [+ t 2]] 2] [:m 5 [:b [+ t 2]]]  ]]]


     [m7/mx `[= [:k y [+ t 2]] [+ [:p [:b [+ t 2]] 2]
                                [:m 5 [:b [+ t 2]]]  ]]]


     [m7/mx `[:p [:b [+ a b]] 2]]

     [m7/mx `[= y [+ [:p 7 2] [* 5 7]  ]]]



     [m7/mx `[= y [* 10 5.5]]]




     [m7/mx `[= 0.22 a]]
     [:div {:style {:color (hsl [2 33 33 1])}}
      [m7/mx `[= [:m s [:b [:k  t o]  ]]  y
               [:m 0.22 [:p [:k t o] 2]]]]]
     [:div {:style {:font-size "1.5rem"}}
      "let, time dt, where " [m7/mx `[= [:p dt 2]
                                      0]]]

     [:div {:style {:color (hsl [2 33 33 1])}}
      [m7/mx `[= [:m s [:b [+ t dt]  ]]    [:m 0.22 [:p
                                                     [:b [+ t dt]] 2]]]]]


     [:div {:style {:color (hsl [2 33 33 1])}}
      [m7/mx `[= [:m s [:b [+ t dt]  ]]
               [+ [:m 0.22 [:p
                            t 2]]
                [:m 0.22 [:p
                          dt 2]]

                [* 0.22 [:m 2 t dt]]]]]]

     [:div {:style {:color (hsl [2 33 33 1])}}
      [m7/mx `[= [:m s [:b [+ [:k t o] dt]  ]]
               [+ [:m 0.22 [:p [:k t o] 2]]
                [* 0.22 [:m 2 t dt]]]]]]

     [:div {:style {:color (hsl [2 33 33 1])}}
      [m7/mx `[= [:m s [:b [+ [:k t o] dt]  ]]
               [+ [:m s [:b [:k t o]  ]]
                [:m ds dt]]]]]


     [:div {:style {:color (hsl [2 33 33 1])}}
      [m7/mx `[= ds
               [* 0.22 [:m 2 t dt]]]]]


     [:div {:style {:color (hsl [2 33 33 1])}}
      [m7/mx `[= ds
               [* 0.22 [:m 2 t dt]]]]]

     [:div {:style {:color (hsl [2 33 33 1])}}
      [m7/mx `[= v [ds dt]
               [* 0.22 [:m 2 t ]]]]]


     #_(let [time-str sec2
             tmp-tmp (js/parseFloat (fix (* 0.22 time-str time-str) 2))]
         [m7/mx `[= ~tmp-tmp  [* 0.22 [:p
                                       ~(* 1 time-str ) 2]]]])



     [m7/m '[= [:p [:b [+ 10 5]] 2] [+ [:p 10 2] [:p 5 2] [* 2 10 5]]]]
     [m7/m '[= [2 9]  a]]


     [m7/m '[= [:m [* 10 15] m] [:m 15 s  v ]]]


     [m7/m '[= 450 [:m 45 k ]]
      ]
     ;;
     [m7/m '[- v u]]
     [m7/m '[= v [s t]  [m s] [:m m [:p s -1]]]]





     [m7/mx `[= [:k y 4] [:m [2 9] [:p 4 2]]]]
     [m7/mx `[= [:k y [+ t 2]] [:m [2 9] [:p  [:b [+ t 2]] 2]]]]

     [:div {:style {:font-size "2rem"}}
      "let ε, where " [m7/mx `[= [:p ~d 2]
                               0]]]

     [m7/mx `[= [:k y [+ t ~d]] [:m [2 9] [:p  [:b [+ t ~d]] 2]]]]

     [m7/mx `[= [:k y [+ t ~d]] [:m [2 9] [:b [+ [:p t 2] [:m 2 t ~d ] [:p ~d  2]]]]]]

     [m7/mx `[= [:k y [+ t ~d]] [+ [:m [2 9] [:p t 2]] [:m  [4 9] t ~d ] ]]]



     [m7/m [1 1]]
     [m7/m '[< [:p 0.00000000000000001 2] 0.00000000000000001 ]]

     [m7/mx `[= [:k y [+ t ~d]] [:m v [:b [+ t ~d] ]]]]

     [m7/mx `[= [:k y [+ t ~d]] [+ [:m v t] [:m v ~d]]]]

     [m7/mx `[= [:k y [+ t ~d]]
              [+ [:k y t] [:m [:p y .] ~d]]]]

     [m7/mx `[= [:k y [+ t ~d]]
              [+ [:k y t] [:m v ~d]]]]



     [m7/mx `[= ~d
              [:sq 0]]]]))
