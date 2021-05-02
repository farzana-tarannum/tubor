(ns math2.resume
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
    :rm/summery "I teach computer science, mathematics, physics  by leveraging my hands on programming skill on computer graphics, animation, user interaction and web technologies. By teaching with better visual interaction helps students get better at receaving intuation of scence & technologies."
    :rm/projects [2 5 7 8]
    :rm/jobs []
    :rm/projects.title "Projects and accomplishment"}
   {:db/id 2
    :rm/task "Online classes on Virtual notebooks"
    :rm/symmery "Collaboration between teachers and students is hard using web Camera and limited zoom experience. Data scientist uses virtual notebooks to collabote betwwen themseft. I have developed similar on experience that would rater focus on student productivity that made a simple and elegant way of writing equation on web at the same speed on pen and paper. So that people can colaborate online. Moreover I made it visually more apealing by making use of digital typography and making use of computer animation and develop ways where computer can assist studests helping with their homework."
    :rm/referane ""}
   {:db/id 3
    :rm/task "Modeling Math and Physics problem with computer aided vector graphics"
    :rm/symmery "Simulating Math and Physics and computer science problem using animation vector graphics helps uploading the information on students brain as long time memory."
    :rm/referane ""}

   {:db/id 5
    :rm/task "Hands on knowledge on Computer chips, electronics"
    :rm/symmery "I have gathered knowledge about computer chips and electronics and networking thought my carrier in Industry leaders like Samsuang, Gammeenphone. Over the year I have developed better understanding about what is going on under the hood which leads me to resonate those hand on knowledge to the students"
    :rm/referane ""}


   {:db/id 7
    :rm/task "Made Virtual Labs for virtual classes."
    :rm/symmery "This virtual Lab has been running for last 5 years on a reputed university with running capatiy of thudents students at time. I was able to inplement a haed of time Lab which help of proferion network engineer and their cutting and age computer infrastructure and headed by a formber Engineer of NASA"
    :rm/referane ""}


   {:db/id 8
    :rm/task "Communication skill using English in professional context"
    :rm/symmery "I have worked with people over the globe, over time I have gotten better at communicaing in professional context both on presentation and at work."
    :rm/referane ""}

   {:db/id 9
    :rm/job "System Engineer at Operations System and Software"
    :rm/company "Grameenphone"
    :rm/duratoin "2009-2013"}
   {:db/id 10
    :rm/job "Lead Engineer at Mobile Development"
    :rm/company "Samsuang R&D"
    :rm/duration "2013-2015"}])
