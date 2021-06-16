(ns math2.math100
  (:require
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
                       [[(+ i 2) 1 (+ 4 (* 3 0)) 3 :center :center 2 :rem :column]
                        [c 70 70 .8] []
                        {:z-index 2}]
                       )}


         n])
         [["" 7.3]
          ["" .7]
          ["" 3.5]])


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
     [:g {:transform (m7/tranfrom [[:rotate 0]
                                   ])}






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
                    [[2 5 7 10  :center :center  3 :rem :column]
                     [(+ 1 (f 3)) 70 90 .2] [] {:gap "1rem"
                                                :z-index 4}])}

      #_[m7/m '[+ [:b [:m [:p Na +] [:p  Cl -]]]
              [:b [:m [:p H +] [:p OH -]]]]
       ]

      #_[m7/m '[+ [:b [:m [:p H +] [:p  Cl -]]]
              [:b [:m [:p H +] [:p OH -]]]]
       ]

      #_[m7/m '[= [+ [:p H +] [:m 2 [:p e -]]] [:k H 2]]
         ]
      [m7/m '[= Power [work-done time-taken]  [energy-transfered
                                               time-taken]
              [joule s]
              ]
       ]

      [:div "1 watt (1 W) = 1 joule per second"]

      [:div "A crane lift a load of weighting 3000 N through a height of 5 m in 10 seconds. What is the power of crane?"]

      ;; [m7/m '[= Work-done [* Force distance-moved]]]

      ;; [m7/m '[= Work-done [* [:m 3000 N] [:m 5 m]]]]

      ;; [m7/m '[= Work-done [:m 15000 joules]]]


      [m7/m '[= Power [work-done time-taken]]]

      [m7/m '[= [* [:m 2000 W ] [:m 10 s]] work-done]]

      #_[m7/m '[= Power [[:m 15000 joule] [:m 10 s]]]]
      #_[m7/m '[= Power [:m 1500 W]]]

      #_[m7/m '[= Power [:m 1.5 kW]]]

      ]



     #_[:div {:style (m7/css
                      [[2 10 2 15  :center :center  2 :rem :column]
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

              [50 1.97]]
       ]





      ]





     [:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [(+ 1 (f 3)) 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [0 -100  300 200])}



       [:g {:transform (m7/tranfrom [[:scale [.3 .3]]
                                     [:rotate [-90]]])}

        [:path {:d (path [0 -90 :c 40 40 60 100   0 180])
                :stroke (hsl [.8 70 70 1])
                :stroke-width 4
                :fill :none}]

        (let [pull (ve 50)]
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
                   :marker-end (m7/url "i")
                   :stroke (hsl [2 70 70 1])
                   :stroke-width 1.2
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
                                  :fill :freeze}])]]




          #_[:circle {:cx 0
                      :cy 0
                      :fill (hsl [-1 50 50 1])
                      :r 1}])]



       #_[:path {:d (path [0 0 :l -100 0 0 70 100 -70 ])
               :stroke (hsl [-1 50 50 1])
               :stroke-width .2
               :fill (hsl [-1 50 90 1]) }
        [:animateTransform
         {:id :sq43
          :attributeName :transform
          :begin :click
          :dur (sec 5)
          :type :translate
          :from (m7/space [0 0])
          :to (m7/space [100 0])
          :fill :freeze
          }]
        [:animateTransform
         {:id :sq2
          :attributeName :transform
          :begin :sq.end
          :dur (sec 5)
          :type :translate
          :from (m7/space [100 0])
          :to (m7/space [200 0])
          :fill :freeze
          }]
        ]

       #_[:path {:d (path [100 0 :l 0 50 50 0 -50 -50 ])
               :stroke (hsl [-1 50 50 1])
               :stroke-width .2
               :fill (hsl [-1 10 50 1])}
        [:animateTransform
         {:id :sq14
          :attributeName :transform
          :begin :sq43.end
          :dur (sec 5)
          :type :translate
          :from (m7/space [0 0])
          :to (m7/space [-100 0])
          :fill :freeze
          }]

        ]

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
      ]




     (comment
       (let [total-min
             (apply + (map #(js/Math.sqrt (/ 1 (* 19.6 %))) (range 1 16)))]
         (reduce

          (fn [acc a]
            (conj acc (+ a (last acc))))

          [0]
          (map #(/
                 (js/Math.sqrt (/ 1 (* 19.6 %)))
                 total-min) (range 1 16))
          )))


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
                                   :fill :freeze}])]



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





       ]]]))



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







     #_(
      (fn [n d]
        [:div {:style (m7/css
                       [[2 1 8 1  :flex-end :flex-end  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      0
      "k")


     #_(
      (fn [n d]
        [:div {:style (m7/css
                       [[1 1 (+ 9 (* n 1)) 1  :flex-end :flex-start  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      0
      "k")

     #_(
      (fn [n d]
        [:div {:style (m7/css
                       [[1 1 (+ 10 0) 3  :flex-end :flex-end  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      0
      "-3")




     #_(
      (fn [n d]
        [:div {:style (m7/css
                       [[2 1 (+ 9 (* n 1)) 1  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      0
      [m7/m [:p 'k 2]])


     #_(
      (fn [n d]
        [:div {:style (m7/css
                       [[2 5 (+ 9 (* n 1)) 4  :center :center  2.3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  1] [] {:gap ".1rem"
                                                             :z-index 5}])}

         d])
      0
      [m7/m '[:m [:b [- k 3]] [:b [- k 4]]]])





     #_(
      (fn [n d]
        [:div {:style (m7/css
                       [[2 1 (+ 10 0) 3  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      2
      [m7/m '[:m -3 k]])


     #_(
      (fn [n d]
        [:div {:style (m7/css
                       [[3 4 (+ 9 (* n 1)) 1  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7]
                        []
                        {:gap ".1rem"
                         :z-index 4}])}

         d])
      (first (range 0 7))
      (first [[:div "-4k"] [m7/m '[- [* 7 1]]]]))


     #_(
      (fn [n d]
        [:div {:style (m7/css
                       [[3 4 (+ 8 (* n 1)) 1  :center :flex-end  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1]
                        []
                        {:gap ".1rem"
                         :z-index 4}])}

         d])
      (first (range 0 7))
      (first [[:div "-4"] [m7/m '[- [* 7 1]]]]))


     #_(
      (fn [n d]
        [:div {:style (m7/css
                       [[3 4 10 3  :center :center 3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  1]
                        []
                        {:gap ".1rem"
                         :z-index 4}])}

         d])
      2
      [m7/m '[* [- 4] [- 3]]])







     [:div {:style (m7/css
                    [[3 5 (+ 2 (* 0 2)) 8  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      #_[m7/m '[= [:m [:b [+ a b]] [:b [- a b]]]
              [- [:p a 2] [:p b 2]]]]


      [:div "A bullet of mass 10 g, is fired at 400 m/s from a rifle of mass 4kg."]


      ]


     [:div {:style (m7/css
                    [[1 10 (+ 11 (* 0 2)) 6  :center :center  3 :rem :column]
                     [(* 2 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      #_[:div "according to conservation of momentum"]



      ]




     (comment
       (let [total-min
             (apply + (map #(js/Math.sqrt (/ 1 (* 19.6 %))) (range 1 16)))]
         (reduce

          (fn [acc a]
            (conj acc (+ a (last acc))))

          [0]
          (map #(/
                 (js/Math.sqrt (/ 1 (* 19.6 %)))
                 total-min) (range 1 16))
          )))


     [:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"
                     }
             :viewBox (m7/space
                       [0 -100  400 200])}


       ;; math
       (let [pull (ve 1)]
         [:g
          #_[:path {:d (path [0 -70 :l pull 70 ])
                  :stroke (hsl [0 70 70 1])
                  :stroke-width 2
                    :fill :none}]

          #_[:text {:x 0
                  :y 0
                  :style {:font-size ".5rem"}
                  :dx 10
                  }
           (name tt)]


          #_[:text {:x 50
                  :y -40
                  :style {:font-size ".5rem"}}
           "c"]

          #_[:text {:x 100
                  :y -40
                  :style {:font-size ".5rem"}}
           "a=70"]

          #_[:text {:x 50
                  :y 0
                  :dy 7
                  :style {:font-size ".5rem"}}
           "b=100"]

          #_[:path {:d (path [0 0 :l 100 0 0 (ve 70) -100 70 ])
                  :stroke (hsl [0 70 70 1])
                  :stroke-width 1
                  :fill :none}]


          #_[:path {:d (path [0 0 :l 90 0 0 -10 10 0])
                  :stroke (hsl [0 70 70 1])
                  :stroke-width 1
                  :fill :none}]


          #_[:path {:d (path [0 0 :l 20 0 :a 20 20 0 false false  -5 -15])
                  :stroke (hsl [0 70 70 1])
                  :stroke-width 1
                  :fill :none}]

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
                                 :fill :freeze}])]]




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
                       [[6 1 11 1  :center :flex-end  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      3
      "k"
      )


     (
      (fn [n d]
        [:div {:style (m7/css
                       [[7 1 10 1  :center :flex-end  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      3
      "k"
      )

     (
      (fn [n d]
        [:div {:style (m7/css
                       [[8 4 10 1  :center :flex-end  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      3
      "-4"
      )

     (
      (fn [n d]
        [:div {:style (m7/css
                       [[6 1 12 3  :center :flex-start  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .1] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      3
      "-3"
      )

     (
      (fn [n d]
        [:div {:style (m7/css
                       [[7 1 11 1  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      2
      [m7/m '[:p k  2]]
      )


     (
      (fn [n d]
        [:div {:style (m7/css
                       [[7 1 12 3  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      0
      [m7/m '[:m [- 3]  k]]
      )


     (
      (fn [n d]
        [:div {:style (m7/css
                       [[8 4 12 3  :center :center  3 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      0
      [m7/m '[* [- 4] [- 3]]]
      )

     (
      (fn [n d]
        [:div {:style (m7/css
                       [[8 4 11 1  :center :center  2 :rem :column]
                        [(* n .2) 70 (+ 50 (* 5 n))  .7] [] {:gap ".1rem"
                                                             :z-index 4}])}

         d])
      0
      [m7/m '[:m [- 4] k]]
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

     [:div {:style (m7/css
                    [[1 4 (+ 9 (* 0 2)) 10  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}

      [m7/m '[= [- [:p a 2] [:p b 2]]
              [:m [:b [+ a b]] [:b [- a b]]]]]


      [m7/m '[= [- [:p k 2] [:p 3 2]]
              [:m [:b [+ k 3]] [:b [- k 3]]]]]




      ]

     [:div {:style (m7/css
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



     #_[:div {:style (m7/css
                    [[7 6 (+ 1 (* 0 2)) 7  :center :center  2 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}



      ]



     [:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [0 -100  400 200])}

       ]]]))




















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
                    [[1 4 (+ 9 (* 0 2)) 10  :center :center  3 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}





      ]

     [:div {:style (m7/css
                    [[2 8 (+ 1 (* 0 2)) 17  :center :center  5 :rem :column]
                     [(* 5 .2) 70 (+ 50 (* 5 5))  .7] [] {:gap ".1rem"
                                                          :z-index 4}])}



      [m7/m '[ [[- a [:m 2 b]]
                 16]
              [[- [:m 4 a] [:m 8 b]]
               24]]]





      ]



     [:div {:style (m7/css
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

       ]]]))
