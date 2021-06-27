(ns math2.math7
  (:require
   [react]
   [clojure.string :as str]
   [clojure.spec.test.alpha :as stest]
   [clojure.test.check.generators :as gen2]
   [clojure.spec.gen.alpha :as gen]
   [clojure.spec.alpha :as s]
   [math2.file :as file]
   [defun.core :refer [defun fun]]
   [moment]))


;; 01741775277 ethan mom maliha
;; 01716980473  angira mom maliha



(s/def ::op-plus '#{+})
(s/def ::op-equal '#{=})

(s/def ::op-mul '#{*})
(s/def ::op-mul2 #{:m})
(s/def ::op-dev '#{/})
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
              :op-mul2 ::op-mul2
              :÷ ::op-dev))



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



(s/def :math7/point
  (s/cat
   :x number?
   :y number?))




(s/def :math7/path
  (s/cat
   :m :math7/point
   :d (s/+
       (s/alt
        :line (s/cat
               :l #{:l :L}
               :points (s/+ :math7/point))
        :curve-c (s/cat
                  :c #{:c :C}
                  :control-point1 :math7/point
                  :control-point2 :math7/point
                  :end-point :math7/point)
        :curve-q (s/cat
                  :q #{:q :Q}
                  :control-point :math7/point
                  :end-point :math7/point)

        :arc (s/cat
              :a #{:a :A}
              :r1 number?
              :r2 number?
              :angle number?
              :f1 boolean?
              :f2 boolean?
              :end :math7/point)))))



(comment
  (s/cat
   )
  (gen2/sample (gen2/choose 5  12)))

(defn conjj [i]
  (fn [arr]
    (conj arr i)))
(def ve (fn [x] (* x -1)))
(def m-add (fn [m] (str "M " m)))

(def f-add (fn [m]
             (fn [st]
               (str (name m) st))))

(def l-add (fn [m]
             (fn [st]
               (str st (name m) ))))

(def sec (l-add :s))
(def rel #{:l :c :a :q})
(def abs #{:L :C :A :Q})
(def space (fn [p] (str/join " " p)))

(def sami-colon (fn [p] (str/join " ; " p)))
(def coma (fn [p] (str/join " , " p)))
(def not-space (fn [p] (str/join "" p)))

(defn wrap [a]
  (str "(" a  ")"))
(defn wrap' [a]
  (str "'" a  "'"))

(defn wrap-sami-colon [a]
  (str "\"" a "\""))

(defn url [i]
  (str "url" "(#" i  ")" ))

(defn fs [fnts]
  (let [wrap (fn [a]
               (str "'" a  "'"))]
    {:font-family "'Amstelvar VF'"
     :font-variation-settings
     (coma
      (map (fn [x y]
             (str x " " y))
           (map wrap ["wdth" "wght" "CNTR"  "opsz" "PWGT"
                      "PWDT" "GRAD" "XOPQ" "XTRA" "YOPQ"])
           fnts))}))

(comment



  (wrap "hello")
  (space [2 3 3]))

(def path
  (comp
   m-add
   space
   (partial mapcat identity)
   (juxt (comp
          (juxt :x :y)
          :m)
         (comp
          (partial
           mapcat
           (fun
            ([[:line {:l :l :points p}]]
             (cons (name :l)  (mapcat (juxt :x :y)   p)))
            ([[:line {:l :L :points p}]]
             (cons (name :L) (mapcat (juxt :x :y) p)))
            ([[:curve-c {:c :c
                         :control-point1 control-point1
                         :control-point2 control-point2
                         :end-point end-point}]]
             (cons (name :c)
                   (mapcat (juxt :x :y)  [control-point1 control-point2 end-point])))
            ([[:curve-c {:c :C
                         :control-point1 control-point1
                         :control-point2 control-point2
                         :end-point end-point}]]
             (cons (name :C)
                   (mapcat (juxt :x :y)  [control-point1 control-point2 end-point])))

            ([[:curve-q
               {:q :q :control-point control-point :end-point end-point}]]
             (cons (name :q) (mapcat (juxt :x :y)
                                     [control-point end-point])))
            ([[:curve-q
               {:q :Q :control-point control-point :end-point end-point}]]
             (cons (name :Q) (mapcat (juxt :x :y)
                                     [control-point end-point])))
            ([[:arc {:a :a :r1 r1 :r2 r2 :angle angle :f1 f1 :f2 f2 :end end}]]
             [(name :a)  r1 r2  angle (if f1 1 0) (if f2 1 0) (:x end) (:y end)])
            ([[:arc {:A :a :r1 r1 :r2 r2 :angle angle :f1 f1 :f2 f2 :end end}]]
             [(name :A)  r1 r2  angle (if f1 1 0) (if f2 1 0) (:x end) (:y end)])))
          :d))
   (partial s/conform :math7/path)))



(defn path2 [[fx fy]]
  (comp
   m-add
   space
   (partial mapcat identity)
   (juxt (comp
          (juxt
           (comp fx :x) (comp fy :y))
          :m)
         (comp
          (partial
           mapcat
           (fun
            ([[:line {:l :l :points p}]]
             (cons (name :l)
                   (mapcat (juxt (comp fx :x) (comp fy :y))   p)))
            ([[:line {:l :L :points p}]]
             (cons (name :L) (mapcat (juxt (comp fx :x) (comp fy :y)) p)))
            ([[:curve-c {:c :c
                         :control-point1 control-point1
                         :control-point2 control-point2
                         :end-point end-point}]]
             (cons (name :c)
                   (mapcat (juxt (comp fx  :x)
                                 (comp fy :y))
                            [control-point1 control-point2 end-point])))
            ([[:curve-c {:c :C
                         :control-point1 control-point1
                         :control-point2 control-point2
                         :end-point end-point}]]
             (cons (name :C)
                   (mapcat (juxt (comp fx :x)
                                 (comp fy :y))
                           [control-point1 control-point2 end-point])))

            ([[:curve-q
               {:q :q :control-point control-point :end-point end-point}]]
             (cons (name :q) (mapcat (juxt
                                      (comp fx :x) (comp fy :y))
                                     [control-point end-point])))
            ([[:curve-q
               {:q :Q :control-point control-point :end-point end-point}]]
             (cons (name :Q) (mapcat (juxt (comp  fx :x) (comp fy :y))
                                     [control-point end-point])))
            ([[:arc {:a :a :r1 r1 :r2 r2 :angle angle :f1 f1 :f2 f2 :end end}]]
             [(name :a)  r1 r2  angle (if f1 1 0) (if f2 1 0) (fx (:x end)) (fy (:y end))])
            ([[:arc {:A :a :r1 r1 :r2 r2 :angle angle :f1 f1 :f2 f2 :end end}]]
             [(name :A)  r1 r2  angle (if f1 1 0) (if f2 1 0) (fx (:x end)) (fy (:y end))])))
          :d))
   (partial s/conform :math7/path)))

(comment (interpose :l [1 2 3]))
(comment
  (path
   [1 3 :l 3 4 :L 3 5 6 3 6 3
    :c 23 23 2 2 3 2
    :a 2 3 3 true false 3 2
    :Q 3 2 -3 0
    :C 3 5 2 3 5 2
    :q 3 5 2 3]))

(defn persent? [s]
  (and (< s 100.001) (> s -0.001)))

(defn frac? [s]
  (and (< s 1.001) (> s -0.001)))

(s/def :math7/persent
  (s/and number? persent?))

(s/def :math7/hsl
  (s/cat
   :hue number?
   :saturation :math7/persent
   :lighness :math7/persent
   :opacity (s/and number? frac?)))


(comment
  (s/valid? :math7/hsl [1 0 100 1]))

(s/def :math7/size
  (s/cat
   :size number?
   :scale #{:rem :px :vh :vw :%}))

(s/def :math7/linear-gradient
  (s/cat
   :angle number?
   :colors
   (s/+ (s/cat
         :size :math7/size
         :color :math7/hsl))))


(s/def :math7/flex
  (s/cat
   :justify-content #{:center :space-between :space-around :flex-start :flex-end}
   :align-items #{:center :space-between :space-around :flex-start :flex-end}
   :font-size :math7/size
   :flex-direction (s/? #{:row :column})))

(comment (s/conform :math7/flex [:center :center 1 :rem]))



(s/def :math7/span
  (s/cat
   :grid-row (s/cat :row int? :span int?)
   :grid-column (s/cat :row int? :span int?)
   :flex :math7/flex
   ))

(comment
  (s/conform :math7/span [1 2 3 4 :center :center 1 :rem :row]))

(comment
  (s/def :math7/cell
    (s/cat
     :span (s/spec :math7/span)
     :color (s/spec :math7/hsl)
     :linear-gradient (s/? (s/spec :math7/linear-gradient)))))


(def hsla
  (comp
   (f-add :hsla)
   wrap
   not-space
   (partial interpose ",")
   (juxt (comp
          (fn [n] (str n "rad"))
          :hue)
         (comp
          (fn [n] (str n "%"))
          :saturation)
         (comp
          (fn [n] (str n "%"))
          :lighness) :opacity)))

(defn hsl [a]
  ((comp hsla
         (partial s/conform :math7/hsl)) a))

(def size
  (comp
   not-space
   (juxt :size (comp name :scale))))




(def linear-gradient
  (comp
   (comp
    (f-add :linear-gradient)
    wrap
    not-space
    (partial interpose ",")
    (juxt (comp
           not-space
           (conjj (name :rad))
           vector
           :angle)
          (comp
           not-space
           (partial interpose ",")
           (partial map (comp
                         space
                         (juxt
                          (comp
                           hsla
                           :color)
                          (comp
                           size
                           :size))))
           :colors)))))



(defn span [[row rspan]]
  (str/join "/"
            [row (+ rspan row)]))




(defn css [[sp color gradient & other]]
  (let [cell
        (comp
         (fn [n] (update n :font-size size))
         (partial into {:display :flex})
         (juxt
          (comp
           (partial vector :grid-row)
           span
           (juxt :row :span)
           :grid-row)
          (comp
           (partial vector :grid-column)
           span
           (juxt :row :span)
           :grid-column)
          :flex))
        g (if (> (count gradient) 0)
            {:background-image
             (linear-gradient
              (s/conform :math7/linear-gradient gradient))}
            {})
        color (if (> (count color) 0)
                {:background-color (hsl color)}
                {})
        ]
    (comment (merge (cell span) g color (partial )))
    (merge
     (cell (s/conform :math7/span sp))
     color
     g
     (apply merge other)
     )))

()

(comment

  (comment
    (s/conform :math7/cell [[1 2 3 4 1 :rem]
                            [1 70 70 1]
                            [2
                             1 :rem  1 70 70 1
                             2 :%  1 70 70 1
                             3 :%  1 70 70 1]])
    (s/valid? :math7/linear-gradient [2
                                      1 :rem  1 70 70 1
                                      2 :%  1 70 70 1
                                      3 :%  1 70 70 1]))
  (s/valid? :math7/cell
            [
             [1 70 70 1]
             [2
              1 :rem  1 70 70 1
              2 :%  1 70 70 1
              3 :%  1 70 70 1]])
  (partial into {})

  (partial vector :font-size)


  (let [v ]
    )
  (css
   [[1 2 3 4 :center :center 1 :rem ]
    [1 70 70 1]
    []])
  ((comp
    (fn [n] (update n :font-size
                    (comp
                     not-space
                     (juxt :size (comp name :scale)))))
    (partial into {:display :flex})
    (juxt
     (comp
      (partial vector :grid-row)
      span
      (juxt :row :span)
      :grid-row)
     (comp
      (partial vector :grid-column)
      span
      (juxt :row :span)
      :grid-column)
     :flex
     )
    )
   (s/conform :math7/span [1 2 3 4 :center :center 1 :rem :row]))
  (css [[1 2 3 4 :center :center 1 :rem :row]
        [1 70 70 1]
        [2
         1 :rem  1 70 70 1
         2 :%  1 70 70 1
         3 :%  1 70 70 1]
        {}])


  (linear-gradient
   (s/conform :math7/linear-gradient [2
                                      1 :rem  1 70 70 1
                                      2 :%  1 70 70 1
                                      3 :%  1 70 70 1]))

  )


(s/def :math7/grid
  (s/cat
   :height  :math7/size
   :width :math7/size
   :grid-template-columns (s/spec
                           (s/+
                            (s/spec :math7/size)))
   :grid-template-rows (s/spec
                        (s/+
                         (s/spec :math7/size)))
   ))


(def grid
  (comp
   (partial into {:display :grid})
   (juxt (comp
          (partial vector :height)
          size
          :height)
         (comp
          (partial vector :width)
          size
          :width)
         (comp
          (partial vector :grid-template-columns)
          space
          (partial map size)
          :grid-template-columns)
         (comp
          (partial vector :grid-template-rows)
          space
          (partial map size)
          :grid-template-rows))
   (partial s/conform :math7/grid)))

(comment
  (s/valid? :math7/grid
            [100 :vh 100 :vw
             (take 10 (repeat [10 :vh]))
             (take 14 (repeat [10 :vh]))])

  (
   [100 :vh 100 :vw
    (take 10 (repeat [10 :vh]))
    (take 14 (repeat [10 :vh]))]))


(comment
  (def grid22 {:background-color (c [90 70 70])
               :display :grid
               :height [100 :vh]
               :width [100 :vw]
               :grid-template-columns (take 12 (repeat [10 :vh]))
               :grid-template-rows (take 10 (repeat [10 :vh]))}))

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
