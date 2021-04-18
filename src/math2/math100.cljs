(ns math2.math100
  (:require [math2.math7 :as m7 :refer
             [grid hsl css space size path ve sec]]))


(def f
  (fn [m n] (/ m n)))
(def square [1 0 0 1 (ve 1) 0 0 (ve 1)])


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
        bm2 (/ bm 2)
        angle-x 0
        angle-y 15

        o [[m7/m ['* 1 80]]
           [m7/m ['* 1 1]]
           [m7/m [100 100]]
           [:div st [m7/m [:m 100 '%]]]]
        f [[m7/m [80
                  4]]
           [:div [m7/m [1 4]] ""]
           [:div [m7/m [25 100]]  ""]
           [:div st
            [m7/m [:m 25 '%]]]]
        n 3
        n2 3
        one (nth o n)
        frac (nth f n)
        one2  (nth o n2)
        frac2 (nth f n2)
        scl (nth [4.2 1.5 1.5 7.5] n)
        scl2 (nth [4.2 1.5 1.5 7.5] n2)]
    [:div {:style
           (merge
            (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 15 (repeat [8 :vh]))])
            {:background-color (hsl [1.4 70 70 .8])})}

     [:div {:key (gensym)
            :style (css
                    [[2 2 2 11 :center :center 2 :rem]
                     [.5 70 70 .3] []
                     {:z-index 23}]
                    )}
      ""
      ]

     [:div {:key (gensym)
              :style (css
                      [[2 11 2 11 :center :center 2 :rem]
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


         ]]


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



(defn template2 []
  [:div {:style
         (merge
          (grid [100 :vh 100 :vw
                 (take 15 (repeat [8 :vh]))
                 (take 15 (repeat [8 :vh]))])
          {:background-color (hsl [1.4 70 70 .8])})}



   [:div {:key (gensym)
          :style
          (css
           [[2 5 2 11 :center :center 3 :rem :column]
            []
            []
            {:z-index 35}])}
    [m7/m '[= Pressure-diff
            [* [:m 10 [N Kg] ] depth density] ]]]




   [:div {:key (gensym)
          :style (css
                  [[2 7 2 7 :center :center 3 :rem :column]
                   []
                   []
                   {:z-index 29}])}
    [:svg {:viewBox (space [-100 -100 200 200]) }
     [:circle {:cx -45
               :cy -10
               :r 4
               :fill (hsl [2 70 70 1])}]
     [:circle {:cx 6
               :cy -10
               :r 4
               :fill (hsl [2 70 70 1])}]

     [:circle {:cx 50
               :cy -10
               :r 4
               :fill (hsl [2 70 70 1])}]]]
   (comment
     )
   [:div {:key (gensym)
          :style (css
                  [[2 7 2 7 :center :center 3 :rem :column]
                   [1.2 70 70 .9] []
                   {:z-index 28}])}

    ]


   (comment
     [:div {:key (gensym)
            :style (css
                    [[2 11 2 11 :center :center 2 :rem]
                     [.5 70 70 .9] []
                     {:z-index 30}]
                    )}

      (let [bm 30
            bf (fn [n] (* bm (/ 1 n)))
            base [0 0]
            bm2 (/ bm 2)]
        [:svg {:viewBox (space (nth [[-100  -100 200 200 ]
                                     [-10 -160 80 80]] 0))
               :style {:height (size {:size 100 :scale :%})
                       :width  (size {:size 100 :scale :%})}}







         (comment

           (let [sq
                 [[1 0 0 1 (ve 1) 0 0 (ve 1)]
                  [(ve 1) 0 0 (ve 1) 1 0 0 1]
                  [1 0 0 (ve 1) (ve 1) 0 0 1]
                  [1 0 0 (ve 1) (ve 1) 0 0 1]]]
             (apply concat sq)))
         [:path {:d (path
                     (
                      (comp
                       (partial into base)
                       (partial cons :l)
                       (partial map (partial * bm))
                       (partial map (partial * -1))
                       )
                      (flatten
                       [
                        [[1 0 0 1 (ve 1) 0 0 (ve 1)]
                         [(ve 1) 0 0 (ve 1) 1 0 0 1]
                         [1 0 0 (ve 1) (ve 1) 0 0 1]
                         [(ve 1) 0 0 1 1 0 0 (ve 1)]
                         ]
                        (map
                         (partial
                          (partial map (partial * 1.2)))
                         [[1 0 0 1 (ve 1) 0 0 (ve 1)]
                          [(ve 1) 0 0 (ve 1) 1 0 0 1]
                          [1 0 0 (ve 1) (ve 1) 0 0 1]
                          [(ve 1) 0 0 1 1 0 0 (ve 1)]
                          ])])))
                 :stroke (hsl [0.5 50 50 1])
                 :stroke-width 1
                 :fill (hsl  [1 70 70 .8])}

          [:animateTransform
           {:attributeName :transform
            :begin (sec 0)
            :dur (sec 1)
            :type :scale
            :from 1
            :to 1
            :fill :freeze
            :id :r1
            }]]

         [:g {:style {:transform
                      (m7/tranfrom
                       [[:rotate 30]])}}




          [:path {:d
                  (path
                   ((comp
                     (partial into [-15 -15])
                     (partial cons :l)
                     (partial map (partial * 30)))
                    [1 0 0 1 (ve 1) 0 0 (ve 1)]))
                  :stroke (hsl [0.5 50 50 1])
                  :stroke-width 1
                  :fill (hsl  [1 70 70 .8])}]

          (map
           (fn [[x y]]
             [:text {:key (gensym)
                     :x x
                     :y y
                     :dx -2
                     :dy 3
                     :style {:font-size ".3rem"}


                     } "Ab"])
           (let [[_ & rest]
                 (reduce
                  (fn [acc  [x y]]
                    (let [[x1 y1] (last acc)]
                      (conj acc [(+ x x1) (+ y y1)])))

                  [[0 0]]

                  (partition
                   2
                   ((comp
                     (partial into [-18 -18])
                     (partial map (partial * 33)))
                    [1 0 0 1 (ve 1) 0 0 (ve 1)])))]
             rest))]

         [:circle {:cx 0
                   :cy 0
                   :r 1
                   :stroke (hsl [1.8 70 70 1])
                   :fill (hsl [2.8 70 70 1])}]])])
   ])

(comment
  (partition
   2
   ((comp
     (partial into [-33 -33])
     (partial map (partial * 33)))
    [1 0 0 1 (ve 1) 0 0 (ve 1)]))

  (partition 2
             ((comp
               (partial into [-3 -3])
               (partial map (partial * 3)))
              [1 0
               0 1 (ve 1) 0
               0 (ve 1)])))


(defn template3 []
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
        f (fn [n] (/ 1 n))
        pi 'π
        view-box? false
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

     [:div
      {:key (gensym)
       :style (css
               [[2 6 2 11 :center :center 3 :rem :column]
                [-0.5 70 70 1] []
                {:z-index 4}]
               )}
      [m7/m ['= 'U [:m pi [:b ['+ 'r 'h]]]]]

      [m7/m ['= 'U   [:m [22 7] [:b ['+ 'r 'h]]]]]

      [m7/m ['= ['* 'U [7 22]]   [:m [7 22] [22 7]  [:b ['+ 'r 'h]]]]]





      ]

     [:div
      {:key (gensym)
       :style (css
               [[7 2 2 11 :center :center 2 :rem :column]
                [1.5 70 70 1] []
                {:z-index 4}])}
      [:div ""]
      ]

     [:div {:key (gensym)
            :style (css
                    [[2 11 2 11 :center :center 2 :rem]
                     [-2 70 70 1] []
                     {:z-index 2}]
                    )}

      [:svg {:viewBox (space (nth [[-100  -100 200 200]
                                   [-10 -160 80 80]] 0))
             :style
             {:height (size {:size 100 :scale :%})
              :width  (size {:size 100 :scale :%})}}

       (if view-box?
           [:animate {:attributeName :viewBox
                      :to [-2 -2 4 4]
                      :dur "1s"
                      :fill :freeze}])

       [:circle {:cx 0 :cy 0 :r (f 20) :fill (hsl [1 70 70 1])}]




       [:path {:d (path
                   [(ve (f 2)) (ve (f 2)) :l 1 0  0 1 (ve 1)  0 0 (ve 1)])
               :stroke (hsl [1 70 70 1])
               :fill :none
               :stroke-dasharray [(f 4) (f 2) (f 4)]
               :stroke-width (f 50)
               }]


       [:path {:d (path
                   [(ve (f 2)) (ve (f 2))
                    :l (f 4) 0  :a (f 4) (f 4) 0 false true (ve (f 4)) (f 4)
                    :l 0 (ve (f 4))
                    1 0  0 1 (ve 1)  0 0 (ve 1)
                     -1 0  0 1 1  0 0 (ve 1)])
               :stroke (hsl [1 70 70 1])
               :fill (hsl [(f 2) 70 70 1])

               :stroke-width (f 50)
               }]

       [:path {:d (path (into [(ve (f 2)) (ve (f 2)) :l] dx))
               :transform (m7/tranfrom [[:translate [0 50]]
                                        [:scale 40]
                                        [:rotate 45]
                                        ])
               :stroke (hsl [1 70 70 1])
               :fill :none
               :stroke-width (f 50)
               }]]]]))


(comment
  (let [dx [1 0  0 1 -1  0 0 -1 ]
        ]
    small-box
    ))
