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
   [math2.physics100 :as py100]
   [math2.fss :as fss]))

;;(defn ^:before-load my-before-reload-callback []
;;    (println "BEFORE reload!!!"))


(defn ^:after-load my-after-reload-callback []
  (println "AFTER reload"))



(def functional-compiler (r/create-compiler {:function-components true}))


(defn render-simple []
  (rdom/render
   #_[fss/fsall ["rs/rs1.png" ]]
   #_[fss/fspdf "resume_nov12.pdf" ]
   #_[fss/fs2 ["rs3/r1.png" "rs3/r2.png" "rs3/r3.png" "rs3/r4.png"]
     [[783 894 30] [782 1114 20] [782 1300 28] [782 1510 28] [782 1702 29] [782 1919 29] [782 2125 26]
     [782 2324 21] [782 2484 34] [782 2700 32] [782 4648 25] [782 4840 29]]]
   #_[fs "resume_nov12_Page_1.jpg" [[852 38 23] [852 190 19]]]
   #_[fs "resume_nov12_Page_4.jpg" [[852 608 18] [852 730 18]]]
   #_[fs "resume_nov12_Page_3.jpg" [[852 38 23] [852 190 19]]]
   #_[fs "resume_nov12_Page_2.jpg" [[852 48 20] [852 201 19]
                                  [852 343 19] [852 501 19]
                                  [852 651 19] [852 799 16]]]
   #_[m19/home-work19]
   #_[m100/app]
   #_[trig/ladder]
   #_[trig/freq3]
   #_[m100/map-family22]
   #_[m100/airplane]
   #_[m100/home-planets-banners]
   #_[m100/chem-ratee]
   [teacher/resume2]
   (js/document.getElementById "app")
   functional-compiler))

(render-simple)
