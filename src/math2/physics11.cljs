(ns math2.physics11
  (:require [math2.math7 :as m7 :refer [grid hsl css space size path ve sec]]))


(defn template []
  [:div {:style
         (merge
          (grid [100 :vh 100 :vw
                 (take 15 (repeat [8 :vh]))
                 (take 15 (repeat [8 :vh]))])
          {:background-color (hsl [1.4 70 70 .8])})}

   (for [[i rd]  (map
                  (fn [x y] [x y])
                  (range 0 8)
                  (into [["N" "cm"]]
                        [[1000 2.0]
                         [2000 4.0]
                         [3200 6.6]
                         [4400 8.8]
                         [5200 10.4]
                         [6500 13.4]]))
         j (range 0 2)
         :let [r (+ 0 (+ i 1))
               c (+ 1 (* j 2))
               v (get rd j)]
         ]
     [:div {:key (gensym)
            :style
            (css
             [[r 1 c 2 :center :center 2 :rem]
              [(* i j) 30 70 .2]
              [(* i j)
               30 :% (* (inc i) (inc j)) 30 60 0.1
               40 :% (* (inc i) (inc j)) 20 40 0.1
               70 :% (* (inc i) (inc j)) 0 80 0.1]
              {:z-index 20}]
             )} v])


   [:div {:key (gensym)
          :style
          (css
           [[1 12 2 12 :center :center 2 :rem :column]
            [3 30 70 .5] []]
           )}


    ]

   [:div {:key (gensym)
          :style (css
                  [[1 6 5 12 :center :center 2 :rem]
                   [.2 30 70 .9] []
                   {:z-index 22}]
                  )}

    (let [bm 250
          bm2 (/ bm 2)
          hi 35
          angle-x 30
          angle-y 30]
      [:svg {:viewBox (space (nth [[-65  -65 170 170 ]
                                   [-10 -160 80 80]]
                                  0))

             :style {:height (size {:size 100 :scale :%})
                     :width  (size {:size 100 :scale :%})}}


       [:path {:d (path [(ve bm2) 10
                         :a 400 400 0 false false  bm 0
                         :l (ve angle-x) (ve angle-y)
                         :a 400 400 0 false true
                         (ve (- bm 60)) 0 :l
                         (ve angle-x) angle-y
                         ])
               :stroke (hsl [0.5 70 70 1])
               :stroke-width 1
               :fill (hsl [4 70 70 .9])}
        [:animateTransform
         {:attributeName :transform
          :begin :click
          :dur (sec 1)
          :type :scale
          :from 1
          :to 1.1
          :fill :freeze
          :id :circ
          }]

        ]
       [:path {:d
               (path
                [(ve (- bm2 20)) 0 :a 400 400 0 false false
                 (- bm 40) 0])
               :stroke (hsl [1 70 70 1])
               :stroke-width 1.2
               :fill :none

               }
        [:animateTransform
         {:attributeName :transform
          :begin :circ.begin
          :dur (sec 1)
          :type :scale
          :from 1
          :to 1.1
          :fill :freeze
          }]]


       [:path {:d (path [(ve (+ 20 bm2)) 0 :l (+ 40 bm) 0])
               :stroke (hsl [3.3 70 70 1])
               :stroke-width 1
               :fill :none
               :stroke-dasharray 7
               }]



       ])]


   [:div {:key (gensym)
          :style
          (css
           [[1 12 2 12 :center :center 2 :rem]
            [3.5 70 70 1] []]
           )}
    [:svg {:viewBox (space (nth [[-10 -160 170 170 ]
                                 [-10 -160 80 80]]
                                0))
           :style {:height (size {:size 100 :scale :%})
                   :width  (size {:size 100 :scale :%})}}

     (comment


       [:animate {:attributeName :viewBox
                  :to (space [-10 -60 70 70])
                  :dur "5s"
                  :fill :freeze}])

     [:circle {:cx 0
               :cy 0
               :r .5}]

     (map (fn [x]
            [:g {:key (gensym) }
             [:path {:key (gensym)
                     :d (path [x 0 :l 10 0 0 2])
                     :stroke (hsl [2 70 70 1])
                     :stroke-width .5
                     :fill :none
                     }]

             [:text {:x x
                     :y 2
                     :style {:font-size ".1rem"}}
              (* 50 x)]])
          (range 0 170 10))

     (map (fn [y]
            [:g {:key (gensym) }
             [:path {:key (gensym)
                     :d (path [0 (ve y) :l 0 -10 -2 0])
                     :stroke (hsl [2 70 70 1])
                     :stroke-width .5
                     :fill :none
                     }]
             [:text {:x -1
                     :y (ve y)
                     :style {:font-size ".1rem"}}
              (/ y 10)]])
          (range 0 170 10))

     (map (comp
           (fn [[x y]]
             [:circle {:key (gensym)
                       :cx x
                       :cy (ve y)
                       :r .5}])
           (fn [[x y]]
             [(/ x 50)
              (* y 10)])
           )
          [[1000 2.0]
           [2000 4.0]
           [3200 6.6]
           [4400 8.8]
           [5200 10.4]
           [6500 13.4]])
     [:path {:d (path [0 0
                       :l (/ 5200 50) (ve (* 10.4 10))])
             :stroke (hsl [2 70 70 1])
             :stroke-width .5
             }]

     [:path {:d (path [(/ 3200 50) 0
                       :l 0 (ve (* 6.6 10))])
             :stroke (hsl [2 70 70 1])
             :stroke-width .5
             }]

     [:path {:d (path (flatten
                       [ [0 -60 :l]
                        (take 10 (repeat [0 -1 -2 0 2 0]))]))
             :stroke (hsl [3 70 70 1])
             :stroke-width .5
             }]

     [:path {:d (path (flatten
                       [ [0 -50 :l]
                        (take 10 (repeat [0 -1 -2 0 2 0]))]))
             :stroke (hsl [3 70 70 1])
             :stroke-width .5
             }]



     [:path {:d (path [0 -64 :l 100 0 ])
             :stroke (hsl [3 70 70 1])
             :stroke-width .5
             }]

     [:path {:d (path [90 0 :l 0 -150 ])
             :stroke (hsl [3 70 70 1])
             :stroke-width .5
             }]
     [:path {:d (path [0 -90 :l 100 0 ])
             :stroke (hsl [3 70 70 1])
             :stroke-width .5
             }]

     [:path {:d (path [0 -52 :l 100 0 ])
             :stroke (hsl [0.4 70 70 1])
             :stroke-width .5
             }]

     [:path {:d (path [52 0 :l 0 -150 ])
             :stroke (hsl [.2 70 70 1])
             :stroke-width .5
             }]

     [:path {:d (path (flatten
                       [ [50 0 :l]
                        (take 10 (repeat [1 0 0 2 0 -2]))]))
             :stroke (hsl [3 70 70 1])
             :stroke-width .5
             :fill :none
             }]]]])
