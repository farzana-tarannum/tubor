(ns math2.resume
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
   [moment]))



(def conn
  (d/create-conn db/schema))

(d/transact! conn db/data)

(d/q '[:find  ?t ?br
       :where
       [?p :rm/task ?t]
       [?p :rm/breakdowns ?br]
       ] @conn)
