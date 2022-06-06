(ns math2.abc)

(comment
  [m7/x `[= y [+ [- [:p x 2] [:m 3 x ]] 1]]]
  [m7/x `[= y [-  [:m 2 x] 5]]]
  [m7/x `[= [-  [:m 2 x ] 5] [+ [- [:p x 2] [:m 2 x ] [:m 2 x ] x]  1]]]
  [m7/x `[= 0 [+ [- [:p x 2] [:m 5 x] ]  6]]]

  [m7/x `[= 0 [:m [:b [- x 2]] [:b [- x 3]]]]]



  [m7/x `[= 0 [- x 3]]]
  [m7/x `[= 3 x]]
  [m7/x `[= y [-  [* 2 3 ] 5]]]
  [m7/x `[= y [-  [* 2 3 ] 5]]]
  [m7/x `[= y 1]]

  [:div ]

  [m7/x `[= 0 [- x 2]]]
  [m7/x `[= 2 x]]
  [m7/x `[= y -1]])

#_[m7/x `[= [-   5] [+ [:p x 2]  x [- 5] 6 ]]]


#_[m7/x
   (m7/eq2
    `[= x
      ~(let [x 'y
             p (fn [c d] `[:m ~c [:p ~x ~d]] )
             np (fn [c _] `[:m ~c ~x] )
             np1 (fn [c _] x)
             c (fn [c _] c)]


         (into ['+]
               (map (fn [c d ff]
                      (ff c d))
                    [ [1 2] [3 2]]
                    [ 1 0]
                    [ np c])))])]


#_[m7/x
   (w/postwalk
          (fn [y]
            (if (= y 'y)
              (let [[_ _ r]
                    (m7/eq2
                     `[= x
                       ~(let [x 'y
                              p (fn [c d] `[:m ~c [:p ~x ~d]] )
                              np (fn [c _] `[:m ~c ~x] )
                              np1 (fn [c _] x)
                              c (fn [c _] c)]


                          (into ['+]
                                (map (fn [c d ff]
                                       (ff c d))
                                     [ 2 3]
                                     [ 1 0]
                                     [ np c])))])]
                [:b r])
              y))
          (m7/eq2
           `[=
             27
             ~(let [cp (fn [c d e] `[:p ~e ~d])
                    p (fn [c d e] `[:m ~c [:p ~e ~d]] )
                    np (fn [c _ e] `[:m ~c ~e] )
                    np1 (fn [c _ e] e)
                    c (fn [c _ e] c)]


                (into ['+]
                      (map (fn [c d ff e]
                             (ff c d e))
                           [ [1 2] 3]
                           [ 2 2]
                           [cp p]
                           ['x 'y])))]))]

(comment
          (m7/x
           (m7/eq2
            `[=
              ~(let [cp (fn [c d e] `[:p ~e ~d])
                     p (fn [c d e] `[:m ~c [:p ~e ~d]] )
                     np (fn [c _ e] `[:m ~c ~e] )
                     np1 (fn [c _ e] e)
                     c (fn [c _ e] c)]


                 (into ['+]
                       (map (fn [c d ff e]
                              (ff c d e))
                            [2 3]
                            [1 1]
                            [np c]
                            ['y 'y])))
              x
              ]))


          (m7/x `[= [+ [:p [:b x] 2]
                     [:m 2 [:p y 2]]] 27])




          (m7/x `[= [+ [:p [:b ~(let [cp (fn [c d e] `[:p ~e ~d])
                                      p (fn [c d e] `[:m ~c [:p ~e ~d]] )
                                      np (fn [c _ e] `[:m ~c ~e] )
                                      np1 (fn [c _ e] e)
                                      c (fn [c _ e] c)]


                                  (into ['+]
                                        (map (fn [c d ff e]
                                               (ff c d e))
                                             [2 3]
                                             [1 1]
                                             [np c]
                                             ['y 'y])))] 2]
                     [:m 2 [:p y 2]]] 27])


          (m7/x `[= [+   ~(let [cp (fn [c d e] `[:p ~e ~d])
                                p (fn [c d e] `[:m ~c [:p ~e ~d]] )
                                np (fn [c _ e] `[:m ~c ~e] )
                                np1 (fn [c _ e] e)
                                c (fn [c _ e] c)]


                            (into ['+]
                                  (map (fn [c d ff e]
                                         (ff c d e))
                                       [(* 2 2) (* 2 3) 1]
                                       [2 1 2]
                                       [p np cp]
                                       ['y 'y 3])))
                     [:m 2 [:p y 2]]] 27])


          (m7/x `[= ~(let [cp (fn [c d e] `[:p ~e ~d])
                           p (fn [c d e] `[:m ~c [:p ~e ~d]] )
                           np (fn [c _ e] `[:m ~c ~e] )
                           np1 (fn [c _ e] e)
                           c (fn [c _ e] c)]
                       (conj
                        (into ['+]
                              (map (fn [c d ff e]
                                     (ff c d e))
                                   [6 (* 2 3) 1]
                                   [2 1 2]
                                   [p np cp]
                                   ['y 'y 3]))
                        `[- [* 3 [:p 3 2]]])
                       ) 0]))



        #_(m7/x `[= [+ [:m 6 [:p y 2]] [:m 6 y] [:m [:p 3 2] [:b [- 1 3]]]] 0])


        #_(m7/x `[= [+ [:m 6 [:p y 2]] [:m 6 y] [* [- 2] [:p 3 2] ]] 0])


        #_(m7/x `[= [+ [:m 1 [:p y 2]] [:m 1 y] [- 3]] 0])

#_[m7/x `[= [-  [:m 8 [:p x 2]]
                   [:m 9 x ] 14] 0]]




        #_[m7/x (m7/eq2 (w/postwalk
                       (fun ([[:m c [:p [:b [+ a b]] 2]]]
                             ['+ [:m c [:p a 2]] ['* 2 c a b] ['* c  [:p b 2]]])
                            ([y] y))
                       '[= 27 [+ [:p x 2] [:m 3 [:p [:b [+ [:m 2 y] 3]] 2]]]]))]


        #_[m7/x
         (m7/eq2 (w/postwalk (fun ([[:m a [:b c]]] c)
                                  ([y] y))
                             (m7/eq2 (w/postwalk
                                      (fun ([[:p [:b [+ a b]] 2]]
                                            [:b ['+ [:p a 2] ['* 2 a b] [:p b 2]]])
                                           ([y] y))
                                      '[= 27 [+ [:p x 2] [:m 3 [:p [:b [+ [:m 2 y] 3]] 2]]]]))))]

(comment
  [m7/x

   (m7/eq2 `[= [- [+ [:p x 2] [:m 2 x] ] 3] y])]



  [m7/x
   `[= [+ [:m 2 x]  1 ] y]]




  [m7/x

   (m7/eq2 `[= [:p x 2]  4])]


  (comment
    [m7/x
     (m7/eq2 `[= [- 3 [:p x 2]]  [+ x 1]])]



    [m7/x
     (m7/eq2 `[= [+ [:p x 2] x [- 2]] 0])]


    [m7/x
     (m7/eq2 `[= [+ [:p x 2] x [- 2]] 0])]



    [m7/x
     (m7/eq2 `[= [+ [:p x 2] [:m x [:b [- 2 1]]] [* 2 [- 1]]] 0])]

    [m7/x
     (m7/eq2 `[= [+ [:p x 2] [- [:m 2 x] x] [* 2 [- 1]]] 0])]


    [m7/x
     (m7/eq2 `[= [- [:m x [:b [+ x 2]]] [:m 1 [:b [+ x 2]]]] 0])]


    [m7/x '[= [:m [:b [+ x 2]]  [:b [- x 1]]] 0] ]




    [m7/m  '[= [+ [- x 1 ] 1] 1]]
    [:div "or"]

    [m7/m  '[= [+ x 2] 0]]))


#_[m7/x
   (m7/eq2 `[= [+ x 1] y])]



(comment


  [:div {:style {:background-color (hsl [3 20 20 1])
                 :width "100%"
                 :height "3px"}
         }]

  [m7/x
   (m7/eq2 `[= [- 3 [:p x 2]] [+ x 1]])]


  [m7/x
   (m7/eq2 `[=  [+ [- [:p x 2]] [- x ] 2 ] 0])]


  [m7/x
   (m7/eq2 `[=  [+ [- [:p x 2]] [- x [:m 2 x]] 2 ] 0])]


  [m7/x
   (m7/eq2 `[- [:m [- x] [:b [- x 1]]]  [:m 2 [:b [- x 1]]]])]



  [m7/x
   (m7/eq2 `[= [:m  [:b [- x 1]] ] 0])])


        #_[m7/x '[+ x 2]]



        #_[m7/x
         (m7/eq2 `[= [- 2 [:p x 2] ] x])]



          #_(w/postwalk (fn [x] x) '[:apply
                                 [= [+ x y] 1]
                                 [- x]])

        #_(m7/x
         (m7/eq2
          (map
           (fun ([(n :guard number?)] (conj `[+ [- x]] n))
                ([(m :guard vector?)] (conj m `[- x]))
                ([n] n))
           )))


        #_(comment
          [m7/x `[= [- [:m 2 [:p x 2]]
                     x [* 7 3]] 0]]



          [m7/x `[= [+ [:m 2 [:p x 2]]
                     [:m x [:b [- [* 2 3] 7]]] [* [- 7] 3]] 0]]



          [m7/x `[= [+ [:m 2 [:p x 2]]
                     [- [:m [* 2 3] x] [:m 7 x]] [* [- 7] 3]] 0]]



          [m7/x `[= [+ [:m [:m 2 x] [:b [+ x 3]]]
                     [:m [- 7] [:b [+ x 3]]]] 0]]


          [m7/x `[= [:m [:b [+ x 3]] [:b [- [:m 2 x] 7]]] 0]]



          #_[m7/x `[= [+ [:m x [:b [- [:m 2 x]
                                    7]]]
                       [:m 3 [:b [- [:m 2 x]
                                  7]]]] 0]])







        #_[m7/m '[= y [- 1 x]]]


        #_[m7/m '[= [+ [:m 2 [:p x 2]]
                   [:m x y]
                   [:p y 2]]
                22]]
        #_[m7/m '[= [+ [:m 2 [:p x 2]] [:m x [:b [- 1 x]]] [:p [:b [- 1 x]] 2]] 22]]


        #_[m7/m '[= [+ [:m 2 [:p x 2]] [- x [:p x 2]]
                   [:p [:b [- 1 x]] 2]] 22]]

        #_[m7/m '[= [- [:m 2 [:p x 2]]  [:m x [:b [- 1 2]]] [- 21]] 0]]


        #_[m7/m '[= [- [:m 2 [:p x 2]] x 21] 0]]




(comment



  [m7/x `[= [+ [:m 2 x] 1] y]]

  [m7/x
   (m7/eq2 `[= [+ [+ [:m 8 [:p x 2]] x] 6
                [:m 5 y] [:m 6 x y] [:m 9 [:p x 2] y]
                [:m 2 y] [:m 3 x y] [:m 2 [:p x 2] y]

                ] 0])
   ]

          #_(m7/x `[= [+ [:m 6 [:p y 2]] [:m 6 y] [:m [:p 3 2] [:b [- 1 3]]]] 0])


        #_(m7/x `[= [+ [:m 6 [:p y 2]] [:m 6 y] [* [- 2] [:p 3 2] ]] 0])


        #_(m7/x `[= [+ [:m 1 [:p y 2]] [:m 1 y] [- 3]] 0])

        [m7/x `[= [-  [:m 8 [:p x 2]]
                   [:m 9 x ] 14] 0]]




        #_[m7/x (m7/eq2 (w/postwalk
                       (fun ([[:m c [:p [:b [+ a b]] 2]]]
                             ['+ [:m c [:p a 2]] ['* 2 c a b] ['* c  [:p b 2]]])
                            ([y] y))
                       '[= 27 [+ [:p x 2] [:m 3 [:p [:b [+ [:m 2 y] 3]] 2]]]]))]


        #_[m7/x
         (m7/eq2 (w/postwalk (fun ([[:m a [:b c]]] c)
                                  ([y] y))
                             (m7/eq2 (w/postwalk
                                      (fun ([[:p [:b [+ a b]] 2]]
                                            [:b ['+ [:p a 2] ['* 2 a b] [:p b 2]]])
                                           ([y] y))
                                      '[= 27 [+ [:p x 2] [:m 3 [:p [:b [+ [:m 2 y] 3]] 2]]]]))))]
        (comment
          )
        (comment
          [m7/x

           (m7/eq2 `[= [- [+ [:p x 2] [:m 2 x] ] 3] y])]



          [m7/x
           `[= [+ [:m 2 x]  1 ] y]]




          [m7/x

           (m7/eq2 `[= [:p x 2]  4])]


          (comment
            [m7/x
             (m7/eq2 `[= [- 3 [:p x 2]]  [+ x 1]])]



            [m7/x
             (m7/eq2 `[= [+ [:p x 2] x [- 2]] 0])]


            [m7/x
             (m7/eq2 `[= [+ [:p x 2] x [- 2]] 0])]



            [m7/x
             (m7/eq2 `[= [+ [:p x 2] [:m x [:b [- 2 1]]] [* 2 [- 1]]] 0])]

            [m7/x
             (m7/eq2 `[= [+ [:p x 2] [- [:m 2 x] x] [* 2 [- 1]]] 0])]


            [m7/x
             (m7/eq2 `[= [- [:m x [:b [+ x 2]]] [:m 1 [:b [+ x 2]]]] 0])]


            [m7/x '[= [:m [:b [+ x 2]]  [:b [- x 1]]] 0] ]




            [m7/m  '[= [+ [- x 1 ] 1] 1]]
            [:div "or"]

            [m7/m  '[= [+ x 2] 0]]))


  #_[m7/x `[= [-   5] [+ [:p x 2]  x [- 5] 6 ]]]

  )


(comment


  [:div {:style {:background-color (hsl [3 20 20 1])
                 :width "100%"
                 :height "3px"}
         }]

  [m7/x
   (m7/eq2 `[= [- 3 [:p x 2]] [+ x 1]])]


  [m7/x
   (m7/eq2 `[=  [+ [- [:p x 2]] [- x ] 2 ] 0])]


  [m7/x
   (m7/eq2 `[=  [+ [- [:p x 2]] [- x [:m 2 x]] 2 ] 0])]


  [m7/x
   (m7/eq2 `[- [:m [- x] [:b [- x 1]]]  [:m 2 [:b [- x 1]]]])]



  [m7/x
   (m7/eq2 `[= [:m  [:b [- x 1]] ] 0])])


(comment
         #_[m7/x '[+ x 2]]



        #_[m7/x
         (m7/eq2 `[= [- 2 [:p x 2] ] x])]



          #_(w/postwalk (fn [x] x) '[:apply
                                 [= [+ x y] 1]
                                 [- x]])

        #_(m7/x
         (m7/eq2
          (map
           (fun ([(n :guard number?)] (conj `[+ [- x]] n))
                ([(m :guard vector?)] (conj m `[- x]))
                ([n] n))
           )))


        #_(comment
          [m7/x `[= [- [:m 2 [:p x 2]]
                     x [* 7 3]] 0]]



          [m7/x `[= [+ [:m 2 [:p x 2]]
                     [:m x [:b [- [* 2 3] 7]]] [* [- 7] 3]] 0]]



          [m7/x `[= [+ [:m 2 [:p x 2]]
                     [- [:m [* 2 3] x] [:m 7 x]] [* [- 7] 3]] 0]]



          [m7/x `[= [+ [:m [:m 2 x] [:b [+ x 3]]]
                     [:m [- 7] [:b [+ x 3]]]] 0]]


          [m7/x `[= [:m [:b [+ x 3]] [:b [- [:m 2 x] 7]]] 0]]



          #_[m7/x `[= [+ [:m x [:b [- [:m 2 x]
                                    7]]]
                       [:m 3 [:b [- [:m 2 x]
                                  7]]]] 0]])







        #_[m7/m '[= y [- 1 x]]]


        #_[m7/m '[= [+ [:m 2 [:p x 2]]
                   [:m x y]
                   [:p y 2]]
                22]]
        #_[m7/m '[= [+ [:m 2 [:p x 2]] [:m x [:b [- 1 x]]] [:p [:b [- 1 x]] 2]] 22]]


        #_[m7/m '[= [+ [:m 2 [:p x 2]] [- x [:p x 2]]
                   [:p [:b [- 1 x]] 2]] 22]]

        #_[m7/m '[= [- [:m 2 [:p x 2]]  [:m x [:b [- 1 2]]] [- 21]] 0]]


        #_[m7/m '[= [- [:m 2 [:p x 2]] x 21] 0]]

        )




(comment
  [m7/x `[= y [+ [- [:p x 2] [:m 3 x ]] 1]]]
  [m7/x `[= y [-  [:m 2 x] 5]]]
  [m7/x `[= [-  [:m 2 x ] 5] [+ [- [:p x 2] [:m 2 x ] [:m 2 x ] x]  1]]]
  [m7/x `[= 0 [+ [- [:p x 2] [:m 5 x] ]  6]]]

  [m7/x `[= 0 [:m [:b [- x 2]] [:b [- x 3]]]]]



  [m7/x `[= 0 [- x 3]]]
  [m7/x `[= 3 x]]
  [m7/x `[= y [-  [* 2 3 ] 5]]]
  [m7/x `[= y [-  [* 2 3 ] 5]]]
  [m7/x `[= y 1]]

  [:div ]

  [m7/x `[= 0 [- x 2]]]
  [m7/x `[= 2 x]]
  [m7/x `[= y -1]])
