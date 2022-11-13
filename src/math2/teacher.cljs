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



(defn resume []
  (let [[name set-name] (react/useState "")
        [rows set-rows] (react/useState 11)
        c (count (d/q '[:find  ?t ?s ?r ?c ?p
                        :where
                        [?e :rm/code :devops]
                        [?e :rm/projects ?p]
                        [?p :rm/task ?t]
                        [?p :rm/summery ?s]

                        [?p :rm/row ?r]
                        [?p :rm/col ?c]
                        ] @conn))
        trow (fn [i col]
               (if (> col 9)
                 (+ 16 (count db/course) (* i 4))
                 (+ 16 (* i 4))))
        ref (react/useRef)
        vh 8
        tvh 15]
    [:div {:style
           (merge
            (grid [100 :vh 100 :vw
                   (take tvh (repeat [vh :vh]))
                   (take (* tvh 6) (repeat [vh :vh]))])
            {:background-color (hsl [1 70 90 0.1])
             :padding "50px"
             :gap ".2rem"})

           }


     [:div {:key (gensym)
            :style (css
                    [[1 3 1 (/ (* 4 tvh) 5)  :center :center 2.5 :rem :column]
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

                     ])

            }

      [:div "Ashik Ahmed"]
      [:div {:style {:font-size "1.1rem"}} "H# 192, R# 2, Mirpur DOHS, DHAKA, BANGLADESH"]
      [:div {:style {:font-size "1.3rem"}}
       "+8801711961024, jaharapi@protonmail.com"]]

     [:div {:key (gensym)
            :style (m7/css
                    [[1 (/ 24 vh) (inc (/ (* 4 tvh) 5)) (/ tvh 5)  :center :center 2.2 :rem ]
                     [2 70 90 0.4] [] {:gap "1rem"
                                      :z-index 4}])



            }
      [file/file-input-background4]]


     [:div {:contenteditable :true
            :key (gensym)
            :style (css
                    [[(inc (/ 24 vh )) (/ 40 vh) 1 tvh  :center :center 2.3 :rem :column]
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







     (let  [car (vec (map vec (partition 5
                                         ["PostGreSQL" "SQL-Query" "Indexing"
                                          "Functions" "PostGIS"

                                          "scripting" "GraalVM" "shell" "go lang"
                                          "awk"
                                          "Command Line" "git ssh"  "wireshark"
                                          "ip netstat ps"
                                          "vi emacs"
                                          "Linux" "Archlinux" "systemd" "alphine"
                                          "ubtuntu"
                                          "JVM"
                                          "Groovy" "jetty" "clojure"  "jdbc"
                                          "Virtualiztoin" "docker" "KVM" "kubernetes" "ingress"

                                          "reactjs" "d3.js" "react hooks"
                                          "redux" "animation"

                                          "audio/video" "gstreamer" "pluseaudio" "vnc" "rdp"
                                          "db" "redis" "graphdb" "mongodb"

                                          "event streaming" "kafka" "" "" ""
                                          "editor" "vi"
                                          "docker"    "redis"   "branch"
                                          "leadership"
                                          "RESTful"
                                          "react"

                                          "emacs" "CentOS" "Ubuntu."
                                          "reactjs" "webrtc" "ingress" "kafka" "groovy"])))]

       (for [i (range 0 9)
             j (range 0 5)]
         [:div {
                :key (gensym)
                :style (css
                        [[(+ (/ (* 8 9) vh) i) 1 (+ 1 (* 3 j)) 3
                          :center :center 1.6 :rem ]
                         [1 70 (if (= j 0) 70 90) 0.4] []
                         {


                          :font-family "Roboto Flex"
                          :gap "1rem"
                          :z-index 21}
                         ])

                }

          (get-in car [i j] "")
          ])
       )




     #_[:div {:contenteditable :true
              :key (gensym)            :style (css
                                               [[8 3 1 15  :center :center 1.6 :rem :column]
                                                [1 70 90 0.4] []
                                                {

                                                 :line-height 1.5
                                                 :padding "20px"
                                                 :font-family "Amazonia Var"
                                                 :gap "1rem"
                                                 :z-index 2}
                                                ])

              }
        (marking
         ["Linux" "JVM" "Groovy" "React" "PostGreSQL"
          "Docker," "Kubernetes," "RESTful"]
         db/summery3
         (m7/hsl [1.2 100 70 0.8]))
        ]




     (map-indexed (fn [i [task sum row col id]]
                    [:div {:key (gensym)
                           :contenteditable :true
                           :style (m7/css
                                   [[(trow i row)
                                     4
                                     13 3 :center :center 1.2 :rem]
                                    [1 70 90 0.4]
                                     [] {:gap "1rem"
                                         :padding "2rem"}
                                    (font/fv [[1 4] [1 1] [1 2] [2 1]])])}


                     (str col " " id " " task)])
                  (sort-by #(nth % 2) <
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
                           :contenteditable :true
                           :style (m7/css
                                   [[(trow i row) 4 1 12 :center :center 1.5 :rem]
                                    [1 70 90 0.3]
                                    []
                                    {:line-height 1.5
                                     :gap "1rem"
                                     :padding "10px"
                                     }
                                    ])}
                     sum
                     #_(marking ["SQL," "Java,"  "scripting" "scripting," "awk," "jetty," "ssh" "PostGreSQL." "wireshark" "vi" "leadership" "embaded" "boot" "KVM" "virtualiztoin" "docker" "docker" "vnc" "rdp" "GraalVM" "redis" "git" "git," "fork" "merge" "branch" "Linux" "Groovy" "RESTful" "react" "d3.js" "iptables," "emacs" "CentOS" "Ubuntu."
                               "Archlinux" "reactjs" "webrtc" "ingress" "kafka" "groovy"]
                              sum
                              (m7/hsl [1.2 100 70 0.8]))
                     ])
                  (sort-by #(nth % 2) <
                           (d/q '[:find  ?t ?s ?r ?c ?p
                                  :where
                                  [?e :rm/code :devops]
                                  [?e :rm/projects ?p]
                                  [?p :rm/task ?t]
                                  [?p :rm/summery ?s]

                                  [?p :rm/row ?r]
                                  [?p :rm/col ?c]
                                  ] @conn)))

     (let [tranings (vec (sort-by second > db/course))]
       (for [i (range 0 (count db/course))
             col [true false]
             :let  [course (get-in  tranings [i 0])
                    tm (get-in tranings [i 1])]
             ]
         (if col
           [:div {:key (gensym)
                  :contenteditable :true
                  :style (m7/css
                          [[(+ 8 (* 4 c) i) 1 1 12 :flex-start :center 1.5 :rem]
                           [1 70 70 0.3]
                           []
                           {:line-height 1.5
                            :gap "1rem"
                            :padding "10px"
                            }
                           ])}

            course]
           [:div {:key (gensym)
                  :contenteditable :true
                  :style (m7/css
                          [[(+ 8 (* 4 c) i
                               ) 1 13 3 :center :center 1.5 :rem]
                           [1 70 70 0.3]
                           []
                           {:line-height 1.5
                            :gap "1rem"
                            :padding "10px"
                            }
                           ])}

            tm])))





     ]))


(comment
  (def conn
    (d/create-conn schema))

  (d/transact! conn data))
