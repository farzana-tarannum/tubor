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
     :size [1.7 :rem]
     :flex :center})
   [m '[=  displacement s ]]
   [m '[= [s t] [[+ v u] 2]]]
   [m '[= s
        [[:m t [:b [+ v u]]] 2]]]

   [m '[= s
        [[:m t [:b [+ [:b  [= v [+ u [:m a t]] ]] u]]] 2]]]

   [m '[= s
        [:m [t 2] [:b [+ [:m 2 u] [:m a t]]]]

        ]]
   [m '[= s
        [+ [:m  u t] [:m [1 2]  a [:p t 2]]]


        ]]

   ])
(js/Math.sqrt 3)

(comment )
(defn lhs2 [[r rs] [c cs]]
  [:div
   (g
    [[r rs] [c cs]
     [10 .9]]
    {:d [1
         -40 :% 0 30 70 0.5
         65 :% 1 30 70 0.1
         65 :% 1 40 70 0.6
         90 :% 2 50 70 .3]
     :size [2 :rem]
     :flex :center})
   [m '[= Acceleration a]]
   [m '[= velocity-at-begining u]]
   [m '[= velocity-at-end v]]
   [m '[=
        a [[- v u] t]]]
   [m '[=
        [:m a t]  [- v u]]]
   [m '[= v
        [+ u [:m a t]] ]]])


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

   [m '[= [:sq [:p 5  2 ]]
        [:p 5 1]]]

   [m '[= [:sq [*  2  [:p 5 2]]]
        [:m 5 [:sq 2] ]]]

   [m '[= [:sq 50] [:sq [+ [:p 7 2] 1]]

        ]

    ]

   [:div "this is estimated as 7 "]

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
     :size [1.2 :rem]
     :flex :center})])

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

     (comment [lhs2 [2 3] [2 5]])
     (comment [lhs [5 5] [2 5]])
     [center [2 2] [3 4]
      (map
       (fn [x] x)
       [[m '[= [+ [- x] [:m 2 y]] 4]]
        [m '[= [+ [- x] x [:m 2 y]] [+ 4 x]]]
        [m '[= [ [:m 2 y] 2]
             [[+ 4 x] 2]]]
        [m '[= y
             [[+ 4 x] 2]]]
        ])]
     (comment [rhs [2 2] [5 7]])
     (comment [rhs2 [4 3] [9 3]])
     (comment
       (loop [iter 253
              acc []]
         (if (= (int (/ iter 2 )) 0 )
           (conj acc [iter (mod iter 2)])
           (recur (int (/ iter 2)) (conj acc [iter
                                              (mod iter 2)]))))
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

       (map (paths
             (fn [d]
               [:path
                {:d d :fill :none :stroke (c [40 80 40])
                 :stroke-width 1.5}])
             tfun-e1)
            [

             (for [i (range 0 10 1)
                   j (range 0 10 1)]
               [[1 i] [-2 j]
                :l
                [0 1] [0 0] [0 0] [0 1]
                [0 -1] [0 0] [0 0] [0 -1]])

             [
              [[2 2.5] [10 0] :l [0 0] [-20 0]]]
             [
              [ [-6 0] [0 -2.5]  :L [-3 0] [ 0.25 0 ]
               [0 0] [1 0]  [5 0] [2.25 0]]]
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
                      )
                    (comment
                      )
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
                   :stroke (c [640 80 40]) :stroke-width 0.8}])
         tfun-e1)
        [(for [i (range -20 20)]
           [[i 0] [0 0] :l
            [1 0] [0 0] [0 0] [0 1] [0 0] [0 -1] [1 0] [0 0]
            [0 0] [0 1]])
         (for [i (range  -10 10)]
           [[0 0] [i 0]
            :l [0 -1] [0 0] [0 1] [0 0] [0 0] [1 0]
            [0 -1] [0 0] [0 1] [0 0]])])]]]))


(defn template1 []
  [grid4])
