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

(def summery2 "As a DevOps my primary gool is to ensure developer productivity and ensure system reliability. I achieve this gools by leveraging  in depth knowledge in past as a network programming in c on linux systems and miro achitecture (ISA) from there I had grudually migrated to jvm finally back to the system as DevOps with Rust, go which have memory safety guarantees. I am strong beliver of multi database, programming pardime as each developer community have their strength as well as weekness, so I laverage modern toolings on service meshes  based on microservies and stream-processing, RESTful API routing to reduce fiction and boiler plates hence increase productivity and reliability")


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

   {:db/id 111
    :rm/code :devops
    :rm/occupation "Computer Engineer"
    :rm/name ""
    :rm/age 38
    :rm/phone "880-1711961024"
    :rm/email ""
    :rm/summery "I am a DevOps engineer, I have more than 15 years experience working on cloud computing architechtures on linux platforms.
I have built my career with start ups to bigtech by developing infrastructure toolings, troubleshooting, system intregration."
    :rm/projects (into [27 25]
                       [12 13 20 16 17 22 23 7 17 127 15])
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

   {:db/id 20
    :rm/row 0
    :rm/col 0
    :rm/task "Web Vector graphics (SVG) based data virtualiztoin and animation platform for cambridge education (CIE)"
    :rm/summery "Collaboration between teachers and students is hard using web Camera and limited zoom experience. Data scientist uses virtual notebooks to collaborate between themself. I have developed similar experience that would rather focus on student productivity. So that people can collaborate online. Moreover I made it visually more appealing by making use of digital typography with reactjs and clojurescript and jvm based clojure backed and expert systems clara rule, kafka event streaming, docker k3s department and graph database with datalog query engine and phyton OpenCV, differential programming, lenearing programming, lenearing and non lenear transformation, paring with darivatives"
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
   :rm/task "Virtual Desktop Environment(VDA)"
   :rm/summery "This virtual Lab has been running since last 5 years on a reputed university. More than thousand students can do their Lab works there. I was able to inplement these ahead of time virtual Lab infrastructure with help of professional network engineers and University's cutting age computer network infrastructure. This project was headed by a former Engineer of NASA"
   :rm/referane ""}


  {:db/id 8
   :rm/row 20
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
   :rm/projects [12 14 13 16 117 22 8 23 24 25 26 27 ]
   :rm/jobs [9 10]
   :rm/projects.title "Projects and accomplishment"}

   {:db/id 13
    :rm/row 1
    :rm/col 0
    :rm/task "System Engineer at GrameenPhone. (2007-2009)"
    :rm/summery "I worked in GrameenPhone as System Automation Expert in Operations System and Software department (OSS). Main goal is to develop and maintain mediation servers which is in a nut shell modern days MicroServies with lot of bells and whistle. In order to telecommunication system work properly every single nodes has to communicate with other systems and often in multi-vendor system things are not compatible so intermediate system are needed to be developed. Back then we have saved tons of money and man hours by developing and maintaining those systems.
            Developed and maintained database for core, transmission and packet network of GrameenPhone
            Determined future network load by data mining
            Automated the complete workflow for planning, approving and executing all network configuration changes
            Implemented packet data service activation, deactivation and charging system through subscriber sent SMS
            Designed and developed missed call alert service activation, deactivation and charging system through subscriber sent SMS
            Designed and developed reporting systems for various value added services and network nodes
            Technologies Used: Java, Oracle, Netbeans, Servlet, SOAP, jboss jetty"
    :rm/referane ""}


   {:db/id 113
    :rm/row 1
    :rm/col 0
    :rm/task "System Engineer at GrameenPhone. (2007-2009)"
    :rm/summery "Main goal is to develop and maintain mediation servers which is in a nut shell modern days MicroServies with less of bells and whistle. In order to telecommunication system work properly every single nodes has to communicate with other systems and often in multi-vendor system things are not compatible."
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
   :rm/summery "I have worked on device drivers on linux kernel, there I have mastered on taking systrace, JTAG. analyzie core dump using GDB toolings on embaded linux, linux boot sequence on UBOOT, handle UART and gpio, memory maped network drivers. I have go 2+1 months training low level device driver programming in c both from SAMSANG SNMC, India and Bangladesh. I also receaved leadership training "
   :rm/referane ""}

   {:db/id 17
    :rm/row 2
    :rm/col 1
    :rm/task "Software Engineer - GENUITY Systems Limited (2006-2007)"
    :rm/summery "I was a Linux Network programmer in c, I used to develop select and epoll based network SBC worked with syscalls, lexical parsers, gather indepth knowledge about real time protocols voice and video packets and UDB based session controlers and implemented high avabalibility on failover using ICMP and arp. Master Postgre sql query optimitaion and hardisk raid for data redandency, mastering remote with ssh and emacs and shell scripting, yum and apt-get for avoyding dependency hell. Wored with highly concorrent softswith routing with load blancing and priodity queues, medeia gateways on CentOS and ubuntu. "
    :rm/referane ""}

   {:db/id 117
    :rm/row 2
    :rm/col 1
    :rm/task "Software Engineer - GENUITY Systems Limited (2006-2007)"
    :rm/summery "As a devOps on video conferencing systems and I have worked with hith availibilty on liunx, shell scripting, packet tracing with wireshark, routing UDP packets over all type network environments."
    :rm/referane ""}

   {:db/id 127
    :rm/row 2
    :rm/col 1
    :rm/task "Eyeball Networks Aug 2008 - May 20101 year 10 months"
    :rm/summery "Eyeball's Nat-Traversal solution development.
Implementation of latest SIP, XMPP, STUN, TURN and ICE drafts/rfc's.
Lead developer of eyeballs AFE / AFS 8.0



- Cloud based development
- Big Data, Machine Learning
- Scalable realtime services and communication software development
- TCP/IP, UDP, RTP, SIP, XMPP, STUN, TURN, ICE
- High performance SDK development experience
- Audio/speech codec implementation
- Audio-video messenger development
"
    :rm/referane ""}


   {:db/id 129
    :rm/row 2
    :rm/col 1
    :rm/task "Fractals Labs"
    :rm/summery "- Video messenger and soft-switch development
- Audio-video codec (G729AB, AMR-NB, H263, H264) implementation and optimization
- Silence detection
- Serial I/O programming
"
    :rm/referane ""}


   {:db/id 42
    :rm/row 2
    :rm/col 1
    :rm/task "Senior Software Engineer - Eyeball Networks (2009-2009)"
    :rm/summery ""
    :rm/referane ""}

  {:db/id 22
   :rm/row 3
   :rm/col 0
   :rm/task "Video conferencing solution with Virtual notebooks"
   :rm/summery "Worked with KVM Libvert virtualiztoin on Archlinux, gstreamer video audio routing from souce to sink."
   :rm/referane ""}
   {:db/id 23
   :rm/row 4
   :rm/col 0
   :rm/task [:div
             [:div "BSC in Computer Engineering"]
             [:div "2001-2005"]]
   :rm/summery "I have graduted from AIUB CGPA of 3.69 out of 4.p
"
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
