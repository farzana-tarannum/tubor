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


(def vert-hostdevice "Pass host devices (/dev/kvm, GPU passthough) into pod environment untimately onto the Virtual Machine (VM) using DevicOkuDe Plugins without granting the pod elevate host previlages"
  )

(def gitops "gitops manage infrastructure and application operation declaratively, versioned and Immutabe, pulled automatically, continuously reconciled the system state and attempt to apply desired state, adopted  these gitops principle as guideline and comming by with get practices thus gateway confiration drift, everyting written in code make it reusable and have a audit trail by making it immutabe no seaking in chane.")



(def data
  [
   {:db/id 111
    :rm/code :devops
    :rm/occupation "Computer Engineer"
    :rm/name ""
    :rm/age 38
    :rm/phone "880-1711961024"
    :rm/email ""
    :rm/summery "I am a DevOps engineer. I have more than 15 years experience working on cloud computing architectures on Linux platforms. I have built my career with start-ups to Bigtechs by developing infrastructure tooling, troubleshooting, system integration. As a DevOps & fullstack developer my primary objective is to ensure developer productivity and ensure system reliability in the mean time still maintain faster development cycle"
    :rm/projects (into [2 27 25 42]
                       [12 13  16 117 23 7  127 15 35 129 20 2 225 1132
                        2132])
    :rm/jobs [9 10]
    :rm/proects.title "Projects and accomplishment"}



   {:db/id 2
    :rm/row 0
    :rm/col 0
    :rm/task "design and develop a computational notebooks with interactive programming techniques"
    :rm/summery "I have been teaching Cambridge IAL computer science, mathematics, physics, IT online since covid statred. My lectures are very well prepared by leveraging my hands on programming skill on computer graphics, animation, user interaction and web technologies these visual interaction with reactjs svg bazier curves d3.js gematric algebra symbolic AI with clojure and clojurescript and deffientiable programming for calculus and gredient decent that is a prelude to machine learing and AI. I have leveraged react hot loading technics to generate code to graphics realtime
on classes. These techniques helps students get better at receiving intuition of science & technologies even who those who have short attention span. "
    :rm/referane ""}


   {:db/id 1132
    :rm/row 0
    :rm/col 0
    :rm/task  "Edge computing, discover and Manage IoT devices from Kubbernetes with Akri."
    :rm/summery  (clojure.string/join
                  ""
                  ["In our scenario we customers are migrant workers, they really depands on us to maintain and deploy IOT on their home, monitoring health of bio gas based reactor, maintain 24/7 connectivity as their clildren depends on distance learning, powering home with solar and hydogen based fuel cell. Our job is to maintain and install IOT  and network device on those hetarogenious envirnoment on remote locations. In order to smooth sailing we have migrathed to kubbernetes leverage akri to communicates via  common Iot protocols udev for divices on linux local file systems (USB cameras, GPU, microphone), OPC UA (industrial machinery), ONVIF (IP cameras) and simple gRPC interface for adding support for new protocols on RUSt & Go lang (for example adding zeroconf based mdns devices)."
                   "Pattern of communication made with devices are discovery handlers by implemanting these are protocols thn deploy a long running pod as information broaker for exaple for ip cameras, pulling streams of I frames and P frames from camera work as protocl translation gateway so dicover a usb camera and broker aka long running pod advertises as an ip camera using akri to discover microphone, cameras then use it on streaming applicaion then go one step further by managing this divices by performing formal update on them. Run k3s on the Edge Amlogic based Arm SOCs not only hatarogenious compute by also environment. IOT devices, mcu class sensors controllers etc and not only there a bunch of different ones but also they're constantly scaling up and down. As these devices are too small to run kuberneters temselves or have just one fix functionor. okri enables dynamicallly leverge these devices in kubernetes cluster and aim to the interface that abstracts the way the dtails of discovering and using iot devices on the edge. First it discovers the devices then for every device that ocari discovers in connects it to a the cluster by creating a kubernetes resource to represent device, then we have a controller that can automatically deploy workloads to discover devices, handle common occurrences that are more common on the edge such as devices dynamicallly disappearing, loos fo connectivity to the device or over heating or all of sudden coming online and Akri can detech these occurrences create, update, remove kubernetesw resource and bring down or deploy appropriate worklaod automatically. Bulit in rust to optimize to being as low footprint as possible" "hetarogenious gpu cluster"])
    :rm/referane ""}

   {:db/id 2132
    :rm/row 0
    :rm/col 0
    :rm/task  ""
    :rm/summery "KubeVirt is Kubernetes extension that allows running  VM and Firecracer MicroVM workloads and  natively side by side with container workloads , as a infrastructure as a service that allows put together VM and cotainer orkastation under Kubernetes which was previously done using with openstack in our case, transition path of old VM based workloads to containerized workloads for Virtual desktop (VDI), Video Fx Rendering. Another one kind of emereged which is a predominat use case to get hold of nested kubernetes on top of kubernetes using kubevirt for virtual machines that works uderlying infrastructure as service nested layer for development or staging. say you have kubernetes cluster and you want to spawn lots of little kubernetes clusters on top of that for development or staging this is how we run the productions, deploying cube verts on the under layer so the bare metal kubernetes layer spin up virtual machines that's the underlying substrate for the next layer of kubernetes clusters and also buliding pipelines like tecton. posting a virtual machine from manifests, hit the api server get persisted on etcd virt-controller spin up virt-hadler instruct virt-lancher, virt operator that manage install update kubevert that pretty neat ensure 0 downtime updates that going to guarantee when doing updates not to disrupt workloads and protect people from kubbernetes doing node level upgrate by evacuating and live migrating virtual machines. by posting a custom resurce that gives operator instractions install on kubevert namespace, persistent virtual machine can be lunch with by combining storage creation and import and how we want virtual machine to start all in one manifest yaml of VirtualMachine kind. Way of doing storage part is this thing call data volume template where pvc is defined and the source that is going to be imported into that pvc then import virtual machine disk from a container image on the pvc and bind the network interface"
    :rm/referane ""}


   {:db/id 3132
    :rm/row 0
    :rm/col 0
    :rm/task  ""
    :rm/summery  (clojure.string/join "" ["enhace machine learning model to real time data pipeline with kafka and webassembly"
                                          "Building fully managed cloud services with kafka and Rust with no runtime or garbage collaboration."
                                          "Realtime stream processing and data transformation using webassembly all in a single unified cluster with upto 3x less latency 5x throughput, 7x cpu utilization, 50x memory utilization."
                                          "feed stream clean data to kafka topic starting with rust http source connector, streaming data sink into kafka source connector where webassembly pipeline is applied which enable direct contorl over the streaming datathen transform event flows to kafka consumer."
                                          ])
    :rm/referane ""}










   {:db/id 20
    :rm/row 7
    :rm/col 6
    :rm/task "Serverless end to end video conferencing and live Streaming v1 (2006-2012)"
    :rm/summery "End to end low latency network connectivity solutions using network UDP whole punching technologies which is becomming standard in 2021/2022 not only for voice and video (SRT) but also in http3 (QUIC) where as streaming Vedeo providers like Google, zoom, facebook, twitch  uses of TCP-based protocls like RTMP thus increase network latency."
    :rm/referane ""}

   {:db/id 225
    :rm/row 7
    :rm/col 6
    :rm/task "Serverless end to end video conferencing and live Streaming with Edge processing v2 (from 2013)"
    :rm/summery "actor modle in scala to make it scalabe and configuration managed side mongodb nodejs with reactjs fontend with webrtc. "
    :rm/referane ""}


   {:db/id 353
    :rm/row -1
    :rm/col 0
    :rm/task "Near real time Edge processing"
    :rm/summery ""
    :rm/referane ""}

   {:db/id 35
    :rm/row -1
    :rm/col 0
    :rm/task "Web based audio & video conferencing"
    :rm/summery "Fontend is developed with reactjs and webrtc with google doc like collaboration platform for data scientist and students. Developing backend services with ingress API gateway, kafka for messaging, groovy for connecting postgresql, kafka and fontend."
    :rm/referane ""}

   {:db/id 45
    :rm/row 0
    :rm/col 0
    :rm/task "Actor model and CPS adoption for scalability"
    :rm/summery ""
    :rm/referane ""}


  {:db/id 3
   :rm/row 2
   :rm/col 1
   :rm/task "Realtime collaboration platform for data scientist"
   :rm/summery ""
   :rm/referane ""}




  {:db/id 7
   :rm/row -30
   :rm/col 1
   :rm/task "Fractals Labs (2016-2018)"
   :rm/summery "Implemented Virtual Desktop Environment(VDE) for University Labs using KVM Libvert virtualization on Archlinux based hypervisor that hosts virtualized linux and windows desktops and docker for containerization on the server and desktop is replaced by ARM based SoC with vnc & rdp clients installed where students can access remote desktops and spawn docker based services on remote services"
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

   {:db/id 13
    :rm/row -15
    :rm/col 0
    :rm/task "System Engineer at GrameenPhone. (2007-2009)"
    :rm/summery "Developed and maintained mediation servers which was in a nut shell modern days micro services with lot of bells and whistle with Java, Oracle SQL, shell scripting, jetty, linux command line utilities - awk, ssh port forwarding and reverse   proxy for remote server login and vi as remote editor. As well implemented push and pull based systems and services for monitoring alarms and IP packet tracing using wireshark"
    :rm/referane ""}


   {:db/id 113
    :rm/row 1
    :rm/col 0
    :rm/task "System Engineer at GrameenPhone. (2007-2009)"
    :rm/summery "Main goal is to develop and maintain mediation servers which is in a nut shell modern days MicroServies with less of bells and whistle. In order to telecommunication system work properly every single nodes has to communicate with other systems and often in multi-vendor system things are not compatible."
    :rm/referane ""}



   {:db/id 115
    :rm/row 8
    :rm/col 1
    :rm/task "Assistant Director - Bangladesh Telecommunication Regulatory Commision - BTRC (2009-2011)"
    :rm/summery "tasted by at least millions time using wireshark packct tracing and lua scripting when I was Lawful Interseptor Bangladesh Telecommunication Regulatory Commisions.
I was an active member of National Telecommunication committee, I used to inspect Telecommunication and IT infrastructure, primary goal is to find out potential reverse or Tax leak from those infrastructure. As only person that had proper communication background my job was very extensive, some time it was extended 24/7."
    }

   {:db/id 15
    :rm/row -1
    :rm/col 4
    :rm/task "Deep packet Inspections (DPI)"
    :rm/summery "Packet Inspection and Auditing for malicious activities and Lawful Interception in Linux Kernel Space using eBPF technologies and systrace using shell scripting later successfully used groovy on GraalVM and PostGreSQL query optimization using join planing and indexing also used redis cluster for cashing frequent data. Develop react based data visualization using d3.js and Groovy based RESTful API backend."
    }



   {:db/id 16
    :rm/row -20
    :rm/col 0
    :rm/task "Lead Engineer - SAMSUNG R&D Institute Bangladesh (2011-2013)"
    :rm/summery "I have worked on device drivers on Linux kernel on Android based mobile handsets and patched linux kernel with device specific changes based on git, As well as fork and maintain specific version then merge it to upstream when stable. Applied three way git merging as well as merge conflicts, mastered on taking systrace I also received leadership training for very big teams of developers"
    :rm/referane ""}


   {:db/id 160
    :rm/row 0
    :rm/col 0
    :rm/task "Lead Engineer - SAMSUNG R&D Institute Bangladesh (2011-2013)"
    :rm/summery "I have worked on device drivers on Linux kernel on Android based mobile handsets and patched linux kernel with device specific changes based on git, As well as fork and maintain specific version then merge it to upstream when stable. Appled three way git merging as well as merge conflicts, mastered on taking systrace, JTAG. analyze core dump using GDB tooling on embedded linux, linux boot sequence on UBOOT, handle UART and GPIO, memory mapped network drivers. I have go 2+1 months training low level device driver programming in c both from SAMSANG SNMC, India and Bangladesh. I also received leadership training for very big teams of developers"
    :rm/referane ""}

   {:db/id 17
    :rm/row 2
    :rm/col 1
    :rm/task "Software Engineer - GENUITY Systems Limited (2006-2007)"
    :rm/summery "I was a Linux Network programmer in c, I used to develop select and epoll based network SBC worked with syscalls, lexical parsers, gather indepth knowledge about real time protocols voice and video packets and UDB based session controlers and implemented high avabalibility on failover using ICMP and arp. Master Postgre sql query optimitaion and hardisk raid for data redandency, mastering remote with ssh and emacs and shell scripting, yum and apt-get for avoyding dependency hell. Worked with highly concorrent softswith routing with load blancing and priodity queues, medeia gateways on CentOS and ubuntu. "
    :rm/referane ""}

   {:db/id 117
    :rm/row -10
    :rm/col 1
    :rm/task "Software Engineer - GENUITY Systems Limited (2006-2007)"
    :rm/summery "As a devOps on audio conferencing systems and I have worked with liunx, iptables, shell scripting, routing UDP packets over all type network environments, ssh and configure remote server using emacs editor. Worked with highly concurrent softswith routing with load balancing and priority queues, media gateways on CentOS and Ubuntu. "
    :rm/referane ""}

   {:db/id 1270
    :rm/row 2
    :rm/col 1
    :rm/task "Learn how to implement Intel Integated Performance primitives (IPP) api Audio/speech codec, udp hole punching technologies named (STRN, TURN, ICE), implemented web based audio, video streaming. Configured dns extentions DNS srv. Impement protocols from RFC draft."
    :rm/referane ""}


   {:db/id 129
    :rm/row -35
    :rm/col 1
    :rm/task "Software Global Consultancy //SGC (science 2018)"
    :rm/summery "Made a successful server based deployment of Audio & video codecs (G729AB, AMR-NB, H263, H264) transcoding on debian based systems. Adopted of Groovy as shell script replacement. Restful API development with Reactjs worked with clojure, scala, phyton flux, websocket, gevent, sqlalkalemi. Implemented CPS and actor model for concurrency using go lang and erlang. "
    :rm/referane ""}

   {:db/id 1290
    :rm/row -35
    :rm/col 1
    :rm/task "Software Global Consultancy //sgc (science 2018)"
    :rm/summery "Successful server based deployment of Audio-video codec (G729AB, AMR-NB, H263, H264) transcoding on debian based systems. Addoption of Phyton and shell script replacement. commercialization of UART, gpio, smart card solutions on raspberry pi. Restful api development with Reactjs worked with clojure, scala, phyton flux, websocket, gevent, sqlalkalemi, CPS and actor model of concurrency. Learned how to run a startups without any investment and when to quet when bigtech with unlimited capital steps in."
    :rm/referane ""}


   {:db/id -18
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
    :rm/row 17
    :rm/col 0
    :rm/task "Personal Summery"
    :rm/summery "I was raised and born on noble family in Bangladesh. My late father was an liberwation war veteran.  I passed  secondary and higher Secondary from Rajshahi Cadet College. 1998-2000"
    :rm/referane ""}

  {:db/id 26
   :rm/row 5
   :rm/col 1
   :rm/task "Reference"
   :rm/summery "Salman Maruf Ifee 8801711081123 Ericsson Sharif Ahmed +1 573-479-0167 Microsoft"
   :rm/referane ""}

  {:db/id 27
   :rm/row 10
   :rm/col 1
   :rm/task "BSC on Computer Engineering. American International University Bangladesh (AIUB) (2001-2005)"
   :rm/summery "My CGPA was 3.69 out of 4, I have participated NCPC and ICPC programming contests. My final project was - Design project modeled and verified a multi-cycle and 5-stage pipelined version of 32-bit RISC processor - MIPS - with Verilog Hardware Description Language (HDL)"

   :rm/referane ""}
  ])


(def course [["Getting to Grips with Kubernetes RBAC • Liz Rice • GOTO 2019 " "2022 Online"]
             ["Samsung Device driver programming on SNMC" "2011 - Delhi"]
             ["Online 8.01x - MIT Physics I: Classical Mechanics by Walter Lewin"
              "2014 - Online"]
             ["2015 Introduction to Dependent Type Theory — Robert Harper University of Oregon" "2015 Online"]

             ["Introduction to Computer Graphics
- UC Davis Academics" "2015 - Online"]
             ["Tutorial: Building the Simplest Possible Linux System - Rob Landley, se-instruments.com" "2015 - Online"]
             ["Online eBPF and Kubernetes: Little Helper Minions for Scaling Microservices - Daniel Borkmann, Cilium" "2022 - Online"]
             ["Tutorial: Communication Is Key - Understanding Kubernetes Networking - Jeff Poole, Vivint Smart Home" "2022 Online"]
             ["Webinar: Kubernetes and Networks: Why is This So Dang Hard?" "2022 - Online"]
             ["Online Intro + Deep Dive: Kubernetes (Network) SIG - Tim Hockin, Google"
              "2022 - Online"]
             ["Container Networking From Scratch - Kristen Jacobs, Oracle" "2022 - Online"]

             ["Kubernetes Networking Intro and Deep-Dive - Bowei Du & Tim Hockin, Google"
              "2022 - Online"]

             ["Demystifying Linux MIPI DSI Subsystem - Jagan Teki, Amarula Solutions"
              "2022 - Online"]

             ["Tutorial: Device Tree (DTS), Linux Board Bring-up and Kernel Version Changing 2018" "2022 - Online"]
             ["Erlang Master Class, Joe Armstrong " "2017 - Online"]
             ["Parsing With Derivatives David Nolen" "2017 - Online"]
             ["The Do's and Don'ts of Error Handling • Joe Armstrong • GOTO" "2018 - Online"]
             ["Logic Programming, Core.Async  Timothy Baldridge" "2015 - Online"]
             ["Digital Design and Computer Architecture, ETH Zürich, Spring" "2021 - Online"]
             ["Intro to Parallel Programming. John Owens University of California, Davis"
              "2019 - Online"]
             ["Building a container from scratch in Go - Liz Rice" "2022 - Online"]


             ["\"Stop Writing Dead Programs\" by Jack Rusher (Strange Loop 2022)" "2022 - Online"]
             ["Delimited Continuations for Everyone by Kenichi Asai Ochanomizu University "
              "2017 - Online"]
             ["Probabilistic scripts for automating common-sense tasks by Alexander Lew PHD student on MIT Probabilistic Computing Projects" "2019 - Online"]
             ["Introduction to Computational Thinking  MIT 18.S191 aka 6.S083 aka 22.S092, Fall 2020 edition" "2021 - Online"]
             [ "Differentiable Programming with Julia by Mike Innes." "2021 - Online"]
             ["The impact of differentiable programming: how ∂P is enabling new science in Julia  ACM SIPGPLAN by Matt Bauman, Julia Computing" "2021  - Online"]
             ["Binomial distributions and Bayesian view by Grant Sanderson, MIT" "2018 - Online"]
             ["Public / Private Keys and Signing - Anders Brownworth" "2014 - Online"]
             ["Online Online Use Least Squares method for Mathematically Darkened Colors."
              "2015 - Online"]
             ["MIT 6.001 Structure and Interpretation" "2016 - Online"]
             ["The Beauty of Bézier Curves  Freya Holmér" "2020 - Online"]
             ["Siggraph2019 Geometric Algebra" "2021 - Online"]
             ["Pixar in a Box - Khan Academy" "2014 - Online"]
             ["2021-04-29 Arun Raghavan - How to get started with GStreamer"
              "2020 - Online"]
             ["How I Design Programs, Jeremy Gibbons, Professor of Computing in the Department of Computer Science at the University of Oxford." "2021 - Online"]])


(def topics
  ["PostGreSQL" "SQL-Query" "Indexing"
   "Functions" "PostGIS"

   "scripting" "GraalVM" "shell" "go lang"
   "awk"
   "Command Line" "git ssh"  "wireshark"
   "ip netstat ps"
   "vi emacs"
   "Linux" "Archlinux" "systemd" "alphine"
   "ubtuntu"
   "JVM"
   "Groovy" "jetty" "clojure"  "jdbc"
   "Virtualiztoin" "docker" "KVM" "kubernetes" "ingress"

   "reactjs" "d3.js" "react hooks"
   "redux" "animation"

   "audio/video" "gstreamer" "pluseaudio" "vnc" "rdp"
   "db" "redis" "graphdb" "mongodb"

   "event streaming" "kafka" "" "" ""
   "editor" "vi"
   "docker"    "redis"   "branch"
   "leadership"
   "RESTful"
   "react"

   "emacs" "CentOS" "Ubuntu."
   "reactjs" "webrtc" "ingress" "kafka" "groovy"])
