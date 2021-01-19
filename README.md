# Screen-sharing-tool
With this tool you can control a pc from another pc. In this one machine is controller or server, from this machine client machine will be controlled. In this tool client machine, keyboard, mouse and screen all things captured.

## Features
This tool has following features:
* With this we can control a pc from another pc over a network
* We can see see mouse move position, done events on mouse up, down and on click events.
* We can also give keyboard buttons pressed from master machine to controller machine.

## Concept
* This tool is made with use of socket programming in java. This tool has a controller machine which is client and a master machine which is our server.
* Firstly, we have to start server machine, then it will listen on given port.
* Then we start client and connect with server IP and port
* Then you can control your client machine fully with server machine.

## Prerequisite
* Firstly, download gradle from (https://gradle.org/install/) and set environment variable.
* Donwload java, from (https://www.oracle.com/in/java/technologies/javase-downloads.html) and set environment variable till jdk/bin folder

## Installation
* Download zip file of this repository
* Unzip file and paste folder in some directory
* Then open command prompt and go inside screen-sharing-tool folder
* Then build project with gradle
```
gradle build
```
* After successful build go to builb/classes/java/main folder and type following command
```
java com.thinking.machines.server.ui2
```
Now you will see a UI for entering a port number, let enter 5000 in this, and click on startServer button.
* Now take another PC and do steps till building project.
* Then open command prompt in second PC and go till main folder and then type this command
```
java com.thnking.machines.client.ui1
```
Now you will see a UI for entering server IP and port number, Now three cases can be there
1) You are using a LAN cable for connecting both client and server, so that client and server be on same network. In this case open another command in server machine and type this command
```
ipconfig
```
Now you will see IPV4 adress, write it in place of IP Adress of client UI and write port number of what you have given while starting server.  
Now you will see server machine window, in client machine, now you can control it.
2) You are not using LAN cable, in this case you can either use router or switch, in that you have to see about port forwarding. Then you can use this system.
3) You are using mobile hotspot, and both server machine and client machine are connected with same hotspot. Now you have to do same procedure as done in case of LAN cable.
