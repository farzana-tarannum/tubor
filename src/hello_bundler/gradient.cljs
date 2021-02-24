(ns hello-bundler.gradient)

(defn grad []
  [:div {:style {:display :flex
                 :justify-content :center
                 :align-items :center
                 :flex-direction :column
                 :position :relative
                 :gap "2rem"
                 :height "80vh"}}
   [:input.gradient-input {:type :text
                          }]
   [:div.test-element]]
  )
