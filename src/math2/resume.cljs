(ns math2.resume
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




(defonce conn
  (d/create-conn db/schema))

(d/transact! conn db/data)


#_(d/q '[:find ?p ?fr  ?t ?br
       :where
       [?p :rm/from-date ?fr]
       [?p :rm/task ?t]
       [?p :rm/breakdowns ?br]
       ] @conn)





#_(mapv
   (fn [[a b]]
     ^{:key a}[:div a])
   (d/q '[:find  ?t ?br
          :where
          [?p :rm/task ?t]
          [?p :rm/breakdowns ?br]
          ] @conn))
(defn resume []
  [:div {:style {:display :flex
                 :width "100vw"
                 :justify-content :center}}

   [:div {:style (merge
                  {:display :grid,
                   :width "50vw",
                   :grid-template-columns "1fr 1fr 1fr 1fr",
                   :grid-template-rows "auto"}
                  {:gap 10
                   :background-color (hsl [1 90 90 0.2])})}
    [:div {:key (gensym)
           :style (css
                   [[1 1 1 3
                     :center :center 2 :rem :column]
                    [1 70 90 0.4]
                    []
                    {
                     :height "20vh"
                     :letter-spacing "0.08em"
                     :font-weight "491"
                     :font-stretch "113%"
                     :gap "1rem"
                     :color "#333"
                     :font-family "Roboto Flex"
                     :font-variation-settings "\"XOPQ\" 175"
                     :z-index 2}

                    ])}

     [:div "Ashrar Ahmed Khan Choudhury"]
     [:div {:style {:font-size "0.6rem"}} "H# 192, R# 2, MIRPUR DOHS, DHAKA, BANGLADESH"]
     [:div {:style {:font-size "0.5rem"}}
      "+8801714116110, jaharapi@protonmail.com"]]
    [:div {:key (gensym)
           :style (merge
                   (m7/css
                    [[1 1
                      4 1
                      :flex-end :center 0.8 :rem ]
                     [1 80 90 0.4]
                     [] {:z-index 4}])
                   {})}
     [file/file-input-background4]]

    [:div {:contenteditable :true
           :key (gensym)
           :style (css
                   [[2 1 1 4 :center
                     :center 1.3 :rem :column]
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



    (map-indexed (fn [i [sm tasks id fdate tdate] ]
           ^{:key (str "project" id)}
                   [:div {:style
                          (m7/css
                           [[(+ 3 (int (/ i 2))) 1
                             (if (= (mod i 2) 0) 1 3) 2
                             :flex-start :flex-start 0.8 :rem :column]
                            [1 70 90 0.3]
                            []
                            {:gap "20px"}])
                          }
                    [:div {:style (merge
                                   {:background-color (hsl [1 70 70 0.3])
                                    :min-height "70px"
                                    :width "100%"
                                    }
                                   {:font-size "1.2rem"
                                    :background-image "linear-gradient(1rad, hsla(1rad, 70%, 70%, 0.8), hsla(2rad, 70%, 90%, 0.2))"})}
                     #_(str id  ". " sm)
                     sm
                     ]


                    [:div {:style (merge
                                   {:background-color (hsl [1 70 70 0.3])
                                    :width "100%"
                                    }
                                   {:font-size "1.2rem"
                                    :background-image "linear-gradient(1rad, hsla(1.5rad, 60%, 70%, 0.8), hsla(2rad, 70%, 90%, 0.2))"})}

                     (str (.format (moment tdate) "MMM YYYY") "-" (.format (moment fdate) "MMM YYYY"))  ]
                    (map-indexed (fn [i {:keys [task]}]
                                   ^{:key (str "t" i id)}
                                   [:div {:style {:background-color
                                                  (hsl [1 70 70 0.3])}}
                                    task]) tasks)
                    ])
                 (reverse (sort-by
                           (fn [a] (nth a 3))
                           (d/q '[:find  ?t ?br ?p ?f ?to
                                  :where
                                  [?p :rm/task ?t]
                                  [?p :rm/breakdowns ?br]
                                  [?p :rm/to-date ?f]
                                  [?p :rm/from-date ?to]
                                  ] @conn)
                           )))

    [:div {:key (gensym)
           :style (m7/css
                   [[9 1
                     3 2
                     :flex-end :flex-end 0.8 :rem
                     :column]
                    [1 80 90 0.4]
                    [] {:width "100%"
                        :gap "20px"
                        :height "670px"
                        :justify-self :end
                        :align-self :end
                        :z-index 1}])}
     (map
      (fn [c]
        [:div {:key (gensym)
               :style {:background-color (hsl [1.3 70 70 0.5])
                       :width "100%"
                       }}
         (:rm/course c)])
      (take 15 db/course))
     ]

    ]])
