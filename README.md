# Scala.js interface to the WebVR API

[WebVR](https://webvr.info/) is an experimental JavaScript API that provides access to Virtual Reality devices, 
such as the Oculus Rift, HTC Vive, Samsung Gear VR, or Google Cardboard, 
in your browser.

The [WebVR API](https://w3c.github.io/webvr/) provides purpose-built interfaces to VR hardware 
to allow developers to build compelling, comfortable VR experiences.

This scala façade follows the specification of the [WebVR API, Editor’s Draft, 3 November 2016](https://w3c.github.io/webvr/).

See also [MDN WebVR API Docs](https://developer.mozilla.org/en-US/docs/Web/API/WebVR_API) for additional information.
    
## Inclusions
    
The following supporting scala.js façades are included, **DeviceOrientation**, **GamePad** and **OffscreenCanvas**.    
   
   
## References

1)  [WebVR API](https://w3c.github.io/webvr/)

2)  [GamePad API](https://w3c.github.io/gamepad/)

3)  [DeviceOrientation API](https://developer.mozilla.org/en-US/docs/Web/Events/deviceorientation)

4)  [OffscreenCanvas API](https://developer.mozilla.org/en-US/docs/Web/API/OffscreenCanvas)
   
## Dependencies
   
To use this scala.js **WebVR** interface you need to have [scala-js-dom](https://github.com/scala-js/scala-js-dom)
   
## Usage   
   
To publish this library locally, type:
   
     sbt publishLocally
   
This will put "com.github.workingDog" %%% "webvrscala" % "0.1-SNAPSHOT" into your local repository.

Then in your app build.sbt file, simply include:
    
     libraryDependencies ++= Seq(
     "org.scala-js" %%% "scalajs-dom" % "0.9.1",
     "com.github.workingDog" %%% "webvrscala" % "0.1-SNAPSHOT")
   
Note the browser **Window** extension for **WebVR** is called **WindowWebVR**, 
similarly the **Navigator** extention is called **NavigatorWebVR**.
For example you may have:

    import org.scalajs.dom.Navigator
    import org.scalajs.dom.window
    
    val displays = window.navigator.asInstanceOf[NavigatorWebVR].getVRDisplays()
 
Or declare an implicit to do this.
 
     implicit def navigatorExt(navigator: Navigator): NavigatorWebVR = navigator.asInstanceOf[NavigatorWebVR]
     
     val displays = window.navigator.getVRDisplays()
   
## Status

As with **WebVR** this is experimental