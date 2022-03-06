(ns math2.math100
  (:require
   [math2.file :as file]
   [react]
   [math2.bdmap :as bdmap]
   [clojure.string :as str]
   [clojure.walk :as w]
   [defun.core :refer [defun fun]]
   [math2.math7 :as m7 :refer
    [grid hsl css space size path ve sec]]))




(def f
  (fn [m n] (/ m n)))
(def square [1 0 0 1 (ve 1) 0 0 (ve 1)])



(def fontf2
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

(def var-vf2
  {:wght 581
   :opsz 10
   :wdth 100
   :PWDT 402
   :PWGT 124
   :GRAD 105
   :XOPQ 96
   :XTRA 402
   :YOPQ 67
   :YTUC 860
   :XTCH 1000
   :YTAS 794
   :YTRA 1000
   :YTLC 500
   :YTSE 18
   :YTCH 1000
    :YTDE 250})







(defn fix [fr n]
  (js/parseFloat (.toFixed fr n)))

(defn fixint [fr n]
  (js/parseInt (.toFixed fr n)))

(defn airplane2 [t]
  (let [y 40]
    [:g {:transform (m7/tranfrom t)}

                [:path {:d (m7/path [0 0 :l 0 (ve y) 240 0 0 (* 2 y) -290 0
                                     0 (* 2 (ve y))
                                     50 0])
                        :stroke-width 1
                        :stroke (hsl [4 70 70 1])
                        :fill (hsl [4.2 80 70 1])}]


                [:g {:transform (m7/tranfrom [[:translate [-100 0]]])}
                 [:path {:d (m7/path [0 0 :l 50 0 0 -40 :c -60 15 -80 20   -100 40
                                      ])
                         :stroke-width 1
                         :stroke (hsl [0 70 70 1])
                         :fill (hsl [4 70 70 1])}]

                 [:path {:d (m7/path [0 0 :l 50 0 0 -40 :c -60 15 -80 20   -100 40
                                      ])
                         :transform (m7/tranfrom [[:scale [1 -1]]])
                         :stroke-width 1
                         :stroke (hsl [0 70 70 1])
                         :fill (hsl [4 70 70 1])
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
                         :fill (hsl [4 70 70 1])}]

                 [:path {:d (m7/path [0 0 :l  20 0 0 -200 -40 0 0 200 20 0])
                         :transform (m7/tranfrom [[:skewX 140]
                                                  [:scale [1 -0.35]]])
                         :stroke-width 1
                         :stroke (hsl [0 70 70 1])
                         :fill (hsl [4 70 70 1])}]




                 ]


                [:g
                 [:g {:transform (m7/tranfrom [[:translate [120 (ve y)]]
                                               [:skewX -140]])}
                  [:path {:d (m7/path [0 0 :l  20 0 0 -200 -40 0 0 100 -60 100
                                       ])
                          :stroke-width 1
                          :stroke (hsl [3 70 70 1])
                          :fill (hsl [4 70 70 1])}]]
                 [:g {:transform (m7/tranfrom [[:translate [120 y]]
                                               [:scale [1 -1]]
                                               [:skewX -140]
                                               ])}
                  [:path {:d (m7/path [0 0 :l  20 0 0 -200 -40 0 0 100 -60 100
                                       ])
                          :stroke-width 1
                          :stroke (hsl [3 70 70 1])
                          :fill (hsl [4 70 70 1])}]]
                 ]



                [:g {:transform (m7/tranfrom [[:translate [240 0]]])}
                 [:path {:d (m7/path [0 0 :l 0 (ve y)
                                      :c 30 -5 100 0  120 y])
                         :stroke-width 1
                         :stroke (hsl [0 70 70 1])
                         :fill (hsl [4 70 70 1])}]
                 [:path {:d (m7/path [0 0 :l 0 (ve y)
                                      :c 30 -5 100 0  120 y])
                         :stroke-width 1
                         :stroke (hsl [0 70 70 1])
                         :transform (m7/tranfrom [[:scale [1 -1]]])
                         :fill (hsl [4 70 70 1])}]]


                ]))

(comment
  (fix 9.983 2))

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



(defn alkine-oh [n1]
  [:g {:transform (m7/tranfrom [[:scale [.7 .7]]
                                         [:rotate 10]])}



           (let [n n1]
              [:g

               [:foreignObject {:x 20
                                :y -150
                                :height 100
                                :width 450
                                :style {:font-size "1.7rem"}}
                [m7/m [:m [:k 'C n] [:k 'H ['+ ['* 2 n] 1]] 'OH]]
                [:div
                 [m7/m [:m [:k 'C n] [:k 'H (+ 1 (* 2 n))] 'OH]]]

                #_[m7/m ['= ['+ [:m [:k 'C n] [:k 'H  (+ (* 2 n) 2)]] [:m 2[:k 'O 2]] ]
                       ['+ [:m 'C [:k 'O 2]] [:m 2 [:k 'H 2] 'O]]
                       ]]
                ]

               [:text {:x 250
                       :y -220
                       :style {:font-size "2rem"}}
                (nth ["methanol" "ethanol" "propanol" "butanol" "pentanol" "hexanol" "heptane" "octane"]
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
                   (if (= i -1)
                     [:text {:x (* i 75)
                             :y -40
                             :dy 10
                             :dx -28
                             ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                             :style {:font-size "1.5rem"}}
                      "OH"]


                     [:circle {:cx (* i 75)
                               :cy -40
                               :r 12
                               :dy -5
                               :dx -10
                               :fill (hsl [(/ i 5) 60 70 1])
                               ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                               }
                      ])

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


               #_[:circle {:cx (* n 75)
                         :cy 0
                         :r 12
                         :dy -5
                         :dx -10
                         :fill (hsl [5 60 70 1])
                         ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                         }
                  ]

               [:text {:x (* n 75)
                       :y 0
                       :dy 10
                       :dx 0
                       ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                       :style {:font-size "1.5rem"}}
                "OH"]


               [:text {:x -75
                       :y 0
                       :dy 10
                       :dx 0
                       ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                       :style {:font-size "1.5rem"}}
                "H"]


               ])
   ])



(defn alkine-oh-acid [n1]
  [:g {:transform (m7/tranfrom [[:scale [.7 .7]]
                                         [:rotate 10]])}



           (let [n n1]
              [:g

               [:foreignObject {:x 20
                                :y -150
                                :height 100
                                :width 450
                                :style {:font-size "1.7rem"}}

                [:div
                 [m7/m [:m [:k 'C n]  'O 'OH]]]

                #_[m7/m ['= ['+ [:m [:k 'C n] [:k 'H  (+ (* 2 n) 2)]] [:m 2[:k 'O 2]] ]
                       ['+ [:m 'C [:k 'O 2]] [:m 2 [:k 'H 2] 'O]]
                       ]]
                ]

               [:text {:x 250
                       :y -220
                       :style {:font-size "2rem"}}
                (nth ["Formic acid" "Acetic acid" "Propionic Acid" "Butyric Acid" "Capoic acid" "hexanol" "heptane" "octane"]
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

                   #_[:circle {:cx (* i 75)
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
                   (if (= i (- n 1))
                     [:g
                      [:path {:d (path [(+ 5  (* i 75))
                                        0 :l 0 -40  ])
                              :stroke (hsl [4 70 70 1])
                              :stroke-width 2
                              :fill (hsl [5 70 70 .2])}]
                      [:text {:x (* i 75)
                              :y -40
                              :dy 0
                              :dx -10
                              ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                              :style {:font-size "1.5rem"}}
                       "O"]]


                     [:circle {:cx (* i 75)
                               :cy -40
                               :r 12
                               :dy -5
                               :dx -10
                               :fill (hsl [(/ i 5) 60 70 1])
                               ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                               }
                      ])

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


               #_[:circle {:cx (* n 75)
                         :cy 0
                         :r 12
                         :dy -5
                         :dx -10
                         :fill (hsl [5 60 70 1])
                         ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                         }
                  ]

               [:text {:x (* n 75)
                       :y 0
                       :dy 10
                       :dx 0
                       ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                       :style {:font-size "1.5rem"}}
                "OH"]


               [:text {:x -75
                       :y 0
                       :dy 10
                       :dx 0
                       ;;:transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                       :style {:font-size "1.5rem"}}
                "H"]


               ])
            ])

(defn grid-on
  ([] (grid-on 5 5))
  ([X Y]
   (grid-on X Y 40 60 false))
  ([X Y ax-dx ax-dy]
   (grid-on X Y ax-dx ax-dy false))
  ([X Y ax-dx ax-dy frac]
   (let [zoom 0]
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
                  :fill (hsl [0 40 20 1])
                  :y 4.5
                  :font-size 3}
           (if frac
             (.toFixed (* x X) 1)
             (* x X))
           ]])
       (range -20 40))



      (map
       (fn [y]
         [:g
          [:path {:d (path [0 (ve (* 20 y))   :l 1200 0])

                  :stroke (hsl [1 70 70 1])
                  :stroke-width 1
                  :fill :none}
           ]

          [:text {:x -4.5
                  :dy -1
                  :fill (hsl [0 40 20 1])
                  :y (ve (* 20 y))
                  :font-size 3}
           (if frac
             (.toFixed (* y Y) 1)
             (* y Y))
           ]


          [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                  :stroke (hsl [1 70 70 1])
                  :stroke-width 1
                  :fill :none}
           ]

          ])
       (range -20 20))





      ])))

(defn flames
  ([id red yellow]
   [:filter
    {:id (name id)
     :height (m7/np [300 :%])
     :width (m7/np [120 :%])
     :y (m7/np [-100 :%])
     :x (m7/np [0 :%])
     :filterunits :objectBoundingBox}
    [:feTurbulence
     {:stitchtiles :stitch
      :result "noise"
      :numoctaves 1
      :basefrequency 1
      :type :fractalNoise}]
    [:feOffset
     {:result "off1", :dy "0"}
     [:animate
      {:repeatcount "indefinite",
       :dur "6s",
       :to "-300",
       :from "0",
       :attributename "dy",
       :attributetype :XML}]]
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
    [:feFlood {:result "yellow", :flood-color yellow}]
    [:feComposite
     {:result "yellow-threshhold",
      :operator "in",
      :in "yellow",
      :in2 "threshhold"}]
    [:feFlood {:result "red", :flood-color red}]
    [:feComponentTransfer
     {:result "exponent-gradient", :in "SourceGraphic"}
     [:feFuncA {:exponent "5", :type "gamma"}]]
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
  ([]
   (flames :flames "#f33" "#ff9" )))








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
                    [[2 7 6 6  :center :center  2.7 :rem :column]
                     [(+ 1 (f 3)) 70 90 .2] [] {:gap "1rem"
                                                :z-index 4}])}





      #_[:div
       "Robin Hood "
       [:span  {:style {:font-weigh 500
                        :font-size "2.7rem"
                        :background-color (hsl [1 70 70 1])}} "exerts on average force of 100N"]
       " in pulling back his bow "
       [:span {:style {:font-weigh 500
                       :font-size "2.7rem"
                       :background-color (hsl [1 70 70 1])}}

        "by 0.5 meters"]

       ". He fires the arrow (mass = .2 kg) vertically upward.
 How much energy is stored in his bow and how high does the arrow go."]]



     [:div {:style (m7/css
                    [[2 8 7 10  :center :center  2.5 :rem :column]
                     [(+ 1 (f 3)) 70 90 .2] [] {:gap "1rem"
                                                :z-index 4}])}


      #_[m7/m '[= Elastic-energy [* average-force displacement]]]
      #_[m7/m '[= Elastic-energy [* [:m 100 N] [:m 0.5 m]] [:m 50 joules]]]



      #_[:div  {:style {:font-size (m7/np [3 :rem])
                      :color (hsl [1 40 30 1])
                      :background-color (hsl [1 70 70 1])}} "Since this energy is conserved than this must be the kinetic energy as the arrow leaves the bow. and it must also equal
 the gravitational PE at its highest point"]

      #_[m7/m '[= Work-done energy [* F displacement]]]


      #_[m7/m '[= Work-done gravitational-PE [* W h]]]

      [m7/m '[= gravitational-PE work-done]]

      [m7/m '[= change-in-PE [* weight change-in-height]]]

      [:div "Weight is a force duo to gravitational pull"]

      [m7/m '[= weight [:m m g]]]

      [:div "value of g in earth is " [m7/m '[:m 10 [m [:p s 2]]]]]



      [m7/m '[= change-in-PE [* m g  change-in-height]]]
      #_[m7/m '[= PE [* m g h] ]]

      #_[m7/m '[= PE mgh]]
;;       [m7/m '[= PE [* .2 kg 9.8 [m [:p s 2]] h ]  Elastic-enrgy ]]

;;       [m7/m '[=  [* .2 kg 9.8 [m [:p s 2]] h ] [:m 50 joules]]]

;;       [m7/m '[=  [* .2 9.8  h ] 50]]


      ]









     [:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [(+ 1 (f 3)) 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [0 -100  300 200])}
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
                :transform (m7/tranfrom [[:scale [.4 .4]]
                                         [:rotate 1]])
                :fill (m7/hsl [2 70 70 1])}]]
       [:marker {:id (name :dot222)
                 :viewBox (m7/space [-5 -5 10 10])
                 :refX 0
                 :refY 0
                 :orient :auto-start-reverse
                 :markerWidth 5
                 :markerHeight 5}

        [:path {:d (m7/path [-3.7
                             0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                :stroke (hsl [5 70 70 1])
                :stroke-width .2
                :transform (m7/tranfrom [[:scale [.6 .6]]
                                         [:rotate 0]])
                :fill (m7/hsl [.4 70 70 1])}]]


       [:g {:transform (m7/tranfrom [[:translate [0 50]]
                                     [:scale [.4 .4]]
                                     [:rotate -90]])}




        [:path {:d (path [0 -90 :c 40 40 60 100 0 180
                          :l 0 (ve 180) ])

                :stroke (hsl [0 70 70 1])
                :stroke-width 2
                :stroke-dashoffset 1050
                :stroke-dasharray (m7/space [10 15])
                :fill :none}]


        [:path {:d (path [0 -90 :c 40 40 60 100 0 180])

                :stroke (hsl [0 70 70 1])
                :stroke-width 2
                :fill :none}]



        (let [pull (ve 0)]
          [:g

           [:circle {:r 5
                     :cx pull
                     :cy 0
                     :fill (hsl [0 50 50 1])}]



           [:circle {:r 5
                     :cx (- 255 pull)
                     :cy 0
                     :fill (hsl [0 50 50 1])}]


           [:path {:d (path [0 -90 :l pull 90 ])
                   :stroke (hsl [0 70 70 1])
                   :marker-end (m7/url (name :dot222))
                   :stroke-width 1.2
                   :fill :none}]

           [:path {:d (path [0 90 :l pull -90 ])
                   :marker-end (m7/url (name :dot222))

                   :stroke (hsl [0 70 70 1])
                   :stroke-width 1.2
                   :fill :none}]

           [:path {:d (path [pull 0 :l  100 0])
                   :marker-end (m7/url (name :dot222))
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

            #_[:animateTransform {:id :arrow2
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
                    [[2 4 (+ 5 (* 0 2)) 15  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}


      #_[m7/m '[= F [[- mv mu] t]]]
      #_[m7/m '[= Ft [- mv mu]]]




      #_[:div "some of mass time velocity before collision"]

      #_[m7/m '[+ [:m [:k m g] [:k u g]]  [:m [:k m b] [:k u b]]]]

      #_[:div "some of mass time velocity after collision"]

      #_[m7/m '[+ [:m [:k m g] [:k v g]]  [:m [:k m b] [:k v b]]]]



      [:div {:style {:font-size "2rem"}} "conservation Law of momentum"]

      [m7/mx '[= [+ [:m [:k m g] [:k u g]]  [:m [:k m b] [:k u b]]]
               [+ [:m [:k m g] [:k v g]]  [:m [:k m b] [:k v b]]]]]


      #_(let [eq-repl ((fn [x  [e1 e2]]
                       (if (= x e1)
                         e2
                         x)))]
        (clojure.walk/postwalk
         (fn [x]
           (eq-repl x ['[:k m g] '[:m 170 g] ]))
         '[= [+ [:m [:k m g] [:k u g]]  [:m [:k m b] [:k u b]]]
           [+ [:m [:k m g] [:k v g]]  [:m [:k m b] [:k v b]]]]))


      #_[m7/m

         (clojure.walk/postwalk
          (fn [x]
            ((fn [[e1 e2]]
               (if (= x e1)
                 e
.                 x))
             ['[:k m g] '[:m 170 g] ]))
          '[= [+ [:m [:k m g] [:k u g]]  [:m [:k m b] [:k u b]]]
            [+ [:m [:k m g] [:k v g]]  [:m [:k m b] [:k v b]]]])]



      #_[m7/sx '[= [+ [:m [:k m g] [:k u g]]  [:m [:k m b] [:k u b]]]
               [+ [:m [:k m g] [:k v g]]  [:m [:k m b] [:k v b]]]]
       '[:k m g] '[:m 170 g]]


      #_(reduce

       (fn [e [x y]]
         (m7/sx e x y))

       [['[:k m g] 5]
        ['[:k m b] 5.2]]
       )



      #_[m7/mx '[= [+ [* [:m 170 g] [:m 5.2 [m s]]] 0]
               [+ [* [:m 170 g] [:k v g]]  [* [:m 160 g] [:m 5 [m s]]]]]]



      #_[m7/mx '[= [- [:m 884 g [m s]] [* [:m 170 g] [:k v g]]]
                 [+ [- [* [:m 170 g] [:k v g]]] [* [:m 170 g] [:k v g]]  [:m 800 g [m s]]]]]


      #_[m7/mx '[= [:k v g]
               [[:m 84  [m s]]
                [* 170 1]]
               [:m 0.494 [m s]]]]


      #_[m7/m '[= [+ [:m [:k m g] [:k u g]]  [:m [:k m b] [:k u b]]]
              [+ [:m [:k m g] [:k v g]]  [:m [:k m b] [:k v b]]]]]

      #_[m7/m '[= [+ m1u1  m2u2 ] [+ m1v1  m2v2 ]]]

      #_[m7/m ['= 'a [ [:m (- 7.76 4.43) ['m 's]]
                      [:m .29 's]]]]

      #_[m7/m ['= 'a   [:m  (/ (- 7.76 4.43) .29) ['m [:p 's 2]]] ]]


      #_[m7/m ['= [:m 'sin tt] ['a 'c]] ]
      #_[m7/m ['= [:m 'sin 35] [70 'c] .573]]
      #_[m7/m ['= [70 .573] 'c]]

      #_[m7/m ['= (js/Math.round (/ 70 .573)) 'c]]

      ]

     [:div {:style (m7/css
                    [[8 4 (+ 1 (* 0 2)) 20  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}


      [:div "The green ball collides with a stationary black ball. the mass of the green ball is 170g and velocity is 5.2 m/s before hit  and mass of brown ball is 160g
 after green ball hits brown ball, brown ball velocity is 5.0 m/s. find the velocity of green ball after collision?"]

      ]



     #_[:div {:style (m7/css
                    [[7 6 (+ 1 (* 0 2)) 7  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      [:div "Momentum of the green ball before collides"]
      [m7/m '[= P1 [:m [* [17 100]  5.2]  [Kgm s]]]]

      [:div "Momentum of the black ball before collides is 0"]


      ]


     #_[:div {:style (m7/css
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
                     [1 70 90 1] [] {:gap "2rem"}])}
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
          [:animateTransform {:id :green
                              :attributeName :transform
                              :begin :click
                              :dur (sec 5)
                              :from (space [0 0])
                              :to (space [150 0])
                              :type :translate
                              :fill :freeze}]

          [:animateTransform {:id :green2
                              :attributeName :transform
                              :begin :green.end
                              :dur (sec 5)
                              :from (space [150 0])
                              :to (space [350 0])
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
                            :to (space [550 0])
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
        time-str (-> timer .toTimeString
                     (str/split " ")
                     first
                     (str/split ":" )
                     (nth 2))]
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




     #_(map
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

     #_(map
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

     [:div {:style (m7/css
                      [[2 5 2 10  :center :center  1.5 :rem :column]
                       [(* 5 .2) 70 (+ 50 (* 5 5)) .1] [] {:gap ".1rem"
                                                           :z-index 4}])}



      #_[m7/m '[= [:k C h] [Q [- T2 T1]]]]


      #_[m7/m '[= [:k C s] [:m 4.184 [j g] C]]]


      #_[m7/m '[= [:k C fe] [:m .45 [j g] C]]]


      [:div "1068J of heat energy was applied to 50g of Aluminum metal. Then temperature changed from 21C to 45C. find out the heat capacity  "]


      [m7/m '[= [:m 1068 J]  [:m 50 g [:k C al] [:b [- [:k T 2] [:k T 1]]]] ]]

      [m7/m '[= [:m 1068 J]  [:m 50 g [:k C al] [:b [- 45 21]]] ]]


      [m7/m '[= [ [:m 1068 J] [- 45 21]] [:k C s]  [:m 50 g [:k C al]  ] ]]

      #_[m7/m '[= [:k C s] [4.184 2]]]

      #_[m7/m '[= [:m 1000 J]  [:m 10 g .45 [J g] C [:b [- [:k T 2] [:k T 1]]]] ]]


      #_[m7/m '[= [:m 1000 J]  [:m 10 g 4.184 [J g] C [:b [- [:k T 2] [:k T 1]]]] ]]



      #_[m7/m '[= [- [:k T 2] [:k T 1]] [1000 4.5]]]



      #_[m7/m '[= [- [:k T 2] [:k T 1]] [1000 41.84]]]

      #_[m7/m '[= [:m m [:k C s]] [:k C h]]]



      #_[m7/m '[= [:m m [:k C s]] [:k C h]]]
      #_[m7/m '[= [0.5 10] 0.05]]
      #_[m7/m '[=  [+ (* 3 0.5) [* 3 [0.5 10]]] 1.65]]
      #_[m7/m '[= 0.75 [75 100] [[* 25 3] [* 25 4]]]]
      ]





     [:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"
                     }
             :viewBox (m7/space
                       (nth [[50 -100  200 200]
                             [0 -10  20 20]
                             [0 -20  40 40]
                             [20 -20  40 40]] 3))}





       (let [
             X 0.5
             Y 1
             ax-dx 20
             ax-dy 60
             small-mark 3
             flag true
             fnt .5
             fixsize 4
             frac false]
         [:g

      ;; small

          [:g
           (map
            (fn [i]
              [:path {:d (path [(+ ax-dy (* i 2)) -400 :l 0 1200 ])

                      :stroke (hsl [(if (= i small-mark) 2 4) 70 70 .5])
                      :stroke-width .4
                      :fill :none}
               ])
            (range 0 11))]






          (map
           (fn [x]
             [:g

              [:path {:d (path [ (* 20 x)  0 :l 0 -400])
                      :stroke (hsl [0 70 70 .5])
                      :stroke-width .5
                      :fill :none}
               ]


              [:path {:d (path [ (* 20 x)  0 :l 0 400])

                  :stroke (hsl [0 70 70 1])
                  :stroke-width .5
                      :fill :none}
               ]

              [:text {:x (* 20 x)
                      :y 2.5
                      :dx -1
                      :fill (hsl [1 70 70 1])
                      :font-size 2.5}
               (if frac
                 (.toFixed (* x X) 1)
                 (* x X))
               ]])
           (range -20 40))



          (map
           (fn [y]
             [:g
              [:path {:d (path [0 (ve (* 20 y))   :l 1200 0])

                      :stroke (hsl [1 70 70 1])
                      :stroke-width .5
                      :fill :none}
               ]


              [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                      :stroke (hsl [1 70 70 1])
                      :stroke-width .5
                      :fill :none}
               ]

              ])
           (range -20 20))




          (map
           (fn [i]
             [:g
              [:path {:d (path [-400 (ve (+ ax-dx (* i 2))) :l 1200 0])
                      :stroke (hsl [4 70 70 .5])
                      :stroke-width .5
                      :fill :none}
               ]
              (if flag
                [:text {:x (* 2 i)
                        :fill (hsl [.6 90 40 1])
                        :font-size fnt
                        :y -3
                        :dx -0.15}
                 (fix (* i 0.1  X) fixsize)
                 ])


              ])
           (range 0 11))





          [:path {:d (path [0 0 :l 20 0])
                  :id :grf22
                  :stroke (hsl [1.2 30 40 .8])
                  :stroke-dasharray (m7/space [1.9 .1])
                  :stroke-width .2
                  :fill :none}

           ]



          (map
           (fn [i]
             [:text {:font-size 1
                     :x (* 2 i)
                     :y 0
                     :fill (hsl [0 20 90 1])}

              [:tspan {:dy 0
                       :dx .5}

               "x"]

              [:tspan {:dy 1
                       :dx -1} 10]
              ])
           (range 0 10))


          #_[:path {:d (path [0 0 :l 10 0])
                  :stroke (hsl [3.5 60 70 1])
                  :stroke-width 1
                  :fill :none}
           ]

          #_[:circle {:r .5
                    :cx 2
                    :cy 0
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .5
                    :fill (hsl [5 70 70 .1])}
             ]





      ])



       #_(let [pull (ve 0)]
         [:g


          [:path {:d (path [-100 0 :l 1200 0])
                  :stroke (hsl [0 70 70 .1])
                  :stroke-width .1
                  :fill :none}]

          [:path {:d (path [0 -600  :l 0 1200])
                  :stroke (hsl [0 70 70 .5])
                  :stroke-width .1
                  :fill :none}]


          [:path {:d (path (mapcat identity  (into [[0 0  :l]] (repeat 60 [10 0 0 -10 0 10]))))
                  :stroke (hsl [0 70 70 1])
                  :stroke-width .2
                  :fill :none}]


          [:path {:d (path (mapcat identity  (into [[0 0  :l]]
                                                   (repeat 60
                                                           [0 -10
                                                            -10 0
                                                            10 0]))))
                  :stroke (hsl [0 70 70 1])
                  :stroke-width .1
                  :fill :none}]



          [:path {:d (path (mapcat identity
                                   (into [[0 0  :l]]
                                         (repeat 60 [10 0 0 -10 0 10]))))
                  :stroke (hsl [0 70 70 1])
                  :stroke-width .1
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
                     :stroke-width .1
                     :fill (hsl [5 80 80 .5])}])
           (range 0 2))




          (map
           (fn [[x y]]
             [:circle {:cx (* x 10)
                       :cy (ve (* y 10))
                       :r .1
                       :fill (hsl [0 70 70 1])}])
           [[0 0] [12 8] [24 (+ 8 4)] [36 (+ 8 4 2.4 )]
            [48 (+ 8 4 2.4 1.6)]])


          [:path {:d (path [0 0 :l 120 -80
                            120 -40 120 -24 120 -16])
                  ;; :marker-end (m7/url "i")
                  :stroke (hsl [2 70 70 1])
                  :stroke-width .1
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

          ])]]]))





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

(def dx [1 0  0 1 -1  0 0 -1 ])




(defn force-diagram []
  (let [[slider get-slider] (react/useState 0)]
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
                       [-200 -200  400 400])}


       [:marker {:id (name :dot232)
                 :viewBox (m7/space [-5 -5 10 10])
                 :refX 0
                 :refY 0
                 :orient :auto-start-reverse
                 :markerWidth 5
                 :markerHeight 5}
        [:path {:d (m7/path [-3.7
                             0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                :stroke (hsl [5 70 70 1])
                :stroke-width .2
                :transform (m7/tranfrom [[:scale [.6 .6]]
                                         [:rotate 0]])
                :fill (m7/hsl [.4 70 70 1])}]]
       #_(grid-on 1 1)

       [:text [:textPath {:href ::#hello-w1
                          :startOffset (m7/not-space [50 "%"])
                          :font-size 12}
               "wight"
               ]]

       [:path {:d (m7/path [0 0 :l 0  80 ])
               :id ::hello-w1
               :stroke (hsl [5 70 70 0.5])
               :stroke-width .1
               :fill :none
               :marker-end (m7/url (name :dot232))}
        [:animateTransform
         {:attributeName :transform
          :begin :hellov.end
          :dur (sec 4)
          :type :scale
          :from (m7/space [.2 .2])
          :to (m7/space [1 1])
          :fill :freeze}]

        [:animate
         {:attributeName :stroke-width
          :begin :hellov.end
          :dur (sec 1)
          :from 1
          :to 2
          :fill :freeze}]

        ]



       #_(take 10 (cycle [2 1]))

       [:g {:transform (m7/tranfrom [[:rotate -30]])}
        ;; F1

        [:path {:d (m7/path `[0 0 :l ~@(map #(* -20 %1 %2)
                                            dx
                                            (take 10 (cycle [10 .3])))])
                :stroke (hsl [0 70 70 1])
                :stroke-width 5
                :fill (hsl [2 70 70 1])
                }
         ]


        [:path {:d (m7/path [0 0 :l 220 0])
                :id ::hello
                :stroke (hsl [3 70 70 1])
                :stroke-width 2
                :fill :none
                :marker-end (m7/url (name :dot232))}
         [:animateTransform
          {:attributeName :transform
           :id :hellov
           :begin 0
           :dur (sec 2)
           :type :scale
           :from (m7/space [.2 .2])
           :to (m7/space [1 1])
           :fill :freeze}]]


        [:text [:textPath {:href ::#hello
                           :startOffset (m7/not-space [50 "%"])
                           :font-size 19}
                "Thrust"
                ]]

          [:path {:d (m7/path [0 0 :l    -120 0 ])
                  :id ::hello2
                  :stroke (hsl [2 70 70 0.5])
                  :stroke-width .1
                  :fill :none
                  :marker-end (m7/url (name :dot232))}
           [:animateTransform
            {:attributeName :transform
             :begin :hellov.end
             :dur (sec 4)
             :type :scale
             :from (m7/space [.2 .2])
             :to (m7/space [1 1])
             :fill :freeze}]

           [:animate
            {:attributeName :stroke-width
             :begin :hellov.end
             :dur (sec 1)
             :from 1
             :to 2
             :fill :freeze}]

           ]

        [:text [:textPath {:href ::#hello2
                           :startOffset (m7/not-space [50 "%"])
                           :font-size 12}
                "drag"
                ]]

        [:text [:textPath {:href ::#hello-w1
                           :startOffset (m7/not-space [50 "%"])
                           :font-size 12}
                ""
                ]]

        [:path {:d (m7/path [0 0 :l 0  80 ])
                :id ::hello-w1
                :stroke (hsl [5 70 70 0.5])
                :stroke-width .1
                :fill :none
                :marker-end (m7/url (name :dot232))}
         [:animateTransform
          {:attributeName :transform
           :begin :hellov.end
           :dur (sec 4)
           :type :scale
           :from (m7/space [.2 .2])
           :to (m7/space [1 1])
           :fill :freeze}]

         [:animate
          {:attributeName :stroke-width
           :begin :hellov.end
           :dur (sec 1)
           :from 1
           :to 2
           :fill :freeze}]

         ]

        [:text [:textPath {:href ::#hello-upw1
                           :startOffset (m7/not-space [50 "%"])
                           :font-size 12}
                ""
                ]]

        [:path {:d (m7/path [0 0 :l 0  (ve 80) ])
                :id ::hello-upw1
                :stroke (hsl [5 70 70 0.5])
                :stroke-width .1
                :fill :none
                :marker-end (m7/url (name :dot232))}
         [:animateTransform
          {:attributeName :transform
           :begin :hellov.end
           :dur (sec 4)
           :type :scale
           :from (m7/space [.2 .2])
           :to (m7/space [1 1])
           :fill :freeze}]

         [:animate
          {:attributeName :stroke-width
           :begin :hellov.end
           :dur (sec 1)
           :from 1
           :to 2
           :fill :freeze}]

         ]




        ;; res
          #_[:path {:d (m7/path [0 0 :l 150 -90])
                  :id ::helloF3
                  :stroke (hsl [4.5 70 70 1])
                  :stroke-width .1
                  :fill :none
                  :marker-end (m7/url (name :dot232))}
           #_[:animateTransform
              {:attributeName :transform
               :begin :hellov.end
               :dur (sec 4)
               :type :scale
               :from (m7/space [.2 .2])
               :to (m7/space [1 1])
               :fill :freeze}]

           [:animate
            {:attributeName :stroke-width
             :begin :hellov.end
             :dur (sec 1)
             :from 1
             :to 5
             :fill :freeze}]

             ]
        ]




       [:text [:textPath {:href ::#helloF3
                          :startOffset (m7/not-space [50 "%"])
                          :font-size 9}
               "F3"
               ]]






       ]]

     [:div {:style (m7/css
                    [[2 10 (+ 2 (* 0 2)) 12  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}



      [:div "The diagram shows a firework rocket: As it flies through the air,
there are three forces on it."]

      [:div {:style {:font-size "1.5rem"}} "Which 3 arrows shows 3 forces"]
      [:div {:style {:font-size "1.5rem"}} "Copy the diagram with these 3 forces"]

      [:div {:style {:font-size "1.5rem"}} "Label the 3 arrows, using these words, weight thrust air resistance (drag)"]
      [:div {:style {:font-size "1.5rem"}} "What can you say about these forces when the rocket is just taking of"]

      [:div {:style {:font-size "1.5rem"}} "Why does the rocket come back down"]



      #_[m7/m '[= F3 [+ [:m 5 N] [:m [- 6] N]]]]
      #_[m7/m '[= F3 [:m [- 1] N]]]


      ]








     ]))





(defn linear-equation []
  (let [[slider get-slider] (react/useState 0)
        lin2 (fn [x y]
               (fn [[a  bb  c]]
                 (let [[b minus]
                       (if (number? bb)
                         [(js/Math.abs bb) (if (> bb 0) false true )]
                         [bb (if (= (first bb) '-)
                               true false)] )]
                   (if minus
                     (m7/mx
                      `[= [-  [:m ~a ~x]
                           [:m ~b ~y]] ~c])
                     (m7/mx
                      `[= [+ [:m ~a ~x]
                           [:m ~b ~y]] ~c])))))

        lin (lin2 'x 'y)

        ]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}

     #_[:div {:style (m7/css
                    [[1 12 10 13  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}
      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [-40 -80 300 200])}


       [:marker {:id (name :dot232)
                 :viewBox (m7/space [-5 -5 10 10])
                 :refX 0
                 :refY 0
                 :orient :auto-start-reverse
                 :markerWidth 5
                 :markerHeight 5}
        [:path {:d (m7/path [-3.7
                             0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                :stroke (hsl [5 70 70 1])
                :stroke-width .2
                :transform (m7/tranfrom [[:scale [.6 .6]]
                                         [:rotate 0]])
                :fill (m7/hsl [.4 70 70 1])}]]

       [:foreignObject {:x 40
                        :y 0
                        :font-size 10
                        :width 100
                        :height 20}
        [m7/m '[:m [:b [+ x y 2]] cm]]
        ]


       [:foreignObject {:x 5
                        :y 40
                        :font-size 10
                        :width 100
                        :height 20}
        [m7/m '[:m [:b [:m 2 y]] cm]]
        ]

       [:foreignObject {:x 180
                        :y 40
                        :font-size 10
                        :width 100
                        :height 20}
        [m7/m '[:m [:b [+ x 2]] cm]]
        ]


       [:foreignObject {:x 40
                        :y 80
                        :font-size 10
                        :width 100
                        :height 20}
        [m7/m '[:m [:b [+ [:m 3 x] 1] ] cm]]
        ]

       [:path {:d (m7/path `[0 0 :l ~@(map #(* 80 %) dx )])
               :id :ffrect3
               :fill :none
               :stroke-width 3
               :transform (m7/tranfrom [[:scale [3 1]]])
               :stroke (hsl [2 50 70 1])}
        ]

       #_(grid-on 1 1)


       ]]

     [:div {:style (m7/css
                      [[2 10 (+ 12 (* 0 2)) 12  :center :center  4 :rem :column]
                     [(* 5 .5) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      [m7/m '[= [- [:m 3 x] [:m 2 y] ] 1 ]]




      #_[m7/m '[= [+ [-[:m 3 x]] y ] [- 5]]]


      #_[:div {:style {:width "100%"
                     :height ".5rem"
                     :background-color :#aaa}}]
      #_[m7/m '[= [* -1 [- y]]
              [* [- 1] [- 4]]]]
      #_[m7/m '[= y 4]]

      #_[m7/m '[= x 3]]

      ]

     [:div {:style (m7/css
                    [[2 10 (+ 2 (* 0 2)) 12  :center :center  3.4 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      #_[:div {:style {:font-size "1.9rem"}}
       #_[:span "If 1 is added to the numerator  "]
       #_[:sapn "and 2 to the denominator of a fraction, "]
       [:span "the value obtained is "]
       [m7/m '[2 3]]

       ]
      [:div {:style {:font-size "1.9rem"}}
       [:span "if 2 is subtracted"]
       [:span " from its same numerator and 1 from its denominator"]
       [:span ", the resulting value is"]
       [m7/m [1 3]]
       ]







      #_[m7/m '[= [+ [:m [- 2] x] y  ] [- 1]]]
      #_[m7/m '[= [- [:m 4 y] [:m 2 x]] 4]]

      #_[m7/m '[= [+  [- y] [:m 2 x] ] 1]]

      #_(lin  (map #(* -2 %) [-2 1 -1]))
      #_[m7/m '[= [+ [- x] [:m 2 y]] 2]]
      #_[m7/m '[= [+ [:m 2 x]  [- y]  ] 1 ]]

      #_[m7/m '[= [- [:m 5 x] [:m 3 x]] [:m 2 x]]]
      #_[m7/m '[= [+ [- x] [:m    2 y]] [+ x [- x] 2]]]
      #_[m7/m '[= [:m 3 y] [:m [* [- 3] [- 1]] y]]]
      #_(lin (map #(* -1 %) [2 -1 1]))
      #_[m7/m '[= [+ [- x] [:m    2 y]] 2]]
      #_(lin (map #(* 2 %) [-1 2 2]))

      [:div {:style {:width "100%"
                     :height ".5rem"
                     :background-color :#aaa}}]

      [:div {:style {:font-size "1.9rem"}} "let, the fraction is, " [m7/m `[x y]]


       ]

      [m7/x `[[- x 2]]]
      #_[m7/m '[= [- [:m 3 x] [:m 2 y]] 1]]

      #_[m7/m '[= [2 3] [[+ x 1 ] [+ y 2 ]]]]

      #_[m7/x '[= [+ [:m 3 x] [- [:m 2 y]]  ] 1]]

      #_[m7/m '[= [1 3] [[- x 2] [- y 1]]]]
      #_[m7/m '[= [+ [- [:m 3 x]] y ] [- 5] ]]

      #_[m7/m '[= 1   [- [:m 3 x] [:m 2 y ]]]]


      #_[m7/m '[= [1 3] [[- x 2] [- y 1]]]]
      #_[:div


       [m7/m '[= [- [:m 3 x] y] 5]]

       [m7/m '[= [+ [- [:m 3 x]]  [:m 2 y]] [- 1] ]]

       [:div {:style {:width "100%"
                      :height ".5rem"
                      :background-color :#aaa}}]



       [m7/m '[= [:m 3 x] [* 3 3]]]

       [m7/m '[= x 3]]]


      #_(lin (map #(+ %1 %2)
                (map #(* 1 %) [2 -1 1])
                (map #(* 2 %) [-1 2 2])
                ))

      #_[m7/m '[= [:m [3 3] y] [5 3]]]

      #_[m7/m '[= y [[* 5 2] [* 2 3]]]]


      #_[m7/m '[= [+ 0 y] 32] ]
      #_(lin (map #(* 1 %) [13 -6 20]))
      #_((lin2 2 'y) (map #(* 1 %) [13 -6 20]))
      #_(lin [1 0 2])

      ]








     ]))


(defn home-vector []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ

        lin (fn [[a  bb  c]]
              (let [[b minus]
                    (if (number? bb)
                      [(js/Math.abs bb) (if (> bb 0) false true )]
                      [bb (if (= (first bb) '-)
                            true false)] )]
                (if minus
                  (m7/mx
                   `[= [- [:m ~a x]
                        [:m ~b y]] ~c])
                  (m7/mx
                   `[= [+ [:m ~a x]
                        [:m ~b y]] ~c]))))


        qd (fn [[a  bb  c]]
              (let [[b minus]
                    (if (number? bb)
                      [(js/Math.abs bb) (if (> bb 0) false true )]
                      [bb (if (= (first bb) '-)
                            true false)] )]
                (if minus
                  (m7/mx
                   `[+ [- [:p x 2]
                        [:m ~b x]] ~c])
                  (m7/mx
                   `[+ [+ [:p x 2]
                        [:m ~b x]] ~c]))))


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
                       [-200 -200  400 400])}


       [:marker {:id (name :dot232)
                 :viewBox (m7/space [-5 -5 10 10])
                 :refX 0
                 :refY 0
                 :orient :auto-start-reverse
                 :markerWidth 5
                 :markerHeight 5}
        [:path {:d (m7/path [-3.7
                             0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                :stroke (hsl [5 70 70 1])
                :stroke-width .2
                :transform (m7/tranfrom [[:scale [.6 .6]]
                                         [:rotate 0]])
                :fill (m7/hsl [.4 70 70 1])}]]
       #_(grid-on 1 1)









       [:g
        ;; F1
        [:path {:d (m7/path [0 0 :l 120 0])
                :id ::hello
                :stroke (hsl [3 70 70 1])
                :stroke-width 5
                :fill :none
                :marker-end (m7/url (name :dot232))}
         [:animateTransform
          {:attributeName :transform
           :id :hellov
           :begin 0
           :dur (sec 2)
           :type :scale
           :from (m7/space [.2 .2])
           :to (m7/space [1 1])
           :fill :freeze}]]

          [:path {:d (m7/path [0 0 :l  30 -90])
                  :id ::hello2
                  :stroke (hsl [2 70 70 0.5])
                  :stroke-width .1
                  :fill :none
                  :marker-end (m7/url (name :dot232))}
           [:animateTransform
            {:attributeName :transform
             :begin :hellov.end
             :dur (sec 4)
             :type :scale
             :from (m7/space [.2 .2])
             :to (m7/space [1 1])
             :fill :freeze}]

           [:animate
            {:attributeName :stroke-width
             :begin :hellov.end
             :dur (sec 1)
             :from 1
             :to 5
             :fill :freeze}]

           ]


          [:path {:d (m7/path [120 0 :l  30 -90])
                  :id ::hello21
                  :stroke (hsl [2 70 70 0.5])
                  :stroke-width .1
                  :fill :none
                  :marker-end (m7/url (name :dot232))}
           [:animateTransform
            {:attributeName :transform
             :begin :hellov.end
             :dur (sec 4)
             :type :scale
             :from (m7/space [.2 .2])
             :to (m7/space [1 1])
             :fill :freeze}]

           [:animate
            {:attributeName :stroke-width
             :begin :hellov.end
             :dur (sec 1)
             :from 1
             :to 5
             :fill :freeze}]

           ]





          [:path {:d (m7/path [0 0 :l 150 -90])
                  :id ::helloF3
                  :stroke (hsl [4.5 70 70 1])
                  :stroke-width .1
                  :fill :none
                  :marker-end (m7/url (name :dot232))}
           #_[:animateTransform
              {:attributeName :transform
               :begin :hellov.end
               :dur (sec 4)
               :type :scale
               :from (m7/space [.2 .2])
               :to (m7/space [1 1])
               :fill :freeze}]

           [:animate
            {:attributeName :stroke-width
             :begin :hellov.end
             :dur (sec 1)
             :from 1
             :to 5
             :fill :freeze}]

           ]]


       [:path {:d (m7/path [0 0 :l (+ 70 80) -60])
                 :id ::hello4
                 :stroke (hsl [4 70 70 .5])
                 :fill :none
                 :marker-end (m7/url (name :dot22))}
          [:animateTransform
           {:attributeName :transform
            :begin (sec 0)
            :dur (sec 4)
            :type :scale
            :from (m7/space [.2 .2])
            :to (m7/space [1 1])
            :fill :freeze}]]

       [:text [:textPath {:href ::#helloF3
                          :startOffset (m7/not-space [50 "%"])
                          :font-size 9}
               "F3"
               ]]

       [:text [:textPath {:href ::#hello2
                          :startOffset (m7/not-space [50 "%"])
                          :font-size 9}
               "F2"
               ]]

       [:text [:textPath {:href ::#hello
                          :startOffset (m7/not-space [50 "%"])
                          :font-size 9}
               "F1"
               ]]


       ]]

     [:div {:style (m7/css
                    [[2 10 (+ 2 (* 0 2)) 12  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}



      [m7/m '[= F3 [+ F1 F2]]]

      #_[m7/m '[= F3 [+ [:m 5 N] [:m [- 6] N]]]]
      #_[m7/m '[= F3 [:m [- 1] N]]]


      ]








     ]))






(defn  banner-qd []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        xa 2
        xb 2

        a 3
        b 1
        a' 3
        b' 1

        lin2 (fn [x y]
               (fn [[a  bb  c]]
                 (let [[b minus]
                       (if (number? bb)
                         [(js/Math.abs bb) (if (> bb 0) false true )]
                         [bb (if (= (first bb) '-)
                               true false)] )]
                   (if minus
                     (m7/mx
                      `[= [- [:m ~a ~x]
                           [:m ~b ~y]] ~c])
                     (m7/mx
                      `[= [+ [:m ~a ~x]
                           [:m ~b ~y]] ~c])))))

        lin (lin2 'x 'y)


        qd (fn [[a  bb  c]]
              (let [[b minus]
                    (if (number? bb)
                      [(js/Math.abs bb) (if (> bb 0) false true )]
                      [bb (if (= (first bb) '-)
                            true false)] )]
                (if minus
                  (m7/mx
                   `[+ [- [:m ~a [:p x 2]]
                        [:m ~b x]] ~c])
                  (m7/mx
                   `[+ [+ [:m ~a [:p x 2]]
                        [:m ~b x]] ~c]))))

        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}

     (map
      (fn [n d]
        [:div {:style (m7/css
                       [[2 1 (+ 9 (* n 2)) 2  :center :center  2 :rem :column]
                        [(* n .2) 70 (+ 50 (* 1 n))  .7] [] {:gap ".1rem"
                                                             :z-index 5}])}

         d])
      (range 0 24)
      [ (m7/mx `[:m (* ~xa ~xb ) [:p x 2]]) (m7/mx `[* ~xa [:m ~b x]])])

     (map
      (fn [n d]
        [:div {:style (m7/css
                       [[3 1 (+ 9 (* n 2)) 2  :center :center  2 :rem :column]
                        [(* n .2) 70 (+ 50 (* 1 n))  .7] [] {:gap ".1rem"
                                                             :z-index 5}])}

         d])
      (range 0 24)
      [ (m7/mx `[* ~xb [:m ~a x]]) (m7/mx `[* ~a ~b])]

      )


     [:div {:style (m7/css
                    [[1 12 10 13  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}
      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [-200 -200  400 400])}


       [:marker {:id (name :dot232)
                 :viewBox (m7/space [-5 -5 10 10])
                 :refX 0
                 :refY 0
                 :orient :auto-start-reverse
                 :markerWidth 5
                 :markerHeight 5}
        [:path {:d (m7/path [-3.7
                             0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                :stroke (hsl [5 70 70 1])
                :stroke-width .2
                :transform (m7/tranfrom [[:scale [.6 .6]]
                                         [:rotate 0]])
                :fill (m7/hsl [.4 70 70 1])}]]
       #_(grid-on 1 1)





       [:g
        [:foreignObject {:x 20
                         :y 60
                         :font-size 20
                         :width 100
                         :height 50}
         [m7/mx `[* ~a ~b]]]




        [:foreignObject {:x -30
                         :y -30
                         :font-size 10
                         :width 40
                         :height 40}
         [m7/m '[:p x 2]]]

        [:foreignObject {:x -30
                         :y (* 40 2)
                         :font-size 10
                         :width 40
                         :height 40}
         [m7/mx `[:m ~a x]]]

        [:foreignObject {:x (+ -30 (* 40 2))
                         :y -30
                         :font-size 10
                         :width 40
                         :height 40}
         [m7/mx `[:m ~b x]]]
        ;; * a b
        [:path {:d (m7/path [0 0 :l (* a' 40) 0 0 (* b' 40)   (ve (* a' 40)) 0 0
                             (ve (* b' 40))])
                :id :qdp1
                :stroke (hsl [3 70 70 1])
                :stroke-width 5
                :fill :none
                }
         [:animateTransform
          {:attributeName :transform
           :id :hellov
           :begin 0
           :dur (sec 2)
           :type :scale
           :from (m7/space [.2 .2])
           :to (m7/space [1 1])
           :fill :freeze}]]

        [:text {:dx 0
                :dy -5}
         [:textPath {:side :right
                     :href :#qdp1
                     :startOffset "10%"
                     :font-size 10}
          a]

         [:textPath {:dx 10
                     :side :right
                     :href :#qdp1
                      :startOffset "35%"
                     :font-size 15}
          b]

         [:textPath {:dx 10
                     :side :right
                     :href :#qdp1
                     :startOffset "65%"
                     :font-size 15}
          a]

         [:textPath {:dx 10
                     :side :right
                     :href :#qdp1
                     :startOffset "80%"
                     :font-size 15}
          b]]




        [:path {:d (m7/path [0 0 :l (* 1 40) 0 0 (* b' 40)   (ve (* 1 40)) 0 0
                             (ve (* b' 40))])
                :id ::hello
                :stroke (hsl [2.5 70 70 1])
                :stroke-width 5
                :fill :none
                }
         [:animateTransform
          {:attributeName :transform
           :id :helloside3
           :begin 0
           :dur (sec 2)
           :type :scale
           :from (m7/space [.2 .2])
           :to (m7/space [-1 1])
           :fill :freeze}]]


        [:path {:d (m7/path [0 0 :l (* a' 40) 0 0 (* 1 40)   (ve (* a' 40)) 0 0
                             (ve (* 1 40))])
                :id ::hello
                :stroke (hsl [2 70 70 1])
                :stroke-width 5
                :fill :none
                }
         [:animateTransform
          {:attributeName :transform
           :id :helloside2
           :begin 0
           :dur (sec 2)
           :type :scale
           :from (m7/space [.2 .2])
           :to (m7/space [1 -1])
           :fill :freeze}]]

        ;;
        [:path {:d (m7/path `[0 0 :l ~@(map #(* 40 %) dx)])
                :id :hellosq
                :stroke (hsl [4 70 70 1])
                :stroke-width 5
                :fill :none
                }
         [:animateTransform
          {:attributeName :transform
           :id :hellov
           :begin 0
           :dur (sec 2)
           :type :scale
           :from (m7/space [.2 .2])
           :to (m7/space [-1 -1])
           :fill :freeze}]]





        [:path {:d (m7/path [-40 -40 :l 0  (* 40 (+ b' 1))   (* 40 (+ a' 1)) 0
                             0  (ve (* 40 (+ b' 1)))   (ve (* 40 (+ a' 1))) 0])
                  :id ::hello4
                  :stroke (hsl [6 70 70 .5])
                  :stroke-width 8
                  :fill :none
                  :marker-end (m7/url (name :dot22))}
           ]]
       ]]



     [:div {:style (m7/css
                    [[2 10 (+ 2 (* 0 2)) 12  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      (qd [4 8 3])
      (qd [`[* 2 2] `[:b [+ (* ~xa ~a) (* ~xb ~b)]] `[* ~a ~b]])
      [m7/m '[:m [:b [+ [:m 2 x] 3]]  [:b [+ [:m 2 x] 1]]]]
      ]



     ]))



(defn banner-factor-identities []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ

        lin2 (fn [x y]
               (fn [[a  bb  c]]
                 (let [[b minus]
                       (if (number? bb)
                         [(js/Math.abs bb) (if (> bb 0) false true )]
                         [bb (if (= (first bb) '-)
                               true false)] )]
                   (if minus
                     (m7/mx
                      `[= [- [:m ~a ~x]
                           [:m ~b ~y]] ~c])
                     (m7/mx
                      `[= [+ [:m ~a ~x]
                           [:m ~b ~y]] ~c])))))

        lin (lin2 'x 'y)


        qd (fn [[a  bb  c]]
              (let [[b minus]
                    (if (number? bb)
                      [(js/Math.abs bb) (if (> bb 0) false true )]
                      [bb (if (= (first bb) '-)
                            true false)] )]
                (if minus
                  (m7/mx
                   `[+ [- [:m ~a [:p x 2]]
                        [:m ~b x]] ~c])
                  (m7/mx
                   `[+ [+ [:m ~a [:p x 2]]
                        [:m ~b x]] ~c]))))

        sq (fn [n]
                (comp
                 (partial map (partial * n))))
        hidden false
        [ax ay av sma] [5 5 (not (not true)) 3]
        [bx by bv smb] [10 10 (not (not true)) 2]
        ]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}

     [:div {:style (m7/css
                    [[1 12 10 13
                      :center :center 2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .1]
                     [] {:gap ".1rem"
                         :z-index 4}])}
      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [-300 -300  700 700])}


       (if bv
         (map
          (fn [y111 y11]
            (let [y1 (* y11 (/ 40 smb))]
              (map
               (fn [x1 y]
                 (let [x (* x1 (/ 40 smb))]
                   [:g

                    [:path {:d (m7/path `[ ~x ~y1 :l ~@(map #(* (/ 40 smb) %) dx)])
                            :fill (hsl  [(if (not y) 1.5 2.3) 90 60 .8])
                            :stroke :#aaa
                            :stroke-width 2
                            }]
                    [:text {:x x
                            :y y1}
                     #_(+ 1 x1)
                     ]]))
               (range 0 (* smb bx))
               (cycle [bv bv])
               )))
          (range 0 (* smb by))
          (range 0 (* smb by))))





       [:g  {:transform (m7/tranfrom [[:translate [(ve (* 40 ax))
                                                   (ve (* 40 ay)) ]]
                                      [:scale [1 1]]])}
        (map
         (fn [y111 y11]
           (let [y1 (* y11 40)]
             (map
              (fn [x1 y]
                (let [x (* x1 40)]
                  [:g

                   [:path {:d (m7/path `[ ~x ~y1 :l ~@(map #(* 40 %) dx)])
                           :fill (hsl  [(if (not y) .1 .8) 90 60 .5])
                           :stroke :#aaa
                           :stroke-width 2
                           }]
                   [:text {:x x
                           :y y1}
                    #_(+ 1 x1)
                    ]]))
              (range 0 (+ ax bx))
              (cycle [true true])
              )))
         (range 0 (+ ay by))
         (range 0 (+ ay by)))]

       (if av
         [:g  {:transform (m7/tranfrom [[:scale [-1 -1]]
                                        [:translate [0 0]]])}
          (map
           (fn [y111 y11]
             (let [y1 (* y11 40)]
               (map
                (fn [x1 y]
                  (let [x (* x1 40)]
                    [:g

                     [:path {:d (m7/path `[ ~x ~y1 :l ~@(map #(* 40 %) dx)])
                             :fill (hsl  [(if (not y) 3 3.5) 90 60 1])
                             :stroke :#aaa
                             :stroke-width 2
                             }]
                     [:text {:x x
                             :y y1}
                      #_(+ 1 x1)
                      ]]))
                (range 0 ax)
                (cycle [true true])
                )))
           (range 0 ay)
           (range 0 ay))])]]





     [:div {:style
            (m7/css
             [[2 10 (+ 2 (* 0 2)) 12  :center :center  2.0 :rem :column]
              [(* 5 .2) 70 (+ 50 (* 5 5))  .1]
              []
              {:gap ".1rem"
               :z-index 4}])}




        #_[m7/m '[= [- [:p a 2] [:p b 2]]
                [:m [:b [+ a 1]]
                 [:b [- a 1]]]]]


      #_(if true
        [:div {:style {:background-color (hsl [1 50 50 .7])}}
         [m7/mx `[= [:p [:b [+ ~ax ~bx]]  2]
                  [+ [:p ~ax  2]  [:p ~bx  2] [* 2 ~ax ~bx ]]]]])



        #_[m7/m '[= [- [:p a 2] [:p b 2]]
                [:m [:b [+ a b]] [:b [- a b]]]]]
      #_[m7/m '[=  [:p [:b [- 8 3]] 2]  [+ [:p 8 2] [:p [:b [- 3]] 2]
                                       [* 2 [- 3] 8]]]]







      #_(let [a sma
            b (ve smb)
            aa ax
            bb bx
            hidden false
            sign '+
            msign '*
            ]
        [:div {:style {:font-size "1.8rem"}}
         [m7/mx `[=
                  ~(if hidden '_
                       `~(* (+ (* aa a)  (* bb b))  (+ (* aa a)  (* bb b))))
                  [~sign [~sign ~(* (* aa  a) (* aa  a))

                          ~(* (* bb  b) (* bb  b))
                          ]
                   ~(* 2 aa  a bb b)
                   ]]]])


      #_(if hidden
          (let [a sma
                b (ve smb)
                aa ax
                bb bx
                hidden false
                sign '+
                msign '*
                ]
            [:div {:style {:font-size "1.8rem"}}
             [m7/mx `[=
                      ~(if hidden '_
                           `[:p
                             [:b [~sign [~msign ~aa ~a] [~msign ~bb ~b] ]] 2])
                      [~sign [~sign [:p [:b [~msign ~aa  ~a]] 2]
                              [:p [:b [~msign ~bb ~b]] 2]]
                       [~msign 2  [:b [~msign ~aa ~a]] [:b [~msign ~bb ~b]]]]]]]
            ))

      (if hidden
        (let [a 'x
              b 'y
              ax 1
              bx 3
              aa ax
              bb bx
              sign '+
              hidden false
              msign :m]
          [m7/mx `[=
                   ~(if hidden '_
                        `[:p
                          [:b [~sign ~(if (= ax 1)
                                        a
                                        `[~msign ~aa ~a])
                               [~msign ~bb ~b] ]] 2])
                   [~sign [+ [:p ~(if (= ax 1)
                                    a
                                    [:b [~msign ~aa  ~a]]) 2]
                           [:p [:b [~msign ~bb ~b]] 2]]
                    [~msign 2  [:b [~msign ~aa ~a]
                                ] [:b [~msign ~bb ~b]]]]]]))



      #_[m7/m '[= 650 [+ [:p x 2]  [:p y 2]]]]


      #_[m7/m '[= [:m 2 xy] [* 2 323]]]



      ;; [m7/m '[= [:p [:b [+ x y]] 2] [+ [:p x 2] [:m 2 xy]  [:p y 2]]]]


      ;; [m7/mx `[= [:p [:b [+ x y]] 2] ~(+ 650 646) ]]

      ;; [m7/mx `[= [+ x y] [:sq ~(+ 650 646)] ]]

      ;; [m7/mx `[= [+ x y] [- [:sq ~(+ 650 646)]] ]]

      #_[m7/mx `[= y  [:b [- 36 x]]]]
      #_[m7/mx `[= 323  [:m x [:b [- 36 x]]] ]]

      #_[m7/mx `[= [+ [- [:p x 2] [:m 36 x] ] 323] 0 ]]

      #_[m7/mx `[= x  [+ 18 [:sq [- [:p 18 2] 323]]]] ]
      #_(js/Math.sqrt (- (* 18 18) 323))
      #_[m7/mx '[+ 18 ]]
      #_(if true
        (let [a 'a
              b 'b
              aa ax
              bb bx
              hidden false
              sign '-
              msign :m
              ]
          [m7/mx `[~sign [+ [:m ~(* aa aa)  [:p ~a 2]]
                          [:p [:b [~msign ~bb ~b]] 2]]
                   [~msign
                    ~(* 2  aa bb) ~a ~b]]]))


      #_(let [a sma
            b (ve smb)
            aa ax
            bb bx
            hidden false
            sign '+
            msign '*
            ]
        [:div {:style {:font-size "1.8rem"}}
         [m7/mx `[=
                  ~(if hidden '_
                       `[:p
                         [:b [~sign [~msign ~aa ~a] [~msign ~bb ~b] ]] 2])
                  [~sign [~sign [:p [:b [~msign ~aa  ~a]] 2]
                          [:p [:b [~msign ~bb ~b]] 2]]
                   [~msign 2  [:b [~msign ~aa ~a]] [:b [~msign ~bb ~b]]]]]]])


      #_(let [a 'a
            b 'b
            aa 4
            bb 6
              sign '+
            hidden false
            ]
        [m7/mx `[=
                 ~(if hidden '_
                      `[:p
                        [:b [+ [:m ~aa ~a] [:m ~bb ~b] ]] 2])
                 [+ [+ [:p [:b [:m ~aa  ~a]] 2]
                     [:p [:b [:m ~bb ~b]] 2]]
                  [* 2  [:m ~aa ~a] [:m ~bb ~b]]]]])

      #_(let [a 'a
            b 'b
            aa 4
            bb 6
            sign '+
            hidden false]
        [m7/mx `[=
                 ~(if  hidden '_
                      `[:p

                       [:b [~sign [:m ~aa ~a] [:m ~bb ~b] ]] 2])
                 [~sign [+ [:m ~(* aa aa)  [:p ~a 2]]
                     [:m ~(* bb bb)  [:p ~b 2]]

                     ] [:m  ~(* 2 aa bb) [:m ~a ~b]]]]])
      #_[m7/m '[= [2 5] [[* 2 2] [* 5 2]] [4 10] ]]

      #_[m7/mx `[= [:m [:b [+ [:m 4 a] [:m 9 b]]]  [:b [- [:m 4 a] [:m 9 b]]]]
               [- [:p [:b [:m 4 a]] 2]  [:p [:b [:m 9 b]] 2]]]]

      #_[m7/m '[= [:p [:b [- a b]] 2]
              [- [+ [:p a 2] [:p b 2]]
               [:m 2 a b]]]]


      #_[m7/m '[= [:p [:b [+ a b]] 2]
              [+ [+ [:p a 2] [:p b 2]]
               [:m 2 a b]]]]




      ]



     ]))



(defn banner-factor-identities2 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ

        lin2 (fn [x y]
               (fn [[a  bb  c]]
                 (let [[b minus]
                       (if (number? bb)
                         [(js/Math.abs bb) (if (> bb 0) false true )]
                         [bb (if (= (first bb) '-)
                               true false)] )]
                   (if minus
                     (m7/mx
                      `[= [- [:m ~a ~x]
                           [:m ~b ~y]] ~c])
                     (m7/mx
                      `[= [+ [:m ~a ~x]
                           [:m ~b ~y]] ~c])))))

        lin (lin2 'x 'y)


        qd (fn [[a  bb  c]]
              (let [[b minus]
                    (if (number? bb)
                      [(js/Math.abs bb) (if (> bb 0) false true )]
                      [bb (if (= (first bb) '-)
                            true false)] )]
                (if minus
                  (m7/mx
                   `[+ [- [:m ~a [:p x 2]]
                        [:m ~b x]] ~c])
                  (m7/mx
                   `[+ [+ [:m ~a [:p x 2]]
                        [:m ~b x]] ~c]))))

        sq (fn [n]
                (comp
                 (partial map (partial * n))))
        hidden false
        [ax ay av sma] [1 1 (not (not true)) 1]
        [bx by bv smb] [10 10 (not (not true)) 2]
        ]
    [:div {:style (merge
                   (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 20 (repeat [8 :vh]))])
                   {:background-color (hsl [1 70 70 1])
                    :gap ".1rem"})}

     [:div {:style (m7/css
                    [[1 12 10 13
                      :center :center 2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .1]
                     [] {:gap ".1rem"
                         :z-index 4}])}
      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       (map
                        #(* 10 %)
                        [-30 -30  70 70]))}


       #_(if bv
         (map
          (fn [y111 y11]
            (let [y1 (* y11 (/ 40 smb))]
              (map               (fn [x1 y]
                 (let [x (* x1 (/ 40 smb))]
                   [:g

                    [:path {:d (m7/path `[ ~x ~y1 :l ~@(map #(* (/ 40 smb) %) dx)])
                            :fill (hsl  [(if (not y) 1.5 2.3) 90 60 .8])
                            :stroke :#aaa
                            :stroke-width 2
                            }]
                    [:text {:x x
                            :y y1}
                     #_(+ 1 x1)
                     ]]))
               (range 0 (* smb bx))
               (cycle [bv bv])
               )))
          (range 0 (* smb by))
          (range 0 (* smb by))))





       #_[:g {:transform (m7/tranfrom [[:translate [(ve (* 40 ax))
                                                    (ve (* 40 ay)) ]]
                                       [:scale [1 1]]])}
          (map
           (fn [y111 y11]
             (let [y1 (* y11 40)]
               (map
                (fn [x1 y]
                  (let [x (* x1 40)]
                    [:g

                     [:path {:d (m7/path `[ ~x ~y1 :l ~@(map #(* 40 %) dx)])
                             :fill (hsl  [(if (not y) .1 .8) 90 60 .5])
                             :stroke :#aaa
                             :stroke-width 2
                             }]
                     [:text {:x x
                             :y y1}
                      #_(+ 1 x1)
                      ]]))
                (range 0 (+ ax bx))
                (cycle [true true])
                )))
           (range 0 (+ ay by))
           (range 0 (+ ay by)))]
       [:g {:transform (m7/tranfrom [[:translate [-100 -100]]
                                     [:scale [1 1]]])}
        (map
         (fn [y111 y11]
           (let [y1 (* y11 40)]
             (map
              (fn [x1 y]
                (let [x (* x1 40)]
                  [:g

                   [:path {:d (m7/path `[ ~x ~y1 :l ~@(map #(* 40 %) dx)])
                           :fill (hsl  [.8 90 60 .5])
                           :stroke :#aaa
                           :stroke-width 1
                           }]
                   [:text {:x x
                           :y y1}
                    #_(+ 1 x1)
                    ]]))
              (range 0 10)
              (cycle [true true])
              )))
         (range 0 10)
         (range 0 10))]



       [:g {:transform (m7/tranfrom [[:translate [-100 -100]]
                                     [:scale [1 1]]])}
        (map
         (fn [y111 y11]
           (let [y1 (* y11 40)]
             (map
              (fn [x1 y]
                (let [x (* x1 40)]
                  [:g

                   [:path {:d (m7/path `[ ~x ~y1 :l ~@(map #(* 40 %) dx)])
                           :fill (hsl  [1.8 90 60 .2])
                           :stroke :#aaa
                           :stroke-width 1
                           }]
                   [:text {:x x
                           :y y1}
                    #_(+ 1 x1)
                    ]]))
              (range 0 1)
              (cycle [true true])
              )))
         (range 0 10)
         (range 0 10))]



       [:g {:transform (m7/tranfrom [[:translate [-100 -100]]
                                     [:scale [1 1]]])}
        (map
         (fn [y111 y11]
           (let [y1 (* y11 4)]
             (map
              (fn [x1 y]
                (let [x (* x1 4)]
                  [:g

                   [:path {:d (m7/path `[ ~x ~y1 :l ~@(map #(* 4 %) dx)])
                           :fill (hsl  [.1 90 60 .5])
                           :stroke :#aaa
                           :stroke-width .1
                           }]
                   [:text {:x x
                           :y y1}
                    #_(+ 1 x1)
                    ]]))
              (range 0 10)
              (cycle [true true])
              )))
         (range 0 10)
         (range 0 10))]






       #_(if av
         [:g  {:transform (m7/tranfrom [[:scale [-1 -1]]
                                        [:translate [0 0]]])}
          (map
           (fn [y111 y11]
             (let [y1 (* y11 40)]
               (map
                (fn [x1 y]
                  (let [x (* x1 40)]
                    [:g

                     [:path {:d (m7/path `[ ~x ~y1 :l ~@(map #(* 40 %) dx)])
                             :fill (hsl  [(if (not y) 3 3.5) 90 60 1])
                             :stroke :#aaa
                             :stroke-width 2
                             }]
                     [:text {:x x
                             :y y1}
                      #_(+ 1 x1)
                      ]]))
                (range 0 ax)
                (cycle [true true])
                )))
           (range 0 ay)
           (range 0 ay))])

       #_(if av
           [:g  {:transform (m7/tranfrom [[:scale [-1 -1]]
                                          [:translate [0 0]]])}
            (map
             (fn [y111 y11]
               (let [y1 (* y11 40)]
                 (map
                  (fn [x1 y]
                    (let [x (* x1 40)]
                      [:g

                       [:path {:d (m7/path `[ ~x ~y1 :l ~@(map #(* 40 %) dx)])
                               :fill (hsl  [(if (not y) 3 3.5) 90 60 1])
                               :stroke :#aaa
                               :stroke-width 2
                               }]
                       [:text {:x x
                               :y y1}
                        #_(+ 1 x1)
                        ]]))
                  (range 0 ax)
                  (cycle [true true])
                  )))
             (range 0 ay)
             (range 0 ay))])]]


     [:div {:style
            (m7/css
             [[2 3 15 3 :center :center  3.0 :rem :column]
              [(* 5 .2) 70 (+ 50 (* 5 5))  .1]
              []
              {:gap ".1rem"
               :z-index 4}])}



      #_[m7/m '[= area-blue [* [ 3 10] [3 10]] [9 100]]]


      #_[m7/m '[> [3 10] [9 100]]]


      #_[m7/m '[> [3 10] [9 10]]]


      #_[m7/m '[> [:k d t] [:b [= [:p [:k d t] 2] 0]]]]

      #_[m7/m '[> [:k d t] 0]]

      #_[m7/m '[> [:k d t] 0]]



      ]


     [:div {:style
            (m7/css
             [[2 10 (+ 2 (* 0 2)) 12  :center :center  2.3 :rem :column]
              [(* 5 .2) 70 (+ 50 (* 5 5))  .1]
              []
              {:gap ".1rem"
               :z-index 4}])}

      #_[:div {:style {:font-size "1.5rem"}} "make x the subject of the formla"]
      #_[m7/x '[= y [[- 2 x]
                   [+ 3 [:m 2 x]]]]]

      #_[m7/x `[= x 4]]

      [:div (js/String.fromCodePoint 0x1F9A0)]



      [m7/x '[=
              .025
              [25 1000]
              [[* 5 5] [* 10 100]]
              [[* 5 5] [* 10 5 5]]
              [1 10]
              ]

       ]

      #_[m7/x '[= [1 4]
              [[* 1  25] [* 4 25]]]]

      #_[m7/x '[= x
              [[- 2 [:m 3 y]]
               [+  [:m 2  y] 1]]]]


      #_[m7/x `[- [+ [- [:m a x] [:m 5 a]]
                 [:m 4 x]]
              20]
       ]


      #_[m7/x `[+ [:m a [:b [- x 5]]]
              [:m 4 [:b [- x 5]]]]
       ]


      #_[m7/x `[:m [:b [+ a 4]]   [:b [- x 5]] ]
       ]

      #_[m7/x '[+ [:m 4 [:p x 2]] [:m 20 x] 25]]


      #_[m7/x '[+ [:p [:b [:m 2 x]] 2] [:m [* 2 [:m 2 x]] 5] [:p 5 2]]]


      #_[m7/x '[:p  [:b [+ [:m 2 x] 5]] 2]]


      ]

     #_[:div {:style
              (m7/css
               [[2 10 (+ 2 (* 0 2)) 12  :center :center  2.3 :rem :column]
                [(* 5 .2) 70 (+ 50 (* 5 5))  .1]
                []
                {:gap ".1rem"
                 :z-index 4}])}




      [m7/sx '[= s  [:m f [:b  t]] [+ [:m [1 2] [:b [- g]] [:p t 2]] [:m  u t]  0]] 't 't ]


      [:div "let, "
       [m7/mx `[= [:p [:k d t] 2] 0]]]

      #_[m7/m '[:a t [:b [+ t 2]]]]


      #_[m7/m '[= [:m f [:b [+ t 2]]] [+ [:m b [:b [+ t 2]]]  [:m a [:p [:b [+ t 2]] 2]] ]]]

      #_[m7/m '[= [:m f [:b [+ t k]]] [+ [:m b [:b [+ t k]]]  [:m a [:p [:b [+ t k]] 2]] ]]]



      #_[m7/m '[= [:m f [:b [+ t 2]]] [+ [:m b t] [:m 2 b] [+ [:m a [:p t 2]]
                                                          [:m 2  a t 2]
                                                          [:m  [:p 2 2] a]] ]]]


      #_[m7/m '[= [:m f [:b [+ t k]]] [+ [:m b t] [:m k b] [+ [:m a [:p t 2]]
                                                          [:m 2  a t k]
                                                          [:m  [:p k 2] a]] ]]]



      #_[m7/m '[:a t [:b [+ t dt]]]]

      #_[m7/m '[= [:m f [:b [+ t dt]]]
              [+ [:m b t] [:m dt b] [+ [:m a [:p t 2]]
                                     [:m 2  a t dt]
                                     [:m  [:p dt 2] a]] ]]]


      #_[m7/m '[= [:p dt 2]  0]]
      #_[m7/sx '[= [:m f [:b [+ t dt]]]
               [+ [:m u t]  [:m a [:p t 2]]   [:m dt [:b [+ u [:m 2  a t ]]]]]]
       'a '[:m [1 2] [:b [- g]]]]

      [m7/sx '[= [:m f [:b [+ t [:k d t]]]]
               [+ [:m f [:b t]]   [:m [:k d t] [:m g [:b t]]]]]
       'a '[:b [- g]]]


      #_[:div {:style {:background-color (hsl [.6 95 70 1])}} "if we substitute " [m7/mx `[:a x [+ x [:k d x]]]]]
      #_[m7/sx '[= [:m f [:b [+ t [:k d t]]]]
               [+ [:m f [:b t]] [:m [:k d t] g [:b t]]]] 't 'x]


      [:div "where " [m7/mx `[= [:m g [:b t]] [- u [:m g t]]]]]



      [m7/sx `[= v [:m g [:b x]] [- u [:m g x ]]]
       'x 't]


      #_[m7/x `[= [:m g [:b [+ t [:k d t]] ]] [+ [- u [:m g t ]] [:m [:k d t] [:b  [- g]]]]]
       ]

      [m7/sx `[= [:m g [:b [+ x [:k d x]] ]] [+ [:m g [:b x]] [:m [:k d x] [:b [- g]]]]]
       'x 't]


      #_[m7/mx `[= [:k d v] [:m [:k d t] [:b [- g]]] ]]

      #_[m7/mx `[= [[:k d v] [:k d t]] [- g]]]

      #_[m7/mx `[= [:k d s] [:m [:k d t] [:b [- u [:m g t]]]] ]]

      #_[m7/mx `[= [[:k d s] [:k d t]] v [- u [:m g t]]]]





      #_(m7/sx `[= [:m g t]  [+ b [:m 2 a t]]] 't '[:b [+ t 3]])










      #_[m7/m '[= [:m f [:b [+ t dt]]]
              [+ [:m f t]   [:m dt [:b [+ b [:m 2  a t ]]]]]]]


      #_[m7/m '[= [:m f [:b [+ t dt]]]
              [+ [:m f t]   [:m dt  df]]]]




        #_[m7/m '[= [- [:p a 2] [:p b 2]]
                [:m [:b [+ a 1]]
                 [:b [- a 1]]]]]


      #_(if true
        [:div {:style {:background-color (hsl [1 50 50 .7])}}
         [m7/mx `[= [:p [:b [+ ~ax ~bx]]  2]
                  [+ [:p ~ax  2]  [:p ~bx  2] [* 2 ~ax ~bx ]]]]])



        #_[m7/m '[= [- [:p a 2] [:p b 2]]
                [:m [:b [+ a b]] [:b [- a b]]]]]
      #_[m7/m '[=  [:p [:b [- 8 3]] 2]  [+ [:p 8 2] [:p [:b [- 3]] 2]
                                       [* 2 [- 3] 8]]]]







      #_(let [a sma
            b (ve smb)
            aa ax
            bb bx
            hidden false
            sign '+
            msign '*
            ]
        [:div {:style {:font-size "1.8rem"}}
         [m7/mx `[=
                  ~(if hidden '_
                       `~(* (+ (* aa a)  (* bb b))  (+ (* aa a)  (* bb b))))
                  [~sign [~sign ~(* (* aa  a) (* aa  a))

                          ~(* (* bb  b) (* bb  b))
                          ]
                   ~(* 2 aa  a bb b)
                   ]]]])


      #_(if hidden
          (let [a sma
                b (ve smb)
                aa ax
                bb bx
                hidden false
                sign '+
                msign '*
                ]
            [:div {:style {:font-size "1.8rem"}}
             [m7/mx `[=
                      ~(if hidden '_
                           `[:p
                             [:b [~sign [~msign ~aa ~a] [~msign ~bb ~b] ]] 2])
                      [~sign [~sign [:p [:b [~msign ~aa  ~a]] 2]
                              [:p [:b [~msign ~bb ~b]] 2]]
                       [~msign 2  [:b [~msign ~aa ~a]] [:b [~msign ~bb ~b]]]]]]]
            ))

      (if hidden
        (let [a 'x
              b 'y
              ax 1
              bx 3
              aa ax
              bb bx
              sign '+
              hidden false
              msign :m]
          [m7/mx `[=
                   ~(if hidden '_
                        `[:p
                          [:b [~sign ~(if (= ax 1)
                                        a
                                        `[~msign ~aa ~a])
                               [~msign ~bb ~b] ]] 2])
                   [~sign [+ [:p ~(if (= ax 1)
                                    a
                                    [:b [~msign ~aa  ~a]]) 2]
                           [:p [:b [~msign ~bb ~b]] 2]]
                    [~msign 2  [:b [~msign ~aa ~a]
                                ] [:b [~msign ~bb ~b]]]]]]))



      #_[m7/m '[= 650 [+ [:p x 2]  [:p y 2]]]]


      #_[m7/m '[= [:m 2 xy] [* 2 323]]]



      ;; [m7/m '[= [:p [:b [+ x y]] 2] [+ [:p x 2] [:m 2 xy]  [:p y 2]]]]


      ;; [m7/mx `[= [:p [:b [+ x y]] 2] ~(+ 650 646) ]]

      ;; [m7/mx `[= [+ x y] [:sq ~(+ 650 646)] ]]

      ;; [m7/mx `[= [+ x y] [- [:sq ~(+ 650 646)]] ]]

      #_[m7/mx `[= y  [:b [- 36 x]]]]
      #_[m7/mx `[= 323  [:m x [:b [- 36 x]]] ]]

      #_[m7/mx `[= [+ [- [:p x 2] [:m 36 x] ] 323] 0 ]]

      #_[m7/mx `[= x  [+ 18 [:sq [- [:p 18 2] 323]]]] ]
      #_(js/Math.sqrt (- (* 18 18) 323))
      #_[m7/mx '[+ 18 ]]
      #_(if true
        (let [a 'a
              b 'b
              aa ax
              bb bx
              hidden false
              sign '-
              msign :m
              ]
          [m7/mx `[~sign [+ [:m ~(* aa aa)  [:p ~a 2]]
                          [:p [:b [~msign ~bb ~b]] 2]]
                   [~msign
                    ~(* 2  aa bb) ~a ~b]]]))


      #_(let [a sma
            b (ve smb)
            aa ax
            bb bx
            hidden false
            sign '+
            msign '*
            ]
        [:div {:style {:font-size "1.8rem"}}
         [m7/mx `[=
                  ~(if hidden '_
                       `[:p
                         [:b [~sign [~msign ~aa ~a] [~msign ~bb ~b] ]] 2])
                  [~sign [~sign [:p [:b [~msign ~aa  ~a]] 2]
                          [:p [:b [~msign ~bb ~b]] 2]]
                   [~msign 2  [:b [~msign ~aa ~a]] [:b [~msign ~bb ~b]]]]]]])


      #_(let [a 'a
            b 'b
            aa 4
            bb 6
              sign '+
            hidden false
            ]
        [m7/mx `[=
                 ~(if hidden '_
                      `[:p
                        [:b [+ [:m ~aa ~a] [:m ~bb ~b] ]] 2])
                 [+ [+ [:p [:b [:m ~aa  ~a]] 2]
                     [:p [:b [:m ~bb ~b]] 2]]
                  [* 2  [:m ~aa ~a] [:m ~bb ~b]]]]])

      #_(let [a 'a
            b 'b
            aa 4
            bb 6
            sign '+
            hidden false]
        [m7/mx `[=
                 ~(if  hidden '_
                      `[:p

                       [:b [~sign [:m ~aa ~a] [:m ~bb ~b] ]] 2])
                 [~sign [+ [:m ~(* aa aa)  [:p ~a 2]]
                     [:m ~(* bb bb)  [:p ~b 2]]

                     ] [:m  ~(* 2 aa bb) [:m ~a ~b]]]]])
      #_[m7/m '[= [2 5] [[* 2 2] [* 5 2]] [4 10] ]]

      #_[m7/mx `[= [:m [:b [+ [:m 4 a] [:m 9 b]]]  [:b [- [:m 4 a] [:m 9 b]]]]
               [- [:p [:b [:m 4 a]] 2]  [:p [:b [:m 9 b]] 2]]]]

      #_[m7/m '[= [:p [:b [- a b]] 2]
              [- [+ [:p a 2] [:p b 2]]
               [:m 2 a b]]]]


      #_[m7/m '[= [:p [:b [+ a b]] 2]
              [+ [+ [:p a 2] [:p b 2]]
               [:m 2 a b]]]]




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

      #_[:div  {:style {:font-size "1.5rem"}}  " At high temperatures (700–1100 °C) and in the presence of a metal-based catalyst (nickel), steam reacts with methane to yield a mixture of CO and H2, known as \"water gas\" or \"syngas\":  "]



      #_[:div  {:style {:font-size "4.5rem"
                      :padding "10.4rem"}}  " At high temperatures (700–1100 °C)  in the presence  of a metal-named catalyst"

       [:span {:style {:background-color (hsl [1 80 50 1])}} " nickel, "]


       [:span {:style {:background-color (hsl [2 80 50 1])}} " stream "]
       [:span "reacts with  "]
       [:span {:style {:background-color (hsl [1.3 80 50 1])}} " ethane "]

       [:sapn  "to yield a mixture of"]
       [:span {:style {:background-color (hsl [1.3 80 50 1])}} " ethanol "]
       ]



      #_[m7/m '[= [+ [:m [:k C 2] [:k H 4]]
                 [:m [:k H 2] O]]
                [:m [:k C 2] [:k H 5] OH]]]


      [m7/m '[= [:m [:k C 6] [:k H 12] [:k O 6]] [+ [:m 2 [:k C 2] [:k H 5] OH]
                                                  [:m 2 C [:k O 2]]]]]















      #_[:div  {:style {:font-size "1.5rem"}}
       " This reaction is strongly endothermic (consumes heat, ΔHr = 206 kJ/mol). Additional hydrogen is obtained by the reaction of CO with water via the water-gas shift reaction "]



      #_[m7/m '[= [+  CO [:m [:k H 2] O]]
              [+ [:k H 2] [:m C [:k O 2]]]]]















      #_[m7/m '[= [+ [:m [:p H +] [:p CL -]] [:m [:p Na +] [:p OH -]]]

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



;

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
          poly false
          vb (fn [z]
               (nth [
                     [600 -150  300 800]
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
                      [[3 10 1 15 :center :center 2 :rem]
                       [1 70 90 .1] [] {:gap "1rem"
                                        :z-index 5}])}
        ""]


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

           #_(grid-on)





           [:g {:transform (m7/tranfrom [[:scale [.7 .7]]
                                         [:rotate 10]])}



           (let [n 2]
              [:g

               [:foreignObject {:x 20
                                :y -150
                                :height 50
                                :width 450
                                :style {:font-size "1.7rem"}}
                (if poly
                  [m7/m [:k [:b [:m '- [:k 'C n] [:k 'H ['* 2 n]] '- ]]   'n]]
                  [m7/m [:m [:k 'C n] [:k 'H ['* 2 n]]]])

                #_[m7/m ['= ['+ [:m [:k 'C n] [:k 'H  (+ (* 2 n) 2)]] [:m 2[:k 'O 2]] ]
                       ['+ [:m 'C [:k 'O 2]] [:m 2 [:k 'H 2] 'O]]
                       ]]
                ]

               [:text {:x 250
                       :y -220
                       :style {:font-size "2rem"}}
                (nth ["methane" (if poly "polyEthylene"  "Ethylene") "propane" "butane" "pentane" "hexane" "heptane" "octane"]
                     (- n 1))]


               (map
                (fn [x]
                  [:g {:transform (m7/tranfrom [[:translate [(* x 150) 40]]])}
                   (map
                    (fn [i j]
                      [:g
                       (if (not (= i 0))
                         [:g

                          [:path {:d (path
                                      [(-  (* i 75) 60 ) -10 :l  40 0])
                                  :stroke (hsl [4 70 70 1])
                                  :stroke-width 2
                                  :fill (hsl [5 70 70 .2])}]

                          (if (not poly)
                            [:path {:d (path [(-  (* i 75) 60 ) 0 :l  40 0])
                                      :stroke (hsl [4 70 70 1])
                                      :stroke-width 2
                                      :fill (hsl [5 70 70 .2])}])

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
                   (if poly
                       [:path {:d (path
                                   [ 0 0  :l  -100 0 ])
                               :marker-end (m7/url (name :dot))
                               :stroke (hsl [5 70 70 1])
                               :stroke-width 2
                               :fill :none}
                        ])
                   ])
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
          ts (into [[m7/m '[:m V [:p cm 3]]]] [0 0 1 2 3 4 18 49 87 88 89 99 100 102 102])
          vms (into ["T (c)"] [ 0 0])
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
           :stroke (hsl [0 70 70 .5])
           :stroke-width 2
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
           :stroke (hsl [2 70 70 1])
           :stroke-width 1
           :fill :none}
    ]

   [:text {
           :style {:font-size "0.8rem"}
           }
    [:textPath {:href :#pp2
                :startOffset 3000}

     eqs
     ]
    ]])


(defn home-work19 []
  (let [[slider set-slider] (react/useState false)
        [pos set-pos] (react/useState false)
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
                       :transform (m7/tranfrom [[:rotate "-10deg"]])}

                      {:background (hsl [2 70 70 .9])
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
    (let [ax-dx (* -2 20)
          ax-dy    (* 20 0)
          m          -2
          d          4
          vb
          (fn [z]
            (nth [[-250 -450  600 600]
                  [0 -25  50 50]
                  (map #(* 4 %) [0 -25  50 50])
                  (map #(* 2 %) [-25 -25  50 50])
                  (map #(* 3 %) [-20 -45  50 50])
                  (map #(* 1 %) [-20 -15  50 50])
                  (map #(* 4 %) [-30 -75  50 50])
                  (map #(* 4 %) [-30 -75  50 50])
                  (map #(* 3 %) [0 -25  50 50])
                  (map #(* 2 %) [0 -45  50 50])
                  (map #(* 3 %) [0 -45  50 50])
                  (map #(* 4 %) [-20 -45  50 50])
                  (map #(* 7 %) [-20 -45  50 50])
                  (map #(* 7 %) [0 -45  50 50])
                  (map #(* 1 %) [100 -25  50 50])

                  [-200 -200  800 200]
                  [-400 -200  800 200]
                  [40 120  80 80]
                  [0 -40  100 100]
                  [75 -175  150 150]
                  [-20 -20  100 100]
                  [-400 -350  800 400]
                  [-250 -120  800 400]
                  ] z))
          fix false
          viewbox  (vb 5)
          viewbox2 (vb 5)
          rn (range -6 7)
          scales [1 -1]
          dfl (if true
                [:div {:style
                       (m7/css
                          [[2 2 16 5
                            :center :center  3 :rem :column]
                           [3.5 70 75 .5]
                           []
                           {:gap     ".1rem"
                            :z-index 10}])}
                 [m7/mx `[= 0 [- [:p t 2] [:p ~d 2]]]]



                 #_[m7/m '[= y [:p 8 2] ]]

                   #_[m7/m '[= y [- [:p x 2] [:p d 2]]]]


                   #_[m7/m '[= 0 [:m [:b (- x 2)] [:b  (+ x 2)]]]]

                   #_[m7/m '[= y [- [:p x 2] [:p 4 2]]]]

                   #_[m7/m '[= y [- [:p x 2] [:p 1 2]]]]

                   #_[m7/m '[= y [- [:p [:b [- x 2]] 2] [:p 5 2]]]]
                   #_[m7/m '[= 0 [- [:p [:b [- x 2]] 2] [:p 5 2]]]]
                   #_[m7/m '[= 0 [:m [:b [- x 2 5]] [:b [+ [- x 2] 5]]]]]


                   #_[m7/m '[= y [:p 7 2]]]
                   #_[m7/m '[= x
                             [-  d]]]])


          eqs (fn [x]
                (if fix
                  (* 1 (- (* (- x m) (- x m)) (* d d)))
                  (.toFixed (* 1 (- (* (- x m) (- x m)) (* d d))) 1)))
          eqs2 (fn [x]
                 (ve (* 1 (- (* 2 (* (- x m) (- x m)))
                             (* 2 d d)))))

          extra [:g

                 #_[line3 ]


                 #_[:circle {:cx (* 8 20)
                           :cy 0
                           :fill (hsl [.5 90 60 1] )
                           :r 1}
                  [:animate {:attributeName :r
                             :from 1.7
                             :to 2
                             :dur (m7/not-space
                                   [3 "s"])
                             :repeatCount :indefinite}]
                  ]

                 #_(ve (+ (* 2 4) (* 20 6)))
                 [:circle {:on-mouse-over
                           (fn [e]
                             (set-pos (not pos)))
                           :cx (* 0 20)
                           :cy (ve (+ (* 2 -6) (* 20 -1)))
                           :fill (hsl [(if pos 0 .8) 90 60 .8] )
                           :r 2}
                  [:animate {:attributeName :r
                             :from 1
                             :to 2
                             :dur (m7/np [3 :s])
                             :repeatCount :indefinite}]
                  ]







                 #_[:circle {:cx 0
                             :cy (* 2 d d)
                             :fill (hsl [5 60 60 .5] )
                             :r 2}
                    [:animate {:attributeName :r
                               :from 0
                               :to 3
                               :dur (m7/not-space [3 "s"])
                               :repeatCount :indefinite}]
                    ]

                 #_(map
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



                 #_[:g#sym
                  (map
                   (fn [sc]
                     [:path {:d (m7/path [0 0 :l  (ve (* 20 7)) 0
                                          0 (ve (- (+ (* 9 2) (* 4 20)) 0))]
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
                   [[1 1] [-1 1]])]


                 #_(map
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
                  [[1 1] ])


                 ]

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
        (into ["t"] rn))

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
                (name :y)
                ]]
              (map (fn [x]
                     [:div {:style {:font-size "1.2rem"}}
                      (eqs x)])
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

          #_[m7/m '[+ [:m 2 [:p x 2]] 1]]

          #_[m7/m ['= ['- ['+ [:p 'x 2] ['- [:m 2 'm 'x]] [:p 'm 2]] [:p 'd 2]]  0]]


          #_[m7/m ['= ['+ [:p 'x 2] [:m (* 2 m) 'x] (- (* m m) (* d d)) ]  0]]

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
            [:path {:d
                    (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke       (hsl [5 70 70 1])
                    :stroke-width .2
                    :transform    (m7/tranfrom [[:rotate 0]
                                                [:scale [.8 .8]]])
                    :fill         (m7/hsl [-0.5 70 70 1])}]]

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




           (map (fn [x y]
                  [:circle {:on-click (fn [_]
                                        (set-point true))
                            :cx    x
                            :cy    y
                            :r     (if point .3 .5)
                            :fill (if point
                                    (hsl [0 70 70 .8])
                                    (hsl [2 70 70 .8]))
                            :style {:font-size ".6rem"}}]
                  )

                (map (fn [x]
                       (* 20 x)) (range -10  20 .25))
                (map eqs2 (range -10 20 .25)))



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
                       :stroke-width .3
                       :fill (m7/url (name :lgg1))
                       }
                ]


               [:g
                [:path {:d (m7/path [0 0 :l
                                     0 (+ 0  (* 2 (* d d)))])
                        :fill :none
                        :stroke-width .5
                        :transform
                        (m7/tranfrom
                         [
                          [:translate [(* m 20)
                                       0]]
                          [:scale [x 1]]
                          ])
                        :id :ddd2
                        :marker-end (m7/url (name :dot))
                        :stroke (hsl [3.5 70 70 1])}
                 ]

                [:text {:dy -5}
                 [:textPath {:href :#ddd2
                             :startOffset "20%"
                             :font-size d}
                  "d"
                  [:tspan {:dy (- d 1)
                           :font-size (/ d 2)}
                   "2"]
                  [:tspan {:dy (ve (- d 1))}
                   (str "= " (* d d))]

                  ]]]

               [:g
                [:path {:d            (path [0 0 :l (* 20 d) 0])
                        :transform    (m7/tranfrom
                                    [[:translate [(* 20 m) 0]]
                                     [:scale [x 1]]
                                     ])
                        :stroke       (hsl [.5 90 70 1])
                        :stroke-width .5
                        :id           (str "dd2" x)
                        :marker-end   (m7/url (name :dot))
                        :fill         :none}
                 ]
                [:text
                 [:textPath {:href        (str "#dd2" x)
                             :startOffset "20%"
                             :style       {
                                           :font-size ".2rem"}
                             } (str "d=" d)]
                 ]]])
            scales)

           #_[:g
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
                         :style       {:font-size ".4rem"}} (str "t=" m)]
             ]]






           #_(line2 0 "y=0")




           extra
           ])


        ]


       ])))


(defn chem-rate []
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
                       :transform (m7/tranfrom [[:rotate "-10deg"]])}

                      {:background (hsl [2 70 70 .9])
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
             (js/console.log "")))
        f                   (fn [n] (/ 1 n))
        tt                  'θ
        dx                  [1 0  0 1 -1  0 0 -1 ]
        sq                  (fn [n]
                              (comp
                               (partial map (partial * n))))
        [point set-point] (react/useState false)]
    (let [
          ax-dx 40
          ax-dy    (* 20 10)
          m          0
          d          0
          vb       (fn [z]
                     (nth [[-250 -450  600 600]
                           [0 -25  50 50]
                           [-25 -40  50 50]
                           [80 -20  50 40]
                           [100 -80  100 100]
                           [-400 -200  800 200]
                           [40 120  80 80]
                           [0 -40  100 100]
                           [75 -175  150 150]
                           [-20 -20  100 100]
                           [-400 -350  800 400]
                           [-250 -120  800 400]
                           ] z))
          fix false
          viewbox  (vb 4)
          viewbox2 (vb 4)
          rn (range 0 0)
          scales []
          dfl [:div {:style
                     (m7/css
                      [[3 6 3 20
                        :center :center  3.5 :rem :column]
                       [3.5 70 75 .5]
                       []
                       {:color (hsl [.5 80 45 1])
                        :gap (m7/np [1 :rem])
                        :z-index 10}])}
               [:div {:style {:font-size "1rem"
                              :width "50%"
                              :background-color (hsl [1 90 90 1])
                              :justify-content :space-between
                              :display :flex}}
                #_[:div "Calcium Carbonate"]
                #_[:div "Hydrochoric Acid"]]
               [:div "Redox Reaction"]
               [m7/m '[:a [- K
                           [:m 1 [:m e -]]]
                       [:p K +]]]

               [m7/m '[:a [ [:m  O H]
                           [:m 1 [:m e -]]]
                       [:p OH -]]]

               #_[:div {:style {:background-color (hsl [2 40 40 1])
                              :height "4px"
                              :width "100%"}}]

               #_[m7/mx `[:a [+ [:m 2 Fe] [:m 3 O]]

                        [:m [:p Fe [:m 3 +]]
                         [:p O [:m 2 -]]]]]

               #_[m7/mx `[:a [+ [:m 2 Fe] [:m 3 O]]

                        [:m [:k Fe 2]
                         [:k O 3]]]]
               ]
          dfl2 [:div {:style
                     (m7/css
                      [[3 6 3 20
                        :center :center  3.5 :rem :column]
                       [3.5 70 75 .5]
                       []
                       {:color (hsl [.5 80 45 1])
                        :gap (m7/np [1 :rem])
                        :z-index 10}])}
               [:div {:style {:font-size "1rem"
                              :width "50%"
                              :background-color (hsl [1 90 90 1])
                              :justify-content :space-between
                              :display :flex}}
                #_[:div "Calcium Carbonate"]
                #_[:div "Hydrochoric Acid"]]
               [:div "Redox Reaction"]
               [m7/m '[:a [- [:m 2 Fe]
                           [:m 6 [:m e -]]]
                       [:m 2 [:p Fe [:m 3 +]]]]]

               [m7/m '[:a [+ [:m 3 O]
                           [:m 6 [:m e -]]]
                       [:m 3 [:p O [:m 2 -]]]]]

               [:div {:style {:background-color (hsl [2 40 40 1])
                              :height "4px"
                              :width "100%"}}]

               [m7/mx `[:a [+ [:m 2 Fe] [:m 3 O]]

                        [:m [:p Fe [:m 3 +]]
                         [:p O [:m 2 -]]]]]

               [m7/mx `[:a [+ [:m 2 Fe] [:m 3 O]]

                        [:m [:k Fe 2]
                         [:k O 3]]]]
               ]




          extra [:g  ]

          ]
      [:div {
             :style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 24 (repeat [8 :vh]))])
                     {:background (hsl [1.5 70 70 .9])
                      :gap              ".1rem"})}


       [:div (m7/css
              [[4 6 3 9
                :center :center  2 :rem :column]
               [3.5 70 (+ 50 (* 5 5))  .7]
               []
               {:gap     ".1rem"
                :z-index 22}])

        ]




       (map
        (fn [n d]
          [:div
           {:style
            (m7/css
             [[2 1 (+ 2 (* n 1)) 1  :center :center  2 :rem :column]
              [(* n .2) 70 (+ 50 (* 1 n))  .7] []
              {:gap ".1rem"
               :z-index 4}])}

           d])
        (range 0 24)
        (into ["t"] rn)

        )

       (map
        (fn [n d]
          [:div
           {:style
            (m7/css
             [[3 1 (+ 2 (* n 1)) 1  :center :center  2 :rem :column]
              [(* n .2) 70 (+ 50 (* 1 n))  .7]
              []
              {:gap ".1rem"
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
                "V"
                ]]
              (map (fn [x]
                     [:div {:style {:font-size "1.2rem"}}
                      x])
                   rn))
        )



       #_"A group of students are studying the effect of surface area on rate on reaction. They use marble chips (calcium carbonate) reacting the dilute hydrochloric acid."

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

          "if i go, i will see her"

          ]

         dfl)
       #_"What did the students investigating the reaction have to do to make it a fair test?"
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

          [:div
           (nth ["if "]
                0)


           ]


            ]
         [:div ])


       [:div {:style (m7/css
                      [[2 10 1 24 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style   {:height "100%"
                           :width  "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [:animate {:attributeName :viewBox
                      :to            (m7/space viewbox2)
                      :dur           "4s"
                      :fill          :freeze}]



           #_(flames)

           #_(grid-on 1 10 ax-dx ax-dy)




           [:pattern {:id (name :starg)
                      :viewBox (space [0 0 10 10])
                      :width "9%"
                      :height "5%"}
            #_[:circle {:cx 5
                        :cy 5
                        :r 4
                        :fill (hsl [0 90 90 1])
                        }]
            [:filter#caco3
             [:feTurbulence {:type :turbulence
                             :baseFrequency 0.05
                             :numOctaves 1
                             :result "t"}
              ]
             [:feDisplacementMap {:in2 "t"
                                  :in "SourceGraphic"
                                  :scale 4
                                  :xChannelSelector "R"
                                  :yChannelSelector "G"}]

             ]




            #_[:path {:d (m7/path [0 0 :l
                                 10 0 0  10
                                   -10 0 0 -10])
                    :fill (hsl [0 90 90 1])
                    :filter (m7/url "caco3")
                    }]

            [:path {:d (m7/path [0 0 :l
                                 4.5 0 0  5
                                 -4.5 0 0 -5])
                    :fill (hsl [0 90 90 1])
                    :filter (m7/url "caco3")
                    }]

            [:path {:d (m7/path [0 5 :l
                                 4.5 0 0  5
                                 -4.5 0 0 -5])
                    :fill (hsl [0 90 90 1])
                    :filter (m7/url "caco3")
                    }]

            #_[:path {:d (m7/path [0 0 :l
                                 4.5 0 0  10
                                 -4.5 0 0 -10])
                    :fill (hsl [0 90 90 1])
                    :filter (m7/url "caco3")
                    }]


            #_[:path {:d (m7/path [5.5 0 :l
                                 4.5 0 0  10
                                 -4.5 0 0 -10])
                    :fill (hsl [0 90 90 1])
                    :filter (m7/url "caco3")
                    }]

            [:path {:d (m7/path [5.5 0 :l
                                 4.5 0 0  5
                                 -4.5 0 0 -5])
                    :fill (hsl [0 90 90 1])
                    :filter (m7/url "caco3")
                    }]

            [:path {:d (m7/path [5.5 5.5 :l
                                 4.5 0 0  5
                                 -4.5 0 0 -5])
                    :fill (hsl [0 90 90 1])
                    :filter (m7/url "caco3")
                    }]


            ]


           [:pattern {:id (name :starc2)
                      :viewBox (space [0 0 10 10])
                      :width "20%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 2
                      :stroke (hsl [0 20 90 .7])
                      :stroke-width 1
                      :fill :none}
             [:animate {
                        :attributeName :r
                        :begin (sec 0)
                        :dur (sec 4)
                        :type :scale
                        :from 2
                        :to 5
                        :repeatCount :indefinite
                        :fill :freeze}]]]



           [:g {:transform (m7/tranfrom [[:translate [100 20]]])}

            [:g
             [:path#bikker {:d (m7/path [0 0 :l
                                         80 0 0 -20  -80 0 0 20 ])
                            :fill (hsl [3 70 70 .51])
                            :transform (m7/tranfrom [[:translate [50 -20]]])
                            :stroke-dasharray (m7/space [100 80])

                            :stroke (hsl [0 70 70 1])
                            :stroke-width 1}]

             [:path {:d (m7/path [0 0 :l
                                  80 0 0 -20  -80 0 0 20 ])
                     :fill :none
                     :transform (m7/tranfrom [[:translate [50 -20]]])
                     :stroke-dasharray (m7/space [100 80])
                     :stroke (hsl [0 70 70 1])
                     :stroke-width 1}]]


            [:g#tube
             [:path
              {:d (m7/path [-5 -5 :l
                            0 -40  10 0 0 40 ])
               :fill (m7/url (name :starc2))
               :transform (m7/tranfrom [[:translate [105 -30]]])
               :stroke-dasharray (m7/space [100 80])

               :stroke (hsl [0 70 70 1])
               :stroke-width .5}]


             [:path
              {:d (m7/path [-5 -5 :l
                            0 -40  10 0 0 40 ])
               :fill (hsl [3 70 70 .51])
               :transform (m7/tranfrom
                           [[:translate [105 -30]]
                            [:scale [1 .1]]])
               :stroke-dasharray (m7/space [100 80])

               :stroke (hsl [0 70 70 1])
               :stroke-width .001}]]



            #_[:linearGradient#pipeg
             [:stop {:offset "0%" :stop-color (hsl [0 70 70 1])}]
             [:stop {:offset "10%" :stop-color (hsl [0 70 90 1])}]]

            [:g
             [:path.pipe {:d (m7/path [0 -50 :l 0 -20 (* 5 20) (* 2 20) 5 0 0 -5])
                          :fill :none
                          :stroke-linejoin :round
                          :stroke (hsl [3 70 70 1])
                          :stroke-width 3}]
             [:path.pipe {:d (m7/path [0 -50 :l 0 -20 (* 5 20) (* 2 20) 5 0 0 -5])
                          :fill :none
                          :stroke-linejoin :round
                          :stroke (hsl [3 70 90 1])
                          :stroke-width 2}]
             ]


            [:g#flux-co2
             [:path {:d (m7/path [0 0 :l 50 0
                                  -30 (ve (* 4 20))
                                  0 (ve 40)
                                  -20 0
                                  ])
                     :transform (m7/tranfrom [[:scale [.5 .5]]])
                     :fill (m7/url (name :starc2))
                     :stroke (hsl [4 70 70 1])
                     :stroke-width 1}]





             [:path {:d (m7/path [0 0 :l 50 0
                                  -30 (ve (* 4 20))
                                  0 (ve 40)
                                  -20 0
                                  ])
                     :transform (m7/tranfrom [[:scale [-0.5 .5]]])
                     :fill (m7/url (name :starc2))
                     :stroke (hsl [4 70 70 1])
                     :stroke-width 1}]]


            [:g#flux
             [:path {:d (m7/path [0 0 :l 50 0
                                  -30 (ve (* 4 20))
                                  0 (ve 40)
                                  -20 0
                                  ])
                     :transform (m7/tranfrom [[:scale [.5 .5]]])
                     :fill :none
                     :stroke (hsl [4 70 70 1])
                     :stroke-width 1}]



             [:path {:d (m7/path [0 0 :l 50 0
                                  -30 (ve (* 4 20))
                                  0 (ve 40)
                                  -20 0
                                  ])
                     :transform (m7/tranfrom [[:scale [-0.5 .5]]])
                     :fill :none
                     :stroke (hsl [4 70 70 1])
                     :stroke-width 1}]]

            ]

           [:clipPath {:id :ca}
            [:path
             {
              :d (m7/path [0 0 :l 50 0 0 -20 -50 0 0 20])
              :fill :none
              :stroke (hsl [3 70 70 1])
              :stroke-width 1}]]

           [:clipPath {:id :ca2}
            [:path
             {
              :d (m7/path [0 0 :l 50 0 0 -30 -50 0 0 30])
              :fill :none
              :stroke (hsl [3 70 70 1])
              :stroke-width 1}]]





           [:g#acid {:transform (m7/tranfrom [[:translate [100 20]]])}
              [:path {:d (m7/path [0 0 :l 50 0
                                 -30 (ve (* 4 20))
                                 0 (ve 40)
                                 -20 0
                                 ])
                    :clip-path (m7/url (name :ca2))
                    :transform (m7/tranfrom [[:scale [.5 .5]]])
                    :fill (hsl [2.5 70 70 1])
                    :stroke (hsl [3 70 70 .1])
                    :stroke-width 1}]



            [:path {:d (m7/path [0 0 :l 50 0
                                 -30 (ve (* 4 20))
                                 0 (ve 40)
                                 -20 0
                                 ])
                    :clip-path (m7/url (name :ca2))
                    :transform (m7/tranfrom [[:scale [-0.5 .5]]])
                    :fill (hsl [2.5 70 70 1])
                    :stroke (hsl [3 70 70 .1])
                    :stroke-width 1}]]

           #_[:path {:d (m7/path [0 0 :l 10 0 0 7 -7 0 0 -7])
                     :transform (m7/tranfrom [[:scale [3 3]]])
                     :fill (hsl [0 90 90 1])
                     }]
           [:g#surface
            (map
             (fn [s]
               [:path {:d (m7/path [0 0 :l 10 0 0 7 -7 0 0 -7])
                       :transform (m7/tranfrom [[:translate [(* s 12) 0]]
                                                [:scale [1 3]]])
                       :fill (hsl [0 90 90 1])
                       }])
             (range 0 4))]


           [:g {:transform (m7/tranfrom [[:translate [100 20]]])
                }
            [:path {:d (m7/path [0 0 :l 50 0
                                 -30 (ve (* 4 20))
                                 0 (ve 40)
                                 -20 0
                                 ])
                    :clip-path (m7/url (name :ca))
                    :transform (m7/tranfrom [[:scale [.5 .5]]])
                    :fill (m7/url (name :starg))
                    :stroke (hsl [3 70 70 1])
                    :stroke-width 1}]



            [:path {:d (m7/path [0 0 :l 50 0
                                 -30 (ve (* 4 20))
                                 0 (ve 40)
                                 -20 0
                                 ])
                    :clip-path (m7/url (name :ca))
                    :transform (m7/tranfrom [[:scale [-0.5 .5]]])
                    :fill (m7/url (name :starg))
                    :stroke (hsl [3 70 70 1])
                    :stroke-width 1}]]



           (line2 0 "")


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


          vb (fn [z]
               (nth [[100 -200  400 400]] z))


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
                 [[2 3 6 10
                   :center :center  1 :rem :column]
                  [3.5 70 (+ 50 (* 5 5))  .7] []
                  {:gap ".1rem"
                   :z-index 10}])}





        "M0,0l-38.63,66.92h-77.27l-38.63-66.92l38.63-66.92H331.51 M338.7,110.25h-91.65l-45.83,79.38   L247.05,269h91.65l45.83-79.37L338.7,110.25L338.7,110.25z"














        #_[:div "Things don't always go how you want them to"]

        #_[:div "This project ended up a lot massier I thought it whould be"]
        #_[:div "This is how I like the things to be"]
        #_[:div "to begin with"]


          ]



       #_[:div {:style
              (m7/css
               [[4 4 10 7
                 :center :center  2 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}

        [m7/m '[= q [:m m C [:b [- [:k T 2] [:k T 1]]]]]]


        [m7/m '[= [:k q 1] [* [:m 1000 g] [:m  4.18 [J g] C] [:m 80 C]]]]

        [m7/m '[= [:k H f] [:m 2221 [J g]]]]


        [m7/m '[=  [:k q 2] [:m m [:k H f]] [* 1000 [:m 2221 [J g]]]]]





       ]



       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [flames :frames-chem (hsl [0 60 70 1]) (hsl [1 76 70 1])]

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
                             :id (name :lg-chem1)
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
                        :dur (m7/np [3 :s])
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

           (grid-on 1 1)

           #_[:g
            {:transform (m7/tranfrom [[:scale [.2 .2]]
                                      [:translate [150 -50]]])}
            (alkine 3)]
           #_[:g
            {:transform (m7/tranfrom [[:translate [250  50]]])}
            (alkine 5)]

           ;;:filter (m7/url "flames")
           ;; Height
           #_[:path#height {:d (path [-60 80 :l 0 (ve 185)])
                   :marker-end (m7/url (name :dot))
                   :marker-start (m7/url (name :dot))
                   :stroke (hsl [4 70 70 1])
                   :stroke-width 2
                            :fill (m7/url (name :lg1))}]






           #_(let [pth
                 (path [-10 0 :a 40 40 0 true false  20 0 :l 0 -60  10 0 0 -4 -20 0])]
             [:g#first-jar {:transform (m7/tranfrom [[:scale 1]])}
              [:path {:d pth
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      :filter (m7/url (name :frames-chem))
                      :fill (m7/url (name :lg-chem1))}]

              [:path {:d pth
                      :transform (m7/tranfrom [[:scale [-1 1]]])
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      :filter (m7/url (name :frames-chem))
                      :fill (m7/url (name :lg-chem1))}
               ]])







           #_[:path.first-jar-head {:d (path [-18 -50
                               :l 40 0
                               0 -20 -40 0 0 20])

                     :stroke (hsl [0 70 70 1])
                     :stroke-width 1.5
                     :filter (m7/url "flames")
                     :fill (hsl [4 40 50 1])}
              ]


           ;; jar




           #_[:path {:d (path [-205 0
                             :l 0 (ve 190)
                             50 0 0 190
                             ])
                   ;; :filter (m7/url "flames")
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 2
                   :fill (m7/url (name :lg1))
                   }
            ]



           #_[:path {:d (path [-100 80
                             :l 0 (ve 190)
                             ])
                   :id :pp3
                   :marker-start (m7/url (name :dot2))
                   :marker-end (m7/url (name :dot2))
                   :stroke (hsl [3 70 70 1])
                   :stroke-width 2
                   :fill (hsl [4 40 50 1])}
            ]
           #_[:text
            [:textPath {:href :#pp3
                        :startOffset 40} "1m"]]



          #_[:g {:transform (m7/tranfrom [[:rotate 0]])}
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
             [:g#little-flask {:transform (m7/tranfrom [[:translate [400 25]]
                                           [:scale .7]])}
              [:path {:d pth
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      ;; :filter (m7/url "flames")
                      :fill (m7/url (name :lg-chem1))}]

              [:path {:d pth
                      :transform (m7/tranfrom [[:scale [-1 1]]])
                      :stroke (hsl [4 70 70 1])
                      :stroke-width 2
                      ;; :filter (m7/url "flames")
                      :fill (m7/url (name :lg-chem1))}
               ]])

           [:path {:d (path (into [0 0 :l]
                                  (map #(* 20 %) dx)))
                   :transform (m7/tranfrom [[:translate [(* 33 20) 0]]
                                            [:scale [2 4]]])
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 1
                   :fill (m7/url (name :lg-chem1))}
            ]

           #_[:text {:x 0
                   :y 0
                   :style {:font-size "1rem"}}
              "101 kN/m2"]
           ;; pipe
           (let [y 0
                 d 3.5]
             [:g#pipe2 {:transform (m7/tranfrom [[:translate [0 0]]
                                           [:rotate 0]])}


              [:path {:d (path [0 0
                                :l 0 (ve 120)  400 80 0 40 ])
                      :stroke (hsl [0 70 70 .5])
                      :stroke-width 5
                      :stroke-linejoin :round
                      :fill :none

                      }
               ]

              [:path {:d (path [0 0
                                :l 0 (ve 120)  400 80 0 40])
                      :stroke  (hsl [0 70 90 1])
                      :stroke-linejoin :round
                      :stroke-width 3
                      :fill :none

                      }
               ]


              #_[:path {:d (path [0 (ve 120)
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
                      :stroke (hsl [1 95 85 1])
                      :stroke-linejoin :round
                      :stroke-width 2
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




           [:path {:d "M31.51,22.71l38.63,66.92l-38.63,66.92h-77.27l-38.63-66.92l38.63-66.92H331.51 M38.7,10.25h-91.65l-45.83,79.38   L247.05,269h91.65l45.83-79.37L338.7,110.25L338.7,110.25z"
                   :stroke (hsl [4 70 70 1])
                   :stroke-width 2

                   :fill :none}]


           ]
          )]


       ])))


(defn chem-pop2 []
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
               (nth [[100 -200  400 400]] z))


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


       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}


           #_[:g
            {:transform (m7/tranfrom [[:translate [-50 -50]]
                                      [:scale [.5 .5]]])}

            #_[:text {:x -75
                    :y 150
                    :font-size 15}
             "boiling point −42.25 to −42.04 °C"]
            (alkine 2)]
           [:g
            {:transform (m7/tranfrom [[:translate [250  50]]])}
            #_[:text {:x 275
                    :y 50
                    :font-size 15}
             "boling point 97 to 98 °C;"
             ]
            (alkine-oh-acid 1)
            #_(alkine-oh 2)

            ]])]


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


       #_[:div {:style
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




           [:g



            [:path {:d (m7/path
                        [0 0 :l 0 200
                         0 -400])
                    :stroke-width 70
                    :stroke (hsl [1 70 70 1])}]










            [:g
             [:path

              {:d (m7/path [0 0 :a 110 80 0 false true 240 0])
               :mask (m7/url (name :msk))
               :stroke-width .1
               :stroke "pink"
               :id :kbrad
               :fill :none
               }
              ]
             [:mask {:id (name :msk)}
              (map
               (fn [y]
                 [:path {:transform
                         (m7/tranfrom
                          [[:translate [0
                                        (* 80 y)]]])
                         :stroke (hsl [0 49  78 1])
                         :fill :back
                         :d (m7/path
                             [-25 0 :l 50 0 0 50 -50 0 0 -50])}]) (range -2 3))]
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
             ]






            ]





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





(defn earth []
  (let []
    [:g
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
              :values (m7/sami-colon
                       (map m7/space
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
          ))



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
                            (take 18 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}
       [:div {:style
                (m7/css
                 [[4 8 14 6
                   :center :center  3 :rem :column]
                  [3.5 70 (+ 50 (* 5 5))  .3] []
                  {:gap ".1rem"
                   :z-index 10}])}


        #_[:div {:style {:font-size "2rem"}}
         "Earth's atmosphere is about 480 kilometers thick, but most of it is within 10km of the surface. Air pressure decreases with altitude. At sea level, air pressure is about 1 kilogram per square centimeter, and the atmosphere is relatively dense. At, (3 km), the air pressure is (0.7 kg per square cm), which means molecules of gas that make up the atmosphere are less dense. That makes it harder for a person to breathe and get enough oxygen to live, although there is evidence for microbial life high up in the clouds."]

        #_[m7/m '[= g [:m 9.8 [:m m [:p s -2]]]]]
        ;; [m7/m '[= [:m ρ A h]
        ;;         m]]

        #_[m7/m '[= W [:m m g] ]]
        #_[m7/m '[= [:k W a] [:m  [:k m a] g ] ]]



        ;; [m7/m '[= P [[:m ρ A h g] A] ]]

        ;; [m7/m '[= P [:m ρ h g]]]


        ;; [m7/m '[= P [:m 101.3 k Pa] [:m 101.3 k N [:p m -2]]]]

        #_[m7/m '[=  P [F A] [N [:p m 2]] [:m N [:p m -2]] Pa]]



        #_[m7/m '[= [:m ρ [:k A a] [:k h a]]
                [:k m a] ]]
        [:div {:style {:font-size "4rem"}}
         [m7/m '[= [:k P a] [:m [:k  ρ a] [:k h a] g]]]]


        [:div {:style {:font-size "4rem"}}
         [m7/m '[= [:k P a] [:m [:k  ρ a] 0 g]]]]


        [:div {:style {:font-size "4rem"}}
         [m7/m '[= [:k P a] [:m [:k  ρ a] [:m 3000 m]  g]]]]


        [:div {:style {:font-size "4rem"}}
         [m7/m '[= [:k P averest] [:m [:k  ρ a] [:m 5000 m]  g]]]]

        [:div {:style {:font-size "4rem"}}
         [m7/m '[= [:k P plane] [:m [:k  ρ a] [:m 7000 m]  g]]]]

        [:div {:style {:font-size "4rem"}}
        [m7/m '[= [:k P sea-label] [:m [:k  ρ a] [:m 10000 m]  g]]]]

        #_[m7/m '[= [1 [:p x 3] ] [:p x -3] ]]


        ;; [m7/m '[= ρV [:m V [m V]] ]]


        ;; [m7/m '[= m ρV ]]

        ;; [m7/m '[= W mg [:m ρ V g] ]]

        ;; [m7/m '[= P [W A] [[:m ρ A h g] A]  [:m ρ h g]  ]]

        [:div "Things don't always go how you want them to"]

        #_[:div "This project ended up a lot massier I thought it whould be"]
          #_[:div "This is how I like the things to be"]
          #_[:div "to begin with"]



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

           [:radialGradient {
                             :id (name :lg1)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [0 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [.2 70 70 .9])}]
            [:stop  {:offset .77
                     :stop-color (hsl [1 70 70 .8])}
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
                     :stop-color (hsl [3 40 40 1])}]
            [:stop  {:offset .55
                     :stop-color (hsl [3.3 60 60 1])}]

            [:stop  {:offset .77
                     :stop-color (hsl [4.3 70 70 .3])}
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




           ]
          )]


       ])))




(defn home-planets []
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
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .1])
                      :gap ".1rem"})}

       [:div {:style (m7/css
                      [[1 12 3 20 :center :center
                        1.8 :rem :column]
                       [1 70 90 .1] []
                       {:gap "1rem"
                        :color (hsl [3 20 30 1])
                        :z-index 2}])}



        ]

       [:div {:style (m7/css
                        [[1 12 2 20 :center :center
                          1.2 :rem :column]
                         [1 70 90 .1] []
                         {:gap "1rem"
                          :color (hsl [3 20 30 1])
                          :z-index 2}])}









        ]


       [:div {:style (m7/css
                      [[2 10 2 23 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"
                                       :z-index 1}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"
                         :transform-box :fill-box}
                 :viewBox (m7/space
                           viewbox)}

           [flames]

           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 6
                      :fill (m7/url (name :lg2))
                      }]]


           [:pattern {:id (name :star2)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 6
                      :fill (m7/url (name :lg1))
                      }]]
           [:pattern {:id (name :water22)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 4
                      :fill (hsl [3 70 70 .3])
                      }]]

           [:pattern {:id (name :un2)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 5
                      :fill (hsl [5.1 70 70 1])
                      }]]

           [:radialGradient {
                             :id (name :lg1)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [0 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [.2 70 70 .9])}]
            [:stop  {:offset .77
                     :stop-color (hsl [1 70 70 .8])}
             [:animate {:attributeName :offset
                        :id :f113
                        :begin 0
                        :from .88
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f123.end
                        :from 1
                        :to .88
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]]]
           [:radialGradient {
                             :id (name :lg2)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [3 40 40 1])}]
            [:stop  {:offset .55
                     :stop-color (hsl [3.3 60 60 1])}]


            [:stop  {:offset .97
                     :stop-color (hsl [1 70 70 .2])}
             [:animate {:attributeName :offset
                        :id :f114
                        :begin 0
                        :from .55
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f114.end
                        :from 1
                        :to .55
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin 0
                        :id :f115
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin :f115.end
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [13 "s"])
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




           #_{:transform (m7/tranfrom [[:translate [200 0]]


                                       [:scale 2]])}


           (let [chain 2]
             [:g







              #_[:circle {:r 120
                          :cx 0
                          :cy 0
                          :filter (m7/url "flames")
                          :fill (m7/url (name :lg2))}]

              #_[:circle {:r 250
                          :cx 0
                          :cy 0
                          :filter (m7/url "flames")
                          :fill (m7/url (name :lg1))}]



              [:circle {:r 350
                        :cx 0
                        :cy 0
                        :stroke  (hsl [0 70 70 1])
                        :stroke-width .5
                        :fill :none}]


              #_:fill (m7/url (name :lg2))
              #_:fill (m7/url (name :star))
              [:circle {:r 70
                        :cx 570
                        :cy 0
                        :stroke (hsl [3 70 70 1])
                        :stroke-width .5
                        :fill (m7/url (name :star2))

                        }

               [:animateTransform {
                                   :attributeName :transform
                                   :begin (sec 0)
                                   :dur (sec 15)
                                   :type :rotate
                                   :from 0
                                   :to 0
                                   :repeatCount :indefinite
                                   :fill :freeze}]]


              #_[:text {:font-size 23
                        :x 470
                        :y 0
                        :dx -40
                        :fill (hsl [3 10 10 1])
                        }
                 #_"1g of H is"
                 "1mol H 1g 6.023x10"
                 [:tspan {:dy -10} 23]


                 ]






              [:circle {:r 70
                        :cx 570
                        :cy 0
                        :stroke (hsl [3 70 70 1])
                        :stroke-width .5
                        :fill (m7/url (name :star))}




               ]

              #_[:text {:dx -100
                        :font-size 20
                        :x 250
                        :y 0
                        :fill (hsl [1 18 27 1])}

                 "1mol O 16g   6.023x10"
                 [:tspan {:dy -10} 23]

                 #_"16g of O is 6.023x10"
                 ]


              [:circle {:r 250
                        :cx 0
                        :cy 0
                        :stroke  (hsl [1 70 70 1])
                        :stroke-width .5
                        :fill :none}]





              [:circle {:r 470
                        :cx 0
                        :cy 0
                        :stroke  (hsl [2 70 70 1])
                        :stroke-width .3
                        :fill :none}]

              #_[:text {:dx 0
                        :transform (m7/tranfrom
                                    [[:rotate -15]])
                        :font-size 20
                        :x 570
                        :y 0
                        :fill (hsl [1 18 27 1])}

                 "1 mol H"
                 [:tspan {:dy 10} 2]
                 [:tspan {:dy -10} "O is 18g"]
                 #_[:tspan {:dy -10} 23]

                 #_"16g of O is 6.023x10"
                 ]


              [:circle {:r 70
                        :transform (m7/tranfrom
                                    [[:rotate -15]])
                        :cx 570
                        :cy 0
                        :stroke (hsl [3 70 70 .5])
                        :stroke-width .5
                        :fill (m7/url (name :water22))}




               ]


              [:circle {:r 580
                        :cx 0
                        :cy 0
                        :stroke  (hsl [2.7 70 70 1])
                        :stroke-width .3
                        :fill :none}]


              ])





           [:clipPath#anik
            [:circle {:r 85
                      :cx 500
                      :cy 100
                      :stroke :black
                      :storke-width 7
                      :fill :white}  ]]



           (let [d 50
                 hydro "methane"
                 chain 1
                 space-h (map
                          (fn [i]
                            (+ 1.5 (* 2 i)))
                          (range 0 chain))
                 rr (range 1 (+ chain 1))

                 chem `["H"  ~@(repeat chain "C" )  "H" ]
                 e (- d 20)
                 ]
             [:g {:transform (m7/tranfrom [[:scale 1.3]])}

              #_[:path {:d (m7/path [(ve e) (ve 10) :l (* e 2) 0])
                        :stroke (hsl [1 23 23 1])

                        :stroke-width 5}]

              (map-indexed
               (fn [i c]
                 [:text {:x (* i (* 2 d))
                         :dx (ve d)
                         :dy 17
                         :y 0}
                  c])
               chem)


              [:text {:style {:font-family "'Amazonia VAR'"
                              :font-size 80}
                      :x 0
                      :y -80}
               hydro]


              [:foreignObject {:style {:font-family "'Amazonia VAR'"
                                       :font-size 40
                                       }
                               :x 0
                               :y 80
                               :height 50
                               :width 250}

               [m7/mx `[:m
                        ~(if (= chain 1)
                           `C
                           `[:k C ~chain])
                        [:k H ~(+ 2 (* 2 chain))]]]
               ]



              [:text
               {:style {:font-family "'Amazonia VAR'"
                        :font-size 80}
                :x 0
                :y 120}
               ]


              [:path {:d (m7/path [0 0  :l (* (+ chain 1) 2 d) 0])
                      :stroke (hsl [1 23 23 1])
                      :stroke-dasharray (m7/space [(* d 1) (* d 1)])
                      :stroke-width 5}]


              #_[:path {:d
                      (m7/path [(* 2 d) -10  :l d 0])
                      :stroke (hsl [1 23 23 1])
                      :stroke-width 5}]

              #_[:path {:d (m7/path [0 0  :l d 0])
                      :id :bond1
                      :transform
                      (m7/tranfrom
                       [[:translate [(* 2 d) 10]]
                        [:rotate 0]])
                      :stroke (hsl [1 70 70 1])
                        :stroke-width 5}]



              (map
               (fn [sp i]
                 [:g
                  [:path {:d (m7/path [0 0  :l d 0])
                          :id (keyword (str "bond" i))
                          :transform
                          (m7/tranfrom
                           [[:translate [(* sp d) 20]]
                            [:rotate 90]])
                          :stroke (hsl [1 70 70 1])
                          :stroke-width 5}]
                  [:path {:d (m7/path [0 0  :l d 0])
                          :id (keyword (str "bondd" i))
                          :transform
                          (m7/tranfrom
                           [[:translate [(* sp d) (ve (* .5 d))]]
                            [:rotate -90]])
                          :stroke (hsl [0 70 70 1])
                          :stroke-width 5}]

                  ])
               space-h
               rr)





              [:text {}
               (map
                (fn [i]
                  [:textPath {:href (keyword (str  "#bond" i))
                              :startOffset "20%"}
                   "H"])
                rr)

               (map
                (fn [i]
                  [:textPath {:href (keyword (str  "#bondd" i))
                              :startOffset "20%"}
                   "H"])
                rr)

               ]


              #_(map               (fn [i c]
                 [:g

                  [:path {:d (m7/path [(* i (* 1.8 d)) (ve (* 1.5 d))  :l  0 (* 3 d)])
                          :stroke (hsl [1 23 23 1])
                          :stroke-dasharray (m7/space [(* d 1) (* d 1)])
                          :stroke-width 5}]

                  [:text {:x (* i (* 2 d))
                          :dx (ve d)
                          :dy 17
                          :y (ve (* 2 d))}
                   c]
                  [:text {:x (* i (* 2 d))
                          :dx (ve d)
                          :dy 17
                          :y (* 2 d)}
                   c]])
               (range 1 6)
               ["H" "H" "H" "H" ])




              #_[:path {:d (m7/path [(ve e) (ve 30) :l (* e 2) 0])
                        :stroke (hsl [1 23 23 1])
                        :stroke-width 5}]

              ]
             )




           ]
          )]


       ])))





(defn index2 []
  (let [[text set-text] (react/useState "")
        [slider set-slider] (react/useState -1)
        animate-ref (react/useRef)
        _ (react/useEffect
           (fn []
             (if (and animate-ref
                      (-> animate-ref .-current))
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
             (js/console.log "1")
             ))

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
               (nth [(map #(* .25 %) [-10 -20  83 40])
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
          m 30
          d 9.4
          scale2 [[.5 .5] [-0.5 .5]]
          ]




      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .5])
                      :gap ".2rem"})}


       [:div {:style
                (m7/css
               [[2 8  3 18
                 :center :flex-start  5.3 :rem :column]
                [1.5 70 80  .3] []
                {:gap ".1rem"
                 :color (hsl [0 30 30 1])
                 :z-index 10}])}
        [m7/x `[= [[:p x m] [:p x n]]
                [:p x [- m n]]]]

        [m7/x `[= [:m [:p x m] [:p x n]]
                [:p x [+ m n]]]]



        #_[m7/x `[- [:m 3 [:p x 2]

                   [:b [+ [:m 2 x] 1]]]
                [:m 5 [:p x 2]
                 [:b [-  [:m 3 x] 4]]]]]


        #_[m7/x `[- [+ [:m 6 [:p x 3]] [:m 3 [:p x 2]]]
                [+  [:m 15 [:p x 3]] [:m 20 [:p x 2]]]
                ]]


        #_[m7/x `[+ [:m [- 9] [:p x 3]]
                [:m 23 [:p x 2]]
                ]]

        #_[m7/x `[= [* -5 -4] 20]]

        #_[m7/x `[= [- [:m 6 [:p x 3]]
                   [:m 15 [:p x 3]]
                   ] [:m -9 [:p x 3]]]]


        #_[m7/x `[= [:m [:p x 3] [:b [- 6 15]]]

                [:m -9 [:p x 3]]]]

        #_[m7/x `[:m -2  [:p y 2] [:b [+ [- 5 [:m 7 y]] [:m 3 [:p y 2]]]]]]


        #_[m7/x `[+ [- 10 [:m 14 y]] [:m 6 [:m y 2]]]]


        #_[m7/x `[:m  1 [:p y 2] [:b [- [+ [- 10] [:m 14 y]] [:m 6 [:m y 2]]]]]]


        #_[m7/x `[- [+ [:p r 2] [:m 3 [:p t 2]] 9]  [:m 2 [:p r 2]] [+ [:m 3 [:p t 2]] 4]]]

        [m7/x `[[- [:m 3 [:p x 5]]
                 [:p x 7]
                 ] x]]


        [m7/x `[- [[:m 3 [:p x 5]] x]
                [[:p x 7] x]
                ]]

        [m7/x `[- [:m 3 [:p x 4]]
                [:p x 6]
                ]]
        ]







       #_[:div {:style (m7/css
                      [[2 10 2 23 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"
                                       :z-index 1}])}
          (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [flames]


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

           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 6
                      :fill (m7/url (name :lg2))
                      }]]

           [:radialGradient {
                             :id (name :lg1)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [0 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [.2 70 70 .9])}]
            [:stop  {:offset .77
                     :stop-color (hsl [1 70 70 .8])}
             [:animate {:attributeName :offset
                        :id :f113
                        :begin 0
                        :from .88
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f123.end
                        :from 1
                        :to .88
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]]
            ]


           [:radialGradient {
                             :id (name :lg2)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [3 40 40 .7])}]
            [:stop  {:offset .55
                     :stop-color (hsl [3.3 60 60 .8])}]


            [:stop  {:offset .97
                     :stop-color (hsl [1 70 70 .2])}
             [:animate {:attributeName :offset
                        :id :f114
                        :begin 0
                        :from .55
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f114.end
                        :from 1
                        :to .55
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin 0
                        :id :f115
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin :f115.end
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [13 "s"])
                        :repeatCount :indefinite}]]

            ]
           ;; :indefinite
           [:marker {:id (name :mb2)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [-3 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]






           #_(grid-on 20 20)


           #_(map

            [:path {:d (m7/path [0 0 :l 0 (ve (* 1 30 ))
                                 (* 4 20) 0 0 (* 1 30 )
                                 (* 4 -20) 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}
             ]


            (rang 0 5))





           [:g
            [:path {:d (m7/path [0 0 :l 0 (ve (* 1 30 ))
                                 (* 4 20) 0 0 (* 1 30 )
                                 (* 4 -20) 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}
             [:animateTransform {:id :green-car
                                 :attributeName :transform
                                 :begin 0
                                 :dur (sec 5)
                                 :from (space [0 0])
                                 :to (space [150 0])
                                 :type :translate
                                 :fill :freeze}]]
            [:path {:d (m7/path [200 0 :l 0 (ve (* 1 30 ))
                                 (* 4 20) 0 0 (* 1 30 )
                                 (* 4 -20) 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}]

            ]

           [:g {:transform (m7/tranfrom [[:translate [180 50] ]
                                         [:scale [3 3]]
                                         ])}
            [:path {:d (m7/path [0 0 :l 0 (ve (* 2 30 ))
                                 20 0 0 (* 2 30 )
                                 -20 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}]


            [:path#b1 {:d (m7/path [-10 0 :l 0 (ve (* 2 30 ))])
                       :stroke-width .5
                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))
                       :stroke (hsl [2 30 30 1])
                       :fill :none}]



            [:path#tri2 {:d (m7/path [20 (ve (* 2 30 )) :l (* 16 2) (ve (* 2 7 ))
                                      0 (ve (ve (* 2 7 ))) (ve (* 16 2)) 0])
                    :stroke (hsl [.5 70 70 1])
                    :stroke-width .5
                    :fill (hsl [1 70 70 1])}]







            [:path {:d (m7/path [ (+ 20 32) 0 :l 0 (ve (* 2 37 ))
                                 20 0 0 (* 2 37 )
                                 -20 0])
                    :stroke-width .5
                    :fill (hsl [2 70 70 1])}]

            [:path#b2 {:d (m7/path [ (+ 50 32) 0 :l
                                    0 (ve (* 2 37))])
                       :stroke (hsl [1 20 20 1])
                       :stroke-width .5

                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))

                       :fill (hsl [1 70 70 1])}]



            [:path#b3 {:d (m7/path [(* 1 20) 0 :l (* 16 2) 0])
                       :stroke (hsl [1 20 20 1])
                       :stroke-width .5

                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))

                       :fill (hsl [1 70 70 1])}]

            [:text {:style {:font-size 5}}

             [:textPath {:href :#tri2
                         :font-size 3}
              "B"]

             [:textPath {:href :#tri2
                         :startOffset (* 2 17)
                         :font-size 3}
              "A"]

             [:textPath {:href :#tri2
                         :startOffset (+ 13 (* 2 17))
                         :font-size 3}
              "C"]

             [:textPath {:href :#b1
                         :startOffset "40%"
                         }
              "30m"]

             [:textPath {:href :#b2
                         :startOffset "40%"}
              "37m"]

             [:textPath {:href :#b3
                         :startOffset "40%"}
              "14m"]]



            ]


           [:g
            [:g#logo
            (map
             (fn [x]
               [:g


                [:path
                 {:d
                  (str (path
                        [ 0 0 :l
                         (* 20 3) (ve (* 2 2 2))
                         (* 20 5) (ve (* 2 5 5))


                         (* 20 1)
                         (ve (- (* 2 9 9) (* 2 7 7)))
                          (ve (* 20 9))  0
                         ])
                       "z")

                  :transform
                  (m7/tranfrom
                   [
                    [:translate [(* m 20)
                                 (* 2 d d)]]
                    [:scale x]
                    ])
                  :stroke (hsl [4 70 70 1])
                  :stroke-width 10
                  :fill  (hsl [0 70 70 1])
                  }
                 ]

                [:path
                   {:d
                    (str (path
                          [ 0 0 :c
                           (* 20 3) (ve (* 2 2 2))
                           (* 20 5) (ve (* 2 5 5))
                           (* 20 7) (ve (* 2 7 7))
                           :c
                           (* 20 1)
                           (ve (- (* 2 9 9) (* 2 7 7)))
                           (* 20 2) (ve (- (* 2 11 11 ) (* 2 7 7)))
                           (* 20 4) (ve (- (* 2 13 13) (* 2 7 7)))
                           :l (ve (* 20 9))  0
                           ])
                         "z")

                    :transform
                    (m7/tranfrom
                     [
                      [:translate [(* m 20)
                                   (* 2 d d)]]
                      [:scale x]
                      ])
                    :stroke (hsl [4 70 70 1])
                    :stroke-width 10
                    :fill  (hsl [0 70 70 1])
                    }
                   ]
                ])
             scale2)


             [airplane2 [
                           [:translate [600 120]]
                        [:scale [.3 .3]]
                         [:rotate -90]]]


            ]





            #_[:circle {:r 90
                      :cx 0
                      :cy 0
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg2))}]

            [:circle {:r 250
                      :cx 0
                      :cy 0
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg2))}]


            #_[:circle {:r 90
                      :cx 0
                      :cy 0
                      :stroke (hsl [3 70 70 .5])
                      :stroke-width .5
                      :fill (m7/url (name :star))}

             #_[:animateTransform {
                                   :attributeName :transform
                                   :begin (sec 0)
                                   :dur (sec 15)
                                   :type :rotate
                                   :from 0
                                   :to -360
                                   :repeatCount :indefinite
                                   :fill :freeze}]


             ]



            [:circle {:r 350
                      :cx 0
                      :cy 0
                      :stroke  (hsl [0 70 70 1])
                      :stroke-width .5
                      :fill :none}]


            #_:fill (m7/url (name :lg2))
            #_:fill (m7/url (name :star))
            [:circle {:r 40
                      :cx 470
                      :cy 0
                      :stroke (hsl [3 70 70 1])
                      :stroke-width .5
                      :fill (m7/url (name :lg2))

                      }

             [:animateTransform {
                                 :attributeName :transform
                                 :begin (sec 0)
                                 :dur (sec 15)
                                 :type :rotate
                                 :from 0
                                 :to -12
                                 :repeatCount :indefinite
                                 :fill :freeze}]


             ]


            [:text {:font-size 56
                    :x 420
                    :y 0
                    :dx -40
                    :fill (hsl [3 70 70 1])
                    }

             ""



             ]





            [:path {:id :com-id
                    :d (m7/path `[450 -150
                                  :l ~@(map
                                        (fn [d x]
                                          (* d x))
                                        dx (cycle [ 300 70]))])
                    :fill (hsl [.2 60 55 .5])}]

            [:text {}
             [:textPath {:startOffset (m7/np [5 :%])
                         :fill (hsl [1 70 70 1])
                         :href :#com-id}
              "SCIENCE Café Scientifique"]]



            #_[:text {:font-size 56
                      :x 0
                      :y 0
                    :dx -40
                    :fill (hsl [2 70 70 1])
                    }

             "BDT 999"



             ]








            [:text {:dx -18
                    :x 250
                    :y 0
                    :fill (hsl [1 18 70 1])}




             ]


            [:circle {:r 250
                      :cx 0
                      :cy 0
                      :stroke  (hsl [1 70 70 1])
                      :stroke-width .5
                      :fill :none}]





            [:circle {:r 470
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2 70 70 1])
                      :stroke-width .3
                      :fill :none}]





            [:circle {:r 580
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2.7 70 70 1])
                      :stroke-width .3
                      :fill :none}]


            ]


           (let [p (fn [d stroke-width stroke fill]
                        [:path {:d (m7/path d)
                                :stroke-width stroke-width
                                :stroke (hsl stroke)
                                :fill (hsl fill)}])]
             [:g

              (map
               (fn [y]
                 [p [-300 y  :l 1600 0] .02
                  [1.8 70 70 1]
                  [1.8 70 70 1]])
               (range -5 5))


              (map
               (fn [x]
                 [p [x -800  :l 0 1600 ] .02
                  [2.5 70 70 1]
                  [2.5 70 70 1]])
               (range -5 5))



              ]



             )



           #_(map
              (fn [x]
                [:circle {:cx x
                          :cy (ve (+ x 1))
                          :r .05
                          :fill (hsl [5 70 70 1])}])
              (range -200 200 .1))




           (map
            (fn [x]
              [:circle {:cx x
                        :cy (ve (+ (* 2 x) 1))
                        :r .05
                        :fill (hsl [5 70 70 1])}])
            (range -200 200 .5))



           [:circle {:cx 0
                     :cy 0
                     :r .05
                     :fill (hsl [5 70 70 1])
                     }]

           #_[:circle {:cx 0
                     :cy 0
                     :r (js/Math.sqrt 5)
                     :fill :none
                     :stroke-width .01
                     :stroke (hsl [5 70 70 1])}]


           #_(map
              (fn [x]
                [:circle {:cx x
                          :cy (ve (- 3 (* x x)))
                          :r .05
                          :fill (hsl [0 70 70 1])}])
              (range -200 200 .1))


           (map
            (fn [x]
              [:circle {:cx x
                        :cy (ve (+ (* x x) (* 2 x ) -3 ))
                        :r .05
                        :fill (hsl [0 70 70 1])}])
            (range -200 200 .1))


           [:clipPath#anik
            [:circle {:r 85
                      :cx 500
                      :cy 100
                      :stroke :black
                      :storke-width 7
                      :fill :white}  ]]])]


       ])))










(defn home-planets-banners []
  (let [[text set-text] (react/useState "")
        [slider set-slider] (react/useState -1)
        animate-ref (react/useRef)
        _ (react/useEffect
           (fn []
             (if (and animate-ref
                      (-> animate-ref .-current))
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
             (js/console.log "1")
             ))

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
               (nth [(map #(* .25 %) [-10 -20  83 40])
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
          m 30
          d 9.4
          scale2 [[.5 .5] [-0.5 .5]]
          ]




      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .5])
                      :gap ".2rem"})}

       (map-indexed
        (fn [n [d r c f1 f2]]
          [:div {:ref (if (= n slider) animate-ref nil)
                 :on-mouse-enter (fn [e]
                                   (set-slider n))
                 :style (m7/css
                         [[(+ 0 (* 3 r)) 3 (+ 12 (* 3 c)) 3  f1 f2  1.5 :rem :column]
                          [(if (= slider n) 3 1) 70 (+ 50 (* 2 n)) .1] []
                          (into
                           {:font-size (m7/np [2.1 :rem])
                            :font-family "Roboto Flex"
                            :gap (m7/np [1 :rem])
                            :color (hsl [1 30 (if (= slider n) 20 20) 1])
                            :z-index 4
                            :cursor :grab}
                           {:z-index 19}
                           )])
                 }
           d])
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
           [f2 2 2 :flex-end  :center]]))











       [:div {:style
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

       #_[:div {:style
              (m7/css
               [[2 8  3 18
                 :center :flex-start  2.2 :rem :column]
                [1.5 70 80  .3] []
                {:gap ".1rem"
                 :color (hsl [0 30 30 1])
                 :z-index 10}])}

        #_"lie lose make mean meet pay put run say sell send set sit speak spend stand take teach tell think understand wear win write "




        #_[:div "symbol"]
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


        (comment

          )

        #_[:div "
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



        #_[:img {:style {:width "30%"
                       }
               :src "https://www.thespruce.com/thmb/l0_mNoIWGQuwtumjslpdZRgk9fg=/941x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/red-winged-blackbird-identification-385990-hero-bca4cabf8e9743e8b2459ce6421dc642.jpg"}]

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
        #_[m7/x `[= [-   5] [+ [:p x 2]  x [- 5] 6 ]]]




        #_[m7/x
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
                          [ [1 2] [3 2]]
                          [ 1 0]
                          [ np c])))])]



        #_[m7/x

         (w/postwalk
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








           ]
        (comment
          (m7/x
           (m7/eq2
            `[=
              ~(let [cp (fn [c d e] `[:p ~e ~d])
                     p (fn [c d e] `[:m ~c [:p ~e ~d]] )
                     np (fn [c _ e] `[:m ~c ~e] )
                     np1 (fn [c _ e] e)
                     c (fn [c _ e] c)]


                 (into ['+]
                       (map (fn [c d ff e]
                              (ff c d e))
                            [2 3]
                            [1 1]
                            [np c]
                            ['y 'y])))
              x
              ]))


          (m7/x `[= [+ [:p [:b x] 2]
                     [:m 2 [:p y 2]]] 27])




          (m7/x `[= [+ [:p [:b ~(let [cp (fn [c d e] `[:p ~e ~d])
                                      p (fn [c d e] `[:m ~c [:p ~e ~d]] )
                                      np (fn [c _ e] `[:m ~c ~e] )
                                      np1 (fn [c _ e] e)
                                      c (fn [c _ e] c)]


                                  (into ['+]
                                        (map (fn [c d ff e]
                                               (ff c d e))
                                             [2 3]
                                             [1 1]
                                             [np c]
                                             ['y 'y])))] 2]
                     [:m 2 [:p y 2]]] 27])


          (m7/x `[= [+   ~(let [cp (fn [c d e] `[:p ~e ~d])
                                p (fn [c d e] `[:m ~c [:p ~e ~d]] )
                                np (fn [c _ e] `[:m ~c ~e] )
                                np1 (fn [c _ e] e)
                                c (fn [c _ e] c)]


                            (into ['+]
                                  (map (fn [c d ff e]
                                         (ff c d e))
                                       [(* 2 2) (* 2 3) 1]
                                       [2 1 2]
                                       [p np cp]
                                       ['y 'y 3])))
                     [:m 2 [:p y 2]]] 27])


          (m7/x `[= ~(let [cp (fn [c d e] `[:p ~e ~d])
                           p (fn [c d e] `[:m ~c [:p ~e ~d]] )
                           np (fn [c _ e] `[:m ~c ~e] )
                           np1 (fn [c _ e] e)
                           c (fn [c _ e] c)]
                       (conj
                        (into ['+]
                              (map (fn [c d ff e]
                                     (ff c d e))
                                   [6 (* 2 3) 1]
                                   [2 1 2]
                                   [p np cp]
                                   ['y 'y 3]))
                        `[- [* 3 [:p 3 2]]])
                       ) 0]))


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


        #_[m7/x
         (m7/eq2 `[= [+ x 1] y])]


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




           (str/split "last year covid situation broke out." #"\s+"))




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
                                   .-value)))}]]


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


       [:div {:style (m7/css
                      [[8 5 1 18 :center :center 2.2 :rem ]
                       [1 70 90 .1] [] {:gap "1rem"
                                        :color (hsl [3 20 30 1])
                                        :z-index 2}])}

        #_[:span {:style {:background-color (hsl [1 70 70 .5])}}"9th October"]








        ]


       [:div {:style (m7/css
                      [[2 10 2 23 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"
                                       :z-index 1}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [flames]


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

           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 6
                      :fill (m7/url (name :lg2))
                      }]]

           [:radialGradient {
                             :id (name :lg1)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [0 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [.2 70 70 .9])}]
            [:stop  {:offset .77
                     :stop-color (hsl [1 70 70 .8])}
             [:animate {:attributeName :offset
                        :id :f113
                        :begin 0
                        :from .88
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f123.end
                        :from 1
                        :to .88
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]]
            ]


           [:radialGradient {
                             :id (name :lg2)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [3 40 40 .7])}]
            [:stop  {:offset .55
                     :stop-color (hsl [3.3 60 60 .8])}]


            [:stop  {:offset .97
                     :stop-color (hsl [1 70 70 .2])}
             [:animate {:attributeName :offset
                        :id :f114
                        :begin 0
                        :from .55
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f114.end
                        :from 1
                        :to .55
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin 0
                        :id :f115
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin :f115.end
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [13 "s"])
                        :repeatCount :indefinite}]]

            ]
           ;; :indefinite
           [:marker {:id (name :mb2)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [-3 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]






           #_(grid-on 20 20)


           #_(map

            [:path {:d (m7/path [0 0 :l 0 (ve (* 1 30 ))
                                 (* 4 20) 0 0 (* 1 30 )
                                 (* 4 -20) 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}
             ]


            (rang 0 5))





           [:g
            [:path {:d (m7/path [0 0 :l 0 (ve (* 1 30 ))
                                 (* 4 20) 0 0 (* 1 30 )
                                 (* 4 -20) 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}
             [:animateTransform {:id :green-car
                                 :attributeName :transform
                                 :begin 0
                                 :dur (sec 5)
                                 :from (space [0 0])
                                 :to (space [150 0])
                                 :type :translate
                                 :fill :freeze}]]
            [:path {:d (m7/path [200 0 :l 0 (ve (* 1 30 ))
                                 (* 4 20) 0 0 (* 1 30 )
                                 (* 4 -20) 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}]

            ]

           [:g {:transform (m7/tranfrom [[:translate [180 50] ]
                                         [:scale [3 3]]
                                         ])}
            [:path {:d (m7/path [0 0 :l 0 (ve (* 2 30 ))
                                 20 0 0 (* 2 30 )
                                 -20 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}]


            [:path#b1 {:d (m7/path [-10 0 :l 0 (ve (* 2 30 ))])
                       :stroke-width .5
                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))
                       :stroke (hsl [2 30 30 1])
                       :fill :none}]



            [:path#tri2 {:d (m7/path [20 (ve (* 2 30 )) :l (* 16 2) (ve (* 2 7 ))
                                      0 (ve (ve (* 2 7 ))) (ve (* 16 2)) 0])
                    :stroke (hsl [.5 70 70 1])
                    :stroke-width .5
                    :fill (hsl [1 70 70 1])}]







            [:path {:d (m7/path [ (+ 20 32) 0 :l 0 (ve (* 2 37 ))
                                 20 0 0 (* 2 37 )
                                 -20 0])
                    :stroke-width .5
                    :fill (hsl [2 70 70 1])}]

            [:path#b2 {:d (m7/path [ (+ 50 32) 0 :l
                                    0 (ve (* 2 37))])
                       :stroke (hsl [1 20 20 1])
                       :stroke-width .5

                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))

                       :fill (hsl [1 70 70 1])}]



            [:path#b3 {:d (m7/path [(* 1 20) 0 :l (* 16 2) 0])
                       :stroke (hsl [1 20 20 1])
                       :stroke-width .5

                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))

                       :fill (hsl [1 70 70 1])}]

            [:text {:style {:font-size 5}}

             [:textPath {:href :#tri2
                         :font-size 3}
              "B"]

             [:textPath {:href :#tri2
                         :startOffset (* 2 17)
                         :font-size 3}
              "A"]

             [:textPath {:href :#tri2
                         :startOffset (+ 13 (* 2 17))
                         :font-size 3}
              "C"]

             [:textPath {:href :#b1
                         :startOffset "40%"
                         }
              "30m"]

             [:textPath {:href :#b2
                         :startOffset "40%"}
              "37m"]

             [:textPath {:href :#b3
                         :startOffset "40%"}
              "14m"]]



            ]


           [:g
            [:g#logo
            (map
             (fn [x]
               [:g


                [:path
                 {:d
                  (str (path
                        [ 0 0 :l
                         (* 20 3) (ve (* 2 2 2))
                         (* 20 5) (ve (* 2 5 5))


                         (* 20 1)
                         (ve (- (* 2 9 9) (* 2 7 7)))
                          (ve (* 20 9))  0
                         ])
                       "z")

                  :transform
                  (m7/tranfrom
                   [
                    [:translate [(* m 20)
                                 (* 2 d d)]]
                    [:scale x]
                    ])
                  :stroke (hsl [4 70 70 1])
                  :stroke-width 10
                  :fill  (hsl [0 70 70 1])
                  }
                 ]

                [:path
                   {:d
                    (str (path
                          [ 0 0 :c
                           (* 20 3) (ve (* 2 2 2))
                           (* 20 5) (ve (* 2 5 5))
                           (* 20 7) (ve (* 2 7 7))
                           :c
                           (* 20 1)
                           (ve (- (* 2 9 9) (* 2 7 7)))
                           (* 20 2) (ve (- (* 2 11 11 ) (* 2 7 7)))
                           (* 20 4) (ve (- (* 2 13 13) (* 2 7 7)))
                           :l (ve (* 20 9))  0
                           ])
                         "z")

                    :transform
                    (m7/tranfrom
                     [
                      [:translate [(* m 20)
                                   (* 2 d d)]]
                      [:scale x]
                      ])
                    :stroke (hsl [4 70 70 1])
                    :stroke-width 10
                    :fill  (hsl [0 70 70 1])
                    }
                   ]
                ])
             scale2)


             [airplane2 [
                           [:translate [600 120]]
                        [:scale [.3 .3]]
                         [:rotate -90]]]


            ]





            #_[:circle {:r 90
                      :cx 0
                      :cy 0
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg2))}]

            [:circle {:r 250
                      :cx 0
                      :cy 0
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg2))}]


            #_[:circle {:r 90
                      :cx 0
                      :cy 0
                      :stroke (hsl [3 70 70 .5])
                      :stroke-width .5
                      :fill (m7/url (name :star))}

             #_[:animateTransform {
                                   :attributeName :transform
                                   :begin (sec 0)
                                   :dur (sec 15)
                                   :type :rotate
                                   :from 0
                                   :to -360
                                   :repeatCount :indefinite
                                   :fill :freeze}]


             ]



            [:circle {:r 350
                      :cx 0
                      :cy 0
                      :stroke  (hsl [0 70 70 1])
                      :stroke-width .5
                      :fill :none}]


            #_:fill (m7/url (name :lg2))
            #_:fill (m7/url (name :star))
            [:circle {:r 40
                      :cx 470
                      :cy 0
                      :stroke (hsl [3 70 70 1])
                      :stroke-width .5
                      :fill (m7/url (name :lg2))

                      }

             [:animateTransform {
                                 :attributeName :transform
                                 :begin (sec 0)
                                 :dur (sec 15)
                                 :type :rotate
                                 :from 0
                                 :to -12
                                 :repeatCount :indefinite
                                 :fill :freeze}]


             ]


            [:text {:font-size 56
                    :x 420
                    :y 0
                    :dx -40
                    :fill (hsl [3 70 70 1])
                    }

             ""



             ]





            [:path {:id :com-id
                    :d (m7/path `[450 -150
                                  :l ~@(map
                                        (fn [d x]
                                          (* d x))
                                        dx (cycle [ 300 70]))])
                    :fill (hsl [.2 60 55 .5])}]

            [:text {}
             [:textPath {:startOffset (m7/np [5 :%])
                         :fill (hsl [1 70 70 1])
                         :href :#com-id}
              "SCIENCE Café Scientifique"]]



            #_[:text {:font-size 56
                      :x 0
                      :y 0
                    :dx -40
                    :fill (hsl [2 70 70 1])
                    }

             "BDT 999"



             ]








            [:text {:dx -18
                    :x 250
                    :y 0
                    :fill (hsl [1 18 70 1])}




             ]


            [:circle {:r 250
                      :cx 0
                      :cy 0
                      :stroke  (hsl [1 70 70 1])
                      :stroke-width .5
                      :fill :none}]





            [:circle {:r 470
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2 70 70 1])
                      :stroke-width .3
                      :fill :none}]





            [:circle {:r 580
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2.7 70 70 1])
                      :stroke-width .3
                      :fill :none}]


            ]


           (let [p (fn [d stroke-width stroke fill]
                        [:path {:d (m7/path d)
                                :stroke-width stroke-width
                                :stroke (hsl stroke)
                                :fill (hsl fill)}])]
             [:g

              (map
               (fn [y]
                 [p [-300 y  :l 1600 0] .02
                  [1.8 70 70 1]
                  [1.8 70 70 1]])
               (range -5 5))


              (map
               (fn [x]
                 [p [x -800  :l 0 1600 ] .02
                  [2.5 70 70 1]
                  [2.5 70 70 1]])
               (range -5 5))



              ]



             )



           #_(map
              (fn [x]
                [:circle {:cx x
                          :cy (ve (+ x 1))
                          :r .05
                          :fill (hsl [5 70 70 1])}])
              (range -200 200 .1))




           (map
            (fn [x]
              [:circle {:cx x
                        :cy (ve (+ (* 2 x) 1))
                        :r .05
                        :fill (hsl [5 70 70 1])}])
            (range -200 200 .5))



           [:circle {:cx 0
                     :cy 0
                     :r .05
                     :fill (hsl [5 70 70 1])
                     }]

           #_[:circle {:cx 0
                     :cy 0
                     :r (js/Math.sqrt 5)
                     :fill :none
                     :stroke-width .01
                     :stroke (hsl [5 70 70 1])}]


           #_(map
              (fn [x]
                [:circle {:cx x
                          :cy (ve (- 3 (* x x)))
                          :r .05
                          :fill (hsl [0 70 70 1])}])
              (range -200 200 .1))


           (map
            (fn [x]
              [:circle {:cx x
                        :cy (ve (+ (* x x) (* 2 x ) -3 ))
                        :r .05
                        :fill (hsl [0 70 70 1])}])
            (range -200 200 .1))


           [:clipPath#anik
            [:circle {:r 85
                      :cx 500
                      :cy 100
                      :stroke :black
                      :storke-width 7
                      :fill :white}  ]]])]


       ])))





(defn home-planets-banners2 []
  (let [[text set-text] (react/useState "")
        [slider set-slider] (react/useState -1)
        animate-ref (react/useRef)
        _ (react/useEffect
           (fn []
             (if (and animate-ref
                      (-> animate-ref .-current))
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
             (js/console.log "1")
             ))

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
               (nth [(map #(* 10 %) [-10 -20  83 40])
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
          m 30
          d 9.4
          scale2 [[.5 .5] [-0.5 .5]]
          ]




      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .5])
                      :gap ".2rem"})}
       (let [d 'ε]
           [:div {:style
                  (m7/css
                   [[2 10  15 7
                     :center :center  1.2 :rem :column]
                    [1.5 70 80  .5] []
                    {:gap ".1rem"
                     :color (hsl [0 30 60 1])
                     :z-index 10}])}
            [:div {:style {:font-size "1.1rem"}}
             #_[:div time-str]

             ]
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
          [m7/m '[< [:p .00000000000000001 2] .00000000000000001 ]]

            [m7/mx `[= [:k y [+ t ~d]] [:m v [:b [+ t ~d] ]]]]

            [m7/mx `[= [:k y [+ t ~d]] [+ [:m v t] [:m v ~d]]]]

            [m7/mx `[= [:k y [+ t ~d]]
                   [+ [:k y t] [:m [:p y .] ~d]]]]

            [m7/mx `[= [:k y [+ t ~d]]
                   [+ [:k y t] [:m v ~d]]]]



            [m7/mx `[= ~d
                     [:sq 0]]]









            ])




       (map
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

       (map
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







       [:div {:style (m7/css
                      [[2 10 2 23 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"
                                       :z-index 1}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [flames]


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

           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 6
                      :fill (m7/url (name :lg2))
                      }]]

           [:radialGradient {
                             :id (name :lg1)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [0 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [.2 70 70 .9])}]
            [:stop  {:offset .77
                     :stop-color (hsl [1 70 70 .8])}
             [:animate {:attributeName :offset
                        :id :f113
                        :begin 0
                        :from .88
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f123.end
                        :from 1
                        :to .88
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]]
            ]


           [:radialGradient {
                             :id (name :lg2)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [3 40 40 .7])}]
            [:stop  {:offset .55
                     :stop-color (hsl [3.3 60 60 .8])}]


            [:stop  {:offset .97
                     :stop-color (hsl [1 70 70 .2])}
             [:animate {:attributeName :offset
                        :id :f114
                        :begin 0
                        :from .55
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f114.end
                        :from 1
                        :to .55
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin 0
                        :id :f115
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin :f115.end
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [13 "s"])
                        :repeatCount :indefinite}]]

            ]
           ;; :indefinite
           [:marker {:id (name :mb2)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [-3 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]






           (grid-on 20 20)




           [:g
            [:path {:d (m7/path [0 0 :l 0 (ve (* 1 30 ))
                                 (* 4 20) 0 0 (* 1 30 )
                                 (* 4 -20) 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}
             [:animateTransform {:id :green-car
                                 :attributeName :transform
                                 :begin 0
                                 :dur (sec 5)
                                 :from (space [0 0])
                                 :to (space [150 0])
                                 :type :translate
                                 :fill :freeze}]]
            [:path {:d (m7/path [200 0 :l 0 (ve (* 1 30 ))
                                 (* 4 20) 0 0 (* 1 30 )
                                 (* 4 -20) 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}]

            ]

           [:g {:transform (m7/tranfrom [[:translate [180 50] ]
                                         [:scale [3 3]]
                                         ])}
            [:path {:d (m7/path [0 0 :l 0 (ve (* 2 30 ))
                                 20 0 0 (* 2 30 )
                                 -20 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}]


            [:path#b1 {:d (m7/path [-10 0 :l 0 (ve (* 2 30 ))])
                       :stroke-width .5
                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))
                       :stroke (hsl [2 30 30 1])
                       :fill :none}]



            [:path#tri2 {:d (m7/path [20 (ve (* 2 30 )) :l (* 16 2) (ve (* 2 7 ))
                                      0 (ve (ve (* 2 7 ))) (ve (* 16 2)) 0])
                    :stroke (hsl [.5 70 70 1])
                    :stroke-width .5
                    :fill (hsl [1 70 70 1])}]







            [:path {:d (m7/path [ (+ 20 32) 0 :l 0 (ve (* 2 37 ))
                                 20 0 0 (* 2 37 )
                                 -20 0])
                    :stroke-width .5
                    :fill (hsl [2 70 70 1])}]

            [:path#b2 {:d (m7/path [ (+ 50 32) 0 :l
                                    0 (ve (* 2 37))])
                       :stroke (hsl [1 20 20 1])
                       :stroke-width .5

                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))

                       :fill (hsl [1 70 70 1])}]



            [:path#b3 {:d (m7/path [(* 1 20) 0 :l (* 16 2) 0])
                       :stroke (hsl [1 20 20 1])
                       :stroke-width .5

                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))

                       :fill (hsl [1 70 70 1])}]

            [:text {:style {:font-size 5}}

             [:textPath {:href :#tri2
                         :font-size 3}
              "B"]

             [:textPath {:href :#tri2
                         :startOffset (* 2 17)
                         :font-size 3}
              "A"]

             [:textPath {:href :#tri2
                         :startOffset (+ 13 (* 2 17))
                         :font-size 3}
              "C"]

             [:textPath {:href :#b1
                         :startOffset "40%"
                         }
              "30m"]

             [:textPath {:href :#b2
                         :startOffset "40%"}
              "37m"]

             [:textPath {:href :#b3
                         :startOffset "40%"}
              "14m"]]



            ]

           [airplane2 [
                       [:translate [200 120]]
                       [:scale [.3 .3]]
                       [:rotate -30]]]









           (map
              (fn [x]
                [:circle {:cx x
                          :cy (ve (+ x 1))
                          :r .05
                          :fill (hsl [5 70 70 1])}])
              (range -200 200 .1))








           [:circle {:cx 0
                     :cy 0
                     :r .05
                     :fill (hsl [5 70 70 1])
                     }]

           [:circle {:cx 0
                     :cy 0
                     :r (js/Math.sqrt 5)
                     :fill :none
                     :stroke-width .01
                     :stroke (hsl [5 70 70 1])}]


           (map
              (fn [x]
                [:circle {:cx x
                          :cy (ve (- 3 (* x x)))
                          :r .05
                          :fill (hsl [0 70 70 1])}])
              (range -200 200 .1))





           [:clipPath#anik
            [:circle {:r 85
                      :cx 500
                      :cy 100
                      :stroke :black
                      :storke-width 7
                      :fill :white}  ]]])]


       ])))


(defn board2 [v box g]
  (let [[text set-text] (react/useState "")
        [slider set-slider] (react/useState -1)
        animate-ref (react/useRef)
        _ (react/useEffect
           (fn []
             (if (and animate-ref
                      (-> animate-ref .-current))
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
             (js/console.log "1")
             ))

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
               (nth [(map #(* .25 %) [-10 -20  83 40])
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
          m 30
          d 9.4
          scale2 [[.5 .5] [-0.5 .5]]
          ]




      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .5])
                      :gap ".2rem"})}

       (map-indexed
        (fn [n [d r c f1 f2]]
          [:div {:ref (if (= n slider) animate-ref nil)
                 :on-mouse-enter (fn [e]
                                   (set-slider n))
                 :style (m7/css
                         [[(+ 0 (* 3 r)) 3 (+ 12 (* 3 c)) 3  f1 f2  1.5 :rem :column]
                          [(if (= slider n) 3 1) 70 (+ 50 (* 2 n)) .1] []
                          (into
                           {:font-size (m7/np [2.1 :rem])
                            :font-family "Roboto Flex"
                            :gap (m7/np [1 :rem])
                            :color (hsl [1 30 (if (= slider n) 20 20) 1])
                            :z-index 4
                            :cursor :grab}
                           {:z-index 19}
                           )])
                 }
           d])
        box)



       [:div {:style
              (m7/css
               [[8 4  15 8
                 :center :flex-start  3.3 :rem :column]
                [1.5 70 80  .3] []
                {:gap ".1rem"
                 :color (hsl [0 30 30 1])
                 :z-index 10}])}
        (map (fn [a]
               a)  g)]


       [:div {:style
              (m7/css
               [[2 8  3 18
                 :center :flex-start  2.2 :rem :column]
                [1.5 70 80  .3] []
                {:gap ".1rem"
                 :color (hsl [0 30 30 1])
                 :z-index 10}])}

        #_"lie lose make mean meet pay put run say sell send set sit speak spend stand take teach tell think understand wear win write "




        #_[:div "symbol"]
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


        (comment

          )

        #_[:div "
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



        #_[:img {:style {:width "30%"
                       }
               :src "https://www.thespruce.com/thmb/l0_mNoIWGQuwtumjslpdZRgk9fg=/941x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/red-winged-blackbird-identification-385990-hero-bca4cabf8e9743e8b2459ce6421dc642.jpg"}]

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
        #_[m7/x `[= [-   5] [+ [:p x 2]  x [- 5] 6 ]]]




        (map (fn [e]
               (m7/x e)) v)


        #_[m7/x










           ]
        (comment
          (m7/x
           (m7/eq2
            `[=
              ~(let [cp (fn [c d e] `[:p ~e ~d])
                     p (fn [c d e] `[:m ~c [:p ~e ~d]] )
                     np (fn [c _ e] `[:m ~c ~e] )
                     np1 (fn [c _ e] e)
                     c (fn [c _ e] c)]


                 (into ['+]
                       (map (fn [c d ff e]
                              (ff c d e))
                            [2 3]
                            [1 1]
                            [np c]
                            ['y 'y])))
              x
              ]))


          (m7/x `[= [+ [:p [:b x] 2]
                     [:m 2 [:p y 2]]] 27])




          (m7/x `[= [+ [:p [:b ~(let [cp (fn [c d e] `[:p ~e ~d])
                                      p (fn [c d e] `[:m ~c [:p ~e ~d]] )
                                      np (fn [c _ e] `[:m ~c ~e] )
                                      np1 (fn [c _ e] e)
                                      c (fn [c _ e] c)]


                                  (into ['+]
                                        (map (fn [c d ff e]
                                               (ff c d e))
                                             [2 3]
                                             [1 1]
                                             [np c]
                                             ['y 'y])))] 2]
                     [:m 2 [:p y 2]]] 27])


          (m7/x `[= [+   ~(let [cp (fn [c d e] `[:p ~e ~d])
                                p (fn [c d e] `[:m ~c [:p ~e ~d]] )
                                np (fn [c _ e] `[:m ~c ~e] )
                                np1 (fn [c _ e] e)
                                c (fn [c _ e] c)]


                            (into ['+]
                                  (map (fn [c d ff e]
                                         (ff c d e))
                                       [(* 2 2) (* 2 3) 1]
                                       [2 1 2]
                                       [p np cp]
                                       ['y 'y 3])))
                     [:m 2 [:p y 2]]] 27])


          (m7/x `[= ~(let [cp (fn [c d e] `[:p ~e ~d])
                           p (fn [c d e] `[:m ~c [:p ~e ~d]] )
                           np (fn [c _ e] `[:m ~c ~e] )
                           np1 (fn [c _ e] e)
                           c (fn [c _ e] c)]
                       (conj
                        (into ['+]
                              (map (fn [c d ff e]
                                     (ff c d e))
                                   [6 (* 2 3) 1]
                                   [2 1 2]
                                   [p np cp]
                                   ['y 'y 3]))
                        `[- [* 3 [:p 3 2]]])
                       ) 0]))


        #_(m7/x `[= [+ [:m 6 [:p y 2]] [:m 6 y] [:m [:p 3 2] [:b [- 1 3]]]] 0])


        #_(m7/x `[= [+ [:m 6 [:p y 2]] [:m 6 y] [* [- 2] [:p 3 2] ]] 0])


        #_(m7/x `[= [+ [:m 1 [:p y 2]] [:m 1 y] [- 3]] 0])

        #_[m7/x `[= [-  [:m 8 [:p x 2]]
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


        #_[m7/x
         (m7/eq2 `[= [+ x 1] y])]


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




           (str/split "last year covid situation broke out." #"\s+"))




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
                                   .-value)))}]]


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


       [:div {:style (m7/css
                      [[8 5 1 18 :center :center 2.2 :rem ]
                       [1 70 90 .1] [] {:gap "1rem"
                                        :color (hsl [3 20 30 1])
                                        :z-index 2}])}

        #_[:span {:style {:background-color (hsl [1 70 70 .5])}}"9th October"]








        ]


       [:div {:style (m7/css
                      [[2 10 2 23 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"
                                       :z-index 1}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [flames]


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

           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 6
                      :fill (m7/url (name :lg2))
                      }]]

           [:radialGradient {
                             :id (name :lg1)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [0 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [.2 70 70 .9])}]
            [:stop  {:offset .77
                     :stop-color (hsl [1 70 70 .8])}
             [:animate {:attributeName :offset
                        :id :f113
                        :begin 0
                        :from .88
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f123.end
                        :from 1
                        :to .88
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]]
            ]


           [:radialGradient {
                             :id (name :lg2)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [3 40 40 .7])}]
            [:stop  {:offset .55
                     :stop-color (hsl [3.3 60 60 .8])}]


            [:stop  {:offset .97
                     :stop-color (hsl [1 70 70 .2])}
             [:animate {:attributeName :offset
                        :id :f114
                        :begin 0
                        :from .55
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f114.end
                        :from 1
                        :to .55
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin 0
                        :id :f115
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin :f115.end
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [13 "s"])
                        :repeatCount :indefinite}]]

            ]
           ;; :indefinite
           [:marker {:id (name :mb2)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [-3 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]






           #_(grid-on 20 20)


           #_(map

            [:path {:d (m7/path [0 0 :l 0 (ve (* 1 30 ))
                                 (* 4 20) 0 0 (* 1 30 )
                                 (* 4 -20) 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}
             ]


            (rang 0 5))





           [:g
            [:path {:d (m7/path [0 0 :l 0 (ve (* 1 30 ))
                                 (* 4 20) 0 0 (* 1 30 )
                                 (* 4 -20) 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}
             [:animateTransform {:id :green-car
                                 :attributeName :transform
                                 :begin 0
                                 :dur (sec 5)
                                 :from (space [0 0])
                                 :to (space [150 0])
                                 :type :translate
                                 :fill :freeze}]]
            [:path {:d (m7/path [200 0 :l 0 (ve (* 1 30 ))
                                 (* 4 20) 0 0 (* 1 30 )
                                 (* 4 -20) 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}]

            ]

           [:g {:transform (m7/tranfrom [[:translate [180 50] ]
                                         [:scale [3 3]]
                                         ])}
            [:path {:d (m7/path [0 0 :l 0 (ve (* 2 30 ))
                                 20 0 0 (* 2 30 )
                                 -20 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}]


            [:path#b1 {:d (m7/path [-10 0 :l 0 (ve (* 2 30 ))])
                       :stroke-width .5
                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))
                       :stroke (hsl [2 30 30 1])
                       :fill :none}]



            [:path#tri2 {:d (m7/path [20 (ve (* 2 30 )) :l (* 16 2) (ve (* 2 7 ))
                                      0 (ve (ve (* 2 7 ))) (ve (* 16 2)) 0])
                    :stroke (hsl [.5 70 70 1])
                    :stroke-width .5
                    :fill (hsl [1 70 70 1])}]







            [:path {:d (m7/path [ (+ 20 32) 0 :l 0 (ve (* 2 37 ))
                                 20 0 0 (* 2 37 )
                                 -20 0])
                    :stroke-width .5
                    :fill (hsl [2 70 70 1])}]

            [:path#b2 {:d (m7/path [ (+ 50 32) 0 :l
                                    0 (ve (* 2 37))])
                       :stroke (hsl [1 20 20 1])
                       :stroke-width .5

                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))

                       :fill (hsl [1 70 70 1])}]



            [:path#b3 {:d (m7/path [(* 1 20) 0 :l (* 16 2) 0])
                       :stroke (hsl [1 20 20 1])
                       :stroke-width .5

                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))

                       :fill (hsl [1 70 70 1])}]

            [:text {:style {:font-size 5}}

             [:textPath {:href :#tri2
                         :font-size 3}
              "B"]

             [:textPath {:href :#tri2
                         :startOffset (* 2 17)
                         :font-size 3}
              "A"]

             [:textPath {:href :#tri2
                         :startOffset (+ 13 (* 2 17))
                         :font-size 3}
              "C"]

             [:textPath {:href :#b1
                         :startOffset "40%"
                         }
              "30m"]

             [:textPath {:href :#b2
                         :startOffset "40%"}
              "37m"]

             [:textPath {:href :#b3
                         :startOffset "40%"}
              "14m"]]



            ]


           [:g
            [:g#logo
            (map
             (fn [x]
               [:g


                [:path
                 {:d
                  (str (path
                        [ 0 0 :l
                         (* 20 3) (ve (* 2 2 2))
                         (* 20 5) (ve (* 2 5 5))


                         (* 20 1)
                         (ve (- (* 2 9 9) (* 2 7 7)))
                          (ve (* 20 9))  0
                         ])
                       "z")

                  :transform
                  (m7/tranfrom
                   [
                    [:translate [(* m 20)
                                 (* 2 d d)]]
                    [:scale x]
                    ])
                  :stroke (hsl [4 70 70 1])
                  :stroke-width 10
                  :fill  (hsl [0 70 70 1])
                  }
                 ]

                [:path
                   {:d
                    (str (path
                          [ 0 0 :c
                           (* 20 3) (ve (* 2 2 2))
                           (* 20 5) (ve (* 2 5 5))
                           (* 20 7) (ve (* 2 7 7))
                           :c
                           (* 20 1)
                           (ve (- (* 2 9 9) (* 2 7 7)))
                           (* 20 2) (ve (- (* 2 11 11 ) (* 2 7 7)))
                           (* 20 4) (ve (- (* 2 13 13) (* 2 7 7)))
                           :l (ve (* 20 9))  0
                           ])
                         "z")

                    :transform
                    (m7/tranfrom
                     [
                      [:translate [(* m 20)
                                   (* 2 d d)]]
                      [:scale x]
                      ])
                    :stroke (hsl [4 70 70 1])
                    :stroke-width 10
                    :fill  (hsl [0 70 70 1])
                    }
                   ]
                ])
             scale2)


             [airplane2 [
                           [:translate [600 120]]
                        [:scale [.3 .3]]
                         [:rotate -90]]]


            ]





            #_[:circle {:r 90
                      :cx 0
                      :cy 0
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg2))}]

            [:circle {:r 250
                      :cx 0
                      :cy 0
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg2))}]


            #_[:circle {:r 90
                      :cx 0
                      :cy 0
                      :stroke (hsl [3 70 70 .5])
                      :stroke-width .5
                      :fill (m7/url (name :star))}

             #_[:animateTransform {
                                   :attributeName :transform
                                   :begin (sec 0)
                                   :dur (sec 15)
                                   :type :rotate
                                   :from 0
                                   :to -360
                                   :repeatCount :indefinite
                                   :fill :freeze}]


             ]



            [:circle {:r 350
                      :cx 0
                      :cy 0
                      :stroke  (hsl [0 70 70 1])
                      :stroke-width .5
                      :fill :none}]


            #_:fill (m7/url (name :lg2))
            #_:fill (m7/url (name :star))
            [:circle {:r 40
                      :cx 470
                      :cy 0
                      :stroke (hsl [3 70 70 1])
                      :stroke-width .5
                      :fill (m7/url (name :lg2))

                      }

             [:animateTransform {
                                 :attributeName :transform
                                 :begin (sec 0)
                                 :dur (sec 15)
                                 :type :rotate
                                 :from 0
                                 :to -12
                                 :repeatCount :indefinite
                                 :fill :freeze}]


             ]


            [:text {:font-size 56
                    :x 420
                    :y 0
                    :dx -40
                    :fill (hsl [3 70 70 1])
                    }

             ""



             ]





            [:path {:id :com-id
                    :d (m7/path `[450 -150
                                  :l ~@(map
                                        (fn [d x]
                                          (* d x))
                                        dx (cycle [ 300 70]))])
                    :fill (hsl [.2 60 55 .5])}]

            [:text {}
             [:textPath {:startOffset (m7/np [5 :%])
                         :fill (hsl [1 70 70 1])
                         :href :#com-id}
              "SCIENCE Café Scientifique"]]



            #_[:text {:font-size 56
                      :x 0
                      :y 0
                    :dx -40
                    :fill (hsl [2 70 70 1])
                    }

             "BDT 999"



             ]








            [:text {:dx -18
                    :x 250
                    :y 0
                    :fill (hsl [1 18 70 1])}




             ]


            [:circle {:r 250
                      :cx 0
                      :cy 0
                      :stroke  (hsl [1 70 70 1])
                      :stroke-width .5
                      :fill :none}]





            [:circle {:r 470
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2 70 70 1])
                      :stroke-width .3
                      :fill :none}]





            [:circle {:r 580
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2.7 70 70 1])
                      :stroke-width .3
                      :fill :none}]


            ]


           (let [p (fn [d stroke-width stroke fill]
                        [:path {:d (m7/path d)
                                :stroke-width stroke-width
                                :stroke (hsl stroke)
                                :fill (hsl fill)}])]
             [:g

              (map
               (fn [y]
                 [p [-300 y  :l 1600 0] .02
                  [1.8 70 70 1]
                  [1.8 70 70 1]])
               (range -5 5))


              (map
               (fn [x]
                 [p [x -800  :l 0 1600 ] .02
                  [2.5 70 70 1]
                  [2.5 70 70 1]])
               (range -5 5))



              ]



             )



           #_(map
              (fn [x]
                [:circle {:cx x
                          :cy (ve (+ x 1))
                          :r .05
                          :fill (hsl [5 70 70 1])}])
              (range -200 200 .1))




           (map
            (fn [x]
              [:circle {:cx x
                        :cy (ve (+ (* 2 x) 1))
                        :r .05
                        :fill (hsl [5 70 70 1])}])
            (range -200 200 .5))



           [:circle {:cx 0
                     :cy 0
                     :r .05
                     :fill (hsl [5 70 70 1])
                     }]

           #_[:circle {:cx 0
                     :cy 0
                     :r (js/Math.sqrt 5)
                     :fill :none
                     :stroke-width .01
                     :stroke (hsl [5 70 70 1])}]


           #_(map
              (fn [x]
                [:circle {:cx x
                          :cy (ve (- 3 (* x x)))
                          :r .05
                          :fill (hsl [0 70 70 1])}])
              (range -200 200 .1))


           (map
            (fn [x]
              [:circle {:cx x
                        :cy (ve (+ (* x x) (* 2 x ) -3 ))
                        :r .05
                        :fill (hsl [0 70 70 1])}])
            (range -200 200 .1))


           [:clipPath#anik
            [:circle {:r 85
                      :cx 500
                      :cy 100
                      :stroke :black
                      :storke-width 7
                      :fill :white}  ]]])]


       ])))




(defn board3 [v box g]
  [:div {:style (merge
                 (grid [100 :vh 100 :vw
                        (take 24 (repeat [8 :vh]))
                        (take 20 (repeat [8 :vh]))])
                 {:background-color (hsl [1 70 70 .5])
                  :gap ".2rem"})}


   [:div {:style
          (m7/css
           [[8 4  15 8
             :center :flex-start  3.3 :rem :column]
            [1.5 70 80  .3] []
            {:gap ".1rem"
             :color (hsl [0 30 30 1])
             :z-index 10}])}
    (map (fn [a]
           a)  g)]


   [:div {:style
          (m7/css
           [[2 8  3 18
             :center :flex-start  2.2 :rem :column]
            [1.5 70 80  .3] []
            {:gap ".1rem"
             :color (hsl [0 30 30 1])
             :z-index 10}])}

    (map (fn [e]
           (m7/x e)) v)


    ]])







(defn app
  []
  (let [[c ck] (react/useState "hello")
        ref (react/useRef)
        p (fn [svg x y]
            (let [p (js/DOMPoint. x y)
                  t (-> svg
                        (.getScreenCTM)
                        (.inverse))
                  xy (-> p
                         (.matrixTransform t))]
              (ck [(fix (-> xy .-x) 1)
                   (fix (-> xy .-y) 1) ])))
        arr [["মোঃ গোলাম কিবরিয়া  খান চৌধুরী" [137 65] 12]
             [
              "11 07 2021"
              [
               137 78] 6]
             [
              "সম্মিলিত সামরিক হাসপাতালে (সিএমএইচ) ঢাকা ঢাকা সেনানিবাস ঢাকা সেনানিবাস - ১২০৬"
              [
               137 84] 6]
             ["০১ ০১ ১৯৫২ নেত্রকোণা" [137 100] 9]
             ["মৃত হেকিম উদ্দিন খান চৌধুরী" [137 128] 9]
             ["মৃত জোবেদা খানম" [137 147] 9]
             ["আছিয়া আক্তার খাতুন" [137 167] 9]
             ["বাড়ী# ১৯২, রোড# ২ (নতুন) পুরাতন# ৫, এ্যভিনিউ#৩,  ডিওএইচএস  মিরপুর, মিরপুর পল্লবী ১২১৬" [162 186] 5]
             ["বাড়ী# ১৯২, রোড# ২ (নতুন) পুরাতন# ৫, এ্যভিনিউ#৩,  ডিওএইচএস  মিরপুর, মিরপুর পল্লবী ১২১৬" [162 200] 5]
             ["আছিয়া আক্তার খাতুন" [158 219] 7]
             ["স্ত্রী" [272 219] 7]

             ["01 Jan 1963" [330 219] 5]

             ["আশ্রার আহমেদ  খান চৌধুরী" [158 229] 7]
             ["পুত্র"  [272 229] 7]
             ["08 Oct 1982" [330 229] 5]

             ["শায়েখ  আহমেদ  খান চৌধুরী" [158 238] 7]
             [
              "পুত্র" [272 238] 7]
             ["31 Aug 1985" [330 238] 5]

             ["কানিজ ফাতিমা" [158 246] 7]
             ["কন্যা" [272 246] 7]
             ["02 Feb 1990" [330 246] 5]
             ["আছিয়া আক্তার খাতুন" [136 397] 7]


             ]]
    [:svg {:ref ref
           :on-click (fn [e]


                       (p
                        (-> e .-target)
                        (-> e  .-clientX)
                        (-> e  .-clientY) )
                       (js/console.log
                          "hello2" (-> e .-target (.getBoundingClientRect))))

           :style {:height "100%"
                   :width "100%"

                   }
           :viewBox (m7/space
                     [0 0 400 600])}
     [:image {:height 600
              :width 400
              :x 0
              :y 0
              :href "ll.jpg"}]

     (map
      (fn [[s [x y] sz] ]
        [:text {:x x
                :y y
                :font-size sz}
         s])
      arr)

     [:text {:x 50
             :y 50}
      (m7/space c)]]
    )
  )








(defn grammer-eng []
  (let [[text set-text] (react/useState "")
        [slider set-slider] (react/useState -1)
        animate-ref (react/useRef)
        _ (react/useEffect
           (fn []
             (if (and animate-ref
                      (-> animate-ref .-current))
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
             (js/console.log "1")
             ))

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
          m 30
          d 9.4
          scale2 [[.5 .5] [-0.5 .5]]
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .1])
                      :gap ".2rem"})}



       #_(map-indexed
          (fn [n d]
            [:div {:ref (if (= n slider) animate-ref nil)
                   :on-mouse-enter (fn [e]
                                     (set-slider n))
                   :style
                   (m7/css
                    [[ (+ 3 n) 1 (+ 1 2) 4  :center :center  1.5 :rem :column]
                     [(if (= slider n) 3 1) 70 (+ 50 (* 2 n)) .3] []
                     (into
                      {:font-size (m7/np [1.7 :rem])
                       :font-family "Roboto Flex"
                       :gap (m7/np [1 :rem])
                       :color (hsl [1 70 (if (= slider n) 40 90) 1])
                       :z-index 4
                       :cursor :grab}
                      {})])}
             [:input  {:type :text
                       :width "100%"
                       :placeholder d}]])
          ["I does"  "you do" "He is" "She is"  "we are"  "They are"])



       (map-indexed
        (fn [n d]
          [:div {:ref (if (= n slider) animate-ref nil)
                 :on-mouse-enter (fn [e]
                                   (set-slider n))
                 :style
                 (m7/css
                  [[ (+ 2 n) 1 (+ 1 2) 18  :center :center  1.1 :rem :column]
                   [(if (= slider n) 3 1) 70 (+ 50 (* 2 n)) .3] []
                   (into
                    {:font-size (m7/np [.7 :rem])
                     :font-family "Roboto Flex"
                     :gap (m7/np [1 :rem])
                     :color (hsl [1 70 (if (= slider n) 40 90) 1])
                     :z-index 4
                     :cursor :grab}
                    {})])}
           [:input  {:style {:type :text
                             :font-size (m7/np [1.7 :rem])
                             :height "100%"
                             :width "100%"
                             }
                     :placeholder d}]])
       ["All the ticket has been booked out"  "You have 8 minutes left, you have finished just one verb out of 12. Your are running against the time." "tapped out" "carry"  "Bring"  "catch" "am,is" "are" "cut" "put"])


       #_(map-indexed
          (fn [n d]
            [:div {:ref (if (= n slider) animate-ref nil)
                   :on-mouse-enter (fn [e]
                                     (set-slider n))
                   :style
                   (m7/css
                    [[ (+ 3 n) 1 (+ 1 2 5) 4  :center :center  1.5 :rem :column]
                     [(if (= slider n) 3 1) 70 (+ 50 (* 2 n)) .3] []
                     (into
                      {:font-size (m7/np [1.7 :rem])
                       :font-family "Roboto Flex"
                       :gap (m7/np [1 :rem])
                       :color (hsl [1 70 (if (= slider n) 40 90) 1])
                       :z-index 4
                       :cursor :grab}
                      {})])}
             [:input  {:type :text
                       :placeholder d}]])
          ["I am going" "you are going" "He is going"  "She is going"
           "we  are going"
           "They are going"])


       #_(map-indexed
        (fn [n d]
          [:div {:ref (if (= n slider) animate-ref nil)
                 :on-mouse-enter (fn [e]
                                   (set-slider n))
                 :style
                 (m7/css
                  [[ (+ 3 n) 1 (+ 1 2 5) 4  :center :center  1.5 :rem :column]
                   [(if (= slider n) 3 1) 70 (+ 50 (* 2 n)) .3] []
                   (into
                    {:font-size (m7/np [1.7 :rem])
                     :font-family "Roboto Flex"
                     :gap (m7/np [1 :rem])
                     :color (hsl [1 70 (if (= slider n) 40 90) 1])
                     :z-index 4
                     :cursor :grab}
                    {})])}
           d])
        ["Yo estoy" "Tu estas" "el esta" "ella esta"  "nosotros estamos" "Usedes estan"
         "Ellos estan"])

       #_[:div {:style
              (m7/css
               [[2 10  12 15
                 :center :center  1.8 :rem :column]
                [1.5 70 80  .5] []
                {:gap ".1rem"
                 :color (hsl [0 30 60 1])
                 :z-index 10}])}
        (reduce
         (fn [acc e]
           (if (some #(= e %)  ["was" "trotting" "came"
                                "coming" "cowered" "looked" "cried"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [.5 95 70 1])
                                    :color (hsl [1 40 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))
         [:div ]
         (str/split "One day, long ago, a Jackal was trotting through a narrow and rocky pass, when he came face to face with a Lion, who was coming from the opposite direction. Realising that he was too close to the lion to escape, the Jackal was afraid. In a flash, he thought of a plan. He cowered down on the cliff path, looked above him and cried , \"Help\" " #"\s+"))


        ]



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
        "b"
        ]


       [:div {:style (m7/css
                      [[8 5 1 18 :center :center 2.2 :rem ]
                       [1 70 90 .1] [] {:gap "1rem"
                                        :color (hsl [3 20 30 1])
                                        :z-index 2}])}

        #_[:span {:style {:background-color (hsl [1 70 70 .5])}}"9th October"]








        ]


       [:div {:style (m7/css
                      [[2 10 2 23 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"
                                       :z-index 1}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [flames]


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

           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 6
                      :fill (m7/url (name :lg2))
                      }]]

           [:radialGradient {
                             :id (name :lg1)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [0 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [.2 70 70 .9])}]
            [:stop  {:offset .77
                     :stop-color (hsl [1 70 70 .8])}
             [:animate {:attributeName :offset
                        :id :f113
                        :begin 0
                        :from .88
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f123.end
                        :from 1
                        :to .88
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]]
            ]


           [:radialGradient {
                             :id (name :lg2)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [3 40 40 .7])}]
            [:stop  {:offset .55
                     :stop-color (hsl [3.3 60 60 .8])}]


            [:stop  {:offset .97
                     :stop-color (hsl [1 70 70 .2])}
             [:animate {:attributeName :offset
                        :id :f114
                        :begin 0
                        :from .55
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f114.end
                        :from 1
                        :to .55
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin 0
                        :id :f115
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin :f115.end
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [13 "s"])
                        :repeatCount :indefinite}]]

            ]
           ;; :indefinite
           [:marker {:id (name :mb2)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [-3 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]






           (grid-on 1 1)

           [:g {:transform (m7/tranfrom [[:translate [180 50] ]
                                         [:scale [.7 .7]]
                                         ])}
            [:path {:d (m7/path [0 0 :l 0 (ve (* 2 30 ))
                                 20 0 0 (* 2 30 )
                                 -20 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 1])}]


            [:path#b1 {:d (m7/path [-10 0 :l 0 (ve (* 2 30 ))])
                       :stroke-width .5
                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))
                       :stroke (hsl [2 30 30 1])
                       :fill :none}]



            [:path#tri2 {:d (m7/path [20 (ve (* 2 30 )) :l (* 16 2) (ve (* 2 7 ))
                                      0 (ve (ve (* 2 7 ))) (ve (* 16 2)) 0])
                    :stroke (hsl [.5 70 70 1])
                    :stroke-width .5
                    :fill (hsl [1 70 70 1])}]







            [:path {:d (m7/path [ (+ 20 32) 0 :l 0 (ve (* 2 37 ))
                                 20 0 0 (* 2 37 )
                                 -20 0])
                    :stroke-width .5
                    :fill (hsl [2 70 70 1])}]

            [:path#b2 {:d (m7/path [ (+ 50 32) 0 :l
                                    0 (ve (* 2 37))])
                       :stroke (hsl [1 20 20 1])
                       :stroke-width .5

                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))

                       :fill (hsl [1 70 70 1])}]



            [:path#b3 {:d (m7/path [(* 1 20) 0 :l (* 16 2) 0])
                       :stroke (hsl [1 20 20 1])
                       :stroke-width .5

                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))

                       :fill (hsl [1 70 70 1])}]

            [:text {:style {:font-size 5}}

             [:textPath {:href :#tri2
                         :font-size 3}
              "B"]

             [:textPath {:href :#tri2
                         :startOffset (* 2 17)
                         :font-size 3}
              "A"]

             [:textPath {:href :#tri2
                         :startOffset (+ 13 (* 2 17))
                         :font-size 3}
              "C"]

             [:textPath {:href :#b1
                         :startOffset "40%"
                         }
              "30m"]

             [:textPath {:href :#b2
                         :startOffset "40%"}
              "37m"]

             [:textPath {:href :#b3
                         :startOffset "40%"}
              "14m"]]



            ]


           [:g
            [:g#logo {:transform (m7/tranfrom [[:scale [.3 .3]]])}
             (map
             (fn [x]
               [:g


                [:path
                 {:d
                  (str (path
                        [ 0 0 :l
                         (* 20 3) (ve (* 2 2 2))
                         (* 20 5) (ve (* 2 5 5))


                         (* 20 1)
                         (ve (- (* 2 9 9) (* 2 7 7)))
                          (ve (* 20 9))  0
                         ])
                       "z")

                  :transform
                  (m7/tranfrom
                   [
                    [:translate [(* m 20)
                                 (* 2 d d)]]
                    [:scale x]
                    ])
                  :stroke (hsl [4 70 70 1])
                  :stroke-width 10
                  :fill  (hsl [0 70 70 1])
                  }
                 ]

                [:path
                   {:d
                    (str (path
                          [ 0 0 :c
                           (* 20 3) (ve (* 2 2 2))
                           (* 20 5) (ve (* 2 5 5))
                           (* 20 7) (ve (* 2 7 7))
                           :c
                           (* 20 1)
                           (ve (- (* 2 9 9) (* 2 7 7)))
                           (* 20 2) (ve (- (* 2 11 11 ) (* 2 7 7)))
                           (* 20 4) (ve (- (* 2 13 13) (* 2 7 7)))
                           :l (ve (* 20 9))  0
                           ])
                         "z")

                    :transform
                    (m7/tranfrom
                     [
                      [:translate [(* m 20)
                                   (* 2 d d)]]
                      [:scale x]
                      ])
                    :stroke (hsl [4 70 70 1])
                    :stroke-width 10
                    :fill  (hsl [0 70 70 1])
                    }
                   ]
                ])
             scale2)


             [airplane2 [
                           [:translate [600 120]]
                        [:scale [.3 .3]]
                        [:rotate -90]]]
            ]



            #_[:circle {:r 90
                      :cx 0
                      :cy 0
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg2))}]

            [:circle {:r 20
                      :cx 0
                      :cy 0
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg2))}]


            #_[:circle {:r 90
                      :cx 0
                      :cy 0
                      :stroke (hsl [3 70 70 .5])
                      :stroke-width .5
                      :fill (m7/url (name :star))}

             #_[:animateTransform {
                                   :attributeName :transform
                                   :begin (sec 0)
                                   :dur (sec 15)
                                   :type :rotate
                                   :from 0
                                   :to -360
                                   :repeatCount :indefinite
                                   :fill :freeze}]


             ]



            [:circle {:r 350
                      :cx 0
                      :cy 0
                      :stroke  (hsl [0 70 70 1])
                      :stroke-width .5
                      :fill :none}]


            #_:fill (m7/url (name :lg2))
            #_:fill (m7/url (name :star))
            [:circle {:r 40
                      :cx 470
                      :cy 0
                      :stroke (hsl [3 70 70 1])
                      :stroke-width .5
                      :fill (m7/url (name :lg2))

                      }

             [:animateTransform {
                                 :attributeName :transform
                                 :begin (sec 0)
                                 :dur (sec 15)
                                 :type :rotate
                                 :from 0
                                 :to -12
                                 :repeatCount :indefinite
                                 :fill :freeze}]


             ]


            [:text {:font-size 56
                    :x 420
                    :y 0
                    :dx -40
                    :fill (hsl [3 70 70 1])
                    }

             ""



             ]





            [:path {:id :com-id
                    :d (m7/path `[450 -150
                                  :l ~@(map
                                        (fn [d x]
                                          (* d x))
                                        dx (cycle [ 300 70]))])
                    :fill (hsl [.2 60 55 .5])}]

            [:text {}
             [:textPath {:startOffset (m7/np [5 :%])
                         :fill (hsl [1 70 70 1])
                         :href :#com-id}
              "English"]]



            #_[:text {:font-size 56
                      :x 0
                      :y 0
                    :dx -40
                    :fill (hsl [2 70 70 1])
                    }

             "BDT 999"



             ]








            [:text {:dx -18
                    :x 250
                    :y 0
                    :fill (hsl [1 18 70 1])}




             ]


            [:circle {:r 250
                      :cx 0
                      :cy 0
                      :stroke  (hsl [1 70 70 1])
                      :stroke-width .5
                      :fill :none}]





            [:circle {:r 470
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2 70 70 1])
                      :stroke-width .3
                      :fill :none}]





            [:circle {:r 580
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2.7 70 70 1])
                      :stroke-width .3
                      :fill :none}]


            ]





           [:clipPath#anik
            [:circle {:r 85
                      :cx 500
                      :cy 100
                      :stroke :black
                      :storke-width 7
                      :fill :white}  ]]])]


       ])))




(defn eng-tense []
  (let [[text set-text] (react/useState "")
        [slider set-slider] (react/useState -1)
        animate-ref (react/useRef)
        _ (react/useEffect
           (fn []
             (if (and animate-ref
                      (-> animate-ref .-current))
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
             (js/console.log "1")
             ))

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
          m 30
          d 9.4
          scale2 [[.5 .5] [-0.5 .5]]
          sen   (fn [a b]
            (reduce
             (fn [acc e]
               (if (some #(= e %) a)
                 (conj acc
                       [:span " "
                        [:span {:style {:background-color (hsl [1 70 70 1])
                                        :color (hsl [1 20 40 1])
                                        :font-weight 600}}
                         (str  e)]])



                 (conj acc [:span (str " "  e)])))

             [:div ]


             (str/split b  #"\s+")))
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .1])
                      :gap ".2rem"})}







       [:div {:style
              (m7/css
               [[4 10  4 20
                 :flex-start :flex-start  3.8 :rem :column]
                [1.5 70 80  .2] []
                {:gap ".1rem"
                 :color (hsl [.4 30 30 1])
                 :z-index 20
                 }])}


        [m7/x `[= [- [- [:m 4 [:p x 2]]]
                   [:m 9 x ]] 14]]

        [m7/x `[= [- [- [:m 4 [:p x 2]]]
                   [:m 9 x ] 14] 0]]

      #(sen
         ["put"] "We put down a $1,000 deposit.")

        #_[:div "last year covid situation broke out."]

        #_[:div "Don’t put off your homework to the last minute."]

        #_[:div "The storm put the game off by a week."]
        #_[:div "Please be quiet. I’m trying to concentrate and you’re putting me off."]
        #_[:div "Almost drowning put him off swimming."]
        #_[:div  "They frequently put down their little sister for walking slowly."]
        #_[:div "My mom always put me down for eating slowly"]

        #_[:div "Put it down in the paper what we have learned today"]



        (comment
         #_[:div "Put down

Meaning: Insult, belittle, or demean
Example: They frequently put down their little sister for walking slowly.
Put down

Meaning: Pay
Example: We put down a $1,000 deposit.
Put down

Meaning: Halt, eliminate, stop, or squelch, often by force
Example: The government quickly put down the insurrection.
Put down

Meaning: Euthanize (an animal)
Example: Rex was in so much pain, they had to put him down.
Put down

Meaning: Write (something)
Example: Put down the first thing you think of on this piece of paper.
Put down

Meaning: Terminate a call; to hang up.
Example: Don’t put the phone down. I want a quick word with him,too.
Put down

Meaning: Add a name to a list
Example: I’ve put myself down for the new Spanish conversation course.
Put down

Meaning: Make prices, or taxes, lower
Example: BP are putting petrol and diesel down in what could be the start of a price war.
Put down

Meaning: Place a baby somewhere to sleep
Example: I had just put Mary down when you rang. So now she’s crying again.
Put down

Meaning: Land
Example: The pilot managed to put down in a nearby farm field.
Put down

Meaning: Drop someone off, or let them out of a vehicle
Example: The taxi put him down outside the hotel.
Put down

Meaning: Cease, temporarily or permanently, reading (a book)
Example: I was unable to put down The Stand: it was that exciting.
Put down as

Meaning: Assume someone has a particular character from very little information
Example: I put him down as ignorant, but then discovered he is, in fact, a university professor!
Put down for

Meaning: Record that someone has offered to help, or contribute something
Example: Put me down for one of the drivers.
Put down to

Meaning: State the cause of a situation
Example: I put the high crime rate down to the high unemployment."])


        #_[m7/x '[= [:p [:b [+ x 9]] 2]
                [+ [:p x 2]  [:p [:b [+ x 7]] 2]]]]


        #_(let []
          [m7/x '[= 0
                  [-  [:p x 2]   [:m [:b [- 8 4]] x]  (* 4 8)]]])
        #_[m7/x `[= [:p [:b [+ x 9] ] 2]
                [+ [:p x 2] [:p [:b [+ x 7] ] 2]]]]


        #_[m7/x `[= [+ [:p x 2] [:m [* 2 [:b [+ 7 2]]] x] [:p 9 2]]
                [+ [:p x 2] [:p [:b [+ x 7] ] 2]]]]


        #_[m7/x `[= [- [:p x 2] [:m [:b [- 8 4]] x] [* 4 8]]
                0]]

        #_[m7/x `[= [:m [:b [- x 8]] [:b [+ x 4]]] 0] ]

        #_[m7/x '[= [:m 215.2 cm]
                [:m [215.2 100 ] m] ]]
        #_[:textarea {:rows 4
                      :cols 50
                      :on-change (fn [e]
                                   (set-text
                                    (-> e
                                        .-target
                                        .-value)))}]]





       [:div {:style (m7/css
                      [[2 10 2 23 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"
                                       :z-index 1}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [flames]


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

           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 6
                      :fill (m7/url (name :lg2))
                      }]]

           [:radialGradient {
                             :id (name :lg1)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [0 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [.2 70 70 .9])}]
            [:stop  {:offset .77
                     :stop-color (hsl [1 70 70 .8])}
             [:animate {:attributeName :offset
                        :id :f113
                        :begin 0
                        :from .88
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f123.end
                        :from 1
                        :to .88
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]]
            ]


           [:radialGradient {
                             :id (name :lg2)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [3 40 40 .7])}]
            [:stop  {:offset .55
                     :stop-color (hsl [3.3 60 60 .8])}]


            [:stop  {:offset .97
                     :stop-color (hsl [1 70 70 .2])}
             [:animate {:attributeName :offset
                        :id :f114********-
                        :begin 0
                        :from .55
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f114.end
                        :from 1
                        :to .55
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin 0
                        :id :f115
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin :f115.end
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [13 "s"])
                        :repeatCount :indefinite}]]

            ]
           ;; :indefinite
           [:marker {:id (name :mb2)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [-3 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:rotate 0]])
                    :fill (m7/hsl [.4 70 70 1])}]]

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]






           (grid-on 1 1)
           #_[:g {:transform (m7/tranfrom [[:translate [-150 -200]]
                                         [:scale [.66 .6]]])}
            [bdmap/bd-map]]




           [:g
            [:g#logo
            (map
             (fn [x]
               [:g


                [:path
                 {:d
                  (str (path
                        [ 0 0 :l
                         (* 20 3) (ve (* 2 2 2))
                         (* 20 5) (ve (* 2 5 5))


                         (* 20 1)
                         (ve (- (* 2 9 9) (* 2 7 7)))
                          (ve (* 20 9))  0
                         ])
                       "z")

                  :transform
                  (m7/tranfrom
                   [
                    [:translate [(* m 20)
                                 (* 2 d d)]]
                    [:scale x]
                    ])
                  :stroke (hsl [4 70 70 .1])
                  :stroke-width 10
                  :fill  (hsl [0 70 70 .1])
                  }
                 ]

                [:path
                   {:d
                    (str (path
                          [ 0 0 :c
                           (* 20 3) (ve (* 2 2 2))
                           (* 20 5) (ve (* 2 5 5))
                           (* 20 7) (ve (* 2 7 7))
                           :c
                           (* 20 1)
                           (ve (- (* 2 9 9) (* 2 7 7)))
                           (* 20 2) (ve (- (* 2 11 11 ) (* 2 7 7)))
                           (* 20 4) (ve (- (* 2 13 13) (* 2 7 7)))
                           :l (ve (* 20 9))  0
                           ])
                         "z")

                    :transform
                    (m7/tranfrom
                     [
                      [:translate [(* m 20)
                                   (* 2 d d)]]
                      [:scale x]
                      ])
                    :stroke (hsl [4 70 70 .1])
                    :stroke-width 10
                    :fill  (hsl [0 70 70 .2])
                    }
                   ]
                ])
             scale2)


             [airplane2 [
                         [:translate [600 120]]
                         [:scale [.3 .3]]
                         [:rotate -90]]]
            ]



            #_[:circle {:r 90
                      :cx 0
                      :cy 0
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg2))}]

            [:circle {:r 250
                      :cx 0
                      :cy 0
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg2))}]


            #_[:circle {:r 90
                      :cx 0
                      :cy 0
                      :stroke (hsl [3 70 70 .5])
                      :stroke-width .5
                      :fill (m7/url (name :star))}

             #_[:animateTransform {
                                   :attributeName :transform
                                   :begin (sec 0)
                                   :dur (sec 15)
                                   :type :rotate
                                   :from 0
                                   :to -360
                                   :repeatCount :indefinite
                                   :fill :freeze}]


             ]



            [:circle {:r 350
                      :cx 0
                      :cy 0
                      :stroke  (hsl [0 70 70 1])
                      :stroke-width .5
                      :fill :none}]


            #_:fill (m7/url (name :lg2))
            #_:fill (m7/url (name :star))
            [:circle {:r 40
                      :cx 470
                      :cy 0
                      :stroke (hsl [3 70 70 1])
                      :stroke-width .5
                      :fill (m7/url (name :lg2))

                      }

             [:animateTransform {
                                 :attributeName :transform
                                 :begin (sec 0)
                                 :dur (sec 15)
                                 :type :rotate
                                 :from 0
                                 :to -12
                                 :repeatCount :indefinite
                                 :fill :freeze}]


             ]


            [:text {:font-size 56
                    :x 420
                    :y 0
                    :dx -40
                    :fill (hsl [3 70 70 1])
                    }

             ""



             ]





            [:path {:id :com-id
                    :d (m7/path `[450 -150
                                  :l ~@(map
                                        (fn [d x]
                                          (* d x))
                                        dx (cycle [ 300 70]))])
                    :fill (hsl [.2 60 55 .5])}]

            [:text {}
             [:textPath {:startOffset (m7/np [5 :%])
                         :fill (hsl [1 70 70 .3])
                         :href :#com-id}
              "SCIENCE Café Scientifique"]]



            #_[:text {:font-size 56
                      :x 0
                      :y 0
                    :dx -40
                    :fill (hsl [2 70 70 1])
                    }

             "BDT 999"



             ]








            [:text {:dx -18
                    :x 250
                    :y 0
                    :fill (hsl [1 18 70 .5])}




             ]


            [:circle {:r 250
                      :cx 0
                      :cy 0
                      :stroke  (hsl [1 70 70 .5])
                      :stroke-width .5
                      :fill :none}]





            [:circle {:r 470
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2 70 70 .5])
                      :stroke-width .3
                      :fill :none}]





            [:circle {:r 580
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2.7 70 70 .5])
                      :stroke-width .3
                      :fill :none}]


            ]


           [:g#buliding {:transform (m7/tranfrom [[:translate [100 50] ]
                                                  [:scale [3 3]]])}
            [:path {:d (m7/path [0 0 :l 0 (ve (* 2 20 ))
                                 80 0 0 (* 2 20 )
                                 -80 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 .1])}]


            [:path#b0 {:d (m7/path [0 0 :l 80 (ve (* 2 20 ))])
                    :stroke (hsl [2 20 40 .1])
                    :stroke-width .5

                    :fill (hsl [2 70 70 .1])}]



            [:path#b1 {:d (m7/path [-10 0 :l 0 (ve (* 2 20 ))])
                       :stroke-width .5
                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))
                       :stroke (hsl [2 30 30 .1])
                       :fill :none}]









            [:path#b2 {:d (m7/path [ (+ 50 40) 0 :l
                                    0 (ve (* 2 20))])
                       :stroke (hsl [1 20 20 1])
                       :stroke-width .5

                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))

                       :fill (hsl [1 70 70 .1])}]



            [:path#b3 {:d (m7/path [0 10 :l (* 40 2) 0])
                       :stroke (hsl [1 20 20 .1])
                       :stroke-width .5

                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))

                       :fill (hsl [1 70 70 .1])}]


            [:path#b4 {:d (m7/path [0 -50 :l (* 40 2) 0])
                       :stroke (hsl [1 20 20 .1])
                       :stroke-width .5

                       :marker-end (m7/url (name :mb2))
                       :marker-start (m7/url (name :mb2))

                       :fill (hsl [1 70 70 .1])}]





            #_[:text {:style {:font-size 5}}


             [:textPath {:href :#b0
                         :startOffset "40%"
                         }
              "(x + 9) m"]

             [:textPath {:href :#b1
                         :startOffset "40%"
                         }
              "x m"]

             [:textPath {:href :#b2
                         :startOffset "40%"}
              "x m"]

             [:textPath {:href :#b3
                         :startOffset "40%"}

              "(x + 7)m"
              ]

             [:textPath {:href :#b4
                         :startOffset "40%"}
              "(x + 7)m"]

             ]



            ]





           [:clipPath#anik
            [:circle {:r 85
                      :cx 500
                      :cy 100
                      :stroke :black
                      :storke-width 7
                      :fill :white}  ]]])]


       ])))


(defn home-planets-banners-exp []
  (let [[text set-text] (react/useState "")
        [slider set-slider] (react/useState -1)
        animate-ref (react/useRef)
        _ (react/useEffect
           (fn []
             (if (and animate-ref
                      (-> animate-ref .-current))
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
                    )))))

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
          m 30
          d 9.4
          scale2 [[.5 .5] [-0.5 .5]]
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {
                      :background-color (hsl [1 70 70 .1])
                      :gap ".2rem"})}

       (map-indexed
        (fn [n d]
          [:div {:ref (if (= n slider) animate-ref nil)
                 :on-mouse-enter (fn [e]
                             (set-slider n))
                 :style
                 (m7/css
                  [[1 1 (+ 1 (* 4 n)) 4  :center :center  1.5 :rem :column]
                   [(if (= slider n) 3 1) 70 (+ 50 (* 2 n)) 1] []
                   (into
                    {:font-size (m7/np [1.7 :rem])
                     :font-family "Roboto Flex"
                     :gap (m7/np [1 :rem])
                     :color (hsl [1 70 (if (= slider n) 40 90) 1])
                     :z-index 4
                     :cursor :grab}
                    {})])}
           d])
        ["Math" "Physics" "Chemistry" "CSE" "Arts & Fation" "English"])


       [:div {:style
              (m7/css
               [[2 10  4 19
                 :center :center 2.9 :rem :column]
                [1.5 70 80  .5] []
                {:gap ".1rem"
                 :color (hsl [0 30 30 1])
                 :z-index 10}])}


        [m7/mx `[* 999 112]]




        #_[m7/m '[= [:m 3  y z] 9]]


        #_[m7/m '[= z [3 y]]]


        #_[m7/m '[= z [+ x y]]]



        #_[m7/m '[= [:p z 3] [+ [:p x 3]  [:m 9 x]  [:p y 3]]]]

        #_[m7/m '[= [:p z 3] [+ [:p x 3]  [:m 9 x]  [:p y 3]]]]











        #_[m7/m '[= [:p a x] b]]


        #_[m7/m '[= x [:m [:k log a] b]]]





        #_[m7/m '[= [:p a xy] c]]


        #_[m7/m '[= [:p a x] b]]


        #_[m7/m '[= [:p b y] c]]


        #_[m7/m '[= x [:m [:k log a] M]]]
        #_[m7/m '[= [1 y] [1 [:m [:k log b] M]] [:m [:k log M] b]]]

        #_[m7/m '[= [x y] [:m [:m [:k log a] M] [:m [:k log M] b]
                         ]]]

        #_[m7/m '[= [x y] [:m [:k log a] b]]]
        #_[m7/m '[= [:p a x] M]]


        #_[m7/m '[= 0.12 [[* 4 3]
                   [* 4 25]]]]


        #_[m7/m '[=
                [+ [- [:m 4 x ]] [:m 3 y] ]
                [- 2]]]


        #_[m7/m '[=
                [- [:m 5 x] [:m 3 y]]
                7
                ]]

        #_[m7/m '[= [:p [:b [a b]] x]  [[:p a x] [:p b x]]]]
        #_[m7/m '[= y [:m [:k log b] M]]]
        #_[:div {:style {:background-color (hsl [1 22 22 1])
                       :height ".2rem"
                       :width "100%"}}]


        #_[m7/m '[= [+ [:p x 3] [:m 9 x] [:p y 3]] [+ 26 [:p y 3]]]]


        #_[m7/m '[= [:p [:b [3 y]] 3] [+ 26 [:p y 3]]]]






        #_[m7/m '[= [27 [:p y 3]] [+ 26 [:p y 3]]]]


        #_[m7/m '[= [27 [:p y 3]] [+ 26 [:p y 3]]]]


        #_[m7/m '[= 0 [- [+  [:p [:b [:p y 3]] 2] [:m [:b [- 27 1]] [:p y 3]] ] 27]]]



        #_[m7/m '[= 0 [:m [:b [+ [:p y 3] 27]]  [:b [- [:p y 3] 1]]]]]


        #_[m7/m '[= [- [:p y 3] 1] 0]]


        #_[m7/m '[= [:s [:p y 3] 3] [:s [:p 1 3] 3]]]




        #_[m7/m '[=  [+ [:p y 3] 27] 0]]



        #_[m7/m '[= [27 [:p y 3]] [+ 26 [:p y 3]]]]

        #_[m7/m '[=  [:p b [1 x]] a]]

        #_[m7/m '[= [1 [:m [:k log a] b]] [:m [:k log b] a]]]

        #_[m7/m '[= [:m [:k log a] b] [1 [:m [:k log b] a]]]]
        #_[m7/m '[=  [:m [:m [:k log a ] b]  [:m [:k log b ] c]]  [:m [:k log a ] c]]]





        #_[m7/m '[= xy [:m [:m [:k log a] M] [:m [:k log b] M]]]]

        #_[m7/m '[= xy [:m [:k log a] b]]]


        #_[m7/m '[= [:p b y] M [:p a x]]]







        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["worked"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 40 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))
         [:div ]
         (str/split "I worked very hard this morning" #"\s+"))



       #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["worked"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 40 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))
         [:div ]
         (str/split "I've worked very hard this morning" #"\s+"))


        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["was"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 40 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))
         [:div ]
         (str/split "October was a successful month" #"\s+"))



        #_(reduce
           (fn [acc e]
             (if (some #(= e %)  ["has" "been"] )
               (conj acc
                     [:span " "
                      [:span {:style {:background-color (hsl [.5 95 70 1])
                                      :color (hsl [1 40 40 1])
                                      :font-weight 600}}
                       (str  e)]])



               (conj acc [:span (str " "  e)])))
           [:div ]
           (str/split "So far November has been a successful month" #"\s+"))


        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["had"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [.5 95 70 1])
                                    :color (hsl [1 40 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))

         [:div ]
         (str/split "I've had a headache all afternoon" #"\s+"))


        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["seen"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [.5 95 70 1])
                                    :color (hsl [1 40 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))

         [:div ]
         (str/split "I've seen the video" #"\s+"))


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
           (str/split "I saw the video" #"\s+"))








        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["tasted"  "lived" "was" "had" "made" "poured" "walked" "might" "called" "looked"
                                "sent" "peeped" "lifted"
                                "did" "suspected" "would" "opened" "went" "saw" "waited"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 40 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))
         [:div ]
         (str/split "First she tasted the porridge of the Great Big Bear, and that was too hot for her. Next she tasted the porridge of the Middle-sized Bear, but that was too cold for her. And then she went to the porridge of the Little Wee Bear, and tasted it, and that was neither too hot nor too cold, but just right, and she liked it so well that she ate it all up, every bit!" #"\s+")


         )


        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["were" "lived" "was" "had" "made" "poured" "walked" "might" "called" "looked"
                                "sent" "peeped" "lifted"
                                "did" "suspected" "would" "opened" "went" "saw" "waited"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 40 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))
         [:div ]
         (str/split "Then Goldilocks, who was tired, for she had been catching butterflies instead of running on her errand, sate down in the chair of the Great Big Bear, but that was too hard for her. And then she sate down in the chair of the Middle-sized Bear, and that was too soft for her. But when she sat down in the chair of the Little Wee Bear, that was neither too hard nor too soft, but just right. So she seated herself in it, and there she sate till the bottom of the chair came out, and down she came, plump upon the ground; and that made her very cross, for she was a bad-tempered little girl." #"\s+")


         )

        #_(reduce
         (fn [acc e]
           (if (some #(= e %)  ["were" "lived" "was" "had" "made" "poured" "walked" "might" "called" "looked"
                                "sent" "peeped" "lifted"
                                "did" "suspected" "would" "opened" "went" "saw" "waited"] )
             (conj acc
                   [:span " "
                    [:span {:style {:background-color (hsl [1 70 70 1])
                                    :color (hsl [1 40 40 1])
                                    :font-weight 600}}
                     (str  e)]])



             (conj acc [:span (str " "  e)])))
         [:div ]
         (str/split "Now, being determined to rest, Goldilocks went upstairs into the bedchamber in which the Three Bears slept. And first she lay down upon the bed of the Great Big Bear, but that was too high at the head for her. And next she lay down upon the bed of the Middle-sized Bear, and that was too high at the foot for her. And then she lay down upon the bed of the Little Wee Bear, and that was neither too high at the head nor at the foot, but just right. So she covered herself up comfortably, and lay there till she fell fast asleep."
           #"\s+")


         )





        #_[m7/m '[=  [:p [:b [:p 2 2]] 2]  [:p 2 [* 2 2]]   [:p 2 4]]]


        #_[m7/m '[= [:p 2 [+ 8 12]] [* [:p 2  8]  [:p 2 12]]]]

        #_[m7/m '[= [:p 2 [+ a b]] [* [:p 2  a]  [:p 2 b]]]]


        #_[m7/m '[= [:m [:k log 2] [* [:p 2  a]  [:p 2 b]]]
                [+ [:m a [:k log 2] 2]  [:m b [:k log 2] 2]] ]]


        #_[m7/m '[= [:m [:k log 2] [* 32 512]]
                  [:m [:k log 2] [:b [* [:p 2 5] [:p 2 9]]]]
                  [+ 5 9] [+ [:m [:k log 2] 32]

                           [:m [:k log 2] 512]]]]

        #_[m7/m '[= [:m [:k log 2] [* 32 512]]

                 [+ [:m [:k log 2] 32]

                         [:m [:k log 2] 512]]]]




        #_[m7/m '[= [:m f  [:b [+ a b]]]  [* [:m f a] [:m f b]]]]




        #_[m7/m '[:a [:m [:k log 2] 2048] 11]]



        #_[m7/m '[=  [:p [:b [:p 2 4]]
                      2]  [:p 2 [* 4 2]]   [:p 2 8]]]





        #_[m7/m '[=  [:p [:b [:p 2 5]]
                      2]  [:p 2 [* 5 2]]   [:p 2 10]]]

        #_[m7/m '[=  [:m [:k log 2] 1024] [:m [:k log 2] [:p 2 10]]  10 [*  2 5]]]

        #_[m7/m '[= [:m [:k log 2] [:p [:b [:p 2 5]] 2]] 10 [* 2 5 ]]]

        #_[m7/m '[= [:m [:k log 2] [:p [:b [:p 2 5]] 2]]  [* 2 [:m [:k log 2] [:p 2 5]] ]]]
        #_[m7/m '[= [:m [:k log 2] [:p 32 2]] [* 2 [:m [:k log 2] 32] ]]]


        #_[m7/m '[= [:m [:k log 2] [:p a n]] [:m n [:m [:k log 2] a] ]]]

        #_[m7/mx `[= [:p 3 15] ~(js/Math.pow 3 15)] ]

        #_[m7/m '[= [:m [:k log 2] 2048] [* 11 1] ] ]



        #_[m7/mx `[= [:m [:k log 3]  ~(js/Math.pow 3 15)] [:m 15 [:k  log 3]
                                                         3] ] ]


        #_[m7/m '[=  [:p [:b [:p 2 a]]
                    b]  [:p 2 [* a b]] ]]

        #_[m7/m '[=  [:p [:b [:p x a]]
                    b]  [:p x [* a b]] ]]
        #_[:div ""]
        #_[:textarea {:rows 4
                    :cols 50
                    :on-change (fn [e]
                              (set-text
                               (-> e
                                   .-target
                                   .-value)))}]

        ]


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




       #_(map-indexed
        (fn [n d]
          [:div {:style (m7/css
                         [[3 1 (+ 2 (* n 1)) 1  :center :center  1.5 :rem :column]
                          [(* n .2) 70 (+ 10 (* 1 n))  .2] [] {:gap ".1rem"
                                                               :z-index 4}])}

           d])
        #_(map (fn [i]
               (js/Math.pow 2 i))
             (range 0 15))

        )

       #_(map-indexed
        (fn [n d]
          [:div {:style (m7/css
                         [[4 1 (+ 2 (* n 1)) 1  :center :center  1.5 :rem :column]
                          [1 70 70  .9] [] {:gap ".1rem"
                                                               :z-index 4}])}

           d])
        (map (fn [i]
               [m7/mx `[:p 2 ~i]])
             (range 0 15)))

       [:div {:style (m7/css
                      [[5 4 4 8 :center :center 3 :rem ]
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
        [:div {:style {}}  ""]
        ]


       [:div {:style (m7/css
                      [[8 5 1 18 :center :center 2.2 :rem ]
                       [1 70 90 .1] [] {:gap "1rem"
                                        :color (hsl [3 20 30 1])
                                        :z-index 2}])}

        #_[:span {:style {:background-color (hsl [1 70 70 .5])}}"9th October"]








        ]


       [:div {:style (m7/css
                      [[2 10 2 23 :center :center 3 :rem]
                       [1 70 90 1] [] {:gap "1rem"
                                       :z-index 1}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [flames]


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

           [:pattern {:id (name :star)
                      :viewBox (space [0 0 10 10])
                      :width "10%"
                      :height "10%"}
            [:circle {:cx 5
                      :cy 5
                      :r 6
                      :fill (m7/url (name :lg2))
                      }]]

           [:radialGradient {
                             :id (name :lg1)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [0 70 70 1])}]
            [:stop  {:offset .33
                     :stop-color (hsl [.2 70 70 .9])}]
            [:stop  {:offset .77
                     :stop-color (hsl [1 70 70 .8])}
             [:animate {:attributeName :offset
                        :id :f113
                        :begin 0
                        :from .88
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f123.end
                        :from 1
                        :to .88
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]]
            ]


           [:radialGradient {
                             :id (name :lg2)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [3 40 40 .7])}]
            [:stop  {:offset .55
                     :stop-color (hsl [3.3 60 60 .8])}]


            [:stop  {:offset .97
                     :stop-color (hsl [1 70 70 .2])}
             [:animate {:attributeName :offset
                        :id :f114
                        :begin 0
                        :from .55
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f114.end
                        :from 1
                        :to .55
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin 0
                        :id :f115
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin :f115.end
                        :from (hsl [1 90 80 .2])
                        :to (hsl [1 90 80 .8])
                        :dur (m7/not-space [13 "s"])
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





           (grid-on 1 1)

           #_(line3 (ve (* (/ 180 js/Math.PI) (js/Math.atan 2))) [0 0] "2x=y")

           [line3 (ve (* (/ 180 js/Math.PI) (js/Math.atan (/ 4 3)))) [0 (* (/ 2 3) 20)] ""]




           [:g




            [:g#logo
            (map
             (fn [x]
               [:g


                [:path
                 {:d
                  (str (path
                        [ 0 0 :l
                         (* 20 3) (ve (* 2 2 2))
                         (* 20 5) (ve (* 2 5 5))


                         (* 20 1)
                         (ve (- (* 2 9 9) (* 2 7 7)))
                          (ve (* 20 9))  0
                         ])
                       "z")

                  :transform
                  (m7/tranfrom
                   [
                    [:translate [(* m 20)
                                 (* 2 d d)]]
                    [:scale x]
                    ])
                  :stroke (hsl [4 70 70 1])
                  :stroke-width 10
                  :fill  (hsl [0 70 70 1])
                  }
                 ]

                #_[:path
                   {:d
                    (str (path
                          [ 0 0 :c
                           (* 20 3) (ve (* 2 2 2))
                           (* 20 5) (ve (* 2 5 5))
                           (* 20 7) (ve (* 2 7 7))
                           :c
                           (* 20 1)
                           (ve (- (* 2 9 9) (* 2 7 7)))
                           (* 20 2) (ve (- (* 2 11 11 ) (* 2 7 7)))
                           (* 20 4) (ve (- (* 2 13 13) (* 2 7 7)))
                           :l (ve (* 20 9))  0
                           ])
                         "z")

                    :transform
                    (m7/tranfrom
                     [
                      [:translate [(* m 20)
                                   (* 2 d d)]]
                      [:scale x]
                      ])
                    :stroke (hsl [4 70 70 1])
                    :stroke-width 10
                    :fill  (hsl [0 70 70 1])
                    }
                   ]
                ])
             scale2)


             #_[airplane2 [
                        [:translate [600 120]]
                        [:scale [.3 .3]]
                        [:rotate -90]]]
            ]



            #_[:circle {:r 90
                      :cx 0
                      :cy 0
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg2))}]

            #_[:circle {:r 250
                      :cx 0
                      :cy 0
                      :filter (m7/url "flames")
                      :fill (m7/url (name :lg2))}]


            #_[:circle {:r 90
                      :cx 0
                      :cy 0
                      :stroke (hsl [3 70 70 .5])
                      :stroke-width .5
                      :fill (m7/url (name :star))}

             #_[:animateTransform {
                                   :attributeName :transform
                                   :begin (sec 0)
                                   :dur (sec 15)
                                   :type :rotate
                                   :from 0
                                   :to -360
                                   :repeatCount :indefinite
                                   :fill :freeze}]


             ]



            [:circle {:r 350
                      :cx 0
                      :cy 0
                      :stroke  (hsl [0 70 70 1])
                      :stroke-width .5
                      :fill :none}]


            #_:fill (m7/url (name :lg2))
            #_:fill (m7/url (name :star))
            [:circle {:r 40
                      :cx 470
                      :cy 0
                      :stroke (hsl [3 70 70 1])
                      :stroke-width .5
                      :fill (m7/url (name :lg2))

                      }

             [:animateTransform {
                                 :attributeName :transform
                                 :begin (sec 0)
                                 :dur (sec 15)
                                 :type :rotate
                                 :from 0
                                 :to -12
                                 :repeatCount :indefinite
                                 :fill :freeze}]


             ]


            [:text {:font-size 56
                    :x 420
                    :y 0
                    :dx -40
                    :fill (hsl [3 70 70 1])
                    }

             ""



             ]





            [:path {:id :com-id
                    :d (m7/path `[450 -150
                                  :l ~@(map
                                        (fn [d x]
                                          (* d x))
                                        dx (cycle [ 300 70]))])
                    :fill (hsl [.2 60 55 .5])}]

            [:text {}
             [:textPath {:startOffset (m7/np [5 :%])
                         :fill (hsl [1 70 70 1])
                         :href :#com-id}
              "SCIENCE Café Scientifique"]]



            #_[:text {:font-size 56
                    :x 0
                    :y 0
                    :dx -40
                    :fill (hsl [2 70 70 1])
                    }

             "BDT 999"



             ]








            [:text {:dx -18
                    :x 250
                    :y 0
                    :fill (hsl [1 18 70 1])}




             ]


            [:circle {:r 250
                      :cx 0
                      :cy 0
                      :stroke  (hsl [1 70 70 1])
                      :stroke-width .5
                      :fill :none}]





            [:circle {:r 470
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2 70 70 1])
                      :stroke-width .3
                      :fill :none}]





            [:circle {:r 580
                      :cx 0
                      :cy 0
                      :stroke  (hsl [2.7 70 70 1])
                      :stroke-width .3
                      :fill :none}]


            ]





           [:clipPath#anik
            [:circle {:r 85
                      :cx 500
                      :cy 100
                      :stroke :black
                      :storke-width 7
                      :fill :white}  ]]])]


       ])))







(defn home-planets-banners-id []
  (let [[text set-text]     (react/useState "")
        [slider set-slider] (react/useState -1)
        animate-ref         (react/useRef)
        _                   (react/useEffect
           (fn []
             (if (and animate-ref (> slider -1)
                      (-> animate-ref .-current))
               (-> animate-ref
                   .-current
                   (.animate
                    (clj->js
                     [{
                       :background (hsl [.5 70 70 .1])
                       :transform  (m7/tranfrom [[:rotate "10deg"]])
                       }
                      {:background (hsl [.9 70 70 .7])
                       :transform  (m7/tranfrom [[:rotate "-10deg"]])

                       }

                      {:background (hsl [2 70 70 .9])
                       :transform  (m7/tranfrom [[:scale .9]])
                       :offset     (/ 9 14)}

                      {:background (hsl [3.5 70 70 .7])
                       :transform  (m7/tranfrom [[:scale 1]])
                       }])
                    (clj->js
                     {:duration   2000
                      :iterations 1})
                    )))
             (js/console.log "1")
             ))

        f  (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
             (comp
              (partial map (partial * n))))]
    (let [zoom     4
          ax-dx    80
          ax-dy    40
          vb       (fn [z]
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
          viewbox  (vb 0)
          viewbox2 (vb 0)
          m        30
          d        9.4
          scale2   [[.5 .5] [-0.5 .5]]
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {
                      :background-color (hsl [1 70 70 .1])
                      :gap              ".2rem"})}

       (map-indexed
        (fn [n d]
          [:div {:ref            (if (= n slider) animate-ref nil)
                 :on-mouse-enter (fn [e]
                                   (set-slider n))
                 :style
                 (m7/css
                  [[1 1 (+ 1 (* 4 n)) 4  :center :center  1.5 :rem :column]
                   [(if (= slider n) 3 1) 70 (+ 50 (* 2 n)) 1] []
                   (into
                    {:font-size   (m7/np [1.7 :rem])
                     :font-family "Roboto Flex"
                     :gap         (m7/np [1 :rem])
                     :color       (hsl [1 70 (if (= slider n) 40 90) 1])
                     :z-index     4
                     :cursor      :grab}
                    {})])}
           d])
        ["Math" "Physics" "Chemistry" "CSE" "Arts & Fation" "English"])


       [:div {:style
              (m7/css
               [[2 10  4 15
                 :center :center  3.0 :rem :column]
                [1.5 70 80  .5] []
                {:gap     ".1rem"
                 :color   (hsl [0 30 60 1])
                 :z-index 10}])}


        [:textarea {:rows      4
                    :cols      50
                    :on-change (fn [e]
                                 (set-text
                                  (-> e
                                      .-target
                                      .-value)))}]

        ]


       #_(let [d 'ε]
           [:div {:style
                  (m7/css
                   [[2 10  4 15
                     :center :center  4.0 :rem :column]
                    [1.5 70 80  .5] []
                    {:gap     ".1rem"
                     :color   (hsl [0 30 60 1])
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
                    tmp-tmp  (js/parseFloat (fix (* 0.22 time-str time-str) 2))]
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
                          [(* n .2) 70 (+ 10 (* 1 n))  .2] [] {:gap     ".1rem"
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
                          [1 70 70  .9] [] {:gap     ".1rem"
                                            :z-index 4}])}

           d])
        (range 0 11)
        (map (fn [i]
               [m7/mx `[:p 2 ~i]])
             (range 0 11)))

       #_[:div {:style (m7/css
                      [[5 4 4 8 :center :center 3 :rem ]
                       [1 90 90 .01] []
                       (into
                        {:gap         "1rem"
                         :font-family "Roboto Flex"
                         :color       (hsl [0 60 60 1])
                         :z-index     2}
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
        [:div {:style {}}  ""]
        ]


       [:div {:style (m7/css
                      [[8 5 1 18 :center :center 2.2 :rem ]
                       [1 70 90 .1] [] {:gap     "1rem"
                                        :color   (hsl [3 20 30 1])
                                        :z-index 2}])}

        #_[:span {:style {:background-color (hsl [1 70 70 .5])}}"9th October"]








        ]


       #_[:div {:style (m7/css
                        [[2 10 2 23 :center :center 3 :rem]
                         [1 70 90 1] [] {:gap     "1rem"
                                         :z-index 1}])}
          (let []
            [:svg {:style   {:height "100%"
                             :width  "100%"}
                   :viewBox (m7/space
                             viewbox)}

           [flames]


             [:linearGradient {:x1                .5
                               :y1                1
                               :x2                .5
                               :y2                0
                               :id                (name :lgg1)
                               :gradientTransform (m7/tranfrom [[:rotate 10]])}
              [:stop  {:offset     0
                       :stop-color (hsl [1 70 70 .1])}]
              [:stop  {:offset     .33
                       :stop-color (hsl [.3 70 70 .7])}
               [:animate {:attributeName :offset
                          :from          .2
                          :to            .5
                          :dur           (m7/not-space [3 "s"])
                          :repeatCount   :indefinite}]]
              [:stop  {:offset     .5
                       :stop-color (hsl [.6 70 70 .4])}
               [:animate {:attributeName :offset
                          :from          .3
                          :to            1
                          :dur           (m7/not-space [3 "s"])
                          :repeatCount   :indefinite}]]
            ]

             [:pattern {:id      (name :star)
                        :viewBox (space [0 0 10 10])
                        :width   "10%"
                        :height  "10%"}
              [:circle {:cx   5
                        :cy   5
                        :r    6
                        :fill (m7/url (name :lg2))
                        }]]

             [:radialGradient {
                               :id                (name :lg1)
                               :gradientTransform (m7/tranfrom [[:rotate 0]])}
              [:stop  {:offset     0
                       :stop-color (hsl [0 70 70 1])}]
              [:stop  {:offset     .33
                       :stop-color (hsl [.2 70 70 .9])}]
              [:stop  {:offset     .77
                       :stop-color (hsl [1 70 70 .8])}
               [:animate {:attributeName :offset
                          :id            :f113
                          :begin         0
                          :from          .88
                          :to            1
                          :dur           (m7/not-space [120 "s"])
                          :repeatCount   :indefinite}]

               [:animate {:attributeName :offset
                          :begin         :f123.end
                          :from          1
                          :to            .88
                          :dur           (m7/not-space [120 "s"])
                          :repeatCount   :indefinite}]]
            ]


             [:radialGradient {
                               :id                (name :lg2)
                               :gradientTransform (m7/tranfrom [[:rotate 0]])}
              [:stop  {:offset     0
                       :stop-color (hsl [3 40 40 .7])}]
              [:stop  {:offset     .55
                       :stop-color (hsl [3.3 60 60 .8])}]


              [:stop  {:offset     .97
                       :stop-color (hsl [1 70 70 .2])}
               [:animate {:attributeName :offset
                          :id            :f114
                          :begin         0
                          :from          .55
                          :to            1
                          :dur           (m7/not-space [120 "s"])
                          :repeatCount   :indefinite}]

               [:animate {:attributeName :offset
                          :begin         :f114.end
                          :from          1
                          :to            .55
                          :dur           (m7/not-space [120 "s"])
                          :repeatCount   :indefinite}]
               [:animate {:attributeName :stop-color
                          :begin         0
                          :id            :f115
                          :from          (hsl [1 90 80 .2])
                          :to            (hsl [1 90 80 .8])
                          :dur           (m7/not-space [120 "s"])
                          :repeatCount   :indefinite}]
               [:animate {:attributeName :stop-color
                          :begin         :f115.end
                          :from          (hsl [1 90 80 .2])
                          :to            (hsl [1 90 80 .8])
                          :dur           (m7/not-space [13 "s"])
                          :repeatCount   :indefinite}]]

            ]
           ;; :indefinite
             [:marker {:id           (name :dot2)
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






           (grid-on 1 1)


           [:g




            [:g#logo
            (map
             (fn [x]
               [:g


                [:path
                 {:d
                  (str (path
                        [ 0 0 :l
                         (* 20 3) (ve (* 2 2 2))
                         (* 20 5) (ve (* 2 5 5))


                         (* 20 1)
                         (ve (- (* 2 9 9) (* 2 7 7)))
                         (ve (* 20 9))  0
                         ])
                       "z")

                  :transform
                  (m7/tranfrom
                   [
                    [:translate [(* m 20)
                                 (* 2 d d)]]
                    [:scale x]
                    ])
                  :stroke       (hsl [4 70 70 1])
                  :stroke-width 10
                  :fill         (hsl [0 70 70 1])
                  }
                 ]

                #_[:path
                   {:d
                    (str (path
                          [ 0 0 :c
                           (* 20 3) (ve (* 2 2 2))
                           (* 20 5) (ve (* 2 5 5))
                           (* 20 7) (ve (* 2 7 7))
                           :c
                           (* 20 1)
                           (ve (- (* 2 9 9) (* 2 7 7)))
                           (* 20 2) (ve (- (* 2 11 11 ) (* 2 7 7)))
                           (* 20 4) (ve (- (* 2 13 13) (* 2 7 7)))
                           :l (ve (* 20 9))  0
                           ])
                         "z")

                    :transform
                    (m7/tranfrom
                     [
                      [:translate [(* m 20)
                                   (* 2 d d)]]
                      [:scale x]
                      ])
                    :stroke       (hsl [4 70 70 1])
                    :stroke-width 10
                    :fill         (hsl [0 70 70 1])
                    }
                   ]
                ])
             scale2)


             #_[airplane2 [
                        [:translate [600 120]]
                        [:scale [.3 .3]]
                        [:rotate -90]]]
            ]



            #_[:circle {:r      90
                        :cx     0
                        :cy     0
                        :filter (m7/url "flames")
                        :fill   (m7/url (name :lg2))}]

            [:circle {:r      250
                      :cx     0
                      :cy     0
                      :filter (m7/url "flames")
                      :fill   (m7/url (name :lg2))}]


            #_[:circle {:r            90
                        :cx           0
                        :cy           0
                        :stroke       (hsl [3 70 70 .5])
                        :stroke-width .5
                        :fill         (m7/url (name :star))}

               #_[:animateTransform {
                                     :attributeName :transform
                                     :begin         (sec 0)
                                     :dur           (sec 15)
                                     :type          :rotate
                                     :from          0
                                     :to            -360
                                     :repeatCount   :indefinite
                                     :fill          :freeze}]


             ]



            [:circle {:r            350
                      :cx           0
                      :cy           0
                      :stroke       (hsl [0 70 70 1])
                      :stroke-width .5
                      :fill         :none}]


            #_:fill (m7/url (name :lg2))
            #_:fill (m7/url (name :star))
            [:circle {:r            40
                      :cx           470
                      :cy           0
                      :stroke       (hsl [3 70 70 1])
                      :stroke-width .5
                      :fill         (m7/url (name :lg2))

                      }

             [:animateTransform {
                                 :attributeName :transform
                                 :begin         (sec 0)
                                 :dur           (sec 15)
                                 :type          :rotate
                                 :from          0
                                 :to            -12
                                 :repeatCount   :indefinite
                                 :fill          :freeze}]


             ]


            [:text {:font-size 56
                    :x         420
                    :y         0
                    :dx        -40
                    :fill      (hsl [3 70 70 1])
                    }

             ""



             ]





            [:path {:id   :com-id
                    :d    (m7/path `[450 -150
                                  :l ~@(map
                                        (fn [d x]
                                          (* d x))
                                        dx (cycle [ 300 70]))])
                    :fill (hsl [.2 60 55 .5])}]

            [:text {}
             [:textPath {:startOffset (m7/np [5 :%])
                         :fill        (hsl [1 70 70 1])
                         :href        :#com-id}
              "SCIENCE Café Scientifique"]]



            #_[:text {:font-size 56
                      :x         0
                      :y         0
                      :dx        -40
                      :fill      (hsl [2 70 70 1])
                      }

             "BDT 999"



             ]








            [:text {:dx   -18
                    :x    250
                    :y    0
                    :fill (hsl [1 18 70 1])}




             ]


            [:circle {:r            250
                      :cx           0
                      :cy           0
                      :stroke       (hsl [1 70 70 1])
                      :stroke-width .5
                      :fill         :none}]





            [:circle {:r            470
                      :cx           0
                      :cy           0
                      :stroke       (hsl [2 70 70 1])
                      :stroke-width .3
                      :fill         :none}]





            [:circle {:r            580
                      :cx           0
                      :cy           0
                      :stroke       (hsl [2.7 70 70 1])
                      :stroke-width .3
                      :fill         :none}]


            ]





           [:clipPath#anik
            [:circle {:r            85
                      :cx           500
                      :cy           100
                      :stroke       :black
                      :storke-width 7
                      :fill         :white}  ]]])]


      ])))

#_ [9 6 4 3 2.9 2.7 2.5 2.3 2.1 2 ]
#_a
#_ (range  36 0  -2)
#_[9 6 4 3 2.9 2.7 2.5 2.3 2.1 2 ]
#_(js/parseInt
   (fix
    (/ (js/parseInt time-str) 6) 1))
(defn chem-mole []
  (let [[timer update-time]
        (react/useState (js/Date.))
        time-str (-> timer .toTimeString
                     (str/split " ")
                     first
                     (str/split ":" )
                     (nth 2))
        sec2 3
        angle {:font-size 12
               :dy -5
               :dx 30}
        turns [ 9 7.5  6 4 3 2.75 2.5 2.25 2 1.75 1.5]
        tn (nth turns sec2)
        tn2 (nth turns 4)
        r 18
        adj (fix (* r  (js/Math.cos (/ js/Math.PI tn)))  2)
        op (fix (* r  (js/Math.sin (/ js/Math.PI tn)))  2)
        opp1 (fix (* r  (js/Math.tan (/ js/Math.PI tn)))  2)

        adj2 (fix (* r  (js/Math.cos (/ js/Math.PI tn2)))  2)
        op2 (fix (* r  (js/Math.sin (/ js/Math.PI tn2)))  2)
        opp12 (fix (* r  (js/Math.tan (/ js/Math.PI tn2)))  2)

        _ (react/useEffect
           (fn []
             (let [i (js/setInterval #(update-time (js/Date.)) 100)]
               (fn []
                 (js/clearInterval i)))))

        [slider get-slider] (react/useState 0)
        dd '☐
        deg '°
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1]

        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40
          vb (fn [z]
               (nth [[100 -320  400 450]
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]
                     ] z))
          viewbox (vb 0)
          viewbox2 (vb 0)
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .1])

                      :gap ".1rem"})}



       [:div {:style (m7/css
                      [[2 6 2 9 :center :center
                        1.8 :rem :column]
                       [1 70 90 .1] []
                       {:gap "1rem"
                        :color (hsl [3 50 30 1])
                        :z-index 2}])}

        "When a ladder of length 18 m leans against the to top edge of a window of a building. It forms a angle of 60 deg with the ground. When the ladder leans against the lower edge of the same window, it from a angle of 45 deg with ground. Calculate the height of the window. "]



       [:div {:style (m7/css
                      [[2 10 14 9 :center :center
                        1.8 :rem :column]
                       [1 70 90 .1] []
                       {:gap "1rem"
                        :color (hsl [3 50 30 1])
                        :z-index 2}])}
        #_[:div "In right triangle ABC "
         [m7/mx `[= AC ~r]]
         "cm , "
         [m7/mx `[= AB ~op]]
         "cm , "
         [m7/mx `[= BC ~adj ]]
         "cm and if "
         [m7/mx `[= CT ~(fix (* 0.7 adj) 2) ]]

         "cm, find the length of  AT?"]





       ;;  [:div "In pythagorium theorum in ATB"]


       ;;  [m7/m '[= [:p AT 2]  [+ [:p AB 2]  [:p BT 2]]]]

       ;;  [m7/m '[= [:p AT 2]  [+ [:p 6 2]  [:p [:b [- BC CT]] 2]]]]

       ;; [m7/m  '[= [:p AT 2]  45.864]]

       ;;  [m7/m '[= AT  [:sq 45.864] 6.7]]

        #_[m7/m '[= [- 10.96 7.67] TB]]
        #_[m7/mx `[= [:p AC 2] [:p ~r 2] 144]]

        #_[m7/mx `[= [:p ~adj 2] 120.1216  [:p BC 2]]]



        ;; [m7/mx `[= [:p AB 2] [:p ~op 2] 156.25]]

        #_[m7/mx `[= [:p AC 2] [+ [:p AB 2] [:p BC 2]]]]

        #_[m7/mx `[= 23.8784 [:p AB 2]]]


        #_[m7/mx `[= 4.886 AB]]

        ;; [m7/mx `[= ~(- 625 156.25) [:p BC 2]]]


        ;; [m7/mx `[= [:sq ~(- 625 156.25)] BC]]


        ;; [m7/mx `[= [:sq ~(- 625 156.25)] ~adj  BC]]

        #_[m7/mx `[= [:p AC 2]   [:p [:b [+ 10 5]] 2]]]
        #_[m7/mx `[= AB ~op]]

        #_[m7/mx `[= AB ~op]]


        #_[m7/mx `[= [:p AC 2] [+  [:p AB 2] [:p BC 2]] ]]


        #_[m7/mx `[= [- 225 112 0.5721] [:p BC 2]]]

        #_[m7/mx `[= [- [+ 112 1] 0.5721] [:p BC 2]]]


        #_[m7/mx `[= 112.4279  [:p BC 2]]]
        #_[m7/mx `[= [- 225 112.5721] [- [- 225 112]  0.5721]]]

        #_[m7/mx '[= BC [:sq 112.4279] 10.6 ]]

        #_[m7/m '[:m 2 2 5 . 0 0 0 0]]
        #_[m7/m '[:m  1 1 2 . 0 0 0 0]]



        #_[m7/mx `[= BC ~adj]]

        #_[m7/mx `[= ~(symbol (str "tan" (name tt)))
                 [[:k opp 1] [:k adj 1]]]]





        #_[m7/mx `[= [:m r ~(symbol (str "tan" (name tt)))] [:k opp 1]]]
        [m7/mx `[= [:m r ~(symbol (str "sin" (name tt)))]
                 opp]]
        #_[m7/mx `[= [:m r ~(symbol (str "cos" (name tt)))]
                   adj]]

        [m7/mx `[= ~tt [:p ~(fix (/ 180 tn) 1)
                        ~deg]]]

        [m7/mx `[= [:k ~tt 2] [:p ~(fix (/ 180 tn2) 1)
                        ~deg]]]


        #_[m7/mx `[= [* ~r [:m tan [:p ~(fix (/ 180 tn) 1)
                                  ~deg]]]
                 [:k opp 1]]]


        #_[m7/mx `[= [* ~r [:m tan [:p ~(fix (/ 180 tn) 1)
                                  ~deg]]]
                 ~opp1]]




        #_[m7/mx `[= BC [* ~r [:m cos [:p ~(fix (/ 180 tn) 1)
                                  ~deg]]]
                 ~adj]]

        [m7/mx `[= AB   [* ~r [:m sin [:p ~(fix (/ 180 tn) 1)
                                    ~deg]]]
                 ~op]]


        #_[m7/mx `[= [:m [:k B 1] C]  [* ~r [:m cos [:p ~(fix (/ 180 tn2) 1)
                                  ~deg]]]
                 ~adj2]]

        [m7/mx `[= [:m [:k A 1] B]  [* ~r [:m sin [:p ~(fix (/ 180 tn2) 1)
                                                   ~deg]]]
                 ~op2]]


        [m7/mx `[= [+ AB [:m A [:k A 1] ]]
                 ~op2]]


        [m7/mx `[= [+ 12.73 [:m A [:k A 1] ]]
                 ~op2]]









        ]



       [:div {:style (m7/css
                      [[2 10 3 23 :center :center 3 :rem]
                       [1 70 90 1] []
                       {:gap "1rem"
                        :z-index 1}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}


           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]
           [flames]
           #_(grid-on 1 1)




           (let [rad tn
                 r (* r 20)]
             [:g

              [:circle {:r r
                        :cx 0
                        :cy 0
                        :fill (hsl [.6 95 70 .5])}]



              [:circle {:r 350
                        :cx 0
                        :cy 0
                        :stroke  (hsl [0 70 70 1])
                        :stroke-width .5
                        :fill :none}]



              [:circle {:r 350
                        :cx 0
                        :cy 0
                        :stroke  (hsl [0 70 70 1])
                        :stroke-width .5
                        :fill :none}]

              [:circle {:r 25
                        :cx 470
                        :cy 0
                        :stroke (hsl [3 70 70 1])
                        :stroke-width .5
                        :fill (m7/url (name :lg2))}




               [:animateTransform {
                                   :attributeName :transform
                                   :begin (sec 0)
                                   :dur (sec 15)
                                   :type :rotate
                                   :from 0
                                   :to -360
                                   :repeatCount :indefinite
                                   :fill :freeze}]


               ]


              [:circle#cline1 {:r 250
                        :cx 0
                        :cy 0
                        :stroke  (hsl [1 70 70 1])
                        :stroke-width .5
                        :fill :none}]


              [:circle#cline2 {:r 470
                        :cx 0
                        :cy 0
                        :stroke  (hsl [2 70 70 1])
                        :stroke-width .3
                        :fill :none}]


              [:g#arcs
               (map
                (fn [se]
                  [:circle {:r 2
                            :cx (* r (js/Math.cos (/ js/Math.PI se)))
                            :cy (ve (* r (js/Math.sin (/ js/Math.PI se))))
                            :fill (hsl [4 70 70 1])}])
                turns)]



              [:g#r1
               (map
                (fn [ang]
                  [:path {:d (m7/path [0 0 :l r 0 ])
                          :id :rrr2
                          :fill :none
                          :transform (m7/tranfrom
                                      [[:rotate ang]])
                          :stroke (hsl [5 70 80 .6])
                          :stroke-width 3}])
                [(ve (/ 180 tn)) 0])]

              #_[:g#tan2
               (map
                (fn [ang]
                  [:path {:d (str
                              (m7/path [0 0 :l r 0 0
                                        (*  r (js/Math.tan
                                               (/ js/Math.PI
                                                  (nth turns sec2))))])
                              "z")
                          :id :rrrt1
                          :fill (hsl [4 70 80 .6])
                          :transform (m7/tranfrom [[:rotate ang]])
                          :stroke (hsl [4 70 80 .6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])]



              #_[:g#tan
               (map
                (fn [ang]
                  [:path {:d (str
                              (m7/path [0 0 :l r 0 0
                                        (*  r (js/Math.tan
                                               (/ js/Math.PI
                                                  (nth turns sec2))))])
                              "z")
                          :id :rrrt1
                          :fill (hsl [4 70 80 .6])
                          :transform (m7/tranfrom [
                                                   [:rotate ang]])
                          :stroke (hsl [4 70 80 .6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])


                 (map
                  (fn [ang]
                    [:path {:d (m7/path [0 0 :l  0
                                         20 -20 0 0 -20 ])

                            :fill (hsl [0 70 80 .6])
                            :transform (m7/tranfrom [
                                                     [:rotate ang]
                                                     [:translate [r 0]]])
                            :stroke (hsl [0 70 80 1])
                            :stroke-width 3}])
                  [(ve (/ 180 rad))])
                 [:text


                  [:textPath {:href :#rrrt1
                              :font-size 20
                              :startOffset (+ r (*  r (js/Math.tan
                                                       (/ js/Math.PI
                                                          (nth turns sec2))))
                                              r)}
                   "hyp"
                   [:tspan {:dy 10} 1]]



                  [:textPath {:href :#rrrt1
                              :font-size 20
                              :startOffset
                              (+ r (* .5 r
                                      (js/Math.tan
                                       (/ js/Math.PI
                                          (nth turns sec2)))))}
                   "opp"
                   [:tspan {:dy 10} 1]]]
                 ]









              [:text
               [:textPath {:href :#rrr2
                           :font-size 10
                           :startOffset "30%"}
                "ladder"]]





              [:g
               [:path#angle {:d
                             (m7/path
                              [0 0 :l r 0 :a r r 0 false false
                               (ve (- r (* 20 adj))) (ve (* 20  op))])
                             :stroke (hsl [3 70 70 1])
                             :transform (m7/tranfrom [[:scale [.2 .2]]])
                             :stroke-width 3
                             :fill (hsl [3 70 70 .5])}]





               [:path#sin {:d
                           (m7/path [0 0 :l (* 20 adj) 0 0 (ve (* 20 op))])
                           :id :tri22
                           :stroke (hsl [1 70 70 1])
                           :stroke-width 1
                           :fill (hsl [1.5 70 70 .5])}]]


              [:g {:transform (m7/tranfrom [[:translate [73 0]]])}
               [:path#angle2 {:d
                              (m7/path
                               [0 0 :l r 0 :a r r 0 false false
                                (ve (- r (* 20 adj2))) (ve (* 20  op2))])
                              :stroke (hsl [4 70 70 1])
                              :transform (m7/tranfrom [[:scale [.2 .2]]])
                              :stroke-width 3
                              :fill (hsl [3 70 70 .5])}]


               [:path#sin2 {:d
                            (m7/path [0 0 :l (* 20 adj2) 0 0 (ve (* 20 op2))])
                            :id :tri222
                            :stroke (hsl [4.5 70 70 1])
                            :stroke-width 1
                            :fill (hsl [2.5 70 70 .5])}]


               [:text

                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ 0 (* adj2 20))}

                 (str "B" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ (* .95 op2 20) (* adj2 20))}

                 (str "A1" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :dy 10
                            :startOffset (+ 0 0)}
                 (str "C1" "")]

                ]



               ]





              #_[:path.tri2 {:d (m7/path
                          [0 0 :l (* .3 adj 20) 0 0 (ve (* 20 op))])
                      :transform (m7/tranfrom
                                  [[:translate [(* .7 20 adj) 0]]])
                      :stroke (hsl [1 70 70 1])
                      :stroke-width 3
                      :fill (hsl [3.5 70 70 .5])}]


              [:text
               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ 0 0)}
                (str "C" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ 0 (* adj 20))}

                (str "B" "")]




               #_[:textPath {:href :#tri22
                           :font-size 15
                           :startOffset (+ 0 (* 20 .7 adj))}

                (str "T" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* .95 op 20) (* adj 20))}

                (str "A" "")]




               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* .3 adj 20) 0)}

                #_(str "adj" "")
                adj

                ]


               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset
                           (+
                            (* 20 adj)
                            (* .4 20 op))}

                #_(str "opp" "")

                op
                ]]






              #_[:text {:x 0
                      :style {:font-size (:font-size angle)}
                      :dy (:dy angle)
                      :dx (:dx angle)
                      :y 0}
               #_(name tt)
               #_(fix (* (/ 180 js/Math.PI) (js/Math.asin .92)
                       ) 2)
               (fix (/ 180 (nth turns sec2)) 1)
               [:tspan {:dy -6}
                (name deg)]
               ]


              [:circle {:r 580
                        :cx 0
                        :cy 0
                        :stroke  (hsl [2.7 70 70 1])
                        :stroke-width .3
                        :fill :none}]])])]])))



(defn grid-circle
  ([X Y]
   (grid-circle X Y 40 60 false))
  ([X Y ax-dx ax-dy]
   (grid-circle X Y ax-dx ax-dy false))
  ([X Y ax-dx ax-dy frac]
   (let [zoom 0]
     [:g
      ;; small
      #_(map
       (fn [i]
         [:path {:d (path [-400 (ve (+ ax-dx (* i 2))) :l 1200 0])
                 :stroke (hsl [4 70 70 .5])
                 :stroke-width .5
                 :fill :none}
          ] )
       (range 0 11))


      #_(map
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

          [:path {:d (path [ (* 100 x)  0 :l 0 -400])
                  :stroke (hsl [(if (= (mod x 2) 0)  5 0) 70 70 1])
                  :stroke-width .5
                  :fill :none}
           ]


          [:path {:d (path [ (* 64 x)  0 :l 0 400])

                  :stroke (hsl [0 70 70 1])
                  :stroke-width .5
                  :fill :none}
           ]

          [:text {:x (* 100 x)
                  :fill (hsl [0 40 20 1])
                  :y 4.5
                  :font-size 3}
           (if frac
             (.toFixed (* x X) 1)
             (* x X))
           ]])
       (range -20  20 (/ js/Math.PI 32)))



      (map
       (fn [y]
         [:g
          [:path {:d (path [0 (ve (* 20 y))   :l 1200 0])

                  :stroke (hsl [1 70 70 1])
                  :stroke-width 1
                  :fill :none}
           ]

          [:text {:x -4.5
                  :dy -1
                  :fill (hsl [0 40 20 1])
                  :y (ve (* 20 y))
                  :font-size 3}
           (if frac
             (.toFixed (* y Y) 1)
             (* y Y))
           ]


          [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                  :stroke (hsl [1 70 70 1])
                  :stroke-width 1
                  :fill :none}
           ]

          ])
       (range -20 20))])))

#_[ 9 7.5  6 4 3 2.75 2.5 2.25 2 1.75 1.5]
#_(* timer js/Math.PI (/ 1 32))
(defn sine-wave []
  (let [[timer update-time]
        (react/useState 0)
        sec2 (* timer js/Math.PI (/ 1 32))
        angle {:font-size 12
               :dy -5
               :dx 30}

        tn sec2
        tn2 sec2
        r 10
        adj (fix (* r  (js/Math.cos tn))  2)
        op (fix (* r  (js/Math.sin tn))  2)
        opp1 (fix (* r  (js/Math.tan tn))  2)

        adj2 (fix (* r  (js/Math.cos tn))  2)
        op2 (fix (* r  (js/Math.sin tn))  2)
        opp12 (fix (* r  (js/Math.tan tn))  2)

        _ (react/useEffect
           (fn []
             (let [i (js/setInterval #(update-time (mod (+ timer 1) 128)) 1000)]
               (fn []
                 (js/clearInterval i)))))

        [slider get-slider] (react/useState 0)
        dd '☐
        deg '°
        pi 'π
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1]

        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40
          vb (fn [z]
               (nth [(map  #(* % 9) [15 -20  40 45])
                     (map  #(* % 10) [-50 -25  100 50])
                     (map  #(* % 10) [-20 -25  100 50])
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]
                     ] z))
          viewbox (vb 2)
          viewbox2 (vb 2)]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .1])})}


       [:div {:style (m7/css
                      [[2 5 3 10 :center :center 3 :rem :column]
                       [1 70 90 .1] []
                       {:gap "1rem"
                        :z-index 2}])}

        #_[m7/mx `[= [c [:m 2 r]] [64 [* 2 10.18]] ~pi ]]

        [m7/mx `[= r 1]]
        [m7/mx `[= c [:m 2 ~pi ] [:m 360 ~deg]]]

        #_[m7/mx `[= A [[:m 8 c] 64] [[* 8 2 ~pi ] 64] [:p [:b [[* 360 8] 64]] ~deg] ]]


        [:div {:style {:background-color (hsl [1 70 70 1])
                       :padding "6px"}
               } "convert " [m7/mx `[[:m 2 ~pi] 3]] " into degree" ]


        [m7/mx `[=  [[:m 2 ~pi ] 3] [:m [360 3] ~deg]]]


        #_[m7/mx `[= [:m [1 3] ~pi ] [:m [180 3] ~deg]]]

        #_[m7/mx `[= arc [ [* 6 c] 64] [:m [:m 2 r] ~pi] ]]



        #_[m7/mx `[= c [:m 2 ~pi] ]]



        ]




       [:div {:style (m7/css
                      [[2 10 2 23 :center :center 3 :rem]
                       [1 70 90 1] []
                       {:gap "1rem"
                        :z-index 1}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}


           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]

           (let [r (* r 20)
                 ps 32
                 angle (* js/Math.PI  (/ 1 ps))]
             [:g
              [:circle {:r r
                        :cx 0
                        :cy 0
                        :stroke-width 1
                        :stroke-dashoffset 0
                        :stroke-dasharray 0
                        :stroke (hsl [1 20 20 .8])
                        :fill (hsl [.6 95 70 .2])}]


              [:circle {:r (* 2 r)
                        :cx 0
                        :cy 0
                        :stroke-width 1
                        :stroke-dashoffset 0
                        :stroke-dasharray 0
                        :stroke (hsl [1 20 20 .8])
                        :fill (hsl [.6 95 70 .2])}]





              [:g#arcs
               (map
                (fn [se]
                  [:g
                   [:circle {:r 4
                             :cx (* r (js/Math.cos (* se angle)))
                             :cy (ve (* r (js/Math.sin (* se angle))))
                             :fill (hsl [(mod se 8) 70 70 .9])}]
                   [:text {:x (* r (js/Math.cos  (* se angle)))
                           :y (ve (* r (js/Math.sin (* se  angle))))
                           :font-size 15
                           :fill (hsl [4 70 70 .4])} se]])
                (range 0 (* 2 ps)))]


              [:g#arcs2
               (map
                (fn [se]
                  [:g
                   [:circle {:r 2
                             :cx (* r (* se angle))
                             :cy (ve (* r (js/Math.sin (* se angle))))
                             :fill (hsl [(mod se 8) 70 70 .9])}]
                   [:text {:x (* r (js/Math.cos (* se angle)))
                           :y (ve (* r (js/Math.sin (* se angle))))
                           :font-size 15
                           :fill (hsl [4 70 70 .4])} se]])
                (range 0 (* 2 ps)))]


              [:path#rad1 {:d (m7/path [  0 0 :l
                                        (* 8 r angle) (ve (* r (js/Math.sin (* angle 8)) ))



                                        ])

                           :fill :none
                           :stroke (hsl [.2 30 30 1])
                           :stroke-width 2}]


              (map
               (fn [se]
                 [:g
                  [:path {:d (m7/path [(* r js/Math.PI se (/ 1 ps)) 1200 :l 0 -2400])
                          :stroke (hsl [(mod se 8) 70 70 .9])
                          :stroke-width 1
                          :fill :none
                          }]
                  [:text {:x (* r js/Math.PI se (/ 1 ps))
                          :y 0
                          :dy 10
                          :dx -5
                          :font-size 10
                          :fill (hsl [4 10 10 .5])
                          }
                   (mod se (* 2 ps))]
                  ])
               (range (ve (+ 1 (* 2 ps)))   (* 6 (+ 1 (* 2 ps)))))
              [:path#rad1 {:d (m7/path [  0 0 :c
                                        (* 0.2 r angle) (ve (* r 0.04))
                                        (* 7.5 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        (* 8 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        :c
                                        (* 6 r angle) (ve (* r .4))
                                        (* 10 r angle) (ve (* r .4))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 24)) (js/Math.sin (* angle 8))) ))
                                        :c
                                        (* .1 r angle) (ve (* r .07))
                                        (* 15.8 r angle) (ve (* r (+ -0.07 (- (js/Math.sin (* angle 40)) (js/Math.sin (* angle 24)))) ))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 40)) (js/Math.sin (* angle 24))) ))
                                        :c
                                        (* 6 r angle) (ve (ve (* r .4)))
                                        (* 10 r angle) (ve (ve (* r .4)))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 24)) (js/Math.sin (* angle 8))) ))
                                        :c
                                        (* 0.2 r angle) (ve (ve (* r 0.04)))
                                        (* 7.5 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        (* 8 r angle) (ve (* r (js/Math.sin (* angle 8)) ))

                                        ])

                           :fill :none
                           :stroke (hsl [.2 30 30 .5])
                           :stroke-width 2}]

              [:circle {:r 20
                        :cx 0
                        :cy 50
                        :fill (hsl  [1 70 70 1])}
               [:animate
                {:attributeName :cy

                 :begin :click
                 :dur (sec 3)
                 :from 50
                 :to 250
                 :calcMode :spline
                 :values (m7/sami-colon   [50   250 120 250   170   250   210 250])
                 :keyTimes (m7/sami-colon [0  0.15  0.3 0.45  0.6   0.75  0.9 1])
                 :keySplines (m7/sami-colon
                              (map m7/space
                                   [[.42 0 1 1]
                                    [0 0 .59 1]
                                    [.42 0 1 1]
                                    [0 0 .59 1]
                                    [.42 0 1 1]
                                    [0 0 .59 1]
                                    [.42 0 1 1]

                                    ]))
                 :fill :freeze}]]


              #_[:path {:d (path (flatten
                             [0 0

                               (repeat 12
                                       [
                                        :a  1 2 0 false false   10 0
                                        :a  1 2 0 true false   -3 0])
                               :a 1 2  0 false false   10 0
                               :l 4 0
                               :a 1 1 0 false true    3 0
                               :a 1 1 0 false true   -3 0
                              ]))
                      :transform (m7/tranfrom [[:translate [(* 2 r) 0]]
                                               [:scale -2]
                                            ])
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 3
                   :fill :none}
                [:animate
                 {:attributeName :d
                  :begin :click
                  :dur (sec 10)
                  :repeatCount 20
                  :keyTimes (m7/sami-colon [0 0.3  0.4 .5
                                            0.6 0.75 0.9 1])
                  :values (m7/sami-colon
                           (map
                            (fn [i j]
                              (path (flatten
                                     [0 0
                                      (repeat 12
                                              [
                                               :a j 2 0 false false   (+ 10 i) 0
                                               :a j 2 0 true false   -3 0])
                                      :a j 2 0 false false   (+ i 10) 0
                                      :l 4 0
                                      :a j 2  0 false true    3 0
                                      :a j 2  0 false true   -3 0
                                      ]))
                              )
                            (map #(* % 8) [0.1 0.50 0.7   1
                                           0.70 0.5  .3  0
                                           ])
                            [1 1.1 1.2 1.5
                             1.5 1.2 1.1 1]
                         ))

                  :fill :freeze
               }]]



              ]






             )






           #_(grid-circle 1 1)

           #_(let [rad tn
                 r (* r 20)]
             [:g




              (if true
                [:g#arcs
                 (map
                  (fn [se]
                    [:g

                     [:circle {:r 4
                               :cx (* r (js/Math.cos (* js/Math.PI se (/ 1 32))))
                               :cy (ve (* r (js/Math.sin (* js/Math.PI se (/ 1 32)))))
                               :fill (hsl [4 70 70 .5])}]
                     [:text {:x (* r (js/Math.cos (* js/Math.PI se (/ 1 32))))
                             :y (ve (* r (js/Math.sin (* js/Math.PI se (/ 1 32)))))
                             :font-size 10
                             }
                      se]])
                  (range 0 (* 2 32)))])



              #_[:g#r1
               (map
                (fn [ang]
                  [:path {:d (m7/path [0 0 :l r 0 ])
                          :id :rrr2
                          :fill :none
                          :transform (m7/tranfrom
                                      [[:rotate ang]])
                          :stroke (hsl [5 70 80 .6])
                          :stroke-width 3}])
                [(ve tn) 0])]

              #_[:g#tan2
               (map
                (fn [ang]
                  [:path {:d (str
                              (m7/path [0 0 :l r 0 0
                                        (*  r (js/Math.tan
                                               (/ js/Math.PI
                                                  (nth turns sec2))))])
                              "z")
                          :id :rrrt1
                          :fill (hsl [4 70 80 .6])
                          :transform (m7/tranfrom [[:rotate ang]])
                          :stroke (hsl [4 70 80 .6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])]



              #_[:g#tan
               (map
                (fn [ang]
                  [:path {:d (str
                              (m7/path [0 0 :l r 0 0
                                        (*  r (js/Math.tan
                                               (/ js/Math.PI
                                                  (nth turns sec2))))])
                              "z")
                          :id :rrrt1
                          :fill (hsl [4 70 80 .6])
                          :transform (m7/tranfrom [
                                                   [:rotate ang]])
                          :stroke (hsl [4 70 80 .6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])


                 (map
                  (fn [ang]
                    [:path {:d (m7/path [0 0 :l  0
                                         20 -20 0 0 -20 ])

                            :fill (hsl [0 70 80 .6])
                            :transform (m7/tranfrom [
                                                     [:rotate ang]
                                                     [:translate [r 0]]])
                            :stroke (hsl [0 70 80 1])
                            :stroke-width 3}])
                  [(ve (/ 180 rad))])
                 [:text


                  [:textPath {:href :#rrrt1
                              :font-size 20
                              :startOffset (+ r (*  r (js/Math.tan
                                                       (/ js/Math.PI
                                                          (nth turns sec2))))
                                              r)}
                   "hyp"
                   [:tspan {:dy 10} 1]]



                  [:textPath {:href :#rrrt1
                              :font-size 20
                              :startOffset
                              (+ r (* .5 r
                                      (js/Math.tan
                                       (/ js/Math.PI
                                          (nth turns sec2)))))}
                   "opp"
                   [:tspan {:dy 10} 1]]]
                 ]









              #_[:text
               [:textPath {:href :#rrr2
                           :font-size 10
                           :startOffset "30%"}
                "R"]]




              (if false
                [:g
                 [:path#angle {:d
                               (m7/path
                                [0 0 :l r 0 :a r r 0 (if (< op 0) true false) false
                                 (ve (- r (* 20 adj))) (ve (* 20  op))])
                               :stroke (hsl [3 70 70 1])
                               :transform (m7/tranfrom [[:scale [.2 .2]]])
                               :stroke-width 3
                               :fill (hsl [3 70 70 .5])}]





                 [:path#sin {:d
                             (m7/path [0 0 :l (* 20 adj) 0 0 (ve (* 20 op))])
                             :id :tri22
                             :stroke (hsl [1 70 70 1])
                             :stroke-width 1
                             :fill (hsl [1.5 70 70 .5])}]])


              #_[:g {:transform (m7/tranfrom [[:translate [73 0]]])}
               [:path#angle2 {:d
                              (m7/path
                               [0 0 :l r 0 :a r r 0 false false
                                (ve (- r (* 20 adj2))) (ve (* 20  op2))])
                              :stroke (hsl [4 70 70 1])
                              :transform (m7/tranfrom [[:scale [.2 .2]]])
                              :stroke-width 3
                              :fill (hsl [3 70 70 .5])}]


               [:path#sin2 {:d
                            (m7/path [0 0 :l (* 20 adj2) 0 0 (ve (* 20 op2))])
                            :id :tri222
                            :stroke (hsl [4.5 70 70 1])
                            :stroke-width 1
                            :fill (hsl [2.5 70 70 .5])}]


               [:text

                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ 0 (* adj2 20))}

                 (str "B" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ (* .95 op2 20) (* adj2 20))}

                 (str "A1" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :dy 10
                            :startOffset (+ 0 0)}
                 (str "C1" "")]

                ]



               ]





              #_[:path.tri2 {:d (m7/path
                          [0 0 :l (* .3 adj 20) 0 0 (ve (* 20 op))])
                      :transform (m7/tranfrom
                                  [[:translate [(* .7 20 adj) 0]]])
                      :stroke (hsl [1 70 70 1])
                      :stroke-width 3
                      :fill (hsl [3.5 70 70 .5])}]


              #_[:text
               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ 0 0)}
                (str "C" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ 0 (* adj 20))}

                (str "B" "")]




               #_[:textPath {:href :#tri22
                           :font-size 15
                           :startOffset (+ 0 (* 20 .7 adj))}

                (str "T" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* .95 op 20) (* adj 20))}

                (str "A" "")]




               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* .3 adj 20) 0)}

                #_(str "adj" "")
                adj

                ]


               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset
                           (+
                            (* 20 adj)
                            (* .4 20 op))}

                #_(str "opp" "")

                op
                  ]]

              #_[:path {:d (m7/path [0 -40 :l 2200 0])
                      :fill :none
                      :stroke-width 4
                      :stroke (hsl [2 70 70 1])}]


              [:path#lam {:d (m7/path [0 40 :l (* 2 js/Math.PI r) 0])
                          :fill :none
                          :stroke-width 4
                          :stroke (hsl [0 50 40 1])}]

              [:path#lam2 {:d (m7/path [0 0 :l (*  2 r) 0])
                           :fill :none
                           :stroke-width 4
                           :stroke (hsl [1 50 40 1])}]

              [:text
               #_[:textPath {:href :#lam
                           :startOffset "30%"}
                "Periodic Time, T"]

               [:textPath {:href :#lam
                           :dy -20
                           :startOffset "30%"}
                "c"]


               [:textPath {:href :#lam2
                           :dy -20
                           :startOffset "30%"}
                "d"]

               ]
              (if true
                  [:g#wave
                   (map-indexed

                    (fn [index i]
                      [:g

                       #_[:circle {:cx (* i r)
                                   :cy (ve (* 1.3 r (js/Math.sin i)))
                                   :r 3
                                   :fill (hsl [2 70 70 1])}]



                       #_[:circle {:cx (* i r)
                                   :cy (ve (- (* 1.0 r (js/Math.sin i)) (* 0.9 r (js/Math.sin i))))
                                   :r 3
                                   :fill (hsl [2 70 70 1])}]

                       #_[:circle {:cx (* i r)
                                   :cy (+ -40 (ve (* 1.0 r (js/Math.sin i))))
                                   :r 3
                                   :fill (hsl [1 70 70 1])}]


                       #_[:circle {:cx (* i r)
                                 :cy (ve (* 1.0 r (js/Math.sin i)))
                                 :r 3
                                 :fill (hsl [0 70 70 1])}]
                       #_[:circle {:cx (* i r)
                                   :cy (ve (ve (* 0.9 r (js/Math.sin i))))
                                   :r 3
                                   :fill (hsl [1 70 70 1])}]

                       [:circle {:cx (* i r)
                                 :cy 0
                                 :r 3
                                 :fill (hsl [4 70 70 1])}]
                       (if (= (mod index 2) 0)
                         [:text {:x (* i r)
                                 :y 0
                                 :dx -10
                                 :dy 15
                                 :font-size 10
                                 :fill (hsl [4 20 20 1])}
                          index
                          #_(str index (name pi))
                          #_[:tspan {:dy 12
                                   :dx -13}
                           32]

                          ])


                       ])
                    (range 0 20 (/ js/Math.PI 32)))])



              [:circle {:cx (* r sec2)
                        :cy (ve (* 20 op))
                        :r 5
                        :fill (hsl [5 70 70 1])}]


              [:circle {:cx (* 20 adj)
                        :cy (ve (* 20 op))
                        :r 5
                        :fill (hsl [5 70 70 1])}]


              [:circle {:cx (* r sec2)
                        :cy 0
                        :r 5
                        :fill (hsl [4 70 70 1])}]


              #_[:text {:x 0
                      :style {:font-size (:font-size angle)}
                      :dy (:dy angle)
                      :dx (:dx angle)
                      :y 0}
               #_(name tt)
               #_(fix (* (/ 180 js/Math.PI) (js/Math.asin .92)
                       ) 2)
               (fix (/ 180 (nth turns sec2)) 1)
               [:tspan {:dy -6}
                (name deg)]
               ]


              ])

           ])]])))


(defn sine-wave3 []
  (let [[timer update-time]
        (react/useState 0)

        sec2 (* timer js/Math.PI (/ 1 32))
        angle {:font-size 12
               :dy -5
               :dx 30}

        tn sec2
        tn2 sec2
        r 10
        adj (fix (* r  (js/Math.cos tn))  2)
        op (fix (* r  (js/Math.sin tn))  2)
        opp1 (fix (* r  (js/Math.tan tn))  2)

        adj2 (fix (* r  (js/Math.cos tn))  2)
        op2 (fix (* r  (js/Math.sin tn))  2)
        opp12 (fix (* r  (js/Math.tan tn))  2)

        _ (react/useEffect
           (fn []
             (let [i (js/setInterval #(update-time (mod (+ timer 1) 128)) 1000)]
               (fn []
                 (js/clearInterval i)))))

        [slider get-slider] (react/useState 0)
        dd '☐
        deg '°
        pi 'π
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1]

        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40
          vb (fn [z]
               (nth [(map  #(* % 15) [20 -22  40 45])

                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]
                     ] z))
          viewbox (vb 0)
          viewbox2 (vb 0)
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .1])})}


       (if false
         [:div {:style (m7/css
                        [[2 7 2 9 :center :center
                          3.8 :rem :column]
                         [1 70 90 .1] []
                         {:gap "1rem"
                          :color (hsl [3 50 30 1])
                          :z-index 2}])}




          [m7/mx `[= c [* ~pi [:m 2 r]]]]
          [m7/mx `[= c [* ~(fix js/Math.PI 5) [:m 2 r]]]]
          #_[m7/x `[= ~pi  [c [:m 2 r]]]]


          #_[:div "what would be angle with arc length of 3 of an unit circle?"]


          #_[:div "what is the circumference of unit circle?"]


          #_[m7/x `[= [[:m 2 ~pi] 64]  [[* 2 ~(fix js/Math.PI 4)] 64] [c 64]]]

          #_[m7/x `[= [:m 2 ~pi ] c ~(fix (* 2 js/Math.PI) 4) [:m 360 ~deg]]]

          #_[:div "what would be angle with arc length of 3 of an unit circle?"]

          #_[m7/x `[= Arc [c 16] [~pi 8]  ~(fix (* 2 js/Math.PI (/ 1 16)) 4)]]


          #_[m7/x `[=  [:p 3 c] [:m ~(fix (/ (* 3 360) (* 2 js/Math.PI)) 2) ~deg]]]



          #_[m7/x `[= [~pi 8] [[:m 360 ~deg] 16] [:m ~(/ 360 16) ~deg]]]


          #_[:div "for unit circle what degree makes up arc length of " [m7/x `[~pi 8]] "?"]




          #_[:div "when r is a unit"]
          #_[m7/x `[= [:m 2 ~pi  ] c]]

          #_[:div "cutting the perimeter into 28 peaces makes each arch"]


          #_[m7/x `[ [:m 2 ~pi  ] 28]]

          #_[m7/x `[= ~pi ~js/Math.PI ]]



          #_[m7/m '[= c [* 2 3.1427  r ]]]
          #_[m7/m '[=  Time_Period T]]

          #_[:div "How long it goes on time T is the wave length"]


          #_[m7/x '[= [:m 60 Hz] [:m [1 60] s]]]

          #_[m7/m '[= frequency f [1 T]]]

          #_[m7/m '[= clock-frequency f [1 [:m 60 s]]
                    [:m [1 60] Hz]]]

          ])


       (if false
         [:div {:style (m7/css
                        [[2 10 14 9 :center :center
                          2.8 :rem :column]
                         [1 70 90 .1] []
                         {:gap "1rem"
                          :color (hsl [3 50 30 1])
                          :z-index 2}])}



          #_[m7/m '[c [:m 2 r]]]

          #_[m7/mx `[= BC ~adj]]

          #_[m7/mx `[= ~(symbol (str "tan" (name tt)))
                     [[:k opp 1] [:k adj 1]]]]

          [m7/x `[= [:m g [:b [+ t dt]]] [+ [:m g [:b t]]  [:m d x ~(symbol "g\u030A")  [:b t]]]]

           [:div "g\u030A g \u030A"]]
          [m7/x `[[:m  ~timer ~pi] 32]]

          [:div "Periodic Time"]
          [m7/x `[=  T [:m 64 s]]]
          [:div "Wave length, \u03BB"]
          [m7/x `[= ~(symbol "\u03BB") ~(fix (* 2 js/Math.PI ) 2)]]


          [m7/m '[= f [1 T] [:m [1 64] Hz] ]]
          #_[:div tn]





          #_[m7/mx `[= [:m r ~(symbol (str "tan" (name tt)))] [:k opp 1]]]
          [m7/mx `[= [:m r ~(symbol (str "sin" (name tt)))]
                   opp]]


          [m7/x `[= [:m sin [[:m ~timer ~pi] 64]] ~(fix (js/Math.sin tn) 2)]]


          [m7/mx `[= [:m r ~(symbol (str "cos" (name tt)))]
                   adj]]

          #_[m7/mx `[= ~tt [:p ~(fix (/ 180 tn) 1)
                            ~deg]]]

          #_[m7/mx `[= [:k ~tt 2] [:p ~(fix (/ 180 tn2) 1)
                                   ~deg]]]


          #_[m7/mx `[= [* ~r [:m tan [:p ~(fix (/ 180 tn) 1)
                                      ~deg]]]
                     [:k opp 1]]]


          #_[m7/mx `[= [* ~r [:m tan [:p ~(fix (/ 180 tn) 1)
                                      ~deg]]]
                     ~opp1]]










          ])



       [:div {:style (m7/css
                      [[2 10 3 23 :center :center 3 :rem]
                       [1 70 90 1] []
                       {:gap "1rem"
                        :z-index 1}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}


           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]
           (grid-on 1 1)

           (let [rad tn
                 r (* r 20)]
             [:g

              [:circle {:r r
                        :cx 0
                        :cy 0
                        :stroke-width 5
                        :stroke-dashoffset 0
                        :stroke-dasharray 0
                        :stroke (hsl [1 30 70 1])
                        :fill (hsl [.6 95 70 .5])}]


              (if true
                [:g#arcs
                 (map
                  (fn [se]
                    [:g

                     [:circle {:r 4
                               :cx (* r (js/Math.cos (* js/Math.PI se (/ 1 32))))
                               :cy (ve (* r (js/Math.sin (* js/Math.PI se (/ 1 32)))))
                               :fill (hsl [4 70 70 .5])}]
                     [:text {:x (* r (js/Math.cos (* js/Math.PI se (/ 1 32))))
                             :y (ve (* r (js/Math.sin (* js/Math.PI se (/ 1 32)))))
                             :font-size 10
                             }
                      se]])
                  (range 0 (* 2 32)))])



              #_[:g#r1
               (map
                (fn [ang]
                  [:path {:d (m7/path [0 0 :l r 0 ])
                          :id :rrr2
                          :fill :none
                          :transform (m7/tranfrom
                                      [[:rotate ang]])
                          :stroke (hsl [5 70 80 .6])
                          :stroke-width 3}])
                [(ve tn) 0])]

              #_[:g#tan2
               (map
                (fn [ang]
                  [:path {:d (str
                              (m7/path [0 0 :l r 0 0
                                        (*  r (js/Math.tan
                                               (/ js/Math.PI
                                                  (nth turns sec2))))])
                              "z")
                          :id :rrrt1
                          :fill (hsl [4 70 80 .6])
                          :transform (m7/tranfrom [[:rotate ang]])
                          :stroke (hsl [4 70 80 .6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])]



              #_[:g#tan
               (map
                (fn [ang]
                  [:path {:d (str
                              (m7/path [0 0 :l r 0 0
                                        (*  r (js/Math.tan
                                               (/ js/Math.PI
                                                  (nth turns sec2))))])
                              "z")
                          :id :rrrt1
                          :fill (hsl [4 70 80 .6])
                          :transform (m7/tranfrom [
                                                   [:rotate ang]])
                          :stroke (hsl [4 70 80 .6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])


                 (map
                  (fn [ang]
                    [:path {:d (m7/path [0 0 :l  0
                                         20 -20 0 0 -20 ])

                            :fill (hsl [0 70 80 .6])
                            :transform (m7/tranfrom [
                                                     [:rotate ang]
                                                     [:translate [r 0]]])
                            :stroke (hsl [0 70 80 1])
                            :stroke-width 3}])
                  [(ve (/ 180 rad))])
                 [:text


                  [:textPath {:href :#rrrt1
                              :font-size 20
                              :startOffset (+ r (*  r (js/Math.tan
                                                       (/ js/Math.PI
                                                          (nth turns sec2))))
                                              r)}
                   "hyp"
                   [:tspan {:dy 10} 1]]



                  [:textPath {:href :#rrrt1
                              :font-size 20
                              :startOffset
                              (+ r (* .5 r
                                      (js/Math.tan
                                       (/ js/Math.PI
                                          (nth turns sec2)))))}
                   "opp"
                   [:tspan {:dy 10} 1]]]
                 ]









              #_[:text
               [:textPath {:href :#rrr2
                           :font-size 10
                           :startOffset "30%"}
                "R"]]




              (if false
                [:g
                 [:path#angle {:d
                               (m7/path
                                [0 0 :l r 0 :a r r 0 (if (< op 0) true false) false
                                 (ve (- r (* 20 adj))) (ve (* 20  op))])
                               :stroke (hsl [3 70 70 1])
                               :transform (m7/tranfrom [[:scale [.2 .2]]])
                               :stroke-width 3
                               :fill (hsl [3 70 70 .5])}]





                 [:path#sin {:d
                             (m7/path [0 0 :l (* 20 adj) 0 0 (ve (* 20 op))])
                             :id :tri22
                             :stroke (hsl [1 70 70 1])
                             :stroke-width 1
                             :fill (hsl [1.5 70 70 .5])}]])


              #_[:g {:transform (m7/tranfrom [[:translate [73 0]]])}
               [:path#angle2 {:d
                              (m7/path
                               [0 0 :l r 0 :a r r 0 false false
                                (ve (- r (* 20 adj2))) (ve (* 20  op2))])
                              :stroke (hsl [4 70 70 1])
                              :transform (m7/tranfrom [[:scale [.2 .2]]])
                              :stroke-width 3
                              :fill (hsl [3 70 70 .5])}]


               [:path#sin2 {:d
                            (m7/path [0 0 :l (* 20 adj2) 0 0 (ve (* 20 op2))])
                            :id :tri222
                            :stroke (hsl [4.5 70 70 1])
                            :stroke-width 1
                            :fill (hsl [2.5 70 70 .5])}]


               [:text

                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ 0 (* adj2 20))}

                 (str "B" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ (* .95 op2 20) (* adj2 20))}

                 (str "A1" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :dy 10
                            :startOffset (+ 0 0)}
                 (str "C1" "")]

                ]



               ]





              #_[:path.tri2 {:d (m7/path
                          [0 0 :l (* .3 adj 20) 0 0 (ve (* 20 op))])
                      :transform (m7/tranfrom
                                  [[:translate [(* .7 20 adj) 0]]])
                      :stroke (hsl [1 70 70 1])
                      :stroke-width 3
                      :fill (hsl [3.5 70 70 .5])}]


              #_[:text
               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ 0 0)}
                (str "C" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ 0 (* adj 20))}

                (str "B" "")]




               #_[:textPath {:href :#tri22
                           :font-size 15
                           :startOffset (+ 0 (* 20 .7 adj))}

                (str "T" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* .95 op 20) (* adj 20))}

                (str "A" "")]




               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* .3 adj 20) 0)}

                #_(str "adj" "")
                adj

                ]


               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset
                           (+
                            (* 20 adj)
                            (* .4 20 op))}

                #_(str "opp" "")

                op
                  ]]

              #_[:path {:d (m7/path [0 -40 :l 2200 0])
                      :fill :none
                      :stroke-width 4
                      :stroke (hsl [2 70 70 1])}]


              [:path#lam {:d (m7/path [0 40 :l (* 2 js/Math.PI r) 0])
                          :fill :none
                          :stroke-width 4
                          :stroke (hsl [0 50 40 1])}]

              [:path#lam2 {:d (m7/path [0 0 :l (*  2 r) 0])
                           :fill :none
                           :stroke-width 4
                           :stroke (hsl [1 50 40 1])}]

              [:text
               #_[:textPath {:href :#lam
                           :startOffset "30%"}
                "Periodic Time, T"]

               [:textPath {:href :#lam
                           :dy -20
                           :startOffset "30%"}
                "c"]


               [:textPath {:href :#lam2
                           :dy -20
                           :startOffset "30%"}
                "d"]

               ]
              (if true
                  [:g#wave
                   (map-indexed

                    (fn [index i]
                      [:g

                       #_[:circle {:cx (* i r)
                                   :cy (ve (* 1.3 r (js/Math.sin i)))
                                   :r 3
                                   :fill (hsl [2 70 70 1])}]



                       #_[:circle {:cx (* i r)
                                   :cy (ve (- (* 1.0 r (js/Math.sin i)) (* 0.9 r (js/Math.sin i))))
                                   :r 3
                                   :fill (hsl [2 70 70 1])}]

                       #_[:circle {:cx (* i r)
                                   :cy (+ -40 (ve (* 1.0 r (js/Math.sin i))))
                                   :r 3
                                   :fill (hsl [1 70 70 1])}]


                       #_[:circle {:cx (* i r)
                                 :cy (ve (* 1.0 r (js/Math.sin i)))
                                 :r 3
                                 :fill (hsl [0 70 70 1])}]
                       #_[:circle {:cx (* i r)
                                   :cy (ve (ve (* 0.9 r (js/Math.sin i))))
                                   :r 3
                                   :fill (hsl [1 70 70 1])}]

                       [:circle {:cx (* i r)
                                 :cy 0
                                 :r 3
                                 :fill (hsl [4 70 70 1])}]
                       (if (= (mod index 2) 0)
                         [:text {:x (* i r)
                                 :y 0
                                 :dx -10
                                 :dy 15
                                 :font-size 10
                                 :fill (hsl [4 20 20 1])}
                          index
                          #_(str index (name pi))
                          #_[:tspan {:dy 12
                                   :dx -13}
                           32]

                          ])


                       ])
                    (range 0 20 (/ js/Math.PI 32)))])



              [:circle {:cx (* r sec2)
                        :cy (ve (* 20 op))
                        :r 5
                        :fill (hsl [5 70 70 1])}]


              [:circle {:cx (* 20 adj)
                        :cy (ve (* 20 op))
                        :r 5
                        :fill (hsl [5 70 70 1])}]


              [:circle {:cx (* r sec2)
                        :cy 0
                        :r 5
                        :fill (hsl [4 70 70 1])}]


              #_[:text {:x 0
                      :style {:font-size (:font-size angle)}
                      :dy (:dy angle)
                      :dx (:dx angle)
                      :y 0}
               #_(name tt)
               #_(fix (* (/ 180 js/Math.PI) (js/Math.asin .92)
                       ) 2)
               (fix (/ 180 (nth turns sec2)) 1)
               [:tspan {:dy -6}
                (name deg)]
               ]


              ])

           ])]])))



(defn sine-wave2 []
  (let [[timer update-time]
        (react/useState 0)

        sec2 (* timer js/Math.PI (/ 1 32))
        angle {:font-size 12
               :dy -5
               :dx 30}

        tn sec2
        tn2 sec2
        r 10
        adj (fix (* r  (js/Math.cos tn))  2)
        op (fix (* r  (js/Math.sin tn))  2)
        opp1 (fix (* r  (js/Math.tan tn))  2)

        adj2 (fix (* r  (js/Math.cos tn))  2)
        op2 (fix (* r  (js/Math.sin tn))  2)
        opp12 (fix (* r  (js/Math.tan tn))  2)

        _ (react/useEffect
           (fn []
             (let [i (js/setInterval #(update-time (mod (+ timer 1) 128)) 1000)]
               (fn []
                 (js/clearInterval i)))))

        [slider get-slider] (react/useState 0)
        dd '☐
        deg '°
        pi 'π
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1]

        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40
          vb (fn [z]
               (nth [(map  #(* % 15) [20 -22  40 45])

                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]
                     ] z))
          viewbox (vb 0)
          viewbox2 (vb 0)
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .1])})}


       (if false
         [:div {:style (m7/css
                        [[2 7 2 9 :center :center
                          3.8 :rem :column]
                         [1 70 90 .1] []
                         {:gap "1rem"
                          :color (hsl [3 50 30 1])
                          :z-index 2}])}




          [m7/mx `[= c [* ~pi [:m 2 r]]]]
          [m7/mx `[= c [* ~(fix js/Math.PI 5) [:m 2 r]]]]
          #_[m7/x `[= ~pi  [c [:m 2 r]]]]


          #_[:div "what would be angle with arc length of 3 of an unit circle?"]


          #_[:div "what is the circumference of unit circle?"]


          #_[m7/x `[= [[:m 2 ~pi] 64]  [[* 2 ~(fix js/Math.PI 4)] 64] [c 64]]]

          #_[m7/x `[= [:m 2 ~pi ] c ~(fix (* 2 js/Math.PI) 4) [:m 360 ~deg]]]

          #_[:div "what would be angle with arc length of 3 of an unit circle?"]

          #_[m7/x `[= Arc [c 16] [~pi 8]  ~(fix (* 2 js/Math.PI (/ 1 16)) 4)]]


          #_[m7/x `[=  [:p 3 c] [:m ~(fix (/ (* 3 360) (* 2 js/Math.PI)) 2) ~deg]]]



          #_[m7/x `[= [~pi 8] [[:m 360 ~deg] 16] [:m ~(/ 360 16) ~deg]]]


          #_[:div "for unit circle what degree makes up arc length of " [m7/x `[~pi 8]] "?"]




          #_[:div "when r is a unit"]
          #_[m7/x `[= [:m 2 ~pi  ] c]]

          #_[:div "cutting the perimeter into 28 peaces makes each arch"]


          #_[m7/x `[ [:m 2 ~pi  ] 28]]

          #_[m7/x `[= ~pi ~js/Math.PI ]]



          #_[m7/m '[= c [* 2 3.1427  r ]]]
          #_[m7/m '[=  Time_Period T]]

          #_[:div "How long it goes on time T is the wave length"]


          #_[m7/x '[= [:m 60 Hz] [:m [1 60] s]]]

          #_[m7/m '[= frequency f [1 T]]]

          #_[m7/m '[= clock-frequency f [1 [:m 60 s]]
                    [:m [1 60] Hz]]]

          ])


       (if false
         [:div {:style (m7/css
                        [[2 10 14 9 :center :center
                          2.8 :rem :column]
                         [1 70 90 .1] []
                         {:gap "1rem"
                          :color (hsl [3 50 30 1])
                          :z-index 2}])}



          #_[m7/m '[c [:m 2 r]]]

          #_[m7/mx `[= BC ~adj]]

          #_[m7/mx `[= ~(symbol (str "tan" (name tt)))
                     [[:k opp 1] [:k adj 1]]]]

          [m7/x `[= [:m g [:b [+ t dt]]] [+ [:m g [:b t]]  [:m d x ~(symbol "g\u030A")  [:b t]]]]

           [:div "g\u030A g \u030A"]]
          [m7/x `[[:m  ~timer ~pi] 32]]

          [:div "Periodic Time"]
          [m7/x `[=  T [:m 64 s]]]
          [:div "Wave length, \u03BB"]
          [m7/x `[= ~(symbol "\u03BB") ~(fix (* 2 js/Math.PI ) 2)]]


          [m7/m '[= f [1 T] [:m [1 64] Hz] ]]
          #_[:div tn]





          #_[m7/mx `[= [:m r ~(symbol (str "tan" (name tt)))] [:k opp 1]]]
          [m7/mx `[= [:m r ~(symbol (str "sin" (name tt)))]
                   opp]]


          [m7/x `[= [:m sin [[:m ~timer ~pi] 64]] ~(fix (js/Math.sin tn) 2)]]


          [m7/mx `[= [:m r ~(symbol (str "cos" (name tt)))]
                   adj]]

          #_[m7/mx `[= ~tt [:p ~(fix (/ 180 tn) 1)
                            ~deg]]]

          #_[m7/mx `[= [:k ~tt 2] [:p ~(fix (/ 180 tn2) 1)
                                   ~deg]]]


          #_[m7/mx `[= [* ~r [:m tan [:p ~(fix (/ 180 tn) 1)
                                      ~deg]]]
                     [:k opp 1]]]


          #_[m7/mx `[= [* ~r [:m tan [:p ~(fix (/ 180 tn) 1)
                                      ~deg]]]
                     ~opp1]]










          ])



       [:div {:style (m7/css
                      [[2 10 3 23 :center :center 3 :rem]
                       [1 70 90 1] []
                       {:gap "1rem"
                        :z-index 1}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}


           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]




           (grid-on 1 1)




           (let [rad tn
                 r (* r 20)]
             [:g

              [:circle {:r r
                        :cx 0
                        :cy 0
                        :stroke-width 5
                        :stroke-dashoffset 0
                        :stroke-dasharray 0
                        :stroke (hsl [1 30 70 1])
                        :fill (hsl [.6 95 70 .5])}]


              (if true
                [:g#arcs
                 (map
                  (fn [se]
                    [:g

                     [:circle {:r 4
                               :cx (* r (js/Math.cos (* js/Math.PI se (/ 1 32))))
                               :cy (ve (* r (js/Math.sin (* js/Math.PI se (/ 1 32)))))
                               :fill (hsl [4 70 70 .5])}]
                     [:text {:x (* r (js/Math.cos (* js/Math.PI se (/ 1 32))))
                             :y (ve (* r (js/Math.sin (* js/Math.PI se (/ 1 32)))))
                             :font-size 10
                             }
                      se]])
                  (range 0 (* 2 32)))])



              #_[:g#r1
               (map
                (fn [ang]
                  [:path {:d (m7/path [0 0 :l r 0 ])
                          :id :rrr2
                          :fill :none
                          :transform (m7/tranfrom
                                      [[:rotate ang]])
                          :stroke (hsl [5 70 80 .6])
                          :stroke-width 3}])
                [(ve tn) 0])]

              #_[:g#tan2
               (map
                (fn [ang]
                  [:path {:d (str
                              (m7/path [0 0 :l r 0 0
                                        (*  r (js/Math.tan
                                               (/ js/Math.PI
                                                  (nth turns sec2))))])
                              "z")
                          :id :rrrt1
                          :fill (hsl [4 70 80 .6])
                          :transform (m7/tranfrom [[:rotate ang]])
                          :stroke (hsl [4 70 80 .6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])]



              #_[:g#tan
               (map
                (fn [ang]
                  [:path {:d (str
                              (m7/path [0 0 :l r 0 0
                                        (*  r (js/Math.tan
                                               (/ js/Math.PI
                                                  (nth turns sec2))))])
                              "z")
                          :id :rrrt1
                          :fill (hsl [4 70 80 .6])
                          :transform (m7/tranfrom [
                                                   [:rotate ang]])
                          :stroke (hsl [4 70 80 .6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])


                 (map
                  (fn [ang]
                    [:path {:d (m7/path [0 0 :l  0
                                         20 -20 0 0 -20 ])

                            :fill (hsl [0 70 80 .6])
                            :transform (m7/tranfrom [
                                                     [:rotate ang]
                                                     [:translate [r 0]]])
                            :stroke (hsl [0 70 80 1])
                            :stroke-width 3}])
                  [(ve (/ 180 rad))])
                 [:text


                  [:textPath {:href :#rrrt1
                              :font-size 20
                              :startOffset (+ r (*  r (js/Math.tan
                                                       (/ js/Math.PI
                                                          (nth turns sec2))))
                                              r)}
                   "hyp"
                   [:tspan {:dy 10} 1]]



                  [:textPath {:href :#rrrt1
                              :font-size 20
                              :startOffset
                              (+ r (* .5 r
                                      (js/Math.tan
                                       (/ js/Math.PI
                                          (nth turns sec2)))))}
                   "opp"
                   [:tspan {:dy 10} 1]]]
                 ]









              #_[:text
               [:textPath {:href :#rrr2
                           :font-size 10
                           :startOffset "30%"}
                "R"]]




              (if false
                [:g
                 [:path#angle {:d
                               (m7/path
                                [0 0 :l r 0 :a r r 0 (if (< op 0) true false) false
                                 (ve (- r (* 20 adj))) (ve (* 20  op))])
                               :stroke (hsl [3 70 70 1])
                               :transform (m7/tranfrom [[:scale [.2 .2]]])
                               :stroke-width 3
                               :fill (hsl [3 70 70 .5])}]





                 [:path#sin {:d
                             (m7/path [0 0 :l (* 20 adj) 0 0 (ve (* 20 op))])
                             :id :tri22
                             :stroke (hsl [1 70 70 1])
                             :stroke-width 1
                             :fill (hsl [1.5 70 70 .5])}]])


              #_[:g {:transform (m7/tranfrom [[:translate [73 0]]])}
               [:path#angle2 {:d
                              (m7/path
                               [0 0 :l r 0 :a r r 0 false false
                                (ve (- r (* 20 adj2))) (ve (* 20  op2))])
                              :stroke (hsl [4 70 70 1])
                              :transform (m7/tranfrom [[:scale [.2 .2]]])
                              :stroke-width 3
                              :fill (hsl [3 70 70 .5])}]


               [:path#sin2 {:d
                            (m7/path [0 0 :l (* 20 adj2) 0 0 (ve (* 20 op2))])
                            :id :tri222
                            :stroke (hsl [4.5 70 70 1])
                            :stroke-width 1
                            :fill (hsl [2.5 70 70 .5])}]


               [:text

                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ 0 (* adj2 20))}

                 (str "B" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ (* .95 op2 20) (* adj2 20))}

                 (str "A1" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :dy 10
                            :startOffset (+ 0 0)}
                 (str "C1" "")]

                ]



               ]





              #_[:path.tri2 {:d (m7/path
                          [0 0 :l (* .3 adj 20) 0 0 (ve (* 20 op))])
                      :transform (m7/tranfrom
                                  [[:translate [(* .7 20 adj) 0]]])
                      :stroke (hsl [1 70 70 1])
                      :stroke-width 3
                      :fill (hsl [3.5 70 70 .5])}]


              #_[:text
               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ 0 0)}
                (str "C" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ 0 (* adj 20))}

                (str "B" "")]




               #_[:textPath {:href :#tri22
                           :font-size 15
                           :startOffset (+ 0 (* 20 .7 adj))}

                (str "T" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* .95 op 20) (* adj 20))}

                (str "A" "")]




               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* .3 adj 20) 0)}

                #_(str "adj" "")
                adj

                ]


               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset
                           (+
                            (* 20 adj)
                            (* .4 20 op))}

                #_(str "opp" "")

                op
                  ]]

              #_[:path {:d (m7/path [0 -40 :l 2200 0])
                      :fill :none
                      :stroke-width 4
                      :stroke (hsl [2 70 70 1])}]


              [:path#lam {:d (m7/path [0 40 :l (* 2 js/Math.PI r) 0])
                          :fill :none
                          :stroke-width 4
                          :stroke (hsl [0 50 40 1])}]

              [:path#lam2 {:d (m7/path [0 0 :l (*  2 r) 0])
                           :fill :none
                           :stroke-width 4
                           :stroke (hsl [1 50 40 1])}]

              [:text
               #_[:textPath {:href :#lam
                           :startOffset "30%"}
                "Periodic Time, T"]

               [:textPath {:href :#lam
                           :dy -20
                           :startOffset "30%"}
                "c"]


               [:textPath {:href :#lam2
                           :dy -20
                           :startOffset "30%"}
                "d"]

               ]
              (if true
                  [:g#wave
                   (map-indexed

                    (fn [index i]
                      [:g

                       #_[:circle {:cx (* i r)
                                   :cy (ve (* 1.3 r (js/Math.sin i)))
                                   :r 3
                                   :fill (hsl [2 70 70 1])}]



                       #_[:circle {:cx (* i r)
                                   :cy (ve (- (* 1.0 r (js/Math.sin i)) (* 0.9 r (js/Math.sin i))))
                                   :r 3
                                   :fill (hsl [2 70 70 1])}]

                       #_[:circle {:cx (* i r)
                                   :cy (+ -40 (ve (* 1.0 r (js/Math.sin i))))
                                   :r 3
                                   :fill (hsl [1 70 70 1])}]


                       #_[:circle {:cx (* i r)
                                 :cy (ve (* 1.0 r (js/Math.sin i)))
                                 :r 3
                                 :fill (hsl [0 70 70 1])}]
                       #_[:circle {:cx (* i r)
                                   :cy (ve (ve (* 0.9 r (js/Math.sin i))))
                                   :r 3
                                   :fill (hsl [1 70 70 1])}]

                       [:circle {:cx (* i r)
                                 :cy 0
                                 :r 3
                                 :fill (hsl [4 70 70 1])}]
                       (if (= (mod index 2) 0)
                         [:text {:x (* i r)
                                 :y 0
                                 :dx -10
                                 :dy 15
                                 :font-size 10
                                 :fill (hsl [4 20 20 1])}
                          index
                          #_(str index (name pi))
                          #_[:tspan {:dy 12
                                   :dx -13}
                           32]

                          ])


                       ])
                    (range 0 20 (/ js/Math.PI 32)))])



              [:circle {:cx (* r sec2)
                        :cy (ve (* 20 op))
                        :r 5
                        :fill (hsl [5 70 70 1])}]


              [:circle {:cx (* 20 adj)
                        :cy (ve (* 20 op))
                        :r 5
                        :fill (hsl [5 70 70 1])}]


              [:circle {:cx (* r sec2)
                        :cy 0
                        :r 5
                        :fill (hsl [4 70 70 1])}]


              #_[:text {:x 0
                      :style {:font-size (:font-size angle)}
                      :dy (:dy angle)
                      :dx (:dx angle)
                      :y 0}
               #_(name tt)
               #_(fix (* (/ 180 js/Math.PI) (js/Math.asin .92)
                       ) 2)
               (fix (/ 180 (nth turns sec2)) 1)
               [:tspan {:dy -6}
                (name deg)]
                 ]

              [:g#spring {:transform (m7/tranfrom [
                                                   [:rotate 90]
                                                   [:translate [0 (ve (* 2 r))]]
                                                   [:scale 4.2]
                                                   ])}
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
                    :stroke-width 1
                    :fill :none}
                [:animate
                 {:attributeName :d
                  :begin :click
                  :dur (sec 10)
                  :keyTimes (m7/sami-colon [0 0.3  0.4 .5
                                            0.6 0.75 0.9 1])
                  :values (m7/sami-colon
                           (map
                            (fn [i j]
                              (path (flatten
                                     [0 0
                                      (repeat 10
                                              [
                                               :a 2 j 0 false false  0 (+ 10 i)
                                               :a 2 j 0 true false  0 -3])
                                      :a 2 j 0 false false  0 (+ i 10)
                                      :l 0 7
                                      :a 2 j 0 false true   0 3
                                      :a 2 j 0 false true   0 -3
                                      ]))
                              )
                            (map #(* % 5) [0 .50 .7 1
                                           1 .7 .5 0
                                           -0.7 .5  ])
                            [1 1.1 1.2 1.5
                             1.5 1.2 1.1 1]
                         ))

               :fill :freeze
               }]]
               ;; :ccr2.begin
               #_[:path {:d (path (flatten
                              [0 (* 7 12)
                               :a 1 1 0 false false  0 20
                               :a 1 1 0 false false  0 -20
                               ]))
                    :stroke (hsl [2 70 70 1])
                    :stroke-width 2
                    :fill (hsl [2 70 70 1])}

             [:animateTransform
              {:id :ccr2
               :attributeName :transform
               :begin :click
               :dur (sec 1)
               :type :translate
               :keyTimes (m7/sami-colon [0 0.5 0.8 1])
               :values (m7/sami-colon (map m7/space
                                           (map
                                            (fn [[x y]]
                                              [0 (* y 10)])
                                            [
                                             [0 0] [0 (* .5 10)]
                                             [0 (* 1 10)] [0 (* 1.5 10)]])))
               :fill :freeze
               }]
             ]
            ]


              ])



           ])]])))



(defn home-pressure2 []
  (let [[slider get-slider] (react/useState 0)]
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


       #_[:div {:style
                (m7/css
                 [[4 3 15 7
                   :center :center  2 :rem :column]
                  [3.5 70 (+ 50 (* 5 5))  .7] []
                  {:gap ".1rem"
                   :z-index 10}])}

        [:div "hydrocarbon + oxygen = water + carbondioxide"]
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
           [:marker {:id (name :dot122)
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

           #_(grid-on 20 20)

           (alkine-oh 1)
           (line3 161 [-700 0]  "h1")
           (line3 175 [-700 0]  "h2")

           (line3 167.9 [-700 0]  "h2")

           (line3 176.8 [-700 0]  "h2")





           (line3 3.5 [700 0]  "h2")
           (line3 5 [700 0]  "h2")
           (line3 13.6 [700 0]  "h2")
           (line3 19 [700 0]  "h2")




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

           ]
          )]


       ])))





(defn home-iso []
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

        [:div "hydrocarbon + oxygen = water + carbondioxide"]
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

           (alkine-oh 5)



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




(defn home-catalatic []
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

        [:div "hydrocarbon + oxygen = water + carbondioxide"]
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

           (alkine-oh 5)



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




#_(react/useEffect
 (fn []
   (let [i (js/setInterval #(update-time (js/Date.)) 100)]
     (fn []
       (js/clearInterval i)))))

(defn airplane []
  (let [[timer update-time] (react/useState (js/Date.))

        time-str (-> timer .toTimeString
                     (str/split " ")
                     first
                     (str/split ":" )
                     (nth 2))

        sec2  (* 5 (js/parseInt
                    (fix
                     (/ (js/parseInt time-str) 5) 1)))

        _ (react/useEffect
           (fn []
             (let [i (js/setInterval #(update-time (js/Date.)) 100)]
               (fn []
                 (js/clearInterval i)))))
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
                            (take 16 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}


       [:div {:style
              (m7/css
               [[4 2  6 2
                 :center :center  3.0 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :color (hsl [0 30 60 1])
                 :z-index 10}])}

        [:div time-str]

        ]

       (if true
         (let [d 'ε]
           [:div {:style
                  (m7/css
                   [[2 5  8 8
                     :center :center 4.0 :rem :column]
                    [3.5 70 (+ 50 (* 5 5))  .7] []
                    {:gap ".1rem"
                     :color (hsl [0 30 60 1])
                     :z-index 10}])}

            [m7/mx `[= s [:m v t]]]
            #_[m7/mx `[= s [:m a [:p t 2]]]]
            #_[m7/mx `[= a [11 100]]]
            [m7/mx `[= s [:m a [:p t 2]]]]


            #_[m7/mx `[= 11 [:m  100 a]]]

            #_[m7/mx `[= a [11 100]]]








            #_[:div "velocity is " [m7/m '[:m 10 [m s]]]]
            #_[m7/mx `[= ~(* 10 time-str) [* 10 ~(* 1 time-str)]]]







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









            ]))











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


           [:g#scale-1 {:transform (m7/tranfrom [
                                                 [:rotate -90]
                                                 [:scale [.2 .2]]])}
            (map (fn [x]
                   [:circle {:cx (* 2 2 50 x)
                             :cy 0
                             :r 2
                             :fill (hsl [0 60 70 1])}])
                 (range 0 30))


            (map (fn [x]
                   [:text {:x (* 2 100 x)
                           :y 0
                           :dy 10
                           :font-size 25
                           :fill (hsl [1 20 20 1])}
                    (* 100 x)])
                 (range 0 30))

            [:g#linear




               #_[line3 0 [0 0] "e"]



               [:animateTransform
                {:id :plane22
                 :attributeName :transform
                 :dur (sec 60)
                 :begin :plane23.begin
                 :repeatCount :indefinite
                 :type :translate
                 :fill :remove
                 :values (m7/sami-colon
                          (map
                           m7/space
                           [[0 0]  [(* 2 60 10) 0]]))
                 :keyTimes (m7/sami-colon
                            [0  1])



                 }]


             [airplane2 [[:rotate 0]
                         [:translate [-110 0]]
                         [:scale [.3 .3]]]]




               ]



           ]



           #_(let [a .11]
             [:g.scale2
              (map (fn [x]
                     [:circle {:cx (*  2   a  (* 10 x)  (* 10 x))
                               :cy 150
                               :r 2
                               :fill (hsl [0 60 70 1])}])
                   (range 0 10))

              (map (fn [x y]
                     [:g
                      [:text {:x (*  2   a  (* 10 x)  (* 10 x))
                              :y 150
                              :dy 40
                              :font-size 20
                              :fill (hsl [1 20 20 1])}

                       (*  1   a  (* 10 x)  (* 10 x))]

                      #_[:text {:x (+ (/ y 2)  (* 2 2 5 5 a x x))
                              :y 150
                              :dy 80
                              :style {:font-family
                                      "'Roboto Flex'"}
                              :font-size 20
                              :fill (hsl [1 20 20 1])}
                       y]])
                   (range 0 10)
                   (map
                    (fn [x y]
                      (- x y))

                    (rest
                     (map (fn [x]
                            (* 2 a 5 5 x x))
                          (range 0 11)))
                    (map (fn [x]
                           (* 2 a 5 5 x x))
                         (range 0 10)))
                   )

              [:g





               #_[line3 0 [0 0] "e"]



               [:animateTransform

                {:id :plane23
                 :attributeName :transform
                 :begin :click
                 :dur (sec 60)
                 :type :translate
                 :fill :remove
                 :repeatCount :indefinite
                 :values (m7/sami-colon
                          (map
                           m7/space
                           (map (fn [i]
                                  [(* a 5 5 2 i i) 0])
                                (range 0 13))))

                 :keyTimes (m7/sami-colon
                            (map #(/ % 60)
                                 (range 0 65 5)))



                 }]


               [airplane2 [[:rotate 0]
                           [:translate [-110 150]]
                           [:scale [.3 .3]]]]




               ]



              ])




           ]
          )]


       ])))


#_[:g
   (line3 0 [0 (ve y)] "e")
   (line3 0 [0  y] "e")


   (comment
     )

   (line3 -130 [(- y 17)  0] "l")
   (line3 -130 [(ve (- y 17)) 0] "l")]


(defn triangle2 []
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
           :style {:font-size "2rem"}
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

   ])



(defn water [tf]
  [:g {:transform (m7/tranfrom tf)}
             [:path {:d (m7/path `[0 0 :c 30 20 40 40 40 120 ])
                     :stroke-width 4
                     :stroke (hsl [3.5 70 70 1])
                     :fill :none}
              [:animateTransform
               {
                :attributeName :transform
                :type :scale
                :begin (m7/sec 0)
                :dur (m7/sec 2)
                :fill :freeze
                :from (m7/space [1.3 1])
                :to (m7/space [.7 1])}

               ]
              [:path {:d (m7/path `[0 0 :c 34 20 40 40 40 60 ])
                      :stroke-width 4
                      :stroke (hsl [3.5 70 70 1])
                      :fill :none}
               [:animateTransform
                {
                 :attributeName :transform
                 :type :scale
                 :begin (m7/sec 0)
                 :dur (m7/sec 2)
                 :fill :freeze
                 :from (m7/space [1.3 1])
                 :to (m7/space [.7 1])}

                ]
               ]
              [:path {:d (m7/path `[0 0 :c 30 20 44  40 38 60 ])
                      :stroke-width 4
                      :stroke (hsl [3.5 70 70 1])
                      :fill :none}
               [:animateTransform
                {
                 :attributeName :transform
                 :type :scale
                 :begin (m7/sec 3)
                 :dur (m7/sec 10)
                 :fill :freeze
                 :from (m7/space [1.3 1])
                 :to (m7/space [.7 1])}

                ]
               ]
              ]



    ])



#_(defn water2 [tf]
  [:g {:transform (m7/tranfrom tf)}


   (map
    (fn []
      [:path {:d (m7/path `[0 0 :c 30 20 40 40 40 120 ])
              :stroke-width 4
              :stroke (hsl [3.5 70 70 1])
              :fill :none}
       [:animateTransform
        {
         :attributeName :transform
         :type :scale
         :begin (m7/sec 0)
         :dur (m7/sec 2)
         :fill :freeze
         :from (m7/space [1.3 1])
         :to (m7/space [.7 1])}

        ]

       ])
    )



         ])

(defn ship [t]
  [:g
   {:stroke :none
    :fill (hsl [4 70 70 1])
    :transform (m7/tranfrom t)
   }
  [:path
   {:d bdmap/ship}]])



(defn pressure []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 -1 -1  0 0 1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4

          ax-dx 80
          ax-dy 40

          vb (fn [z]
               (nth [[100 -450  500 500]
                     [0 -200  300 300]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]
                     [-200 -300  600 400]] z))
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
               [[2 8 14 8
                 :center :center  2.5 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}


        [m7/mx `[= [:m cos ~tt] [[+ w f2] F2]]]

        [m7/mx `[= [:m f2 cos ~tt] [+ w f2]]]


        [m7/mx `[= [:m 50 N cos 30] [+ w f2]]]

        [m7/mx `[= [:m
                    43.30  N] [+ w f2]]]

        #_[m7/mx `[= [:m
                      ~(* 50
                          (js/Math.cos (/ js/Math.PI 6)))  N] [+ w f2]]]











        #_[m7/m '[= P [:m ρ  h g]]]
        #_[m7/m '[= [:k P o] [:m   12 km  ρ g]]]
        #_[m7/m '[= [:k P po] [:m   12 km ρ g]]]

        #_[m7/m '[= [:k P p] [:m   24 m [:b [* 12 ρ]] g]]]


        #_[m7/m '[:m 1  g [:p cm -3]]]
        #_[m7/m '[= [:k P bath] [:m ρ  12 km g]]]
        #_[:div "Specific Gravity"]

        #_[:div {:style {:font-size "1.8rem"}}
         "Ratio of density of a substance to the density of a given reference material"]
        #_[:k  r]

        #_[m7/m '[= [[:k ρ s]
                     ρ] 1.3]]

        #_[m7/m '[= W weight]]

        #_[m7/m '[= [:k W d] displaced-water]]

        #_[m7/m '[= [:k W r] resultent-weight]]

        #_[m7/m '[= [:k F r] [+ W  [:k F b]]]]
        #_[m7/m '[= [:k W r] [+ W  [:k F ]]]]

        #_[m7/m '[= [:k W r] [+ W [:k W d]]]]

        #_[m7/m '[= [- [:k m r]] [+ [- m] [:k m d]]]]

        #_[m7/m '[= [:m ρ  [:k V crown]] [- m [:k m r]]]]

        #_[m7/m '[= [:m [- 94] N] [+ [:m [- 100] N] [:k W d]]]]



        #_[m7/m '[= [:m 6 N] [:k W d] [:m 10 ρ [:k V crown] N]]]


        #_[m7/m '[= [:m [* .6 [:p 10 -3]] [:p m 3]] [:k V crown]]]



        #_[m7/m '[=  [:k ρ crown] [[:m 10 Kg] [:k V crown]]]]

        #_(/ (js/Math.pow 10 5) 6)
        #_[m7/m '[=  [:k ρ crown] [[:m [:p 10 5]  Kg] [:m 6 [:p m 3]]]]]


        #_[m7/m '[= [:m 94 N] [- [:m 100 N] [:m 10 [:k m d] ]]]]


        #_[m7/m '[= [:m 94 N] [- [:m 100 N] [:m 10 [:k m d] ]]]]


        #_[m7/m '[= [:m 6 N] [:m 10  ρ [:k V crown] ]]]


        #_[m7/m '[=  [:k V crown]  [[:m 6 N] [:m 10  ρ ]] ]]




        [:div {:style {:background-color (hsl [0 70 70 .31])}}
         #_[m7/m '[[:k ρ s]
                   [:m  [:p 10 3]  Kg  [:p m -3]]]]


         #_[m7/m '[[:p 19.3 2 ]
                 [:p 10 3]]]]




        #_[m7/m '[= [:m [:k m b] g]   [- mg [:m [:k m s] g]]]]

        #_[m7/m '[=  [:k ρ a] [[:m [:m 10 kg] ρ] [:m .6 kg]]  ]]

        #_[m7/m '[=  [:k ρ g]  [m V] [ [:m [:m 10 Kg]  [:p 10 3] [kg [:m m 3]] ] [:m .6 Kg]]]]


        #_[m7/m '[=  [:k ρ g] [:m 166666.6 [kg [:p m 3]]]]]
        #_[:div  {:style {:font-size "2rem"}} "g is an acceleration duo to gravitational pull" ]
        #_[m7/m '[= g [:m 9.8 [:m m [:p s -2]]]]]
        #_[m7/m '[= [:m ρ A h]
                  m]]
        #_[m7/m '[= P [W A] [ [:m ρ A h g] A] ]]
        #_[m7/m '[=  P [F A] [N [:p m 2]] [:m N [:p m -2]] Pa]]

        #_[m7/m '[= [:m [:k ρ w] V]  [:m ρ [:k V 1]]  ]]
        ;; [m7/m '[= ρV [:m V [m V]] ]]
        ;; [m7/m '[= m ρV ]]
        #_[m7/m '[=  [:m [:k ρ w] V ] [:m  ρ [:k V 1] ] ]]
        #_[m7/m '[=  [ V1 V] [[:k ρ w]  ρ ] ]]
        #_[m7/m '[=
                [ V1 V] [[:m [* 0.5 [:p 10 3]] kg [:p m 3]]
                         [:m [:p 10 3] kg [:p m 3]]] .5 ]]

        ;; [m7/m '[= P [W A] [[:m ρ A h g] A]  [:m ρ h g]  ]]


        ]


       #_[:div {:style
              (m7/css
               [[5 3 12 10
                 :center :center  7 :rem ]
                [1.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}


        #_[m7/m '[= [:p [:m 1 mL] -1] [:m [:p 10 6] [:p m -3]]]]
        #_[m7/m '[= [:p [:b [:p a m]] n] [:p a [* n m]]]]

        #_[m7/m '[= [:p [:b [:m b  [:p a m]]] n] [:m [:p b n] [:p a [* n m]]]]]
        #_[m7/m '[= [:p [:b [:m b  [:p a m]]] n] [:m [:p b n] [:p a [* n m]]]]]

        [m7/m '[= [:m [:p 10 -3] [:p 10 6]]

                  [:p 10 [+ [- 3] 6]]]]
        #_[:div "Volume, "]

        #_[m7/m '[= V [* l w h]]]
        #_[m7/m '[= ρ [m V] ]]

        #_[m7/m '[= V [* [:m 1 cm] [:m 1 cm] [:m 1 cm]]]]

        #_[m7/m '[= V [= [:m 1 [:p cm 3]] [:m 1 mL]]]]




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
                    :transform (m7/tranfrom [[:scale [.4 .4]]
                                             [:rotate 1]])
                    :fill (m7/hsl [2 70 70 1])}]]
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




           #_[:g
              (map (fn [i]
                     [:path {:stroke-width 2
                             :fill (hsl [(if (< i 2) 1 2) 70 70 1])
                             :stroke (hsl [7 70 70 1])
                             :d (m7/path
                                 `[~(* i 40) 0 :l ~@(map #(* 20 %1 %2)
                                                         dx
                                                         (cycle [2 1]))])}])
                   (range 0 5))]



           #_[:g
            (map (fn [i]
                   [:path {:stroke-width 2
                           :fill (hsl [(if (< i 4) 1 2) 70 70 1])
                           :stroke (hsl [7 70 70 1])
                           :d (m7/path
                               `[~(* i 20) 40 :l ~@(map #(* 20 %1 %2)
                                                       dx
                                                       (cycle [1 1]))])}])
                 (range 0 10))]



           [:g
            ;; water
            #_(water [[:translate  [-60 -120]]
                      [:scale [-1 1]]])

            #_(water [[:translate  [60 -120]]
                    [:scale [1 1]]])


            (let [x -60
                  y 95]
              [:g
               [:g  {:transform (m7/tranfrom [[:scale [1 1]]])}

                [:path {:d (m7/path `[~x ~y :l ~@(map #(* 120 %) dx)])
                        :stroke-dasharray (m7/space [(* 2 120) 120])
                        :stroke-width 3
                        :stroke (hsl [0 70 70 1])
                        :fill :none}]




                [:path {:d (m7/path `[~x ~y :l ~@(map #(* 120 %) dx)])
                        :stroke-dasharray (m7/space [(* 3 120)])
                        :stroke-width 0
                        :stroke (hsl [0 70 70 1])
                        :fill (hsl [3.5 70 70 1])}
                 ]


                ]
               [:path {:d (m7/path `[70 ~y :l ~@(map #(* 50 %) dx)])
                       :stroke-dasharray (m7/space [100 50])
                       :stroke (hsl [1 70 70 1])
                       :stroke-width 2
                       :fill (hsl [3.5 70 70 1])}
                [:animateTransform
                 {
                  :attributeName :transform
                  :type :scale
                  :begin (m7/sec 0)
                  :dur (m7/sec 2)
                  :fill :freeze
                  :to (m7/space [1 1])
                  :from (m7/space [1 .1])}
                 ]]
               ])






            [:g

             [:animateTransform
              {:attributeName :transform
               :begin (sec 0)
               :dur (sec 10)
               :type :translate
               :from (m7/space [0 -50])
               :to (m7/space [400 -50])
               :fill :freeze
               :repeatCount :indefinite
               }]

             #_(grid-on 2 2)




             (let [f1 [0 120]
                   f2 [100  -120 ]]
               [:g



                #_[:circle {:cx 0
                          :cy (- (* 6 20) 10)
                          :r 20
                          :stroke (hsl [1 60 70 1])
                          :fill (hsl [3 70 70 .6])}]


                #_[:circle {:cx 0
                          :cy (- (* 6 20) 10)
                          :r 2
                          :fill (hsl [5 70 70 1])}]

                #_(let [x -60
                      y 0]
                  [:g  {:transform (m7/tranfrom [[:translate [0 (* 6 20)]]
                                                [:scale [.05 1]]])}

                   [:path {:d (m7/path `[~x ~y :l ~@(map #(* 120 %) dx)])
                           :stroke-dasharray (m7/space [(* 2 120) 120])
                           :stroke-width 3
                           :stroke (hsl [0 70 70 .6])
                           :fill :none}]




                   [:path {:d (m7/path `[~x ~y :l ~@(map #(* 120 %) dx)])
                           :stroke-dasharray (m7/space [(* 3 120)])
                           :stroke-width 0
                           :stroke (hsl [0 70 70 1])
                           :fill (hsl [3.5 70 70 .6])}
                    ]


                   ])

                #_[ship [[:translate [-60 10]]
                         [:scale [0.01 -0.01]]]]
                ;; cube
                #_[:path {:d (m7/path `[-25 50 :l ~@(map #(* 50 %) dx)])
                          :stroke-dasharray (m7/space [100 50])
                          :stroke (hsl [1 70 70 1])
                          :stroke-width 2
                          :fill (hsl [1 70 70 1])}]

                #_(water [[:translate  [-60 0]]
                          [:scale [-1 1]]])

                #_(water [[:translate  [60 0]]
                          [:scale [1 1]]])



                [:path {:d bdmap/helicopter1
                        :fill (hsl [0.5 50 40 1])
                        :stroke :none
                        :transform (m7/tranfrom [
                                                 [:translate [-70  -50]]
                                                 [:scale [0.05 0.05]]
                                                 [:rotate 10]])
                        }]




                [:text {:dy -3}
                 [:textPath {:href :#pasw2
                             :fill (hsl [4 70 70 1])
                             :startOffset "20%"
                             :font-size 10} "W"]]

                [:path {:d (m7/path `[0 0 :l ~@f1])
                        :id :pasw2
                        :stroke-width 2
                        :marker-end (m7/url (name :dot3))

                        :stroke (hsl [1.5 70 80 1])
                        :fill (hsl [1.5 70 80 1])}
                 [:animateTransform
                  {:attributeName :transform
                   :begin (sec 0)
                   :dur (sec 12)
                   :type :translate
                   :from (m7/space [0 0])
                   :to (m7/space [0 0])
                   :fill :freeze
                   :id :shipw33
                   }]


                 ]


                [:path {:d (m7/path `[0 0 :l ~@(let [[x y] f1]
                                                 [x (ve y)])])
                        :id :paswbar2
                        :stroke-width 2
                        :marker-end (m7/url (name :dot3))
                        :stroke-dasharray "10 5"
                        :stroke (hsl [2.51 70 80 1])
                        :fill (hsl [2.5 70 80 1])}
                 [:animateTransform
                  {:attributeName :transform
                   :begin (sec 0)
                   :dur (sec 12)
                   :type :translate
                   :from (m7/space [0 0])
                   :to (m7/space [0 0])
                   :fill :freeze
                   :id :shipw33
                   }]


                 ]

                [:path {:d (m7/path `[0 0 :l ~@(let [[x y] f1]
                                                 [(* .2  x) (* .2 (ve y))])
                                      20 0])
                        :id :paswbarang2
                        :stroke-width 2
                        :marker-end (m7/url (name :dot3))
                        :stroke (hsl [5.0 70 80 1])
                        :fill (hsl [5.0 70 80 1])}
                 [:animateTransform
                  {:attributeName :transform
                   :begin (sec 0)
                   :dur (sec 12)
                   :type :translate
                   :from (m7/space [0 0])
                   :to (m7/space [0 0])
                   :fill :freeze
                   :id :shipw33
                   }]


                 ]

                [:g
                 [:text {:dy -2
                         :fill (hsl [5.1 70 70 0])}
                  [:textPath {
                           :href :#paswr2
                           :startOffset "10%"
                           :font-size 20} "W+F2"]
                  [:animate
                   {:attributeName :fill
                    :begin :buoyancy3.end
                    :dur (sec 5)
                    :from (hsl [0 70 70 0])
                    :to (hsl [.5 50 50 1])
                    :fill :freeze

                 }]]


              [:g
               [:text {:dy -3} [:textPath {
                                          :fill (hsl [0 70 70 .7])
                                          :href :#pasw3
                                          :startOffset "21%"
                                          :font-size 10}
                                 "F2"]]


               [:path {:d (m7/path `[0 0  :l ~@f2])
                       :id :pasw3
                       :stroke-width 4
                       :marker-end (m7/url (name :dot3))

                       :stroke (hsl [0.5 70 80 .5])
                       :fill (hsl [0.5 70 80 1])}
                [:animateTransform
                 {:attributeName :transform
                  :begin :shipw33.end
                  :dur (sec 5)
                  :type :translate
                  :from (m7/space [0 0])
                  :to (m7/space f1)
                  :fill :freeze
                  :id :buoyancy3
                  }]
                ]]

                 (let [[x1 y1] f1
                       [x2 y2] f2]
                   [:path {:d (m7/path [0 0 :l (+ x1 x2) (+ y1 y2) ])
                        :id :paswr2
                        :stroke-width 0
                        :marker-end (m7/url (name :dot3))
                        :stroke (hsl [4.5 70 80 .9])
                        :fill (hsl [4.5 70 80 .9])}

                    [:animate
                     {:attributeName :stroke-width
                      :begin :buoyancy3.end
                      :dur (sec 5)
                      :from 0
                      :to 4
                      :fill :freeze}]])


                 ]])


             #_[ship [[:translate [-60 0]]
                    [:scale [0.01 -0.01]]]]
             #_[:path {:d (m7/path `[-25 0 :l ~@(map #(* 50 %) dx)])
                     :stroke-dasharray (m7/space [100 50])
                     :stroke (hsl [1 70 70 1])
                     :stroke-width 2
                     :fill (hsl [1 70 70 1])}]


             #_"buoyancy"
             #_"upthrust"



             #_[:path {:d (m7/path [150 -150 :l 0 70 ])
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
            ]
           ]
          )]


       ])))


(defn pressure2 []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 -1 -1  0 0 1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4

          ax-dx 80
          ax-dy 40

          vb (fn [z]
               (nth [[100 -450  500 500]
                     [0 -200  300 300]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]
                     [-200 -300  600 400]] z))
          viewbox (vb 1)
          viewbox2 (vb 1)
          ]


      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}

       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]

                       [1 70 90 .1] [] {:gap "1rem"
                                        :z-index 3}])}

        [m7/m '[= [:m tan A] [ opp adj]]]]

       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]

                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
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
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:scale [.4 .4]]
                                             [:rotate 1]])
                    :fill (m7/hsl [2 70 70 1])}]]

           [:g

            [:g



             (let [f1 [0 -120]
                   f2 [120 0]]
               [:g

                [:path {:d bdmap/helicopter1
                        :fill (hsl [0.5 50 40 1])
                        :stroke :none
                        :transform (m7/tranfrom [
                                                 [:translate [-70  -50]]
                                                 [:scale [0.05 0.05]]
                                                 [:rotate 10]])
                        }]

                [:text {:dy -3}
                 [:textPath {:href :#pasw2
                             :fill (hsl [4 70 70 1])
                             :startOffset "20%"
                             :font-size 20} "oposite"]]

                [:path {:d (m7/path `[0 0 :l ~@f1])
                        :id :pasw2
                        :stroke-width 2
                        :marker-end (m7/url (name :dot3))

                        :stroke (hsl [1.5 70 80 1])
                        :fill (hsl [1.5 70 80 1])}
                 [:animateTransform
                  {:attributeName :transform
                   :begin (sec 0)
                   :dur (sec 2)
                   :type :translate
                   :from (m7/space [0 0])
                   :to (m7/space [0 0])
                   :fill :freeze
                   :id :shipw33
                   }]


                 ]
                [:g
                 [:text {:dy -2
                         :fill (hsl [5 70 70 0])}
                  [:textPath {
                              :href :#paswr2
                              :startOffset "20%"
                              :font-size 15} "hypotenious"]
                  [:animate
                   {:attributeName :fill
                    :begin :buoyancy3.end
                    :dur (sec 5)
                    :from (hsl [0 70 70 0])
                    :to (hsl [.5 50 50 1])
                    :fill :freeze

                 }]]


              [:g
               [:text {:dy -3} [:textPath {
                                          :fill (hsl [0 70 70 .7])
                                          :href :#pasw3
                                          :startOffset "20%"
                                          :font-size 20}
                                "adj"]]


               [:path {:d (m7/path `[0 0  :l ~@f2])
                       :id :pasw3
                       :stroke-width 4
                       :marker-end (m7/url (name :dot3))

                       :stroke (hsl [0.5 70 80 .5])
                       :fill (hsl [0.5 70 80 1])}
                [:animateTransform
                 {:attributeName :transform
                  :begin :shipw33.end
                  :dur (sec 5)
                  :type :translate
                  :from (m7/space [0 0])
                  :to (m7/space f1)
                  :fill :freeze
                  :id :buoyancy3
                  }]
                ]]

                 (let [[x1 y1] f1
                       [x2 y2] f2]
                   [:path {:d (m7/path [0 0 :l (+ x1 x2) (+ y1 y2) ])
                        :id :paswr2
                        :stroke-width 0
                        :marker-end (m7/url (name :dot3))
                        :stroke (hsl [4.5 70 80 .9])
                        :fill (hsl [4.5 70 80 .9])}

                    [:animate
                     {:attributeName :stroke-width
                      :begin :buoyancy3.end
                      :dur (sec 5)
                      :from 0
                      :to 4
                      :fill :freeze}]])


                 ]])


             #_[ship [[:translate [-60 0]]
                    [:scale [0.01 -0.01]]]]
             #_[:path {:d (m7/path `[-25 0 :l ~@(map #(* 50 %) dx)])
                     :stroke-dasharray (m7/space [100 50])
                     :stroke (hsl [1 70 70 1])
                     :stroke-width 2
                     :fill (hsl [1 70 70 1])}]


             #_"buoyancy"
             #_"upthrust"



             #_[:path {:d (m7/path [150 -150 :l 0 70 ])
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
            ]
           ]
          )]


       ])))



(defn energy []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 -1 -1  0 0 1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4
          ax-dx 80
          ax-dy 40
          vb (fn [z]
               (nth [[100 -450  500 500]
                     [0 -670  1200 700]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-400 -200  800 200]
                     [-200 -300  600 400]] z))
          viewbox (vb 1)
          viewbox2 (vb 1)
          ]


      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}


       #_[:div {:style
              (m7/css
               [[2 7 10 8
                 :center :center  4.5 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}


          ]


       [:div {:style
                (m7/css
                 [[2 10 10 10
                   :center :center  2.8 :rem :column]
                  [3.5 70 (+ 50 (* 5 5))  .7] []
                  {:gap ".1rem"
                   :z-index 10}])}

        [:div {:style {:font-size "1.0rem"}} "kinetic energy is proportional to square of velocity and the mass"]


        [m7/m '[= KE [:m [1 2] m [:p  v 2]]]]
        #_[:div {:style {:font-size "1.8rem"}} "if you lift a rock on a hill, work done would be "]

;;         [:div {:style {:font-size "1.2rem"}} "Height after 2s would be"]





        ]



       #_[:div {:style
                (m7/css
                 [[2 10 10 10
                   :center :center  2.8 :rem :column]
                  [3.5 70 (+ 50 (* 5 5))  .7] []
                  {:gap ".1rem"
                   :z-index 10}])}

        #_[:div "kinetic energy is proportional to square of velocity and m"]


        #_[m7/m '[= KE [:m [1 2] m [:p  v 2]]]]
        #_[:div {:style {:font-size "1.8rem"}} "if you lift a rock on a hill, work done would be "]

;;         [:div {:style {:font-size "1.2rem"}} "Height after 2s would be"]



;;         [m7/m '[= [:k h t] [- [:m u t] [:m [1 2] g [:p t 2]] ]]]

;;         [m7/m '[= [:k h 2] [- [:m 2 u ] [:m [1 2] g [:p 2 2]] ]]]


;;         [m7/m '[= [:k h 2] [- [:m 2 u ] [:m 2 g ] ]]]

;;         [m7/m '[= [:k h 2] [- [* [:m 2 s] [:m 30 [m s]]  ] [* [:m 2 [:p s 2]]
;;                                                             [:m 10  [m [:p s 2]]] ] ]]]


;;         [m7/m '[= [:k h 2] [:m 40 m]]]


;;         [m7/m '[= PE [:m m g [:k h 2] ] ]]

;;         [m7/m '[= PE [* .2
;;                       10 [:k h 2]]
;;                 [:m 80 J]]]




;;         [m7/m '[= v [- u gt]]]


;;         [m7/m '[= KE  [:m [1 2] m [:p [:b [- u gt] ] 2]]]]


;;         [m7/m '[= KE  [:m [1 2] .2 [:p [:b [- [:m 30 [m s]] [* [:m 10 [m [:p s 2]]]
;;                                                              [:m 2 s]]] ] 2]]]]



;;         [m7/m '[= KE  [:m [1 2] [2 10] [:p [:b [:m 10 [m s]]] 2]]]]


;;         [m7/m '[= KE  [:m 10 J]]]


;;         [:div {:style {:font-size "1rem"}} "total energy after 2 sec "
;;          [m7/m '[= [+ PE KE] [:m [:b [+ 80 10]] J]]]]


;;         [:div "When the Object is just through due to conservation of energy it must be same as
;; after 2 sec passed"]

;;         [m7/m '[= [:m [1 2] m [:p v 2]] [:m 90 J]]]




        #_[m7/m '[= h [- 196  h]]]

        #_[m7/m '[= [:m 2 h] 196]]

        #_[m7/m '[= h [196 2]]]


         [:div {:style {:font-size "1rem"}} "g is acceleration i.e rate of velocity change over time duo to gravity on earth "
            [m7/m '[:m 10 [m [:p s 2]]]]
          ]

        [m7/m '[= g [:m 10 [m [:p s 2]]]]]

        [:div {:style {:font-size "1rem"}} "force is mass times rate of change of velocity over time"]

        [m7/m '[= F ma]]

        [:div {:style {:font-size "1rem"}} "Free falling object has downward Force"]

        [m7/m '[= W F mg]]

;;         [:div "What is weight?"]

;;         [m7/m '[= F W [:m m g]]]


;;         [m7/m '[= R [+ w [- w]] 0]]



         [:div {:style {:font-size "1rem"}} "Weight is Force where direction of Force  towards the center of earth this is duo to gravitational pull where acceleration g on earth "
          ]

;;         [m7/m '[=  W mg]]

         [m7/m '[=   Work-done [* Force distance-moved]]]

         [:div {:style {:font-size "1rem"}} "if we lift the ball at height h"]

;;         [m7/m '[=  W mg]]

        [m7/m '[=   Work-done [* F h] [* W h] mgh]]
        [:div {:style {:font-size "1rem"}} "let , PE is potential energy duo to gravity"]
        [m7/m '[= PE work-done mgh]]



        #_[m7/m '[= PE Work-done [*   W h]]]

        #_[m7/m '[= PE [* [:m m g] h]]]


        #_[m7/m '[= PE [* [* [:m 2 kg] [:m 10 [m [:p s 2]]]] [:m 1200 m]]]]


        #_[m7/m '[= PE [:m 2400 J]]]



        ;; [m7/m '[=  [* d1 A1] [* d2 A2]]]


        ;; [m7/mx `[= [:k W 1] [:m [:k F 1] [:k d 1] ]  ]]

        ;; [m7/mx `[= [:k W 2] [:m [:k F 2] [:k d 2]]  ]]
        #_[:div "conservation of energy"]


        #_[m7/m '[= PE KH]]


        #_[m7/m '[= mgh [:m [1 2] m [:p v 2]]]]


        #_[m7/m '[= gh [:m [1 2]  [:p v 2]]]]

        #_[m7/m '[= v [:sq [:m 2 gh]] ]]
        ;; [m7/m '[= energy-in energy-out]]
        ;; [m7/m '[= W1 W2 ]]
        ;; [m7/m '[= [[:k F 1]
        ;;            [:k A 1]] [[:k F 2]
        ;;                       [:k A 2]]]]


        ;; [m7/m '[= [:k P in] [:k P out]]]


        ]










       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]

                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
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
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:scale [.4 .4]]
                                             [:rotate 1]])
                    :fill (m7/hsl [2 70 70 1])}]]

           [:marker {:id (name :energy33)
                     :viewBox (m7/space [-5 -5 10 10])
                     :refX 0
                     :refY 0
                     :orient :auto-start-reverse
                     :markerWidth 5
                     :markerHeight 5}
            [:path {:d (m7/path [0 0 :l 5 0 -10 -5 5 5 5 0 -10 5 5 -5])
                    :stroke (hsl [5 70 70 1])
                    :stroke-width .1
                    :transform (m7/tranfrom [[:scale [1 1]]
                                             [:rotate 1]])
                    :fill (m7/hsl [2 70 70 1])}]]


           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]


            ;; water

           #_(grid-on 1 1 40 40 false)






           (let [a .22]
             [:g
              [:g {:transform
                   (m7/tranfrom
                    [[:translate [35 -485]]
                     [:scale [1.2 1.2]]])}

               [:animateTransform
                {
                 :attributeName :transform
                 :begin :click
                 :dur (sec 10)
                 :type :translate
                 :fill :remove
                 :repeatCount :indefinite
                 :values (m7/sami-colon
                          (map
                           m7/space
                           (map (fn [i]
                                  [0 (* a 5 5 2 i i) ])
                                (range 0 13))))

                 :keyTimes (m7/sami-colon
                            (map #(/ % 60)
                                 (range 0 65 5)))}]

            [:circle {:cx 0
                      :cy -15
                      :r 20
                      :stroke-width 2
                      :stroke (hsl [1 70 70 1])
                      :fill (hsl [1 70 70 1])}]


               [:path#ew1 {:d (m7/path [0 0 :l 0 100])
                           :stroke-width 3
                           :marker-end (m7/url (name :energy33))
                           :stroke (hsl [3.5 70 70 1])
                           :fill (hsl [3.5 70 70 1])}]

               #_[:path#ew2 {:d (m7/path [0 0 :l 0 100])
                           :transform (m7/tranfrom [[:scale [1 -1]]])
                           :stroke-width 3
                           :marker-end (m7/url (name :energy33))
                           :stroke (hsl [3.5 70 70 1])
                           :fill (hsl [3.5 70 70 1])}]

               [:text {}
                [:textPath {:href :#ew2
                            :startOffset "20%"
                            :font-size ".9rem"}
                 "-W"]

                [:textPath {:href :#ew1
                            :startOffset "20%"
                            :font-size ".9rem"}
                 "F=W=mg"]]]

              [:g#ballA


               [:g
                [:animateTransform
                 {
                  :attributeName :transform
                  :begin :click
                  :dur (sec 10)
                  :type :translate
                  :fill :remove
                  :repeatCount :indefinite
                  :values (m7/sami-colon
                           (map
                            m7/space
                            (map (fn [i]
                                   [0 (* a 5 5 2 i i) ])
                                 (range 0 13))))

                  :keyTimes (m7/sami-colon
                             (map #(/ % 60)
                                  (range 0 65 5)))}]

                [:g {:transform (m7/tranfrom [[:translate [40 -300]]
                                              [:scale [1.2 1.2]]
                                              ])}



                 [:circle {:cx 0
                           :cy -15
                           :r 20
                           :stroke-width 2
                           :stroke (hsl [3.5 70 70 1])
                           :fill (hsl [.5 70 70 1])}]

                 [:path#ew1 {:d (m7/path [0 0 :l 0 40])
                             :stroke-width 3
                             :marker-end (m7/url (name :energy33))
                             :stroke (hsl [3.5 70 70 1])
                             :fill (hsl [3.5 70 70 1])}]

                 [:text {}
                  [:textPath {:href :#ew1
                              :startOffset "50%"
                              :font-size "2rem"}
                   "w"]]]]]

              ])





           [:g {:transform (m7/tranfrom [[:rotate -90]])}
            (
             (fn [n n1]
               [:g {:transform (m7/tranfrom [[:translate [0 0]]])}
                (map (fn [i]
                       [:path {:stroke-width 2
                               :fill (hsl [(if (< i n1)  3.5 1) 70 70 .6])
                               :stroke (hsl [7 70 70 1])
                               :d (m7/path
                                   `[~(* i 40) 0 :l ~@(map #(* 20 %1 %2)
                                                           dx
                                                           (cycle [2 1.5]))])}])
                     (range 0 n))])
             12 8)]






           #_[:g {:transform (m7/tranfrom [[:translate [60 0]]
                                         [:rotate -90]
                                         ])}
            (
             (fn [n n1]
               [:g {:transform (m7/tranfrom [[:translate [0 0]]])}
                (map (fn [i]
                       [:path {:stroke-width 2
                               :fill (hsl [(if (< i n1)  3.5 1) 70 70 .8])
                               :stroke (hsl [7 70 70 1])
                               :d (m7/path
                                   `[~(* i 40) 0 :l ~@(map #(* 20 %1 %2)
                                                           dx
                                                           (cycle [2 1.5]))])}])
                     (range 0 n))])
             12 3)]



           #_[:g {:transform (m7/tranfrom [[:translate [120 0]]
                                         [:rotate -90]
                                         ])}
            (
             (fn [n n1]
               [:g {:transform (m7/tranfrom [[:translate [0 0]]])}
                (map (fn [i]
                       [:path {:stroke-width 2
                               :fill (hsl [(if (< i n1)  3.5 1) 70 70 .8])
                               :stroke (hsl [7 70 70 1])
                               :d (m7/path
                                   `[~(* i 40) 0 :l ~@(map #(* 20 %1 %2)
                                                           dx
                                                           (cycle [2 1.5]))])}])
                     (range 0 n))])
             12 11)]









           #_[:g {:transform (m7/tranfrom [[:rotate -90]
                                         ])}
            (map (fn [i]
                   [:path {:stroke-width 2
                           :fill (hsl [(if (< i 0)  3.5 1) 70 70 1])
                           :stroke (hsl [7 70 70 1])
                           :d (m7/path
                               `[~(* i 40) 0 :l ~@(map #(* 20 %1 %2)
                                                       dx
                                                       (cycle [2 1]))])}])
                 (range 0 4))]


           #_[:path {:stroke-width 2
                   :fill (hsl [3.5 60 75 1])
                   :stroke (hsl [7 70 70 1])
                   :d (m7/path
                       `[-20 0 :l ~@(map #(* 20 %1 %2)
                                         dx
                                         (cycle [1 -4]))
                         ])}]


           #_[:g {:transform (m7/tranfrom [[:translate [0 50]]])}
            (map (fn [i]
                   [:path {:stroke-width 2
                           :fill (hsl [3.5 70 70 1])
                           :stroke (hsl [7 70 70 1])
                           :d (m7/path
                               `[~(* i 40) 30 :l ~@(map #(* 20 %1 %2)
                                                        dx
                                                        (cycle [2 1]))])}])
                 (range 0 10))]


           #_[:g {:transform (m7/tranfrom [[:translate [240 40]]])}
            (map (fn [i]
                   [:path {:stroke-width 2
                           :fill (hsl [3.5 70 70 1])
                           :stroke (hsl [7 70 70 1])
                           :d (m7/path
                               `[~(* i 40) 30 :l ~@(map #(* 20 %1 %2)
                                                        dx
                                                        (cycle [2 2]))])}])
                 (range 0 4))]



           #_[:g {:transform (m7/tranfrom [[:translate [-10 40]]])}
            (map (fn [i]
                   [:circle {:stroke-width 2
                             :fill (hsl [5.5 70 70 1])
                             :stroke (hsl [7 70 70 1])
                             :r 5}])
                 (range 0 4))]


           #_[:g {:transform (m7/tranfrom [[:translate [260 40]]])}
            (map (fn [i]
                   [:circle {:cx (* i 20)
                             :cy 0
                             :stroke-width 2
                             :fill (hsl [5.5 70 70 1])
                             :stroke (hsl [7 70 70 1])
                             :r 5}])
                 (range 0 7))]




           [:g {:transform (m7/tranfrom [[:translate [-40 0]]])}
            (map (fn [i]
                   [:path {:stroke-width 2
                           :fill (hsl [(if (< i 0) 1 3.5) 70 70 1])
                           :stroke (hsl [7 70 70 1])
                           :d (m7/path
                               `[~(* i 40) 30 :l ~@(map #(* 20 %1 %2)
                                                        dx
                                                        (cycle [2 1.25]))])}])
                 (range 0 4))]







           #_[:g
            (map (fn [i]
                   [:path {:stroke-width 2
                           :fill (hsl [(if (< i 2) 1 2) 70 70 1])
                           :stroke (hsl [7 70 70 1])
                           :d (m7/path
                               `[~(* i 20 2.67) 60 :l ~@(map #(* 20 %1 %2)
                                                       dx
                                                       (cycle [2.67 1]))])}])
                 (range 0 4))]




           ]
          )]


       ])))



(defn frection []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 -1 -1  0 0 1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    (let [zoom 4

          ax-dx 80
          ax-dy 40

          vb (fn [z]
               (nth [[100 -450  500 500]
                     [-40 -100  200 200]] z))
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
               [[2 8 2 8
                 :center :center  4.5 :rem :column]
                [3.5 70 (+ 50 (* 5 5))  .7] []
                {:gap ".1rem"
                 :z-index 10}])}

        #_[m7/m '[= [6 15] [[* 3 2] [* 3 5]] [2 5]]]
        [m7/m '[= [12 15] [[* 3 4] [* 3 5]] [4 5]]]

        [:div {:style {:background-color (hsl [0 70 70 .31])}}]

        ]



       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]

                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]


           (grid-on .1 .1 ax-dy ax-dy true)

           [:g
            (map (fn [i]
                   [:path {:stroke-width 2
                           :fill (hsl [(if (< i 2) 1 2) 70 70 1])
                           :stroke (hsl [7 70 70 1])
                           :d (m7/path
                               `[~(* i 40) 0 :l ~@(map #(* 20 %1 %2)
                                                       dx
                                                       (cycle [2 1]))])}])
                 (range 0 5))]



           [:g
            (map (fn [i]
                   [:path {:stroke-width 2
                           :fill (hsl [(if (< i 4) 1 2) 70 70 1])
                           :stroke (hsl [7 70 70 1])
                           :d (m7/path
                               `[~(* i 20) 40 :l ~@(map #(* 20 %1 %2)
                                                       dx
                                                       (cycle [1 1]))])}])
                 (range 0 10))]




           ]
          )]


       ])))


(defn triangle []
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



        #_[m7/m '[= [:m [:k ρ w] V]  [:m ρ [:k V 1]]  ]]

        ;; [m7/m '[= ρV [:m V [m V]] ]]


        ;; [m7/m '[= m ρV ]]

        #_[m7/m '[=  [:m [:k ρ w] V ] [:m  ρ [:k V 1] ] ]]


        [m7/m '[=  [ V1 V] [[:k ρ w]  ρ ] ]]


        [m7/m '[=
                [ V1 V] [[:m [* 0.5 [:p 10 3]] kg [:p m 3]]
                         [:m [:p 10 3] kg [:p m 3]]] .5 ]]




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

       [file/file-input-background2]


       (map
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



           [:image {:on-click (fn [e]
                                (js/console.log "hello animals"))
                    :height 100
                    :width 100
                    :x 300
                    :y -120
                    :href "https://cdn.britannica.com/s:800x450,c:crop/83/195983-138-66807699/numbers-tiger-populations.jpg"}
            [:animate {:attributeName :width
                       :begin :click
                       :to 150
                       :dur (sec 3)
                       :fill :freeze}]]


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





(defn map-family []
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
                      :background-color (hsl [1 100 100 1])
                      ;; :background-image (str "url(" (.getItem js/localStorage "file-input-img" )   ")")
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

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]





           #_(grid-on 20 20)



           [:clipPath {:id (name :hello-clip)}
            [:path {:d (m7/path [20 -300 :l 365 0 0 235 -365 0 0 -235])
                    :storke (hsl [3 70 70 1])
                    :stroke-width 3
                    :fill (hsl [5 70 70 1])}]]

           [:image { :height 400
                    :clip-path (m7/url (name :hello-clip))
                    :width 400
                    :x 0
                    :y -400
                    :href "bd/idbaba.jpeg"}
            ]




           [:clipPath {:id (name :hello-clip1)}
            [:path {:d (m7/path [410 -310 :l 375 0 0 245 -375 0 0 -245])
                    :storke (hsl [3 70 70 1])
                    :stroke-width 3
                    :fill (hsl [5 70 70 1])}]]

           [:image {:clip-path (m7/url (name :hello-clip1))
                    :height 400
                    :width 400
                    :x 400
                    :y -400
                    :href "bd/idbabas1.jpeg"}
            ]


           #_[:path {:d (m7/path [0 -300 :l 100 0 0 100 -100 0 0 -100])
                     :storke (hsl [3 70 70 1])
                     :stroke-width 3
                     :fill (hsl [5 70 70 1])}]


           #_[:image {:on-click (fn [e]
                                (js/console.log "hello animals"))
                    :height 200
                    :width 200
                    :x 700
                    :y -400
                    :href "bd/idaks1.jpeg"}
            [:animate {:attributeName :width
                       :begin :click
                       :to 150
                       :dur (sec 3)
                       :fill :freeze}]]









           ]
          )]


       ])))


(defn map-family2 []
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
                      :background-color (hsl [1 100 100 1])
                      ;; :background-image (str "url(" (.getItem js/localStorage "file-input-img" )   ")")
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

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]





           #_(grid-on 20 20)



           [:clipPath {:id (name :hello-clip11)}
            [:path {:d (m7/path [10 -320 :l 380 0 0 250 -380 0 0 -250])
                    :storke (hsl [3 70 70 1])
                    :stroke-width 3
                    :fill (hsl [5 70 70 1])}]]

           [:image { :height 400
                    :clip-path (m7/url (name :hello-clip11))
                    :width 400
                    :x 0
                    :y -400
                    :href "bd/idmas1.jpeg"}
            ]




           [:clipPath {:id (name :hello-clip1)}
            [:path {:d (m7/path [425 -310 :l 375 0 0 245 -375 0 0 -245])
                    :storke (hsl [3 70 70 1])
                    :stroke-width 3
                    :fill (hsl [5 70 70 1])}]]

           [:image {:clip-path (m7/url (name :hello-clip1))
                    :height 400
                    :width 400
                    :x 400
                    :y -400
                    :href "bd/idmas2.jpeg"}
            ]


           #_[:path {:d (m7/path [0 -300 :l 100 0 0 100 -100 0 0 -100])
                     :storke (hsl [3 70 70 1])
                     :stroke-width 3
                     :fill (hsl [5 70 70 1])}]


           #_[:image {:on-click (fn [e]
                                (js/console.log "hello animals"))
                    :height 200
                    :width 200
                    :x 700
                    :y -400
                    :href "bd/idaks1.jpeg"}
            [:animate {:attributeName :width
                       :begin :click
                       :to 150
                       :dur (sec 3)
                       :fill :freeze}]]









           ]
          )]


       ])))




(defn map-family3 []
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
                      :background-color (hsl [1 90 100 1])
                      ;; :background-image (str "url(" (.getItem js/localStorage "file-input-img" )   ")")
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

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]





           #_(grid-on 20 20)



           [:clipPath {:id (name :hello-clip31)}
            [:path {:d (m7/path [10 -320 :l 450 0 0 350 -450 0 0 -350])
                    :storke (hsl [3 70 70 1])
                    :stroke-width 3
                    :fill (hsl [5 70 70 1])}]]

           [:image { :height 400
                    :clip-path (m7/url (name :hello-clip31))
                    :width 400
                    :x 0
                    :y -400
                    :href "bd/mapass.jpeg"}
            ]




           [:clipPath {:id (name :hello-clip13)}
            [:path {:d (m7/path [400 -300 :l 375 0 0 245 -375 0 0 -245])
                    :storke (hsl [3 70 70 1])
                    :stroke-width 3
                    :fill (hsl [5 70 70 1])}]]

           [:image {
                    :height 400
                    :width 400
                    :x 400
                    :y -400
                    :href "bd/mapass3.jpeg"}
            ]


           #_[:path {:d (m7/path [0 -300 :l 100 0 0 100 -100 0 0 -100])
                     :storke (hsl [3 70 70 1])
                     :stroke-width 3
                     :fill (hsl [5 70 70 1])}]


           #_[:image {:on-click (fn [e]
                                (js/console.log "hello animals"))
                    :height 200
                    :width 200
                    :x 700
                    :y -400
                    :href "bd/idaks1.jpeg"}
            [:animate {:attributeName :width
                       :begin :click
                       :to 150
                       :dur (sec 3)
                       :fill :freeze}]]









           ]
          )]


       ])))
