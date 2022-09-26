(ns math2.db
  (:require
   [datascript.core :as d]))

(def schema
  {:rm/projects {:db/cardinality :db.cardinality/many
                 :db/valueType   :db.type/ref}
   :rm/jobs   {:db/cardinality :db.cardinality/many
               :db/valueType   :db.type/ref}
   :rm/code {:db/unique :db.unique/identity}
   })


(def summery
  "I have been teaching A level computer science, mathematics, physics, IT online since 2019. My lectures are very well prepared by leveraging my hands on programming skill on computer graphics, animation, user interaction and web technologies. It is really helpful for those who want to study at top Universities around the world as well as top Engineering Universities in Bangladesh. My teaching techniques with visual interaction helps students get better at receiving intuition of science & technologies even who those who have short attention span."
  )

(def data
  [
  {:db/id 43
   :rm/code :couch
   :rm/occupation "Computer Engineer"
   :rm/name ""
   :rm/age 38
   :rm/phone "880-1711961024"
   :rm/email ""
   :rm/summery summery
    :rm/projects (into [2 3 5 7 8]
                      [12 14 13 16 17 22 8 23 ])
    :rm/jobs [9 10]
    :rm/projects.title "Projects and accomplishment"}
  {:db/id 1
   :rm/code :abc
   :rm/occupation "Computer Engineer"
   :rm/name ""
   :rm/age 38
   :rm/phone "880-1711961024"
   :rm/email ""
   :rm/summery "I am a fullstack senior software engineer and  DevOps engineer who has more than 10 years experience working on disruptive technological innovation on cloud computing telecommunication finance education sector. I have built my career with start ups to bigtech and from small caps to fortune 500 companies, government organization and universities."
   :rm/projects (into [12 14 13 16 17 22 8 23  25 26 27] [2 3 5 7 8 15] )
   :rm/jobs [9 10]
   :rm/projects.title "Projects and accomplishment"}
  {:db/id 2
   :rm/row 0
   :rm/col 0
   :rm/task "Online classes on Virtual notebooks"
   :rm/summery "Collaboration between teachers and students is hard using web Camera and limited zoom experience. Data scientist uses virtual notebooks to collaborate between themself. I have developed similar experience that would rather focus on student productivity that made a simple and elegant way of writing equation on web at the same speed on pen and paper. So that people can collaborate online. Moreover I made it visually more appealing by making use of digital typography and making use of computer animation and develop ways where computer can assist students helping with their homework."
   :rm/referane ""}
  {:db/id 3
   :rm/row 2
   :rm/col 1
   :rm/task "Modeling Math and Physics problem with computer aided vector graphics"
   :rm/summery "Simulating Math and Physics and computer science problems using animation vector graphics helps uploading the information on students brain and keep it stay inside long time memory."
   :rm/referane ""}

  {:db/id 5
   :rm/row 1
   :rm/col 0
   :rm/task "Hands on knowledge on Computer chips, electronics"
   :rm/summery "I have gathered knowledge about computer chips and electronics and networking thought my carrier for Industry leaders like SAMSUNG, GrameenPhone. Over the year I have developed better understanding about what is going on under the hood which leads me to resonate those hands on knowledge to the students better."
   :rm/referane ""}


  {:db/id 7
   :rm/row 1
   :rm/col 1
   :rm/task "Made Virtual Labs for virtual classes."
   :rm/summery "This virtual Lab has been running since last 5 years on a reputed university. More than thousand students can do their Lab works there. I was able to inplement these ahead of time virtual Lab infrastructure with help of professional network engineers and University's cutting age computer network infrastructure. This project was headed by a former Engineer of NASA"
   :rm/referane ""}


  {:db/id 8
   :rm/row 3
   :rm/col 1
   :rm/task "English communication skill on professional context"
   :rm/summery "I have worked with people over the globe, over time I have gotten better at
communicating in professional context both on presentation and at work."
   :rm/referane ""}

  {:db/id 9
   :rm/row 2
   :rm/col 1
   :rm/job "System Engineer at Operations System and Software"
   :rm/company "Grameenphone"
   :rm/duration "2009-2013"}
  {:db/id 10
   :rm/row 3
   :rm/job "Lead Engineer at Mobile Development"
   :rm/company "Samsuang R&D"
   :rm/duration "2013-2015"}
  {:db/id 11
   :rm/code :sap
   :rm/occupation "Computer Engineer"
   :rm/name ""
   :rm/age 38
   :rm/phone ""
   :rm/email ""
   :rm/summery "I am a skilled IT professional with years of experience on
                System & database Administration. Thought my carrier I have nailed
                every kind of task an IT professional asked for. I am spetialized on
                SAP based DevOps, continuous integration systems"
   :rm/projects [12 14 13 16 17 22 8 23 24 25 26 27]
   :rm/jobs [9 10]
   :rm/projects.title "Projects and accomplishment"}
  {:db/id 12
   :rm/row 0
   :rm/col 0
   :rm/task "Tech Lead at Software Global Consultancy // SGC"
   :rm/summery "I have been working as lead software engineer since 2013. The company has been around for 15 years founded by a former renowned engineer from GrameenPhone. He was able to together put together industry veterans and those who are passionate and persistence on their work and focus with challenges on distributed system, big data analytics, blockchain technology, cloud computing, data science, AI machine learning, internet of things - IOT."
   :rm/referane ""}




  {:db/id 13
   :rm/row 1
   :rm/col 0
   :rm/task "System Engineer at GrameenPhone. (2007-2009)"
   :rm/summery "I worked in GrameenPhone as System Automation Expert in Operations System and Software department (OSS). Main goal is to develop and maintain mediation servers which is in a nut shell modern days MicroServies with lot of bells and whistle. In order to telecommunication system work properly every single nodes has to communicate with other systems and often in multi-vendor system things are not compatible so intermediate system are needed to be developed. Back then we have saved tons of money and man hours by developing and maintaining those systems."
   :rm/referane ""}

  {:db/id 14
   :rm/row 0
   :rm/col 0
   :rm/task "ASHRAF AHMED \\8801711961024 H# 192, R# 2, Avenue# 3, Mirpur DOHS, Dhaka - 1216 "
   :rm/summery "jaharapi@protonmail.com"}

  {:db/id 15
   :rm/row 1
   :rm/col 1
   :rm/task "Assistant Director - Bangladesh Telecommunication Regulatory Commision - BTRC (2009-2011)"
   :rm/summery "I was an active member of National Telecommunication committee, I used to inspect Telecommunication and IT infrastructure, primary goal is to find out potential reverse or Tax leak from those infrastructure. As only person that had proper communication background my job was very extensive, some time it was extended 24/7."
   }

  {:db/id 16
   :rm/row 2
   :rm/col 0
   :rm/task "Lead Engineer - SAMSUNG R&D Institute Bangladesh (2011-2013)"
   :rm/summery "We have developed device drivers on on Real time kernel. As an embedded system engineer my primary goat is to fix bug driver related issues and help engineers to pin down the bugs - they were dealing with and walk then thought the debugging and tracing equipment and guide technical know how for fixing the device driver related issues."
   :rm/referane ""}

  {:db/id 17
   :rm/row 2
   :rm/col 1
   :rm/task "Software Engineer - GENUITY Systems Limited (2006-2007)"
   :rm/summery "I have started my carrer as communcation server developer i.e video conference server that is concurrent, distribued that can handle millions of request per second."
   :rm/referane ""}
  {:db/id 22
   :rm/row 3
   :rm/col 0
   :rm/task "Video conferencing solution with Virtual notebooks"
   :rm/summery "Collaboration between professionals i.e engineers, managers is hard using web Camera and limited zoom experience. Using my solution virtual notebooks in video conferencing make collaborate between themself more productive. Moreover I made it visually more appealing by making use of digital typography and making use of computer animation and develop ways where computer can assist prefessional with their work."
   :rm/referane ""}
  {:db/id 23
   :rm/row 4
   :rm/col 0
   :rm/task [:div
             [:div "BSC in Computer Engineering"]
             [:div "2001-2005"]]
   :rm/summery "I did lot of project works, research, programming contest besides formal Education
where I scored CGPA of 3.69 out of 4. I was a programming contestant which help me solving problems in constraint environment with limited resources."
   :rm/referane ""}

   {:db/id 25
   :rm/row 6
   :rm/col 0
    :rm/task "Personal Summery"
    :rm/summery "I raised and born on noble family. My late father was an veteran we traveled a lot accross the
country. I collage was -
Secondary and Higher Secondary from Rajshahi Cadet College. 1998-2000"
    :rm/referane ""}

  {:db/id 26
   :rm/row 5
   :rm/col 1
   :rm/task "Reference"
   :rm/summery "Salman Maruf Ifee 8801711081123 Ericsson Sharif Ahmed +1 573-479-0167 Microsoft"
   :rm/referane ""}

  {:db/id 27
   :rm/row 6
   :rm/col 1
   :rm/task "Verilog HDL Modeling Systhesis and FPGA-based Place and Route of a 32-Bit Multicycle and Pipelined RISC Processor"
   :rm/summery "This design project modeled and verified a multi-cycle and 5-stage pipelined version of 32-bit RISC processor - MIPS - with Verilog Hardware Description Language (HDL)."

   :rm/referane ""}
  ])
