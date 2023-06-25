(ns ^:figwheel-hooks math2.core
  (:require
   [reagent.dom :as rdom]
   [reagent.core :as r]
   [clojure.string :as str]
   [math2.file :as file]
   [math2.math7 :as math7]
   [math2.physics11 :as p11]
   [math2.math100 :as m100]
   [math2.math19 :as m19]
   [math2.frection :as frac]
   [math2.math :as mtemp]
   [math2.trig :as trig]
   [math2.stat :as stat]
   [math2.bdmap :as bdmap]
   [math2.solve :as s]
   [math2.teacher :as teacher]
   [clojure.walk :as w]
   [math2.transfrom :as trans]
   [math2.physics100 :as py100]))

;;(defn ^:before-load my-before-reload-callback []
;;    (println "BEFORE reload!!!"))


(defn ^:after-load my-after-reload-callback []
  (println "AFTER reload"))



(def functional-compiler (r/create-compiler {:function-components true}))

(defn handle-mouse-move [event]
  (let [x (.-clientX event)
        y (.-clientY event)]
    (println "Mouse coordinates: x=" x "y=" y)))



(defn fs []
  [:div {
         :style {:margin "0px"
                  :background "#0e0e0e"
                 :height "100%"}}
   (let [x 852
         y 595]
     [:div#unicodeCharacter
      {:style {:position "absolute"
               :font-size "24px"
               :transform (str "translate(" x "px, " y "px)")}}
      (char 0x2714)
      ])



   [:img {:on-mouseMove handle-mouse-move
          :style {:display "block"
                  "-webkit-user-select" "none"
                  :margin "auto"
                  :cursor "zoom-in"
                  :background-color "hsl(0, 0%, 90%)"
                  :transition "background-color 300ms"}
          :src "resume_nov12_Page_4.jpg"
          :width "656"
          :height "927"}]
   ])

(defn render-simple []
  (rdom/render
   [fs]
   #_[m19/home-work19]
   #_[m100/app]
 #_[trig/ladder]
 #_[trig/freq3]
   #_[m100/map-family22]
   #_[m100/airplane]
   #_[m100/home-planets-banners]
   #_[m100/chem-ratee]
   #_[teacher/resume2]
   (js/document.getElementById "app")
   functional-compiler))

(render-simple)
