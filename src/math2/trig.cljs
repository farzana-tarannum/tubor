(ns math2.trig
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
(defn fix [fr n]
  (js/parseFloat (.toFixed fr n)))
(defn flames
  ([id red yellow]
   [:filter
    {:id (name id)
     :height (m7/np [300 :%])
     :width (m7/np [120 :%])
     :y (m7/np [-100 :%])
     :x (m7/np [0 :%])
     }
    [:feTurbulence
     {:stitchTiles :stitch
      :result "noise"
      :numOctaves 1
      :baseFrequency 1
      :type :fractalNoise}]
    [:feOffset
     {:result "off1", :dy "0"}
     [:animate
      {:repeatCount "indefinite",
       :dur "6s",
       :to "-300",
       :from "0",
       :attributeName "dy",
       :attributeType :XML}]]
    [:feOffset
     {:result "off2", :dy "60", :in "noise"}
     [:animate
      {:repeatCount "indefinite",
       :dur "6s",
       :to "0",
       :from "300",
       :attributeName "dy",
       :attributeType "XML"}]
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
     [:feFuncR {:tableValues "0 0.2", :type "discrete"}]
     [:feFuncG {:tableValues "0 0.2", :type "discrete"}]
     [:feFuncB {:tableValues "0 0.2", :type "discrete"}]
     [:feFuncA {:tableValues "0 0.2", :type "discrete"}]]
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
                 :stroke (hsl [4 70 70 0.5])
                 :stroke-width 0.5
                 :fill :none}
          ])
       (range 0 11))


      (map-indexed
       (fn [index i]
         [:path {:key (str "grid-onsdfasd2" index)
                 :d (path [(+ ax-dy (* i 2)) -400 :l 0 1200 ])

                 :stroke (hsl [4 70 70 0.5])
                 :stroke-width 0.5
                 :fill :none}
          ] )
       (range 0 11))






      (map-indexed
       (fn [i x]
         [:g {:key (str "path-indexed333" i)}

          [:path {:d (path [ (* 20 x)  0 :l 0 -400])

                  :stroke (hsl [(if (= x -2)  5 0) 70 70 1])
                  :stroke-width 0.5
                  :fill :none}
           ]


          [:path {:d (path [ (* 20 x)  0 :l 0 400])

                  :stroke (hsl [0 70 70 1])
                  :stroke-width 0.5
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


(comment
  time-str (-> timer .toTimeString
               (str/split " ")
               first
               (str/split ":" )
               (nth 2)))

(defn ladder []
  (let [[timer update-time] (react/useState 0)
        _ (react/useEffect
           (fn []
             (let [i (js/setInterval
                      (fn [x] (update-time (mod (inc timer) 16))) 5000)]
               (fn []
                 (js/clearInterval i)))))
        sec2 timer
        angle {:font-size 12 :dy -5 :dx 30}
        turns (mapcat (fn [y]
                        (map
                         (fn [x]
                           (+ (/ js/Math.PI x ) (* y  (/ js/Math.PI 2))))
                         [6 4 3 2]))
                      [0 1 2 3])
        tn (nth turns sec2)
        tn2 (nth turns 4)
        r 18
        adj (fix (* r  (js/Math.cos tn))  2)
        op (fix (* r  (js/Math.sin tn))  2)
        opp1 (fix (* r  (js/Math.tan tn))  2)

        adj2 (fix (* r  (js/Math.cos tn))  2)
        op2 (fix (* r  (js/Math.sin tn))  2)
        opp12 (fix (* r  (js/Math.tan tn))  2)



        [slider get-slider] (react/useState 0)
        pi 'π
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
                     [-200 -500  1200 900]
                     ] z))
          viewbox (vb 0)
          viewbox2 (vb 9)
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 0.1])

                      :gap ".1rem"})}


       (map
        (fn [r an]
          [:div
           {:style (m7/css
                    [[5 1 (+ 9 r) 1 :center :center
                      1.8 :rem :column]
                     [1 70 90 0.1] []
                     {:gap "1rem"
                      :color (hsl [3 50 30 1])
                      :z-index 2}])}
           an])


        (range 0 17)
        [0 [m7/x `[~pi
                   6]]

         [m7/x `[~pi
                 4]]
         [m7/x `[~pi
                 3]]
         [m7/x `[~pi
                 2]]
         [m7/x `[[:m 2 ~pi]
                 3]]

         [m7/x `[[:m 3 ~pi]
                 4]]
         [m7/x `[[:m 3 ~pi]
                 4]]
         [m7/x `[[:m 5 ~pi]
                 6]]

         pi
         [m7/x `[[:m 7 ~pi]
                 6]]

         [m7/x `[[:m 5 ~pi]
                 4]]

         [m7/x `[[:m 4 ~pi]
                 3]]
         [m7/x `[[:m 3 ~pi]
                 2]]
         ]
        )

       (map
        (fn [r an]
          [:div
           {:style (m7/css
                    [[6 1 (+ 9 r) 1 :center :center
                      1.2 :rem :column]
                     [1 70 90 0.1] []
                     {:gap "1rem"
                      :color (hsl [3 50 30 1])
                      :z-index 2}])}
           (fix an 3)])


        (range 0 17)
        [0 (js/Math.sin (/ (* 1 js/Math.PI)  6))
         (js/Math.sin (/ (* 1 js/Math.PI)  4))
         (js/Math.sin (/ (* 1 js/Math.PI)  3))
         (js/Math.sin (/ (* 1 js/Math.PI)  2))
         (js/Math.sin (/ (* 2 js/Math.PI)  3))
         (js/Math.sin (/ (* 3 js/Math.PI)  4))
         (js/Math.sin (/ (* 5 js/Math.PI)  6))
         (js/Math.sin (/ (* 5 js/Math.PI)  6))
         (js/Math.sin js/Math.PI)
         (js/Math.sin (/ (* 7 js/Math.PI)  6))
         (js/Math.sin (/ (* 6 js/Math.PI)  4))
         (js/Math.sin (/ (* 4 js/Math.PI)  3))
         (js/Math.sin (/ (* 3 js/Math.PI)  2))

         ]
        )



       (map
        (fn [r an]
          [:div
           {:style (m7/css
                    [[7 1 (+ 9 r) 1 :center :center
                      1.2 :rem :column]
                     [1 70 90 0.1] []
                     {:gap "1rem"
                      :color (hsl [3 50 30 1])
                      :z-index 2}])}
           (fix an 3)])


        (range 0 17)
        [0 (js/Math.cos (/ (* 1 js/Math.PI)  6))
         (js/Math.cos (/ (* 1 js/Math.PI)  4))
         (js/Math.cos (/ (* 1 js/Math.PI)  3))
         (js/Math.cos (/ (* 1 js/Math.PI)  2))
         (js/Math.cos (/ (* 2 js/Math.PI)  3))
         (js/Math.cos (/ (* 3 js/Math.PI)  4))
         (js/Math.cos (/ (* 5 js/Math.PI)  6))
         (js/Math.cos (/ (* 5 js/Math.PI)  6))
         (js/Math.cos (/ (* 1 js/Math.PI)  1))
         (js/Math.cos (/ (* 7 js/Math.PI)  6))
         (js/Math.cos (/ (* 6 js/Math.PI)  4))
         (js/Math.cos (/ (* 4 js/Math.PI)  3))
         (js/Math.cos (/ (* 3 js/Math.PI)  2))

         ]
        )



       [:div {:style (m7/css
                      [[2 6 2 9 :center :center
                        1.8 :rem :column]
                       [1 70 90 0.1] []
                       {:gap "1rem"
                        :color (hsl [3 50 30 1])
                        :z-index 2}])}
        (map
          (fn [t]
            (let [snr (nth (mapcat (fn [y]
                                     (map
                                      (fn [x]
                                        [[:m (+ y 1) pi] x])
                                      [6 4 3 2]))
                                   [0 1 2 3]) t)

                  snb (nth (mapcat (fn [y]
                                     (map
                                      (fn [x]

                                        (fix (js/Math.cos (/ (* (+ y 1) js/Math.PI) x) )  3))
                                      [6 4 3 2]))
                                   [0 1 2 3]) t)


                  snh (nth (mapcat (fn [y]
                                     (map
                                      (fn [x]

                                        (fix (js/Math.sin (/ (* (+ y 1) js/Math.PI) x) )  3))
                                      [6 4 3 2]))
                                   [0 1 2 3]) t)

                  ]
              [:div
               [m7/x `[= h  [:m sin ~snr]
                       ~snh
                       ]]
               ","
               [m7/x `[= b  [:m cos ~snr]
                       ~snb
                       ]]])
            )
          (range 0 3))





        [:div (str " Angle " )]


        [m7/x `[= ~tt [:s ~(nth (mapcat (fn [y]
                                          (map
                                           (fn [x]
                                             [[:m (+ y 1) pi] x])
                                           [6 4 3 2]))
                                        [0 1 2 3]) timer) ]]]
        (let [snr (nth (mapcat (fn [y]
                                 (map
                                  (fn [x]
                                    [[:m (+ y 1) pi] x])
                                  [6 4 3 2]))
                               [0 1 2 3]) timer)
              snh (nth (mapcat (fn [y]
                                 (map
                                  (fn [x]

                                    (fix (js/Math.sin (/ (* (+ y 1) js/Math.PI) x) )  3))
                                  [6 4 3 2]))
                               [0 1 2 3]) timer)]
          [m7/x `[= h [:m sin ~tt] [:m sin ~snr]
                  ~snh
                  ]])


        [m7/x `[= b [:m cos ~tt] [:m cos ~(nth (mapcat (fn [y]
                                                       (map
                                                        (fn [x]
                                                          [[:m (+ y 1) pi] x])
                                                        [6 4 3 2]))
                                                     [0 1 2 3]) timer)]]]

        #_[m7/x `[= ~pi [c [:m 2 r]] ]]

        #_[m7/x `[= [:m ~pi 2 r]  [[:m 2 r c ] [:m 2 r]] ]]

        #_[m7/x `[= [:m 2 ~pi  r] c]]

        #_[m7/x `[= [:m 2 ~pi  ] c]]

        #_[m7/x `[= [[:m 2 ~pi  ] 4] [c 4]]]

        ]



       #_[:div {:style (m7/css
                        [[2 6 2 9 :center :center
                          1.8 :rem :column]
                         [1 70 90 0.1] []
                         {:gap "1rem"
                          :color (hsl [3 50 30 1])
                          :z-index 2}])}

          "When a ladder of length 18 m leans against the to top edge of a window of a building. It forms a angle of 60 deg with the ground. When the ladder leans against the lower edge of the same window, it from a angle of 45 deg with ground. Calculate the height of the window. "]




       #_[:div {:style (m7/css
                      [[2 10 14 9 :center :center
                        2.1 :rem :column]
                       [1 70 90 0.1] []
                       {:gap "1rem"
                        :color (hsl [3 50 30 1])
                        :z-index 3}])}
        [m7/mx `[= AC r]]

        [m7/mx `[= AB BC x]]
        [:div "In pythagorium theorum in ABC"]
        [m7/x `[= [:p r 2]  [+ [:p x 2]  [:p x 2]]]]
        [m7/x `[= [:p r 2]  [:m 2 [:p x 2] ]]]

        [m7/x `[=  r 1]]
        [m7/x `[= [:p 1 2]  [:m 2 [:p x 2] ]]]


        [m7/x `[= [1 2]  [ [:m 2 [:p x 2]] 2]]]


        [m7/x `[= [1 2]  [:p x 2]]]




        #_[m7/x `[= [:p 1 2]  [+ [:p [:b [1 2]] 2]  [:p x 2]]]]

        #_[m7/x `[= 1  [+ [1 4]  [:p x 2]]]]

        #_[m7/x `[= [3 4] [:p x 2]]]

        #_[m7/x `[= [[:sq 3] 2] x]]
        #_[m7/x `[= [:p 3 2]  [:m 2 [:p x 2]]]]
        #_[m7/x `[= [ [:p 3 2] 2]  [:p x 2]]]

        #_[m7/x `[= [ [* 2 [:p 3 2]] [* 2 2]]  [:p x 2]]]

        #_[m7/x `[= [ [* 2 [:p 3 2]] [:p 2 2]]  [:p x 2]]]

        #_[m7/x `[= [:sq [ [* 2 [:p 3 2]] [:p 2 2]]]  [:sq [:p x 2]]]]

        #_[m7/x `[= [[:m 3 [:sq 2] ] 2]
                x]]


        #_[m7/x `[= [2 [:p 2 2]]  [:p x 2]]]

        #_[m7/x `[= [:sq [2 [:p 2 2]]] x]]
        #_[m7/x `[=  [[:m 3 [:sq 2] ] 2]
                x  [:m 3 [:m sin 45 ~deg]]]]

        #_[m7/x `[= [[:sq 2] 2] x [:m sin [pi 4]] [:m cos [pi 4]]]]
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
                        :fill (hsl [0.6 95 70 0.5])}]



              [:circle {:r 350
                        :cx 0
                        :cy 0
                        :stroke  (hsl [0 70 70 1])
                        :stroke-width 0.5
                        :fill :none}]



              [:circle {:r 350
                        :cx 0
                        :cy 0
                        :stroke  (hsl [0 70 70 1])
                        :stroke-width 0.5
                        :fill :none}]

              [:circle {:r 25
                        :cx 470
                        :cy 0
                        :stroke (hsl [3 70 70 1])
                        :stroke-width 0.5
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
                        :stroke-width 0.5
                        :fill :none}]


              [:circle#cline2 {:r 470
                        :cx 0
                        :cy 0
                        :stroke  (hsl [2 70 70 1])
                        :stroke-width 0.3
                        :fill :none}]


              [:g#arcs
               (map
                (fn [se]
                  [:circle {:r 5
                            :cx (* 1 r (js/Math.cos se))
                            :cy (ve (* 1 r (js/Math.sin se)))
                            :fill (hsl [3 70 70 1])}])
                turns)]



              #_[:g#r1
               (map
                (fn [ang]
                  [:path {:d (m7/path [0 0 :l r 0 ])
                          :id :rrr2
                          :fill :none
                          :transform (m7/tranfrom
                                      [[:rotate ang]])
                          :stroke (hsl [2 70 80 0.6])
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
                          :fill (hsl [4 70 80 0.6])
                          :transform (m7/tranfrom [[:rotate ang]])
                          :stroke (hsl [4 70 80 0.6])
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
                          :fill (hsl [4 70 80 0.6])
                          :transform (m7/tranfrom [
                                                   [:rotate ang]])
                          :stroke (hsl [4 70 80 0.6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])


                 (map
                  (fn [ang]
                    [:path {:d (m7/path [0 0 :l  0
                                         20 -20 0 0 -20 ])

                            :fill (hsl [0 70 80 0.6])
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
                              (+ r (* 0.5 r
                                      (js/Math.tan
                                       (/ js/Math.PI
                                          (nth turns sec2)))))}
                   "opp"
                   [:tspan {:dy 10} 1]]]
                 ]









              [:text
               [:textPath {:href :#rrr2
                           :font-size 20
                           :startOffset "30%"}
                "r = 1"]]





              [:g
               [:path#angle {:d
                             (m7/path
                              [0 0 :l r 0 :a r r 0 false false
                               (ve (- r (* 20 adj)))
                               (ve (* 20 op))])
                             :stroke (hsl [3 70 70 1])
                             :transform (m7/tranfrom [[:scale [0.4 0.4]]])
                             :stroke-width 3
                             :fill (hsl [1.2 70 70 0.5])}]


               [:path#sin {:d (m7/path [0 0 :l (* 20 adj) 0
                                        0 (ve (* 20 op))])
                           :id :tri22
                           :stroke (hsl [1 70 70 1])
                           :stroke-width 1
                           :fill (hsl [1.5 70 70 0.5])}]]


              [:g {:transform (m7/tranfrom [[:translate [0 0]]])}
               [:path#angle2 {:d
                              (m7/path
                               [0 0 :l r 0 :a r r 0 false false
                                (ve (- r (* 20 adj2))) (ve (* 20  op2))])
                              :stroke (hsl [4 70 70 1])
                              :transform (m7/tranfrom [[:scale [0.2 0.2]]])
                              :stroke-width 3
                              :fill (hsl [3 70 70 0.5])}]


               [:path#sin2 {:d
                            (m7/path [0 0 :l (* 20 adj2) 0 0 (ve (* 20 op2))])
                            :id :tri222
                            :stroke (hsl [4.5 70 70 1])
                            :stroke-width 1
                            :fill (hsl [2.5 70 70 0.5])}]


               [:text

                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ 0 (* adj2 20))}

                 (str "B" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ (* 0.95 op2 20) (* adj2 20))}

                 (str "A1" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :dy 10
                            :startOffset (+ 0 0)}
                 (str "C1" "")]

                ]



               ]





              [:path.tri2 {:d (m7/path
                               [0 0 :l (* 0.3 adj 20) 0 0 (ve (* 20 op))])
                      :transform (m7/tranfrom
                                  [[:translate [(* 0.7 20 adj) 0]]])
                      :stroke (hsl [1 70 70 1])
                      :stroke-width 3
                      :fill (hsl [3.5 70 70 0.5])}]


              [:text
               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ 0 0)}
                (str "C" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ 0 (* adj 20))}

                (str "B" "")
                ]




               [:textPath {:href :#tri22
                             :font-size 15
                           :startOffset (+ 0 (* 20 0.7 adj))}

                (str "T" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* 0.95 op 20) (* adj 20))}

                (str "A" "")]




               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* 0.3 adj 20) 0)}

                #_(str "adj" "")
                adj

                ]


               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset
                           (+
                            (* 20 adj)
                            (* 0.4 20 op))}

                (str "opp" "")

                op
                  ]
               ]






              #_[:text {:x 0
                      :style {:font-size (:font-size angle)}
                      :dy (:dy angle)
                      :dx (:dx angle)
                      :y 0}
               (name tt)
               (fix (* (/ 180 js/Math.PI) (js/Math.asin 0.92)
                       ) 2)
               (fix (/ 180 (nth turns sec2)) 1)
               [:tspan {:dy -6}
                (name deg)]
               ]


              [:circle {:r 580
                        :cx 0
                        :cy 0
                        :stroke  (hsl [2.7 70 70 1])
                        :stroke-width 0.3
                        :fill :none}]])])]])))



(defn ladder2 []
  (let [[timer update-time] (react/useState 0)
        sec2 5
        angle {:font-size 12 :dy -5 :dx 30}
        turns (mapcat (fn [y]
                        (map
                         (fn [x]
                           (+ (/ js/Math.PI x ) (* y  (/ js/Math.PI 2))))
                         [6 4 3 2]))
                      [0 1 2 3])
        tn (nth turns sec2)
        tn2 (nth turns 4)
        r 4
        adj (fix (* r  (js/Math.cos tn))  2)
        op (fix (* r  (js/Math.sin tn))  2)
        opp1 (fix (* r  (js/Math.tan tn))  2)

        adj2 (fix (* r  (js/Math.cos tn))  2)
        op2 (fix (* r  (js/Math.sin tn))  2)
        opp12 (fix (* r  (js/Math.tan tn))  2)

        _ (react/useEffect
           (fn []
             (let [i (js/setInterval
                      (fn [x] (update-time (mod (inc timer) 16))) 5000)]
               (fn []
                 (js/clearInterval i)))))

        [slider get-slider] (react/useState 0)
        pi 'π
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
                     [0 -100  200 300]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-200 -600  1200 1000]
                     ] z))
          viewbox (vb 0)
          viewbox2 (vb 1)
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 0.1])

                      :gap ".1rem"})}


       #_[:div {:style (m7/css
                        [[2 6 2 9 :center :center
                          1.8 :rem :column]
                         [1 70 90 0.1] []
                         {:gap "1rem"
                          :color (hsl [3 50 30 1])
                          :z-index 2}])}

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

           #_(grid-on 1 1)




           (let [rad tn
                 a (gensym)
                 b (gensym)
                 c (gensym)
                 ab (gensym)
                 bc (gensym)
                 ca (gensym)
                 abc (gensym)
                 f1 (gensym)
                 r (* r 20)]
             [:g


              #_[:circle {:r r
                        :cx 0
                        :cy 0
                        :stroke  (hsl [1 50 50 1])
                        :stroke-width 2
                        :fill :none}]

              [:clipPath {:id a}
               [:circle {:r r
                         :cx 0
                         :cy 0
                         :stroke  (hsl [1 70 70 1])
                         :stroke-width 2
                         }]]




              [:circle {:r r
                        :cx (* 20 6)
                        :cy 0
                        :stroke  (hsl [1 70 70 1])
                        :stroke-width 2
                        :fill :none}]











              [:filter {:id f1}
               [:feOffset {:in :SourceGraphic
                           :dx (* 20 6)
                           :dy 0}]
               [:feComposite {:in2 :SourceGraphic
                              :operator :light}]]


              [:circle {:r r
                        :cx 0
                        :cy 0
                        :stroke  (hsl [1 50 50 1])
                        :stroke-width 2
                        :fill :none


                        }]

              [:circle {:r r
                        :id ab
                        :clip-path (m7/url a)
                        :cx (* 20 6)
                        :cy 0
                        :stroke  (hsl [1 70 70 1])
                        :stroke-width 2
                        :fill (m7/hsl [2 70 70 0.5])}]


              [:clipPath {:id abc}

               [:circle {:r r
                         :id ab
                         :clip-path (m7/url a)
                         :cx (* 20 6)
                         :cy 0
                         :stroke  (hsl [1 70 70 1])
                         :stroke-width 2
                         :fill (m7/hsl [2 70 70 0.5])}]]

              [:circle {:r r
                        :clip-path (m7/url abc)
                        :cx (* 20 3)
                        :cy (* 20 5)
                        :stroke  (hsl [1 70 70 1])
                        :stroke-width 2
                        :fill :none}]


              [:circle {:r r
                        :clip-path (m7/url abc)
                        :cx (* 20 6)
                        :cy 0
                        :stroke  (hsl [1 70 70 1])
                        :stroke-width 2
                        :fill (m7/hsl [2 70 70 0.5])}]

              #_[:g#arcs
               (map-indexed
                (fn [i se]
                  [:g
                   [:circle {:r 1
                             :cx (* r (js/Math.cos se))
                             :cy (ve (* r (js/Math.sin se)))
                             :fill (hsl [3 70 70 1])}]
                   [:text {:font-size 3
                           :x (* r (js/Math.cos se))
                           :y (ve (* r (js/Math.sin se)))
                           :fill (hsl [3 45 5 1])}   i]])
                turns)]






              ])])]])))






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
               (nth [(map  #(* % 8) [15 -20  40 45])
                     (map  #(* % 24) [0 -25  100 50])

                     (map  #(* % 8) [-25 -30  100 52])
                     (map  #(* % 5) [20 -25  100 50])
                     (map  #(* % 8) [-50 -25  100 50])

                     (map  #(* % 4) [20 -25  100 50])


                     (map  #(* % 23) [-50 -25  100 50])
                     (map  #(* % 4) [160 -25  100 50])
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
          viewbox2 (vb 1)]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 0.1])})}


       [:div {:style (m7/css
                      [[2 8 3 10 :center :center 2.1 :rem :column]
                       [1 70 90 0.1] []
                       {:gap "1rem"


                        :z-index 2}])}


        #_[m7/x `[= [:m cos [:p 135 *]] [:m cos [:p [:b [+ 90 45]] *]] [- [:m cos [:p 45 *]]] [- [[:sq 2] 2]] ]]

        #_[m7/x `[= [:m sing [:p 135 *]] [:m sing [:p [:b [+ 90 45]] *]] [:m sin [:p 45 *]] [[:sq 2] 2] ]]

        #_[m7/x `[= AD [:m AB sin [:p 20 *]]]]

        #_[m7/x `[= [:m cos [:p 50 *]] [[- [+ [:p 8 2] [:p 10 2]] [:p PR 2]] [* 2 8 10]]]]


        [m7/x `[= [PS [:m sin [:p 15 ~deg]]] [8 [:m sin [:p 36.585 ~deg]] ]
                ]]



        [m7/x `[= [RS [:m sin [:p 15 ~deg]]] [10.282 [:m sin [:p 36.585 ~deg]] ]
                ]]


        [m7/x `[= [:m Area PQS] [:m [1 2] (* [:b [+ 10 RS]] 8 [:m sin [:p 50 ~deg]])]]]


        #_[m7/x `[= [:m cos x] [11
                              16]]]


        #_[m7/x `[= x  [:m [:p cos -1] [11
                                      16]]]]

        #_[m7/x `[= x [:p 46.567 *]]]


        #_[m7/x `[= [:m  cos x] [[- [+ [:p 10 2] [:p 15 2]] [:p y 2]] [* 2 10 15]]]]



        #_[m7/x `[= [:m  cos x] [[- [+ [:p 10 2] [:p 15 2]] [:p y 2]] [* 2 10 15]]]]


        #_[m7/x `[= [:p y 2]  [- ~(+ 100 (* 15 15)) [* [* 55 15] 4] ]]]


        #_[m7/x `[= [BD [:m cos [:p 20 *]]] AB]]
        #_[m7/x `[= hyp r 1 ]]
        #_[m7/x `[=  [+ [:p x 2] [:p x 2]]  [:p 1 2]]]

        #_[m7/x `[=  [:m 2 [:p x 2]] 1]]

        #_[m7/x `[=  [:p x 2] [[* 2 1] [:p 2 2]]]]

        #_[m7/x `[=  [:sq [:p x 2]] [:sq [[* 2 1] [:p 2 2]]]]]

        #_[m7/x `[=  x [[:sq 2] 2]]]
        #_[:div "Circumference is 64 unit"]

        #_[m7/x `[360 64]]
        #_[m7/x `[= c [:m 2 ~pi r]]]

        #_[m7/x `[= [c [:m 2 r]]  ~pi ]]

        #_[m7/mx `[= [c [:m 2 r]]
                 ~pi ]]

        #_[m7/mx `[= [[* 16 [:k c 1]] 64] [[* 4 [:m 2 ~pi ]] 64] [[* 4 360] 64]] ]
        #_[m7/mx `[=  [[* 4 [:m 2 ~pi ]] 64] [:p [:b [[* 4 360] 64]] *]] ]

        #_[m7/mx `[= [[* [:b [- 32 4]] [:m 2 ~pi ]] 64] [:p [:b [[* [:b [- 32 4]] 360] 64]] *]] ]

        #_[m7/mx `[=  [:m sin  [[* 4 [:m 2 ~pi ]] 64]] [:m sin [:p [:b [[* 4 360] 64]] *]]
           [:m sin [[* [:b [- 32 4]] [:m 2 ~pi ]] 64]] [:m sin [:p [:b [[* [:b [- 32 4]] 360] 64]] *]]
                 ] ]




        ;; #_[m7/mx `[= [c [:m 2 r]]  ~pi ]]
        #_[:div "if r is 1 then"]
         #_[m7/mx `[= [c [* 2 1]]  ~pi ]]
        #_[m7/mx `[= [[:m 2 c] [* 2 1]] [:m 2 ~pi] ]]

        ;; #_[m7/mx `[= r 1]]
        #_[m7/mx `[= [[* 4 [:k c 1]] 64] [[* 4 [:m 2 ~pi ]] 64] [[* 4 360] 64] ] ]

        #_[m7/mx `[= [[* 4 [:k c 1]] 64] 0.3927 [:p 22.5 *] ] ]


        #_[m7/mx `[= [c 64] [[:m 2 ~pi ] 64] ]]


        #_[m7/mx `[= [c 64] [[:m 2 ~pi ] 64] ]]

        #_[:div (/ (* 2 js/Math.PI) 64)]

        #_[m7/mx `[= A [[:m 8 c] 64] [[* 8 2 ~pi ] 64] [:p [:b [[* 360 8] 64]] ~deg] ]]


        #_[:div {:style {:background-color (hsl [1 70 70 1])
                       :padding "6px"}
               } "convert " [m7/mx `[[:m 2 ~pi] 3]] " into degree" ]


        #_[m7/mx `[=  [[:m 2 ~pi ] 3] [:m [360 3] ~deg]]]


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

           (let [r (* 10.19 20)
                 ps 32
                 angle (* js/Math.PI  (/ 1 ps))]
             [:g
              [:circle {:r r
                        :cx 0
                        :cy 0
                        :stroke-width 0.5
                        :stroke-dashoffset 0
                        :stroke-dasharray 0
                        :stroke (hsl [1 20 20 0.8])
                        :fill (hsl [0.6 95 70 0.2])}]


              #_[:circle {:r (* 2 r)
                        :cx 0
                        :cy 0
                        :stroke-width 0.5
                        :stroke-dashoffset 0
                        :stroke-dasharray 0
                        :stroke (hsl [1 20 20 0.8])
                        :fill (hsl [0.6 95 70 0.2])}]





              [:g#arcs
               (map
                (fn [se]
                  [:g
                   [:circle {:r 2.11
                             :cx (* r (js/Math.cos (* se angle)))
                             :cy (ve (* r (js/Math.sin (* se angle))))
                             :fill (hsl [(mod se 8) 70 70 0.9])}]
                   [:text {:x (* r (js/Math.cos  (* se angle)))
                           :y (ve (* r (js/Math.sin (* se  angle))))
                           :font-size 5
                           :fill (hsl [4 9 9 1])} se]])
                (range 0 (* 2 ps)))]




              [:path#rad1 {:d (str (m7/path [0 0 :l
                                             (* r 1)
                                             (ve (* r (js/Math.sin (* angle 0))))
                                             :a r r 0 false false (ve (- r (* r (js/Math.cos (* angle 8)))))  (ve (* r (js/Math.sin (* angle 8))))
                                             ])
                                   "z")

                           :fill (hsl [1 70 70 1])
                           :stroke (hsl [1 30 30 1])
                           :stroke-width 1}]


              #_[:path#rad2 {:d (str (m7/path [0 0 :l
                                             (* r 1)
                                             (ve (* r (js/Math.sin (* angle 0))))
                                             :a r r 0 false true (- r (* r (js/Math.cos (* angle 24))))  (ve (* r (js/Math.sin (* angle -8))))
                                             ])
                                   "z")

                           :fill (hsl [1 70 70 1])
                           :stroke (hsl [1 30 30 1])
                           :stroke-width 1}]




              [:path#rad5 {:d (str (m7/path [0 0 :l
                                             (* r (js/Math.cos (* angle 8))) 0
                                             0 (ve (* r (js/Math.sin (* angle 8))))


                                             ])
                                   "z")

                           :fill (hsl [2 70 70 1])
                           :stroke (hsl [2 30 30 1])
                           :stroke-width 1}]


              [:path#rad51 {:d (str (m7/path [0 0 :l
                                            (* 2 r (js/Math.cos (* angle 8))) 0
                                             0 (ve (* r 2 (js/Math.sin (* angle 8))))


                                             ])
                                   "z")

                           :fill (hsl [5 70 70 0.3])
                           :stroke (hsl [5 30 30 0.3])
                           :stroke-width 1}]




              [:path#rad6 {:d (str (m7/path [0 0 :l
                                             (* r (js/Math.cos (* angle 24))) 0
                                             0 (ve (* r (js/Math.sin (* angle 8))))


                                             ])
                                   "z")

                           :fill (hsl [2 70 70 1])
                           :stroke (hsl [2 30 30 1])
                           :stroke-width 1}]

              #_[:path#rad2 {:d (m7/path [0 0 :l
                                        (* r (js/Math.cos (* angle 3)))
                                        (ve (* r (js/Math.sin (* angle 3))))

                                        ])

                           :fill :none
                           :stroke (hsl [0.2 30 30 1])
                           :stroke-width 0.5}]



              #_[:g#arcs2
               (map
                (fn [se]
                  [:g
                   [:circle {:r 0.5
                             :cx (* r (* se angle))
                             :cy (ve (* r (js/Math.sin (* se angle))))
                             :fill (hsl [(mod se 8) 70 70 0.9])}]
                   [:text {:x (* r (js/Math.cos (* se angle)))
                           :y (ve (* r (js/Math.sin (* se angle))))
                           :font-size 5
                           :fill (hsl [4 70 70 0.4])} se]])
                (range 0 (* 2 ps)))]





              (map
               (fn [se]
                 [:g
                  [:path {:d (m7/path [(* r js/Math.PI se (/ 1 ps)) 1200 :l 0 -2400])
                          :stroke (hsl [(mod se 8) 70 70 1])
                          :stroke-width 0.3
                          :fill :none
                          }]
                  [:text {:x (* r js/Math.PI se (/ 1 ps))
                          :y 0
                          :dy 10
                          :dx -5
                          :font-size 4
                          :fill (hsl [4 10 10 0.5])
                          }
                   (mod se (* 2 ps))]
                  ])
               (range (ve (+ 1 (* 2 ps)))   (* 6 (+ 1 (* 2 ps)))))
              [:path#rad1 {:d (m7/path [  0 0 :c
                                        (* 0.2 r angle) (ve (* r 0.04))
                                        (* 7.5 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        (* 8 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        :c
                                        (* 6 r angle) (ve (* r 0.4))
                                        (* 10 r angle) (ve (* r 0.4))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 24)) (js/Math.sin (* angle 8))) ))
                                        :c
                                        (* 0.1 r angle) (ve (* r 0.07))
                                        (* 15.8 r angle) (ve (* r (+ -0.07 (- (js/Math.sin (* angle 40)) (js/Math.sin (* angle 24)))) ))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 40)) (js/Math.sin (* angle 24))) ))
                                        :c
                                        (* 6 r angle) (ve (ve (* r 0.4)))
                                        (* 10 r angle) (ve (ve (* r 0.4)))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 24)) (js/Math.sin (* angle 8))) ))
                                        :c
                                        (* 0.2 r angle) (ve (ve (* r 0.04)))
                                        (* 7.5 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        (* 8 r angle) (ve (* r (js/Math.sin (* angle 8)) ))

                                        ])

                           :fill :none
                           :stroke (hsl [0.2 30 30 0.5])
                           :stroke-width 0.5}]




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
                                   [[0.42 0 1 1]
                                    [0 0 0.59 1]
                                    [0.42 0 1 1]
                                    [0 0 0.59 1]
                                    [0.42 0 1 1]
                                    [0 0 0.59 1]
                                    [0.42 0 1 1]]))
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
                  :keyTimes (m7/sami-colon [0 0.3  0.4 0.5
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
                                           0.70 0.5  0.3  0
                                           ])
                            [1 1.1 1.2 1.5
                             1.5 1.2 1.1 1]
                         ))

                  :fill :freeze
                  }]]])






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
                               :fill (hsl [4 70 70 0.5])}]
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
                          :stroke (hsl [5 70 80 0.6])
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
                          :fill (hsl [4 70 80 0.6])
                          :transform (m7/tranfrom [[:rotate ang]])
                          :stroke (hsl [4 70 80 0.6])
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
                          :fill (hsl [4 70 80 0.6])
                          :transform (m7/tranfrom [
                                                   [:rotate ang]])
                          :stroke (hsl [4 70 80 0.6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])


                 (map
                  (fn [ang]
                    [:path {:d (m7/path [0 0 :l  0
                                         20 -20 0 0 -20 ])

                            :fill (hsl [0 70 80 0.6])
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
                              (+ r (* 0.5 r
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
                               :transform (m7/tranfrom [[:scale [0.2 0.2]]])
                               :stroke-width 3
                               :fill (hsl [3 70 70 0.5])}]





                 [:path#sin {:d
                             (m7/path [0 0 :l (* 20 adj) 0 0 (ve (* 20 op))])
                             :id :tri22
                             :stroke (hsl [1 70 70 1])
                             :stroke-width 1
                             :fill (hsl [1.5 70 70 0.5])}]])


              #_[:g {:transform (m7/tranfrom [[:translate [73 0]]])}
               [:path#angle2 {:d
                              (m7/path
                               [0 0 :l r 0 :a r r 0 false false
                                (ve (- r (* 20 adj2))) (ve (* 20  op2))])
                              :stroke (hsl [4 70 70 1])
                              :transform (m7/tranfrom [[:scale [0.2 0.2]]])
                              :stroke-width 3
                              :fill (hsl [3 70 70 0.5])}]


               [:path#sin2 {:d
                            (m7/path [0 0 :l (* 20 adj2) 0 0 (ve (* 20 op2))])
                            :id :tri222
                            :stroke (hsl [4.5 70 70 1])
                            :stroke-width 1
                            :fill (hsl [2.5 70 70 0.5])}]


               [:text

                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ 0 (* adj2 20))}

                 (str "B" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ (* 0.95 op2 20) (* adj2 20))}

                 (str "A1" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :dy 10
                            :startOffset (+ 0 0)}
                 (str "C1" "")]

                ]



               ]





              #_[:path.tri2 {:d (m7/path
                          [0 0 :l (* 0.3 adj 20) 0 0 (ve (* 20 op))])
                      :transform (m7/tranfrom
                                  [[:translate [(* 0.7 20 adj) 0]]])
                      :stroke (hsl [1 70 70 1])
                      :stroke-width 3
                      :fill (hsl [3.5 70 70 0.5])}]


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
                           :startOffset (+ 0 (* 20 0.7 adj))}

                (str "T" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* 0.95 op 20) (* adj 20))}

                (str "A" "")]




               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* 0.3 adj 20) 0)}

                #_(str "adj" "")
                adj

                ]


               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset
                           (+
                            (* 20 adj)
                            (* 0.4 20 op))}

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
               #_(fix (* (/ 180 js/Math.PI) (js/Math.asin 0.92)
                       ) 2)
               (fix (/ 180 (nth turns sec2)) 1)
               [:tspan {:dy -6}
                (name deg)]
               ]


              ])

           ])]])))


(defn freq2 []
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
    (let [zoom 0
          ax-dx 80
          ax-dy 40
          vb (fn [z]
               (nth [(map  #(* % 7) [15 -20  40 45])
                     (map  #(* % 24) [0 -25  100 50])

                     (map  #(* % 8) [-25 -40  100 62])
                     (map  #(* % 5) [20 -25  100 50])
                     (map  #(* % 8) [-50 -25  100 50])


                     ] z))
          viewbox (vb 1)
          viewbox2 (vb 0)]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 0.1])})}


       [:div {:style (m7/css
                      [[2 8 3 16 :center :center 2.4 :rem :column]
                       [1 70 90 0.1] []
                       {:gap "1rem"
                        :z-index 2}])}


        [m7/x `[= a [1 [:m ln 10]]]]

        [m7/x `[= y [:p ~pi [:m a [:p x 2]]]
                ]]

        #_[m7/x `[= [:m ln x] [:m [:k log  e] x]]]

        #_[m7/x `[= [:m [d dx] ln x] [:m [d dx] [:k log  e] x]
                [1 x]]]


        #_[m7/x `[= [:m  [:k log  10] x]  [[:m  [:k log  e] x]
                                         [:m  [:k log  e] 10]]]]


        #_[m7/x `[= [:m [d dx]  [:k log  10] x]  [:m [d dx][:b [[:m  [:k log  e] x]
                                                              [:m  [:k log  e] 10]]]]]


         ]

        #_[m7/x `[= [:m [d dx] 10 [:p e x]]  [:m 10 [d dx]  [:p e x]]]]

        #_[m7/x `[= [:m [d dx]  [:k log  10] x]  [:m [1 [:m  [:k log  e] 10]]  [d dx] [:m  [:k log  e] x] ]]




         ]

        #_[m7/x `[= [:m [d dx]  [:k log  10] x]  [:m [1 [:m  [:k log  e] 10]]  [d dx] [:m ln x] ]]


         #_[m7/x `[:m ~pi [:b [+ [1 4] [1 2] [1 2]]]]]]

        #_[m7/x `[= [:m [d dx]  [:k log  10] x]  [:m [1 [:m  [:k log  e] 10]] [ 1 x]]]


         #_[m7/x `[:m ~pi [:b [+ [1 4] [1 2] [1 2]]]]]]

        #_[m7/x `[= [:m [d dx]  [:k log  10] x]  [1 [:m x  [:k log  e] 10]]]


         #_[m7/x `[:m ~pi [:b [+ [1 4] [1 2] [1 2]]]]]]

        #_[m7/mx `[= [* ~r [:m tan [:p ~(fix (/ 180 tn) 1)
                                    ~deg]]]
                   ~opp1]]





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

           (let [r (* 10.19 20)
                 ps 32
                 angle (* js/Math.PI  (/ 1 ps))]
             [:g

              [:g#arcs
               (map
                (fn [se]
                  [:g
                   [:circle {:r 2.11
                             :cx (* r (js/Math.cos (* se angle)))
                             :cy (ve (* r (js/Math.sin (* se angle))))
                             :fill (hsl [(mod se 8) 70 70 0.9])}]
                   [:text {:x (* r (js/Math.cos  (* se angle)))
                           :y (ve (* r (js/Math.sin (* se  angle))))
                           :font-size 5
                           :fill (hsl [4 9 9 1])} se]])
                (range 0 (* 2 ps)))]




              [:path#rad1 {:d (str (m7/path [0 0 :l
                                             (* r 1)
                                             (ve (* r (js/Math.sin (* angle 0))))
                                             :a r r 0 false false (ve (- r (* r (js/Math.cos (* angle 8)))))  (ve (* r (js/Math.sin (* angle 8))))
                                             ])
                                   "z")

                           :fill (hsl [1 70 70 1])
                           :stroke (hsl [1 30 30 1])
                           :stroke-width 1}]


              #_[:path#rad2 {:d (str (m7/path [0 0 :l
                                             (* r 1)
                                             (ve (* r (js/Math.sin (* angle 0))))
                                             :a r r 0 false true (- r (* r (js/Math.cos (* angle 24))))  (ve (* r (js/Math.sin (* angle -8))))
                                             ])
                                   "z")

                           :fill (hsl [1 70 70 1])
                           :stroke (hsl [1 30 30 1])
                           :stroke-width 1}]


              (let [a (* angle 8)]
                [:g
                 [:path#rad5 {:d (str (m7/path [0 0 :l
                                                (* r (js/Math.cos a)) 0
                                                0 (ve (* r (js/Math.sin (* angle 8))))


                                                ])
                                      "z")
                              :transform (m7/tranfrom [[:scale [1 1]]])
                              :fill (hsl [2 70 70 1])
                              :stroke (hsl [2 30 30 1])
                              :stroke-width 1}]
                 [:path#rad5 {:d (str (m7/path [0 0 :l
                                                (* r (js/Math.cos (* angle 8))) 0
                                                0 (ve (* r (js/Math.sin (* angle 8))))


                                                ])
                                      "z")
                              :transform (m7/tranfrom [[:scale [1 -1]]])
                              :fill (hsl [2 70 70 1])
                              :stroke (hsl [2 30 30 1])
                              :stroke-width 1}]])


              #_[:path#rad51 {:d (str (m7/path [0 0 :l
                                            (* 2 r (js/Math.cos (* angle 8))) 0
                                             0 (ve (* r 2 (js/Math.sin (* angle 8))))


                                             ])
                                   "z")

                           :fill (hsl [5 70 70 0.3])
                           :stroke (hsl [5 30 30 0.3])
                           :stroke-width 1}]




              #_[:path#rad6 {:d (str (m7/path [0 0 :l
                                             (* r (js/Math.cos (* angle 24))) 0
                                             0 (ve (* r (js/Math.sin (* angle 8))))


                                             ])
                                   "z")

                           :fill (hsl [2 70 70 1])
                           :stroke (hsl [2 30 30 1])
                           :stroke-width 1}]

              #_[:path#rad2 {:d (m7/path [0 0 :l
                                        (* r (js/Math.cos (* angle 3)))
                                        (ve (* r (js/Math.sin (* angle 3))))

                                        ])

                           :fill :none
                           :stroke (hsl [0.2 30 30 1])
                           :stroke-width 0.5}]



              #_[:g#arcs2
               (map
                (fn [se]
                  [:g
                   [:circle {:r 0.5
                             :cx (* r (* se angle))
                             :cy (ve (* r (js/Math.sin (* se angle))))
                             :fill (hsl [(mod se 8) 70 70 0.9])}]
                   [:text {:x (* r (js/Math.cos (* se angle)))
                           :y (ve (* r (js/Math.sin (* se angle))))
                           :font-size 5
                           :fill (hsl [4 70 70 0.4])} se]])
                (range 0 (* 2 ps)))]





              (map
               (fn [se]
                 [:g
                  [:path {:d (m7/path [(* r js/Math.PI se (/ 1 ps)) 1200 :l 0 -2400])
                          :stroke (hsl [(mod se 8) 70 70 1])
                          :stroke-width 0.3
                          :fill :none
                          }]
                  [:text {:x (* r js/Math.PI se (/ 1 ps))
                          :y 0
                          :dy 10
                          :dx -5
                          :font-size 4
                          :fill (hsl [4 10 10 0.5])
                          }
                   (mod se (* 2 ps))]
                  ])
               (range (ve (+ 1 (* 2 ps)))   (* 6 (+ 1 (* 2 ps)))))
              [:path#rad1 {:d (m7/path [  0 0 :c
                                        (* 0.2 r angle) (ve (* r 0.04))
                                        (* 7.5 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        (* 8 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        :c
                                        (* 6 r angle) (ve (* r 0.4))
                                        (* 10 r angle) (ve (* r 0.4))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 24)) (js/Math.sin (* angle 8))) ))
                                        :c
                                        (* 0.1 r angle) (ve (* r 0.07))
                                        (* 15.8 r angle) (ve (* r (+ -0.07 (- (js/Math.sin (* angle 40)) (js/Math.sin (* angle 24)))) ))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 40)) (js/Math.sin (* angle 24))) ))
                                        :c
                                        (* 6 r angle) (ve (ve (* r 0.4)))
                                        (* 10 r angle) (ve (ve (* r 0.4)))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 24)) (js/Math.sin (* angle 8))) ))
                                        :c
                                        (* 0.2 r angle) (ve (ve (* r 0.04)))
                                        (* 7.5 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        (* 8 r angle) (ve (* r (js/Math.sin (* angle 8)) ))

                                        ])

                           :fill :none
                           :stroke (hsl [0.2 30 30 0.5])
                           :stroke-width 0.5}]




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
                                   [[0.42 0 1 1]
                                    [0 0 0.59 1]
                                    [0.42 0 1 1]
                                    [0 0 0.59 1]
                                    [0.42 0 1 1]
                                    [0 0 0.59 1]
                                    [0.42 0 1 1]]))
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
                  :keyTimes (m7/sami-colon [0 0.3  0.4 0.5
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
                                           0.70 0.5  0.3  0
                                           ])
                            [1 1.1 1.2 1.5
                             1.5 1.2 1.1 1]
                         ))

                  :fill :freeze
                  }]]])






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
                               :fill (hsl [4 70 70 0.5])}]
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
                          :stroke (hsl [5 70 80 0.6])
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
                          :fill (hsl [4 70 80 0.6])
                          :transform (m7/tranfrom [[:rotate ang]])
                          :stroke (hsl [4 70 80 0.6])
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
                          :fill (hsl [4 70 80 0.6])
                          :transform (m7/tranfrom [
                                                   [:rotate ang]])
                          :stroke (hsl [4 70 80 0.6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])


                 (map
                  (fn [ang]
                    [:path {:d (m7/path [0 0 :l  0
                                         20 -20 0 0 -20 ])

                            :fill (hsl [0 70 80 0.6])
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
                              (+ r (* 0.5 r
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
                               :transform (m7/tranfrom [[:scale [0.2 0.2]]])
                               :stroke-width 3
                               :fill (hsl [3 70 70 0.5])}]





                 [:path#sin {:d
                             (m7/path [0 0 :l (* 20 adj) 0 0 (ve (* 20 op))])
                             :id :tri22
                             :stroke (hsl [1 70 70 1])
                             :stroke-width 1
                             :fill (hsl [1.5 70 70 0.5])}]])


              #_[:g {:transform (m7/tranfrom [[:translate [73 0]]])}
               [:path#angle2 {:d
                              (m7/path
                               [0 0 :l r 0 :a r r 0 false false
                                (ve (- r (* 20 adj2))) (ve (* 20  op2))])
                              :stroke (hsl [4 70 70 1])
                              :transform (m7/tranfrom [[:scale [0.2 0.2]]])
                              :stroke-width 3
                              :fill (hsl [3 70 70 0.5])}]


               [:path#sin2 {:d
                            (m7/path [0 0 :l (* 20 adj2) 0 0 (ve (* 20 op2))])
                            :id :tri222
                            :stroke (hsl [4.5 70 70 1])
                            :stroke-width 1
                            :fill (hsl [2.5 70 70 0.5])}]


               [:text

                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ 0 (* adj2 20))}

                 (str "B" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ (* 0.95 op2 20) (* adj2 20))}

                 (str "A1" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :dy 10
                            :startOffset (+ 0 0)}
                 (str "C1" "")]

                ]



               ]





              #_[:path.tri2 {:d (m7/path
                          [0 0 :l (* 0.3 adj 20) 0 0 (ve (* 20 op))])
                      :transform (m7/tranfrom
                                  [[:translate [(* 0.7 20 adj) 0]]])
                      :stroke (hsl [1 70 70 1])
                      :stroke-width 3
                      :fill (hsl [3.5 70 70 0.5])}]


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
                           :startOffset (+ 0 (* 20 0.7 adj))}

                (str "T" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* 0.95 op 20) (* adj 20))}

                (str "A" "")]




               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* 0.3 adj 20) 0)}

                #_(str "adj" "")
                adj

                ]


               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset
                           (+
                            (* 20 adj)
                            (* 0.4 20 op))}

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
               #_(fix (* (/ 180 js/Math.PI) (js/Math.asin 0.92)
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
                     {:background-color (hsl [1 70 70 0.1])})}


       (if false
         [:div {:style (m7/css
                        [[2 7 2 9 :center :center
                          3.8 :rem :column]
                         [1 70 90 0.1] []
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
                         [1 70 90 0.1] []
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
                        :fill (hsl [0.6 95 70 0.5])}]


              (if true
                [:g#arcs
                 (map
                  (fn [se]
                    [:g

                     [:circle {:r 4
                               :cx (* r (js/Math.cos (* js/Math.PI se (/ 1 32))))
                               :cy (ve (* r (js/Math.sin (* js/Math.PI se (/ 1 32)))))
                               :fill (hsl [4 70 70 0.5])}]
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
                          :stroke (hsl [5 70 80 0.6])
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
                          :fill (hsl [4 70 80 0.6])
                          :transform (m7/tranfrom [[:rotate ang]])
                          :stroke (hsl [4 70 80 0.6])
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
                          :fill (hsl [4 70 80 0.6])
                          :transform (m7/tranfrom [
                                                   [:rotate ang]])
                          :stroke (hsl [4 70 80 0.6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])


                 (map
                  (fn [ang]
                    [:path {:d (m7/path [0 0 :l  0
                                         20 -20 0 0 -20 ])

                            :fill (hsl [0 70 80 0.6])
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
                              (+ r (* 0.5 r
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
                               :transform (m7/tranfrom [[:scale [0.2 0.2]]])
                               :stroke-width 3
                               :fill (hsl [3 70 70 0.5])}]





                 [:path#sin {:d
                             (m7/path [0 0 :l (* 20 adj) 0 0 (ve (* 20 op))])
                             :id :tri22
                             :stroke (hsl [1 70 70 1])
                             :stroke-width 1
                             :fill (hsl [1.5 70 70 0.5])}]])


              #_[:g {:transform (m7/tranfrom [[:translate [73 0]]])}
               [:path#angle2 {:d
                              (m7/path
                               [0 0 :l r 0 :a r r 0 false false
                                (ve (- r (* 20 adj2))) (ve (* 20  op2))])
                              :stroke (hsl [4 70 70 1])
                              :transform (m7/tranfrom [[:scale [0.2 0.2]]])
                              :stroke-width 3
                              :fill (hsl [3 70 70 0.5])}]


               [:path#sin2 {:d
                            (m7/path [0 0 :l (* 20 adj2) 0 0 (ve (* 20 op2))])
                            :id :tri222
                            :stroke (hsl [4.5 70 70 1])
                            :stroke-width 1
                            :fill (hsl [2.5 70 70 0.5])}]


               [:text

                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ 0 (* adj2 20))}

                 (str "B" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ (* 0.95 op2 20) (* adj2 20))}

                 (str "A1" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :dy 10
                            :startOffset (+ 0 0)}
                 (str "C1" "")]

                ]



               ]





              #_[:path.tri2 {:d (m7/path
                          [0 0 :l (* 0.3 adj 20) 0 0 (ve (* 20 op))])
                      :transform (m7/tranfrom
                                  [[:translate [(* 0.7 20 adj) 0]]])
                      :stroke (hsl [1 70 70 1])
                      :stroke-width 3
                      :fill (hsl [3.5 70 70 0.5])}]


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
                           :startOffset (+ 0 (* 20 0.7 adj))}

                (str "T" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* 0.95 op 20) (* adj 20))}

                (str "A" "")]




               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* 0.3 adj 20) 0)}

                #_(str "adj" "")
                adj

                ]


               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset
                           (+
                            (* 20 adj)
                            (* 0.4 20 op))}

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
               #_(fix (* (/ 180 js/Math.PI) (js/Math.asin 0.92)
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
                  :keyTimes (m7/sami-colon [0 0.3  0.4 0.5
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
                            (map #(* % 5) [0 0.50 0.7 1
                                           1 0.7 0.5 0
                                           -0.7 0.5  ])
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
                                             [0 0] [0 (* 0.5 10)]
                                             [0 (* 1 10)] [0 (* 1.5 10)]])))
               :fill :freeze
               }]
             ]
            ]


              ])



           ])]])))


(defn freq3 []
  (let [[timer update-time]
        (react/useState 0)

        sec2 (* timer js/Math.PI (/ 1 16))
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
               (nth [(map  #(* % 19) [25 -22  40 45])
                     (map  #(* % 15) [20 -22  40 45])
                     [0 -180  200 200]
                     [0 -50  100 100]

                     ] z))
          viewbox (vb 1)
          viewbox2 (vb 0)
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 24 (repeat [8 :vh]))])
                     {:background-color (hsl [1 30 70 0.4])})}



       (map
        (fn [n d]
          [:div {:style (m7/css
                         [[1 1 (+ 2 (* n 1)) 1  :center :center  1 :rem :column]
                          [(+ (/ n 10) 0.8) 70 (+ 50 (* 1 n))  0.4] [] {:gap ".1rem"
                                                                        :z-index 4}])}

           d])
        (range 0 23)
        (map (fn [ph]
               [m7/x `[:m ~ph  ~pi ]])
             [0 [1 6] [1 4] [1 3] [1 2]
              [5 6] [3 4] [2 3] 1
              [7 6] [5 4] [3 4] [3 2]
              [5 3] [(+ 9 2) 6] [(+ 6 1) 4] [(+ 6 1) 6]
              2 [13 6] [9 4] [7 3] [5 2] 3])
        )


       (map
        (fn [n d]
          [:div {:style (m7/css
                         [[2 1 (+ 2 (* n 1)) 1  :center :center  1 :rem :column]
                          [(+ (/ n 10) 0.8) 70 (+ 50 (* 1 n))  0.4] [] {:gap ".1rem"
                                                                        :z-index 4}])}

           d])
        (range 0 23)
        (map (fun
              ([[x y]]
               (/ (* 180 x) y))
              ([ph]
                   (* 180 ph))
                  )
             [0 [1 6] [1 4] [1 3] [1 2]
              [4 6] [3 4]
              [2 3] 1
              [7 6] [5 4] [3 4] [3 2]
              [5 3] [(+ 9 2) 6] [(+ 6 1) 4] [(+ 6 1) 6]
              2 [13 6] [9 4] [7 3] [5 2] 3])
        )






       #_[30 15 15 30]

       #_[0 30 45 60 90 120 135 150 180 210 235]




       [:div {:style (m7/css
                      [[2 10 3 23 :center :center 3 :rem]
                       [1 70 90 0.6]
                       []
                       {:gap "1rem"

                        :z-index 1}])}
        #_[m7/x `[= [:m h [:b t]] [:m  sin [:m ~pi t]]]]
        [m7/x `[= [:m T [:b t]] [- 20 [:m 7 cos [:m [~pi 12] 6]]]]]

        #_[m7/x `[= [:m T [:b t]] [- 20 [:m 7 cos [~pi 2]]]]]
        #_[m7/x `[= [~pi 2] [:m [~pi 12] t]]]
        #_[m7/x `[= [1 2] [:m [1 12] t]]]
        #_[m7/x `[= 6 [12 2] t]]





        #_[m7/x `[= [:m T [:b 6]] [- 20 [:m 7 cos [:m [~pi 12] 6]]]]]

        #_[m7/x `[= [:m T [:b 6]] [- 20 [* 7 1]]]]

        #_[:div "template varies from 7 deg to -7 deg"]

        #_[m7/x `[= [:m T [:b 12]] [+ 20 7]]]
        #_[m7/x `[= [:m h [:b t]] [+ 9 [:m 3 sin [[:m ~pi t] 6] ]]]]

        #_[m7/x `[= [:m h [:b 3]] [+ 9 [:m 3 sin [[* ~pi 3] 6] ]]]]


        ]





       ;; hsl(3.18rad, 42.6%, 81.6%)
       ;; [1 70 90 1]
       [:div {:style (m7/css
                      [[2 10 3 23 :center :center 3 :rem]
                       [1 70 90 0.6]
                       []
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



           #_(grid-on)



           [:path {
                   :d (path [-400 0 :l 3700 0])
                   :stroke (hsl [4 70 70 0.9])
                   :stroke-width 0.8
                   :fill :none}
            ]


           [:path {
                   :d (path [0 -400 :l 0 800])
                   :stroke (hsl [4 70 70 0.9])
                   :stroke-width 0.5
                   :fill :none}
            ]

           (map
            (fn [f]
              [:text {:x -10
                      :y (ve (* f r 20))
                      :font-size 20
                      }
               #_(+ 9 (* 3 f))
               (+ 0 f)])
            [ 0 0.5 0.707 0.866 1 -0.866  -0.707 -0.5 -1])






           (let [rad tn
                 r (* r 20)]
             [:g




              (if true
                [:g#arcs
                 #_(map
                  (fn [se]
                    [:g

                     [:circle {:r 3
                               :cx (* se 80)
                               :cy (ve (* r (js/Math.cos (* js/Math.PI se (/ 1 8)))))
                               :fill (hsl [4 70 70 1])}]
                     #_[:text {:x (* se 20)
                               :y (ve (* r (js/Math.cos (* js/Math.PI se (/ 1 32)))))
                               :font-size 10
                               }
                        se]
                     ])
                  (range (ve (* 2 32)) (* 4 32)))


                 (map
                  (fn [ran val]
                    [:g

                     [:circle {:r 6
                               :cx (* ran 80)
                               :cy (ve (* r val))
                               :fill (hsl [2 70 70 1])}]
                     #_[:text {:x (* se 20)
                               :y (ve (* r (js/Math.sin (* js/Math.PI se (/ 1 32)))))
                               :font-size 10
                               }
                        se]
                     ])

                  #_(map
                   (fn [p]
                     (* js/Math.PI p))
                   [0 (/ 1 6) (/ 1 4) (/ 3) (/ 1 2)])
                  (map (fn [s]
                         (js/Math.sin s))
                       (map
                        (fn [p]
                          (* js/Math.PI p))
                        [0 (/ 1 6) (/ 1 4) (/ 3) (/ 1 2)]))

                  )


                 (map
                  (fn [se]
                    [:g

                     [:circle {:r 6
                               :cx (* se 80)
                               :cy (ve (* r (js/Math.sin (* js/Math.PI se (/ 1 8)))))
                               :fill (hsl [2 70 70 1])}]
                     #_[:text {:x (* se 20)
                               :y (ve (* r (js/Math.sin (* js/Math.PI se (/ 1 32)))))
                               :font-size 10
                               }
                        se]
                     ])
                  (range (ve (* 2 32)) (* 4 32))

                  )

                 ])













              [:circle {:cx (* r sec2)
                        :cy (ve (* 20 op))
                        :r 5
                        :fill (hsl [5 70 70 1])}]





              ])



           ])]])))






















(defn trig2 []
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
               (nth [(map  #(* % 8) [15 -20  40 45])
                     (map  #(* % 24) [0 -25  100 50])

                     (map  #(* % 8) [-25 -30  100 52])
                     (map  #(* % 5) [20 -25  100 50])
                     (map  #(* % 8) [-50 -25  100 50])

                     (map  #(* % 4) [20 -25  100 50])


                     (map  #(* % 23) [-50 -25  100 50])
                     (map  #(* % 4) [160 -25  100 50])
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
          viewbox2 (vb 1)]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 0.1])})}

       [:div {:style (m7/css
                      [[2 8 3 10 :center :center 1.5 :rem :column]
                       [1 70 90 0.1] []
                       {:gap "1rem"


                        :z-index 2}])}

        [m7/x `[= T [:m 0.1 sec]]]
        [m7/x `[= f [1 T]]]

        [m7/x `[= f [1 0.1] [:m 10 Hz]]]

        ]


       #_[:div {:style (m7/css
                        [[2 8 3 10 :center :center 1.5 :rem :column]
                         [1 70 90 0.1] []
                         {:gap "1rem"


                          :z-index 2}])}


        [m7/x `[= [:m cos [:p 135 ~deg]] [:m cos [:p [:b [+ 90 45]] ~deg]] [- [:m cos [:p 45 ~deg]]] [- [[:sq 2] 2]]]]

        [m7/x `[= [:m sing [:p 135 ~deg]] [:m sing [:p [:b [+ 90 45]] ~deg]] [:m sin [:p 45 ~deg]] [[:sq 2] 2] ]]

        [m7/x `[= AD [:m AB sin [:p 20 ~deg]]]]

        [m7/x `[= [:m cos [:p 50 ~deg]] [[- [+ [:p 8 2] [:p 10 2]] [:p PR 2]] [* 2 8 10]]]]


        [m7/x `[= [PS [:m sin [:p 15 ~deg]]] [8 [:m sin [:p 36.585 ~deg]] ]
                ]]



        [m7/x `[= [RS [:m sin [:p 15 ~deg]]] [10.282 [:m sin [:p 36.585 ~deg]] ]
                ]]


        [m7/x `[= [:m Area PQS] [:m [1 2] (* [:b [+ 10 RS]] 8 [:m sin [:p 50 ~deg]])]]]


        [m7/x `[= [:m cos x] [11 16]]]


        [m7/x `[= x  [:m [:p cos -1] [11
                                      16]]]]

        [m7/x `[= x [:p 46.567 ~deg]]]


        [m7/x `[= [:m  cos x] [[- [+ [:p 10 2] [:p 15 2]] [:p y 2]] [* 2 10 15]]]]



        [m7/x `[= [:m  cos x] [[- [+ [:p 10 2] [:p 15 2]] [:p y 2]] [* 2 10 15]]]]


        [m7/x `[= [:p y 2]  [- ~(+ 100 (* 15 15)) [* [* 55 15] 4] ]]]


        [m7/x `[= [BD [:m cos [:p 20 *]]] AB]]
        [m7/x `[= hyp r 1 ]]
        [m7/x `[=  [+ [:p x 2] [:p x 2]]  [:p 1 2]]]

        [m7/x `[=  [:m 2 [:p x 2]] 1]]

        #_[m7/x `[=  [:p x 2] [[* 2 1] [:p 2 2]]]]

        #_[m7/x `[=  [:sq [:p x 2]] [:sq [[* 2 1] [:p 2 2]]]]]

        #_[m7/x `[=  x [[:sq 2] 2]]]
        #_[:div "Circumference is 64 unit"]

        #_[m7/x `[360 64]]
        #_[m7/x `[= c [:m 2 ~pi r]]]

        #_[m7/x `[= [c [:m 2 r]]  ~pi ]]

        #_[m7/mx `[= [c [:m 2 r]]
                 ~pi ]]

        #_[m7/mx `[= [[* 16 [:k c 1]] 64] [[* 4 [:m 2 ~pi ]] 64] [[* 4 360] 64]] ]
        #_[m7/mx `[=  [[* 4 [:m 2 ~pi ]] 64] [:p [:b [[* 4 360] 64]] *]] ]

        #_[m7/mx `[= [[* [:b [- 32 4]] [:m 2 ~pi ]] 64] [:p [:b [[* [:b [- 32 4]] 360] 64]] *]] ]

        #_[m7/mx `[=  [:m sin  [[* 4 [:m 2 ~pi ]] 64]] [:m sin [:p [:b [[* 4 360] 64]] *]]
           [:m sin [[* [:b [- 32 4]] [:m 2 ~pi ]] 64]] [:m sin [:p [:b [[* [:b [- 32 4]] 360] 64]] *]]
                 ] ]




        ;; #_[m7/mx `[= [c [:m 2 r]]  ~pi ]]
        #_[:div "if r is 1 then"]
         #_[m7/mx `[= [c [* 2 1]]  ~pi ]]
        #_[m7/mx `[= [[:m 2 c] [* 2 1]] [:m 2 ~pi] ]]

        ;; #_[m7/mx `[= r 1]]
        #_[m7/mx `[= [[* 4 [:k c 1]] 64] [[* 4 [:m 2 ~pi ]] 64] [[* 4 360] 64] ] ]

        #_[m7/mx `[= [[* 4 [:k c 1]] 64] 0.3927 [:p 22.5 *] ] ]


        #_[m7/mx `[= [c 64] [[:m 2 ~pi ] 64] ]]


        #_[m7/mx `[= [c 64] [[:m 2 ~pi ] 64] ]]

        #_[:div (/ (* 2 js/Math.PI) 64)]

        #_[m7/mx `[= A [[:m 8 c] 64] [[* 8 2 ~pi ] 64] [:p [:b [[* 360 8] 64]] ~deg] ]]


        #_[:div {:style {:background-color (hsl [1 70 70 1])
                       :padding "6px"}
               } "convert " [m7/mx `[[:m 2 ~pi] 3]] " into degree" ]


        #_[m7/mx `[=  [[:m 2 ~pi ] 3] [:m [360 3] ~deg]]]


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

           (let [r (* 10.19 20)
                 ps 32
                 angle (* js/Math.PI  (/ 1 ps))]
             [:g
              [:circle {:r r
                        :cx 0
                        :cy 0
                        :stroke-width 0.5
                        :stroke-dashoffset 0
                        :stroke-dasharray 0
                        :stroke (hsl [1 20 20 0.8])
                        :fill (hsl [0.6 95 70 0.2])}]


              #_[:circle {:r (* 2 r)
                        :cx 0
                        :cy 0
                        :stroke-width 0.5
                        :stroke-dashoffset 0
                        :stroke-dasharray 0
                        :stroke (hsl [1 20 20 0.8])
                        :fill (hsl [0.6 95 70 0.2])}]





              [:g#arcs
               (map
                (fn [se]
                  [:g
                   [:circle {:r 2.11
                             :cx (* r (js/Math.cos (* se angle)))
                             :cy (ve (* r (js/Math.sin (* se angle))))
                             :fill (hsl [(mod se 8) 70 70 0.9])}]
                   [:text {:x (* r (js/Math.cos  (* se angle)))
                           :y (ve (* r (js/Math.sin (* se  angle))))
                           :font-size 5
                           :fill (hsl [4 9 9 1])} se]])
                (range 0 (* 2 ps)))]




              [:path#rad1 {:d (str (m7/path [0 0 :l
                                             (* r 1)
                                             (ve (* r (js/Math.sin (* angle 0))))
                                             :a r r 0 false false (ve (- r (* r (js/Math.cos (* angle 8)))))  (ve (* r (js/Math.sin (* angle 8))))
                                             ])
                                   "z")

                           :fill (hsl [1 70 70 1])
                           :stroke (hsl [1 30 30 1])
                           :stroke-width 2}]


              #_[:path#rad2 {:d (str (m7/path [0 0 :l
                                             (* r 1)
                                             (ve (* r (js/Math.sin (* angle 0))))
                                             :a r r 0 false true (- r (* r (js/Math.cos (* angle 24))))  (ve (* r (js/Math.sin (* angle -8))))
                                             ])
                                   "z")

                           :fill (hsl [1 70 70 1])
                           :stroke (hsl [1 30 30 1])
                           :stroke-width 1}]




              [:path#rad5 {:d (str (m7/path [0 0 :l
                                             (* r (js/Math.cos (* angle 8))) 0
                                             0 (ve (* r (js/Math.sin (* angle 8))))


                                             ])
                                   "z")

                           :fill (hsl [2 70 70 1])
                           :stroke (hsl [2 30 30 1])
                           :stroke-width 1}]


              [:path#rad51 {:d (str (m7/path [0 0 :l
                                            (* 2 r (js/Math.cos (* angle 8))) 0
                                             0 (ve (* r 2 (js/Math.sin (* angle 8))))


                                             ])
                                   "z")

                           :fill (hsl [5 70 70 0.3])
                           :stroke (hsl [5 30 30 0.3])
                           :stroke-width 1}]




              [:path#rad6 {:d (str (m7/path [0 0 :l
                                             (* r (js/Math.cos (* angle 24))) 0
                                             0 (ve (* r (js/Math.sin (* angle 8))))


                                             ])
                                   "z")

                           :fill (hsl [2 70 70 1])
                           :stroke (hsl [2 30 30 1])
                           :stroke-width 1}]

              #_[:path#rad2 {:d (m7/path [0 0 :l
                                        (* r (js/Math.cos (* angle 3)))
                                        (ve (* r (js/Math.sin (* angle 3))))

                                        ])

                           :fill :none
                           :stroke (hsl [0.2 30 30 1])
                           :stroke-width 0.5}]



              #_[:g#arcs2
               (map
                (fn [se]
                  [:g
                   [:circle {:r 0.5
                             :cx (* r (* se angle))
                             :cy (ve (* r (js/Math.sin (* se angle))))
                             :fill (hsl [(mod se 8) 70 70 0.9])}]
                   [:text {:x (* r (js/Math.cos (* se angle)))
                           :y (ve (* r (js/Math.sin (* se angle))))
                           :font-size 5
                           :fill (hsl [4 70 70 0.4])} se]])
                (range 0 (* 2 ps)))]





              (map
               (fn [se]
                 [:g
                  [:path {:d (m7/path [(* r js/Math.PI se (/ 1 ps)) 1200 :l 0 -2400])
                          :stroke (hsl [(mod se 8) 70 70 1])
                          :stroke-width 0.3
                          :fill :none
                          }]
                  [:text {:x (* r js/Math.PI se (/ 1 ps))
                          :y 0
                          :dy 10
                          :dx -5
                          :font-size 4
                          :fill (hsl [4 10 10 0.5])
                          }
                   (mod se (* 2 ps))]
                  ])
               (range (ve (+ 1 (* 2 ps)))   (* 6 (+ 1 (* 2 ps)))))
              [:path#rad1 {:d (m7/path [  0 0 :c
                                        (* 0.2 r angle) (ve (* r 0.04))
                                        (* 7.5 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        (* 8 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        :c
                                        (* 6 r angle) (ve (* r 0.4))
                                        (* 10 r angle) (ve (* r 0.4))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 24)) (js/Math.sin (* angle 8))) ))
                                        :c
                                        (* 0.1 r angle) (ve (* r 0.07))
                                        (* 15.8 r angle) (ve (* r (+ -0.07 (- (js/Math.sin (* angle 40)) (js/Math.sin (* angle 24)))) ))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 40)) (js/Math.sin (* angle 24))) ))
                                        :c
                                        (* 6 r angle) (ve (ve (* r 0.4)))
                                        (* 10 r angle) (ve (ve (* r 0.4)))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 24)) (js/Math.sin (* angle 8))) ))
                                        :c
                                        (* 0.2 r angle) (ve (ve (* r 0.04)))
                                        (* 7.5 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        (* 8 r angle) (ve (* r (js/Math.sin (* angle 8)) ))

                                        ])

                           :fill :none
                           :stroke (hsl [0.2 30 30 0.5])
                           :stroke-width 10}]




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
                                   [[0.42 0 1 1]
                                    [0 0 0.59 1]
                                    [0.42 0 1 1]
                                    [0 0 0.59 1]
                                    [0.42 0 1 1]
                                    [0 0 0.59 1]
                                    [0.42 0 1 1]]))
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
                  :keyTimes (m7/sami-colon [0 0.3  0.4 0.5
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
                                           0.70 0.5  0.3  0
                                           ])
                            [1 1.1 1.2 1.5
                             1.5 1.2 1.1 1]
                         ))

                  :fill :freeze
                  }]]])






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
                               :fill (hsl [4 70 70 0.5])}]
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
                          :stroke (hsl [5 70 80 0.6])
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
                          :fill (hsl [4 70 80 0.6])
                          :transform (m7/tranfrom [[:rotate ang]])
                          :stroke (hsl [4 70 80 0.6])
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
                          :fill (hsl [4 70 80 0.6])
                          :transform (m7/tranfrom [
                                                   [:rotate ang]])
                          :stroke (hsl [4 70 80 0.6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])


                 (map
                  (fn [ang]
                    [:path {:d (m7/path [0 0 :l  0
                                         20 -20 0 0 -20 ])

                            :fill (hsl [0 70 80 0.6])
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
                              (+ r (* 0.5 r
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
                               :transform (m7/tranfrom [[:scale [0.2 0.2]]])
                               :stroke-width 3
                               :fill (hsl [3 70 70 0.5])}]





                 [:path#sin {:d
                             (m7/path [0 0 :l (* 20 adj) 0 0 (ve (* 20 op))])
                             :id :tri22
                             :stroke (hsl [1 70 70 1])
                             :stroke-width 1
                             :fill (hsl [1.5 70 70 0.5])}]])


              #_[:g {:transform (m7/tranfrom [[:translate [73 0]]])}
               [:path#angle2 {:d
                              (m7/path
                               [0 0 :l r 0 :a r r 0 false false
                                (ve (- r (* 20 adj2))) (ve (* 20  op2))])
                              :stroke (hsl [4 70 70 1])
                              :transform (m7/tranfrom [[:scale [0.2 0.2]]])
                              :stroke-width 3
                              :fill (hsl [3 70 70 0.5])}]


               [:path#sin2 {:d
                            (m7/path [0 0 :l (* 20 adj2) 0 0 (ve (* 20 op2))])
                            :id :tri222
                            :stroke (hsl [4.5 70 70 1])
                            :stroke-width 1
                            :fill (hsl [2.5 70 70 0.5])}]


               [:text

                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ 0 (* adj2 20))}

                 (str "B" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ (* 0.95 op2 20) (* adj2 20))}

                 (str "A1" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :dy 10
                            :startOffset (+ 0 0)}
                 (str "C1" "")]

                ]



               ]





              #_[:path.tri2 {:d (m7/path
                          [0 0 :l (* 0.3 adj 20) 0 0 (ve (* 20 op))])
                      :transform (m7/tranfrom
                                  [[:translate [(* 0.7 20 adj) 0]]])
                      :stroke (hsl [1 70 70 1])
                      :stroke-width 3
                      :fill (hsl [3.5 70 70 0.5])}]


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
                           :startOffset (+ 0 (* 20 0.7 adj))}

                (str "T" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* 0.95 op 20) (* adj 20))}

                (str "A" "")]




               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* 0.3 adj 20) 0)}

                #_(str "adj" "")
                adj

                ]


               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset
                           (+
                            (* 20 adj)
                            (* 0.4 20 op))}

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
               #_(fix (* (/ 180 js/Math.PI) (js/Math.asin 0.92)
                       ) 2)
               (fix (/ 180 (nth turns sec2)) 1)
               [:tspan {:dy -6}
                (name deg)]
               ]


              ])

           ])]])))


(defn freq []
  (let [[timer update-time]
        (react/useState 0)
        vb (fn [z]
             (nth [(map  #(* % 5) [15 -20  40 65])
                   (map  #(* % 24) [0 -25  100 50])

                   (map  #(* % 8) [-25 -30  100 52])
                   (map  #(* % 5) [20 -25  100 50])
                   ] z))
        viewbox (vb 2)
        viewbox2 (vb 0)
        ]
    (let []
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 0.1])})}

       [:div {:style (m7/css
                      [[2 8 3 10 :center :center 1.5 :rem :column]
                       [1 70 90 0.1] []
                       {:gap "1rem"


                        :z-index 2}])}



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

           (let [r (* 10.19 20)
                 ps 32
                 angle (* js/Math.PI  (/ 1 ps))]
             [:g




              [:g#arcs
               (map
                (fn [se]
                  [:g
                   [:circle {:r 2.11
                             :cx (* 20 se)
                             :cy (ve (* r (js/Math.sin (* se angle))))
                             :fill (hsl [(mod se 8) 70 70 0.9])}]
                   [:text {:x (* se 20)
                           :y (ve (* r (js/Math.sin (* se  angle))))
                           :font-size 5
                           :fill (hsl [4 9 9 1])} se]])
                (range (ve ps) (* 4 ps)))]














              (map
               (fn [se]
                 [:g
                  [:path {:d (m7/path [(* r js/Math.PI se (/ 1 ps)) 1200 :l 0 -2400])
                          :stroke (hsl [(mod se 8) 70 70 1])
                          :stroke-width 0.3
                          :fill :none
                          }]
                  [:text {:x (* r js/Math.PI se (/ 1 ps))
                          :y 0
                          :dy 10
                          :dx -5
                          :font-size 4
                          :fill (hsl [4 10 10 0.5])
                          }
                   (mod se (* 2 ps))]
                  ])
               (range (ve (+ 1 (* 2 ps)))   (* 6 (+ 1 (* 2 ps)))))




              [:path#rad1 {:d (m7/path [  0 0 :c
                                        (* 0.2 r angle) (ve (* r 0.04))
                                        (* 7.5 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        (* 8 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        :c
                                        (* 6 r angle) (ve (* r 0.4))
                                        (* 10 r angle) (ve (* r 0.4))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 24)) (js/Math.sin (* angle 8))) ))
                                        :c
                                        (* 0.1 r angle) (ve (* r 0.07))
                                        (* 15.8 r angle) (ve (* r (+ -0.07 (- (js/Math.sin (* angle 40)) (js/Math.sin (* angle 24)))) ))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 40)) (js/Math.sin (* angle 24))) ))
                                        :c
                                        (* 6 r angle) (ve (ve (* r 0.4)))
                                        (* 10 r angle) (ve (ve (* r 0.4)))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 24)) (js/Math.sin (* angle 8))) ))
                                        :c
                                        (* 0.2 r angle) (ve (ve (* r 0.04)))
                                        (* 7.5 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        (* 8 r angle) (ve (* r (js/Math.sin (* angle 8)) ))

                                        ])

                           :fill :none
                           :stroke (hsl [0.2 30 30 0.5])
                           :stroke-width 0.5}]




              ])








           ])]])))
















(defn circle []
  (let [[timer update-time] (react/useState 0)
        sec2 0
        sin (fn [t]
              (fix (js/Math.sin (/ (* t js/Math.PI) 180)) 5))

        asin (fn [t]
               (fix (js/Math.asin t) 5))

        cos (fn [t]
              (fix (js/Math.cos (/ (* t js/Math.PI) 180)) 5))

        angle {:font-size 12 :dy -5 :dx 30}
        turns (mapcat (fn [y]
                        (map
                         (fn [x]
                           (+ (/ js/Math.PI x )
                              (* y  (/ js/Math.PI 2))))
                         [6 4 3 2]))
                      [0 1 2 3])
        tn (nth turns sec2)
        tn2 (nth turns 4)
        r 18
        adj (fix (* r  (js/Math.cos tn))  2)
        op (fix (* r  (js/Math.sin tn))  2)
        opp1 (fix (* r  (js/Math.tan tn))  2)

        adj2 (fix (* r  (js/Math.cos tn))  2)
        op2 (fix (* r  (js/Math.sin tn))  2)
        opp12 (fix (* r  (js/Math.tan tn))  2)

        _ (react/useEffect
           (fn []
             (let [i (js/setInterval
                      (fn [x] (update-time
                               (mod (inc timer) 16))) 5000)]
               (fn []
                 (js/clearInterval i)))))

        [slider get-slider] (react/useState 0)
        pi 'π
        dd '☐
        deg '°
        nl '∅
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
                     [-200 -500  1200 800]
                     [-2000 -5000  12000 8000]
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-200 -500  1200 800]
                     ] z))
          viewbox (vb 2)
          viewbox2 (vb 2)
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 0.1])
                      :gap ".1rem"})}


       #_[:div {:style (m7/css
                      [[3 3 1 8 :center :center
                        1.8 :rem :column]
                       [1 70 90 0.1] []
                       {:gap "1rem"
                        :color (hsl [3 50 30 1])
                        :z-index 2}])}

        #_[m7/x `[s- C [:b [s+ A B]]]]


        #_[m7/x `[el a b ]]

        #_[m7/x `[sub a b ]]

        #_[m7/x `[++ 1 2 3]]

        ;; [m7/x `[= A [:c [++ 2 3 5 7] ] ]]

        ;; [m7/x `[= C  [:c [++ 1 4] ] ]]

        ;; [m7/x `[= [s- C A]
        ;;         [:c 0]]]



        ;; [m7/x `[sub [:c [++ 9 3 5]] B ]]

        ;; [:div "{2x: x is an  integers,  2 <= 2x <= 20}"]





        ;; [m7/x '[= M [:c [++ 1 2 4 6 8]]]]





        [m7/x '[= M [:c [++ 1 2 4 6 8]]]]

        #_[m7/x '[= N [:c [++   6 7 8 9]]]]

        [m7/x `[= [s+ M N] [:c [++ 1 2 4 6 8 7 9]]]]
        [m7/x `[= [:p  M ~deg] [:c [++ 7 9]]]]

        [m7/x `[= [s- [:p M ~deg] N] [s- [:c [++ 7 9]]
                                      [:c [++ 6 7 8 9]]]]]


        [m7/x `[= [s- [:p N ~deg] N] ~nl]]




        ]




       [:div {:style (m7/css
                      [[8 1 8 1 :center :center
                        1.8 :rem :column]
                       [1 70 90 0.1] []
                       {:gap "1rem"
                        :color (hsl [3 50 30 1])
                        :z-index 2}])}




        ]







       [:div {:style (m7/css
                      [[2 10 3 23 :center :center 3 :rem]
                       [1 70 90 1] []
                       {:gap "1rem"
                        :z-index 1}])}
        (let [rad tn
              r (* r 20)
              c1 (gensym)
              c2 (gensym)
              c3 (gensym)
              c4 (gensym)
              c5 (gensym)
              c6 (gensym)
              ca (gensym)
              cb (gensym)
              cc (gensym)
              filter1 (gensym)]
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space

                           viewbox)}




           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]

           #_(grid-on 1 1)

           [:filter {:id filter1
                     :filterUnits :userSpaceOnUse}
            [:feMorphology {:operator :dilate
                            :radius 50
                            :in :SourceGraphic
                            :result :thickness}]
            [:feComposite {:operator :out
                           :in :thickness
                           :in2 :SourceGraphic}]]


           [:filter {:id c3}
            [:feGaussianBlur {:in  :SourceGraphic
                              :stdDeviation 20
                              :result :blur}
             ]
            [:feColorMatrix {:in :blur
                             :type :matrix
                             :values (m7/space [1 0 0 0 0
                                                0 1 0 0 0
                                                0 0 1 0 0
                                                0 0 0 39 -7])
                             :result :goo} ]
            [:feComposite {:id :SourceGraphic
                           :in2 :goo
                           :operator :atop}
             ]]

           [:filter {:id c4}
            [:feOffset {:in  :SourceGraphic
                        :dx -100
                        :dy -100}]
            [:feGaussianBlur {
                              :stdDeviation 60
                              :result :dropshadow}]
            [:feColorMatrix {:in :dropshadow
                             :type :matrix
                             :values (m7/space [1 0 0 0 0
                                                0 1 0 0 0
                                                0 0 1 0 0
                                                0 0 0 0.8 0])
                             :result :fshadow} ]
            [:feMerge
             [:feMergeNode {:in :fshadow}]
             [:feMergeNode {:in :SourceGraphic}]]
            #_[:feComposite {:id :SourceGraphic
                             :in2 :goo
                             :operator :atop}
               ]
            ]

           [:clipPath {:id c1}
            [:circle {:r (* 10 r)
                      :clip-path (m7/urll (str "#" c1))
                      :cx 0
                      :cy 0
                      :fill (hsl [2.6 65 70 0.6])}]]



           #_[:circle {:r 580

                       :cx 0
                       :cy 0
                       :stroke  (hsl [2.7 70 70 1])
                       :stroke-width 0.3
                       :fill :none}]



           [:clipPath {:id c2}
            [:circle {
                      :r (* 6 r)
                      :cx (* r 10 (js/Math.cos tn))
                      :cy (ve (* r 10 (js/Math.sin tn)))
                      :fill (hsl [1 70 70 0.3])}]

            ]




           [:clipPath {:id c5}
            [:circle {:r (* 6 r)
                      :clip-path (m7/urll (str "#" c2))
                      :cx 0
                      :cy -200
                      :fill (hsl [2.6 65 70 0.4])}]

            ]


           [:g {:filter (m7/url c4)}


            (comment :clip-path (m7/urll (str "#" c2))
                     :clip-path (m7/urll (str "#" c2))
                     :clip-path (m7/urll (str "#" c2)))

            ;; a


            [:circle {:r (* 6 r)
                      :cx 0
                      :cy -200
                      :fill (hsl [2.6 65 70 0.4])}]


            ;; c
            [:circle {
                      :r (* 6 r)
                      :cx (+ 2000 (* r 10 (js/Math.cos tn)))
                      :cy 200
                      :fill (hsl [3 70 70 0.3])}]
            ;; b c
            #_[:circle {
                      :clip-path (m7/urll (str "#" c2))
                      :r (* 6 r)
                      :cx (+ 100 (* r 10 (js/Math.cos tn)))
                      :cy 200
                      :fill (hsl [3 70 70 0.3])}]

            ;; b
            [:circle {

                      :r (* 6 r)
                      :cx (* r 10 (js/Math.cos tn))
                      :cy (ve (* r 10 (js/Math.sin tn)))
                      :fill (hsl [1 60 70 0.3])}]


            #_[:circle {:r (* 6 r)
                      :clip-path (m7/urll (str "#" c2))
                      :cx 0
                      :cy -200
                      :fill (hsl [2.3 65 70 0.4])}]



            #_[:circle {:clip-path (m7/urll (str "#" c5))
                      :r (* 6 r)
                      :cx (+ 100 (* r 10 (js/Math.cos tn)))
                      :cy 200
                      :fill (hsl [3 70 70 0.3])}]






            #_[:circle {:r (* 4 r)

                        :cx -2500
                        :cy 0
                        :fill (hsl [0.6 75 70 0.8])}]


















            #_[:circle {:r (* 20 r)
                      :filter (m7/url filter1)
                      :clip-path (m7/url c5)
                      :cx -400
                      :cy 0
                      :fill (hsl [1.6 95 70 0.6])
                      }]

              #_[:circle {:r (* 3 r)
                        :cx 0
                        :cy 0
                        :fill (hsl [1.6 95 70 0.7])}]




















              #_[:g


               [:path#angle {:d
                             (m7/path
                              [0 0 :l r 0 :a r r 0 false false
                               (ve (- r (* 20 adj)))
                               (ve (* 20 op))])
                             :stroke (hsl [3 70 70 1])
                             :transform (m7/tranfrom [[:scale [0.4 0.4]]])
                             :stroke-width 3
                             :fill (hsl [3 70 70 0.5])}]


               [:path#sin {:d (m7/path [0 0 :l (* 20 adj) 0
                                        0 (ve (* 20 op))])
                           :id :tri22
                           :stroke (hsl [1 70 70 1])
                           :stroke-width 1
                           :fill (hsl [1.5 70 70 0.5])}]


               [:path#sin {:d (m7/path [0 0 :l (* 200 adj) 0
                                        0 (ve (* 200 op))])
                           :id :tri22
                           :stroke (hsl [1 70 70 1])
                           :stroke-width 1
                           :fill (hsl [1.5 70 70 0.5])}]]




            #_[:text {:x 500
                    :filter (m7/url filter1)
                    :font-size 1000
                    :dy (:dy angle)
                    :dx (:dx angle)
                    :y -100}

               "Hello world"]


            #_[:text {:x -2000
                      :font-size 200
                      :dy (:dy angle)
                      :dx (:dx angle)
                      :y -100}

               4]


            #_[:text {:x 4000
                      :font-size 200
                      :dy (:dy angle)
                      :dx (:dx angle)
                      :y -100}

               5]




              #_[:text {:x 0
                        :style {:font-size (:font-size angle)}
                        :dy (:dy angle)
                        :dx (:dx angle)
                        :y 0}
                 (name tt)
                 (fix (* (/ 180 js/Math.PI) (js/Math.asin 0.92)
                         ) 2)
                 (fix (/ 180 (nth turns sec2)) 1)
                 [:tspan {:dy -6}
                  (name deg)]
                 ]


            ]
           ])]])))


(defn circle1 []
  (let [[timer update-time] (react/useState 0)
        sec2 1
        sin (fn [t]
              (fix (js/Math.sin (/ (* t js/Math.PI) 180)) 5))

        asin (fn [t]
               (fix (js/Math.asin t) 5))

        cos (fn [t]
              (fix (js/Math.cos (/ (* t js/Math.PI) 180)) 5))

        angle {:font-size 12 :dy -5 :dx 30}
        turns (mapcat (fn [y]
                        (map
                         (fn [x]
                           (+ (/ js/Math.PI x )
                              (* y  (/ js/Math.PI 2))))
                         [6 4 3 2]))
                      [0 1 2 3])
        tn (nth turns sec2)
        tn2 (nth turns 4)
        r 18
        adj (fix (* r  (js/Math.cos tn))  2)
        op (fix (* r  (js/Math.sin tn))  2)
        opp1 (fix (* r  (js/Math.tan tn))  2)

        adj2 (fix (* r  (js/Math.cos tn))  2)
        op2 (fix (* r  (js/Math.sin tn))  2)
        opp12 (fix (* r  (js/Math.tan tn))  2)

        _ (react/useEffect
           (fn []
             (let [i (js/setInterval
                      (fn [x] (update-time
                               (mod (inc timer) 16))) 5000)]
               (fn []
                 (js/clearInterval i)))))

        [slider get-slider] (react/useState 0)
        pi 'π
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
                     [-200 -500  1200 800]
                     [-2000 -5000  12000 8000]
                     [0 -180  200 200]
                     [0 -50  100 100]
                     [0 -25  50 50]
                     [-100 -200  800 200]
                     [40 120  80 80]
                     [0 40  100 100]
                     [75 -175  150 150]
                     [-20 -20  100 100]
                     [-200 -500  1200 800]
                     ] z))
          viewbox (vb 0)
          viewbox2 (vb 2)
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 0.1])

                      :gap ".1rem"})}




       [:div {:style (m7/css
                      [[2 6 2 9 :center :center
                        1.8 :rem :column]
                       [1 70 90 0.1] []
                       {:gap "1rem"
                        :color (hsl [3 50 30 1])
                        :z-index 2}])}

        [m7/x `[= [h r] [:m sin [:p 45 ~deg]]]]

        [m7/x `[= [h 1] [:m sin [:p 45 ~deg]]]]

        [m7/x `[= h [:m sin [:p 45 ~deg]]]]

        [m7/x `[= 1 [+ [:p h 2] [:p b 2 ]]]]

        [m7/x `[= 1 [+ [:p h 2] [:p h 2 ]]]]

        [m7/x `[= 1 [:m 2 [:p h 2] ]]]

        [m7/x `[=  [1 2 ]  [:p h 2]]]
        [m7/x `[=  [1 2 ]  [:p h 2]]]

        #_[m7/x `[= DAC [:p [:b [- 90 30]] ~deg]]]


        #_[m7/x `[= BAC [:p 90 ~deg]]]


        #_[m7/x `[= BAC [+ BAD DAC] [:p 90 ~deg]]]


        #_[m7/x `[=  [+ BAD [:p 60 ~deg]] [:p 90 ~deg]]]

        #_[m7/x `[= BAD [:p 30 ~deg]]]


        #_[m7/x `[= BAD [:p 30 ~deg]]]


        #_[m7/x `[= BD [:m 20 sin [:p 30 ~deg]]]]

        #_[m7/x `[= BD [:m 20 cos B]]]


        #_[m7/x `[= AD [:m 20 cos [:p 30 ~deg]]]]


        #_[m7/x `[= BD [:m 20 sin [:p 30 ~deg]]]]





        #_[m7/x `[=
                [[:p 30 ~deg]
                 [:p 180 ~deg]]
                [x ~pi]
                ]
         ]

        #_[m7/x `[= x [~pi 6]]]

        #_[m7/x `[= [:m [1 2] [~pi 6] [:p r 2]]
                A
                ]]



        #_[m7/x `[= [:m 10 x cm] [:m 10 [~pi 6] cm]]]





        #_[m7/x `[= ~tt [:s ~(nth
                            (mapcat (fn [y]
                                      (map
                                       (fn [x]
                                         [:m y pi])
                                       [6 4 3 2]))
                                    [0 1 2 3]) timer) ]]]
        ]





       [:div {:style (m7/css
                      [[2 10 14 9 :center :center
                        2.1 :rem :column]
                       [1 70 90 0.1] []
                       {:gap "1rem"
                        :color (hsl [3 50 30 1])
                        :z-index 3}])}


        #_[m7/x `[30 180]]


        #_[m7/x `[= ~pi ~js/Math.PI]]

        #_[m7/x `[= ~pi [:m 180 ~deg]]]

        #_[m7/x `[= [~pi 180] [:m 1 ~deg]]]

        #_[m7/x `[= [* 110 [~pi 180]] [:m 110 ~deg]]]




        #_[m7/x `[= [:m sin 45 ~deg]
                [:m sin [:b [- 180 45]] ~deg]
                [:m sin 135 ~deg]]]


        #_[m7/x `[= [:m sin 110 ~deg]
                [:m sin [:b [- 180 110]] ~deg]

                [:m sin 70 ~deg]

                ~(sin 70)


                ]]

        #_[m7/x `[= [:m [:p sin -1] [1 2]] [:m [:p sin -1] 0.5]
                [:m 30 ~deg]]]

        #_[m7/x `[= [:m [:p sin -1] [[:sq 3] 2]]

                [:m [:p sin -1] ~(fix (/ (js/Math.sqrt 3) 2) 5)]


                  [:m 60 ~deg]]]

        #_[m7/x `[=  [:m [:p cos -1] [[:sq 2] 2]] [:m [:p sin -1] [[:sq 2] 2]]

                [:m [:p sin -1] ~(fix (/ (js/Math.sqrt 2) 2) 5)]

                [:m 45 ~deg]]]

        #_[m7/x `[= [:m [:p sin -1] [1 2]] [:m 30 ~deg]]]

        #_[m7/x `[= [:m [:p cos -1] 0.866] [:m 30 ~deg]]]

        #_[m7/x `[= [[:sq 3] 2]
                0.866 [:m cos 30 ~deg]]]

        #_[m7/x `[= [[:sq 3] 2]
                  0.866 [:m cos 30 ~deg]]]


        #_[m7/x `[+ [:m 2 cos  45 ~deg] [:m 3 cos  135 ~deg]]]

        #_[m7/x `[+ [:m 2 cos  45 ~deg] [:m 3 cos  [:b [- 180 45]] ~deg]]]
        #_[m7/x `[- [:m 2 cos  45 ~deg] [:m 3 cos  45 ~deg]]]
        #_[m7/x `[- [* 2 0.7] [* 3 0.7]]]
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

           #_(grid-on 1 1)




           (let [rad tn
                 r (* r 20)]
             [:g

              [:circle {:r r
                        :cx 0
                        :cy 0
                        :fill (hsl [0.6 95 70 0.1])}]


              [:circle {:r (* 10 r)
                        :cx 0
                        :cy 0
                        :fill (hsl [1.6 95 70 0.3])}]


              [:circle {:r (* 2 r)
                        :cx 0
                        :cy 0
                        :fill (hsl [1.6 95 70 0.3])}]

              [:circle {:r (* 3 r)
                        :cx 0
                        :cy 0
                        :fill (hsl [1.6 95 70 0.3])}]










              [:g#arcs
               (map
                (fn [se]
                  [:circle {:r 2
                            :cx (* r (js/Math.cos se))
                            :cy (ve (* r (js/Math.sin se)))
                            :fill (hsl [3 70 70 1])}])
                turns)]







              [:g


               [:path#angle {:d
                             (m7/path
                              [0 0 :l r 0 :a r r 0 false false
                               (ve (- r (* 20 adj)))
                               (ve (* 20 op))])
                             :stroke (hsl [3 70 70 1])
                             :transform (m7/tranfrom [[:scale [0.4 0.4]]])
                             :stroke-width 3
                             :fill (hsl [3 70 70 0.5])}]


               [:path#sin {:d (m7/path [0 0 :l (* 20 adj) 0
                                        0 (ve (* 20 op))])
                           :id :tri22
                           :stroke (hsl [1 70 70 1])
                           :stroke-width 1
                           :fill (hsl [1.5 70 70 0.5])}]


               [:path#sin {:d (m7/path [0 0 :l (* 200 adj) 0
                                        0 (ve (* 200 op))])
                           :id :tri22
                           :stroke (hsl [1 70 70 1])
                           :stroke-width 1
                           :fill (hsl [1.5 70 70 0.5])}]]










              #_[:text {:x 0
                        :style {:font-size (:font-size angle)}
                      :dy (:dy angle)
                      :dx (:dx angle)
                      :y 0}
               (name tt)
               (fix (* (/ 180 js/Math.PI) (js/Math.asin 0.92)
                       ) 2)
               (fix (/ 180 (nth turns sec2)) 1)
               [:tspan {:dy -6}
                (name deg)]
               ]


              [:circle {:r 580
                        :cx 0
                        :cy 0
                        :stroke  (hsl [2.7 70 70 1])
                        :stroke-width 0.3
                        :fill :none}]


              #_[:circle {:r (* 10 r)
                        :cx (* r 10 (js/Math.cos tn))
                        :cy (ve (* r 10 (js/Math.sin tn)))
                        :fill (hsl [1 70 70 0.3])}]


              ])])]])))
