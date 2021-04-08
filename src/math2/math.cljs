(ns math2.math
   (:require
    [react]
    [clojure.string :as str]
    [clojure.test.check.generators :as gen2]
    [clojure.spec.gen.alpha :as gen]
    [clojure.spec.alpha :as s]
    [math2.bdmap :as bmap]
    [math2.file :as file]
    [defun.core :refer [defun fun]]
    [moment]
    [math2.math-test :as mt]))



(s/def :math2/point
  (s/tuple
   (s/tuple
    (s/and
     number?
     (fn [n] (and (> n -251)) (< n 251)))
    (s/and
     number?
     (fn [n] (and (> n -251)) (< n 251))))
   (s/tuple
    (s/and
     number?
     (fn [n] (and (> n -251)) (< n 251)))
    (s/and
     number?
     (fn [n] (and (> n -251)) (< 5 221))))))

(s/def :math2/svg-point
  (s/cat
   :x (s/tuple
       (s/and
        number?
        (fn [n] (and (> n -251)) (< n 251)))
       (s/and
        number?
        (fn [n] (and (> n -251)) (< n 251))))
   :y
   (s/tuple
    (s/and
     number?
     (fn [n] (and (> n -251)) (< n 251)))
    (s/and
     number?
     (fn [n] (and (> n -251)) (< n 251))))
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
          (s/&
           (s/+ :math2/point)
           (fn [points]
             (= (count points) 3))))
         :curve-q
         (s/tuple
          #{:q :Q}
          (s/&
           (s/+ :math2/point)
           (fn [points]
             (= (count points) 2))))
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
          :points (s/+ :math2/svg-point)
          )

         :arc (s/cat
               :a #{:a :A}
               :r1 :math2/r
               :r2 :math2/r
               :angle number?
               :f1 boolean?
               :f2 boolean?
               :end :math2/svg-point )))))


(comment

  (s/def ::kwd (s/with-gen
                 (s/and keyword?
                        #(= (namespace %) "my.domain"))
                 #(s/gen #{:my.domain/name :my.domain/occupation
                           :my.domain/id})))

  (def kw-gen2 (gen/fmap #(keyword "my.domain" %)
                         (gen/string-alphanumeric)))

  (gen/sample kw-gen2 5)
  (def kw-gen-3 (gen/fmap #(keyword "my.domain" %)
                          (gen/such-that #(not= % "")
                                         (gen/string-alphanumeric))))
  (gen/sample kw-gen-3)

  (s/def ::hello (s/with-gen
                   #(clojure.string/includes? % "hello")
                   #(gen/fmap
                     (fn [[s1 s2]]
                       (str s1 "hello" s2))
                     (gen/tuple
                      (gen/string-alphanumeric)
                      (gen/string-alphanumeric)))))
  (gen/sample (s/gen (s/int-in 0 10)))
  (gen/sample (s/gen ::hello))

  (namespace :my.domain/name)
  (s/valid? ::kwd :my.domain/name))


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
                         (and (< r 59)
                              (> r -59))))
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
                   (and (> a -50)
                        (< a 50))))
   :colors
   (s/+ (s/cat
         :size (s/cat
                :size number?
                :scale #{:rem :px :vh :vw :%})
         :color ::hsl2))))

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
    (fn [e] (s/conform ::gradient e))) v))


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


(defn circles2 [plot-e1 [t-s t-x t-y] [txt cir]]
  (let [ space (fn [p] (str/join " " p))]
    (map
     (comp
      (fun ([[[x y] [:r r]]]
            [:circle
             (into {:cx x
                    :cy y
                    :r r
                    :fill (c [270 70 70])
                    :stroke-width .2
                    :stroke (c [330 70 70])}
                   cir)
             ])
           ([[[x y] [:text r]]]
            [:text {:x x
                    :y y
                    :style (into {:font-size (size [0.5 :rem])}
                                 txt)
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

    (circles2 (gen/sample (s/gen :math2/circle 10 ))
              [t-s t-x t-y]
              [{} {}])))






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

(def ss
  (fn [step factor]
    (fn [data]
          (let [[_ x]
                (reduce
                 (fn [[factor sum] i]
                   [(/  factor 5)
                    (+ sum (* factor i))])
                 [step 0] data)]
            (/ x factor)))))
(def space (fn [p] (str/join " " p)))
(def ve (fn [n] (* n -1)))

(def grad2 (fn [n]
             [4
              -5 :% (+ n .5) 70 70 0.6
              20 :% (+ n n .5) 70 70 0.3
              80 :% (+ n n .5)  70 70 0.7]))

(def grid22 {:background-color (c [90 70 70])
             :display :grid
             :height (size [100 :vh])
             :width (size [100 :vw])
             :grid-template-columns (size (apply concat (take 12 (repeat [10 :vh]))))
             :grid-template-rows (size (apply concat (take 10 (repeat [10 :vh]))))})

(defn path-temp2 []
  (let [[s tx ty] [(ss 40 10) (partial + 200) (partial + -400)]
        m-add (fn [m] (str "M " m))
        space (fn [p] (str/join " " p))
        path
        (comp
         m-add
         space
         flatten
         (juxt
          (comp
           (juxt (comp ty s :x)
                 (comp ty s :y))
           :m)
          (comp
           (fn  [l]
             (map
              (comp
               (fun
                ([[:line p]]
                 ((juxt (comp
                         name :l)
                        (comp
                         (fn [l]
                           (map
                            (juxt (comp tx s :x) (comp ty s :y))
                            l))
                         :points) ) p)
                 )
                ([[:arc  x]]
                 (let [{:keys [a r1 r2 angle f1 f2  end]} x]
                   [(name a) r1 r2 angle (if f1 1 0) (if f2 1 0)
                    ((juxt (comp ty s :x)
                           (comp ty s :y)) end)]))
                ([[:curve-c  c]]
                 ((juxt (comp
                         name
                         :c)
                        (comp
                         (fn [l]
                           (map
                            (juxt (comp ty  s :x) (comp ty s :y))
                            l))
                         :points) ) c)
                 )
                ([[:curve-q  q]]
                 ((juxt (comp
                         name
                         :q)
                        (comp
                         (fn [l]
                           (map
                            (juxt (comp tx s :x) (comp ty s :y))
                            l))
                         :points) ) q))
                ))
              l))
           :d))
         (fn [x]
           (s/conform :math2/svg-path x)))
        ]
    (map
     (comp
      (fn [d] [:path {:d d}
               :stroke (c [20 50 50])
               :stroke-width 2
               :fill :none])
      path)
     (gen/sample (s/gen :math2/svg-path 10)))

    ))



(defn path22 [[s tx ty]]
  (let [ve (fn [x] (* x -1))
        maping (fn [f] (partial map f))
        m-add (fn [m] (str "M " m))
        rel #{:l :c :a :q}
        abs #{:L :C :A :Q}
        tx2 (fn [n] (if (rel n) identity tx))
        ty2 (fn [n] (if (rel n) ve ty))
        space (fn [p] (str/join " " p))]
    (comp
     m-add
     space
     flatten
     (juxt
      (comp
       (juxt (comp tx s :x)
             (comp ty s :y))
       :m)
      (comp
       (partial
        map
        (fun
         ([[:line p]]
          ((juxt (comp name :l)
                 (comp
                  (partial map (juxt
                                (comp (tx2 (get p :l)) s :x)
                                (comp (ty2 (get p :l)) s :y)))
                  :points)) p))
         ([[:arc  x]]
          (let [{:keys [a r1 r2 angle f1 f2  end]} x]
            [(name a) r1 r2 angle (if f1 1 0) (if f2 1 0)
             ((juxt (comp (tx2 a) s :x)
                    (comp (ty2 a) s :y)) end)]))
         ([[:curve-c  c]]
          ((juxt (comp
                  name
                  :c)
                 (comp
                  (fn [l]
                    (map
                     (juxt (comp (tx2 (get c :c))  s :x)
                           (comp (ty2 (get c :c))  s :y))
                     l))
                  :points) ) c))
         ([[:curve-q  q]]
          ((juxt (comp
                  name
                  :q)
                 (comp
                  (fn [l]
                    (map
                     (juxt (comp (tx2 (get q :q)) s :x)
                           (comp (ty2 (get q :q)) s :y))
                     l))
                  :points) ) q))
         ))
       :d))
     (fn [x]
       (s/conform :math2/svg-path x)))))




(comment

  ((comp
    (fn [d] [:path {:d d}
             :stroke (c [20 50 50])
             :stroke-width 2
             :fill :none])
    (path22 [(s 40 10) (partial + 200) (partial + -400)]))
   (nth (gen/sample (s/gen :math2/svg-path 10)) 3))


  (nth (path-temp2) 0))

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


(defn circle2 [[s tx ty]]
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
          :fill (c [10 70 60])}]))


  ((cr [s tx ty])
   ))

(comment
  (let
      [[s tx ty] [(comp
                   (fn [[x y]] (+ x y))
                   (juxt (comp (fn [x] (* x 30))  first)
                         (comp (fn [x] (* x 6) ) second)))
                  (fn [x] (+ (* 40 -2) x))
                  (fn [y] (- (+ (* 40 (- 12 4)) 0) y))]
       [txt-fn circle-fn]
       [(fn [x y s]
          [:text {:x x
                  :y  y
                  :font-size (size [1 :rem])} s])
        (fn [x y r] [:circle {:cx x
                              :cy  y
                              :r  4
                              :fill (c [70 70 70])}
                     ])]
       data (gen/sample (s/gen :math2/svg-circle))]
    ((comp
      (fn [[x y f v]]
        (f x y v))
      (partial mapcat identity )
      (juxt
       (comp
        (juxt
         (comp tx s :x)
         (comp ty s :y)) :point)
       (comp
        (juxt (comp
               (fn [n]
                 (condp = n
                   :text txt-fn
                   :r circle-fn))
               first) (comp
                       second))
             :attr))
      (fn [n]
        (s/conform :math2/svg-circle n)))
     (first data)))
  )



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
              ]])

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
  (let [ve (fn [n] (* n -1))
        grad2 (fn [n]
                [4
                 -5 :% (+ n .5) 70 70 0.3
                 20 :% (+ n n .5) 70 70 0.2
                 80 :% (+ n n .5)  70 70 0.3])
        mm m
        [m1 n1] [3 5]
        [start x1 m n] [9 2 (* 2 m1) (* 2 n1)]
        dx 4
        dy 2
        x (* x1 dx)
        y (* x1 dy)
        v 'h

        vb (str/join
            " "
            [(* 40 -0.55) (* 40 0.5)
             (* 40 18) (* 40 18)])
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
            :grid-template-columns (size (apply concat (take 12 (repeat [10 :vh]))))
            :grid-template-rows (size (apply concat (take 10 (repeat [10 :vh]))))}}


     (comment solve )
     (let [x2 [:m ['* dx dy] [:p v 2]]
           a m1
           b n1
           ab+ [:b ['+ ['- ['* dx  a]] ['* dy b]]]
           abx+ [:m ab+ v]
           ab ['* ['- a] b]
           r1 ['+ x2 abx+ ab]
           r2 ['+ x2 [:m  (- m1 n1) v] ab]
           s1 [:b ['+ v m1]]
           s2 [:b ['+ v n1]]
           sol [:m s1 s2]
           ]
       (map-indexed
        (fn [i eq]
          [:div  (g [[(inc i) 1] [8 7] [10 1]]
                    {:d (grad2 (inc i))
                     :size [2 :rem]
                     :flex :center})
           [mm eq]

           ])
        [['= r2 0] ['= r1 0]  x2 abx+ ab
         ['= sol 0]
         ['= s1 0]
         ['= v (* m1 -1)]]))





     [:div {
            :style
            {:z-index 2
             :overflow :hidden
             :color :#444
             :grid-row (str/join "/" [2 -2])
             :grid-column (str/join "/" [2 -2])
             :background-color (c [70 70 70])
             }}
      [:svg {:style {:background-color (c [150 70 70])
                     :height (size [100 :%])
                     :width (size [100 :%])}
             :viewBox vb
             }

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
             [
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
                       :fill  (c [80 70 80])
                       }])
             [s (comp tx s) (comp ty s)])
            [
             (for [i (range dx)
                   :let [k 2
                         x1 (* i k)]
                   j (range dy)
                   :let [k 2
                         y1 (+ start
                               (* j k -1))]
                   ]
               [[x1 0] [y1 0]
                :l [k 0] [0 0] [0 0] [(* k -1) 0]
                [(* k -1) 0] [0 0] [0 0] [k 0]])

             (for [i (range 0 n1)
                   :let [k 2
                         x1 (+ x (* i k))]
                   j (range 0 m1)
                   :let [k 2
                         y1 (+ (- start y) (* j k -1))]]
               [[x1 0] [y1 0]
                :l [k 0] [0 0] [0 0] [(* k -1) 0]
                [(* k -1) 0] [0 0] [0 0] [k 0]])])






       (comment   a + b)
       (map (paths
             (fn [d]
               [:path {:d d
                       :stroke (c [120 70 50])
                       :stroke-width 4
                       :marker-end (url "i")
                       :fill :none}])
             [s (comp tx s) (comp ty s)])
            [
             [
              [
               [0 0] [(- start y (/ m 4)) 0]
               :a 4 4 0 true true [0 3] [0 0]
               :a 4 4 0 true true [0 -3] [0 0]
               :l
               [0 -3] [0  0]
               [0 0] [(+ y (/ m 4))  2]
               [9 0] [ 0 0]
               [0 0] [ -2 0]
               :a 4 4 0 true true [0 3] [0 0]
               :a 4 4 0 true true [0 -3] [0 0]
               :l
               [0 0] [2 0]
               [8 0] [0 0]
               [0 0] [-7 0]
               [2 0] [0 0]
               ]



              #_[[0 0] [(- start y) 0]
                 :l
                 [x 0] [0 0]
                 [0 0] [(* n -1) 0 ]
                 [(* -1 x) 0] [0 0]]]])

       (comment  pink  [x a][x b] )
       (map (paths
             (fn [d]
               [:path {:d d
                       :stroke (c [400 70 80])
                       :stroke-width 4
                       :marker-end (url "i")
                       :fill :none}])
             [s (comp tx s) (comp ty s)])
            [
             [
              [[0 0] [(+ 0 start) 0]
               :l
               [x 0] [0 0]
               [n 0] [0 0]
               [0 0] [(* -1 (+ x m)) 0]]
              ]])



       (comment blue kkx2)
       (map (paths
             (fn [d]
               [:path {:d d
                       :stroke (c [250 70 80])
                       :stroke-width 2
                       :marker-end (url "i")
                       :fill :none}])
             [s (comp tx s) (comp ty s)])
            [
             [

              [
               [0 3] [start -3]
               :a 4 4 0 true true [0 3] [0 0]
               :a 4 4 0 true true [0 -3] [0 0]
               :l
               [0 0] [0 4]
               [15 0] [0 0]
               [0 0] [-4 0]
               [2 0] [0 0]
               ]

              ]])

       (comment purple one a*b )
       (map (paths
             (fn [d]
               [:path {:d d
                       :stroke (c [10 70 50])
                       :stroke-width 3
                       :marker-end (url "i")
                       :fill :none}])
             [s (comp tx s) (comp ty s)])
            [
             [

              [
               [(+ x  (/ n 2)) 3] [(- start y (/ m 4)) 0]
               :a 4 4 0 true true [0 3] [0 0]
               :a 4 4 0 true true [0 -3] [0 0]
               :l
               [0 0] [-4 0]
               [10 0] [0 0]

               ]

              ]])
       (let [[txt-fn circle-fn]
            [(fn [x y s]
               [:text {:x x
                       :y  y
                       :font-size (size [1 :rem])} s])
             (fn [x y r] [:circle
                          {:cx x
                           :cy  y
                           :r  r
                           :fill (c [70 70 70])}
                          ])]
             #_ (gen/sample (s/gen :math2/svg-circle))
             data [[[0 0] [0 0] 2]]
             ]
         (map
          (comp
           (fn [[x y f v]]
             (f x y v))
           (partial mapcat identity )
           (juxt
            (comp
             (juxt
              (comp tx s :x)
              (comp ty s :y)) :point)
            (comp
             (juxt (comp
                    (fn [n]
                      (condp = n
                        :text txt-fn
                        :r circle-fn))
                    first) (comp
                            second))
             :attr))
           (fn [n]
             (s/conform :math2/svg-circle n)))
          data))
       ]]]))








(defn grid9 []
  (let [mm m
        [m1 n1] [4 5]
        [start x1 m n] [8 2 (* 2 m1) (* 2 n1)]
        dx 1
        dy 1
        x (* x1 dx)
        y (* x1 dy)
        v 'x
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


     [:div  (g [[3 1] [3 1] [10 1]]
               {:d [1
                    -5 :% .2 70 70 0.5
                    20 :% .5 70 70 0.5
                    80 :% .7 70 70 0.5]
                :size [1 :rem]
                :flex :center})
      [mm [:p 'x 2]]]

     [:div  (g [[3 1] [3 (+ 2 m1 )] [10 1]]
               {:d [1
                    -5 :% .2 70 70 0.5
                    20 :% .5 70 70 0.5
                    80 :% .7 70 70 0.5]
                :size [1 :rem]
                :flex :center})
      [mm [:m 'm 'x ]]]

     [:div  (g [[(+ 3 n1 -1) 1] [3 (+ 2 m1 )] [10 1]]
               {:d [1
                    -5 :% .2 70 70 0.5
                    20 :% .5 70 70 0.5
                    80 :% .7 70 70 0.5]
                :size [1 :rem]
                :flex :center})
      [mm [:m 'm 'x ]]]

     [:div  (g [[3 1] [(+ 3 n1 1) 1] [10 1]]
               {:d [1
                    -5 :% .2 70 70 0.5
                    20 :% .5 70 70 0.5
                    80 :% .7 70 70 0.5]
                :size [1 :rem]
                :flex :center})
      [mm [:p 'x 2]]]

     [:div  (g [[(+ 3 1) m1] [(+ 3 n1 1) 1] [10 1]]
               {:d [1
                    -5 :% .2 70 70 0.5
                    20 :% .5 70 70 0.5
                    80 :% .7 70 70 0.5]
                :size [1 :rem]
                :flex :center})
      [mm [:m 'n 'x ]]]

     [:div  (g [[(+ 3 m1) 1] [3 1] [10 1]]
               {:d [1
                    -5 :% .2 70 70 0.5
                    20 :% .5 70 70 0.5
                    80 :% .7 70 70 0.5]
                :size [1 :rem]
                :flex :center})
      [mm [:p 'x 2]]]

     [:div  (g [[(+ 3 1) m1] [3 1] [10 1]]
               {:d [1
                    -5 :% .2 70 70 0.5
                    20 :% .5 70 70 0.5
                    80 :% .7 70 70 0.5]
                :size [1 :rem]
                :flex :center})
      [mm [:m 'n 'x ]]]


     [:div  (g [[3 2] [(+ 3  2 1  m1) 4] [10 1]]
               {:d [1
                    -5 :% .2 70 70 0.5
                    20 :% .5 70 70 0.5
                    80 :% .7 70 70 0.5]
                :size [1 :rem]
                :flex :center})
      [mm '[= [+ [:m 4 [:p x 2]]
               [* 2 [:m 70 x]]
               [* 2 [:m 50 x]]]
            1024]]

      [mm '[= [+ [:m 4 [:p x 2]]

               [:m 240 x]]
            1024]]

      [mm '[= [+ [:m [4 4] [:p x 2]]

               [:m [240 4] x]]
            [1024 4]]]
      [mm '[= [+ [:m 1
                  [:p x 2]]

               [:m [[* 60 4] 4]
                x]]
            [[* 256 4] 4]]]

      [mm '[= [+ [:m 1
                  [:p x 2]]

               [:m 60
                x]]
            [:m [:p 2 4] [:p 2 4]]]]
      ]





     [:div  (g [[(+ 3 m1) 1] [(+ 3 n1 1) 1] [10 1]]
               {:d [1
                    -5 :% .2 70 70 0.5
                    20 :% .5 70 70 0.5
                    80 :% .7 70 70 0.5]
                :size [1 :rem]
                :flex :center})
      [mm [:p 'x 2]]]





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




       (map (paths
         (fn [d]
           [:path {:d d
                   :stroke (c [20 70 80])
                   :stroke-width 2
                   :marker-end (url "i")
                   :fill (c [40 70 80])}])
         [s (comp tx s) (comp ty s)])
            [

             [[[0 0] [start 0] :l
               [x 0] [0 0]
               [0 0] [(* -1 x) 0]
               [(* x -1) 0] [0 0]
               [0 0] [(* 1 x) 0]
               [x 0] [0 0]

               [n 0] [0 0]
               [0 0] [(* -1 x) 0]
               [(* n -1) 0] [0 0]
               [0 0] [(* 1 x) 0]
               [n 0] [0 0]

               [x 0] [0 0]
               [0 0] [(* -1 x) 0]
               [(* x -1) 0] [0 0]
               [0 0] [(* 1 x) 0]
               [x 0] [0 0]
               [0 0] [(* -1 x) 0]

               [0 0]  [(* -1 m) 0]
               [(* x -1) 0] [0 0]
               [0 0] [(* 1 x) 0]
               [x 0] [0 0]
               [0 0] [(* -1 x) 0]
               [(* x -1) 0] [0 0]
               [0 0] [(* 1 m) 0]]
              ]

             [[[0 0] [(- start x) 0] :l
               [0 0] [(* -1 (- n x x)) 0]
               [x 0] [0 0]
               [0 0] [(* 1 (- n x x)) 0]
               [(* -1 x) 0] [0 0]
               [0 0] [(* -1 (- n x)) 0]
               [(* 1 x) 0] [0 0]
               [0 0] [(* 1 x) 0]
               [(* -1 x) 0] [0 0]
               [0 0] [(* -1 x) 0]
               [(+ x x m) 0] [0 0]
               [0 0] [(* 1 x) 0]
               [(* -1 (+ x x m)) 0] [0 0]
               ]]])

       (map (paths
             (fn [d]
               [:path {:d d
                       :stroke (c [300 70 80])
                       :stroke-width 4
                       :marker-end (url "i")
                       :fill :none}])
             [s (comp tx s) (comp ty s)])
            [
             [
              [[x 0] [(- start y) 0]
               :l

               [n 0] [0 0]
               [0 0] [(* -1 (- m y) ) 0]]

              [[(+ x n) 0] [start 0]
               :l

               [0 0] [ (* -1 y) 0]
               ]
              [[(+ x n) 0] [(- start y 8) 0]
               :l

               [(* x 1) 0] [0
                      0]
               ]



              #_[[x 0] [start 0]
                 :l
                 [m 0] [0 0]
                 [0 0] [(* y -1) 0 ]
                 [(* -1 m) 0] [0 0]]
              #_[[0 0] [(- start y) 0]
                 :l
                 [x 0] [0 0]
                 [0 0] [(* n -1) 0 ]
                 [(* -1 x) 0] [0 0]]]])
       ]]
     ]))




(defn grid10 []
  (let [ve (fn [n] (* n -1))

        mm m
        [m1 n1] [9 7]
        [start x1 m n] [9 2 (* 2 m1) (* 2 n1)]
        dx 1
        dy 1
        x (* x1 dx)
        xt1 (+ x 0)
        yt1 6.5
        y (* x1 dy)
        v 'x
        vb (str/join " "
                     [(* 40 -0.55) (* 40 0.5)
                      (* 40 12) (* 40 12)])
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
            :grid-template-columns (size (apply concat (take 12 (repeat [10 :vh]))))
            :grid-template-rows (size (apply concat (take 10 (repeat [10 :vh]))))}}



     [:div  (g [[3 1] [3 1] [10 1]]
               {:d [1
                    -5 :% 1.5 70 70 0.5
                    20 :% 1.8 70 70 0.5
                    80 :% 2.2 70 70 0.5]
                :size [2 :rem]
                :flex :center})

      ]

     [:div {
            :style
            {:z-index 2
             :overflow :hidden
             :color :#444
             :grid-row (str/join "/" [2 -2])
             :grid-column (str/join "/" [2 -2])
             :background-color (c [70 70 70])
             }}
      [:svg {:style {:background-color (c [150 70 70])
                     :height (size [100 :%])
                     :width (size [100 :%])}
             :viewBox vb
             }

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
             [



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
                   :fill  (c [80 70 80])
                   }])
         [s (comp tx s) (comp ty s)])
            [
             [
              [[0 0] [(+ 0 start) 0]
               :l
               [x 0] [0 0]
               [n 0] [0 0]]
              ]


             (for [i (range 0 n)
                   :let [k 1
                         x1 (+ x (* i k))]
                   j (range 0 m)
                   :let [k 1
                         y1 (+ (- start y) (* j k -1))]]
               [[x1 0] [y1 0]
                :l [k 0] [0 0] [0 0] [(* k -1) 0]
                [(* k -1) 0] [0 0] [0 0] [k 0]])])




       (map (paths
             (fn [d]
               [:path {:d d
                       :stroke (c [300 70 80])
                       :stroke-width 2
                       :stroke-dasharray (str/join " " [550 1000])
                       :marker-end (url "i")
                       :fill :none}])
             [s (comp tx s) (comp ty s)])
            [
             [
              [[0 0] [0 0]
               :l
               [xt1 0] [yt1 0]
               ]

              [[0 0] [0 0]
               :l
               [m1 0] [yt1 0]
               ]

              [[0 0] [0 0]
               :l
               [(+ m1 y) 0] [0 0]
               ]

              [[0 0] [0 0]
               :c
               [xt1 0] [yt1 0]
               [(- m1 2) 0] [yt1 0]
               [m1
                0] [0 0]
               ]

              #_[[x 0] [start 0]
                 :l
                 [m 0] [0 0]
                 [0 0] [(* y -1) 0 ]
                 [(* -1 m) 0] [0 0]]
              #_[[0 0] [(- start y) 0]
                 :l
                 [x 0] [0 0]
                 [0 0] [(* n -1) 0 ]
                 [(* -1 x) 0] [0 0]]]])
       (let [[txt-fn circle-fn]
            [(fn [x y s]
               [:text {:x x
                       :y  y
                       :font-size (size [2 :rem])} s])
             (fn [x y r] [:circle
                          {:cx x
                           :cy  y
                           :r  r
                           :fill (c [170 70 50])}
                          ])]
             #_ (gen/sample (s/gen :math2/svg-circle))
             data (map
                   (fn [i]
                     [[i 0] [(/ (- (* 45 i)
                                   (* 5 i i))
                                20) 0] 2])
                   (range 0 10))
             ]
         (map
          (comp
           (fn [[x y f v]]
             (f x y v))
           (partial mapcat identity )
           (juxt
            (comp
             (juxt
              (comp tx s :x)
              (comp ty s :y)) :point)
            (comp
             (juxt (comp
                    (fn [n]
                      (condp = n
                        :text txt-fn
                        :r circle-fn))
                    first) (comp
                            second))
             :attr))
           (fn [n]
             (s/conform :math2/svg-circle n)))
          data))
       ]]]))


(defn grid11 []
  (let [ve (fn [n] (* n -1))
        grad2 (fn [n]
                [4
                 -5 :% (+ n .5) 70 70 0.3
                 20 :% (+ n n .5) 70 70 0.2
                 80 :% (+ n n .5)  70 70 0.3])
        mm m
        [m1 n1] [10 10]
        [start x1 m n] [9 2 (* 2 m1) (* 2 n1)]
        dx 1
        dy 1
        x (* x1 dx)
        y (* x1 dy)
        v 'x

        vb (str/join
            " "
            [(* 40 -0.55) (* 40 0.5)
             (* 40 30) (* 40 30)])
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
            :grid-template-columns (size (apply concat (take 12 (repeat [10 :vh]))))
            :grid-template-rows (size (apply concat (take 10 (repeat [10 :vh]))))}}

     (comment
       (let [x2 [:p v 2]
             a m1
             b n1
             ab+ [:b ['+ a b]]
             abx+ [:m ab+ v]
             ab ['* a b]
             r1 ['+ x2 abx+ ab]]
         (map-indexed
          (fn [i eq]
            [:div  (g [[(inc i) 1] [8 7] [10 1]]
                      {:d (grad2 (inc i))
                       :size [2 :rem]
                       :flex :center})
             [mm eq]

             ])
          [r1  x2 abx+ ab ])))


     [:div {
            :style
            {:z-index 2
             :overflow :hidden
             :color :#444
             :grid-row (str/join "/" [2 -2])
             :grid-column (str/join "/" [2 -2])
             :background-color (c [70 70 70])
             }}
      [:svg {:style {:background-color (c [150 70 70])
                     :height (size [100 :%])
                     :width (size [100 :%])}
             :viewBox vb
             }

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
             [
              [[0 0] [start 0]
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
                   :fill  (c [80 70 80])
                   }])
         [s (comp tx s) (comp ty s)])
            [
             [
              [[0 0] [(+ 0 start) 0] :l [x 0] [0 0] [n 0] [0 0]]
              [[0 -2] [(+ 0 start) 0] :l [0 0] [(* -1 y) 0]
               [0 0] [(* -1 y) 0]]
              [[x 0] [(- start 1) 0] :l [n 0] [0 0]]

              #_[[x 0] [start 0]
                 :l
                 [m 0] [0 0]
                 [0 0] [(* y -1) 0 ]
                 [(* -1 m) 0] [0 0]]
              #_[[0 0] [(- start y) 0]
               :l
               [x 0] [0 0]
               [0 0] [(* n -1) 0 ]
               [(* -1 x) 0] [0 0]]]


             (for [i (range dx)
                   :let [k 2
                         x1 (* i k)]
                   j (range dy)
                   :let [k 2
                         y1 (+ start
                               (* j k -1))]
                   ]
               [[x1 0] [y1 0]
                :l [k 0] [0 0] [0 0] [(* k -1) 0]
                [(* k -1) 0] [0 0] [0 0] [k 0]])


             (for [i (range 0 n1)
                   :let [k 2
                         x1 (+ x (* i k))]
                   j (range 0 m1)
                   :let [k 2
                         y1 (+ (- start y) (* j k -1))]]
               [[x1 0] [y1 0]
                :l [k 0] [0 0] [0 0] [(* k -1) 0]
                [(* k -1) 0] [0 0] [0 0] [k 0]])

             (for [i (range 0 n1)
                   :let [k 2
                         x1 (+ x 24 (* i k))]
                   j (range 0 m1)
                   :let [k 2
                         y1 (+ (- start y) (* j k -1))]]
               [[x1 0] [y1 0]
                :l [k 0] [0 0] [0 0] [(* k -1) 0]
                [(* k -1) 0] [0 0] [0 0] [k 0]])

             ]
            )




       (map (paths
             (fn [d]
               [:path {:d d
                       :stroke (c [300 70 80])
                       :stroke-width 6
                       :marker-end (url "i")
                       :fill :none}])
             [s (comp tx s) (comp ty s)])
            [
             [
              #_[[0 0] [(+ 0 start) 0]
               :l
               [x 0] [0 0]
               [n 0] [0 0]
               [0 0] [(* -1 (+ x m)) 0]
               [(* -1 (+ x n))   0] [ 0 0]
               [0 0] [(* 1 (+ x m)) 0]]



              [
               [(+ x 4) 0] [(- start 2) 0]
               :c
               [2 0] [-1 0]
               [2 0] [-2 0]
               [0 0] [-4 0]
               ]
              [
               [(+ x 14) 0] [(- start 2) 0]
               :c
               [-2 0] [-1 0]
               [-2 0] [-3 0]
               [0 0] [-4 0]
               :l
               [-10 0] [0 0]
               ]



              #_[[0 0] [(- start y) 0]
                 :l
                 [x 0] [0 0]
                 [0 0] [(* n -1) 0 ]
                 [(* -1 x) 0] [0 0]]]])
       (let [[s tx ty] [(comp
                         (fn [[x y]] (+ x y))
                         (juxt (comp (fn [x] (* x 30))  first)
                               (comp (fn [x] (* x 6) ) second)))
                        (fn [x] (+ (* 40 -2) x))
                        (fn [y] (- (+ (* 40 (- 12 4)) 0) y))]
             [txt-fn circle-fn]
            [(fn [x y s]
               [:text {:x x
                       :y  y
                       :font-size (size [2 :rem])} s])
             (fn [x y r] [:circle
                          {:cx x
                           :cy  y
                           :r  r
                           :fill (c [70 70 70])}
                          ])]
             tns (comp
                  (fn [[x y f v]]
                    (f x y v))
                  (partial mapcat identity )
                  (juxt
                   (comp
                    (juxt
                     (comp tx s :x)
                     (comp ty s :y)) :point)
                   (comp
                    (juxt (comp
                           (fn [n]
                             (condp = n
                               :text txt-fn
                               :r circle-fn))
                           first) (comp
                                   second))
                    :attr))
                  (fn [n]
                    (s/conform :math2/svg-circle n)))
             #_ (gen/sample (s/gen :math2/svg-circle))




             data5 (map-indexed
                    (fn [i c]
                      [[(+ (* i 2) 3) 0] [(- start 3) 0] c])
                    ["" "6"  ""  "1" "2" "1" "" "2" "0" ""])

             data6 (map-indexed
                    (fn [i c]
                      [[(+ (* i 2) 3) 0] [(- start 5) 0] c])
                    ["" "" ""  "1"  "2" "0" "" "" "" "" ])

             data7 (map-indexed
                    (fn [i c]
                      [[(+ (* i 2) 3) 0] [(- start 7) 0] c])
                    ["" "" ""  ""  "" "1" "" "" "" "" ])



             data4 (map-indexed
                    (fn [i c]
                      [[25 0] [(- (* i 2) 12) 0] (str (- 11 c))])
                    (range 1 11))
             ]
         (map tns (for [i [ data4
                           data5
                           data6
                           data7]
                    j (range 0 10)
                    :let [k (nth i j)]
                    ]
                    k)))




       ]]]))


(defn grid12 []
  (let [ve (fn [n] (* n -1))
        grad2 (fn [n]
                [4
                 -5 :% (+ n .5) 70 70 0.3
                 20 :% (+ n n .5) 70 70 0.2
                 80 :% (+ n n .5)  70 70 0.3])
        mm m
        [m1 n1] [11 11]
        [start x1 m n] [9 2 (* 2 m1) (* 2 n1)]
        dx 1
        dy 1
        x (* x1 dx)
        y (* x1 dy)
        v 'h
        vb (str/join
            " "
            [(* 40 -0.55) (* 40 0.55)
             (* 40 30) (* 40 30)])

        [s tx ty] [(comp
                    (fn [[x y]] (+ x y))
                    (juxt (comp (fn [x] (* x 30))  first)
                          (comp (fn [x] (* x 6) ) second)))
                   (fn [x] (+ (* 40 (ve 2)) x))
                   (fn [y] (- (+ (* 40 (- 12 4)) 0) y))]
        tfun-e1 [s (comp tx s) (comp ty s)]]



    [:div {:style
           {:background-color (c [90 70 70])
            :display :grid
            :height (size [100 :vh])
            :width (size [100 :vw])
            :grid-template-columns
            (size
             (apply concat (take 12 (repeat [10 :vh]))))
            :grid-template-rows
            (size
             (apply concat (take 10 (repeat [10 :vh]))))}}


     [:div  (g [[8 3] [4 7] [10 1]]
               {:d (grad2 2)
                :size [1.7 :rem]
                :flex :center})
      [mm '[= [+ [:m 4 x] [:m 4 y]] 44]]
      [mm '[= [:b [- 11 x]] y]]
      [mm '[= [+ [:p x 2]  [:p y 2]] 65]]
      [mm '[= [+ [:p x 2]  [:p [:b [- 11 x]] 2 ]] 65]]
      [mm '[= [+ [:p x 2]  [:p x 2] [:m [- 11] x] [:m [- 11] x] [:p 11 2] ]
            65]]

      ]

     (comment solve )
     (comment(let [x2 [:m ['* dx dy] [:p v 2]]
                   a m1
                   b n1
                   ab+ [:b ['+ ['- ['* dx  a]] ['* dy b]]]
                   abx+ [:m ab+ v]
                   ab ['* ['- a] b]
                   r1 ['+ x2 abx+ ab]
                   r2 ['+ x2 [:m  (- m1 n1) v] ab]
                   s1 [:b ['+ v m1]]
                   s2 [:b ['+ v n1]]
                   sol [:m s1 s2]
                   ]
               (map-indexed
                (fn [i eq]
                  [:div  (g [[(inc i) 1] [8 7] [10 1]]
                            {:d (grad2 (inc i))
                             :size [1.7 :rem]
                             :flex :center})
                   [mm eq]

                   ])
                [['= r2 0] ['= r1 0]  x2 abx+ ab
                 ['= sol 0]
                 ['= s1 0]
                 ['= v (* m1 -1)]])))





     [:div {
            :style
            {:z-index 2
             :overflow :hidden
             :color :#444
             :grid-row (str/join "/" [2 -2])
             :grid-column (str/join "/" [2 -2])
             :background-color (c [70 70 70])
             }}
      [:svg {:style {:background-color (c [150 70 70])
                     :height (size [100 :%])
                     :width (size [100 :%])}
             :viewBox vb
             }

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



       (comment
         )
       (map (paths (fn [d]
                     [:path {:d d
                             :stroke (c [90 70 50])
                             :stroke-width 2
                             :fill :none}])
                   [s (comp tx s) (comp ty s)])
            [
             [
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

       (comment
         )
       (map (paths
               (fn [d]
                 [:path {:d d
                         :stroke (c [20 70 80])
                         :stroke-width 2
                         :fill  (c [80 70 80])
                         }])
               [s (comp tx s) (comp ty s)])
              [
               (for [i (range dx)
                     :let [k 2
                           x1 (* i k)]
                     j (range dy)
                     :let [k 2
                           y1 (+ start
                                 (* j k -1))]
                     ]
                 [[x1 0] [y1 0]
                  :l [k 0] [0 0] [0 0] [(* k -1) 0]
                  [(* k -1) 0] [0 0] [0 0] [k 0]])

               (for [i (range 0 n1)
                     :let [k 2
                           x1 (+ x (* i k))]
                     j (range 0 m1)
                     :let [k 2
                           y1 (+ (- start y) (* j k -1))]]
                 [[x1 0] [y1 0]
                  :l [k 0] [0 0] [0 0] [(* k -1) 0]
                  [(* k -1) 0] [0 0] [0 0] [k 0]])])






       (comment   a + b)
       (comment )
       (comment
         (map (paths
               (fn [d]
                 [:path {:d d
                         :stroke (c [120 70 50])
                         :stroke-width 4
                         :marker-end (url "i")
                         :fill :none}])
               [s (comp tx s) (comp ty s)])
              [
               [
                [
                 [0 0] [(- start y (/ m 4)) 0]
                 :a 4 4 0 true true [0 3] [0 0]
                 :a 4 4 0 true true [0 -3] [0 0]
                 :l
                 [0 -3] [0  0]
                 [0 0] [(+ y (/ m 4))  2]
                 [9 0] [ 0 0]
                 [0 0] [ -2 0]
                 :a 4 4 0 true true [0 3] [0 0]
                 :a 4 4 0 true true [0 -3] [0 0]
                 :l
                 [0 0] [2 0]
                 [8 0] [0 0]
                 [0 0] [-7 0]
                 [2 0] [0 0]
                 ]



                #_[[0 0] [(- start y) 0]
                   :l
                   [x 0] [0 0]
                   [0 0] [(* n -1) 0 ]
                   [(* -1 x) 0] [0 0]]]]))



       (comment  pink  [x a][x b] )
       (map (paths
               (fn [d]
                 [:path {:d d
                         :stroke (c [400 70 80])
                         :stroke-width 4
                         :marker-end (url "i")
                         :fill :none}])
               [s (comp tx s) (comp ty s)])
              [
               [
                [[0 0] [(+ 0 start) 0]
                 :l
                 [x 0] [0 0] [n 0] [0 0]
                 [0 0] [(* -1 (+ y m)) 0]]
                ]

               [
                #_[[ (* 3 (/ n 4)) 0] [(+ -20 start) 0]
                   :l
                   [(/ n 4) 0] [0 0]
                   [0 0] [(/ n 4) 0]
                   [(ve (/ n 4)) 0] [0 0]
                   [0 0] [(ve (/ n 4)) 0]
                   [(/ n 4) 0] [0 0]
                   [(/ x 4) 0] [0 0]
                   [0 0] [(/ x 4) 0]
                   [(ve (/ x 4)) 0] [0 0]
                   [0 0] [(ve (/ x 4)) 0]
                   [(ve (* 1 (/ n 4))) 0] [0 0]
                   [0 0] [(/ n 4) 0]
                   [(/ n 4) 0] [0 0]


                   ]

                [[28 0] [0 15] :l [0 0] [(- start 15) 0]  ]

                [[0 0] [(+ -30 start) 0]
                 :l
                 [(+ 20 24) 0] [0 0]]

                [[0 0] [(+ -29 start) -3]
                 :l
                 [(+ 20 0) 0] [0 0]]


                [[20 0] [(+ -29 start) 0]
                 :l
                 [-5 0] [0 0]
                 [0 0] [5 0]
                 [5 0] [0 0]
                 [0 0] [-5 0]


                 ]

                [[20 0] [(+ -28 start) 0]
                 :l
                 [24 0] [0 0]]





                [[20 0] [(+ -27 start) 0]
                 :l
                 [6 0] [0 0]
                 [0  0] [6 0]
                 [-6 0] [0 0]
                 [0 0] [-6 0]
                 ]


                ]




               ])
       (comment
         )
       (comment
         )
       (map (paths
             (fn [d]
               [:path {:d d
                       :stroke (c [250 70 80])
                       :stroke-width 2
                       :fill :none}])
             [s (comp tx s) (comp ty s)])
            [(for [i (range 0 44)
                   :let [x (+ 0 (* i 1))]]
               [[x 0] [(- start 32) 0] :l
                [0 0] [0 2]
                [0 0] [0 -2]
                [1 0] [0 0]
                [0 0] [0 2]])])

       (comment blue kkx2)
       (comment
         (map (paths
               (fn [d]
                 [:path {:d d
                         :stroke (c [250 70 80])
                         :stroke-width 2
                         :marker-end (url "i")
                         :fill :none}])
               [s (comp tx s) (comp ty s)])
              [
               [

                [
                 [0 3] [start -3]
                 :a 4 4 0 true true [0 3] [0 0]
                 :a 4 4 0 true true [0 -3] [0 0]
                 :l
                 [0 0] [0 4]
                 [15 0] [0 0]
                 [0 0] [-4 0]
                 [2 0] [0 0]
                 ]

                ]]))

       (comment purple one a*b )
       (comment
         (map (paths
               (fn [d]
                 [:path {:d d
                         :stroke (c [10 70 50])
                         :stroke-width 3
                         :marker-end (url "i")
                         :fill :none}])
               [s (comp tx s) (comp ty s)])
              [
               [

                [
                 [(+ x  (/ n 2)) 3] [(- start y (/ m 4)) 0]
                 :a 4 4 0 true true [0 3] [0 0]
                 :a 4 4 0 true true [0 -3] [0 0]
                 :l
                 [0 0] [-4 0]
                 [10 0] [0 0]]

                ]]))




       (let [[txt-fn circle-fn]
            [(fn [x y s]
               [:text {:x x
                       :y  y
                       :font-size (size [1.5 :rem])} s])
             (fn [x y r] [:circle
                          {:cx x
                           :cy  y
                           :r  r
                           :fill (c [70 70 70])}
                          ])]
             #_ (gen/sample (s/gen :math2/svg-circle))

             data2 [[[0 0] [(- start 0) 0] "-x"]
                    [[n 0] [(- start 0) 0] "11"]
                    [[24 2] [(- start 27) 0] "y"]
                    [[30 2] [(- start 27) -3] "4y"]]
             data (conj (conj data2 [[17 2] [(- start 29) 0] "x"])
                        [[10 0] [(- start 29) -2] "4x"]
                        [[30 0] [(- start 31) 2] "44"])
             ]
         (map
          (comp
           (fn [[x y f v]]
             (f x y v))
           (partial mapcat identity )
           (juxt
            (comp
             (juxt
              (comp tx s :x)
              (comp ty s :y)) :point)
            (comp
             (juxt (comp
                    (fn [n]
                      (condp = n
                        :text txt-fn
                        :r circle-fn))
                    first) (comp
                            second))
             :attr))
           (fn [n]
             (s/conform :math2/svg-circle n)))
          data))
       ]]]))

(comment(->
         ref
         .-current
         (.getTotalLength)))
(defn grid13 []
  (let [ref (react/useRef)

        [get-v set-v] (react/useState 0)
        _ (react/useEffect
           (fn []
             (set-v (->
                     ref
                     .-current
                     (.getTotalLength)))
             (js/console.log "effect"
                             (->
                              ref
                              .-current
                              (.getTotalLength)))))
        ve (fn [n] (* n -1))
        [s tx ty] [(comp
                    (fn [[x y]] (+ x y))
                    (juxt (comp (fn [x] (* x 30))  first)
                          (comp (fn [x] (* x 6) ) second)))
                   (fn [x] (+ (* 40 (ve 2)) x))
                   (fn [y] (- (+ (* 40 (- 12 4)) 0) y))]
        m-add (fn [m] (str "M " m))
        space (fn [p] (str/join " " p))
        grad2 (fn [n]
                [4
                 -5 :% (+ n .5) 70 70 0.3
                 20 :% (+ n n .5) 70 70 0.2
                 80 :% (+ n n .5)  70 70 0.3])
        mm m
        [m1 n1] [10 12]
        [start x1 m n] [9 2 (* 2 m1) (* 2 n1)]
        dx 1
        dy 1
        x (* x1 dx)
        y (* x1 dy)
        v 'h
        box '☐
        vb (nth [(space [(* 40 -0.55) (* 40 0.5)
                         (* 40 24) (* 40 24)])
                 (space [(* 40 14.55) (* 40 0.5)
                         (* 4   60) (* 4 60)])
                 (space [(* 40 -4.55) (* 40 -4.5)
                         (* 40 24) (* 40 24)])]

                2)

        tfun-e1 [s (comp tx s) (comp ty s)]]


    [:div {:style
           {:background-color (c [90 70 70])
            :display :grid
            :height (size [100 :vh])
            :width (size [100 :vw])
            :grid-template-columns (size (apply concat (take 12 (repeat [10 :vh]))))
            :grid-template-rows (size (apply concat (take 10 (repeat [10 :vh]))))}}




     [:div  (g [[1 1] [8 7] [10 1]]
               {:d (grad2 2)
                :size [1.7 :rem]
                :flex :center})

      get-v
      ]





     (map-indexed
      (fn [i eq]
        [:div  (g [[(+ 1 (inc i)) 1] [8 7] [10 1]]
                  {:d (grad2 (inc i))
                   :size [1.5 :rem]
                   :flex :center})
         [mm eq]

         ])


      [ ['= 'Force
         ['* 'Sprint-constant
          'extension]]

       ['= [:m 10 'N] [:m 'k  'x1 ]]
       ['= [:m 20 'N] [:m 'k  'x2 ]]
       ['= [:m [:b ['- 20 10]] 'N] [:m 'k [:b ['- 'x2 'x1]] ]]
       ['= [:m 10 'N] ['* 'k .1 'm]]])


     [:div {
            :style
            {:z-index 2
             :overflow :hidden
             :color :#444
             :grid-row (str/join "/" [2 -2])
             :grid-column (str/join "/" [2 -2])
             :background-color (c [70 70 70])
             }}
      [:svg {:style {:transform-box :fill-box
                     :background-color (c [150 70 70])
                     :height (size [100 :%])
                     :width (size [100 :%])}
             :viewBox vb
             }

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



       (map (paths
             (fn [d]
               [:path {:d d
                       :stroke (c [20 80 10])
                       :stroke-width 1
                       :stroke-opacity 0.5
                       :fill :none
                       }])
             [s (comp tx s) (comp ty s)])
            [

             (for [i (range 0 m1)
                   :let [y (+ 2 (* i 2))]]
               [[0 -3] [(- start y 2) 0] :l [0 0] [0 4] [0 0]
                [0 -4] [0 0] [2 0] [0 4] [0 0]])

             (for [i (range 0 n1)
                   :let [x (+ 2 (* i 2))]]
               [[x 0] [start 0] :l [2 0] [0 0] [0 0] [0 -4]])])

       (comment
         )
       (map (paths
             (fn [d]
               [:path {:d d
                       :stroke (c [20 70 80])
                       :stroke-width 2
                       :fill  (c [80 70 80])
                       }])
             [s (comp tx s) (comp ty s)])
            [
             (for [i (range 0 n1)
                   :let [k 2
                         x1 (+ x (* i k))]
                   j (range 0 m1)
                   :let [k 2
                         y1 (+ (- start y) (* j k -1))]
                   ;;:when (not (and (= 16 j) (> i 4) ))
                   ]
               [[x1 0] [y1 0]
                :l [k 0] [0 0] [0 0] [(* k -1) 0]
                [(* k -1) 0] [0 0] [0 0] [k 0]])])


       (map (paths
             (fn [d]
               [:path {:d d
                       :stroke (c [20 70 80])
                       :stroke-width 2
                       :fill  (c [150 70 80])
                       }])
             [s (comp tx s) (comp ty s)])
            [
             (for [i (range 0 n1)
                   :let [k 2
                         x1 (+ x (* i k))]
                   j (range 0 7)
                   :let [k 2
                         y1 (+ (- start y) (* j k -1))]
                   ;;:when (not (and (= 2 j)
                   ;;                (> i 9) ))
                   ]
               [[x1 0] [y1 0]
                :l [k 0] [0 0] [0 0] [(* k -1) 0]
                [(* k -1) 0] [0 0] [0 0] [k 0]])])
       (comment
         ((comp
                  (partial + 200)
                  (ss 30 1)) [10 0]))
       ((comp
         (fn [d]
           [:path {:d d
                   :stroke (c [20 50 50])
                   :stroke-width 2
                   :fill-opacity .3
                   :stroke-opacity 1
                   :fill-rule :evenodd
                   :fill (c [170 70 70])}])
         (path22 [(ss 30 1) (partial + 400) (partial - 400)]))
        [[-1 0] [2 0] :l
         [0 0] [-6 0] [0 2] [0 0] [0 -4] [0 0] [0 2] [0 0]
         [0 0] [-2 0]
         [0 2] [0 0] [0 -4] [0 0] [0 2] [0 0]
         [0 0] [-6 0]
         [0 -4] [0 0]
         :a 1 1 20 true true [0 8] [0 0]
         :a 1 1 20 true true [0 -8] [0 0]

         ])





       ((comp
         (fn [d]
           [:path {:d d
                   :on-click (fn [e]
                               (js/console.log "helo"
                                               (->
                                                ref
                                                .-current
                                                (.getTotalLength))))
                   :ref ref
                   :stroke (c [20 50 50])
                   :stroke-width 2
                   :stroke-dasharray 550
                   :stroke-dashoffset 900
                   :stroke-opacity 1
                   :fill-opacity 0.3
                   :fill (c [300 50 80])}])
         (path22 [s tx ty]))
        [[0 0] [0 0] :a 1 1.4 24 true true
         [10 0] [0 0]
         ])





       (let [
            [txt-fn circle-fn]
            [(fn [x y s]
               [:text {:x x
                       :y  y
                       :font-size (size [1.5 :rem])} s])
             (fn [x y r] [:circle
                          {:cx x
                           :cy  y
                           :r  r
                           :fill (c [70 70 70])}
                          ])]
             #_ (gen/sample (s/gen :math2/svg-circle))
             data2
             (for [i [(map
                       (fn [x]
                         [[(+ .2 (* 2  x)) 0 ]
                          [(- start 2) 3]
                          (str x)])
                       (range 1 (+ 1 n1)))

                      (map (fn [y]
                             [[(+ 0 0) 0 ]
                              [(- start (* 2 y) 1) 0]
                              (str y)])
                           (range 1 (+ 1 m1)))
                      ]
                   j (range (count i))
                   :let [k (nth i j)]
                   ]
               k)

             data (conj data2 [[40 0] [0 0] 120])

             ]
         (map
          (comp
           (fn [[x y f v]]
             (f x y v))
           (partial mapcat identity )
           (juxt
            (comp
             (juxt
              (comp tx s :x)
              (comp ty s :y)) :point)
            (comp
             (juxt (comp
                    (fn [n]
                      (condp = n
                        :text txt-fn
                        :r circle-fn))
                    first) (comp
                            second))
             :attr))
           (fn [n]
             (s/conform :math2/svg-circle n)))
          data))
       (comment
         )
       (
        (comp
         (fn [d]
           [:path {:d d
                   :stroke (c [20 50 50])
                   :stroke-width 1
                   :stroke-opacity 1
                   :fill-opacity 1
                   :fill (c [300 50 80])

                   :transform (tranfrom [
                                         [:translate
                                          [80
                                           ((comp ty s) [0 0])]]

                                         [:rotate 180]
                                         [:scale .4]
                                         [:translate
                                          [-80
                                           (ve ((comp ty s) [0 0]))]]])
                   }]


           )
         (path22 [s tx ty]))
        [[0 0] [0 0] :L [5 0] [-5 0] :L [-12.5 0] [0 0]
         :L [3 0] [5 0] :L [0 0] [0 0]])

       (map
        (comp
         (fn [d]
           [:path {:d d
                   :stroke (c [20 50 50])
                   :stroke-width 1
                   :stroke-opacity 1
                   :fill-opacity 1
                   :fill (c [300 50 80])}])
         (path22 [s tx ty]))
        [[[15 -2] [0 0] :a 1 1 0 true true
          [0 2] [0 0] :a 1 1 0 true true [0 -2] [0 0]
          ]])

       ((comp
         (fn [d]
           [:path {:d d
                   :stroke (c [200 50 50])
                   :stroke-width 1
                   :stroke-opacity 1
                   :fill-opacity 1
                   :fill (c [100 50 80])}])
         (path22 [s tx ty]))
        [[15 8] [0 -8] :l
         [0 0] [0 16]
         [0 -16] [0 0]
         [0 0] [0 -16]
         [0 16] [0 0]
         ])

       ]]]))


(defn circle22 [[s tx ty]]
  (fn [[txt-fn circle-fn]]
    (comp
     (fn [[x y f v]]
       (f x y v))
     (partial mapcat identity )
     (juxt
      (comp
       (juxt
        (comp tx s :x)
        (comp ty s :y)) :point)
      (comp
       (juxt (comp
              (fn [n]
                (condp = n
                  :text txt-fn
                  :r circle-fn))
              first) (comp
                      second))
       :attr))
     (fn [n]
       (s/conform :math2/svg-circle n)))))

(def marker
  (comp
   (fn [d]
     [:path {:d d
             :style {:fill-rule :evenodd
                     :stroke (c [70 70 70])
                     :stroke-width 1
                     :stroke-opacity 1
                     :fill (c [330 70 70])
                     :fill-opacity 1}
             :transform
             (tranfrom [[:scale .4]
                        [:rotate 180]
                        [:translate [12.5 0]]])}])
   (path22 [(fn [[x _]] x)
            (fn [x] x)
            (fn [x] x)])))


(defn grid15 []
  (let [step 30
        [txt-fn circle-fn]
        [(fn [x y s]
           [:text {:x x :y  y
                   :font-size (size [1.5 :rem])} s])
         (fn [x y r] [:circle {:cx x :cy  y :r  r
                               :fill (c [70 70 70])}])]

        box [[-4 -4 24 24]
             [0 0 12 12]]
        view-box (map (partial * step)  (nth box 0))
        trans [(ss step 1)
               (partial + (* step 4))
               (partial - (* step 4))]
        cir1 ((circle22 trans)
              [txt-fn circle-fn])
        path1 (comp
               (fn [d] [:path {:d d
                               :fill-rule :nonzero
                               :marker-end (url "i")
                               :stroke (c [20 50 50])
                               :stroke-width 5
                               :fill (c [90 70 70])}])
               (path22 [(ss step 1)
                        (partial + (/ (last view-box) 4))
                        (partial - (/ (last view-box) 4))]))
        trans2 [(ss step 1)
                (partial + 0)
                (partial - (/ (last view-box) 1.3))]
        cir2 ((circle22 trans2)
              [txt-fn circle-fn])

        path2 (comp
               (fn [d] [:path {:d d
                               :fill-rule :nonzero
                               :marker-end (url "i")
                               :stroke (c [180 20 10])
                               :stroke-width 4
                               :fill :none}])
               (path22 trans2))]

    [:div {:style grid22}
     [:div (g [[2 8] [8 8] [3 .8]]
              {:flex :end :size [1.5 :rem]
               :d (grad2 3)})
      [m '[= F [* k extention]]]
      [m '[= N [* [N m] m]]]
      [m '[= Weight [* Mass 10]]]]

     [:div (g [[2 8] [8 8] [21 1]]
              {:flex :center :size [2 :rem]
               :d (grad2 3)})
      [:svg {:viewBox view-box}
       [:defs
        [:marker {:id "i"
                  :refY 0
                  :refX 0
                  :orient :auto
                  :style {:overflow :visible}}
         (marker [[0 0] [0 0] :L [5 0] [-5 0]
                  :L [-12.5 0] [0 0]
                  :L [3 0] [5 0] :L [0 0] [0 0]])
         ]]





       (map path2
            [[[0 0] [0 0] :l [20 0] [0 0]]
             [[0 0] [0 0] :l [0 0] [20 0]]
             [[0 0] [0 0] :L [10 0] [8 0] :Q [12 3] [10  2] [14 0] [15 0]]
             [[7 3] [0 0] :l [0 0] [6 0]]
             [[0 0] [6 0] :l [7 2] [0 0]]

             ])
       (map cir2
            (map
             (fn [x]
               [[(* 2 x) 0] [0 -4] (str x)])
             (range 0 10)))

       (map cir2
            (map
             (fn [x]
               [[(* 2 x) 0] [0 -4] (str x)])
             (range 0 14)))

       (map cir2
            (map
             (fn [y]
               [[-1 0] [(* 2 y)  0] (str (* 10 y))])
             (range 0 14)))

       (cir2 [[10 0] [-1 -2] "Force"])
       (cir2 [[-2 0] [20 3] "extension (mm)"])
       (cir2 [[2 0] [0 8] 4])
       (cir2 [[4 0] [4 0] 4])
       (cir2 [[6 0] [4 2] 4])
       (cir2 [[8 0] [6 2] 4])
       (cir2 [[10 0] [8 0] 4])
       (cir2 [[12 0] [10 2] 4])
       (cir2 [[14 0] [15 0] 4])

       ]

      ]

     [:div (g [[2 8] [2 8] [3 .5]]
                {:flex :center :size [2 :rem]
                 :d (grad2 3)})

        [:svg {:viewBox view-box}
         [:defs
          [:marker {:id "i"
                    :refY 0
                    :refX 0
                    :orient :auto
                    :style {:overflow :visible}}
           (marker [[0 0] [0 0] :L [5 0] [-5 0]
                    :L [-12.5 0] [0 0]
                    :L [3 0] [5 0] :L [0 0] [0 0]])
           ]]

         (map path1
              (let [str -1.5
                    str-e .5]
                [(mapcat
                  (fn [x] x)
                  [[[0 0] [8 0]]
                   (mapcat (fn [x] x)
                           (repeat 4 [:a 2 1 0 true false [0 0] [str 0]
                                      :q [1 0] [0 2] [0 0] [str-e 0]]))
                   [:a 2 1 0 true false [0 0] [str 0]
                    :l [0 0] [-2 0]]
                   ])
                 (if false
                   [[0 0] [(+ 8 -2 -2.4 str (* 4  (+ str str-e))) 0]
                    :a 40 40 0 true true [0 1] [0 0]]
                   []
                   )
                 [[-6 0] [8 0] :l [15 0] [0 0]]
                 [[-4 0] [(+ 8 -2  str (* 4  (+ str str-e))) 0]
                  :l [4 0] [0 0] ]]))
         (comment
           (cir1 [[-4 0] [-6 0] "50 mm"])
           (cir1 [[3 0] [-10 0] "1 N"])
           (cir1 [[0 0] [-8 0] "58 mm"])
           (cir1 [[8 0] [-12 0] "2 N"])
           (cir1 [[8 0] [-10 0] "70 mm"]))

         ]]

     ]


    ))




(defn grid16 []
  (let [step 30
        [txt-fn circle-fn]
        [(fn [x y s]
           [:text {:x x
                   :y  y
                   :font-size (size [1.5 :rem])} s])
         (fn [x y r] [:circle
                      {:cx x
                       :cy  y
                       :r  r
                       :fill (c [70 70 70])}
                      ])]

        box [[-2 -2 24 24]
             [0 0 12 12]]
        view-box (map (partial * step)  (nth box 0))
        trans [(ss step 1)
               (partial + (* step 4))
               (partial - (* step 4))]
        cir1 ((circle22 trans)
              [txt-fn circle-fn])
        cir2 ((circle22 trans)
              [(fn [x y s]
                 [x y s])
               (fn [x y r]
                 (space [r x y]))])
        cir3 (circle22 trans)
        path3 (path22 trans)

        path1 (comp
               (fn [d] [:path {:d d
                               :id (name :p122222)
                               :stroke (c [20 50 50])
                               :stroke-width 5
                               :fill (c [70 50 50])}])
               (path22 [(ss step 1)
                        (partial + (/ (last view-box) 4))
                        (partial - (/ (last view-box) 4))]))



        path2 (comp
               (fn [d] [:path {:d d
                               :id (name :p122222)
                               :stroke (c [20 50 50])
                               :stroke-width 5
                               :fill :none}
                        #_[:animateTransform
                           {:attributeType :XML
                            :attributeName :transform
                            :type :rotate
                            :from (cir2 [[2 0] [-2 0] 2])
                            :to (cir2 [[2 0] [-2 0] 362])
                            :dur (str 2 (name :s))
                            :begin :click
                            :fill :freeze
                            }]
                        #_[:animateTransform
                         {:attributeType :XML
                          :attributeName :transform
                          :type :skewY
                          :from 0

                          :to 30
                          :dur (str 2 (name :s))
                          :begin :click
                          :fill :freeze
                          }]


                        [:animate
                         {:attributeType :CSS
                          :attributeName :opacity
                          :from 0
                          :to 1
                          :dur (str 2 (name :s))
                          :fill :freeze}]])
               (path22 [(ss step 1)
                        (partial + (/ (last view-box) 4))
                        (partial - (/ (last view-box) 4))]))]


    [:div {:style grid22}

     [:div (g [[2 8] [8 16] [3 1]]
              {:flex :center :size [1.6 :rem]
               :d (grad2 3)})
      [m '[= [+ [* 12 80] 4] 724]]

      ]
     [:div (g [[2 8] [2 8] [3 .5]]
              {:flex :center :size [2 :rem]
               :d (grad2 3)})
      [:svg {:viewBox view-box}
       [:defs
        [:marker {:id "i"
                  :refY 0
                  :refX 0
                  :orient :auto
                  :style {:overflow :visible}}
         (marker [[0 0] [0 0] :L [5 0] [-5 0]
                  :L [-12.5 0] [0 0]
                  :L [3 0] [5 0] :L [0 0] [0 0]])
         ]]


       (path2
        [[0 0] :l [10 0] [0 0] [0 0] [-2 0]
         [-2 0] [0 0] [0 0] [2 0]])

       (comment
         (for [i (range 0 10)
               :let [ii (- 15 (* 1 i))]
               j (range 0 10)
               :let [deg (if (> j 4) 70 120)
                     jj (* 1 (ve j))]]
           ((cir3
             [(fn [x y s]
                [x y s])
              (fn [x y r] [:circle
                           {:cx x
                            :cy  y
                            :r  r
                            :fill (c [deg 70 70])}
                           ])]
             )
            [[ii 0] [jj 0] 15])))








       ]]]))




;;https://css-tricks.com/guide-svg-animations-smil/

(defn template1 []
  [grid16])
