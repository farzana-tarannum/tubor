(ns math2.fss
  (:require
   [math2.file :as file]
   [react]
   [math2.bdmap :as bdmap]
   [clojure.string :as str]
   [clojure.walk :as w]
   [defun.core :refer [defun fun]]
   [math2.solution :as sol]
   [math2.solve :as sl]
   [math2.math7 :as m7 :refer
    [grid hsl css space size path ve sec]]))










(defn fspdf [file]
  [:div
   [:iframe {:src file
             :style {
                     :width "100vw"
                     :height "600vh"}}]])

(defn fsall [files]
  [:div {:style (merge
                 (grid [100 :vh 100 :vw
                        (take 24 (repeat [8 :vh]))
                        (take 20 (repeat [8 :vh]))])

                 {:background-color (hsl [1 70 70 0.05])
                  :width "40%"

                  :gap ".1rem"})}


   (map
    (fn [file]
      [:div {:style (merge (m7/css
                            [[1 12 1 9 :center :center
                              1.8 :rem :column]
                             [1 70 90 0.1] []
                             ])
                           {})}
       [:img
        (merge
         {:height "100%"}
         {:src file})]])
    files)
   ]


  )


#_(defn fs [file flags]
  [:div {
         :style {:margin "0px"
                 :background "#0e0e0e"
                 :height "100%"}}
   (map (fn [[x y w]]
          [:div#unicodeCharacter
           {:style {:position "absolute"
                    :display :flex
                    :justify-content :center
                    :width (str w "px")
                    :background-color "hsla(1rad,70%,90%,1)"
                    :font-size "10px"
                    :transform (str "translate(" x "px, " y "px)")
                    }}
           (char 0x2714)
           ]) flags)



   [:img {:on-mouseMove handle-mouse-move
          :style {:display "block"
                  "-webkit-user-select" "none"
                  :margin "auto"
                  :cursor "zoom-in"
                  :background-color "hsl(0, 0%, 90%)"
                  :transition "background-color 300ms"}
          :src file
          :width "656"
          :height "827"}]
   ])

(defn fs2 [files flags]
  (let [[pos set-pos] (react/useState "abc")]
    [:div {
           :style {:margin "0px"
                   :background "#0e0e0e"
                   :height "100%"}}
     [:div#unicodeCharacter
      {:style {:position :fixed
               :display :flex
               :justify-content :center
               :width (str 400 "px")
               :background-color "hsla(1rad,70%,90%,1)"
               :font-size "50px"
               :transform (str "translate(" 1200 "px, " 100 "px)")
               }}
      pos
      ]
     (map (fn [[x y w]]
            [:div#unicodeCharacter
             {:style {:position "absolute"
                      :display :flex
                      :justify-content :center
                      :width (str w "px")
                      :height (str "1.7em")
                      :background-color "hsla(1rad,70%,90%,1)"
                      :font-size "10px"
                      :transform (str "translate(" x "px, " y "px)")
                      }}
             (char 0x2714)
             ]) flags)

     (map
      (fn [file]

        [:img {:on-mouseMove (fn [event]
                               (let [x (.-clientX event)
                                     y (.-clientY event)]
                                 (set-pos (str "x=" x "y=" (+ y js/window.pageYOffset)))))
               :style {:display "block"
                       "-webkit-user-select" "none"
                       :margin "auto"
                       :cursor "zoom-in"
                       :background-color "hsl(0, 0%, 90%)"
                       :transition "background-color 300ms"}
               :src file
               :width "956"
               :height "1227"}])
      files)
     ]))
