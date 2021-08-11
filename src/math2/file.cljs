(ns math2.file
  (:require [reagent.core :as r]
            [react]))

(defn file-reader [file update-image]
  (let [reader (js/FileReader.)]
    (.readAsDataURL reader file)
    (.addEventListener reader "load"
                       (fn [e]
                         (update-image (.-result e.target))
                         (.setItem js/localStorage "file-input-img" (.-result e.target))
                         (js/console.log (.-name file) "data:" (.-result e.target))))
    ))

(defn upload-size [o-files update-image]
  (let [
        n-files (.-length o-files)
        n-bytes (clj->js (map (fn [file] (.-size file)) o-files))
        file-names (clj->js (map (fn [file]
                                   (do
                                     (file-reader file update-image)
                                     (.-name file))) o-files))
        ]
    (js/console.log "number of files - " n-files " bytes" n-bytes
                    " names " file-names)
    (js/console.log o-files)))

(defn file-input []
  (let [[img update-image] (react/useState "")]
    [:div {:style {:background-size :cover
                   :background-position :center

                   :background-color "hsl(34,70%,70%)"
                   :background-image (str "url(" img   ")")}}

     [:img {:src (if (> (count img) 0) img (.getItem js/localStorage "file-input-img" ))
            :style {
                    :height "300px"
                    :width "100%"
                    :object-fit :cover}}]
     [:input {:type :file
              :multiple true
              :on-change (fn [e]
                           (upload-size (.-files e.target) update-image)
                           )}
      ]]))

(defn file-input-background []
  (let [[img update-image] (react/useState "")]
    [:div {:style {:width "80vw"
                   :height "80vh"
                   :grid-row "1 / 2"
                   :grid-column "1 / 3"
                   :background-size :cover
                   :background-position :center
                   :background-color :black
                   :background-image (str "url(" img   ")")}}


     [:input {:type :file
              :multiple true
              :on-change (fn [e]
                           (upload-size (.-files e.target) update-image)
                           )}
      ]]))

(defn file-input-background2 []
  (let [[img update-image] (react/useState "")]
    [:div


     [:div {:style {:width "100vw"
                    :height "100vh"
                    :background-size :cover
                    :background-position :center

                    :background-color "hsl(34,70%,70%)"
                    :background-image (str "url(" img   ")")}}]

     [:input {:type :file
              :multiple true
              :on-change (fn [e]
                           (upload-size (.-files e.target) update-image)
                           )}
      ]]))
