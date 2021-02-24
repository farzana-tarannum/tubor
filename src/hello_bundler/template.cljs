(ns hello-bundler.template)

(def x2 (fn [x y]  [(+ x y) 5 2 24]))

(def x3 (fn [x y] (+ (- x 4) y)))

(def template
  (fn []
    [:div {:style
           {
            :display :grid
            :grid-template-columns "1fr 2fr 1fr"
            :grid-template-rows "20vh 20vh 20vh"
            :color :green
            :font-size (x3 30 "px")
            }}
     [:div {:style {:background-color "#eeeeee"}}  "hello world2"]
     [:div {:style {:background-color "#111111"}} (str (x2 78 300))]
     [:div {:style {:background-color "#dddddd"}}  "a"]
     [:div {:style {:background-color "#bbbbbb"}} "b"]
     [:div {:style {:background-color "#999999"}} "aaa"]
     [:div {:style {:background-color "#cccccc"}} "aber"]
     [:div {:style {:background-color "#bb0000"}} "abc"]
     [:div {:style {:background-color "#335522"}} "sf"]
     [:div {:style {:background-color "#A52566"}} "fs"]


     ]))
