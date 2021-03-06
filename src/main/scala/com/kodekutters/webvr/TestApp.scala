//package com.kodekutters.webvr
//
//import com.kodekutters.{NavigatorWebVR, webvr}
//import cesium._
//import cesiumOptions._
//import org.scalajs.dom
//import org.scalajs.dom.Navigator
//
//import scala.scalajs.js.{Date, JSApp}
//import scala.scalajs.js
//import scala.language.implicitConversions
//import org.scalajs.dom.window
//
///**
//  * a basic example using WebvrScala.
//  *
//  * http://localhost:8080/Apps/webvrtest.html
//  *
//  */
//object TestApp extends JSApp {
//
//  implicit def navigatorExt(navigator: Navigator): NavigatorWebVR = navigator.asInstanceOf[NavigatorWebVR]
//
//  def main(): Unit = {
//
//    var vrDisplay = null
//
//    Console.println("---> hello from WebvrApp q")
//
//    val disp = window.navigator.getVRDisplays()
//
//    // launch the Cesium viewer
//    val viewer = new Viewer("cesiumContainer", ViewerOptions.
//      scene3DOnly(true).
//      animation(true).
//      timeline(false).
//      infoBox(false).
//      vrButton(true))
//
//    viewer.vrButton.viewModel.command
//
//    Console.println("isVREnabled " + viewer.vrButton.viewModel.isVREnabled)
//    Console.println("isVRMode " + viewer.vrButton.viewModel.isVRMode)
//
//    viewer.scene.globe.enableLighting = true
//
//    viewer.terrainProvider = new CesiumTerrainProvider(
//      CesiumTerrainProviderOptions.
//        url("//assets.agi.com/stk-terrain/world").
//        requestVertexNormals(true))
//
//    viewer.scene.globe.depthTestAgainstTerrain = true
//
//    // Follow the path of a plane. See the interpolation Sandcastle example.
//    val start = JulianDate.fromDate(new Date(2015, 2, 25, 16))
//    val stop = JulianDate.addSeconds(start, 360, new JulianDate())
//
//    viewer.clock.startTime = start.clone()
//    viewer.clock.stopTime = stop.clone()
//    viewer.clock.currentTime = start.clone()
//    viewer.clock.clockRange = ClockRange.LOOP_STOP
//    viewer.clock.multiplier = 1.0
//
//    def computeCirclularFlight(lon: Double, lat: Double, radius: Double) = {
//      val property = new SampledPositionProperty()
//      val startAngle = Math.random() * 360.0
//      val endAngle = startAngle + 360.0
//      var increment = (Math.random() * 2.0 - 1.0) * 10.0 + 45.0
//
//      for (i <- startAngle until endAngle by increment) {
//        val radians = Math.toRadians(i)
//        val timeIncrement = i - startAngle
//        val time = JulianDate.addSeconds(start, timeIncrement, new JulianDate())
//        val position = Cartesian3.fromDegrees(lon + (radius * 1.5 * Math.cos(radians)), lat + (radius * Math.sin(radians)), Math.random() * 500 + 1750)
//        property.addSample(time, position)
//      }
//      property
//    }
//
//    val longitude = -112.110693
//    val latitude = 36.0994841
//    val radius = 0.03
//    val radians = 0.03
//
//    val modelURI = "SampleData/models/CesiumBalloon/CesiumBalloon.glb"
//
//    // the ballon we are in
//    var entity = viewer.entities.add(new Entity(EntityOptions.
//      position(Cartesian3.fromDegrees(longitude, latitude, radius * 500 + 1750)).
//      model(new ModelGraphics(ModelGraphicsOptions.
//        uri(modelURI).
//        minimumPixelSize(64)))
//    ))
//
//    // Set initial camera position and orientation to be when in the model's reference frame.
//    var camera = viewer.camera
//    camera.position = new Cartesian3(0.25, 0.0, 0.0)
//    camera.direction = new Cartesian3(1.0, 0.0, 0.0)
//    camera.up = new Cartesian3(0.0, 0.0, 1.0)
//    camera.right = new Cartesian3(0.0, -1.0, 0.0)
//
//    val callback = (scene: Scene, time: JulianDate) => {
//      val position = entity.position.getValue(time)
//      if (!Cesium.defined(position)) return
//
//      var transform = Matrix4.IDENTITY
//      if (!Cesium.defined(entity.orientation)) {
//        transform = Transforms.eastNorthUpToFixedFrame(position)
//      } else {
//        val orientation = entity.orientation.getValue(time).asInstanceOf[Quaternion]
//        if (!Cesium.defined(orientation)) return
//        transform = Matrix4.fromRotationTranslation(Matrix3.fromQuaternion(orientation), position)
//      }
//
//      // Save camera state
//      val offset = Cartesian3.clone(camera.position)
//      val direction = Cartesian3.clone(camera.direction)
//      val up = Cartesian3.clone(camera.up)
//
//      // Set camera to be in model's reference frame.
//      camera.lookAtTransform(transform)
//
//      // Reset the camera state to the saved state so it appears fixed in the model's frame.
//      Cartesian3.clone(offset, camera.position)
//      Cartesian3.clone(direction, camera.direction)
//      Cartesian3.clone(up, camera.up)
//      Cartesian3.cross(direction, up, camera.right)
//
//      () => Unit
//    }
//
//    viewer.scene.preRender.addEventListener(callback)
//
//    // Add a few more balloons flying with the one the viewer is in.
//    val numBalloons = 12
//    for (i <- 0 until numBalloons by 1) {
//      var balloonRadius = (Math.random() * 2.0 - 1.0) * 0.01 + radius
//
//      var balloon = viewer.entities.add(new Entity(EntityOptions.
//        availability(new TimeIntervalCollection(js.Array(new TimeInterval(TimeIntervalOptions.
//          start(start).
//          stop(stop))))).
//        position(computeCirclularFlight(longitude, latitude, balloonRadius)).
//        model(new ModelGraphics(ModelGraphicsOptions.
//          uri(modelURI).
//          minimumPixelSize(64)))
//      ))
//
//      balloon.position.asInstanceOf[SampledPositionProperty].setInterpolationOptions(
//        InterpolationOptions.
//          interpolationDegree(2).
//          interpolationAlgorithm(HermitePolynomialApproximation))
//
//    }
//
//  }
//}