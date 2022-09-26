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
            (font/fv [[1 4] [1 1] [1 2] [2 1]])
            (merge
             (grid [100 :vh 100 :vw
                    (take 24 (repeat [8 :vh]))
                    (take 100 (repeat [8 :vh]))])
             {:background-color (hsl [3 20 98 1])
              :gap ".1rem"})
            )}


     [:div {:key (gensym)
            :style (m7/css
                    [[3 10 3 11  :center :center 2.2 :rem ]
                     [1 70 90 .4] [] {:gap "1rem"
                                      :z-index 2}])

            }
      (ffirst
       (d/q '[:find  ?s
              :where
              [?e :rm/code :couch]
              [?e :rm/summery ?s]] @conn))
      ]

     [:div {:key (gensym)
            :style (m7/css
                    [[3 10 14 7  :center :center 2.2 :rem ]
                     [2 70 90 .4] [] {:gap "1rem"
                                      :z-index 4}])



            }
      [file/file-input-background4]
      ]




     (map-indexed (fn [i [task sum row col id]]
                    [:div {:key (gensym)
                           :style (m7/css
                                   [[(+ 13 (* i 3))
                                     3 14 7 :center :center 1.7 :rem]
                                    [2 70 90 .7] [] {:gap "1rem"
                                                     :padding "2rem"}
                                    (font/fv [[1 4] [1 1] [1 2] [2 1]])])}


                     task])
                  (d/q '[:find  ?t ?s ?r ?c ?p
                         :where
                         [?e :rm/code :abc]
                         [?e :rm/projects ?p]
                         [?p :rm/task ?t]
                         [?p :rm/summery ?s]
                         [?p :rm/row ?r]
                         [?p :rm/col ?c]
                         ] @conn))

     (map-indexed (fn [i [task sum row col id]]
                    [:div {:key (gensym)
                           :style (m7/css
                                   [[(+ 13 (* i 3)) 3 3 11 :center :center 1.2 :rem]
                                    [2 70 90 .7] []
                                    {:gap "1rem"}
                                    ])}


                     sum])
                  (d/q '[:find  ?t ?s ?r ?c ?p
                         :where
                         [?e :rm/code :abc]
                         [?e :rm/projects ?p]
                         [?p :rm/task ?t]
                         [?p :rm/summery ?s]
                         [?p :rm/row ?r]
                         [?p :rm/col ?c]
                         ] @conn))



     ]))


(comment
  (def conn
    (d/create-conn schema))

  (d/transact! conn data))
