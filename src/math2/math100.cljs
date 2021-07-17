(ns math2.math100
  (:require
   [math2.file :as file]
   [react]
   [math2.math7 :as m7 :refer
    [grid hsl css space size path ve sec]]))


(def f
  (fn [m n] (/ m n)))
(def square [1 0 0 1 (ve 1) 0 0 (ve 1)])


(defn marker [a]
  [:defs
   [:marker {:id a
             :refY 0
             :refX 0
             :orient :auto
             :style {:overflow :visible}}
    [:path {:d [0 0 :L 5 -5
                :L -12.5 0
                :L 3 5 :L 0 0]
            :style {:fill-rule :evenodd
                    :stroke (m7/hsl [1 70 70 1])
                    :stroke-width 1
                    :stroke-opacity 1
                    :fill (m7/hsl [3 70 70 1])
                    :fill-opacity 1}
            :transform (m7/tranfrom [[:scale .4]
                                     [:rotate 180]
                                     [:translate [12.5 0]]])
            }]]])

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
         }]]
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
                       [[3 1 (+ 2 (* n 2)) 2  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                            :z-index 4}])}

         d])
      (range 0 7)
      [[:div [m7/m [:m 'cos tt]]] [m7/m ['b 'c] ] [m7/m [100 'c] ]])

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


     (map
      (fn [n d]
        [:div {:style (m7/css
                       [[3 1 (+ 9 (* n 2)) 2  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      (range 0 7)
      [[m7/m '[:p a 2]] ""]

      )

     (map
      (fn [n d]
        [:div {:style (m7/css
                       [[4 1 (+ 9 (* n 2)) 2  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      (range 0 7)
      ["" [m7/m '[* 3 2]]]










      [:div {:style (m7/css
                     [[2 10 2 15  :center :center  7 :rem :column]
                      [(* 5 .2) 70 (+ 50 (* 5 5)) 1] [] {:gap ".1rem"
                                                           :z-index 4}])}

       #_[m7/m '[   [- [:m 3 a] 6]
                 [+ [:p a 2] a [- 6]]]]


       #_[m7/m '[   [:m 3 [:b [- a 2]]]
                 [+ [:p a 2] a [- 6]]]]



       [m7/m '[- [+ [:p a 2] a ] 6]]

       #_[m7/m '[   [:m 3 [:b [- a 2]]]
                 [+ [:m a [:b [- a 2]]]

                  [:m 3 [:b [- a 2]]]]]]



       #_[m7/m '[   [:m 3 [:b [- a 2]]]
                 [:m [:b [+  a 3]] [:b [- a 2]]]]]





       #_[m7/m '[= [+ [:m 2 [:p  H +]]  [:m 2 [:p  e -]]] [:k H 2] ] ]


       #_[m7/m '[= [- [:m 2 [:p  Cl -]]  [:m 2 [:p  e -]]] [:k Cl 2] ] ]

       ])





     #_[:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"
                     }
             :viewBox (m7/space
                       [0 -100  400 200])}



       (let [pull (ve 3)]
         [:g
          #_[:path {:d (path [0 -70 :l pull 70 ])
                  :stroke (hsl [0 70 70 1])
                  :stroke-width 2
                    :fill :none}]




          #_[:path {:d (path [0 0 :l 0 -70 40 0 0 -5 0 10 20 0 0 -10 -20 0 20 0 0 5 40 0 0 70])
                    :stroke (hsl [0 70 70 1])
                    :stroke-width 1
                    :fill :none}]



          [:g {:transform (m7/tranfrom [[:translate [180 0]]])}
           [:circle {:stroke (hsl [0 70 70 1])
                     :cx 0
                     :cy 0
                     :r 5
                     :stroke-width 1
                     :fill (hsl [0 70 70 1])}]

           [:circle {:stroke (hsl [0 70 70 1])
                     :cx 30
                     :cy -2
                     :r 2
                     :stroke-width 1
                     :fill (hsl [3 90 90 1])}]

           [:circle {:stroke (hsl [0 70 70 1])
                     :cx 0
                     :cy 0
                     :r 30
                     :stroke-width 1
                     :fill :none}]

           [:g
            [:circle {:stroke (hsl [0 70 70 1])
                      :cx 20
                      :cy -2
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]




            [:circle {:stroke (hsl [0 70 70 1])
                      :cx 20
                      :cy 0
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]]



           [:g
            [:circle {:stroke (hsl [0 70 70 1])
                      :cx -20
                      :cy 0
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]
            [:circle {:stroke (hsl [0 70 70 1])
                      :cx -20
                      :cy 2
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]]

           [:g

            [:circle {:stroke (hsl [0 70 70 1])
                      :cx 4
                      :cy -20
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]
            [:circle {:stroke (hsl [0 70 70 1])
                      :cx 0
                      :cy -20
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]]

           [:g
            [:circle {:stroke (hsl [0 70 70 1])
                      :cx 0
                      :cy 20
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]

            [:circle {:stroke (hsl [0 70 70 1])
                      :cx 3
                      :cy 20
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]]





           [:circle {:stroke (hsl [0 70 70 1])
                     :cx 10
                     :cy 0
                     :r 2
                     :stroke-width 1
                     :fill (hsl [3 90 90 1])}]

           [:circle {:stroke (hsl [0 70 70 1])
                     :cx -10
                     :cy 0
                     :r 2
                     :stroke-width 1
                     :fill (hsl [3 90 90 1])}]



           [:circle {:stroke (hsl [0 70 70 1])
                     :cx 0
                     :cy 0
                     :r 10
                     :stroke-width 1
                     :fill :none}]



           [:circle {:stroke (hsl [0 70 70 1])
                     :cx 0
                     :cy 0
                     :r 20
                     :stroke-width 1
                     :fill :none}



            ]
           [:text {:x 0
                   :y 0
                   :style {:font-size ".5rem"}
                   :dx -2
                   :dy 2
                   }
            "Na"]]


          [:g {:transform (m7/tranfrom [[:translate [100 0]]])}
           [:circle {:stroke (hsl [0 70 70 1])
                     :cx 0
                     :cy 0
                     :r 5
                     :stroke-width 1
                     :fill (hsl [0 70 70 1])}]

           #_[:circle {:stroke (hsl [0 70 70 1])
                     :cx 30
                     :cy -2
                     :r 2
                     :stroke-width 1
                     :fill (hsl [3 90 90 1])}]

           #_[:circle {:stroke (hsl [0 70 70 1])
                     :cx 0
                     :cy 0
                     :r 30
                     :stroke-width 1
                     :fill :none}]

           [:g
            [:circle {:stroke (hsl [0 70 70 1])
                      :cx 20
                      :cy -2
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]




            [:circle {:stroke (hsl [0 70 70 1])
                      :cx 20
                      :cy 0
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]]



           [:g
            [:circle {:stroke (hsl [0 70 70 1])
                      :cx -20
                      :cy 0
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]
            [:circle {:stroke (hsl [0 70 70 1])
                      :cx -20
                      :cy 2
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]]

           [:g

            [:circle {:stroke (hsl [0 70 70 1])
                      :cx 4
                      :cy -20
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]
            [:circle {:stroke (hsl [0 70 70 1])
                      :cx 0
                      :cy -20
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]]

           [:g
            [:circle {:stroke (hsl [0 70 70 1])
                      :cx 0
                      :cy 20
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]

            [:circle {:stroke (hsl [0 70 70 1])
                      :cx 3
                      :cy 20
                      :r 2
                      :stroke-width 1
                      :fill (hsl [3 90 90 1])}]]





           [:circle {:stroke (hsl [0 70 70 1])
                     :cx 10
                     :cy 0
                     :r 2
                     :stroke-width 1
                     :fill (hsl [3 90 90 1])}]

           [:circle {:stroke (hsl [0 70 70 1])
                     :cx -10
                     :cy 0
                     :r 2
                     :stroke-width 1
                     :fill (hsl [3 90 90 1])}]



           [:circle {:stroke (hsl [0 70 70 1])
                     :cx 0
                     :cy 0
                     :r 10
                     :stroke-width 1
                     :fill :none}]



           [:circle {:stroke (hsl [0 70 70 1])
                     :cx 0
                     :cy 0
                     :r 20
                     :stroke-width 1
                     :fill :none}



            ]
           [:text {:x 0
                   :y 0
                   :style {:font-size ".5rem"}
                   :dx -2
                   :dy 2
                   }
            "Na"]

           [:text {:x 0
                   :y 0
                   :style {:font-size ".5rem"}
                   :dx 10
                   :dy -5
                   }
            "+"]
           ]



          [:g {:transform (m7/tranfrom [[:translate [40 0]]])}
           [:circle {:stroke (hsl [0 70 70 1])
                     :cx 0
                     :cy 0
                     :r 5
                     :stroke-width 1
                     :fill (hsl [0 70 70 1])}]


           [:circle {:stroke (hsl [0 70 70 1])
                     :cx 20
                     :cy 0
                     :r 2
                     :stroke-width 1
                     :fill (hsl [3 90 90 1])}]


           [:circle {:stroke (hsl [0 70 70 1])
                     :cx 0
                     :cy 0
                     :r 10
                     :stroke-width 1
                     :fill :none}]



           [:circle {:stroke (hsl [0 70 70 1])
                     :cx 0
                     :cy 0
                     :r 20
                     :stroke-width 1
                     :fill :none}



            ]
           [:text {:x 0
                   :y 0
                   :style {:font-size ".5rem"}
                   :dx -2
                   :dy 2
                   }
            "Li"]]
          #_[:path {:d (path [0 0 :l 90 0 0 -10 10 0])
                  :stroke (hsl [0 70 70 1])
                  :stroke-width 1
                  :fill :none}]


          #_[:path {:d (path [0 0 :l 20 0 :a 20 20 0 false false  -5 -15])
                  :stroke (hsl [0 70 70 1])
                  :stroke-width 1
                    :fill :none}]

          #_[:path {:d (path [pull 0 :l  20 0  -5 0  -10 7  10 -7 -10 -7 10 7
                            -20 0 -5 -5])
                  ;; :marker-end (m7/url "i")
                  :stroke (hsl [2 70 70 1])
                  :stroke-width 6
                  :fill :none}
           (if (= pull 0)
             [:animateTransform {:id :arrow2
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





      [m7/m '[= [:m [1 2] [:b [+ x 3]] [:b [- [:m 2 x] 5]]]
              20
              ]]

      [m7/m '[= [+ [:m 2 x] [:m [- 5 ] x] [:m 6 x] [- 55]]   0]]

      [m7/m '[= [+ [:m 2 x] x [- 55]]   0]]


      [m7/m '[= [:m [:b [- x 5]] [:b [+ [:m 2 x] 11]]] 0]]
      [m7/m '[= [- x 5] 0]]



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
                    [[1 10 (+ 9 (* 3 2)) 10  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}


      "Solving Simultaneous Linear Equations Using Algebraic Methods"


      ]


     [:div {:style (m7/css
                    [[2 10 (+ 2 (* 0 2)) 12  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}


      [:div
       [m7/m '[= [+ [:m 4 x] [:m 6 y]] 14]]
       "---------------------(1)"]

      [:div [m7/m '[= [- [:m 3 x] [:m 6 y]] 7]]
       "--------------------(2)"]



      [:div "(1) + (2)"]

      [m7/m '[= [+ [:m 4 x] [:m 6 y] [- [:m 3 x] [:m 6 y]]] [+ 14 7]]]

      [m7/m '[= [:m 7 x]
              21]]

      [m7/m '[= x
              [21 7] 3]]


      [m7/m '[= [:m 6 y] 2]]
      [m7/m '[= y [2 6] [1 3]]]



      ]



     [:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [0 -100  400 200])}




       ]]]))




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





           (map
            (fn [i]
              [:g

               [:circle {:cx 0
                         :cy -150
                         :r 10
                         :transform (m7/tranfrom [[:rotate (* i (/ 360 12))]])
                         :fill (hsl [2 70 70 1])}
                ]

               [:text {:x 0
                       :y -150
                       :transform (m7/tranfrom [[:rotate (* i (/ 360 12))]])
                       :style {:font-size ".5rem"}}
                (if (= i 0) 12 i)
                ]
               ])
            (range 0 12))

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

           [:path {:d (path [0 0 :l 150 0])
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



           [:path {:d (path [160 0 :a 160 160 0 true true   -160 -160])
                   :stroke (hsl [0 70 70 1])
                   :stroke-width 2
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



(defn home-work15 []
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
                    [[2 8 2 15  :center :center  4 :rem :column]
                     [(* 10 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                           :z-index 7}])}

      #_[:div "A ball is thrown vertically upwards at 20 m/s ignoring air
resistance and taking g = 10 m/s2,"]

      ;; [:div "how high it goes"]

      ;; [m7/m '[= [:p v 2]
      ;;         [+  [:p u 2] [:m 2  a s ]]]]

      ;; [m7/m '[= [:p 0 2]
      ;;         [+  400 [:m [- 20] s]]]]

      ;; [:div "the time taken to reach the height"]
      ;; [m7/m '[= v [+ u at]]]

      ;; [m7/m '[= 0 [+ 20 [:m [- 10] t]]]]

      ;; [m7/m '[= y [k x]]]

      ;; [m7/m '[= xy k]]


      ;; [m7/m '[= [* 72 6] k]]

      ;; [m7/m '[= 432 k]]



      ;; [m7/m '[= y [k x]]]
      ;; [m7/m '[= y [432 (- 72 18)]]]


      ;; [m7/m '[= y [432 54] 8]]

      #_[m7/m '[= [- [:m 2 Cu] [:m 4 [:p e -]]]  [:m 2 [:p Cu +]]]]


      #_[m7/m '[= [+ [:k O 2] [:m 4 [:p e -]]]  [:m 2 [:p O -]]]]

      #_[m7/m '[= [+ 3 a b c] [+ 3 b a c] [+ 3 c b a ] [+  [+ c b a]  3]
              [+ a b c 3]]]

      [m7/m '[= [+ solute solvent]  solution]]

      [m7/m '[= [+ [:m [:k H 2] O] [:m [:m Na Cl] [:b [- s]]]]  [:m [:m Na Cl] [:b [- aq]]]  ]]

      [m7/m '[= [:m 1 Kg] [:m 1000 g]]]

      [m7/m '[= [:m [1 2] Kg] [:m 500 g]]]


      [m7/m '[= [:m 1 L] [:m 1000 ml]]]


      [:div "solution of 10g of Salt in 100mL of water"]


      #_[m7/m '[+ [:m 7 x] [- [:m 9 y]] [:m 10 z]]]
      #_[m7/m '[- [:m -3 c] [* [- 3] 6]]]
      #_[m7/m '[+ [- x] 3]]

      #_[m7/m '[=
              [+ [:m 2 K] [:k Cl 2] ]
              [:m [:m 2 [:p K +]]
               [:p Cl -]]]]
      #_[m7/m '[= Na  [+ [:p Na +] [:p e -]]]]


      #_[m7/m '[= [- Fe [:m 2 [:p e -]]]  [:p Fe +]]]

      #_[m7/m '[= [- [:m 2 Mg] [:m 4 [:p e -]]]  [:m 4 [:p Mg +]]]]

      #_[m7/m '[= [- Cu [:m 2 [:p e -]]]  [:p Cu +]]]


      #_[m7/m '[=  [+ [:m [:p Na +] [:b [- aq]]]   [:p e -]] [:m Na [:b [- s]]]]]
      ;; [:div "time taken to return to it's start point"]

      #_[m7/m '[= [+ [:m 2 [:p Zn +] [:b [- aq]]] [:m 4 [:p e -]]]
              [:m 2 Zn [:b [- s]]]] ]

      #_[m7/m '[= [:m 2 [:p Cu +] ]  [:m 2 Cu ]] ]


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

     #_[:div {:style (m7/css
                    [[2 5 (+ 12 (* 2 2)) 7 :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}
      [file/file-input]]




     ]))



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













           #_[:g

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





           (map
            (fn [i]
              [:g

               [:circle {:cx 0
                         :cy -150
                         :r 10
                         :transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                         :fill (hsl [2 70 70 1])}
                ]

               [:text {:x 0
                       :y -150
                       :transform (m7/tranfrom [[:rotate (* i (/ 360 4))]])
                       :style {:font-size ".5rem"}}
                (if (= i 0) 12 i)
                ]
               ])
            (range 0 12))




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
