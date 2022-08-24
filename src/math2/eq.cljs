(ns math2.eq
  (:require
   [clojure.string :as str]
   [clojure.spec.test.alpha :as stest]
   [clojure.test.check.generators :as gen2]
   [clojure.spec.gen.alpha :as gen]
   [clojure.spec.alpha :as s]
   [defun.core :refer [defun fun]]))



(s/def ::op-plus '#{+})


(s/def ::op-arrow #{:a})


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
              :→ ::op-arrow
              :+ ::op-plus
              :> '#{>}
              :< '#{<}
              :<= '#{<=}
              :>= '#{>=}
              :!= '#{!=}
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
  (if (= mo :b)
    [:mrow [:mo "("]
     (expr elem)
     [:mo ")"]]
    [:mrow [:mo "["]
     (expr elem)
     [:mo "]"]]))



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
  (if (= (first e) :expr)
      (let [expr-f (juxt
                    first
                    (comp  first second)
                    (comp  second second))
            [_ t-expr expr-1] (expr-f e)]
        (( {:p-exp p-exp
            :m-exp m-exp
            :b-exp b-exp
            :f-exp f-exp
            :e-exp e-exp} t-expr)
         expr-1))
      e))


(defn mx [eq]
  [:math
   (expr
    (s/conform ::element
               (vec (clojure.walk/postwalk
                     (fn [x]
                       (if (symbol? x)
                         (symbol (name x))
                         x))
                     eq))))])

(defn x [eq]
  [:math
   (expr
    (s/conform ::element
               (let [r (vec (clojure.walk/postwalk
                             (fn [x]
                               (if (symbol? x)
                                 (symbol (name x))
                                 x))
                             eq))]
                 (if (s/valid? ::element r)
                   r
                   0)
                 )))])
(defn eq2 [eq]
  (vec (clojure.walk/postwalk
        (fn [x]
          (if (symbol? x)
            (symbol (name x))
            x))
        eq)))


(defn sx2 [eq x2 tt]
  [:math
   (expr
    (s/conform ::element
               (let [r (vec (clojure.walk/postwalk
                             (fn [x]
                               (if (symbol? x)
                                 (let [x1 (symbol (name x))]
                                   (if (= x1 x2)
                                     tt
                                     x1))
                                 x))
                             eq))
                     ]
                 (if (s/valid? ::element r)
                   r
                   0)
                 )))])


(defn sx [eq x2 tt]
  [:math
   (expr
    (s/conform ::element
               (let [r (vec (clojure.walk/postwalk
                             (fn [x]
                               (if (= x x2)
                                 tt
                                 x))
                             eq))
                     ]
                 (if (s/valid? ::element r)
                   r
                   0)
                 )))])
