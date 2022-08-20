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
     [:feFuncR {:tableValues "0 .2", :type "discrete"}]
     [:feFuncG {:tableValues "0 .2", :type "discrete"}]
     [:feFuncB {:tableValues "0 .2", :type "discrete"}]
     [:feFuncA {:tableValues "0 .2", :type "discrete"}]]
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
          viewbox2 (vb 9)
          ]
      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 .1])

                      :gap ".1rem"})}


       [:div {:style (m7/css
                        [[2 6 2 9 :center :center
                          1.8 :rem :column]
                         [1 70 90 .1] []
                         {:gap "1rem"
                          :color (hsl [3 50 30 1])
                          :z-index 2}])}

        [:div {:style (m7/css
                       [[2 6 2 9 :center :center
                         1.8 :rem :column]
                        [1 70 90 .1] []
                        {:gap "1rem"
                         :color (hsl [3 50 30 1])
                         :z-index 2}])}

         ]

        #_[:div (str " Angle " )]
        [:div (str " arc " tn)]]



       #_[:div {:style (m7/css
                        [[2 6 2 9 :center :center
                          1.8 :rem :column]
                         [1 70 90 .1] []
                         {:gap "1rem"
                          :color (hsl [3 50 30 1])
                          :z-index 2}])}

          "When a ladder of length 18 m leans against the to top edge of a window of a building. It forms a angle of 60 deg with the ground. When the ladder leans against the lower edge of the same window, it from a angle of 45 deg with ground. Calculate the height of the window. "]




       [:div {:style (m7/css
                      [[2 10 14 9 :center :center
                        1.4 :rem :column]
                       [1 70 90 .1] []
                       {:gap "1rem"
                        :color (hsl [3 50 30 1])
                        :z-index 3}])}
        #_[m7/mx `[= AC r 3]]
        #_[m7/mx `[= AB BC x]]
        [:div "In pythagorium theorum in ABC"]
        [m7/x `[= [:p 1 2]  [+ [:p [:b [1 2]] 2]  [:p x 2]]]]

        [m7/x `[= 1  [+ [1 4]  [:p x 2]]]]

        [m7/x `[= [3 4] [:p x 2]]]

        [m7/x `[= [[:sq 3] 2] x]]
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
                        :fill (hsl [.6 95 70 .5])}]



              [:circle {:r 350
                        :cx 0
                        :cy 0
                        :stroke  (hsl [0 70 70 1])
                        :stroke-width .5
                        :fill :none}]



              [:circle {:r 350
                        :cx 0
                        :cy 0
                        :stroke  (hsl [0 70 70 1])
                        :stroke-width .5
                        :fill :none}]

              [:circle {:r 25
                        :cx 470
                        :cy 0
                        :stroke (hsl [3 70 70 1])
                        :stroke-width .5
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
                        :stroke-width .5
                        :fill :none}]


              [:circle#cline2 {:r 470
                        :cx 0
                        :cy 0
                        :stroke  (hsl [2 70 70 1])
                        :stroke-width .3
                        :fill :none}]


              [:g#arcs
               (map
                (fn [se]
                  [:circle {:r 2
                            :cx (* r (js/Math.cos se))
                            :cy (ve (* r (js/Math.sin se)))
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
                          :stroke (hsl [2 70 80 .6])
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
                          :fill (hsl [4 70 80 .6])
                          :transform (m7/tranfrom [[:rotate ang]])
                          :stroke (hsl [4 70 80 .6])
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
                          :fill (hsl [4 70 80 .6])
                          :transform (m7/tranfrom [
                                                   [:rotate ang]])
                          :stroke (hsl [4 70 80 .6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])


                 (map
                  (fn [ang]
                    [:path {:d (m7/path [0 0 :l  0
                                         20 -20 0 0 -20 ])

                            :fill (hsl [0 70 80 .6])
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
                              (+ r (* .5 r
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
                             :fill (hsl [3 70 70 .5])}]


               [:path#sin {:d (m7/path [0 0 :l (* 20 adj) 0
                                        0 (ve (* 20 op))])
                           :id :tri22
                           :stroke (hsl [1 70 70 1])
                           :stroke-width 1
                           :fill (hsl [1.5 70 70 .5])}]]


              #_[:g {:transform (m7/tranfrom [[:translate [0 0]]])}
               [:path#angle2 {:d
                              (m7/path
                               [0 0 :l r 0 :a r r 0 false false
                                (ve (- r (* 20 adj2))) (ve (* 20  op2))])
                              :stroke (hsl [4 70 70 1])
                              :transform (m7/tranfrom [[:scale [.2 .2]]])
                              :stroke-width 3
                              :fill (hsl [3 70 70 .5])}]


               [:path#sin2 {:d
                            (m7/path [0 0 :l (* 20 adj2) 0 0 (ve (* 20 op2))])
                            :id :tri222
                            :stroke (hsl [4.5 70 70 1])
                            :stroke-width 1
                            :fill (hsl [2.5 70 70 .5])}]


               #_[:text

                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ 0 (* adj2 20))}

                 (str "B" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ (* .95 op2 20) (* adj2 20))}

                 (str "A1" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :dy 10
                            :startOffset (+ 0 0)}
                 (str "C1" "")]

                ]



               ]





              #_[:path.tri2 {:d (m7/path
                               [0 0 :l (* .3 adj 20) 0 0 (ve (* 20 op))])
                      :transform (m7/tranfrom
                                  [[:translate [(* .7 20 adj) 0]]])
                      :stroke (hsl [1 70 70 1])
                      :stroke-width 3
                      :fill (hsl [3.5 70 70 .5])}]


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




               #_[:textPath {:href :#tri22
                             :font-size 15
                           :startOffset (+ 0 (* 20 .7 adj))}

                (str "T" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* .95 op 20) (* adj 20))}

                (str "A" "")]




               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* .3 adj 20) 0)}

                #_(str "adj" "")
                adj

                ]


               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset
                           (+
                            (* 20 adj)
                            (* .4 20 op))}

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
               (fix (* (/ 180 js/Math.PI) (js/Math.asin .92)
                       ) 2)
               (fix (/ 180 (nth turns sec2)) 1)
               [:tspan {:dy -6}
                (name deg)]
               ]


              [:circle {:r 580
                        :cx 0
                        :cy 0
                        :stroke  (hsl [2.7 70 70 1])
                        :stroke-width .3
                        :fill :none}]])])]])))



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
                     {:background-color (hsl [1 70 70 .1])})}


       [:div {:style (m7/css
                      [[2 8 3 10 :center :center 2.1 :rem :column]
                       [1 70 90 .1] []
                       {:gap "1rem"


                        :z-index 2}])}


        #_[m7/x `[= [:m cos [:p 135 *]] [:m cos [:p [:b [+ 90 45]] *]] [- [:m cos [:p 45 *]]] [- [[:sq 2] 2]] ]]

        #_[m7/x `[= [:m sing [:p 135 *]] [:m sing [:p [:b [+ 90 45]] *]] [:m sin [:p 45 *]] [[:sq 2] 2] ]]

        #_[m7/x `[= AD [:m AB sin [:p 20 *]]]]

        #_[m7/x `[= [:m cos [:p 50 *]] [[- [+ [:p 8 2] [:p 10 2]] [:p PR 2]] [* 2 8 10]]]]


        [m7/x `[= [PS [:m sin [:p 15 *]]] [8 [:m sin [:p 36.585 *]] ]
                ]]



        [m7/x `[= [RS [:m sin [:p 15 *]]] [10.282 [:m sin [:p 36.585 *]] ]
                ]]


        [m7/x `[= [:m Area PQS] [:m [1 2] (* [:b [+ 10 RS]] 8 [:m sin [:p 50 *]] ) ]
                ]]


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
                        :stroke-width .5
                        :stroke-dashoffset 0
                        :stroke-dasharray 0
                        :stroke (hsl [1 20 20 .8])
                        :fill (hsl [.6 95 70 .2])}]


              #_[:circle {:r (* 2 r)
                        :cx 0
                        :cy 0
                        :stroke-width .5
                        :stroke-dashoffset 0
                        :stroke-dasharray 0
                        :stroke (hsl [1 20 20 .8])
                        :fill (hsl [.6 95 70 .2])}]





              [:g#arcs
               (map
                (fn [se]
                  [:g
                   [:circle {:r 2.11
                             :cx (* r (js/Math.cos (* se angle)))
                             :cy (ve (* r (js/Math.sin (* se angle))))
                             :fill (hsl [(mod se 8) 70 70 .9])}]
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

                           :fill (hsl [5 70 70 .3])
                           :stroke (hsl [5 30 30 .3])
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
                           :stroke (hsl [.2 30 30 1])
                           :stroke-width .5}]



              #_[:g#arcs2
               (map
                (fn [se]
                  [:g
                   [:circle {:r .5
                             :cx (* r (* se angle))
                             :cy (ve (* r (js/Math.sin (* se angle))))
                             :fill (hsl [(mod se 8) 70 70 .9])}]
                   [:text {:x (* r (js/Math.cos (* se angle)))
                           :y (ve (* r (js/Math.sin (* se angle))))
                           :font-size 5
                           :fill (hsl [4 70 70 .4])} se]])
                (range 0 (* 2 ps)))]





              (map
               (fn [se]
                 [:g
                  [:path {:d (m7/path [(* r js/Math.PI se (/ 1 ps)) 1200 :l 0 -2400])
                          :stroke (hsl [(mod se 8) 70 70 1])
                          :stroke-width .3
                          :fill :none
                          }]
                  [:text {:x (* r js/Math.PI se (/ 1 ps))
                          :y 0
                          :dy 10
                          :dx -5
                          :font-size 4
                          :fill (hsl [4 10 10 .5])
                          }
                   (mod se (* 2 ps))]
                  ])
               (range (ve (+ 1 (* 2 ps)))   (* 6 (+ 1 (* 2 ps)))))
              [:path#rad1 {:d (m7/path [  0 0 :c
                                        (* 0.2 r angle) (ve (* r 0.04))
                                        (* 7.5 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        (* 8 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        :c
                                        (* 6 r angle) (ve (* r .4))
                                        (* 10 r angle) (ve (* r .4))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 24)) (js/Math.sin (* angle 8))) ))
                                        :c
                                        (* .1 r angle) (ve (* r .07))
                                        (* 15.8 r angle) (ve (* r (+ -0.07 (- (js/Math.sin (* angle 40)) (js/Math.sin (* angle 24)))) ))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 40)) (js/Math.sin (* angle 24))) ))
                                        :c
                                        (* 6 r angle) (ve (ve (* r .4)))
                                        (* 10 r angle) (ve (ve (* r .4)))
                                        (* 16 r angle) (ve (* r (- (js/Math.sin (* angle 24)) (js/Math.sin (* angle 8))) ))
                                        :c
                                        (* 0.2 r angle) (ve (ve (* r 0.04)))
                                        (* 7.5 r angle) (ve (* r (js/Math.sin (* angle 8)) ))
                                        (* 8 r angle) (ve (* r (js/Math.sin (* angle 8)) ))

                                        ])

                           :fill :none
                           :stroke (hsl [.2 30 30 .5])
                           :stroke-width .5}]




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
                                   [[.42 0 1 1]
                                    [0 0 .59 1]
                                    [.42 0 1 1]
                                    [0 0 .59 1]
                                    [.42 0 1 1]
                                    [0 0 .59 1]
                                    [.42 0 1 1]]))
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
                  :keyTimes (m7/sami-colon [0 0.3  0.4 .5
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
                                           0.70 0.5  .3  0
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
                               :fill (hsl [4 70 70 .5])}]
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
                          :stroke (hsl [5 70 80 .6])
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
                          :fill (hsl [4 70 80 .6])
                          :transform (m7/tranfrom [[:rotate ang]])
                          :stroke (hsl [4 70 80 .6])
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
                          :fill (hsl [4 70 80 .6])
                          :transform (m7/tranfrom [
                                                   [:rotate ang]])
                          :stroke (hsl [4 70 80 .6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])


                 (map
                  (fn [ang]
                    [:path {:d (m7/path [0 0 :l  0
                                         20 -20 0 0 -20 ])

                            :fill (hsl [0 70 80 .6])
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
                              (+ r (* .5 r
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
                               :transform (m7/tranfrom [[:scale [.2 .2]]])
                               :stroke-width 3
                               :fill (hsl [3 70 70 .5])}]





                 [:path#sin {:d
                             (m7/path [0 0 :l (* 20 adj) 0 0 (ve (* 20 op))])
                             :id :tri22
                             :stroke (hsl [1 70 70 1])
                             :stroke-width 1
                             :fill (hsl [1.5 70 70 .5])}]])


              #_[:g {:transform (m7/tranfrom [[:translate [73 0]]])}
               [:path#angle2 {:d
                              (m7/path
                               [0 0 :l r 0 :a r r 0 false false
                                (ve (- r (* 20 adj2))) (ve (* 20  op2))])
                              :stroke (hsl [4 70 70 1])
                              :transform (m7/tranfrom [[:scale [.2 .2]]])
                              :stroke-width 3
                              :fill (hsl [3 70 70 .5])}]


               [:path#sin2 {:d
                            (m7/path [0 0 :l (* 20 adj2) 0 0 (ve (* 20 op2))])
                            :id :tri222
                            :stroke (hsl [4.5 70 70 1])
                            :stroke-width 1
                            :fill (hsl [2.5 70 70 .5])}]


               [:text

                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ 0 (* adj2 20))}

                 (str "B" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ (* .95 op2 20) (* adj2 20))}

                 (str "A1" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :dy 10
                            :startOffset (+ 0 0)}
                 (str "C1" "")]

                ]



               ]





              #_[:path.tri2 {:d (m7/path
                          [0 0 :l (* .3 adj 20) 0 0 (ve (* 20 op))])
                      :transform (m7/tranfrom
                                  [[:translate [(* .7 20 adj) 0]]])
                      :stroke (hsl [1 70 70 1])
                      :stroke-width 3
                      :fill (hsl [3.5 70 70 .5])}]


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
                           :startOffset (+ 0 (* 20 .7 adj))}

                (str "T" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* .95 op 20) (* adj 20))}

                (str "A" "")]




               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* .3 adj 20) 0)}

                #_(str "adj" "")
                adj

                ]


               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset
                           (+
                            (* 20 adj)
                            (* .4 20 op))}

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
               #_(fix (* (/ 180 js/Math.PI) (js/Math.asin .92)
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
                     {:background-color (hsl [1 70 70 .1])})}


       (if false
         [:div {:style (m7/css
                        [[2 7 2 9 :center :center
                          3.8 :rem :column]
                         [1 70 90 .1] []
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
                         [1 70 90 .1] []
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
                        :fill (hsl [.6 95 70 .5])}]


              (if true
                [:g#arcs
                 (map
                  (fn [se]
                    [:g

                     [:circle {:r 4
                               :cx (* r (js/Math.cos (* js/Math.PI se (/ 1 32))))
                               :cy (ve (* r (js/Math.sin (* js/Math.PI se (/ 1 32)))))
                               :fill (hsl [4 70 70 .5])}]
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
                          :stroke (hsl [5 70 80 .6])
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
                          :fill (hsl [4 70 80 .6])
                          :transform (m7/tranfrom [[:rotate ang]])
                          :stroke (hsl [4 70 80 .6])
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
                          :fill (hsl [4 70 80 .6])
                          :transform (m7/tranfrom [
                                                   [:rotate ang]])
                          :stroke (hsl [4 70 80 .6])
                          :stroke-width 3}])
                [(ve (/ 180 rad))])


                 (map
                  (fn [ang]
                    [:path {:d (m7/path [0 0 :l  0
                                         20 -20 0 0 -20 ])

                            :fill (hsl [0 70 80 .6])
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
                              (+ r (* .5 r
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
                               :transform (m7/tranfrom [[:scale [.2 .2]]])
                               :stroke-width 3
                               :fill (hsl [3 70 70 .5])}]





                 [:path#sin {:d
                             (m7/path [0 0 :l (* 20 adj) 0 0 (ve (* 20 op))])
                             :id :tri22
                             :stroke (hsl [1 70 70 1])
                             :stroke-width 1
                             :fill (hsl [1.5 70 70 .5])}]])


              #_[:g {:transform (m7/tranfrom [[:translate [73 0]]])}
               [:path#angle2 {:d
                              (m7/path
                               [0 0 :l r 0 :a r r 0 false false
                                (ve (- r (* 20 adj2))) (ve (* 20  op2))])
                              :stroke (hsl [4 70 70 1])
                              :transform (m7/tranfrom [[:scale [.2 .2]]])
                              :stroke-width 3
                              :fill (hsl [3 70 70 .5])}]


               [:path#sin2 {:d
                            (m7/path [0 0 :l (* 20 adj2) 0 0 (ve (* 20 op2))])
                            :id :tri222
                            :stroke (hsl [4.5 70 70 1])
                            :stroke-width 1
                            :fill (hsl [2.5 70 70 .5])}]


               [:text

                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ 0 (* adj2 20))}

                 (str "B" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :startOffset (+ (* .95 op2 20) (* adj2 20))}

                 (str "A1" "")]


                [:textPath {:href :#tri222
                            :font-size 10
                            :dy 10
                            :startOffset (+ 0 0)}
                 (str "C1" "")]

                ]



               ]





              #_[:path.tri2 {:d (m7/path
                          [0 0 :l (* .3 adj 20) 0 0 (ve (* 20 op))])
                      :transform (m7/tranfrom
                                  [[:translate [(* .7 20 adj) 0]]])
                      :stroke (hsl [1 70 70 1])
                      :stroke-width 3
                      :fill (hsl [3.5 70 70 .5])}]


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
                           :startOffset (+ 0 (* 20 .7 adj))}

                (str "T" "")]

               [:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* .95 op 20) (* adj 20))}

                (str "A" "")]




               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset (+ (* .3 adj 20) 0)}

                #_(str "adj" "")
                adj

                ]


               #_[:textPath {:href :#tri22
                           :font-size 10
                           :startOffset
                           (+
                            (* 20 adj)
                            (* .4 20 op))}

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
               #_(fix (* (/ 180 js/Math.PI) (js/Math.asin .92)
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
                  :keyTimes (m7/sami-colon [0 0.3  0.4 .5
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
                            (map #(* % 5) [0 .50 .7 1
                                           1 .7 .5 0
                                           -0.7 .5  ])
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
                                             [0 0] [0 (* .5 10)]
                                             [0 (* 1 10)] [0 (* 1.5 10)]])))
               :fill :freeze
               }]
             ]
            ]


              ])



           ])]])))
