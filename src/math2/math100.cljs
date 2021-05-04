(ns math2.math100
  (:require
   [react]
   [math2.math7 :as m7 :refer
    [grid hsl css space size path ve sec]]))


(def f
  (fn [m n] (/ m n)))
(def square [1 0 0 1 (ve 1) 0 0 (ve 1)])

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

        o ['b
           [m7/m ['* 1 1]]
           [m7/m [100 100]]
           [:div st [m7/m [:m 100 '%]]]]
        f ['a
           [:div [m7/m [1 'l]] ""]
           [:div [m7/m ['e 100]]  ""]
           [:div st
            [m7/m [:m 'e '%]]]]
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

      #_[m7/m '[= [:m e %] [e 100] [[e 100] [100 100]] [1 l] [a b]]]

      [m7/m '[= [e 100] [a b]]]

      #_[m7/m '[= [5 100] [a 80]]]
      #_[:div "5% of students failed on exam out  of 80 students, how many students failed?"]


      [:div
       "15% profit  is 75tk, so what is total investment?"]

      [m7/m '[= [15 100] [75 Inv]]]

      [m7/m '[= Inv
              500]]
      #_[m7/m '[= Inv
              [7500 15]]]


      #_[:div {:style {:font-size "1.5rem"}}
       "Buying price is 1500 tk, we sold of with 20% profit, what is our profit?"]

      #_[m7/m '[=  [20 100]
              [pofit 1500]]]

      ]

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

   [:div {:key (gensym)
          :style (css
                  [[2 7 2 7 :center :center 3 :rem :column]
                   [1.2 70 70 .9] []
                   {:z-index 28}])}

    ]


   (comment
     )
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
                   :fill (hsl [2.8 70 70 1])}]])]
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
                    [[2 11 2 20 :center :center 3 :rem :column]
                     [0 70 70 1] []
                     {:z-index 2}
                     ]

                    )
            }

      [:div "Impulse is change in momentum"]
      [m7/m '[= [:m F t] [- [:m m v ] [:m m u ]]]]
      [m7/m '[= [:m F t] [- [* m 0 ] [:m m u ]]]]
      [m7/m '[= [* F [:m 0.070 s]] [- [* m 0 ] [:m 0.5 kg [:b [- 3.1] ] [m s] ]]]]
      [m7/m '[= [* F [:m 0.070 s]] [:m 1.55 kg  [m s] ]]]
      [m7/m '[= F
              [[:m 1.55 N] 0.07]]]]]))

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
                          (conj acc [(+ x x1) (+ y y1)])))
                      [[0 0]])
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
        view-box? true
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

      [:svg {:viewBox (space (nth [[-100  -100 200 200]
                                   [-10 -160 80 80]] 0))
             :style
             {:height (size {:size 100 :scale :%})
              :width  (size {:size 100 :scale :%})}}

       (if view-box?
           [:animate {:attributeName :viewBox
                      :to [-4 -4 8 8]
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
                   (let  [dx [1 0  0 1 -1  0 0 -1 ]
                          sq2 (partition
                               2
                               (map (partial * (f 3)) dx))
                          toggle (fn [[x & r]]
                                   (conj (vec r) x))
                          add (fn [[x y] [u v]]
                                [(+ x u)
                                 (+ y v)])

                          ]
                     (flatten
                      [
                       [(ve (f 2)) (ve (f 2))]
                       :l
                       (interleave
                        (map
                         (fn [[a b c d]]
                           [a :a (f 2) (f 2) 0 false true  (add b c) :l d])
                         (reduce
                          (fn [acc _]
                            (conj acc (toggle
                                       (last acc))))
                          [sq2]
                          (range 3)))
                        (partition 2  dx))]))
                   )
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
               }]]

      ]

     [:div {:key (gensym)
            :style (css
                    [[2 11 2 20 :center :center 3 :rem :column]
                     [-2 70 70 1] []
                     {:z-index 2}]
                    )}

      [:div "Area of Triangle A, where b is the base and h is height"]
      [:div [m7/m '[= b 20 cm]] " , " [m7/m '[= h 45 cm]]]
      [m7/m '[= A [* [1 2] 20 45]]]
      [m7/m '[= A [:m 450 [:p cm 2]]]]





      ]

     [:div {:key (gensym)
            :style (css
                    [[2 11 2 11 :center :center 2 :rem]
                     [-2 70 70 .1] []
                     {:z-index 2}]
                    )}

      [:svg {:viewBox (space (nth [[-100  -100 200 200]
                                   [-10 -160 80 80]] 0))
             :style
             {:height (size {:size 100 :scale :%})
              :width  (size {:size 100 :scale :%})}}

       (if view-box?
           [:animate {:attributeName :viewBox
                      :to [-4 -4 8 8]
                      :dur "1s"
                      :fill :freeze}])

       [:circle {:cx 0 :cy 0 :r (f 20) :fill (hsl [1 70 70 1])}]




       [:path {:d (path
                   [0 -2 :l 1 2 -2 0 1  -2])
               :stroke (hsl [1 70 70 1])
               :fill :none
               :stroke-width (f 50)
               }]


       [:path {:d (path
                   [0 -2 :l 1 0 0 ])
               :stroke (hsl [2.5 70 70 1])
               :fill :none
               :stroke-width (f 50)
               }]


       [:path {:d (path
                   [0 0 :l 0 -2])
               :stroke (hsl [0 70 70 1])
               :fill :none
               :stroke-width (f 20)
               }]

       [:path {:d (path
                   [-1 0 :l  2 0])
               :stroke (hsl [.5 70 70 1])
               :fill :none
               :stroke-width (f 20)
               }]




       ]

      ]

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
                    [[2 5 1 20 :center :center 2 :rem :column]
                     [(+ 1 (f 3)) 70 90 1] [] {:z-index 3
                                               :gap "1rem"}])}
      [:div "Before"]
      [m7/m '[= [+ P1 P2] [+ [:m 43.2 kg 4.2 [m s]] [:m 2.50 Kg  0 [m s]]] ]]

      [m7/m '[= [+ P1 P2] [:m 43.2 kg 4.2 [m s]]]]
      [m7/m '[= [+ P1 P2] [:m 181.44 kg  [m s]]]]


      [:div "Combined velocity of body and skateboard After boy land on it"]
      [m7/m '[= P3 [:m  [:b [+ m1 m2]] v]]]

      [m7/m '[= P3 [:m  [:b [+ 43.2 2.50]] kg v]]]

      [m7/m '[= P3 [:m  45.7 kg v]]]


      [:div "According to rule of conservation"]
      [:div "Sum of Momentums Before  = Sum of Momentums After "]

      [m7/m '[= [+ P1 P2] P3]]

      [m7/m '[= [:m 181.44 kg  [m s]] [:m  45.7 kg v]] ]

      [m7/m '[= [:m [181.44 [:m  45.7 kg ]] kg  [m s]] v ] ]

      ]






     [:div {:style (m7/css
                    [[2 10 1 20 :center :center 3 :rem]
                     [(+ 1 (f 3)) 70 90 1] [] {:gap "1rem"}])}
      [:svg {:style {:height "100%"
                     :width "100%"}
             :viewBox (m7/space
                       [-150 -100  300 200])}
       [:circle {:cx 0
                 :cy 0
                 :fill (hsl [-1 50 50 1])
                 :r 1}]

       [:path {:d (path (flatten [100 0 :l ((sq 15) dx)]))
               :stroke (hsl [-1 50 50 1])
               :stroke-width .2
               :fill (hsl [-1 10 50 1]) }
        [:animateTransform
         {:id :sq1
          :attributeName :transform
          :begin :sq.end
          :dur (sec 5)
          :type :translate
          :from (m7/space [0 0])
          :to (m7/space [100 0])
          :fill :freeze
          }]

        ]

       [:path {:d (path (flatten [0 0 :l ((sq 10) dx)]))
               :stroke (hsl [-1 50 50 1])
               :stroke-width .2
               :fill (hsl [-1 50 90 1]) }
        [:animateTransform
         {:id :sq
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

       ]]]))

(comment
  (let [ f (fn [n] (/ 1 n))
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
                (comp
                 (partial map (partial * n))))]
    )
  )
