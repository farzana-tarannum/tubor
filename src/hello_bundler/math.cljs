(ns hello-bundler.math
   (:require
    [react]
    [clojure.string :as str]
    [clojure.test.check.generators :as gen2]
    [clojure.spec.gen.alpha :as gen]
    [clojure.spec.alpha :as s]
    [hello-bundler.bdmap :as bmap]
    [hello-bundler.file :as file]
    [defun.core :refer [defun fun]]
    [moment]
    [hello-bundler.math-test :as mt]))



(s/def :math2/point
  (s/tuple
   (s/tuple
    (s/and
     number?
     (fn [n] (and (> n -51)) (< n 51)))
    (s/and
     number?
     (fn [n] (and (> n -51)) (< n 51))))
   (s/tuple
    (s/and
     number?
     (fn [n] (and (> n -51)) (< n 51)))
    (s/and
     number?
     (fn [n] (and (> n -51)) (< 5 21))))))

(s/def :math2/svg-point
  (s/cat
   :x (s/tuple
       (s/and
        number?
        (fn [n] (and (> n -51)) (< n 51)))
       (s/and
        number?
        (fn [n] (and (> n -51)) (< n 51))))
   :y
   (s/tuple
    (s/and
     number?
     (fn [n] (and (> n -51)) (< n 51)))
    (s/and
     number?
     (fn [n] (and (> n -51)) (< n 51))))
   ))

(s/def :math2/r
  (s/and number?
         (fn [n]
           (and (> n 0)
                (< n 400)))))

(s/def :math2/circle
  (s/tuple
   :math2/point
   (s/or
    :r :math2/r
    :text string?)))

(s/def :math2/svg-circle
  (s/cat
   :point :math2/svg-point
   :attr (s/or
          :r :math2/r
          :text string?)))

(s/def :math2/path
  (s/tuple
   :math2/point
   (s/+ (s/alt
         :line (s/tuple
                #{:l :L}
                (s/+ :math2/point))
         :curve-c
         (s/tuple
          #{:c :C}
          (s/+ :math2/point))
         :curve-q
         (s/tuple
          #{:q :Q}
          (s/+ :math2/point))

         :arc (s/tuple
               #{:a :A}
               :math2/r
               :math2/r
               number?
               boolean? boolean? :math2/point )))))

(s/def :math2/svg-path
  (s/cat
   :m :math2/svg-point
   :d (s/+
       (s/alt
        :line (s/cat
               :l #{:l :L}
               :points (s/+ :math2/svg-point))
        :curve-c
        (s/cat
          :c #{:c :C}
          :points (s/+ :math2/svg-point))
         :curve-q
         (s/cat
          :q #{:q :Q}
          :points (s/+ :math2/svg-point))

         :arc (s/cat
               :a #{:a :A}
               :r1 :math2/r
               :r2 :math2/r
               :angle number?
               :f1 boolean?
               :f2 boolean?
               :end :math2/svg-point )))))

(defn svg-path [e1]
  (map
   (comp
    (fn [e]
      (if (s/valid? :math2/path e)
        e))
    (juxt
     (comp
      (juxt :x :y)
      :m)

     (comp
      (fn  [l]
        (map

         (comp
          (fun
           ([[:line p]]
            ((juxt :l
                   (comp
                    (fn [l]
                      (map
                       (juxt :x :y)
                       l))
                    :points) ) p)
            )
           ([[:arc  x]]
            (let [{:keys [a r1 r2 angle f1 f2  end]} x]
              [a r1 r2 angle f1 f2 ((juxt :x :y) end)]))
           ([[:curve-c  c]]
            ((juxt :c
                   (comp
                    (fn [l]
                      (map
                       (juxt :x :y)
                       l))
                    :points) ) c)
            )
           ([[:curve-q  q]]
            ((juxt :q
                   (comp
                    (fn [l]
                      (map
                       (juxt :x :y)
                       l))
                    :points) ) q))
           ))
         l))
      :d))

    (fn [x]
      (s/conform :math2/svg-path x)))
   e1))


(comment
  (svg-path (gen/sample (s/gen :math2/svg-path 2))))


(s/def ::gpath
  (s/cat
   :m ::gpoint
   :lines ::glines))

(s/def ::gpath2
  (s/cat
   :m ::gpoint
   :lines
   (s/+ ::glines)))



(defn  path [transform]
  (fun [{:m m
         :lines {:l l
                 :points points}}]
   (str/join " "
             ["M"
              (transform m)
              (name l)
              (str/join " " (map transform points))])))

(s/def ::hsl
  (s/cat
   :hue number?
   :saturation number?
   :lighness number?))



(s/def ::hsl2
  (s/cat
   :hue (s/and number? (fn [r]
                         (and (< r 7)
                              (> r -7))))
   :saturation (s/and int? (fn [s]
                             (and
                              (< s 101)
                              (> s -1))
                             ))
   :lighness (s/and int? (fn [s]
                           (and
                            (< s 101)
                            (> s -1))
                           ))
   :opacity (s/and number?
                   (fn [num]
                     (and (> num 0)
                          (< num 1.01))))))


(s/def ::gradient
  (s/cat
   :angle (s/and number?
               (fn [a]
                 (and (> a -8)
                      (< a 8))))
   :colors
   (s/+ (s/cat
         :size (s/cat
                :size number?
                :scale #{:rem :px :vh :vw :%})
         :color ::hsl2))))
(comment


  )
(defn gen-grad [v]
  ((comp
    (fn [x] (str "linear-gradient" x) )
    (fn [x] (str "(" x ")") )
    (fn [[x y]] (str x "rad , " y))
    (juxt :angle
          (comp
           (partial str/join " , ")
           (fn [v]
             (map
              (comp
               (partial str/join " ")
               (juxt

                (comp
                 (partial str/join "")
                 (juxt (comp
                        name
                        (fn [e]
                          (if (= e 3) :hsl :hsla))
                        (partial count))
                       (comp
                        (fn [a]
                          (str "(" a ")"))
                        (partial str/join ",")))
                 (partial remove nil?)
                 (juxt (comp
                        (fn [n] (str n "rad"))
                        :hue)
                       (comp
                        (fn [n] (str n "%"))
                        :saturation)
                       (comp
                        (fn [n] (str n "%"))
                        :lighness) :opacity)
                 :color)

                (comp
                 (comp
                  (partial str/join "")
                  (fn [[x y]] [x
                               (if (keyword? y)
                                 (name y) "")])
                  (juxt :size
                        :scale))
                 :size)

                ))  v))
           :colors))
    (fn [e] (s/conform ::gradient e)))     v)
  )


(comment
  (nth  (gen/sample (s/gen ::gradient 10)) 3)

  (gen-grad (s/conform
             ::gradient
             [1
              -2 :% 10 70 70
              ])))

(comment
  (gen/sample
   (s/gen ::hsl2 10)))

(comment
  (map
   (comp
    (juxt :angle
          (comp
           (fn [c]
             (map
              (juxt (comp
                     first
                     :color)
                    :size)
              c))
           :colors))
    (fn [e] (s/conform ::gradient e)))
   (gen/sample (s/gen ::gradient 10))))



(comment
  (map
   (comp
    (fn [s] (str/join "" s))
    (fun ([[:hsla {:hsl hsl
                   :opacity p}]]
          [(name :hsla)
           ((comp
             (fn [n] (str "("
                          n
                          ")") )
             (fn [[x y]]
               (str/join ","
                [x y]))

             (juxt
              (comp
               (fn [x] (str/join "," x))
               (juxt :hue :saturation :lighness)
               first)
              second))
            [hsl p])
           ])
         ([[:hsl hsl]]
          [(name :hsl)

           ( (comp
              (fn [s] (str "(" s ")"))
              (fn [x] (str/join "," x))
              (juxt :hue :saturation :lighness)
             )
            hsl)]))

    (fn [n]
      (s/conform ::hsl3 n)))
   (gen/sample (s/gen ::hsl3 10))))

(comment
  (first
   (map
    (fn [e] (s/conform ::hsl3 e))
    (gen/sample (s/gen ::hsl3 10))))

  (s/conform ::hsl3 [70 70 70 .3])


  )



(defn hsl
  [{:keys [hue saturation lighness]}]
  (let [s {:hsl "hsl"
           :bracket-start "("
           :bracket-end ")"
           :comma ","
           :% "%"
           }]
    (str
     (:hsl s)
     (:bracket-start s)
     hue
     (:comma s)
     saturation
     (:% s)
     (:comma s)
     lighness
     (:% s)
     (:bracket-end s))))



(s/def ::size
  (s/+ (s/cat
        :size number?
        :scale #{:rem :px :fr :vh :vw :%}
        :name (s/?
               keyword?))))


(s/def ::mark-area
  (s/cat
   :row int?
   :row-span int?
   :col int?
   :col-span int?))





(defn mark-area [area]
  (let [{:keys [row row-span col col-span]} (s/conform ::mark-area area)]
    (str/join "/" [row row-span
                   col  col-span]))
  )

(comment (s/valid? ::size [1 :% ]))


(defn c [a]
  (hsl
   (s/conform ::hsl a)))




(defn size [s]
  (str/join " "
   (map (fn [{:keys [size scale nm]}]
          (str/join "" [size (name scale) nm]))
        (s/conform ::size s))))




(comment

  (size [.1 :rem
         2 :vw
         10 :vh :hello
         ]))





(defn circles [plot-e1 [t-s t-x t-y]]
  (let [ space (fn [p] (str/join " " p))]
    (map
     (comp
      (fun ([[[x y] [:r r]]]
            [:circle {:cx x
                      :cy y
                      :r 2
                      :fill (c [270 70 70])
                      :stroke-width .2
                      :stroke (c [330 70 70])}])
           ([[[x y] [:text r]]]
            [:text {:x x
                    :y y
                    :style {:font-size (size [0.5 :rem])}
                    } r]))
      (juxt (comp
             (juxt (comp
                    t-x
                    first)
                   (comp
                    t-y
                    second))
             first)
            (comp
             second))
      (fn [x]
        (s/conform :math2/circle x))
      )
     plot-e1)))

(comment
  (let
      [[t-s t-x t-y]
       (let [t-s (comp
                  (fn [[x y]] (+ x y))
                  (juxt (comp (fn [x] (* x 30))  first)
                        (comp (fn [x] (* x 6) ) second)))]
         [t-s
          (comp (fn [x] (+ (* 40 (+ 0 4)) x))
                t-s)
          (comp (fn [y]
                  (- (+ (* 40 (- 12 4)) 0) y))
                t-s)
          ]
         )
       data (map
             (fn [x]
               [[[x
                  0]
                 [(/ (- 9 (* 2 x) ) 3)
                  0]]
                2])
             (range 1 5))]

    (circles (gen/sample (s/gen :math2/circle 10 ))
             [t-s t-x t-y])))






(defn paths [svg-pathf [t-s t-x t-y]]
  (fn [pts]
    (let [
          space (fn [p] (str/join " " p))
          ]
      (map
       (comp
        svg-pathf
        (fn [x] (str "M" x))
        space
        (juxt (comp
               space
               (juxt (comp
                      t-x
                      first)
                     (comp
                      t-y
                      second))
               first)
              (comp
               space
               (comp
                (fn [l]
                  (map (fun
                        ([[:arc [:a r1 r2
                                 angle
                                 f1 f2
                                 point]]]
                         (space
                          [
                           (name :a) r1 r2
                           angle
                           (if f1 1 0)
                           (if f2 1 0)
                           ((comp
                             space
                             (juxt (comp
                                    t-s
                                    first)
                                   (comp
                                    (partial * -1)
                                    t-s
                                    second)))
                            point)

                           ]))
                        ([[:arc [:A r1 r2 angle f1 f2
                                 point]]]
                         (space [(name :A) r1 r2 angle (if f1 1 0) (if f2 1 0)
                                 ((comp
                                   space
                                   (juxt (comp
                                          t-x
                                          first)
                                         (comp
                                          t-y
                                          second)))
                                  point)])
                         )
                        ([[:line [:l points]]]
                         ((comp
                           space
                           (juxt first (comp
                                        space
                                        second)))
                          [(name :l)
                           (map
                            (comp
                             space
                             (juxt (comp
                                    t-s
                                    first)
                                   (comp
                                    (partial * -1)
                                    t-s
                                    second)))
                            points)])
                         )
                        ([[:curve-c [:c points]]]
                         ((comp
                           space
                           (juxt first (comp
                                        space
                                        second)))
                          [(name :c)
                           (map
                            (comp
                             space
                             (juxt (comp
                                    t-s
                                    first)
                                   (comp
                                    (partial * -1)
                                    t-s
                                    second)))
                            points)])
                         )
                        ([[:curve-q [:q points]]]
                         ((comp
                           space
                           (juxt first (comp
                                        space
                                        second)))
                          [(name :q)
                           (map
                            (comp
                             space
                             (juxt (comp
                                    t-s
                                    first)
                                   (comp
                                    (partial * -1)
                                    t-s
                                    second)))
                            points)])
                         )
                        ([[:line [:L points]]]
                         ((comp
                           space
                           (juxt first (comp
                                        space
                                        second)))
                          [(name :L)
                           (map
                            (comp
                             space
                             (juxt (comp
                                    t-x
                                    first)
                                   (comp
                                    t-y
                                    second)))

                            points)]))
                        ([[:curve-c [:C points]]]
                         ((comp
                           space
                           (juxt first (comp
                                        space
                                        second)))
                          [(name :C)
                           (map
                            (comp
                             space
                             (juxt (comp
                                    t-x
                                    first)
                                   (comp
                                    t-y
                                    second)))

                            points)]))
                        ([[:curve-q [:Q points]]]
                         ((comp
                           space
                           (juxt first (comp
                                        space
                                        second)))
                          [(name :Q)
                           (map
                            (comp
                             space
                             (juxt (comp
                                    t-x
                                    first)
                                   (comp
                                    t-y
                                    second)))

                            points)]))
                        )
                       l)))
               second))
        (fn [x]
          (s/conform :math2/path x)))
       (svg-path
        pts)

       ))))





;; 01741775277 ethan mom maliha
;; 01716980473  angira mom maliha



(s/def ::op-plus '#{+})
(s/def ::op-equal '#{=})

(s/def ::op-mul '#{*})
(s/def ::op-mul2 #{:m})
(s/def ::op-b #{:b :c :s})

(s/def ::op-minus '#{-})
(s/def ::op-sqroot #{:sq})
(s/def ::op-pow #{:p})
(s/def ::op-sub  #{:s})

(s/def ::betas (s/or
               :α #{:alpha}
               :β #{:beta}))


(s/def ::ops (s/or
              :+ ::op-plus
              :> '#{>}
              :< '#{<}
              :<= '#{<=}
              :>= '#{>=}
              :- ::op-minus
              :× ::op-mul
              := ::op-equal
              :op-mul2 ::op-mul2))



(s/def ::element
  (s/or
   :mn number?
   :betas ::betas
   :mi symbol?
   :expr ::expr))

(s/def ::element2
  (s/or
   :mn number?
   :mi symbol?
   :expr ::expr2))

(s/def ::p-exp
  (s/cat
   :mo ::ops
   :first-elem ::element
   :elem (s/+ ::element)))



(s/def ::m-exp
  (s/cat
   :mo (s/or :- ::op-minus
             :sq ::op-sqroot)
   :elem ::element))


(s/def ::b-exp
  (s/cat
   :mo ::op-b
   :elem ::element))

(s/def ::f-exp
  (s/cat
   :elem-left ::element
   :elem-right ::element))

(s/def ::e-exp
  (s/cat
   :mo (s/or
             :p ::op-pow
             :k #{:k}
             :s ::op-sub)
   :elem-left ::element
   :elem-right ::element))


(s/def ::expr
  (s/or
   :p-exp ::p-exp
   :b-exp ::b-exp
   :m-exp ::m-exp
   :e-exp ::e-exp
   :f-exp ::f-exp))

(s/def ::expr2
  (s/alt
   :p-exp ::p-exp
   :m-exp ::m-exp
   :f-exp ::f-exp))



(declare expr)



(defn p-exp [{:keys [mo first-elem elem ]}]
  (reduce
   (fn [acc e]
     (let [[exp elem] e]
       (conj
        (if (= (first mo)
               :op-mul2 )
          acc
          (conj acc [:mo (name (first mo))]))
        (if (= exp :expr)
          (expr e)
          [exp (str elem)])))
     )
   [:mrow
        (let [[exp elem] first-elem]
          (if (= exp :expr) (expr first-elem) first-elem))]
   elem))

(defn m-exp [{:keys [mo elem]}]
  (let [[op-a op] mo]
    (cond
      (= op-a :-)
      [:mrow
       [:mo "-"]
       (let [[exp e] elem]
         (if (= exp :expr)
           (expr elem)
           elem))]
      (= op-a :sq)
      [:msqrt (let [[exp e] elem]
                (if (= exp :expr)
                  (expr elem)
                  elem))])))

(defn e-exp [{:keys [mo elem-left elem-right]}]
  ((fun ([[:p _]]
         [:msup
          (if (= (first elem-left) :expr)
            (expr elem-left)
            elem-left)
          (if (= (first elem-right) :expr)
            (expr elem-right)
            elem-right)
          ])
        ([[:k _]]
         [:msub
          (if (= (first elem-left) :expr)
            (expr elem-left)
            elem-left)
          (if (= (first elem-right) :expr)
            (expr elem-right)
            elem-right)
          ])
        ([[:s _]]
         [:mroot
          (if (= (first elem-left) :expr)
            (expr elem-left)
            elem-left)
          (if (= (first elem-right) :expr)
            (expr elem-right)
            elem-right)
          ])
        ([[:= _]]
         [:mrow

          (if (= (first elem-left) :expr)
            (expr elem-left)
            elem-left)

          [:mo "="]
          (if (= (first elem-right) :expr)
            (expr elem-right)
            elem-right)])) mo))




(defn b-exp [{:keys [mo elem]}]
  [:mrow [:mo "("]
   (expr elem)
   [:mo ")"]])



(defn f-exp [e]
  (let [ {:keys [elem-left elem-right]} e]
    [:mrow
     [:mfrac
      (if (= (first elem-left) :expr)
        (expr elem-left) elem-left )
      (if (= (first elem-right) :expr)
        (expr elem-right) elem-right )
      ]]))


(defn expr [e]
  (let [expr-f (juxt first (comp  first second)
                     (comp  second second))
        [_ t-expr expr-1] (expr-f e)]
    (( {:p-exp p-exp
        :m-exp m-exp
        :b-exp b-exp
        :f-exp f-exp
        :e-exp e-exp} t-expr)
     expr-1)))




(comment
  (expr2 (s/conform ::element '(:s x 2) ))  )

(comment
  (expr (s/conform ::element '(:m 2 x))))

(comment (expr (s/conform ::element [:m 2 'x])))

(comment
  (expr
   (s/conform ::element
              '(+ :alpha x (- 5 2 4)
                  ((- x ) 1)))))

(s/conform ::element
           '(= (- 2) (:c (:p x 3))
               ))
(comment
  (expr
   (s/conform ::element
              '(+ 2 x 4
                  (:sq a)))))

(comment (expr
         (s/conform ::element
                    '(= 3 (+ 2 x 4
                             )))))

(comment
  (expr (s/conform ::element '(1 2))))


(defn e [ex]
  (expr (s/conform ::element ex)))

(defn m [ex]
  [:math (e ex)])

(defn animate-circle []
  (let [ref (react/useRef)
        _ (react/useEffect
           (fn []
             (.animate (-> ref
                           .-current)
                       (clj->js
                        [{
                          :transform
                          "translateX(200px)"
                          }
                         {:transform
                          "translateX(190px)"
                          :offset (/ 3 14)}

                         {:transform
                          "translateX(0px)"
                          :offset (/ 9 14)}

                         {:transform
                          "translateX(450px)"}])
                       (clj->js
                        {:duration 14000
                         :iterations 2})
                       )
             (js/console.log "hello world")))
        ]
    [:div {:ref ref
           :style {
                   :height "70px"
                   :width "70px"

                   :background-color :#ddd
                   }}

     ]))





(defn g [[[row rspan] [col cspan] [z op]] m]
  ((comp
    (fn [m] {:style m} )
    (fn [n] (into {:z-index z
                   :opacity op
                   :grid-row (str/join
                              "/"
                              [row
                               (+ rspan
                                  row)])
                   :grid-column (str/join "/" [col (+ cspan col)])
                   }
                  n))
    (juxt
     (comp
      (fn [c2] [:background-color (c c2)] )
      :c)
     (comp
      (fn [f-size] [:font-size (size f-size)] )
      :size)
     (comp
      (fun ([:center]
            {:display :flex
             :flex-direction :column
             :justify-content :center
             :align-items :center
             })
           ([[:center :left]]
            {:display :flex
             :flex-direction :column
             :justify-content :center
             :align-items :flex-start
             })
           ([[:center :right]]
            {:display :flex
             :flex-direction :column
             :justify-content :center
             :align-items :flex-end
             })
           ([[:center :space]]
            {:display :flex
             :flex-direction :column
             :justify-content :space-around
             :align-items :center
             })
           ([[:center :space :row]]
            {:display :flex
             :flex-direction :row
             :justify-content :space-around
             :align-items :center
             })
           ([:start]
            {:display :flex
             :flex-direction :column
             :justify-content :space-between
             :align-items :flex-start
             })
           ([:end]
            {:display :flex
             :flex-direction :column
             :justify-content :flex-end
             :align-items :flex-end
             }
            ))
      :flex)
     (comp
      (fn [d] [:background-image (gen-grad d)])
      :d)
     )) m)
  )


(def cr
  (fn [[s t-x t-y]]
    (fn [shape]
      (comp
       shape
       (juxt
        (comp
         (juxt
          (comp
           t-x
           s
           :x)
          (comp
           t-y
           s
           :y))
         :point)
        :attr
        )
       (fn [n]
         (s/conform :math2/svg-circle n))))))


(defn lhs [[r rs] [c cs]]
  [:div
   (g
    [[r rs] [c cs]
     [10 .9]]
    {:d [1
         -40 :% 0 30 70 0.5
         65 :% 1 30 70 0.7
         65 :% 1 40 70 0.6
         90 :% 2 50 70 .3]
     :size [3 :rem]
     :flex :center})
   [m '[:p x 2]]

   ])


(comment )
(defn lhs2 [[r rs] [c cs] m]
  [:div
   (g
    [[r rs] [c cs]
     [10 .9]]
    {:d [1
         -40 :% 0 30 70 0.2
         65 :% 1 30 70 0.1
         65 :% 1 40 70 0.2
         90 :% 2 50 70 .1]
     :size [2 :rem]
     :flex :center})
   m
   ])


(defn rhs [[r rs] [c cs]]
  [:div
   (g
    [[r rs] [c cs]
     [10 .9]]
    {:d [.9
         -40 :% -1 70 70 0.25
         65 :% -2 70 40 0.375
         65 :% 0.75 70 70 0.3
         90 :% -3.5 70 70 .5]
     :size [1.8 :rem]
     :flex :center})



   ])

(defn rhs2 [[r rs] [c cs]]
  [:div
   (g
    [[r rs] [c cs]
     [10 .9]]
    {:d [-1
         -40 :% -1 70 70 0.25
         65 :% -2 70 40 0.375
         65 :% 0.75 70 70 0.3
         90 :% -3.5 70 70 .5]
     :size [3 :rem]
     :flex :center})

   [m '[= [:m [:b [+ x 6]] [:b  [+ x 7]]]
        [+ [:p x 2] [:m x [:b  [+ 6 7]]] [* 7 6] ]] ]])

(defn center [[r rs] [c cs] m]
  [:div
   (g  [[r rs] [c cs]
        [10 .9]]
       {:d [1
            -20 :% 1 70 70 0.25
            25 :% .3 70 40 0.1
            37 :% .2 40 70 0.2
            90 :% .3 70 70 .2]
        :size [2 :rem]
        :flex :center})
   m])

(defn center2 [[r rs] [c cs] bin]
  [:div
   (g
    [[r rs] [c cs]
     [10 .9]]
    {:d [-1
         -20 :% -2 70 70 0.5
         25 :% -2 100 40 0.2
         28 :% 1 50 70 0.7
         90 :% .7 70 60 .7]
     :size [1.4 :rem]
     :flex :center})])
(defn tranfrom [t]
  ((partial str/join " ")
   (map
    (comp
     (partial str/join "")
     (juxt
      (comp
       name
       first)
      (comp
       (fn [n]
         (str "(" n ")"))
       (fn [n]
         (if (vector? n)
           (str/join "," n)
           (str n)))
       second)))

    t)))

(defn url [i]
  (str "url" "(#" i  ")" ))


(comment
  (url 1)
  (tranfrom [[:scale 3]
             [:rotate 180]
             [:translate [12.5 0]]]))

(defn grid4 []
  (let [[w h s] [420 420 30]
        [s tx ty] [(comp
                    (fn [[x y]] (+ x y))
                    (juxt (comp (fn [x] (* x 30))  first)
                          (comp (fn [x] (* x 6) ) second)))
                   (fn [x] (+ (* 40 (+ 0 4)) x))
                   (fn [y] (- (+ (* 40 (- 12 4)) 0) y))]
        tfun-e1 [s (comp tx s) (comp ty s)]]
    [:div {:style
           {:background-color (c [90 70 70])
            :display :grid
            :height (size [100 :vh])
            :width (size [100 :vw])
            :grid-template-columns (size (apply concat (take 14 (repeat [10 :vh]))))
            :grid-template-rows (size (apply concat (take 10 (repeat [10 :vh]))))}}

     [lhs [2 2] [5 2]]

     [lhs2 [4 2] [5 2] [m '[:m 6 x]]]

     [center [2 2] [7 3]
      [m '[:m 7 x]]]

     [center [4 2] [7 3]
      [m '[* 7 6]]]

     [center [2 5] [4 1] [m '[+ x 6]] ]

     [center [6 1] [5 5] [m '[+ x 7]] ]

     [rhs2 [7 3] [3 9]]

     [:div {
            :style
            {:z-index 2
             :color :#444
             :grid-row (str/join "/" [2 -2])
             :grid-column (str/join "/" [2 -2])
             :background-color (c [70 70 70])
             }}
      [:svg {:style {:background-color (c [150 70 70])
                     :height (size [100 :%])
                     :width (size [100 :%])}
             :viewBox
             (str/join " "
                       [(* 40 -0.55) (* 40 0.5)
                        (* 40 12) (* 40 12)])}

       (map (paths
             (fn [d]
               [:path
                {:d d
                 :fill (c [70 70 70])
                 :stroke (c [90 80 40])
                 :stroke-width .6}])
             tfun-e1)
            [
             [
              [[-4 0] [6 0] :l
               [0 0] [4 0] [4 0] [0 0]
               [0 0] [-4 0] [-4 0] [0 0]]

              [[0 0] [6 0] :l
               [0 0] [4 0] [5 3] [0 0]
               [0 0] [-4 0] [-5 -3] [0 0]]

              [[-4 0] [1 1] :l
               [0 0] [4 4] [4 0] [0 0]
               [0 0] [-4 -4] [-4 0] [0 0]]

              ]

             (for [i (range 0 28 4)
                   j (range 0 24 4)]
               [[0 i] [2 (- j 4)]
                :l
                [0 4] [0 0] [0 0] [0 4]
                [0 -4] [0 0] [0 0] [0 -4]])])

       (map (paths
             (fn [d]
               [:path
                {:style {:z-index 24}
                 :d d :fill :none :stroke (c [309 80 40])
                 :stroke-width 4}])
             tfun-e1)
            [
             #_(for [i (range 0 10 1)
                   j (range 0 10 1)]
               [[1 i] [-2 j]
                :l
                [0 1] [0 0] [0 0] [0 1]
                [0 -1] [0 0] [0 0] [0 -1]])

             [[[-4 0] [1 1] :l [0 0] [10 0]]
              [[-4 0] [1 1] :l [9 3] [0 0]]]


             ])




       ((paths
         (fn [d]
           [:path
            {:d d
             :fill :none
             :stroke (c [70 40 80])
             :stroke-width .5}]) tfun-e1)
        (for [i (range -12 13 2)
              j (range -12 13 2)]
          [[i 0] [j 0]
           :l
           [2 0] [0 0] [0 0] [2 0] [-2 0] [0 0] [0 0] [-2 0]]))

       (let [ shapefn ((cr [s tx ty])
                      (fun ([[[x y] [:text t]]]
                            [:text
                             {:x x
                              :style {:font-size
                                      (size [0.5 :rem])}
                              :y y}
                             t])
                           ([[[x y] [:r r]]]
                            [:circle
                             {:cx x
                              :style {:z-index 22}
                              :cy  y
                              :r  r
                              :fill (c [10 70 60])}])))]
         (for [xyz [(map (fn [x]
                           [[0 0] [x
                                   0] (str
                                       (* 2 x))])
                         (range -8 8))
                    (map (fn [x]
                           [[(* x 2) 0] [0 0] (str x)])
                         (range -8 8))

                    (comment
                      (map (fn [x]
                                   [[0 2] [x 0] (str x)])
                                 (range -10  10)))

                    (comment(map (fn [x]
                                   (let [yp (* 3 x x)
                                         y (int (/ yp 10))
                                         dy (* 6 (/ (mod yp 10) 10))]
                                     [[(* x x) 0] [y dy] 4]))
                                 (range 0 5)))
                    (comment
                      )]]
           (map shapefn xyz)))
       (map (paths
             (fn [d]
               [:path {:d d :fill :none
                       :stroke-dasharray (size [2 :rem 2 :rem])
                       :stroke (c [20 80 80]) :stroke-width 0.8}])
             tfun-e1)
            [(for [i (range -20 20)]
               [[i 0] [0 0] :l
                [1 0] [0 0] [0 0] [0 1] [0 0] [0 -1] [1 0] [0 0]
                [0 0] [0 1]])
             (for [i (range  -10 10)]
               [[0 0] [i 0]
                :l [0 -1] [0 0] [0 1] [0 0] [0 0] [1 0]
                [0 -1] [0 0] [0 1] [0 0]])])]]]))




(defn grid5 []
  (let [[w h s] [420 420 30]
        [s tx ty] [(comp
                    (fn [[x y]] (+ x y))
                    (juxt (comp (fn [x] (* x 30))  first)
                          (comp (fn [x] (* x 6) ) second)))
                   (fn [x] (+ (* 40 -2) x))
                   (fn [y] (- (+ (* 40 (- 12 4)) 0) y))]
        tfun-e1 [s (comp tx s) (comp ty s)]]
    [:div {:style
           {:background-color (c [90 70 70])
            :display :grid
            :height (size [100 :vh])
            :width (size [100 :vw])
            :grid-template-columns (size (apply concat (take 14 (repeat [10 :vh]))))
            :grid-template-rows (size (apply concat (take 10 (repeat [10 :vh]))))}}


     (comment )
     [:div (g [[3 2] [3 2] [10 1]]
              {:d [1
                   20 :% 1 70 70 .2
                   50 :% 2 70 70 .2]
               :size [3 :rem]
               :flex :center})
      [m '[:m 2 [:p x 2  ]]]]







     [:div (g [[5 1] [3 2] [10 1]]
              {:d [1
                   20 :% 3 70 70 .2
                   50 :% 4 70 70 .2]
               :size [3 :rem]
               :flex :center})
      [m '[:m [- 3]
           x]]]



     [:div (g [[3 2] [5 2] [10 1]]
              {:d [4
                   5 :% .1 70 70 .4
                   50 :% .3 70 70 .4
                   80 :% .4 70 70 .4]
               :size [3 :rem]
               :flex :center})
      [m '[:m [* 4 2 ] x ]]]


     [:div (g [[3 5] [8 3] [10 1]]
              {:d [4
                   5 :% .1 70 70 .2
                   50 :% .3 70 70 .2
                   80 :% .4 70 70 .2]
               :size [1.8 :rem]
               :flex :center})

      [m '[= [- [+ [:m 2 [:p x  2]] [:m 5 x]] (* 4 3)] 0] ]
      [m '[= [- [+ [:m 2 [:p x  2]] [:m (:b (- 8 3)) x]] (* 4 3)] 0] ]
      [m '[:m

           [:b [- [:m 2 x] 3]]
           [:b [+ x 4]]]]
      ]

     (comment
       [:div (g [[3 2] [9 2] [10 1]]
                {:d [1
                     20 :% 1 70 70 .2
                     50 :% 2 70 70 .2]
                 :size [2 :rem]
                 :flex :start})
        [m '[+ x 4]]])
     (comment
       [:div (g [[3 2] [3 2] [10 1]]
                {:d [1
                     20 :% 1 70 70 .2
                     50 :% 2 70 70 .2]
                 :size [2 :rem]
                 :flex :start})
        [m '[+ x 4]]])

     (comment
       [:div (g [[3 5] [3 1] [10 1]]
                {:d [1
                     20 :% 1 70 70 .2
                     50 :% 2 70 70 .2]
                 :size [2 :rem]
                 :flex :end})
        [m '[- x 7]]])


     (comment
       )

     [:div {
            :style
            {:z-index 2
             :color :#444
             :grid-row (str/join "/" [2 -2])
             :grid-column (str/join "/" [2 -2])
             :background-color (c [70 70 70])
             }}
      [:svg {:style {:background-color (c [150 70 70])
                     :height (size [100 :%])
                     :width (size [100 :%])}
             :viewBox
             (str/join " "
                       [(* 40 -0.55) (* 40 0.5)
                        (* 40 12) (* 40 12)])}

       [:defs
        [:marker {:id "i"
                  :refY 0
                  :refX 0
                  :orient :auto
                  :style {:overflow :visible}}

         ((paths (fn [d]
                   [:path {:d d
                           :style {:fill-rule :evenodd
                                   :stroke (c [70 70 70])
                                   :stroke-width 1
                                   :stroke-opacity 1
                                   :fill (c [330 70 70])
                                   :fill-opacity 1}
                           :transform (tranfrom [[:scale .4]
                                                 [:rotate 180]
                                                 [:translate [12.5 0]]])


                           }])
                 [(fn [[x _]] x)
                  (fn [[x _]] x)
                  (fn [[x _]] x)])
          [[[0 0] [0 0] :L [5 0] [-5 0] :L [-12.5 0] [0 0]
            :L [3 0] [5 0] :L [0 0] [0 0]]])
         ]]

       (map (paths (fn [d]
                     [:path {:d d
                             :stroke (c [90 70 50])
                             :stroke-width 2
                             :marker-end (url "i")
                             :fill :none}])
               [s (comp tx s) (comp ty s)])
            [
             [[[0 0] [8 0]
               :l [4 0] [0 0] [0 0] [-4 0]
               [-4 0] [0 0] [0 0] [4 0]]
              [[0 0] [4 0]
               :l [4 0] [0 0] [0 0] [-3 0]
               [-4 0] [0 0] [0 0] [3 0]]

              [[4 0] [8 0]
               :l [4 0] [0 0] [0 0] [-4 0]
               [-4 0] [0 0] [0 0] [4 0]]
              [[4 0] [4 0]
               :l [4 0] [0 0] [0 0] [-3 0]
               [-4 0] [0 0] [0 0] [3 0]]
              ]

             [[[9 0] [8 0]
               :l [8 0] [0 0] [0 0] [-11 0]
               [-8 0] [0 0] [0 0] [11 0]]]


             ])
       (map (paths
         (fn [d]
           [:path {:d d
                   :stroke (c [20 70 80])
                   :stroke-width 2
                   :fill (c [40 70 80])}])
         [s (comp tx s) (comp ty s)])
            [
             [[[0 0] [8 0]
               :l [4 0] [0 0]
               ]]
             [[[0 0] [8 0]
               :l [0 0] [-3 0]
               ]]

             (for [i (range 4)
               :let [k 1
                     x (+ 4 (* i k))]
               j (range 3)
               :let [k 1
                     y (+ 4 (* j k -1))]
               ]
           [[x 0] [y 0]
            :l [k 0] [0 0] [0 0] [(* k -1) 0]
            [(* k -1) 0] [0 0] [0 0] [k 0]])])




       ]]]))




(defn grid6 []
  (let [[w h s] [420 420 30]
        [s tx ty] [(comp
                    (fn [[x y]] (+ x y))
                    (juxt (comp (fn [x] (* x 30))  first)
                          (comp (fn [x] (* x 6) ) second)))
                   (fn [x] (+ (* 40 5) x))
                   (fn [y] (- (+ (* 40 (- 12 4)) 0) y))]
        tfun-e1 [s (comp tx s) (comp ty s)]]
    [:div {:style
           {:background-color (c [90 70 70])
            :display :grid
            :height (size [100 :vh])
            :width (size [100 :vw])
            :grid-template-columns (size (apply concat (take 14 (repeat [10 :vh]))))
            :grid-template-rows (size (apply concat (take 10 (repeat [10 :vh]))))}}


     [:div  (g [[3 1] [5 7] [10 1]]
               {:d [1
                    20 :% 1 70 70 .2
                    50 :% 2 70 70 .2]
                :size [1.6 :rem]
                :flex :center})



      [m '[:sq [* 4 [441 9]]]]

      [m '[:sq [* 4 9 49]]]

      [m '[:sq [* [:p 2
                   2] [:p 3 2] [:p 7 2] ]]]

      [m '[* [:p 2
              1] [:p 3 1] [:p 7 1]
           ]
       ]


      ]












     [:div {
            :style
            {:z-index 2
             :color :#444
             :grid-row (str/join "/" [2 -2])
             :grid-column (str/join "/" [2 -2])
             :background-color (c [70 70 70])
             }}
      [:svg {:style {:background-color (c [150 70 70])
                     :height (size [100 :%])
                     :width (size [100 :%])}
             :viewBox
             (str/join " "
                       [(* 40 -0.55) (* 40 0.5)
                        (* 40 12) (* 40 12)])}

       [:defs
        [:marker {:id "i"
                  :refY 0
                  :refX 0
                  :orient :auto
                  :style {:overflow :visible}}

         ((paths (fn [d]
                   [:path
                    {:d d
                     :style {:fill-rule :evenodd
                             :stroke (c [70 70 70])
                             :stroke-width 1
                             :stroke-opacity 1
                             :fill (c [330 70 70])
                             :fill-opacity 1}
                     :transform
                     (tranfrom [[:scale .4]
                                [:rotate 180]
                                [:translate [12.5 0]]])


                     }])
                 [(fn [[x _]] x)
                  (fn [[x _]] x)
                  (fn [[x _]] x)])
          [[[0 0] [0 0] :L [5 0] [-5 0] :L [-12.5 0] [0 0]
            :L [3 0] [5 0] :L [0 0] [0 0]]])
         ]]


       (map (paths
             (fn [d]
               [:path
                {:d d
                 :fill (c [70 70 70])
                 :stroke (c [70 80 40])
                 :stroke-width 2}])
             tfun-e1)
            [


             (for [i (range 0 20 4)
                   j (range 0 16 4)]
               [[0 i] [3 (- j 4)]
                :l
                [0 4] [0 0] [0 0] [0 4]
                [0 -4] [0 0] [0 0] [0 -4]])])

       (map (paths
             (fn [d]
               [:path
                {:d d
                 :fill (c [300 70 70])
                 :stroke (c [70 80 40])
                 :stroke-width 2}])
             tfun-e1)
            [


             (for [i (range 0 4 4)
                   j (range 0 16 4)]
               [[-1 i] [3 (- j 4)]
                :l
                [0 4] [0 0] [0 0] [0 4]
                [0 -4] [0 0] [0 0] [0 -4]])])
       (comment
         )]]]))

(defn grid7 []
  (let [[w h s] [420 420 30]
        [s tx ty] [(comp
                    (fn [[x y]] (+ x y))
                    (juxt (comp (fn [x] (* x 30))  first)
                          (comp (fn [x] (* x 6) ) second)))
                   (fn [x] (+ (* 40 -2) x))
                   (fn [y] (- (+ (* 40 (- 12 4)) 0) y))]
        tfun-e1 [s (comp tx s) (comp ty s)]]
    [:div {:style
           {:background-color (c [90 70 70])
            :display :grid
            :height (size [100 :vh])
            :width (size [100 :vw])
            :grid-template-columns (size (apply concat (take 14 (repeat [10 :vh]))))
            :grid-template-rows (size (apply concat (take 10 (repeat [10 :vh]))))}}



     [:div (g [[3 1] [4 1] [10 1]]
              {:d [1
                   20 :% 1 70 70 .2
                   50 :% 2 70 70 .2]
               :size [3 :rem]
               :flex :end})
      [m '[:m 2 x]]
      ]


     [:div (g [[5 1] [3 2] [10 1]]
              {:d [1
                   20 :% 3 70 70 .1
                   50 :% 4 70 70 .1]
               :size [3 :rem]
               :flex :center})
      [m '[:m [- 3] x]]
      ]



     [:div (g [[3 1] [5 2] [10 1]]
              {:d [4
                   5 :% .1 70 70 .1
                   50 :% .3 70 70 .1
                   80 :% .4 70 70 .1]
               :size [3 :rem]
               :flex :center})
      [m '[:m 8 x]]
      ]


     [:div (g [[3 5] [8 5] [10 1]]
              {:d [4
                   5 :% .1 70 70 .2
                   50 :% .3 70 70 .2
                   80 :% .4 70 70 .2]
               :size [1.8 :rem]
               :flex :center})
      [m '[= [- [+ [:m 2 [:p  x 2]]
               [:m 5 x ]
                 ] [* 3 4]
             ]
           0]]
      [m '[= [:m [:b [+ [:m  2 x]
                    [- 3]]]
              [:b (+ x 4)]]
          0]]

      ]



     [:div {
            :style
            {:z-index 2
             :color :#444
             :grid-row (str/join "/" [2 -2])
             :grid-column (str/join "/" [2 -2])
             :background-color (c [70 70 70])
             }}
      [:svg {:style {:background-color (c [150 70 70])
                     :height (size [100 :%])
                     :width (size [100 :%])}
             :viewBox
             (str/join " "
                       [(* 40 -0.55) (* 40 0.5)
                        (* 40 12) (* 40 12)])}

       [:defs
        [:marker {:id "i"
                  :refY 0
                  :refX 0
                  :orient :auto
                  :style {:overflow :visible}}

         ((paths (fn [d]
                   [:path {:d d
                           :style {:fill-rule :evenodd
                                   :stroke (c [70 70 70])
                                   :stroke-width 1
                                   :stroke-opacity 1
                                   :fill (c [330 70 70])
                                   :fill-opacity 1}
                           :transform (tranfrom [[:scale .4]
                                                 [:rotate 180]
                                                 [:translate [12.5 0]]])


                           }])
                 [(fn [[x _]] x)
                  (fn [[x _]] x)
                  (fn [[x _]] x)])
          [[[0 0] [0 0] :L [5 0] [-5 0] :L [-12.5 0] [0 0]
            :L [3 0] [5 0] :L [0 0] [0 0]]])
         ]]

       (map (paths (fn [d]
                     [:path {:d d
                             :stroke (c [90 70 50])
                             :stroke-width 2

                             :fill :none}])
               [s (comp tx s) (comp ty s)])
            [
             [[[0 0] [8 0]
               :l [4 0] [0 0] [0 0] [-3 0]
               [-4 0] [0 0] [0 0] [3 0]]

              [[0 0] [5 0]
               :l [4 0] [0 0] [0 0] [-3 0]
               [-4 0] [0 0] [0 0] [3 0]]
              [[4 0] [8 0]
               :l [4 0] [0 0] [0 0] [-3 0]
               [-4 0] [0 0] [0 0] [3 0]]
              [[4 0] [5 0]
               :l [4 0] [0 0] [0 0] [-3 0]
               [-4 0] [0 0] [0 0] [3 0]]
              ]




             ])
       (map (paths
         (fn [d]
           [:path {:d d
                   :stroke (c [20 70 80])
                   :stroke-width 2
                   :marker-end (url "i")
                   :fill (c [40 70 80])}])
         [s (comp tx s) (comp ty s)])
            [

             [[[0 0] [2 0] :l [0 0] [6 0]]]

             [[[0 0] [8 0] :l [8 0] [0 0]]]

             (for [i (range 4)
               :let [k 1
                     x (+ 4 (* i k))]
               j (range 3)
               :let [k 1
                     y (+ 5 (* j k -1))]
               ]
           [[x 0] [y 0]
            :l [k 0] [0 0] [0 0] [(* k -1) 0]
            [(* k -1) 0] [0 0] [0 0] [k 0]])])




       ]]]))


(defn grid8 []
  (let [mm m
        [start x1 m n] [8 1 2 4]
        dx 1
        dy 1
        x (* x1 dx)
        y (* x1 dy)
        v 'z
        [w h s] [420 420 30]
        [s tx ty] [(comp
                    (fn [[x y]] (+ x y))
                    (juxt (comp (fn [x] (* x 30))  first)
                          (comp (fn [x] (* x 6) ) second)))
                   (fn [x] (+ (* 40 -2) x))
                   (fn [y] (- (+ (* 40 (- 12 4)) 0) y))]
        tfun-e1 [s (comp tx s) (comp ty s)]]
    [:div {:style
           {:background-color (c [90 70 70])
            :display :grid
            :height (size [100 :vh])
            :width (size [100 :vw])
            :grid-template-columns (size (apply concat (take 14 (repeat [10 :vh]))))
            :grid-template-rows (size (apply concat (take 10 (repeat [10 :vh]))))}}


     [:div (g [[(+ x 1) 1] [3 1] [10 1]]
              {:d [1
                   -5 :% 1.5 70 70 0.5
                   20 :% 1.8 70 70 0.5
                   80 :% 2.2 70 70 0.5]
               :size [1 :rem]
               :flex :center})
      [mm [:m ['* dx dy]  [:p v 2]]]

      ]


     [:div (g [[(+ y 1) 1] [3 1] [10 1]]
              {:d [1
                   -5 :% 1.5 70 70 0.5
                   20 :% 1.8 70 70 0.5
                   80 :% 2.2 70 70 0.5]
               :size [1 :rem]
               :flex :center})
      [mm [:m ['* ['- m]
               dx] v]]
      ]

     [:div (g [[(+ x 1) 1] [(+ 3 x) 1] [10 1]]
              {:d [1
                   -5 :% 1.5 70 70 0.8
                   20 :% 1.8 70 70 0.8
                   80 :% 2.2 70 70 0.8]
               :size [1 :rem]
               :flex :center})
      [mm [:m ['* n  dy] v]]
      ]



     [:div (g [[(+ y 1)  1] [(+ 3 x) 1] [10 1]]
              {:d [1
                   -5 :% 1.5 70 70 0.2
                   20 :% 1.8 70 70 0.2
                   80 :% 2.2 70 70 0.2]
               :size [1 :rem]
               :flex :center})
      [mm ['*  m n ]]
      ]

     [:div (g [[x 5] [(+ 3 x 2) 5] [10 1]]
              {:d [1
                   -5 :% 1.5 70 70 0.2
                   20 :% 1.8 70 70 0.2
                   80 :% 2.2 70 70 0.2]
               :size [1 :rem]
               :flex :center})
      [mm [:m [:b ['+ v n ]]
           [:b ['+ [:m dy v] ['- m] ]]]]
      ]


     [:div {
            :style
            {:z-index 2
             :color :#444
             :grid-row (str/join "/" [2 -2])
             :grid-column (str/join "/" [2 -2])
             :background-color (c [70 70 70])
             }}
      [:svg {:style {:background-color (c [150 70 70])
                     :height (size [100 :%])
                     :width (size [100 :%])}
             :viewBox
             (str/join " "
                       [(* 40 -0.55) (* 40 0.5)
                        (* 40 12) (* 40 12)])}

       [:defs
        [:marker {:id "i"
                  :refY 0
                  :refX 0
                  :orient :auto
                  :style {:overflow :visible}}

         ((paths (fn [d]
                   [:path {:d d
                           :style {:fill-rule :evenodd
                                   :stroke (c [70 70 70])
                                   :stroke-width 1
                                   :stroke-opacity 1
                                   :fill (c [330 70 70])
                                   :fill-opacity 1}
                           :transform (tranfrom [[:scale .4]
                                                 [:rotate 180]
                                                 [:translate [12.5 0]]])


                           }])
                 [(fn [[x _]] x)
                  (fn [[x _]] x)
                  (fn [[x _]] x)])
          [[[0 0] [0 0] :L [5 0] [-5 0] :L [-12.5 0] [0 0]
            :L [3 0] [5 0] :L [0 0] [0 0]]])
         ]]


       (map (paths (fn [d]
                     [:path {:d d
                             :stroke (c [90 70 50])
                             :stroke-width 2

                             :fill :none}])
                   [s (comp tx s) (comp ty s)])
            [
             [[[0 0] [start 0]
               :l [x 0] [0 0] [0 0] [(* y -1) 0]
               [(* x -1) 0] [0 0] [0 0] [(* y 1) 0]]

              [[0 0] [(+ start (* y -1) ) 0]
               :l [x 0] [0 0] [0 0] [(* -1 m) 0]
               [(* -1 x) 0] [0 0] [0 0] [(* m 1) 0]]
              [[x 0] [start 0]
               :l [n 0] [0 0] [0 0] [(* -1 y) 0]
               [(* -1 n) 0] [0 0] [0 0] [(* 1 y) 0]]
              [[x 0] [(- start y)  0]
               :l [n 0] [0 0] [0 0] [(* -1 m) 0]
               [(* -1 n) 0] [0 0] [0 0] [m 0]]
              ]
             ])

       (map (paths
         (fn [d]
           [:path {:d d
                   :stroke (c [20 70 80])
                   :stroke-width 2
                   :marker-end (url "i")
                   :fill (c [40 70 80])}])
         [s (comp tx s) (comp ty s)])
            [

             [[[0 0] [start 0] :l [(+ x 0) 0] [0 0]]]

             [[[0 0] [start 0] :l [(+ x n) 0] [0 0]]]


             [[[0 0] [start 0] :l [0 0] [(* y -1) 0]]]
             [[[0 0] [start 0] :l [0 0] [(* (+ y m)  -1) 0]]]


             [[[10 0] [start 0] :l
               [x 0] [0 0] [n 0] [0 0]
               [0 0] [(* y -1) 0] [0 0] [ (* m -1) 0]
               [(* -1 x) 0] [0 0] [(* -1 n) 0] [0 0]

               ]]

             (for [i (range dx)
                   :let [k 1
                         x1 (* i k)]
                   j (range dy)
                   :let [k 1
                         y1 (+ start
                               (* j k -1))]
                   ]
               [[x1 0] [y1 0]
                :l [k 0] [0 0] [0 0] [(* k -1) 0]
                [(* k -1) 0] [0 0] [0 0] [k 0]])

             (for [i (range n)
                   :let [k 1
                         x1 (+ x (* i k))]
                   j (range m)
                   :let [k 1
                         y1 (+ (- start y) (* j k -1))]
                   ]
               [[x1 0] [y1 0]
                :l [k 0] [0 0] [0 0] [(* k -1) 0]
                [(* k -1) 0] [0 0] [0 0] [k 0]])])



       ]]]))


(defn template1 []
  [grid8])
