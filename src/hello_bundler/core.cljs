(ns ^:figwheel-hooks hello-bundler.core
  (:require [reagent.dom :as rdom]
            [reagent.core :as r]
            [clojure.string :as str]
            [hello-bundler.template :as t]
            [hello-bundler.file :as file]
            [hello-bundler.bdmap :as bmap]
            [hello-bundler.math :as math]
            [hello-bundler.gradient :as gradient]
            [react]))

;;(defn ^:before-load my-before-reload-callback []
;;    (println "BEFORE reload!!!"))


(defn ^:after-load my-after-reload-callback []
    (println "AFTER reload!!!"))


;
(defn template []
  [:div {:style {:background "#F8DAFA"
                 :display :grid
                 :grid-template-columns
                 "repeat(auto-fit, minmax(200px, 1fr))"
                 :grid-auto-rows "200px"
                 :height "100vh"
                 :grid-auto-flow :dense
                 }}
   (map (fn [id]
          [:div {:id id
                 :style {
                         :grid-row (str "span " (rand-int 3))
                         :grid-column (str "span " (rand-int 4))
                         :width "100%"
                         :box-shadow
                         "2px 2px 2px 1px rgba(0,0,0,0.06)"}}
           (str "hello" id)])
        (range 0 50))])

(defn template2 []
  [:div {:style {:margin "0 1rem"
                 :display :grid
                 :grid-template-rows "3fr minmax(100px, 350px) max-content auto 1fr"
                 :justify-items :center
                 :height "100vh"}}
   (map (fn [id row]
          [:div {:id id}
           row])
        (range 0 5)
        [[:div ""]
         [:div "image"]
         [:div "header"]
         [:div {:style {:display :flex} } (map (fn [id] [:div {:id (str "mne" id)}
                         (str "menu" id)]
                 )
               (range 0 9))]
         [:div ""]])])



(defn hero2 []
  [:div {:style {:display :flex
                  :flex-direction :column
                  :align-items :center}}
    [:div {:style {:width "77vw"
                   :display :grid
                   :grid-gap "1rem"
                   :grid-auto-rows "40vh"
                   :grid-template-columns "1fr 2fr 1fr"
                   }}
     [:div {:style {:background-color "#ddd"}} "grid1"]
     [:div {:style {:background-color "#eee"
                    :grid-row-end "span 2"}} "grid2"]
     [:div {:style {:background-color "#ddd"}} ""]
     [:div {:style {:background-color "#ddd"}} ""]
     [:div {:style {:background-color "#ddd"}} ""]]])

(defn menu-overlay [direction]
  [:div
   {:style {
            :font-size "1rem"
            :cursor "pointer"
            :width "80vw"
            :height "30vh"
            :position :absolute
            :background-color :white
            :display "block"
            :animation (str "hello 700ms ease-in-out 0s 1"
                            (if direction "" "reverse"))
            }}
   [:div {:style {:display :flex
                  :padding "1rem"
                  }
          }
    (map-indexed (fn [i vals]
                   [:div
                    {:key (str "absolute" i )
                     :style {:width "20vw"
                             :font-variation-settings  "'wdth' 70, 'wght' 444, 'CNTR' 20"
                             :font-weight "450"}}
                    (map-indexed
                     (fn [i val]
                       [:div {:key (str "mmm" i)}
                        val])
                     vals)
                    ])
                 [
                  ["APPAREL" "Men" "Woman"]
                  ["HOME & LIVING" "Home Decor""Kitchen"]
                  ["JUTE ITEMS" "Jute Shika" "Jute Mates"
                   "Jute Sandals" "Jute Bags"]
                  ["ACCESSORIES" "Gift Voucher"]
                  ["COVID-19 SafeTY" "Safety Items" "Cleaning equipments"]
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
                    ;;                   :left "8rem"

                    :display :flex
                    ;; :width "80vw"
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
           [:path {:d "M 0,5 L 7,10 L 12,5"
                   :style {:fill "none"
                           :stroke "#1E1935"
                           :stroke-width "1.2"}}]

           ]
          ])
       [
        "ALL CATEGORIES"
        "APPAREL"
        "HOME & LIVING"
        "JUTE ITEMS"
        "ACCESSORIES"
        "COVID-19 SafeTY"
        "OUR BLOG"
        "IN The NEWS"]
       )
      ]
     (if (< menu-num 10)
       [:div {:on-mouse-leave (fn [e]
                                (set-menu-num 10))}
        [menu-overlay (if (= set-menu-num 9) false true)]])
     ]))










(defn logo []
  (let [[click update-click] (react/useState 5)
        dash (str "2," click ",5")]
    [:svg {:style {:background-color :white}
           :viewBox "0 0 100 100"
           :height "5vh"
           :width "5vh"}
     [:path {:on-click (fn [e]
                         (update-click
                          (if (< click 45 )
                            (+ click 5)
                            5)
                          ))
             :style {:fill :white
                     :stroke
                     "hsl(78,100%,50%)"
                     :stroke-width ".5rem"}
             :stroke-dasharray dash
             :stroke-linecap :round
             :d "m 44.055121,77.976268 a 24.948597,38.982552 0 0 1 4.22425,-54.547194 24.948597,38.982552 0 0 1 34.937331,6.23568 24.948597,38.982552 0 0 1 -3.757168,54.63056 24.948597,38.982552 0 0 1 -34.987561,-5.505316"}]]))


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


(defn animate-circle []
  (let [ref (react/useRef)
        _ (react/useEffect
           (fn []
             (let [rf (. ref -current)]
               (. rf animate
                  (clj->js
                   [{:transform
                     "translateY(0)"
                     }
                    {:transform
                     "translateY(450px)"}])
                  (clj->js
                   {:duration 1000
                    :iterations 4})))
             ))]
    [:div {:ref ref
           :style {:height "200px"
                   :border-radius "100%"
                   :background-color :#ddd
                   :width "200px"}}

     ]))




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
       (if (= toggle-chat 0)
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
          [animate-circle]
          ])
       [test-canvas]
       ])
  )


















(defn color-input [time-color update-time-color]
  [:div {:style {}}
   [:input {:type "text"
            :value time-color
            :on-change #(update-time-color
                         (-> % .-target .-value))}]])

(defn clock [time-color]
  (let [[timer update-time] (react/useState (js/Date.))
       ;; time-str (-> timer .toTimeString (str/split " ") first)
        ]
    (react/useEffect
     (fn []
       (let [i (js/setInterval #(update-time (js/Date.)) 2000)]
         (fn []
           (js/clearInterval i)))))
    [:div.example-clock
     {:style {:background-color time-color
              :color :#111 }}
     (str timer) ]))


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
   [file/file-input-background]

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


(defn greeting [message]
  [:div message])


(defn layout2 []
  [:div {:style {
                 :grid-row "4/5"
                 :grid-column "2/3"
                   :display :grid
                   :grid-gap ".2rem"
                   :grid-auto-rows "40vh"
                   :grid-template-columns "1fr 2fr 1fr"
                   }}
   [:div {:style {:background-color "#ddd"}} ""]
     [:div {:style {:background-color "#eee"
                    :grid-row-end "span 2"}}
      [bmap/stat2]]
   [:div {:style {:background-color "#ddd"}}
    [:div {:style {:display :flex
                   :flex-direction :column}}
     [:div {:style {:height "50%"
                    :width "50%"}}
      [bmap/stat1] ]

     [simple-example]]]
   [:div {:style {:background-color "#ddd"}}
    [file/file-input]]
     [:div {:style {:background-color "#ddd"}} ""]])

(defn template4 []
  [:div {:style {:display :grid
                 :grid-template-columns "1fr 7fr 1fr"
                 :grid-template-rows "auto 1rem
repeat(auto-fill)"
                 :background-color "hsl(10,70%,90%)"}}
   [:div {:style {
                  :grid-row "1/10"
                  :grid-column "1/2"
                  :background-color "hsl(180,90%,90%)"}}
    ""]

   [:div {:style {
                  :grid-row "1/10"
                  :grid-column "3/4"
                  :background-color "hsl(180,90%,90%)"}}
    ""]

   [menu-ps]
   [:div {:style {:grid-column "1/4"
                  :grid-row "2/3"
                  :background-color "#ddd"
                  :z-index 1
                  :box-shadow "0 2px 2px -1px #aaa"
                  }
          } "" ]


   [layout1]
   [layout2]

   [:div {:style {:display :grid
                  :grid-column "2/3"
                  :grid-row "5/6"
                  :grid-template-columns (str "repeat" "("
                                        "auto-fit" "," "minmax" "("
                                        "12vw" "," "1fr"
                                        "))")
                  :grid-gap "1px"
                  :grid-auto-rows "20vh"
                  :grid-auto-flow "dense"}}
    (map-indexed
     (fn [i v]
       [:div {:key (str "grid-auto-" i)
              :style {
                      :grid-row (str "span " (rand-int 0))
                      :grid-column (str  "span " (rand-int 0))
                      :background-color (str "hsl" "("
                                           v  ","  "45%" "," "70%"  ")")}}
        ""])
     (range 0 1200 30))]

   (comment   [math/template1])
   (comment[gradient/grad])
   ])


(def functional-compiler (r/create-compiler {:function-components true}))
(comment
  (template4)
  )
(defn render-simple []
  (rdom/render
   (math/template1)
   (js/document.getElementById "app") functional-compiler))

(render-simple)
