(ns math2.db
  (:require
   [datascript.core :as d]
   [clojure.string :as str]))

(def schema
  {:rm/projects {:db/cardinality :db.cardinality/many
                 :db/valueType   :db.type/ref}
   :rm/jobs   {:db/cardinality :db.cardinality/many
               :db/valueType   :db.type/ref}
   :rm/code {:db/unique :db.unique/identity}
   })

;; (/dev/kvm, GPU pass-through)

(defn join [m]
  (mapv
   (fn [[a b]]
     {:task (str (str/capitalize (name a)) " " b)}) m))

(def vert-hostdevice "Building a virtualization Desktop Lab (VDE)  on cloud infrastructure supervised by
engineers of NASA Jet Propulsion Lab where  distributed computers are acting as physical hosts
on the Cloud which is spawning millions of virtual operating systems while the  users are connected
through the consoles that are based on ARM-SOC over the network to access the system.
Converting thousands of physical systems
resources into million of virtual operating systems in a secure and efficient way was the primary
challenge of that project. After having rollout done in a concise timeframe, we then pay attention
to network latency and shared graphics co-processors that enables users to stream games using consoles
rather than purchasing high performance graphics computers. We use Kubernetes, alpine linux and
Amazon firecracker OS under KVM hypervisor to scale these system into millions.")

(def vde
  {:project "Building a virtualization Desktop Lab (VDE) on cloud infrastructure supervised by engineers of NASA Jet Propulsion Lab"
   :details [
             {:description "Utilized distributed computers as physical hosts on the cloud."
              :feature "distributed-computing"}
             {:description "Spawned millions of virtual operating systems on the cloud infrastructure."
              :feature "virtualization"}
             {:description "Users connected through consoles based on ARM-SOC over the network to access the system."
              :feature "ARM-SOC consoles"}
             {:description "Converted thousands of physical system resources into millions of virtual operating systems."
              :feature "resource-conversion"}
             {:description "Ensured secure and efficient virtualization of resources was the primary challenge of the project."
              :feature "security"}
             {:description "Paid attention to network latency and shared graphics co-processors for streaming games using consoles instead of high-performance graphics computers."
              :feature "game-streaming"}
             {:description "Used Kubernetes, Alpine Linux, and Amazon Firecracker OS under the KVM hypervisor to scale the system into millions."
              :feature "technology-stack"}]})



(def gitops "gitops manage infrastructure and application operation declaratively,
versioned and Immutabe, pulled automatically, continuously reconciled the system state and attempt to apply desired state, adopted  these gitops principle as guideline and comming by with get practices thus gateway confiration drift, everyting written in code make it reusable and have a audit trail by making it immutabe no seaking in chane.")

(def gitops-tasks
  (map
   (fn [[a b]]
     {:task (str (str/capitalize (name a)) " " b)})
   {:manage "infrastructure and application operations declaratively, versioned, and immutable"
    :pull "changes automatically"
    :reconcile "system state continuously"
    :apply "desired state"
    :adopt "GitOps principles as guidelines"
    :adhere "to best practices for preventing configuration drift"
    :write "everything in code for reusability"
    :maintain "immutability for audit trail and preventing ad hoc changes"}))




(defonce data
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


   {:db/id 1666
    :rm/code :iccddrb
    :rm/occupation "Computer Engineer"
    :rm/name ""
    :rm/age 38
    :rm/phone "880-1711961024"
    :rm/email ""

    :rm/summery  "With over 15 years of experience in Software engineering, data scientist, cloud engineering, my primary focus is on ensuring developer productivity and system reliability while maintaining faster development cycles. I specialize in designing and managing databases, developing error detection tools, programming logic, and creating applications for the management of study tools. Additionally, I excel in creating detailed test plans and executing automation scripts to identify and track bugs, conducting vulnerability assessments, and promoting gender diversity and collaboration among the team members. I am dedicated to mentoring young professionals and preparing them for higher responsibilities while maintaining high standards"
    :rm/projects (into [2 27 25 42]
                       [12 13  16 117 23 7  127 15 35 129 20 2 225 1132
                        2132])
    :rm/jobs [9 10]
    :rm/proects.title "Projects and accomplishment"}



   {:db/id 2
    :rm/row 0
    :rm/col 0
    :rm/task "Design and develop a computational notebooks with interactive programming techniques on RUST lang"
    :rm/summery "I have been teaching Cambridge IAL computer science, mathematics, physics, IT online since covid statred. My lectures are very well prepared by leveraging my hands on programming skill on computer graphics, animation, user interaction and web technologies these visual interaction with reactjs svg bazier curves d3.js gematric algebra symbolic AI with clojure and clojurescript and deffientiable programming for calculus and gredient decent that is a prelude to machine learing and AI. I have leveraged react hot loading technics to generate code to graphics realtime
on classes. These techniques helps students get better at receiving intuition of science & technologies even who those who have short attention span."
    :rm/from-date #inst "2019-05-01T00:00:00.000-00:00"
    :rm/to-date #inst "2023-07-01T00:00:00.000-00:00"
    :rm/breakdowns
    (join
     {:teaching "Cambridge IAL computer science, mathematics, physics, IT online"
      :realtime "Realtime collaboration platform for data scientist"
      :prepare "well-prepared lectures"
      :leverage "hands-on programming skills"
      :topics "computer graphics, animation, user interaction, web technologies"
      :utilize "visual interaction with Dioxus, SVG, Bezier curves with hot loading techniques"
      :apply "geometric algebra, symbolic AI with Rust"
      :employ "differentiable programming for calculus and gradient descent"
      :introduce "machine learning and AI concepts"
      :generate "real-time code to graphics"
      })

    :rm/referane ""}


   {:db/id 1132
    :rm/row 0
    :rm/col 0
    :rm/from-date #inst "2015-05-01T00:00:00.000-00:00"
    :rm/to-date #inst "2022-07-01T00:00:00.000-00:00"
    :rm/task  "Edge computing using hetereogeneous GPU cluster, discover and Manage IoT devices from Kubbernetes with Akri."
    :rm/breakdowns (join {:customers "migrant workers"
                           :tasks (str/join " "  ["maintain and deploy IoT on their homes"
                                              "monitor health of biogas-based reactor"
                                              "maintain 24/7 connectivity for distance learning"
                                              "power homes with solar and hydrogen-based fuel cell"])
                           :job "maintain and install IoT and network devices"
                           :environment "heterogeneous environments on remote locations"
                           :technologies "Kubernetes, Akri, udev, OPC UA, ONVIF, gRPC"
                           :protocols (str/join ["common IoT protocols"
                                                 "protocol translation gateway"
                                                 "dynamic device discovery and management"])
                           :platform "K3s on Edge Amlogic-based Arm SOCs"
                           :challenges "scaling heterogeneous compute and IoT devices"
                           :akri "enables dynamic leverage of devices in Kubernetes cluster"
                           :controller "automatically deploys workloads to discovered devices"
                          :rust "built for low footprint optimization"})
    :rm/summery  (clojure.string/join
                  ""
                  ["In our scenario we customers are migrant workers, they really depands on us to maintain and deploy IOT on their home, monitoring health of bio gas based reactor, maintain 24/7 connectivity as their clildren depends on distance learning, powering home with solar and hydogen based fuel cell. Our job is to maintain and install IOT  and network device on those hetarogenious envirnoment on remote locations. In order to smooth sailing we have migrathed to kubbernetes leverage akri to communicates via  common Iot protocols udev for divices on linux local file systems (USB cameras, GPU, microphone), OPC UA (industrial machinery), ONVIF (IP cameras) and simple gRPC interface for adding support for new protocols on RUSt & Go lang (for example adding zeroconf based mdns devices)."
                   "Pattern of communication made with devices are discovery handlers by implemanting these are protocols thn deploy a long running pod as information broaker for exaple for ip cameras, pulling streams of I frames and P frames from camera work as protocl translation gateway so dicover a usb camera and broker aka long running pod advertises as an ip camera using akri to discover microphone, cameras then use it on streaming applicaion then go one step further by managing this divices by performing formal update on them. Run k3s on the Edge Amlogic based Arm SOCs not only hatarogenious compute by also environment. IOT devices, mcu class sensors controllers etc and not only there a bunch of different ones but also they're constantly scaling up and down. As these devices are too small to run kuberneters temselves or have just one fix functionor. okri enables dynamicallly leverge these devices in kubernetes cluster and aim to the interface that abstracts the way the dtails of discovering and using iot devices on the edge. First it discovers the devices then for every device that ocari discovers in connects it to a the cluster by creating a kubernetes resource to represent device, then we have a controller that can automatically deploy workloads to discover devices, handle common occurrences that are more common on the edge such as devices dynamicallly disappearing, loos fo connectivity to the device or over heating or all of sudden coming online and Akri can detech these occurrences create, update, remove kubernetesw resource and bring down or deploy appropriate worklaod automatically. Bulit in rust to optimize to being as low footprint as possible" "hetarogenious gpu cluster"])
    :rm/referane ""}

   {:db/id 2132
    :rm/row 0
    :rm/col 0
    :rm/from-date #inst "2015-05-01T00:00:00.000-00:00"
    :rm/to-date #inst "2023-01-01T00:00:00.000-00:00"
    :rm/task  "Firecracker MicroVM using KubeVirt on Kubernetes cluster."
    :rm/breakdowns [{:task "Run VM and Firecracker MicroVM workloads alongside container workloads in Kubernetes using KubeVirt."
                           }
                          {:task "Serve as an infrastructure-as-a-service platform for combining VM and container orchestration under Kubernetes, replacing OpenStack."
                           }
                          {:task "Transition old VM-based workloads to containerized workloads for Virtual Desktop Infrastructure (VDI) and Video FX Rendering."
                           }
                          {:task "Enable nested Kubernetes on top of Kubernetes using KubeVirt for virtual machines as an underlying infrastructure-as-a-service layer for development or staging."
                           }
                          {:task "Spawn multiple Kubernetes clusters on top of an existing cluster for development, staging, and production environments."
                           }
                          {:task "Build pipelines, like Tecton, for managing virtual machines and containers using KubeVirt."
                           }
                          {:task "Ensure zero-downtime updates by avoiding disruptions to workloads during updates and protecting against node-level upgrades."
                           }
                          {:task "Deploy and manage virtual machines with persistent storage, network interfaces, and other configuration using KubeVirt."
                           }]
    :rm/summery "KubeVirt is Kubernetes extension that allows running  VM and Firecracer MicroVM workloads and  natively side by side with container workloads , as a infrastructure as a service that allows put together VM and cotainer orkastation under Kubernetes which was previously done using with openstack in our case, transition path of old VM based workloads to containerized workloads for Virtual desktop (VDI), Video Fx Rendering. Another one kind of emereged which is a predominat use case to get hold of nested kubernetes on top of kubernetes using kubevirt for virtual machines that works uderlying infrastructure as service nested layer for development or staging. say you have kubernetes cluster and you want to spawn lots of little kubernetes clusters on top of that for development or staging this is how we run the productions, deploying cube verts on the under layer so the bare metal kubernetes layer spin up virtual machines that's the underlying substrate for the next layer of kubernetes clusters and also buliding pipelines like tecton. posting a virtual machine from manifests, hit the api server get persisted on etcd virt-controller spin up virt-hadler instruct virt-lancher, virt operator that manage install update kubevert that pretty neat ensure 0 downtime updates that going to guarantee when doing updates not to disrupt workloads and protect people from kubbernetes doing node level upgrate by evacuating and live migrating virtual machines. by posting a custom resurce that gives operator instractions install on kubevert namespace, persistent virtual machine can be lunch with by combining storage creation and import and how we want virtual machine to start all in one manifest yaml of VirtualMachine kind. Way of doing storage part is this thing call data volume template where pvc is defined and the source that is going to be imported into that pvc then import virtual machine disk from a container image on the pvc and bind the network interface"
    :rm/referane ""}


   {:db/id 3132
    :rm/row 0
    :rm/col 0
    :rm/from-date #inst "2020-03-01T00:00:00.000-00:00"
    :rm/to-date #inst "2023-02-01T00:00:00.000-00:00"
    :rm/task  "enhance machine learning model to real time data pipeline with kafka and WebAssembly"
    :rm/breakdowns (mapv (fn [a] {:task a}) [
                         "Building fully managed cloud services with kafka and Rust with no runtime or garbage collaboration."
                         "Realtime stream processing and data transformation using WebAssembly all in a single unified cluster with upto 3x less latency 5x throughput, 7x cpu utilization, 50x memory utilization."
                         "feed stream clean data to kafka topic starting with rust http source connector, streaming data sink into kafka source connector where WebAssembly pipeline is applied which enable direct contorl over the streaming datathen transform event flows to kafka consumer."])
    :rm/summery  (clojure.string/join "" [
                                          "Building fully managed cloud services with kafka and Rust with no runtime or garbage collaboration."
                                          "Realtime stream processing and data transformation using WebAssembly all in a single unified cluster with upto 3x less latency 5x throughput, 7x cpu utilization, 50x memory utilization."
                                          "feed stream clean data to kafka topic starting with rust http source connector, streaming data sink into kafka source connector where WebAssembly pipeline is applied which enable direct contorl over the streaming datathen transform event flows to kafka consumer."
                                          ])
    :rm/referane ""}










   {:db/id 20
    :rm/row 7
    :rm/col 6
    :rm/task "Serverless end to end video conferencing and live Streaming v1"
    :rm/from-date #inst "2005-02-01T00:00:00.000-00:00"
    :rm/to-date #inst "2012-01-01T00:00:00.000-00:00"
    :rm/breakdowns [
                    {:task "Implement end-to-end low latency network connectivity solutions."},
                    {:task "Utilize network UDP hole punching technologies, which are becoming the standard in 2021/2022."},
                    {:task "Apply UDP hole punching for voice and video communication using protocols like SRT."},
                    {:task "Implement UDP hole punching for HTTP3 (QUIC) to reduce network latency."},
                    {:task "Explore the use of TCP-based protocols like RTMP for streaming video providers such as Google, Zoom, Facebook, and Twitch."},
                    {:task "Identify the drawbacks of TCP-based protocols in terms of increased network latency."},
                    {:task "actor modle in scala to make it scalabe and configuration managed side mongodb nodejs with reactjs fontend with webrtc. "}
                    {:task "Near real time Edge processing with
Actor model and CPS adoption for scalability"}]

    :rm/summery "End to end low latency network connectivity solutions using network UDP whole punching technologies which is becomming standard in 2021/2022 not only for voice and video (SRT) but also in http3 (QUIC) where as streaming Vedeo providers like Google, zoom, facebook, twitch  uses of TCP-based protocls like RTMP thus increase network latency."
    :rm/referane ""}

   {:db/id 35
    :rm/row -1
    :rm/col 0
    :rm/from-date #inst "2012-02-01T00:00:00.000-00:00"
    :rm/to-date #inst "2023-01-01T00:00:00.000-00:00"
    :rm/breakdowns [
                    {:task "Develop the frontend using React.js and WebRTC."},
                    {:task "Create a collaboration platform similar to Google Docs for data scientists and students."},
                    {:task "Build backend services with an Ingress API gateway."},
                    {:task "Incorporate Kafka for messaging functionality in the backend."},
                    {:task "Utilize clojure, rust, go for connecting PostgreSQL, Kafka, and the frontend components."},
                    ]
    :rm/task "Web based audio & video conferencing"
    :rm/summery "Fontend is developed with reactjs and webrtc with google doc like collaboration platform for data scientist and students. Developing backend services with ingress API gateway, kafka for messaging, clojure for connecting postgresql, kafka and fontend."
    :rm/referane ""}






  {:db/id 7
   :rm/row -30
   :rm/col 1
   :rm/task "Fractals Labs"
   :rm/from-date #inst "2016-01-01T00:00:00.000-00:00"
   :rm/to-date #inst "2023-07-01T00:00:00.000-00:00"
   :rm/breakdowns [
                   {:task ""},
                   {:task ""},
                   {:task ""},
                   {:task ""},

                   ]
   :rm/summery "Implemented Virtual Desktop Environment(VDE) for University Labs using KVM Libvert virtualization on Archlinux based hypervisor that hosts virtualized linux and windows desktops and docker for containerization on the server and desktop is replaced by ARM based SoC with vnc & rdp clients installed where students can access remote desktops and spawn docker based services on remote services"
   :rm/referane ""}


   {:db/id 13
    :rm/row -15
    :rm/col 0
    :rm/from-date #inst "2007-06-01T00:00:00.000-00:00"
    :rm/to-date #inst "2009-01-01T00:00:00.000-00:00"
    :rm/task "System Engineer on  Operations System and Software at GrameenPhone."
    :rm/breakdowns [{:task "Develop and maintain mediation servers as modern microservices using Java, Oracle SQL, shell scripting, Jetty, and Linux command line utilities."}
                    {:task "Implement reverse proxy and SSH port forwarding for remote server login and utilize vi as a remote editor."}
                    {:task "Design and implement push and pull-based systems and services for monitoring alarms and IP packet tracing using Wireshark."}
                    {:task "Develop and maintain mediation servers for telecommunication systems."},
                    {:task "Implement modern Microservices architecture with a focus on simplicity."},
                    {:task "Ensure seamless communication between different nodes and systems in multi-vendor environments."},
                    {:task "Address compatibility issues and ensure smooth interoperability between systems."},
]

    :rm/summery "Developed and maintained mediation servers which was in a nut shell modern days micro services with lot of bells and whistle with Java, Oracle SQL, shell scripting, jetty, linux command line utilities - awk, ssh port forwarding and reverse   proxy for remote server login and vi as remote editor. As well implemented push and pull based systems and services for monitoring alarms and IP packet tracing using wireshark. Main goal is to develop and maintain mediation servers which is in a nut shell modern days MicroServies with less of bells and whistle. In order to telecommunication system work properly every single nodes has to communicate with other systems and often in multi-vendor system things are not compatible."
    :rm/referane ""}






   {:db/id 115
    :rm/row 8
    :rm/col 1

    :rm/from-date #inst "2009-12-01T00:00:00.000-00:00"
    :rm/to-date #inst "2011-02-01T00:00:00.000-00:00"


    :rm/task "Assistant Director - Bangladesh Telecommunication Regulatory Commision - BTRC."
    :rm/breakdowns [
                    {:task "Performed extensive packet tracing and analysis using Wireshark for millions of network interactions."},
                    {:task "Utilized Lua scripting in Wireshark for custom analysis and filtering."},
                    {:task "Served as a Lawful Interceptor for the Bangladesh Telecommunication Regulatory Commission."},
                    {:task "Participated as an active member of the National Telecommunication committee."},
                    {:task "Inspected and assessed the Telecommunication and IT infrastructure for potential reverses or tax leaks."},
                    {:task "Worked tirelessly, including extended hours and 24/7 availability, to fulfill job responsibilities."},
                    ]
    :rm/summery "tasted by at least millions time using wireshark packct tracing and lua scripting when I was Lawful Interseptor Bangladesh Telecommunication Regulatory Commisions.
I was an active member of National Telecommunication committee, I used to inspect Telecommunication and IT infrastructure, primary goal is to find out potential reverse or Tax leak from those infrastructure. As only person that had proper communication background my job was very extensive, some time it was extended 24/7."
    }

   {:db/id 15
    :rm/row -1
    :rm/col 4
    :rm/from-date #inst "2011-02-01T00:00:00.000-00:00"
    :rm/to-date #inst "2014-07-01T00:00:00.000-00:00"

    :rm/task "Deep packet Inspections (DPI)"
    :rm/breakdowns [
                    {:task "Performed packet inspection and auditing for detecting malicious activities and lawful interception in the Linux kernel space."},
                    {:task "Utilized eBPF technologies and systrace for packet inspection and auditing purposes."},
                    {:task "Implemented shell scripting for systrace functionality and later transitioned to using Clojure on GraalVM."},
                    {:task "Optimized PostGreSQL queries through join planning and indexing."},
                    {:task "Utilized Redis cluster for efficient caching of frequently accessed data."},
                    {:task "Developed data visualization using d3.js in the React-based frontend."},
                    {:task "Implemented a RESTful API backend using Clojure for data processing and communication."},
                    ]
    :rm/summery "Packet Inspection and Auditing for malicious activities and Lawful Interception in Linux Kernel Space using eBPF technologies and systrace using shell scripting later successfully used clojure on GraalVM and PostGreSQL query optimization using join planing and indexing also used redis cluster for cashing frequent data. Develop react based data visualization using d3.js and Clojure based RESTful API backend."
    }



   {:db/id 16
    :rm/row -20
    :rm/col 0
    :rm/from-date #inst "2011-05-01T00:00:00.000-00:00"
    :rm/to-date #inst "2013-11-01T00:00:00.000-00:00"

    :rm/task "Lead Engineer - SAMSUNG R&D"
    :rm/summery "I have worked on device drivers on Linux kernel on Android based mobile handsets and patched linux kernel with device specific changes based on git, As well as fork and maintain specific version then merge it to upstream when stable. Applied three way git merging as well as merge conflicts, mastered on taking systrace I also received leadership training for very big teams of developers"
    :rm/breakdowns
    [
     {
      :task "Worked on device drivers for Linux kernel on Android-based mobile handsets."
      }
     {
      :task "Patched Linux kernel with device-specific changes based on Git."
      }
     {
      :task "Forked and maintained specific versions of the kernel."
      }
     {
      :task "Merged changes to upstream when stable, including applying three-way Git merging and resolving merge conflicts."
      }
     {
      :task "Mastered the process of taking systrace, JTAG, and analyzing core dumps using GDB tooling on embedded Linux systems."
      }
     {
      :task "Acquired knowledge of the Linux boot sequence on UBOOT and handled UART and GPIO interactions."
      }
     {
      :task "Developed memory-mapped network drivers."
      }
     {
      :task "Received 2+1 months of training in low-level device driver programming in C from Samsung SNMC in India and Bangladesh."
      }
     {
      :task "Received leadership training for managing large teams of developers."
      }
     ]


    :rm/referane ""}





   {:db/id 117
    :rm/row -10
    :rm/col 1

    :rm/from-date #inst "2003-05-01T00:00:00.000-00:00"
    :rm/to-date #inst "2008-06-01T00:00:00.000-00:00"


    :rm/task "Software Engineer - GENUITY Systems Limited"
    :rm/breakdowns [{:task "Worked as a Linux network programmer in C."}
                    {:task "Developed select and epoll-based network SBC (Session Border Controller) and worked with syscalls and lexical parsers."}
                    {:task "Gained in-depth knowledge of real-time protocols, voice and video packets, and UDP-based session controllers."}
                    {
                     :task "Implemented high availability failover mechanisms using ICMP and ARP protocols."
                     }
                    {
                     :task "Mastered PostgreSQL query optimization and configured disk RAID for data redundancy."
                     }
                    {
                     :task "Demonstrated proficiency in remote administration using SSH, Emacs, and shell scripting."
                     }
                    {
                     :task "Utilized package managers such as yum and apt-get to manage software dependencies effectively."
                     }
                    {
                     :task "Worked with highly concurrent softswitch routing, load balancing, and priority queues."
                     }
                    {
                     :task "Developed media gateways on CentOS and Ubuntu."
                     }
                    {
                     :task "Worked as a DevOps on audio conferencing systems, managing Linux, iptables, and routing UDP packets across diverse network environments."
                     }
                    {
                     :task "Configured remote servers using Emacs editor and SSH."
                     }
                    {
                     :task "Worked with highly concurrent softswitch routing, load balancing, and priority queues."
                     }
                    { :task "Developed media gateways on CentOS and Ubuntu."}
                    { :task "Learned how to implement Intel Integrated Performance Primitives (IPP) API for audio/speech codecs."}
                    { :task "Implemented UDP hole punching technologies such as STUN, TURN, and ICE."}
                    { :task "Developed web-based audio and video streaming solutions."}
                    { :task "Configured DNS extensions, specifically DNS SRV records."}
                    { :task "Implemented protocols based on RFC drafts."}
]
    :rm/summery "As a devOps on audio conferencing systems and I have worked with liunx, iptables, shell scripting, routing UDP packets over all type network environments, ssh and configure remote server using emacs editor. Worked with highly concurrent softswith routing with load balancing and priority queues, media gateways on CentOS and Ubuntu. "

    :rm/referane ""}


   {:db/id 1290
    :rm/row -35
    :rm/col 1
    :rm/breakdowns [
                    {:task "Successfully deployed server-based transcoding of audio-video codecs (G729AB, AMR-NB, H263, H264) on Debian-based systems."}
                    { :task "Adopted Python and replaced shell scripts for development purposes."}
                    { :task "Commercialized UART, GPIO, and smart card solutions on Raspberry Pi."}
                    { :task "Developed RESTful APIs using React.js and worked with Clojure, Scala, Python, Flux, WebSocket, gevent, and SQLAlchemy."}
                    { :task "Implemented the CPS (Continuation-Passing Style) and actor model of concurrency."}
                    { :task "Gained knowledge and experience in running startups without any investment and understanding when to quit when big tech companies with unlimited capital enter the market."}
                    {:task {:manage "infrastructure and application operations declaratively, versioned, and immutable"
                      :pull "changes automatically"
                      :reconcile "system state continuously"
                      :apply "desired state"
                      :adopt "GitOps principles as guidelines"
                      :adhere "to best practices for preventing configuration drift"
                      :write "everything in code for reusability"
                      :maintain "immutability for audit trail and preventing ad hoc changes"}}
                    ]
    :rm/from-date #inst "2013-05-01T00:00:00.000-00:00"
    :rm/to-date #inst "2018-06-01T00:00:00.000-00:00"
    :rm/task "Software Global Consultancy //SGC"
    :rm/summery "Successful server based deployment of Audio-video codec (G729AB, AMR-NB, H263, H264) transcoding on debian based systems. Addoption of Phyton and shell script replacement. commercialization of UART, gpio, smart card solutions on raspberry pi. Restful api development with Reactjs worked with clojure, scala, phyton flux, websocket, gevent, sqlalkalemi, CPS and actor model of concurrency. Learned how to run a startups without any investment and when to quet when bigtech with unlimited capital steps in."
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
   :rm/breakdowns [
                   {:task "Participated in NCPC and ICPC programming contests."}
                   {:task "Completed a final project involving the design, modeling,
                          and verification of a multi-cycle and 5-stage pipelined 32-bit
                          RISC processor (MIPS) using Verilog Hardware Description Language (HDL)."}
                   ]
   :rm/task "BSC on Computer Engineering"
   :rm/from-date #inst "2000-05-01T00:00:00.000-00:00"
   :rm/to-date #inst "2005-06-01T00:00:00.000-00:00"

   :rm/summery "My CGPA was 3.69 out of 4, I have participated NCPC and ICPC programming contests. My final project was - Design project modeled and verified a multi-cycle and 5-stage pipelined version of 32-bit RISC processor - MIPS - with Verilog Hardware Description Language (HDL)"

   :rm/referane ""}
   ])

(def topics
  [
   {:db/id :G__20
    :rm/tag :react
    :rm/names ["React" "Vue.js" "Node.js" "webpack" ]
    :rm/parent :G__15}
   {:db/id :G__24
    :rm/tag :python
    :rm/names ["Python" "Django" "Flask" "NumPy" "Pandas"]}
   {:db/id :G__26
    :rm/tag :java
    :rm/names ["Java" "Spring" "Hibernate" "Kotlin" "Gradle"
               "Kotlin Native" "Coroutines" "Gradle Plugin" "Ktor"]}
   {:db/id :G__30
    :rm/tag :ruby
    :rm/names ["Ruby" "Rails" "Sinatra" "RSpec" "Capistrano"]}
   {:db/id :G__32
    :rm/tag :ruby
    :rm/names []}
   {:db/id :G__36
    :rm/tag :fp
    :rm/names ["Scala" "Akka" "Play" "Slick" "SBT"]}
   {:db/id :G__15
    :rm/parent :G__36
    :rm/tag :js
    :rm/names ["JavaScript"]}
   {:db/id :G__343
    :rm/tag :devops
    :rm/name "Devops"}
   {:db/id :G__39
    :rm/parent :G__343
    :rm/tag :container
    :rm/names ["Docker" "Go" "Kubernetes" "Jenkins" "Ansible" "Terraform"
               "Microservices" "Service Mesh" "Istio" "Linkerd" "Envoy"
               "Serverless" "FaaS" "AWS Lambda" "Azure Functions" "Google Cloud Functions"]}
   {:db/id :G__41
    :rm/tag :db
    :rm/names ["PostgreSQL" "MongoDB" "Redis" "Elasticsearch" "SQL-Query" "Indexing" "Functions" "PostGIS"]
    }
   {:db/id :G__46
    :rm/names ["GraphQL" "RESTful" "gRPC" "OpenAPI" "JSON"]
    :rm/tag :rpc
    }
   {:db/id :G__49
    :rm/tag :cloud
    :rm/names ["AWS" "Azure" "Google Cloud" "Firebase" "Lambda"]}
   {:db/id :G__51
    :rm/tag :git
    :rm/names ["Git" "GitHub" "GitLab" "Bitbucket" "CI/CD"]}
   {:db/id :G__54
    :rm/tag :se
    :rm/names ["Agile" "Scrum" "Kanban"]}
   {:db/id :G__57
    :rm/tag :ml
    :rm/names ["Machine Learning" "Deep Learning" "Computer Vision" "NLP" "TensorFlow"]}
   {:db/id :G__60
    :rm/names ["Security" "OWASP" "Penetration Testing" "Encryption" "Authentication"]
    :rm/tag :hack}
   {:db/id :G__5
    :rm/tag :cs
    :rm/names ["computer graphics"]
    }
   {:db/id :G__8
    :rm/tag :cs}
   {:db/id :G__11
    :db/tag :system-programming
    :db/names ["c" "posix" "epoll" "linux kernel"]}
   {:db/id :G__14
    :rm/tag :math
    :rm/names ["math" "physics"]
    }])



(def course (map (fn [[a b & rest]]
                   {:db/id (gensym)
                    :rm/course a
                    :rm/timeline b
                    })
                 [["Getting to Grips with Kubernetes RBAC • Liz Rice • GOTO 2019"
                   "2022 Online" :kubernetes ]
                  ["Samsung Device driver programming on SNMC"
                   "2011 - Delhi" :kernel]
                  ["Online 8.01x - MIT Physics I: Classical Mechanics by Walter Lewin"
                   "2014 - Online" :physics]
                  ["2015 Introduction to Dependent Type Theory — Robert Harper University of Oregon"
                   "2015 Online" :cs]

                  ["Introduction to Computer Graphics - UC Davis Academics"
                   "2015 - Online" :cs ]
                  ["Tutorial: Building the Simplest Possible Linux System - Rob Landley, se-instruments.com" "2015 - Online" :linux]
                  ["Online eBPF and Kubernetes: Little Helper Minions for Scaling Microservices - Daniel Borkmann, Cilium" "2022 - Online"  :kubernetes]
                  ["Tutorial: Communication Is Key - Understanding Kubernetes Networking - Jeff Poole, Vivint Smart Home"
                   "2022 Online" :kubernetes]
                  ["Webinar: Kubernetes and Networks: Why is This So Dang Hard?" "2022 - Online"
                   :kubernetes ]
                  ["Online Intro + Deep Dive: Kubernetes (Network) SIG - Tim Hockin, Google"
                   "2022 - Online"
                   :kubernetes ]
                  ["Container Networking From Scratch - Kristen Jacobs, Oracle" "2022 - Online"
                   :docker ]
                  ["Kubernetes Networking Intro and Deep-Dive - Bowei Du & Tim Hockin, Google"
                   "2022 - Online" :kubernetes]
                  ["Demystifying Linux MIPI DSI Subsystem - Jagan Teki, Amarula Solutions"
                   "2022 - Online" :kernel]

                  ["Tutorial: Device Tree (DTS), Linux Board Bring-up and Kernel Version Changing 2018" "2022 - Online" :kernel ]
                  ["Erlang Master Class, Joe Armstrong " "2017 - Online"
                   :actor-model]
                  ["Parsing With Derivatives David Nolen" "2017 - Online" :cs]
                  ["The Do's and Don'ts of Error Handling • Joe Armstrong • GOTO" "2018 - Online"
                   :actor-model]
                  ["Logic Programming, Core.Async  Timothy Baldridge" "2015 - Online"
                   :fp :logic :ai]
                  ["Digital Design and Computer Architecture, ETH Zürich, Spring" "2021 - Online"
                   :cse]
                  ["Intro to Parallel Programming. John Owens University of California, Davis"
                   "2019 - Online" :cuda :ai]
                  ["Building a container from scratch in Go - Liz Rice" "2022 - Online"
                   :linux :go]


                  ["\"Stop Writing Dead Programs\" by Jack Rusher (Strange Loop 2022)" "2022 - Online" :cs]
                  ["Delimited Continuations for Everyone by Kenichi Asai Ochanomizu University "
                     "2017 - Online" :cs]
                    ["Probabilistic scripts for automating common-sense tasks by Alexander Lew PHD student on MIT Probabilistic Computing Projects" "2019 - Online" :ai]
                    ["Introduction to Computational Thinking  MIT 18.S191 aka 6.S083 aka 22.S092, Fall 2020 edition" "2021 - Online":cs]
                  ["Differentiable Programming with Julia by Mike Innes." "2021 - Online" :ml :julia]
                    ["The impact of differentiable programming: how ∂P is enabling new science in Julia  ACM SIPGPLAN by Matt Bauman, Julia Computing" "2021  - Online" :ml :julia]
                    ["Binomial distributions and Bayesian view by Grant Sanderson, MIT" "2018 - Online" :math]
                    ["Public / Private Keys and Signing - Anders Brownworth" "2014 - Online" :cryptography]
                    ["Online Online Use Least Squares method for Mathematically Darkened Colors."
                     "2015 - Online" :cs :uiux :colors]
                    ["MIT 6.001 Structure and Interpretation" "2016 - Online" :cs]
                  ["The Beauty of Bézier Curves  Freya Holmér" "2020 - Online"
                   :cg :react :svg]
                    ["Siggraph2019 Geometric Algebra" "2021 - Online" :math]
                    ["Pixar in a Box - Khan Academy" "2014 - Online" :cg :math :svg :react]
                    ["2021-04-29 Arun Raghavan - How to get started with GStreamer"
                     "2020 - Online" :voip :linux]
                  ["How I Design Programs, Jeremy Gibbons, Professor of Computing in the Department of Computer Science at the University of Oxford."
                   "2021 - Online" :cs] ]  ))







(comment
  (def conn
    (d/create-conn schema))

  (d/transact! conn data)


  (d/q '[:find ?s ?b
         :where
         [?p :rm/task ?s]
         [?p :rm/breakdowns ?b]
         ] @conn)


  )
