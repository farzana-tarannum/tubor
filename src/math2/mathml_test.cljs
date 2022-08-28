(ns math2.mathml-test
  (:require
   [defun.core :refer [defun fun]]
   [clojure.walk :as w]
   [math2.mathml :as ml :refer [expr x mx eq2 sx]]
   [clojure.spec.test.alpha :as stest]
   [clojure.test.check.generators :as gen2]
   [clojure.spec.gen.alpha :as gen]
   [clojure.spec.alpha :as s]
   [clojure.test :as test :refer [deftest is testing]]
   ))


(defn e [ex]
  (expr
   (s/conform :math2.mathml/element ex)))


(testing "element"
  (is (= [:mn 1] (s/conform :math2.mathml/element 1) ))
  (is (=  (s/conform :math2.mathml/element 'x) '[:mi x]))
  (is
   (=
    (s/conform :math2.mathml/element '(= 3 (+ 2 x 4)))
    '[:expr [:p-exp {:mo [:= =], :first-elem [:mn 3],
                     :elem [[:expr
                             [:p-exp {:mo [:+ +],
                                      :first-elem [:mn 2],
                                      :elem [[:mi x] [:mn 4]]}]]]}]]))
  )



(testing "expr"
  (is (= (ml/expr (s/conform :math2.mathml/element '(1 2)))))
  (is (= (ml/expr
          (s/conform :math2.mathml/element '(:s x 2)))))

  (is (= (expr (s/conform :math2.mathml/element '(:m 2 x)))
         [:mrow [:mn 2] [:mi "x"]]))
  (is (= (expr (s/conform :math2.mathml/element [:m 2 'x]))
         [:mrow [:mn 2] [:mi "x"]]
         ))
  )

;;  (expr (s/conform :math2.mathml/element
;;                   '(+ :alpha x (- 5 2 4) ((- x ) 1))))

;; [:mrow [:betas [:α :alpha]] [:mo "+"] [:mi "x"] [:mo "+"] [:mrow [:mn 5] [:mo "-"] [:mn "2"] [:mo "-"] [:mn "4"]] [:mo "+"] [:mrow [:mfrac [:mrow [:mo "-"] [:mi x]] [:mn 1]]]]


;; (s/conform :math2.mathml/element
;;            '(= (- 2) (:c (:p x 3))))

;; [:expr [:p-exp {:mo [:= =], :first-elem [:expr [:m-exp {:mo [:- -], :elem [:mn 2]}]], :elem [[:expr [:b-exp {:mo :c, :elem [:expr [:e-exp {:mo [:p :p], :elem-left [:mi x], :elem-right [:mn 3]}]]}]]]}]]


;; (expr
;;  (s/conform :math2.mathml/element
;;             '(+ 2 x 4
;;                 (:sq a))))

;; [:mrow [:mn 2] [:mo "+"] [:mi "x"] [:mo "+"] [:mn "4"] [:mo "+"]
;;  [:msqrt [:mi a]]]







;; (expr
;;   (s/conform :math2.mathml/element '(= 3 (+ 2 x 4))))

;; [:mrow [:mn 3] [:mo "="] [:mrow [:mn 2] [:mo "+"] [:mi "x"] [:mo "+"] [:mn "4"]]]

;; (expr (s/conform :math2.mathml/element '(1 2)))
;; [:mrow [:mfrac [:mn 1] [:mn 2]]]


;; (apply x [ '[:sq [* 4 [441 9]]]])
;; [:math [:msqrt [:mrow [:mn 4] [:mo "×"] [:mrow [:mfrac [:mn 441] [:mn 9]]]]]]

;; (x '[:sq [* 4 9 49]])[:math [:msqrt [:mrow [:mn 4] [:mo "×"] [:mn "9"] [:mo "×"] [:mn "49"]]]]

;; (x '[:sq [* [:p 2
;;               2] [:p 3 2] [:p 7 2] ]])

;; (x '[* [:p 2
;;           1] [:p 3 1] [:p 7 1]])

;; (x [:m 'm 'x ])

;; (x [:m 'm 'x ])

;; (x [:m 'm 'x ])
;; (x  [:p 'x 2])
;; (x [:m 'n 'x ])
;; (x [:p 'x 2])
;; (x '[= [+ [:m 4 [:p x 2]]
;;         [* 2 [:m 70 x]]
;;         [* 2 [:m 50 x]]]
;;      1024])
;; (x '[= [+ [:m 4 [:p x 2]]

;;         [:m 240 x]]
;;      1024])

;; (x '[= [+ [:m [4 4] [:p x 2]]

;;         [:m [240 4] x]]
;;      [1024 4]])
;; (x '[= [+ [:m 1
;;            [:p x 2]]

;;         [:m [[* 60 4] 4]
;;          x]]
;;      [[* 256 4] 4]])

;; (x '[= [+ [:m 1
;;            [:p x 2]]

;;         [:m 60
;;          x]]
;;      [:m [:p 2 4] [:p 2 4]]])
;; (x '[= [+ [:m 4 x] [:m 4 y]] 44])
;; (x '[= [:b [- 11 x]] y])
;; (x '[= [+ [:p x 2]  [:p y 2]] 65])
;; (x '[= [+ [:p x 2]  [:p [:b [- 11 x]] 2 ]] 65])
;; (x '[= [+ [:p x 2]  [:p x 2] [:m [- 11] x] [:m [- 11] x] [:p 11 2] ]
;;      65])

;; (comment
;;   (let [x2 [:m ['* dx dy] [:p v 2]]
;;         a m1
;;         b n1
;;         ab+ [:b ['+ ['- ['* dx  a]] ['* dy b]]]
;;         abx+ [:m ab+ v]
;;         ab ['* ['- a] b]
;;         r1 ['+ x2 abx+ ab]
;;         r2 ['+ x2 [:m  (- m1 n1) v] ab]
;;         s1 [:b ['+ v m1]]
;;         s2 [:b ['+ v n1]]
;;         sol [:m s1 s2]
;;         ]
;;     (map-indexed
;;      (fn [i eq]
;;        (x eq))
;;      [['= r2 0] ['= r1 0]  x2 abx+ ab
;;       ['= sol 0]
;;       ['= s1 0]
;;       ['= v (* m1 -1)]])))


;; (map-indexed
;;  (fn [i eq]
;;    (x eq))


;;  [ ['= 'Force
;;     ['* 'Sprint-constant
;;      'extension]]

;;   ['= [:m 10 'N] [:m 'k  'x1 ]]
;;   ['= [:m 20 'N] [:m 'k  'x2 ]]
;;   ['= [:m [:b ['- 20 10]] 'N] [:m 'k [:b ['- 'x2 'x1]] ]]
;;   ['= [:m 10 'N] ['* 'k .1 'm]]])

;; [
;;  (x '[= F [* k extention]])
;;  (x '[= N [* [N m] m]])
;;  (x '[= Weight [* Mass 10]])]


;; (x `[= y [+ [- [:p x 2] [:m 3 x ]] 1]])
;; (x `[= y [-  [:m 2 x] 5]])
;; (x `[= [-  [:m 2 x ] 5] [+ [- [:p x 2] [:m 2 x ] [:m 2 x ] x]  1]])
;; (x `[= 0 [+ [- [:p x 2] [:m 5 x] ]  6]])

;; (x `[= 0 [:m [:b [- x 2]] [:b [- x 3]]]])


;; (x `[= 0 [- x 3]])
;; (x `[= 3 x])
;; (x `[= y [-  [* 2 3 ] 5]])
;; (x `[= y [-  [* 2 3 ] 5]])
;; (x `[= y 1])
;; (x `[= 0 [- x 2]])
;; (x `[= 2 x])
;; (x `[= y -1])
;; (x `[= [-   5] [+ [:p x 2]  x [- 5] 6 ]])
;; (x (eq2
;;     `[= x
;;       ~(let [x 'y
;;              p (fn [c d] `[:m ~c [:p ~x ~d]] )
;;              np (fn [c _] `[:m ~c ~x] )
;;              np1 (fn [c _] x)
;;              c (fn [c _] c)]


;;          (into ['+]
;;                (map (fn [c d ff]
;;                       (ff c d))
;;                     [ [1 2] [3 2]]
;;                     [ 1 0]
;;                     [ np c])))]))

;; (x (clojure.walk/postwalk
;;           (fn [y]
;;             (if (= y 'y)
;;               (let [[_ _ r]
;;                     (eq2
;;                      `[= x
;;                        ~(let [x 'y
;;                               p (fn [c d] `[:m ~c [:p ~x ~d]] )
;;                               np (fn [c _] `[:m ~c ~x] )
;;                               np1 (fn [c _] x)
;;                               c (fn [c _] c)]


;;                           (into ['+]
;;                                 (map (fn [c d ff]
;;                                        (ff c d))
;;                                      [ 2 3]
;;                                      [ 1 0]
;;                                      [ np c])))])]
;;                 [:b r])
;;               y))
;;           (eq2
;;            `[=
;;              27
;;              ~(let [cp (fn [c d e] `[:p ~e ~d])
;;                     p (fn [c d e] `[:m ~c [:p ~e ~d]] )
;;                     np (fn [c _ e] `[:m ~c ~e] )
;;                     np1 (fn [c _ e] e)
;;                     c (fn [c _ e] c)]


;;                 (into ['+]
;;                       (map (fn [c d ff e]
;;                              (ff c d e))
;;                            [ [1 2] 3]
;;                            [ 2 2]
;;                            [cp p]
;;                            ['x 'y])))])))
;; (x
;;  (eq2
;;   `[=
;;     ~(let [cp (fn [c d e] `[:p ~e ~d])
;;            p (fn [c d e] `[:m ~c [:p ~e ~d]] )
;;            np (fn [c _ e] `[:m ~c ~e] )
;;            np1 (fn [c _ e] e)
;;            c (fn [c _ e] c)]


;;        (into ['+]
;;              (map (fn [c d ff e]
;;                     (ff c d e))
;;                   [2 3]
;;                   [1 1]
;;                   [np c]
;;                   ['y 'y])))
;;     x
;;     ]))
;; (x `[= [+ [:p [:b x] 2]
;;         [:m 2 [:p y 2]]] 27])

;; (x `[= [+ [:m 6 [:p y 2]] [:m 6 y] [:m [:p 3 2] [:b [- 1 3]]]] 0])
;; (x `[= [+ [:m 6 [:p y 2]] [:m 6 y] [* [- 2] [:p 3 2] ]] 0])
;; (x `[= [+ [:m 1 [:p y 2]] [:m 1 y] [- 3]] 0])

;; (x `[= [-  [:m 8 [:p x 2]]
;;           [:m 9 x ] 14] 0])




;; (x (eq2 (w/postwalk
;;          (fun ([[:m c [:p [:b [+ a b]] 2]]]
;;                ['+ [:m c [:p a 2]] ['* 2 c a b] ['* c  [:p b 2]]])
;;               ([y] y))
;;          '[= 27 [+ [:p x 2] [:m 3 [:p [:b [+ [:m 2 y] 3]] 2]]]])))
;; (x (eq2 (w/postwalk
;;          (fun ([[:m a [:b c]]] c)
;;               ([y] y))
;;          (eq2 (w/postwalk
;;                (fun ([[:p [:b [+ a b]] 2]]
;;                      [:b ['+ [:p a 2] ['* 2 a b] [:p b 2]]])
;;                     ([y] y))
;;                '[= 27 [+ [:p x 2] [:m 3 [:p [:b [+ [:m 2 y] 3]] 2]]]])))))


;; (eq2 `[= [- [+ [:p x 2] [:m 2 x] ] 3] y])
;; (x `[= [+ [:m 2 x]  1 ] y])
;; (x (eq2 `[= [:p x 2]  4]))
;; (x (eq2 `[= [- 3 [:p x 2]]  [+ x 1]]))
;; (x (eq2 `[= [+ [:p x 2] x [- 2]] 0]))
;; (x  `[= [+ x 1] y])
;; (x `[= [- 3 [:p x 2]] [+ x 1]])
;; (x `[=  [+ [- [:p x 2]] [- x ] 2 ] 0])
;; (x `[=  [+ [- [:p x 2]] [- x [:m 2 x]] 2 ] 0])
;; (x `[- [:m [- x] [:b [- x 1]]]  [:m 2 [:b [- x 1]]]])
;; (x `[= [:m  [:b [- x 1]] ] 0])

;; (x '[+ x 2])
;; (eq2 `[= [- 2 [:p x 2] ] x])
;; (w/postwalk (fn [x] x) '[:apply
;;                          [= [+ x y] 1]
;;                          [- x]])

;; [m7/x `[= [- [:m 2 [:p x 2]]
;;            x [* 7 3]] 0]]



;; [m7/x `[= [+ [:m 2 [:p x 2]]
;;            [:m x [:b [- [* 2 3] 7]]] [* [- 7] 3]] 0]]



;; [m7/x `[= [+ [:m 2 [:p x 2]]
;;            [- [:m [* 2 3] x] [:m 7 x]] [* [- 7] 3]] 0]]



;; [m7/x `[= [+ [:m [:m 2 x] [:b [+ x 3]]]
;;            [:m [- 7] [:b [+ x 3]]]] 0]]


;; [m7/x `[= [:m [:b [+ x 3]] [:b [- [:m 2 x] 7]]] 0]]


;; [m7/m '[= y [- 1 x]]]


;; [m7/m '[= [+ [:m 2 [:p x 2]]
;;                    [:m x y]
;;                    [:p y 2]]
;;                 22]]
;; [m7/m '[= [+ [:m 2 [:p x 2]] [:m x [:b [- 1 x]]] [:p [:b [- 1 x]] 2]] 22]]


;; [m7/m '[= [+ [:m 2 [:p x 2]] [- x [:p x 2]]
;;                    [:p [:b [- 1 x]] 2]] 22]]

;; [m7/m '[= [- [:m 2 [:p x 2]]  [:m x [:b [- 1 2]]] [- 21]] 0]]


;;         #_[m7/m '[= [- [:m 2 [:p x 2]] x 21] 0]]


;; [m7/x `[= [+ [:m 2 x] 1] y]]
;; [m7/x
;;  (m7/eq2 `[= [+ [+ [:m 8 [:p x 2]] x] 6
;;               [:m 5 y] [:m 6 x y] [:m 9 [:p x 2] y]
;;               [:m 2 y] [:m 3 x y] [:m 2 [:p x 2] y]

;;               ] 0])
;;  ]
;; (m7/x `[= [+ [:m 6 [:p y 2]] [:m 6 y] [:m [:p 3 2] [:b [- 1 3]]]] 0])
;; (m7/x `[= [+ [:m 6 [:p y 2]] [:m 6 y] [* [- 2] [:p 3 2] ]] 0])
;; (m7/x `[= [+ [:m 1 [:p y 2]] [:m 1 y] [- 3]] 0])
;; [m7/x `[= [-  [:m 8 [:p x 2]]
;;            [:m 9 x ] 14] 0]]
;; [m7/x (m7/eq2 (w/postwalk
;;                (fun ([[:m c [:p [:b [+ a b]] 2]]]
;;                      ['+ [:m c [:p a 2]] ['* 2 c a b] ['* c  [:p b 2]]])
;;                     ([y] y))
;;                '[= 27 [+ [:p x 2] [:m 3 [:p [:b [+ [:m 2 y] 3]] 2]]]]))]
;; [m7/x
;;  (m7/eq2 (w/postwalk (fun ([[:m a [:b c]]] c)
;;                           ([y] y))
;;                      (m7/eq2 (w/postwalk
;;                               (fun ([[:p [:b [+ a b]] 2]]
;;                                     [:b ['+ [:p a 2] ['* 2 a b] [:p b 2]]])
;;                                    ([y] y))
;;                               '[= 27 [+ [:p x 2] [:m 3 [:p [:b [+ [:m 2 y] 3]] 2]]]]))))]

;; [m7/x

;;  (m7/eq2 `[= [- [+ [:p x 2] [:m 2 x] ] 3] y])]
;; [m7/x
;;  `[= [+ [:m 2 x]  1 ] y]]

;; [m7/x
;;  (m7/eq2 `[= [- 3 [:p x 2]]  [+ x 1]])]



;; [m7/x
;;  (m7/eq2 `[= [+ [:p x 2] x [- 2]] 0])]


;; [m7/x
;;  (m7/eq2 `[= [+ [:p x 2] x [- 2]] 0])]



;; [m7/x
;;  (m7/eq2 `[= [+ [:p x 2] [:m x [:b [- 2 1]]] [* 2 [- 1]]] 0])]

;; [m7/x
;;  (m7/eq2 `[= [+ [:p x 2] [- [:m 2 x] x] [* 2 [- 1]]] 0])]


;; [m7/x
;;  (m7/eq2 `[= [- [:m x [:b [+ x 2]]] [:m 1 [:b [+ x 2]]]] 0])]


;; [m7/x '[= [:m [:b [+ x 2]]  [:b [- x 1]]] 0] ]




;; [m7/m  '[= [+ [- x 1 ] 1] 1]]

;; (x (m7/eq2 `[= [:p x 2]  4]))
;; [m7/m  '[= [+ x 2] 0]]
;; (x `[= [-   5] [+ [:p x 2]  x [- 5] 6 ]])
;; (x (m7/eq2 `[= [- 3 [:p x 2]] [+ x 1]]))
;; (x (m7/eq2 `[=  [+ [- [:p x 2]] [- x ] 2 ] 0]))
;; (x (m7/eq2 `[=  [+ [- [:p x 2]] [- x [:m 2 x]] 2 ] 0]))
;; [m7/x
;;  (m7/eq2 `[- [:m [- x] [:b [- x 1]]]  [:m 2 [:b [- x 1]]]])]
;; [m7/x
;;  (m7/eq2 `[= [:m  [:b [- x 1]] ] 0])]
;; (comment


;;   [m7/x '[+ x 2]]



;;   [m7/x
;;          (m7/eq2 `[= [- 2 [:p x 2] ] x])]



;;   (w/postwalk (fn [x] x) '[:apply
;;                                  [= [+ x y] 1]
;;                                  [- x]])


;;   [m7/x `[= [- [:m 2 [:p x 2]]
;;              x [* 7 3]] 0]]



;;   [m7/x `[= [+ [:m 2 [:p x 2]]
;;              [:m x [:b [- [* 2 3] 7]]] [* [- 7] 3]] 0]]



;;   [m7/x `[= [+ [:m 2 [:p x 2]]
;;              [- [:m [* 2 3] x] [:m 7 x]] [* [- 7] 3]] 0]]



;;   [m7/x `[= [+ [:m [:m 2 x] [:b [+ x 3]]]
;;              [:m [- 7] [:b [+ x 3]]]] 0]]


;;   [m7/x `[= [:m [:b [+ x 3]] [:b [- [:m 2 x] 7]]] 0]]

;;   [m7/m '[= y [- 1 x]]]


;;   [m7/m '[= [+ [:m 2 [:p x 2]]
;;              [:m x y]
;;              [:p y 2]]
;;           22]]
;;   [m7/m '[= [+ [:m 2 [:p x 2]] [:m x [:b [- 1 x]]] [:p [:b [- 1 x]] 2]] 22]]


;;   [m7/m '[= [+ [:m 2 [:p x 2]] [- x [:p x 2]]
;;              [:p [:b [- 1 x]] 2]] 22]]

;;   [m7/m '[= [- [:m 2 [:p x 2]]  [:m x [:b [- 1 2]]] [- 21]] 0]]


;;   [m7/m '[= [- [:m 2 [:p x 2]] x 21] 0]])


;; [m7/x `[= y [+ [- [:p x 2] [:m 3 x ]] 1]]]
;; [m7/x `[= y [-  [:m 2 x] 5]]]
;; [m7/x `[= [-  [:m 2 x ] 5] [+ [- [:p x 2] [:m 2 x ] [:m 2 x ] x]  1]]]
;; [m7/x `[= 0 [+ [- [:p x 2] [:m 5 x] ]  6]]]

;; [m7/x `[= 0 [:m [:b [- x 2]] [:b [- x 3]]]]]



;; [m7/x `[= 0 [- x 3]]]
;; [m7/x `[= 3 x]]
;; [m7/x `[= y [-  [* 2 3 ] 5]]]
;; [m7/x `[= y [-  [* 2 3 ] 5]]]
;; [m7/x `[= y 1]]
;; [m7/x `[= 0 [- x 2]]]
;; [m7/x `[= 2 x]]



;; (x `[:m 8 [:p ~x 2]])
;; (x `[* ~f1 ~f2])
;; (x `[:m 7 ~x])
;; (x `[:m -16 ~x])
;; (x `[:m 1 ~x])
;; (x `[:m 8 ~x])

;; (s/unform
;;  (s/conform :math2.mathml/element
;;             (eq2 `[= [:m g [:b t]]  [+ b [:m 2 a t]]])))

;; (sx `[= [:m g [:b t]]  [+ b [:m 2 a t]]] 't '[+ t 3])

;; (let [eq `[= [:m g [:b t]]  [+ b [:m 2 a t]]]
;;       x2 't
;;       tt '[+ t 3]]
;;   (let [r (vec (clojure.walk/postwalk
;;                 (fn [x]
;;                   (if (symbol? x)
;;                     (let [x1 (symbol (name x))]
;;                       (if (= x1 x2)
;;                         tt
;;                         x1))
;;                     x))
;;                 eq))]
;;     r))

;; (s/valid? ::element '[:b 1 3])
;; (x `[:t 3 5 ~(+ 3 4)])
;; (expr (s/conform ::element '[+ 1 3]))
;; (s/conform ::element
;;            '[:b 3])
;; (let [eq `[= [- [:m 3 x] [:m 2 y]] 7] ]
;;   (clojure.walk/postwalk (fn [x]
;;                            (if (symbol? x)
;;                              (symbol (name x))
;;                              x))
;;                          eq))


;; (s/conform ::element
;;            (vec (map
;;                  (fn [x]
;;                    (if (symbol? x)
;;                      (symbol (name x))
;;                      x))
;;                  `[= [- [:m 3 x] [:m 2 y]] 7])))
;; (mx `[= [- [:m 3 x] [:m 2 y]] 7])
;; (symbol (name `x))

;; (expr (s/conform ::element 1))

;; (expr
;;  (s/conform ::element
;;             (clr (let [ay 1]
;;                    `(+ x ~(+ ay 2)))))
;;  )

;; (expr (s/conform ::element '(:m 2 x)))

;; (expr (s/conform ::element '(:b 2)))

;; (expr (s/conform ::element [:m 2 'x 3 4]))


;; (s/conform ::element [:b [:b 3]])

;; (expr
;;  (s/conform ::element
;;             '(+ :alpha) x (- 5 2 4)
;;             ((- x ) 1)))


;; (s/conform ::element
;;            '(= (- 2) (:c (:p x 3))
;;                ))

;; (expr
;;  (s/conform ::element
;;             '(= 3 (+ 2 x 4
;;                      ))))

;; (s/conform ::element
;;            1)
;; (expr
;;  (s/conform ::element
;;             '(= 3 (+ 2 x 4
;;                      ))))
;; (expr (s/conform ::element '(1 2)))

;; (expr
;;  (s/conform ::element
;;             '(+ 2 x 4
;;                 (:sq a))))
