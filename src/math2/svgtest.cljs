(ns math2.svgtest
  (:require
   [clojure.string :as str]
   [clojure.spec.test.alpha :as stest]
   [clojure.test.check.generators :as gen2]
   [clojure.spec.gen.alpha :as gen]
   [clojure.spec.alpha :as s]
   [defun.core :refer [defun fun]]))


(gen2/sample (gen2/choose 5  12))


(comment



  (wrap "hello")
  (space [2 3 3]))
