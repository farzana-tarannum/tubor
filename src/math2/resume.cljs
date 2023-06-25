(ns math2.resume
  (:require
   [react]
   [cljs.reader :as edn]
   [datascript.core :as d]
   [math2.math7 :as m7 :refer
    [grid hsl css space size path ve sec]]
   [clojure.walk :as walk]))


#{["Assistant Director - Bangladesh Telecommunication Regulatory Commision - BTRC (2009-2011)"
   [{:task
     "Performed extensive packet tracing and analysis using Wireshark for millions of network interactions."}
    {:task
     "Utilized Lua scripting in Wireshark for custom analysis and filtering."}
    {:task
     "Served as a Lawful Interceptor for the Bangladesh Telecommunication Regulatory Commission."}
    {:task
     "Participated as an active member of the National Telecommunication Inspection committee."}
    {:task
     "Inspected and assessed the Telecommunication and IT infrastructure for potential revenue or tax leaks."}
    {:task
     "Work included extended hours during Audits and Inspections while maintaining confidentiality,
sometimes having me stay available on the sight of inspections straight 24 hours"}]]
  ["Lead Engineer - SAMSUNG R&D Institute Bangladesh (2011-2013)"
   [{:task
     "Worked on device drivers for Linux kernel on Android-based mobile handsets."}
    {:task
     "Patched Linux kernel with device-specific changes based on Git."}
    {:task "Forked and maintained specific versions of the kernel for specific cell phone models."}
    {:task
     "Merged changes to upstream when kernel code is stable, sometimes resolving merge conflicts."}
    {:task
     "Mastered the process of taking systrace, JTAG, and analyzing core dumps using GDB tooling on embedded Linux systems."}
    {:task
     "Acquired knowledge of the Linux boot sequence on UBOOT and handled UART and GPIO interactions."}
    {:task "Developed memory-mapped network drivers for devices."}
    {:task
     "Received 2+1 months of training in low-level device driver programming in C from Samsung SNMC
in  Delhi, India"}
    {:task
     "Received leadership training for managing large teams of developers."}]]
  ["BSC on Computer Engineering. American International University Bangladesh (AIUB) (2001-2005)"
   [{:task "Achieved a CGPA of 3.69 out of 4."}
    {:task "Ranked int the top 15 in NCPC and ICPC programming contests."}
    {:task
     "Completed a final project involving the design, modeling, and verification of a multi-cycle and 5-stage pipelined 32-bit RISC processor (MIPS) using Verilog Hardware Description Language (HDL)."}]]
  ["Software Engineer - GENUITY Systems Limited (2006-2007)"

   [{:task "Worked as a embedded Linux programmer in C specialized in Networking."}
    {:task
     "Developed select and epoll-based network SBC (Session Border Controller) and worked with syscalls and lexical parsers."}
    {:task
     "Gained in-depth knowledge of real-time protocols, audio and video packets,
and UDP-based session controllers."}
    {:task
     "Implemented high availability failover mechanisms using ICMP and ARP protocols."}
    {:task
     "Mastered PostgreSQL query optimization and configured disk RAID for data redundancy."}
    {:task
     "Demonstrated proficiency in remote administration using SSH, Vi, Emacs, and shell scripting."}
    {:task
     "Utilized package managers such as yum and apt-get to manage software dependencies effectively."}
    {:task
     "Worked with highly concurrent softswitch routing, load balancing, and priority queues."}
    {:task "Developed media gateways on CentOS and Ubuntu."}
    {:task
     "Worked as a DevOps on audio conferencing systems, managing Linux, iptables, and routing UDP packets across diverse network environments."}

    {:task
     "Worked with highly concurrent softswitch routing, load balancing, and priority queues."}
    {:task "Developed media gateways on CentOS and Ubuntu."}
    {:task
     "Learned how to implement Intel Integrated Performance Primitives (IPP) API for audio/speech codecs."}
    {:task
     "Implemented UDP hole punching technologies such as STUN, TURN, and ICE."}
    {:task
     "Developed web-based audio and video streaming solutions."}
    {:task
     "Configured DNS extensions, specifically DNS SRV records."}
    {:task "Implemented protocols based on RFC drafts."}]]
  ["Enhance machine learning model to real time data pipeline with kafka and webassembly"
   [ "Building fully managed cloud services with kafka and Rust with no runtime or garbage collaboration."
    "Realtime stream processing and data transformation using webassembly, all in a single unified cluster,
with upto 3x less latency 5x throughput, 7x CPU utilization, 50x memory utilization."
    "feed stream clean data to Kafka topic starting with source connector in Rust, go, clojure.
Then stream the data sink into kafka source connector where webassembly pipeline is applied."]]
  ["Deep packet Inspections (DPI)"
   [{:task
     "Performed packet inspection and auditing for detecting malicious activities and lawful interception in the Linux kernel space."}
    {:task
     "Utilized eBPF technologies and systrace for packet inspection and auditing purposes."}
    {:task
     "Implemented shell scripting for systrace functionality and later transitioned to clojure on GraalVM."}
    {:task
     "Optimized PostGreSQL queries through join planning and indexing."}
    {:task
     "Utilized Redis cluster for efficient caching of frequently accessed data."}
    {:task
     "Developed data visualization using d3.js in the React-based frontend."}
    {:task
     "Implemented a RESTful API backend using clojure, go, rust, elixir for data processing and communication."}]]
  ["Fractals Labs (2016-2018)"
   "Realtime collaboration platform for data scientist"]


  ["design and develop a computational notebook with interactive programming techniques"
   [
    {:task  "'enhance students intuition' in science and technologies"}
    {:task  "leverage hands-on programming skills"}
    {:task
     "utilize visual interaction with ReactJS, SVG, Bezier curves, D3.js and React hot loading techniques"}
    {:task "Realtime collaboration platform for data scientist",}
    {:task "generate real-time code to graphics",}
    {:task "machine learning and AI concepts",}

    {:task "accommodate students with short attention spans",}
    {:task "topics computer graphics, animation, user interaction, web technologies",}
    {:task
     "apply geometric algebra, symbolic AI with Clojure and ClojureScript",}
    {:task  "prepare well-prepared lectures",}
    {:task
     "employ differentiable programming for calculus and gradient descent",}
    {:task
     "teaching Cambridge IAL computer science, mathematics, physics, IT online"}
    ]]
  ["Web based audio & video conferencing"
   [{:task "Develop the frontend using React.js and WebRTC."}
    {:task
     "Create a collaboration platform similar to Google Docs for data scientists and students."}
    {:task "Build backend services with an Ingress API gateway."}
    {:task
     "Incorporate Kafka for messaging functionality in the backend."}
    {:task
     "Utilize clojure, rust, go for connecting PostgreSQL, Kafka, and the frontend components."}]]
  ["Software Global Consultancy //sgc (science 2018)"
   [{:task
     "Successfully deployed server-based transcoding of audio-video codecs (G729AB, AMR-NB, H263, H264) on Debian-based systems."}
    {:task
     "Adopted Python and replaced shell scripts for development purposes."}
    {:task
     "Commercialized UART, GPIO, and smart card solutions on Raspberry Pi."}
    {:task
     "Developed RESTful APIs using React.js and worked with Clojure, Scala, Python, Flux, WebSocket, gevent and SQLAlchemy."}
    {:task
     "Implemented the CPS (Continuation-Passing Style) and actor model of concurrency."}
    {:task
     "Gained knowledge and experience in running startups without any investment and understanding when to quit when big tech companies with unlimited capital enter the market."}
    {:task "manage infrastructure and application operations declaratively, versioned and immutable with
Git Ops principles as guidelines",}
    ]]
  ["Edge computing, discover and Manage IoT devices from Kubbernetes with Akri."
   [{:task
     "controller automatically deploys workloads to discovered devices",}
    {:task "challenges scaling heterogeneous compute and IoT devices with protocol translation gateway
dynamic device discovery and management",}
    {:task "customers are migrant workers who maintain and deploy IoT on their homes"}
    {:task "monitor health of biogas-based reactor"}
    {:task "maintain 24/7 connectivity for distance learning"}
    {:task "power homes with solar and hydrogen-based fuel cell"




     :tasks
     [""


      ""],
     :akri
     "enables dynamic leverage of devices in Kubernetes cluster",
     :environment "heterogeneous environments on remote locations",
     :rust "built for low footprint optimization",
     :platform "K3s on Edge Amlogic-based Arm SOCs",
     :technologies "Kubernetes, Akri, udev, OPC UA, ONVIF, gRPC",
     :job "maintain and install IoT and network devices"}
    {:scenario "hetereogeneous GPU cluster"}]]
  ["Serverless end to end video conferencing and live Streaming v1 (2006-2012)"
   [{:task
     "Implement end-to-end low latency network connectivity solutions."}
    {:task
     "Utilize network UDP hole punching technologies, which are becoming the standard in 2021/2022."}
    {:task
     "Apply UDP hole punching for voice and video communication using protocols like SRT."}
    {:task
     "Implement UDP hole punching for HTTP3 (QUIC) to reduce network latency."}
    {:task
     "Explore the use of TCP-based protocols like RTMP for streaming video providers such as Google, Zoom, Facebook, and Twitch."}
    {:task
     "Identify the drawbacks of TCP-based protocols in terms of increased network latency."}
    {:task
     "actor modle in scala to make it scalabe and configuration managed side mongodb nodejs with reactjs fontend with webrtc. "}
    {:task
     "Near real time Edge processing with\nActor model and CPS adoption for scalability"}]]
  ["System Engineer at GrameenPhone. (2007-2009)"
   [{:task
     "Develop and maintain mediation servers for telecommunication systems."}
    {:task
     "Implement modern Microservices architecture with a focus on simplicity."}
    {:task
     "Ensure seamless communication between different nodes and systems in multi-vendor environments."}
    {:task
     "Address compatibility issues and ensure smooth interoperability between systems."}]]}
