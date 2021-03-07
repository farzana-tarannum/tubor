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
   [moment]))




(s/def
  ::g-num
  (s/and number? (fn [n]
                   (and (< n  20)
                        (> n -20)))))


(s/def
  ::i-num
  (s/and int?
         (fn [n]
                   (and (< n  20)
                        (> n -20)))))

(s/def
  ::d-num
  (s/and number? (fn [n]
                   (and (< n 5)
                        (>  -5)))))

(s/def
  ::di-num
  (s/and int? (fn [n]
                   (and (< n 10)
                        (>  -10)))))

(comment
  (gen/sample
   (s/gen ::g-num 10)))

(comment
  (gen/sample
   (s/gen ::di-num 10)))

(s/def ::point
  (s/cat
   :x number?
   :y number?))

(s/def ::s-num
  (s/tuple ::g-num ::d-num))

(s/def ::si-num
  (s/tuple ::i-num ::di-num))

(s/def ::gpoint
  (s/cat

   :x ::s-num

   :y ::s-num
   ))

(s/def ::ipoint
  (s/cat

   :x ::si-num

   :y ::si-num
   ))




(s/def ::ghpoint
  (s/and
   ::ipoint
   (fn [[x y]]
     true)))



(comment
  (gen/sample (s/gen ::gpoint 10)))

(comment
  (gen/sample (s/gen ::ipoint 10)))

(comment
  (gen/sample (s/gen ::ghpoint 1)))


(defn point [{:keys [x y]}]
  (fn [[xf yf]]
    (str/join " "
              [(xf x) (yf y)])))

(defn point2 [p]
  (fn [f]
    (str/join " "
              (f p))))

(comment ((point
           (s/conform ::point [2 3])) [(fn [x] x) (fn [x] x)]))

(comment (s/def ::text-ts (s/+ (s/tuple number? number?))))

(comment
  (s/conform
   ::text-ts
   [[2 3] [2 3]
    [3 4]
    [2 3]]))


(comment (gen/sample (s/gen
                      (s/and
                       ::point
                       (fn [{:keys [x y]}]
                         (and (> x -1111100) (< x 27000000)
                              (> y -1111100) (< y 27000000)))) 10)))

(comment (map
          (fn [cod]
            (map
             (fn [[x dx]]
               (+ (* x 30)
                  (* dx 6)))
             cod))
          (gen/sample
           (s/gen ::gpoint 10))))


(comment (map
          (fn [cod]
            ((point
              (s/conform ::point
                         (map
                          (fn [[x dx]]
                            (+ (* x 30)
                               (* dx 6)))
                          cod)))
             [(fn [x] x)
              (fn [x] x)])
            )
          (gen/sample
           (s/gen ::gpoint 10))))


(s/def ::circle
  (s/cat
   :point ::point
   :r number?))


(s/def ::gcircle
  (s/cat
   :point ::gpoint
   :r (s/and
       ::s-num
       (fn [[x _]]
         (> x 0)))))

(s/def ::gbubble
  (s/cat
   :point ::gpoint
   :r (s/and
       ::s-num
       (fn [[x dx]]
         (and (> x 0 )
              (< x 1)
              (< dx 3))))
   ))


(comment
  (gen/sample
   (s/gen ::gcircle 10)))

(s/conform ::gcircle [[2 3] [3 4] [1 2]])


(comment

  (let [t (fn [[x dx]]
            (+ (* x 30)
               (* dx 6)))
        f-list
        [t t]]
    (map
     (fn [d1]
       ((fun [{:point p
               :r r}]
             ((point p) f-list))
        (s/conform ::gcircle d1))
       )
     (gen/sample (s/gen ::gcircle 10)))))


(comment

  (let [sam (gen/sample (s/gen ::gbubble 2))
        t (fn [[x dx]]
            (+ (* x 30)
               (* dx 6)))
        txy (juxt
             (comp
              (fn [x]
                (+ 200 x)) t first)
             (comp (fn [y]
                     (+ y 200)) t second)

             (comp t (fn [a] (nth  a 2))))]
    (map (fn [x y]
           {:a x
            :b y})
         sam
         (map
          (fn [d1]
            ((fun [{:point {:x x
                            :y y}
                    :r r}]

                  (txy [x y r]))
             (s/conform ::gcircle d1))
            )

          sam))))

(s/def ::arc
  (s/cat
   :a #{:a :A}
   :r1 number?
   :r2 number?
   :rotation number?
   :size number?
   :direction number?
   :end ::point))

(defn arc [{:keys [a r1 r2 rotation size direction end]}]
  (fn [fx fy]
    (str/join " "
              [a r1 r2 rotation size direction
               ((point end) [fx fy])
               ])))



(s/def ::c-curve
  (s/cat
   :c #{:c :C}

   :points
   (s/+
    (s/cat
     :control-left ::point
     :control-right ::point
     :end-point ::point
     :s-points  (s/cat
                 :s  #{:s :S}
                 :control-right ::point
                 :end-point2 ::point)

     ))
   ))



(s/def ::q-curve
  (s/cat
   :q #{:q :Q}
   :control ::point
   :end-point ::point
   :t (s/*
       (s/alt
        :t #{:t :T}
        :point ::point))))

(defn c-curve [{:keys [c points s]}]
  (fn [fx fy]
    ))

(s/conform ::c-curve
           [:c 2 3 3 4 7 4
            :s 2 3 2 3])

(s/conform ::q-curve [:q 2 3 3 4])




(s/def ::lines
  (s/cat
   :l #{:l :L}
   :points
   (s/+
    ::point)))


(s/def ::glines
  (s/cat
   :l #{:l :L}
   :points
   (s/+
    ::gpoint)))

(s/def ::gtlines
  (s/cat
   :l #{:l}
   :points
   (s/+
    ::gpoint)))



(comment (defn lines [{:keys [l points]}]
           (fn [[fx fy]]
             (str (name l) " "
                  (str/join " "
                            (map
                             (fn [p]
                               ((point p) [fx fy])
                               ) points))))))


(comment (defn lines2 [{:keys [l points]}]
           (fn [f]
             (str (name l) " "
                  (str/join " "
                            (map
                             (fn [point]
                               (f point)) points))))))

(comment (gen/sample
          (s/gen ::glines 10)))

(comment ((lines (s/conform ::lines [:l 2 3 4 5]))
          [(fn [x] x)
           (fn [x] x)]))


(comment
  (str
   (name :l)
   " "
   (str/join  " "
              (map
               (comp
                (fn [p] (str/join " " p))
                (juxt
                 (comp
                  (fn [y] (- 200 y))
                  (fn [[x y]] (+ x y))
                  (juxt (comp (fn [x] (* x 30))  first)
                        (comp (fn [x] (* x 6) ) second))
                  :y)
                 (comp
                  (fn [x] (- 200 x))
                  (fn [[x y]] (+ x y))
                  (juxt (comp (fn [x] (* x 30))  first)
                        (comp (fn [x] (* x 6) ) second))
                  :x)))

               (:points (s/conform ::glines
                                   (second (gen/sample
                                            (s/gen ::glines 4)))))))))


(s/def ::path
  (s/cat
   :m ::point
   :lines ::lines))


(s/def ::gpath
  (s/cat
   :m ::gpoint
   :lines ::glines))

(s/def ::gtpath
  (s/cat
   :m ::gpoint
   :lines ::gtlines))



(s/conform ::path (last  (gen/sample (s/gen ::path  10)) ))

(defn  path [transform]
  (fun [{:m m
         :lines {:l l
                 :points points}}]
   (str/join " "
             ["M"
              (transform m)
              (name l)
              (str/join " " (map transform points))])))




(comment
  (str
   (name :l)
   " "
   (str/join  " "
              (map
               (comp
                (fn [p] (str/join " " p))
                (juxt
                 (comp
                  (fn [y] (- 200 y))
                  (fn [[x y]] (+ x y))
                  (juxt (comp (fn [x] (* x 30))  first)
                        (comp (fn [x] (* x 6) ) second))
                  :y)
                 (comp
                  (fn [x] (+ 200 x))
                  (fn [[x y]] (+ x y))
                  (juxt (comp (fn [x] (* x 30))  first)
                        (comp (fn [x] (* x 6) ) second))
                  :x)))

               (:points (s/conform ::glines
                                   (second (gen/sample
                                            (s/gen ::glines 4)))))))))


(comment
  (comp
   (path (comp
          (fn [p] (str/join " " p))
          (juxt
           (comp
            (fn [y] (- 200 y))
            (fn [[x y]] (+ x y))
            (juxt (comp (fn [x] (* x 30))  first)
                  (comp (fn [x] (* x 6) ) second))
            :y)
           (comp
            (fn [x] (+ 200 x))
            (fn [[x y]] (+ x y))
            (juxt (comp (fn [x] (* x 30))  first)
                  (comp (fn [x] (* x 6) ) second))
            :x))))

   ))


(comment
  (let [t (path (comp
                 (fn [p] (str/join " " p))
                 (juxt
                  (comp
                   (fn [y] (- 200 y))
                   (fn [[x y]] (+ x y))
                   (juxt (comp (fn [x] (* x 30))  first)
                         (comp (fn [x] (* x 6) ) second))
                   :y)
                  (comp
                   (fn [x] (+ 200 x))
                   (fn [[x y]] (+ x y))
                   (juxt (comp (fn [x] (* x 30))  first)
                         (comp (fn [x] (* x 6) ) second))
                   :x))))]
    (map


     (comp
      t
      (fn [l]
        (s/conform ::gpath l)))

     (gen/sample
      (s/gen ::gpath 4)))))

(comment (s/conform ::path [3 4
                            :L 2 3 3 5 5 2
                            :c 4 3 4 5 3 4 :s 5 2 3 4
                            :c 4 5 6 2 2 3
                            :L 2 3 3 5
                            :q 23 23 24 53 :t 23 23
                            :l 3 5 5 6
                            :q 23 23 24 53
                            :A 24 20 25 0 0 200 200
                            ]))




(s/def ::hsl
  (s/cat
   :hue number?
   :saturation number?
   :lighness number?))


(defn hsl [{:keys [hue saturation lighness]}]
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
        :scale #{:rem :px :fr :vh :vw}
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

(comment (mark-area [3 2 3 2]))

(s/valid? ::size [1 :fr ])




(defn c [a]
  (hsl
   (s/conform ::hsl a)))




(defn size [s]
  (str/join " "
   (map (fn [{:keys [size scale nm]}]
          (str/join "" [size (name scale) nm]))
        (s/conform ::size s))))






(size [.1 :rem
       2 :vw
       10 :vh :hello
       ])




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
              :- ::op-minus
              :× ::op-mul
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
   :mo (s/or := ::op-equal
             :p ::op-pow)
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
  (if (= (first mo) :p)
    [:msup
     (if (= (first elem-left) :expr)
       (expr elem-left)
       elem-left)
     (if (= (first elem-right) :expr)
       (expr elem-right)
       elem-right)
     ]
    [:mrow

     (if (= (first elem-left) :expr)
       (expr elem-left)
       elem-left)

     [:mo "="]
     (if (= (first elem-right) :expr)
       (expr elem-right)
       elem-right)]))


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

(expr (s/conform ::element '(:m 2 x)))
(expr
 (s/conform ::element
            '(+ :alpha x (- 5 2 4)
                ((- x ) 1))))
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



(defn test-canvas []
  (let [ref (react/useRef)
        _ (react/useEffect
           (fn []
             (let [canvas (. ref -current)
                   context (. canvas getContext "2d")
                   _ (. context beginPath)
                   _ (. context arc 100 70 50 0
                        (* 2 js/Math.PI))]
               (. context fill))
             ))]
    [:canvas {:ref ref
              :style {:width "100px"
                      :height "100px"}}]))

(comment (react/useEffect
          (fn []
            (let [rf (. ref -current)]
              (ref.current.animate
               (clj->js
                [{:transform
                  "translateY(0)"
                  }
                 {:transform
                  "translateY(450px)"}])
               (clj->js
                {:duration 1000
                 :iterations 2}))
              )
            )))
(comment (.. ref -current -animate ))




(declare logo)

(defn menu-ps []
  (let [[toggle-chat update-toggle-chat]
        (react/useState 0)]
      [:div {:style {:background-color :white
                     :grid-column "1/4"
                     :grid-row "1"
                     :padding ".8rem"
                     :color "#232323"
                     :font-size "1rem"
                     :align-items :center
                     :justify-content :space-between
                     :display :grid
                     :grid-template-columns "2fr 4fr 3fr"}}
       [:div {:style {:display :flex
                      :gap "1rem"}}
        [logo]
        [:div {:style {:display :flex
                       :align-items :center}} "PRANTI"]]
       [:div {:style {:width "40vw"
                      :display :flex
                      :justify-content :space-around}}
        (map
         (fn [i]
           [:div i])
         ["Home" "Pages" "Blog" "Portfolio" "Contacts"])]
       [:div {:style {:justify-content :space-around
                      :display :flex}}


        [:div {:on-click (fn [e]
                           (update-toggle-chat (mod (+ toggle-chat 1) 2)))
               :style {
                       :padding ".7rem"
                       :background :#111
                       :color :white
                       :font-variation-settings
                       "'wdth' 110, 'wght' 800, 'CNTR' 85"}}
         "Chat with Me"]]
       [animate-circle]
       (comment (if (= toggle-chat 0)
                  [:div
                   {:style {
                            :grid-column "2/4"
                            :display :flex
                            :justify-content :center
                            :align-items :center
                            :height "30vh"}}
                   [:input {:type :text
                            :style {:width "50vw"
                                    :height "5vh"}}

                    ]

                   ]))

       ])
  )




(defn menu-overlay [direction]
  [:div
   {:style {
            :font-size "1rem"
            :cursor "pointer"
            :width "100vw"
            :height "30vh"
            :position :absolute
            :background-color :white
            :display "block"
            :animation (str "hello 700ms ease-in-out 0s 1"
                            (if direction "" "reverse"))
            }}
   [:div {:style {:display :flex
                  :justify-content :space-around

                  }
          }
    (map-indexed (fn [i vals]
                   [:div
                    {:key (str "absolute" i )
                     :style { :font-variation-settings  "'wdth' 70, 'wght' 444, 'CNTR' 20"
                             :font-weight "450"}}
                    (map-indexed
                     (fn [i val]
                       [:div {:key (str "mmm" i)}
                        val])
                     vals)
                    ])
                 [
                  ["Elementary alGebra"
                   "Graphs"
                   "Numbers"
                   "Proportions"]
                  ["Units" "State of Matter" "displacement"
                   ]
                  ["History of Computers" ]
                  ["Living Things" "Cell Biology"]
                  ["hazards" "Instruments"]
                  ]
                 )
              ]])

(defn menu []
  (let [[menu-num set-menu-num] (react/useState 10)]
    [:div {:style {:grid-column "1/4"
                   :grid-row "1/2"
                   :margin 0
                   :padding 0
                   :top 0
                   :position :sticky
                   }}
     [:div {:style {
                    :margin 0
                    :display :flex
                    :padding "1rem"
                    :background-color :white
                    :justify-content :space-around
                    :color "#333"
                    :font-variation-settings
                    "'wdth' 100, 'wght' 650, 'CNTR' 40"
                    :font-size ".75rem"}}

      (map-indexed
       (fn [i mnu ]
         [:div {:key (str "mnu-" i)
                :on-mouse-enter (fn [e]
                                  (do
                                    (js/console.log "hello menu world mouse enter")
                                    (set-menu-num i)) )
                :on-mouse-leave
                (fn [e]
                  (do
                    (js/console.log
                     "hello menu world mouse leave")
                    (set-menu-num 9)) )
                :style {:display :flex
                        }}
          [:div mnu]
          [:svg {:viewBox "0 0 20 20"
                 :style {:width "15px" :height "15px"
                         }}
           [:path {:d "M 0,5 L 7,10 12,5"
                   :style {:fill "none"
                           :stroke "#1E1935"
                           :stroke-width "1.2"}}]

           ]])
       [
        "MATH"
        "PHYSICS"
        "CHEMISTRY"
        "CSE"
        ]
       )
      ]
     (if (< menu-num 10)
       [:div {:on-mouse-leave (fn [e]
                                (set-menu-num 10))}
        [menu-overlay (if (= set-menu-num 9) false true)]])
     ]))




(defn problem2 []
  [:div

   (comment [file/file-input-background2])

   [:math {:xmlns "http://www.w3.org/1998/Math/MathML"}
    [:mstyle {:displaystyle "true", :scriptlevel "0"}
     [:mfrac
      [:mrow
       [:mn "3"]
       [:msup
        [:mi [:div "α"] ]
        [:mrow
         [:mn "2"]]]
       [:mo "−" ]
       [:mn "9"]]
      [:mrow
       [:mn [:div {:style {:text-decoration :line-through}} "6"]]
       [:mi "β"]
       [:mo "−" ]
       [:mn "1"]]]
     [:mo "="]
     [:mfrac
      [:mrow
       [:mn "6"]
       [:mi "x"]
       [:mo "−" ]
       [:mn "1000"]]
      [:msqrt
       [:mi "y"]
       [:mo "+"]
       [:mn "1"]]]]]])

(defn logo [style]
  (let [[click update-click] (react/useState 5)
        dash (str "2," click ",5")]
    [:svg
     {:style
      (into
       {}
       style
       )
      :viewBox "0 0 100 100"
      }
     [:path {:on-click (fn [e]
                         (update-click
                          (if (< click 45 )
                            (+ click 5)
                            5)
                          ))
             :style {:fill :white
                     :stroke
                     (hsl
                      (s/conform ::hsl [78 100 50]))

                     :stroke-width ".1rem"}
             :stroke-dasharray dash
             :stroke-linecap :round
             :d "m 44.055121,77.976268 a 24.948597,38.982552 0 0 1 4.22425,-54.547194 24.948597,38.982552 0 0 1 34.937331,6.23568 24.948597,38.982552 0 0 1 -3.757168,54.63056 24.948597,38.982552 0 0 1 -34.987561,-5.505316"}]]))



(defn color-input [time-color update-time-color]
  [:div {:style {}}
   [:input {:type "text"
            :value time-color
            :on-change #(update-time-color
                         (-> % .-target .-value))}]])

(comment time-str (-> timer .toTimeString (str/split " ") first))
(defn clock [time-color]
  (let [[timer update-time] (react/useState (js/Date.))]
    (react/useEffect
     (fn []
       (let [i (js/setInterval
                #(update-time (js/Date.)) 2000)]
         (fn []
           (js/clearInterval i)))))
    [:div.example-clock
     {:style {:background-color time-color
              :color :#111 }}
     (str timer)]))


(defn simple-example []
  (let
      [[time-color update-time-color]
       (react/useState "hsl(70,70%,70%)")]

    [:div {:style {:font-size "1rem"
                   :display :flex
                   :flex-direction :column}}
     [clock time-color]
     [color-input time-color update-time-color]
     ]))




(defn header [n]
  [:div {:style {:background-color "hsl(60, 70%, 70%)"
                 :font-size "1.5rem"
                 :border-radius "4rem 2rem 1rem 0"
                 :display :flex
                 :padding ".3rem"
                 :box-shadow ".2rem .2rem .4rem .1rem rgba(0,0,0,.3)"
                 :border ".2rem solid hsl(40,100%,90%)"
                 :justify-content :center}}
   (str "Exercise " n)])

(defn style-note [bg]
 {:flex 1
  :font-size "1rem"
  :background-image "linear-gradient(0deg, transparent 95%, hsl(100,20%,70%) 100%)"
  :background-size (str "100% " bg "rem")} )

(defn container [n bg contents]
  [:div
   {:style {:display :flex
            :gap "1rem"
            :flex-direction :column
            :justify-content :space-between}}
   [header n]
   [:div {:style (into (style-note bg) {:font-size "1.4rem"})}
    (map-indexed (fn [i con]
                   [:div {:key (str "math-container-n" n )}
                    con])  contents)
      ]])



(defn square [x op n]
  (let [ops (get {:puls "+"
                  :minus "-"
                  :mul "×"}
                 op)]
    [:math ]))

(defn op [operator a b]
  (let [o {:plus "+"
           :minus "-"
           :mul "×"
           :div "/"
           :eq "="
           :pow ""
           :brac ""}]
    (case operator
      :plus [:mrow a [:mo (:plus o)] b]
      :p [:mrow a [:mo (:plus o)] b]
      :minus [:mrow a [:mo (:minus o)] b]
      :m [:mrow a [:mo (:minus o)] b]
      :ms [:mrow [:mo "-"] [:mi a]]
      :div [:mfrac a b]
      :d [:mfrac a b]
      :mul [:mrow a [:mo (:mul o)] b]
      :ml [:mrow a [:mo (:mul o)] b]
      :eq [:mrow a [:mo (:eq o)] b]
      :pow [:msup a [:mn b]]
      :b [:mrow
          [:mo "(" ]
          (op a (first b) (second b))
          [:mo ")"] ])))



(defn sq-factor [exer x a]
  (let []
   [container exer "2.5"
     [
      [:math (op :mul (- x a) (+ x a))]
      [m '(:m (:b (- x 3)) (:b (+ x 3)))]
      [m '(:m (:b (- 30 3)) (:b (+ 30 3)))]



      [:div {:style {
                     :width "6rem"
                     :overflow :visible}}
     [:svg {:viewBox "0 0 200 50"
            :style {}}
      [:defs
       [:marker {:id "arr"
                 :viewBox "0 0 7 7"
                 :style {:overflow :visible}
                 :refX 5
                 :refY 5
                 :markerHeight 5
                 :markerWidth 5
                 }
        [:path {:fill "black"
                :stroke "black"
                :transform "scale(1.2) rotate(270) "
                :d "M 0.0,0.0 L 5.0,-5.0 L -12.5,0.0 L 5.0,5.0 L 0.0,0.0 z "}]]

       ]

      [:path {:d
              "m 100 30 c 80,-88 79,5 110,-4"
              :style {:fill "none"
                      :marker-end "url(#arr)"
                      :stroke "#000000"
                      :stroke-width "1.28px"
                      }}]
      (comment
        )
      [:path {:d "m 100,50 c 70,-88 129,0 150,-4"
              :style {:fill "none"
                      :marker-end "url(#arr)"
                      :stroke "#000000"
                      :stroke-width "1.28px"
                      }}]
           ]]
      [:math
       [:mstyle {:displaystyle "true", :scriptlevel "0"}
        [:mrow
         [:mrow {:style {:background-color "hsl(10,70%,70%)"
                         :filter "blur(.2rem)"
                         }}
          [:mo "("]
          [:mn "x"]
          [:mo "−" ]
          [:mi a]
          [:mo ")"]]
         [:mrow {:style {:background-color "hsl(40,70%,70%)"}}
          [:mo "("]
          [:mn "x"]
          [:mo "+" ]
          [:mi a]
          [:mo ")"]]]
        ]]

      [:math
       [:mstyle {:displaystyle "true", :scriptlevel "0"}
        [:mrow

         [:mrow {:style {:background-color "hsl(40,70%,70%)"}}
          [:mrow {:style {:background-color "hsl(10,70%,70%)"
                          :filter "blur(.2rem)"
                          }}
           [:mo "("]
           [:mn "x"]
           [:mo "−" ]
           [:mi a]
           [:mo ")"]]
          [:mn "x"]
          [:mo "+" ]
          [:mrow {:style {:background-color "hsl(10,70%,70%)"
                          :filter "blur(.2rem)"
                          }}
           [:mo "("]
           [:mn "x"]
           [:mo "−" ]
           [:mi a]
           [:mo ")"]]
          [:mi a]

          ]]
        ]]
      [:math
       [:mstyle {:displaystyle "true", :scriptlevel "0"}
        [:mrow

         [:mrow {:style {:background-color "hsl(40,70%,70%)"}}
          [:mrow {:style {:background-color "hsl(10,70%,70%)"
                          :filter "blur(.0rem)"
                          }}
           [:mo "("]
           [:mn "x"]
           [:mo "−" ]
           [:mi a]
           [:mo ")"]]
          [:mn "x"]
          [:mo "+" ]
          [:mrow {:style {:background-color "hsl(10,70%,70%)"
                          :filter "blur(.0rem)"
                          }}
           [:mo "("]
           [:mn "x"]
           [:mo "−" ]
           [:mi a]
           [:mo ")"]]
          [:mi a]

          ]]
        ]]
      [:math
       [:mstyle {:displaystyle "true", :scriptlevel "0"}
        [:mrow {:style {:background-color "hsl(20,70%,70%)"
                        :filter "blur(.0rem)"
                        }}

         [:mrow
          [:mrow
           [:msup
            [:mn "x"]
            [:mn 2]]
           [:mo "−" ]
           [:mi a]
           ]
          [:mn "x"]
          [:mo "+" ]
          [:mrow
           [:mi a]
           [:mn "x"]
           [:mo "−" ]
           [:mi a]]
          [:mo "×"]
          [:mi a]

          ]]]]
      [:math
       [:mstyle {:displaystyle "true", :scriptlevel "0"}
        [:mrow
         [:mrow
          [:mrow
           [:msup
            [:mn "x"]
            [:nm 2]]
           ]]
         [:mrow
          [:mrow
           [:mo "-" ]
           [:mi a]]]]]]
      [:math [:mrow [:msup [:mn x]
                     [:mn 2]

                     ]
              [:mo "-"]
              [:mn a]
              [:mo "=" ]
              [:nm "?"]]
       ]
      ]] ))

(defn hello-math []
  [:div {:style {:font-size "30px"
                 :color "white"
                 :background-color "blue"}}
   [:div "hello World"]
   [:div "date 26th october"]
   [:div "good job"]
   ])










(defn ex-2d []
  [:div {:style {:display :flex
                   :flex-direction :column
                   :justify-content :space-between}}
     [:div {:style {:background-color "hsl(30,70%, 70%)"
                    :font-size "1.5rem"
                    :padding ".5rem"
                    :display :flex
                    :justify-content :center}} "Exercise 2D"]
     [:div {:style {}}
      "Find out "]

     [:div {:style {:background-color "#ddd"
                    :display :flex
                    :gap "1rem"}}
      [:div {:style {:display :inline-block
                     :height "1.5rem"
                     :width ".1rem"
                     :background-color "hsl(90,80%,40%)"}} ""]
      [:math [:mrow [:mfrac [:mo -1]
                     [:mo 2]]
              [:mo "-"]
              [:mfrac [:mo -3]
               [:mo 4]]]]]
     [:div {:style {:background-color "#fff"}}
      [:math
       [:mrow
        [:mfrac {:style {:background-color "hsl(90,80%,80%)"}}[:mrow [:mo -1] [:mo "*"] [:mo 2]]
                     [:mrow [:mo 2] [:mo "*"] [:mo 2]]]
              [:mo "-"]
              [:mfrac [:mo -3]
               [:mo 4]]]]]
     [:div {:style {:background-color "#fff"}}
      [:math [:mrow [:mfrac [:mo -2]
                     [:mo 4]]
              [:mo "-"]
              [:mfrac [:mo -3]
               [:mo 4]]]]]
     [:div {:style {:background-color "hsl(90,80%,80%)"}}
      [:math [:mrow [:mfrac [:mrow [:mo -2] [:mo "-"]
                             "(-3)"]
                     [:mo 4]]
              ]]]
     [:div {:style {:background-color "pink"}}
      [:math [:mrow [:mfrac [:mrow [:mo -2] [:mo "+"]
                             [:mo 3]]
                     [:mo 4]]
              ]]]
     [:div {:style {:background-color "pink"}}
      [:math [:mrow [:mfrac
                     [:mo 1]
                     [:mo 4]]
              ]]]
   ])
(defn prime []
  [:div {:style {:display :flex
                   :flex-direction :column
                   :justify-content :space-between}}
   [:div "prime factors"
    (comment
      [:div {:style {:font-size "2rem"}} "18"])

    [:div [:math
           [:mrow
            [:mn 50]

            [:mo "="]
            [:mn "2"]
            [:mo "×"]
            [:mn 25]]]]
    [:div [:math
           [:mrow
            [:mn 100]

            [:mo "="]
            [:mn "4"]
            [:mo "×"]
            [:mn 25]]]]
    [:div [:math
           [:mrow
            [:mn 16]
            [:mo "="]
            [:mn "2"]
            [:mo "×"]

            [:mn 2]
            [:mo "×"]
            [:mn 2 ]
            [:mo "×"]
            [:mn 2]]]]

    [:div [:math
           [:mrow
            [:mn 18]
            [:mo "="]
            [:mn "2"]
            [:mo "×"]
            [:mn 3]
            [:mo "×"]
            [:mn 3 ]]]]

    [:div [:math
           [:mrow
            [:mn 116]

            [:mo "="]
            [:mn "4"]
            [:mo "×"]

            [:mn 25]
            [:mo "+"]
            [:mn "4"]
            [:mo "×"]
            [:mo "4"]
            ]]]
    [:div [:math
           [:mrow
            [:mn 116]

            [:mo "="]

            [:mn "4"]
            [:mo "("]

            [:mn 25]
            [:mo "+"]
            [:mn "4"]
            [:mo ")"]
            ]]]
    [:div [:math
           [:mrow
            [:mn 116]

            [:mo "="]
            [:mn "4"]
            [:mo "("]

            [:mn 25]
            [:mo "+"]
            [:mn "4"]
            [:mo ")"]

            ]]]
    (comment
      [:div [:math
             [:mrow
              [:mn 18]

              [:mo "="]
              [:mn "2"]
              [:mo "×"]
              [:mn "3"]
              [:mo "×"]
              [:mn 3]
              ]]
       [:div {:style {:font-size "2rem"}} "30"]
       [:div [:math
              [:mrow
               [:mn 30]

               [:mo "="]
               [:mn "2"]
               [:mo "×"]
               [:mn "5"]
               [:mo "×"]
               [:mn 3]
               ]]
        [:div {:style {:font-size "2rem"}} "36"]
        [:div [:math
               [:mrow
                [:mn 36]

                [:mo "="]
                [:mn "2"]
                [:mo "×"]
                [:mo "18"]


                ]]]]])]])

(defn divisible []
[:div {:style {:display :flex
                   :gap "1rem"
                   :flex-direction :column}}
     [:div
      {:style {:background-color "hsl(60, 70%, 70%)"
                     :font-size "150%"
                     :border-radius "4rem 2rem 1rem 0"
                     :display :flex
                     :padding "1rem"
                     :box-shadow ".2rem .2rem .4rem .1rem rgba(0,0,0,.3)"
                     :border ".2rem solid hsl(40,100%,90%)"
                     :justify-content :center}}
      "divisibility"]
     [:div {:style {:display :grid
                    :flex 1
                    :gap ".2rem"
                    :font-size "1rem"
                    :grid-template-columns "repeat(auto-fit,.6rem)"
                    :grid-template-rows "repeat(auto-fit,1rem)"}}

      [:div {:style {:grid-row 1
                     :grid-column 1}}
       1]
      [:div {:style {:grid-row 1
                     :grid-column 2}}
       2]
      [:div  {:style {:grid-row "1/3"
                      :grid-column "3"
                      :background
                      "radial-gradient(at 0% , #ddd 52%, black 64%,#ddd 75%)"
                     ;; "linear-gradient( 90deg, white 20%, black)"
                      }}
       ""]
      [:div {:style {:grid-row 1
                     :grid-column 4}}
       1]
      [:div {:style {:grid-row 1
                     :grid-column 5}}
       2]
      [:div  {:style {:grid-row 1
                     :grid-column 6}}
       3]
      [:div  {:style {:grid-row "1/3"
                      :grid-column "7"

                      :background
                      "radial-gradient(at 180% , #ddd 54%, black 64%, #ddd 75%)"
                      }}
       ""]
      [:div {:style {:grid-column 9}}
       ""]
      [:div {:style {:grid-column 9
                     :grid-row 1}}
       1]

      [:div {:style {:grid-row 2
                     :grid-column 1
                     }}
       ""]
      [:div {:style {:grid-row 2
                     :grid-column 2
                     }}
       ""]

      [:div {:style {:grid-row 2
                     :grid-column 4
                     }}
       1]

      [:div {:style {:grid-row 2
                     :grid-column 5
                     }}
       2]
      [:div {:style {:grid-row 2
                     :grid-column "3/8"
                     :padding-bottom "1rem"
                     :border-bottom ".1rem solid black"
                     }}
       ""]
      [:div {:style {:grid-row 2
                     }}
       ""]
      [:div {:style {:grid-row 3
                     :grid-column 6}}
       3]

      ]])

(defn arrow2 []
  [:svg {:viewBox "0 0 200 20"
         :overflow :visible
         :style {:height "3rem"}}
   [:path {:d "M 5 24 Q 12 10 18 22"
           :style {:fill :none
                   :stroke :black}}]
   [:path {:d "M 5 24 C 7 10 32 10 43 24"
           :style {:fill :none
                   :stroke :black}}]
   [:circle {:r 1 :cx 5 :cy 24}]
   [:circle {:r 1 :cx 18 :cy 22}]
   ;; [:circle {:r 1 :cx 12 :cy 10}]
   ;; [:circle {:r 1 :cx 32 :cy 10}]
   [:circle {:r 1 :cx 43 :cy 24}]] )


(comment [m '(:m (:b (+ t 3)) (:b (- t 3)))])
(defn ab2 [a b]
  (let [
        orange {:background-color "hsl(40,70%,70%)"}
        pink {:background-color "hsl(10,70%,70%)"}
        aa [:mrow {:style (into orange
                                {:font-size ".7rem"
                                 :filter "blur(.1rem)"})}
            (op :b :p [a b])]
        AA [:mrow {:style orange}
            (op :b :p [a b])]
        abc [:mrow AA
             [:mrow {:style pink}
              (op :b :m [a b])]]
        b+c [:mrow {:style pink}
             (op :b :m [a b])
             ]
        arrow (arrow2 )
        ]

    [container (str a b) "1.5"
     [



      [:div {:style {
                     :background-color "hsl(40,70%,70%)"
                     :display :flex
                     :justify-content :center}}

       [m '(:m
            a
            (:b (- t 3)))]]

      [:div {:style {:background-color "hsl(100,70%,70%)"
                     :display :flex
                     :justify-content :center}}

       [m '(:m
            a
            (:b (+ t (:b (- 3)))))]]

      [:div {:style {:background-color "hsl(70,70%,70%)"
                     :display :flex
                     :justify-content :center}}

       [m  '(+ (:m a t ) (:m (:b (- 3)) a)  ) ]]

      [m '(= a 3)]

      [:div {:style {:background-color "hsl(130,70%,70%)"
                     :display :flex
                     :justify-content :center}}

       [m  '(+ (:m 3 t ) (:m (:b (- 3)) 3)  ) ]]

      [m '(= a x)]
      [:div {:style {:background-color "hsl(130,70%,70%)"
                     :display :flex
                     :justify-content :center}}

       [m  '(+ (:m x t ) (:m (:b (- 3)) x)  ) ]]

      (comment
        [:math  (op :p [:mrow aa a] (op :ml aa (op :ms b b)))]  )


      (comment
        [:math  (op :p [:mrow AA a] (op :ml AA (op :ms b b)))])

      (comment
        [:math (op :p [:mrow
                       (op :p
                           [:mrow {:style {:background-color :pink}}
                            (op :p (op :ml a a) (op :ml a b))]
                           (op :b  :ml [a (op :ms b b)])
                           )]
                   [:mrow {}
                    (op :b :ml [b
                                (op :ms b b)])] ) ])
      (comment


        [:math (op :m (op :m (op :p (op :pow [:mi a] 2)
                                 [:mrow [:mn b] [:mi a] ])
                          [:mrow b [:mi a]])
                   (op :pow [:mi b] 2))])
      (comment

        [:math (op :m (op :pow [:mi a] 2)
                   (op :pow [:mi b] 2))])
      (comment

        [:div {:style {:font-size "1.8rem"
                       :padding "1rem"
                       :display :flex
                       :justify-content :center
                       :background "hsl(60,80%,83%)"}}
         ])]]))


(defn ab2a [a b n]
  (let [p [:mo "+"]
        eq [:mo "="]
        ml [:mo "×"]
        m [:mo "-"]
        br1 [:mo "("]
        br2 [:mo ")"]
        b' [:mi b]
        a' [:mi a]

        AA [:mrow
            br1 a' p b' br2]
        na [:mrow
            br1 [:mn n] p [:mn b] br2]
        nb [:mrow
            br1 [:mn n] m [:mn b] br2]
        abc [:mrow AA
             [:mrow
              br1 a' m b' br2]]
        b+c [:mrow
             br1 a' m b' br2]

        ]

    [container  (str (+ n b) "×" (- n b) )  "1.5"
     [

      [:div {:style {:font-size "1.8rem"
                     :padding "1rem"
                     :display :flex
                     :justify-content :center
                     :background "hsl(60,80%,83%)"}}
       [:math
        [:mrow AA b+c]
        eq
        [:mrow [:msup a' [:mn 2]] m [:msup b' [:mn 2]]]]]
      [:div {:style {:display :flex
                     :flex-direction :column
                     :align-items :center
                     }}
       [:div [:math [:mn (+ n b) ml [:mn (- n b)]]]]
       [:math na nb]
       [:div [:math na nb eq
              [:mrow [:msup [:mn n] [:mn 2]] m [:msup b' [:mn 2]] eq ] ]]
       [:math [:mn (* n n)] m [:mn (* b b)] eq [:mn (- (* n n) (* b b))]]]]]))

(defn draw-1 []
  [:svg {:viewBox "0 0 300 300"
         :style {:background "hsl(40,50%,70%)"}}
   [:path {:d "M10 10 q 0 90 90 90 t 90 90"
           :style {:fill :none
                   :stroke-width "3px"
                   :stroke :black
                   }}]
   [:path {:d "M10 10 l90 0 l0 90 l -90 0 z"
           :style {:fill :none
                   :stroke-width "3px"
                   :stroke :black
                   }}]
   [:path {:d "M100 10 l90 0 l0 90 l -90 0 z"
           :style {:fill :none
                   :stroke-width "3px"
                   :stroke :black
                   }}]
   [:path {:d "M10 100 h 90 v 90 h -90z"
           :style {:fill :none
                   :stroke-width "3px"
                   :stroke :black
                   }}]
   [:path {:d "M100 100 h 90 v 90 h -90z"
           :style {:fill :none
                   :stroke-width "3px"
                   :stroke :black
                   }}]

   ])

(defn draw-2 []
  [:svg {:viewBox "0 0 300 600"}
   [:path {:d "M 10 100 c 0 -90 90 -90 90 0
s -90 90 -90 0"
           :style {:fill :none
                   :stroke :black
                   :stroke-width "3px"}}]
   [:circle {:r 3 :cx 10 :cy 10
             :style {:fill "hsl(90,90%,40%)"}}]
   [:circle {:r 3 :cx 100 :cy 10
             :style {:fill "hsl(90,90%,40%)"}}]
   [:circle {:r 3 :cx 100 :cy 10
             :style {:fill "hsl(90,90%,40%)"}}]
   [:circle {:r 3 :cx 100 :cy 180
             :style {:fill "hsl(90,90%,40%)"}}]])

(defn dis-law []
  [:div
     [:div {:style {:font-size "1.5rem"
                    :padding "1rem"
                    :background "hsl(60,80%,83%)"}} "Distributive Law"]
   [:svg {:viewBox "0 0 200 20"
          :overflow :visible
          :style {:height "3rem"}}
    [:path {:d "M 5 24 Q 12 10 18 22"
            :style {:fill :none
                    :stroke :black}}]
    [:path {:d "M 5 24 C 7 10 32 10 43 24"
            :style {:fill :none
                    :stroke :black}}]
    [:circle {:r 1 :cx 5 :cy 24}]
    [:circle {:r 1 :cx 18 :cy 22}]
    ;; [:circle {:r 1 :cx 12 :cy 10}]
    ;; [:circle {:r 1 :cx 32 :cy 10}]
    [:circle {:r 1 :cx 43 :cy 24}]]



   [:math {:style {:font-size "2rem"}}
      [:mrow
       [:mrow
        [:mi "a"] [:mo "("] [:mi "b"] [:mo "+"] [:mi "c"] [:mo ")"]]
       [:mrow [:mo "="][:mi "ab"] [:mo "+"] [:mi "ac"]]]
      ]
     [:div {:style {}}
      "This is known as Distributive Law, where 'a groups of b and c' is the same as 'a groups of b' and 'a groups of c', i.e 'a times of b' and 'a times of c'."]])

(comment
  (let [p [:mo "+"]
        m [:mo "-"]
        ml [:mo "×"]
        br1 [:mo "("]
        br2 [:mo ")"]
        orange {:background-color "hsl(40,70%,70%)"}
        pink {:background-color "hsl(10,70%,70%,40%)"}
        eq [:mo "="]
        abc [:mrow a
             [:mrow {:style pink} br1 b p c br2]]
        b+c [:mrow {:style pink} br1 b p c br2]
        arrow
        [:svg {:viewBox "0 0 200 20"
               :overflow :visible
               :style {:height "3rem"
                       :z-index 2}}
         [:path {:d "M 5 24 Q 12 10 18 22"
                 :style {:fill :none
                         :stroke :black}}]
         [:path {:d "M 5 24 C 7 10 32 10 43 24"
                 :style {:fill :none
                         :stroke :black}}]
         [:circle {:r 1 :cx 5 :cy 24}]
         [:circle {:r 1 :cx 18 :cy 22}]
         ;; [:circle {:r 1 :cx 12 :cy 10}]
         ;; [:circle {:r 1 :cx 32 :cy 10}]
         [:circle {:r 1 :cx 43 :cy 24}]]]
    [:div
     [:div {:style {:font-size "1.5rem"
                    :padding "1rem"
                    :background "hsl(60,80%,83%)"}} "Distributive Law"]

     arrow
     [:math
      {:style {:font-size "2rem"
               :z-index 1}} a b+c eq a ml b p a ml c]
     ]))
(defn dis-law2 [a b c]
  [:div {:style {:background-color "hsl(70%,70%,70%)"
                 :font-size "2rem"}}
   [:div [m '(= (* 25 12) (* (:b (* 25 4)) 3))]]
   [:div [m '(= (* 25 12) (* 100 3))]]
   [m '(= (:m 5 (:b (+ 10 5))) (* 5 15))]
   [m '(= (+ (* 5 10) (* 5 5))
          (* 5 15))]])

(defn exer-4a []
  [:div
     [:div {:style {:background-color "hsl(60, 70%, 70%)"
                     :font-size "150%"
                     :border-radius "4rem 2rem 1rem 0"
                     :display :flex
                     :padding "1rem"
                     :box-shadow ".2rem .2rem .4rem .1rem rgba(0,0,0,.3)"
                     :border ".2rem solid hsl(40,100%,90%)"
                     :justify-content :center}}
      "Exercise 4A 4.b"]
     [:div {:style {:padding "1rem"}}
      [:math {:style {}} [:mrow [:mi "x" ]
                          [:mo "-"]
                          [:mo "["
                           ]
                          [:mi "y"]
                          [:mo "-"]
                          [:mn 3]
                          [:mo "("]
                          [:mi "2x"]
                          [:mo "-"]
                          [:mi "y"]

                          [:mo ")"]
                          [:mo "]"]
                          ]]
      [:math {:style {}} [:mrow [:mi "x" ]
                          [:mo "-"]

                          [:mi "y"]
                          [:mo "+"]
                          [:mn 3]
                          [:mo "("]
                          [:mi "2x"]
                          [:mo "-"]
                          [:mi "y"]

                          [:mo ")"]
                          [:mo "]"]
                          ]]
      [:math {:style {}} [:mrow [:mi "x" ]
                          [:mo "-"]

                          [:mi "y"]


                          [:mo "+"]
                          [:mi "6x"]
                          [:mo "-"]
                          [:mi "3y"]
                          ]]
      [:math {:style {}} [:mrow [:mi "x" ]
                           [:mo "+"]
                          [:mi "6x"]
                          [:mo "-"]

                          [:mi "y"]


                         [:mo "-"]
                          [:mi "3y"]
                          ]]

      ]

   ])

(defn exer-4a-2 []
  [:div
   [:div {:style {:background-color "hsl(60, 70%, 70%)"
                  :font-size "150%"
                  :border-radius "4rem 2rem 1rem 0"
                  :display :flex
                  :padding "1rem"
                  :box-shadow ".2rem .2rem .4rem .1rem rgba(0,0,0,.3)"
                  :border ".2rem solid hsl(40,100%,90%)"
                  :justify-content :center}}
    "Exercise A"]
   [:div {:style {:padding "1rem"
                  :font-size "1.7rem"}}
    [:math {:style {}}
     [:mrow
      [:mn 45252]
      [:mo "+"]
      [:mn 6323]
      [:mo "+"]
      [:mn 433]
      [:mo "+"]
      [:mn 72]
      ]]
    ]])

(defn exer-4a-3 []
  [:div
   [:div {:style {:background-color "hsl(60, 70%, 70%)"
                  :font-size "150%"
                  :border-radius "4rem 2rem 1rem 0"
                  :display :flex
                  :padding "1rem"
                  :box-shadow ".2rem .2rem .4rem .1rem rgba(0,0,0,.3)"
                  :border ".2rem solid hsl(40,100%,90%)"
                  :justify-content :center}}
    "Exercise B"]
   [:div {:style {:padding "1rem"
                  :font-size "1.7rem"}}
    [:math {:style {}}
     [:mrow
      [:mn 98522]
      [:mo "+"]
      [:mn 3432]
      [:mo "+"]
      [:mn 223]
      [:mo "+"]
      [:mn 14]
      ]]
    ]])

(defn exer-4a-4 []
  [:div
   [:div {:style {:background-color "hsl(60, 70%, 70%)"
                  :font-size "150%"
                  :border-radius "4rem 2rem 1rem 0"
                  :display :flex
                  :padding "1rem"
                  :box-shadow ".2rem .2rem .4rem .1rem rgba(0,0,0,.3)"
                  :border ".2rem solid hsl(40,100%,90%)"
                  :justify-content :center}}
    "Exercise D"]
   [:div {:style {:padding "1rem"
                  :font-size "1.7rem"}}
    [:math {:style {}}
     [:mrow
      [:mn 6324]
      [:mo "+"]
      [:mn 1242]
      [:mo "+"]
      [:mn 325]
      [:mo "+"]
      [:mn 742]
      ]]
    ]])

(defn exer-4a-5 []
  [:div
   [:div {:style {:background-color "hsl(60, 70%, 70%)"
                  :font-size "150%"
                  :border-radius "4rem 2rem 1rem 0"
                  :display :flex
                  :padding "1rem"
                  :box-shadow ".2rem .2rem .4rem .1rem rgba(0,0,0,.3)"
                  :border ".2rem solid hsl(40,100%,90%)"
                  :justify-content :center}}
    "Exercise E"]
   [:div {:style {:padding "1rem"
                  :font-size "1.7rem"}}
    [:math {:style {}}
     [:mrow
      [:mn 353]
      [:mo "+"]
      [:mn 323]
      [:mo "+"]
      [:mn 433]
      [:mo "+"]
      [:mn 232]
      ]]
    ]])



(defn exercise-1-1 []
  [container 1 "2"
   [
    [:div {:style {:padding "1rem"}}
     "Mass of the  lorry when it was carring grain was 11,600kg, Mass of the grain 3 times of empty lorry, findout out the mass of the grain."]
    [:div {:style {:background-color "hsl(90,80%,80%)"
                   :padding ".5rem"
                   :display :flex
                   :justify-content :center
                   :font-size "1.5rem"}}
     "Solution"]
    [:div
     {:style {:font-size "1.1rem"
              :padding "1rem"}}
     "let, define mass of the empty lorry, "
     [:math [:mi "x"]]]

    [:div {:style {:font-size "1.1rem"
                   :padding "1rem"}}
     "Mass the gain is 3 time of empty lorry,  "
     [:math [:mn 3] [:mi "x"] ] ]

    [:div {:style {:background-color "#ddd"

                   :font-size "2rem"
                   :display :flex
                   :align-items :center
                   :flex-direction :column}}
     [:div "Expression"]
     [m '(= 11600 (+ x (:m 3 x)))]
     [m '(=  (:m 4 x) 11600 )]
     [m '(=  x
             (11600 4) )]
     [m '(= 11600 (+ x (:m 3 x)))]
     [m '(= 11600 (+ (11600 4)  (:m 3 x)))]
     [m '(= (- 11600 (11600 4) )
            (:m 3 x))]
     [m '(=
          (:m 3 x)
          (- 11600 (11600 4) ))]
     [:div {:style {:background-color "hsl(40,70%,70%)"}}
      [m '(=
           (:m 3 x)
           (- 11600 ((*
                      116
                      100) 4) ))]]
     [m '(=
          (:m 3 x)
          (- 11600 ((* (:b (+ 100 16)) 100) 4) ))]
     [m '(=
          (:m 3 x)
          (- 11600 ((* (:b (+ (* 25 4) (* 4 4))) 100) 4) ))]
     [m '(=
          (:m 3 x)
          (- 11600 ((* (:b (+ 25  4 ))
                       4  100) 4) ))]
     [:div {:style {:background-color "hsl(40,70%,70%)"}}
      [m '(=
           (:m 3 x)
           (- 11600 (* 29 100)))]
      ]

     ]


    ]])


(defn ex-2 []
  [container 2 "1.1"
   [
    [:div "sum of 4 consecutive odd number 56. find the greatest of all"]
    [:div [:div {:style {:background-color "hsl(60,80%,80%)"
                         :font-size "1.3rem"
                         :padding ".9rem .0 .9rem .3rem"}}
           "Solution"]]
    [:div "example of 4 consecutive numbers 89 90 91 92"  ]
    [:div "example of 4 consecutive even numbers 88 90 92 94"  ]
    [:div "example of 4 consecutive even numbers 1 3 5 7"  ]
    [:div "example of 4 consecutive even numbers 11 13 15 17"]
    [:div "example of 4 consecutive even numbers 21 23 25 27"]
    [:div
     [m '(= (+ x
               (:b (+ x 2))(:b (+ x 4)) (:b (+ x 6))) 56)]]
    [:div
     [m '(= (+ (:m 4 x) 12 )
            56)]]
    [:div
     [m '(= (+ (:m 4 x) (* 4 3) )
            (* 14 4))]]
    [:div {:style {:background-color "hsl(42,70%,70%)"}}
     [m '(=  (:m 4 (:b  (+  x  3 )))
             (* 14 4))]]

    [:div
     [m '(=  ((:m 4 (:b  (+  x  3 ))) 4)
             ((* 14 4) 4))]]]])



(defn test22 []
  [container 5 "1.1"
   [
    [:div "if 6 kg of biscuits cost $27,
calculate the cost of 13 kg of biscuits"]
    [:div "lets, cost of 13 kg of biscuits $x"]

    [:math
     [:mfrac
      [:mn 27]
      [:mn 6]]
     [:mo "="]
     [:mfrac
      [:mn "x"]
      [:mn 13]]
     [:mo "="
      ]
     [:mn "4.5"]]


    ]]
  )

(defn test23 []
  [container 6 "1.1"
   [
    [:div {:style {:padding "1rem"}}
     "if 50 g of sweets cost $2.10,
find the cost of 380 g of sweets."]
    (comment

      )
    [:div {:style {:padding "1rem"
                     :font-size "1.5rem"
                     :display :flex
                     :gap ".5rem"
                     :flex-direction :column
                     :justify-content :center
                     :align-items :center}}
       (m '(= (2.1 50) (x 380) ))
       (m '(= (:m 380 (2.1 50)) (:m 380 (x 380)) ))
       (m '(= x (* 38 (2.1 5)) ))
       (m '(= x (* (:b (- 40 2)) (2.1 5)) ))
       (m '(= x (- (* (2.1 5) 40) (* 2 (2.1 5)))))
       (m '(= x (- (* 2.1
                      8) (* 2 (2.1 5)))))
       (m '(= x (- ((* 21
                       8 ) 10)
                   (* 2 (2.1 5)))))
       (m '(= x (- 16.8
                   ((+ 40 2) (* 5 10)))))
       (m '(= x (- 16.8
                   (- (40 (* 5 10)) (2 (* 5 10)))
                   )))
       (m '(= x (- 16.8
                   0.8 (4 100))))
       [:div
        (m '(= x (- ((* 16 100) 100)
                    (4 100))))]

       [:div
        (m '(= x (- ((* 16 100) 100)
                    (4 100))))]
       [:div
        (m '(= x ((- 1600 4
                     ) 100)))]

       [:div {:style
              {:background-color "hsl(40,70%,70%)"}}
        (m '(= x (1596
                  100)))]
       [:div {:style
              {:background-color "hsl(40,70%,70%)"}}
        (m '(= x 15.96))]


       ]

    ]]
  )


(defn testpy [a]
  [:div
   {:style
    {:height "100vh"}}
   [:div (m '(= nx (+  (:m a x ) (:m b y ) e)))]
   [:div (m '(= nx (+  (:m c x ) (:m d y ) f)))]
   [:div {:style {:display :flex
                  :gap "1rem"}}
    [:div {:style {:display :grid
                   :font-size "2rem"
                   :gap ".2rem"
                   :background-color "hsl(52,70%,78%)"
                   :grid-template-columns "1fr"
                   :grid-template-rows "1fr 1fr 1fr"}}
     [:div  "xn"]
     [:div "yn"]
     [:div "1"]]
    [:div  "="]
    [:div {:style {:font-size "2rem"
                   :background-color "hsl(140,70%,70%)"
                   :display :grid
                   :height "20vh"
                   :width "15vw"
                   :gap ".2rem"
                   :grid-template-columns "1fr 1fr 1fr"} }
     (map (fn [i]
            [:div {:style {:background-color "hsl(70,70%,70%)"}}
             i])
          (apply concat [["a" "b" "e"]
                         ["c" "d" "f"]
                         [0 0 1]]))
     ]
    [:div {:style {:font-size "2rem"
                   :background-color "hsl(140,70%,70%)"
                   :display :grid
                   :height "20vh"
                   :width "15vw"
                   :gap ".2rem"
                   :grid-template-columns "1fr 1fr 1fr"} }
     (map (fn [i]
            [:div {:style {:background-color "hsl(70,70%,70%)"}}
             i])
          ;; "matrix(3 1 -1 3 30 40)"
          (apply concat [[3 1 -1]
                         [3 30 40]
                         [0 0 1]]))
     ]

    ]
   [:svg {:viewBox "0 0 400 400"}

    [:rect {:x 10
            :y 10
            :width 10
            :height 20
            :fill :green}]

    [:rect {:x 10
            :y 10
            :width 10
            :height 20
            :transform "matrix(3 1 -1 3 30 40)"
            :fill :green}]
    [:circle {:cx (+ (* 3 10) (* -1 10) 30)
              :cy (+ (* 1 10) (* 3 10) 40)
              :r 2

              :fill "hsl(23,60%,60%)"}]

    [:circle {:cx (+ 50 25)
              :cy 50
              :r 2
              :transform "matrix(1 0 0 0 1 0)"
              :fill "hsl(53,60%,60%)"}]

    [:circle {:cx (+ 37 100)
              :cy 50
              :r 2
              :fill "hsl(123,60%,60%)"}]

    (comment [:circle {:cx (+ 37 220)
                      :cy 182
                      :r 2
                      :fill "hsl(93,60%,60%)"}])

    [:path {:fill "hsl(360,60%,60%)"
            :stroke-width 2
            :stroke "hsl(180,60%,60%)"
            :marker-end "url(#aid)"
            :d "M 37,182 C 75 50 137 50 257 182"}]
    [:path {:d "M 0,0 l 5.0,-5.0 l -12.5,0.0 l 5.0,5.0 l 0.0,0.0 z"
            :transform "translate(100,100) rotate(-90) scale(1.5)"

            :style
            {:fill-rule :evenodd
             :stroke "#dddddd"
             :fill "#aaaaaa"
             :stroke-width ".01rem"
             }}]

    ]])



(defn test24 []
  [container 7 "1.1"
   [
    [:div
     [:math (e '(3 4))]
     " of a piece of metal has a mass of 15 kg. "
     [:div "What is the mass of "
      [:math (e '(2 5)) ]
      " of the piece of metal?"]
     ]
    (comment
      [:div {:style {:background-color
                     "hsl(60,70%,70%)"
                     :display :grid
                     :grid-template-columns "1fr"}}

       [:svg {:viewBox "0 0 400 100"
              :style {:background-color
                      "hsl(70,70%,70%)"
                      :display :flex
                      :grid-column "1/2"
                      :grid-row "1/2"
                      :justify-content :center}
              :width "100%"}
        [:path {:stroke "hsl(30,70%,80%)"
                :stroke-width "2"
                :fill "none"
                :id "l1"
                :d "M10 90 l 80 0 l 0 -80 l -80 0 z"}]
        [:path {:stroke "hsl(30,70%,80%)"
                :stroke-width "2"
                :fill "none"
                :d "M90 90 l 80 0 l 0 -80 l -80 0 z"}]
        [:path {:stroke "hsl(30,70%,80%)"
                :stroke-width "2"
                :fill "none"
                :d "M170 90 l 80 0 l 0 -80 l -80 0 z"}]
        [:path {:stroke "hsl(30,70%,80%)"
                :stroke-width "2"
                :fill "none"
                :d "M250 90 l 80 0 l 0 -80 l -80 0 z"}]]
       [:div {:style {:grid-column "1/2"
                      :grid-row "1/2"
                      :display :grid
                      :grid-template-columns "1fr 1fr 1fr 1fr 1fr"}}
        [:div {:style {:display :flex
                       :align-items :center
                       :justify-content :center}}
         (m '(1 4))]
        [:div {:style {:display :flex
                       :align-items :center
                       :justify-content :center}}
         (m '(1 4))]
        [:div {:style {:display :flex
                       :align-items :center
                       :justify-content :center}}
         (m '(1 4))]
        [:div {:style {:display :flex
                       :align-items :center
                       :justify-content :center}}
         (m '(1 4))]
        ]
       [:div {:style {}}
        [:div "Mass of medal is x kg"]
        [:div
         (m '(= (:m (3 4) x) 15))]
        [:div
         (m '(= (:m 4 (3 4) x) (* 4 15)))]

        [:div
         (m '(= (:m 3 x)
                (* 4 15)))]

        [:div
         (m '(= (:m 3 x)
                (* 4 3 5)))]
        [:div
         (m '(= x
                (* 4  5)))]

        [:div
         (m '(= (:m (2 5)  x )
                ( (* 4  5 2) 5)))]

        ]
       ])



    ]])


(comment

      (comment
        [:div "size of " (m '(4 9)) " of the zucchini is 15cm"]
        [:div "let, size of zucchini is x"]
        [:div {:style {:display :flex
                       :padding "1rem"
                       :background-color "#eee"
                       :justify-content :center}}
         (m '(= (:m (4 9) x) 15)  )]
        [:div {:style {:display :flex
                       :background-color "#eee"
                       :justify-content :center}}
         (m '(= (:m 9 (4 9) x) (* 15 9))  )]))
(comment

  [:div {:style {:display :flex
                 :background-color "#eee"
                 :justify-content :center}}
   (m '(= (:m 4 x) (* 15 9))  )]
  [:div {:style {:display :flex
                 :background-color "#eee"
                 :justify-content :center}}
   (m '(= x
          ( (* 15 9) 4))  )]
  [:div "size of " (m '(3 15)) "of the zucchini is -"]
  [:div {:style {:display :flex
                 :background-color "#eee"
                 :justify-content :center}}
   (m '(= (:m (3 15) x)

          ( (* 15 9 3) (* 4 15)))  )])

(defn exercise-8 []
  [container 8 "1.5"
   [
    [:div
     [:div {:style {:padding "1rem"}} "A zucchini cut into 9 even peaces and
size of 4 peaces is 15 cm. What is the size of the zucchini"]
     [:div "Lets mark it 1 to 9"]
     [:div "cut it into 9 equal peaces"]
     [:div {:style {:display :grid
                    :padding "1rem"

                    :grid-template-columns
                    "repeat(9,auto)"
                    :grid-auto-rows "5vh"}}

      (map-indexed
       (fn [i n]
         [:div {:style {:background-color
                        (str
                         "hsl("
                         (* 9 10)
                         ", 70%,70%)")
                        :border-radius ".5rem"
                        }}
          (str (+ i 1))])
       (range 9))
      ]

       [:div {:style {:display :grid
                 :padding "1rem"
                 :grid-template-columns
                 "repeat(9,auto)"
                 :grid-auto-rows "5vh"}}


        [:div "size of 1st 4 peaces is 15 cm"]
        ]
     [:div {:style {:display :grid
                    :padding "1rem"
                    :grid-template-columns
                    "repeat(9,auto)"
                    :grid-auto-rows "5vh"}}
      [:div {:style {:grid-column "1/4"
                     :justify-items :center
                     :background-color
                     "hsl(180,70%,80%)"}}
       (m '( 4 9))]
      (map-indexed
       (fn [i n]
         [:div {:style {:background-color
                        (str
                         "hsl("
                         (if (< i 4) (* 2 10)  (* i 10))
                         ", 70%,70%)")

                        }}
          (str (+ i 4))])
       (range 5))
      ]

     ]



    ]])

(defn exercise-205 []
  [container 205 "1.5"
   [
    [:div {:style {:padding "2rem"
                   :font-size "1rem"
                   :display :flex
                   :height "100vh"
                   :align-items :center

                   :flex-direction :column
                   :gap "1rem"}}

     [m '(- (+ (:m 3 x) (x y)) (:p y 2))]
     [m '(- (:m 2 (:p x 2))
            (:p y 2))]


     [m '(+ (    (:m 5 x) (:m 3 y) ) x)]


     [m '(+ (    (* 5 6) (:m 3 y) ) 6)]

     [m '(+ (    (* 5 6) (:m 3 (:b (- 4))) ) 6)]

     [m '(+ (    (* 5 6 )
             (* 3 (:b (- 1)) 4) ) 6)]

     [m '(+ (    (* 5 6 (:b (- 1)))
             (* 3 (:b (- 1) ) (:b (- 1) )
                4) ) 6)]

     [m '(+ (    (* 5 6 (:b (- 1)))
             (* 3
                4) ) 6)]





     [:div {:style {:display :flex
                    :gap ".4rem"}}
      [:div {:style {:height "5vh"
                     :width "5vh"
                     :display :flex
                     :justify-content :center
                     :align-items :center
                     :background-color "hsl(70,70%,70%)"
                     :border-radius "100%"}}



       "-1"]
      [:div {:style {:height "5vh"
                     :width "5vh"
                     :display :flex
                     :justify-content :center
                     :align-items :center
                     :background-color "hsl(70,70%,70%)"
                     :border-radius "100%"}}



       "-1"]
      [:div {:style {:height "5vh"
                     :width "5vh"
                     :display :flex
                     :justify-content :center
                     :align-items :center
                     :background-color "hsl(70,70%,70%)"
                     :border-radius "100%"}}



       "-1"]]

     [:div {:style {:display :flex
                    :gap ".4rem"}}
      [:div {:style {:height "5vh"
                     :width "5vh"
                     :display :flex
                     :justify-content :center
                     :align-items :center
                     :background-color "hsl(70,70%,70%)"
                     :border-radius "100%"}}



       "-1"]
      [:div {:style {:height "5vh"
                     :width "5vh"
                     :display :flex
                     :justify-content :center
                     :align-items :center
                     :background-color "hsl(70,70%,70%)"
                     :border-radius "100%"}}



       "-1"]
      [:div {:style {:height "5vh"
                     :width "5vh"
                     :display :flex
                     :justify-content :center
                     :align-items :center
                     :background-color "hsl(70,70%,70%)"
                     :border-radius "100%"}}



       "-1"]]


     [m '(= (* (:b (- 1)) 3) (- 3)) ]


     [:div {:style {:display :flex
                    :width "70vw"
                    :justify-content :space-around}}

      [:div {:style {:display :flex
                     :gap ".4rem"}}


       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(10,70%,70%)"
                      :border-radius "100%"}}



        "-"]

       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]]




      [:div "flip, 1 groups of 3"]

      [:div {:style {:display :flex
                     :gap ".4rem"}}


       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(40,70%,70%)"
                      :border-radius "100%"}}



        "-1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(40,70%,70%)"
                      :border-radius "100%"}}



        "-1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(30,70%,70%)"
                      :border-radius "100%"}}



        "-1"]]]


     [:div [m '(= (* (:b (- 2)) 3) (- 6)) ]]


     [:div {:style {:display :flex
                    :width "70vw"
                    :justify-content :space-around}}


      [:div
       [:div {:style {:display :flex
                     :gap ".4rem"}}


       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(10,70%,70%)"
                      :border-radius "100%"}}



        "-"]

       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]]
       [:div {:style {:display :flex
                     :gap ".4rem"}}


       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(10,70%,70%)"
                      :border-radius "100%"}}



        "-"]

       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]]]



      [:div "flip, 2 groups of 3"]

      [:div
       [:div {:style {:display :flex
                      :gap ".4rem"}}


        [:div {:style {:height "5vh"
                       :width "5vh"
                       :display :flex
                       :justify-content :center
                       :align-items :center
                       :background-color "hsl(40,70%,70%)"
                       :border-radius "100%"}}



         "-1"]
        [:div {:style {:height "5vh"
                       :width "5vh"
                       :display :flex
                       :justify-content :center
                       :align-items :center
                       :background-color "hsl(40,70%,70%)"
                       :border-radius "100%"}}



         "-1"]
        [:div {:style {:height "5vh"
                       :width "5vh"
                       :display :flex
                       :justify-content :center
                       :align-items :center
                       :background-color "hsl(30,70%,70%)"
                       :border-radius "100%"}}



         "-1"]]
       [:div {:style {:display :flex
                      :gap ".4rem"}}


        [:div {:style {:height "5vh"
                       :width "5vh"
                       :display :flex
                       :justify-content :center
                       :align-items :center
                       :background-color "hsl(40,70%,70%)"
                       :border-radius "100%"}}



         "-1"]
        [:div {:style {:height "5vh"
                       :width "5vh"
                       :display :flex
                       :justify-content :center
                       :align-items :center
                       :background-color "hsl(40,70%,70%)"
                       :border-radius "100%"}}



         "-1"]
        [:div {:style {:height "5vh"
                       :width "5vh"
                       :display :flex
                       :justify-content :center
                       :align-items :center
                       :background-color "hsl(30,70%,70%)"
                       :border-radius "100%"}}



         "-1"]]
       ]]

     [:div [m '(= (* (:b (- 1)) (:b (- 3))) 3)]]

     [:div {:style {:display :flex
                    :width "70vw"
                    :justify-content :space-between}}


      [:div {:style {:display :flex
                     :gap ".4rem"}}


       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(10,70%,70%)"
                      :border-radius "100%"}}



        "-"]

       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(40,70%,70%)"
                      :border-radius "100%"}}



        "-1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(40,70%,70%)"
                      :border-radius "100%"}}



        "-1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(30,70%,70%)"
                      :border-radius "100%"}}



        "-1"]]
      [:div  "flip, 1 group of -3"]

      [:div {:style {:display :flex
                     :gap ".4rem"}}




       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]]
      ]
     [m '(= (* (:b (- 2)) (:b (- 3))) 6) ]

     [:div {:style {:display :flex
                    :width "70vw"
                    :justify-content :space-between}}


      [:div
       [:div {:style {:display :flex
                     :gap ".4rem"}}


       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(10,70%,70%)"
                      :border-radius "100%"}}



        "-"]

       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(40,70%,70%)"
                      :border-radius "100%"}}



        "-1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(40,70%,70%)"
                      :border-radius "100%"}}



        "-1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(30,70%,70%)"
                      :border-radius "100%"}}



        "-1"]]
       [:div {:style {:display :flex
                     :gap ".4rem"}}


       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(10,70%,70%)"
                      :border-radius "100%"}}



        "-"]

       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(40,70%,70%)"
                      :border-radius "100%"}}



        "-1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(40,70%,70%)"
                      :border-radius "100%"}}



        "-1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(30,70%,70%)"
                      :border-radius "100%"}}



        "-1"]]]
      [:div  "flip, 2 group of -3"]

      [:div
       [:div {:style {:display :flex
                     :gap ".4rem"}}




       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]
       [:div {:style {:height "5vh"
                      :width "5vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      :background-color "hsl(70,70%,70%)"
                      :border-radius "100%"}}



        "1"]]
       [:div {:style {:display :flex
                      :gap ".4rem"}}




        [:div {:style {:height "5vh"
                       :width "5vh"
                       :display :flex
                       :justify-content :center
                       :align-items :center
                       :background-color "hsl(70,70%,70%)"
                       :border-radius "100%"}}



         "1"]
        [:div {:style {:height "5vh"
                       :width "5vh"
                       :display :flex
                       :justify-content :center
                       :align-items :center
                       :background-color "hsl(70,70%,70%)"
                       :border-radius "100%"}}



         "1"]
        [:div {:style {:height "5vh"
                       :width "5vh"
                       :display :flex
                       :justify-content :center
                       :align-items :center
                       :background-color "hsl(70,70%,70%)"
                       :border-radius "100%"}}



         "1"]]]
      ]
     ]


    ] ])


(defn exercise-215 []
  [container 215 "1.5"
   [[:div {:style {:padding "2rem"
                   :font-size "2rem"
                   :display :flex
                   :height "100vh"
                   :align-items :center

                   :flex-direction :column
                   :gap "1rem"}}

     (m '(= (* (:b (- 4))
               (:b (- 4))
               ) 16))

     (m '(:p (:b (- 4)) 3))

     (m '(* (:b (- 4))
            (:b (- 4))
            (:b (- 4))
            ))





     (m '(= (*
             16
             (:b (- 4))
             ) -64) )


     (m '(= (:p (:b (- x)) 3)
            (:m (:b (- x))
                (:b (- x))
                (:b (- x))
                )))

     (m '(= (:p (:b (- x)) 3)
            (:m
             (:p x 2)
             (:b (- x))
             )))
     (m '(= (:p (:b (- x)) 3)
            (- (:p x 3))))


     [:div "x = 6 , y = -4"]

     [m '(- (:m 2 (:p x 2))
            (:p y 3))]

     (comment
       )
     [m '(- (* 2 (:p 6 2))
            (:p (:b (- 4)) 3))]
     [m '(- 72
            (:b (- 64))
            )]

     [m '(+ 72
            64
            )]













     ]


    ] ])


(defn exercise-216 []
  [container 216 "1.5"
   [[:div {:style {:padding "2rem"
                   :font-size "2rem"
                   :display :flex
                   :height "100vh"
                   :align-items :center

                   :flex-direction :column
                   :gap "1rem"}}




     [m '(+ ((:m 5 x) (:m 3 y)) x)]

     [m '(+ ((* 5 6 (:b (- 1)))
             (:m 3 (:b  (- 4)) (:b  (- 1)))) 6) ]

     [m '(+ ((* 5 6 (:b (- 1)))
             (* 3 4)) 6)]


     [m '(= (+ (:m 3 x) (- (x y) (:p y 2)) ) 17)]

     [m '(+ (:m 3 6) (- (6 (:b (- 4))) (:p (:b (- 4)) 2)) )]
     [m '(+ (:m 3 6) (- ((* 6 (:b (- 1)))
                         (* (:b (- 4)) (:b (- 1)))) (:p (:b (- 4)) 2)) )]

     [m '(+ (:m 3 6) (- ((* 6 (:b (- 1)))
                         24) (:p (:b (- 4)) 2)) )]













     ]


    ] ])



(defn exercise-206 []
  [container 206 "1.5"
   [[:div {:style {:padding "2rem"
                   :font-size "1rem"
                   :display :flex
                   :height "800vh"
                   :align-items :center

                   :flex-direction :column
                   :gap "1rem"}}


     [:div (m '(= (:p  a  (+ x y))
                  (* (:p a x) (:p a y)) ))]

     [:div
      {:contenteditable :true
       :style {:background-color
               (c [250 77 70])
               :font-size "3rem"}}
      "if " (m '(= (:p 2 9) 512))
      ]


     [:div
      {:contenteditable :true
       :style {
               :background-color
               (c [150 77 70])
               :font-size "3rem"}}
      (m '(= (:p 2 11) (:p 2 (:b (+ 9 2)))))


      ]

     [:div
      {:contenteditable :true
       :style {
               :background-color
               (c [50 77 70])
               :font-size "3rem"}}
      (m '(= (:p 2 11) (* (:p 2 9) (:p 2 2))))


      ]

     [:div
      {:contenteditable :true
       :style {
               :background-color
               (c [50 77 70])
               :font-size "3rem"}}
      (m '(= (:p 2 11) (* (:b (+ 500 12))
                          (:p 2 2))))


      ]

     [:div
      {:contenteditable :true
       :style {
               :background-color
               (c [50 77 70])
               :font-size "3rem"}}
      (m '(= (:p 2 11) (* (:b (+ 500 12))
                          (:p 2 2))))


      ]

     [:div
      {:contenteditable :true
       :style {
               :background-color
               (c [50 77 70])
               :font-size "3rem"}}
      (m '(= (:p 2 11) (+ (*  500 4) (* 12 4)) ))


      ]


     [:div {:style {:height "40vh"}}
      [:div [m '(= (:p (:b (:p a x)) y)
                   (:p a (* x y)))]]]

     [:div {:style {:height "40vh"}}
      [:div [m '(= (:p (:b (:p 2 2)) 3)
                   (:p 2 (* 2 3)))]]]



















     (comment [:div {:style {:background-color
                             "hsl(200,70%,70%)"
                             :font-size "3rem"}}
               (m '(=
                    (:p 2 8)
                    (:m (:p 2 3) (:p 2 5))))])

     [:div {:contenteditable :true
            :style {:background-color
                    "hsl(100,70%,70%)"
                    :font-size "3rem"}}
      (m '(=
           (:p (:b (:p 2 9)) (1 3))
           (:p 2 (* 9 (1 3)))
           ))]

     [:div {:contenteditable :true
            :style {:background-color
                    "hsl(140,70%,70%)"
                    :font-size "3rem"}}

      [:math [:mroot [:mn
                      (m '(:p 2 9))]
              [:mi 3]]]

      [:mo "="]

      [:math [:mroot [:mn
                      (m '(:m (:p 2 3) (:p 2 3)
                              (:p 2 3))
                         )   ]
              [:mi 3]]]
      ]

     [:div {:contenteditable :true
            :style {:background-color
                    "hsl(300,70%,70%)"
                    :font-size "3rem"}}
      (m '(=
           (:p (:b (:p 2 8)) (1 3))
           (:m
            (:p
             (:b (:p 2 3)) (1 3))
            (:p (:b (:p 2 3)) (1 3))
            (:p  (:b (:p 2 2)) (1 3)))
           ))]

     [:div {:contenteditable :true
            :style {:background-color
                    "hsl(200,70%,70%)"
                    :font-size "3rem"}}
      (m '(=
           (:p (:b (:p 2 8)) (1 3))
           (*
            (:b (:p 2 (* 3 (1 3))))

            (:b (:p 2 (* 3 (1 3))))

            (:b (:p 2 (* 2 3)))
            )
           ))]

     [:div {:contenteditable :true
            :style {:background-color
                    "hsl(100,70%,70%)"
                    :font-size "3rem"}}
      (m '(=
           (:p (:b (:p 2 8)) (1 3))
           (*
            2

            2

            (:b (:p 2 (2 3)))
            )
           ))]







     [:div {:style {:background-color
                    "hsl(10,70%,70%)"
                    :font-size "3rem"}}
      [:math

       [:mroot [:mn
                (m '(:p 2 8))]
        [:mi 3]]

       [:mo "="]

       [:mi 4 ]

       [:mroot [:mn
                (m '(:p 2 2))]
        [:mi 3]]]



      ]

     [:div {:style {:background-color
                    "hsl(100,70%,70%)"
                    :font-size "3rem"}}
      (m '(= 125
             (:p 5 3)))



      ]

     [:div {:style {:background-color
                    "hsl(100,70%,70%)"
                    :font-size "3rem"}}
      (m '(= (:p 125 (1 3))
             (:p (:b (:p 5 3)) (1 3))))



      ]

     [:div {:style {:background-color
                    "hsl(100,70%,70%)"
                    :font-size "3rem"}}
      (m '(= (:p 5 (3 3)) 5))



      ]

     [:div {:style {:background-color
                    "hsl(300,70%,70%)"
                    :font-size "3rem"}}
      [:math

       [:mroot [:mn
                125]
        [:mi 3]]

       [:mo "="]

       [:mi 5 ]

       ]



      ]






     ]
    ]
   ])

(defn exercise-10 []
  [container 10 "1.5"
   [[:div ""]]
   (comment
     [[:math (expr (s/conform ::element '((+ 1 3 x)
                                          (:m 2 x))))]
      [:math
       [:mrow
        (expr
         (s/conform
          ::element
          '(= (1 (+ (3 2) (:m 2 x) (2 (:m 3 y))
                    (- y (* (:b (- 3 5))

                            (:b (+  3 (:b (- x))))))))
              (+ (x 2) y))))]]
      [:math (expr (s/conform ::element
                              '(= (1 2) 2)))]
      [:math (expr (s/conform ::element
                              '(= (:m (4 6) x)
                                  (4 y))))]
      [m '(+ x 2 (:sq a))]
      [m '(:sq  (+ (2 1) 3))]
      [m '(:p (:b  (+ (2 1) 3)) 2)]
      [:math (expr (s/conform ::element
                              '(= (:m (4 (:p 6 3)) (:p x 4))
                                  (4 (:p y 3)))))]

      ])

   ] )

(defn exercise-11 []
 [container 11 "1.5"
     [[:div {:style {:display :grid}}
       [:div {:style {:display :grid
                   :grid-template-rows "32vh auto"
                   :grid-template-columns "3vw 25vw"}}
       [:div {:style {:display :flex
                      :justify-content :center
                      :align-items :center}}
        "h"]
       [:svg {:viewBox "0 0 100 100"
              :style {:background-color "hsl(60,80%,80%)"}}
        (map-indexed
         (fn [i v]
           [:path {:stroke "hsl(120,80%,80%)"
                   :stroke-width ".2"
                   :d (str  "M" v " 0  V100")}])
         (range 0 100 10))
        (map-indexed
         (fn [i v]
           [:path {:stroke "hsl(120,80%,80%)"
                   :stroke-width ".4"
                   :d (str  "M0 " v " H100")}])
         (range 0 100 10))
        (map-indexed
         (fn [i v]
           [:circle {:cx v
                     :cy (- 100 v)
                     :r 1}])
         (range 10 100 10))

        [:text {:x .1
                :y 99.5
                :style {:font-size ".1rem"}
                } "0,0"]
        (map-indexed
         (fn [i v]
           [:text {:x .1
                   :y v
                   :style {:font-size ".1rem"}
                   } (+ i 1)])
         (range 89.5 0 -10))
        (map-indexed
         (fn [i v]
           [:text {:x v
                   :y 99.5
                   :style {:font-size ".1rem"}
                   } (str (* (+ i 1) 40))])
         (range 10.1 100.1 10 ))


        [:path {:stroke "hsl(250,80%,70%)"
                :stroke-width ".4"
                :d "M0 100 l 10 -10 l 10 -10 l 10 -10 10 -10 l 20 -20"}]]
       [:div ""]
       [:div {:style {:display :flex
                    :justify-content :center
                    :align-items :center}}
        "m"]]]]] )

(defn exercise-1 []
  [container 1 "1.5"
    [
     [:div {:style {:border "2px solid #ddd"
                    :display :flex
                    :padding "5rem"
                    :flex-direction :column
                    :align-items :center
                    :font-size
                    "2rem"}}
      [:div {:style {:background-color
                     "hsl(70,70%,70%)"}}
       (m '(= a (+ x y z)) )]
      [:div {:style {:background-color
                     "hsl(70,20%,70%)"}}
       (m '(= (:m  a b) (:m (:b (+ x y z)) b)) )]
      [:div {:style {:background-color
                     "hsl(70,20%,50%)"}}
       (m '(= (:m  a b) (+ (:m x b) (:m y b)
                           (:m z b)) ) )]

      [:div {:style {:background-color

                     "hsl(140,70%,78%)"}}
       (m '(= x (+ y  (-  (:b (- z))  1))))]
      [:div {:style {:background-color
                     "hsl(140,70%,78%)"}}
       (m '(= x (- y  (:b (+ z
                             1)))))]
      (m '(= (:m 33 x ) (:m
                         33
                         (:b
                          (+ y  (-
                                 (:b (- z)) 1)))
                         )))
      (m '(= (:m 33 x ) (+ (:m 33 y)
                           (-
                            (:m 33(:b (- z))) 33))
             ))
      [:div "z = 3"]
      [:div {:style {:background-color
                     "hsl(77,70%,70%)"}}
       (m '(= (:m 33 x ) (+ (:m 33 y)
                            (-
                             (:m 33(:b (- 3))) 33))
              ))]
      [:div {:style {:background-color
                     "hsl(77,50%,70%)"}}
       (m '(= (:m 33 x ) (- (:m 33 y)
                            (:m 33 (:b  (+ 3 1))))
              ))]

      [:div {:style {:background-color
                     "hsl(77,70%,70%)"}}
       (m '(= (:m 33 x ) (+ (:m 33 y)
                            (-
                             (:m 33(:b (- 3))) 33))
              ))]


      (m '(= (:m 33 x )
             (+ (:m 33 y)
                (- (- 99)
                   33))
             ))
      (m '(= (:m 33 x ) (- (:m 33 y)
                           132)
             ))
      (m '(= (:m 33 x ) (- (* 33 4 100)
                           132)
             ))
      (m '(= (:m 33 x ) (- (* 132 100)
                           132)
             ))
      (m '(= (:m 33 x ) (- 13200
                           132)
             ))
      (m '(= (:m 33 x ) (- 13200
                           100  32)
             ))
      (m '(= (:m 33 x ) (- 13100
                           30 2)
             ))
      (m '(= (:m 33 x ) (- 13070
                           2)
             ))
      (m '(= (:m 33 x ) 13068
             ))
      (m '(=  x
              (13068 33)
             ))
      ]
     [:div {:style
            {:display :grid
             :background-color "hsl(50,70%,70%)"
             :grid-template-columns
             "repeat(3,7vw 2vw) 7vw"
             :grid-template-rows
             "3vh 3vh 3vh 3vh"}}
      [:div {:style {:background-color
                     "hsl(10,70%,70%)"}}
       "396"]
      [:div "="]
      [:div {:style {:background-color
                     "hsl(10,70%,70%)"}}
       "400"]
      [:div "-"]
      [:div {:style {:background-color
                     "hsl(10,70%,70%)"}}
       "3"]
      [:div "-"]
      [:div {:style {:background-color
                     "hsl(10,70%,70%)"}}
       "1"]
      (map-indexed
       (fn [i n]
         (if (= (mod i 2) 1)
           [:div {:style {:border-bottom
                          "1px solid #111"}}
            "" ]
           [:div {:style {:border-bottom
                          "1px solid #111"}}
            33])
         )
       (range 7))

      [:div "x"]
      [:div "="]
      [:div "y"]
      [:div "-"]
      [:div {:style {:background-color
                     "hsl(200,80%,80%)"}} "99"]
      [:div "-"]
      [:div {:style {:background-color
                     "hsl(200,80%,80%)"}} "33"]
      ]
     [:div {:style
            {:display :grid
             :background-color "hsl(100,70%,70%)"
             :grid-template-columns
             "repeat(3,7vw 2vw)"
             :grid-template-rows
             "3vh 3vh 3vh 3vh"}}
      [:div {:style {:background-color
                     "hsl(10,70%,70%)"}}
       "396"]
      [:div "="]
      [:div {:style {:background-color
                     "hsl(10,70%,70%)"}}
       "400"]
      [:div "-"]
      [:div {:style {:background-color
                     "hsl(10,70%,70%)"}}
       "4"]

      (map-indexed
       (fn [i n]
         (if (= (mod i 2) 0)
           [:div {:style {:border-bottom
                          "1px solid #111"}}
            "" ]
           [:div {:style {:border-bottom
                          "1px solid #111"}}
            33])
         ) (range 6))
      [:div ""]
      [:div "x"]
      [:div "="]
      [:div "13200"]
      [:div "-"]
      [:div {:style {:background-color
                     "hsl(200,80%,80%)"}} "132"]

      ]
     ]])

(defn exercise-111 []
  [container 111 "1.5"
    [[:div {:style
            {:padding "1rem"
             :font-size "1rem"}}

      [:div {:style
             {:background-color
              "hsl(32,70%,90%)"
              :font-size "1rem"
              :border-top "1rem"}}
       (m '(= (* 23 5 7) 805))]

      [:div {:style {:background-color
                     "hsl(32,70%,90%)"
                     :font-size
                     "1rem"
                     }}
       (m '(=
            (* 23 35)
            805))]


      [:div {:style
             {:background-color
              "hsl(32,70%,90%)"

              :font-size "1rem"
              :border-top "1rem"}}
       (m
        '(=
          (* 115
             7) 805))]




      [:div {:style
             {:padding "1rem"
              :font-size "1rem"
              :background-color
              "hsl(22,70%,90%)"
              :border-top "1rem"}}
       (m
        '(=
          (*
           161
             5) 805))]





      (comment
        [:div
         (m '(= (* 23 5 7) 805))]
        [:div
         (m '(= (* 115 7) 805))]

        [:div 805]
        [:div (m '(= (161 7) 23))]
        [:div (m '(= (* 5 7 23)
                     805)) ]



        [:div (m '(= (805 5) ( (+ 800 5) 5)))]

        [:div (m '(= (805 5)

                     (+ (800 5) (5 5))
                     ))]

        [:div (m '(= (805 5)

                     (+ 160
                        1)
                     ))]

        [:div (m '(= (805 5)

                     (+ 160
                        1)
                     ))]
        [:div (m '(= (805 5) 161))])


      ]]])

(defn exercise-17 []
  [container 17
   "1.5"
   [
    [:div "hello"]
    [:div {:style
           {:font-size "1rem"

            :display :flex
            :flex-direction :column
            :justify-content :center
            :gap ".3rem"
            }}
     [m '(= (* 2 2 3 3 3 3) (* (:p 2 2) (:p 3 4)))]
     [m '(= (:sq (* 2 2 3 3 3 3))
            (:sq (* (:p 2 2) (:p 3 4))))]

     ]]] )

(defn exercise-51 []
  [container 51
   "1.5"
   [

    [:div {:style
           {:width "40vw"
            :font-size "1rem"
            :height "20vh"
            :display :grid
            :gap ".1rem"

            :grid-template-columns "repeat(10,1fr)"

            :grid-auto-rows "5vh"
            }}
     (map (fn [i]
            [:div
             {:style {:background-color"hsl(45,70%,70%)"}}
             i])
          (range 1 11))

     (map
      (fn [i]
        [:div {:style {:background-color "hsl(70,70%,70%)"}}
         (* i 13)])
      (range 1 11))]

    ]


   ] )

(defn exercise-21 []
  [container 21
   "1.5"
   [
    [:div {:style
           {:font-size "4rem"
            :height "80vh"
            :justify-content :center
            :display :grid
            :gap ".3rem"
            :grid-template-rows "repeat(5,10vw)"
            :grid-template-columns "10vh 10vh"
            }}
     [:div {:style
            {:background-color
             "hsl(70,70%,70%)"}}
      "2"]
     [:div
      {:style
       {:background-color
        "hsl(70,70%,70%)"}}
      "324"]
     [:div {:style
            {:background-color
             "hsl(70,70%,70%)"}} "2"]
     [:div "162"]
     [:div "3"]
     [:div "81"]
     [:div "3"]
     [:div "27"]
     [:div "3"]
     [:div "9"]
     [:div "3"]
     [:div "3"]
     [:div ""]
     [:div "1"]
     ]

    ]


   ] )




(defn puls-grid []
  [:div {:style
         {:padding ".3rem"
          :display :grid
          :grid-template-columns
          "3fr 3fr 3fr 1fr 3fr 1fr 3fr"
          ;; :grid-gap ".1rem"
          ;; :grid-auto-rows "5vh"

          }}
   (map-indexed
    (fn [i n]
      (if (= (mod i 2) 0)
        [:input {:type :text
                 :placeholder n
                 :style {:width "10vh"

                         :background-color
                         "hsl(350,70%,70%)"}}]
        [:div {:contenteditable :true
               :style {:display :flex

                         :justify-content :center
                         :background-color
                         "hsl(300,70%,70%)"}} n]))
    ["2223" "=" "300" "+" "80" "-" "2"])

   (map-indexed
    (fn [i n]
      (if (= (mod i 2) 0)
        [:input {:type :text
                 :style {:display :flex
                         :width "10vh"
                         :justify-content :center
                         :background-color
                         "hsl(300,70%,70%)"}}]
        [:div {:style
               {:display :flex
                :justify-content :center
                :border-bottom "2px solid #111"
                :background-color
                "hsl(270,70%,70%)"}} n]))
    [ "36" "" "36" "" "36" "" "36"])

   (map-indexed
    (fn [i n]
      (if (= (mod i 2) 0)
        [:input {:type :text
                 :style {:width "10vh"
                         :display :flex
                         :justify-content :center
                         :background-color
                         "hsl(300,70%,70%)"}}]
        [:div {:style {:display :flex
                       :justify-content :center
                       :border-bottom "2px solid #111"
                       :background-color
                       "hsl(240,70%,70%)"}} n]))

    [ "?" "=" (* 108 100) "+" (* 80 36)
     "-" "72"])
   ])









(defn exercise-112 []
 [container 112 "1.5"
    [
     [:div {:style
            {
             :border "2px solid #ddd"
             :display :flex
             :padding "1rem"
             :flex-direction
             :column
             :align-items
             :center
             :font-size
             "1.5rem"}}

      (puls-grid )


      ]]] )


(defn exercise-113 []
  [container "112a" "2.5"
   [ [:div {:style {:padding "2rem"}}


      ]
     [:div {:style {:border "2px solid #ddd"
                    :display :flex
                    :padding "1rem"
                    :flex-direction :column
                    :align-items :center
                    :font-size
                    "1.5rem"}}
      [:div {:style
             {:padding "1rem"
             :display :grid
              :grid-template-columns
              "8vw 2vw 8vw 2vw 6vw 2vw 6vw 2vw 6vw"
              :gap ".1rem"
              :grid-auto-rows "5vh"
              ;;:grid-template-rows "1fr 7vh 1fr"
              }}
       (map-indexed
        (fn [i n]

          [:div {:contenteditable :true
                 :style {:display :flex
                         :justify-content :center
                         :background-color "hsl(300,70%,70%)"
                         }} n])
        ["1234" "=" "1000" "+" "200" "+" "30"  "+" "4"])

       (map-indexed
        (fn [i n]

          [:div {:contenteditable :true
                 :style {:display :flex
                         :justify-content :center
                         :border-bottom "2px solid #111"
                         :background-color
                         "hsl(270,70%,70%)"}} n])
        [ "56" "" "56" "" "56" "" "56" "" "56"])

       (map-indexed
        (fn [i n]

          [:div {:contenteditable :true
                 :style {:display :flex
                         :justify-content :center
                         :border-bottom "2px solid #111"
                         :background-color
                         "hsl(240,70%,70%)"}} n])
        [ "69104" "=" "56000" "+" "11200" "+" "1680" "+" "224"])]]]])



(defn exercise-313 []
  [container "313" "2.5"
   [[:div {:style {:height "100vh"
                   :width "100vw"}}


     [:svg {:viewBox "0  0 400 400"
            :style {
                    :background-color (c [180 70 70])}
            }
      [:text {:x 20
              :y 20} "6"]

      [:path {:stroke (c [310 70 70])
              :stroke-width 2
              :fill :none
              :d "M 40 0 q 10 10  0 46  l 40 0 l 0 -46"}]

      [:text {:x 45
              :y 20}
       "84"]

      [:text {:x 45
              :y 40}
       "60"]

      [:text {:x 45
              :y 65}
       "24"]

      [:text {:x 45
              :y 85}
       "24"]




      [:text {:x 85
              :y 20}
       "10 + 4"]





      ]






     ]]])




(defn exercise-163 []
  [container "163" "2.5"
   [[:div {:style {
                   :display :grid
                   :gap ".2rem"
                   :grid-template-columns "1fr 1fr"
                   :justify-items :center
                   :height (str 50 "vh")}}
     [:dev {:style {:background-color (c [70 70 70])}}
      ""]

     [:div {:style {:background-color (c [70 70 70])}}
      "1 day"]

     [:dev {:style {:background-color (c [70 70 70])}}
      "she earns "
      [m '(:b (+ 125 125))] " or "
      [m '(:b (* 2 125))]
      "tk"]



     [:div {:style {:background-color (c [70 70 70])}}
      "2 days"]

     [:dev {:style {:background-color (c [70 70 70])}}
      "she earns "
      [m '(:b (+ 125 125 125))] " or "
      [m '(:b (* 3 125))]
      "tk"]
     [:div {:style {:background-color (c [70 70 70])}} "3 days"]

     [:dev {:style {:background-color (c [70 70 70])}}
      [:div
       "she earns "

       [m '(:b (* 25 125))]
       "tk"]
      [:div {:style {:display :grid
                     :gap ".4rem"
                     :grid-template-columns "repeat(7,1fr)"
                     :background-color (c [10 70 70])}}
       [:div "125"]
       [:div "="]
       [:div "100"]
       [:div "+"]
       [:div "20"]
       [:div "+"]
       [:div "5"]

       [:div "25"]
       [:div ""]
       [:div "25"]
       [:div ""]
       [:div "25"]
       [:div ""]
       [:div "25"]

       [:div "3125"]
       [:div "="]
       [:div "2500"]
       [:div "+"]
       [:div "500"]
       [:div "+"]
       [:div "125"]



       ]

      ]
     [:div {:style {:background-color (c [70 70 70])}} "25 days"]



     ]









    ]])

(defn exercise-114 []
  [container 114 "1.5"
   [


    [:div {:style {:display :grid
                   :background-color :#ddd
                   :gap ".3rem"
                   :grid-template-columns
                   "repeat(10,5vw)"}}
     (map (fn [i]
            (cond
              (= i 1)
              [:div {:style {:background-color
                             "hsl(10,70%,70%)"}}
               i]
              (= i 2)
              [:div {:style {:background-color
                             "hsl(60,70%,70%)"
                             :border-radius "50%"}}
               i]

              (= i 3)
              [:div {:style {:background-color
                             "hsl(60,70%,70%)"
                             :border-radius "50%"}}
               i]

              (= i 5)
              [:div {:style {:background-color
                             "hsl(60,70%,70%)"
                             :border-radius "50%"}}
               i]
              (= i 7)
              [:div {:style {:background-color
                             "hsl(60,70%,70%)"
                             :border-radius "50%"}}
               i]

              (= i 11)
              [:div {:style {:background-color
                             "hsl(60,70%,70%)"
                             :border-radius "50%"}}
               i]
              (= i 13)
              [:div {:style {:background-color
                             "hsl(60,70%,70%)"
                             :border-radius "50%"}}
               i]
              (= i 17)
              [:div {:style {:background-color
                             "hsl(60,70%,70%)"
                             :border-radius "50%"}}
               i]
              (= (mod i 17) 0)
              [:div {:style {:background-color
                             "hsl(10,70%,70%)"}}
               i]
              (= (mod i 13) 0)
              [:div {:style {:background-color
                             "hsl(10,70%,70%)"}}
               i]
              (= (mod i 7) 0)
              [:div {:style {:background-color
                             "hsl(10,70%,70%)"}}
               i]

              (= (mod i 2) 0)
              [:div {:style {:background-color
                             "hsl(10,70%,70%)"}}
               i]

              (= (mod i 3) 0)
              [:div {:style {:background-color
                             "hsl(10,70%,70%)"}}
               i]

              (= (mod i 5) 0)
              [:div {:style {:background-color
                             "hsl(10,70%,70%)"}}
               i]


              (> i 1)
              [:div {:style {:background-color
                             "hsl(70,70%,70%)"}}
               i]


              ))
          (range 1 270))]]])

(defn exercise-155 []
  [container 155 "1.5"
   [
    [:div {:style {:padding "2rem"
                   :display :grid
                   :gap ".2rem"
                   :background-color
                   "hsl(10,70%,70%)"
                   :grid-template-columns "repeat(7,5vw)"}}
     (map (fn [i]
            (cond
              (= i "T")
              [:div {:style {:background "hsl(20,70%,70%)"}}
               i]
              (or (> i -1) (= i "T"))
              [:div {:style {:background :white}}
               i] ))
          [135 49 183 147 93 121 236
           201 261 150 11 131 5 89
           291 117 153 "T" 57 0 61
           192 231 27   1   111 100 149
           17 103 43 7 127 51 53
           83 33 32 105 29 71 37])
     ]
    ]])



(defn exercise-200 []
  (container
   200
   "1.4"
   [
    [:div {:style
           {:padding "4rem"
            :display :grid
            :gap "2rem"
            :grid-template-columns "2fr 8fr"
            }}
     [:div {:style {:display :grid
                    :gap ".5rem"
                    :grid-template-columns "1fr 1fr"}}
      [:div {:style {:background-color
                     "hsl(70,70%,70%)"
                     :display :flex
                     :justify-content :center
                     :align-items :center
                     }}
       "x"]
      [:div {:style {:background-color
                     "hsl(100,70%,70%)"
                     :display :flex
                     :justify-content :center
                     :align-items :center}}
       "y"]
      (map-indexed (fn [i x]
                     [:div
                      {:style {:background-color
                               "hsl(150,70%,70%)"
                               :display :flex
                               :justify-content :center
                               :align-items :center}}
                      x]

                     )
                   (apply concat (map (fn [x y] [x y])
                                      (range 1 11)
                                      [5 53 24
                                       45 52 52
                                       23 42 32
                                       12]))

           )]

               [:div
                [:svg {:viewBox "0 0 400 400"
                       :style {:width "70vh"
                               :background-color "hsl(70,70%,70%)"}}
                 [:path {:stroke :#aaa
                         :stroke-width 1
                         :fill :none
                         :d "M20 10 l 0 360 l 360 0"}]
                 (map (fn [x]
                        [:path {:stroke :#aaa
                                :stroke-width 1
                                :fill :none
                                :d (str "M20 " x " l -10 0" )}
                         ]
                        )
                      (range 10 360 20))

                 (map (fn [x]
                        [:g
                         [:text
                          {:x (+ x 30)
                           :y 380
                           :style {:font-size ".4rem"}}
                          (/ x 2)]
                         [:text
                          {:x 10
                           :y x
                           :style {:font-size ".4rem"}}
                          (- 180 (/ x 2))]
                         (comment
                           [:path {:stroke :#111
                                   :stroke-width 1
                                   :fill :none
                                   :d (str "M" (+ x 22)
                                           " 370 l 0 3" )}
                            ]
                           [:path {:stroke :#111
                                   :stroke-width 1
                                   :fill :none
                                   :d (str "M" (+ x 28)
                                           " 370 l 0 3" )}
                            ]
                           [:path {:stroke :#111
                                   :stroke-width 1
                                   :fill :none
                                   :d (str "M" (+ x 18)
                                           " 370 l 0 3" )}
                            ]
                           [:path {:stroke :#111
                                   :stroke-width 1
                                   :fill :none
                                   :d (str "M" (+ x 14)
                                           " 370 l 0 3" )}
                            ])
                         [:path {:stroke "hsl(30,70%,70%)"
                                 :stroke-width 2
                                 :fill :none
                                 :d (str "M" (+ x 30)
                                         " 370 l 0 10" )}
                          ]
                         ]
                        )
                      (range 10 360 20))

                 [:circle {:cx 20
                           :cy 370
                           :r 2}]
                 [:text {:x 15
                         :y 380
                         :style {:font-size ".4rem"}} "(0,0)"]

                 (comment

                   [:path {:stroke :#aaa
                           :stroke-width 1
                           :fill :none
                           :d (str "M" (+ 20 (+ (* 20 6)
                                                (* 4 5)))
                                   " " 370 " " " l 0 " -100 )}
                    ])



                 [:circle {:cx (+ 20 (+ (* 20 6)
                                        (* 4 5)))
                           :cy (- 370 100)
                           :r 2}]
                 [:circle {:cx (+ 20 (+ (* 20 0)
                                        (* 4 1)))
                           :cy (- 370 (* 20 1))
                           :r 2}]

                 [:circle {:cx (+ 20 (+ (* 20 0)
                                        (* 4 2)))
                           :cy (- 370 (* 20 5) (* 4 3) )
                           :r 2
                           :fill "hsl(290,70%,70%)"}]

                 [:circle {:cx (+ 20 (+ (* 20 2)
                                        (* 4 0)))
                           :cy (- 370 (* 20 2) (* 4 2) )
                           :r 2
                           :fill "hsl(290,70%,70%)"}]

                 [:circle {:cx (+ 20 (+ (* 20 0)
                                        (* 4 3)))
                           :cy (- 370 (* 20 4) (* 4 3))
                           :r 2}]


                 (comment
                   [:path {:stroke "hsl(180,70%,70%)"
                           :stroke-width 2
                           :fill :none
                           :d (str "M"
                                   (+ 20 (+ (* 20 6)
                                            (* 4 5)))
                                   " " (- 370 100) " " " l " -100 " " 0)}
                    ])
                 (comment
                   [:path {:stroke :#aaa
                           :stroke-width 1
                           :fill :none
                           :d (str "M" 20
                                   " " 370 " " " L "
                                   (+ 20 (+ (* 20 6)
                                            (* 4 5)))
                                   " "  (- 370 100) )}
                    ])

                 ]]]]))

(defn exercise-201 []
  (container
   201
   "1.4"
   [
    [:div {:style
           {:padding "4rem"
            :display :grid
            :gap "2rem"
            :grid-template-columns "2fr 8fr"
            }}
     [:div {:style {:display :grid
                    :gap ".5rem"
                    :grid-template-columns "1fr 1fr"}}
      [:div {:style {:background-color
                     "hsl(70,70%,70%)"
                     :display :flex
                     :justify-content :center
                     :align-items :center
                     }}
       "x"]
      [:div {:style {:background-color
                     "hsl(100,70%,70%)"
                     :display :flex
                     :justify-content :center
                     :align-items :center}}
       "y"]
      (map-indexed (fn [i x]
                     [:div
                      {:style {:background-color
                               "hsl(150,70%,70%)"
                               :display :flex
                               :justify-content :center
                               :align-items :center}}
                      x]

                     )
                   (apply concat (map (fn [x y] [x y])
                                      (range 1 11)
                                      [5 53 24
                                       45 52 52
                                       23 42 32
                                       12]))

           )]

               [:div
                [:svg {:viewBox "0 0 400 400"
                       :style {:width "70vh"
                               :background-color "hsl(70,70%,70%)"}}
                 [:path {:stroke :#aaa
                         :stroke-width 1
                         :fill :none
                         :d "M20 10 l 0 360 l 360 0"}]
                 (map (fn [x]
                        [:path {:stroke :#aaa
                                :stroke-width 1
                                :fill :none
                                :d (str "M20 " x " l -10 0" )}
                         ]
                        )
                      (range 10 360 20))

                 (map (fn [x]
                        [:g
                         [:text
                          {:x (+ x 30)
                           :y 380
                           :style {:font-size ".4rem"}}
                          (/ x 2)]
                         [:text
                          {:x 10
                           :y x
                           :style {:font-size ".4rem"}}
                          (- 180 (/ x 2))]
                         (comment
                           [:path {:stroke :#111
                                   :stroke-width 1
                                   :fill :none
                                   :d (str "M" (+ x 22)
                                           " 370 l 0 3" )}
                            ]
                           [:path {:stroke :#111
                                   :stroke-width 1
                                   :fill :none
                                   :d (str "M" (+ x 28)
                                           " 370 l 0 3" )}
                            ]
                           [:path {:stroke :#111
                                   :stroke-width 1
                                   :fill :none
                                   :d (str "M" (+ x 18)
                                           " 370 l 0 3" )}
                            ]
                           [:path {:stroke :#111
                                   :stroke-width 1
                                   :fill :none
                                   :d (str "M" (+ x 14)
                                           " 370 l 0 3" )}
                            ])
                         [:path {:stroke "hsl(30,70%,70%)"
                                 :stroke-width 2
                                 :fill :none
                                 :d (str "M" (+ x 30)
                                         " 370 l 0 10" )}
                          ]
                         ]
                        )
                      (range 10 360 20))

                 [:circle {:cx 20
                           :cy 370
                           :r 2}]
                 [:text {:x 15
                         :y 380
                         :style {:font-size ".4rem"}} "(0,0)"]

                 (comment

                   [:path {:stroke :#aaa
                           :stroke-width 1
                           :fill :none
                           :d (str "M" (+ 20 (+ (* 20 6)
                                                (* 4 5)))
                                   " " 370 " " " l 0 " -100 )}
                    ])


                 [:text {:x (+ (+ 20 (+ (* 20 1)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 1))
                         :style {:font-size ".4rem"}} "(1,5)"]

                 [:circle {:cx (+ 20 (+ (* 20 1)
                                        (* 4 0)))
                           :cy (- 370 (* 20 1))
                           :r 2}]

                 [:text {:x (+ (+ 20 (+ (* 20 2)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 10) (* 4 3))
                         :style {:font-size ".4rem"}} "(2,53)"]

                 [:circle {:cx (+ 20 (+ (* 20 2)
                                        (* 4 0)))
                           :cy (- 370 (* 20 10) (* 4 3))
                           :r 2
                           :fill "hsl(270,70%,70%)"}]
                 [:text {:x (+ (+ 20 (+ (* 20 3)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 4) (* 4 4))
                         :style {:font-size ".4rem"}} "(3,24)"]

                 [:circle {:cx (+ 20 (+ (* 20 3)
                                        (* 4 0)))
                           :cy (- 370 (* 20 4) (* 4 4))
                           :r 2
                           :fill "hsl(170,100%,70%)"}]

                 [:text {:x (+ (+ 20 (+ (* 20 4)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 9) (* 4 0))
                         :style {:font-size ".4rem"}} "(4,45)"]

                 [:circle {:cx (+ 20 (+ (* 20 4)
                                        (* 4 0)))
                           :cy (- 370 (* 20 9) (* 4 0))
                           :r 2
                           :fill "hsl(10,100%,70%)"}]
                 [:text {:x (+ (+ 20 (+ (* 20 5)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 10) (* 4 2))
                         :style {:font-size ".4rem"}} "(5,52)"]

                 [:circle {:cx (+ 20 (+ (* 20 5)
                                        (* 4 0)))
                           :cy (- 370 (* 20 10) (* 4 2))
                           :r 2
                           :fill "hsl(110,100%,70%)"}]

                 [:text {:x (+ (+ 20 (+ (* 20 7)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 4) (* 4 3))
                         :style {:font-size ".4rem"}} "(7,23)"]

                 [:circle {:cx (+ 20 (+ (* 20 7)
                                        (* 4 0)))
                           :cy (- 370 (* 20 4) (* 4 3))
                           :r 2
                           :fill "hsl(110,100%,70%)"}]
                 [:text {:x (+ (+ 20 (+ (* 20 8)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 8) (* 4 2))
                         :style {:font-size ".4rem"}} "(8,42)"]

                 [:circle {:cx (+ 20 (+ (* 20 8)
                                        (* 4 0)))
                           :cy (- 370 (* 20 8) (* 4 2))
                           :r 2
                           :fill "hsl(210,100%,70%)"}]
                 [:text {:x (+ (+ 20 (+ (* 20 8)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 6) (* 4 2))
                         :style {:font-size ".4rem"}} "(9,32)"]
                 [:circle {:cx (+ 20 (+ (* 20 9)
                                        (* 4 0)))
                           :cy (- 370 (* 20 6) (* 4 2) )
                           :r 2
                           :fill "hsl(200,100%,70%)"}]
                 [:text {:x (+ (+ 20 (+ (* 20 10)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 2) (* 4 2))
                         :style {:font-size ".4rem"}} "(10,12)"]
                 [:circle {:cx (+ 20 (+ (* 20 10)
                                        (* 4 0)))
                           :cy (- 370 (* 20 2) (* 4 2) )
                           :r 2
                           :fill "hsl(200,100%,70%)"}]

                 (comment
                   [:circle {:cx (+ 20 (+ (* 20 0)
                                          (* 4 1)))

                             :r 2}])
                 (comment
                   [:circle {:cx (+ 20 (+ (* 20 0)
                                          (* 4 2)))
                             :cy (- 370 (* 20 5) (* 4 3) )
                             :r 2
                             :fill "hsl(290,70%,70%)"}])
                 (comment
                   [:circle {:cx (+ 20 (+ (* 20 2)
                                          (* 4 0)))
                             :cy (- 370 (* 20 2) (* 4 2) )
                             :r 2
                             :fill "hsl(290,70%,70%)"}])
                 (comment
                   [:circle {:cx (+ 20 (+ (* 20 0)
                                          (* 4 3)))
                             :cy (- 370 (* 20 4) (* 4 3))
                             :r 2}])


                 (comment
                   [:path {:stroke "hsl(180,70%,70%)"
                           :stroke-width 2
                           :fill :none
                           :d (str "M"
                                   (+ 20 (+ (* 20 6)
                                            (* 4 5)))
                                   " " (- 370 100) " " " l " -100 " " 0)}
                    ])
                 (comment
                   [:path {:stroke :#aaa
                           :stroke-width 1
                           :fill :none
                           :d (str "M" 20
                                   " " 370 " " " L "
                                   (+ 20 (+ (* 20 6)
                                            (* 4 5)))
                                   " "  (- 370 100) )}
                    ])

                 ]]]]))


(defn exercise-202 []
  (container
   202
   "1.4"
   [
    [:div {:style {:display :flex
                   :justify-content :center}} "Find out Y on 45, 95, 125, 55"]
    [:div {:style
           {:padding "4rem"
            :display :grid
            :gap "2rem"
            :grid-template-columns "2fr 8fr"
            }}
     [:div {:style {:display :grid
                    :gap ".5rem"
                    :grid-template-columns "1fr 1fr"}}
      [:div {:style {:background-color
                     "hsl(70,70%,70%)"
                     :display :flex
                     :justify-content :center
                     :align-items :center
                     }}
       "time"]
      [:div {:style {:background-color
                     "hsl(100,70%,70%)"
                     :display :flex
                     :justify-content :center
                     :align-items :center}}
       "position"]
      (map-indexed (fn [i x]
                     [:div
                      {:style {:background-color
                               "hsl(150,70%,70%)"
                               :display :flex
                               :justify-content :center
                               :align-items :center}}
                      x])

                   (apply concat (map (fn [x y] [x y])
                                      ["10" "25" "x"
                                       "x" "x" "x"
                                       "x" "x" "x"
                                       "x"]
                                      ["30" "53" "y"
                                       "y" "y" "y"
                                       "y" "y" "y"
                                       "y"]
                                      ))

           )]

               [:div
                [:svg {:viewBox "0 0 400 400"
                       :style {:width "70vh"
                               :background-color "hsl(70,70%,70%)"}}
                 [:path {:stroke :#aaa
                         :stroke-width 1
                         :fill :none
                         :d "M20 10 l 0 360 l 360 0"}]
                 (map (fn [x]
                        [:path {:stroke :#aaa
                                :stroke-width 1
                                :fill :none
                                :d (str "M20 " x " l -10 0" )}
                         ]
                        )
                      (range 10 360 20))

                 (map (fn [x]
                        [:g
                         [:text
                          {:x (+ x 30)
                           :y 380
                           :style {:font-size ".4rem"}}
                          (/ x 2)]
                         [:text
                          {:x 10
                           :y x
                           :style {:font-size ".4rem"}}
                          (- 180 (/ x 2))]
                         (comment
                           [:path {:stroke :#111
                                   :stroke-width 1
                                   :fill :none
                                   :d (str "M" (+ x 22)
                                           " 370 l 0 3" )}
                            ]
                           [:path {:stroke :#111
                                   :stroke-width 1
                                   :fill :none
                                   :d (str "M" (+ x 28)
                                           " 370 l 0 3" )}
                            ]
                           [:path {:stroke :#111
                                   :stroke-width 1
                                   :fill :none
                                   :d (str "M" (+ x 18)
                                           " 370 l 0 3" )}
                            ]
                           [:path {:stroke :#111
                                   :stroke-width 1
                                   :fill :none
                                   :d (str "M" (+ x 14)
                                           " 370 l 0 3" )}
                            ])
                         [:path {:stroke "hsl(30,70%,70%)"
                                 :stroke-width 2
                                 :fill :none
                                 :d (str "M" (+ x 30)
                                         " 370 l 0 10" )}
                          ]
                         ]
                        )
                      (range 10 360 20))

                 [:circle {:cx 20
                           :cy 370
                           :r 2}]
                 [:text {:x 15
                         :y 380
                         :style {:font-size ".4rem"}} "(0,0)"]

                 [:path {:stroke "hsl(10,70%,70%)"
                         :stroke-width 1
                         :fill :none
                         :d (str "M"
                                 "50 300"
                                 "l 0 70"
                                 )}
                  ]

                 [:path {:stroke "hsl(10,70%,70%)"
                         :stroke-width 1
                         :fill :none
                         :d (str "M"
                                 "50 300"
                                 "l -30 0"
                                 )}
                  ]
                 (comment
                   [:path {:stroke :black
                           :stroke-width 1
                           :fill :none
                           :d (str "M"
                                   "50 300"
                                   "l 0 -100"
                                   )}
                    ] )
                 (comment
                   [:path {:stroke :black
                           :stroke-width 1
                           :fill :none
                           :d (str "M"
                                   "300 200"
                                   "l 0 -100"
                                   )}
                    ])

                 [:path {:stroke :#aaa
                         :stroke-width 1.5
                         :fill :none
                         :d (str "M"
                                 "50 300"
                                 " "
                                 "c 50 -100  200 -200
250 -170")}
                  ]

                 [:path {:stroke :#aaa
                         :stroke-width 1.5
                         :fill :none
                         :d (str "M"
                                 "50 300"
                                 " "
                                 "c 50 100  200 100
250 -170")}
                  ]


                 [:path {:stroke :#aaa
                         :stroke-width 1.5
                         :fill :none
                         :d (str "M"
                                 "50 100"
                                 " "
                                 "c 50 -50  200 -50
250 200")}]


                 [:path {:stroke :#aaa
                         :stroke-width 1
                         :fill :none
                         :d (str "M" (+ 20 (+ (* 20 6)
                                              (* 4 5)))
                                 " " 370 " " " l 0 " -100 )}
                  ]


                 [:text {:x (+ (+ 20 (+ (* 20 1)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 1))
                         :style {:font-size ".4rem"}} "(1,5)"]

                 [:circle {:cx (+ 20 (+ (* 20 1)
                                        (* 4 0)))
                           :cy (- 370 (* 20 1))
                           :r 2}]

                 [:text {:x (+ (+ 20 (+ (* 20 2)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 10) (* 4 3))
                         :style {:font-size ".4rem"}} "(2,53)"]

                 [:circle {:cx (+ 20 (+ (* 20 2)
                                        (* 4 0)))
                           :cy (- 370 (* 20 10) (* 4 3)  5)
                           :r 2
                           :fill "hsl(270,70%,70%)"}]
                 [:text {:x (+ (+ 20 (+ (* 20 3)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 4) (* 4 4))
                         :style {:font-size ".4rem"}} "(3,24)"]

                 [:circle {:cx (+ 20 (+ (* 20 3)
                                        (* 4 0)))
                           :cy (- 370 (* 20 4) (* 4 9))
                           :r 2
                           :fill "hsl(170,100%,70%)"}]

                 [:path {:stroke :red
                         :stroke-width 1.5
                         :fill :none
                         :d (str "M"
                                 (+ 20 (+ (* 20 3)
                                          (* 4 0)))
                                 " "
                                 (- 370 (* 20 4) (* 4 9))
                                 "l 0 120")}]

                 [:path {:stroke :green
                         :stroke-width 1.5
                         :fill :none
                         :d (str "M"
                                 (+ 20 (+ (* 20 3)
                                          (* 4 0)))
                                 " "
                                 (- 370 (* 20 4) (* 4 9))
                                 "l -58 0")}]

                 [:path {:stroke :red
                         :stroke-width 1.5
                         :fill :none
                         :d (str "M"
                                 (+ 20 (+ (* 20 7)
                                          (* 4 0)))
                                 " "
                                 (- 370 (* 20 15)
                                    (* 4 0))
                                 "l 0 360")}]

                 [:path {:stroke :green
                         :stroke-width 1.5
                         :fill :none
                         :d (str "M"
                                 (+ 20 (+ (* 20 7)
                                          (* 4 0)))
                                 " "
                                 (- 370 (* 20 8) (* 4 8.5))
                                 "l -190 0")}]

                 [:path {:stroke "hsl(10,70%,70%)"
                         :stroke-width 1.5
                         :fill :none
                         :d (str "M"
                                 (+ 20 (+ (* 20 7)
                                          (* 4 0)))
                                 " "
                                 (- 370 (* 20 14) (* 4 2.3))
                                 "l -190 0")}]

                 [:circle {:cx (+ 20 (+ (* 20 7)
                                        (* 4 0)))
                           :cy (- 370 (* 20 14) (* 4 2.3))

                           :r 4
                           :fill "hsl(310,100%,70%)"}]
                 [:text {:x (+ 20 (+ (* 20 7)
                                     (* 4 1)))
                         :y (- 370 (* 20 14) (* 4 2.3))
                         :style {:font-size ".4rem"}} "(65,91)"]

                 [:circle {:cx (+ 20 (+ (* 20 7)
                                        (* 4 0)))
                           :cy (- 370 (* 20 8) (* 4 8.5))

                           :r 4
                           :fill "hsl(310,100%,70%)"}]

                 [:text {:x (+ 20 (+ (* 20 7)
                                     (* 4 1)))
                         :y (- 370 (* 20 8) (* 4 8.5))
                         :style {:font-size ".4rem"}} "(65,91)"]

                 [:circle {:cx (+ 20 (+ (* 20 4)
                                        (* 4 0)))
                           :cy (- 370 (* 20 9) (* 4 0))
                           :r 2
                           :fill "hsl(10,100%,70%)"}]
                 [:text {:x (+ (+ 20 (+ (* 20 5)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 10) (* 4 2))
                         :style {:font-size ".4rem"}} "(5,52)"]

                 [:circle {:cx (+ 20 (+ (* 20 5)
                                        (* 4 0)))
                           :cy (- 370 (* 20 10) (* 4 2))
                           :r 2
                           :fill "hsl(110,100%,70%)"}]

                 [:text {:x (+ (+ 20 (+ (* 20 7)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 4) (* 4 3))
                         :style {:font-size ".4rem"}} "(7,23)"]

                 [:circle {:cx
                           (+ 20 (+ (* 20 7)
                                    (* 4 0)))
                           :cy (- 370 (* 20 4) (* 4 3))
                           :r 2
                           :fill "hsl(110,100%,70%)"}]
                 [:text {:x (+ (+ 20 (+ (* 20 8)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 8) (* 4 2))
                         :style {:font-size ".4rem"}} "(8,42)"]

                 [:circle {:cx (+ 20 (+ (* 20 8)
                                        (* 4 0)))
                           :cy (- 370 (* 20 8) (* 4 2))
                           :r 2
                           :fill "hsl(210,100%,70%)"}]
                 [:text {:x (+ (+ 20 (+ (* 20 8)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 6) (* 4 2))
                         :style {:font-size ".4rem"}} "(9,32)"]
                 [:circle {:cx (+ 20 (+ (* 20 9)
                                        (* 4 0)))
                           :cy (- 370 (* 20 6) (* 4 2) )
                           :r 2
                           :fill "hsl(200,100%,70%)"}]
                 [:text {:x (+ (+ 20 (+ (* 20 10)
                                        (* 4 0))) 2)
                         :y (- 370 (* 20 2) (* 4 2))
                         :style {:font-size ".4rem"}} "(10,12)"]
                 [:circle {:cx (+ 20 (+ (* 20 10)
                                        (* 4 0)))
                           :cy (- 370 (* 20 2) (* 4 2) )
                           :r 2
                           :fill "hsl(200,100%,70%)"}]

                 (comment
                   [:circle {:cx (+ 20 (+ (* 20 0)
                                          (* 4 1)))

                             :r 2}])
                 (comment
                   [:circle {:cx (+ 20 (+ (* 20 0)
                                          (* 4 2)))
                             :cy (- 370 (* 20 5) (* 4 3) )
                             :r 2
                             :fill "hsl(290,70%,70%)"}])
                 (comment
                   [:circle {:cx (+ 20 (+ (* 20 2)
                                          (* 4 0)))
                             :cy (- 370 (* 20 2) (* 4 2) )
                             :r 2
                             :fill "hsl(290,70%,70%)"}])
                 (comment
                   [:circle {:cx (+ 20 (+ (* 20 0)
                                          (* 4 3)))
                             :cy (- 370 (* 20 4) (* 4 3))
                             :r 2}])


                 (comment
                   [:path {:stroke "hsl(180,70%,70%)"
                           :stroke-width 2
                           :fill :none
                           :d (str "M"
                                   (+ 20 (+ (* 20 6)
                                            (* 4 5)))
                                   " " (- 370 100) " " " l " -100 " " 0)}
                    ])
                 (comment
                   [:path {:stroke :#aaa
                           :stroke-width 1
                           :fill :none
                           :d (str "M" 20
                                   " " 370 " " " L "
                                   (+ 20 (+ (* 20 6)
                                            (* 4 5)))
                                   " "  (- 370 100) )}
                    ])

                 ]]]]))




(defn exercise-209 []
  (container
   230
   "1.4"
   [
    [:div {:style
           {:padding "4rem"
            :display :grid
            :gap "2rem"
            :grid-template-columns "2fr 8fr"}}


     [:div
      (let [
            s (fn [x dx]
                (+ 20 (+ (* 20 x)
                         (* 4 dx))))
            sy (fn [y dy]
                 (- 370 (* 20 y) (* 4 dy)))

            v (fn [[x dx] [y dy]]
                [:path {:stroke :green
                        :stroke-width 1.5
                        :fill :none
                        :d (str "M"
                                (s x dx)
                                " "
                                (sy y dy)
                                "l " (str (* (- (s x dx) 20) -1))   " 0")}])

            h (fn [[x dx] [y dy]]
                [:path {:stroke :red
                        :stroke-width 1.5
                        :fill :none
                        :d (str "M" (s x dx)

                                " " (sy y dy)
                                "l 0 " (+ (* 20 y) (* 4 dy)))}])
            point (fn [[x1 dx] [y1 dy]]
                    (let
                        [x (s x1 dx)
                         y (sy y1 dy)]
                      [:g
                       [:circle {:cx x
                                 :cy y
                                 :r 2}]

                       [:text {:x (- x 5)
                               :y (+ y 10)
                               :style {:font-size ".4rem"}}
                        (str "(" (+ (* x1 1) dx) "," (+ (* y1 4) dy)  ")")
                        ]]))]

          [:svg {:viewBox "0 0 400 400"
                 :style {:width "80vh"
                         :background-color "hsl(70,70%,70%)"}}
           [:path {:stroke :#aaa
                   :stroke-width 1
                   :fill :none
                   :d "M20 10 l 0 360 l 360 0"}]
           (map-indexed (fn [i x]
                  [:g
                   [:path {:stroke "hsl(10,70%,80%)"
                           :stroke-width 1
                           :fill :none
                           :d (str "M" (+ x 30)
                                   " " 370 " l 0 10" )}
                    ]

                   [:text {:style {

                                   :font-size "0.5rem"}
                           :fill "hsl(310,10%,40%)"
                           :x  (+ x 30)
                           :y (+ 370 5)}
                    (+ i 1)]
                   [:text {:style {

                                   :font-size "0.5rem"}
                           :fill "hsl(210,10%,40%)"
                           :x 2
                           :y (- 370 x 5)}
                    (* 4 (+ i 1))]
                   [:path {:stroke :#aaa
                           :stroke-width 1
                           :fill :none
                           :d (str "M20 " x " l -10 0" )}
                    ]])
                        (range 10 360 20))

           [:path {:stroke :#aaa
                   :stroke-width 3
                   :fill :none
                   :d  (str "M20 370"
                            " " "l 300 -300" )}]

           [:path {:stroke "hsl(260,70%,70%)"
                   :stroke-width 3
                   :fill :none
                   :d  (str "M20 370"
                            " " "l 200 -300" )}]

           (h [4 0] [17 4])
           (h [2 0] [17 4])
           (h [9 0] [17 4])
           (v [12 0] [4 0])
           (v [12 0] [9 0])

           (v [12 0] [13 2.5])
           (h [15 0] [17 4])
           (v [15 0] [15 0])
           (h [3 0] [17 4])
           (v [17 0] [3 0])
           [point [15 0] [15 0]]
           (comment
             [point [3 0] [5 4]]




             (h [6 2] [17 0])

             (h [11 0] [17 0])

             (h [15 0] [17 0])

             (h [9 0] [17 0])
             [point [9 0] [11 0]]
             [point [6 2] [14 3]]
             (v [3 0] [4 9])

             (v [15 0] [13 0])

             [point [9 0] [13 1.7]]

             [point [11 0] [3 3.5]]

             [point [11 0] [11 1]]
             [point [11 0] [11 4]]
             (v [15 0] [12 0])
             (v [15 0] [3 0])
             (v [15 0] [4 0]))])]]]))




















(defn abc [a]
  [:div {:style {:height "200vh"
                 :display :grid
                 :grid-template-columns "1fr 1fr 1fr 1fr 1fr"}}
   (map (fn [t]
          [:div {:style
                 {:background-color
                  (c [(* t 10) 77 57])}}
           (str "" (* t 10) " deg")])

        (range 80))])































(defn exercise-240 []
  (container
   240
   "1.4"
   [
    [:div {:style
           {:padding "4rem"
            :display :grid
            :gap "2rem"
            :grid-template-columns "2fr 8fr"}}





     [:div {:style {:height "80vh"}}

      [:div {:style {:padding "2rem"}}
       [:div "Exercise 4A (9 - 12)"]
       [:div "Reading 82 - 90"]
       [:div "Reading 28 - 57"]]

      [:div "folding, reduceing"]
      [:div "exponents of 2"]

      [:div
       [m '(= (:p 2 0)
              1)]]

      [:div
       [m '(= (:p 2 1)
              2)]]
      [:div
       [m '(= (:p 2 2)
              4)]]
      [:div
       [m '(= (:p 2 3)
              8)]]
      [:div
       [m '(= (:p 2 4)
              16)]]
      [:div [m '(= (:p 2 5)
                   32)]]

      [:div [m '(= (:p 2 6)
                   64)]]


      [:div {:style {:background-color (c [70 70 70])}}
       [:div [m '(= (:p 2 7)
                    128)]]

       [:div [m '(= 128 (* 2 2 2 2 2 2 2))]]

       [:div [m '(= 14 (* 2 (:b (+ 1 1 1 1 1 1 1) )))]]
       [:div [m '(= 14 (:b (+ 2 2 2 2 2 2 2) )
                    )]]
       [:div [m '(= 14 (* 2 7))]]]

      [:div [m '(= (* 2 151) 302)]]

      [:div [m '(= 302 (* 151 2))]]
      [:div [m '(= 1 (:p 3 0))]]
      [:div [m '(= 3 (:p 3 1))]]
      [:div [m '(= 9 (:p 3 2))]]
      [:div [m '(= 27 (:p 3 3))]]
      [:div [m '(= 81 (:p 3 4))]]
      [:div [m '(=  243 (:p 3 5))]]
      [:div [m '(= (:p 4 5)
                   (:p 2 (+ 5 2)))]]

      [:div [m '(= (:p 4 5)
                   (:p (:b (:p 2 2)) 5))]]
      [:div [m '(= (:p 4 5)
                   (:p 2 (* 5 2)))]]
      [:div [m '(= (:p 4 5)
                   (:p 2 (* 5 2)))]]

      [:div [m '(= 453 (+ 4 5 3)
                   )]]


      ]


     [:div
      (let [
            s (fn [x dx]
                (+ 20 (+ (* 20 x)
                         (* 4 dx))))
            sy (fn [y dy]
                 (- 370 (* 20 y) (* 4 dy)))

            v (fn [[x dx] [y dy]]
                [:path {:stroke :green
                        :stroke-width 1.5
                        :fill :none
                        :d (str "M"
                                (s x dx)
                                " "
                                (sy y dy)
                                "l " (str (* (- (s x dx) 20) -1))   " 0")}])

            h (fn [[x dx] [y dy]]
                [:path {:stroke :red
                        :stroke-width 1.5
                        :fill :none
                        :d (str "M" (s x dx)

                                " " (sy y dy)
                                "l 0 " (+ (* 20 y)
                                          (* 4 dy)))}])
            point (fn [[x1 dx] [y1 dy]]
                    (let
                        [x (s x1 dx)
                         y (sy y1 dy)]
                      [:g
                       [:circle {:cx x
                                 :cy y
                                 :r 2}]

                       [:text {:x (- x 5)
                               :y (+ y 10)
                               :style {:font-size ".4rem"}}
                        (str "(" (+ (* x1 1) dx) "," (+ (* y1 4) dy)  ")")
                        ]]))
            point1 (fn [[x1 dx] [y1 dy] [xt yt]]
                     (let
                         [x (s x1 dx)
                          y (sy y1 dy)]
                       [:g
                        [:circle {:cx x
                                  :cy y
                                  :r 2}]

                        [:text {:x (- x 5)
                                :y (+ y 10)
                                :style {:font-size ".4rem"}}
                         (str "(" xt
                              "," yt
                              ")")
                         ]])) ]

          [:svg {:viewBox "0 0 400 400"
                 :style {:width "80vh"
                         :background-color "hsl(70,70%,70%)"}}
           [:path {:stroke :#aaa
                   :stroke-width 1
                   :fill :none
                   :d "M20 10 l 0 360 l 360 0"}]
           (map-indexed (fn [i x]
                  [:g
                   [:path {:stroke "hsl(10,70%,80%)"
                           :stroke-width 1
                           :fill :none
                           :d (str "M" (+ x 30)
                                   " " 370 " l 0 10" )}
                    ]

                   [:text {:style {
                                   :font-size "0.5rem"}
                           :fill "hsl(310,10%,40%)"
                           :x  (+ x 30)
                           :y (+ 370 5)}
                    (- (+ i 1) 10)]

                   ])
                        (range 10 360 20))




           [:path {:stroke :#aaa
                   :stroke-width 2
                   :fill :none
                   :d (str "M" (s 2 0)
                           " "
                           (sy 2 0)
                           "l" (s 3 0) " " 0)
                   }]

           [:path {:stroke :#aaa
                   :stroke-width 2
                   :fill :none
                   :d (str "M" (s 6 0)
                           " "
                           (sy 2 0)
                           "l" (s 3 0) " " 0)
                   }]

           [:path {:stroke :#aaa
                   :stroke-width 2
                   :fill :none
                   :d (str "M" (s 10 0)
                           " "
                           (sy 2 0)
                           "l" (s 3 0) " " 0)
                   }]
           [:text {:x  (s 10 4)
                   :y (sy 2 0)}
            "920"]

           [:path {:stroke :#aaa
                   :stroke-width 2
                   :fill :none
                   :d (str "M" (s 14 0)
                           " "
                           (sy 2 0)
                           "l" (s 3 0) " " 0)
                   }]


           ])]]


    ]))




(defn exercise-221 [row col]
  (container
   221
   "1.4"
   [[:div {:style {:display :flex
                   :justify-content :center
                   :flex-direction :column
                   :align-items :center
                   }}
     [:div "lets put 1 bar on x is equal 1m, 30s as 1 bar of y axis"]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [50 80 80])}} [m '(= v (m s))] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [50 80 80])}} [m '(= m (:m v s))] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [50 80 80])}} [m '(= m (* 0.042 s))] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [50 80 80])}} [m '(= m (* 0.042 s))] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [60 70 80])}} [m '(= (:m m 1) (* 0.042 1))] ]
     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [70 80 80])}} [m '(=  m2 (* 0.042 2))] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [70 80 80])}}
      [m '(= m2 (* 0.042 (:b (+ 1 1))))] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [70 80 80])}}
      [m '(= m2 (+ 0.042 0.042))] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [70 80 80])}} [m '(= m2 (+ m1 0.042))] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [70 80 80])}}
      [m '(= m2 0.084)] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [100 80 80])}} [m '(= m3 (* 0.042 3))] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [100 80 80])}} [m '(= m3 (* 0.042 (:b (+ 2 1))))] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [100 80 80])}}
      [m '(= m3 (+ (* 0.042 2) (* 0.042 1) ))] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [100 80 80])}}
      [m '(= m3 (+ m2
                   (* 0.042 1) ))] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [100 80 80])}}
      [m '(= m3 (+ 0.084
                   (* 0.042 1) ))] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [100 80 80])}}
      [m '(= m3 (+ 0.084
                   (* 0.042 1) ))] ]


     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [50 80 80])}} [m '(= m (* 0.042 4))] ]

     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [50 80 80])}} [m '(= m (* 0.042 (:b (+ 3 1))))] ]
     [:div  {:style {:font-size "3rem"
                     :padding "1rem"
                     :background-color (c [50 80 80])}}
      [m '(= m (+ (* 0.042 3) (* 0.042 1)) )] ]




     [:div {:style
            {:padding "4rem"
             :display :grid
             :gap "2rem"
             :grid-template-columns "2fr 8fr"
             }}
      [:div {:style {:display :grid
                     :gap ".5rem"
                     :grid-template-columns "1fr 1fr"}}
       [:div {:style {:background-color
                      "hsl(70,70%,70%)"
                      :display :flex
                      :justify-content :center
                      :align-items :center
                      }}
        "m"]
       [:div {:style {:background-color
                      "hsl(100,70%,70%)"
                      :display :flex
                      :justify-content :center
                      :align-items :center}}
        "s"]
       (map-indexed (fn [i x]
                      [:div
                       {:style {:background-color
                                "hsl(150,70%,70%)"
                                :display :flex
                                :justify-content :center
                                :align-items :center}}
                       x])

                    (apply concat (map (fn [x y] [x y])
                                       row
                                       col
                                       ))

                    )]

      [:div
       (let [
             s (fn [x dx]
                 (+ 20 (+ (* 20 x)
                          (* 4 dx))))
             sy (fn [y dy]
                  (- 370 (* 20 y) (* 4 dy)))

             v (fn [[x dx] [y dy]]
                 [:path {:stroke :green
                         :stroke-width 1.5
                         :fill :none
                         :d (str "M"
                                 (s x dx)
                                 " "
                                 (sy y dy)
                                 "l " (str (* (- (s x dx) 20) -1))   " 0")}])

             h (fn [[x dx] [y dy]]
                 [:path {:stroke :red
                         :stroke-width 1.5
                         :fill :none
                         :d (str "M" (s x dx)

                                 " " (sy y dy)
                                 "l 0 "
                                 (+ (* 20 y)
                                    (* 4 dy)))}])
             point (fn [[x1 dx] [y1 dy]]
                     (let
                         [x (s x1 dx)
                          y (sy y1 dy)]
                       [:g
                        [:circle {:cx x
                                  :cy y
                                  :r 2}]

                        [:text {:x (- x 5)
                                :y (+ y 10)
                                :style {:font-size ".4rem"}}


                         (str "(" (+ (* x1 1) (* dx 0.4)) "," (+ (* y1 1) (* 0.4 dy))  ")")
                         ]]))]
         [:svg {:viewBox "0 0 400 400"
                :style {:width "80vh"
                        :background-color "hsl(70,70%,70%)"}}
          [:path {:stroke :#aaa
                  :stroke-width 1
                  :fill :none
                  :d "M20 10 l 0 360 l 360 0"}]
          (map-indexed (fn [i x]
                         [:g
                          [:path {:stroke "hsl(10,70%,80%)"
                                  :stroke-width 1
                                  :fill :none
                                  :d (str "M" (+ x 30)
                                          " " 370 " l 0 10" )}
                           ]

                          [:text {:style {
                                          :font-weight "700"
                                          :font-size "0.5rem"}
                                  :fill "hsl(310,10%,40%)"
                                  :x  (+ x 30)
                                  :y (+ 370 10)}
                           (* 1 (+ i 1))]
                          [:text {:style {

                                          :font-size "0.5rem"}
                                  :fill "hsl(210,10%,40%)"
                                  :x 2
                                  :y (- 370 x 5)}
                           (* 30 (+ i 1))]
                          [:path {:stroke :#aaa
                                  :stroke-width 1
                                  :fill :none
                                  :d (str "M20 " x " l -10 0" )}
                           ]

                          ]
                         )
                       (range 10 360 20))

          (comment

            [point [1 0] [1 0]]
            )


          [point
           [(/ 0 25) 0]
           [(/ (* 0.042 0)  2) 0] ]


          [point
           [(/ 50 25) 0]
           [(/ 2.1  2) 0] ]


          [point
           [(/ 80 25) 0]
           [(/ (* 0.042 80)  2) 0] ]


          [point
           [(/ 120 25) 0]
           [(/ (* 0.042 120)  2) 0] ]

          [point
           [(/ 150 25) 0]
           [(/ (* 0.042 150)  2) 0] ]
          [point
           [(/ 450 25) 0]
           [(/ (* 0.042 450)  2) 0]]



          [:path {:stroke :#111
                  :stroke-width 1
                  :fill :none
                  :d
                  (str "M " (s 0 0) " " (sy 0 0) " L "
                       (s (/ 450 25) 0 ) " "  (sy (/ (* 0.042 450)  2) 0)) }]


          (h [(/ 300 25) 0]
             [(/ 36 2 ) 0])

          (v [(/ 450 25) 0]
             [(/ (* 0.042 300)  2) 0]
             )


          [point
           [(/ 250 25) 0]
           [(/ 10 2) 1]

           ]

          (h [(/ 250 25) 0]
             [(/ 36 2 ) 0])

          (v [(/ 450 25) 0]
             [(/ (* 0.042 250)  2) 0]
             )

          (comment [point [2 0] [1 .25]])
          (comment [point [3 1] [1 3.4]])

          (comment [point [4 4] [2 2.6]])

          (comment
            )

          (comment



            [point [3 0] [5 4]]

            (h [3 0] [17 4])

            (h [6 2] [17 0])

            (h [11 0] [17 0])

            (h [15 0] [17 0])

            (h [9 0] [17 0])
            [point [9 0] [11 0]]
            [point [6 2] [14 3]]
            (v [3 0] [4 9])

            (v [15 0] [13 0])

            [point [9 0] [13 1.7]]

            [point [11 0] [3 3.5]]

            [point [11 0] [11 1]]
            [point [11 0] [11 4]]
            (v [15 0] [12 0])
            (v [15 0] [11 0])


            (v [15 0] [3 0])
            (v [15 0] [4 0]))])]]]]))

(defn exercise-116 []
  [container 116 "2.1"
   [[:div {:style
           {:padding "2rem"}}
     [:div "what are the prime numbers that makes up -"]
     [:div (m '(= 672 ?))]
     [:div
      (m '(= (* 23 380) ?))
      " by using aljebric way"]

     [:div "generate primes from 1 - 100 using sieve of Eratosthenes"]

     ]]])

(defn exercise-115 []
  [container 115 "2.2"
   [
    (let [b 4]
      (m '(= (* a a) b)))
    (m '(= (* a a) (* 2 2)))

    (m '(= (* 13 q) 13))


    (m '(= (* p q) 13))

    (m '(= (+ p q) ?))

    (m '(= (* x y) 15))

    (m '(= (+ x y) ?))
    (m '(= (+ 3 5) 7))
    (m '(= (+ 1 15) 16))
    (m '(= 18 (* 9 2)))
    (m '(= 18 (* 3 3 2)))
    (m '(= 18 (* (:p 3 2) 2)))
    (m '(= 120 (* 2 60)))
    (m '(= 120 (* 2 2 30)))
    (m '(= 120 (* 2 2 2 15)))
    (m '(= 120 (* 2 2 2 3 5)))
    (m '(= 120 (* (:p 2 3) 3 5)))
    (m '(= 120 (* 3 40)))
    (m '(= 120 (* 3 4 10)))
    (m '(= 120 (* 3 4 5 2)))
    (m '(= 120 (* 3 2 2 5 2)))
    (m '(= 120 (* (:p 2 3) 3 5)))


    ]])

(defn exercise-210 []
 [container 210 "1.4"
  [[:div {:style {:padding "1rem"
                  :display :flex
                  :justify-content :center}}
    [:div {:style {:background-color "hsl(30,70%,70%)"}}
     "if 50 g of sweets cost $2.10,
"

     ]
    [:div "find the cost of 380 g of sweets."]]
   [:div {:style {:font-size "2rem"
                     :display :flex

                     :align-items :center
                     :flex-direction :column
                     :justify-content :center}}
       [:div (m '(= (y x) k))]
       [:div (m '(= (x1 y1)  (x2 y2) ))]


       [:div (m '(= ($ g) k))]
       [:div (m '(= $
                    (* k g)))]

    [:div (m '(= (2.1 50) k))]
    [:div (m '(= k
                 0.042
                 ))]


    [:div (m '(= $
                 (* 0.042 g)))]

    [:div (m '(= $
                 (* 0.042 380)))]

    [:div (m '(= (15.96 380)
                 k))]

    (comment
      [:div (m '(= (15.96 380)
                   0.042))])
    (comment

      [:div (m '(= (2.1 50) (15.96 380)))])
    (comment
      [:div (m '(= (2.1 50) 0.042))])]
      [:div {:style {:display :grid
                     :padding "1rem"
                     :gap ".5rem"
                     :grid-template-columns
                     "repeat(10,1fr)"
                     :grid-template-rows "1fr 1fr 1fr"}}
       [:div {:style {:grid-row "1/2"
                      :grid-column "1/2"
                      :background-color "hsl(70,70%,70%)"}}
        "g"]
       [:div {:style {:grid-row "2/3"
                      :grid-column "1/2"
                      :background-color "hsl(170,70%,70%)"}} [:div (m '(= $
                                                                          (* 0.042 g)))]]

       [:div {:style {:grid-row "1/2"
                      :grid-column "2/3"
                      :background-color "hsl(70,70%,70%)"}}
        "50"]
       [:div {:style {:grid-row "2/3"
                      :grid-column "2/3"
                      :background-color "hsl(170,70%,70%)"}}
        "2.1"]

       [:div {:style {:grid-row "1/2"
                      :grid-column "3/4"
                      :background-color "hsl(70,70%,70%)"}}
        "80"]
       [:div {:style {:grid-row "2/3"
                      :grid-column "3/4"
                      :background-color "hsl(170,70%,70%)"}}
        "3.36"]

       [:div {:style {:grid-row "1/2"
                      :grid-column "4/5"
                      :background-color "hsl(70,70%,70%)"}}
        "120"]
       [:div {:style {:grid-row "2/3"
                      :grid-column "4/5"
                      :background-color "hsl(170,70%,70%)"}}
        (* 120.0 .042)]

       [:div {:style {:grid-row "1/2"
                      :grid-column "5/6"
                      :background-color "hsl(70,70%,70%)"}}
        "150"]
       [:div {:style {:grid-row "2/3"
                      :grid-column "5/6"
                      :background-color "hsl(170,70%,70%)"}}

        "6.3"
        ]

       [:div {:style {:grid-row "1/2"
                      :grid-column "6/7"
                      :background-color "hsl(70,70%,70%)"}}
        "180"]
       [:div {:style {:grid-row "2/3"
                      :grid-column "6/7"
                      :background-color "hsl(170,70%,70%)"}}
        "7.56"

        ]
       [:div {:style {:grid-row "1/2"
                      :grid-column "7/8"
                      :background-color "hsl(70,70%,70%)"}}
        "210"]
       [:div {:style {:grid-row "2/3"
                      :grid-column "7/8"
                      :background-color "hsl(170,70%,70%)"}}
        (* 210 .042)

        ]
       [:div {:style {:grid-row "1/2"
                      :grid-column "8/9"
                      :background-color "hsl(70,70%,70%)"}}
        "230"]

       ]


      ]] )

(defn exercise-207 []
  [:div {:style {:display :flex
                 :flex-direction :column
                 :align-items :center
                 :justify-content :center}}
   [:div  "1110011000011011"]

   [:div {:style {:display :grid
                  :width "80vh"
                  :gap ".4rem"
                  :font-size "2rem"
                  :background-color "hsl(70,70%,70%)"
                  :grid-template-columns "1fr 1fr 1fr 1fr
1fr 1fr"}}
    [:div {:style {:display :flex
                   :justify-content :center
                   :grid-column "1/-1"
                   :grid-row "1/2"}}
     "add $t0, $s1, $s2"]

    [:div {:style {:display :flex
                   :justify-content :center
                   :grid-column "1/-1"
                   :grid-row "2/3"}}
     "add $8, $17, $18"]


    [:div "12 bits"]
    [:div "10 bits"]
    [:div "10 bits"]
    [:div "10 bits"]
    [:div "10 bits"]
    [:div "12 bits"]


    [:div "6 bits"]
    [:div "5 bits"]
    [:div "5 bits"]
    [:div "5 bits"]
    [:div "5 bits"]
    [:div "6 bits"]

    [:div {:style {:background-color "hsl(10,70%,70%)"}}
     "op"]
    [:div {:style {:background-color "hsl(30,70%,70%)"}}
     "rs"]
    [:div {:style {:background-color "hsl(30,70%,70%)"}}
     "rt"]
    [:div {:style {:background-color "hsl(30,70%,70%)"}}
     "rd"]
    [:div {:style {:background-color "hsl(30,70%,70%)"}}
     "shamt"]
    [:div {:style {:background-color "hsl(30,70%,70%)"}}
     "funct"]

    [:div {:style {:background-color "hsl(10,70%,70%)"}}
     "000000"]
    [:div {:style {:background-color "hsl(30,70%,70%)"}}
     "10001"]
    [:div {:style {:background-color "hsl(30,70%,70%)"}}
     "10010"]
    [:div {:style {:background-color "hsl(30,70%,70%)"}}
     "01000"]
    [:div {:style {:background-color "hsl(30,70%,70%)"}}
     "00000"]
    [:div {:style {:background-color "hsl(30,70%,70%)"}}
     "100000"]
    ]])
(comment "m 422.58528,70.565308 c 1.56302,120.578662 -3.82176,172.867882 -34.9175,263.267792 -31.09574,90.39991 -123.41199,421.14221 -140.58581,473.84957 -17.17383,52.70735 5.27961,118.0953 124.68288,129.83911 119.40326,11.7438 291.93945,-12.49269 298.98945,-18.27974 7.05,-5.78705 99.14156,-13.96838 69.58933,-102.23556 C 710.79139,728.7393 574.01918,334.60023 572.26067,203.18695 570.50216,71.773666 570.14039,58.497864 570.14039,58.497864")
(comment "m 170,230 c -7,-26 -7,-23 -13,-67 -4,-29 2.7,-40 48,-32 46.51941,7.883962 30.671,28.778912 29.1258,63.964058 -1.5452,35.18515 20.42691,39.10414 -16.64616,55.61646 -41.248662,18.37212 -47.69033,-21.12692 -47.69033,-21.12692 z")
(defn exercise-225 []
  [:div {:style {:height "30vh"
                 :width "30vh"}}
   [:svg {:viewBox "0 0 800 900"}



    [:path {:fill :none
            :stroke :#111
            :stroke-width 2
            :d "m 422.58528,64.685567 c 1.56302,61.827913 -3.82176,88.639733 -34.9175,134.993193 -31.09574,46.35346 -123.41199,215.94488 -140.58581,242.97111 -17.17383,27.02622 5.27961,60.55455 124.68288,66.5763 119.40326,6.02175 291.93945,-6.40575 298.98945,-9.37312 7.05,-2.96737 99.14156,-7.16242 69.58933,-52.4223 C 710.79139,402.17087 574.01918,200.07211 572.26067,132.68864 570.50216,65.305164 570.14039,58.497864 570.14039,58.497864"



            }]


    ]])


(defn exercise-226 []
  [container 226 "1.4"
   [[:div {:style {:height "70vh"
                   }}
     [:div {:style {:padding "1rem"
                    }}
      "Mass of the  lorry when it was carring grain was 11,600kg, Mass of the grain 3 times of empty lorry,
findout out the mass of the grain."]

     [:div {:style {:display :flex
                    :flex-direction :column
                    :align-items :center
                    }}
      [:div {:style {:padding "1rem"
                     :font-size "1.5rem"}} "let, x kg is the  of empty lorry"]

      [m '(= (+ (:m 3 x) x ) 11600)]
      [m '(= (:m 4 x)
             11600)]
      [m '(= (:m (4 4) x)
             (11600 4))]

      [m '(= x
             (11600 4))]
      [:div {:style {:padding "1rem"
                     :font-size "1.5rem"}}
       "as mass of the grain is 3x"]

      [m '(= (:m 3 x)
             (* 3 (11600 4)))]
      [m '(= (:m 3 x)
             (* 3 ((* (:b (+ 100 16)) 100) 4)))]
      [m '(= (:m 3 x)
             (* 3 ((* (:b (+ (* 25 4) (* 4 4))) 100) 4)))]
      [m '(= (:m 3 x)
             (* 3 ((* (:b (+ 25
                             4)) 4 100) 4)))]
      [m '(= (:m 3 x)
             (* 3 (:b (- 30 1)) 100))]

      ]


     ]]])

(defn exercise-227 []
  [container 227 "1.4"
   [[:div {:style {:height "70vh"
                   :display :flex
                   :justify-content :center
                   :flex-direction :column
                   :align-items :center
                   }}

     [:div
      [m '(= (+
              5000
              300
              90
              2
              0) 5392)]]

     [:div
      [m '(= (+
              (* 5 1000)
              (* 3 100)
              (* 9 10)
              (* 2 1)
              0) 5392)]]
     [:div
      [m '(= (+
              (* 5 (:p 10 3))
              (* 3 (:p 10 2))
              (* 9 (:p 10 1))
              (* 2 (:p 10 0))
              0) 5392)]]

     (comment )


     (comment
       [:div
        [m '(= (+
                (* 5 (:p 10 3))
                (* 3 (:p 10 2))
                (* 0 (:p 10 1))
                (* 2 (:p 10 0))
                0) 5302)]])
     (comment

       [:div
        [m '(= (+
                (* 9 (:p 10 3))
                (* 1 (:p 10 2))
                (* 6 (:p 10 1))
                (* 2 (:p 10 0))
                0) ?)]])
     (comment

       [:div
        [m '(= (+
                9000
                100
                60
                2
                0)
               9162
               )]])



     (comment
       )
     [:div {:style {:background-color "hsl(70,70%,70%)"

                    }}
      [m '(= (+
              (* 1 (:p 2 3))
              (* 0 (:p 2 2))
              (* 0 (:p 2 1))
              (* 1 (:p 2 0))
              ) 1001)]]
     [:svg {:viewBox "0 0 800 100"
            :style {:background-color "hsl(70,70%,70%)"
                    :height "10vh"}}
      [:path {:stroke :#111
              :stroke-width 2
              :fill :none
              :d "M180 0 l 0 20 l 390 0 l 0 -20"}]

      [:path {:stroke :#999
              :stroke-width 2
              :fill :none
              :d "M280 0 l 0 40 l 310 0 l 0 -40"}]

      [:path {:stroke "hsl(10,70%,70%)"
              :stroke-width 2
              :fill :none
              :d "M380 0 l 0 60 l 220 0 l 0 -60"}]

      [:path {:stroke "hsl(210,70%,70%)"
              :stroke-width 2
              :fill :none
              :d "M470 0 l 0 80 l 150 0 l 0 -80"}]


      ]

     [:div
      [m '(= (+
              8
              0
              0
              1
              ) 1001)]]

     [:div "1101"]

     (comment [file/file-input])

     (comment
       )
     (comment
       )










     ]]])

(defn testing-server []
  [container "240" "1.5"
   [[:div {:style {:height "400vh"

                   :font-size "2rem"
                                         :display :flex
                                         :justify-content :center
                                         :flex-direction :column
                                         :align-items :center
                   }}




     [bmap/stat1]

     [bmap/stat2]

     [:svg {:viewBox "0 0 400 400"
            :style {:background-color "hsl(70,70%,70%)"}}

      [:path {:stroke :#111
              :stroke-width 2
              :fill (c [70 70 70])
              :d "M100 20 l 100,30"}]

      [:path {:stroke :#111
              :stroke-width 2
              :fill (c [70 70 70])
              :d "M100 20 l 100,250"}]

      [:path {:stroke :#111
              :stroke-width 2
              :fill :none
              :d "M100 20 l 0,250"}]

      [:path {:stroke :#111
              :stroke-width 2
              :fill :none
              :d "M100 270 l -90 -30"}]

      [:path {:stroke :#111
              :stroke-width 2
              :fill :none
              :d "M100 270 l -90 -200"}]

      [:path {:stroke :#111
              :stroke-width 2
              :fill :none
              :d "M100 20 c 100,30 100,250  0,250 s -90 -200 0 -250"}]



      ]


     ]]]


  )

(defn layout1 []
  [:div {:style {:grid-column "2/3"
                   :grid-row "3/3"
                   :display :grid
                   :grid-template-columns
                   (str "max-content max-content"
                        " "
                        "minmax(min-content,1fr)"
                        "min-content")
                 :grid-template-rows "12vw 8vh 10vh 10vh 14vh"
                 :background-color "hsl(70,70%,90%)"}}
   (comment [file/file-input-background])

   [:div {:style {:grid-column "3 / 5"
                  :grid-row "1 / 6"
                  :overflow :hidden
                  }}
    [bmap/bd-map]]
     [:div {:style {:grid-row "2 / 3"
                    :grid-column "1 / 3"
                    :font-variation-settings
                    "'wdth' 110, 'wght' 700, 'CNTR' 85"
                    :font-size "3rem"}} "now available in 7 districts" ]

     [:div {:style {:grid-column "2/3"
                    :grid-row "3 / 4"}}
      "within 15 min"]
     [:div
      {:style {:font-variation-settings
               "'wdth' 110, 'wght' 800, 'CNTR' 100"
               :font-size "2.2rem"
               :grid-column "3 / 5"
               :grid-row "3 / 4"}}
      "Dhaka, Gazipur Narayangang"]
   [:div {:style {
                    :grid-row "4 / 5"
                    :grid-column "2 / 4"
                    :font-variation-settings "'wdth' 110, 'wght' 210, 'CNTR' 5"
                  :font-size ".8rem"

                  }}
    [:div {:style {:display :flex
                   :position :sticky
                   :top "20vh"

                   }}
     [:input {:type "text"
              :style {:flex .8}}]
     [:div ""]]]
   [:div {:style {:text-decoration :line-through
                  :grid-row "5 / 6"
                  :grid-column "2 / 3"}}
    "24/7 instant delevary"]
     [:div {:style {:grid-row "5 / 6"
                    :grid-column "4 / 5"
                    :font-variation-settings "'wdth' 110, 'wght' 210, 'CNTR' 5"
                    :font-size ".8rem"
                    }}
      "dhaka chittagong narayangang gazipur manikgog doshore kulna"]
   ])

(defn exercise-250 []
  [container "250"
   "1.5"
   [[:div {:style {:height "100vh"
                   :display :flex
                   :flex-direction :column
                   :align-items :center}}
     [:div "Length " 1 "m"]
     [:div {:style {:background-color "hsl(70,70%,70%)"}}
      [m '(= (:m 1 c m) (:m (1 (:p 10 2))
                            m)  )]
      [:div
       [m '(= (:m 1 c m) (:m (1  (* 10 (:p 10 1)))
                             m)  )]]]
     [:div [m '(= (:m 1 c m) (:m (:p 10 -2) m)  )]]

     [:div {:style {:background-color "hsl(170,70%,70%)"}}
      [:div [m '(= (:m 1 m m) (:m (:p 10 -3) m)  )]
       ]
      [:div [m '(= (:m 1 m m) (:m (1 (:p 10 3)) m)  )]
       ]

      ]
     [:div {:style {:background-color "hsl(10,70%,70%)"
                    :font-size "2rem"}}
      [m '(= (:p a (+ x y) ) (:m (:p a x) (:p a y)))]]


     [:div [m '(= (:m 1 μ m) (:m (:p 10 -6) m)  )]]
     [:div [m '(= (:m 1 μ m) (:m (1 (:p 10 6)) m)  )]]
     [:div {:style {:background-color "hsl(210,70%,70%)"
                    :font-size "2rem"}}
      [m '(= (:m 1 μ m) (:m (1 (* 1000000 (:p 10 0))) m)  )]
      ]
     [:div {:style {:background-color "hsl(310,70%,70%)"
                    :font-size "2rem"}}
      [:div [m '(= (:m 1 n m) (:m (:p 10 -9) m)  )]]
      [:div [m '(= (:m 1 n m) (:m (1 (* (:p 10 3) (:p 10 6)))
                                  m)  )]]

      [:div {:style {:background-color "hsl(10,70%,70%)"
                     :font-size "2rem"}}
       [m '(= (:p 10 (+ 3 6) ) (* (:p 10 3) (:p 10 6)))]]
      [:div [m '(= (:m 1 n m) (:m (1
                                   (* 1000
                                      (:p 10 6)))
                                  m)  )]]
      ]
     ]]])


(defn exercise-260 []
  [container 260 "1.5"
   [
    [:div [m '(* 250 23)]]
    ]])


(defn timeline []
  [:div {:style
         {:min-height "470vh"
          :font-size "2rem"
          :gap "0.5rem"
          :display :grid
          :grid-template-columns "1fr 4fr"}}
   [:div {:style {:background-color "#aaa"}}
    [:div {:style
           {:display :flex
            :align-items :center
            :flex-direction :column} }
     [m '(* 101 99)]
     [m '(:m (:b (+ 100 1)) (:b (- 100 1)))]
     [m '(= (- (:p 100 2) 1) 9999)]

     [m '(- (* 100 100)
            1)]
     [m '(- 10000
            1)]
     [m '(- 10000
            1)]
     [:div {:style {:width "30vh"
                    :height ".1rem"
                    :background-color (c [70 70 70])}} ""]
     [m '(:m (:b (+ 100 3)) (:b (- 100 3)))]

     [m '(= (- (:p 100 2) (:p 3 2) ) 9991)]
     ]


    ]
   [:div {:style {:background-color "#eee"
                  :display :flex
                  :align-items :center
                  :flex-direction :column}}


    [:div {:style {:height "20vh"}}
     [:div
      [m '(:m (:b (+ x 1)) (:b (- x 1)))]]
     [:div
      [m '(- (:m x (:b (+ x 1)))
             (:m 1 (:b (+ x 1))))]]
     [:div
      [m '(- (+ (:p x 2) x ) x (:p 1 2)) ]]
     [:div
      [m '(- (:p x 2) (:p 1 2)) ]]]

    [:div {:style {:height "40vh"}}
     [:div
      [m '(:m (:b (+ x 3)) (:b (- x 3)))]]
     [:div
      [m '(- (:m x (:b (+ x 3)))
             (:m 3 (:b (+ x 3))))]]
     [:div
      [m '(- (+ (:p x 2) (:m 3 x) ) (:m 3 x)
             (:p 3 2)) ]]
     [:div {:style {:background-color (c [70 70 70])}}
      [m '(- (:p x 2) (:p 3 2))]]]

    [:div {:style {:background-color "hsl(70,70%,70%)"}}
     [m '(= (- (+ (:p u 2) (:m 6 u)) 27) 0)]]

    [:div {:style {:background-color "hsl(70,70%,70%)"}}
     [m '(= (+ (- (+ (:p u 2) (:m 6 u)) 27) 27)
            27)]]
    [:div {:style {:background-color "hsl(100,70%,70%)"}}
     [m '(= (+ (:p u 2) (:m 6 u))
            27)]]
    [:div {:style {:background-color "hsl(110,70%,70%)"}}
     [m '(=
          (+ (:p u 2) (:m 6 u)) (* 3 3 3))]]

    [:div {:style {:background-color "hsl(110,70%,70%)"}}
     [m '(=
          (:m u (:b (+ u 6)))
          (* 3 3 3))]]

    [:div {:style {:background-color "hsl(160,70%,70%)"}}
     [m '(=
          (:m 3 (:b (+ 3 6)))
          (* 3 3 3))]]
    [:div {:style {:background-color "hsl(160,70%,70%)"}}
     [m '(=
          ((:m 3 (:b (+ 3 6))) 3)
          ((* 3 3 3) 3))]]

    [:div {:style {:background-color "hsl(160,70%,70%)"}}
     [m '(=
          (:b (+ 3 6))
          (*  3 3))]]

    [:div {:style {:background-color "hsl(160,70%,70%)"}}
     [m '(=
          (:b (+ 3 6))
          (*  3 3))]]
    [:div {:style {:background-color "hsl(160,70%,70%)"}}
     [m '(=
          9
          9)]]

    [:div {:style {:background-color "hsl(260,70%,70%)"}}
     [m '(=
          (:m (:p x 2)
              (:b
               (+
                (:m 7 (:p x 1))
                (:m 21 (:p x 0))

                ))) 0)]]
    [:div {:style {:background-color "hsl(260,70%,70%)"}}
     [m '(=
          ( (:m (:p x 2)
                (:b
                 (+
                  (:m 7 x)
                  21)))
           (:p x 2)) 0)]]

    [:div {:style {:background-color "hsl(260,70%,70%)"}}
     [m '(=
          (:b
           (+
            (:m 7 x)
            21))
          0)]]
    [:div {:style {:background-color "hsl(300,70%,70%)"}}
     [m '(=
          (+
           (:m 7 x)
           (* 7 3))
          0)]]

    [:div {:style {:background-color "hsl(300,70%,70%)"}}
     [m '(=
          (:m (7 7)
              (:b
               (+
                x
                3)))
          (0 7))]]

    [:div {:style {:background-color "hsl(300,70%,70%)"}}
     [m '(=
          (+
           x
           3)
          0)]]
    [:div {:style {:background-color "hsl(300,70%,70%)"}}
     [m '(=
          (+
           x
           3 (- 3))
          (- 3))]]
    [:div {:style {:background-color "hsl(300,70%,70%)"}}
     [m '(=
          x
          (- 3))]]
    [:div "next"]

    [:div {:style {:background-color "hsl(300,70%,70%)"}}
     [m '(=
          (+
           (- (:m 6 (:p x 2))

              (:m 24 x)
              (:m 5 x)
              )
           (* 5 4))
          0)]]
    [:div {:style {:background-color "hsl(300,70%,70%)"}}
     [m '(=
          (+
           (- (:m 6 (:p x 2))

              (:m (* 4 6) x)
              (:m 5 x)
              )
           (* 5 4))
          0)]]

    [:div {:style
           {:background-color "hsl(100,70%,70%)"}}
     [m '(=
          (- (:m  (:m 6 x)
                  (:b (- x 4)))
             (:m 5 (:b (- x 4))))
          0)]]

    [:div {:style
           {:height "25vh"
            :display :flex
            :justify-content :center
            :align-items :center
            :flex-direction :column
            :background-color "hsl(10,70%,70%)"}}
     [m '(-
          (:m 5 (:b (- x 4))))]

     [m '(-
          (:m x (:b (- 5))) (:m 4 (:b (- 5))))]
     [m '(+
          (- (:m  5 x))
          (* 4 5))]
     ]

    [:div {:style
           {:height "25vh"
            :display :flex
            :justify-content :center
            :align-items :center
            :flex-direction :column
            :background-color "hsl(210,70%,70%)"}}

     [m '(+
          (- (:m  5 x))
          (* 5 4))]
     [m '(-
          (:m x (:b (- 5))) (:m 4 (:b (- 5))))]
     [m '(-
          (:m 5 (:b (- x 4))))]
     ]

    [:div {:style
           {:background-color "hsl(400,70%,70%)"}}
     [m '(=
          (- (:m  (:m 6 x)
                  (:b (- x 4)))
             (:m 5 (:b (- x 4))))
          0)]]
    [:div {:style
           {:background-color "hsl(400,70%,70%)"}}
     [m '(=
          (:m (:b (- x 4))
              (:b (- (:m 6 x ) 5))) 0)]]

    [:div "if " [m '(= (- x 4) 0)] " , " [m '(= x 4)]]
    [:div "else "
     [m '(= (- (:m 6 x ) 5) 0)
      ]
     " , "
     [m '(= x (5 6))]]

    [:div  {:style {:background-color (c [70 70 70])
                    :font-size "1rem"
                    :display :flex
                    :justify-content :center
                    :width "70vw"
                    :height "2vh"}}
     "(IV)"]

    [m '(= (+ (:m 3 (:p w 2))
              (:m 49 w)
              60) 0)]

    [m '(= (+ (:m 3 (:p w 2))
              (:m 45 w)

              (:m 4 w)
              60) 0)]

    [m '(= (+ (:m 3 (:p w 2))
              (:m (* 15 3 )
                  w)

              (:m 4 w)
              (* 4 15)) 0)]

    [m '(= (+  (* w (:m 3 w ))
               (:m (* 15 3 )
                   w)

               (:m 4 w)
               (* 4 15)) 0)]
    [m '(= (+ (:m 3  w (:b (+ w 15)))
              (:m 4  (:b (+ w 15)))) 0)]
    [m '(= (:m (:b (+ w 15)) (:b (+ (:m 3 w) 4))) 0)]

    [:div "if " (m '(= (+ w 15) 0))]
    [:div "then " (m '(= (- (+ w 15) 15) (- 15)))]
    [:div "so " (m '(= w
                       (- 15)))]

    [:div "else  " (m '(= (+ (:m 3 w) 4) 0))]
    [:div "else  " (m '(=
                        (+ (:m 3 w ) 4 (:b (- 4))) (- 4)))]
    [:div "else  " (m '(=

                        (:m 3 w)
                        (- 4)))]
    [:div "else  " (m '(=

                        w
                        ( (- 4) 3)))]
    [:div "else  " (m '(=

                        w
                        (- (4
                            3))))]

    [:div  {:style {:background-color (c [70 70 70])
                    :font-size "1rem"
                    :display :flex
                    :justify-content :center
                    :width "70vw"
                    :height "2vh"}}
     "(V)"]
    [m '(=
         (- (:m 8 (:p h 2))  (:m 2 h) 15) 0)]
    [m '(=
         (- (:m 8 (:p h 2))  (:m 2 h)) 15)]
    [m '(=
         (- (:m (:m  2 h 4) h)
            (:m 2 h)) (* 3 5))]
    [m '(=
         (:m (:m 2 h)
             (:b (- (:m 4 h) 1)))
         (* 3 5))]
    [m '(= (:m 2 h) 3)]
    [m '(= (- (* 2 (:m 2 h)) 1) ?)]
    [m '(= (- (* 2 3) 1) 5)]
    [m '(= h (3 2))]

    [:div  {:style {:background-color (c [70 70 70])
                    :font-size "1rem"
                    :display :flex
                    :justify-content :center
                    :width "70vw"
                    :height "2vh"}}
     "(VI) back?"]
    (comment
      [:svg {:viewBox "0 0 200 20"
             :overflow :visible
             :style {:height "3rem"}}
       [:path {:d "M 5 24 q 7 -14 13 -2"
               :style {:fill :none
                       :stroke :black}}]
       [:path {:d "M 5 24 c 2 -14 27 -14 38 0"
               :style {:fill :none
                       :stroke :black}}]
       [:circle {:r 1 :cx 5 :cy 24}]
       [:circle {:r 1 :cx 18 :cy 22}]
       ;; [:circle {:r 1 :cx 12 :cy 10}]
       ;; [:circle {:r 1 :cx 32 :cy 10}]
       [:circle {:r 1 :cx 43 :cy 24}]])

    [m '(= (:m  (:b (- p 1)) (:b (- p 6)))
           (:m 4 (:b (- (* 2 5 5) 1))))]

    [m '(= (- p 1)
           (- (* 2 5 5) 1)
           )]
    [m '(= p
           (* 2 5 5)

           )]
    [m '(= (- p 6)
           4
           )]
    [:div "our initial guess is wrong"]
    (m '(= 196  (:b (- 200 4))))
    (m '(= 196  (:b (- (* 4 50)  4))))
    (m '(= 196 (:m 4 (:b (- 50
                            1)))))
    [m '(= (:m  (:b (- p 1)) (:b (- p 6)))
           (* 4 7 7) )]
    [:div "That's a End "]

    ]]






  )

(defn exercise-261 []
    (container "4A.4a" "1.4"
               [[:div {:style {:min-height
                               "400vh"
                               :display :flex
                               :flex-direction :column

                               :align-items :center
                               }}

                 [:div "Simplify each of the following expression"]

                 [:div "(a). " [m '(+ (:m 5 x)
                                      (- 22
                                         (:m 6 x)
                                         23))]]

                 [m '(- (:m 5 x)

                        (:m 6 x)
                        1)]

                 [m '(- (:m x (:b  (- 5
                                      6)
                                   )) 1)]

                 [m '(- (:m x (:b  (-
                                    1)
                                   )) 1)]

                 [m '(- (- x) 1)]
                 [:div "let, do 4(d)"]
                 [m '(- (+ (-
                            (+
                             (- (:m 6 x) (:m 20 y))
                             (:m 7 z)
                             ) (:m 8 x)) (:m 25 y))
                        (:m 11 z))]

                 [m '(+
                      (:b (- (:m 6 x) (:m 8 x)))
                      (:b (- (:m 25 y) (:m 20 y)))
                      (:b
                       (- (:m 7 z) (:m 11 z))))]
                 [:div "let, do 4(c)"]
                 [:div "Commutative law"]
                 [m '(= (:m x y)
                        (:m y x)
                        )]


                 [m '(-
                      (+
                       (:m 6 x y)
                       (:m 13 x))
                      (:m 2 y x)
                      (:m 5 x))]

                 [:div "let, do 8(b)"]
                 [m '(- (+
                         (:m (- 3) x)
                         (:b (:m (- 5) y))
                         (:m 10 y)
                         )

                        (:m 7 x))]

                 [m '(+ (+
                         (:b (:m (- 3) x))
                         (:b (:m (- 5) y))
                         (:m 10 y)
                         )

                        (:b (:m (- 7) x)))]
                 [m '(+
                      (:b (:m (- 3) x))
                      (:b (:m (- 7) x)
                          )
                      (:m 10 y)
                      (:b (:m (- 5) y))
                      )]

                 [m '(+

                      (:m (- x) (:b (+ 3 7)))
                      (:m y (:b (- 10 5)))
                      )]

                 [:div {:style {:background-color
                                "hsl(70,70%,70%)"
                                :height "4vh"
                                :display :flex
                                :justify-content :center
                                :width "100vw"} } "4A.8(d)" ]
                 [m '(+ (- (:m (- 7) x) (:b (:m (- 15) y))
                           (:b (:m (- 2) x))) (:b (:m (- 6) y)))]
                 [m '(+   (:m (:b (- 7)) x)
                          (:m 15 y)
                          (:m 2 x)
                          (:m (:b (- 6)) y)

                          )
                  ]

                 [m '(+
                      (:m 2 x)
                      (:m (:b (- 7)) x)
                      (:m 15 y)
                      (:m (:b (- 6)) y))
                  ]


                 [m '(+

                      (:m  x (:b (+ 2 (:b (- 7)))))

                      (:m  y (:b (+ 15 (:b (- 6)))))

                      )
                  ]

                 [m '(+

                      (:m  x (:b (- 2 7)))

                      (:m  y (:b (- 15 6)))

                      )
                  ]
                 [:div {:style {:background-color
                                "hsl(70,70%,70%)"
                                :height "4vh"
                                :display :flex
                                :justify-content :center
                                :width "100vw"} } "4A.7(d)" ]
                 [m '(= a 3)]
                 [m '(= b (- 4))]
                 [m '(= c (- 2))]
                 [:div "part 1"]
                 [m '((- b c) (+ (:m 3 c) (:m 4 b)))]
                 [m '((- (:b (- 4)) (:b (- 2)))
                      (+ (:m 3 (:b (- 2))) (:m 4 (:b (- 4)))))]
                 [m '(= (- 4) (* 2 (:b (- 2))))]

                 [:div {:style {:background-color "hsl(70,70%,70%)"}}
                  [m '(
                       (-
                        2 4)

                       (+ (:m 3 (:b (- 2)))
                          (* 4 (:b (- 2))
                             2)))]        ]

                 [:div {:style {:background-color "hsl(10,70%,70%)"}}
                  [m '(
                       -2

                       (:m (- 2) (:b (+ 3
                                        (* 4
                                           2)))))]        ]

                 [:div {:style {:background-color "hsl(40,70%,70%)"}}
                  [m '(
                       1
                       (+ 3
                          (* 4
                             2))

                       )]        ]

                 [:div {:style {:background-color "hsl(40,70%,70%)"}}
                  [m '(
                       1
                       11

                       )]]




                 [:div "part 2"]
                 [m '(+ ((:m b c) a) ((:m a c ) b))]

                 [m '(+ ((:m (:b (- 4)) (:b (- 2))) 3)
                        ((:m 3 (:b (- 2)) ) (:b (- 4))))]

                 [m '(+ (8 3)
                        ((:m 3 (:b (- 2)) ) (:m 2 (:b (- 2)))))]
                 [m '(+ ((* 8 2) (* 3 2))
                        ((* 3 3) (* 2 3)))]

                 [m '((+ 16 9) 6)]
                 [m '(25
                      6)]
                 [:div
                  [m '(1 11) ] "÷"
                  [m '(25 6)] ]

                 [:div
                  [m '(* (1 (:b (+ 10 1))) (6 25)) ]
                  ]
                 [:div {:style {:background-color
                                "hsl(70,70%,70%)"
                                :font-size "3rem"}}
                  [m '((1 11) (25 6))]]

                 [:div {:style {:background-color
                                "hsl(70,70%,70%)"
                                :font-size "3rem"}}
                  [m '(* (1 11) (6 25))


                   ]]
                 [:div  "--------------------"]
                 [:div
                  {:style {:font-size "2rem"}}
                  [m '(-
                       ((+ a b (:m 2 c))
                        (- (:m 3 c) a b))
                       ((:m 5 c) (:m 4 b)))]]


                 [:div {:style {:font-size "2rem"}}

                  [m '(-
                       ((+ 3 (:b (- 4) )
                           (:m 2 (:b (- 2))))
                        (- (:m 3 (:b (- 2))) 3
                           (:b (- 4))))
                       ((:m 5 (:b (- 2))) (:m 4
                                              (:b (- 4)))))]]
                 [:div {:style {:background-color (c [70 70 70])}}
                  "factor "
                  [m '(= (- 4) (* 2 (- 2)))]]

                 [:div {:style
                        {:font-size "2rem"}}

                  [m '(-
                       ((+ 3 (:b (- 4) )
                           (:m 2 (:b (- 2))))
                        (- (:m 3 (:b (- 2))) 3
                           (:b (- 4))))
                       ((:m 5 (:b (- 2)))
                        (:m 4
                            (:b (- 2) ) 2)))]]
                 [:div {:style {:padding "1rem"
                                :background-color
                                (c [90 70 70])}}
                  [:div [m '(= 1 (* (:b (- 1))
                                    (:b (- 1))))]]
                  [:div
                   [m '(= (- 1) (* 1
                                   (:b (- 1))))]]

                  [:div
                   [m '(= (- 1) (* (:b (- 1)) 1
                                   ))]]
                  ]

                 [:div {:style
                        {:font-size "2rem"}}

                  [m '(-
                       (
                        (- 3 8)

                        (- 4 6
                           3
                           ))
                       (5
                        8))]]

                 [:div {:style
                        {:font-size "2rem"}}

                  [m '(-
                       (
                        (- 3 8)

                        (- 4
                           9
                           ))
                       (5
                        8))]]


                 [:div {:style
                        {:font-size "2rem"}}

                  [m '(-
                       (
                        (- 5)

                        (-
                         5
                         ))
                       (5
                        8))]]

                 [:div {:style
                        {:font-size "2rem"}}

                  [m '(-
                       1
                       (5
                        8))]]
                 [:div {:style
                        {:font-size "2rem"}}

                  [m '(3 8)]]











                 ]]))

(comment [:svg {:viewBox (reduce
                      (fn [acc b]
                        (str acc " " b)
                        )
                      ""
                      [-10 -10 400 400])
            :style {:background-color
                    (c [70 80 85])
                    :height (str 90
                                 (name :vh))
                    }
            }

      [:path {:stroke-width .5
              :stroke :#111
              :d  (str "M" 0 " "
                       (- 350 0) " "
                       "l 450 0")
              }]

      [:path {:stroke-width .5
              :stroke (c [10 70 70])
              :d  (str "M" 0 " "
                       (- 350 80) " "
                       "l 450 0")
              }]

      [:path {:stroke-width .5
              :stroke (c [10 70 70])
              :d  (str "M" 60 " "
                       (- 350 0) " "
                       "l 0 -400")
              }]

      [:path {:stroke-width .5
              :stroke :#111
              :d  (str "M" 0 " "
                       (- 350 0) " "
                       "l 0 -450")
              }]

      [:circle {:r 2
                :cx 0
                :cy (- 350 290)}]


      [:text {:x 0
              :y (- 360 0)
              :style {:font-size ".4rem"}}
       "(0,0)" ]

      [:text {:x 0
              :y (- 350 290)
              :style {:font-size ".4rem"}}
       "(0,290)" ]


          ])

(comment
       [:svg {:viewBox (reduce
                        (fn [acc b]
                          (str acc " " b)
                          )
                        ""
                        [-10 -10 400 400])
              :style {:background-color
                      (c [70 80 85])
                      :height (str 90
                                   (name :vh))
                      }}


        [:path {:stroke-width .5
                :stroke :#111
                :d  "M0 0 l 0 350"}]

        [:circle {:fill (c [70 20 70])
                  :r 2
                  :cx 87.5
                  :cy 350}]



        [:circle {:r 2
                  :cx 0
                  :cy (+ 175
                         (/
                          (- 350 175) 2))}]



        [:path {:stroke-width .5
                :stroke :#111
                :d  "M0 0 l 350 0"}]

        [:path {:stroke-width .5
                :stroke :#111
                :d  "M0 175 l 175 0"}]

        [:path {:stroke-width .5
                :stroke (c [30 70 70])
                :d  "M0 185 l 175 0"}]

        [:circle {:r 2
                  :cx 350
                  :cy 0 }]

        [:circle {:r 2
                  :cx 175
                  :cy 185 }]

        [:text {:x 176
                :y 185
                :style {:font-size ".4rem"}}
         "(175,185)" ]

        [:text {:x 175
                :y 175
                :style {:font-size ".4rem"}}
         "(175,175)" ]

        [:text {:x 350
                :y 0
                :style {:font-size ".4rem"}}
         "(350,0)" ]

        [:circle {:r 2
                  :cx 165
                  :cy 50 }]

        [:circle {:fill (c [10 70 70])
                  :r 2
                  :cx 175
                  :cy 0 }]




        [:text {:x 2
                :y 175
                :style {:font-size ".4rem"}}
         "(0,175)" ]


        [:circle {:fill (c [200 70 70])
                  :r 2
                  :cx 175
                  :cy 350 }]

        [:circle {:fill (c [200 70 70])
                  :r 2
                  :cx 175
                  :cy 350 }]

        [:text {:x 175
                :y 360
                :style {:font-size ".4rem"}}
         "(175,350)" ]


        [:circle {:fill (c [10 70 70])
                  :r 2
                  :cx 0
                  :cy 175}]

        [:text {:x 175
                :y -3
                :style {:font-size ".4rem"}}
         "(175,0)" ]

        [:path {:stroke-width .5
                :stroke :#111
                :d  "M0 350 l 400 0"}]

        [:circle {:r 2
                  :cx 0
                  :cy 350 }]

        [:text {:x -5
                :y 360
                :style {:font-size ".4rem"}}
         "(0,350)" ]

        [:circle {:r 2
                  :cx 350
                  :cy 350 }]

        [:text {:x 350
                :y 360
                :style {:font-size ".4rem"}}
         "(350,350)" ]

        [:circle {:r 2
                  :cx 0
                  :cy 50}]

        [:text {:x 5
                :y 50
                :style {:font-size ".4rem"}}
         "(0,50)" ]

        [:circle {:r 2
                  :cx 0
                  :cy 0}]

        [:text {:x 0
                :y 0
                :style {:font-size ".4rem"}}
         "(0,0)" ]
        [:circle {:r 2
                  :cx
                  (+ 175 (/ (- 350 175) 2))
                  :cy 350}]

        [:text {
                :x (+ 175 (/ (- 350 175) 2))
                :y 360
                :style {:font-size ".4rem"}}
         (str  "("  (+ 175 (/ (- 350 175) 2))  ",350)") ]
        ])

(defn exercise-164 []
  [container "164" "1.4"
   [
    [:div {:style {:display :grid
                   :grid-template-columns "auto auto"}}
     [:div {:style {:padding-left "10vw"}}
      [:div [m '(= y (- 400 yc))]]

      [:div [m '(= yc (- 400 y))]]

      [:div [m '(= x (xc 30))]]
      [:div [m '(= xc (:m  30 x))]]




      ]


     (let [y (fn [yc] (- 400 yc))]

       [:svg {:viewBox (reduce
                        (fn [acc b]
                          (str acc " " b)
                          )
                        ""
                        [-10 -10 420 420])
              :style {:background-color
                      (c [70 80 85])
                      :height (str 90
                                   (name :vh))
                      }
              }

        (map

         (fn [a]
           (let [y1 (+ 10 (* a 30))]
             [:g
              [:circle {:cx 0
                        :cy y1
                        :r 1}]
              [:text {:x -7
                      :y y1
                      :style {:font-size ".3rem"}
                      } (y y1)]
              ])
           )


         (range 20))


        (map

         (fn [a]
           [:g
            [:circle {:cx (* a 30)
                      :cy (y 0)
                      :r 1}]
            [:text {:x (* a 30)
                    :y (y -5)
                    :style {:font-size ".3rem"}
                    } a]
            ]
           )


         (range 15))

        [:circle {:cx 0
                  :cy (y 20)
                  :r 2}]

        [:circle {:cx 90
                  :cy (y 0)
                  :r 2}]
        (map
         (fn [a]
           [:path {:stroke (c [90 70 70])
                   :stroke-width .8
                   :d (str "M" -10  " " (y (* 30 a))   " l  420 0" )}])
         (range 41))


        [:path {:stroke (c [40 70 70])
                :stroke-width 1.2
                :d (str "M0 " -10   " l 0 410" )}]

        (map
         (fn [x]
           [:path {:stroke (c [10 70 70])
                   :stroke-width .8
                   :d (str "M" (* x 30)  " " (y -10)   " l 0 -420 " )}])
         (range 42))

        [:path {:stroke (c [180 70 70])
                :stroke-width 1.2
                :d (str "M -10 400 l 440 0" )}]])]]])



(defn exercise-175 []
  [container "175" "1.4"
   [
    [:div {:style {:display :grid
                   :grid-template-columns "auto auto"}}
     [:div {:style {:padding-left "10vw"}}




      [:div [m '(= yc-min 0)]]
      [:div [m '(= xc-min 0)]]

      [:div [m '(= xc-max 400)]]
      [:div [m '(= yc-max 400)]]

      [:div [m '(= step 30)]]
      [:div [m '(= (* 7 step) 210)]]


      [:div {:style {:background-color (c [70 70 70])}}
       [m '(= yc
                   (- (:b (- 400 y)) 200)

                   )]]

      [:div {:style {:background-color (c [120 70 70])}}
       [m '(= yc
                   (- (:b (- 400 0)) 200)

                   )]]

      [:div {:style {:background-color (c [120 70 70])}}
       [m '(= yc
                   (- (:b (- 400 100)) 200)

                   )]]

      [:div {:style {:background-color (c [150 70 70])}}
       [m '(= yc
                   (- (:b (- 400 (:b (- 10)))) 200)

                   )]]


      [:div [m '(= x (xc 30))]]]
     (let [y (fn [yc]
               (- (* (- 400 yc) 1)
                  200))
           x (fn [xp] xp)
           xc-min 0
           yc-min 0
           yc-max 400
           xc-max 400
           step 30
           yc-max-with-border (+ yc-max 20)
           xc-max-with-border (+ xc-max 20)
           xc-min-with-border (- xc-min 10)
           yc-min-with-border (- yc-min 10)

           ]

       [:svg {:viewBox (reduce
                        (fn [acc b]
                          (str acc " " b)
                          )
                        ""
                        [xc-min-with-border
                         yc-min-with-border
                         xc-max-with-border
                         yc-max-with-border])
              :style {:background-color
                      (c [70 80 85])
                      :height (str 90
                                   (name :vh))
                      }
              }

        (map
         (fn [[y y1]]
           (let []
             [:g
              [:circle {:cx (x 0)
                        :cy y1
                        :r 1}]
              [:text {:x (x -7)
                      :y y1
                      :style {:font-size ".3rem"}
                      }
               y]]))
         (map
          (fn [y2] [y2 (y y2)])
          (range  -210 -210 (* step -1))))

        (map
         (fn [a]
           [:g
            [:circle {:cx (* a 30)
                      :cy (y 0)
                      :r 1}]
            [:text {:x (* a 30)
                    :y (y -5)
                    :style {:font-size ".3rem"}
                    } a]
            ]
           )


         (range 15))


        [:circle {:cx 0
                  :cy (y 20)
                  :r 2}]
        [:circle {:cx 90
                  :cy (y 0)
                  :r 2}]

        (map
         (fn [a]
           [:path {:stroke (c [90 70 70])
                   :stroke-width .8
                   :d (str "M" -10  " "
                           (y (* 30 a))
                           " l  420 0" )}])
         (range  -7 7))

        (map
         (fn [x]
           [:path {:stroke (c [10 70 70])
                   :stroke-width .8
                   :d (str "M" (* x 30)  " " (y -200)   " l 0 -420 " )}] )
         (range 14))


        [:circle {:cx 0
                  :cy (y 120)
                  :r 2
                  :fill (c [10 70 70])}]

        [:circle {:cx (* 3 30)
                  :cy (y 120)
                  :r 2
                  :fill (c [10 70 70])}]

        [:path {:stroke (c [10 70 70])
                :stroke-width .8
                :d (str "M" 0
                        " " (y 120)
                        " l " (* step 3)   " "  0 )}]

        [:path {:stroke (c [10 70 70])
                :stroke-width .8
                :d (str "M" (* 3 30)
                        " " (y 120)
                        " L " (* 7 30)
                        " "  (y -120) )}]

        [:circle {:cx (* 7 30)
                  :cy (y -120)
                  :r 2
                  :fill (c [10 70 70])}]

        [:circle {:cx (* 9 30)
                  :cy (y -120)
                  :r 2
                  :fill (c [10 70 70])}]

        [:circle {:cx (* 11 30)
                  :cy (y 180)
                  :r 2
                  :fill (c [10 70 70])}]





        ])]]])

(comment
  )






(defn exercise-165 []
  [container "165" "1.4"
   [
    [:div {:style {:display :grid
                   :grid-template-columns "auto auto"}}

     [:div {:style {:padding "2vw"}}
      (comment [file/file-input-background])
      [:div
       [m '(= x-canvas 0)]]
      [:div
       [m '(= x-canvas-max 800)]
       ]
      [:div
       [m '(= step-size 20)]]
      [:div
       [m '(= xc  (- (:m 20 x ) (* 20 20) 6))]]

      [:div
       [m '(+ 0 6)]]

      ]


     (comment :transform "rotate(0)")
     (let [x (fn [x1]
               (+ (* 20 20) (* x1 20) ))
           ]
       [:svg {:viewBox (reduce
                        (fn [acc b]
                          (str acc " " b)
                          )
                        ""
                        [-10 -10 820 420])
              :style {:background-color
                      (c [70 80 85])
                      :height (str 90
                                   (name :vh))
                      }
              }
        [:defs
         [:marker {:id "arr"
                   :viewBox "0 0 7 7"
                   :style {:overflow :visible}
                   :refX 5
                   :refY 5
                   :markerHeight 5
                   :markerWidth 5
                   }
          [:path {:fill "black"
                  :stroke "black"
                  :transform "rotate(0)"

                  :d "M 0.0,0.0 L 5.0,-5.0 L -12.5,0.0 L 5.0,5.0 L 0.0,0.0 z "}]]
         [:marker {:id "dot"
                   :viewBox "0 0 10 10"
                   :refX 5
                   :refY 5
                   :markerWidth 5
                   :markerHeight 5}
          [:circle {:cx 5
                    :cy 5
                    :r 5
                    :fill "black"}]]
         ]

        (map
         (fn [x]
           (let [a (* x 20)]
             [:g
              [:circle {:cx a
                        :cy 200
                        :r 1}]
              [:text  {:style {:font-size ".7rem"}
                       :x a
                       :y 210}

               (comment )
               (- (+ (- x 20)
                     5) 10)
               (comment (- x 20))
               ]
              ]))

         (range 0 40))



        [:circle {:cx (x -5)
                  :cy "180"
                  :r 2}]

        [:path {:stroke (c [10 70 70])
                :stroke-width 1
                :d (str "M" (x 0) " 180" " L " (x -5)  " 180")  }]


        [:path {:stroke (c [10 70 70])
                :stroke-width 1
                :d "M 0 200 l 900 0"}]



        ])









     ]]])


(defn ex-1 []
  [container 1 "2"
   [
    [:div {:style {:padding "1rem"}}
     "Mass of the  lorry when it was carring grain was 11,600kg, Mass of the grain 3 times of empty lorry,
findout out the mass of the grain."]

    [:div {:style {:background-color "hsl(90,80%,80%)"
                   :padding ".5rem"
                   :display :flex
                   :justify-content :center
                   :font-size "1.5rem"}}
     "Solution"]
    [:div
     {:style {:font-size "1.1rem"
              :padding "1rem"}}
     "let, define mass of the empty lorry, "
     [:math [:mi "x"]]]

    [:div {:style {:font-size "1.1rem"
                   :padding "1rem"}}
     "Mass the gain is 3 time of empty lorry,  "
     [:math [:mn 3] [:mi "x"] ] ]

    [:div {:style {:background-color "#ddd"

                   :font-size "2rem"
                   :display :flex
                   :align-items :center
                   :flex-direction :column}}
     [:div "Expression"]
     [m '(= 11600 (+ x (:m 3 x)))]
     [m '(=  (:m 4 x) 11600 )]
     [m '(=  x
             (11600 4) )]
     [m '(= 11600 (+ x (:m 3 x)))]
     [m '(= 11600 (+ (11600 4)  (:m 3 x)))]
     [m '(= (- 11600 (11600 4) )
            (:m 3 x))]
     [m '(=
          (:m 3 x)
          (- 11600 (11600 4) ))]
     [:div {:style {:background-color "hsl(40,70%,70%)"}}
      [m '(=
           (:m 3 x)
           (- 11600 ((*
                      116
                      100) 4) ))]]
     [m '(=
          (:m 3 x)
          (- 11600 ((* (:b (+ 100 16)) 100) 4) ))]
     [m '(=
          (:m 3 x)
          (- 11600 ((* (:b (+ (* 25 4) (* 4 4))) 100) 4) ))]
     [m '(=
          (:m 3 x)
          (- 11600 ((* (:b (+ 25  4 ))
                       4  100) 4) ))]
     [:div {:style {:background-color "hsl(40,70%,70%)"}}
      [m '(=
           (:m 3 x)
           (- 11600 (* 29 100)))]
      ]

     ]


    ]])

















(defn template11 []
  [:div {:style {:background-color :#f1fafa}}
   (comment
     [menu])

   [:div {:style {:display :grid
                  :background-color :#eee
                  :font-size "1rem"
                  :grid-auto-rows "minmax(40vh,auto)"
                  :grid-gap "1rem"
                  :grid-template-columns "repeat(auto-fit, minmax(35vw, 1fr))"}}
    [ex-1]
    [ex-2]

    [problem2]
    [logo {:height "20vh"
           :nwidth "20vw"}]
    (comment [ex-2d])

    [dis-law]
    (comment [divisible])
    [dis-law2 "-1" "b" "-a"]
    [ab2 "t" "3"]
    [ab2a "t" 3 100]
    [ab2a "t" 3 10]

    [ab2 "x" "1"]
    [dis-law2 "-1" "x" "3"]
    [ab2a "t" 1 100]

    [sq-factor "12"  30 3]
    [simple-example]
    (comment )
    (comment )

    [draw-1]
    [draw-2]
    [exer-4a]
    [test22]
    [test23]
    [test24]
    [exercise-8]
    [exercise-10]
    [exercise-11]
    [exercise-1]
    [exercise-111]
    [exercise-17]
    [exercise-51]
    [exercise-112]
    [exercise-155]
    [exercise-114]
    [exercise-113]
    [exercise-115]
    [exercise-116]
    ]

   [:div

    ;;[testpy]
    [exercise-206]
    [exercise-210]
    [exercise-221
     (map (fn [n] (* n 30 0.042)) (range 10))
     (map (fn [n] (* n 30)) (range 10))
     ]
    [exercise-201]
    [exercise-200]
    [exercise-202]
    [exercise-205]

    [exercise-215]
    [exercise-216]

    [exercise-207]

    [exercise-209
     ]

    [exercise-209
     ]
    [exercise-225]
    [exercise-226]
    [exercise-227]
    [testing-server]
    [exercise-240]
    [exercise-250]
    [exercise-260]
    [exercise-261]
    [timeline]
    [layout1]
    [exercise-163]
    [exercise-164]
    [exercise-165]
    [exercise-175]
    (comment [exercise-176])
    (comment [exercise-177])
    [exercise-313]
    [menu-ps]
    [:div
     [:div {:style {
                    :font-variation-settings
                    "'wdth' 107.5, 'wght' 257.33, 'CNTR' 0"}}
      (.format (moment) "dddd")]

     [:div {:style {:font-family "'Amstelvar VF'"
                    :font-size "1rem"}}
      (.format (moment) "MMMM Do YYYY, h:mm:ss a")]
     ]
    [simple-example]
    ]])


(defn grid-svg [[x y]
                [[xc-min yc-min]
                 [yc-max xc-max]]
                [[bxc-min byc-min]
                 [byc-max bxc-max]]
                step
                eqs]
  [:div {:style
         {
          :padding-left "10px"
          :width "98vw"
          :height "200vh"
          :display :grid
          :grid-template-columns "1fr 1fr 1fr 1fr"
          :grid-template-rows "1fr 1fr 1fr 1fr"}}

   [:div {:style {:z-index 2
                  :grid-column "4/5"
                  :grid-row "1/2"}}

    (map (fn [e] e) eqs)]
   [:div {:style {:grid-row "3/5"
                  :grid-column "1/5"}}
    [file/file-input]]
   [:div {:style {:z-index 2
                  :grid-row "1/2"
                  :grid-column "1/5"}}
    [animate-circle]]
   [:div {:style
          {
           :z-index 1
           :grid-column "1/5"
           :grid-row "1/3"}}
    (let [ [x1 y1 x2 y2 :as canvas]
          [(+ xc-min bxc-min)
           (+ yc-min byc-min)
           (+ xc-max bxc-max)
           (+ yc-max byc-max)]

          mark-y-grid
          (map
           (fn [[y y1]]
             (let []
               [:g
                [:circle {:cx (x 0)
                          :cy y1
                          :r 1}]
                [:text {:x (x -7)
                        :y y1
                        :style
                        {:font-size ".3rem"}}
                 y]]))
           (map
            (fn [a]
              [a
               (y (* step a))])
            (into (range 0 (/
                            (- y2 y1) (* 2 step)))
                  (range -1 (/
                             (- y2 y1) (* -1 2 step)) -1))
            ))
          mark-x-grid
          (map
           (fn [a]
             [:g
              [:circle {:cx (* a 30)
                        :cy (y 0)
                        :r 1}]
              [:text {:x (* a 30)
                      :y (y -5)
                      :style {:font-size ".3rem"}
                      } a]
              ]
             )


           (range (/ (- x2 x1) (* 1 step))))

          ]
      [:div


       [:svg {:viewBox (reduce
                        (fn [acc b]
                          (str acc " " b)
                          )
                        ""
                        canvas)
              :style
              {:background-color
               (c [70 80 85])}}
        [:defs
         [:marker {:id "i"
                   :refY 0
                   :refX 0
                   :orient :auto
                   :style {:overflow :visible}}
          [:path {:d "M 0 0 L 5 -5 L -12.5 0 L 5 5 L 0 0 z"
                  :style {:fill-rule :evenodd
                          :stroke (c [70 70 70])
                          :stroke-width 1
                          :stroke-opacity 1
                          :fill (c [300 70 70])
                          :fill-opacity 1}
                  :transform "scale(.5 ) rotate(180) translate(2,0)"
                  }]]]


        mark-y-grid
        mark-x-grid

        [:text {
                :style {:font-size ".4rem"}
                :x (x 30)
                :y (+ (y 30)
                      (* 6 2))
                } "a"]

        [:text {
                :style {:font-size ".4rem"}
                :x (x (* 4 30))
                :y (+ (y (+ (* 1 30) (* 6 3)))
                      )
                } "b"]

        [:text {
                :style {:font-size ".4rem"}
                :x (x (+ (* 6 30) (* 6 1 -1)))
                :y (+ (y (+ (* 3 30) (* 6 0)))
                      )
                } "c"]

        [:text {
                :style {:font-size ".4rem"}
                :x (x (+ (* 7 30) (* 6 2  1)))
                :y (+ (y (+ (* 4 30) (* 6 2)))
                      )
                } "d"]

        [:text {
                :style {:font-size ".4rem"}
                :x (x (+ (* 9 30) (* 6 0  1)))
                :y (+ (y (+ (* 4 30) (* 6 3 1)))
                      )
                } "e"]

        [:text {
                :style {:font-size ".4rem"}
                :x (x (+ (* 12 30) (* 6 0  1)))
                :y (+ (y (+ (* 5 30) (* 6 0 1)))
                      )
                } "f"]

        [:text {
                :style {:font-size ".4rem"}
                :x (x (+ (* 13 30) (* 6 0  1)))
                :y (+ (y (+ (* 5 30) (* 6 2 1)))
                      )
                } "g"]


        (map
         (fn [a]
           [:g
            [:path {:stroke (c [180 70 70])
                    :stroke-width .8
                    :d (str "M" -10  " "
                            (y (* 30 a))
                            " l  820 0" )}]

            [:path {:stroke (c [90 70 70])
                    :stroke-width .8
                    :d (str "M" -10  " "
                            (y (+ (* 30 a ) (* 6 1)))
                            " l  820 0" )}]

            [:path {:stroke (c [90 70 70])
                    :stroke-width .8
                    :d (str "M" -10  " "
                            (y (+ (* 30 a ) (* 6 2)))
                            " l  820 0" )}]

            [:path {:stroke (c [90 70 70])
                    :stroke-width .8
                    :d (str "M" -10  " "
                            (y (+ (* 30 a ) (* 6 3)))
                            " l  820 0" )}]
            [:path {:stroke (c [90 70 70])
                    :stroke-width .8
                    :d (str "M" -10  " "
                            (y (+ (* 30 a ) (* 6 4)))
                            " l  820 0" )}]])
         (range -7
                7))

        (map
         (fn [x]
           [:g
            [:path {:stroke (c [10 70 70])
                    :stroke-width .8
                    :d (str "M" (* x 30)  " " (y -200)   " l 0 -420 " )}]

            [:path {:stroke (c [10 70 70])
                    :stroke-width .8
                    :d (str "M" (* x 30)  " " (y -200)   " l 0 -420 " )}]

            ] )

         (range (/ (- x2 x1) (* 1 step))))





        [:path {:stroke (c [10 70 70])
                :stroke-width .8
                :marker-end "url(#i)"
                :d (str "M" 0
                        " " (y 120)
                        " l " (* step 3)   " "  0 )}]

        [:path {:stroke (c [10 70 70])
                :stroke-width .8
                :d (str "M" (* 3 30)
                        " " (y 120)
                        " L " (* 7 30)
                        " "  (y -120) )}]




        [:path {:stroke (c [10 70 70])
                :stroke-width .8
                :d (str "M" (* 7 30)
                        " " (y -120)
                        " l " (* 2 30)
                        " " 0)}]



        [:path {:stroke (c [10 70 70])
                :stroke-width .8
                :d (str "M" (* 7 30)
                        " " (y -120)
                        " L " (* 14 30)
                        " " (y 180))}]

        [:path {:stroke (c [380 70 70])
                :stroke-width 1
                :d (str "M" (x (+ (* 0 30) (* 6 0  1)))
                        " " (y (+ (* 0 30) (* 6 0 1)))

                        " l "  (* step 3)
                        " 0" )}]

        [:path {:stroke (c [180 70 70])
                :stroke-width 1
                :d (str "M" (x (+ (* 8 30) (* 6 0  1)))
                        " " (y (+ (* 5 30) (* 6 0 1)))

                        " l "  (+ (* step 3) (* (/ step 5) 3))
                        " 0" )}]


        [:path {:stroke (c [200 70 70])
                :stroke-width 1
                :d (str "M" (x (+ (* 3 30) (* 6 0  1)))
                        " " (y (+ (* 0 30)
                                  (* 6 0 1)))

                        " l " 0
                        " " (+ (* step 1 -1) (* (/ step 5) 3 -1) ) )}]


        [:path {:stroke (c [300 70 70])
                :stroke-width 1
                :d (str "M" (x (+ (* 8 30) (* 6 0  1)))
                        " " (y (+ (* 4 30)
                                  (* 6 2 1)))

                        " l " 0
                        " " (+ (* step 0 -1) (* (/ step 5) 3 -1) ) )}]

        [:path {:stroke (c [300 70 70])
                :stroke-width .8
                :fill :none
                :stroke-opacity .5
                :marker-mid "url(#i)"
                :marker-end "url(#i)"
                :d (str "M" (* 0 30)
                        " " (y 0)
                        " L " (* 3 30)
                        " " (y (- 60 12))
                        " L " (* 5 30)
                        " " (y (- 60 12))
                        " L " (* 7 30)
                        " " (y (+ (* 4 30) (* 6 2)))
                        " L " (* 8 30)
                        " " (y (+ (* 4 30) (* 6 2)))
                        " L " (+ (* 11 30) (* 6 3))
                        " " (y (+ (* 5 30)
                                  (* 6 0)))
                        " L " (+ (* 12 30) (* 6 3))
                        " " (y (+ (* 5 30)
                                  (* 6 0)))
                        " L " (+ (* 14 30)
                                 (* 6 0))
                        " " (y (+ (* 6 30)
                                  (* 6 0))))}]]])]])





(defn grid-svg3 [ [
                  [x y]
                  [xc-max yc-max]
                  [stepx stepy]
                  & rest]
                 eqs2]
  (let [
        [xc-min yc-min] [0 0]
        step stepx
        [[bxc-min byc-min]
         [byc-max bxc-max]]

        [[(* step -1) (* step -1)] [step step]]
        [x1 y1 x2 y2 :as canvas]
        [(+ xc-min bxc-min)
         (+ yc-min byc-min)
         (+ xc-max bxc-max)
         (+ yc-max byc-max)]
        [xg yg] [(fn [[xl xs]]
                   (x (+ (* step xl)
                         (* (/ step 5) xs))
                      ))

                 (fn [[xl xs]]
                   (y (+ (* step xl)
                         (* (/ step 5) xs))
                      ))]
        [sx sy] [(fn [[s sa]]
                   (+ (* s step) (* sa (/ step 5))))
                 (fn [[s sa]]
                   (+ (* s step) (* sa (/ step 5))))
                 ]
        eqs (filter (fn [[x & rest :as whole]]
                      (if (= x :s) false true))
                 eqs2)

        [[_ mark-y-grid] & eqs-svg]
        (map (fn [[x & rest :as whole]]
                       (if (= x :s) true false))
                 eqs2)

        mark-x-grid
        (map
         (fn [a]
           [:g
            [:circle {:cx (xg [a 0])
                      :cy (y 0)
                      :r 1}]
            [:text {:x (xg [a 0])
                    :y (y -5)
                    :style {:font-size ".3rem"}
                    } a]
            ]
           )


         (range (/ (- xc-max xc-min) (* 1 step))))
        ]
    [:div
     {:style
      {
       :width "100vw"
       :height "100vh"
       :display :grid
       :grid-template-columns
       (apply str (repeat
                   (/ yc-max step)
                   (str 11   "vh ")))
       :grid-template-rows
       (apply str (repeat
                   (/ xc-max step)
                   (str 11   "vh "))) }}

     (map
      (fn [[[x y] [spx spy] style & rest ]]
        [:div {:style
               (into style {:z-index 2
                            :grid-row (str x "/ span " spx)
                            :grid-column (str y "/ span " spy)})}

         (map (fn [e]
                e)  rest)])


      eqs)


     [:svg {:viewBox (reduce
                            (fn [acc b]
                              (str acc " " b)
                              )
                            ""
                            canvas)
                 :style
                 {:height "100vh"
                  :width "100vw"
                  :z-index 1
                  :grid-column "1/-1"
                  :grid-row "1/-1"
                  :background-color
                   (c [70 80 85])}}
            [:defs
             [:marker {:id "i"
                       :refY 0
                       :refX 0
                       :orient :auto
                       :style {:overflow :visible}}
              [:path {:d "M 0 0 L 5 -5 L -12.5 0 L 5 5 L 0 0 z"
                      :style {:fill-rule :evenodd
                              :stroke (c [70 70 70])
                              :stroke-width 1
                              :stroke-opacity 1
                              :fill (c [300 70 70])
                              :fill-opacity 1}
                      :transform "scale(.2) rotate(180) translate(12.5,0)"
                      }]]]


      (comment [mark-y-grid [xg yg] [sx sy] [step _] ])
      mark-x-grid





            (map
             (fn [a]
               [:g
                [:path {:stroke (c [180 70 70])
                        :stroke-width .8
                        :d (str "M" -10  " "
                                (yg [a 0])
                                " l " (- x2 x1)  " 0")}]

                [:path {:stroke (c [90 70 70])
                        :stroke-width .8
                        :d (str "M" -10  " "
                                (yg [a 1])
                                " l " (- x2 x1)  " 0")}]

                [:path {:stroke (c [90 70 70])
                        :stroke-width .8
                        :d (str "M" -10  " "
                                (yg [a 2])
                                " l " (- x2 x1)  " 0"
                                )}]

                [:path {:stroke (c [90 70 70])
                        :stroke-width .8
                        :d (str "M" -10  " "
                                (yg [a 3])
                                " l " (- x2 x1)  " 0"
                                )}]
                [:path {:stroke (c [90 70 70])
                        :stroke-width .8
                        :d (str "M" -10  " "
                                (yg [a 4])
                                " l " (- x2 x1)  " 0"
                                )}]])
             (range (* (- yc-max yc-min) -1)
                    (- yc-max yc-min)))

            (map
             (fn [x]
               [:g
                [:path {:stroke (c [10 70 70])
                        :stroke-width .8
                        :d (str "M" (xg [x 0])
                                " " y2
                                " l 0 " (- y1 y2))}]



                ] )

             (range
              (/ (- x2 x1) (* 2 step -1))
              (/ (- x2 x1) (* 2 step))))
            [:path {:stroke (c [370 70 70])
                    :stroke-width 20
                    :fill :none
                    :stroke-opacity .5

                    :d (str
                        "M " (+ (* 5 30) (* 6 0))
                        " " (y (+ (* 0 30)
                                  (* 6 0)))
                        " L " (+ (* 12 30) (* 6 0))
                        " " (y (+ (* 0 30)
                                  (* 6 0)))

                        " L " (+ (* 12 30) (* 6 0))

                        " " (y (+ (* 5 30)
                                  (* 6 0))

                               )
                        " L " (+ (* 5 30) (* 6 0))
                        " " (y (+ (* 5 30)
                                  (* 6 0)))

                        " z"

                        )}]



            (comment
              a)
            (let [yy 4]
              [:g
               [:path {:stroke (c [10 40 40])
                       :stroke-width 2
                       :fill :none
                       :stroke-opacity .5
                       :marker-end "url(#i)"
                       :d (str
                           "M " (+ (* 4 30) (* 6 3.2))
                           " " (y (+ (* yy 30)
                                     (* 6 2)))
                           " l " (+ (* 7 30) (* 6 3))
                           " " 0)}]
               [:text {:style {:font-size ".8rem"}
                       :x (+ (* 7 30) (* 6 3.2))
                       :y (y (+ (* yy 30)
                                (* 6 0)))}
                "a"]])



            (comment 50)
            [:path {:stroke (c [50 40 40])
                    :stroke-width 2
                    :fill :none
                    :stroke-opacity .5
                    :marker-end "url(#i)"
                    :d (str
                        "M " (+ (* 9 30) (* 6 3.2))
                        " " (y (+ (* 5 30)
                                  (* 6 1.8)))
                        " l " 0
                        " " (+ (* 5 30) (* 6 3)))}]

            (comment x udown)
            [:path {:stroke (c [80 40 40])
                    :stroke-width 2
                    :fill :none
                    :stroke-opacity .5
                    :marker-end "url(#i)"
                    :d (str
                        "M " (+ (* 9 30) (* 6 3.2))
                        " " (y (+ (* 5 30)
                                  (* 6 1.8)))
                        " l " 0
                        " " (+ (* 0 301) (* 6 3))

                        )}]

            (comment 70)

            (let [yy 5]
              [:g

               [:path {:stroke (c [90 40 40])
                       :stroke-width 20
                       :fill :none
                       :stroke-opacity .5

                       :d (str
                           "M " (+ (* 5 30) (* 6 1.8))
                           " " (y (+ (* yy 30)
                                     (* 6 0)))
                           " l " (+ (* 6 30) (* 6 1))
                           " " 0
                           )}]
               [:path {:stroke (c [90 40 40])
                       :stroke-width 20
                       :fill :none
                       :stroke-opacity .5

                       :d (str
                           "M " (+ (* 5 30) (* 6 1.8))
                           " " (y (+ (* 0 30)
                                     (* 6 0)))
                           " l " (+ (* 6 30) (* 6 1))
                           " " 0
                           )}]
               [:text {:x (xg [8 1.8])
                       :y (yg [yy 1])} "70"]])

            [:path {:stroke (c [70 40 40])
                    :stroke-width 1
                    :fill :none
                    :stroke-opacity .5
                    :marker-end "url(#i)"
                    :d (str
                        "M " (+ (* 11 30) (* 6 3.2))
                        " " (y (+ (* 4 30)
                                  (* 6 0)))
                        " l " (+ (* 0 30) (* 6 3))
                        " " 0



                        )}]
            (comment x)

            (let [yy 2]
              [:g
               [:path {:stroke (c [140 70 70])
                       :stroke-width 2
                       :fill :none
                       :stroke-opacity 1
                       :marker-end "url(#i)"
                       :d (str
                           "M " (+ (* 11 30) (* 6 3.2))
                           " " (y (+ (* yy 30)
                                     (* 6 0)))
                           " l " (+ (* 0 30) (* 6 4))
                           " " 0



                           )}]

               [:path {:stroke (c [140 70 70])
                       :stroke-width 2
                       :fill :none
                       :stroke-opacity 1
                       :marker-end "url(#i)"
                       :d (str
                           "M " (+ (* 4 30) (* 6 3.2))
                           " " (y (+ (* yy 30)
                                     (* 6 0)))
                           " l " (+ (* 0 30) (* 6 4))
                           " " 0



                           )}]

               [:text {:style {:font-size ".5rem"}
                       :x (+ (* 4 30) (* 6 4.5))
                       :y (yg [yy 0])
                       } "x"]


               [:text {:style {:font-size ".5rem"}
                       :x (+ (* 11 30) (* 6 4.5))
                       :y (yg [yy 0])
                       } "x"]



               ])

            [:path {:stroke (c [300 70 70])
                    :stroke-width 2
                    :fill (c [300 70 70])
                    :stroke-opacity 1
                    :d (str
                        "M " (+ (* 4 30) (* 6 3.5))
                        " " (y (+ (* 0 30)
                                  (* 6 1.6 -1)))
                        " l " (+ (* 0 30) (* 6 3))
                        " " 0
                        " l " 0
                        " " (+ (* 0 30) (* 6 3 -1))
                        " l " (+ (* 0 30) (* 6 3 -1))
                        "  " 0
                        " z"



                        )}]


            [:path {:stroke (c [300 70 70])
                    :stroke-width 2
                    :fill (c [300 70 70])
                    :stroke-opacity 1
                    :d (str
                        "M " (xg [4 3.4])
                        " " (yg [4 3.3])
                        " l " (+ (* 0 30) (* 6 3))
                        " " 0
                        " l " 0
                        " " (+ (* 0 30) (* 6 3 -1))
                        " l " (+ (* 0 30) (* 6 3 -1))
                        "  " 0
                        " z")}]


            [:path {:stroke (c [300 70 70])
                    :stroke-width 2
                    :fill (c [300 70 70])
                    :stroke-opacity 1
                    :d (str
                        "M " (xg [11 3.4])
                        " " (yg [4 3.3])
                        " l " (+ (* 0 30) (* 6 3))
                        " " 0
                        " l " 0
                        " " (+ (* 0 30) (* 6 3 -1))
                        " l " (+ (* 0 30) (* 6 3 -1))
                        "  " 0
                        " z")}]

            (comment [:path {:stroke (c [300 70 70])
                             :stroke-width 1
                             :fill :none
                             :stroke-opacity 1
                             :d (str
                                 "M " (xg [-5 0])
                                 " " (yg [15 -2.1])
                                 " l " (+ (* 1 30) (* 6 0))
                                 " " (+ (* 1 30) (* 6 3))
                                 " l " (* (+ (* 1 30) (* 6 0)) -1)
                                 " " (+ (* 1 30) (* 6 3))
                                 )}])


            [:path {:stroke
                    (c [300 70 70])
                    :stroke-width 3
                    :fill :none
                    :stroke-opacity 1
                    :d (str
                        "M " (xg [-5 0])
                        " " (yg [15 -2.1])
                        " q " (sx [0 3])
                        " " (sy [0 4])
                        " " (sx [0 0])
                        " " (sy [0 8 ])
                        )}]

            [:path {:stroke
                    (c [300 70 70])
                    :stroke-width 3
                    :fill :none
                    :stroke-opacity 1
                    :d (str
                        "M " (xg [-3 0])
                        " " (yg [15 -2.1])
                        " q " (sx [0 -3])
                        " " (sy [0 4])
                        " " (sx [0 0])
                        " " (sy [0 8 ])
                        " l " (sx [-2 0])
                        " " (sy [0 0])
                        " l " (sx [0 0])
                        "  " (sy [1 3])
                        " l " (sx [2 0])
                        " " (sy [ 0 0])
                        )}]



            [:text {:x (xg [-5 -2])
                    :y (yg [14 -2.1])}
             "4"]

            [:text {:x (xg [-4 -2])
                    :y (yg [14 0])}
             "93"]

            [:text {:x (xg [-4 -2])
                    :y (yg [13 1])}
             "80"]

            [:text {:x (xg [-4 -2])
                    :y (yg [12 2])}
             "13"]

            [:text {:x (xg [-4 -2])
                    :y (yg [11 3])}
             "12"]

            [:text {:x (xg [-4 0])
                    :y (yg [10 3])}
             "1"]

            [:text {:x (xg [-3 0])
                    :y (yg [14 -2.1])}
             "20 + 3"]



            [:path {:stroke (c [300 70 70])
                    :stroke-width 2
                    :fill (c [300 70 70])
                    :stroke-opacity 1
                    :d (str
                        "M " (xg [11 3.4])
                        " " (yg [0 -2.1])
                        " l " (sx [0 3])
                        " " (sy [0 0])
                        " l " 0
                        " " (+ (* 0 30) (* 6 3 -1))
                        " l " (+ (* 0 30) (* 6 3 -1))
                        "  " 0
                        " z")}]


            [:path {:stroke (c [300 70 70])
                    :stroke-width 2
                    :fill (c [300 70 70])
                    :stroke-opacity 1
                    :d (str
                        "M " (xg [11 3.4])
                        " " (yg [0 -2.1])
                        " l " (+ (* 0 30) (* 6 3))
                        " " 0
                        " l " 0
                        " " (+ (* 0 30) (* 6 3 -1))
                        " l " (+ (* 0 30) (* 6 3 -1))
                        "  " 0
                        " z")}]]

     ]))










(defn exercise-177 []
  [container "177" "1.4"
   [
    [grid-svg [(fn [xp] (+ xp  0))
               (fn [yc]
                 (- (* (- 400 yc) 1) 200))]
      [[0 0] [400 600]]
      [[-10 -10] [20 20]] 30
     [
      [:div [m '(= xc (:b (+ (:m 30 x) 200)))]]
      [:div {:style {:background-color (c [70 70 70])}}
       [m '(= yc (- (:b (- 400 y)) 200))]]
      [:div [m '(= yc-min 0)]]
      [:div [m '(= xc-min 0)]]
      [:div [m '(= xc-max 400)]]
      [:div [m '(= yc-max 400)]]
      [:div [m '(= step 30)]]
      [:div [m '(= (* 7 step) 210)]]
      [:div {:style {:background-color (c [70 70 70])}}
        [m '(= 0
               (- (:b (- 400 y)) 200))]]
      [:div [m '(= v
                   (d
                    t))]]
      [:div {:style {:background-color (c [70 70 70])
                     :font-size "2rem"}}
       [m '(= va
                   (1.6
                    3))]]
      [:div {:style {:background-color (c [70 70 70])
                     :font-size "2rem"}}
       [m '(= vb
                   0)]]

      [:div {:style {:background-color (c [10 70 70])
                     :font-size "2rem"}}
       [:div
        [m '(= vc
               (:m ((- 4.4 1.6) (- 7 5))

                   (km min))

               )]]

       [:div
        [m '(= vc
               (:m ((* 2.8 1000)
                    (* 2 60))

                   (m s))
               )]]]

      [:div {:style {:background-color (c [120 70 70])
                     :font-size "2rem"}}
       [m '(= vd
                   0)]]
      [:div {:style  {:background-color (c [150 70 70])
              :font-size "2rem"}}
       [m '(= ve
                   ((- 4.4
                       5) (- 11.6 8)))]]

      [:div {:background-color (c [180 70 70])
             :font-size "2rem"}
       [m '(= vf
                   0)]]

      [:div {:background-color (c [70 70 70])
             :font-size "2rem"}
       [m '(= vg
                   ((- 6
                       5)
                    (- 12.6 8)))]]


      ]]
    ]])


(defn exercise-178 []
  [container "178" "1.4"
   [

    (let [[w h] [420 210]
          step 30]
      [grid-svg3
       [
        [(fn [xp]
           (+ xp 0))
         (fn [yc]
           (-
            (*
             (- 400 yc) 1) 200))]
        [w h]
        [step step]]
       [
        [:s
         (fn [[x y] [sx sy] [stepx _] ]
           (map
            (fn [i]
              [:circle {:cx (x [0 0])
                        :cy (y [i 0])
                        :r 1}])

            (into (range 0 5)
                  (range -1 -4))

            ) )]


        [[7 4] [1 4]
         {:background-color (c [10 70 70])
          }
         (m '(= (+ (:m 4
                       (:p x 2))
                   (:m 70 x)
                   (:m 70 x)
                   (:m 50 x)
                   (:m 50 x)
                   )
                1024)

            )
         (m '(= (+ (:m 4
                       (:p x 2))
                   (:m 70 x)
                   (:m 70 x)
                   (:m 50 x)
                   (:m 50 x)
                   )
                1024)

            )
         ]

        [[1 2] [1 1]
         {:background-color (c [30 70 70])}
         (m '(:p x 2))]
        ]])]])

(comment
  (let [transform-s
        (comp
         (fn [[x y]] (+ x y))
         (juxt (comp (fn [x] (* x 30))  first)
               (comp (fn [x] (* x 6) ) second)))

        transform-x (comp
                     (fn [x] (+ 200 x))
                     transform-s
                     :x)


        transform-y

        (comp
         (fn [y] (- 200 y))
         transform-s
         :y)
        cir (fun [{:point {:x x
                       :y y}
               :r r}]

             (let [[cx cy r] (juxt
                              (comp space transform-x)
                              (comp space transform-y)
                              (comp space transform-s))]
               ))
        ]
    (map

     (comp
      (fn [[x y r]]
        [:circle {:cx x
                  :cy y
                  :r r}])
      (juxt (comp first first)
            (comp second first)
            second)
      (juxt
       (comp
        (juxt transform-x transform-y)
        :point)
       (comp transform-s
             :r))
      #(s/conform ::gcircle %))
     (gen/sample (s/gen ::gcircle 10)))))

(comment (let [{:keys [m curve]}
               (s/conform
                ::path
                [3 4
                 :L 2 3 3 5 5 2
                 :c 4 3 4 5 3 4 :s 5 2 3 4
                 :c 4 5 6 2 2 3
                 :L 2 3 3 5
                 :q 23 23 24 53 :t 23 23
                 :l 3 5 5 6
                 :q 23 23 24 53
                 :A 24 20 25 0 0 200 200])]
           (map (fn [[x & r]]
                  (:points (first r)))
                curve)))

(comment (let [ t (fn [[x dx]]
                   (+ (* x 30)
                      (* dx 6)))
               txy (juxt
                    (comp
                     (fn [x]
                       (+ 200 x)) t first)
                    (comp (fn [y]
                            (+ y 200)) t second)

                    (comp t (fn [a] (nth  a 2))))]
           (map
            (fn [d1]
              ((fun [{:point {:x x
                              :y y}
                      :r r}]

                    (let [[cx cy r] (txy [x y r])]
                      [:circle {:fill (c [(rand-int 70)
                                          (+ 40 (rand-int 20))

                                          (+ 40 (rand-int 40))])
                                :cx cx
                                :cy cy
                                :r r}]))
               (s/conform ::gcircle d1)))

            sam)))




(defn grid2 [[w h s]]
  [:div {:style
         {:background-color (c [10 70 70])
          :display :grid
          :height (size [100 :vh])
          :width (size [100 :vw])
          :grid-template-columns (size
                                  (into []
                                        (apply concat
                                               (take 13 (repeat [10 :vh])))) )

          :grid-template-rows
          (size (into []
                      (apply concat
                             (take 10 (repeat [10 :vh])))))
          }}




   [:div {
          :style
          {:color :#444
           :grid-row (str/join "/" [2 -2])
           :grid-column (str/join "/" [2 -2])
           :background-color (c [70 70 70])
           }}

    (comment (comp
              (fn [x] (+ 200 x))
              (fn [[x y]] (+ x y))
              (juxt (comp (fn [x] (* x 30))  first)
                    (comp (fn [x] (* x 6) ) second))
              :x))

    (let [
          transform-s
          (comp
           (fn [[x y]] (+ x y))
           (juxt (comp (fn [x] (* x 30))  first)
                 (comp (fn [x] (* x 6) ) second)))

          transform-x (comp
                       (fn [x] (+ 200 x))
                       transform-s
                       :x)


          transform-y
          (comp
           (fn [y] (- 200 y))
           transform-s
           :y)

          transform-points
          (juxt
           transform-x
           transform-y
           )
          space (fn [p] (str/join " " p))

          sam [
               [[2 3] [-1 4] [2 3]]

               [[-2 -2] [-1 4] [2 0]]

               [[8 -2] [-1 4] [2 0]]
               [[2 3] [-2 -4] [2 3]]
               [[2 3] [-5 -4] [2 3]]
               [[12 -2] [-1 4] [2 0]]
               [[2 3] [-8 -4] [2 3]]
               ]]

      [:svg {:style {:background-color (c [150 70 70])
                     :height "100%"
                     :width "100%"}
             :viewBox (str/join [0 0 w h])}

       (comment (let [t (path (comp space transform-points))]
                  (map
                   (comp
                    (fn [d]
                      [:path {:d d
                              :fill :none
                              :stroke-width 1
                              :stroke :#111}])
                    t
                    (fn [l]
                      (s/conform ::gpath l)))

                   (gen/sample
                    (s/gen ::gpath 10)))))

       (let [t (path (comp space transform-points))]
         (map
          (comp
           (fn [d]
             [:path {:d d
                     :fill :none
                     :stroke-width 1
                     :stroke :#111}])
           t
           (fn [l]
             (s/conform ::gtpath l)))

          (gen/sample
           (s/gen ::gtpath 10))))

       (comment cirlce)
       (map

        (comp
         (fn [[x y r]]
           [:circle {:cx x
                     :cy y
                     :r r}])
         (juxt (comp first first)
               (comp second first)
               second)
         (juxt
          (comp
           (juxt transform-x transform-y)
           :point)
          (comp transform-s
                :r))
         #(s/conform ::gbubble %))
        (gen/sample (s/gen ::gbubble 30)))

       (map

        (comp
         (fn [[x y r]]
           [:circle {:cx x
                     :cy y
                     :r r}])
         (juxt (comp first first)
               (comp second first)
               second)
         (juxt
          (comp
           (juxt transform-x transform-y)
           :point)
          (comp transform-s
                :r))
         (fn [x]
           (s/conform ::gcircle x)))
        sam)])]])





(defn template1 []
  [grid2 [420 420 30]])
