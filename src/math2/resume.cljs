(ns math2.physics100
  (:require
   [react]
   [cljs.reader :as edn]
   [datascript.core :as d]
   [math2.math7 :as m7 :refer
    [grid hsl css space size path ve sec]]
   [clojure.walk :as walk]))





(def resume
  [{:db/id 1
    :rm/occupation "Computer Engineer"
    :rm/name ""
    :rm/age 38 :rm/phone "880-1712192643" :rm/email ""
    :rm/summery "I teach computer science, mathematics, physics and intuation behind those by leveraging my knowledge on computer graphics, animation, user interaction,  web technologies. Teaching with Visual Interaction makes students and better on understanding, help students remember without memorizing which turns out geting better grades on examinaiton and not to mention of greater implecatoin on thir real life by understanding science better than before."
    :rm/projects [2 3 4]
    :rm/projects.title "Projects and accomplishment"}
   {:db/id 2
    :rm/task "Algebra and calculus hands on collaboration on Internet"
    :rm/symmery "Collaboration between teachers and students is hard using web Camera. I have made a simple and elegant way of writing equation on web at the same speed on pen and paper. So that people can colaborate online. Moreover I made it visually more apealing by using digital typography and animation. By utilizing symbolic algebirc manupulation technicies and  symbolic Artifitial Interllenge pioyoneered by MIT, computer can assist studests helping with their homework."
    :rm/referane ""}
   {:db/id 3
    :rm/task "Simulation Math and Physics problem with computer aided vector graphics"
    :rm/symmery "Simulating Math and Physics and computer science problem using animation vector graphics helps uploading the information and says it on students brain. Thus better chance Learn once and apply the knowledge on day to day life and examinaiton."
    :rm/referane ""}

   {:db/id 4
    :rm/task ""
    :rm/symmery "Simulating Math and Physics and computer science problem using animation vector graphics helps uploading the information and says it on students brain. Thus better chance Learn once and apply the knowledge on day to day life and examinaiton."
    :rm/referane ""}

   {:db/id 5
    :rm/task "Understand how computer work by making a chomputer chip"
    :rm/symmery "Computer Archituectue and it"
    :rm/referane ""}

   {:db/id 6
    :rm/task "Understand how computer work by making a chomputer chip"
    :rm/symmery "Computer Archituectue and it"
    :rm/referane ""}

   {:db/id 7
    :rm/task "Made Virtual Labs for virtual classes."
    :rm/symmery "This virtual Lab has been running for last 5 years on a reputed university with running capatiy of thudents students at time. I was able to inplement a haed of time Lab which help of proferion network engineer and their cutting and age computer infrastructure and headed by a formber Engineer of NASA"
    :rm/referane ""}
   {:db/id 8
    :rm/post "System Engineer"
    :rm/organizaion "Grameenphone"
    :rm/to-date ""
    :rm/form-date ""}




   ])
