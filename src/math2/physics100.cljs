(ns math2.physics100
  (:require
   [react]
   [cljs.reader :as edn]
   [datascript.core :as d]
   [math2.math7 :as m7 :refer
    [grid hsl css space size path ve sec]]
   [clojure.walk :as walk]))




(def schema
  { :rm/projects {:db/cardinality :db.cardinality/many
                  :db/valueType   :db.type/ref}
   :rm/jobs   {:db/cardinality :db.cardinality/many
               :db/valueType   :db.type/ref}
   :rm/code {:db/unique :db.unique/identity}
   })
(def conn   (d/create-conn schema))

(d/transact!
 conn
 [
  {:db/id 1
   :rm/code :abc
   :rm/occupation "Computer Engineer"
   :rm/name ""
   :rm/age 38 :rm/phone "880-1712192643" :rm/email ""
   :rm/summery "I teach computer science, mathematics, physics  by leveraging my hands on programming skill on computer graphics, animation, user interaction and web technologies. By teaching with better visual interaction helps students get better at receaving intuation of scence & technologies."
   :rm/projects [2 3 5 7 8]
   :rm/jobs [9 10]
   :rm/projects.title "Projects and accomplishment"}
  {:db/id 2
   :rm/row 0
   :rm/col 0
   :rm/task "Online classes on Virtual notebooks"
   :rm/summery "Collaboration between teachers and students is hard using web Camera and limited zoom experience. Data scientist uses virtual notebooks to collaborate between themself. I have developed similar experience that would rather focus on student productivity that made a simple and elegant way of writing equation on web at the same speed on pen and paper. So that people can collaborate online. Moreover I made it visually more appealing by making use of digital typography and making use of computer animation and develop ways where computer can assist students helping with their homework."
    :rm/referane ""}
  {:db/id 3
   :rm/row 0
   :rm/col 1
   :rm/task "Modeling Math and Physics problem with computer aided vector graphics"
   :rm/summery "Simulating Math and Physics and computer science problems using animation vector graphics helps uploading the information on students brain and keep it stay inside long time memory."
    :rm/referane ""}

  {:db/id 5
   :rm/row 1
   :rm/col 0
    :rm/task "Hands on knowledge on Computer chips, electronics"
    :rm/summery "I have gathered knowledge about computer chips and electronics and networking thought my carrier in Industry leaders like SAMSUNG, GrameenPhone. Over the year I have developed better understanding about what is going on under the hood which leads me to resonate those hands on knowledge to the students"
    :rm/referane ""}


  {:db/id 7
   :rm/row 1
   :rm/col 1
   :rm/task "Made Virtual Labs for virtual classes."
   :rm/summery "This virtual Lab has been running since last 5 years on a reputed university. More than thousand students can do their Lab works there. I was able to inplement these ahead of time virtual Lab infrastructure with help of professional network engineers and University's cutting age computer network infrastructure. This project was headed by a former Engineer of NASA"
    :rm/referane ""}


  {:db/id 8
   :rm/row 2
   :rm/col 0
   :rm/task "English communication skill on professional context"
   :rm/summery "I have worked with people over the globe, over time I have gotten better at
communicating in professional context both on presentation and at work."
   :rm/referane ""}

  {:db/id 9
   :rm/row 2
   :rm/col 1
   :rm/job "System Engineer at Operations System and Software"
   :rm/company "Grameenphone"
   :rm/duratoin "2009-2013"}
  {:db/id 10
   :rm/row 3
   :rm/job "Lead Engineer at Mobile Development"
   :rm/company "Samsuang R&D"
   :rm/duration "2013-2015"}])



(def fontf
  (comp
   m7/coma
   (partial map
            (comp
             m7/space
             (juxt (comp
                    m7/wrap-sami-colon
                    name
                    first) second)))
   vec))

(def var-vf
  {:wght 431.85

   :wdth 100
   :opsz 14
   :GRAD 88
   :XOPQ 88
   :XTCH 1000
   :YTAS 750
   :YTRA 1000
   :YTUC 750
   :YTLC 500
   :YTSE 18
   :YTCH 1000
   :PWDT 402
   :PWGT 88
   :YOPQ 50
   :XTRA 402
   :YTDE 250})


(defn fv1 [n]
  {:font-family "'Amstelvar VF'"
   :font-variation-settings
   (fontf
    (update
     var-vf
     (get [:wght :wdth :opsz :GRAD]
          0)
     (fn [k] (+ k (/ 880 n)))))})

(defn fconf [elms]
  (let [f (fn [n d] (/ n d))]
    (map
     (fn [[u [mn mx]] [n d]]
       [u (fn [k]
            (+ k (* k (f n d))))])
     [[:wght  [100 900]]
      [:wdth  [35 10]]
      [:opsz  [10 72]]
      [:GRAD  [88 150]]]
     elms)))

(defn fv [elms]
  {:font-family "'Amstelvar VF'"
   :font-variation-settings
   (fontf
    (reduce
     (fn [vf [w f]]
       (update vf w f))
     var-vf
     (fconf elms)))})



(comment
  (defn table

    [n]
    (for [[c data]
          (map (fn [x y]
                 [x y])
               (range 0 2)

               )
          :let [col-space 20
                j (+  2 (* c col-space))]
          r (range 0 n)
          :let [row-space 3
                i (+ 2 (* row-space r))]]
      [:div {:key (gensym)
             :style (m7/css [[i row-space
                              j col-space
                              :center :center 1.8 :rem]
                             [1 70 70 1] [] {}])}
       (nth data r)])))




(comment
  (fv [[1 2] [3 7] [2 9] [9 11]]))

(comment

  (def schema {:phy/sub {:db/isComponent true
                         :db/valueType :db.type/ref
                         :db/cardinality :db.cardinality/many}})
  (def conn   (d/create-conn schema))
  (d/transact! conn
               [{:db/id 4 :phy/name  "Maksimy" :phy/seq 3 :phy/sub   [2]}
                {:db/id 1 :phy/name  "Maksim" :phy/seq 4  :phy/sub   [2 3]}
                {:db/id 2 :phy/name  "Ms" :phy/seq 5 }
                {:db/id 3 :phy/name  "Msk" :phy/seq 6}])

  (def schema2 { :aka { :db/cardinality :db.cardinality/many }})
  (def datoms #{(d/datom 1 :age  17)
                (d/datom 1 :name "Ivan")}))





(comment
  (d/q '[:find ?e ?n ?s ?ns
         :where
         [?e :phy/name ?n]
         [?e :phy/sub ?s]
         [?s :phy/name ?ns]] @conn))


(def people-db
  (d/db-with (d/empty-db) [#:person{:first-name "John" :last-name "Doe" :age 45}
                           #:person{:first-name "Jane" :last-name "Doe" :age 44}
                           #:person{:first-name "Sarah" :last-name "Doe" :age 19}
                           #:person{:first-name "Bobby" :last-name "Doe" :age 14}]))




(def ballot-rule
  '{:when [[?e :person/first-name ?first]
           [?e :person/last-name ?last]
           [?e :person/age ?age]
           [(>= ?age 18)]
           [(str ?first " " ?last) ?full]]
    :then [[:db/add ?e :person/ballot-sent? true]
           [:db/add ?full :ballot/full-name ?full]]})


(defn r
  "Tries to match rule against db
  Returns tx-data or empty list if rule does not match"
  [{:keys [when then args]} db]
  (let [syms (for [row then el row :when (symbol? el)] el)
        results (apply d/q {:find syms :in (cons '$ (keys args)) :where when} db (vals args))]
    (for [match results tx then]
      (let [swaps (zipmap syms match)
            f (fn [x] (if (coll? x) x (get swaps x x)))]
        (walk/postwalk f tx)))))





(r ballot-rule people-db)





  (->>
    (d/datoms (d/db-with people-db (r ballot-rule people-db)) :eavt)
    (map (juxt :e :a :v)))


(def crime-db
  (d/db-with
   (d/empty-db {:country/code       {:db/unique :db.unique/identity}
                 :person/name        {:db/unique :db.unique/identity}
                 :person/citizens-of {:db/cardinality :db.cardinality/many
                                      :db/valueType   :db.type/ref}
                 :country/enemy-of   {:db/cardinality :db.cardinality/many
                                      :db/valueType   :db.type/ref}
                 :country/owns       {:db/cardinality :db.cardinality/many
                                      :db/valueType   :db.type/ref}
                 :object/sold-by     {:db/valueType :db.type/ref}})

    [{:db/id "USA" :country/code :usa}
     {:db/id "Robert" :person/criminal? false :person/name "Robert" :person/citizens-of [{:db/id "USA"}]}
     {:db/id "Missiles" :object/type :missiles :object/sold-by {:db/id "Robert"}}
     {:db/id "Sokovia" :country/code :sokovia :country/enemy-of [{:db/id "USA"}] :country/owns [{:db/id "Missiles"}]}]))






(defn infer
  "Takes a db and a list of rules. Returns a new db with all inferred facts.
  Keeps adding data derived from the rules to the db until db doesn't change.
  Stops after max 100 iterations."
  [db rules]
  (loop [db db max-iter 100]
    (let [tx-data (for [rule rules tx (r rule db)] tx)
          new-db (d/db-with db tx-data)]
      (cond
        (= db new-db) new-db
        (= 1 max-iter) new-db
        :else (recur new-db (dec max-iter))))))
(def rules
  '[;; American law
    {:when [[?p :person/american? true]
            [?c :country/hostile? true]
            [?o :object/weapon? true]
            [?i :invoice/seller ?p]
            [?i :invoice/buyer ?c]
            [?i :invoice/object ?o]]
     :then [[:db/add ?p :person/criminal? true]]}
    ;; A USA citizen is American
    {:when [[?p :person/citizens-of ?c]
            [?c :country/code :usa]]
     :then [[:db/add ?p :person/american? true]]}
    ;; An enemy is hostile
    {:when [[?c1 :country/enemy-of ?c2]
            [?c2 :country/code :usa]]
     :then [[:db/add ?c1 :country/hostile? true]]}
    ;; A missile is a weapon
    {:when [[?o :object/type :missiles]]
     :then [[:db/add ?o :object/weapon? true]]}
    ;; If someone sold it and someone bought it, there should be an invoice somewhere, right?
    {:when [[?c :country/owns ?o]
            [?o :object/sold-by ?p]
            (not [?i :invoice/object ?o])]
     :then [[:db/add "invoice" :invoice/seller ?p]
            [:db/add "invoice" :invoice/buyer ?c]
            [:db/add "invoice" :invoice/object ?o]]}])


(-> crime-db (d/pull [:person/criminal?] [:person/name "Robert"]))
(-> crime-db
    (infer rules)
    (d/pull [:person/criminal?] [:person/name "Robert"]))


(defn template []
  [:div {:style (merge (m7/grid [300 :vh 100 :vw
                                 (take 38 (repeat [5 :vh]))
                                 (take 20 (repeat [5 :vh]))])
                       {:background-color (hsl [1.5 70 70 1])
                        :gap ".5rem"})}
   [:div {:key (gensym)
          :style (m7/css
                  [[2 8 5 20 :center :center 2.3 :rem]
                   [1 70 90 .2] [] {:gap "1rem"
                                    :padding "2rem"}
                   (fv [[1 4] [1 1] [1 2] [2 1]])])}

    (ffirst
     (d/q '[:find  ?s
            :where
            [?e :rm/code :abc]
            [?e :rm/summery ?s]] @conn))]


   (map identity
        (reduce
         (fn [acc [task sum row col]]
           (conj
            (conj acc
                  [:div {:key (gensym)
                         :style (m7/css
                                 [[(+ 10 (* row 20)) 3 (+ 5 (* col 10)) 10 :center :center 2 :rem]
                                  [2 70 90 .7] [] {:gap "1rem"}
                                  (fv [[1 4] [1 1] [1 2] [2 1]])])}

                   task])
            [:div {:key (gensym)
                   :style (m7/css
                           [[(+ 13 (* row 20)) 15 (+ 5 (* col 10)) 10 :center
                             :center 2 :rem]
                            [1 70 90 1] [] {:gap "1rem"}])}

             sum]))
         []
         (d/q '[:find ?t ?s ?r ?c
                :where
                [?e :rm/code :abc]
                [?e :rm/projects ?p]
                [?p :rm/task ?t]
                [?p :rm/summery ?s]
                [?p :rm/row ?r]
                [?p :rm/col ?c]
                ] @conn)))


   ])
