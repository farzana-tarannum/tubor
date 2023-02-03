(ns math2.banners
  (:require
   [math2.file :as file]
   [react]
   [math2.bdmap :as bdmap]
   [applied-science.js-interop :as j]
   [clojure.string :as str]
   [clojure.walk :as w]
   [defun.core :refer [defun fun]]
   [math2.solution :as sol]
   [math2.math7 :as m7 :refer
    [grid hsl css space size path ve sec]]))
