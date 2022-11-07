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




(def summery2 "As a DevOps, my primary goal is to ensure developer productivity and ensure system reliability. I achieved these goals by leveraging in depth knowledge in the past as a network system programming on C and Linux systems and micro architectures (ISA) from there I had gradually migrated to jvm with Clojure, Scala finally back to the system as DevOps with Elixir, Rust, GO-lang which ensure memory safety guarantees and faster development cycle. I am a strong believer of multi-database, programming paradigm as each developer community have their strengths as well as weakness, so I leverage modern tooling like service meshes on microservices and stream-processing, RESTful API routing to reduce fiction and boiler plates hence increase productivity and reliability.")


(def summery3 "As a DevOps, fullstack developer to ensure developer productivity and ensure system reliability in the mean time still maintain faster development cycle, I have been using groovy on jvm and react as fontend scince 2014 and linux system intergration and postgresql science 2006. So I leverage modern tooling such as docker, kubernetes, RESTful API to reduce fiction and boiler plates hence increase productivity and reliability.")


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



    :rm/summery "I am a DevOps engineer. I have more than 15 years experience working on cloud computing architectures on Linux platforms. I have built my career with start-ups to Bigtechs by developing infrastructure toolings, troubleshooting, system integration."
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
    :rm/task "Serverless end to end video conferencing and live Streaming v1 (2006-2012)"
    :rm/summery "I having been making end to end low latency network connectivity solutions since 2006 using network UDP whole punching technologies which is becomming standard in 2021/2022 not only for voice and video (SRT) but also in http3 (QUIC). Streaming Vedeo providers like Google, zoom, facebook, twitch  uses of TCP-based protocls like RTMP, make unlaw full interceptions, make pessing encoding, transcoding, processing on the server side -  debending on network condition and data colocations their latency ended up 1000 times slower than my solution. In 2006 I have introdue network compressing using client and server side programs in event based c networking programming in linux, make it fault tolarent using arp spoofing then update this solution where servers help endpoints steaming through least latency route in 2008 and in 2011 added multiplxing to in crease network throughput 2013."
    :rm/referane ""}

   {:db/id 25
    :rm/row 0
    :rm/col 0
    :rm/task "Serverless end to end video conferencing and live Streaming with Edge processing v2 (from 2013)"
    :rm/summery "2015 I managed to ttansfer network discovery soluton and in first with CPS(comminication sqeuntional processing) in clojure and using actor modle in scala to make it scalabe and configuration managed side mongodb nodejs with reactjs fontend with webrtc. In 2006 I have introdue network compressing using client and server side programs in event based c networking programming in linux, make it fault tolarent using arp spoofing then update this solution where servers help endpoints steaming through least latency route in 2008 and in 2011 added multiplxing to in crease network throughput 2013 - 2015 I managed to ttansfer network discovery soluton and in first with CPS(comminication sqeuntional processing) in clojure and using actor modle in scala to make it scalabe and configuration managed side mongodb nodejs with reactjs fontend with webrtc.
    "
    :rm/referane ""}


   {:db/id 35
    :rm/row 0
    :rm/col 0
    :rm/task "Near real time Edge processing"
    :rm/summery ""
    :rm/referane ""}

   {:db/id 35
    :rm/row 0
    :rm/col 0
    :rm/task "Web based audio & vedio conferencing"
    :rm/summery ""
    :rm/referane ""}

   {:db/id 45
    :rm/row 0
    :rm/col 0
    :rm/task "Actor model and CPS adoption for scalavility"
    :rm/summery ""
    :rm/referane ""}


  {:db/id 3
   :rm/row 2
   :rm/col 1
   :rm/task "Realtime collaboration platform for data scientist"
   :rm/summery ""
   :rm/referane ""}




  {:db/id 7
   :rm/row 1
   :rm/col 1
   :rm/task "Virtual Desktop Environment(VDA)"
   :rm/summery "This virtual Lab has been running since last 5 years on a reputed university. More than thousand students can do their Lab works there. I was able to inplement these ahead of time virtual Lab infrastructure with help of professional network engineers and University's cutting age computer network infrastructure. This project was headed by a former Engineer of NASA"
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
    :rm/row 8
    :rm/col 1
    :rm/task "Assistant Director - Bangladesh Telecommunication Regulatory Commision - BTRC (2009-2011)"
    :rm/summery "tasted by at least millions time using wireshark packct tracing and lua scripting when I was Lawful Interseptor Bangladesh Telecommunication Regulatory Commisions.
I was an active member of National Telecommunication committee, I used to inspect Telecommunication and IT infrastructure, primary goal is to find out potential reverse or Tax leak from those infrastructure. As only person that had proper communication background my job was very extensive, some time it was extended 24/7."
    }

   {:db/id 15
    :rm/row 1
    :rm/col 1
    :rm/task "Deep packet Inspections (DPI)"
    :rm/summery "
Packet Inspection and Auditing for malatious mel activities and Lawful Interception
 in Linux Kernel Space using eBPF technologies and systrace using GO-lang and PostGreSQL. "
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
    :rm/task "Learn how to implement Intel Integated Performance primitives (IPP) api Audio/speech codec, udp hole punching technologies named (STRN, TURN, ICE), implemented web based audio, video streaming. Configured dns extentions DNS srv. Impement protocols from RFC draft."
    :rm/referane ""}


   {:db/id 129
    :rm/row 2
    :rm/col 1
    :rm/task "Fractals Labs"
    :rm/summery "Successful server based deployment of Audio-video codec (G729AB, AMR-NB, H263, H264) transcoding on debian based systems. Addoption of Phyton and shell script replacement. commercialization of UART, gpio, smart card solutions on raspberry pi. Restful api development with Reactjs worked with phyton flux, websocket, gevent, sqlalkalemi. Learned how to run a startups without any investment and when to quet when bigtech with unlimited capital steps in."
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
