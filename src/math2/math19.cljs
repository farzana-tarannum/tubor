(ns math2.math19
  (:require
   [math2.file :as file]
   [react]
   [math2.bdmap :as bdmap]
   [clojure.string :as str]
   [clojure.walk :as w]
   [defun.core :refer [defun fun]]
   [math2.solution :as sol]
   [math2.solve :as sl]
   [math2.math7 :as m7 :refer
    [grid hsl css space size path ve sec]]))

(defn grid-on [X Y ax-dx ax-dy frac stroke-width font-size]
  (let []
     [:g

      (map-indexed
       (fn [i x]
         [:g {:key (str "path-indexed333" i)}

          [:path {:d (path [ (* 20 x)  0 :l 0 -800])

                  :stroke (hsl [(if (= x -2)  5 0) 70 70 1])
                  :stroke-width stroke-width
                  :fill :none}
           ]


          [:path {:d (path [ (* 20 x)  0 :l 0 800])

                  :stroke (hsl [0 70 70 1])
                  :stroke-width stroke-width
                  :fill :none}
           ]

          [:text {:x (* 20 x)
                  :fill (hsl [0 40 20 1])
                  :y font-size
                  :font-size font-size}
           (if frac
             (.toFixed (* x X) 1)
             (* x X))
           ]])
       (range -40 40))



      (map-indexed
       (fn [i y]
         [:g {:key (str "grid-onpathysdfsdd" i)}
          [:path {:d (path [0 (ve (* 20 y))   :l 1200 0])

                  :stroke (hsl [1 70 70 1])
                  :stroke-width stroke-width
                  :fill :none}
           ]

          [:text {:x font-size
                  :dy -1
                  :fill (hsl [0 40 20 1])
                  :y (ve (* 20 y))
                  :font-size font-size}
           (if frac
             (.toFixed (* y Y) 1)
             (* y Y))
           ]


          [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                  :stroke (hsl [1 70 70 1])
                  :stroke-width stroke-width
                  :fill :none}
           ]

          ])
       (range -40 40))



      (map-indexed
       (fn [index i]
         [:path {:key (str "grid-onadfadsf" index)
                 :d (path [-400 (ve (+ ax-dx (* i 2))) :l 1200 0])
                 :stroke (hsl [4 70 70 .2])
                 :stroke-width .1
                 :fill :none}
          ] )
       (range 0 11))


      (map-indexed
       (fn [index i]
         [:path {:key (str "grid-onsdfasd2" index)
                 :d (path [(+ ax-dy (* i 2)) -400 :l 0 1200 ])

                 :stroke (hsl [4 70 70 .2])
                 :stroke-width .1
                 :fill :none}
          ] )
       (range 0 11))



      ]))

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
           :style {:font-size "0.2rem"}
           }
    [:textPath {:href :#pp2
                :startOffset 1200}

     eqs
     ]
    ]])

(def animate-fn
  (fn [ref style]
    (if (and ref (-> ref .-current))
      (-> ref .-current
          (.animate style (clj->js {:duration 2000 :iterations 1}))))))



(defn home-work19 []
  (let [[slider set-slider] (react/useState false)
        [pos set-pos] (react/useState false)
        animate-ref (react/useRef)
        animate-ref2 (react/useRef)
        font-ref (react/useRef)
        style1 (clj->js
                [{:background (hsl [.5 70 70 .1])
                  :transform (m7/tranfrom [[:rotate "10deg"]])}
                 {:background (hsl [.9 70 70 .7])
                  :transform (m7/tranfrom [[:rotate "-10deg"]])}
                 {:background (hsl [2 70 70 .9])
                  :transform (m7/tranfrom [[:scale .9]])
                  :offset (/ 9 14)}
                 {:background (hsl [3.5 70 70 .7])
                  :transform (m7/tranfrom [[:scale 1]])}])
        _ (react/useEffect (fn []
                             (if (and animate-ref (-> animate-ref .-current))
                               (-> animate-ref
                                   .-current
                                   (.animate
                                    style1
                                    (clj->js
                                     {:duration 2000
                                      :iterations 1})
                                    )))
                             (js/console.log "1")))

        _ (react/useEffect
           (fn []
             (if (and animate-ref2 (-> animate-ref2 .-current))
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
        [point set-point] (react/useState true)
        [btn set-btn] (react/useState 100)
        [xx set-xx] (react/useState 2)
        [yy set-yy] (react/useState 3)
        [zm set-zm] (react/useState [])
        [q2 set-q2] (react/useState [6 1 1])
        [center set-center] (react/useState true)
        [zoom-in set-zoom-in] (react/useState false)
        [zoom-out set-zoom-out] (react/useState false)


        ]
    (let [fix true
          scales [1 -1]
          [a m d :as amd]  q2
          d2 (/ (* d d) a)
          vbr [[(ve (- (* 3 3 20 .5) (* m 20))) (ve (- (* 3 20) (* 20 d2))) (* 3 3 20) (* 3 20) ]
               [(ve (* 3 12 20 .5)) (ve (- (* 12 20) (* 20 d2))) (* 3 12 20) (* 12 20) ]]
          gd [1 1 0 0 false .1 (if point 1  4)]
          r (if point .2 (nth `[~@(take 5 (repeat 2)) ~@(take 15 (repeat 1)) ] a))
          viewbox2 (if center
                     (let [[x y w h] (if point (first vbr) (second vbr))]
                       [(ve (/ w 2)) (ve (/ h 2)) w h])


                     (if point (first vbr) (second vbr)))
          viewbox (if point (second vbr) (first vbr))

          rn (apply range (map #(* % a) [-6 6 1]))
          rnn (apply range (map #(* % a)  [-6 6 (if point .01 .1 )]))
          main-eq ((fun ([[1 0 0]]
                         [m7/x `[= y [:p x 2]]])
                        ([[1 m d]]
                         [m7/x `[= y
                                 [- [:p [:b [-  x ~m]] 2]
                                  [:p ~d 2]]]])
                        ([[a 0 0]]
                         [m7/x `[= y [[:p x 2] ~a]]])
                        ([[a m 0]]
                         [m7/x `[= y
                                 [[:p [:b [-  x ~m]] 2] ~a]]])
                        ([[a m d]]
                         [m7/x `[= y
                                 [[- [:p [:b [-  x ~m]] 2]
                                   [:p ~d 2]] ~a]]]))
                   amd)
          down false
          mid true
          curve false
          shape false
          [mx my :as mark] [0 0]
          eqsprev (fn [x]
                    (if fix
                      (.toFixed (* (/ 1 a) (- (* (- x m) (- x m)) (* d d))) 1)
                      (* 1 (- (* (- x m) (- x m)) (* d d)))))
          eqs (fn [x]
                (if fix
                  (.toFixed (* (/ 1 a) (- (* (- x m) (- x m)) (* d d))) 1)
                  (* 1 (- (* (- x m) (- x m)) (* d d)))))

          eqsprev2 (fn [x]
                 (ve (* (/ 20 a) (- (* (- x m) (- x m)) (* d d)))))

          eqs2 (fn [x]
                 (ve (* (/ 20 a) (- (* (- x m) (- x m) (- x m)) (* d d)))))

          ]
      [:div {
             :style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 24 (repeat [8 :vh]))])
                     {:background (hsl [.5 65 55 1])
                      :gap              ".2rem"})}


       (map
        (fn [n d]
          [:div {:on-mouse-enter (fn [e] (set-btn n))
                    :on-mouse-leave (fn [e] (set-btn 100))
                    :style (m7/css
                            [[12 1 (+ 1 (* n 1)) 1  :center :center  2 :rem :column]
                             [(if (= btn n) 1 .5) 70 70  .8] []
                             {:font-size (m7/np
                                          [(if (= btn n) 2 1.8 ) :rem])
                              :font-family "Roboto Flex"
                              :gap (m7/np [1 :rem])
                              :border-radius "50%"
                              :color (hsl
                                      (if (= btn n)
                                        [1 30 30 1]
                                        [1 70 60 .7]))
                              :z-index 4
                              :cursor :grab}
                             ])}

           d])
        (range 0 40)

        [
         [:div {:on-click (fn [e] (set-zoom-in (not zoom-in)))}
          (if zoom-in "+" "-")]

         [:div
          (.toFixed (first viewbox2 ) 1) ]
         [:div {:on-click (fn [e] (set-zoom-out (not zoom-out)))}
          (if zoom-out "+" "-")]

         [:div (.toFixed (second viewbox2 )  1)]

         [:div {:on-click (fn [e] (set-zoom-out (not zoom-out)))}
          (if zoom-out "+" "-")]
         [:div
          (.toFixed (nth viewbox2 2) 1)]
         [:div {:on-click (fn [e] (set-zoom-out (not zoom-out)))}
          (if zoom-out "+" "-")]
         [:div (.toFixed
                (nth viewbox2 3) 1)]
         [:div {:on-click (fn [_]
                            (set-xx (- xx 1)))} "⬅"]
         [:div xx]
         [:div {:on-click (fn [_]
                            (set-xx (+ xx 1)))} "⇨"]
         [:div {:on-click (fn [_]
                            (set-yy (- yy 1)))} "⬅"]
         [:div yy]
         [:div {:on-click (fn [_]
                            (set-yy (+ yy 1)))} "⇨"]


         [:div  {:on-click (fn [] (set-center (not center)))} (.toFixed (second viewbox2) 1)]



         [:div {:on-click (fn [_]
                            (set-q2 (update q2 2 dec)))} "⬅"]
         [:div (get q2 2)]
         [:div {:on-click (fn [_]
                            (set-q2 (update q2 2 inc)))} "⇨"]




         [:div {:on-click (fn [_]
                            (set-q2 (update q2 1 dec)))} "⬅"]
         [:div (get q2 1)]
         [:div {:on-click (fn [_]
                            (set-q2 (update q2 1 inc)))} "\u279C"]




         [:div {:on-click (fn [_]
                            (set-q2 (update q2 0 dec)))} "⬅"]
         [:div "⇨" (get q2 0)]
         [:div {:on-click (fn [_]
                            (set-q2 (update q2 0 inc)))} "⇨"]



         ]
        )


       (map
        (fn [n d]
          [:div {:style (m7/css
                         [[1 1 (+ 2 (* n 1)) 1  :center :center  2 :rem :column]
                          [(+ (/ n 10) .8) 70 (+ 50 (* 1 n))  .4] [] {:gap ".1rem"
                                                               :z-index 4}])}

           d])
        (range 0 24)
        (into [[:div {:style {:font-size "3rem"}}  "x"]]
              rn))

       (map
        (fn [n d]
          [:div {:style (m7/css
                         [[2 1 (+ 2 (* n 1)) 1  :center :center  2 :rem :column]
                          [(+ (/ n 10) .8) 70 (+ 50 (* 1 n))  .4]
                          [] {:gap ".1rem"
                                                               :z-index 4}])}

           d])
        (range 0 24)
        (into [[:div {:style {:cursor :pointer
                              :color (hsl [3 90 90 1])
                              :font-size "4rem"}
                      :on-click (fn [e]
                                  (do
                                    (set-slider (not slider))
                                    (js/console.log "hello")
                                    ))}
                #_[m7/m '[= y [:p x 2]]]
                (name :y)
                ]]
              (map (fn [x]
                     [:div {:style {:font-size "1.2rem"}}
                      (eqs x)]) rn)))





       (if slider
         [:div {:ref animate-ref
                :style
                (m7/css
                 [[4 6 3 7 :center :center  2 :rem :column] [3.5 70 (+ 50 (* 5 5))  .7] []
                  {:gap     ".1rem"
                   :z-index 10}])}
          [m7/m ['= 'y [:m 'a [:b ['- [:p [:b ['- 'x 'm]] 2] [:p 'd 2]]]]]]

          [m7/m ['= [:m 8 [:b ['- [:p [:b ['- 'x m]] 2] [:p d 2]]]] 0 ]]

          [m7/m ['= [[:m 8 [:b ['- [:p [:b ['- 'x m]] 2] [:p d 2]]]] 8] [0 8] ]]

          [m7/m ['= ['- [:p [:b ['- 'x m]] 2] [:p d 2]] 0 ]]

          [:div  {:ref font-ref
                  :style {:font-size "2rem"
                          }}
             [m7/m ['= [:m [:b ['- 'x m d]]  [:b ['+ ['- 'x m] d]]] 0 ]]]

          [m7/m ['= ['+ ['- 'x m] d] 0]]
          [m7/m ['= ['- 'x m d] 0]]
          ]

         [:div {:on-click (fn [_]
                            (set-point (not point)))
                :style
                (m7/css
                 [[1 2 16 7 :center :center  3 :rem :column]
                  [3.5 70 75 .5]
                  []
                  {:gap     ".1rem"
                   :z-index 10}])}
          main-eq]
         )
       (if slider
         (let [a (m7/eq2 `[[1 [1] [x]] [-1 [1] [m]]])
               d (m7/eq2 `[-1 [2] [d]])
               a2 [[1 [2] [[:b (sl/symeq a)]]]
                   d]
               d1 (sl/mkeq1a d)
               d2 (sl/mkeq2a d1)
               bb 'b
               cc 'c
               ee (m7/eq2 [[1 [2] ['x]]
                           [1 [1 1] [bb 'x]]
                           [1 [1] [cc]]])
               eee (m7/eq2 [[1 [2] ['x]]
                            [bb [1] ['x]]
                            1])


               ee1 (m7/eq2 `[[1 [ 2] [ x]]
                             [1 [1 1] [[b a] x]]
                             [1 [1] [[c a]]]])
               ek1 (conj (vec (sl/lawdr2 a a)) d1)
               ek2 (rest (rest ek1))
               ]
           [:div {:ref animate-ref2
                  :style
                  (m7/css
                   [[4 7 14 12
                     :flex-start :flex-start  2 :rem :column]
                    [1 70 (+ 50 (* 5 5))  .7]
                    []
                    {:padding-left "100px"
                     :gap ".1rem"
                     :z-index 10}])}
            (m7/x (sl/e=  [:m 'a [:b (sl/symeq a2)]]) )
            (m7/x (sl/e= [:m 'a [:b (sl/symeq2 ek1)]]))
            (m7/x (sl/e= [:m 'a [:b (sl/symeq ee1)]]))
            (m7/x ['= [cc 'a]  (sl/symeq2 ek2)])
            (m7/x `[= [~bb a] [- [:m 2 m]]])








            ])
           [:div ""])


       [:div {:style (m7/css
                      [[2 10 1 25 :center :center 3 :rem]
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
                                                [:scale [.6 .6]]])
                    :fill         (m7/hsl [-0.5 70 70 .2])}]]

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
                     :stop-color (hsl [.5 63 57 .3])}
             [:animate {:attributeName :offset
                        :from .2
                        :to .5
                        :dur (m7/not-space [3 "s"])
                        :repeatCount :indefinite}]]
            [:stop  {:offset .5
                     :stop-color (hsl [1 70 70 .3])}
             [:animate {:attributeName :offset
                        :from .3
                        :to 1
                        :dur (m7/not-space [3 "s"])
                        :repeatCount :indefinite}]]
            ]



           (apply grid-on gd)



           (if (not curve)
             (map (fn [x y]
                    [:circle {
                              :cx    x
                              :cy    y
                              :r     r
                              :fill (if point
                                      (hsl [0 70 70 .8])
                                      (hsl [2 70 70 .8]))
                              }]
                    )

                  (map (fn [x]
                         (* 20 x)) rnn)
                  (map eqs2 rnn)))


           (map
              (fn [x]
                [:g



                 (if shape
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

                           :transform
                           (m7/tranfrom
                            [
                             [:translate [(* m 20)
                                          (* 2 d d)]]
                             [:scale [x 1]]
                             ])
                           :stroke (hsl [0 70 70 1])
                           :stroke-width .1
                           :fill (m7/url (name :lgg1))
                           }
                    ])



                 [:g#d2
                  [:path {:d (m7/path [0 0 :l
                                       0 (* 20 d2)])
                          :fill :none
                          :stroke-width .3
                          :transform
                          (m7/tranfrom
                           [
                            [:translate [(* m 20)
                                         0]]
                            [:scale [x 1]]
                            ])
                          :id :ddd2
                          :marker-end (m7/url (name :dot))
                          :stroke (hsl [3.5 70 70 .7])}
                   ]

                  [:text {:translate (m7/tranfrom [[:rotate -90]])}
                   [:textPath {:href :#ddd2
                               :startOffset "30%"
                               :font-size d}

                    [:tspan {:dy -1.5
                             :font-size d}
                     (str  "min = " d2)]

                    ]]]

                 [:g
                  [:path {:d            (path [0 0 :l (* 20 d) 0])
                          :transform    (m7/tranfrom
                                         [[:translate [(* 20 m) 0]]
                                          [:scale [x 1]]
                                          ])
                          :stroke       (hsl [.5 90 70 .7])
                          :stroke-width .3
                          :id           (str "dd2" x)
                          :marker-end   (m7/url (name :dot))
                          :fill         :none}
                   ]
                  [:text
                   [:textPath {:href        (str "#dd2" x)
                               :startOffset "20%"
                               :style       {
                                             :font-size ".2rem"}
                               } (str "d = " d)]
                   ]]])
              scales)












           (if mid
             [:g
              #_(line2 0 "y=0")


              [:circle {:cx (* mx 20)
                        :cy (* my 20)
                        :fill (hsl [.5 90 60 .5] )
                        :r 2}
               [:animate {:attributeName :r
                          :from 1
                          :to 2
                          :dur (m7/not-space [10 "s"])
                          :repeatCount :indefinite}]
               ]

              #_(ve (+ (* 2 4) (* 20 6)))
              (if (= m 0)
                [:g ]
                [:circle {:on-mouse-over
                          (fn [e]
                            (set-pos (not pos)))
                          :cx (* m 20)
                          :cy 0
                          :fill (hsl [(if pos 0 .5) 90 60 .6] )
                          :r 1.2}
                 [:animate {:attributeName :r
                            :from 1.2
                            :to 1
                            :dur (m7/np [10 :s])
                            :repeatCount :indefinite}]
                 ])



              [:circle {:cx (* 20 m)
                        :cy (/ (* 20 d d) a)
                        :fill (hsl [5 60 60 .5] )
                        :r 2}
               [:animate {:attributeName :r
                          :from 0
                          :to 2
                          :dur (m7/not-space [3 "s"])
                          :repeatCount :indefinite}]
               ]






              [:circle {:cx (* 20 m)
                        :cy (/ (* 20 d d) a)
                        :fill (hsl [5 60 60 .5] )
                        :r 2}
               [:animate {:attributeName :r
                          :from 0
                          :to 2
                          :dur (m7/not-space [3 "s"])
                          :repeatCount :indefinite}]
               ]

              (map
               (fn [ne]
                 [:circle {:cx (* 20 (+ m (* d ne)))
                           :cy 0
                           :fill (hsl [1 70 60 1] )
                           :r 1}
                  [:animate {:attributeName :r
                             :from 1
                             :to 2
                             :dur (m7/not-space [1 "s"])
                             :repeatCount :indefinite}]
                  ])
               [1 -1])


              (if down
                [:g#sym
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
                                :dur (m7/not-space [10 "s"])
                                :repeatCount :indefinite}]])
                  [[1 1] [-1 1]])])


              [:path {:d (m7/path [(* m 20) -120  :l 0 240 ])
                      :fill :none
                      :stroke-width .3
                      :stroke-dasharray 10
                      :marker-end (m7/url (name :dot))
                      :stroke (hsl [0 70 70 1])}
               [:animate {:attributeName :stroke-dashoffset
                          :from 0
                          :to 80
                          :dur (m7/not-space [20 "s"])
                          :repeatCount :indefinite}]]



              ])


           ])


        ]


       ])))




















(defn home-work190 [q22]
  (let [[slider set-slider] (react/useState false)
        [pos set-pos] (react/useState false)
        animate-ref (react/useRef)
        animate-ref2 (react/useRef)
        font-ref (react/useRef)
        style1 (clj->js
                [{:background (hsl [.5 70 70 .1])
                  :transform (m7/tranfrom [[:rotate "10deg"]])}
                 {:background (hsl [.9 70 70 .7])
                  :transform (m7/tranfrom [[:rotate "-10deg"]])}
                 {:background (hsl [2 70 70 .9])
                  :transform (m7/tranfrom [[:scale .9]])
                  :offset (/ 9 14)}
                 {:background (hsl [3.5 70 70 .7])
                  :transform (m7/tranfrom [[:scale 1]])}])
        _ (react/useEffect (fn []
                             (if (and animate-ref (-> animate-ref .-current))
                               (-> animate-ref
                                   .-current
                                   (.animate
                                    style1
                                    (clj->js
                                     {:duration 2000
                                      :iterations 1})
                                    )))
                             (js/console.log "1")))

        _ (react/useEffect
           (fn []
             (if (and animate-ref2 (-> animate-ref2 .-current))
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
        [point set-point] (react/useState true)
        [btn set-btn] (react/useState 100)
        [xx set-xx] (react/useState 2)
        [yy set-yy] (react/useState 3)
        [zm set-zm] (react/useState [])
        [q2 set-q2] (react/useState q22)
        [center set-center] (react/useState true)
        [zoom-in set-zoom-in] (react/useState false)
        [zoom-out set-zoom-out] (react/useState false)


        ]
    (let [fix true
          scales [1 -1]
          [a m d :as amd]  q2
          d2 (/ (* d d) a)
          vbr [[(ve (- (* 3 3 20 .5) (* m 20)))
                (ve (- (* 3 20) (* 20 d2)))
                (* 3 3 20) (* 3 20) ]
               [(ve (* 3 12 20 .5))
                (ve (- (* 12 20)
                       (* 20 d2)))
                (* 3 12 20)
                (* 12 20) ]]
          gd [1 1 0 0 false .1 (if point 1  4)]
          r (if point .2
                (nth `[~@(take 5
                               (repeat 2)) ~@(take 15 (repeat 1))] a))
          viewbox2 (if center
                     (let [[x y w h] (if point (first vbr) (second vbr))]
                       [(ve (/ w 2)) (ve (/ h 2)) w h])


                     (if point (first vbr) (second vbr)))
          viewbox (if point (second vbr) (first vbr))

          rn (apply range (map #(* % a) [-6 6 1]))
          rnn (apply range (map #(* % a)  [-6 6 (if point .01 .1 )]))
          main-eq ((fun ([[1 0 0]]
                         [m7/x `[= y [:p x 2]]])
                        ([[1 m d]]
                         [m7/x `[= y
                                 [- [:p [:b [-  x ~m]] 2]
                                  [:p ~d 2]]]])
                        ([[a 0 0]]
                         [m7/x `[= y [[:p x 2] ~a]]])
                        ([[a m 0]]
                         [m7/x `[= y
                                 [[:p [:b [-  x ~m]] 2] ~a]]])
                        ([[a m d]]
                         [m7/x `[= y
                                 [[- [:p [:b [-  x ~m]] 2]
                                   [:p ~d 2]] ~a]]]))
                   amd)
          down false
          mid true
          curve false
          shape false
          [mx my :as mark] [0 0]
          eqsprev (fn [x]
                    (if fix
                      (.toFixed (* (/ 1 a) (- (* (- x m) (- x m)) (* d d))) 1)
                      (* 1 (- (* (- x m) (- x m)) (* d d)))))
          eqs (fn [x]
                (if fix
                  (.toFixed (* (/ 1 a) (- (* (- x m) (- x m)) (* d d))) 1)
                  (* 1 (- (* (- x m) (- x m)) ))))

          eqsprev2 (fn [x]
                 (ve (* (/ 20 a) (- (* (- x m) (- x m)) (* d d)))))

          eqs2 (fn [x]
                 (ve (* (/ 20 a) (- (* (- x m) (- x m) ) (* d d)))))

          ]
      [:div {
             :style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 24 (repeat [8 :vh]))])
                     {:background (hsl [.5 65 55 1])
                      :gap              ".2rem"})}


       (map
        (fn [n d]
          [:div {:on-mouse-enter (fn [e] (set-btn n))
                 :on-mouse-leave  (fn [e] (set-btn 100))
                 :style (m7/css
                         [[12 1 (+ 1 (* n 1)) 1  :center :center  2 :rem :column]
                          [(if (= btn n) 1 .5) 70 70  .8] []
                          {:font-size (m7/np
                                       [(if (= btn n) 2 1.8 ) :rem])
                           :font-family "Roboto Flex"
                           :gap (m7/np [1 :rem])
                           :border-radius "50%"
                           :color (hsl
                                   (if (= btn n)
                                     [1 30 30 1]
                                     [1 70 60 .7]))
                           :z-index 4
                           :cursor :grab}
                          ])}

           d])
        (range 0 40)

        [
         [:div {:on-click (fn [e] (set-zoom-in (not zoom-in)))}
          (if zoom-in "+" "-")]

         [:div
          (.toFixed (first viewbox2 ) 1) ]
         [:div {:on-click (fn [e] (set-zoom-out (not zoom-out)))}
          (if zoom-out "+" "-")]

         [:div (.toFixed (second viewbox2 )  1)]

         [:div {:on-click (fn [e] (set-zoom-out (not zoom-out)))}
          (if zoom-out "+" "-")]
         [:div
          (.toFixed (nth viewbox2 2) 1)]
         [:div {:on-click (fn [e] (set-zoom-out (not zoom-out)))}
          (if zoom-out "+" "-")]
         [:div (.toFixed
                (nth viewbox2 3) 1)]
         [:div {:on-click (fn [_]
                            (set-xx (- xx 1)))} "⬅"]
         [:div xx]
         [:div {:on-click (fn [_]
                            (set-xx (+ xx 1)))} "⇨"]
         [:div {:on-click (fn [_]
                            (set-yy (- yy 1)))} "⬅"]
         [:div yy]
         [:div {:on-click (fn [_]
                            (set-yy (+ yy 1)))} "⇨"]


         [:div  {:on-click (fn [] (set-center (not center)))} (.toFixed (second viewbox2) 1)]



         [:div {:on-click (fn [_]
                            (set-q2 (update q2 2 dec)))} "⬅"]
         [:div (get q2 2)]
         [:div {:on-click (fn [_]
                            (set-q2 (update q2 2 inc)))} "⇨"]




         [:div {:on-click (fn [_]
                            (set-q2 (update q2 1 dec)))} "⬅"]
         [:div (get q2 1)]
         [:div {:on-click (fn [_]
                            (set-q2 (update q2 1 inc)))} "\u279C"]




         [:div {:on-click (fn [_]
                            (set-q2 (update q2 0 dec)))} "⬅"]
         [:div "⇨" (get q2 0)]
         [:div {:on-click (fn [_]
                            (set-q2 (update q2 0 inc)))} "⇨"]



         ]
        )


       (map
        (fn [n d]
          [:div {:style (m7/css
                         [[1 1 (+ 2 (* n 1)) 1  :center :center  2 :rem :column]
                          [(+ (/ n 10) .8) 70 (+ 50 (* 1 n))  .4] [] {:gap ".1rem"
                                                               :z-index 4}])}

           d])
        (range 0 24)
        (into [[:div {:style {:font-size "3rem"}}  "x"]]
              rn))

       (map
        (fn [n d]
          [:div {:style (m7/css
                         [[2 1 (+ 2 (* n 1)) 1  :center :center  2 :rem :column]
                          [(+ (/ n 10) .8) 70 (+ 50 (* 1 n))  .4]
                          [] {:gap ".1rem"
                                                               :z-index 4}])}

           d])
        (range 0 24)
        (into [[:div {:style {:cursor :pointer
                              :color (hsl [3 90 90 1])
                              :font-size "4rem"}
                      :on-click (fn [e]
                                  (do
                                    (set-slider (not slider))
                                    (js/console.log "hello")
                                    ))}
                #_[m7/m '[= y [:p x 2]]]
                (name :y)
                ]]
              (map (fn [x]
                     [:div {:style {:font-size "1.2rem"}}
                      (eqs x)]) rn)))





       (if slider
         [:div {:ref animate-ref
                :style
                (m7/css
                 [[4 6 3 7 :center :center  2 :rem :column] [3.5 70 (+ 50 (* 5 5))  .7] []
                  {:gap     ".1rem"
                   :z-index 10}])}
          [m7/m ['= 'y [:m 'a [:b ['- [:p [:b ['- 'x 'm]] 2] [:p 'd 2]]]]]]

          [m7/m ['= [:m 8 [:b ['- [:p [:b ['- 'x m]] 2] [:p d 2]]]] 0 ]]

          [m7/m ['= [[:m 8 [:b ['- [:p [:b ['- 'x m]] 2] [:p d 2]]]] 8] [0 8] ]]

          [m7/m ['= ['- [:p [:b ['- 'x m]] 2] [:p d 2]] 0 ]]

          [:div  {:ref font-ref
                  :style {:font-size "2rem"
                          }}
             [m7/m ['= [:m [:b ['- 'x m d]]  [:b ['+ ['- 'x m] d]]] 0 ]]]

          [m7/m ['= ['+ ['- 'x m] d] 0]]
          [m7/m ['= ['- 'x m d] 0]]
          ]

         [:div {:on-click (fn [_]
                            (set-point (not point)))
                :style
                (m7/css
                 [[1 2 16 7 :center :center  3 :rem :column]
                  [3.5 70 75 .5]
                  []
                  {:gap     ".1rem"
                   :z-index 10}])}
          main-eq]
         )
       (if slider
         (let [a (m7/eq2 `[[1 [1] [x]] [-1 [1] [m]]])
               d (m7/eq2 `[-1 [2] [d]])
               a2 [[1 [2] [[:b (sl/symeq a)]]]
                   d]
               d1 (sl/mkeq1a d)
               d2 (sl/mkeq2a d1)
               bb 'b
               cc 'c
               ee (m7/eq2 [[1 [2] ['x]]
                           [1 [1 1] [bb 'x]]
                           [1 [1] [cc]]])
               eee (m7/eq2 [[1 [2] ['x]]
                            [bb [1] ['x]]
                            1])


               ee1 (m7/eq2 `[[1 [ 2] [ x]]
                             [1 [1 1] [[b a] x]]
                             [1 [1] [[c a]]]])
               ek1 (conj (vec (sl/lawdr2 a a)) d1)
               ek2 (rest (rest ek1))
               ]
           [:div {:ref animate-ref2
                  :style
                  (m7/css
                   [[4 7 14 12
                     :flex-start :flex-start  2 :rem :column]
                    [1 70 (+ 50 (* 5 5))  .7]
                    []
                    {:padding-left "100px"
                     :gap ".1rem"
                     :z-index 10}])}
            (m7/x (sl/e=  [:m 'a [:b (sl/symeq a2)]]) )
            (m7/x (sl/e= [:m 'a [:b (sl/symeq2 ek1)]]))
            (m7/x (sl/e= [:m 'a [:b (sl/symeq ee1)]]))
            (m7/x ['= [cc 'a]  (sl/symeq2 ek2)])
            (m7/x `[= [~bb a] [- [:m 2 m]]])








            ])
           [:div ""])


       [:div {:style (m7/css
                      [[2 10 1 25 :center :center 3 :rem]
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
                                                [:scale [.6 .6]]])
                    :fill         (m7/hsl [-0.5 70 70 .2])}]]

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
                     :stop-color (hsl [.5 63 57 .3])}
             [:animate {:attributeName :offset
                        :from .2
                        :to .5
                        :dur (m7/not-space [3 "s"])
                        :repeatCount :indefinite}]]
            [:stop  {:offset .5
                     :stop-color (hsl [1 70 70 .3])}
             [:animate {:attributeName :offset
                        :from .3
                        :to 1
                        :dur (m7/not-space [3 "s"])
                        :repeatCount :indefinite}]]
            ]



           (apply grid-on gd)



           (if (not curve)
             (map (fn [x y]
                    [:circle {
                              :cx    x
                              :cy    y
                              :r     r
                              :fill (if point
                                      (hsl [0 70 70 .8])
                                      (hsl [2 70 70 .8]))
                              }]
                    )

                  (map (fn [x]
                         (* 20 x)) rnn)
                  (map eqs2 rnn)))


           (map
              (fn [x]
                [:g



                 (if shape
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

                           :transform
                           (m7/tranfrom
                            [
                             [:translate [(* m 20)
                                          (* 2 d d)]]
                             [:scale [x 1]]
                             ])
                           :stroke (hsl [0 70 70 1])
                           :stroke-width .1
                           :fill (m7/url (name :lgg1))
                           }
                    ])



                 [:g#d2
                  [:path {:d (m7/path [0 0 :l
                                       0 (* 20 d2)])
                          :fill :none
                          :stroke-width .3
                          :transform
                          (m7/tranfrom
                           [
                            [:translate [(* m 20)
                                         0]]
                            [:scale [x 1]]
                            ])
                          :id :ddd2
                          :marker-end (m7/url (name :dot))
                          :stroke (hsl [3.5 70 70 .7])}
                   ]

                  [:text {:translate (m7/tranfrom [[:rotate -90]])}
                   [:textPath {:href :#ddd2
                               :startOffset "30%"
                               :font-size d}

                    [:tspan {:dy -1.5
                             :font-size d}
                     (str  "min = " d2)]

                    ]]]

                 [:g
                  [:path {:d            (path [0 0 :l (* 20 d) 0])
                          :transform    (m7/tranfrom
                                         [[:translate [(* 20 m) 0]]
                                          [:scale [x 1]]
                                          ])
                          :stroke       (hsl [.5 90 70 .7])
                          :stroke-width .3
                          :id           (str "dd2" x)
                          :marker-end   (m7/url (name :dot))
                          :fill         :none}
                   ]
                  [:text
                   [:textPath {:href        (str "#dd2" x)
                               :startOffset "20%"
                               :style       {
                                             :font-size ".2rem"}
                               } (str "d = " d)]
                   ]]])
              scales)












           (if mid
             [:g
              #_(line2 0 "y=0")


              [:circle {:cx (* mx 20)
                        :cy (* my 20)
                        :fill (hsl [.5 90 60 .5] )
                        :r 2}
               [:animate {:attributeName :r
                          :from 1
                          :to 2
                          :dur (m7/not-space [10 "s"])
                          :repeatCount :indefinite}]
               ]

              #_(ve (+ (* 2 4) (* 20 6)))
              (if (= m 0)
                [:g ]
                [:circle {:on-mouse-over
                          (fn [e]
                            (set-pos (not pos)))
                          :cx (* m 20)
                          :cy 0
                          :fill (hsl [(if pos 0 .5) 90 60 .6] )
                          :r 1.2}
                 [:animate {:attributeName :r
                            :from 1.2
                            :to 1
                            :dur (m7/np [10 :s])
                            :repeatCount :indefinite}]
                 ])



              [:circle {:cx (* 20 m)
                        :cy (/ (* 20 d d) a)
                        :fill (hsl [5 60 60 .5] )
                        :r 2}
               [:animate {:attributeName :r
                          :from 0
                          :to 2
                          :dur (m7/not-space [3 "s"])
                          :repeatCount :indefinite}]
               ]






              [:circle {:cx (* 20 m)
                        :cy (/ (* 20 d d) a)
                        :fill (hsl [5 60 60 .5] )
                        :r 2}
               [:animate {:attributeName :r
                          :from 0
                          :to 2
                          :dur (m7/not-space [3 "s"])
                          :repeatCount :indefinite}]
               ]

              (map
               (fn [ne]
                 [:circle {:cx (* 20 (+ m (* d ne)))
                           :cy 0
                           :fill (hsl [1 70 60 1] )
                           :r 1}
                  [:animate {:attributeName :r
                             :from 1
                             :to 2
                             :dur (m7/not-space [1 "s"])
                             :repeatCount :indefinite}]
                  ])
               [1 -1])


              (if down
                [:g#sym
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
                                :dur (m7/not-space [10 "s"])
                                :repeatCount :indefinite}]])
                  [[1 1] [-1 1]])])


              [:path {:d (m7/path [(* m 20) -120  :l 0 240 ])
                      :fill :none
                      :stroke-width .3
                      :stroke-dasharray 10
                      :marker-end (m7/url (name :dot))
                      :stroke (hsl [0 70 70 1])}
               [:animate {:attributeName :stroke-dashoffset
                          :from 0
                          :to 80
                          :dur (m7/not-space [20 "s"])
                          :repeatCount :indefinite}]]



              ])


           ])


        ]


       ])))
