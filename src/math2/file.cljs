(ns math2.file
  (:require
   [math2.math7 :as m7]
   [reagent.core :as r]
   [react]))

(defn file-reader [file update-image]
  (let [reader (js/FileReader.)]
    (.readAsDataURL reader file)
    (.addEventListener reader "load"
                       (fn [e]
                         (update-image (.-result e.target))
                         (.setItem js/localStorage "file-input-img" (.-result e.target))))
    ))



(defn file-reader2 [file update-image]
  (let [reader (js/FileReader.)]
    (.readAsDataURL reader file)
    (.addEventListener reader "load"
                       (fn [e]
                         (update-image (.-result e.target))))))

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



(defn upload-size2 [o-files update-image tar ]
  (let [
        n-files (.-length o-files)
        n-bytes (map (fn [file] (.-size file)) o-files)
        file-names (map (fn [file]
                          (do
                            (file-reader2 file update-image)
                            (.-name file))) o-files)
        ]
    (tar [n-files
          (first n-bytes)
          (first file-names)])

    ))

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


     [:div {:style {:width (m7/np [100 :%])
                    :height (m7/np [100 :%])
                    :background-size :cover
                    :background-position :center

                    :background-color "hsl(34,70%,70%)"
                    :background-image (str "url(" img   ")")}}

      ]

     [:input {:type :file
              :multiple true
              :on-change (fn [e]
                           (upload-size (.-files e.target) update-image)
                           )}
      ]]))


(defn file-input-background3 [name]
  (let [[img update-image] (react/useState "")
        [target set-target] (react/useState [])]
    [:div {:style {:width "100%"
                   :height "100%"
                   :background-size :cover
                   :background-position :center
                   :background-color "hsl(34,70%,70%)"
                   }}



     [:div {:style (merge
                    (m7/grid [100 :vh 100 :vw
                           (take 5 (repeat [20 :vh]))
                           (take 15 (repeat [20 :vh]))])
                    {:background-color (m7/hsl [1 70 70 1])
                     :gap ".1rem"})}


      (map-indexed
       (fn [i tar]
         (let [local (str "file-input-img" "-bio" i)
               img (.getItem js/localStorage local)]
           [:div {:style
                  (m7/css
                   [[(* i 4) 4  4 1
                     :center :center  2 :rem :column]
                    [3.5 70 (+ 50 (* 5 5))  .7] []
                    {:background-image (str "url(" img   ")")
                     :gap ".1rem"
                     :z-index 10}])}
            [:div
             [:div tar]
             [:div local]
             [:div img]]

            ]))
       target
       )
      ]

     [:input {:type :file
              :multiple true
              :on-change
              (fn [e]
                (let [o-files (.-files e.target)
                      n-files (.-length o-files)
                      n-bytes (map (fn [file] (.-size file)) o-files)
                      file-names (map (fn [file] (.-name file)) o-files)
                      files (map (fn [file] file) o-files)]
                  (set-target file-names)
                  (map
                   (fn [file n]
                     (file-reader2
                      file
                      (fn [data]
                        (do
                          (.setItem js/localStorage
                                    (str
                                     "file-input-img"
                                     "-bio" n) data)))))
                   files (range 0 n-files))
                  ))}]]))
