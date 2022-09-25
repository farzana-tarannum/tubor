(ns math2.transfrom
  (:require
   [math2.file :as file]
   [react]
   [math2.bdmap :as bdmap]
   [clojure.string :as str]
   [clojure.walk :as w]
   [defun.core :refer [defun fun]]
   [math2.solution :as sol]
   [math2.solve :as sl]
   [math2.svg :as svg]
   [math2.math7 :as m7 :refer
    [grid hsl css space size path ve sec]]))

(defn fix [fr n]
  (js/parseFloat (.toFixed fr n)))

(defn fixint [fr n]
  (js/parseInt (.toFixed fr n)))


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
      (map-indexed
       (fn [index i]
         [:path {:key (str "grid-onadfadsf" index)
                 :d (path [-400 (ve (+ ax-dx (* i 2))) :l 1200 0])
                 :stroke (hsl [4 70 70 .5])
                 :stroke-width .5
                 :fill :none}
          ] )
       (range 0 11))

      (map-indexed
       (fn [index i]
         [:path {:key (str "grid-onsdfasd2" index)
                 :d (path [(+ ax-dy (* i 2)) -400 :l 0 1200 ])

                 :stroke (hsl [4 70 70 .5])
                 :stroke-width .5
                 :fill :none}
          ] )
       (range 0 11))

      (map-indexed
       (fn [i x]
         [:g {:key (str "path-indexed333" i)}

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
                  :font-size 5}
           (if frac
             (.toFixed (* x X) 1)
             (* x X))
           ]])
       (range -20 40))



      (map-indexed
       (fn [i y]
         [:g {:key (str "grid-onpathysdfsdd" i)}
          [:path {:d (path [0 (ve (* 20 y))   :l 1200 0])

                  :stroke (hsl [1 70 70 1])
                  :stroke-width 1
                  :fill :none}
           ]

          [:text {:x -4.5
                  :dy -1
                  :fill (hsl [0 40 20 1])
                  :y (ve (* 20 y))
                  :font-size 5}
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
(defn green []
  [:g
   [:path#green {
                 :d (m7/path [0 -30 :l 0 (ve (* 1 20 ))
                              (* 10 20) 0 0 (* 1 20 )
                              (* 10 -20) 0])
                 :stroke-width 1
                 :fill (hsl [2 70 70 .3])}
    [:animateTransform {:id :green-car
                        :attributeName :transform
                        :begin 0
                        :dur (sec 5)
                        :from (space [-60 0])
                        :to (space [70 0])
                        :type :translate
                        :fill :freeze}]]
   [:path {:d (m7/path [200 0 :l 0 (ve (* 1 30 ))
                        (* 4 20) 0 0 (* 1 30 )
                        (* 4 -20) 0])
           :stroke-width 1
           :fill (hsl [2 70 70 .3])}]

   ])

(defn grad []
  [:radialGradient {
                             :id (name :lg2)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [3 40 40 .7])}]
            [:stop  {:offset .55
                     :stop-color (hsl [3.3 60 60 .3])}]


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

            ])

(defn coordinates []
  (let [f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
             (comp
              (partial map (partial * n))))
        ax-dx 80
        ax-dy 40
        vb (fn [z]
             (nth [(map #(* 5 %) [-10 -70  83 80])
                   [-100 -150  300 300]
                   [-100 -150  300 300]
                   [0 -80  80 100]
                   [0 -25  50 50]
                   [-100 -200  890 200]
                   [40 120  80 80]
                   [0 40  100 100]
                   [75 -175  150 150]
                   [-20 -20  100 100]
                   [-400 -200  800 200]] z))
        [viewbox4 set-viewbox4] (react/useState (vb 0))
        [viewbox3 set-viewbox3] (react/useState (vb 0))
        [clear set-clear] (react/useState false)
        m 30
        d 9.4
        scale2 [[.5 .5] [-0.5 .5]]
        [text set-text] (react/useState "hello")
        [slider set-slider] (react/useState 1)
        [count set-count] (react/useState 1)
        [xx set-xx] (react/useState 2)
        [yy set-yy] (react/useState 3)


        animate-ref (react/useRef)
        svg-ref (react/useRef)
        text-ref (react/useRef)
        text-style (clj->js
                   [{
                     :background (hsl [.5 70 70 .1])
                     :transform (m7/tranfrom [[:scale .7]])
                     }
                    {:background (hsl [1 60 70 .7])
                     :transform (m7/tranfrom [[:scale .8]])

                     }

                    {:background (hsl [.5 60 60 .9])
                     :transform (m7/tranfrom [[:scale .9]])
                     :offset (/ 9 14)}

                    {:background (hsl [.3 70 70 .7])
                     :transform (m7/tranfrom [[:scale 1]])
                     }])
        anm-style (clj->js
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
        svg-style (clj->js
                   [{
                     :background (hsl [.5 70 70 .1])
                     :transform (m7/tranfrom [[:rotate "10deg"]
                                              [:scale .2 .3]])
                     }
                    {:background (hsl [.9 70 70 .7])
                     :transform (m7/tranfrom [[:rotate "-10deg"]
                                              [:scale .5 .6]])
                     }

                    {
                     :background (hsl [2 70 70 .9])
                     :transform (m7/tranfrom [[:scale .9]])
                     :offset (/ 9 14)}

                    {
                     :background (hsl [3.5 70 70 .7])
                     :transform (m7/tranfrom [[:scale 1]])
                     }])
        anm-fn (fn []
                 (if (and animate-ref (-> animate-ref .-current))
                   (-> animate-ref .-current
                       (.animate
                        anm-style
                        (clj->js
                         {:duration 400
                          :iterations 1}))))
                 (js/console.log "1"))
        animate-fn
        (fn [ref style]
          (if (and ref (-> ref .-current))
            (-> ref .-current
                (.animate
                 style
                 (clj->js
                  {:duration 2000
                   :iterations 1})))))

        svg-fn (fn []
                 (animate-fn svg-ref svg-style)
                 (js/console.log "svg"))
        text-fn (fn []
                 (animate-fn text-ref text-style)
                 (js/console.log "svg"))
        _ (react/useEffect text-fn)
        _ (react/useEffect anm-fn)
        _ (react/useEffect svg-fn)

        [c ck] (react/useState [0 0])
        p (fn [svg x y]
            (let [p (js/DOMPoint. x y)
                  t (-> svg
                        (.getScreenCTM)
                        (.inverse))
                  xy (-> p
                         (.matrixTransform t))]
              (ck [(fix (/ (-> xy .-x) 2) 1)
                   (fix (* -1 (/ (-> xy .-y) 2)) 1) ])))]
    [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .5])
                      :gap ".2rem"})}
     [:div
      {:ref (if (and (= slider 0)
                     (> count 0))
              text-ref nil)
       :style (m7/css
               [[3 9 8 12  :center :center 2.2 :rem :column]
                [2 70 90 .4] [] {:gap "1rem"
                                 :z-index 2}])}


      #_[m7/x  `[= [[- ya yb] [- xa xb]] [[- -5 1] [- 0 [- 2]]] [-6 2]]]
      #_[m7/x `[= [:p AB 2] [+ [:p [:b -6] 2]  [:p 2 2]] 40]]

      #_[m7/x  `[= [[- ya ya] [- xa xc]] [[- -5 5] [- 0 [- 10]]] [-10 -10]]]

      #_[m7/x `[= [:p AC 2] [+ [:p [:b -10] 2]  [:p 10 2]] 200]]

      #_[m7/x  `[= [[-  yb yc] [-  xb xc]] [[-  1 5] [- [- 2] 10]] [-4 -12]]]

      #_[m7/x `[= m gradient [rise run] [[:m r sin ~tt] [:m r cos ~tt]]
              [[- y y1] [- x x1]]]]


      #_[:div "A(-1,2) C(2,5) for AC"]

      #_[m7/x `[= m [[- 2 5 ] [- -1 2 ]]]]

      #_[m7/x `[= m [3 -3]]]


      #_[m7/x `[= AC [+ [:p 3 2]  [:p [:b [- 3]] 2]]]]

      #_[m7/x `[= [:p AC 2]  [+ [:p [:b [- 6 y ]] 2] [:p [:b [- [- 2] x ]] 2]]]]



      #_[m7/x `[= [:p AC 2] [+ [:p [:b [- 6 y ]] 2] [:p [:b [- [- 2] x ]] 2]]]]




      #_[:div "B(5,2) C(2,5) for BC"]

      #_[m7/x `[= m [[- 2 5 ] [- 5 2 ]]]]

      #_[m7/x `[= m [-3 3]]]

      #_[m7/x `[= BC [+ [:p [:b -3] 2] [:p 3 2]]]]




      #_[:div "A(-1,2) B(5,2)  for AB"]

      #_[m7/x `[= m [[- 2 2 ] [- -1 5 ]]]]

      #_[m7/x `[= [:m m [:b [- x x1]]] [- y y1] ]]


      #_[m7/x `[= [:k m bc] [[- 3 y ] [- 9 x ]]]]


      #_[m7/x `[= [:p BC 2]  [+ [:p [:b [- 3 y ]] 2] [:p [:b [- 9 x ]] 2]]]]

      #_[m7/x `[= [:p BC 2]  [+ [:p [:b [- 3 y ]] 2] [:p [:b [- 9 x ]] 2]]]]


      #_[m7/x `[= [:p AC 2] [:p BC 2]]]

      #_[m7/x `[= [+ [:p [:b [- 6 y ]] 2] [:p [:b [- [- 2] x ]] 2]]  [+ [:p [:b [- 3 y ]] 2] [:p [:b [- 9 x ]] 2]]]]

      #_[m7/x `[= [+ [:p [:b [- 6 y ]] 2] [:p [:b [:m  [- 1] [:b [+ 2 x]] ]] 2]]  [+ [:p [:b [- 3 y ]] 2] [:p [:b [- 9 x ]] 2]]]]


      #_[m7/x `[= [+ [:p [:b [- 6 y ]] 2] [:m [:p [:b [- 1]] 2]  [:p [:b [+ 2 x]] 2]]]  [+ [:p [:b [- 3 y ]] 2] [:p [:b [- 9 x ]] 2]]]]


      #_[m7/x `[= [+ [:p [:b [- 6 y ]] 2] [:p [:b [+ 2 x]] 2]]  [+ [:p [:b [- 3 y ]] 2] [:p [:b [- 9 x ]] 2]]]]

      #_[:div "As point C is in X axis "]

      #_[m7/x `[= y 0]]

      #_[m7/x `[= [+ [:p [:b [- 6 0 ]] 2] [:p [:b [+ 2 x]] 2]]  [+ [:p [:b [- 3 0 ]] 2] [:p [:b [- 9 x ]] 2]]]]


      #_[m7/x `[= x [25 11]]]

      #_[m7/x `[= [- [1 2]]  [[- 4 3] [- 1.5 1] ]   [[- 4.5 3] [- [- 2] 1]] [[- y 3] [- x 1]]]]


      #_[m7/x `[= [- [1 2]] [[- y 3] [- x 1]] ]]

      #_[m7/x `[= [- [1 2]] [[- y 1.5] [- x 4]] ]]


      #_[m7/x `[= [:m [- [1 2]] [:b [- x 1]]] [- y 3]]]


      #_[m7/x ['= [14 32] [7 16]]]
      #_(if (> count 0)
        "Describe fully single transformation that maps triangle P to R")]

     #_(if (= slider 5)
       [:div
        {:ref text-ref
         :style (m7/css
                 [[1 1 1 23 :center :center 2.5 :rem]
                  [2 70 90 1] [] {:gap "1rem"
                                  :z-index 1}])}
        "Answer: Enlargement of scale factor 2 at the point 40,15" ])

       (map-indexed
        (fn [n [d r c f1 f2]]
          [:div {:key (str "abc_svg" n)
                 :ref (if (= n slider) animate-ref nil)
                 :on-click (fn [e]
                                   (if (= n 4)
                                     (set-count 0)
                                     (do
                                       (set-viewbox4 viewbox3)
                                       (set-viewbox3 (vb n))
                                       (set-slider n)
                                       (set-count (+ 1 count))
                                       (js/console.log "hello" count (space viewbox3) (space viewbox4))))

                                   )
                 :style (m7/css
                         [[(* 2 r) 1 (* 3 c) 3  f1 f2  1.5
                           :rem :column]
                          [(if (= slider n) 2.5 3.5 ) 70 (+ 50 (* 2 n)) .3] []
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
        [["Q.3" 1 1 :center :center]
         [[:div {:style {:width :100%
                         :font-size "1rem"
                         :display :flex
                         :justify-content :space-around}}
           [:div {:on-click (fn [_]
                              (set-xx (- xx 1)))} "⬅"] [:div [m7/x  `[= y ~xx]]]
           [:div {:on-click (fn [_]
                              (set-xx (+ xx 1)))} "⇨"]
           [:div {:on-click (fn [_]
                              (set-yy (- yy 1)))} "⬅"]
           [:div [:div [m7/x  `[= x ~yy]]]]
           [:div {:on-click (fn [_]
                              (set-yy (+ yy 1)))} "⇨"]] 1 2 :center :center]
         ["Graph"
          1 3 :center :center]
         ["zoom" 1 4 :center :center]
         ["" 1 5 :center :center]
         [[:div {:style {:width :100%
                         :display :flex
                         :justify-content :space-around}}
           "Answer"
           ]
          1 6 :center :center]

         ])

     [:div {:ref (if (= slider 2) svg-ref nil)
            :style (m7/css
                    [[2 10 2 23 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"
                                     :z-index 1}])}

      (let [[x1 y1 x2 y2] [20 (ve (*  2 30 )) -40 (ve (* 4.5 20 ))]
              [rise run] [(ve (* 2 7 )) (* 16 2)]
              [x3 y3 x4 y4] [(+ x1 run) (+ y1 rise)
                             (+ x2 (* run 2)) (+ y2 (* rise 2))]

              m (/ (- y2 y1) (- x2 x1))
              m1 (/ (- y4 y3) (- x4 x3))
              leq (fn [[m x1 y1]]
                    (fn [x]
                      (+ (* m (- x x1)) y1)))

              y11 ((leq [m x1 y1]) (* -5 20))
              y22 ((leq [m x1 y1]) (* 5 20))
              y33 ((leq [m1 x3 y3]) (* 5 20))
              y44 ((leq [m1 x3 y3]) (* -5 20))
              point-scale
              (fn [c n]
                (if (= n 2 )
                  (let [pid (str (random-uuid))]
                    [:g {:id (str (random-uuid))}
                     [:path {:id pid
                             :d (m7/path [0 0 :l run rise
                                          0 (ve rise)
                                          (ve run) 0])
                             :stroke (hsl [(rand 4) 70 70 .5])
                             :stroke-width .5
                             :fill (hsl [(rand 4) 70 70 .6])}]

                     [:animateTransform
                      {:id (str (random-uuid))
                       :href (str "#" pid)
                       :attributeName :transform
                       :begin 0
                       :dur (sec 10)
                       :from (space [(* 4 20) -30])
                       :to (space [(* -5 20) y11])
                       :repeatCount :indefinite
                       :type :translate
                       :fill :freeze}]
                     [:animateTransform
                      {:href (str "#" pid)
                       :id (str (random-uuid))
                       :attributeName :transform
                       :begin 0
                       :dur (sec 10)
                       :from 0
                       :to (* 1 (/ (- -120 -30)  (- -60 -30)))
                       :repeatCount :indefinite
                       :type :scale
                       :additive :sum
                       :fill :freeze}]
                     [:g ]])))
            ]
        (if (= count 0 )
          [:div ""]

          [:svg {
                 :style {:height :100%
                         :width :100%}
                 :on-click (fn [e]
                             (p
                              (-> e .-target)
                              (-> e  .-clientX)
                              (-> e  .-clientY) ))
                 :viewBox (m7/space viewbox4)}
           [svg/flames]
           [:radialGradient {
                             :id (name :lg2)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [3 40 40 .7])}]
            [:stop  {:offset .55
                     :stop-color (hsl [3.3 60 60 .3])}]


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
           [:animate {:ref svg-ref
                      :attributeName :viewBox
                      :to (m7/space viewbox3)
                      :dur "4s"
                      :fill :freeze}]





           (if (or (= slider 2) (= slider 1) (= slider 3))
             (grid-on 1 1 (* xx 20) (* yy 20) false))


           [:g
            [:path#tri2 {:d (m7/path [x1 y1 :l run rise
                                      0 (ve rise)
                                      (ve run) 0])
                         :stroke (hsl [.5 70 70 1])
                         :stroke-width .5
                         :fill (hsl [1 70 70 1])}
             ]


            [:path#quad2 {:d (m7/path [x1 y1 :l run rise
                                       0 (ve rise)
                                       (ve run) 0])
                          :stroke (hsl [3.5 70 70 1])
                          :stroke-width .5
                          :fill (hsl [4.1 70 70 1])}
             ]






            [:path#tri3 {:d (m7/path [x2 y2 :l (* 16 2 2) (ve (* 2 7 2))
                                      0 (ve (ve (* 2 7 2))) (ve (* 16 2 2)) 0])
                         :stroke (hsl [.5 70 70 1])
                         :stroke-width .5
                         :fill (hsl [1 70 70 1])}
             ]



            [:path#tri3 {:d (m7/path [x2 y2 :l (* 16 2 2) (ve (* 2 7 2))
                                      0 (ve (ve (* 2 7 2))) (ve (* 16 2 2)) 0])
                         :stroke (hsl [.5 70 70 1])
                         :stroke-width .5
                         :fill (hsl [1 70 70 1])}
             ]

            [:path#tri31 {:d (m7/path [x2 y2 :l (* 16 2 2) (ve (* 2 7 2))
                                      0 (ve (ve (* 2 7 2))) (ve (* 16 2 2)) 0])
                         :stroke (hsl [.5 70 70 1])
                         :stroke-width .5
                         :fill (hsl [1 70 70 1])}
             ]

            (comment
              :transform (m7/tranfrom
                          [[:translate [(* 4 20) -30]]
                           [:scale 0]
                           [:translate [(ve x1) (ve y1)]]]))


            (point-scale count slider)




            ]


           [:g
            [:path#green {
                          :d (m7/path [0 -30 :l 0 (ve (* 1 20 ))
                                       (* 10 20) 0 0 (* 1 20 )
                                       (* 10 -20) 0])
                          :stroke-width 1
                          :fill (hsl [2 70 70 .3])}
             [:animateTransform {:id :green-car
                                 :attributeName :transform
                                 :begin 0
                                 :dur (sec 5)
                                 :from (space [-60 0])
                                 :to (space [70 0])
                                 :type :translate
                                 :fill :freeze}]]
            [:path {:d (m7/path [200 0 :l 0 (ve (* 1 30 ))
                                 (* 4 20) 0 0 (* 1 30 )
                                 (* 4 -20) 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 .3])}]

            ]
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

           [:g
            [:path {:d (m7/path [0 0 :l 0 (ve (* 2 30 ))
                                 20 0 0 (* 2 30 )
                                 -20 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 .2])}]



            [:path {:d (m7/path [ (+ 20 32) 0 :l 0 (ve (* 2 37 ))
                                 20 0 0 (* 2 37 )
                                 -20 0])
                    :stroke-width .5
                    :fill (hsl [2 70 70 .15])}]
            (if (and (= slider 0) (= clear false))
              [:g]
              [:g
               [:path#b1 {:d (m7/path [(* -5 20) y11 :L (* 5 20) y22])
                          :stroke-width .5
                          :marker-end (m7/url (name :mb2))
                          :marker-start (m7/url (name :mb2))
                          :stroke (hsl [2 30 30 1])
                          :fill :none}]

               [:path#b2 {:d (m7/path [(* -5 20) y44
                                       :L
                                       (* 5 20) y33])
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

                          :fill (hsl [1 70 70 1])}]])





            [:text {:style {:font-size 5}}

             [:textPath {:href :#tri2
                         :startOffset :20%
                         :dy -20
                         }
              "P"]

             [:textPath {:href :#tri3
                         :startOffset :3%
                         :font-size 3}
              "A"]
             [:textPath {:href :#tri31
                         :startOffset :90%
                         :font-size 3}
              "B"]

             [:textPath {:href :#tri3
                         :startOffset :100%
                         :font-size 3}
              "B"]

             #_[:textPath {:href :#tri2
                           :startOffset (+ 13 (* 2 17))
                           :font-size 3}
                "C"]

             [:textPath {:href :#b1
                         :startOffset "40%"
                         }
              ""]

             [:textPath {:href :#b2
                         :startOffset "40%"}
              ""]

             [:textPath {:href :#green
                         :font-size 7
                         :dy 20
                         :font-family "Roboto Flex"
                         :startOffset "5%"}
              ""]

             [:textPath {:href :#b3
                         :startOffset "40%"}
              (str (first c) " " (last c))



              ]]



            ]

           [:g






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









           #_(map
              (fn [x]
                [:circle {:cx x
                          :cy (ve (+ x 1))
                          :r .05
                          :fill (hsl [5 70 70 1])}])
              (range -200 200 .1))




           (map-indexed
            (fn [i x]
              [:circle {:key (str "cir" i)
                        :cx x
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


           (map-indexed
            (fn [x]
              [:circle {:key (str (random-uuid))
                        :cx x
                        :cy (ve (+ (* x x) (* 2 x ) -3 ))
                        :r .05
                        :fill (hsl [0 70 70 1])}])
            (range -200 200 .1))])
          )
      ]]))


(defn coordinates2 []
  (let [f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
             (comp
              (partial map (partial * n))))
        ax-dx 80
        ax-dy 40
        vb (fn [z]
             (nth [(map #(* 5 %) [-10 -70  83 80])
                   [-100 -150  300 300]
                   [-100 -150  300 300]
                   [0 -80  80 100]
                   [0 -25  50 50]
                   [-100 -200  890 200]
                   [40 120  80 80]
                   [0 40  100 100]
                   [75 -175  150 150]
                   [-20 -20  100 100]
                   [-400 -200  800 200]] z))
        [viewbox4 set-viewbox4] (react/useState (vb 0))
        [viewbox3 set-viewbox3] (react/useState (vb 0))
        [clear set-clear] (react/useState false)
        m 30
        d 9.4
        scale2 [[.5 .5] [-0.5 .5]]
        [text set-text] (react/useState "hello")
        [slider set-slider] (react/useState 1)
        [count set-count] (react/useState 1)
        [xx set-xx] (react/useState 2)
        [yy set-yy] (react/useState 3)


        animate-ref (react/useRef)
        svg-ref (react/useRef)
        text-ref (react/useRef)
        text-style (clj->js
                   [{
                     :background (hsl [.5 70 70 .1])
                     :transform (m7/tranfrom [[:scale .7]])
                     }
                    {:background (hsl [1 60 70 .7])
                     :transform (m7/tranfrom [[:scale .8]])

                     }

                    {:background (hsl [.5 60 60 .9])
                     :transform (m7/tranfrom [[:scale .9]])
                     :offset (/ 9 14)}

                    {:background (hsl [.3 70 70 .7])
                     :transform (m7/tranfrom [[:scale 1]])
                     }])
        anm-style (clj->js
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
        svg-style (clj->js
                   [{
                     :background (hsl [.5 70 70 .1])
                     :transform (m7/tranfrom [[:rotate "10deg"]
                                              [:scale .2 .3]])
                     }
                    {:background (hsl [.9 70 70 .7])
                     :transform (m7/tranfrom [[:rotate "-10deg"]
                                              [:scale .5 .6]])
                     }

                    {
                     :background (hsl [2 70 70 .9])
                     :transform (m7/tranfrom [[:scale .9]])
                     :offset (/ 9 14)}

                    {
                     :background (hsl [3.5 70 70 .7])
                     :transform (m7/tranfrom [[:scale 1]])
                     }])
        anm-fn (fn []
                 (if (and animate-ref (-> animate-ref .-current))
                   (-> animate-ref .-current
                       (.animate
                        anm-style
                        (clj->js
                         {:duration 400
                          :iterations 1}))))
                 (js/console.log "1"))
        animate-fn
        (fn [ref style]
          (if (and ref (-> ref .-current))
            (-> ref .-current
                (.animate
                 style
                 (clj->js
                  {:duration 2000
                   :iterations 1})))))

        svg-fn (fn []
                 (animate-fn svg-ref svg-style)
                 (js/console.log "svg"))
        text-fn (fn []
                 (animate-fn text-ref text-style)
                 (js/console.log "svg"))
        _ (react/useEffect text-fn)
        _ (react/useEffect anm-fn)
        _ (react/useEffect svg-fn)

        [c ck] (react/useState [0 0])
        p (fn [svg x y]
            (let [p (js/DOMPoint. x y)
                  t (-> svg
                        (.getScreenCTM)
                        (.inverse))
                  xy (-> p
                         (.matrixTransform t))]
              (ck [(fix (/ (-> xy .-x) 2) 1)
                   (fix (* -1 (/ (-> xy .-y) 2)) 1) ])))]
    [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .5])
                      :gap ".2rem"})}
     [:div
      {:style (m7/css
               [[3 1 8 11  :center :center 2.2 :rem ]
                [2 70 90 .4] [] {:gap "1rem"
                                 :z-index 2}])}

      [:div {:on-click (fn [_]
                         (set-xx (- xx 1)))} "⬅"]
      [:div [m7/x  `[= y ~xx]]]
      [:div {:on-click (fn [_]
                         (set-xx (+ xx 1)))} "⇨"]
      [:div {:on-click (fn [_]
                         (set-yy (- yy 1)))} "⬅"]
      [:div [:div [m7/x  `[= x ~yy]]]]
      [:div {:on-click (fn [_]
                         (set-yy (+ yy 1)))} "⇨"]
      ]

     (let [
           a (m7/eq2 `[[1 [1]  [x2]] [-1 [1]  [x1]]])
           b (m7/eq2 `[[1 [1]  [y1]] [1 [1] [y2]]])
           a1 (m7/eq2 `[[1 [1]  [x3]] [-1 [1]  [x2]]])
           b1 (m7/eq2 `[[1 [1]  [y3]] [1 [1] [y2]]])
           a2 (m7/eq2 `[[1 [1]  [x3]] [-1 [1]  [x1]]])
           b2 (m7/eq2 `[[1 [1]  [y3]] [1 [1] [y2]]])

           ]
       [:div
        {:style (m7/css
                 [[4 4 1 12  :center :center 1.5 :rem :column]
                  [2 70 90 .4] [] {:gap "1rem"
                                   :z-index 2}])}




        #_[m7/x `[= gr [* y1 [:b [- x2 x1]]]]]
        #_[m7/x `[= bt [* [1 2] [:b [-  y2 y1]] [:b [- x2 x1]]]]]


        #_[m7/x `[= base [:b [- x2 x1]]]]
        #_[m7/x `[= [+ gr bt]
                  [+ [* y1 base]
                   [* [1 2] [:b [- y2 y1]] base]]]]


        #_[m7/x `[= [+ gr bt]
                  [:m base [:b [+ y1
                                [* [1 2] [:b [- y2 y1]] ]]]]]]

        #_[m7/x `[= [+ gr bt]
                  [:m base [:b [+ [[:m 2 y1] 2] [- [y2 2] [y1 2]]]]]]]

        #_[m7/x `[= [+ gt bt]
                  [:m base
                   [:b [[+ [:m 2 y1] [- y2 y1]] 2]]]]]




        [m7/x `[= [:k t 1]
                [:m [:b [- x2 x1]]
                 [:b [[+ y1 y2] 2]]]]]



        [m7/x `[= [:k t 1]
                [:m [:b [- 2 -1]]
                 [:b [[+ 2 5] 2]]
                 ]
                [21 2]]]

        #_(m7/x ['= 'gtr
               [[:m
                 [:b (sl/symeq b)]
                 [:b (sl/symeq a)]] 2]])

        #_(m7/x ['= 'gtr [(sl/lawd2 a b ) 2]])

        #_(m7/x ['= 'gtr [(sl/lawd b a  ) 2]])

        #_[m7/x `[= [+ gt bt]
                  [:m [:b [- x2 x1]]
                   [:b [[+ y1 y2] 2]]]]]






        [m7/x `[= [:k t 2]
                [:m [:b [- x3 x2]]
                 [:b [[+ y2 y3] 2]]]]]


        [m7/x `[= [:k t 2]
                [:m [:b [- 5 2]]
                 [:b [[+ 5 2] 2]]
                 ]
                [21 2]]]



        #_(m7/x ['= 'rtr [(sl/lawd b1 a1  ) 2]])

        [m7/x `[= [:k t 3]
                [:m [:b [- x3 x1]]
                 [:b [[+ y1 y3] 2]]]]]

        [m7/x `[= [:k t 3]
                [:m [:b [- 5 -1]]
                 [:b [[+ 2 2] 2]]]
                12]]

        #_(m7/x ['= 'ytr [(sl/lawd b2 a2  )
                        2]])

        #_[m7/x `[= [+ gt [- rt yt] ]
                [+ [:m [:b [- x2 x1]]
                    [:b [[+ y1 y2] 2]]]
                 [-
                  [:m [:b [- x3 x2]]
                   [:b [[+ y2 y3] 2]]]

                  [:m [:b [- x3 x1]]
                   [:b [[+ y3 y1] 2]]]

                  ]
                 ]]]

        #_(m7/x ['= ['+ 'gtr ['- 'rtr 'ytr]]
               [['+ (sl/lawd b a  ) (sl/lawd b1 a1  )
                 ['- [:b (sl/lawd b2 a2  )]]
                 ] 2]])


#_        [m7/x `[= [+ gt [- rt yt] ]
                [[+ [:m [:b [- x2 x1]]
                     [:b [+ y1 y2] ]]
                  [-
                   [:m [:b [- x3 x2]]
                    [:b [+ y2 y3] ]]

                   [:m [:b [- x3 x1]]
                    [:b [+ y3 y1] ]]

                   ]] 2]]]



        ])







     [:div {:style (m7/css
                    [[2 10 2 23 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"
                                     :z-index 1}])}

      (let [[x1 y1 x2 y2] [20 (ve (*  2 30 )) -40 (ve (* 4.5 20 ))]
            [rise run] [(ve (* 2 7 )) (* 16 2)]
            [x3 y3 x4 y4] [(+ x1 run) (+ y1 rise)
                           (+ x2 (* run 2)) (+ y2 (* rise 2))]

            m (/ (- y2 y1) (- x2 x1))
            m1 (/ (- y4 y3) (- x4 x3))
            leq (fn [[m x1 y1]]
                  (fn [x]
                    (+ (* m (- x x1)) y1)))

            y11 ((leq [m x1 y1]) (* -5 20))
            y22 ((leq [m x1 y1]) (* 5 20))
            y33 ((leq [m1 x3 y3]) (* 5 20))
            y44 ((leq [m1 x3 y3]) (* -5 20))]
        [:svg {
                 :style {:height :100%
                         :width :100%}
                 :on-click (fn [e]
                             (p
                              (-> e .-target)
                              (-> e  .-clientX)
                              (-> e  .-clientY) ))
                 :viewBox (m7/space viewbox4)}
         [svg/flames]
         [grad]
         [:animate {:attributeName :viewBox
                    :to (m7/space viewbox3)
                    :dur "4s"
                    :fill :freeze}]




         (grid-on 1 1 (* xx 20) (* yy 20) false)



         [:g





          [:path#quad2a {:d (m7/path [20 0 :l 120 0 0 -60 -120 0])
                         :stroke (hsl [3.5 70 70 1])

                         :stroke-width .5
                         :fill (hsl [2.5 70 70 .6])}
           ]


          [:path#quad2b {:d (m7/path [20 -60 :l 120 0 0 -80])
                         :stroke (hsl [1.5 70 70 1])

                         :stroke-width .5
                         :fill (hsl [2.5 70 70 .6])}
           ]


          [:path#quad2c {:d (m7/path [140 0 :l 40 0 0 -80 -40 -60])
                         :stroke (hsl [3.5 70 70 1])

                         :stroke-width .5
                         :fill (hsl [0.5 70 70 .6])}
           ]


          [:path#quad2d {:d (m7/path [20 0 :l 160 0 0 -80 -160 20])
                         :stroke (hsl [1.1 70 70 1])
                         :stroke-width .5
                         :fill (hsl [1.1 70 70 .6])}
           ]



          [:path#quad2 {:d (m7/path [x1 y1 :l (* 8 20) (ve (* 1 20)) (* -2 20) (ve (* 3 20))
                                     ])
                        :stroke (hsl [3.5 70 70 1])

                        :stroke-width .5
                        :fill (hsl [4.1 70 70 .6])}
           ]

          #_[:path#quad2 {:d (m7/path [x1 y1 :l run rise
                                       0 (ve rise)
                                       (ve run) 0])
                          :stroke (hsl [3.5 70 70 1])
                          :stroke-width .5
                          :fill (hsl [4.1 70 70 1])}
             ]





          [:path#tri3 {:d (m7/path [x2 y2 :l (* 16 2 2) (ve (* 2 7 2))
                                    0 (ve (ve (* 2 7 2))) (ve (* 16 2 2)) 0])
                       :stroke (hsl [.5 70 70 1])
                       :stroke-width .5
                       :fill (hsl [1 70 70 1])}
           ]



          [:path#tri3 {:d (m7/path [x2 y2 :l (* 16 2 2) (ve (* 2 7 2))
                                      0 (ve (ve (* 2 7 2))) (ve (* 16 2 2)) 0])
                         :stroke (hsl [.5 70 70 1])
                       :stroke-width .5
                       :fill (hsl [1 70 70 1])}
           ]

          [:path#tri31 {:d (m7/path [x2 y2 :l (* 16 2 2) (ve (* 2 7 2))
                                      0 (ve (ve (* 2 7 2))) (ve (* 16 2 2)) 0])
                         :stroke (hsl [.5 70 70 1])
                         :stroke-width .5
                         :fill (hsl [1 70 70 1])}
             ]









            ]


         [green]
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

           [:g
            [:path {:d (m7/path [0 0 :l 0 (ve (* 2 30 ))
                                 20 0 0 (* 2 30 )
                                 -20 0])
                    :stroke-width 1
                    :fill (hsl [2 70 70 .2])}]



            [:path {:d (m7/path [ (+ 20 32) 0 :l 0 (ve (* 2 37 ))
                                 20 0 0 (* 2 37 )
                                 -20 0])
                    :stroke-width .5
                    :fill (hsl [2 70 70 .15])}]
            (if (and (= slider 0) (= clear false))
              [:g]
              [:g
               [:path#b1 {:d (m7/path [(* -5 20) y11 :L (* 5 20) y22])
                          :stroke-width .5
                          :marker-end (m7/url (name :mb2))
                          :marker-start (m7/url (name :mb2))
                          :stroke (hsl [2 30 30 1])
                          :fill :none}]

               [:path#b2 {:d (m7/path [(* -5 20) y44
                                       :L
                                       (* 5 20) y33])
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

                          :fill (hsl [1 70 70 1])}]])





            [:text {:style {:font-size 5}}

             [:textPath {:href :#tri2
                         :startOffset :20%
                         :dy -20
                         }
              "P"]

             [:textPath {:href :#tri3
                         :startOffset :3%
                         :font-size 3}
              "A"]
             [:textPath {:href :#tri31
                         :startOffset :90%
                         :font-size 3}
              "B"]

             [:textPath {:href :#tri3
                         :startOffset :100%
                         :font-size 3}
              "B"]

             #_[:textPath {:href :#tri2
                           :startOffset (+ 13 (* 2 17))
                           :font-size 3}
                "C"]

             [:textPath {:href :#b1
                         :startOffset "40%"
                         }
              ""]

             [:textPath {:href :#b2
                         :startOffset "40%"}
              ""]

             [:textPath {:href :#green
                         :font-size 7
                         :dy 20
                         :font-family "Roboto Flex"
                         :startOffset "5%"}
              ""]

             [:textPath {:href :#b3
                         :startOffset "40%"}
              (str (first c) " " (last c))



              ]]



            ]

           [:g






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









           #_(map
              (fn [x]
                [:circle {:cx x
                          :cy (ve (+ x 1))
                          :r .05
                          :fill (hsl [5 70 70 1])}])
              (range -200 200 .1))




           (map-indexed
            (fn [i x]
              [:circle {:key (str "cir" i)
                        :cx x
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


           (map-indexed
            (fn [x]
              [:circle {:key (str (random-uuid))
                        :cx x
                        :cy (ve (+ (* x x) (* 2 x ) -3 ))
                        :r .05
                        :fill (hsl [0 70 70 1])}])
            (range -200 200 .1))]
          )
      ]]))




(defn coordinates3 []
  (let [f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
             (comp
              (partial map (partial * n))))
        ax-dx 80
        ax-dy 40
        vb (fn [z]
             (nth [(map #(* 5 %) [-10 -70  83 80])
                   [0 -250  300 300]
                   [-100 -150  300 300]
                   [0 -80  80 100]
                   [0 -25  50 50]
                   [-100 -200  890 200]
                   [40 120  80 80]
                   [0 40  100 100]
                   [75 -175  150 150]
                   [-20 -20  100 100]
                   [-400 -200  800 200]] z))
        viewbox4 (vb 0)
        viewbox3 (vb 1)
        m 30
        d 9.4
        scale2 [[.5 .5] [-0.5 .5]]
        [xx set-xx] (react/useState 2)
        [yy set-yy] (react/useState 3)

        [c ck] (react/useState [0 0])
        p (fn [svg x y]
            (let [p (js/DOMPoint. x y)
                  t (-> svg
                        (.getScreenCTM)
                        (.inverse))
                  xy (-> p
                         (.matrixTransform t))]
              (ck [(fix (/ (-> xy .-x) 2) 1)
                   (fix (* -1 (/ (-> xy .-y) 2)) 1) ])))]
    [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .5])
                      :gap ".2rem"})}
     [:div
      {:style (m7/css
               [[3 1 8 11  :center :center 2.2 :rem ]
                [2 70 90 .4] [] {:gap "1rem"
                                 :z-index 2}])}

      [:div {:on-click (fn [_]
                         (set-xx (- xx 1)))} "⬅"]
      [:div [m7/x  `[= y ~xx]]]
      [:div {:on-click (fn [_]
                         (set-xx (+ xx 1)))} "⇨"]
      [:div {:on-click (fn [_]
                         (set-yy (- yy 1)))} "⬅"]
      [:div [:div [m7/x  `[= x ~yy]]]]
      [:div {:on-click (fn [_]
                         (set-yy (+ yy 1)))} "⇨"]
      ]







     (let [
           a (m7/eq2 `[[1 [1]  [x2]] [-1 [1]  [x1]]])
           b (m7/eq2 `[[1 [1]  [y1]] [1 [1] [y2]]])]
       [:div
        {:style (m7/css
                 [[4 4 1 12  :center :center 1.5 :rem :column]
                  [2 70 90 .4] [] {:gap "1rem"
                                   :z-index 2}])}


        [m7/x `[= [* [1 2] [* BC h]] [* [1 2] BC [:m AB sin B]]]]

        [m7/x `[= [* [1 2] BC h] [* [1 2] BC [:m AC sin C]]]]

        [m7/x `[= [* [1 2] AC [:k h ac]] [* [1 2] AB [:m AC sin A]]]]


        [:div "sine rule"]
        [m7/x `[= [AC
                   [:m sin B]]
                [AB
                 [:m sin C]]]]

        [m7/x `[=
                [* [1 2] BC [:m AC sin C]]
                [* [1 2] AB [:m AC sin A]]]]

        [m7/x `[=
                [*  BC [:m  sin C]]
                [*  AB [:m  sin A]]]]

        [m7/x `[=
                [[*  BC [:m  sin C]]

                 [* [:m  sin A] [:m  sin C]]]
                [[*  AB [:m  sin A]]
                 [* [:m  sin A] [:m  sin C]]]]]

        [m7/x `[=
                [BC
                 [:m  sin A]
                 ]
                [AB
                 [:m  sin C]]]]

        [m7/x `[=
                [BC
                 [:m  sin A]
                 ]
                [AB
                 [:m  sin C]]
                [AC [:m sin B]]]]





        ])







     [:div {:style (m7/css
                    [[2 10 2 23 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"
                                     :z-index 1}])}

      (let [[x1 y1 x2 y2] [(* 3 20) (ve (*  4 20 ))
                           (* 20 -1) (ve (* 8 20 ))]

            [rise run] [(ve (* 2 7 )) (* 16 2)]
            [x3 y3 x4 y4] [(+ x1 run) (+ y1 rise)
                           (+ x2 (* run 2)) (+ y2 (* rise 2))]

            m (/ (- y2 y1) (- x2 x1))
            ma (/ 2 7)
            mb (/ -7 2)

            mc (/ (- 5 7)
                  (- 9 2))


            m1 (/ (- y4 y3) (- x4 x3))
            leq (fn [[m x1 y1]]
                  (fn [x]
                    (+ (* m (- x x1)) y1)))

            maeq (leq [ma (* 2 20) (* 20 3)])

            mbeq (leq [mb (* 2 20) (* 20 7)])


            mceq (leq [mc (* 2 20) (* 20 7)])


            x11 (* -10 20)
            y11 ((leq [m x1 y1]) x11)
            x22 (* 10 20)
            y22 ((leq [m x1 y1]) (* 10 20))
            y33 ((leq [m1 x3 y3]) (* 5 20))
            y44 ((leq [m1 x3 y3]) (* -5 20))]
        [:svg {
                 :style {:height :100%
                         :width :100%}
                 :on-click (fn [e]
                             (p
                              (-> e .-target)
                              (-> e  .-clientX)
                              (-> e  .-clientY) ))
                 :viewBox (m7/space viewbox4)}
         [svg/flames]
         [grad]
         [:animate {:attributeName :viewBox
                    :to (m7/space viewbox3)
                    :dur "4s"
                    :fill :freeze}]




         #_(grid-on 1 1 (* xx 20) (* yy 20) false)


         [green]


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

         [:g





            [:g

             #_[:path#bma1 {:d (m7/path [-200 (ve (maeq -200)) :L 1200 (ve (maeq 1200))])
                          :stroke-width 1
                          :marker-end (m7/url (name :mb2))
                          :marker-start (m7/url (name :mb2))
                          :stroke (hsl [2 30 30 1])
                          :fill :none}]


             [:path#bmb1 {:d (m7/path
                              [(* 2 20) (ve
                                         (mbeq (* 2 20))) :L 65 (ve (mbeq 65))])
                          :stroke-width 1
                          :marker-end (m7/url (name :mb2))
                          :marker-start (m7/url (name :mb2))
                          :stroke (hsl [2 30 30 1])
                          :fill :none}]


             #_[:path#bmc1 {:d
                          (m7/path
                           [-200 (ve
                                  (mceq -200)) :L
                            1200
                            (ve (mceq 1200))])
                          :stroke-width 1
                          :marker-end (m7/url (name :mb2))
                          :marker-start (m7/url (name :mb2))
                          :stroke (hsl [2 30 30 1])
                          :fill :none}]

             [:path {:d (m7/path [(* 2 20) (ve (* 7 20)) :L
                                  (* 20 2) (ve (* 20 3))

                                  (* 9 20) (ve (* 20 5))
                                  (* 2 20) (ve (* 7 20))
                                  ])
                     :stroke-width 1
                     :stroke (hsl [2 30 30 1])
                     :fill (hsl [1 70 70 .4])}]


             #_[:path#b1 {:d (m7/path [x11 y11 :L x22 y22])
                          :stroke-width 1
                          :marker-end (m7/url (name :mb2))
                          :marker-start (m7/url (name :mb2))
                          :stroke (hsl [2 30 30 1])
                          :fill :none}]


             [:circle {:r 2
                       :cy (ve (* 20 3))
                       :cx (* 20 2)
                       :fill (hsl [0.5 80 60 1])}]

             [:text {:r 2
                     :y (ve (* 20 3))
                     :x (* 20 2)
                     :font-size 10}
              "C"]

             [:circle {:r 2
                       :cy (ve (* 20 5))
                       :cx (* 20 9)
                       :fill (hsl [0.5 80 60 1])}]

             [:text {:r 2
                     :y (ve (* 20 5))
                     :x (* 9 20)
                     :font-size 10}
              "B"]


             [:circle {:r 2
                       :cy (ve (* 20 7))
                       :cx (* 20 2)
                       :fill (hsl [0.5 80 60 1])}]

             [:text {:r 2
                     :y (ve (* 20 7))
                     :x (* 20 2)
                     :font-size 10}
              "A"]




             ]





            [:text {:style {:font-size 10}}


             [:textPath {:href :#bmb1
                         :startOffset "40%"
                         }
              "h"]



             [:textPath {:href :#b3
                         :startOffset "40%"}
              (str (first c) " " (last c))



              ]]

            ]


         ])
      ]]))





(defn stat3 []
  (let [f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
             (comp
              (partial map (partial * n))))
        ax-dx 80
        ax-dy 40
        vb (fn [z]
             (nth [(map #(* 5 %) [-10 -70  83 80])
                   [0 -250  300 300]
                   [-100 -150  300 300]
                   [0 -80  80 100]
                   [0 -25  50 50]
                   [-100 -200  890 200]
                   [40 120  80 80]
                   [0 40  100 100]
                   [75 -175  150 150]
                   [-20 -20  100 100]
                   [-400 -200  800 200]] z))
        viewbox4 (vb 0)
        viewbox3 (vb 1)
        m 30
        d 9.4
        scale2 [[.5 .5] [-0.5 .5]]
        [xx set-xx] (react/useState 2)
        [yy set-yy] (react/useState 3)

        [c ck] (react/useState [0 0])
        p (fn [svg x y]
            (let [p (js/DOMPoint. x y)
                  t (-> svg
                        (.getScreenCTM)
                        (.inverse))
                  xy (-> p
                         (.matrixTransform t))]
              (ck [(fix (/ (-> xy .-x) 2) 1)
                   (fix (* -1 (/ (-> xy .-y) 2)) 1) ])))]
    [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .5])
                      :gap ".2rem"})}
     [:div
      {:style (m7/css
               [[3 1 8 11  :center :center 2.2 :rem ]
                [2 70 90 .4] [] {:gap "1rem"
                                 :z-index 2}])}

      [:div {:on-click (fn [_]
                         (set-xx (- xx 1)))} "⬅"]
      [:div [m7/x  `[= y ~xx]]]
      [:div {:on-click (fn [_]
                         (set-xx (+ xx 1)))} "⇨"]
      [:div {:on-click (fn [_]
                         (set-yy (- yy 1)))} "⬅"]
      [:div [:div [m7/x  `[= x ~yy]]]]
      [:div {:on-click (fn [_]
                         (set-yy (+ yy 1)))} "⇨"]
      ]








     [:div
      {:style (m7/css
               [[4 4 1 12  :center :center 2.5 :rem :column]
                [2 70 90 .4] [] {:gap "1rem"
                                 :z-index 2}])}






      ]







     [:div {:style (m7/css
                    [[2 10 2 23 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"
                                     :z-index 1}])}

      (let [[x1 y1 x2 y2] [(* 3 20) (ve (*  4 20 ))
                           (* 20 -1) (ve (* 8 20 ))]

            [rise run] [(ve (* 2 7 )) (* 16 2)]
            [x3 y3 x4 y4] [(+ x1 run) (+ y1 rise)
                           (+ x2 (* run 2)) (+ y2 (* rise 2))]

            m (/ (- y2 y1) (- x2 x1))
            ma (/ 2 7)
            mb (/ -7 2)

            mc (/ (- 5 7)
                  (- 9 2))


            m1 (/ (- y4 y3) (- x4 x3))
            leq (fn [[m x1 y1]]
                  (fn [x]
                    (+ (* m (- x x1)) y1)))

            maeq (leq [ma (* 2 20) (* 20 3)])

            mbeq (leq [mb (* 2 20) (* 20 7)])


            mceq (leq [mc (* 2 20) (* 20 7)])


            x11 (* -10 20)
            y11 ((leq [m x1 y1]) x11)
            x22 (* 10 20)
            y22 ((leq [m x1 y1]) (* 10 20))
            y33 ((leq [m1 x3 y3]) (* 5 20))
            y44 ((leq [m1 x3 y3]) (* -5 20))]
        [:svg {
                 :style {:height :100%
                         :width :100%}
                 :on-click (fn [e]
                             (p
                              (-> e .-target)
                              (-> e  .-clientX)
                              (-> e  .-clientY) ))
                 :viewBox (m7/space viewbox4)}
         [svg/flames]
         [grad]
         [:animate {:attributeName :viewBox
                    :to (m7/space viewbox3)
                    :dur "4s"
                    :fill :freeze}]




         (grid-on 1 1 (* xx 20) (* yy 20) false)


         [green]


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

         [:g





            [:g

             [:path#bma1 {:d (m7/path [-200 (ve (maeq -200)) :L 1200 (ve (maeq 1200))])
                          :stroke-width 1
                          :marker-end (m7/url (name :mb2))
                          :marker-start (m7/url (name :mb2))
                          :stroke (hsl [2 30 30 1])
                          :fill :none}]


             [:circle {:r 2
                       :cy (ve (* 20 3))
                       :cx (* 20 2)
                       :fill (hsl [0.5 80 60 1])}]

             [:text {:r 2
                     :y (ve (* 20 3))
                     :x (* 20 2)
                     :font-size 10}
              "P(2,3)"]

             ]







            ]


         ])
      ]]))








(defn cd4 []
  (let [f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
             (comp
              (partial map (partial * n))))
        ax-dx 80
        ax-dy 40
        vb (fn [z]
             (nth [(map #(* 5 %) [-10 -70  83 80])
                   [0 -250  300 300]
                   [-100 -150  300 300]
                   [0 -80  80 100]
                   [0 -25  50 50]
                   [-100 -200  890 200]
                   [40 120  80 80]
                   [0 40  100 100]
                   [75 -175  150 150]
                   [-20 -20  100 100]
                   [-400 -200  800 200]] z))
        viewbox4 (vb 0)
        viewbox3 (vb 1)
        m 30
        d 9.4
        scale2 [[.5 .5] [-0.5 .5]]
        [xx set-xx] (react/useState 2)
        [yy set-yy] (react/useState 3)

        [c ck] (react/useState [0 0])
        p (fn [svg x y]
            (let [p (js/DOMPoint. x y)
                  t (-> svg
                        (.getScreenCTM)
                        (.inverse))
                  xy (-> p
                         (.matrixTransform t))]
              (ck [(fix (/ (-> xy .-x) 2) 1)
                   (fix (* -1 (/ (-> xy .-y) 2)) 1) ])))]
    [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .5])
                      :gap ".2rem"})}
     [:div
      {:style (m7/css
               [[3 1 8 11  :center :center 2.2 :rem ]
                [2 70 90 .4] [] {:gap "1rem"
                                 :z-index 2}])}

      [:div {:on-click (fn [_]
                         (set-xx (- xx 1)))} "⬅"]
      [:div [m7/x  `[= y ~xx]]]
      [:div {:on-click (fn [_]
                         (set-xx (+ xx 1)))} "⇨"]
      [:div {:on-click (fn [_]
                         (set-yy (- yy 1)))} "⬅"]
      [:div [:div [m7/x  `[= x ~yy]]]]
      [:div {:on-click (fn [_]
                         (set-yy (+ yy 1)))} "⇨"]
      ]



     [:div {:style (m7/css
                    [[4 4 1 12  :center :center 1.5 :rem :column]
                     [2 70 90 .4] [] {:gap "1rem"
                                      :z-index 2}])}

      [m7/x `[= [+ [:m 2 x] [:m 3 y]] 7]]

      [m7/x `[= [+ [:m -2 x]  7]  [:m 3 y]]]

      [m7/x `[= [+ [:m [-2 3] x]  [7 3]] y]]




      [:div {:style {:height "4px"
                     :width "100%"
                     :background-color (hsl [1 30 30 1])}
             }]

      [m7/x `[= [- [:m 7 x] y] 13]]

      [m7/x `[= [- [:m 7 x] 13] y]]


      #_[m7/x `[= [+ [:m 23 x] 0] 46]]


      #_[m7/x `[= [+ [:m 2 x] [:m 3 y]] 7]]
      #_[m7/x `[= [+ [* 2 2] [:m 3 y]] 7]]
      #_[m7/x `[= y [- [:m 7 x] 13]]]

      #_[:div "(x,y)=(2,1)"]



      #_[m7/x `[= [+ [:m 2 x] [:m 3 [:b [- [:m 7 x] 13]]]] 7]]
      #_[m7/x `[= y [:p 10  x]] ]


      #_[m7/x `[= [:m f [:b 0]]
              0]
       ]

      #_[m7/x `[= [:m f [:b 0]] 1]]
      #_[m7/x `[= [:m f [:b x]]
              [:m x [:m f [:b [- x 1]]] ]]
       ]

      ]





     #_(let [
           a (m7/eq2 `[[1 [1]  [x2]] [-1 [1]  [x1]]])
           b (m7/eq2 `[[1 [1]  [y1]] [1 [1] [y2]]])]
         [:div
          {:style (m7/css
                   [[4 4 1 12  :center :center 1.5 :rem :column]
                    [2 70 90 .4] [] {:gap "1rem"
                                     :z-index 2}])}



          [m7/x `[= AD [* AC [:m sin [:p 30 *]]]]]

          [m7/x `[=  [:m AB cos [:p 30 *]]  [* AC [1 2]]]]

          [m7/x `[=  [* 20 [[:sq 3] 2]]  [* AC [1 2]]]]

          [m7/x `[= [:m  20 [:sq 3] ]
                  AC]]

          [m7/x `[= DC [:m AC cos [:p 30 *]]]]

          [m7/x `[= DC [:m AC  [[:sq 3] 2]]]]

          [m7/x `[=  DC [* [:m  20 [:sq 3] ]  [[:sq 3] 2]]]]


          [m7/x `[= BC [+ BD DC] [+ [[* 20 3] 2] 10]]]

          [m7/x `[= BC 40]]

          [m7/x `[* 20 10 [:sq 3]]]

          [m7/x `[* 300 [:sq 3]]]







          #_[m7/x `[= [* [1 2] [* BC h]] [* [1 2] BC [:m AB sin B]]]]



          #_[m7/x `[=
                    [BC
                     [:m  sin A]
                     ]
                    [AB
                     [:m  sin C]]
                    [AC [:m sin B]]]]





          ])







     [:div {:style (m7/css
                    [[2 10 2 23 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"
                                     :z-index 1}])}

      (let [[x1 y1 x2 y2] [(* 3 20) (ve (*  4 20 ))
                           (* 20 -1) (ve (* 8 20 ))]

            [rise run] [(ve (* 2 7 )) (* 16 2)]
            [x3 y3 x4 y4] [(+ x1 run) (+ y1 rise)
                           (+ x2 (* run 2)) (+ y2 (* rise 2))]

            m (/ (- y2 y1) (- x2 x1))
            ma (/ -2 3)
            mb 7

            mc (/ (- 5 7)
                  (- 9 2))


            m1 (/ (- y4 y3) (- x4 x3))
            leq (fn [[m x1 y1]]
                  (fn [x]
                    (+ (* m (- x x1)) y1)))

            maeq (leq [ma (* 2 0) (* 20 (/ 7 3))])

            mbeq (leq [mb (* 20 (/ 13 7)) 0])


            mceq (leq [mc (* 2 20) (* 20 7)])


            x11 (* -10 20)
            y11 ((leq [m x1 y1]) x11)
            x22 (* 10 20)
            y22 ((leq [m x1 y1]) (* 10 20))
            y33 ((leq [m1 x3 y3]) (* 5 20))
            y44 ((leq [m1 x3 y3]) (* -5 20))]
        [:svg {
                 :style {:height :100%
                         :width :100%}
                 :on-click (fn [e]
                             (p
                              (-> e .-target)
                              (-> e  .-clientX)
                              (-> e  .-clientY) ))
                 :viewBox (m7/space viewbox4)}
         [svg/flames]
         [grad]
         [:animate {:attributeName :viewBox
                    :to (m7/space viewbox3)
                    :dur "4s"
                    :fill :freeze}]




         (grid-on 1 1 (* xx 20) (* yy 20) false)


         [green]


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

         [:g
          #_[:g
           (let [a (* 5 20)
                 b (* 2 20)]
             [:g
              [:path {:d (m7/path [0 0 :l -50 0 50 0  a 0 0 (ve b) (ve a) 0 0 b

                                   0 (ve (* 3 b))

                                   a (* 3 b)

                                   ])
                      :transform (m7/tranfrom [[:skewX 0]])
                      :stroke-width 1
                      :marker-end (m7/url (name :mb2))
                      :marker-start (m7/url (name :mb2))
                      :stroke (hsl [2 30 30 1])
                      :fill :none}]


              [:circle {:r 2
                        :cy (ve (* 3 b))
                        :cx (js/Math.cos (+ (/ js/Math.PI 2)
                                            (/ js/Math.PI 6)))
                        :fill (hsl [0.5 80 60 1])}]
              ])]








          [:g

           [:path#bma1 {:d (m7/path [-200 (ve (maeq -200)) :L 1200 (ve (maeq 1200))])
                        :stroke-width 1
                        :marker-end (m7/url (name :mb2))
                        :marker-start (m7/url (name :mb2))
                        :stroke (hsl [2 30 30 1])
                        :fill :none}]

           [:path#bma2 {:d (m7/path [-200 (ve (mbeq -200)) :L 1200 (ve (mbeq 1200))])
                        :stroke-width 1
                        :marker-end (m7/url (name :mb2))
                        :marker-start (m7/url (name :mb2))
                        :stroke (hsl [2 30 30 1])
                        :fill :none}]


           #_[:path#bmc1 {:d
                          (m7/path
                           [-200 (ve
                                  (mceq -200)) :L
                            1200
                            (ve (mceq 1200))])
                          :stroke-width 1
                          :marker-end (m7/url (name :mb2))
                          :marker-start (m7/url (name :mb2))
                          :stroke (hsl [2 30 30 1])
                          :fill :none}]

             #_[:path {:d (m7/path [(* 2 20) (ve (* 7 20)) :L
                                    (* 20 2) (ve (* 20 3))

                                    (* 9 20) (ve (* 20 5))
                                    (* 2 20) (ve (* 7 20))
                                    ])
                       :stroke-width 1
                       :stroke (hsl [2 30 30 1])
                       :fill (hsl [1 70 70 .4])}]


             #_[:path {:d (m7/path [(* 2 20) (ve (* 7 20)) :L
                                  (* 20 2) (ve (* 20 3))

                                  (* 9 20) (ve (* 20 5))
                                  (* 2 20) (ve (* 7 20))
                                  ])
                     :stroke-width 1
                     :stroke (hsl [2 30 30 1])
                     :fill (hsl [1 70 70 .4])}]



             #_[:path#b1 {:d (m7/path [x11 y11 :L x22 y22])
                          :stroke-width 1
                          :marker-end (m7/url (name :mb2))
                          :marker-start (m7/url (name :mb2))
                          :stroke (hsl [2 30 30 1])
                          :fill :none}]


           #_[:circle {:r 2
                       :cy (ve (* 20 3))
                       :cx (* 20 2)
                       :fill (hsl [0.5 80 60 1])}]

             #_[:text {:r 2
                     :y (ve (* 20 3))
                     :x (* 20 2)
                     :font-size 10}
              "C"]

             #_[:circle {:r 2
                       :cy (ve (* 20 5))
                       :cx (* 20 9)
                       :fill (hsl [0.5 80 60 1])}]

             #_[:text {:r 2
                     :y (ve (* 20 5))
                     :x (* 9 20)
                     :font-size 10}
              "B"]


             #_[:circle {:r 2
                       :cy (ve (* 20 7))
                       :cx (* 20 2)
                       :fill (hsl [0.5 80 60 1])}]

             #_[:text {:r 2
                     :y (ve (* 20 7))
                     :x (* 20 2)
                     :font-size 10}
              "A"]
           ]

          #_[:text {:style {:font-size 10}}


             [:textPath {:href :#bmb1
                         :startOffset "40%"
                         }
              "h"]



               [:textPath {:href :#b3
                         :startOffset "40%"}
              (str (first c) " " (last c))



                ]]

            ]


         ])
      ]]))




(defn cd5 []
  (let [f (fn [n] (/ 1 n))
        tt 'θ
        dx [1 0  0 1 -1  0 0 -1 ]
        sq (fn [n]
             (comp
              (partial map (partial * n))))
        ax-dx 80
        ax-dy 40
        vb (fn [z]
             (nth [(map #(* 5 %) [-10 -70  83 80])
                   [0 -250  300 300]
                   [-100 -150  300 300]
                   [0 -80  80 100]
                   [0 -25  50 50]
                   [-100 -200  890 200]
                   [40 120  80 80]
                   [0 40  100 100]
                   [75 -175  150 150]
                   [-20 -20  100 100]
                   [-400 -200  800 200]] z))
        viewbox4 (vb 0)
        viewbox3 (vb 1)
        m 30
        d 9.4
        scale2 [[.5 .5] [-0.5 .5]]
        [xx set-xx] (react/useState 2)
        [yy set-yy] (react/useState 3)

        [c ck] (react/useState [0 0])
        p (fn [svg x y]
            (let [p (js/DOMPoint. x y)
                  t (-> svg
                        (.getScreenCTM)
                        (.inverse))
                  xy (-> p
                         (.matrixTransform t))]
              (ck [(fix (/ (-> xy .-x) 2) 1)
                   (fix (* -1 (/ (-> xy .-y) 2)) 1) ])))]
    [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .5])
                      :gap ".2rem"})}
     [:div
      {:style (m7/css
               [[3 1 8 11  :center :center 2.2 :rem ]
                [2 70 90 .4] [] {:gap "1rem"
                                 :z-index 2}])}

      [:div {:on-click (fn [_]
                         (set-xx (- xx 1)))} "⬅"]
      [:div [m7/x  `[= y ~xx]]]
      [:div {:on-click (fn [_]
                         (set-xx (+ xx 1)))} "⇨"]
      [:div {:on-click (fn [_]
                         (set-yy (- yy 1)))} "⬅"]
      [:div [:div [m7/x  `[= x ~yy]]]]
      [:div {:on-click (fn [_]
                         (set-yy (+ yy 1)))} "⇨"]
      ]



     [:div {:style (m7/css
                    [[4 4 1 12  :center :center 1.5 :rem :column]
                     [2 70 90 .4] [] {:gap "1rem"
                                      :z-index 2}])}

      [m7/x `[= [+  [:m 2 x ] y] 5]]


      #_[m7/x `[= [+  [:m 2 x y] [:p y 2]] [:m 5 y]]]

      [m7/x `[= y [+ [- [:m 2 x]]  5] ] ]


      [m7/x `[= [:p y 2] [:p [:b [- 5 [:m 2 x]  ]] 2] ] ]


      [m7/x `[= [:p y 2] [+ [- 25 [:m 20 x]] [:m 4 [:p  x 2]]]] ]


      #_[m7/x `[= [+ [- [:m 2 x]]  [* [- 2] [5 [- 2]]]]  y ] ]


      #_[m7/x `[= [:m [- 2] [:b [-  x  [5 2]]]]  y ] ]




      [:div {:style {:height "2px"
                       :width "80%"
                       :background-color (hsl [1 30 30 1])}
             }]


      [m7/x `[= [+ [:p x 2] [:p y 2]] 5 ]]

      [m7/x `[= [+ [:p x 2] [+ [- 25 [:m 20 x]] [:m 4 [:p  x 2]]]] 5 ]]

      [m7/x `[= [+ [- [* 5 4] [:m [* 5 4] x]] [:m 5 [:p  x 2]]] 0 ]]

      [m7/x `[= [+ [- [:p 2 2] [:m  [* 2 2] x]] [:p  x 2]] 0 ]]

      [m7/x `[=  [:p [:b [- 2  x]] 2]
              0 ]]

      [m7/x `[=  [:sq [:p [:b [- 2  x]] 2]]
              [:sq 0] ]]

      [m7/x `[= [- x 2] 0 ]]



      #_[m7/x `[= [+ [:p x 2] [:p y 2]] [+  [:m 2 x ] y] ]]

      #_[m7/x `[= [+ [:p x 2] [:p y 2]] [:p [:b [:sq 5]] 2] ]]






      ]






     [:div {:style (m7/css
                    [[2 10 2 23 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"
                                     :z-index 1}])}

      (let [ ma -2
            mb 7
            leq (fn [[m x1 y1]]
                  (fn [x]
                    (+ (* m (- x x1)) y1)))

            maeq (leq [ma (* 20 (/ 5 2))  (* 20 0)])

            mbeq (leq [mb (* 20 (/ 13 7)) 0])


            ]
        [:svg {:style {:height :100%
                       :width :100%}
               :on-click (fn [e]
                           (p
                            (-> e .-target)
                            (-> e  .-clientX)
                            (-> e  .-clientY)))
               :viewBox (m7/space viewbox4)}
         [svg/flames]
         [grad]
         [:animate {:attributeName :viewBox
                    :to (m7/space viewbox3)
                    :dur "4s"
                    :fill :freeze}]




         (grid-on 1 1 (* xx 20) (* yy 20) false)


         [green]


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

         [:g
          [:g

           [:path#bma1 {:d (m7/path [-200 (ve (maeq -200)) :L 1200 (ve (maeq 1200))])
                        :stroke-width .3
                        :marker-end (m7/url (name :mb2))
                        :marker-start (m7/url (name :mb2))
                        :stroke (hsl [2 30 30 1])
                        :fill :none}]

           #_[:path#bma2 {:d (m7/path [-200 (ve (mbeq -200)) :L 1200 (ve (mbeq 1200))])
                        :stroke-width 1
                        :marker-end (m7/url (name :mb2))
                        :marker-start (m7/url (name :mb2))
                        :stroke (hsl [2 30 30 1])
                        :fill :none}]




           [:circle {:r (* 20 (js/Math.sqrt 5))
                     :cy 0
                     :cx 0
                     :stroke-width .3
                     :fill :none
                     :stroke (hsl [0.5 80 60 1])}]

             #_[:text {:r 2
                     :y (ve (* 20 5))
                     :x (* 9 20)
                     :font-size 10}
              "B"]







             ]





          #_[:text {:style {:font-size 10}}


             [:textPath {:href :#bmb1
                         :startOffset "40%"
                         }
              "h"]



             ]]])]]))
