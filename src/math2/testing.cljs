(ns math2.testing
  (:require
   [defun.core :refer [defun fun]]
   [clojure.walk :as w]
   [math2.math7 :as m7]))



(let [
      cp (fn [c d e] `[:p ~e ~d])
      p (fn [c d e] `[:m ~c [:p ~e ~d]] )
      np (fn [c _ e] `[:m ~c ~e] )
      np1 (fn [c _ e] e)
      c (fn [c _ e] c)
      mkeq1 (fun
             ([[a b c d]]
              [a b c d])
             ([[con 0 _]]
              [:c con 0 1])
             ([[1 1 e]]
              [:np1 1 1 e])
             ([[c2 1 e]]
              [:np c2 1 e])
             ([[1 d e]]
              [:cp 1 d e])
             ([[c2 d e]]
              [:p c2 d e])
             ([[c2  e]]
              [:np c2 1 e])
             ([(e2 :guard symbol?)]
              [:np1 1 1 e2])
             ([con]
              [:c con 0 1]))
      mkeq2 (comp
             (fun
              ([[:np1 co deg ee]]
               (np1 co deg ee))
              ([[:np co deg ee]]
               (np co deg ee))
              ([[:p co deg ee]]
               (p co deg ee))
              ([[:cp co deg ee]]
               (p co deg ee))
              ([[:c co deg ee]]
               (c co deg ee))
              ) mkeq1)
      neg-eq (fn [e]
               (let [[a b c d] (mkeq1 e)]
                 [a (* -1 b) c d]))
      draw-eq (fn [exx]
                (map mkeq2 exx))
      draw-eq2 (fn [[exx exx2]]
                 `[=
                   [+ ~@(map mkeq2 exx)]
                   [+ ~@(map mkeq2 exx2)]])

      dd2 `[[2 2 x] [4 2 y] [3  x] [2  y] -56 ]

      dd3 `[= [+  ~@(map mkeq2 dd2)] 0]
      dd4 `[ [5 x] [-2 y] 7]
      dd5  ((fn [dd]
              (let [[a & r] dd]
                [`[~(neg-eq a) ~@dd]
                 [a 0]]))
            dd4)
      a4 (draw-eq2 dd5)
      ]
  (m7/eq2 (draw-eq2 dd5)))


(let [
      cp (fn [c d e] `[:p ~e ~d])
      p (fn [c d e] `[:m ~c [:p ~e ~d]] )
      np (fn [c _ e] `[:m ~c ~e] )
      np1 (fn [c _ e] e)
      c (fn [c _ e] c)
      mkeq1 (fun
             ([[con 0 _]]
              [:c con 0 1])
             ([[1 1 e]]
              [:np1 1 1 e])
             ([[c2 1 e]]
              [:np c2 1 e])
             ([[1 d e]]
              [:cp 1 d e])
             ([[c2 d e]]
              [:p c2 d e])
             ([[c2  e]]
              [:np c2 1 e])
             ([(e2 :guard symbol?)]
              [:np1 1 1 e2])
             ([con :guard number?]
              [:c con 0 1])
             ([pass]
              pass))
      mkeq21
      (fun
       ([[:np1 co deg ee]]
        (np1 co deg ee))
       ([[:np co deg ee]]
        (np co deg ee))
       ([[:p co deg ee]]
        (p co deg ee))
       ([[:cp co deg ee]]
        (p co deg ee))
       ([[:c co deg ee]]
        (c co deg ee))
       )

      mkeq2 (comp
             mkeq21
             mkeq1)
    
      draw-eq (fn [exx]
                (map mkeq2 exx))


      draw-eq2 (fn [[exx exx2]]
                 `[=
                   [+ ~@(map mkeq2 exx)]
                   [+ ~@(map mkeq2 exx2)]])

      eq3 (fn [[exx exx2]]
                 `[=
                   [+ ~@(map mkeq1 exx)]
                   [+ ~@(map mkeq1 exx2)]])

      dd2 `[[2 2 x] [4 2 y] [3  x] [2  y] -56 ]

      dd3 `[= [+  ~@(map mkeq2 dd2)] 0]
      dd4 `[ [5 x] [-2 y] 7]
      neg-eq (fn [e]
               (let [[a b c d] (mkeq1 e)]
                 [a (* -1 b) c d]))
      dd5  ((fn [dd]
              (let [[a & r] dd]
                [`[~(neg-eq a) ~@dd]
                 [a 0]]))
            dd4)
      a4 (draw-eq2 dd5)
      ]
  (m7/eq2 (eq3 dd5)))
(comment
  (m7/eq2
   ((comp
     mkeq2    
     ffirst)
    dd5)
   ))
(let [
      cp (fn [c d e] `[:p ~e ~d])
      p (fn [c d e] `[:m ~c [:p ~e ~d]] )
      np (fn [c _ e] `[:m ~c ~e] )
      np1 (fn [c _ e] e)
      c (fn [c _ e] c)
      mkeq1 (fun
             ([[con 0 _]]
              [:c con 0 1])
             ([[1 1 e]]
              [:np1 1 1 e])
             ([[c2 1 e]]
              [:np c2 1 e])
             ([[1 d e]]
              [:cp 1 d e])
             ([[c2 d e]]
              [:p c2 d e])
             ([[c2  e]]
              [:np c2 1 e])
             ([(e2 :guard symbol?)]
              [:np1 1 1 e2])
             ([con :guard number?]
              [:c con 0 1])
             ([pass]
              pass))
      mkeq21
      (fun
       ([[:np1 co deg ee]]
        (np1 co deg ee))
       ([[:np co deg ee]]
        (np co deg ee))
       ([[:p co deg ee]]
        (p co deg ee))
       ([[:cp co deg ee]]
        (p co deg ee))
       ([[:c co deg ee]]
        (c co deg ee))
       )

      mkeq2 (comp
             mkeq21
             mkeq1)
    
      draw-eq (fn [exx]
                (map mkeq2 exx))


      draw-eq2 (fn [[exx exx2]]
                 `[=
                   [+ ~@(map mkeq2 exx)]
                   [+ ~@(map mkeq2 exx2)]])

      eq3 (fn [[exx exx2]]
                 `[=
                   [+ ~@(map mkeq1 exx)]
                   [+ ~@(map mkeq1 exx2)]])

      dd2 `[[2 2 x] [4 2 y] [3  x] [2  y] -56 ]
      ddd2 `[[2 2 x] [-2 2 x] [4 2 y] [3  x] [2  y] -56 34]
      dd3 `[= [+  ~@(map mkeq2 dd2)] 0]
      dd4 (m7/eq2 `[ [5 x] [-2 y] 7])
      neg-eq (fn [e]
               (let [[a b c d] (mkeq1 e)]
                 [a (* -1 b) c d]))
      dd5  ((fn [dd]
              (let [[a & r] dd]
                [`[~(neg-eq a) ~@dd]
                 [a 0]]))
            dd4)
      make-a4 (fn [[exx exx2]]
                 `[=
                   [+ ~@(map mkeq2 exx)]
                   [+ ~@(map mkeq2 exx2)]])
      add-first (fn [dd]
                  (let [[a & r] dd]
                    [`[~(neg-eq a) ~@(map mkeq1  dd)]
                     [(neg-eq a) (mkeq1 0)]]))
      add-nth (fn [dd n]
                (let [a (get dd n)]
                  [`[~(neg-eq a) ~@(map mkeq1  dd)]
                   [(neg-eq a) (mkeq1 0)]]))

      a4 (draw-eq2 dd5)
      t1 (comp
          mkeq2 
          (fun [[[e f g] b]]
               [e b f g]))
      t21 (comp
          mkeq1 
          (fun [[[e f g] b]]
               [e b f g]))
      t2 (fn [acc e]
           (let [[f c deg sm] (mkeq1 e)
                 v (get acc [f deg sm])]
             (if v
               (assoc acc [f deg sm] (+ v c))
               (assoc acc [f deg sm] c))))
      dd-cls (fn [ddd]
               (map
                t1
                (filter (fn [[u v]]
                          (if (= v 0)
                            false true))
                        (reduce t2    {}  (m7/eq2 ddd)))))
      dd-cls2 (fn [ddd]
               (map
                t21
                (filter (fn [[u v]]
                          (if (= v 0)
                            false true))
                        (reduce t2    {}  (m7/eq2 ddd)))))

      dd-cls-eq (fn [dd]
                  (m7/eq2 (let [[a b] (m7/eq2 (add-first dd))]
                            `[= [+ ~@(vec (dd-cls a))]
                              ~(first (dd-cls b))]
                            )))
      simplify (fn [dd]
                 (vec  
                  (map
                   (comp
                    (fn [[a b]]
                      (a b))
                    (juxt
                     (comp
                      #(if (> % 1) (fn [a]
                                     (vec (cons '+ a))) first)
                      count) identity)
                    #(map mkeq2 %)
                    vec
                    #(map (fun [[[e f g] b]]
                               [e b f g]) %)
                    vec
                    #(filter (fn [[u v]]
                               (if (= v 0) false true)) %)
                    vec
                    #(reduce t2  {} %))
                   dd
                   )))]
  
  (simplify (add-nth dd4 1)))


(let [
      cp (fn [c d e] `[:p ~e ~d])
      p (fn [c d e] `[:m ~c [:p ~e ~d]] )
      np (fn [c _ e] `[:m ~c ~e] )
          np1 (fn [c _ e] e)
          c (fn [c _ e] c)
          mkeq1 (fun
                 ([[con 0 _]]
                  [:c con 0 1])
                 ([[1 1 e]]
                  [:np1 1 1 e])
                 ([[c2 1 e]]
                  [:np c2 1 e])
                 ([[1 d e]]
                  [:cp 1 d e])
                 ([[c2 d e]]
                  [:p c2 d e])
                 ([[c2  e]]
                  [:np c2 1 e])
                 ([(e2 :guard symbol?)]
                  [:np1 1 1 e2])
                 ([con :guard number?]
                  [:c con 0 1])
                 ([con]
                  con))
          mkeq2 (comp
                 (fun
                  ([[:np1 co deg ee]]
                   (np1 co deg ee))
                  ([[:np co deg ee]]
                   (np co deg ee))
                  ([[:p co deg ee]]
                   (p co deg ee))
                  ([[:cp co deg ee]]
                   (p co deg ee))
                  ([[:c co deg ee]]
                   (c co deg ee))
                  ) mkeq1)
          draw-eq (fn [exx]
                    (map mkeq2 exx))
          draw-eq2 (fn [[exx exx2]]
                     `[=
                       [+ ~@(map mkeq2 exx)]
                       [+ ~@(map mkeq2 exx2)]])
          neg-eq (fn [e]
                   (let [[a b c d] (mkeq1 e)]
                     [a (* -1 b) c d]))
          dd2 `[[2 2 x] [4 2 y] [3  x] [2  y] -56 ]
          dd3 `[= [+  ~@(map mkeq2 dd2)] 0]
          dd4 `[ [5 x] [-2 y] 7]
          dd5  ((fn [dd]
                  (let [[a & r] dd]
                    [`[~(neg-eq a) ~@dd]
                     [(neg-eq a) 0]]))
                dd4)
          dd6 `[  [-2 y] 7]
          a2 `[= [+  ~@(map mkeq2 dd4)] 0]
          a4 (draw-eq2 dd5)
          t1 (comp
              mkeq2 
              (fun [[[e f g] b]]
                   [e b f g]))
          t2 (fn [acc e]
               (let [[f c deg sm] (mkeq1 e)
                     v (get acc [f deg sm])]
                 (if v
                   (assoc acc [f deg sm] (+ v c))
                   (assoc acc [f deg sm] c))))
          add-first (fn [dd]
                      (let [[a & r] dd]
                        [`[~(neg-eq a) ~@(map mkeq1  dd)]
                         [(neg-eq a) 0]]))
          dd-cls (fn [ddd]
                   (map
                    t1
                    (filter (fn [[u v]]
                              (if (= v 0)
                                false true))
                            (reduce t2    {}  (m7/eq2 ddd)))))

          dd-cls-eq (fn [dd]
                      (m7/eq2 (let [[a b] (m7/eq2 (add-first dd))]
                                `[= [+ ~@(vec (dd-cls a))]
                                  ~(first (dd-cls b))]
                                )))
          a5  (dd-cls-eq dd4)
          a6  `[=
                ~(into ['+]
                       (map (fn [c d ff e]
                              (ff c d e))
                            (map (fn [x] [x -5])  [ -2 7])
                            [ 1 0]
                            [ np c]
                            [ 'y 1]))
                x]
          [_ a3 _] a6
          [_ aq1 aq2] a3 
          iden1  `[+ [:p [:b ~aq1] 2] [:m 2 [:b ~aq1] [:b ~aq2]]
                   [:p [:b ~aq2] 2]]
          idenab2 (fn [aq1 aq2]
                    (m7/eq2 
                     `[+ [:p [:b ~aq1] 2] [:m 2 [:b ~aq1] [:b ~aq2]]
                       [:p [:b ~aq2] 2]]))
          a7 (w/postwalk
              (fn [y]
                (if (= y 'x)
                  [:b a3] 
                  y))
              (m7/eq2 dd3))
          a8 (w/postwalk
              (fn [y]
                   (if (= y [:p [:b a3] 2])
                     [:b iden1]
                     y))
              (m7/eq2 a7))
          a9 (w/postwalk
              (fun ([[:m f1 [:p [:b [_ fq1 fq2]] 2]]]
                    [:m f1 [:b (idenab2 fq1 fq2)]])
                   ([y] y))
              (m7/eq2 a7))
          ]

      [
       dd3
       a2
       a4
       a5
       a6
       a7 
       a8
       a9
       ])
