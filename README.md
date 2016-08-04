# Scala.js interface to the WebVR API

The [WebVR](https://w3c.github.io/webvr/) API provides purpose-built interfaces to VR hardware 
to allow developers to build compelling, comfortable VR experiences.

Note: this is just a repository of some code. The Scala.js WebVR API code is at 
 [my fork](https://github.com/workingDog/scala-js-dom) of scala-js-dom
 in the "workingdog" branch.

You can publish it locally using;

    sbt publishLocal
    
Then put this in your build.sbt file:
    
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.2-SNAPSHOT"
    
   
