(ns math2.math100
  (:require [math2.math7 :as m7 :refer
             [grid hsl css space size path ve sec]]))

(def square [1 0 0 1 (ve 1) 0 0 (ve 1)])

(defn template []
  (let [bm 30
        bf (fn [n] (* bm (/ 1 n)))
        base [(ve (bf 2)) (ve (bf 2))]
        bm2 (/ bm 2)
        angle-x 0
        angle-y 15

        o [[m7/m ['* 1 80]]
           [m7/m ['* 1 1]]
           [m7/m [100 100]]
           [m7/m [:m 100 '%]]]
        f [[m7/m [80
                  4]]
           [:div [m7/m [1 4]] ""]
           [:div [m7/m [25 100]]  ""]
           [:div  [m7/m [:m 25 '%]
                   ] ""]]
        n 0
        one (nth o n)
        frac (nth f n)
        one2  (nth o 3)
        frac2 (nth f 3)
        scl (nth [4.2 1.5 1.5 7.5] n)]
    [:div {:style
           (merge
            (grid [100 :vh 100 :vw
                   (take 15 (repeat [8 :vh]))
                   (take 15 (repeat [8 :vh]))])
            {:background-color (hsl [1.4 70 70 .8])})}

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
                          :y 10
                          :font-size ".4rem"
                          :width (* 4 (bf 2))
                          :height (* 4 (bf 2))} one]

         [:foreignObject {:x (- (first base) 10)
                          :y (second base)
                          :font-size ".4rem"
                          :width (* 10 (bf 2))
                          :height (* 10 (bf 2))} frac]


       ]]


     [:div {:key (gensym)
            :style (css
                    [[8 6 8 6 :center :center 2 :rem]
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
                          :y 10
                          :font-size ".4rem"
                          :width (* 4 (bf 2))
                          :height (* 4 (bf 2))} one2]

         [:foreignObject {:x (- (first base) 10)
                          :y (second base)
                          :font-size ".4rem"
                          :width (* 10 (bf 2))
                          :height (* 10 (bf 2))} frac2]


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
  (let [dx [1 0 0 1 -1 0 0 -1 ]
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
                  (partial mapv (partial * 10))))
        points (s4 dx)
        ]
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
       [:path {:d (path sq-path)
               :stroke (hsl [1 70 70 1])
               :stroke-width 1
               :fill :none}]
       (map (fn [[x y]]
              [:circle {:key (gensym)
                        :cx x :cy y :r 1 :fill (hsl [1 70 70 1])}])
            points)

       ]]]))

(comment
  )
