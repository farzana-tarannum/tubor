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
   ))




(def conn
  (d/create-conn db/schema))

(d/transact! conn db/data)




(defn resume []
  (let [[name set-name] (react/useState "")
        ref (react/useRef)]
    [:div {:style
           (into
            (merge
             (grid [100 :vh 100 :vw
                    (take 24 (repeat [8 :vh]))
                    (take 40 (repeat [8 :vh]))])
             {:background-color (hsl [1 70 90 .1])
              :gap ".14rem"})
            )}


     [:div {:key (gensym)
            :style (css
                    [[1 3 1 12  :center :center 2.5 :rem :column]
                     [1 70 90 .4] [] {
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

                     ])

            }

      [:div "Ashrar Ahmed"]
      [:div {:style {:font-size "1.1rem"}} "H# 192, R# 2, Mirpur DOHS, DHAKA, BANGLADESH"]
      [:div {:style {:font-size "1.3rem"}}
       "+8801711961024, jaharapi@protonmail.com"]]

     [:div {:key (gensym)
            :style (m7/css
                    [[1 3 13 3  :center :center 2.2 :rem ]
                     [2 70 90 .4] [] {:gap "1rem"
                                      :z-index 4}])



            }
      [file/file-input-background4]]


     [:div {:key (gensym)
            :style (css
                    [[4 4 1 15  :center :center 2.3 :rem :column]
                     [1 70 90 .4] [] {
                                      :padding "20px"

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

     [:div {:key (gensym)            :style (css
                                             [[8 5 1 15  :center :center 1.6 :rem :column]
                     [1 70 90 .4] [] {
                                      :padding "20px"
                                      :font-family "Amazonia Var"
                                      :gap "1rem"
                                      :z-index 2}
                     ])

            }
      db/summery2
      ]




     (map-indexed (fn [i [task sum row col id]]
                    [:div {:key (gensym)
                           :style (m7/css
                                   [[(+ 13 (* i 4))
                                     4 13 3 :center :center 1.2 :rem]
                                    [2 70 90 .7] [] {:gap "1rem"
                                                     :padding "2rem"}
                                    (font/fv [[1 4] [1 1] [1 2] [2 1]])])}


                     task])
                  (sort-by first <
                           (d/q '[:find  ?t ?s ?r ?c ?p
                                  :where
                                  [?e :rm/code :devops]
                                  [?e :rm/projects ?p]
                                  [?p :rm/task ?t]
                                  [?p :rm/summery ?s]
                                  [?p :rm/row ?r]
                                  [?p :rm/col ?c]
                                  ] @conn)))

     (map-indexed (fn [i [task sum row col id]]
                    [:div {:key (gensym)
                           :style (m7/css
                                   [[(+ 13 (* i 4)) 4 1 12 :center :center 1.5 :rem]
                                    [2 70 90 .3] []
                                    {:gap "1rem"
                                     :padding "10px"
                                     }
                                    ])}


                     sum])
                  (sort-by first <
                   (d/q '[:find  ?t ?s ?r ?c ?p
                          :where
                          [?e :rm/code :devops]
                          [?e :rm/projects ?p]
                          [?p :rm/task ?t]
                          [?p :rm/summery ?s]
                          [?p :rm/row ?r]
                          [?p :rm/col ?c]
                          ] @conn)))



     ]))


(comment
  (def conn
    (d/create-conn schema))

  (d/transact! conn data))
