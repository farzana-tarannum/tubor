(ns math2.cse
  (:require
   [math2.file :as file]
   [react]
   [math2.bdmap :as bdmap]
   [clojure.string :as str]
   [clojure.walk :as w]
   [defun.core :refer [defun fun]]
   [math2.solution :as sol]
   [math2.math7 :as m7 :refer
    [grid hsl css space size path ve sec]]))

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
       (nth ["methane" "ethane" "propane" "butane" "pentane" "hexane" "heptane" "octane"
             9 10 11 12 13 14 15 16 17 18 19 20]
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






       [:div {:style
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

           [:filter#flames2
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
            [:feFlood {:result "yellow", :flood-color (hsl [2 70 70 1])}]
            [:feComposite
             {:result "yellow-threshhold",
              :operator "in",
              :in "yellow",
              :in2 "threshhold"}]
            [:feFlood {:result "red", :flood-color (hsl [1 50 70 1])}]
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
                             :id (name :llg1)
                             :gradientTransform (m7/tranfrom [[:rotate 4]])}
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
                      :filter (m7/url "flames2")
                      :fill (m7/url (name :llg1))}]

              [:path {:d pth
                      :transform (m7/tranfrom [[:scale [-1 1]]])
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      :filter (m7/url "flames2")
                      :fill (m7/url (name :llg1))}
               ]])







           #_[:path {:d (path [-18 -50
                               :l 40 0
                               0 -20 -40 0 0 20])

                     :stroke (hsl [0 70 70 1])
                     :stroke-width 1.5
                     :filter (m7/url "flames2")
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
                   :fill (m7/url (name :llg1))
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
                      :filter (m7/url "flames2")
                      :fill (m7/url (name :llg1))}]

              [:path {:d pth
                      :transform (m7/tranfrom [[:scale [-1 1]]])
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      :filter (m7/url "flames2")
                      :fill (m7/url (name :llg1))}
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
                      :filter (m7/url "flames2")
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
                      :stroke  (m7/url (name :llg1))
                      :stroke-linejoin :round
                      :stroke-width 4
                      :fill :none
                      ;; :filter (m7/url "flames")
                      }
               ]


              [:path {:d (path [0 (ve 120)
                                :l  400 80 ])
                      :stroke  (m7/url (name :wlg1))
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
