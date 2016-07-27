# Scala.js interface to the WebVR API

The [WebVR](https://w3c.github.io/webvr/) API provides purpose-built interfaces to VR hardware 
to allow developers to build compelling, comfortable VR experiences.

This library, **WebvrScala**, is a Scala.js façade to the WebVR API that is built into 
the WebVR capable browsers.

## Usage


## Installation and packaging

To compile and generate a javascript file from the source code:

    sbt fullOptJS 

The javascript file (webvrscala-opt.js) will be in the "./target/scala-2.11" directory.


To publish **WebvrScala** to your local (Ivy) repository, simply type:

    sbt publishLocal
    
Then put this in your build.sbt file

    libraryDependencies += "com.github.workingDog" %%% "webvrscala" % "0.1-SNAPSHOT"

## Status

work in progress



