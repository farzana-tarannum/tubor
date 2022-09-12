(ns math2.fraction
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
       (range -20 20))





      ])))

(comment

  (let [a (m7/eq2 `[[1 [1] [x]] [-1 [1] [m]]])
        b (m7/eq2 `[[1 [1] [x]] [-1 [1] [y]]])
        c (m7/eq2 `[[1 [1 1] [a x]] [1 [1 1] [a y]]])

        d (m7/eq2 `[-1 [2] [d]])
        a2 [[1 [2] [[:b (sl/symeq a)]]] d]
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
    [(m7/x ['= [:m [:b (sl/symeq b)] [:b (sl/symeq b)]]
            (sl/lawd2 b b)])

     (m7/x ['= [:m [:b (sl/symeq b)] [:b (sl/symeq b)]]
            (sl/lawd b b)])

     (m7/x ['= [:m [:b (sl/symeq b)] [:b (sl/symeq b)]]
            (sl/lawdr b b)])

     (m7/x ['= [:p [:b (sl/symeq b)] 2]
            (sl/lawdr b b)])




     #_(m7/x (sl/e=  [:m 'a [:b (sl/symeq a2)]]) )
     #_(m7/x (sl/e= [:m 'a [:b (sl/symeq2 ek1)]]))
     #_(m7/x (sl/e= [:m 'a [:b (sl/symeq ee1)]]))
     #_(m7/x ['= [cc 'a]  (sl/symeq2 ek2)])
     #_(m7/x `[= [~bb a] [- [:m 2 m]]])


     ])


  (let [ b (m7/eq2 `[[1 [1] [x]] [-1 [1] [1]]])]
    (m7/x ['= [:m [:b (sl/symeq b)] [:b (sl/symeq b)]]
           ((fn [eqk1 eqk2]
              (let [f2 (fn [[a1 b1 c1]]
                         (fn [[a b c]]
                           [a (* b1 b)
                            (merge-with (fn [e d] (+ e d)) c c1)]))
                    k (eq2 eqk1)
                    k2 (eq2 eqk2)
                    kf2 (map
                         (comp
                          f2
                          mkeq1a) k2)]
                (symeq2
                 (mapcat (fn [f]
                           (map
                            (comp
                             f
                             mkeq1a)
                            k))
                         kf2)))) b b)])))


(defn frection []
  (let [[slider get-slider] (react/useState 0)
        f (fn [n] (/ 1 n))
        tt 'θ
        dd '☐
        dx [1 0  0 -1 -1  0 0 1]
        abc (fn [a] (js/Math.abs a))
        b 5
        c 3
        d 2
        e 4
        tt 'θ
        sq (fn [n]
             (comp
              (partial map (partial * n))))]
    (let [zoom 4

          ax-dx 80
          ax-dy 40

          vb (fn [z]
               (nth [[-80 -100  250 250]
                     [-40 -100  200 200]] z))
          viewbox (vb 1)
          viewbox2 (vb 0)
          ]


      [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}


       (let [
             a (m7/eq2 `[[~b [1]  [t]] [~c [1]  [x]]])
             b (m7/eq2 `[[~d [1]  [t]] [~e [1]  [x]]])



             bbox (let [[[c1 x2 x3] [c2 y2 y3]] b]
                    [[c1 (conj x2 1) (conj x3 dd)]
                     [c2 (conj y2 1) (conj y3 dd)]])

             c (m7/eq2 `[[1 [1 1] [~dd  x]] [-2 [1 1] [ c ~dd]]])

             d (m7/eq2 `[-1 [2] [d]])
             a2 [[1 [2] [[:b (sl/symeq a)]]] d]
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
             ek2 (rest (rest ek1))]
         [:div {:style
                (m7/css
                 [[1 7 2 8 :center :flex-start 2 :rem :column]
                  [1 70 (+ 50 (* 5 5))  .7]
                  []
                  {:padding-left "25px"
                   :gap ".1rem"
                   :z-index 10}])}


          #_(m7/x [:p
                   [:b (sl/symeq b)] 2])





          #_(m7/x [:m
                   [:b (sl/symeq a)]
                 [:b (sl/symeq b)]])

          #_(m7/x (sl/symeq bbox))










          (m7/x (sl/lawdr b a))

          #_(m7/x (sl/lawd  b a))

          #_(m7/x (sl/lawd2 a b ))
          #_(m7/x [:m [:b (sl/symeq b)] dd ])


          #_(m7/x [:m [:b (sl/symeq b)] [:b (sl/symeq a)]])

          #_(m7/x ['= [:m [:b (sl/symeq b)] [:b (sl/symeq b)]]
                   (sl/lawdr b b)])

          #_(m7/x ['= [:p [:b (sl/symeq b)] 2]
                   (sl/lawdr b b)])




          #_(m7/x (sl/e=  [:m 'a [:b (sl/symeq a2)]]) )
          #_(m7/x (sl/e= [:m 'a [:b (sl/symeq2 ek1)]]))
          #_(m7/x (sl/e= [:m 'a [:b (sl/symeq ee1)]]))
          #_(m7/x ['= [cc 'a]  (sl/symeq2 ek2)])
          #_(m7/x `[= [~bb a] [- [:m 2 m]]])








          ])





       [:div {:style (m7/css
                      [[2 10 1 20 :center :center 3 :rem]

                       [1 70 90 1] [] {:gap "1rem"}])}
        (let []
          [:svg {:style {:height "100%"
                         :width "100%"}
                 :viewBox (m7/space
                           viewbox)}

           [:animate {:attributeName :viewBox
                      :to (m7/space viewbox2)
                      :dur "4s"
                      :fill :freeze}]


           (grid-on 1 1 ax-dy ax-dy)


           #_(for [j (range 0 a)
                   i (range 0 (+  b c))]
               [:path {:stroke-width 1
                       :fill (hsl [(if (< i b) 1 2) 70 70 .8])
                       :stroke (hsl [1 70 50 .8])
                       :d (m7/path
                           `[~(* i 20) ~(+ -80 (* 20 j )) :l ~@(map #(* 20 %1 %2)
                                                                    dx
                                                                    (cycle [1 1]))])}])
           [:g {:transform (m7/tranfrom [[:scale [1 -1]]
                                         [:translate [0 80]]])}
            (let [e (abc e)
                  b (abc b)
                  c (abc c)
                  d (abc d)]
                (for [i (range 0 (+ b c))
                      j (range 0 (+ d e))
                      :let [xx [(< i b ) (< j d)]
                            yy [(>= i b) (>= j d)]
                            yx [(< i b) (>= j d)]
                            xy [(>= i b) (< j d)]
                            qd (map (fn [[a b] y] [a b y]) [xx xy yx yy] [1 2 3 4])
                            [_ _ color] (first (filter (fn [[x y _]] (and x y)) qd))
                            ]
                      ]
                  [:path {:stroke-width 1
                          :fill (hsl [color 70 70 .8])
                          :stroke (hsl [1 70 50 .8])
                          :d (m7/path
                              `[~(* i 20) ~(ve (* 20 j )) :l ~@(map #(* 20 %1 %2)
                                                                    dx
                                                                    (cycle [1 1]))])}]
                  ))]

           #_(for [j (range 0 a)
                   i (range 0 (+  b c))]
               [:path {:stroke-width 1
                       :fill (hsl [(if (< i b) 1 2) 70 70 .8])
                       :stroke (hsl [1 70 50 .8])
                       :d (m7/path
                           `[~(* i 20) ~(+ -80 (* 20 j )) :l ~@(map #(* 20 %1 %2)
                                                                    dx
                                                                    (cycle [1 1]))])}])





           #_(for [j (range 3 8)
                   i (range 0 (+  b c))]
               [:path {:stroke-width 1
                       :fill (hsl [(if (< i b) 3 4) 70 70 .8])
                       :stroke (hsl [3 70 50 .8])
                       :d (m7/path
                           `[~(* i 20) ~(+ -80 (* 20 j )) :l
                             ~@(map #(* 20 %1 %2)
                                    dx
                                    (cycle [1 1]))])}])


           #_[:g
              (map
               (fn [j]
                 (map (fn [i]
                        [:path {:stroke-width 2
                                :fill (hsl [(if (< i 2) 1 2) 70 70 1])
                                :stroke (hsl [7 70 70 1])
                                :d (m7/path
                                    `[~(* i 40) ~(* 20 j ) :l ~@(map #(* 20 %1 %2)
                                                                     dx
                                                                     (cycle [2 1]))])}])
                      (range 0 5)))
               (range 0 2))]


           ]
          )]


       ])))



(defn frection2 []
  [:div {:style (merge
                     (grid [100 :vh 100 :vw
                            (take 15 (repeat [8 :vh]))
                            (take 20 (repeat [8 :vh]))])
                     {:background-color (hsl [1 70 70 1])
                      :gap ".1rem"})}

   [:div {:style
                (m7/css
                 [[1 7 10 8 :center :flex-start 4 :rem :column]
                  [2 70 (+ 50 (* 5 5))  .7]
                  []
                  {:padding-left "25px"
                   :gap ".1rem"
                   :z-index 10}])}



    [m7/x `[= [- [:m 2 x] [:m 3 y] 1 ] 0]]

    #_[m7/x `[= 0 [- [+ [:p a 2]  [:m a [:b [- 6 3]]]] [* 6 3]]]]

    #_[m7/x `[= 0 [- [+ [:p a 2]  [:m 6 a ] [:m [- 3] a]]
                 [* 6 3]]]]

    #_[m7/x `[= 0 [:m [:b [+ a 6 ]] [:b [- a 3 ]]]]]




    ]


   [:div {:style
                (m7/css
                 [[1 7 2 8 :center :flex-start 2 :rem :column]
                  [1 70 (+ 50 (* 5 5))  .7]
                  []
                  {:padding-left "25px"
                   :gap ".1rem"
                   :z-index 10}])}
    [m7/x `[= [:sq [+ [:p [:b [- [:m 2 t] 1]]
                       2]
                    [:p [:b [- 1  [:m 1 [:b [- 1 t]]]]] 2]]]
            [:sq [- 11 [:m 9 t]]]]
     ]

    [m7/x `[= [:sq [+ [:p [:b [- [:m 2 t] 1]]
                       2]
                    [:p [:b [+ 0 t]] 2]]]
            [:sq [- 11 [:m 9 t]]]]
     ]

    [m7/x `[= [:p [:sq [+ [:p [:b [- [:m 2 t] 1]]
                           2]
                        [:p [:b [+ 0 t]] 2]]] 2]
            [:p  [:sq [- 11 [:m 9 t]]] 2]]
     ]

    [m7/x `[= [+ [:p [:b [- [:m 2 t] 1]]
                  2]
               [:p t 2]]
            [- 11 [:m 9 t]]]]

    [m7/x `[= [+ [:m 6 [:p  x 2]] [- [:m 5 x] 10]] 0]]


    [m7/x `[= [+ [:p  x 2] [- [:m [5 6] x] [10 6]]] 0]]


    #_[m7/x `[= [+ [:p  x 2] [- [:m [5 6] x] [10 6]]] 0]]

    #_[m7/x `[= [+ [:p  x 2] [- [:m [5 6] x] [10 6]]]  0]]

    #_[m7/x `[= [+ [:p  x 2] [:m [5 6] x]  [- 10 10 [10 6]]]  0]]

    #_[m7/x `[= [+ [:p  x 2] [:m [5 6] x]  [- [:p [:b [5 6]] 2] [:p [:b [5 6]] 2] [10 6]]]  0]]




    #_[m7/x `[= [+ [:p  x 2] [:m 2 [5 12] x]  [- [:p [:b [5 12]] 2] [:p [:b [5 12]] 2] [10 6]]]  0]]


    #_[m7/x `[= [- [:p [:b [+ x [5 12]]] 2]  [:p [:b [5 12]] 2] [10 6]]  0]]

    #_[m7/x `[= [- [:p [:b [+ x [5 12]]] 2]  [:p [:b [16.2 12]] 2]]  0]]

    #_[m7/x `[= [:m [:b [+ x [- [5 12] [16.2 12]] ]] [:b [+ x [+ [5 12] [16.2 12]] ]]]  0]]

    #_[m7/x `[= x [- [:b [- [5 12] [16.2 12]]]] ]]








    [m7/x `[= x [- m
                 [:sq [- [:p m 2]
                       c] ]]]]

    [m7/x `[= x [+ m
                 [:sq [- [:p m 2]
                       c] ]]]]

    [m7/x `[= m [- [* [1 2] [5 6]]]
            [- [5 12]]]]

    [m7/x `[= [- [:p m 2] c] [- [25 144] [- [10 6]]]]]

    [m7/x `[= [- [:p m 2] c] [+ [25 144] [10 6]]]]

    [m7/x `[= [:sq [- [:p m 2] c]] [[:sq [* 5 ~(/ 265 5)]]
                                    12]]]


    [m7/x `[= x [+ [- [5 12]] [[:sq [* 5 ~(/ 265 5)]]
                               12]]

            [+ [- [5 12]] 1.356]]]

    [m7/x `[= x [- [- [5 12]]
                 [[:sq [* 5 ~(/ 265 5)]] 12]]
            [- [- [5 12]] 1.356]]]




    #_[m7/x `[= [:p m 2] [25 144]]]




    ]])
