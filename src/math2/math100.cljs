(ns math2.math100
  (:require [math2.math7 :as m7 :refer
             [grid hsl css space size path ve sec]]))

(def square [1 0 0 1 (ve 1) 0 0 (ve 1)])

(defn template []
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

     (let [bm 30
           bf (fn [n] (* bm (/ 1 n)))
           base [(ve (bf 2)) (ve (bf 2))]
           bm2 (/ bm 2)
           angle-x 0
           angle-y 15

           one [m7/m ['* 1 68]]
           frac [m7/m [['* 17 2 2] 4]]
           scl 4.2


;;            one [m7/m ['* 1 1]]
;;            frac [:div [m7/m [1 4]] "of 68"]
;;            scl 1.5


;;           one [m7/m [100 100]]
;;           frac [:div [m7/m [25 100]]  "of 68"]
;;           scl 1.5

          ;;  one [m7/m [:m 100 '%]]
          ;; frac [:div  [m7/m [:m 25 '%]
          ;;               ] "of 68"]
          ;;    scl 7.5

           ]
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
                         :height (* 4 (bf 2))}

         one
         ]
        (comment [m7/m '[:p 3 2]])

        [:foreignObject {:x (- (first base) 10)
                         :y (second base)
                         :font-size ".4rem"
                         :width (* 10 (bf 2))
                         :height (* 10 (bf 2))}
         frac
         ]
        (comment [m7/m '[[:p 3 2] 4]])

        ])]








   [:div {:key (gensym)
           :style (css
                   [[1 7 9 7 :center :center 2 :rem]
                    [.5 70 70 .5] []
                    {:z-index 22}]
                   )}

     (let [bm 30
           bf (fn [n] (* bm (/ 1 n)))
           base [(ve (bf 2)) (ve (bf 2))]
           bm2 (/ bm 2)
           angle-x 0
           angle-y 15

;;           one [m7/m ['* 1 32]]
;;           frac [m7/m [32 4]]
;;           scl 4.2


;;            one [m7/m ['* 1 1]]
;;            frac [:div [m7/m [1 4]] "of 32"]
;;            scl 1.5


;;           one [m7/m [100 100]]
;;           frac [:div [m7/m [25 100]]  "of 32"]
;;           scl 1.5

          one [m7/m [:m 100 '%]]
          frac [:div  [m7/m [:m 25 '%]
                        ] "of 68"]
             scl 7.5

           ]
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
                         :height (* 4 (bf 2))}

         one
         ]
        (comment [m7/m '[:p 3 2]])

        [:foreignObject {:x (- (first base) 10)
                         :y (second base)
                         :font-size ".4rem"
                         :width (* 10 (bf 2))
                         :height (* 10 (bf 2))}
         frac
         ]
        (comment [m7/m '[[:p 3 2] 4]])

        ])]


   ])



(defn template2 []
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

     (let [bm 30
           bf (fn [n] (* bm (/ 1 n)))
           base [0 0]
           bm2 (/ bm 2)

           ]
       [:svg {:viewBox (space (nth [[-100  -100 200 200 ]
                                    [-10 -160 80 80]] 0))
              :style {:height (size {:size 100 :scale :%})
                      :width  (size {:size 100 :scale :%})}}




        [:path {:d
                (path
                 (
                  (comp
                   (partial into [-15 -15])
                   (partial cons :l)
                   (partial map (partial * 30)))
                  [1 0 0 1 (ve 1) 0 0 (ve 1)]))
                :stroke (hsl [0.5 50 50 1])
                :stroke-width 1
                :fill (hsl  [1 70 70 .8])}

         ]

        (map


         (fn [[x y]]
           [:text {:key (gensym)
                   :x x
                   :y y
                   :dx -2
                   :dy 3
                   :style {:font-size ".3rem"}


                   } "Ab"])
         (let [[_ & rest] (reduce
                           (fn [acc  [x y]]
                             (let [[x1 y1] (last acc)]
                               (conj acc [(+ x x1) (+ y y1)]))
                             )

                           [[0 0]]
                           (partition 2
                                      (
                                       (comp
                                        (partial into [-18 -18])
                                        (partial map (partial * 33)))
                                       [1 0 0 1 (ve 1) 0 0 (ve 1)])))]

           rest))


        (comment

          (let [sq
                [[1 0 0 1 (ve 1) 0 0 (ve 1)]
                 [(ve 1) 0 0 (ve 1) 1 0 0 1]
                 [1 0 0 (ve 1) (ve 1) 0 0 1]
                 [1 0 0 (ve 1) (ve 1) 0 0 1]]
                ]
            (apply concat sq))







          )
        (comment
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
             }]])

        [:circle {:cx 0
                  :cy 0
                  :r 1
                  :stroke (hsl [1.8 70 70 1])
                  :fill (hsl [2.8 70 70 1])}]])]])
