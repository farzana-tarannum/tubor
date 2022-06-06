(ns math2.statements)


(comment
  #_[:div "A car weight 1000Kg moving right at 9 m/s and it strikes a stationary 2000Kg of truck. When the hit the truck they stuck together and they started moving together. what would be the final velocity"]


  #_[:div "let, velocity of the car and the trucks after collision is v"  ]


  #_[m7/m '[= [+ [* [:m 9 [m s]] [:m 1000 Kg]] 0]
            [:m [:b [:m [:b [+ 1000 2000]] Kg]] v]

            ]]

  #_[m7/m '[= v
            [:m 3 [m s] ]


            ]]


  #_[:div "break"]


  #_(reduce
     (fn [acc e]
       (if (some #(= e %)  ["broke"] )
         (conj acc
               [:span " "
                [:span {:style {:background-color (hsl [1 70 70 1])
                                :color (hsl [1 20 40 1])
                                :font-weight 600}}
                 (str  e)]])



         (conj acc [:span (str " "  e)])))

     [:div ]




     (str/split "last year covid situation broke out." #"\s+")))



#_(reduce
   (fn [acc e]
     (if (some #(= e %)  ["lay"] )
       (conj acc
             [:span " "
              [:span {:style {:background-color (hsl [1 70 70 1])
                              :color (hsl [1 20 40 1])
                              :font-weight 600}}
               (str  e)]])



       (conj acc [:span (str " "  e)])))

   [:div ]




   (str/split "Last night I was so tired and I lay down into the bed." #"\s+")

   )


#_(reduce
   (fn [acc e]
     (if (some #(= e %)  ["mede"] )
       (conj acc
             [:span " "
              [:span {:style {:background-color (hsl [1 70 70 1])
                              :color (hsl [1 20 40 1])
                              :font-weight 600}}
               (str  e)]])



       (conj acc [:span (str " "  e)])))

   [:div ]




   (str/split "Last month my mother made a pizza for us." #"\s+"))


#_(reduce
   (fn [acc e]
     (if (some #(= e %)  ["met"] )
       (conj acc
             [:span " "
              [:span {:style {:background-color (hsl [1 70 70 1])
                              :color (hsl [1 20 40 1])
                              :font-weight 600}}
               (str  e)]])



       (conj acc [:span (str " "  e)])))

   [:div ]




   (str/split "Last month I met my friends in the park." #"\s+"))

#_(reduce
   (fn [acc e]
     (if (some #(= e %)  ["was"] )
       (conj acc
             [:span " "
              [:span {:style {:background-color (hsl [1 70 70 1])
                              :color (hsl [1 20 40 1])
                              :font-weight 600}}
               (str  e)]])



       (conj acc [:span (str " "  e)])))

   [:div ]




   (str/split "Once up on a time there was a lady" #"\s+"))


#_(reduce
   (fn [acc e]
     (if (some #(= e %)  ["was"] )
       (conj acc
             [:span " "
              [:span {:style {:background-color (hsl [1 70 70 1])
                              :color (hsl [1 20 40 1])
                              :font-weight 600}}
               (str  e)]])



       (conj acc [:span (str " "  e)])))

   [:div ]
   (str/split "She was sick" #"\s+"))


(comment



          [:div "
A stake-out requires being inconspicuous, but not so for red-winged blackbirds.
Showing off and making noise is the main objective of their stake-out.
Male red-winged blackbirds have already staked-out their territories in wetlands and around ponds and lakes.
Their red-and-yellow shoulder patches add glints of color to the dull landscape.
And male red-wings don't hesitate to show their colors
When other birds invade the red-wing's territory, he spreads his tail, droops his wings, and lets out the gurgling Song.
In February and March, male red-wings come north from their southern wintering grounds
and find good spots for nesting and feeding.
They are probably our most numerous songbird.
"]



          [:img {:style {:width "30%"
                         }
                 :src "https://www.thespruce.com/thmb/l0_mNoIWGQuwtumjslpdZRgk9fg=/941x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/red-winged-blackbird-identification-385990-hero-bca4cabf8e9743e8b2459ce6421dc642.jpg"}])

(comment
  [:div {:style
              (m7/css
               [[2 8  3 18
                 :center :flex-start  2.2 :rem :column]
                [1.5 70 80  .3] []
                {:gap ".1rem"
                 :color (hsl [0 30 30 1])
                 :z-index 10}])}

        #_"lie lose make mean meet pay put run say sell send set sit speak spend stand take teach tell think understand wear win write "




        [:div "symbol"]
        #_[:div "exponent"]
        #_[:div "coefficent"]

        #_[:div "
Meaning: Distract; to disturb the concentration of
Example: Please be quiet. I’m trying to concentrate and you’re putting me off.
Put off

Meaning: Cause to dislike; to discourage (from doing)
Example: Almost drowning put him off swimming."]
        #_[m7/x `[= [+ [:m 2 x] 1] y]]

        #_[m7/x
           (m7/eq2 `[= [+ [+ [:m 8 [:p x 2]] x] 6
                        [:m 5 y] [:m 6 x y] [:m 9 [:p x 2] y]
                        [:m 2 y] [:m 3 x y] [:m 2 [:p x 2] y]

                        ] 0])
           ]

























        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["were"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 20 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))

         [:div ]

         (str/split "There were school teachers that were women" #"\s+"))


        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["was"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 20 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))

         [:div ]

         (str/split "My mother was angry" #"\s+"))


        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["was"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 20 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))

         [:div ]

         (str/split "My brother  was playing" #"\s+"))


        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["were"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 20 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))

         [:div ]

         (str/split "My friends were going to school" #"\s+"))


        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["were"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 20 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))

         [:div ]

         (str/split "They were happy" #"\s+"))


        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["was" "held"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 20 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))

         [:div ]

         (str/split "she was held responsible for careless driving" #"\s+"))


        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["was" "held"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 20 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))

         [:div ]

         (str/split "she held her baby with one hand" #"\s+"))


        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["was" "kept"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 20 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))

         [:div ]

         (str/split "she kept watching movies until it was midnight " #"\s+"))




        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["was" "led"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 20 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))

         [:div ]

         (str/split "In August of 2012, I led mjy first expedition on north pool." #"\s+"))













        #_(reduce
           (fn [acc e]
             (if (some #(= e %)  ["broke"] )
               (conj acc
                     [:span " "
                      [:span {:style {:background-color (hsl [1 70 70 1])
                                      :color (hsl [1 20 40 1])
                                      :font-weight 600}}
                       (str  e)]])



               (conj acc [:span (str " "  e)])))

           [:div ]




           (str/split "last year covid situation broke out." #"\s+"))


        #_["thought" "brought" "could" "was" "began" "kept" "delevered"
         "didn't," "deployed" "decided" "jumped"]
        #_(reduce
         (fn [acc e]
           (if (some #(= e %) ["brought" "thought" "could" "was" "proved" "began" "delevered" "kept"
                               "decided" "jumped" "deployed"])
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 20 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))
         [:div ]





         #_(str/split "About a decade ago, the government thought that if it brought this system online, it could save taxpayer dollars and proved a better service, it was a great idea. So, the typical government process began . Six years and 1.2 billion dollars later, no working product was delevered . At this point they could have kept pouring money into the failing program. Sadly that what often happens, that's the status quo today. But they didn't, the dedicated people inside the agency decided to stand up and call for change. We deployed a small team of just six people. The team jumped in side-by-side to support the agency in transitioning this project into more modern business practices." #"\s+"))


        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["thought" "brought" "could" "was" "began" "kept" "delevered"
                                "didn't," "deployed" "decided" "jumped"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 20 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))

         [:div ]




         (str/split "I felt very lucky because yesterday I nearly survived an
                       accident" #"\s+"))

        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["began"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 20 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))

         [:div ]
         (str/split "Yesterday I began our journey to school at 10:30 am" #"\s+"))



        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["saw" ""] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 40 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))

         [:div ]
         (str/split "I saw a beautiful bird on 31st December." #"\s+"))

        #_[m7/mx `[+ 3 [- [/ [:s [+ [* [:b [- 14 10]]
                                   [:b [- 20 15]]]
                                4]]
                         25] 4]
                 ]]

        #_[m7/mx `[+ 3 [- [/ [:s [+ [* 4 5] 4]] 25] 4]]]


        #_[m7/mx `[+ 3 [- [/ [:s [+ 20 4]] 25] 4]]]


        #_[m7/mx `[+ 3 [- 0.96 4]]]

        #_[m7/mx `[= [- 3.96 ~(symbol (str "4.00"))] -0.04]]
        #_[:div ""]
        #_[m7/mx '[357 9900]]

        #_[m7/mx '[- 3570000 35700]]
        #_[m7/mx '[* 357 [:b [-  2]]]]


        #_[m7/m 1176]

        #_[m7/mx '[* 14 [:b [+ 10000 1000]]]]

        #_[m7/mx '[+ 140000 14000]]

        #_[m7/m 154000]
        #_[m7/m '[* [:b [- 1000 1]] 12]]

        #_[m7/m '[* [:b [- 1000 1]] 12]]


        #_[m7/m '[= [- 12000 10 2] [- 11990 2]]]




        #_[m7/m '[= [* 6 3] k]]



        #_[m7/m '[= s  [[* 6 3] [- 7 2]]]]
        #_[:div "I " [:input {:type :text }]
         " my keys. could you please help me finding out the keys " ]
        #_[:textarea {:rows 4
                    :cols 50
                    :on-change (fn [e]
                              (set-text
                               (-> e
                                   .-target
                                   .-value)))}]])










#_(let [d 'ε]
           [:div {:style
                  (m7/css
                   [[2 10  4 15
                     :center :center  4.0 :rem :column]
                    [1.5 70 80  .5] []
                    {:gap ".1rem"
                     :color (hsl [0 30 60 1])
                     :z-index 10}])}
          #_[:div {:style {:font-size "1.1rem"}}
           [:div time-str]]
          #_[m7/mx `[= y [:m 10 t]]]
          #_[m7/mx `[= ~(* 10 time-str) [* 10 ~(* 1 time-str)]]]


          [m7/mx `[= y [+ [:p x 2] [:m 5 x]  ]]]
          #_(= 'x )

          #_(vec (clojure.walk/postwalk
                (fn [x]
                  (if (symbol? x)
                    (let [s (symbol (name x))]
                      (if (= s 'x)
                        3
                        s))

                    x))
                `[= y [+ [:p x 2] [:m 5 x]]]))



          [m7/mx `[:a x [+ x ~d]]]


          [m7/mx `[= [:k y [+ t 2]] [+ [:p [:b [+ t 2]] 2] [:m 5 [:b [+ t 2]]]  ]]]


          [m7/mx `[= [:k y [+ t 2]] [+ [:p [:b [+ t 2]] 2]
                                     [:m 5 [:b [+ t 2]]]  ]]]


          #_[m7/mx `[:p [:b [+ a b]] 2]]

          #_[m7/mx `[= y [+ [:p 7 2] [* 5 7]  ]]]



          #_[m7/mx `[= y [* 10 5.5]]]




          #_[m7/mx `[= 0.22 a]]
          #_[:div {:style {:color (hsl [2 33 33 1])}}
           [m7/mx `[= [:m s [:b [:k  t o]  ]]  y
                    [:m 0.22 [:p [:k t o] 2]]]]]
          #_[:div {:style {:font-size "1.5rem"}}
           "let, time dt, where " [m7/mx `[= [:p dt 2]
                                     0]]]

          #_[:div {:style {:color (hsl [2 33 33 1])}}
           [m7/mx `[= [:m s [:b [+ t dt]  ]]    [:m 0.22 [:p
                                                          [:b [+ t dt]] 2]]]]]


          #_[:div {:style {:color (hsl [2 33 33 1])}}
           [m7/mx `[= [:m s [:b [+ t dt]  ]]
                    [+ [:m 0.22 [:p
                                 t 2]]
                     [:m 0.22 [:p
                               dt 2]]

                     [* 0.22 [:m 2 t dt]]]]]]

          #_[:div {:style {:color (hsl [2 33 33 1])}}
           [m7/mx `[= [:m s [:b [+ [:k t o] dt]  ]]
                    [+ [:m 0.22 [:p [:k t o] 2]]
                     [* 0.22 [:m 2 t dt]]]]]]

          #_[:div {:style {:color (hsl [2 33 33 1])}}
           [m7/mx `[= [:m s [:b [+ [:k t o] dt]  ]]
                    [+ [:m s [:b [:k t o]  ]]
                     [:m ds dt]]]]]


          #_[:div {:style {:color (hsl [2 33 33 1])}}
           [m7/mx `[= ds
                    [* 0.22 [:m 2 t dt]]]]]


          #_[:div {:style {:color (hsl [2 33 33 1])}}
           [m7/mx `[= ds
                    [* 0.22 [:m 2 t dt]]]]]

          #_[:div {:style {:color (hsl [2 33 33 1])}}
           [m7/mx `[= v [ds dt]
                    [* 0.22 [:m 2 t ]]]]]


          #_(let [time-str sec2
                tmp-tmp (js/parseFloat (fix (* 0.22 time-str time-str) 2))]
            [m7/mx `[= ~tmp-tmp  [* 0.22 [:p
                                          ~(* 1 time-str ) 2]]]])



          #_[m7/m '[= [:p [:b [+ 10 5]] 2] [+ [:p 10 2] [:p 5 2] [* 2 10 5]]]]
          #_[m7/m '[= [2 9]  a]]


          #_[m7/m '[= [:m [* 10 15] m] [:m 15 s  v ]]]


          #_[m7/m '[= 450 [:m 45 k ]]
             ]
          ;;
          #_[m7/m '[- v u]]
          #_[m7/m '[= v [s t]  [m s] [:m m [:p s -1]]]]





          #_[m7/mx `[= [:k y 4] [:m [2 9] [:p 4 2]]]]
          #_[m7/mx `[= [:k y [+ t 2]] [:m [2 9] [:p  [:b [+ t 2]] 2]]]]

          #_[:div {:style {:font-size "2rem"}}
           "let ε, where " [m7/mx `[= [:p ~d 2]
                                    0]]]

          #_[m7/mx `[= [:k y [+ t ~d]] [:m [2 9] [:p  [:b [+ t ~d]] 2]]]]

          #_[m7/mx `[= [:k y [+ t ~d]] [:m [2 9] [:b [+ [:p t 2] [:m 2 t ~d ] [:p ~d  2]]]]]]

          #_[m7/mx `[= [:k y [+ t ~d]] [+ [:m [2 9] [:p t 2]] [:m  [4 9] t ~d ] ]]]



          #_[m7/m [1 1]]
          #_[m7/m '[< [:p .00000000000000001 2] .00000000000000001 ]]

          #_[m7/mx `[= [:k y [+ t ~d]] [:m v [:b [+ t ~d] ]]]]

          #_[m7/mx `[= [:k y [+ t ~d]] [+ [:m v t] [:m v ~d]]]]

          #_[m7/mx `[= [:k y [+ t ~d]]
                   [+ [:k y t] [:m [:p y .] ~d]]]]

          #_[m7/mx `[= [:k y [+ t ~d]]
                   [+ [:k y t] [:m v ~d]]]]



          #_[m7/mx `[= ~d
                     [:sq 0]]]









            ])


#_(map
   (fn [n d]
     [:div {:style (m7/css
                    [[3 1 (+ 2 (* n 2)) 2  :center :center  1.5 :rem :column]
                     [(* n .2) 70 (+ 10 (* 1 n))  .2] [] {:gap ".1rem"
                                                          :z-index 4}])}

      d])
   (range 0 11)
   (map (fn [i]
          (js/Math.pow 2 i))
        (range 0 11))

   )

#_(map
   (fn [n d]
     [:div {:style (m7/css
                    [[4 1 (+ 2 (* n 2)) 2  :center :center  1.5 :rem :column]
                     [1 70 70  .9] [] {:gap ".1rem"
                                       :z-index 4}])}

      d])
   (range 0 11)
   (map (fn [i]
          [m7/mx `[:p 2 ~i]])
        (range 0 11)))


#_[:div {:style (m7/css
                 [[5 4 2 8 :center :center 1.8 :rem :column]
                  [1 90 90 .01] []
                  (into
                   {:gap "1rem"
                    :font-family "Roboto Flex"
                    :color (hsl [0 60 60 1])
                    :z-index 2}
                   )
                  ])
         }

   #_{:font-family "'Roboto Flex'"
      :font-variation-settings
      (fontf2
       (update
        var-vf
        :wght
        (fn [k] (+ k 100))))}
   #_[:div {:style {}}  ""]

   [m7/m '[= [:p AB 2]
           [+ [:p BC 2]  [:p AC 2]]]]


   [m7/m '[= [:p AB 2]
           [+ [:p 14 2]  [:p 7 2]]]]
   #_(js/Math.sqrt (+ 49 (* 16 16)))
   [m7/m '[= AB
           [:sq [+ [:p 16 2]  [:p 7 2]]]]]
   ]


#_[:div {:style (m7/css
               [[8 5 1 18 :center :center 2.2 :rem ]
                [1 70 90 .1] [] {:gap "1rem"
                                 :color (hsl [3 20 30 1])
                                 :z-index 2}])}

 #_[:span {:style {:background-color (hsl [1 70 70 .5])}}"9th October"]








 ]


#_[:div {:style
       (m7/css
        [[2 8  3 18
          :center :flex-start  5.3 :rem :column]
         [1.5 70 80  .3] []
         {:gap ".1rem"
          :color (hsl [0 30 30 1])
          :z-index 10}])}
 [m7/x `[= [[:p x m] [:p x n]]
         [:p x [- m n]]]
  ]

 [m7/x `[= [:m [:p x m] [:p x n]]
         [:p x [+ m n]]]]



 [m7/x `[= [1 [:p x n]]
         [:p x [- n]]]]




 ]
