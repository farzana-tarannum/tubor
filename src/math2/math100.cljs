(ns math2.math100
  (:require
   [math2.file :as file]
   [react]
   [clojure.string :as str]
   [math2.math7 :as m7 :refer
    [grid hsl css space size path ve sec]]))


(def f
  (fn [m n] (/ m n)))
(def square [1 0 0 1 (ve 1) 0 0 (ve 1)])

(defn alkine [n1]
  [:g {:transform (m7/tranfrom [[:scale [.7 .7]]
                                         [:rotate 10]])}



           (let [n n1]
              [:g

               [:foreignObject {:x 20
                                :y -150
                                :height 50
                                :width 450
                                :style {:font-size "1.7rem"}}
                [m7/m [:m [:k 'C n] [:k 'H ['+ ['* 2 n] 2]]]]

                #_[m7/m ['= ['+ [:m [:k 'C n] [:k 'H  (+ (* 2 n) 2)]] [:m 2[:k 'O 2]] ]
                       ['+ [:m 'C [:k 'O 2]] [:m 2 [:k 'H 2] 'O]]
                       ]]
                ]

               [:text {:x 250
                       :y -220
                       :style {:font-size "2rem"}}
                (nth ["methane" "ethane" "propane" "butane" "pentane" "hexane" "heptane" "octane"]
                     (- n 1))]
               (map
                (fn [i j]
                  [:g
                   (if (not (= i 0))
                     [:g

                      #_[:path {:d (path
                                    [(-  (* i 75) 60 ) -10 :l  40 0])
                                :stroke (hsl [4 70 70 1])
                                :stroke-width 2
                                :fill (hsl [5 70 70 .2])}]


                      [:path {:d (path [(-  (* i 75) 60 ) 0 :l  40 0])
                              :stroke (hsl [4 70 70 1])
                              :stroke-width 2
                              :fill (hsl [5 70 70 .2])}]

                      #_[:path {:d (path [(-  (* i 75) 60 ) 10 :l  40 0])
                                :stroke (hsl [4 70 70 1])
                                :stroke-width 2
                                :fill (hsl [5 70 70 .2])}]]
                     [:g ])


                   [:path {:d (path [(* i 75)
                                     0 :l 0 40  ])
                           :stroke (hsl [4 70 70 1])
                           :stroke-width 2
                           :fill (hsl [5 70 70 .2])}]


                   [:path {:d (path [(* i 75)
                                     0 :l 0 -40  ])
                           :stroke (hsl [4 70 70 1])
                           :stroke-width 2
                           :fill (hsl [5 70 70 .2])}]


                   [:circle {:cx (* i 75)
                             :cy 0
                             :r 20
                             ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                             :fill (hsl [(/ i 5) 60 70 1])}]
                   [:text {:x (* i 75)
                           :y 0
                           :dy 7
                           :dx -7
                           ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                           :style {:font-size "1.5rem"}}
                    "C"]

                   [:circle {:cx (* i 75)
                             :cy 50
                             :r 12
                             ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                             :fill (hsl [(/ i 5) 60 70 1])}]
                   #_[:text {:x (* i 75)
                           :y 55
                           :dy 0
                           :dx -10
                           ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                           :style {:font-size "1.5rem"}}
                      "H"]

                   [:circle {:cx (* i 75)
                             :cy -40
                             :r 12
                             :dy -5
                             :dx -10
                             :fill (hsl [(/ i 5) 60 70 1])
                             ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                             }
                    ]

                   #_[:text {:x (* i 75)
                             :y -40
                             :dy 0
                             :dx -10
                             ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                             :style {:font-size "1.5rem"}}
                      "H"]

                   ])
                (range 0 n))

               [:path {:d (path [(-  (* n 75) 60 ) 0 :l  40 0])
                       :stroke (hsl [4 70 70 1])
                       :stroke-width 2
                       :fill (hsl [5 70 70 .2])}]

               [:path {:d (path [(-  (* 0 75) 60 ) 0 :l  40 0])
                       :stroke (hsl [4 70 70 1])
                       :stroke-width 2
                       :fill (hsl [5 70 70 .2])}]

               [:text {:x -75
                       :y 0
                       :dy 10
                       :dx 0
                       ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                       :style {:font-size "1.5rem"}}
                "H"]

               [:text {:x (* n 75)
                       :y 0
                       :dy 10
                       :dx -18
                       ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                       :style {:font-size "1.5rem"}}
                "H"]
               ])
   ])



(defn alkine-oh [n1]
  [:g {:transform (m7/tranfrom [[:scale [.7 .7]]
                                         [:rotate 10]])}



           (let [n n1]
              [:g

               [:foreignObject {:x 20
                                :y -150
                                :height 50
                                :width 450
                                :style {:font-size "1.7rem"}}
                [m7/m [:m [:k 'C n] [:k 'H ['+ ['* 2 n] 2]]]]

                #_[m7/m ['= ['+ [:m [:k 'C n] [:k 'H  (+ (* 2 n) 2)]] [:m 2[:k 'O 2]] ]
                       ['+ [:m 'C [:k 'O 2]] [:m 2 [:k 'H 2] 'O]]
                       ]]
                ]

               [:text {:x 250
                       :y -220
                       :style {:font-size "2rem"}}
                (nth ["methanol" "ethanol" "propane" "butane" "pentane" "hexane" "heptane" "octane"]
                     (- n 1))]
               (map
                (fn [i j]
                  [:g
                   (if (not (= i 0))
                     [:g

                      #_[:path {:d (path
                                    [(-  (* i 75) 60 ) -10 :l  40 0])
                                :stroke (hsl [4 70 70 1])
                                :stroke-width 2
                                :fill (hsl [5 70 70 .2])}]


                      [:path {:d (path [(-  (* i 75) 60 ) 0 :l  40 0])
                              :stroke (hsl [4 70 70 1])
                              :stroke-width 2
                              :fill (hsl [5 70 70 .2])}]

                      #_[:path {:d (path [(-  (* i 75) 60 ) 10 :l  40 0])
                                :stroke (hsl [4 70 70 1])
                                :stroke-width 2
                                :fill (hsl [5 70 70 .2])}]]
                     [:g ])


                   [:path {:d (path [(* i 75)
                                     0 :l 0 40  ])
                           :stroke (hsl [4 70 70 1])
                           :stroke-width 2
                           :fill (hsl [5 70 70 .2])}]


                   [:path {:d (path [(* i 75)
                                     0 :l 0 -40  ])
                           :stroke (hsl [4 70 70 1])
                           :stroke-width 2
                           :fill (hsl [5 70 70 .2])}]


                   [:circle {:cx (* i 75)
                             :cy 0
                             :r 20
                             ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                             :fill (hsl [(/ i 5) 60 70 1])}]
                   [:text {:x (* i 75)
                           :y 0
                           :dy 7
                           :dx -7
                           ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                           :style {:font-size "1.5rem"}}
                    "C"]

                   [:circle {:cx (* i 75)
                             :cy 50
                             :r 12
                             ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                             :fill (hsl [(/ i 5) 60 70 1])}]
                   #_[:text {:x (* i 75)
                           :y 55
                           :dy 0
                           :dx -10
                           ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                           :style {:font-size "1.5rem"}}
                      "H"]

                   [:circle {:cx (* i 75)
                             :cy -40
                             :r 12
                             :dy -5
                             :dx -10
                             :fill (hsl [(/ i 5) 60 70 1])
                             ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                             }
                    ]

                   #_[:text {:x (* i 75)
                             :y -40
                             :dy 0
                             :dx -10
                             ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                             :style {:font-size "1.5rem"}}
                      "H"]

                   ])
                (range 0 n))

               [:path {:d (path [(-  (* n 75) 60 ) 0 :l  40 0])
                       :stroke (hsl [4 70 70 1])
                       :stroke-width 2
                       :fill (hsl [5 70 70 .2])}]

               [:path {:d (path [(-  (* 0 75) 60 ) 0 :l  40 0])
                       :stroke (hsl [4 70 70 1])
                       :stroke-width 2
                       :fill (hsl [5 70 70 .2])}]

               [:text {:x -75
                       :y 0
                       :dy 10
                       :dx 0
                       ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                       :style {:font-size "1.5rem"}}
                "H"]

               [:text {:x (* n 75)
                       :y 0
                       :dy 10
                       :dx -28
                       ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                       :style {:font-size "1.5rem"}}
                "OH"]
               ])
            ])

(defn grid-on
  ([] (grid-on 5 5))
  ([X Y]
   (grid-on X Y 40 60))
  ([X Y ax-dx ax-dy]
   (let [zoom 0
         cor [[5 2] [10 9] [15 19] [20 34] [25 58] [30 95] [32 125]]
         ts (into ["t(s)"] [ 1 2 3 4 5 6 6.3])
         vms (into ["v(m/s)"] [ 1 4.5 9.5 17 29 47 62.5])
         vb (fn [z]
              (nth [
                    [0 -200  400 400]
                    [0 -180  200 200]
                    [0 -50  100 100]
                    [0 -25  50 50]
                    [-100 -200  800 200]

                    [-100 -200 200 100]
                    [40 120  80 80]
                    [0 40  100 100]
                    [75 -175  150 150]
                    [-20 -20  100 100]
                    ] z))
         viewbox (vb zoom)
         viewbox2 (vb zoom)
         st (fn [aa row]
              {:style (m7/css
                       [[row 1 (+ 3 (* aa 1)) 1  :center :center  2 :rem :column]
                        [(* 3 .2) 70 (+ 50 (* 5 5))  .7] []
                        {:gap ".1rem"
                         :z-index 10}])})]
     [:g

      ;; small
      (map
       (fn [i]
         [:path {:d (path [-400 (ve (+ ax-dx (* i 2))) :l 1200 0])
                 :stroke (hsl [4 70 70 .5])
                 :stroke-width .5
                 :fill :none}
          ] )
       (range 0 11))


      (map
       (fn [i]
         [:path {:d (path [(+ ax-dy (* i 2)) -400 :l 0 1200 ])

                 :stroke (hsl [4 70 70 .5])
                 :stroke-width .5
                 :fill :none}
          ] )
       (range 0 11))




      (map
       (fn [x]
         [:g

          [:path {:d (path [ (* 20 x)  0 :l 0 -400])

                  :stroke (hsl [(if (= x -2)  5 0) 70 70 1])
                  :stroke-width .5
                  :fill :none}
           ]


          [:path {:d (path [ (* 20 x)  0 :l 0 400])

                  :stroke (hsl [0 70 70 1])
                  :stroke-width .5
                  :fill :none}
           ]

          [:text {:x (* 20 x)
                  :y 5
                  :style {:font-size "0.4rem"}}
           (* x X)]])
       (range -20 40))



      (map
       (fn [y]
         [:g
          [:path {:d (path [0 (ve (* 20 y))   :l 1200 0])

                  :stroke (hsl [1 70 70 1])
                  :stroke-width 1
                  :fill :none}
           ]


          [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                  :stroke (hsl [1 70 70 1])
                  :stroke-width 1
                  :fill :none}
           ]

          [:text {:x -10
                  :y (ve (* 20 y))
                  :style {:font-size "0.4rem"}}
           #_(.toFixed (* y Y) 1)
           (* y Y)
           ]])
       (range -20 20))

      ])))

(defn flames []
  [:filter#flames
            {:height "300%",
             :width "100%",
             :y "-100%",
             :x "0%",
             :filterunits "objectBoundingBox"}
            [:feTurbulence
             {:stitchtiles "stitch",
              :result "noise",
              :numoctaves "2",
              :basefrequency "0.2",
              :type "fractalNoise"}]
            [:feOffset
             {:result "off1", :dy "0"}
             [:animate
              {:repeatcount "indefinite",
               :dur "6s",
               :to "-300",
               :from "0",
               :attributename "dy",
               :attributetype "XML"}]]
            [:feOffset
             {:result "off2", :dy "60", :in "noise"}
             [:animate
              {:repeatcount "indefinite",
               :dur "6s",
               :to "0",
               :from "300",
               :attributename "dy",
               :attributetype "XML"}]
             ]
            [:feMerge
             {:result "scrolling-noise"}
             [:feMergeNode {:in "off1"}]
             [:feMergeNode {:in "off2"}]]
            [:feComponentTransfer
             {:result "brighter-noise"}
             [:feFuncA {:exponent "0.5", :amplitude "1", :type "gamma"}]]
            [:feComposite
             {:result "gradient-noise",
              :operator "in",
              :in2 "brighter-noise",
              :in "SourceGraphic"}]
            [:feComponentTransfer
             {:result "threshhold"}
             [:feFuncR {:tablevalues "0 1", :type "discrete"}]
             [:feFuncG {:tablevalues "0 1", :type "discrete"}]
             [:feFuncB {:tablevalues "0 1", :type "discrete"}]
             [:feFuncA {:tablevalues "0 1", :type "discrete"}]]
            [:feFlood {:result "yellow", :flood-color "#ff9"}]
            [:feComposite
             {:result "yellow-threshhold",
              :operator "in",
              :in "yellow",
              :in2 "threshhold"}]
            [:feFlood {:result "red", :flood-color "#f33"}]
            [:feComponentTransfer
             {:result "exponent-gradient", :in "SourceGraphic"}
             [:feFuncA {:exponent "3", :type "gamma"}]]
            [:feComposite
             {:result "red-gradient",
              :operator "in",
              :in2 "exponent-gradient",
              :in "red"}]
            [:feComposite
             {:result "red-gradient-threshhold",
              :operator "in",
              :in "red-gradient",
              :in2 "threshhold"}]
            [:feMerge
             [:feMergeNode {:in "yellow-threshhold"}]
             [:feMergeNode {:in "red-gradient-threshhold"}]]])


(defn marker [a]
  [:marker {:id a
            :refY 0
            :refX 0
            :orient :auto
            :style {:overflow :visible}}
   [:path {:d (m7/path [0 0 :L 5 -5
                        :L -12.5 0
                        :L 3 5 :L 0 0])
           :style {:fill-rule :evenodd
                   :stroke (m7/hsl [1 70 70 1])
                   :stroke-width 1
                   :stroke-opacity 1
                   :fill (m7/hsl [3 70 70 1])
                   :fill-opacity 1}
           :transform (m7/tranfrom [[:scale .4]
                                    [:rotate 180]
                                    [:translate [12.5 0]]])
           }]]
  )

;; 01779298654 ref
(defn template []
  (let [sq2 [1 0 0 (f 3 4) (ve 1) 0 0 (ve (f 3 4))]
        st {:style {:display :flex
                    :width "100%"
                    :height "100%"
                    :justify-content :center
                    :align-items :center}}
        bm 30
        bf (fn [n] (* bm (/ 1 n)))
        base [(ve (bf 2)) (ve (bf 2))]
        base2 [(ve (+ (bf 8) (bf 2))) (ve (+ (bf 8) (bf 2)))]
        bm2 (/ bm 2)
        angle-x 0
        angle-y 15

        o ['t
           [m7/m ['* 1 1]]
           [m7/m [100 100]]
           [:div st [m7/m [:m 100 '%]]]]
        f [ [m7/m '[t f]]
           [:div [m7/m [1 'f]] ""]
           [:div [m7/m ['p 100]]  ""]
           [:div st
            [m7/m [:m 'p '%]]]]
        n 0
        n2 3
        one (nth o n)
        frac (nth f n)
        one2  (nth o n2)
        frac2 (nth f n2)
        scl (nth [4.2 1.5 1.5 7.5] n)
        scl2 (nth [4.2 1.5 1.5 4.5] n2)]
    [:div {:style
           (merge
            (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 15 (repeat [8 :vh]))])
            {:background-color (hsl [1.4 70 70 .8])})}

     [:div {:key (gensym)
            :style (css
                    [[2 2 2 11 :center :center 3 :rem :column]
                     [.5 70 70 .3] []
                     {:z-index 23}]
                    )}



      [m7/m '[= [:m p %] [p 100] [[p 100] [p 100]]  [[1 f] 1] [[t f] t] [fr t] ]]

      [m7/m '[=
              [p 100] [fr t] ]]










      [:div {:key (gensym)
             :style (css
                     [[4 11 2 11 :center :center 2 :rem]
                      [.5 70 70 .9] []
                      {:z-index 22}]
                     )}
       [:svg {:viewBox (space (nth [[-100  -100 200 200 ]
                                    [-10 -160 80 80]] 0))
              :style {:height (size {:size 100 :scale :%})
                      :width  (size {:size 100 :scale :%})}}


        [:path {:d (path (flatten
                          [base
                           :l (map
                               (partial * bm)
                               square)]))
                :stroke (hsl [0.5 50 50 1])
                :stroke-width .1
                :fill (hsl [4 70 70 .9])}
         [:animateTransform
          {:attributeName :transform
           :begin (sec 0)
           :dur (sec 1)
           :type :scale
           :from 3
           :to scl
           :fill :freeze
           :id :r1
           }]]

        [:path {:d (path (flatten
                          [base
                           :l (map
                               (partial * (bf 2))
                               square)]))
                :stroke (hsl [1.7 50 50 1])
                :stroke-width .1
                :fill (hsl [3.5 70 70 .9])}
         [:animateTransform
         {:attributeName :transform
           :begin :r1.begin
           :dur (sec 1)
           :type :scale
           :from 3
           :to scl
           :fill :freeze
           :id :r2
           }]]



        [:path {:d (path [(ve bm2) 0 :l bm 0])
                :stroke (hsl [3.3 70 70 1])
                :stroke-width 1
                :fill :none
                :stroke-dasharray 2
                }]

        [:circle {:cx 0
                  :cy 0
                  :r 1
                  :stroke (hsl [1.8 70 70 1])
                  :fill (hsl [2.8 70 70 1])}]


        [:foreignObject {:x 0
                         :y 0
                         :font-size ".4rem"
                         :width (* 4 (bf 2))
                         :height (* 4 (bf 2))} one]



        [:foreignObject {:x (ve (bf 2))
                         :y (ve (bf 2))
                         :style {:font-size ".4rem"}
                         :width (bf 2)
                         :height (bf 2)} frac]


        ]]]


     [:div {:key (gensym)
            :style (css
                    [[3 6 10 6 :center :center 2 :rem]
                     [.5 70 70 .5] []
                     {:z-index 22}]
                    )}

      [:svg {:viewBox (space (nth [[-100  -100 200 200 ]
                                     [-10 -160 80 80]] 0))
               :style {:height (size {:size 100 :scale :%})
                       :width  (size {:size 100 :scale :%})}}




         [:path {:d (path (flatten
                           [base
                            :l (map
                                (partial * bm)
                               square)]))
                 :stroke (hsl [0.5 50 50 1])
                 :stroke-width .1
                 :fill (hsl [4 70 70 .9])}
          [:animateTransform
           {:attributeName :transform
            :begin (sec 0)
            :dur (sec 1)
            :type :scale
            :from 3
            :to scl2
            :fill :freeze
            :id :r1
            }]]


         [:path {:d (path (flatten
                           [base
                            :l (map
                                (partial * (bf 2))
                                square)]))
                 :stroke (hsl [1.7 50 50 1])
                 :stroke-width .1
                 :fill (hsl [3.5 70 70 .9])}
          [:animateTransform
           {:attributeName :transform
            :begin :r1.begin
            :dur (sec 1)
            :type :scale
            :from 3
            :to scl2
            :fill :freeze
            :id :r2
            }]]

         [:path {:d (path [(ve bm2) 0 :l bm 0])
                 :stroke (hsl [3.3 70 70 1])
                 :stroke-width 1
                 :fill :none
                 :stroke-dasharray 2
                 }]

         [:circle {:cx 0
                   :cy 0
                   :r 1
                   :stroke (hsl [1.8 70 70 1])
                   :fill (hsl [2.8 70 70 1])}]

       [:foreignObject {:x 0
                        :y 0
                        :font-size ".4rem"
                        :width (* 5 (bf 2))
                        :height (* 5 (bf 2))} one2]

       [:foreignObject {:x -100
                        :y -100
                        :font-size ".4rem"
                        :width (* 8 (bf 2))
                        :height (* 8 (bf 2))} frac2]


       ]]

     ]))



(defn template2b []
  [:div {:style
         (merge
          (grid [100 :vh 100 :vw
                 (take 15 (repeat [8 :vh]))
                 (take 15 (repeat [8 :vh]))])
          {:background-color (hsl [1.4 70 70 .8])})}



        (map-indexed

      (fn [i [n c]]
        [:div {:key (gensym)
               :style (css
                       [[(+ i 2) 1 (+ 4 (* 3 0)) 3 :center :center 2 :rem :column]
                        [c 70 70 .8] []
                        {:z-index 2}]
                       )}


         n])
      [["Load (N)" 7.3]
       ["Length (mm)" .7]
       ["Extersion (mm)" 3.5]])


     (map-indexed
      (fn [j a]

        (map-indexed

         (fn [i [n c]]
           [:div {:key (gensym)
                  :style (css
                          [[(+ 2 i) 1 (+ 5 (* 2 (inc j))) 2 :center :center 2 :rem :column]
                           [c 70 70 .8] []
                           {:z-index 2}]
                          )}


            n])
         a)
        )
      (take 1
            [
             [["0" 7.2]
              ["50" .5]
              ["0" 3.5]
              ]
             [["1" 7.2]
              ["58" .5]
              ["8" 3.5]
              ]

             [["2" 7.2]
              ["70" .5]
              ["20" 3.5]
              ]

             [["3" 7.2]
              ["74" .5]
              ["24" 3.5]
              ]
             [["4" 7.2]
              ["82" .5]
              ["32" 3.5]
              ]
             [["5" 7.2]
              ["90" .5]
              ["40" 3.5]
              ]

             [["6" 7.2]
              ["102" .5]
              ["52" 3.5]
              ]

             [["7" 7.2]
              ["125" .5]
              ["75" 3.5]
              ]
             ])
      )






   [:div {:key (gensym)
            :style (css
                    [[2 11 2 11 :center :center 2 :rem]
                     [.5 70 70 .5] []
                     {:z-index 30}]
                    )}
    [:svg {:viewBox (space  [-5  -180 200 200 ])
               :style {:height (size {:size 100 :scale :%})
                       :width  (size {:size 100 :scale :%})}}
     (marker "a")
     [:g {:transform (m7/tranfrom [[:rotate 0]])}


      [:path {:d (path [ 0 0 :l 180 0  ])
              :stroke (hsl [0 70 70 1])
              :marker-end (m7/url "i")
              :stroke-width 1
              :fill :none}]

      [:path {:d (path [0 0 :l 0 -180])
              :marker-end (m7/url "i")
              :stroke (hsl [1 70 70 1])
              :stroke-width 1
              :fill :none}]

      [:path {:d (path [0 0 :l (* 20 5) (ve (* 40 2))])

              :stroke (hsl [7 70 70 1])
              :stroke-width 2
              :fill :none}]

      ]

     (map
      (fn [n]
        [:text {:x (* n 20)
                :y 4
                :style {:font-size ".2rem"}}
         n])
      (range 0 10))




     (map
      (fn [n]
        [:text {:x 0
                :y (ve (* n 20))
                :style {:font-size ".2rem"}}
         (* n 10)])
      (range 0 10))






     (map
      (fn [[x y]]
        [:circle {:cx (* x 20) :cy (ve (* y 2)) :r 1 :fill (hsl [1 70 70 1])}])
      (take 1
            [[1 8]
             [2 20]
             [3 24]
             [4 32]
             [5 40]

             [6 52]
             [7 75]
             ]))






     ]]

   [:div {:key (gensym)
            :style (css
                    [[2 11 10 11 :center :center 2 :rem]
                     [.5 70 70 .5] []
                     {:z-index 30}]
                    )}
    [:svg {:viewBox (space  [-100  -10 200 200 ])
               :style {:height (size {:size 100 :scale :%})
                       :width  (size {:size 100 :scale :%})}}
     (marker "a")
     [:g {:transform (m7/tranfrom [[:rotate 0]
                                   ])}




      [:path {:d (path [0 0
                        :a 2 1 0 false false  0 10
                        :a 2 1 0 false true  0 10
                        :a 2 1 0 false false  0 10
                        :a 2 1 0 false true  0 10
                        :a 2 1 0 false false  0 10
                        :a 2 1 0 false true  0 10
                        :a 2 1 0 false false  0 10
                        :a 2 1 0 false true  0 10
                        :a 2 1 0 false false  0 10
                        :a 2 1 0 false true  0 10
                        :a 2 1 0 false false  0 10
                        :a 2 1 0 false true  0 10
                        :l 0 10
                        :a 1 1 0 false false  0 20
                        :a 1 1 0 false false  0 -20
                        ])
              :stroke (hsl [2 70 70 1])
              :stroke-width 1
              :fill :none}]






      [:path {:d (path [-40 0
                        :l 0 115
                        ])
              :marker-end (m7/url "i")
              :stroke (hsl [2 70 70 1])
              :stroke-width .5
              :fill :none}]







      [:text {:x 40
              :y 100
              :style {:font-size ".4rem"}}
       ""]

      #_[:circle {:cx 0 :cy 135 :r 7
                :stroke (hsl [2 70 70 1])
                :stroke-width 1
                :fill (hsl [3 70 70 1])}]

      ]





     #_[:circle {:cx 0 :cy 0 :r 3 :fill (hsl [1 70 70 1])}]




     [:text {:x 0
             :y 135
             :style {:font-size ".4rem"}}
      "2N"]

     ]]






   ])



(defn template2 []
  [:div {:style
         (merge
          (grid [100 :vh 100 :vw
                 (take 15 (repeat [8 :vh]))
                 (take 15 (repeat [8 :vh]))])
          {:background-color (hsl [1.4 70 70 .8])})}



        (map-indexed

         (fn [i [n c]]
           [:div {:key (gensym)
               :style (css
                       [[(+ i 2) 1 (+ 3 (* 3 0)) 3 :center :center 2 :rem :column]
                        [c 70 70 .7] []
                        {:z-index 8}]
                       )}


         n])
         [["F (N)" 7.3]
          ["L (mm)" .7]
          ["d (mm)" 3.5]])


     (map-indexed
      (fn [j a]
        (map-indexed

         (fn [i [n c]]
           [:div {:key (gensym)
                  :style (css
                          [[(+ 2 i) 1 (+ 5 (* 1 (inc j))) 1 :center :center 2 :rem :column]
                           [c 70 70 .7] []
                           {:z-index 8}]
                          )}


            n])
         a)
        )
      [
       [["0" 7.2]
        ["50" .5]
        ["0" 3.5]
        ]
       [["1" 7.2]
        ["58" .5]
        ["8" 3.5]
        ]

       [["2" 7.2]
        ["70" .5]
        ["20" 3.5]
        ]

       [["3" 7.2]
        ["74" .5]
        ["24" 3.5]
        ]
       [["4" 7.2]
        ["82" .5]
        ["32" 3.5]
        ]
       [["5" 7.2]
        ["90" .5]
        ["40" 3.5]
        ]

       [["6" 7.2]
        ["102" .5]
        ["52" 3.5]
        ]

       [["7" 7.2]
        ["125" .5]
        ["75" 3.5]
        ]
       ]

      )






   [:div {:key (gensym)
            :style (css
                    [[2 11 2 11 :center :center 2 :rem]
                     [.5 70 70 .5] []
                     {:z-index 2}]
                    )}
    [:svg {:viewBox (space  [-5  -180 200 200 ])
               :style {:height (size {:size 100 :scale :%})
                       :width  (size {:size 100 :scale :%})}}
     (marker "a")
     [:g {:transform (m7/tranfrom [[:rotate 0]])}


      [:path {:d (path [ 0 0 :l 180 0  ])
              :stroke (hsl [0 70 70 1])
              :marker-end (m7/url "i")
              :stroke-width 1
              :fill :none}]

      [:path {:d (path [0 0 :l 0 -180])
              :marker-end (m7/url "i")
              :stroke (hsl [1 70 70 1])
              :stroke-width 1
              :fill :none}]

      [:path {:d (path [0 0 :l (* 20 5) (ve (* 40 2))])

              :stroke (hsl [7 70 70 1])
              :stroke-width 2
              :fill :none}]

      ]

     (map
      (fn [n]
        [:text {:x (* n 20)
                :y 4
                :style {:font-size ".2rem"}}
         n])
      (range 0 10))




     (map
      (fn [n]
        [:text {:x 0
                :y (ve (* n 20))
                :style {:font-size ".2rem"}}
         (* n 10)])
      (range 0 10))






     (map
      (fn [[x y]]
        [:circle {:cx (* x 20) :cy (ve (* y 2)) :r 1 :fill (hsl [1 70 70 1])}])
      (take 1
            [[1 8]
             [2 20]
             [3 24]
             [4 32]
             [5 40]

             [6 52]
             [7 75]
             ]))






     ]]

   [:div {:key (gensym)
            :style (css
                    [[2 11 14 11 :center :center 2 :rem]
                     [.5 70 70 .5] []
                     {:z-index 30}]
                    )}
    [:svg {:viewBox (space  [-100  -10 200 200 ])
               :style {:height (size {:size 100 :scale :%})
                       :width  (size {:size 100 :scale :%})}}
     (marker "a")
     [:g {:transform (m7/tranfrom [[:rotate 0]])}




      [:path {:d (path (flatten
                        [0 0

                         (repeat 10
                                 [
                                  :a 2 1 0 false false  0 10
                                  :a 2 1 0 true false  0 -3])
                         :a 2 1 0 false false  0 10
                         :l 0 7
                         :a 1.5 1 0 false true   0 3
                         :a 1.5 1 0 false true   0 -3
                         ]))
              :stroke (hsl [2 70 70 1])
              :stroke-width 2
              :fill :none}
       [:animate
        {:attributeName :d
         :begin :ccr.begin
         :dur (sec 1)
         :keyTimes (m7/sami-colon [0 0.5 0.8 1])
         :values (m7/sami-colon
                  (map
                   (fn [i]
                     (path (flatten
                            [0 0
                            (repeat 10
                                     [
                                      :a 2 1 0 false false  0 (+ 10 i)
                                      :a 2 1 0 true false  0 -3])
                             :a 2 1 0 false false  0 (+ i 10)
                             :l 0 7
                             :a 1.5 1 0 false true   0 3
                             :a 1.5 1 0 false true   0 -3
                             ]))
                     )
                   [0 .50 1 1.50]
                   ))

         :fill :freeze
         }]]


      [:path {:d (path (flatten
                        [0 (* 7 12)
                         :a 1 1 0 false false  0 20
                         :a 1 1 0 false false  0 -20
                         ]))
              :stroke (hsl [2 70 70 1])
              :stroke-width 2
              :fill (hsl [2 70 70 1])}

       [:animateTransform
        {:id :ccr
         :attributeName :transform
         :begin :click
         :dur (sec 1)
         :type :translate
         :keyTimes (m7/sami-colon [0 0.5 0.8 1])
         :values (m7/sami-colon (map m7/space
                                     [
                                      [0 0] [0 (* .5 10)]
                                      [0 (* 1 10)] [0 (* 1.5 10)]]))
         :fill :freeze
         }]
       ]
      [:path {:d (path (flatten
                        [-20 0
                         :l
                         (repeat
                          15 [0 10 -10 0 10 0])

                         ]))
              :marker-end (m7/url "i")
              :stroke (hsl [5 70 70 1])
              :stroke-width .5
              :fill :none}]]]]])


(defn template21 []
  [:div {:style
         (merge
          (grid [100 :vh 100 :vw
                 (take 15 (repeat [8 :vh]))
                 (take 15 (repeat [8 :vh]))])
          {:background-color (hsl [1.4 70 70 .8])})}







   [:div {:key (gensym)
          :style (css
                  [[(+ 8 0) 4 (+ 5 (* 4 (inc 0))) 4 :center :center 5 :rem :column]
                   [2 70 70 .8] []
                   {:z-index 2}]
                  )}



    ]


   [:div {:key (gensym)
            :style (css
                    [[2 11 2 11 :center :center 2 :rem]
                     [.5 70 70 .5] []
                     {:z-index 30}]
                    )}
    [:svg {:viewBox (space  [-100  -180 200 200 ])
               :style {:height (size {:size 100 :scale :%})
                       :width  (size {:size 100 :scale :%})}}
     (marker "a")
     [:g {:transform (m7/tranfrom [[:rotate 310]])}


      [:path {:d (path [ 0 0 :l 180 0  ])
              :stroke (hsl [0 70 70 1])
              :marker-end (m7/url "i")
              :stroke-width 1
              :fill :none}]


      [:path {:d (path [ 103 0 :l 0 -10 10 0])
              :stroke (hsl [0 70 70 1])
              :marker-end (m7/url "i")
              :stroke-width 1
              :fill :none}]





      [:path {:d (path [0 0 :l 110 (ve 83)
                        0 83 -110 0])

              :stroke (hsl [5 70 70 1])
              :stroke-width 2
              :fill :none}]


      [:path {:d (path [0 0 :l 0 -83  110 0  -110 83])

              :stroke (hsl [1 70 70 1])
              :stroke-width 2
              :fill :none}]

      [:text {:x 40
              :y 0
              :style {:font-size ".5rem"}}
       "b"]

      #_[:text {:x 40
                :y 0
                :dy -3
                :dx 3
                :style {:font-size ".5rem"}}
         "2"]


      [:text {:x 0
              :y -25
              :style {:font-size ".5rem"}}
       "83"]


      [:text {:x 50
              :y -83
              :style {:font-size ".5rem"}}
       ""]

      [:text {:x 100
              :y -40
              :style {:font-size ".5rem"}}
       "83"]


      [:text {:x 50
              :y -46
              :transform (m7/tranfrom [[:rotate 0]])
              :style {:font-size ".5rem"}}
       "137.8"]


      #_[:text {:x 100
              :y -40
              :dy -3
              :dx 3
              :style {:font-size ".5rem"}}
       "2"]

      ]











     ]]

   [:div {:key (gensym)
            :style (css
                    [[2 11 14 11 :center :center 3 :rem :column]
                     [.5 70 70 .5] []
                     {:z-index 30}]
                    )}


    [m7/m '[= [:p 137.8 2] [+ [:p 83 2] [:p 'b 2]] ]]

    [m7/m ['= ['+ [:p 137.8 2] ['- [:p 83 2]]] [:p 'b 2]]]


    [:svg {:viewBox (space  [-100  -10 200 200 ])
               :style {:height (size {:size 100 :scale :%})
                       :width  (size {:size 100 :scale :%})}}
     (marker "a")
     [:g {:transform (m7/tranfrom [[:rotate 0]])}



      ]







     ]]






   ])



(defn template2-1 []
  [:div {:style
         (merge
          (grid [100 :vh 100 :vw
                 (take 15 (repeat [8 :vh]))
                 (take 15 (repeat [8 :vh]))])
          {:background-color (hsl [1.4 70 70 .8])})}




   [:div {:key (gensym)
          :style (css
                  [[2 7 8 7 :center :center 2 :rem :column]
                   [1.2 70 70 1] []
                   {:z-index 28}])}

    [m7/m '[= [[:m 9 cm] [:m tan  48]]  RS]]

    [m7/m '[= [:m 8.10 cm]  RS]]


    [m7/m '[= [QR RS] [:m cos 46]] ]

    [m7/m '[= [QR [:m 8.1 cm]] [:m cos 46]] ]

    [m7/m '[= QR  [* [:m 8.1 cm]
                   [:m cos 46]]] ]

    ]





   [:div {:key (gensym)
            :style (css
                    [[2 11 2 11 :center :center 2 :rem]
                     [.5 70 70 .5] []
                     {:z-index 30}]
                    )}
    [:svg {:viewBox (space  [-100  -100 200 200 ])
               :style {:height (size {:size 100 :scale :%})
                       :width  (size {:size 100 :scale :%})}}



     [:g {:transform (m7/tranfrom [[:rotate 270]
                                   [:scale 1.7]])}
      [:circle {:cx 0 :cy 0 :r 1 :fill (hsl [1 70 70 1])}]



      [:path {:d (path [0 0 :l
                        50 0 -50
                        0 0 -55
                        50 55])
              :stroke (hsl [.3 70 70 1])
              :stroke-width 1
              :fill :none}]


      [:path {:d (path [5 0 :l 0 -5 -5 0])
              :stroke (hsl [1.3 70 70 1])
              :stroke-width 1
              :fill :none}]


      [:path {:d (path [0 0 :l 25 24 25 -24])
              :stroke (hsl [1 70 70 1])
              :stroke-width 1
              :fill :none}]


      #_[:path {:d (path [0 0 :l 25 24 25 -20])
              :stroke (hsl [1 70 70 1])
              :stroke-width 1
              :fill :none}]

      [:text {:x 0
              :y 4
              :style {:font-size "0.2rem"}}
       "R"]

      [:text {:x 4
              :y 4
              :style {:font-size "0.2rem"}}
       "46"]

      [:text {:x 25
              :y 24
              :style {:font-size "0.2rem"}}
       "Q"]

      [:text {:x 25
              :y 20
              :style {:font-size "0.2rem"}}
       "90"]



      [:text {:x 50
              :y 0
              :style {:font-size "0.2rem"}}
       "S"]

      [:text {:x 0
              :y -55
              :style {:font-size "0.2rem"}}
       "P"]

      [:text {:x 0
              :y -25
              :style {:font-size "0.2rem"}}
       "9.2cm"]







      [:text {:x 35
              :y -5
              :style {:font-size "0.2rem"}}
       "48"]


      ]

     ]]

   #_[:div {:key (gensym)
            :style (css
                    [[2 11 2 11 :center :center 2 :rem]
                     [.5 70 70 .5] []
                     {:z-index 30}]
                    )}
    [:svg {:viewBox (space  [-100  -100 200 200 ])
               :style {:height (size {:size 100 :scale :%})
                       :width  (size {:size 100 :scale :%})}}
     [:circle {:cx 0 :cy 0 :r 1 :fill (hsl [1 70 70 1])}]
     [:path {:d (path [0 0 :l
                       50 0 -50
                       0 0 -70
                       50 70])
             :stroke (hsl [5 70 70 1])
             :stroke-width 1
             :fill :none}]


     [:path {:d (path [0 0 :l
                       50 0 -50
                       0 0 -55
                       50 55])
             :stroke (hsl [.3 70 70 1])
             :stroke-width 1
             :fill :none}]


     [:path {:d (path [0 0 :l
                       70 0  0 70 -70 0 0 -70
                       ])
             :stroke (hsl [1 70 70 1])
             :stroke-width 1
             :fill (hsl [.2 70 70 .2])}]


     [:path {:d (path [0 0 :l
                       0 -98 -98 0 0 98 98 0
                       ])
             :stroke (hsl [1 70 70 1])
             :stroke-width 1
             :fill (hsl [1 70 70 .2])}]


     [:text {:x -50
             :y -50
             :style {:font-size "0.2rem"}}
      ""
      ]


     [:text {:x 2
             :y -56
             :style {:font-size "0.2rem"}}
      "D"
      ]


     [:text {:x -50
             :y -50
             :dx 5
             :dy -2
             :style {:font-size "0.2rem"}}
      "2"
      ]


     [:text {:x 35
             :y 35
             :style {:font-size "0.2rem"}}
      "70"
      ]

     [:text {:x 35
             :y 35
             :dy -2
             :dx 5
             :style {:font-size "0.2rem"}}
      "2"
      ]


     [:text {:x 45
             :y -45
             :style {:font-size "0.2rem"}}
      "70"
      ]

     [:text {:x 55
             :y -45
             :style {:font-size "0.2rem"}}
      "+"
      ]


     [:text {:x 60
             :y -45
             :style {:font-size "0.2rem"}}
      "98"
      ]

     [:text {:x 45
             :y -45
             :dx 5
             :dy -2
             :style {:font-size "0.2rem"}}
      "2"
      ]

     [:text {:x 60
             :y -45
             :dx 5
             :dy -2
             :style {:font-size "0.2rem"}}
      "2"
      ]


     [:text {:x 35
             :y -50
             :style {:font-size "0.2rem"}}
      (js/Math.sqrt (+ (* 70 70) (* 98 98)))]



     [:path {:d (path [0 0 :l
                       50 0 -50
                       0 0 -70
                       50 70])
             :stroke (hsl [2 70 70 1])
             :transform (m7/tranfrom
                         [[:scale 1.4]])
             :stroke-width .74
             :fill :none}]


     [:path {:d (path [0 0 :l
                       50 0 -50
                       0 0 -70
                       50 70])
             :stroke (hsl [4 70 70 1])
             :transform (m7/tranfrom
                         [[:scale .5]])
             :stroke-width 2
             :fill :none}]

     #_[:path {:d (path [0 0 :l
                       70 0
                       ])
             :stroke (hsl [1 70 70 1])
             :stroke-width 1
             :fill :none}]


     #_[:path {:d (path [30 0 :a
                       20 20 0 false true
                       7 -16])
             :stroke (hsl [5 70 70 1])
             :stroke-width 1
             :fill :none}]


     #_[:path {:d (path [50 0 :a
                       20 20 0 false true
                       7 -16])
             :stroke (hsl [5 70 70 1])
             :stroke-width 1
             :fill :none}]

     [:text {:x 0
             :y 4
             :style {:font-size "0.2rem"}}
      "C"]

     [:text {:x 0
             :y -74
             :style {:font-size "0.2rem"}}
      "B"]

     [:text {:x 50
             :y 0
             :style {:font-size "0.2rem"}}
      "A"]

     [:text {:x 35
             :y -5
             :style {:font-size "0.2rem"}}
      "𝝷"]


     [:text {:x 40
             :y -15

             :style
             {:font-size "0.2rem"}}
      "hyp"]

     [:text {:x 25
             :y 5
             :style
             {:font-size "0.2rem"}}
      "adj"]



     [:text {:x 25
             :y 10
             :style {:font-size "0.2rem"}}
      "AC=50"]

     [:text {:x -10
             :y -10
             :style
             {:font-size "0.2rem"}}
      "opp"]

     [:text {:x -10
             :y -20
             :style
             {:font-size "0.2rem"}}
      "BC=70"]

     ]]

   ])



(defn template3 []
  [:div {:style
           (merge
            (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 15 (repeat [8 :vh]))])
            {:background-color (hsl [2.4 70 70 1])})}


     [:div {:key (gensym)
            :style (css
                    [[2 11 2 11 :center :center 2 :rem]
                     [.5 70 70 .5] []
                     {:z-index 30}]
                    )}
    [:svg {:viewBox (space  [-100  -100 200 200 ])
               :style {:height (size {:size 100 :scale :%})
                       :width  (size {:size 100 :scale :%})}}




     #_[:path {:d (path [-40 -10 :q 44 -44   72 0])
             :stroke (hsl [2 70 70 .5])
               :marker-end (m7/url "a")
             :stroke-width 1
             :fill :none}]







     #_[:path {:d (path [-40 -10 :q 44 -44 100 0])
             :stroke (hsl [5 70 70 .5])
             :stroke-width 1
             :fill :none}]







     #_[:text {:x 0
             :y -5
             :style {:font-size ".7rem"} }
      "600 m"]

     #_[:text {:x 0
             :y 12
             :style {:font-size ".7rem"} }
      "15 sec"]


     #_[:path {:d (path [0 90  :l 90 0 0 -160  -10 0  -80  160])
             :stroke (hsl [2 70 70 .8])
             :stroke-width 2
             :fill :none}]








    #_[:circle {:cx 85 :cy -80 :r 15 :fill (hsl [0 70 70 .7])}
      [:animateTransform
       {:id :sq
        :attributeName :transform
        :begin :click
        :dur (sec .2)
        :type :translate
        :to (m7/space [0 160])
        :fill :freeze
        }]]



     [:path {:d (path [-90 0 :l 180 0 ])
             :stroke (hsl [3 70 70 .8])
             :marker-end (m7/url "i")
             :stroke-width 1
             :fill :none}]




     [:path {:d (path [-90 0 :l 20 0 0 -5 -5 0 0 -5 -10 0 0 5 -5 0 0 5])
             :transform (m7/tranfrom [[:scale 3]])
             :stroke (hsl [1 70 70 .8])
             :stroke-width 1.1
             :fill (hsl [1 70 70 .9])}
      [:animateTransform
       {:id :sq
        :attributeName :transform
        :begin :click
        :dur (sec 5)
        :type :translate

        :to (m7/space [160 0])
        :fill :freeze
        }]]









     ]

      ]



   [:div {:key (gensym)
          :style
          (css [
                [2 11 2 10 :center :center 3 :rem :column]
                [.5 70 70 1]
                []
                {:z-index 4}])}



    #_[:div "Galileo drops a stone from the leaning tower of Pisa, which is 45 miters high. At what speed does the stone hit the ground?"]


    #_[:div "The Gravitational Potential Energy by lifting the ball from ground to 45 miters high at tower of Pisa"]

    ;; [m7/m '[= PE mgh]]


    ;; [m7/m '[= KE [:m [1 2] m [:p v 2]]]]

    ;; [:div "According to conservation of energy"]

    ;; [m7/m '[= PE KE]]

    ;; [m7/m '[= mgh [:m [1 2] m [:p v 2]]]]

    ;; [m7/m '[= mgh [:m [1 2] m [:p v 2]]]]




    #_[m7/m '[= F ma]]

    #_[m7/m '[= Weight mg]]

    #_[m7/m '[= g [:m 9.8 [m [:p s 2]]]]]
    #_[m7/m '[= Weight [:m 500 Kg 9.8 [m [:p s 2]]]]]

    #_[m7/m '[= Weight [:m [* 500 9.8] N]]]
    #_[m7/m '[= Work-done [* Force displacement] ]]

    #_[m7/m '[= PE Work-done [:m m g h]]]

    #_[m7/m '[= Work-done [:m Weigth h]]]


     #_[m7/m '[= PE Work-done [* [:m 500 Kg] 9.8 [m [:p s 2]] [:m 200 m]] ]]

    ;; [m7/m '[= Work-done [* [:m 500 Kg] 9.8 [m [:p s 2]] [:m 200 m]] ]]

    #_[m7/m '[= PE Work-done [:m 980000  j]]]





    ]




  [:div {:key (gensym)
          :style
          (css [
                [2 11 14 5 :center :center 5 :rem :column]
                [.5 70 70 .5]
                []
                {:z-index 3}])}

   ;; [m7/m '[= PE KE]]

   ;; [m7/m '[= mgh [:m [1 2] m [:p v 2]]]]

   ;; [m7/m '[= [:m 9.8 [m [:p s 2]] 45 m]  [:m [1 2] [:p v 2]]]]

   ;; [m7/m ['= [:m 882  [[:p 'm 2] [:p 's 2]]  ]  [:p 'v 2]]]

   ;; [m7/m ['= [:sq [:m 882  [[:p 'm 2] [:p 's 2]]  ]]  [:sq [:p 'v 2]]]]


   ;; [m7/m ['= [:m 29.7  ['m 's]  ] 'v]]


   [m7/m '[= v [d t]]]


   #_[m7/m '[= v [[:m [* 16 5] m] [:m 5 s ]]]]


   #_[m7/m '[= v [:m 16 [m s]]]]

   ;; [m7/m '[= v [[:m [* 2 1000] m]
   ;;              [:m [* 60 1] s]]]]

   ;; [m7/m '[= v [[:m [* 2 1000] m]
   ;;              [:m [* 60 1] s]]]]


   ;; [m7/m '[= v [:m [2000
   ;;                  60] [m s]]]]



   ;; [m7/m ['= 'v [:m [(/ (* 2 1000) 10)
   ;;                   (/ (* 1 60) 10) ] ['m 's]]]]


   ;; [m7/m ['= 'v [:m 33.33 ['m 's]]]]


    ;; [:div "Moon"]

    ;; [m7/m '[= F ma]]

    ;; [m7/m '[= Weight [:m m [g 6]]]]
    ;; [m7/m '[= Weight [:m 1 Kg [9.8 2] [m [:p s 2]]]]]


    ;; [m7/m '[= Weight [:m 1 Kg [9.8 2] [m [:p s 2]]]]]

    ;; [m7/m '[= Weight [:m 1.6 N]]]


    ]


  [:div {:key (gensym)
          :style
          (css [
                [2 11 14 5 :flex-start :flex-start 2 :rem :column]
                [.5 70 70 .5]
                []
                {:z-index 4}])}

   ;; [m7/m '[=  PE [:m m g h]]]

   ;; [m7/m '[=  PE [* 500 Kg  [:m 9.8 m [:p s 2]] [:m 100 m]]]]

   ;; [m7/m '[=  PE KE]]
   ;; [m7/m '[=  [* [:m 500 kg] [:m 9.8 [m [:p s 2]]] [:m 100 m]] [:m [1 2] 500 Kg [:p v 2]]]]

   ;; [m7/m '[=  [:sq [:m [* 2 980] [:p [:b [m s]] 2]]] [:sq [:p v 2]]]]

   ;; [m7/m '[= [:m [:sq [* 2 980]] [m s]] v]]

   ;; [m7/m '[= [:m [:sq [* 2 980]] [m s]] v]]

   ;; [m7/m '[=
   ;;         v [:m 44.217 [m s]]]]






    ]

   ])

(defn template31 []
  (let [dx [1 0  0 1 -1  0 0 -1 ]
        dy [0 1 -1 0  0 -1 1  0 ]
        l 45
        l2 (/ l 2)
        base [(ve l2) (ve l2)]
        s2 (comp
            (partial into (conj base :l))
            (partial map (partial * l)))
        sq-path (s2 dx)
        s3  (comp
             (fn [n]
               (subvec n  0 (dec (count n))))
             vec
             rest
             (partial reduce
                      (fn [acc  [x y]]
                        (let [[x1 y1] (last acc)]
                          (conj acc [(+ x x1) (+ y y1)])))  [[0 0]])
             (partial partition 2)
             (partial into base)
             (partial mapv (partial * l)))
        s4 (comp
            vec
            (partial partition 2)
            (fn [[e v]]
              (map + e v))
            (juxt (comp
                   (partial mapcat identity) s3)
                  (partial mapv (partial * 12))))
        points (s4 dx)
        angles (map
                (fn [[a b] [c d]]
                  [a b :l c d])
                points
                (partition 2
                           (map +
                                (map (comp
                                      (partial * 10)
                                      ve) dx)
                                (map (partial * 10) dy))))

        ag (comp
            (partial partition 2)
            (fn [[x y]]
              (map + x y))
            (juxt (comp
                   (partial map (partial * 12))
                   (partial map ve)
                   first)
                  (comp
                   (partial map (partial * 12))
                   second)))

        angles2 (map (fn [[a b] [c d]]
                       [a b :a 12 12 0 false true c d])
                     points
                     (ag [dx dy]))]
    [:div {:style
           (merge
            (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 15 (repeat [8 :vh]))])
            {:background-color (hsl [2.4 70 70 1])})}

     [:div {:key (gensym)
            :style (css
                    [[2 11 2 11 :center :center 2 :rem]
                     [-2 70 70 1] []
                     {:z-index 2}]
                    )}

      [:svg {:viewBox (space (nth [[-100  -100 200 200 ]
                                   [-10 -160 80 80]] 0))
             :style {:height (size {:size 100 :scale :%})
                     :width  (size {:size 100 :scale :%})}}

       [:circle {:cx 0 :cy 0 :r 1 :fill (hsl [1 70 70 1])}]



       [:circle {:cx (+ 5 (ve l2)) :cy (+ 40 (ve l2)) :r 1
                 :fill (hsl [1 70 70 1])}]


       [:text {:x (+ 5 (ve l2)) :y (+ 30 (ve l2)) :r 1
               :style {:font-size "1rem"}
               :fill (hsl [1 70 70 1])}
        "1716"]


       [:circle {:cx (ve l) :cy 0 :r 1 :fill (hsl [1 70 70 1])}]


       [:text {:x (ve (+ l l2)) :y 10
               :style {:font-size ".8rem"}
               :fill (hsl [1 70 70 1])}
        "80 - 2"]

       [:text {:x (+ 10 l2) :y 10
               :style {:font-size ".6rem"}
               :fill (hsl [1 70 70 1])}
        "20 + 2"]

       [:text {:x (+ 5 (ve l2)) :y (+ 30 l2) :r 1
               :style {:font-size "1rem"}
               :fill (hsl [1 70 70 1])}
        "1560"]



       [:g
        [:path {:d (path sq-path)
                :stroke (hsl [1 70 70 1])
                :stroke-width 1
                :fill :none}]


        (map (comp
              (fn [d]
                [:path {:d d
                        :key (gensym)
                        :stroke (hsl [1 70 70 1])
                        :stroke-width 1
                        :fill :none}])
              path)
             angles2)]

       [:g {:transform (m7/tranfrom
                        [[:translate [-45 0]
                          ]
                         ])}
        [:path {:d (path sq-path)
                :stroke (hsl [1 70 70 1])
                :stroke-width 1
                :fill :none}]


        (map (comp
              (fn [d]
                [:path {:d d
                        :key (gensym)
                        :stroke (hsl [1 70 70 1])
                        :stroke-width 1
                        :fill (hsl [1 70 70 1])}])
              path)
             angles2)]

       [:g {:transform (m7/tranfrom
                        [[:translate [45 0]
                          ]
                         ])}
        [:path {:d (path sq-path)
                :stroke (hsl [1 70 70 1])
                :stroke-width 1
                :fill :none}]


        (map (comp
              (fn [d]
                [:path {:d d
                        :key (gensym)
                        :stroke (hsl [1 70 70 1])
                        :stroke-width 1
                        :fill (hsl [1 70 70 1])}])
              path)
             angles2)]

       [:g {:transform (m7/tranfrom
                        [[:translate [0 45]
                          ]
                         ])}
        [:path {:d (path sq-path)
                :stroke (hsl [1 70 70 1])
                :stroke-width 1
                :fill :none}]


        (map (comp
              (fn [d]
                [:path {:d d
                        :key (gensym)
                        :stroke (hsl [1 70 70 1])
                        :stroke-width 1
                        :fill (hsl [1 70 70 1])}])
              path)
             angles2)]

       ]]]))












(defn template4 []
  (let []
    [:div {:style
           (merge
            (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 15 (repeat [8 :vh]))])
            {:background-color (hsl [2.4 70 70 1])
             :gap "5px"})}



     [:div {:key (gensym)
            :style (css
                    [[10 1 1 20 :center :center 3 :rem :column]
                     [1 70 70 .8] []
                     {:z-index 2}]
                    )}


      ]



     (map-indexed

      (fn [i [n c]]
        [:div {:key (gensym)
               :style (css
                       [[(+ (* 2 i) 1) 2 (+ 5 (* 3 0)) 3 :center :center 2 :rem :column]
                        [c 70 70 .8] []
                        {:z-index 2}]
                       )}


         n])
      [["" 7.3]
       ["Top 5%" .7]
       ["Next 5%" 3.5]])


     (map-indexed
      (fn [j a]

        (map-indexed

         (fn [i [n c]]
           [:div {:key (gensym)
                  :style (css
                          [[(+ 2 i) 1 (+ 5 (* 3 (inc j))) 3 :center :center 2 :rem :column]
                           [c 70 70 .8] []
                           {:z-index 2}]
                          )}


            n])
         a)
        )
      [
       [["Level/Year" 1.1]
        ["Primary 1 - 6" 7.2]
        ["Secondary 1 - 5" .5]
        ["Primary 1 - 6" 3.5]
        ["Secondary 1 - 5" .3]
        ]
       [["2008" 7.2]
        ["$300" .5]
        ["$500" 3.5]
        ["$250" 3.5]
        ["$300" .3]
        ]

       [["2009" 7.2]
        ["$400" .5]
        [[m7/m '[* 500 [135 100]]] 3.5]
        [[m7/m '[ [- 350 250] 250]] 3.5]
        ["$400" .3]]


       ]
      )


     #_[:div {:key (gensym)
            :style (css
                    [[5 1 (+ 5 (* 3 2)) 3 :center :flex-end 3 :rem :column]
                     [1 70 70 .8] []
                     {:z-index 2}]
                    )}


      "12000"]






     ]))


(comment
  (let [f (fn [n] (/ 1 n))
        dx [1 0  0 1 -1  0 0 -1 ]
        sq-fn (fn [n]
                (comp
                 (partial partition 2)
                 (partial map (partial * (f n)))))
        add (fn [[x y] [u v]]
              [(+ x u)
               (+ y v)])
        c ((comp
            cycle
            (sq-fn 4))
           dx)]
    (interpose
     (mapcat
      (fn [n]
        (take 4 (drop n c)))
      (range 0 4))
     ((sq-fn 1) dx))))
(comment
  _ (react/useEffect
     (fn []
       (-> ref .-current (.play))))

  )




(defn template5 []
  (let [ref (react/useRef)
        ref-path (react/useRef)
        [slider set-slider] (react/useState 3)
        [is-playing set-is-playing] (react/useState false)
        toggle-playing
        (fn [event]
          (do
            (set-is-playing
             (not is-playing))
            (if is-playing
              (-> ref .-current (.pause))
              (-> ref .-current (.play)))))
        [media-time  set-media-time] (react/useState 0)
        [duration set-duration] (react/useState 0)
        [first-elapsed set-first-elapsed] (react/useState 0)
        on-loaded-metadata
        (fn []
          (set-duration (-> ref .-current .-duration)))
        on-time-update
        (fn []
          (cond
            (= first-elapsed 0)
            (do

              (set-first-elapsed -1)
              (toggle-playing nil)
              (set!
               (-> ref .-current .-currentTime) 215))
            (> first-elapsed 0) (set-first-elapsed (dec first-elapsed))
            )


          (set-media-time
           (-> ref .-current .-currentTime)))
        on-scrubber-change
        (fn [e]
          (do
            (set-media-time
             (js/parseFloat
              (-> e .-target .-value)))
            (set!
             (-> ref .-current .-currentTime)
             (js/parseFloat
              (-> e .-target .-value)))))
        first-clip
        (fn []
          (do
            (set-slider 0)
            (set-first-elapsed 45)
            (set!
             (-> ref .-current .-currentTime) 195.909994)
            (toggle-playing nil)))
        [inertia set-inertia] (react/useState false)
        toggle-arrow (fn [e]
                       (set-inertia (not inertia)))

        animate-path
        (fn [e]
          (-> ref-path
              .-current
              (.animate
               (clj->js
                [{:stroke (m7/hsl [0 70 70 0])
                  :strokeDasharray 100}
                 {:strokeDasharray 110
                  :stroke (m7/hsl [0 70 70 .5])}
                 {:strokeDasharray 120
                  :stroke (m7/hsl [0 70 70 1])
                  }
                 {:opacity 0
                  :strokeDasharray 120
                  :stroke (m7/hsl [0 70 70 0])
                  }
                 ])
               (clj->js
                {:duration 1400
                 :iterations 2})
               )))
        dummy-acc (fn [e]
                    (toggle-arrow nil)
                    (set!
                     (-> ref .-current .-currentTime) 215)
                    (toggle-playing nil))

        f (fn [n] (/ 1 n))
        dx [1 0  0 1 -1  0 0 -1 ]
        sq-fn (fn [n]
                (comp
                 (partial partition 2)
                 (partial map (partial * (f n)))))
        add (fn [[x y] [u v]]
              [(+ x u)
               (+ y v)])
        c2 (fn [n]
             (comp
              cycle
              (sq-fn n)))]
    [:div {:style
           (merge
            (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
            {:background-color (hsl [3.4 70 70 1])})}


     [:div {:style
            (css
             [[1 1 2 6 :space-around :center 2 :rem]
              [.5 70 70 1] []
              {:z-index 4
               :gap "1rem"}]
             )}
      (comment [:label {:html-for "scrubber"} ])
      (comment
        [:input {:type :range
                 :style {:width "100%"}
                 :id "scrubber"
                 :value media-time
                 :on-change on-scrubber-change
                 :min 0
                 :max duration}])

      (map
       (fn [i v]
         [:div {:key (gensym)
                :on-click (fn [e] (set-slider i))
                :style {:background-color (hsl [i 70 70 1])
                        :min-width "8vh"
                        :padding ".3rem"
                        }}
          v]) (range 1 5)
       ["moment" 2 3 4 ])]

     [:div {:style
            (css
             [[1 1 8 1 :center :center 2 :rem]
              [-2 70 70 1] []
              {:z-index 4}]
             )}
      [:button {:on-click toggle-playing
                :style {:width "100%"
                        :height "100%"}}
       (if is-playing  "Pause" "Play")
       ]]
     [:div {:style
            (css
             [[1 1 9 2 :center :center 1.2 :rem :column]
              [.5 70 70 1] []
              {:z-index 4
               :gap "1rem"}])}
      [:span "elapsed"]
      [:span media-time]
      ]
     [:div {:style
            (css
             [[1 1 11 2 :center :center 1.2 :rem :column]
              [-0.5 70 70 1] []
              {:z-index 4 :gap "1rem"}]
             )}
      [:span "duration"]
      [:span duration]
      ]
     [:div {:on-click first-clip
            :style
            (css
             [[1 1 13 2 :center :center 1.2 :rem :column]
              [-0.5 70 70 1] []
              {:z-index 4 :gap "1rem"}]
             )}
      [:span "Inertia"]
      [:span first-elapsed]
      ]

     [:div {:style
            (css
             [[2 11 2 15 :center :center 2 :rem :column]
              [-3 70 70 1] []
              {:z-index 3}]
             )}

      [:video {:controls true
               :on-loaded-metadata on-loaded-metadata
               :on-time-update on-time-update
               :ref ref
               :width "100%"
               :height "100%"
               :autoplay true}
       [:source
        {:src
         "UnderstandingCarCrashesItsBasicPhysics.mp4"}]]


      ]

     [:div {:key (gensym)
            :style (css
                    [[2 7 17 7 :center :center 2 :rem :column]
                     [-3 70 70 .1] []
                     {:z-index 4}]
                    )}

      [:div {:style {:font-size "2rem"
                     :font-weigh 700
                     :color (hsl [.5 70 50 1 ])}}
       (nth ["First Law of Motion" "Second Law of Motion" "velocity & accalaration" "Momentum" "Tense"] slider)]

      [:div {:on-click toggle-arrow
             :style {:font-size "5rem"

                     :font-weigh 700
                     :color (hsl [.5 70 50 1 ])}}
       (nth ["Inertia" "Momentum" " ∇ = Rate of change " "Mass times Velocity" "Simple Past"] slider)]
      [:div {:style {
                     :font-size "2.2rem"
                     :font-weigh 500
                     :color (hsl [5.7 70 70 1 ])}}
       (nth [[:div  "Property of Matter that causes it to resist any change in motion"]
             [m7/m '[= F [[:m ∇ [:b [:m  m  v]] ] t]]]
             [:div

              "Accalaration is rate of change of velocity 🠖 (∇v) over time "]
             [:div {:style {:display :flex
                            :flex-direction :column}}
              [m7/m '[= P [:m m   v]]]
              [m7/m '[= P  [:m [* 5.2  [170 1000] ]   [:m Kg [m s]]]]]
              ]
             [:div "Things happen in future"]

             ] slider)

       ]]



     [:div {:keys (gensym)
            :style (css
                    [[7 1 17 7 :center :center 3 :rem]
                     [-3 70 70 .1] []
                     {:z-index 1}
                     (m7/fs [200 500 100 31 26 200 154 75 491 52])]
                    )}
      (nth [
            [:div "If forces on a mass are balanced & no resultant force applied"]
            [:div [m7/m '[= [:m F t] [:m ∇ [:b [:m  m v]] ]]]]
            [:div {:style {:padding "2rem"}}
             [m7/m  '[= a
                      [[:m ∇ v] t]
                      [:m m [:p s -2] ]
                      [[:b [:m [:b [- 0 60]]
                            [ [:m 1000 m]
                             [:m [* 60 60] s]]] ]
                       [:m 10 s]]
                      ] ]]
            [:div ""]
            ]
           slider)
      ]
     [:div {:keys (gensym)
            :style (css
                    [[8 2 18 2 :center :center 2.2 :rem]
                     [-3 70 70 .1] []
                     {:z-index 5
                      :padding ".3rem"}]
                    )}


      (nth
       [[:div "If it is at rest, it stays at rest"]
        [:div [:span {:style {:background-color (hsl [1.2 70 70 1])}} "mv"]
         [:span " is the mass times velocity, that is call momentum"]
         [:div {:style {:padding "1rem"
                        :display :flex
                        :justify-content :center}}
          [m7/m '[= p [:m m v]]]]]
        [:div
         [m7/m '[= v  [[:m ∇ s ] t] [:m m [:p sec -1]]
                 [[:b [=  ∇
                       [:b [:m [:b [- 20 0  ]] m]]]]
                  [:m 5 sec]]
                 ]]
         [:div
          [:div {:on-click dummy-acc}
           "Car"]]]
        [:div ""]
        ]
       slider)]
     [:div {:keys (gensym)
            :on-click animate-path
            :style (css
                    [[9 2 20 2 :center :center 2 :rem]
                     [-3 70 70 .1] []
                     {:z-index 5}]
                    )}
      (nth [
            [:div
             "If it is " [:span {:style {:background-color (hsl [1.2 70 70 1])}}
                          "moving"]  ",
it keep " [:span {:style {:background-color (hsl [1.2 70 70 1])}} "moving"]    " on a constant speed in
in a straight line"]
            [:div "Force (F) times change in time is met impluse that is change in momentum"]
            [:div "Velocity Rate of change ∇  over time "]
            [:div ""]
            ]
           slider)]
     [:div {:key (gensym)
            :style (css
                    [[2 7 15 7 :center :center 2 :rem :column]
                     [-3 70 70 .1] []
                     {:z-index 2}]
                    )}
      [:svg {:viewBox (space [-6 -6 12 12])
             :style
             {:height (size {:size 100 :scale :%})
              :width  (size {:size 100 :scale :%})}}
       [:g
        [:circle {:cx 0 :cy 0 :r (f 8) :fill (hsl [0 70 70 1])}]

        (map
         (fn [i  [a b] [x y]]
           [:path {:key (gensym)
                   :d ((m7/path2 [(fn [x]
                                    (* a x (- 1 (f 3))))
                                  (fn [y]
                                    (* b y (- 1 (f 3))))])
                       (flatten
                        [(* x (f 3)) (* y (f 3)) :l
                         (take 4
                               (drop i ((c2 1) dx)))]))
                   :stroke (hsl [1 70 70 1])
                   :stroke-width (f 50)
                   :fill (hsl [2 70 70 1])}])
         (range 0 4)
         (let [[[x1 y1] [x2 y2]]
               [[1 1] [1 1]]
               c4 [y1 y2]
               c3 [x1 y2]
               c2 [x1 x2]
               c1 [y1 x2]]
           [c4 c3 c2 c1 ])
         [[1 1] [-1 1] [-1 -1] [1 -1]])



        (map
         (fn [[x y] [dx dy] i]
           [:circle {:keys (gensym)
                     :cx (* x dx)
                     :cy (* y dy)
                     :r (f 12)
                     :fill (hsl [(* i (f 3)) 70 70 1])}])
         (let [[[x1 y1] [x2 y2]]
               [[3 3] [2 5]]
               c4 [y1 y2]
               c3 [x1 y2]
               c2 [x1 x2]
               c1 [y1 x2]]
           [c4 c3 c2 c1 ])
         (partition 2
                    (map + dx
                         (flatten
                          (take 4  (drop 1 ((c2 1) dx))))))
         (range 1 5))]

       ]
      ]

     [:div {:key (gensym)
            :style (css
                    [[2 7 4 7 :center :center 2 :rem :column]
                     [-3 70 70 .1] []
                     {:z-index (if inertia 4 1)}]
                    )}
      [:svg {:viewBox (space [-100 -100 200 200])
             :style
             {:height (size {:size 100 :scale :%})
              :width  (size {:size 100 :scale :%})}}
       [:defs
        [:marker {:id "j"
                  :refY 0
                  :refX 0
                  :orient :auto
                  :style {:overflow :visible}}
         [:path {:d (m7/path [0 0 :L 5 -5
                           :L -12.5  0
                           :L 3  5  :L 0  0 ])
                 :style {:fill-rule :evenodd
                         :stroke (hsl [1 70 70 1])
                         :stroke-width 1
                         :stroke-opacity 1
                         :fill (hsl [4 70 70 1])
                         :fill-opacity 1}
                 :transform
                 (m7/tranfrom [[:scale .2]
                            [:rotate 180]
                            [:translate [12.5 0]]])}]

         ]]
       [:g

        [:path {:ref ref-path
                :style {:stroke-dashoffset 1000
                        :stroke-dasharray 122
                        :stroke (m7/hsl [0 70 70 0])
                        :stroke-width 3
                        }

                :d (m7/path [100 0 :c -20 -20 -80 -20   -100 0])

                :fill :none
                :marker-end (m7/url "j")
                }]]]]

     ]))




(comment
  (partition
   2
   (interleave
    (range 0 4)
    (let [[[x1 y1] [x2 y2]] [[2 3] [3 5]]
          c4 [y1 y2]
          c1 [x1 x2]
          c2 [y1 x2]
          c3 [x1 y2]]
      [c1 c2 c3 c4])))

  (let [f (fn [n] (/ 1 n))
        dx [1 0  0 1 -1  0 0 -1 ]
        sq-fn (fn [n]
                (comp
                 (partial partition 2)
                 (partial map (partial * (f n)))))
        add (fn [[x y] [u v]]
              [(+ x u)
               (+ y v)])
        c2 (fn [n]
             (comp
              cycle
              (sq-fn n)))]
    (map + dx
         (flatten (take 4  (drop 1 ((c2 1) dx)))))
    )

  )




(defn home-work []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap "1rem"})}



     [:div {:style (m7/css
                      [[2 10 5 15  :center :center  2 :rem :column]
                       [(+ 1 (f 3)) 70 90 .2] [] {:gap "1rem"
                                                  :z-index 4}])}





      [:div "Robin Hood exerts on average force of 100N in pulling back his bow by .5 m. He fires the arrow (mass = .2 kg) vertically upward.
 How much energy is stored in his bow and how high does the arrow go."]

      [m7/m '[= Elastic-energy [* average-force distance]]]

      [m7/m '[= Elastic-energy [* [:m 100 N] [:m .5 m]]]]


      [m7/m '[= Elastic-energy [:m 50 joules]]]



      [:div "Since this energy is conserved than this must be the kinetic energy as the arrow leaves the bow. and it must also equal
the gravitational PE at its highest point"]

      [m7/m '[= PE mgh]]
      [m7/m '[= PE [* .2 kg 9.8 [m [:p s 2]] h ]  Elastic-enrgy ]]

      [m7/m '[=  [* .2 kg 9.8 [m [:p s 2]] h ] [:m 50 joules]]]

      [m7/m '[=  [* .2 9.8  h ] 50]]

      [m7/m '[=  h

              [50 [* .2 9.8]]]
       ]

      [m7/m '[=  h
              [50 1.97]]]]





     [:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [(+ 1 (f 3)) 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [0 -100  300 200])}



       [:g {:transform (m7/tranfrom [[:scale [.4 .4]]
                                     [:rotate [-90]]])}

        [:path {:d (path [0 -90 :c 40 40 60 100   0 180])
                :stroke (hsl [.8 70 70 1])
                :stroke-width 4
                :fill :none}]

        (let [pull (ve 0)]
          [:g
           [:path {:d (path [0 -90 :l pull 90 ])
                   :stroke (hsl [0 70 70 1])
                   :stroke-width 1.2
                   :fill :none}]

           [:path {:d (path [0 90 :l pull -90 ])
                   :stroke (hsl [0 70 70 1])
                   :stroke-width 1.2
                   :fill :none}]

           [:path {:d (path [pull 0 :l  100 0])
                   :marker-end (m7/url "abccab")
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 2.2
                   :fill :none}
            (if (= pull 0)
              [:animateTransform {:id :arrow1
                                  :attributeName :transform
                                  :begin (sec 0)
                                  :dur (sec 5)
                                  :type :translate
                                  :values (m7/sami-colon
                                           (map
                                            m7/space
                                            [[-50 0] [100 0] [160 0]]))
                                  :keyTimes (m7/sami-colon [0.0 .4 1])
                                  ;;:from (m7/space [0 0])
                                  ;;:to (m7/space [200 0])
                                  :fill :freeze}])

            [:animateTransform {:id :arrow2
                                :attributeName :transform
                                :begin :arrow1.end
                                :dur (sec 5)
                                :type :translate
                                :values (m7/sami-colon
                                         (map
                                          m7/space
                                          [[160 0] [100 0]  [-50 0] ]))
                                :keyTimes (m7/sami-colon [0.0 .4 1])
                                ;;:from (m7/space [0 0])
                                ;;:to (m7/space [200 0])
                                :fill :freeze}]

            ]]
          )]
       ]]]))



(defn home-work2 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}


    ;;  (map
    ;;   (fn [n d]
    ;;     [:div {:style (m7/css
    ;;                    [[2 1 (+ 3 (* n 2)) 2  :center :center  3 :rem :column]
    ;;                     [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
    ;;                                                          :z-index 4}])}

    ;;      d])
    ;;   (range 0 8)
    ;;   (into [[:div "s"] ] (repeat 7 1))
    ;;   )


    ;; (map
    ;;   (fn [n d]
    ;;     [:div {:style (m7/css
    ;;                    [[3 1 (+ 3 (* n 2)) 2  :center :center  3 :rem :column]
    ;;                     [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
    ;;                                                          :z-index 4}])}

    ;;      d])
    ;;   (range 0 7)
    ;;   (into [[:div "t"] ] (map #(.toFixed (js/Math.sqrt (/ 1 (* 19.6 %))) 3) (range 1 11))))


    ;;  (map
    ;;   (fn [n d]
    ;;     [:div {:style (m7/css
    ;;                    [[4 1 (+ 3 (* n 2)) 2  :center :center  3 :rem :column]
    ;;                     [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
    ;;                                                          :z-index 4}])}

    ;;      d])
    ;;   (range 0 8)
    ;;   (into [[:div [m7/m '[= v [m s]]]] ]
    ;;         (map #(.toFixed (/ 1 (js/Math.sqrt (/ 1 (* 19.6 %)))) 2) (range 1 11))))

     [:div {:style (m7/css
                    [[1 4 (+ 9 (* 0 2)) 10  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}


      [m7/m '[= F [[- mv mu] t]]]
      [m7/m '[= Ft [- mv mu]]]


      [:div {:style {:font-size "2rem"}} "conservation Law of momentum"]

      [m7/m '[= [+ m1u1  m2u2 ] [+ m1v1  m2v2 ]]]

      #_[m7/m ['= 'a [ [:m (- 7.76 4.43) ['m 's]]
                      [:m .29 's]]]]

      #_[m7/m ['= 'a   [:m  (/ (- 7.76 4.43) .29) ['m [:p 's 2]]] ]]


      #_[m7/m ['= [:m 'sin tt] ['a 'c]] ]
      #_[m7/m ['= [:m 'sin 35] [70 'c] .573]]
      #_[m7/m ['= [70 .573] 'c]]

      #_[m7/m ['= (js/Math.round (/ 70 .573)) 'c]]

      ]

     [:div {:style (m7/css
                    [[2 4 (+ 1 (* 0 2)) 7  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}


      [:div "The green ball collides with a stationary black ball. the mass of the green ball is 170g"]

      ]



     [:div {:style (m7/css
                    [[7 6 (+ 1 (* 0 2)) 7  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      [:div "Momentum of the green ball before collides"]
      [m7/m '[= P1 [:m [* [17 100]  5.2]  [Kgm s]]]]

      [:div "Momentum of the black ball before collides is 0"]


      ]


     [:div {:style (m7/css
                    [[7 6 (+ 10 (* 0 2)) 7  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      [:div "Momentum of the green ball after collides"]
      [m7/m '[= P3 [:m [* [17 100] v]  [Kgm s]]]]

      [:div "Momentum of the black ball after collides is"]
      [m7/m '[= P2 [:m [* [16 100]  5.0]  [Kgm s]]]]

      [:div "duo to conservation of momentum"]

      [m7/m '[= P1 [+ P3 P2]]]
      [m7/m '[= [:m [* [17 100]  5.2]  [Kgm s]] [+ [:m [* [17 100] v]  [Kgm s]]
                                                 [:m [* [16 100]  5.0]  [Kgm s]]
                                                 ]]]


      [m7/m '[= [* 17  5.2] [+ [:m 17 v ]
                             [* 16  5.0]]]]


      [m7/m '[= [:m [[- [* 17  5.2] [* 16  5.0]] 17] [m s]] v]]

      [m7/m '[= [:m 0.49 [m s]] v]]
      ]





     [:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"
                     }
             :viewBox (m7/space
                       [0 -100  400 200])}

       (let [pl (ve 0)]
         [:circle {:cx 0
                   :cy 0
                   :r 5
                    ;; :marker-end (m7/url "i")
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 6
                   :fill :none}
             (if (= pl 0)
               [:animateTransform {:id :green
                                   :attributeName :transform
                                   :begin (sec 0)
                                   :dur (sec 5)
                                   :from (space [0 0])
                                   :to (space [150 0])
                                   :type :translate
                                   :fill :freeze}])

          [:animateTransform {:id :green2
                              :attributeName :transform
                              :begin :green.end
                              :dur (sec 5)
                              :from (space [150 0])
                              :to (space [200 0])
                              :type :translate
                              :fill :freeze}]

          ]



         )


       [:circle {:cx 150
                 :cy 0
                 :r 5
                    ;; :marker-end (m7/url "i")
                 :stroke (hsl [7 70 70 1])
                 :stroke-width 6
                 :fill :none}
        [:animateTransform {:id :arrow222
                            :attributeName :transform
                            :begin :green.end
                            :dur (sec 5)
                            :from (space [0 0])
                            :to (space [150 0])
                            :type :translate
                            :fill :freeze}]
        ]

       (comment
         (let [pull (ve 0)]
           [:g


            [:path {:d (path [pull 0 :l  20 0  -5 0  -10 7  10 -7 -10 -7 10 7
                              -20 0 -5 -5])
                    ;; :marker-end (m7/url "i")
                    :stroke (hsl [2 70 70 1])
                    :stroke-width 6
                    :fill :none}
             (if (= pull 0)
               [:animateTransform {:id :arrow22
                                   :attributeName :transform
                                   :begin (sec 0)
                                   :dur (sec 5)
                                   :type :translate
                                   :values (m7/sami-colon
                                            (map
                                             m7/space
                                             (map (fn [i]
                                                    [(* i 40) 0])  (range 0 11))))
                                   :keyTimes (m7/sami-colon
                                              (let [total-min
                                                    (apply + (map #(js/Math.sqrt (/ 1 (* 19.6 %))) (range 1 11)))]
                                                (reduce

                                                 (fn [acc a]
                                                   (conj acc (+ a (last acc))))

                                                 [0]
                                                 (map #(/
                                                        (js/Math.sqrt (/ 1 (* 19.6 %)))
                                                        total-min) (range 1 11))
                                                 ))
                                              )
                                   ;;:from (m7/space [0 0])
                                   ;;:to (m7/space [200 0])
                                   :fill :freeze}])]]




           ))





       ]]


     ]))



(defn home-work5 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}











     [:div {:style (m7/css
                    [[3 5 (+ 12 (* 0 2)) 8  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      [m7/m '[= Volume
              [* [1 3] base-area height ]]]


      [m7/m '[= [:m 7000 [:p m 3]]
              [* [1 3] [* 30 20] [:p m 2] height ]]]




      [m7/m '[= Surface-Area [* 4 area-triange-face area-base]]]

      [m7/m '[= Surface-Area [* 4 [1 2] 14 17 14 14]]]
      ]


     #_[:div {:style (m7/css
                    [[1 10 (+ 11 (* 0 2)) 6  :center :center  3 :rem :column]
                     [(* 2 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}



      ]







     [:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"
                     }
             :viewBox (m7/space

                       [0 -100  400 200])}



       [:path {:d (path [100 50 :l  40 0 0 -40 -40 0 0 40
                         ])
               ;; :marker-end (m7/url "i")
               :stroke (hsl [2 70 70 1])
               :stroke-width 1
               :fill :none}]


       [:path {:d (path [100 50 :l  40 0 60 -20 -60 -20
                         ])
               ;; :marker-end (m7/url "i")
               :stroke (hsl [2 70 70 1])
               :stroke-width 1
               :fill :none}]



       [:path {:d (path [100 50 :l  20 60
                         ])
               ;; :marker-end (m7/url "i")
               :stroke (hsl [2 70 70 1])
               :stroke-width 1
               :fill :none}]

       [:path {:d (path [100 50 :l 40 0  -20 60
                         ])
               ;; :marker-end (m7/url "i")
               :stroke (hsl [2 70 70 1])
               :stroke-width 1
               :fill :none}]



       [:path {:d (path [0 30 :l 200 0
                         ])
               ;; :marker-end (m7/url "i")
               :stroke (hsl [2 70 70 1])
               :stroke-width 1
               :fill :none}]


       [:path {:d (path [120 50 :l 0 -200 0 400
                         ])
               ;; :marker-end (m7/url "i")
               :stroke (hsl [2 70 70 1])
               :stroke-width 1
               :fill :none}]



       [:path {:d (path [100 50 :l  40 0 -40 -40
                         ])
               ;; :marker-end (m7/url "i")
               :stroke (hsl [2 70 70 1])
               :stroke-width 1
               :fill :none}]


       [:path {:d (path [100 50 :l  40  -40
                         ])
               ;; :marker-end (m7/url "i")
               :stroke (hsl [2 70 70 1])
               :stroke-width 1
               :fill :none}]


       [:g {:transform (m7/tranfrom [
                                     [:skewX -32]
                                     [:scale [.7 .7]]])}


        [:text {:x 40
                :y 0
                :style {:font-size ".4rem"}}
         "14cm"]

        [:text {:x 80
                :y -40
                :style {:font-size ".4rem"}}
         "14m"]

        [:path {:d (path [0 0 :l  80 0 0 -60 -80 0 0 60
                          ])
                ;; :marker-end (m7/url "i")
                :stroke (hsl [2 70 70 1])
                :stroke-width 1
                :fill :none}]


        [:path {:d (path [0 0 :l  -20 -140 63 110
                          ])
                ;; :marker-end (m7/url "i")
                :stroke (hsl [2 70 70 1])
                :stroke-width 1
                :fill :none}]



        [:path {:d (path [  -20 -140 :l 20 80 80 0 -100 -80
                          ])
                ;; :marker-end (m7/url "i")
                :stroke (hsl [2 70 70 1])
                :stroke-width 1
                :fill :none}]


        [:path {:d (path [  -20 -140 :l 100 140
                          ])
                ;; :marker-end (m7/url "i")
                :stroke (hsl [2 70 70 1])
                :stroke-width 1
                :fill :none}]


        [:path {:d (path [  -20 -140 :l 60 140
                          ])
                ;; :marker-end (m7/url "i")
                :stroke-dasharray "10 10"
                :stroke (hsl [2 70 70 1])
                :stroke-width 1
                :fill :none}]


        [:text {:x -5
                :y -68
                :style {:font-size ".4rem"}}
         "17m"]


        [:path {:d (path [0 0 :l  80 -60
                          ])
                ;; :marker-end (m7/url "i")
                :stroke (hsl [2 70 70 1])
                :stroke-width 1
                :fill :none}]



        [:path {:d (path [0 0 :l  80 0   -80 -60
                          ])
                ;; :marker-end (m7/url "i")
                :stroke (hsl [2 70 70 1])
                :stroke-width 1
                :fill :none}]]

       ;; math
       #_(let [pull (ve 1)]
         [:g





          [:path {:d (path [pull 0 :l  20 0  -5 0  -10 7  10 -7 -10 -7 10 7
                            -20 0 -5 -5])
                  ;; :marker-end (m7/url "i")
                  :stroke (hsl [2 70 70 1])
                  :stroke-width 6
                  :fill :none}
           (if (= pull 0)
             [:animateTransform {:id :arrow22
                                 :attributeName :transform
                                 :begin (sec 0)
                                 :dur (sec 5)
                                 :type :translate
                                 :values (m7/sami-colon
                                          (map
                                           m7/space
                                           (map (fn [i]
                                                  [(* i 40) 0])  (range 0 11))))
                                 :keyTimes (m7/sami-colon
                                            (let [total-min
                                                  (apply + (map #(js/Math.sqrt (/ 1 (* 19.6 %))) (range 1 11)))]
                                              (reduce

                                               (fn [acc a]
                                                 (conj acc (+ a (last acc))))

                                               [0]
                                               (map #(/
                                                      (js/Math.sqrt (/ 1 (* 19.6 %)))
                                                      total-min) (range 1 11))
                                               ))
                                            )
                                 ;;:from (m7/space [0 0])
                                 ;;:to (m7/space [200 0])
                                 :fill :freeze}])]]




         )





       ]]]))




(defn clock [time-color]
  (let [[timer update-time] (react/useState (js/Date.))
        time-str (-> timer .toTimeString (str/split " ") first)]
    (react/useEffect
     (fn []
       (let [i (js/setInterval #(update-time (js/Date.)) 100)]
         (fn []
           (js/clearInterval i)))))
    [:div.example-clock
     {:style {:color time-color}}
     time-str]))

(defn color-input [time-color update-time-color]
  [:div.color-input
   [:input {:type "text"
            :autoFocus true
            :value time-color
            :on-change #(update-time-color (-> % .-target .-value))}]])

(defn simple-example []
  (let [[time-color update-time-color] (react/useState "#f34")]
    [:div
     [clock time-color]
     [color-input time-color update-time-color]


     ]))




(defn home-work3 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}





     #_(map
        (fn [n d]
          [:div {:style (m7/css
                         [[2 1 (+ 2 (* n 2)) 2  :center :center  3 :rem :column]
                          [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                               :z-index 4}])}

           d])
        (range 0 7)
        [[:div [m7/m [:m 'sin tt]]] [m7/m ['a 'c]] [m7/m [70 'c]]])




     (map
      (fn [n d]
        [:div {:style (m7/css
                       [[2 1 (+ 2 (* n 2)) 2  :center :center  2 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      (range 0 7)
      [[:div [m7/m '[= v [ [:m 120 m] [:m 8 s]   ]]]]
       [:div
        [m7/m '[= v [ [:m 120 m] [:m 4 s]   ]]]]

       [:div
        [m7/m '[= v [ [:m 120 m] [:m 2.4 s]   ]]]]

       [:div
        [m7/m '[= v [ [:m 120 m] [:m 1.6 s]   ]]]]])

     (map
      (fn [n d]
        [:div {:style (m7/css
                       [[3 4 (+ 2 (* n 2)) 3  :center :center  3 :rem :column]
                        [(* 2 .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                            :z-index 4}])}

         d])
      (range 0 7)
      [[simple-example]
       ])

     #_(map
      (fn [n d]
        [:div {:style (m7/css
                       [[4 1 (+ 2 (* n 2)) 2  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      (range 0 7)
      [[:div [m7/m [:m 'tan tt]]] [m7/m ['b 'a]]  [m7/m [70 100]]])

     #_(map
      (fn [n d]
        [:div {:style (m7/css
                       [[4 1 (+ 2 (* n 2)) 2  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      (range 0 15)
      [[:div (name tt)] 0 15 30 45 60 75 90 100 14 32 32])


     #_(map
      (fn [n d]
        [:div {:style (m7/css
                       [[3 1 (+ 9 (* n 2)) 2  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      (range 0 7)
      [[m7/m '[:p a 2]] ""]

      )

     #_(map
      (fn [n d]
        [:div {:style (m7/css
                       [[4 1 (+ 9 (* n 2)) 2  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      (range 0 7)
      ["" [m7/m '[* 3 2]]]










      )

     #_[:div {:style (m7/css
                      [[2 10 2 15  :center :center  7 :rem :column]
                       [(* 5 .2) 70 (+ 50 (* 5 5)) 1] [] {:gap ".1rem"
                                                          :z-index 4}])}



        ]





     [:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"
                     }
             :viewBox (m7/space
                       (nth [[50 -100  200 200]
                             [-25 -25  50 50]] 1))}


       (grid-on 20 20 0 -20)
       (let [pull (ve 1)]
         [:g


          [:path {:d (path [-100 0 :l 1200 0])
                  :stroke (hsl [0 70 70 .1])
                  :stroke-width 1
                  :fill :none}]

          [:path {:d (path [0 -600  :l 0 1200])
                  :stroke (hsl [0 70 70 .5])
                  :stroke-width 1
                  :fill :none}]


          [:path {:d (path (mapcat identity  (into [[0 0  :l]] (repeat 60 [10 0 0 -10 0 10]))))
                  :stroke (hsl [0 70 70 1])
                  :stroke-width 1
                  :fill :none}]


          [:path {:d (path (mapcat identity  (into [[0 0  :l]]
                                                   (repeat 60
                                                           [0 -10
                                                            -10 0
                                                            10 0]))))
                  :stroke (hsl [0 70 70 1])
                  :stroke-width 1
                  :fill :none}]



          [:path {:d (path (mapcat identity
                                   (into [[0 0  :l]]
                                         (repeat 60 [10 0 0 -10 0 10]))))
                  :stroke (hsl [0 70 70 1])
                  :stroke-width 1
                  :fill :none}]


          (map
           (fn [i]
             [:path {:d (path (mapcat
                               identity
                               (into [[0 (* 60 i)  :l]]
                                     (repeat 7
                                             (map #(* 6 %)
                                                  [10 0 0 -10 -10 0
                                                   0 10 10 0 ])))))
                     :stroke (hsl [5 70 70 1])
                     :stroke-width 1
                     :fill (hsl [5 80 80 .5])}])
           (range 0 2))




          (map
           (fn [[x y]]
             [:circle {:cx (* x 10)
                       :cy (ve (* y 10))
                       :r 2
                       :fill (hsl [0 70 70 1])}])
           [[0 0] [12 8] [24 (+ 8 4)] [36 (+ 8 4 2.4 )]
            [48 (+ 8 4 2.4 1.6)]])


          [:path {:d (path [0 0 :l 120 -80
                            120 -40 120 -24 120 -16])
                  ;; :marker-end (m7/url "i")
                  :stroke (hsl [2 70 70 1])
                  :stroke-width 1
                  :fill :none}]

          [:path {:d (path [0 0 :l 30 0  :c  0 -5 -5 -10   -15 -10
                            :c -5 3  -5 3 -15 0 :l  -18 -18  0  5 ])
                  ;; :marker-end (m7/url "i")
                  :stroke (hsl [2 70 70 1])
                  :stroke-width .2
                  :fill (hsl [4 70 70 1])}
           (if (= pull 0)
             [:animateTransform {:id :arrow2
                                 :attributeName :transform
                                 :begin 0
                                 :dur (sec 25)
                                 :repeatcount 3
                                 :type :translate
                                 :values (m7/sami-colon
                                          (map
                                           m7/space
                                           (map (fn [i]
                                                  [(* i 120) 0])
                                                (range 0 5))))
                                 :keyTimes (m7/sami-colon
                                            [0 .5 .75 .9 1])
                                 ;;:from (m7/space [0 0])
                                 ;;:to (m7/space [200 0])
                                 :fill :freeze}])]

          ]




         )





       ]]]))





(defn home-work7 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}



     (
      (fn [n d]
        [:div {:style (m7/css
                       [[6 1 10 2  :center :flex-end  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      3
      "x"
      )


     (
      (fn [n d]
        [:div {:style (m7/css
                       [[7 1 9 1  :center :flex-end  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      3
      "x"
      )

     (
      (fn [n d]
        [:div {:style (m7/css
                       [[8 4 9 1  :center :flex-end  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      3
      "64"
      )

     (
      (fn [n d]
        [:div {:style (m7/css
                       [[6 1 12 3  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      3
      "-4"
      )

     (
      (fn [n d]
        [:div {:style (m7/css
                       [[7 1 10 2  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      2
      [m7/m '[:p x  2]]
      )


     (
      (fn [n d]
        [:div {:style (m7/css
                       [[7 1 12 3  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      0
      [m7/m '[:m [- 4]  x]]
      )


     (
      (fn [n d]
        [:div {:style (m7/css
                       [[8 4 12 3  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      0
      [m7/m '[* [- 64] 4]]
      )

     (
      (fn [n d]
        [:div {:style (m7/css
                       [[8 4 10 2  :center :center  2 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      0
      [m7/m '[:m 64 x]]
      )


    ;; (map
    ;;   (fn [n d]
    ;;     [:div {:style (m7/css
    ;;                    [[3 1 (+ 3 (* n 2)) 2  :center :center  3 :rem :column]
    ;;                     [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
    ;;                                                          :z-index 4}])}

    ;;      d])
    ;;   (range 0 7)
    ;;   (into [[:div "t"] ] (map #(.toFixed (js/Math.sqrt (/ 1 (* 19.6 %))) 3) (range 1 11))))


    ;;  (map
    ;;   (fn [n d]
    ;;     [:div {:style (m7/css
    ;;                    [[4 1 (+ 3 (* n 2)) 2  :center :center  3 :rem :column]
    ;;                     [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
    ;;                                                          :z-index 4}])}

    ;;      d])
    ;;   (range 0 8)
    ;;   (into [[:div [m7/m '[= v [m s]]]] ]
    ;;         (map #(.toFixed (/ 1 (js/Math.sqrt (/ 1 (* 19.6 %)))) 2) (range 1 11))))

     #_[:div {:style (m7/css
                    [[1 4 (+ 9 (* 0 2)) 10  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      [m7/m '[= [- [:p a 2] [:p b 2]]
              [:m [:b [+ a b]] [:b [- a b]]]]]


      [m7/m '[= [- [:p k 2] [:p 3 2]]
              [:m [:b [+ k 3]] [:b [- k 3]]]]]




      ]

     #_[:div {:style (m7/css
                    [[2 8 (+ 1 (* 0 2)) 7  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}


      [m7/m '[[- [:p k 2] 9]
              [+ [- [:p k 2] [:m 7 k]] 12]]]


      [m7/m '[[- [:p k 2] [* 3 3]]
              [+ [- [:p k 2] [:m 7 k]] [* 4 3]]]]


      [m7/m '[[- [:p k 2] [:p 3 2]]
              [:m [:b [- k 4] ] [:b [- k 3] ]]]]

      [m7/m '[[:m [:b [+ k 3]] [:b [- k 3]]]
              [:m [:b [- k 4] ] [:b [- k 3] ]]]]

      [m7/m '[[+ k 3]
              [- k 4]]]





      ]


     [:div {:style (m7/css
                    [[2 8 (+ 1 (* 0 2)) 7  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}





      [m7/m '[= [+ [:m 4 [:p x 2]] [:m [* 2 70] x]  [:m [* 2 50] x]]
              1024
              ]]

      [m7/m '[= [+ [:p x 2] [:m 35 x]  [:m 25 x]]
              256
              ]]

      [m7/m '[= [+ [:p x 2] [:m 60 x] ]
              [* 4 64]
              ]]

      [m7/m '[= [- [+ [:p x 2] [:m 60 x] ] [* 4 64]]
              0
              ]]
      [m7/m '[= [:m [:b [- x 4]] [:b [+ x 64]]] 0 ]]


      [m7/m '[= [- x 4] 0 ]]
      [m7/m '[= x [:m 4 m]]]










      ]


     [:div {:style (m7/css
                    [[2 8 (+ 16 (* 0 2)) 7  :center :center  3 :rem :column]
                     [(* 1 .2) 70 (+ 50 (* 5 5))  .1] [] {:gap ".1rem"
                                                          :z-index 4}])}





      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [-20 -80  100 100])}


       [:path {:d (path [ 0 0  :l  70 0 0 -50 -70 0 0 50
                         ])
               :marker-end (m7/url "i")
               :stroke (hsl [5 70 70 1])
               :stroke-width 1
               :fill :none}
        ]



       [:path {:d (path [ 0 -60  :l  70 0  0 10 -70 0 0 -10
                         ])
               :marker-end (m7/url "i")
               :stroke (hsl [5 70 70 1])
               :stroke-width 1
               :fill (hsl [4 70 70 1])}



        ]


       [:path {:d (path [ 0 0  :l  70 0  0 10 -70 0 0 -10
                         ])
               :marker-end (m7/url "i")
               :stroke (hsl [5 70 70 1])
               :stroke-width 1
               :fill (hsl [4 70 70 1])}



        ]

       [:path {:d (path [ 0 0  :l  -10 0  0 -50 10 0 0 50
                         ])
               :marker-end (m7/url "i")
               :stroke (hsl [3 70 70 1])
               :stroke-width 1
               :fill (hsl [2 70 70 1])}



        ]

       [:path {:d (path [ 80 0  :l  -10 0  0 -50 10 0 0 50
                         ])
               :marker-end (m7/url "i")
               :stroke (hsl [3 70 70 1])
               :stroke-width 1
               :fill (hsl [2 70 70 1])}



        ]

       [:text {:x -10
               :y -20
               :style {:font-size ".3rem"}}
        "50x"]

       [:text {:x 40
               :y 5
               :style {:font-size ".5rem"}}
        "70x"]


       (map
        (fn [d]
          [:g {:transform (m7/tranfrom [[:translate d]])}
           [:path {:d (path [ 0 0  :l -10 0 0 10 10 0 0 -10
                             ])
                   :marker-end (m7/url "i")
                   :stroke (hsl [5 70 70 1])
                   :stroke-width 1
                   :fill (hsl [3 70 70 1])}
            ]
           [:text {:x -10
                   :y 10
                   :style {:font-size ".5rem"}}
            "x"]

           [:text {:x -5
                   :y 5
                   :style {:font-size ".3rem"}}
            "2"]])
        [[0 0] [0 -60] [80 0] [80 -60] ])



       ]






      ]



     #_[:div {:style (m7/css
                    [[7 6 (+ 1 (* 0 2)) 7  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}



      ]



     #_[:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"}])}
      #_[:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [0 -100  400 200])}


       [:path {:d (path [ 0 0  :l 10 0 :c  50 -10 100  -40   120 -130
                         :c 30 -60 60 -60   90 0 :l 4 120 40 0 :c 20 20 40 50  50 0])
               :marker-end (m7/url "i")
               :stroke (hsl [5 70 70 1])
               :stroke-width 1
               :fill :none}
        ]

         ]]]))



(defn home-work7-1 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}



     (
      (fn [n d]
        [:div {:style (m7/css
                       [[6 1 10 2  :center :flex-end  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      3
      "2x"
      )


     (
      (fn [n d]
        [:div {:style (m7/css
                       [[7 1 9 1  :center :flex-end  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      3
      "x"
      )

     (
      (fn [n d]
        [:div {:style (m7/css
                       [[8 4 9 1  :center :flex-end  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      3
      "-5"
      )

     (
      (fn [n d]
        [:div {:style (m7/css
                       [[6 1 12 3  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      3
      "11"
      )

     (
      (fn [n d]
        [:div {:style (m7/css
                       [[7 1 10 2  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      2
      [m7/m '[:m 2 [:p x  2]]]
      )


     (
      (fn [n d]
        [:div {:style (m7/css
                       [[7 1 12 3  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      0
      [m7/m '[:m 11  x]]
      )


     (
      (fn [n d]
        [:div {:style (m7/css
                       [[8 4 12 3  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      0
      [m7/m '[* [- 5] 11 ]]
      )

     (
      (fn [n d]
        [:div {:style (m7/css
                       [[8 4 10 2  :center :center  2 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      0
      [m7/m '[* [- 5] [:m 2 x] ]]
      )





     [:div {:style (m7/css
                    [[2 8 (+ 1 (* 0 2)) 7  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}





      #_[m7/m '[= [:m [1 2] [:b [+ x 3]] [:b [- [:m 2 x] 5]]]
              20
              ]]

      #_[m7/m '[= [+ [:m 2 x] [:m [- 5 ] x] [:m 6 x] [- 55]]   0]]

      [m7/m '[= [- [+ [:m 2 [:p x 2]] x ] 55]   0]]


      [m7/m '[= [:m [:b [- x 5]] [:b [+ [:m 2 x] 11]]] 0]]
      #_[m7/m '[= [- x 5] 0]]



      ]


     [:div {:style (m7/css
                    [[2 8 (+ 16 (* 0 2)) 7  :center :center  3 :rem :column]
                     [(* 1 .2) 70 (+ 50 (* 5 5))  .1] [] {:gap ".1rem"
                                                          :z-index 4}])}





      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [-200 -200  400 400])}


       [:path {:d (path [ 0 0  :l 40 0 0 -40  -40 0 0 40
                         ])
               :marker-end (m7/url "i")
               :stroke (hsl [5 70 70 1])
               :stroke-width 1
               :fill :none}
        ]
       [:text {:x  10
               :y 0
               :font-size "1.5rem"}
        "x"]

       ]

      ]



     ]))




















(defn home-work8 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}




     [:div {:style (m7/css
                    [[1 12 10 13  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}
      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [-100 -100  200 200])}


       (grid-on 1 1)

       [:circle {:cx (* 10 1)
                 :cy (ve
                      (((fn [a b c]
                          (fn [x]
                            (/ (- c (* a 10 x)) b)))
                        3 -2 7) 4))
                 :r 2
                 :fill (hsl [0 87 70 1])}
        ]


       [:circle {:cx (* 20 3)
                 :cy (* 20 (ve
                            (((fn [a b c]
                                (fn [x]
                                  (/ (- c (* a  x)) b)))
                              3 -2 7) 3)))

                 :r 2
                 :fill (hsl [0 87 70 1])}
        ]





       ]]


     [:div {:style (m7/css
                    [[2 10 (+ 2 (* 0 2)) 12  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      (let [a 3
            b 4
            c 7]
        (m7/mx
         `[= [- [:m ~a x]
              [:m ~b y]] ~c]))
      (m7/m '[= [- [:m 3 x] [:m 2 y]] 7])

      [m7/m '[= [+ [:m 2 x ] [:m 3 y]] 9]]


      #_[m7/m '[= [* 1 [:b  [+ [:m 2 x] [:m 6 y]]]] [* 1 25]]]
      #_[m7/m '[=  [* 3 [:b [- [:m 3 x ] [:m 2 y]]]] [* 3 9]]]


      [:div {:style {:height "10px"
                     :width "100%"
                     :background-color (hsl [3 30 30 1])}}]










      ]



     ]))




(defn home-work9 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}




     [:div {:style (m7/css
                    [[1 4 (+ 9 (* 0 2)) 10  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}





      ]

     #_[:div {:style (m7/css
                      [[2 8 (+ 1 (* 0 2)) 20  :center :center  6 :rem :column]
                       [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                            :z-index 4}])}


        [m7/m '[= [+ [:m [:p H +] [:p [:m N [:k O 3]] -]  ] [:m [:p K +] [:p OH -]]]

                [+ [:m [:p K +] [:p [:m N [:k O 3]] -]  ] [:m [:k H 2] O]]

                ]
         ]


        ]


     [:div {:style (m7/css
                    [[2 8 (+ 1 (* 0 2)) 20  :center :center  6 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}


      [m7/m '[= [+ [:m [:p H +] [:p CL -]] [:m [:p Na +] [:p OH -]]]

              [+ [:m [:p Na +] [:p Cl
                                -]] [:m [:k H 2] O]]

              ]
       ]


      ]



     #_[:div {:style (m7/css
                    [[7 6 (+ 1 (* 0 2)) 7  :center :center  5 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}
      [:div ""]


      ]



     [:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [0 -100  400 200])}
       [:path {:d (path [120 0 :c  -40 50 -80 50  -140 0
                         ])
               :marker-end (m7/url "i")
               :stroke (hsl [2 70 70 1])
               :stroke-width 2
               :fill :none}
        ]

       [:path {:d (path [120 0 :c  -40 50 -80 50  -140 0
                         ])
               :marker-end (m7/url "i")
               :stroke (hsl [2 70 70 1])
               :stroke-width 2
               :fill :none}
        ]


       ]]]))



(defn home-work10 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}




     #_[:div {:style (m7/css
                    [[1 4 (+ 9 (* 0 2)) 10  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}





      ]

     #_[:div {:style (m7/css
                      [[2 8 (+ 1 (* 0 2)) 20  :center :center  6 :rem :column]
                       [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                            :z-index 4}])}


        [m7/m '[= [+ [:m [:p H +] [:p [:m N [:k O 3]] -]  ] [:m [:p K +] [:p OH -]]]

                [+ [:m [:p K +] [:p [:m N [:k O 3]] -]  ] [:m [:k H 2] O]]

                ]
         ]


        ]


     #_[:div {:style (m7/css
                    [[2 8 (+ 1 (* 0 2)) 20  :center :center  6 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}





      ]




     [:div {:style (m7/css
                    [[7 6 (+ 1 (* 1 2)) 17  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}








      ]



     [:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [0 -200  400 400])}
       #_[:path {:d (path [120 0 :c  -40 50 -80 50  -140 0
                         ])
               :marker-end (m7/url "i")
               :stroke (hsl [2 70 70 1])
               :stroke-dasharray "10 10"
               :stroke-width 2
               :fill :none}
        ]



       [:path {:d (path [0 0 :l 100 0 0 -100 -100 0 0 100])
               :transform (m7/tranfrom [[:skewX -40]])
               :stroke (hsl [2 70 70 1])
               :stroke-width 2
               :fill :none}
        ]



       [:path {:d (path [220 0 :l 100 0 0 -100 -100 0 0 100])
               :transform (m7/tranfrom [[:skewX 0]])
               :stroke (hsl [2 70 70 1])
               :stroke-width 2
               :fill (hsl [2 70 70 1])}
        ]







       [:path {:d (path [0 0 :l 100 0 -20 -180 -80 180 ])

               :stroke (hsl [4 70 70 1])
               :stroke-width 2
               :fill (hsl [4.5 80 70 .4]) }
        ]


       [:path {:d (path [0 0 :l 50 0 27 -180])

               :stroke (hsl [4 70 70 1])
               :stroke-width 2
               :fill (hsl [4.5 80 70 .4]) }
        ]

       [:path {:d (path [0 0 :l 100 0 -50 -120 -50 120 ])
               :transform (m7/tranfrom [
                                        [:translate [220 0]]
                                        [:scale [1 -1]]])
               :stroke (hsl [4 70 70 1])
               :stroke-width 2
               :fill (hsl [4.5 80 70 .4]) }
        ]


       [:path {:d (path [0 0 :l 100 0 -50 -120 -50 120 ])
               :transform (m7/tranfrom [
                                        [:translate [220 -100]]
                                        [:scale [1 1]]])
               :stroke (hsl [4 70 70 1])
               :stroke-width 2
               :fill (hsl [4.5 80 70 .4]) }
        ]


       [:path {:d (path [0 0 :l 100 0 -50 -120 -50 120 ])
               :transform (m7/tranfrom [[:translate [320 -100]]
                                        [:rotate 90]])
               :stroke (hsl [4 70 70 1])
               :stroke-width 2
               :fill (hsl [4.5 80 70 .4]) }
        ]

       [:path {:d (path [0 0 :l 100 0 -50 -120 -50 120 ])
               :transform (m7/tranfrom [[:translate [220 -100]]
                                        [:scale [-1 1]]
                                        [:rotate 90]
                                        ])
               :stroke (hsl [4 70 70 1])
               :stroke-width 2
               :fill (hsl [4.5 80 70 .4]) }
        ]









       #_[:path {:d (path [200 0 :l 100 0 -20 -180 -80 180 ])

                 :stroke (hsl [4 70 70 1])
                 :stroke-width 2
                 :fill (hsl [4.5 80 70 .4]) }
          ]

       #_[:path {:d (path [200 0 :l 100 0 -50 -120 -50 120 ])

               :stroke (hsl [4 70 70 1])
               :stroke-width 2
               :fill (hsl [4.5 80 70 .4]) }
        ]



       [:path {:d (path [100 0 :l 85  -100 -105 -80])

               :stroke (hsl [4 70 70 1])
               :stroke-width 2
               :fill (hsl [4 70 70 .4]) }
        ]







       #_[:path {:d (path [-120 20 :l 85  -100 -105 -80])

               :stroke (hsl [4 70 70 1])
               :stroke-width 2
               :fill (hsl [4 70 70 .4]) }
        ]



       [:path {:d (path [0 0 :l 80  -100 0 -80])

               :stroke (hsl [4 70 70 1])
               :stroke-width 2

               :fill (hsl [5 70 70 .4]) }
        ]



       [:path {:d (path [80 -180 :l 20 120])
               :stroke (hsl [5 70 70 1])
               :stroke-width 2
               :stroke-dasharray "10 5"
               :fill :none}
        ]





       [:text {:x 50
               :y 0
               :style {:font-size "1rem"}}
        "a"]


       [:circle  {:cx 80
                  :cy -180
                  :r 1
                  :fill (hsl [3 70 70 1])}]]]]))



(defn home-work11 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}




     #_[:div {:style (m7/css
                    [[1 4 (+ 9 (* 0 2)) 10  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}





      ]

     [:div {:style (m7/css
                      [[2 4 (+ 1 (* 0 2)) 10  :center :center  3 :rem :column]
                       [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                            :z-index 4}])}


      #_[m7/m '[= [:p [:b [+ a b]] 2]
              [+ [:p a 2]  [:m 2 ab]  [:p b 2]]]]

      #_[m7/m '[= [:p [:b [- a b]] 2]
              [+ [- [:p a 2]  [:m 2 ab]]  [:p b 2]]]]

      #_[m7/m '[=
              [- [:p a 2]    [:p b 2]]
              [:m [:b [+ a b]] [:b [- a b]]]]]

      ]


     [:div {:style (m7/css
                    [[2 8 (+ 1 (* 0 2)) 20  :center :center  8 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}
      ;; [:div "nearest 1,00,000"]
      ;; [m7/m '[= 698352
      ;;         [+  600000 98352]]]

      ;; [m7/m '[= 698352
      ;;         [+  600000 0]]]

      ;; [m7/m '[= 698352
      ;;         [+  600000 100000] 700000]]

      (let [b '☐]
        [m7/m ['= ['+ 5 ['* b 3]] 29 ]])



      (let [b '☐]
        [m7/m ['= ['+ 5 ['* b 3]] ['+ 5 24] ]])


      (let [b '☐]
        [m7/m ['= ['* b 3] ['* 8 3]]])


      (let [b '☐]
        [m7/m ['= b 8]])





      #_[:div "nearest 3 decimal places "]

      #_[m7/m '[= 45.7395 [+ 45.73 .0095] ]]

      #_[m7/m '[= 45.7395 [+ 45.73 .01] ]]









      #_[m7/m '[= 698352
              [+ 698000 1000 ] 699000 ]]











      ]



     #_[:div {:style (m7/css
                    [[7 4 (+ 12 (* 1 2)) 10  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}


      [m7/m '[- [:s [- 9] 2]]]

      [m7/m '[- [:sq [* -1 9]]]]

      [m7/m '[- [* [:sq -1] [:sq 9]]]]

      [m7/m '[- [* [:sq -1] [:sq [:p 3 2]]]]]

      [m7/m '[- [* [:sq -1] [:p [:b  [:p 3 2]]
                             [1 2]]]]]

      [m7/m '[- [* [:sq -1] 3]]]


      #_[m7/m '[= Volume [* [1 3] Area-triange height-pyramid]]]

      #_[m7/m '[= Area-triange [:m [1 2] a h] ]]

      #_[m7/m '[= Surface-Area [* 4 Area-triange]]]

      #_[m7/m '[= Surface-Area [* 4 [1 2] base height-triange ]]]







      ]


     #_[:div {:style (m7/css
                    [[8 3 (+ 2 (* 3 4)) 5  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      ;; [m7/m ['= [:p 'c 2] ['+ [:p 'a 2] [:p 'b 2]]]]

      [m7/m '[= [+ [:p b *] [:p 42 *] [:p 73 *]] [:p 180 *]]]

      [m7/m '[= [:p b *]  [- [:p 180 *] [:p 42 *] [:p 73 *]]]]



      ;; [m7/m ['= 'c [:sq ['+ [:p 'a 2] [:p 'b 2]]]]]

      ;; [m7/m ['= 'a [:sq ['- [:p 'c 2] [:p 'b 2]]]]]
      ;; [m7/m ['= 'b [:sq ['- [:p 'c 2] [:p 'a 2]]]]]



      ]


     #_[:div {:style (m7/css
                    [[8 3 (+ 1 (* 1 1)) 8  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      ;; [m7/m ['= ['opp 'hyp] [:m 'sin 'A]]]

      ;; [m7/m ['= ['adj 'hyp] [:m 'cos 'A]]]

      #_[m7/m ['= ['+ [:p 'a '*] [:p 33 '*]] [:p 180 '*]]]

      #_[m7/m ['= ['+ [:p 'a '*] [:p 33 '*] ['- [:p 33 '*]]] ['- [:p 180 '*] [:p 33 '*]]]]


      [:div "AOB is a straight line"]

      [m7/m '[= [+ [:m 4 c] 80 c] 180] ]

      [m7/m '[= [+ [:m 4 c]  c] [- 180 80]] ]



      ]





     [:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [0 -200  400 400])}
       #_[:path {:d (path [120 0 :c  -40 50 -80 50  -140 0
                         ])
               :marker-end (m7/url "i")
               :stroke (hsl [2 70 70 1])
               :stroke-dasharray "10 10"
               :stroke-width 2
               :fill :none}
          ]



       #_(let [b 500]
         [:g
          [:path {:d (path [b 0 :l 100 0 -50 -100 -50 100 0 -250 100 250])
                  :transform (m7/tranfrom [[:skewX -20]
                                           [:scale [1 .5]]])
                  :stroke (hsl [2 70 70 1])
                  :stroke-width 2
                  :fill (hsl [2 70 70 .4])}]
          [:path {:d (path [b 0 :l 100 0 -50 -100 -50 -150 ])
                  :transform (m7/tranfrom [[:skewX -20]
                                           [:scale [1 .5]]])
                  :stroke (hsl [2 70 70 1])
                  :stroke-width 2
                  :fill (hsl [2 70 70 .4])}]])



       (let [bs 40
             x 100]
         [:g
          {:transform (m7/tranfrom [[:rotate 90]])}


          [:path {:d (path [0 0 :l 100 0  100 -30 ])
                  :stroke (hsl [2 70 70 1])
                  :stroke-width 2
                  :fill :none }]

          [:path {:d (path [0 0 :l 200 0 ])
                  :stroke (hsl [2 70 70 1])
                  :stroke-width 2
                  :fill :none }]

          [:path {:d (path [0 0 :l 100 0 -100 -70 ])
                  :stroke (hsl [2 70 70 1])
                  :stroke-width 2
                  :fill :none }]

          #_[:path {:d (path [0 0 :l 100 0 0 -70 ])
                    :stroke (hsl [2 70 70 1])
                    :stroke-width 2
                    :fill :none }]


          [:path {:d (path [0 0 :l 70 0 :a 1 1 0 false true 60 0    ])
                  :stroke (hsl [2 70 70 1])
                  :stroke-width 2
                  :fill :none }]


          [:path {:d (path [0 0 :l 70 0 :a 1 1 0 false false 60 0    ])
                  :stroke (hsl [3 70 70 1])
                  :stroke-width 2
                  :fill :none }]





          #_[:path {:d (path [80 bs :l 4 (ve (- bs 26)) ])
                  :stroke (hsl [2 70 70 1])
                  :stroke-width 2
                  :fill (hsl [2 70 70 .4])}]

          #_[:text {:x bs
                  :y 0
                  :style {:font-size ".5rem"}}
           "hyp"]

          [:text {:x 100
                  :y 0
                  :dy 7
                  :style {:font-size ".5rem"}}
           "O"]

          #_[:text {:x 50
                  :y bs
                  :style {:font-size ".5rem"}}
           "adj"]



          #_[:text {:x 0
                  :y (- bs 10)
                  :style {:font-size ".5rem"}}
           "opp"]

          ])




       #_(let [bs 40
             x 100]
         [:g {:transform (m7/tranfrom [[:translate [100 0]]])}


          [:path {:d (path [0 bs :l  0 -80 0 80  100 0  -100 -80])
                  :stroke (hsl [2 70 70 1])
                  :stroke-width 2
                  :fill (hsl [2 70 70 .4])}]

          [:text {:x bs
                  :y 0
                  :style {:font-size "1rem"}}
           "c"]

          [:text {:x 50
                  :y bs
                  :style {:font-size "1rem"}}
           "b"]



          [:text {:x 0
                  :y (- bs 10)
                  :style {:font-size "1rem"}}
           "a"]

          ])


       #_[:path {:d (path [200 0 :l 100 0 -50 -100 -50 100 ])

               :stroke (hsl [2 70 70 1])
               :stroke-width 2
               :fill (hsl [2 70 70 .4])}
        ]


       #_[:path {:d (path [200 0 :l 50 0 0 -100 ])

               :stroke (hsl [2 70 70 1])
               :stroke-width 2
               :fill (hsl [2 70 70 .4])}
        ]



       ]]]))




(defn home-work12 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 1
          ax-dx 120
          ax-dy 120
          cor [[5 2] [10 9] [15 19] [20 34] [25 58] [30 95] [32 125]]
          points (into ["p"] ["a" "b" "c" "d" "e" "f" "g" "h"])
          ts (into ["t (s)"] [ 1 2 3 4 5 6 6.3])
          vms (into ["v (m/s)"] [ 1 4.5 9.5 17 29 47 62.5])
          viewbox (nth [
                        [0 -200  400 400]
                        [0 -180  200 200]
                        [0 -50  100 100]
                        [0 -25  50 50]
                        [-100 -200  800 200]
                        [40 120  80 80]
                        [0 40  100 100]
                        [75 -175  150 150]
                        [-20 -20  100 100]
                        ] zoom)
          st (fn [aa row]
               {:style (m7/css
                        [[row 1 (+ 3 (* aa 1)) 1  :center :center  2 :rem :column]
                         [(* 3 .2) 70 (+ 50 (* 5 5))  .7] []
                         {:gap ".1rem"
                          :z-index 10}])})]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}




       (map-indexed
        (fn [x v]
          [:div (st x 1) v]) points)

       (map-indexed
        (fn [x v]
          [:div (st x 2) v]) ts)


       (map-indexed
        (fn [x v]
          [:div (st x 3) v]) vms)








       ;; (map


       ;;  (fn [x v]
       ;;    [:div {:style (m7/css
       ;;                   [[3 1 (+ 3 (* x 1)) 1  :center :center  2 :rem :column]
       ;;                    [(* 5 .2) 70 (+ 50 (* 5 5))  .4] [] {:gap ".1rem"
       ;;                                                         :z-index 10}])}


       ;;     v


       ;;     ])
       ;;  (range 0 11)
       ;;  (into ["y"]
       ;;        [2
       ;;         0]))



       #_(


          (fn [[x v] ]
            [:div {:style (m7/css
                           [[7 1 (+ 12 (* x 1)) 4  :center :center  2 :rem :column]
                            [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                                 :z-index 10}])}


             v


             ])

          [0 [m7/m ['= 'AB [:sq (+ (* 18 18) (* 23 23))]]]])




       ;; (map


       ;;  (fn [[x row v] ]
       ;;    [:div {:style (m7/css
       ;;                   [[row 1 (+ 12 (* x 1)) 4  :center :center  2 :rem :column]
       ;;                    [(* 6 .3) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
       ;;                                                         :z-index 10}])}


       ;;     v


       ;;     ])

       ;;  [[0 5 [m7/m '[= [+ [:m 3 x]  [:m 2 y] ] 4]]]
       ;;   [0 6 [m7/m '[= [+ [:m 3 x]  0 ] 4]]]
       ;;   [0 7 [m7/m '[= x [4 3]]]]])


       ;; (map


       ;;  (fn [[x row v] ]
       ;;    [:div {:style (m7/css
       ;;                   [[row 1 (+ 3 (* x 1)) 4  :center :center  2 :rem :column]
       ;;                    [(* 6 .3) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
       ;;                                                         :z-index 10}])}


       ;;     v


       ;;     ])

       ;;  [[0 5 [m7/m '[= [+ [:m 5 x]  y ] 2]]]
       ;;   [0 6 [m7/m '[= [+ [:m 5 x]  0 ] 2]]]
       ;;   [0 7 [m7/m '[= x [2 5]]]]])



       ;; (map


       ;;  (fn [[x row v] ]
       ;;    [:div {:style (m7/css
       ;;                   [[row 1 (+ 12 (* x 1)) 4  :center :center  2 :rem :column]
       ;;                    [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
       ;;                                                         :z-index 10}])}


       ;;     v


       ;;     ])

       ;;  [[0 8 [m7/m '[= [+ [:m 3 x]  [:m 2 y] ] 4]]]
       ;;   [0 9 [m7/m '[= [+ 0  [:m 2 y] ] 4]]]
       ;;   [0 10 [m7/m '[= y 2]]]])



       ;; (map


       ;;  (fn [[x row v] ]
       ;;    [:div {:style (m7/css
       ;;                   [[row 1 (+ 3 (* x 1)) 4  :center :center  2 :rem :column]
       ;;                    [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
       ;;                                                         :z-index 10}])}


       ;;     v


       ;;     ])

       ;;  [[0 8 [m7/m '[= [+ [:m 5 x]  y ] 2]]]
       ;;   [0 9 [m7/m '[= [+ 0  y ] 2]]]
       ;;   [0 10 [m7/m '[= y 2]]]])









       ;; (map-indexed


       ;;  (fn [x v]
       ;;    [:div {:style (m7/css
       ;;                   [[4 1 (+ 12 (* x 1)) 1  :center :center  2 :rem :column]
       ;;                    [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
       ;;                                                         :z-index 10}])}


       ;;     v


       ;;     ])

       ;;  (into ["x"] (range -100 100 20)))








       ;; (map


       ;;  (fn [x v]
       ;;    [:div {:style (m7/css
       ;;                   [[5 1 (+ 12 (* x 1)) 1  :center :center  2 :rem :column]
       ;;                    [(* 5 .2) 70 (+ 50 (* 5 5))  .4] [] {:gap ".1rem"
       ;;                                                         :z-index 10}])}


       ;;     v


       ;;     ])
       ;;  (range 0 11)
       ;;  (into ["y"]
       ;;        (map
       ;;         (fn [x]
       ;;           (- (* 4 x) 16))
       ;;         (range -100 100 20))))




       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}




           [:path {:d (path
                       [ 0 0  :l 10 0 :c  50 -10 100  -40   120 -130
                        :c 30 -60 60 -60   90 0 :l 4 120 40 0 :c 20 20 40 50  50 0])
                   :marker-end (m7/url "i")
                   :stroke (hsl [5 70 70 1])
                   :stroke-width 1
                   :fill :none}
            ]


           #_[:path {:d (path [ 0 0  :l 10 0 :c  50 -10 100  -40   120 -130
                               :c 30 -60 60 -60   90 0 :l 4 120 40 0 :c 20 20 40 50  50 0])
                     :marker-end (m7/url "i")
                     :stroke (hsl [5 70 70 1])
                     :stroke-width 1
                     :fill :none}
              ]




           [:g

            ;; small
            (map
             (fn [i]
               [:path {:d (path [-400 (ve (+ ax-dx (* i 2))) :l 1200 0])

                       :stroke (hsl [4 70 70 .5])
                       :stroke-width .5
                       :fill :none}
                ] )
             (range 0 11))


            (map
             (fn [i]
               [:path {:d (path [(+ ax-dy (* i 2)) -400 :l 0 1200 ])

                       :stroke (hsl [4 70 70 .5])
                       :stroke-width .5
                       :fill :none}
                ] )
             (range 0 11))




            (map
             (fn [x]
               [:g

                [:path {:d (path [ (* 20 x)  0 :l 0 -400])

                        :stroke (hsl [(if (= x -2)  5 0) 70 70 1])
                        :stroke-width .5
                        :fill :none}
                 ]


                [:path {:d (path [ (* 20 x)  0 :l 0 400])

                        :stroke (hsl [0 70 70 1])
                        :stroke-width .5
                        :fill :none}
                 ]

                [:text {:x (* 20 x)
                        :y 5
                        :style {:font-size "0.4rem"}}
                 x]])
             (range -20 40))



            (map
             (fn [y]
               [:g
                [:path {:d (path [0 (ve (* 20 y))   :l 1200 0])

                        :stroke (hsl [1 70 70 1])
                        :stroke-width 1
                        :fill :none}
                 ]


                [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                        :stroke (hsl [1 70 70 1])
                        :stroke-width 1
                        :fill :none}
                 ]

                [:text {:x -10
                        :y (ve (* 20 y))
                        :style {:font-size "0.4rem"}}
                 #_(str "y=" (* y 5))
                 (* 10 y)]])
             (range -20 20))



            #_(
               (fn [y]
                 [:g {:transform (m7/tranfrom [[:rotate [69 0 (ve (* 20 y))]]])}
                  [:path {:d (path [0 (ve (* 20 y))
                                    :l 1200 0])

                          :stroke (hsl [0 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]


                  [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                          :stroke (hsl [0 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]

                  #_[:text {:x 50
                            :y (ve (* 20 y))
                            :style {:font-size "0.4rem"}}
                     (str "y=" 23)
                     ]

                  ])
               2)



            #_(
               (fn [y]
                 [:g {:transform (m7/tranfrom [[:rotate [85 0 (ve (* 20 y))]]])}
                  [:path {:d (path [0 (ve (* 20 y))
                                    :l 1200 0])

                          :stroke (hsl [0 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]


                  [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                          :stroke (hsl [0 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]

                  #_[:text {:x 50
                            :y (ve (* 20 y))
                            :style {:font-size "0.4rem"}}
                     (str "y=" 23)
                     ]

                  ])
               2)



            #_(
               (fn [y]
                 [:g {:transform (m7/tranfrom [[:rotate [56.2 0 (ve (* 20 y))]]])}
                  [:path {:d (path [0 (ve (* 20 y))
                                    :l 1200 0])

                          :stroke (hsl [0 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]


                  [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                          :stroke (hsl [0 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]

                  #_[:text {:x 50
                            :y (ve (* 20 y))
                            :style {:font-size "0.4rem"}}
                     (str "y=" 23)
                     ]

                  ])
               4.3)


            #_(
               (fn [y]
                 [:g #_{:transform (m7/tranfrom [[:rotate [50 0 (ve (* 20 y))]]])}
                  [:path {:d (path [0 (ve (* 20 y))
                                    :l 1200 0])

                          :stroke (hsl [5 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]


                  [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                          :stroke (hsl [5 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]

                  [:text {:x 50
                          :y (ve (* 20 y))
                          :style {:font-size "0.4rem"}}
                   (str "y=" y)
                   ]

                  ])
               0)

            [:text {:x -50
                    :y 0
                    :dy 35
                    :dx -35
                    :transform (m7/tranfrom [[:rotate 90]])
                    :style {:font-size ".3rem"}}
             "velocity in m/s"]

            [:text {:x 20
                    :y 30
                    :style {:font-size ".3rem"}}
             "time in sec"]
            ]








           #_(map
              (fn [[x y] text]
                [:g
                 [:circle {:cx x
                           :cy (ve (* 1 y))
                           :r 1
                           :fill (hsl [4 70 70 1])
                           }
                  ]

                 [:text {:x x
                         :y (ve (* 1 y))
                         :style {:font-size ".6rem"}

                         }
                  text
                  ]])
              [[(+ (* 3 20) (* 2 5.9)) 0] [0 (+ (* 4 20) (* 2 3))] [0 0]]

              ["A" "B" "C"]
              )










           (map
            (fn [[x y] text]
              [:g
               [:circle {:cx (* 4 x)
                         :cy (ve (* 1 y))
                         :r 1
                         :fill (hsl [2 70 70 1])
                         }
                ]
               [:text {:x (* 4 x)
                       :y (ve (* 1 y))
                       :dy -2
                       :dx -2
                       :style {:font-size ".5rem"}

                       }
                text
                ]
               ])
            cor
            (rest points)
            )





           #_[:path {:d (path [0 0 :l  45 -26  :a 28 28 0 false false   -45  -8 ])
                     :stroke (hsl [4 70 70 1])
                     :stroke-width 2
                     :fill (hsl [5 70 70 1])}]


           #_[:path {:d (path [0 0 :l 200 0])
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 2
                   :fill :none}

            [:animateTransform {:id :dec
                                :attributeName :transform
                                :begin (sec 0)
                                :dur (sec 4)
                                :type :rotate

                                :from 0
                                :to -210
                                :fill :freeze}]
            ]




           ;; [:path {:d (path [0 0 :l 200 0])
           ;;         :stroke (hsl [2 70 70 1])
           ;;         :stroke-width 2
           ;;         :fill :none}

           ;;  [:animateTransform {:id :dec
           ;;                      :attributeName :transform
           ;;                      :begin (sec 0)
           ;;                      :dur (sec 4)
           ;;                      :type :rotate

           ;;                      :from 0
           ;;                      :to -30
           ;;                      :fill :freeze}]
           ;;  ]


           ;; [:text {:x 25
           ;;         :y -5
           ;;         :style {:font-size "0.8rem"}}
           ;;  "30"]

           ;; [:text {:x 6
           ;;         :y -25
           ;;         :style {:font-size "0.8rem"}}
           ;;  "150"]
           #_[:path {:d (path [0 0 :l 100 0  0 -70])

                     :stroke (hsl [2 70 70 1])
                     :stroke-width 2
                     :fill :none}
              ]










           ])]


       ])))



(defn home-work13 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}







     [:div {:style (m7/css
                    [[2 1 (+ 1 (* 2 1)) 3  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] []
                     {:gap ".1rem"
                      :z-index 7}])}



      [m7/m '[= a [[- v u] t]]]

      ]

     [:div {:style (m7/css
                    [[3 1  (+ 1 (* 2 1)) 3  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 7}])}



      [m7/m '[= v [+ u at]]]

      ]

     [:div {:style (m7/css
                    [[4 1  (+ 1 (* 2 1)) 4  :center :center  3 :rem :column]
                     [(* 7 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 7}])}



      [m7/m '[= s [+ ut  [:m [1 2] a [:p t 2]]] ]]

      ]

     [:div {:style (m7/css
                    [[5 1  (+ 1 (* 2 1)) 4  :center :center  3 :rem :column]
                     [(* 7 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 7}])}


      [m7/m '[= [:p v 2]
              [+  [:p u 2] [:m 2  a s ]]]]

      ]









     #_[:div {:style (m7/css
                      [[2 8 (+ 2 (* 2 3)) 7  :center :center  2 :rem :column]
                       [(* 10 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                             :z-index 7}])}


        [:div "A cheetah starts from rest and accelerates at 2 m/s2 for 10 secconds"]
        [:div "final velocity"]
        [m7/m '[= v [+ u at]]]

        [m7/m '[= v [+ 0 [:m 2 [m [:p s 2]] 10 s]]]]
        [m7/m '[= v [:m 20 [m s] ]]]
        [:div "distance travelled"]

        [m7/m '[= [s t] [[+ u v] 2]]]

        [m7/m '[= [s [:m 10 s]] [:m [[+ 0 20] 2] [m s] ]]]

        [m7/m '[= s
                [:m 100 m]]]

        ]


     [:div {:style (m7/css
                    [[1 12 (+ 2 (* 2 3)) 7  :center :center  2 :rem :column]
                     [(* 10 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                           :z-index 7}])}


      [:div ""]

      [:div "At the top to the jump velocity is"
       [m7/m '[:m 0 [m s]]]]

      [:div "the whale fall 2.2m from the top of the jump  to the surface of the sea"]
      [:div "velocity when it hit the sea surface"]

      [m7/m '[= [:p v 2]
              [+  [:p u 2] [:m 2  g s ]]]]


      [m7/m '[= [:p v 2]
              [+  [:p 0 2] [:m [* 2  10] [m [:p s 2]] [:m 2.2 m] ]]]]


      [m7/m '[= [:p v 2]
              [:m 44 [[:p m 2] [:p s 2]] ]]]

      [m7/m '[= v
              [:m [:sq 44] [m s] ]]]

      [:div "OR"]
      [m7/m '[= PE KE]]


      [m7/m '[= gh [:m [1 2]  [:p v 2]]]]

      [m7/m '[= [:m 44 [[:p m 2] [:p s 2]] ] [:p v 2]]]
      [m7/m '[= [:p v 2]
              [:m 44 [[:p m 2] [:p s 2]] ]]]

      [m7/m '[= v
              [:m [:sq 44] [m s] ]]]

      ]




     #_[:div {:style (m7/css
                    [[6 8 15 6  :center :center  2 :rem :column]
                     [(* 10 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                           :z-index 7}])}

      [:div "A ball is thrown vertically upwards at 20 m/s ignoring air resistance and taking g = 10 m/s2,"]

      [:div "how high it goes"]

      [m7/m '[= [:p v 2]
              [+  [:p u 2] [:m 2  a s ]]]]

      [m7/m '[= [:p 0 2]
              [+  400 [:m [- 20] s]]]]

      [:div "the time taken to reach the height"]
      [m7/m '[= v [+ u at]]]

      [m7/m '[= 0 [+ 20 [:m [- 10] t]]]]


      [:div "time taken to return to it's start point"]


      ]


     #_[:div {:style (m7/css
                    [[2 5 (+ 4 (* 2 2)) 7 :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      [m7/m '[= accelaration [change-in-velocity time-taken]]]

      [m7/m '[= a [[- v u] t]]]
      [m7/m '[= average-speed [distance-travelled time-taken]]]

      [m7/m '[= [[+ u  v] 2] [s t]]]
      ]

     [:div {:style (m7/css
                    [[2 5 (+ 12 (* 2 2)) 7 :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}
      [file/file-input]]




     ]))



(defn home-work14 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 0
          ax-dx 80
          ax-dy 40
          cor [[5 2] [10 9] [15 19] [20 34] [25 58] [30 95] [32 125]]
          points (into ["p"] ["a" "b" "c" "d" "e" "f" "g" "h"])
          ts (into ["t (s)"] [ 1 2 3 4 5 6 6.3])
          vms (into ["v (m/s)"] [ 1 4.5 9.5 17 29 47 62.5])
          vb (fn [z]
               (nth [
                     [0 -200  400 400]
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     ] z))
          viewbox (vb zoom)
          viewbox2 (vb zoom)
          st (fn [aa row]
               {:style (m7/css
                        [[row 1 (+ 3 (* aa 1)) 1  :center :center  2 :rem :column]
                         [(* 3 .2) 70 (+ 50 (* 5 5))  .7] []
                         {:gap ".1rem"
                          :z-index 10}])})]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}




       #_(map-indexed
        (fn [x v]
          [:div (st x 1) v]) points)

       #_(map-indexed
        (fn [x v]
          [:div (st x 2) v]) ts)


       #_(map-indexed
        (fn [x v]
          [:div (st x 3) v]) vms)





       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [:marker {:id (name :dot)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5 ])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]










           #_[:path {:d (path [ 0 0  :l 10 0 :c  50 -10 100  -40   120 -130
                               :c 30 -60 60 -60   90 0 :l 4 120 40 0 :c 20 20 40 50  50 0])
                     :marker-end (m7/url (name :dot))
                     :stroke (hsl [5 70 70 1])
                     :stroke-width 1
                     :fill :none}
              ]




           [:g

            ;; small
            (map
             (fn [i]
               [:path {:d (path [-400 (ve (+ ax-dx (* i 2))) :l 1200 0])

                       :stroke (hsl [4 70 70 .5])
                       :stroke-width .5
                       :fill :none}
                ] )
             (range 0 11))


            (map
             (fn [i]
               [:path {:d (path [(+ ax-dy (* i 2)) -400 :l 0 1200 ])

                       :stroke (hsl [4 70 70 .5])
                       :stroke-width .5
                       :fill :none}
                ] )
             (range 0 11))




            (map
             (fn [x]
               [:g

                [:path {:d (path [ (* 20 x)  0 :l 0 -400])

                        :stroke (hsl [(if (= x -2)  5 0) 70 70 1])
                        :stroke-width .5
                        :fill :none}
                 ]


                [:path {:d (path [ (* 20 x)  0 :l 0 400])

                        :stroke (hsl [0 70 70 1])
                        :stroke-width .5
                        :fill :none}
                 ]

                [:text {:x (* 20 x)
                        :y 5
                        :style {:font-size "0.4rem"}}
                 (* x 5)]])
             (range -20 40))



            (map
             (fn [y]
               [:g
                [:path {:d (path [0 (ve (* 20 y))   :l 1200 0])

                        :stroke (hsl [1 70 70 1])
                        :stroke-width 1
                        :fill :none}
                 ]


                [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                        :stroke (hsl [1 70 70 1])
                        :stroke-width 1
                        :fill :none}
                 ]

                [:text {:x -10
                        :y (ve (* 20 y))
                        :style {:font-size "0.4rem"}}
                 #_(str "y=" (* y 5))
                 (* y 5)]])
             (range -20 20))



            #_(
               (fn [y]
                 [:g {:transform (m7/tranfrom [[:rotate [69 0 (ve (* 20 y))]]])}
                  [:path {:d (path [0 (ve (* 20 y))
                                    :l 1200 0])

                          :stroke (hsl [0 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]


                  [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                          :stroke (hsl [0 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]

                  #_[:text {:x 50
                            :y (ve (* 20 y))
                            :style {:font-size "0.4rem"}}
                     (str "y=" 23)
                     ]

                  ])
               2)



            #_(
               (fn [y]
                 [:g {:transform (m7/tranfrom [[:rotate [85 0 (ve (* 20 y))]]])}
                  [:path {:d (path [0 (ve (* 20 y))
                                    :l 1200 0])

                          :stroke (hsl [0 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]


                  [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                          :stroke (hsl [0 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]

                  #_[:text {:x 50
                            :y (ve (* 20 y))
                            :style {:font-size "0.4rem"}}
                     (str "y=" 23)
                     ]

                  ])
               2)



            #_(
               (fn [y]
                 [:g {:transform (m7/tranfrom [[:rotate [56.2 0 (ve (* 20 y))]]])}
                  [:path {:d (path [0 (ve (* 20 y))
                                    :l 1200 0])

                          :stroke (hsl [0 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]


                  [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                          :stroke (hsl [0 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]

                  #_[:text {:x 50
                            :y (ve (* 20 y))
                            :style {:font-size "0.4rem"}}
                     (str "y=" 23)
                     ]

                  ])
               4.3)


            #_(
               (fn [y]
                 [:g #_{:transform (m7/tranfrom [[:rotate [50 0 (ve (* 20 y))]]])}
                  [:path {:d (path [0 (ve (* 20 y))
                                    :l 1200 0])

                          :stroke (hsl [5 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]


                  [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                          :stroke (hsl [5 70 70 1])
                          :stroke-width 1
                          :fill :none}
                   ]

                  [:text {:x 50
                          :y (ve (* 20 y))
                          :style {:font-size "0.4rem"}}
                   (str "y=" y)
                   ]

                  ])
               0)

            #_[:text {:x -50
                    :y 0
                    :dy 35
                    :dx -35
                    :transform (m7/tranfrom [[:rotate 90]])
                    :style {:font-size ".3rem"}}
             "velocity in m/s"]

            #_[:text {:x 20
                    :y 30
                    :style {:font-size ".3rem"}}
             "time in sec"]
            ]








           #_(map
              (fn [[x y] text]
                [:g
                 [:circle {:cx x
                           :cy (ve (* 1 y))
                           :r 1
                           :fill (hsl [4 70 70 1])
                           }
                  ]

                 [:text {:x x
                         :y (ve (* 1 y))
                         :style {:font-size ".6rem"}

                         }
                  text
                  ]])
              [[(+ (* 3 20) (* 2 5.9)) 0] [0 (+ (* 4 20) (* 2 3))] [0 0]]

              ["A" "B" "C"]
              )










           #_(map
            (fn [[x y] text]
              [:g
               [:circle {:cx (* 4 x)
                         :cy (ve (* 1 y))
                         :r 1
                         :fill (hsl [2 70 70 1])
                         }
                ]
               [:text {:x (* 4 x)
                       :y (ve (* 1 y))
                       :dy -2
                       :dx -2
                       :style {:font-size ".5rem"}

                       }
                text
                ]
               ])
            cor
            (rest points)
            )





           #_[:path {:d (path [0 0 :l  45 -26  :a 28 28 0 false false   -45  -8 ])
                   :marker-end (m7/url (name :dot))
                   :stroke (hsl [4 70 70 1])
                   :stroke-width 2
                   :fill (hsl [5 70 70 .2])}]


           [:circle {:cx 0
                     :cy 0
                     :r 10
                     :fill (hsl [0 70 70 1])}
            ]


           (map
            (fn [i j]
              [:g

               [:circle {:cx 0
                         :cy -150
                         :r 8
                         :transform (m7/tranfrom [[:rotate (* i (/ 360 12))]])
                         :fill (hsl [2 70 70 1])}
                ]

               [:text {:x 0
                       :y -150
                       :dx -2
                       :dy 2
                       :transform (m7/tranfrom [[:rotate (* i (/ 360 12))]])
                       :style {:font-size ".3rem"}}
                j
                ]
               ])
            (range 0 12)
            [90 60 30 0 330 300 270 240 210 180 150 120 ])

           #_[:path {:d (path
                       [ 0 0  :l  100 0 ])
                     :marker-end (m7/url (name :dot))
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 2
                   :fill :none}
            ]


           #_[:path {:d (path
                       [ 100 0  :l  -100 0 ])
                   :marker-end (m7/url (name :dot))
                   :stroke (hsl [5 70 70 1])
                   :stroke-width 2
                   :fill :none}
            ]


           #_[:text {:x 50
                   :y -10
                   :style {:font-size "1rem"}
                   }
            "F1"]














           #_[:path {:d (path
                         [ 0 0  :l  60 0])
                     :id :f2
                     :transform (m7/tranfrom [
                                              [:translate [-52 30]]
                                              [:rotate -30]])
                     :marker-end (m7/url (name :dot))
                     :stroke (hsl [2 70 70 1])
                     :stroke-width 2
                     :fill :none}

              ]


           #_[:path {:d (path
                       [ 0 0  :l  80 0])
                   :id :f1
                   :transform (m7/tranfrom [
                                            [:rotate 0]])
                   :marker-end (m7/url (name :dot))
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 2
                   :fill :none}

            ]





           ;; F2
           #_[:g







            [:path {:d (path
                        [ 0 0  :l  80 0])
                    :id :f2
                    :transform (m7/tranfrom [
                                             [:translate [0 0]]
                                             [:rotate -75]])
                    :stroke-dasharray (m7/space [5 5])
                    :marker-end (m7/url (name :dot))
                    :stroke (hsl [3.5 70 70 1])
                    :stroke-width 2
                    :fill :none}

             ]

            [:text {:dy -5
                    :style {:font-size "1rem"}}
             [:textPath {:href :#ft2
                         :startOffset 40}
              "F"
              [:tspan {:dy 10} 2]]]




            ]


           #_[:path {:d (path
                       [ 0 0  :l  120 0])
                   :id :f2
                   :transform (m7/tranfrom [
                                            #_[:translate [120 0]]
                                            [:rotate -120]])
                   :marker-end (m7/url (name :dot))
                   :stroke-dasharray (m7/space [2 3])
                   :stroke (hsl [3 70 70 1])
                   :stroke-width 2
                   :fill :none}

            ]

           #_[:g
            [:path {:d (path
                        [ 0 0  :l  77 -75])
                    :id :f12
                    :marker-end (m7/url (name :dot))
                    :stroke (hsl [0 70 70 1])
                    :stroke-width 2
                    :fill :none}

             ]

            [:text {:dx 25
                    :dy -10
                    :style {:font-size "1rem"}}
             [:textPath {:href :#f12
                         :startOffset 23}

              "F"
              [:tspan {:dy 5} 1]

              [:tspan {:dy -5}
               "+"
               "F"]
              [:tspan {:dy 5} 2]]

             ]]


           #_[:path {:d (path
                       [ 0 0  :l  100 0])
                   :id :f4
                   :transform (m7/tranfrom [
                                            [:translate [0 120]]
                                            [:rotate -120]
                                            ])
                   :marker-end (m7/url (name :dot))
                   :stroke (hsl [5 70 70 1])
                   :stroke-width 2
                   :fill :none}

            ]


           ;; F1
           #_[:g
            [:text {:dy -5
                    :style {:font-size "1rem"}}
             [:textPath {:href (str "#" (name :f1))
                         :startOffset 40}
              "F"
              [:tspan {:dy 10} 1]]]
            [:path {:d (path
                        [ 0 0  :l  120 -10])
                    :id (name :f1)
                    :transform (m7/tranfrom [[:rotate 0]])
                    :marker-end (m7/url (name :dot))
                    :stroke (hsl [4.2 70 70 1])
                    :stroke-width 2
                    :fill :none}
             ]]

           ;; F1 F2
           #_[:g
            [:text {:dy -5
                    :style {:font-size "1rem"}}
             [:textPath {:href (str "#" (name :fr1))
                         :startOffset 40}
              "F"
              [:tspan {:dy 10} 3]

              [:tspan {:dy -10} "= F"]
              [:tspan {:dy 10} 1]
              [:tspan {:dy -10} " + F"]
              [:tspan {:dy 10} 2]

              ]]
            [:path {:d (path
                        [ 0 0  :l  140 -87])
                    :id (name :fr1)
                    :transform (m7/tranfrom [[:rotate 0]])
                    :marker-end (m7/url (name :dot))
                    :stroke (hsl [0 70 70 1])
                    :stroke-width 2
                    :fill :none}
             ]]





           #_(let [ag -180
                 rd 6]

             [:g
              [:path {:d (path
                          [ 0 0  :l  0 120])
                      :id (name :f1)
                      :transform (m7/tranfrom [[:rotate 0]])
                      :marker-end (m7/url (name :dot))
                      :stroke (hsl [3 70 70 1])
                      :stroke-width 2
                      :fill :none}
               ]


              [:path {:d (path
                          [ 200 0  :l
                           (ve (- 200 (* 200 (js/Math.cos (* rd (/ js/Math.PI 6)) ))))
                           (ve (* 200 (js/Math.sin (* rd (/ js/Math.PI 6)) )))
                           ])
                      :id :f3
                      :marker-end (m7/url (name :dot))
                      :stroke (hsl [3 70 70 1])
                      :stroke-width 2
                      :fill :none}
               ]])

           #_[:text {:dy -10

                   :style {:font-size ".8rem"}}
            [:textPath {:href :#f3
                        :startOffset 10}
             "F"
             [:tspan {:dy 5} "1"]
             [:tspan {:dy -5} " + F" ]

             [:tspan {:dy 5}
              "2"]

             [:tspan {:dy -5}
              "= 0"]
             ]]

           [:path {:d (path [0 0 :l 150 0])
                   :stroke (hsl [3.8 70 70 1])
                   :stroke-width 3
                   :marker-end (m7/url (name :dot))
                   :fill :none}

            [:animateTransform {:id :dec
                                :attributeName :transform
                                :begin :click
                                :dur (sec 4)
                                :type :rotate
                                :from 0
                                :to -25
                                :fill :freeze}]
            ]


           [:path {:d (path [0 0 :l  100 0])
                   :stroke (hsl [5.9 70 70 1])
                   :stroke-width 5
                   :marker-end (m7/url (name :dot))
                   :fill :none}


            ]



           [:path {:d (path [160 0 :a 160 160 0 true false   -160 160])
                   :stroke (hsl [0 70 70 1])
                   :stroke-width 2
                   :transfrom (m7/tranfrom [[:scale .3]])
                   :marker-end (m7/url (name :dot))
                   :fill :none}]


           ;; [:path {:d (path [0 0 :l 200 0])
           ;;         :stroke (hsl [2 70 70 1])
           ;;         :stroke-width 2
           ;;         :fill :none}

           ;;  [:animateTransform {:id :dec
           ;;                      :attributeName :transform
           ;;                      :begin (sec 0)
           ;;                      :dur (sec 4)
           ;;                      :type :rotate

           ;;                      :from 0
           ;;                      :to -30
           ;;                      :fill :freeze}]
           ;;  ]


           ;; [:text {:x 25
           ;;         :y -5
           ;;         :style {:font-size "0.8rem"}}
           ;;  "30"]

           ;; [:text {:x 6
           ;;         :y -25
           ;;         :style {:font-size "0.8rem"}}
           ;;  "150"]
           #_[:path {:d (path [0 0 :l 100 0  0 -70])

                     :stroke (hsl [2 70 70 1])
                     :stroke-width 2
                     :fill :none}
              ]])]


       ])))



(defn stat2 []
  (let [points [
                [0 10] [10 40] [40 30] [60 5]
                [90 45] [120 10] [150 45] [200 10]]
        m (fn [[x y]]
            (str  "M" " "
                  x
                  ","
                  y
                  " "))
        line-cmd (fn [acc [x y] index]
                   )


        bezier-command (fn [[cx cy] [cxx cyy] [x y]]
                         (str "C "
                              cx
                              ","
                              cy
                              " "
                              cxx
                              " ,"
                              cyy
                              " "
                              x
                              " ,"
                              y
                              " "))
        line-len (fn [[x1 y1] [x2 y2]]
                   (let [lx (- x2 x1)
                         ly (- y2 y1)]
                     (js/Math.sqrt
                      (+
                       (* lx lx)
                       (* ly ly)))
                     ))
        angle (fn [[x1 y1] [x2 y2]]
             (let [lx (- x2 x1)
                   ly (- y2 y1)]
               (js/Math.atan2 ly lx)))
        curve-points
        (map (fn [quadro]
               (let [[start-p [x1 y1 :as start] start-n _] quadro
                     [_ end-p [x2 y2 :as end] end-n] quadro
                     start-len (* .25 (line-len start-p start-n))
                     start-angle (angle start-p start-n)
                     end-len (* 0.25 (line-len end-p end-n))
                     end-angle (+ js/Math.PI (angle end-p end-n))
                     projection
                     (fn [[x y] angle len]
                       [
                        (+ x (* (js/Math.cos angle) len))
                        (+ y (*  (js/Math.sin angle) len))])
                     control-point-1 (projection start start-angle start-len)
                     control-point-2 (projection end end-angle end-len)]
           [[start  control-point-1]
            [end  control-point-2]]))
       (partition 4 1
                  (conj (into [[0 50]] points) [240 0])))
        svg-path-data (fn [[mp & np :as points]]
                        (reduce (fn [acc [x y]]
                                  (str acc  "L " x "," y " "))
                                 (m mp)
                                 np))
        svg-path (fn [paths]
                   [:path {:style {:fill "none"
                                   :stroke-width ".8px"
                                   :stroke "#000000"}
                           :d (svg-path-data paths)}])
        draw-curve (str (m [0 0])  " "
                        (apply str (map  (fn [[[_ c1] [end c2]]]
                                           (bezier-command c1 c2 end))
                                         curve-points))
                        " L 200,0 z")
        a (js/console.log draw-curve)
        ]
    [:svg {:viewBox "0 0 200 200"}
     [:defs
      [:linearGradient {:id "grad"
                        :x1 0
                        :x2 1
                        :y1 1
                        :y2 0}
       [:stop {:style {:stop-color "hsl(30,80%,80%)"
                       }
               :offset "0%"}
        ]
       [:stop {:style {:stop-color "hsl(45,80%,80%)"
                       :stop-opacity "15"}
               :offset "50%"}
        ]
       [:stop {:style {:stop-color "hsl(60,80%,80%)"}
               :offset "100%"}
        ]]]
 ;;    [svg-path points]
 ;;     (map (fn [[line-points1 line-points2]]
 ;;           (svg-path line-points2) ) curve-points)
 ;;     (map (fn [[line-points1 line-points2]]
 ;;           (svg-path line-points1) ) curve-points)
     [:path {:d draw-curve
             :style {:fill "url(#grad)"
                     :stroke-width ".2px"
                     :stroke "hsl(120,20%,60%)"}}]
     ]


    ))

(defn  d2t1 []
  (let [points [
                [0 10] [10 40] [40 30] [60 5]
                [90 45] [120 10] [150 45] [200 10]]
        line-len (fn [[x1 y1] [x2 y2]]
                   (let [lx (- x2 x1)
                         ly (- y2 y1)]
                     (js/Math.sqrt
                      (+
                       (* lx lx)
                       (* ly ly)))
                     ))

        angle (fn [[x1 y1] [x2 y2]]
                (let [lx (- x2 x1)
                      ly (- y2 y1)]
                  (js/Math.atan2 ly lx)))
        projection2
        (fn [[[x y] angle len]]
          [
           (+ x (* (js/Math.cos angle) len))
           (+ y (*  (js/Math.sin angle) len))])
        line-len2 (fn [[[x1 y1] [x2 y2]]]
                    (let [lx (- x2 x1)
                          ly (- y2 y1)]
                      (js/Math.sqrt
                       (+
                        (* lx lx)
                        (* ly ly)))
                      ))


        angle2 (fn [[[x1 y1] [x2 y2]]]
                 (let [lx (- x2 x1)
                       ly (- y2 y1)]
                   (js/Math.atan2 ly lx)))
        curve-points (map (fn [quadro]
                            (let [[start-p [x1 y1 :as start] start-n _] quadro
                                  [_ end-p [x2 y2 :as end] end-n] quadro
                                  start-len (* .25 (line-len start-p start-n))
                                  start-angle (angle start-p start-n)
                                  end-len (* 0.25 (line-len end-p end-n))
                                  end-angle (+ js/Math.PI (angle end-p end-n))
                                  projection
                                  (fn [[x y] angle len]
                                    [
                                     (+ x (* (js/Math.cos angle) len))
                                     (+ y (*  (js/Math.sin angle) len))])
                                  control-point-1 (projection start start-angle start-len)
                                  control-point-2 (projection end end-angle end-len)]
                              [[start  control-point-1]
                               [end  control-point-2]]))
                          (partition 4 1
                                     (conj (into [[0 50]] points) [240 0])))

        _ (partition 4 1
                     (conj (into [[0 50]] points) [240 0]))

        ]
    (vec
     (map
      (juxt
       last
       (comp
        projection2
        (juxt
         last
         (comp
          #(+ js/Math.PI %)
          angle2)
         (comp
          #(* .15 %)
          line-len2)))
       (comp
        projection2
        (juxt last
              angle2
              (comp
               #(* .15 %)
               line-len2))))
      (partition 2 1 (into [[-10 50]] points))))))

(defn d3t1 []
  (loop [data (d2t1)
         prev [[0 0] [0 0] [0 0]]
         acc []]
    (if (< (count data) 1)
      acc
      (let [[h & tail] data
            [x c2 _] h
            [_ _ c1] prev]
        (recur tail h (conj acc (into [:C ] (mapcat identity [c1 c2 x]))))))))






(defn home-work15 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        zoom 0
        vb (fn [z]
             (nth [
                   [0 -20  140 140]
                   [0 -180  200 200]
                   [0 -50  100 100]
                   [0 -25  50 50]
                   [-100 -200  800 200]
                   [40 120  80 80]
                   [0 40  100 100]
                   [75 -175  150 150]
                   [-20 -20  100 100]
                   ] z))
        viewbox (vb zoom)
        viewbox2 (vb zoom)

        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}

     [:div {:style
            (m7/css
             [[2 10 2 15  :center :center  4 :rem :column]
              [(* 10 .2) 70 (+ 50 (* 5 5))  .3] []
              {:gap ".1rem"
               :z-index 7}])}

      (let [points [
                [0 10] [10 40] [40 30] [60 5]
                [90 45] [120 10] [150 45] [200 10]]
            line-len (fn [[x1 y1] [x2 y2]]
                   (let [lx (- x2 x1)
                         ly (- y2 y1)]
                     (js/Math.sqrt
                      (+
                       (* lx lx)
                       (* ly ly)))
                     ))
            angle (fn [[x1 y1] [x2 y2]]
                    (let [lx (- x2 x1)
                          ly (- y2 y1)]
                      (js/Math.atan2 ly lx)))
            curve-points (map (fn [quadro]
                                (let [[start-p [x1 y1 :as start] start-n _] quadro
                                      [_ end-p [x2 y2 :as end] end-n] quadro
                                      start-len (* .25 (line-len start-p start-n))
                                      start-angle (angle start-p start-n)
                                      end-len (* 0.25 (line-len end-p end-n))
                                      end-angle (+ js/Math.PI (angle end-p end-n))
                                      projection
                                      (fn [[x y] angle len]
                                        [
                                         (+ x (* (js/Math.cos angle) len))
                                         (+ y (*  (js/Math.sin angle) len))])
                                      control-point-1 (projection start start-angle start-len)
                                      control-point-2 (projection end end-angle end-len)]
                                  [[start  control-point-1]
                                   [end  control-point-2]]))
                              (partition 4 1
                                         (conj (into [[0 50]] points) [240 0])))

            projection2
            (fn [[[x y] angle len]]
              [
               (+ x (* (js/Math.cos angle) len))
               (+ y (*  (js/Math.sin angle) len))])
            line-len2 (fn [[[x1 y1] [x2 y2]]]
                        (let [lx (- x2 x1)
                              ly (- y2 y1)]
                          (js/Math.sqrt
                           (+
                            (* lx lx)
                            (* ly ly)))
                          ))


            angle2 (fn [[[x1 y1] [x2 y2]]]
                     (let [lx (- x2 x1)
                           ly (- y2 y1)]
                       (js/Math.atan2 ly lx)))


            _ (partition 4 1
                         (conj (into [[0 50]] points) [240 0]))

            ]

        [:svg {:style {:height "100%"
                       :width "100%"}
               :viewBox (m7/space
                         viewbox)}
         (grid-on 1 1)
         (stat2)
         (map
          (fn [[pl1 pl2]]
            [:g
             [:path {:d pl1
                     :stroke (hsl [0 70 70 1])
                     :stroke-width 1
                     :fill :none}]
             [:path {:d pl2
                     :stroke (hsl [2 70 70 1])
                     :stroke-width 1
                     :fill :none}]])
          (map
           (juxt
            (comp
             m7/path
             #(mapcat identity %)
             (juxt last
                   (constantly [:L])
                   (comp
                    projection2
                    (juxt last
                          (comp
                           #(+ js/Math.PI %)
                           angle2)
                          (comp
                           #(* .25 %)
                           line-len2)))))
            (comp
             m7/path
             #(mapcat identity %)
             (juxt last
                   (constantly [:L])
                   (comp
                    projection2
                    (juxt last
                          angle2
                          (comp
                           #(* .25 %)
                           line-len2)))))
            )
           (partition 2 1 (into [[-10 50]] points))))


         (map
          (fn [[x y]]
            [:circle {:cx x
                      :cy y
                      :r 1
                      :fill (hsl [0 70 70 1])}])
          (into [[-10 50]] points))

         [:path {:d (m7/path (mapcat identity (into [(first points)]  (d3t1))))
                 :stroke (hsl [4.7 70 70 1])
                 :stroke-width 1
                 :fill :none}]



         ])


      #_[stat2]
      [:div "15"]
      #_[:div "35 workers are employed to complete the construction project in
16 days. Before the project start, the boss of the company told that he needs to
complete the project in 14 days. Assume that all the workers works at the rate.
how may  more workers does the need to employ in order to complete the project
on time?"]



      ;; [m7/m '[:m [:k C 2] [:k H 6]]]

      ;; [m7/m '[:m [:k C [= n 3]] [:k H [+ [* 2 3] 2]]]]

      ;; [m7/m '[:m [:k C n] [:k H [+ [:m 2 n] 2]]]]



      #_[m7/m '[+ [:m 7 x] [- [:m 9 y]] [:m 10 z]]]
      #_[m7/m '[- [:m -3 c] [* [- 3] 6]]]
      #_[m7/m '[:m [:k H 2] O]]

      #_[:div "Energy needed to melt ice at 0 deg C = 340000 (J/ Kg )"]


      #_[:div "How much energy is needed just to melt 100g of ice at 0 dec C"]
      #_[:div "Water boils at Room temparature 0 kPa"]

      #_[m7/m '[= E [* 340000 [J Kg] [100 1000] Kg]]]

      #_[m7/m '[=
              [+ [:m 2 K] [:k Cl 2] ]
              [:m [:m 2 [:p K +]]
               [:p Cl -]]]]

      #_[m7/m '[= y [k x]]]

      #_[m7/m '[= k [2 3]]]




      #_[m7/m '[= y [3 [:sq x]]]]

      #_[m7/m '[= [:m [:p 10 -3] m] [:m [1 1000] m]]]

      #_[m7/m '[= [:m [:p 10 -9] m] [:m [1 [* 1000 1000 1000]] m]]]

      #_[:div "(x,y) are not inverse proportional"]
      #_[m7/m '[= z [:sq x]]]

      #_[m7/m '[= y [3 z]]]
      #_[:div "(y,z) are  inverse proportional"]
      #_[m7/m '[= Na  [+ [:p Na +] [:p e -]]]]


      #_[m7/m '[= [- Fe [:m 2 [:p e -]]]  [:p Fe +]]]

      #_[m7/m '[= [- [:m 2 Mg] [:m 4 [:p e -]]]  [:m 4 [:p Mg +]]]]

      #_[m7/m '[= [- Cu [:m 2 [:p e -]]]  [:p Cu +]]]


      #_[m7/m '[=  [+ [:m [:p Na +] [:b [- aq]]]   [:p e -]] [:m Na [:b [- s]]]]]
      ;; [:div "time taken to return to it's start point"]

      #_[m7/m '[= [+ [:m 2 [:p Zn +] [:b [- aq]]] [:m 4 [:p e -]]]
              [:m 2 Zn [:b [- s]]]] ]

      #_[m7/m '[= [:m 2 [:p Cu +] ]  [:m 2 Cu ]] ]]


     #_[:div {:style (m7/css
                    [[2 5 (+ 4 (* 2 2)) 7 :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      [m7/m '[= accelaration [change-in-velocity time-taken]]]

      [m7/m '[= a [[- v u] t]]]
      [m7/m '[= average-speed [distance-travelled time-taken]]]

      [m7/m '[= [[+ u  v] 2] [s t]]]
      ]

     #_[:div {:style (m7/css
                    [[2 5 (+ 12 (* 2 2)) 7 :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}
      [file/file-input]]




     ]))

(defn line [y]
  [:g {:transform (m7/tranfrom [[:rotate y]])}
   [:path {:d (path [-1200 0
                     :l 2400 0])
           :id :pp2
           :stroke (hsl [0 70 70 1])
           :stroke-width 1
           :fill :none}
    ]

   [:text {
           :style {:font-size "0.8rem"}
           }
    [:textPath {:href :#pp2
                :startOffset 1220}

     (str "y=" 23)
     ]
    ]])


#_(
 [56.2 0 40])





(defn home-work16 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 0
          ax-dx 80
          ax-dy 40
          vb (fn [z]
               (nth [
                     [0 -200  400 400]
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     ] z))
          viewbox (vb zoom)
          viewbox2 (vb zoom)
          st (fn [aa row]
               {:style (m7/css
                        [[row 1 (+ 3 (* aa 1)) 1  :center :center  2 :rem :column]
                         [(* 3 .2) 70 (+ 50 (* 5 5))  .7] []
                         {:gap ".1rem"
                          :z-index 10}])})]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {
                      :background-color (hsl [1 70 70 1])
                      :gap ".1rem"
                      :z-index 30})}




       #_(map-indexed
        (fn [x v]
          [:div (st x 1) v]) points)

       #_(map-indexed
        (fn [x v]
          [:div (st x 2) v]) ts)


       #_(map-indexed
        (fn [x v]
          [:div (st x 3) v]) vms)


       [:div {:style (m7/css
                      [[2 5 1 10 :center :center 3 :rem]
                       [1 70 90 .1] [] {:gap "1rem"
                                        :z-index 5}])}
        "sulfur"]


       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [:marker {:id (name :dot)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5 ])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]

          (grid-on)

           [:circle {:cx 250
                     :cy 0
                     :r 20
                     ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                     :fill (hsl [0.5 60 70 1])}]

           [:circle {:cx 280
                     :cy 0
                     :r 20
                     ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                     :fill (hsl [0.5 60 70 1])}]



           [:circle {:cx 350
                     :cy 0
                     :r 20
                     ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                     :fill (hsl [1.5 60 70 1])}]



           [:circle {:cx 370
                     :cy 0
                     :r 20
                     ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                     :fill (hsl [1.5 60 70 1])}]





           [:circle {:cx 340
                     :cy 50
                     :r 20
                     ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                     :fill (hsl [1.5 60 70 1])}]

           #_[:circle {:cx 400
                     :cy 50
                     :r 20
                     ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                     :fill (hsl [1.5 60 70 1])}]

           #_[:circle {:cx 370
                     :cy 50
                     :r 30
                     ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                     :fill (hsl [1.5 70 10 1])}]


           #_[:text {:x 340
                   :y 70
                     ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                     :style {:color (hsl [5 70 70 1])}}
            "C"]






           [:g {:transform (m7/tranfrom [[:scale [.7 .7]]
                                         [:rotate 10]])}



           (let [n 2]
              [:g

               [:foreignObject {:x 20
                                :y -150
                                :height 50
                                :width 450
                                :style {:font-size "1.7rem"}}
                [m7/m [:m [:k 'C n] [:k 'H ['* 2 n]]]]

                #_[m7/m ['= ['+ [:m [:k 'C n] [:k 'H  (+ (* 2 n) 2)]] [:m 2[:k 'O 2]] ]
                       ['+ [:m 'C [:k 'O 2]] [:m 2 [:k 'H 2] 'O]]
                       ]]
                ]

               [:text {:x 250
                       :y -220
                       :style {:font-size "2rem"}}
                (nth ["methane" "Ethylene" "propane" "butane" "pentane" "hexane" "heptane" "octane"]
                     (- n 1))]


               (map
                (fn [x]
                  [:g {:transform (m7/tranfrom [[:translate [(* x 150) -40]]])}
                   (map
                    (fn [i j]
                      [:g
                       (if (not (= i 0))
                         [:g

                          #_[:path {:d (path
                                      [(-  (* i 75) 60 ) -10 :l  40 0])
                                  :stroke (hsl [4 70 70 1])
                                  :stroke-width 2
                                  :fill (hsl [5 70 70 .2])}]


                          [:path {:d (path [(-  (* i 75) 60 ) 0 :l  40 0])
                                  :stroke (hsl [4 70 70 1])
                                  :stroke-width 2
                                  :fill (hsl [5 70 70 .2])}]

                          #_[:path {:d (path [(-  (* i 75) 60 ) 10 :l  40 0])
                                    :stroke (hsl [4 70 70 1])
                                    :stroke-width 2
                                    :fill (hsl [5 70 70 .2])}]]
                         [:g ])


                       [:path {:d (path [(* i 75)
                                         0 :l 0 40  ])
                               :stroke (hsl [4 70 70 1])
                               :stroke-width 2
                               :fill (hsl [5 70 70 .2])}]


                       [:path {:d (path [(* i 75)
                                         0 :l 0 -40  ])
                               :stroke (hsl [4 70 70 1])
                               :stroke-width 2
                               :fill (hsl [5 70 70 .2])}]


                       [:circle {:cx (* i 75)
                                 :cy 0
                                 :r 20
                                 ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                                 :fill (hsl [(/ i 5) 60 70 1])}]
                       [:text {:x (* i 75)
                               :y 0
                               :dy 7
                               :dx -7
                               ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                               :style {:font-size "1.5rem"}}
                        "C"]

                       [:circle {:cx (* i 75)
                                 :cy 50
                                 :r 12
                                 ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                                 :fill (hsl [(/ i 5) 60 70 1])}]


                       [:circle {:cx (* i 75)
                                 :cy -40
                                 :r 12
                                 :dy -5
                                 :dx -10
                                 :fill (hsl [(/ i 5) 60 70 1])
                                 ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                                 }
                        ]



                       ])
                    (range 0 n))

                   [:path {:d (path
                               [ 0 0  :l  -100 0 ])
                           :marker-end (m7/url (name :dot))
                           :stroke (hsl [5 70 70 1])
                           :stroke-width 2
                           :fill :none}
                    ]])
                (range 0 20))




               ])
            ]


           #_[:path {:d (path
                         [ 100 0  :l  -100 0 ])
                     :marker-end (m7/url (name :dot))
                     :stroke (hsl [5 70 70 1])
                     :stroke-width 2
                     :fill :none}
              ]


           #_[:text {:x 50
                   :y -10
                   :style {:font-size "1rem"}
                   }
            "F1"]


           #_[:path {:d (path
                         [ 0 0  :l  60 0])
                     :id :f2
                     :transform (m7/tranfrom [
                                              [:translate [-52 30]]
                                              [:rotate -30]])
                     :marker-end (m7/url (name :dot))
                     :stroke (hsl [2 70 70 1])
                     :stroke-width 2
                     :fill :none}

              ]


           #_[:path {:d (path
                       [ 0 0  :l  80 0])
                   :id :f1
                   :transform (m7/tranfrom [
                                            [:rotate 0]])
                   :marker-end (m7/url (name :dot))
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 2
                   :fill :none}

            ]





           ;; F2
           #_[:g



            [:path {:d (path
                        [ 0 0  :l  80 0])
                    :id :ft2
                    :transform (m7/tranfrom [
                                             [:translate [120 -10]]
                                             [:rotate -75]])

                    :marker-end (m7/url (name :dot))
                    :stroke (hsl [3.5 70 70 1])
                    :stroke-width 2
                    :fill :none}

             ]



            [:path {:d (path
                        [ 0 0  :l  80 0])
                    :id :f2
                    :transform (m7/tranfrom [
                                             [:translate [0 0]]
                                             [:rotate -75]])
                    :stroke-dasharray (m7/space [5 5])
                    :marker-end (m7/url (name :dot))
                    :stroke (hsl [3.5 70 70 1])
                    :stroke-width 2
                    :fill :none}

             ]

            [:text {:dy -5
                    :style {:font-size "1rem"}}
             [:textPath {:href :#ft2
                         :startOffset 40}
              "F"
              [:tspan {:dy 10} 2]]]




            ]


           #_[:path {:d (path
                       [ 0 0  :l  120 0])
                   :id :f2
                   :transform (m7/tranfrom [
                                            #_[:translate [120 0]]
                                            [:rotate -120]])
                   :marker-end (m7/url (name :dot))
                   :stroke-dasharray (m7/space [2 3])
                   :stroke (hsl [3 70 70 1])
                   :stroke-width 2
                   :fill :none}

            ]

           #_[:g
            [:path {:d (path
                        [ 0 0  :l  77 -75])
                    :id :f12
                    :marker-end (m7/url (name :dot))
                    :stroke (hsl [0 70 70 1])
                    :stroke-width 2
                    :fill :none}

             ]

            [:text {:dx 25
                    :dy -10
                    :style {:font-size "1rem"}}
             [:textPath {:href :#f12
                         :startOffset 23}

              "F"
              [:tspan {:dy 5} 1]

              [:tspan {:dy -5}
               "+"
               "F"]
              [:tspan {:dy 5} 2]]

             ]]


           #_[:path {:d (path
                       [ 0 0  :l  100 0])
                   :id :f4
                   :transform (m7/tranfrom [
                                            [:translate [0 120]]
                                            [:rotate -120]
                                            ])
                   :marker-end (m7/url (name :dot))
                   :stroke (hsl [5 70 70 1])
                   :stroke-width 2
                   :fill :none}

            ]


           ;; F1
           #_[:g
            [:text {:dy -5
                    :style {:font-size "1rem"}}
             [:textPath {:href (str "#" (name :f1))
                         :startOffset 40}
              "F"
              [:tspan {:dy 10} 1]]]
            [:path {:d (path
                        [ 0 0  :l  120 -10])
                    :id (name :f1)
                    :transform (m7/tranfrom [[:rotate 0]])
                    :marker-end (m7/url (name :dot))
                    :stroke (hsl [4.2 70 70 1])
                    :stroke-width 2
                    :fill :none}
             ]]

           ;; F1 F2
           #_[:g
            [:text {:dy -5
                    :style {:font-size "1rem"}}
             [:textPath {:href (str "#" (name :fr1))
                         :startOffset 40}
              "F"
              [:tspan {:dy 10} 3]

              [:tspan {:dy -10} "= F"]
              [:tspan {:dy 10} 1]
              [:tspan {:dy -10} " + F"]
              [:tspan {:dy 10} 2]

              ]]
            [:path {:d (path
                        [ 0 0  :l  140 -87])
                    :id (name :fr1)
                    :transform (m7/tranfrom [[:rotate 0]])
                    :marker-end (m7/url (name :dot))
                    :stroke (hsl [0 70 70 1])
                    :stroke-width 2
                    :fill :none}
             ]]





           #_(let [ag -180
                 rd 6]

             [:g
              [:path {:d (path
                          [ 0 0  :l  0 120])
                      :id (name :f1)
                      :transform (m7/tranfrom [[:rotate 0]])
                      :marker-end (m7/url (name :dot))
                      :stroke (hsl [3 70 70 1])
                      :stroke-width 2
                      :fill :none}
               ]


              [:path {:d (path
                          [ 200 0  :l
                           (ve (- 200 (* 200 (js/Math.cos (* rd (/ js/Math.PI 6)) ))))
                           (ve (* 200 (js/Math.sin (* rd (/ js/Math.PI 6)) )))
                           ])
                      :id :f3
                      :marker-end (m7/url (name :dot))
                      :stroke (hsl [3 70 70 1])
                      :stroke-width 2
                      :fill :none}
               ]])

           #_[:text {:dy -10

                   :style {:font-size ".8rem"}}
            [:textPath {:href :#f3
                        :startOffset 10}
             "F"
             [:tspan {:dy 5} "1"]
             [:tspan {:dy -5} " + F" ]

             [:tspan {:dy 5}
              "2"]

             [:tspan {:dy -5}
              "= 0"]
             ]]

           #_[:path {:d (path [0 0 :l 150 0])
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 2
                   :marker-end (m7/url (name :dot))
                   :fill :none}

            [:animateTransform {:id :dec
                                :attributeName :transform
                                :begin :click
                                :dur (sec 4)
                                :type :rotate
                                :from 0
                                :to 271
                                :fill :freeze}]
            ]



           #_[:path {:d (path [160 0 :a 160 160 0 true true   -160 -160])
                   :stroke (hsl [0 70 70 1])
                   :stroke-width 2
                   :marker-end (m7/url (name :dot))
                   :fill :none}]


           ;; [:path {:d (path [0 0 :l 200 0])
           ;;         :stroke (hsl [2 70 70 1])
           ;;         :stroke-width 2
           ;;         :fill :none}

           ;; [:animateTransform {:id :dec
           ;;                      :attributeName :transform
           ;;                      :begin (sec 0)
           ;;                      :dur (sec 4)
           ;;                      :type :rotate

           ;;                      :from 0
           ;;                      :to -30
           ;;                      :fill :freeze}]
           ;;  ]


           ;; [:text {:x 25
           ;;         :y -5
           ;;         :style {:font-size "0.8rem"}}
           ;;  "30"]

           ;; [:text {:x 6
           ;;         :y -25
           ;;         :style {:font-size "0.8rem"}}
           ;;  "150"]
           #_[:path {:d (path [0 0 :l 100 0  0 -70])

                     :stroke (hsl [2 70 70 1])
                     :stroke-width 2
                     :fill :none}
              ]])]


       ])))

(defn vt []
  [:g
   [:path {:d (path
               [ 0 0  :l  80 0])
           :id :ft2
           :transform (m7/tranfrom [
                                    [:translate [120 -10]]
                                    [:rotate -75]])
           :marker-end (m7/url (name :dot))
           :stroke (hsl [3.5 70 70 1])
           :stroke-width 2
           :fill :none}
    ]

   [:path {:d (path
               [ 0 0  :l  80 0])
           :id :f2
           :transform (m7/tranfrom [
                                    [:translate [0 0]]
                                    [:rotate -75]])
           :stroke-dasharray (m7/space [5 5])
           :marker-end (m7/url (name :dot))
           :stroke (hsl [3.5 70 70 1])
           :stroke-width 2
           :fill :none}
    ]
   [:text {:dy -5
           :style {:font-size "1rem"}}
    [:textPath {:href :#ft2
                :startOffset 40}
     "F"
     [:tspan {:dy 10} 2]]]
   ])

(defn sf [m s] (+ s (* m 60)))

(defn home-work17 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40

          cor [[5 2] [10 9] [15 19] [20 34] [25 58] [30 95] [32 125]]
          points (into ["t (s)"] [0 30 150 170 (+ 180 15) (sf 3 21) (sf 5 25)
                                  (sf 9 7) (sf 14 36)
                                  (sf 14 48) (sf 14 57)
                                  (sf 21 7) (sf 22 37) (sf 23 30) (sf 25 0)])
          ts (into ["T (c)"] [0 0 1 2 3 4 18 49 87 88 89 99 100 102 102])
          vms (into ["v (m/s)"] [ 0 0])
          vb (fn [z]
               (nth [
                     [0 -200  400 400]
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     ] z))
          viewbox (vb zoom)
          viewbox2 (vb 4)
          st (fn [aa row]
               {:style (m7/css
                        [[row 1 (+ 3 (* aa 1)) 1  :center :center  1.5 :rem :column]
                         [(* 3 .2) 70 (+ 50 (* 5 5))  .7] []
                         {:gap ".1rem"
                          :z-index 10}])})]
      [:div {:style (merge
                     (grid [200 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}


       [:div {:style
              (m7/css
               [[12 20 2 18
                 :center :center  1.5 :rem :column]
                [(* 3 .2) 70 (+ 50 (* 5 5))  .7] []
                       {:gap ".1rem"
                        :z-index 10}])}

        [:video {:controls true
                 :width "100%"
                 :height "100%"
                 :autoplay true}
         [:source
          {:src
           "abc/hcurve.mp4"}]]]


       (map-indexed
        (fn [x v]
          [:div (st x 1) v]) points)

       (map-indexed
        (fn [x v]
          [:div (st x 2) v]) ts)


       #_(map-indexed
        (fn [x v]
          [:div (st x 3) v]) vms)





       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                :viewBox (m7/space
                           viewbox)}

           [:marker {:id (name :dot)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]

           (grid-on 50 10)

           [:path {:d (path [ 0  0 :l 60 0  300 (* -90 2) 200   -22 50 0])

                   :stroke (hsl [4 70 70 1])
                   :stroke-width 2
                   :fill :none}
            ]

           [:circle {:cx (* 1400 (/ 20 50))
                     :cy (ve (* 100 2))
                     :r 60
                     :fill :none
                     :stroke (hsl [.5 70 70 1])
                     :stroke-width 3

                     }]

           (map (fn [x y]
                  [:circle {:cx x
                        :cy y
                        :r 2
                        :fill (hsl [0 70 70 1])
                        :style {:font-size ".6rem"}}]
                  )


                (map (fn [x]
                       (* x (/ 20 50)))
                     points)


                (map (fn [x]
                       (ve (* 2 x))
                       )
                     ts))



           ])]


       ])))

(defn st
  ([aa row]
   (st aa row 1 1))
  ([aa row s1 s2]
   (st aa row 1 1 1.5))
  ([aa row s1 s2 fnt]
   {:style (m7/css
            [[row s1 (+ 3 (* aa 1)) s2  :center :center  fnt :rem :column]
             [(* 3 .2) 70 (+ 50 (* 5 5))  .7] []
             {:gap ".1rem"
              :z-index 10}])}))





(defn home-work18 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 0
          ax-dx 80
          ax-dy 40
          points (into ["t (s)"] [0 30 150 170 (+ 180 15) (sf 3 21) (sf 5 25)
                                  (sf 9 7) (sf 14 36)
                                  (sf 14 48) (sf 14 57)
                                  (sf 21 7) (sf 22 37) (sf 23 30) (sf 25 0)])
          ts (into ["T (c)"] [0 0 1 2 3 4 18 49 87 88 89 99 100 102 102])
          vms (into ["v (m/s)"] [ 0 0])
          box 11
          black-tri 4
          white-tri 1
          [n1 m1 o1] ['▨ '▲ '▿]
          ;; [n m o] [box black-tri white-tri]
          ;; [n m o] [n1 m1 o1]

         [n m o] [11 4 1]

          vb (fn [z]
               (nth [

                     [0 -200 400 400]
                     [0 -400  2000 2000]

                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     ] z))
          viewbox (vb zoom)
          viewbox2 (vb zoom)
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}




       #_(map-indexed
        (fn [x v]
          [:div (st x 1) v]) points)


       #_[:div (st 6 6 1 2 3)
        [m7/m '[* 4 9]]]






       #_[:div (st 6 8 1 2 3)
        [m7/m '[* 6 9]]]



       #_[:div {:style
                (m7/css
                 [[2 3 3 15
                   :center :center  5 :rem :column]
                  [5 70 (+ 50 (* 5 5))  .7] []
                  {:gap ".1rem"
                   :z-index 10}])}

          #_[m7/m '[:m 400 k Pa]]
          "Energy needed to melt ice at 0 dec C = 340 000 J/Kg * mass"

          ]


       [:div {:style
              (m7/css
               [[2 3 3 15
                 :center :center  3 :rem :column]
                [1.4 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}

        [m7/m '[= [+ [:m [:m 2 Li] (:b (- s))]  [:m 2 [:k H 2] O (:b (- l) )]
                   ]
                [+ [:m 2 Li OH (:b (- aq))] [:m [:k H 2] (:b (- g))]]]]


        [m7/m '[:m K CL]]


        [m7/m '[:m [:k K 2] C [:k O 3] ]]

        ]


       [:div {:style
              (m7/css
               [[7 5 3 15
                 :center :center  3 :rem :column]
                [0 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}

        [m7/m '[= [+ [:m [:m 1 Ca] (:b (- s))]  [:m 2 [:k H 2] O (:b (- l) )]
                   ]
                [+ [:m 1 Ca (:k (:b (:m O H)) 2) (:b (- aq))] [:m [:k H 2] (:b (- g))]]]]


        ]







       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [:marker {:id (name :dot)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]


           #_[:text {:x (* 20 3)
                   :y (ve (* 20 5))
                   :style {:font-size "2rem"}}
            9]


           #_[:text {:x (ve (* 40 1))
                   :y (ve (* 20 1))
                   :style {:font-size "2rem"}}
            4]

           #_[:text {:x (ve (* 40 1))
                   :y 60
                   :style {:font-size "2rem"}}
            6]



           #_(grid-on 20 20)
           #_(map
            (fn [y]
              (map
               (fn [x]
                 [:circle {:cx (* 30 x)
                           :cy (* 30 y -1)
                           :r 15
                           :fill (hsl [1 70 70 1])}])
               (range 0 box)
               ))
            (range 0 white-tri)
            )

           (map
            (fn [y]
              (map
               (fn [x]
                 [:circle {:cx (* 20 x (rand-int 2))
                           :cy (* 20 y (ve (rand-int 3)) )
                           :r  15
                           :fill (hsl [2 70 70 1])}])
               (range 0 box)
               ))
            (range 1 (+ black-tri 1))
            )





           ])]


       ])))

;; (.toFixed (/ (js/Math.sqrt 85) 2) 3)


(defn line2 [y eqs]
  [:g {:transform (m7/tranfrom [[:rotate y]])}
   [:path {:d (path [-1200 0
                     :l 2400 0])
           :id :pp2
           :stroke (hsl [5 70 70 .1])
           :stroke-width 1
           :fill :none}
    ]

   [:text {
           :style {:font-size "0.8rem"}
           }
    [:textPath {:href :#pp2
                :startOffset 1350}

     eqs
     ]
    ]])


(defn line3 [y  ts eqs]
  [:g {:transform
       (m7/tranfrom [[:translate ts]
                     [:rotate y]])}
   [:path {:d (path [-3000 0
                     :l 6000 0])
           :id :pp2
           :stroke (hsl [2 70 70 .5])
           :stroke-width 1
           :fill :none}
    ]

   [:text {
           :style {:font-size "0.8rem"}
           }
    [:textPath {:href :#pp2
                :startOffset 1350}

     eqs
     ]
    ]])


(defn home-work19 []
  (let [[slider set-slider] (react/useState false)
        animate-ref (react/useRef)
        animate-ref2 (react/useRef)
        font-ref (react/useRef)
        _ (react/useEffect
           (fn []
             (if (and animate-ref (-> animate-ref .-current))
               (-> animate-ref
                   .-current
                   (.animate
                    (clj->js
                     [{
                       :background (hsl [.5 70 70 .1])
                       :transform (m7/tranfrom [[:rotate "10deg"]])
                       }
                      {:background (hsl [.9 70 70 .7])
                       :transform (m7/tranfrom [[:rotate "-10deg"]])

                       }

                      {:background (hsl [2 70 70 .9])
                       :transform (m7/tranfrom [[:scale .9]])
                       :offset (/ 9 14)}

                      {:background (hsl [3.5 70 70 .7])
                       :transform (m7/tranfrom [[:scale 1]])
                       }])
                    (clj->js
                     {:duration 2000
                      :iterations 1})
                    )))
             (js/console.log "3")
             ))
        _ (react/useEffect
           (fn []
             (if (and animate-ref2 (-> animate-ref .-current))
               (-> animate-ref2
                   .-current
                   (.animate
                    (clj->js
                     [{
                       :background (hsl [.5 70 70 .1])
                       :transform (m7/tranfrom [[:rotate "10deg"]])
                       }
                      {:background (hsl [.9 70 70 .7])
                       :transform (m7/tranfrom [[:rotate "-10deg"]])

                       }

                      {:background (hsl [2 70 70 .9])
                       :transform (m7/tranfrom [[:scale .9]])
                       :offset (/ 9 14)}

                      {:background (hsl [3.5 70 70 .7])
                       :transform (m7/tranfrom [[:scale 1]])
                       }])
                    (clj->js
                     {:duration 2000
                      :iterations 1})
                    )))
             (js/console.log "1")
             ))


        _ (react/useEffect
           (fn []
             (if (and font-ref (-> font-ref .-current))
               (-> font-ref
                   .-current
                   (.animate
                    (clj->js
                     [{
                       :background (hsl [.5 70 70 .1])
                       :transform (m7/tranfrom [[:rotate "10deg"]])
                       }
                      {:background (hsl [.9 70 70 .7])
                       :transform (m7/tranfrom [[:rotate "-10deg"]])

                       }

                      { :background (hsl [2 70 70 .9])
                       :transform (m7/tranfrom [[:scale .1]])
                       :offset (/ 9 14)}

                      {
                       :background (hsl [3.5 70 70 .7])
                       :transform (m7/tranfrom [[:scale 1]])
                       }])
                    (clj->js
                     {:duration 12000
                      :iterations 1})
                    )))
             (js/console.log "")
             ))
        f                   (fn [n] (/ 1 n))
        tt                  'θ
        dx                  [1 0  0 1 -1  0 0 -1 ]
        sq                  (fn [n]
                              (comp
                               (partial map (partial * n))))
        [point set-point] (react/useState false)]
    (let [ ax-dx    -20
          ax-dy    0
          m          0
          d          1
          vb       (fn [z]
                     (nth [[0 -200  600 600]
                           [0 -25  50 50]
                           [-100 -150  200 200]
                           [-100 -85  150 155]
                           [-200 -200  800 200]
                           [40 120  80 80]
                           [0 -40  100 100]
                           [75 -175  150 150]
                           [-20 -20  100 100]
                           [-400 -350  800 400]
                           [-250 -120  800 400]
                           ] z))
          fix true
          viewbox  (vb 3)
          viewbox2 (vb 1)
          rn (range -10   22 1)
          scales [1 -1]
          dfl [:div {:style
                     (m7/css
                      [[4 1 3 6
                        :center :center  4 :rem :column]
                       [3.5 70 (+ 50 (* 5 5))  .7]
                       []
                       {:gap     ".1rem"
                        :z-index 10}])}
               #_[m7/m '[= y [:p x 2]]]
               [m7/m '[= y [- [:p x 2] [:p d 2]]]]

               #_[m7/m '[= y [- [:p [:b [- x 2]] 2] [:p 5 2]]]]
               #_[m7/m '[= 0 [- [:p [:b [- x 2]] 2] [:p 5 2]]]]
               #_[m7/m '[= 0 [:m [:b [- x 2 5]] [:b [+ [- x 2] 5]]]]]


               #_[m7/m '[= y [:p 7 2]]]
               #_[m7/m '[= x
                         [-  d]]]




               ]
          extra [:g

                 #_[line3 ]

                 [:circle {:cx 0
                           :cy (* 2 d d)
                           :fill (hsl [5 60 60 .5] )
                           :r 1}
                  [:animate {:attributeName :r
                             :from 0
                             :to 6
                             :dur (m7/not-space [1 "s"])
                             :repeatCount :indefinite}]
                  ]

                 (map
                  (fn [ne]
                    [:circle {:cx (* ne (* 20 d))
                              :cy (ve (* 2 0))
                              :fill (hsl [1 70 60 1] )
                              :r 1}
                     [:animate {:attributeName :r
                                :from 0
                                :to 4
                                :dur (m7/not-space [1 "s"])
                                :repeatCount :indefinite}]
                     ])
                  [1 -1])


                 [:path {:d (m7/path [0 0 :l
                                      0 (+ -3  (* 2 (* d d)))]
                                     )
                         :fill :none
                         :stroke-width 1

                         :id :ddd2
                         :marker-end (m7/url (name :dot))
                         :stroke (hsl [3.5 70 70 1])}
                  ]

                 [:text {:dy -5}
                  [:textPath {:href :#ddd2
                              :startOffset "20%"
                              :font-size d}
                   "d"
                   [:tspan {:dy (- d 1)}
                    "2"]
                   [:tspan {:dy (ve (- d 1))}
                    (str " = " (* d d))]

                   ]]


                 #_(map
                    (fn [sc]
                      [:path {:d (m7/path [0 0 :l  (ve (* 20 7)) 0
                                           0 (ve (- (+ (* 9 2) (* 4 20)) 3))]
                                          )
                              :fill :none
                              :stroke-width 1
                              :transform (m7/tranfrom [[:scale sc]])
                              :stroke-dasharray 10
                              :marker-end (m7/url (name :dot))
                              :stroke (hsl [0 70 70 1])}
                       [:animate {:attributeName :stroke-dashoffset
                                  :from 0
                                  :to 100
                                  :dur (m7/not-space [3 "s"])
                                  :repeatCount :indefinite}]])
                    [[1 1] [-1 1]])


                 (map
                  (fn [sc]
                    [:path {:d (m7/path [-1200 0 :l
                                         2400 0]
                                        )
                            :fill :none
                            :stroke-width 1
                            :transform (m7/tranfrom [[:scale sc]])
                            :stroke-dasharray 10
                            :marker-end (m7/url (name :dot))
                            :stroke (hsl [0 70 70 1])}
                     [:animate {:attributeName :stroke-dashoffset
                                :from 0
                                :to 100
                                :dur (m7/not-space [3 "s"])
                                :repeatCount :indefinite}]])
                  [[1 1] ])]

          ]
      [:div {
             :style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 24 (repeat [8 :vh]))])
                     {:background (hsl [1.5 70 70 .9])
                      :gap              ".1rem"})}


       (map
        (fn [n d]
          [:div {:style (m7/css
                         [[2 1 (+ 2 (* n 1)) 1  :center :center  2 :rem :column]
                          [(* n .2) 70 (+ 50 (* 1 n))  .7] [] {:gap ".1rem"
                                                               :z-index 4}])}

           d])
        (range 0 24)
        (into ["x"] rn)

        )

       (map
        (fn [n d]
          [:div {:style (m7/css
                         [[3 1 (+ 2 (* n 1)) 1  :center :center  2 :rem :column]
                          [(* n .2) 70 (+ 50 (* 1 n))  .7] [] {:gap ".1rem"
                                                               :z-index 4}])}

           d])
        (range 0 24)
        (into [[:div {:style {:cursor :pointer
                              :color (hsl [3 90 90 1])
                              :font-size "1.2rem"}
                      :on-click (fn [e]
                                  (set-slider (not slider))
                                  (js/console.log "slide r " slider))}
                #_[m7/m '[= y [:p x 2]]]
                "y"
                ]]
              (map (fn [x]
                     [:div {:style {:font-size "2rem"}}
                      (if fix
                        (- (* (- x m) (- x m)) (* d d))
                        (.toFixed (- (* (- x m) (- x m)) (* d d)) 2))


                      ])
                   rn))
        )


       (if slider
         [:div {:ref animate-ref
                :style
                (m7/css
                 [[4 6 3 7
                   :center :center  2 :rem :column]
                  [3.5 70 (+ 50 (* 5 5))  .7]
                  []
                  {:gap     ".1rem"
                   :z-index 10}])}
           [m7/m ['= 'y ['- [:p [:b ['- 'x 'm]] 2] [:p 'd 2]]]]

          [m7/m ['= ['- [:p [:b ['- 'x m]] 2] [:p d 2]] 0 ]]
          [:div  {:ref font-ref
                   :style {:font-size "2rem"
                           }}
           [m7/m ['= [:m [:b ['- 'x m d]]  [:b ['+ ['- 'x m] d]]] 0 ]]]

          [m7/m ['= ['+ ['- 'x m] d] 0]]
          [m7/m ['= ['- 'x m d] 0]]
          ]

         dfl)
       (if slider
         [:div {:ref animate-ref2
                :style
                  (m7/css
                   [[4 7 14 12
                     :center :center  4 :rem :column]
                    [1 70 (+ 50 (* 5 5))  .7]
                    []
                    {:gap     ".1rem"
                     :z-index 10}])}
          #_[m7/m ['= 'y ['- [:p [:b ['- 'x 'm]] 2] [:p 'd 2]]]]

          #_[m7/m ['= ['- [:p [:b ['- 'x m]] 2] [:p d 2]] 0 ]]



          [m7/m ['= ['- ['+ [:p 'x 2] ['- [:m 2 'm 'x]] [:p 'm 2]] [:p 'd 2]]  0]]


          [m7/m ['= ['+ [:p 'x 2] [:m (* 2 m) 'x] (- (* m m) (* d d)) ]  0]]

          #_[m7/m ['= ['- ['- [:p 'x 2] [:m 5 'x] ] 15] 0]]

          #_[m7/m ['=  ['- [:m 2 'm ]] ['- 5]] ]

          ;; [m7/m ['=  [:m 2 'm ] 5] ]

          #_[m7/m ['=  'm [5 2]] ]

          #_[m7/m '[= -15 [- [:p m 2] [:p d 2]]]]

          ;; [m7/m '[= [:p d 2] [+ [:p [:b [5 2]] 2] 15]]]

          ;; [m7/m '[= [:p d 2] [+ [25 4] [60 4]]]]

            #_[m7/m ['= ['- [:m 2 'm ]] ['- 16]] ]
            #_[m7/m ['= 'm 8]]
            ;; [m7/m ['= 'm 5] ]
          #_[m7/m '[= [+ 64
                       17]  [:p d 2]]]

            #_[m7/m '[= 81
                    [:p d 2]]]


          #_[m7/m '[= d 9]]

            ;; [m7/m ['= [:p 7 2]
            ;;        [:p 'd 2]] ]




            ]
           [:div ""])


       [:div {:style (m7/css
                      [[2 10 1 24 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style   {:height "100%"
                           :width  "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [:marker {:id           (name :dot)
                     :viewBox      (m7/space [-5 -5 10 10])
                     :refX         0
                     :refY         0
                     :orient       :auto-start-reverse
                     :markerWidth  5
                     :markerHeight 5}
            [:path {:d            (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke       (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform    (m7/tranfrom [[:rotate 0]])
                    :fill         (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to            (m7/space viewbox2)
                      :dur           "4s"
                      :fill          :freeze}]

           [:linearGradient {:x1 .5
                             :y1 1
                             :x2 .5
                             :y2 0
                             :id (name :lgg1)
                             :gradientTransform (m7/tranfrom [[:rotate 10]])}
            [:stop  {:offset 0
                     :stop-color (hsl [1 70 70 .1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [.3 70 70 .7])}
             [:animate {:attributeName :offset
                        :from .2
                        :to .5
                        :dur (m7/not-space [3 "s"])
                        :repeatCount :indefinite}]]
            [:stop  {:offset .5
                     :stop-color (hsl [.6 70 70 .4])}
             [:animate {:attributeName :offset
                        :from .3
                        :to 1
                        :dur (m7/not-space [3 "s"])
                        :repeatCount :indefinite}]]
            ]

           (flames)

           (grid-on 1 10 ax-dx ax-dy)







           (map
            (fn [x]
              [:g

               [:path {:d
                       (str (path [ 0 0 :c
                                   (* 20 3.5) (ve (* 2 2 2))
                                   (* 20 5.5) (ve (* 2 5 5))
                                   (* 20 7) (ve (* 2 7 7))
                                   :c
                                   (* 20 2) (ve (- (* 2 9 9) (* 2 7 7)))
                                   (* 20 4) (ve (- (* 2 11 11 ) (* 2 7 7)))
                                   (* 20 6) (ve (- (* 2 13 13) (* 2 7 7)))
                                   :l (ve (* 20 13))  0
                                   ])
                            "z")
                       :filter (m7/url "flames")
                       :transform
                       (m7/tranfrom
                        [
                         [:translate [(* m 20)
                                      (* 2 d d)]]
                         [:scale [x 1]]
                         ])
                       :stroke (hsl [4 70 70 1])
                       :stroke-width 1
                       :fill (m7/url (name :lgg1))
                       }
                ]


               [:g
                [:path {:d            (path [0 0 :l (* 20 d) 0])
                        :transform    (m7/tranfrom
                                    [[:translate [(* 20 m) 0]]
                                     [:scale [x 1]]
                                     ])
                        :stroke       (hsl [5 70 70 1])
                        :stroke-width .7
                        :id           (str "dd2" x)
                        :marker-end   (m7/url (name :dot))
                        :fill         :none}
                 ]
                [:text
                 [:textPath {:href        (str "#dd2" x)
                             :startOffset "20%"
                             :style       {
                                           :font-size ".4rem"}
                             } (str "d=" d)]
                 ]]])
            scales)


           [:path {:d            (path [  (* 20 m) 200 :l
                             0 (ve 500)])
                   :stroke       (hsl [2 70 70 1])
                   :stroke-width 1
                   :id           :mmpt
                   :marker-end   (m7/url (name :dot))
                   :fill         :none}
            ]


           [:text {:dy 20}
            [:textPath {:href        :#mmpt
                        :startOffset 220
                        :style       {:font-size ".9rem"}} (str "x=m=" m)]
            ]


           [:text {:dy 20}
            [:textPath {:href        :#mmpt
                        :startOffset 280
                        :style       {:font-size ".5rem"}} (str "line of symmetry")]
            ]



           (line2 0 "y=0")


           (map (fn [x y]
                  [:circle {:on-click (fn [_]
                                        (set-point true))
                            :cx    x
                            :cy    y
                            :r     (if point
                                     1
                                     1.5)
                            :fill (if point
                                    (hsl [0 70 70 .5])
                                    (hsl [2 70 70 .5]))
                            :style {:font-size ".6rem"}}]
                  )

                (map


                 (fn [x]
                       (* 20 x))


                 (range -10  20 .25))












                (map
                 (fn [x]
                   (ve (- (* 2 (* (- x m) (- x m)))
                          (* 2 d d)
                          )))






                 (range -10 20 .25)))

           extra
           ])


        ]


       ])))







(defn home-work20 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40

          cor [[5 2] [10 9] [15 19] [20 34] [25 58] [30 95] [32 125]]
          points (into ["x"] (range 0 10))
          ts (into [
                    [:div {:style {:font-size "1rem"}}
                     [m7/m '[= y [:p x
                                  2]]]]]
                   (map (fn [x] (* (- x 3) (- x 3))) (range 0 10)))
          vms (into ["v (m/s)"] [ 0 0])
          vb (fn [z]
               (nth [[100 -200  400 400]
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]] z))
          viewbox (vb 0)
          viewbox2 (vb 0)
          st (fn [aa row]
               {:style (m7/css
                        [[row 1 (+ 3 (* aa 1)) 1  :center :center  1.5 :rem :column]
                         [(* 3 .2) 70 (+ 50 (* 5 5))  .7] []
                         {:gap ".1rem"
                          :z-index 10}])})]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}






       #_[:div {:style
              (m7/css
               [[4 4 10 7
                 :center :center  2 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}

        [:div "Things don't always go how you want them to"]

        [:div "This project ended up a lot massier I thought it whould be"]
        [:div "This is how I like the things to be"]
        [:div "to begin with"]


        ]



       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [:filter#flames
            {:height "300%",
             :width "100%",
             :y "-100%",
             :x "0%",
             :filterunits "objectBoundingBox"}
            [:feTurbulence
             {:stitchtiles "stitch",
              :result "noise",
              :numoctaves "1",
              :basefrequency "0.1",
              :type "fractalNoise"}]
            [:feOffset
             {:result "off1", :dy "0"}
             [:animate
              {:repeatcount "indefinite",
               :dur "6s",
               :to "-300",
               :from "0",
               :attributename "dy",
               :attributetype "XML"}]]
            [:feOffset
             {:result "off2", :dy "60", :in "noise"}
             [:animate
              {:repeatcount "indefinite",
               :dur "6s",
               :to "0",
               :from "300",
               :attributename "dy",
               :attributetype "XML"}]
             ]
            [:feMerge
             {:result "scrolling-noise"}
             [:feMergeNode {:in "off1"}]
             [:feMergeNode {:in "off2"}]]
            [:feComponentTransfer
             {:result "brighter-noise"}
             [:feFuncA {:exponent "0.5", :amplitude "1", :type "gamma"}]]
            [:feComposite
             {:result "gradient-noise",
              :operator "in",
              :in2 "brighter-noise",
              :in "SourceGraphic"}]
            [:feComponentTransfer
             {:result "threshhold"}
             [:feFuncR {:tablevalues "0 1", :type "discrete"}]
             [:feFuncG {:tablevalues "0 1", :type "discrete"}]
             [:feFuncB {:tablevalues "0 1", :type "discrete"}]
             [:feFuncA {:tablevalues "0 1", :type "discrete"}]]
            [:feFlood {:result "yellow", :flood-color "#ff9"}]
            [:feComposite
             {:result "yellow-threshhold",
              :operator "in",
              :in "yellow",
              :in2 "threshhold"}]
            [:feFlood {:result "red", :flood-color "#f33"}]
            [:feComponentTransfer
             {:result "exponent-gradient", :in "SourceGraphic"}
             [:feFuncA {:exponent "3", :type "gamma"}]]
            [:feComposite
             {:result "red-gradient",
              :operator "in",
              :in2 "exponent-gradient",
              :in "red"}]
            [:feComposite
             {:result "red-gradient-threshhold",
              :operator "in",
              :in "red-gradient",
              :in2 "threshhold"}]
            [:feMerge
             [:feMergeNode {:in "yellow-threshhold"}]
             [:feMergeNode {:in "red-gradient-threshhold"}]]]

           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 4
                      :fill (hsl [0 70 70 1])
                      }]]

           [:linearGradient {:x1 .5
                             :y1 1
                             :x2 .5
                             :y2 0
                             :id (name :lg1)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [2 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [2.3 70 70 .7])}]
            [:stop  {:offset .77
                     :stop-color (hsl [3 70 70 .4])}
             [:animate {:attributeName :offset
                        :from 0
                        :to 1
                        :dur (m7/not-space [3 "s"])
                        :repeatCount :indefinite}]]
            ]
           ;; :indefinite
           [:marker {:id (name :dot2)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]

           #_(grid-on 1 1)

          (alkine 6)

           ;;:filter (m7/url "flames")
           ;; Height
           #_[:path {:d (path [-60 80 :l 0 (ve 185)])
                   :marker-end (m7/url (name :dot))
                   :marker-start (m7/url (name :dot))
                   :stroke (hsl [4 70 70 1])
                   :stroke-width 2
                   :fill (m7/url (name :lg1))}]

           (let [pth
                 (path [-10 0 :a 40 40 0 true false  20 0 :l 0 -60  10 0 0 -4 -20 0])]
             [:g {:transform (m7/tranfrom [[:scale 1]])}
              [:path {:d pth
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg1))}]

              [:path {:d pth
                      :transform (m7/tranfrom [[:scale [-1 1]]])
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg1))}
               ]])







           #_[:path {:d (path [-18 -50
                               :l 40 0
                               0 -20 -40 0 0 20])

                     :stroke (hsl [0 70 70 1])
                     :stroke-width 1.5
                     :filter (m7/url "flames")
                     :fill (hsl [4 40 50 1])}
              ]


           ;; jar

           [:path {:d (path [-150 80
                             :l 0 (ve 190)
                             -50 0 0 190
                             ])
                   ;; :filter (m7/url "flames")
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 2
                   :fill (m7/url (name :lg1))
                   }
            ]

           [:path {:d (path [-100 80
                             :l 0 (ve 190)
                             ])
                   :id :pp3
                   :marker-start (m7/url (name :dot2))
                   :marker-end (m7/url (name :dot2))
                   :stroke (hsl [3 70 70 1])
                   :stroke-width 2
                   :fill (hsl [4 40 50 1])}
            ]
           [:text
            [:textPath {:href :#pp3
                        :startOffset 40} "1m"]]



           [:g {:transform (m7/tranfrom [[:rotate 0]])}
            [:path {:d (path [-1200 80
                              :l 2400 0])
                    :id :dpp2
                    :stroke (hsl [0 70 70 1])
                    :stroke-width 2
                    :fill :none}
             ]

            [:text {
                    :style {:font-size "2rem"}
                    }
             [:textPath {:href :#dpp2
                         :startOffset 1220}

              (str "Distillation")
              ]
             ]]

           ;; little flask
           (let [pth
                 (path [-10 0 :a 40 40 0 true false  20 0 :l 0 -60  10 0 0 -4 -20 0])]
             [:g {:transform (m7/tranfrom [[:translate [400 25]]
                                           [:scale .7]])}
              [:path {:d pth
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      ;; :filter (m7/url "flames")
                      :fill (m7/url (name :lg1))}]

              [:path {:d pth
                      :transform (m7/tranfrom [[:scale [-1 1]]])
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      ;; :filter (m7/url "flames")
                      :fill (m7/url (name :lg1))}
               ]])

           [:path {:d (path (into [0 0 :l]
                                  (map #(* 20 %) dx)))
                   :transform (m7/tranfrom [[:translate [(* 33 20) 0]]
                                            [:scale [2 4]]])
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 1
                   :fill (m7/url (name :lg1))}
            ]

           #_[:text {:x 0
                   :y 0
                   :style {:font-size "1rem"}}
              "101 kN/m2"]
           ;; pipe
           (let [y 0
                 d 3.5]
             [:g {:transform (m7/tranfrom [[:translate [0 0]]
                                           [:rotate 0]])}


              [:path {:d (path [0 d
                                :l 0 (ve 120)  400 80 0 40 ])
                      :stroke (hsl [0 70 70 .5])
                      :stroke-width 5
                      :stroke-linejoin :round
                      :fill :none

                      }
               ]

              [:path {:d (path [0 d
                                :l 0 (ve 120)  400 80 0 40])
                      :stroke  (m7/url (name :lg1))
                      :stroke-linejoin :round
                      :stroke-width 4
                      :fill :none
                      :filter (m7/url "flames")
                      }
               ]


              [:path {:d (path [0 (ve 120)
                                :l  400 80 ])
                      :stroke  (m7/url (name :lg1))
                      :stroke-linejoin :round
                      :stroke-dashoffset 300
                      :stroke-dasharray 200
                      :stroke-width 20
                      :fill :none

                      }
               ]

              ])


           (let [y 0
                 d 3.5]
             [:g {:transform (m7/tranfrom [[:translate [405 0]]
                                           [:scale .7]
                                           [:rotate 0]])}


              [:path {:d (path [0 d
                                :l 0 (ve 120)  400 80 0 40 ])
                      :stroke (hsl [0 70 70 .5])
                      :stroke-width 5
                      :stroke-linejoin :round
                      :fill :none

                      }
               ]

              [:path {:d (path [0 d
                                :l 0 (ve 120)  400 80 0 40])
                      :stroke  (m7/url (name :lg1))
                      :stroke-linejoin :round
                      :stroke-width 4
                      :fill :none
                      ;; :filter (m7/url "flames")
                      }
               ]


              [:path {:d (path [0 (ve 120)
                                :l  400 80 ])
                      :stroke  (m7/url (name :lg1))
                      :stroke-linejoin :round
                      :stroke-dashoffset 300
                      :stroke-dasharray 200
                      :stroke-width 20
                      :fill :none

                      }
               ]

              ])





           ]
          )]


       ])))







(defn home-work22 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40

          cor [[5 2] [10 9] [15 19] [20 34] [25 58] [30 95] [32 125]]
          points (into ["x"] (range 0 10))
          ts (into [
                    [:div {:style {:font-size "1rem"}}
                     [m7/m '[= y [:p x
                                  2]]]]]
                   (map (fn [x] (* (- x 3) (- x 3))) (range 0 10)))
          vms (into ["v (m/s)"] [ 0 0])
          vb (fn [z]
               (nth [[100 -200  400 400]
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]] z))
          viewbox (vb 0)
          viewbox2 (vb 0)
          st (fn [aa row]
               {:style (m7/css
                        [[row 1 (+ 3 (* aa 1)) 1  :center :center  1.5 :rem :column]
                         [(* 3 .2) 70 (+ 50 (* 5 5))  .7] []
                         {:gap ".1rem"
                          :z-index 10}])})]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}






       #_[:div {:style
              (m7/css
               [[4 4 10 7
                 :center :center  2 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}

        [:div "Things don't always go how you want them to"]

        [:div "This project ended up a lot massier I thought it whould be"]
        [:div "This is how I like the things to be"]
        [:div "to begin with"]


        ]



       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [:filter#flames
            {:height "300%",
             :width "100%",
             :y "-100%",
             :x "0%",
             :filterunits "objectBoundingBox"}
            [:feTurbulence
             {:stitchtiles "stitch",
              :result "noise",
              :numoctaves "1",
              :basefrequency "0.1",
              :type "fractalNoise"}]
            [:feOffset
             {:result "off1", :dy "0"}
             [:animate
              {:repeatcount "indefinite",
               :dur "6s",
               :to "-300",
               :from "0",
               :attributename "dy",
               :attributetype "XML"}]]
            [:feOffset
             {:result "off2", :dy "60", :in "noise"}
             [:animate
              {:repeatcount "indefinite",
               :dur "6s",
               :to "0",
               :from "300",
               :attributename "dy",
               :attributetype "XML"}]
             ]
            [:feMerge
             {:result "scrolling-noise"}
             [:feMergeNode {:in "off1"}]
             [:feMergeNode {:in "off2"}]]
            [:feComponentTransfer
             {:result "brighter-noise"}
             [:feFuncA {:exponent "0.5", :amplitude "1", :type "gamma"}]]
            [:feComposite
             {:result "gradient-noise",
              :operator "in",
              :in2 "brighter-noise",
              :in "SourceGraphic"}]
            [:feComponentTransfer
             {:result "threshhold"}
             [:feFuncR {:tablevalues "0 1", :type "discrete"}]
             [:feFuncG {:tablevalues "0 1", :type "discrete"}]
             [:feFuncB {:tablevalues "0 1", :type "discrete"}]
             [:feFuncA {:tablevalues "0 1", :type "discrete"}]]
            [:feFlood {:result "yellow", :flood-color "#ff9"}]
            [:feComposite
             {:result "yellow-threshhold",
              :operator "in",
              :in "yellow",
              :in2 "threshhold"}]
            [:feFlood {:result "red", :flood-color "#f33"}]
            [:feComponentTransfer
             {:result "exponent-gradient", :in "SourceGraphic"}
             [:feFuncA {:exponent "3", :type "gamma"}]]
            [:feComposite
             {:result "red-gradient",
              :operator "in",
              :in2 "exponent-gradient",
              :in "red"}]
            [:feComposite
             {:result "red-gradient-threshhold",
              :operator "in",
              :in "red-gradient",
              :in2 "threshhold"}]
            [:feMerge
             [:feMergeNode {:in "yellow-threshhold"}]
             [:feMergeNode {:in "red-gradient-threshhold"}]]]

           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 4
                      :fill (hsl [0 70 70 1])
                      }]]

           [:linearGradient {:x1 .5
                             :y1 1
                             :x2 .5
                             :y2 0
                             :id (name :lg1)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [2 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [2.3 70 70 .7])}]
            [:stop  {:offset .77
                     :stop-color (hsl [3 70 70 .4])}
             [:animate {:attributeName :offset
                        :from 0
                        :to 1
                        :dur (m7/not-space [3 "s"])
                        :repeatCount :indefinite}]]
            ]
           ;; :indefinite
           [:marker {:id (name :dot2)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]

           #_(grid-on 1 1)

          (alkine 6)

           ;;:filter (m7/url "flames")
           ;; Height
           #_[:path {:d (path [-60 80 :l 0 (ve 185)])
                   :marker-end (m7/url (name :dot))
                   :marker-start (m7/url (name :dot))
                   :stroke (hsl [4 70 70 1])
                   :stroke-width 2
                   :fill (m7/url (name :lg1))}]

           (let [pth
                 (path [-10 0 :a 40 40 0 true false  20 0 :l 0 -60  10 0 0 -4 -20 0])]
             [:g {:transform (m7/tranfrom [[:scale 1]])}
              [:path {:d pth
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg1))}]

              [:path {:d pth
                      :transform (m7/tranfrom [[:scale [-1 1]]])
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg1))}
               ]])







           #_[:path {:d (path [-18 -50
                               :l 40 0
                               0 -20 -40 0 0 20])

                     :stroke (hsl [0 70 70 1])
                     :stroke-width 1.5
                     :filter (m7/url "flames")
                     :fill (hsl [4 40 50 1])}
              ]


           ;; jar



           [:path {:d (path [-25 0
                             :l 0 (ve 190)
                             50 0 0 190
                             ])
                   ;; :filter (m7/url "flames")
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 2
                   :fill (m7/url (name :lg1))
                   }
            ]

           [:path {:d (path [-100 80
                             :l 0 (ve 190)
                             ])
                   :id :pp3
                   :marker-start (m7/url (name :dot2))
                   :marker-end (m7/url (name :dot2))
                   :stroke (hsl [3 70 70 1])
                   :stroke-width 2
                   :fill (hsl [4 40 50 1])}
            ]
           [:text
            [:textPath {:href :#pp3
                        :startOffset 40} "1m"]]



           [:g {:transform (m7/tranfrom [[:rotate 0]])}
            [:path {:d (path [-1200 80
                              :l 2400 0])
                    :id :dpp2
                    :stroke (hsl [0 70 70 1])
                    :stroke-width 2
                    :fill :none}
             ]

            [:text {
                    :style {:font-size "2rem"}
                    }
             [:textPath {:href :#dpp2
                         :startOffset 1220}

              (str "Distillation")
              ]
             ]]

           ;; little flask
           (let [pth
                 (path [-10 0 :a 40 40 0 true false  20 0 :l 0 -60  10 0 0 -4 -20 0])]
             [:g {:transform (m7/tranfrom [[:translate [400 25]]
                                           [:scale .7]])}
              [:path {:d pth
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      ;; :filter (m7/url "flames")
                      :fill (m7/url (name :lg1))}]

              [:path {:d pth
                      :transform (m7/tranfrom [[:scale [-1 1]]])
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      ;; :filter (m7/url "flames")
                      :fill (m7/url (name :lg1))}
               ]])

           [:path {:d (path (into [0 0 :l]
                                  (map #(* 20 %) dx)))
                   :transform (m7/tranfrom [[:translate [(* 33 20) 0]]
                                            [:scale [2 4]]])
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 1
                   :fill (m7/url (name :lg1))}
            ]

           #_[:text {:x 0
                   :y 0
                   :style {:font-size "1rem"}}
              "101 kN/m2"]
           ;; pipe
           (let [y 0
                 d 3.5]
             [:g {:transform (m7/tranfrom [[:translate [0 0]]
                                           [:rotate 0]])}


              [:path {:d (path [0 d
                                :l 0 (ve 120)  400 80 0 40 ])
                      :stroke (hsl [0 70 70 .5])
                      :stroke-width 5
                      :stroke-linejoin :round
                      :fill :none

                      }
               ]

              [:path {:d (path [0 d
                                :l 0 (ve 120)  400 80 0 40])
                      :stroke  (m7/url (name :lg1))
                      :stroke-linejoin :round
                      :stroke-width 4
                      :fill :none
                      :filter (m7/url "flames")
                      }
               ]


              [:path {:d (path [0 (ve 120)
                                :l  400 80 ])
                      :stroke  (m7/url (name :lg1))
                      :stroke-linejoin :round
                      :stroke-dashoffset 300
                      :stroke-dasharray 200
                      :stroke-width 20
                      :fill :none

                      }
               ]

              ])


           (let [y 0
                 d 3.5]
             [:g {:transform (m7/tranfrom [[:translate [405 0]]
                                           [:scale .7]
                                           [:rotate 0]])}


              [:path {:d (path [0 d
                                :l 0 (ve 120)  400 80 0 40 ])
                      :stroke (hsl [0 70 70 .5])
                      :stroke-width 5
                      :stroke-linejoin :round
                      :fill :none

                      }
               ]

              [:path {:d (path [0 d
                                :l 0 (ve 120)  400 80 0 40])
                      :stroke  (m7/url (name :lg1))
                      :stroke-linejoin :round
                      :stroke-width 4
                      :fill :none
                      ;; :filter (m7/url "flames")
                      }
               ]


              [:path {:d (path [0 (ve 120)
                                :l  400 80 ])
                      :stroke  (m7/url (name :lg1))
                      :stroke-linejoin :round
                      :stroke-dashoffset 300
                      :stroke-dasharray 200
                      :stroke-width 20
                      :fill :none

                      }
               ]

              ])





           ]
          )]


       ])))




(defn home-work21 []
  (let [[slider get-slider] (react/useState 0)
        [color set-color] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40

          cor [[5 2] [10 9] [15 19] [20 34] [25 58] [30 95] [32 125]]
          points (into ["x"] (range 0 10))
          ts (into [
                    [:div {:style {:font-size "1rem"}}
                     [m7/m '[= y [:p x
                                  2]]]]]
                   (map (fn [x] (* (- x 3) (- x 3))) (range 0 10)))
          vms (into ["v (m/s)"] [ 0 0])
          vb (fn [z]
               (nth [[0 -200  400 400]
                     [0 -512  800 600]
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -90  100 100]
                     [-100 -200  800 200]
                     [0 -50  100 100]
                     [0 -20  100 100]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [0 -500  800 600]] z))
          temmat [["4" "3" "abc@kmail.com" "017555224" "2" "abc abc" "chrismas 2" "due at 13"]
                  ["2" "5" "daf@kmail.com" "01855224" "5" "def def" "cake" "none"]]
          tem (nth temmat
                   1)
          viewbox (vb 1)
          viewbox2 (vb 1)
          st (fn [aa row]
               {:style (m7/css
                        [[row 1 (+ 3 (* aa 1)) 1  :center :center  1.5 :rem :column]
                         [(* 3 .2) 70 (+ 50 (* 5 5))  .7] []
                         {:gap ".1rem"
                          :z-index 10}])})]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}
       #_(map
        (fn [i j]
          [:div {:style
                 (m7/css
                  [[2 1 (* i 3) 3
                    :center :center  2 :rem :column]
                   [5 70 (+ 50 (* 5 5))  .7] []
                   {:gap ".1rem"
                    :z-index 10}])}
           j])
        (range 1 10)
        ["House no" "Road no" "email" "phone #" "Roll No" "Name" "Gift Name" "Comments"])



       #_[:div (st 3 4) [m7/m '[= P [F A]]]]
       #_[:div (st 3 4) [m7/m '[= P
                              [N [:p m 2]]
                              [:m N [:p m -2]]
                              ]
                       ]]

       #_(map-indexed
        (fn [x v]
          [:div (st x 3) v]) vms)





       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:on-click (fn [e]
                             (js/console.log "hello" color)
                             (set-color 2))
                 :style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [:marker {:id (name :dot3)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [-5 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:scale [2 2]]
                                             [:rotate 0]
                                             ])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]




           #_(let [z -90]
             [:g {:transform (m7/tranfrom [[:rotate z]])}
              [:path {:d (path [-1200 140
                                :l 2400 0])
                      :id :pppp2
                      :stroke (hsl [2 60 70 1])
                      :stroke-width 3
                      :fill :none}
               ]

              [:text {
                      :style {:font-size "0.8rem"}
                      }
               [:textPath {:href :#pppp2
                           :startOffset 1320}

                (str "x=" "7")
                ]
               ]])

           (grid-on 5 2)




           (let [pth
                 (path [-10 0 :a 40 40 0 true false  20 0 :l 0 -60 10 0 0 -4 -20 0])]
             [:g
              [:path {:d pth
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 1
                      :fill :none}
               ]

              [:path {:d pth
                      :transform (m7/tranfrom [[:scale [-1 1]]])
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 1
                      :fill :none}
               ]])


           #_(let [y 90
                 d 2.5]
             [:g {
                  :transform (m7/tranfrom [[:translate [0 (ve 60)]]
                                           [:rotate y]
                                           ])}
              (map
               (fn [d]
                 [:path {:d (path [0 d
                                   :l (ve 40) 0])
                         :id :pp2
                         :stroke (hsl [color 70 70 1])
                         :stroke-width 1
                         :fill :none}
                  ])
               [d (ve d)])


              ])
           #_(let [y 0
                 d 2.5]
             [:g {:transform (m7/tranfrom [[:translate [0 (ve 100)]]
                                           [:rotate 10]])}
              (map (fn [d]
                     [:path {:d (path [0 d
                                       :l 400 0])
                             :id :pp2
                             :stroke (hsl [0 70 70 1])
                             :stroke-width 1
                             :fill :none}
                      ])
                   [d (ve d)])])




           #_(let [y 0
                 d 9
                 ]

             [:g {:transform (m7/tranfrom [[:rotate 10]])}
              (map
               (fn [s]
                 [:g  {:transform (m7/tranfrom
                                   [
                                    [:translate
                                     [120 (ve 100)]]
                                    [:scale [s 1]]
                                    ])}
                  (map (fn [d]
                         [:path {:d (path [0 d
                                           :l 50 0 0 d])
                                 :stroke (hsl [0 70 70 1])
                                 :stroke-width 1
                                 :fill :none}
                          ])
                       [d (ve d)])

                  [:circle {:cx 0
                            :cy 0
                            :r 10
                            :stroke (hsl [0 70 70 1])
                            :stroke-width 1
                            :fill :none
                            }]

                  ])
               [1 -1])])


           (map
            (fn [y]
              (map
               (fn [x]
                 [:path {:d (path [(* x 80) (ve (* 60 y)) :l 80 0 0 -60 -80 0 0 60])
                         :stroke (hsl [3 70 70 1])
                         :stroke-width 4
                         :fill (hsl [4 70 70 1])}
                  ])
               (range 9)))
            (range 12))

           (let [y 0]
             [:g {:transform (m7/tranfrom [[:rotate y]])}
              [:path {:d (path [-1200 (ve 20)
                                :l 2400 0])
                      :id :ppp2
                      :stroke (hsl [5 70 70 1])
                      :stroke-width 2
                      :fill :none}
               ]

              [:text {
                      :style {:font-size "0.8rem"}
                      }
               [:textPath {:href :#ppp2
                           :startOffset 1220}

                (str "y=" 10)
                ]
               ]



              ])

           (let [y 0]
             [:g {:transform (m7/tranfrom [[:rotate y]])}
              [:path {:d (path [0 0
                                :l (* 3 3 80) 0])
                      :marker-end (m7/url (name :dot3))
                      :id :appp2
                      :stroke (hsl [5 70 70 1])
                      :stroke-width 10
                      :fill :none}
               ]

              [:text {
                      :style {:font-size "3rem"}
                      }
               [:textPath {:href :#appp2
                           :startOffset 120}

                (str (* 3 12))
                ]
               ]

              [:animateTransform {:id :dec
                                  :attributeName :transform

                                  :begin :click
                                  :dur (sec 4)
                                  :type :rotate

                                  :from 0
                                  :to -90
                                  :fill :freeze}]])




           ])]


       ])))



(defn banner1 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40

          cor [[5 2] [10 9] [15 19] [20 34] [25 58] [30 95] [32 125]]
          points (into ["x"] (range 0 10))
          ts (into [
                    [:div {:style {:font-size "1rem"}}
                     [m7/m '[= y [:p x
                                  2]]]]]
                   (map (fn [x] (* (- x 3) (- x 3))) (range 0 10)))
          vms (into ["v (m/s)"] [ 0 0])
          vb (fn [z]
               (nth [[0 -200  400 400]
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]] z))
          temmat [["4" "3" "abc@kmail.com" "017555224" "2" "abc abc" "chrismas 2" "due at 13"]
                  ["2" "5" "daf@kmail.com" "01855224" "5" "def def" "cake" "none"]]
          tem (nth temmat
                   1)
          viewbox (vb 0)
          viewbox2 (vb 0)
          st (fn [aa row]
               {:style (m7/css
                        [[row 1 (+ 3 (* aa 1)) 1  :center :center  1.5 :rem :column]
                         [(* 3 .2) 70 (+ 50 (* 5 5))  .7] []
                         {:gap ".1rem"
                          :z-index 10}])})]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}
       #_(map
        (fn [i j]
          [:div {:style
                 (m7/css
                  [[2 1 (* i 3) 3
                    :center :center  2 :rem :column]
                   [5 70 (+ 50 (* 5 5))  .7] []
                   {:gap ".1rem"
                    :z-index 10}])}
           j])
        (range 1 10)
        ["House no" "Road no" "email" "phone #" "Roll No" "Name" "Gift Name" "Comments"])


       #_(map
        (fn [i j]
          [:div {:style
                 (m7/css
                  [[3 1 (* i 3) 3
                    :center :center  2 :rem :column]
                   [5 70 (+ 50 (* 5 5))  .7] []
                   {:gap ".1rem"
                    :z-index 10}])}
           j])
        (range 1 10)
        ["4" "3" "abc@kmail.com" "017555224" "2" "abc abc" "chrismas 2" "due at 13"])

       #_(map
        (fn [i j]
          [:div {:style
                 (m7/css
                  [[4 1 (* i 3) 3
                    :center :center  2 :rem :column]
                   [2 70 (+ 50 (* 5 5))  .7] []
                   {:gap ".1rem"
                    :z-index 10}])}
           j])
        (range 1 10)
        ["2" "5" "daf@kmail.com" "01855224" "5" "def def" "cake" "none"]
        )


       #_[:div {:style
              (m7/css
               [[8 1 3 3
                 :center :center  1.5 :rem :column]
                [3 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}
        (str "Dear "
             (nth tem 5)

             ) ]


       #_[:div {:style
              (m7/css
               [[8 1 9 3
                 :center :center  1.5 :rem :column]
                [3 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}
        (nth tem 3)]


       [:div {:style
              (m7/css
               [[4 3 10 7
                 :center :center  4 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}

        [:div ""]
        [:div  {:style {:font-size "2rem"}} "Back" ]




        #_[m7/m '[= g [:m 9.8 [:m m [:p s -2]]]]]


        #_[m7/m '[= [:m ρ A h]
                  m]]

        #_[m7/m '[= W mg [:m 9.8 m N]]]

        #_[m7/m '[= P [W A] [ [:m ρ A h g] A] ]]

        #_[m7/m '[=  P [F A] [N [:p m 2]] [:m N [:p m -2]] Pa]]



        #_[m7/m '[= Density ρ [m V] [ Kg [:p m 3]] [:m Kg [:p m -3]]]]



        #_[m7/m '[= [1 [:p x 3] ] [:p x -3] ]]


        ;; [m7/m '[= ρV [:m V [m V]] ]]


        ;; [m7/m '[= m ρV ]]

        ;; [m7/m '[= W mg [:m ρ V g] ]]

        ;; [m7/m '[= P [W A] [[:m ρ A h g] A]  [:m ρ h g]  ]]


        ]


       #_[:div {:style
              (m7/css
               [[4 4 7 7
                 :center :center  5 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}
        [m7/m '[= P pgh]]


        ]

       #_[:div {:style
              (m7/css
               [[11 1 3 3
                 :center :center  1.5 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}
        (str "H# "  (nth tem 0))]

       #_[:div {:style
              (m7/css
               [[11 1 6 3
                 :center :center  1.5 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}
        (str "Road# "  (nth tem 1))]



       #_[:div {:style
              (m7/css
               [[9 3 3 7
                 :center :center  2 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}
        "Pressure of Liquid"
        [m7/m '[= P pgh]]
        [m7/m '[= g [:m 9.8 m [:p s -2]]]]]




       #_(map-indexed
        (fn [x v]
          [:div (st x 2) v]) ts)



       #_[:div (st 3 4) [m7/m '[= P [F A]]]]
       #_[:div (st 3 4) [m7/m '[= P
                              [N [:p m 2]]
                              [:m N [:p m -2]]
                              ]
                       ]]

       #_(map-indexed
        (fn [x v]
          [:div (st x 3) v]) vms)





       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [:filter#flames
            {:height "300%",
             :width "100%",
             :y "-100%",
             :x "0%",
             :filterunits "objectBoundingBox"}
            [:feTurbulence
             {:stitchtiles "stitch",
              :result "noise",
              :numoctaves "1",
              :basefrequency "0.1",
              :type "fractalNoise"}]
            [:feOffset
             {:result "off1", :dy "0"}
             [:animate
              {:repeatcount "indefinite",
               :dur "6s",
               :to "-300",
               :from "0",
               :attributename "dy",
               :attributetype "XML"}]]
            [:feOffset
             {:result "off2", :dy "60", :in "noise"}
             [:animate
              {:repeatcount "indefinite",
               :dur "6s",
               :to "0",
               :from "300",
               :attributename "dy",
               :attributetype "XML"}]
             ]
            [:feMerge
             {:result "scrolling-noise"}
             [:feMergeNode {:in "off1"}]
             [:feMergeNode {:in "off2"}]]
            [:feComponentTransfer
             {:result "brighter-noise"}
             [:feFuncA {:exponent "0.5", :amplitude "1", :type "gamma"}]]
            [:feComposite
             {:result "gradient-noise",
              :operator "in",
              :in2 "brighter-noise",
              :in "SourceGraphic"}]
            [:feComponentTransfer
             {:result "threshhold"}
             [:feFuncR {:tablevalues "0 1", :type "discrete"}]
             [:feFuncG {:tablevalues "0 1", :type "discrete"}]
             [:feFuncB {:tablevalues "0 1", :type "discrete"}]
             [:feFuncA {:tablevalues "0 1", :type "discrete"}]]
            [:feFlood {:result "yellow", :flood-color "#ff9"}]
            [:feComposite
             {:result "yellow-threshhold",
              :operator "in",
              :in "yellow",
              :in2 "threshhold"}]
            [:feFlood {:result "red", :flood-color "#f33"}]
            [:feComponentTransfer
             {:result "exponent-gradient", :in "SourceGraphic"}
             [:feFuncA {:exponent "3", :type "gamma"}]]
            [:feComposite
             {:result "red-gradient",
              :operator "in",
              :in2 "exponent-gradient",
              :in "red"}]
            [:feComposite
             {:result "red-gradient-threshhold",
              :operator "in",
              :in "red-gradient",
              :in2 "threshhold"}]
            [:feMerge
             [:feMergeNode {:in "yellow-threshhold"}]
             [:feMergeNode {:in "red-gradient-threshhold"}]]]

           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 4
                      :fill (hsl [0 70 70 1])
                      }]]

           [:linearGradient {:x1 .5
                             :y1 1
                             :x2 .5
                             :y2 0
                             :id (name :lg1)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [2 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [2.3 70 70 .7])}]
            [:stop  {:offset .77
                     :stop-color (hsl [3 70 70 .4])}
             [:animate {:attributeName :offset
                        :from 0
                        :to 1
                        :dur (m7/not-space [3 "s"])
                        :repeatCount 10}]]
            ]
           ;; :indefinite
           [:marker {:id (name :dot2)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
 :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]

           #_(grid-on 1 10)

           ;;:filter (m7/url "flames")
           ;; Height
           #_[:path {:d (path [-60 80 :l 0 (ve 185)])
                   :marker-end (m7/url (name :dot))
                   :marker-start (m7/url (name :dot))
                   :stroke (hsl [4 70 70 1])
                   :stroke-width 2
                   :fill (m7/url (name :lg1))}]

           (let [pth
                 (path [-10 0 :a 40 40 0 true false  20 0 :l 0 -60  10 0 0 -4 -20 0])]
             [:g
              [:path {:d pth
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      ;; :filter (m7/url "flames")
                      :fill (m7/url (name :lg1))}]

              [:path {:d pth
                      :transform (m7/tranfrom [[:scale [-1 1]]])
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg1))}
               ]])



           [:path {:d (path [-18 -50
                             :l 40 0
                             0 -20 -40 0 0 20])

                   :id :pp2
                   :stroke (hsl [0 70 70 1])
                   :stroke-width 1.5
                   :fill (hsl [4 40 50 1])}
            ]


           [:g {:transform (m7/tranfrom [[:rotate 10]])}

            [:circle {:cx 600
                      :cy 80
                      :r 5
                      :fill (hsl [5 70 70 1])}
             ]
            [:path {:d (path [-600 80
                              :l 1200 0 ])
                    :id :dpp2
                    :stroke (hsl [0 70 70 1])
                    :stroke-width 2
                    :fill (hsl [3 70 70 1])}
             ]

            [:text {
                    :style {:font-size "4rem"
                            :stroke-width 1
                            :stroke (hsl [0 70 70 1])
                            :fill (hsl [0.3 70 85 1])}
                    }
             [:textPath {:href :#dpp2
                         :startOffset 1220}

              (str "Heighering Teaching Assistant" )
              ]
             ]]


           (let [y 90
                 d 3.5]
             [:g {:transform (m7/tranfrom [[:translate [0 (ve 60)]]
                                           [:rotate y]

                                           ])}

              [:path {:d (path [0 d
                                :l (ve 540) 0
                                0 d 5 40 0 (ve d) 0 ])

                      :id :pp2
                      :stroke (hsl [0 70 70 1])
                      :stroke-width 1.5
                      :fill (hsl [3 70 70 1])}
               ]



              ])

           #_[:path {:d (path [400 120
                             :l 20 0 0 (ve 120) -40 0 0 120 40 0])
                   :stroke (hsl [5 70 70 1])
                   :stroke-width 1
                   :fill (hsl [2 70 70 .3])}
            ]

           #_[:text {:x 0
                   :y 0
                   :style {:font-size "1rem"}}
              "101 kN/m2"]
           ;; bar
           (let [y 0
                 d 3.5]
             [:g {:transform (m7/tranfrom [[:translate [0 0]]
                                           [:rotate 0]])}

              [:path {:d (path [0 d
                                :l 0 (ve 120)  400 80 0 40 -8 0 0 (ve 32) -385 -80 0 120])
                      :stroke (hsl [0 70 70 1])
                      :stroke-width 1
                      :fill (m7/url (name :lg1))
                      }
               ]
              ;; :fill (hsl [2 70 70 .3])
              #_[:path {:d (path [0 d
                                :l 0 (ve 170)  8 0  0 120])
                      :stroke (hsl [0 70 70 1])
                      :stroke-width 1
                      :fill (m7/url (name :lg1))
                      }
               ]
              #_(map (fn [d]
                     )
                   [d (ve d)])])

           #_(let [y 0
                 d 9
                 ]

             [:g {:transform (m7/tranfrom [[:rotate 10]])}
              (map
               (fn [s]
                 [:g  {:transform (m7/tranfrom
                                   [
                                    [:translate
                                     [120 (ve 100)]]
                                    [:scale [s 1]]
                                    ])}
                  (map (fn [d]
                         [:path {:d (path [0 d
                                           :l 120 0 0 d])
                                 :stroke (hsl [0 70 70 1])
                                 :stroke-width 1
                                 :fill :none}
                          ])
                       [d (ve d)])



                  ])
               [1 -1])])



           ]
          )]])))


(def fontf
  (comp
   m7/coma
   (partial map
            (comp
             m7/space
             (juxt (comp
                    m7/wrap-sami-colon
                    name
                    first) second)))
   vec))

(def var-vf
  {:wght 431.85

   :wdth 100
   :opsz 14
   :GRAD 88
   :XOPQ 88
   :XTCH 1000
   :YTAS 750
   :YTRA 1000
   :YTUC 750
   :YTLC 500
   :YTSE 18
   :YTCH 1000
   :PWDT 402
   :PWGT 88
   :YOPQ 50
   :XTRA 402
   :YTDE 250})


(defn fv1 [n]
  {:font-family "'Amstelvar VF'"
   :font-variation-settings
   (fontf
    (update
     var-vf
     (get [:wght :wdth :opsz :GRAD]
          0)
     (fn [k] (+ k (/ 880 n)))))})

(defn fconf [elms]
  (let [f (fn [n d] (/ n d))]
    (map
     (fn [[u [mn mx]] [n d]]
       [u (fn [k]
            (+ k (* k (f n d))))])
     [[:wght  [100 900]]
      [:wdth  [35 10]]
      [:opsz  [10 72]]
      [:GRAD  [88 150]]]
     elms)))

(defn fv [elms]
  {:font-family "'Amstelvar VF'"
   :font-variation-settings
   (fontf
    (reduce
     (fn [vf [w f]]
       (update vf w f))
     var-vf
     (fconf elms)))})





(defn banner-brand []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40

          cor [[5 2] [10 9] [15 19] [20 34] [25 58] [30 95] [32 125]]
          points (into ["x"] (range 0 10))
          ts (into [
                    [:div {:style {:font-size "1rem"}}
                     [m7/m '[= y [:p x
                                  2]]]]]
                   (map (fn [x] (* (- x 3) (- x 3))) (range 0 10)))
          vms (into ["v (m/s)"] [ 0 0])
          vb (fn [z]
               (nth [[0 -200  400 400]
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]] z))
          temmat [["4" "3" "abc@kmail.com" "017555224" "2" "abc abc" "chrismas 2" "due at 13"]
                  ["2" "5" "daf@kmail.com" "01855224" "5" "def def" "cake" "none"]]
          tem (nth temmat
                   1)
          viewbox (vb 0)
          viewbox2 (vb 0)
          st (fn [aa row]
               {:style (m7/css
                        [[row 1 (+ 3 (* aa 1)) 1  :center :center  1.5 :rem :column]
                         [(* 3 .2) 70 (+ 50 (* 5 5))  .7] []
                         {:gap ".1rem"
                          :z-index 10}])})]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}


       [:div {:style
              (m7/css
               [[4 3 10 7
                 :center :center  4 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}

        [:div ""]
        [:div  {:style {:font-size "2rem"}} "" ]
        [m7/m '[+ [1 3] [1 3]]]
        ]


       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           (flames)

           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 4
                      :fill (hsl [0 70 70 1])
                      }]]

           [:linearGradient {:x1 .5
                             :y1 1
                             :x2 .5
                             :y2 0
                             :id (name :lg1)
                             :gradientTransform
                             (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [2 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [2.3 70 70 .7])}]
            [:stop  {:offset .77
                     :stop-color (hsl [3 70 70 .4])}
             [:animate {:attributeName :offset
                        :from 0
                        :to 1
                        :dur (m7/not-space [3 "s"])
                        :repeatCount 10}]]
            ]
           ;; :indefinite
           [:marker {:id (name :dot2)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]

           (grid-on 20 20)


           [:path

            {:d (m7/path [0 (* 20 2 2) :l
                          (* 3 2 20) 0
                          0 (ve (* 2 2 20))
                          (ve (* 3 2 20)) 0
                          0 (ve (ve (* 2 2 20)))])
             :stroke-width 2
             :stroke (hsl [0 70 70 1])
             :id :kbrad2
             :fill :none
             }
            ]


           [:path

            {:d (m7/path [0 0 :l
                          (* 2 20) 0
                          0 (ve (* 2 20))
                          (ve (* 2 20)) 0
                          0 (ve (ve (* 2 20)))])
             :stroke-width 2
             :stroke (hsl [0 70 70 1])
             :id :kbrad2
             :fill :none
             :transform (m7/tranfrom
                         [[:translate [0 0]]])
             }
            ]

           [:text {}
            [:textPath {:href :#kbrad2
                        :startOffset (/ (* 3 2 20) 2)
                        :font-size 15}
             3]

            [:textPath {:href :#kbrad2
                        :startOffset (+ (* 2 (* 3 2 20))  (/ (* 2 2 20) 1) (/ (* 2 2 20) 2))
                        :font-size 15}
             2]

            ]

           #_[:g

              [:path

               {:d (m7/path [0 0 :a 110 80 0 false true 240 0])
                :stroke-width .1
                :stroke "pink"
                :id :kbrad
                :fill :none
                }
               ]


            [:text {:fill (hsl [.5 65 65 1])
                    :stroke (hsl [.1 70 60 1])
                    :style (fv [[2 3] [4 3] [0 9] [5 5]])
                    :stroke-width 2.5
                    :font-size 50}

             [:textPath {:startOffset "5%"
                         :href :#kbrad}
              "AFSHEEN'S"]

             ]

            [:text {:x 20
                    :y -20
                    :stroke (hsl [.1 70 60 1])
                    :fill (hsl [.5 65 65 1])
                    :font-size 30}
             "ICECREAM"
             ]


            [:text {:x (+ 20 50)
                    :fill (hsl [.5 60 60 1])

                    :y -0
                    :font-size 13}
             "ORIGINAL"
             ]






            [:path {:d (m7/path [-80 -140
                                 :l 40 0
                                 :a 50 50 0 false false
                                 (* 2 js/Math.PI 50)
                                 0
                                 :l 40 0
                                 :a 50 50 0 false false
                                 (* 2 js/Math.PI 50)
                                 0
                                 ]


                                )
                    :stroke :pink
                    :fill :none
                    }
             ]]


           #_(map
            (fn [a]
              [:text {:x (* 150 (js/Math.sin (+ a (* 3 (/  js/Math.PI 2)) )))
                      :y (ve (* 150 (js/Math.cos (+ a  (* 3 (/  js/Math.PI 2)) ))))
                      :font-size 7
                      } (.toFixed (* a (/ 180 js/Math.PI)) 0)])
            (range 0 js/Math.PI (* 10 (/  js/Math.PI 180))  ))


           #_(map
            (fn [a]
              [:path {:d (m7/path [0 0 :l
                                   (* 150 (js/Math.sin (+ a (* 3 (/  js/Math.PI 2)) )))
                                   (ve (* 150 (js/Math.cos (+ a  (* 3 (/  js/Math.PI 2)) ))))])
                      :stroke (hsl [.5 70 70 1])
                      :stroke-width .5

                      } ])
            (range 0 js/Math.PI  .125))



           #_[:path {:d (m7/path [0 0
                                :l 20 0
                                0 150
                                -20 0
                                0 -150
                                ])
                   :fill :pink
                   }
            [:animateTransform
             {:attributeName :transform
              :begin :click
              :dur (sec 5)
              :type :rotate
              :from -90
              :to (+ -360 -90)
              :fill :freeze
              :id :scl2
              }]
              ]






           #_[:circle {:cx 0
                     :cy 0
                     :r 150
                     :stroke-dasharray 0
                     :stroke-width 10
                     :fill (hsl [0 70 70 .75])
                     :stroke (hsl [3 70 70 .75])}

            [:animate {:attributeName :r
                       :from 150
                       :to 0
                       :dur (sec 5)
                       :repeatCount :indefinite}]

            [:animate {:attributeName
                       :stroke-dasharray
                       :from 200
                       :to 300
                       :dur (sec 5)
                       :repeatCount :indefinite}]


            [:animate {:attributeName :cy
                       :from 0
                       :to -60
                       :dur (sec 5)
                       :repeatCount :indefinite}]

            [:animate {:attributeName :cx
                       :from 0
                       :to 160
                       :dur (sec 5)
                       :repeatCount :indefinite}]

            [:animate {:attributeName :stroke-width
                       :from 10
                       :to 60
                       :dur (sec 5)
                       :repeatCount :indefinite}]

            [:animate {:attributeName :fill
                       :from (hsl [0 70 70 .75])
                       :to (hsl [4 70 70 1])
                       :dur (sec 5)
                       :repeatCount :indefinite}]



            ]













           #_[:circle
            {:cx 0
             :cy 0
             :r 150
             :fill :none
             :stroke (hsl [0 70 70 1])
             :stroke-dasharray (- (* 2 150 js/Math.PI ) 1)
             }
            [:animate
             {:attributeName :stroke-dashoffset
              :begin :scl2.begin
              :dur (sec 5)
              :type :rotate
              :from 0
              :to (* 2 150 js/Math.PI )
              :fill :freeze

              }]
            ]

















           ]
          )]])))







(defn home-pressure []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40
          vb (fn [z]
               (nth [[100 -200  400 400]
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]] z))
          viewbox (vb 0)
          viewbox2 (vb 0)
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}



       #_(map
        (fn [n d]
          [:div {:style (m7/css
                         [[2 1 (+ 2 (* n 2)) 2  :center :center  2 :rem :column]
                          [(* n .2) 70 (+ 50 (* 1 n))  .7] [] {:gap ".1rem"
                                                               :z-index 4}])}

           d])
        (range 0 24)
        (into ["x"] [0 2])

        )


       #_(map
        (fn [n d]
          [:div {:style (m7/css
                         [[3 1 (+ 2 (* n 2)) 2  :center :center  2 :rem :column]
                          [(* n .2) 70 (+ 50 (* 1 n))  .7] [] {:gap ".1rem"
                                                               :z-index 4}])}

           d])
        (range 0 24)
        (into [
               [:div {:style {:font-size "1.5rem"}}
                [m7/m '[= [:m f x]   [- [:p  x 2] 1]]]]]
              (map (fn [x]
                     (-  (* x x) 1))

                   [0 2]))

        )


       #_[:div {:style
                (m7/css
                 [[4 8 10 12
                   :center :center  3 :rem :column]
                  [3.5 70 (+ 50 (* 5 5))  .7] []
                  {:gap ".1rem"
                   :z-index 10}])}


        [m7/m '[= u [:m 40 Km [:p h -1]]]]

        [m7/m '[= v [:m 0 Km [:p h -1]]]]


        [m7/m '[= [:m ∇ v] [- v u]]]


        [m7/m '[= [:m ∇ v] [- [:m 0 Km [:p h -1]]  [:m 40 Km [:p h -1]]]]]


        [m7/m '[= [:m ∇ v] [-  [ [:m 100 m] [:m (* 3 3) s]]]]]

        [m7/m '[= [:m ∇ v] [-  [:m  [100 9] m [:p s -1]]]]]

        #_[m7/m '[= [:m ∇ v] [:m [- [100 9]] [m s]]]]


        #_[m7/m '[= a [[-  [:m 40 Km [:p h -1]]] [:m 10 s]] ]]


        #_[m7/m '[= [:m ∇ v] [:m [:b [- 10 50]] km [:p h -1]]]]

        #_[m7/m '[= a [[:m ∇ v] t] [:m [40 72] [m [:p s 2]]] ]]

        #_[m7/m '[= a  [[- v u] t]]]


        #_[m7/m '[= a [[- [:m 10 Km [:p h -1]] [:m 50 Km [:p h -1]]] t]]]





        ;; [m7/m '[= g [:m 9.8 [:m m [:p s -2]]]]]
        ;; [m7/m '[= [:m ρ A h]
        ;;         m]]


        ;; [m7/m '[= W mg [:m 9.8 m N]]]

        ;; [m7/m '[= P [mg A] ]]

        ;; [m7/m '[= P [[:m ρ A h g] A] ]]

        ;; [m7/m '[= P [:m ρ h g]]]


        ;; [m7/m '[= P [:m 101.3 k Pa] [:m 101.3 k N [:p m -2]]]]

        #_[m7/m '[=  P [F A] [N [:p m 2]] [:m N [:p m -2]] Pa]]



        #_[m7/m '[= Density ρ [m V] [ Kg [:p m 3]] [:m Kg [:p m -3]]]]



        #_[m7/m '[= [1 [:p x 3] ] [:p x -3] ]]


        ;; [m7/m '[= ρV [:m V [m V]] ]]


        ;; [m7/m '[= m ρV ]]

        ;; [m7/m '[= W mg [:m ρ V g] ]]

        ;; [m7/m '[= P [W A] [[:m ρ A h g] A]  [:m ρ h g]  ]]

        #_[:div [m7/m '[=  9 [* 3 3] ]]]

        #_[:div [m7/m '[=  12 [* 3 2 2] ]]]

        #_[:div [m7/m '[=  18 [* 3 3 2] ]]]


        #_[:div "Things don't always go how you want them to"]

          #_[:div "This project ended up a lot massier I thought it whould be"]
          #_[:div "This is how I like the things to be"]
          #_[:div "to begin with"]



          ]
       #_(map
        (fn [j k]
          (map
           (fn [i]
             [:div {:style
                    (m7/css
                     [[(+ 4 j) 1 (+ 2 i) 1
                       :center :center  2 :rem :column]
                      [3.5 70 (+ 50 (* 5 5))  .7] []
                      {:gap ".1rem"
                       :z-index 10}])}
              (* i k)]
             )
           (range 1 5)))
        (range 0 3)
        [9 12 18])





       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [:filter#flames
            {:height "300%",
             :width "100%",
             :y "-100%",
             :x "0%",
             :filterunits "objectBoundingBox"}
            [:feTurbulence
             {:stitchtiles "stitch",
              :result "noise",
              :numoctaves "2",
              :basefrequency "0.2",
              :type "fractalNoise"}]
            [:feOffset
             {:result "off1", :dy "0"}
             [:animate
              {:repeatcount "indefinite",
               :dur "6s",
               :to "-300",
               :from "0",
               :attributename "dy",
               :attributetype "XML"}]]
            [:feOffset
             {:result "off2", :dy "60", :in "noise"}
             [:animate
              {:repeatcount "indefinite",
               :dur "6s",
               :to "0",
               :from "300",
               :attributename "dy",
               :attributetype "XML"}]
             ]
            [:feMerge
             {:result "scrolling-noise"}
             [:feMergeNode {:in "off1"}]
             [:feMergeNode {:in "off2"}]]
            [:feComponentTransfer
             {:result "brighter-noise"}
             [:feFuncA {:exponent "0.5", :amplitude "1", :type "gamma"}]]
            [:feComposite
             {:result "gradient-noise",
              :operator "in",
              :in2 "brighter-noise",
              :in "SourceGraphic"}]
            [:feComponentTransfer
             {:result "threshhold"}
             [:feFuncR {:tablevalues "0 1", :type "discrete"}]
             [:feFuncG {:tablevalues "0 1", :type "discrete"}]
             [:feFuncB {:tablevalues "0 1", :type "discrete"}]
             [:feFuncA {:tablevalues "0 1", :type "discrete"}]]
            [:feFlood {:result "yellow", :flood-color "#ff9"}]
            [:feComposite
             {:result "yellow-threshhold",
              :operator "in",
              :in "yellow",
              :in2 "threshhold"}]
            [:feFlood {:result "red", :flood-color "#f33"}]
            [:feComponentTransfer
             {:result "exponent-gradient", :in "SourceGraphic"}
             [:feFuncA {:exponent "3", :type "gamma"}]]
            [:feComposite
             {:result "red-gradient",
              :operator "in",
              :in2 "exponent-gradient",
              :in "red"}]
            [:feComposite
             {:result "red-gradient-threshhold",
              :operator "in",
              :in "red-gradient",
              :in2 "threshhold"}]
            [:feMerge
             [:feMergeNode {:in "yellow-threshhold"}]
             [:feMergeNode {:in "red-gradient-threshhold"}]]]

           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 4
                      :fill (hsl [0 70 70 1])
                      }]]

           [:radialGradient {:x1 .5
                             :y1 1
                             :x2 .5
                             :y2 0
                             :id (name :lg1)
                             :gradientTransform (m7/tranfrom [[:rotate 10]])}
            [:stop  {:offset 0
                     :stop-color (hsl [1 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [2 70 70 .7])}]
            [:stop  {:offset .77
                     :stop-color (hsl [3 70 70 .4])}
             [:animate {:attributeName :offset
                        :from .88
                        :to 1
                        :dur (m7/not-space [3 "s"])
                        :repeatCount :indefinite}]]
            ]


           [:radialGradient {
                             :id (name :lg2)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [1 40 40 1])}]
            [:stop  {:offset .55
                     :stop-color (hsl [.3 60 60 1])}]

            [:stop  {:offset .77
                     :stop-color (hsl [1.3 70 70 .3])}
             ]

            [:stop  {:offset .97
                     :stop-color (hsl [1 90 80 .2])}
             ]
            [:animate {:attributeName :offset
                       :from .33
                       :to 1
                       :dur (m7/not-space [3 "s"])
                       :repeatCount :indefinite}]
            ]
           ;; :indefinite
           [:marker {:id (name :dot2)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]

           #_(grid-on 1 1)



           ;;:filter (m7/url "flames")
           ;; Height






           #_{:transform (m7/tranfrom [[:translate [200 0]]
                                       [:scale 2]])}

           [:g




            [:animateTransform
             {:id :earth
              :attributeName :transform
              :begin :click
              :additive :sum
              :dur (sec 1)
              :type :translate
              :keyTimes (m7/sami-colon [0 0.5 0.8 1])
              :values (m7/sami-colon (map m7/space
                                          [
                                           [0 0] [200 50]
                                           [200 150] [200 520]]))
              :fill :freeze
              }]


            [:animateTransform
             {
              :attributeName :transform
              :begin :earth.end
              :dur (sec 1)
              :additive :sum
              :type :scale
              :keyTimes (m7/sami-colon [0 0.5 0.8 1])
              :values (m7/sami-colon [1 2 6 7])
              :fill :freeze
              }]

            #_[:animateTransform {:id :dec
                                :attributeName :transform

                                :begin :click
                                :dur (sec 4)
                                :type :translate
                                :from 0
                                :to -90
                                :fill :freeze}]


            [:circle {:r 120
                      :cx 0
                      :cy 0
                      :fill (m7/url (name :lg2))}]

            [:circle {:r 50
                      :cx 0
                      :cy 0

                      :fill (m7/url (name :lg1))}]



            [:circle {:r 50
                      :cx 0
                      :cy 0
                      :stroke  (hsl [0 70 70 1])
                      :stroke-width .5
                      :fill :none}]

            [:circle {:r 59
                      :cx 0
                      :cy 0
                      :stroke  (hsl [1 70 70 1])
                      :stroke-width .5
                      :fill :none}]


            [:circle {:r 70
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2 70 70 1])
                      :stroke-width .3
                      :fill :none}]


            [:circle {:r 80
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2.7 70 70 1])
                      :stroke-width .3
                      :fill :none}]

            [:path {:d (path [50 0
                              :l 9 0
                              ])

                    :stroke (hsl [3 70 70 1])
                    :stroke-width 10
                    :fill :none
                    :transform (m7/tranfrom [[:rotate -90]])
                    }
             ]
            [:path {:d (path [50 0
                              :l 9 0
                              ])

                    :stroke (hsl [3 70 70 1])
                    :stroke-width .1
                    :fill :none
                    :transform (m7/tranfrom [[:rotate -100]])
                    }
             ]

            [:path {:d (path [50 0
                              :l 9 0
                              ])

                    :stroke (hsl [3 70 70 1])
                    :stroke-width 4
                    :fill :none
                    :transform (m7/tranfrom [[:rotate -80]])
                    }
             ]
            ]

           [:g {:transform (m7/tranfrom [[:scale .5]])}
            (map
             (fn [n1]
               (map
                (fn [n]
                  [:path {:d (path [(* n 70) (* n1 50) :l 70 0 0 50 -70 0 0 -50])
                          :stroke (hsl [4 70 70 1])
                          :stroke-width 2

                          :fill (m7/url (name :lg1))}])
                (range 0 5)))
             (range 0 7))


            [:path {:d (path [0 0 :l 70 0])
                    :id :dccr
                    :stroke (hsl [4 70 70 1])
                    :stroke-width 2
                    :fill (m7/url (name :lg1))}]


            [:path {:d (path [0 0 :l 0 50])
                    :id :dccr2
                    :stroke (hsl [4 70 70 1])
                    :stroke-width 2
                    :fill (m7/url (name :lg1))}]








            [:text
             [:textPath {:href :#dccr
                         :font-size "10"
                         :startOffset "50%"
                         }
              "7"]

             [:textPath {:href :#dccr2
                         :font-size "10"
                         :startOffset "50%"
                         }
              "5"]]]


           ]
          )]


       ])))



(defn home-pressure2 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40
          vb (fn [z]
               (nth [[100 -200  400 400]
                     [0 -280  400 400]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]] z))
          viewbox (vb 0)
          viewbox2 (vb 1)
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}


       [:div {:style
                (m7/css
                 [[4 3 15 7
                   :center :center  2 :rem :column]
                  [3.5 70 (+ 50 (* 5 5))  .7] []
                  {:gap ".1rem"
                   :z-index 10}])}

        #_[:div "hydrocarbon + oxygen = water + carbondioxide"]
        #_[:div "carbonmonoxide CO"]
        #_[:div "Nitrus oxide NO"]
        #_[:div "Volume of Pyramid"]

        #_[m7/m '[= V  [* [1 3] base-area height]]]


        #_[m7/m '[= V  [* [1 3] 100 80 160]]]
        #_[:div "Things don't always go how you want them to"]

          #_[:div "This project ended up a lot massier I thought it whould be"]
          #_[:div "This is how I like the things to be"]
          #_[:div "to begin with"]



          ]
       #_(map
        (fn [j k]
          (map
           (fn [i]
             [:div {:style
                    (m7/css
                     [[(+ 4 j) 1 (+ 2 i) 1
                       :center :center  2 :rem :column]
                      [3.5 70 (+ 50 (* 5 5))  .7] []
                      {:gap ".1rem"
                       :z-index 10}])}
              (* i k)]
             )
           (range 1 5)))
        (range 0 3)
        [9 12 18])





       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}



           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 4
                      :fill (hsl [0 70 70 1])
                      }]]

           [:radialGradient {:x1 .5
                             :y1 1
                             :x2 .5
                             :y2 0
                             :id (name :lg1)
                             :gradientTransform (m7/tranfrom [[:rotate 10]])}
            [:stop  {:offset 0
                     :stop-color (hsl [1 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [2 70 70 .7])}]
            [:stop  {:offset .77
                     :stop-color (hsl [3 70 70 .4])}
             [:animate {:attributeName :offset
                        :from .88
                        :to 1
                        :dur (m7/not-space [3 "s"])
                        :repeatCount :indefinite}]]
            ]


           [:radialGradient {
                             :id (name :lg2)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [4.3 40 40 1])}]
            [:stop  {:offset .55
                     :stop-color (hsl [4.3 60 60 1])}]

            [:stop  {:offset .77
                     :stop-color (hsl [4.3 70 70 .3])}
             ]

            [:stop  {:offset .97
                     :stop-color (hsl [4 90 80 .2])}
             ]
            [:animate {:attributeName :offset
                       :from .33
                       :to 1
                       :dur (m7/not-space [3 "s"])
                       :repeatCount :indefinite}]
            ]
           ;; :indefinite
           [:marker {:id (name :dot2)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]

           (grid-on 20 20)

           (alkine-oh 3)



           (line3 161 [-700 0]  "h1")
           (line3 175 [-700 0]  "h2")

           (line3 167.9 [-700 0]  "h2")

           (line3 176.8 [-700 0]  "h2")





           (line3 3.5 [700 0]  "h2")
           (line3 5 [700 0]  "h2")
           (line3 13.6 [700 0]  "h2")
           (line3 19 [700 0]  "h2")
           #_[:path {:d (m7/path [0 -60 :l 0 -180])
                   :stroke-width 2
                   :stroke (hsl [5 70 70 1])}]

           #_[:path {:d (m7/path [-120 -50 :l 0 -150  280 14])
                   :stroke-width 1
                   :stroke (hsl [5 70 70 1])
                   :stroke-dasharray "4"
                   :fill :none}]

           #_[:path {:d (m7/path [-120 -50 :l 140  -140 -20  130 -110 8 ])
                   :stroke-width 1
                   :stroke (hsl [5 70 70 1])

                   :fill (hsl [5 70 70 .9])}]


           #_[:path {:d (m7/path [-120 -50 :l 140  -140 -20  130 -110 8 ])
                   :stroke-width 1
                   :stroke (hsl [5 70 70 1])

                   :fill (hsl [5 70 70 .9])}]


           #_[:path {:d (m7/path [0 -60 :l 20 -130 137 143 ])
                   :stroke-width 1
                   :stroke (hsl [4 70 70 1])

                   :fill (hsl [3.5 70 70 .9])}]


           #_[:path {:d (m7/path [160 -30 :l 0 -194])
                   :stroke-width 2
                   :stroke (hsl [5 70 70 1])}]


           #_[:path {:d (m7/path [0 -60 :l -120 8 150 10 126 -4.5])
                   :stroke-width 2
                   :stroke (hsl [4 70 70 1])
                   :fill (hsl [4 70 70 .8])}]


           [:g



            [:circle {:cx 160
                      :cy 0
                      :r 20
                      :fill (hsl [2 70 70 1]) }]

            [:circle {:cx 180
                      :cy 0
                      :r 20
                      :fill (hsl [2 70 70 1]) }]


            [:text {:x 180
                    :y 0
                    :dy 10
                    :text-anchor :middle
                    :style {:font-size "1.5rem"
                            :fill (hsl [2 90 90 1])}} "O"]


            [:text {:x 160
                    :y 0
                    :dy 10
                    :text-anchor :middle
                    :style {:font-size "1.5rem"
                            :fill (hsl [2 90 90 1])}} "O"]]



           ;;:filter (m7/url "flames")
           ;; Height
           #_[:path {:d (path [-60 80 :l 0 (ve 185)])
                   :marker-end (m7/url (name :dot))
                   :marker-start (m7/url (name :dot))
                   :stroke (hsl [4 70 70 1])
                   :stroke-width 2
                     :fill (m7/url (name :lg1))}]


           #_{:transform (m7/tranfrom [[:translate [200 0]]
                                       [:scale 2]])}


           #_(let [x 100
                 y 80
                 px 20]
             [:g {:transform (m7/tranfrom [[:skewX -0]])}


              [:text {:x px
                      :y -200
                      :style {:font-size "1rem"}
                      :fill (hsl [0 70 70 1])}
               "Apex"]

              [:circle {:cx px
                        :cy -200
                        :r 2
                        :fill (hsl [0 70 70 1])}]

              [:path {:d (m7/path [(/ x 2) (ve (/ y 2)) :l (ve (- (/ x 2) px)) (+ -200 (/ y 2))])
                      :stroke-width 1
                      :id :phr
                      :stroke-dasharray "2 3"
                      :stroke (hsl [3 70 70 1])}
               ]

              [:text
               [:textPath {:href :#phr
                           :startOffset 50
                           :style {:font-size "1rem"}}
                "height"]]


              [:path {:d (str
                          (m7/path [0 0 :l x 0
                                    0 (ve y) (ve x) 0 0 y])

                          " ")
                      :stroke-width 1
                      :id :qdr
                      :transform (m7/tranfrom [
                                               [:skewX -0]])
                      :stroke (hsl [4 70 70 1])
                      :fill (hsl [4 70 70 .3])}]


              [:path {:d (str


                          (m7/path [0 0 :l x (ve y)])

                          " "
                          (m7/path [0 (ve y) :l x  y])
                          " "
                          (m7/path [0 0 :l 30 0 :a 40 40 0 false false -30 -30])
                          " "
                          (m7/path [0 0 :l x (ve y)
                                    -30 0 :a 40 40 0 false false 30 30]))
                      :stroke-width 1
                      :id :qdr
                      :stroke-dasharray "2 3"
                      :transform (m7/tranfrom [
                                               [:skewX -0]])
                      :stroke (hsl [4 70 70 1])
                      :fill :none}]





              [:path {:d (str
                          (m7/path [0 0 :l px -200
                                    ])

                          (m7/path [0 -80 :l px -120
                                    ])

                          (m7/path [x 0 :l (+ (ve x) px) -200
                                    ])

                          (m7/path [x (ve y) :l (+ (ve x) px) (-  y 200)
                                    ])

                          )
                      :stroke-width 1
                      :id :qdr
                      :transform (m7/tranfrom [
                                               [:skewX -0]])
                      :stroke (hsl [2 70 70 1])
                      :fill :none}]])

           [:text {
                   }
            [:textPath {:href  :#qdr
                        :method :stretch}
             ""]]

           #_[:g




            [:animateTransform
             {:id :earth
              :attributeName :transform
              :begin :click
              :additive :sum
              :dur (sec 1)
              :type :translate
              :keyTimes (m7/sami-colon [0 0.5 0.8 1])
              :values (m7/sami-colon (map m7/space
                                          [
                                           [0 0] [200 50]
                                           [200 150] [200 520]]))
              :fill :freeze
              }]

            [:animateTransform
             {
              :attributeName :transform
              :begin :earth.end
              :dur (sec 1)
              :additive :sum
              :type :scale
              :keyTimes (m7/sami-colon [0 0.5 0.8 1])
              :values (m7/sami-colon [1 2 6 7])
              :fill :freeze
              }]

            #_[:animateTransform {:id :dec
                                :attributeName :transform

                                :begin :click
                                :dur (sec 4)
                                :type :translate
                                :from 0
                                :to -90
                                :fill :freeze}]


            [:circle {:r 120
                      :cx 0
                      :cy 0
                      :fill (m7/url (name :lg2))}]

            [:circle {:r 50
                      :cx 0
                      :cy 0

                      :fill (m7/url (name :lg1))}]



            [:circle {:r 50
                      :cx 0
                      :cy 0
                      :stroke  (hsl [0 70 70 1])
                      :stroke-width .5
                      :fill :none}]

            [:circle {:r 59
                      :cx 0
                      :cy 0
                      :stroke  (hsl [1 70 70 1])
                      :stroke-width .5
                      :fill :none}]


            [:circle {:r 70
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2 70 70 1])
                      :stroke-width .3
                      :fill :none}]


            [:circle {:r 80
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2.7 70 70 1])
                      :stroke-width .3
                      :fill :none}]

            [:path {:d (path [50 0
                              :l 9 0
                              ])

                    :stroke (hsl [3 70 70 1])
                    :stroke-width 10
                    :fill :none
                    :transform (m7/tranfrom [[:rotate -90]])
                    }
             ]
            [:path {:d (path [50 0
                              :l 9 0
                              ])

                    :stroke (hsl [3 70 70 1])
                    :stroke-width .1
                    :fill :none
                    :transform (m7/tranfrom [[:rotate -100]])
                    }
             ]

            [:path {:d (path [50 0
                              :l 9 0
                              ])

                    :stroke (hsl [3 70 70 1])
                    :stroke-width 4
                    :fill :none
                    :transform (m7/tranfrom [[:rotate -80]])
                    }
             ]
            ]






           ]
          )]


       ])))



(defn airplane []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40
          vb (fn [z]
               (nth [[100 -450  500 500]
                     [200 -300  600 600]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]] z))
          viewbox (vb 1)
          viewbox2 (vb 1)
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}


       [:div {:style
              (m7/css
               [[2 3 6 11
                 :center :center  3 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}


        [:div ""]
        #_[m7/m '[= v [:m [[-  [- 400] 0] 3]  [m s]]]]


        #_[m7/m '[= F [[:m ∇ m v] t]
                  [[- [:m m v] [:m m u]]
                   t]
                  ]]


        #_[m7/m '[= Ft [:m ∇ [:k m p] [:k v p]] [- [:m [:k m p] [:k v p]] [:m [:k m p] [:k u p]]]]]


        #_[m7/m '[= [- Ft] [:m ∇ [:k m w] [:k v w]] [- [:m [:k m w] [:k v w]] [:m [:k m w] [:k u w]]]]]


        #_[m7/m '[= [- [:m [:k m p] [:k v p]] [:m [:k m p] [:k u p]]]
                [- [:b [- [:m [:k m w] [:k v w]] [:m [:k m w] [:k u w]]]]]]]

        #_[m7/m '[= [- Ft] [:m ∇ m v] [- [:m m v] [:m m u]]]]


        [:div {:style {:font-size "1rem"}} "Conservation of momentum where"]



        #_[m7/m '[= [+ [:m [:k m p] [:k u p] ] [:m [:k m w] [:k u w] ] ]
                [+ [:m [:k m p] [:k v p] ]
                 [:m [:k m w] [:k v w] ] ]]]


        #_[m7/m '[= [:m .494  [m s] ]
                [:k v p]]]





        #_[m7/m '[= P [W A] [ [:m ρ A h g] A] ]]

        #_[m7/m '[=  P [F A] [N [:p m 2]] [:m N [:p m -2]] Pa]]



        #_[m7/m '[= Density ρ [m V] ]]



        #_[m7/m '[= [1 [:p x 3] ] [:p x -3] ]]


        ;; [m7/m '[= ρV [:m V [m V]] ]]


        ;; [m7/m '[= m ρV ]]

        ;; [m7/m '[= W mg [:m ρ V g] ]]

        ;; [m7/m '[= P [W A] [[:m ρ A h g] A]  [:m ρ h g]  ]]


        ]




       #_[:div {:style
              (m7/css
               [[9 3 5 7
                 :center :center  4 :rem :column]
                [1.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}



        [m7/m '[= [:k u p] [:m 5.2  [m s]]]]

        [m7/m '[= [:k m p] [:m 170 Kg]]]

        ]


       #_[:div {:style
              (m7/css
               [[9 3 15 4
                 :center :center  4 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}



        [m7/m '[= [:k u w] [:m 0  [m s]]]]

        [m7/m '[= [:k m w] [:m 160 Kg]]]

        ]

       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]

                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}
           [:marker {:id (name :abcdblablabla2)
                     :refY 0
                     :refX 0
                     :orient :auto
                     :style {:overflow :visible}}
            [:path {:d (m7/path [0 0 :L 5 -5
                                 :L -12.5 0
                                 :L 3 5 :L 0 0])
                    :style {:fill-rule :evenodd
                            :stroke (m7/hsl [1 70 70 1])
                            :stroke-width 1
                            :stroke-opacity 1
                            :fill (m7/hsl [3 70 70 1])
                            :fill-opacity 1}
                    :transform (m7/tranfrom [[:scale .4]
                                             [:rotate 180]
                                             [:translate [12.5 0]]])
                    }]]
           (flames)
           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 4
                      :fill (hsl [0 70 70 1])
                      }]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]


           [:path {:d (m7/path [0 80 :l 400 0])
                   :id :driving2
                   :stroke-width 2
                   :marker-end (m7/url (name :dot2))
                   :stroke (hsl [0 70 70 1])
                   :fill (hsl [0 70 70 1])}]

           [:text
            [:textPath {:href :#driving2
                        :startOffset 50
                        :style {:font-size "1.2rem"}}
             "F1=5N"]
            ]


           [:path {:d (m7/path [500 -40 :l -200 0])
                   :stroke-width 2
                   :marker-end (m7/url (name :abcdblablabla2))
                   :stroke (hsl [0 70 70 1])
                   :fill (hsl [0 70 70 1])}]


           [:path {:d (m7/path [400 80 :l -200 0])
                   :id :friction2
                   :stroke-width 4
                   :marker-end (m7/url (name :abcdblablabla2))
                   :stroke (hsl [5 70 70 1])
                   :fill (hsl [0 70 70 1])}]

           [:text [:textPath {:href :#friction2
                              :startOffset 20
                              :style {:font-size "1.2rem"}}
                   "F2=-2N"]]


           [:path {:d (m7/path [0 80 :l 200 0])
                   :id :resf2
                   :stroke-width 8
                   :marker-end (m7/url (name :abcdblablabla2))
                   :stroke (hsl [2 70 70 1])
                   :fill (hsl [0 70 70 1])}]
           [:text {:dy 70}
            [:textPath {:href :#resf2


                        :style {:font-size "2rem"}}
             "F1 + F2 = 3N"]
            ]




           [:path {:d (m7/path [540 0 :l 40 0 0 (ve 80) (ve 40) 0 0 (ve (ve 80))])

                   :stroke-width 1
                   :stroke (hsl [0 70 70 1])
                   :fill (hsl [3 70 70 1])}
            [:animateTransform
             {:attributeName :transform
              :begin :plane2.end
              :dur (sec 30)
              :type :translate
              :fill :freeze
              :from (m7/space [0 0])
              :to (m7/space [300 0])

              }]]



           (grid-on 20 20)

           (let [y 40]
             [:g


              #_[:g
               (line3 0 [0 (ve y)] "e")
               (line3 0 [0  y] "e")


                 (comment
                   )

               (line3 -130 [(- y 17)  0] "l")
               (line3 -130 [(ve (- y 17)) 0] "l")]

              (comment
                {
                 :values (m7/sami-colon
                          (map
                           m7/space
                           (map (fn [i]
                                  [(* i 120) 0])
                                (range 0 4))))
                 :keyTimes (m7/sami-colon
                            [0 .25 .45 .70 .8 .87 .92 .95 .97 .98 .985  1])})

              [:animateTransform
               {:id :plane2
                :attributeName :transform
                :begin :click
                :dur (sec 3)
                :type :translate
                :fill :freeze
                :values (m7/sami-colon
                         (map
                          m7/space
                          [[0 0]  [470 0]]))
                :keyTimes (m7/sami-colon
                           [0  1])



                }]
              [:animateTransform
               {:id :plane4
                :attributeName :transform
                :begin :plane2.end
                :dur (sec 12)
                :type :translate
                :fill :freeze
                :values (m7/sami-colon
                         (map
                          m7/space
                          [[570 0]  [1470 0]]))
                :keyTimes (m7/sami-colon
                           [0  1])



                }]

              [:g {:transform (m7/tranfrom [[:rotate 0]
                                            [:scale [.3 .3]]])}

               [:path {:d (m7/path [0 0 :l 0 (ve y) 240 0 0 (* 2 y) -290 0
                                    0 (* 2 (ve y))
                                    50 0])
                       :stroke-width 1
                       :stroke (hsl [0 70 70 1])
                       :fill (hsl [5 70 70 .5])}]


               [:g {:transform (m7/tranfrom [[:translate [-100 0]]])}
                [:path {:d (m7/path [0 0 :l 50 0 0 -40 :c -60 15 -80 20   -100 40
                                     ])
                        :stroke-width 1
                        :stroke (hsl [0 70 70 1])
                        :fill (hsl [1 70 70 1])}]

                [:path {:d (m7/path [0 0 :l 50 0 0 -40 :c -60 15 -80 20   -100 40
                                     ])
                        :transform (m7/tranfrom [[:scale [1 -1]]])
                        :stroke-width 1
                        :stroke (hsl [0 70 70 1])
                        :fill (hsl [1 70 70 1])
                        }]

                ]

               #_{:transform (m7/tranfrom [[:translate [-140 0]]
                                           [:skewX -140]
                                           [:scale [1.3 .4]]
                                           ])}

               [:g {:transform (m7/tranfrom [[:translate [-140 0]]

                                             ])}
                [:path {:d (m7/path [0 0 :l  20 0 0 -200 -40 0 0 200 20 0
                                     ])
                        :transform (m7/tranfrom [
                                                 [:skewX -140]
                                                 [:scale [1 0.35]]
                                                 ])
                        :stroke-width 1
                        :stroke (hsl [0 70 70 1])
                        :fill (hsl [3 70 70 1])}]

                [:path {:d (m7/path [0 0 :l  20 0 0 -200 -40 0 0 200 20 0])
                        :transform (m7/tranfrom [[:skewX 140]
                                                 [:scale [1 -0.35]]])
                        :stroke-width 1
                        :stroke (hsl [0 70 70 1])
                        :fill (hsl [3 70 70 1])}]




                ]


               [:g
                [:g {:transform (m7/tranfrom [[:translate [120 (ve y)]]
                                              [:skewX -140]])}
                 [:path {:d (m7/path [0 0 :l  20 0 0 -200 -40 0 0 100 -60 100
                                      ])
                         :stroke-width 1
                         :stroke (hsl [3 70 70 1])
                         :fill (hsl [3 70 70 1])}]]
                [:g {:transform (m7/tranfrom [[:translate [120 y]]
                                              [:scale [1 -1]]
                                              [:skewX -140]
                                              ])}
                 [:path {:d (m7/path [0 0 :l  20 0 0 -200 -40 0 0 100 -60 100
                                      ])
                         :stroke-width 1
                         :stroke (hsl [3 70 70 1])
                         :fill (hsl [3 70 70 1])}]]
                ]



               [:g {:transform (m7/tranfrom [[:translate [240 0]]])}
                [:path {:d (m7/path [0 0 :l 0 (ve y)
                                     :c 30 -5 100 0  120 y])
                        :stroke-width 1
                        :stroke (hsl [0 70 70 1])
                        :fill (hsl [1 70 70 1])}]
                [:path {:d (m7/path [0 0 :l 0 (ve y)
                                     :c 30 -5 100 0  120 y])
                        :stroke-width 1
                        :stroke (hsl [0 70 70 1])
                        :transform (m7/tranfrom [[:scale [1 -1]]])
                        :fill (hsl [1 70 70 1])}]]


               ]])




           ]
          )]


       ])))



(defn amd []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40
          vb (fn [z]
               (nth [[100 -450  500 500]
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]] z))
          viewbox (vb 0)
          viewbox2 (vb 0)
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}

       #_[:div {:style (m7/css
                        [[2 10 10 20 :center :center 2 :rem :column]

                         [1 70 90 .1] [] {:gap "1rem"
                                          :z-index 4}])}
          [:div
           "Lets, add point A,C"]
          [:div
           "Now, we got 2 triange's ABC and ADC"]

          [:div
           "angles on triange's ABC"]
          [:div
           "angles on triange's ADC"]
          [m7/m '[= [+ BAC ABC ACB] 180]]
          [m7/m '[= [+ DAC ADC DCB] 180]]

          [:div {:style {:background-color (hsl [0 70 70 1])
                         :height "1rem"
                         :width "50%"}}
           ""]

          [m7/m '[= [+ A B D C] 360]]


          ]
       [:div {:style
              (m7/css
               [[4 3 10 7
                 :center :center  4 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}

        #_[:div ""]
        #_[:div  {:style {:font-size "2rem"}} "g is an acceleration duo to gravitational pull" ]




        #_[m7/m '[= g [:m 9.8 [:m m [:p s -2]]]]]


        #_[m7/m '[= [:m ρ A h]
                  m]]

        #_[m7/m '[= F [[:m ∇ [:b [:m  m  v]] ]  t]]]


        #_[m7/m '[= F ma [[:m ∇ m   v] t]
                [[:m m  [:b [- v u]]] t]
                ]]





        #_[m7/m '[= P [W A] [ [:m ρ A h g] A] ]]

        #_[m7/m '[=  P [F A] [N [:p m 2]] [:m N [:p m -2]] Pa]]



        #_[m7/m '[= ρ [m V] ]]








        #_[m7/m '[= [1 [:p x 3] ] [:p x -3] ]]


        ;; [m7/m '[= ρV [:m V [m V]] ]]


        ;; [m7/m '[= m ρV ]]

        [m7/m '[=  [:m [:k ρ w] V ] [:m  ρ [:k V 1] ] ]]


        [m7/m '[=  [V V1] [  ρ [:k ρ w]] ]]




        ;; [m7/m '[= P [W A] [[:m ρ A h g] A]  [:m ρ h g]  ]]


        ]




       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]

                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           (flames)
           [:marker {:id (name :dot3)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]
           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 4
                      :fill (hsl [0 70 70 1])
                      }]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]

           [:g {:transform (m7/tranfrom [[:skewX 0]])}





            [:path {:d (m7/path [0 0 :l 200 0 0 -150 -200 0 0 150])
                    :stroke-dasharray "200 150 120 80"
                    :stroke-dashoffset "400"
                    :stroke-width 3
                    :stroke (hsl [0 70 70 1])
                    :fill (hsl [3.5 70 70 1])}]





            [:g
             [:path {:d (m7/path [120 -150 :l 50 0 0 -50 -50 0 0 50])
                     :stroke-width 3

                     :stroke (hsl [0.5 70 80 1])
                     :fill (hsl [0.5 70 80 1])}
              [:animateTransform
               {:attributeName :transform
                :begin (sec 0)
                :dur (sec 1)
                :type :translate
                :from (m7/space [0 0])
                :to (m7/space [0 20])
                :fill :freeze
                :id :ark1
                }]
              ]

             [:path {:d (m7/path [150 -150 :l 0 -70 ])
                     :stroke-width 3
                     :marker-end (m7/url (name :dot3))

                     :stroke (hsl [0.5 70 80 1])
                     :fill (hsl [0.5 70 80 1])}
              [:animateTransform
               {:attributeName :transform
                :begin (sec 0)
                :dur (sec 1)
                :type :translate
                :from (m7/space [0 0])
                :to (m7/space [0 20])
                :fill :freeze
                :id :rfff1
                }]
              ]


             [:path {:d (m7/path [150 -150 :l 0 70 ])
                     :stroke-width 3
                     :marker-end (m7/url (name :dot3))

                     :stroke (hsl [0.5 70 80 1])
                     :fill (hsl [0.5 70 80 1])}
              [:animateTransform
               {:attributeName :transform
                :begin (sec 0)
                :dur (sec 1)
                :type :translate
                :from (m7/space [0 0])
                :to (m7/space [0 20])
                :fill :freeze
                :id :rfff1
                }]
              ]

             ]



            [:path {:d (m7/path [0 0 :l 300 0 0 -50])
                    :stroke-width 3
                    :stroke (hsl [0 70 70 1])
                    :fill :none
                    }]


            #_[:path {:d (m7/path [200 0 :l 100 0 0 -20  -100 0 0 20])
                      :stroke-width 3
                      :stroke (hsl [0 70 70 1])
                      :fill (hsl [3.7 70 70 1])
                      }
               [:animateTransform
                {:attributeName :transform
                 :begin (sec 0)
                 :dur (sec 1)
                 :type :scale
                 :from (m7/space  [1 .05])
                 :to (m7/space  [1 1.8])
                 :fill :freeze
                 :id :rff1
                 }]]


            [:path {:d (m7/path [200 0 :l 50 0 0 -15  -50 0 0 15])
                    :stroke-width 3
                    :stroke (hsl [0 70 70 1])
                    :fill (hsl [3.7 70 70 1])
                    }
             [:animateTransform
              {:attributeName :transform
               :begin (sec 0)
               :dur (sec 1)
               :type :scale
               :from (m7/space  [1 .05])
               :to (m7/space  [1 1.8])
               :fill :freeze
               :id :rff1
               }]]

            [:path {:d (m7/path [300 -15 :l 50 0])
                    :marker-end (m7/url (name :dot2))
                    :stroke-width 2
                    :stroke (hsl [0.5 90 90 1])
                    :fill :none}]


            [:path {:d (m7/path [300 -35 :l 50 0])
                    :marker-end (m7/url (name :dot2))
                    :stroke-width 2
                    :stroke (hsl [0.5 70 70 1])
                    :fill :none}]

            [:g {:transform (m7/tranfrom [[:translate [-140 -240]]])}

             [:path {:d (m7/path [0 0 :l 150 0  -75 -200 -75 200 75 0 0 -75 45 0 -90 0 ])
                     :stroke-width 2
                     :stroke (hsl [1.5 70 70 1])
                     :fill (hsl [2.5 70 70 1])}]

             [:text {:x 20
                     :y -20
                     :style {:font-size "2rem"}
                     } "ρ"]


             [:text {:x 90
                     :y -20
                     :style {:font-size "2rem"}
                     } "V"]

             [:text {:x 75
                     :dx -10
                     :y -100
                     :style {:font-size "2rem"
                             }
                     } "m"]


             #_[:foreignObject
              {:x 45
               :y -120
               :width 100
               :height 100
               :style {:font-size "1.5rem"}
               } [m7/m '[:m ∇ [:m  m  v]]] ]


             #_[:text {:x 10
                     :y 0
                     :style {:font-size "2rem"}
                       } "ρ"]

             #_[:text {:x 10
                     :y 0
                     :style {:font-size "2rem"}
                       } "ρ"]

             ]




            #_[:path {:d (m7/path [200  -150 :l  -40 0 :a 40 40 0 false false    40  30])
                      :stroke-width 2
                      :stroke (hsl [0 70 70 1])
                      :fill (hsl [0 70 70 1])}]


            #_[:path {:d (m7/path [200 -150 :l -28 21 28 14])

                    :stroke-width 3
                    :stroke (hsl [0 70 70 1])
                    :fill (hsl [2 70 70 1])}]


            #_[:path {:d (m7/path [0 0 :l   40 0  0 -30 -40 0 0 30])

                    :stroke-width 3
                    :stroke (hsl [0 70 70 1])
                    :fill :none}]


            #_[:path {:d (m7/path [0 0 :l 40 0  0 -30 -40 30])

                    :stroke-width 3
                    :stroke (hsl [0 70 70 1])
                    :fill (hsl [2 70 70 1])}]


            [:path {:d (m7/path [0 0 :l 200 -150])

                    :stroke-width 3
                    :stroke (hsl [.5 70 70 1])
                    :fill :none}]


            ]




           #_(grid-on 20 20)






           ]
          )]


       ])))



(defn map-asia []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40
          vb (fn [z]
               (nth [[100 -450  500 500]
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]] z))
          viewbox (vb 0)
          viewbox2 (vb 0)
          ]



      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-size :cover
                      :background-position :center
                      :background-color (hsl [1 70 70 1])
                      :background-image (str "url(" (.getItem js/localStorage "file-input-img" )   ")")
                      :gap ".1rem"})}

       #_[file/file-input-background2]







       [:div {:style (m7/css
                      [[1 12 1 20 :center :center 3 :rem]

                       [1 70 90 .3] [] {:gap "1rem"
                                       }])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           (flames)
           [:marker {:id (name :dot3)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]
           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 4
                      :fill (hsl [0 70 70 1])
                      }]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]





           #_(grid-on 20 20)



           [:image {:height 80
                    :width 80
                    :x 300
                    :y -120
                    :href "https://cdn.britannica.com/s:800x450,c:crop/83/195983-138-66807699/numbers-tiger-populations.jpg"}]


           [:image {:height 100
                    :width 100
                    :x 260
                    :y -300
                    :href "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEkfMw_fOiZE1xITudP7cBetJL521XS8l2fcJXkj-dD8jNUgUbXCjFrPjgLFAeeUf78FU&usqp=CAU"}]


           [:image {:height 80
                    :width 80
                    :x 120
                    :y -220
                    :href "https://i.pinimg.com/originals/01/96/7a/01967a3449d6e3247c2beefa4f5fbc8d.jpg"}]


           [:image {:height 80
                    :width 80
                    :x 320
                    :y 0
                    :href "https://www.insidescience.org/sites/default/files/2020-07/disturbed-monkey_cropped_0.gif"}]


           ]
          )]


       ])))
