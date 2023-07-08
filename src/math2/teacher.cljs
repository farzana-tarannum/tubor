(ns math2.teacher
  (:require
   [react]
   [cljs.reader :as edn]
   [math2.file :as file]
   [datascript.core :as d]
   [clojure.string :as str]
   [math2.css :as m7 :refer
    [grid hsl css space size ve sec]]
   [clojure.walk :as walk]
   [math2.db :as db]
   [math2.img :as img]
   [math2.font :as font]
   [moment]))




(def conn
  (d/create-conn db/schema))

(d/transact! conn db/data)

(d/q '[:find  ?t ?br
       :where
       [?p :rm/task ?t]
       [?p :rm/breakdowns ?br]
       ] @conn)

(def tasks
  (d/q '[:find  ?t ?s ?r ?c ?p
         :where
         [?e :rm/code :devops]
         [?e :rm/projects ?p]
         [?p :rm/task ?t]
         [?p :rm/summery ?s]

         [?p :rm/row ?r]
         [?p :rm/col ?c]
         ] @conn))






#_(d/q '[:find  ?t
       :where
       [?e :rm/code :devops]
       [?e :rm/projects ?p]
       [?p :rm/task ?t]
       [?p :rm/summery ?s]

       [?p :rm/row ?r]
       [?p :rm/col ?c]
       ] @conn)








;; (def to-date #inst "2014-05-01T00:00:00.000-00:00")

;; (->
;;  (moment to-date)
;;  (.add "days" 33)
;;  (.fromNow))

;; (->
;;  (moment to-date)
;;  (.add "days" 2)
;;  (.fromNow))

;; (->
;;  (moment)
;;  (.subtract "days" 30)
;;  (.fromNow))

;; (.fromNow (moment "November 1977"))

;; (->
;;  (moment)
;;  (.add "days" 2)
;;  (.calendar))

;; (->
;;  (moment)
;;  (.subtract "days" 2)
;;  (.calendar))

;; (.format (moment "1977-08-20 14:29:00 UTC") "MMM. d, YYYY")

;; (.fromNow (moment "1977-08-20 14:29:00 UTC"))




(defn marking [marks paragraph color]
  (reduce
   (fn [acc e]
     (if (some #(= e %) marks)
       (conj acc
             [:span " "
              [:span {:style {:padding "2.5px 1px"
                              :letter-spacing "1px"
                              :background-color color
                              :font-weight 700}}
               (str  e)]])



       (conj acc [:span (str " "  e)])))
   [:div ]

   (str/split paragraph
              #"\s+")))



(defn resume2 []
  (let [psizie 5
        car (vec (map vec (partition psizie db/topics)))
        c (count tasks)
        ref (react/useRef)
        vh 4
        tvh (js/Math.floor (/ (* 15 8) 4))
        spacing [3  6 (/ (count car) 2) (+ 20 (* 4 c)) (* 2 (count db/course))]
        width tvh
        fifth (js/Math.floor (/ tvh 5))
        fifth4 (* 4 fifth)
        heights (map-indexed (fn [i x] (if (= i 2)
                                         x
                                         (js/Math.floor
                                          (/ (* 3 x) vh)))) spacing)
        heights2 (reduce
                  (fn [acc x]
                    (conj acc (+ x (last acc))))
                  []
                  heights)
        ]
    [:div {:style
           (merge
            (grid [100 :vh 100 :vw
                   (take tvh (repeat [vh :vh]))
                   (take 200  (repeat [(/ vh 2)  :vh]))])
            {:background-color (hsl [1 70 90 0.1])
             :padding "10px"
             :gap ".2rem"})}

     [:div {:key (gensym)
            :style (css
                    [[1 (first heights) 1 width
                      :center :center 0.4 :rem :column]
                     [1 70 90 0.4]
                     []
                     {

                      :letter-spacing "0.08em"
                      :font-weight "491"
                      :font-stretch "113%"
                      :gap "0.1rem"
                      :color "#333"
                      :font-family "Roboto Flex"
                      :font-variation-settings "\"XOPQ\" 175"
                      :z-index 2}

                     ])}

      [:div "Ashrar Ahmed Khan"]
      [:div {:style {:font-size "0.2em"}} "H# 192, R# 2, MIRPUR DOHS, DHAKA, BANGLADESH"]
      [:div {:style {:font-size "0.2rem"}}
       "+8801711961024, jaharapi@protonmail.com"]]

     #_[:div {:key (gensym)
            :style (m7/css
                    [[1 (first heights)
                      (inc fifth4)
                      fifth
                      :center :center 0.8 :rem ]
                     [2 70 90 0.4] [] {:gap "1rem"
                                       :z-index 4}])



            }
      [file/file-input-background4]]


     [:div {:contenteditable :true
            :key (gensym)
            :style (css
                    [[(inc (first heights)) (second heights) 1 tvh  :center :center 0.3 :rem :column]
                     [1 70 90 0.4]
                     []
                     {
                      :padding "20px"
                      :contenteditable :true
                      :gap "1rem"
                      :z-index 2}
                     ])
            }
      (ffirst
       (d/q '[:find  ?s
              :where
              [?e :rm/code :iccddrb]
              [?e :rm/summery ?s]] @conn))]
     #_(let  []
       (for [i (range 0 (/ (count car) 2))
             j (range 0 psizie)]
         [:div {
                :key (gensym)
                :style (css [[(+ 1 i (second heights2)) 1 (+ 1 (* 3 j)) 3 :center :center 0.3 :rem ]
                             [1 70 (if (= j 0) 70 90) 0.4] []
                             {:font-family "Roboto Flex"
                              :gap "0.1rem"
                              :z-index 21}])}
          (get-in car [i j] "")]))


     #_(let  []
       (for [i (range (/ (count car) 2) (count car))
             j (range 0 psizie)]
         [:div {
                :key (gensym)
                :style (css [[(+ 1 (int (- i (/ (count car) 2))) (second heights2)) 1 (+ 16 (* 3 j)) 3
                              :center :center 0.3 :rem ]
                             [1 70 (if (= j 0) 70 90) 0.4] []
                             {:font-family "Roboto Flex"
                              :gap "0.1rem"
                              :z-index 21}])}

          (get-in car [i j] "")]))



     (let [tsk (sort-by #(nth % 2) < tasks)]
       (map (fn [ind]
              (let [i (js/Math.floor (/ ind 2))
                    tsk? (if (= 0 (mod ind 2)) true false)
                    r (+ 1 (* (js/Math.floor vh) i) (nth heights2 2))
                    w (js/Math.floor (/ (* 4 8) (* vh 2)))
                    [task sum row col id] (get (vec tsk) i)]
                (if tsk?
                  [:div {:key (gensym)
                         :contenteditable :true
                         :style (m7/css
                                 [[r
                                   w
                                   (inc fifth4) fifth :center :center 0.4 :rem]
                                  [1 70 90 0.4]
                                  [] {:gap "0.1rem"
                                      :padding "1rem"}
                                  (font/fv [[1 4] [1 1] [1 2] [2 1]])])}


                   #_(str col " " id " " task)
                   task
                   ]
                  [:div {:key (gensym)
                         :contenteditable :true
                         :style (m7/css
                                 [[r w
                                    1 fifth4 :center :center 0.4 :rem]
                                  [1 70 90 0.3]
                                  []
                                  {:line-height 1.5
                                   :gap "0.1rem"
                                   :padding "1px"
                                   }
                                  ])}

                   (marking db/topics
                            (str/join "" (take 600 sum))
                            (m7/hsl [1.2 100 70 0.8]))
                     ])
                ))
            (range 0 (* 2 (count tasks))))
       )

     (for [i (range 0 (count db/course))]
       [:div {:key (gensym)
              :style (m7/css
                      [[(+ 1 (* 2 i) (nth heights2 3)) 2 1 tvh  :center :center 0.5 :rem ]
                       [2 70 90 0.4] [] {:gap "0.1rem"
                                         :z-index 4}])



              }
        (get-in db/course [i 0])])
     ]))




(defn resume []
  (let [psizie 5
        car (vec (map vec (partition psizie db/topics)))
        c (count tasks)
        ref (react/useRef)
        vh 4
        tvh (js/Math.floor (/ (* 15 8) vh))
        spacing [3  5 (count car) (* 4 c) (* 2 (count db/course))]
        width tvh
        fifth (js/Math.floor (/ tvh 5))
        fifth4 (* 4 fifth)
        heights (map-indexed (fn [i x] (if (= i 2)
                                         x
                                         (js/Math.floor
                                          (/ (* 8 x) vh)))) spacing)
        heights2 (reduce
                  (fn [acc x]
                    (conj acc (+ x (last acc))))
                  []
                  heights)
        ]
    [:div {:style
           (merge
            (grid [100 :vh 100 :vw
                   (take tvh (repeat [vh :vh]))
                   (take 200  (repeat [vh :vh]))])
            {:background-color (hsl [1 70 90 0.1])
             :padding "50px"
             :gap ".2rem"})}

     [:div {:key (gensym)
            :style (css
                    [[1 (first heights) 1 width
                      :center :center 2.5 :rem :column]
                     [1 70 90 0.4]
                     []
                     {
                      :padding "20px"
                      :font-size "62px"
                      :letter-spacing "0.08em"
                      :font-weight "491"
                      :font-stretch "113%"
                      :gap "1rem"
                      :color "#333"
                      :font-family "Roboto Flex"
                      :font-variation-settings "\"XOPQ\" 175"
                      :z-index 2}

                     ])}

      [:div "Ash Ahmed"]
      [:div {:style {:font-size "1.1rem"}} "H# 192, R# 2, Mirpur DOHS, DHAKA, BANGLADESH"]
      [:div {:style {:font-size "1.3rem"}}
       "+8801711961024, jaharapi@protonmail.com"]]

     [:div {:key (gensym)
            :style (m7/css
                    [[1 (first heights)
                      (inc fifth4)
                      fifth
                      :center :center 2.2 :rem ]
                     [2 70 90 0.4] [] {:gap "1rem"
                                       :z-index 4}])



            }
      [file/file-input-background4]]


     [:div {:contenteditable :true
            :key (gensym)
            :style (css
                    [[(inc (first heights)) (second heights) 1 tvh  :center :center 2.3 :rem :column]
                     [1 70 90 0.4]
                     []
                     {
                      :padding "20px"
                      :contenteditable :true
                      :gap "1rem"
                      :z-index 2}
                     ])
            }
      (ffirst
       (d/q '[:find  ?s
              :where
              [?e :rm/code :devops]
              [?e :rm/summery ?s]] @conn))
      ]







     (let  []
       (for [i (range 0 (count car))
             j (range 0 psizie)]
         [:div {
                :key (gensym)
                :style (css [[(+ 1 i (second heights2)) 1 (+ 1 (* 3 j)) 3 :center :center 0.9 :rem ]
                             [1 70 (if (= j 0) 70 90) 0.4] []
                             {:font-family "Roboto Flex"
                              :gap "1rem"
                              :z-index 21}])}

          (get-in car [i j] "")]))


     (let  []
       (for [i (range 0 (count car))
             j (range 0 psizie)]
         [:div {
                :key (gensym)
                :style (css [[(+ 1 i (second heights2)) 1 (+ 16 (* 3 j)) 3 :center :center 0.9 :rem ]
                             [1 70 (if (= j 0) 70 90) 0.4] []
                             {:font-family "Roboto Flex"
                              :gap "1rem"
                              :z-index 21}])}

          (get-in car [i j] "")]))



     (let [tsk (sort-by #(nth % 2) < tasks)]
       (map (fn [ind]
              (let [i (js/Math.floor (/ ind 2))
                    tsk? (if (= 0 (mod ind 2)) true false)
                    [task sum row col id] (get (vec tsk) i)]
                (if tsk?
                  [:div {:key (gensym)
                         :contenteditable :true
                         :style (m7/css
                                 [[(+ 1 (* (js/Math.floor (/ (* 4 8) vh)) i) (nth heights2 2))
                                   (js/Math.floor (/ (* 4 8) vh))
                                   (inc fifth4) fifth :center :center 1.2 :rem]
                                  [1 70 90 0.4]
                                  [] {:gap "1rem"
                                      :padding "2rem"}
                                  (font/fv [[1 4] [1 1] [1 2] [2 1]])])}


                   (str col " " id " " task)]
                  [:div {:key (gensym)
                         :contenteditable :true
                         :style (m7/css
                                 [[(+ 1
                                      (*
                                       (js/Math.floor (/ (* 4 8) vh)) i)
                                      (nth heights2 2))
                                   (js/Math.floor (/ (* 4 8) vh)) 1 fifth4 :center :center 1.5 :rem]
                                  [1 70 90 0.3]
                                  []
                                  {:line-height 1.5
                                   :gap "1rem"
                                   :padding "10px"
                                   }
                                  ])}

                   (marking db/topics
                            (str/join "" (take 600 sum))
                              (m7/hsl [1.2 100 70 0.8]))
                     ])
                ))
            (range 0 (* 2 (count tasks))))
       )

     (for [i (range 0 (count db/course))]
       [:div {:key (gensym)
              :style (m7/css
                      [[(+ 1 (* 2 i) (nth heights2 3)) 2 1 tvh  :center :center 1.2 :rem ]
                       [2 70 90 0.4] [] {:gap "1rem"
                                         :z-index 4}])



              }
        (get-in db/course [i 0])])
     ]))


(comment
  (def conn
    (d/create-conn schema))

  (d/transact! conn data))
