# Scala.js interface to the WebVR API

[WebVR](https://webvr.info/) is an experimental JavaScript API that provides access to Virtual Reality devices, 
such as the Oculus Rift, HTC Vive, Samsung Gear VR, or Google Cardboard, 
in your browser.

The [WebVR API](https://w3c.github.io/webvr/) provides purpose-built interfaces to VR hardware 
to allow developers to build compelling, comfortable VR experiences.

This scala façade follows the specification of the [WebVR Editor’s Draft, Draft, 6 September 2017](https://w3c.github.io/webvr/spec/latest/).

This interface allows WebVR to be used in [Scala.js](https://www.scala-js.org/) the Scala to javascript compiler.

See also [MDN WebVR API Docs](https://developer.mozilla.org/en-US/docs/Web/API/WebVR_API) for additional information.
    
## Inclusions
    
The following supporting scala.js façades are included, **DeviceOrientation**, **GamePad** and **OffscreenCanvas**.    
   
   
## References

1)  [WebVR API](https://w3c.github.io/webvr/)

2)  [GamePad API](https://w3c.github.io/gamepad/)

3)  [DeviceOrientation API](https://developer.mozilla.org/en-US/docs/Web/Events/deviceorientation)

4)  [OffscreenCanvas API](https://developer.mozilla.org/en-US/docs/Web/API/OffscreenCanvas)
   
## Dependencies
      
Using Scala.js-0.6.19, scalajs-dom-0.9.3 and Scala-2.12.3
      
## Usage   
   
To publish this library locally, type:
   
     sbt publishLocally
   
This will put "com.github.workingDog" %%% "webvrscala" % "0.1-SNAPSHOT" into your local repository.

Then in your [Scala.js](https://www.scala-js.org/) app build.sbt file, simply include:
    
     libraryDependencies += "com.github.workingDog" %%% "webvrscala" % "0.1-SNAPSHOT"
    
## Status

As with **WebVR** this is experimental