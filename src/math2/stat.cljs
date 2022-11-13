(ns math2.stat
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
   [applied-science.js-interop :as j]
   [math2.math7 :as m7 :refer
    [grid hsl css space size path ve sec]]))

(defn fix [fr n]
  (js/parseFloat (.toFixed fr n)))

(defn fixint [fr n]
  (js/parseInt (.toFixed fr n)))


(defn grid-on [X Y ax-dx ax-dy frac]
  [:g
      ;; small
   (map-indexed
       (fn [index i]
         [:path {:key (str "grid-onadfadsf" index)
                 :d (path [-400 (ve (+ ax-dx (* i 4))) :l 1200 0])
                 :stroke (hsl [4 70 70 0.5])
                 :stroke-width 0.5
                 :fill :none}
          ] )
       (range 0 5))

      (map-indexed
       (fn [index i]
         [:path {:key (str "grid-onsdfasd2" index)
                 :d (path [(+ ax-dy (* i 4)) -400 :l 0 1200 ])

                 :stroke (hsl [4 70 70 0.5])
                 :stroke-width 0.5
                 :fill :none}
          ] )
       (range 0 5))

   (map-indexed

    (fn [i x]
      (let [t1 (if frac
                 (.toFixed (* (/ x 2) X) 1)
                 (if (= x 0 ) 0
                     (* (/ x 2) X)))
            t (if (= (mod i 2) 1) (+ 135 t1 ) "")]
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
          ;;; imp
         [:text {:x (* 20 x)
                 :fill (hsl [0 40 20 1])
                 :y 4.5
                 :font-size 5} t]]))
       (range -20 40))



      (map-indexed
       (fn [i y]
         (let [t (if (= (mod i 2) 0)
                   (if frac (.toFixed (* (/ y 2) Y) 1) (* (/ y 2) Y))
                   "")
               ]
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
             t
             ]


            [:path {:d (path [0 (ve (* 20 y))   :l -1200 0])

                    :stroke (hsl [1 70 70 1])
                    :stroke-width 1
                    :fill :none}
             ]

            ]))
       (range -20 20))])
(defn green []
  [:g
   [:path#green {
                 :d (m7/path [0 -30 :l 0 (ve (* 1 20 ))
                              (* 10 20) 0 0 (* 1 20 )
                              (* 10 -20) 0])
                 :stroke-width 1
                 :fill (hsl [2 70 70 0.3])}
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
           :fill (hsl [2 70 70 0.3])}]

   ])

(defn grad []
  [:radialGradient {
                             :id (name :lg2)
                             :gradientTransform (m7/tranfrom [[:rotate 0]])}
            [:stop  {:offset 0
                     :stop-color (hsl [3 40 40 0.7])}]
            [:stop  {:offset 0.55
                     :stop-color (hsl [3.3 60 60 0.3])}]


            [:stop  {:offset 0.97
                     :stop-color (hsl [1 70 70 0.2])}
             [:animate {:attributeName :offset
                        :id :f114
                        :begin 0
                        :from 0.55
                        :to 1
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]

             [:animate {:attributeName :offset
                        :begin :f114.end
                        :from 1
                        :to 0.55
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin 0
                        :id :f115
                        :from (hsl [1 90 80 0.2])
                        :to (hsl [1 90 80 0.8])
                        :dur (m7/not-space [120 "s"])
                        :repeatCount :indefinite}]
             [:animate {:attributeName :stop-color
                        :begin :f115.end
                        :from (hsl [1 90 80 0.2])
                        :to (hsl [1 90 80 0.8])
                        :dur (m7/not-space [13 "s"])
                        :repeatCount :indefinite}]]

            ])


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
        scale2 [[0.5 0.5] [-0.5 0.5]]
        [xx set-xx] (react/useState 2)
        [yy set-yy] (react/useState 3)

        [c ck] (react/useState [0 0])
        p (fn [svg x y]
            (let [p (js/DOMPoint. x y)
                  t (j/call (j/call svg :getScreenCTM)  :inverse)
                  xy (-> p
                         (.matrixTransform t))]
              (ck [(fix (/ (-> xy .-x) 2) 1)
                   (fix (* -1 (/ (-> xy .-y) 2)) 1) ])))]
    [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 24 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 0.5])
                      :gap ".2rem"})}
     [:div
      {:style (m7/css
               [[3 1 8 11  :center :center 2.2 :rem ]
                [2 70 90 0.4] [] {:gap "1rem"
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
                [2 70 90 0.4] [] {:gap "1rem"
                                 :z-index 2}])}






      ]







     [:div {:style (m7/css
                    [[2 10 2 23 :center :center 3 :rem]
                     [1 70 90 1] [] {:gap "1rem"
                                     :z-index 1}])}

      (let [

            ma (/ 2 7)
            leq (fn [[m x1 y1]]
                  (fn [x]
                    (+ (* m (- x x1)) y1)))

            maeq (leq [ma (* 2 20) (* 20 3)])

            ]
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




         (grid-on 10 25 (* xx 20) (* yy 20) false)


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
                  :stroke-width 0.1
                  :transform (m7/tranfrom [[:rotate 0]])
                  :fill (m7/hsl [0.4 70 70 1])}]]

         [:g

          [:path {:d (m7/path [0 0 :l 2 10 2 -20 2 10])
                  :stroke-width 1
                  :marker-end (m7/url (name :mb2))
                  :marker-start (m7/url (name :mb2))
                  :stroke (hsl [2 30 30 1])
                  :fill :none}]





          [:path {:d (m7/path [40 0 :l (* 2 20) (ve (* 6 20)) 80 -40])
                  :stroke-width 1
                  :marker-end (m7/url (name :mb2))
                  :marker-start (m7/url (name :mb2))
                  :stroke (hsl [2 30 30 1])
                  :fill :none}]

          #_[:path {:d (m7/path [40 0 :l (* 2 20) (ve (* 6 20))

                                 0 (* 6 20) (ve (* 20 6)) 0 ])
                  :stroke-width 1
                  :marker-end (m7/url (name :mb2))
                  :marker-start (m7/url (name :mb2))
                  :stroke (hsl [2 30 30 1])
                  :fill (hsl [2 70 70 0.5])}]


          #_[:path {:d (m7/path [80 (ve (* 20 6)) :l (* 4 20) (ve (* 2 20))
                               0 (* 20 2)
                               (ve (* 4 20)) 0
                               ])
                  :stroke-width 1
                  :marker-end (m7/url (name :mb2))
                  :marker-start (m7/url (name :mb2))
                  :stroke (hsl [1 30 30 1])
                  :fill (hsl [1 70 70 0.5])}]



          [:path {:d (m7/path [20 0 :l (* 3 20) (ve (* 2 20)) (* 5 20) (ve (* 6 20))])
                  :stroke-width 1
                  :marker-end (m7/url (name :mb2))
                  :marker-start (m7/url (name :mb2))
                  :stroke-dasharray (str/join " " [5 2])
                  :stroke (hsl [2 30 30 1])
                  :fill :none}]


          [:path {:d (m7/path [20 0 :l
                               (* 3 20) (ve (* 2 20))
                               (* 4 20) (ve (* 2 20))
                               0 (* 20 4)])
                  :stroke-width 1
                  :marker-end (m7/url (name :mb2))
                  :marker-start (m7/url (name :mb2))
                  :stroke-dasharray (str/join " " [2 1])
                  :stroke (hsl [2 30 30 1])
                  :fill (hsl [1  70 60 0.5])}]





          #_[:path#bma1 {:d (m7/path [-200 (ve (maeq -200)) :L 1200 (ve (maeq 1200))])
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
           "P(2,3)"]

          ]




         ])
      ]]))
