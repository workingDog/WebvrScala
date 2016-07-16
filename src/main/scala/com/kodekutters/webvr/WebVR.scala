package com.kodekutters.webvr

/*
 * Façade to the WebVR API, Editor’s Draft, 1 June 2016.
 *
 * [[https://w3c.github.io/webvr/]]
 *
 */

import org.scalajs.dom._

import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.scalajs.js.{Promise => _, _}
import scala.scalajs.js.typedarray.Float32Array

import scala.concurrent._
import scala.language.implicitConversions



/**
  * The VRLayer interface is provided to a VRDisplay and presented in the HMD.
  */
@ScalaJSDefined
trait VRLayer extends js.Object {
  var source: VRSource
  var leftBounds: js.Array[Float]
  var rightBounds: js.Array[Float]
}

object VRLayer {
  def apply(source: VRSource, leftBounds: js.Array[Float], rightBounds: js.Array[Float]): VRLayer = {
    js.Dynamic
      .literal(source = source, leftBounds = leftBounds, rightBounds = rightBounds)
      .asInstanceOf[VRLayer]
  }
}

/**
  * The VRDisplay interface forms the base of all VR devices supported by this API.
  * It includes generic information such as device IDs and descriptions.
  */
@js.native
class VRDisplay extends EventTarget {
  def isConnected: Boolean = js.native
  def isPresenting: Boolean = js.native
  /** Dictionary of capabilities describing the VRDisplay. */
  val capabilities: VRDisplayCapabilities = js.native
  /**
    * If this VRDisplay supports room-scale experiences, the optional
    * stage attribute contains details on the room-scale parameters.
    */
  val stageParameters: VRStageParameters = js.native

  /* Return the current VREyeParameters for the given eye. */
  def getEyeParameters(whichEye: String): VREyeParameters = js.native

  /**
    * An identifier for this distinct VRDisplay. Used as an
    * association point in the Gamepad API.
    */
  val displayId: Double = js.native
  /** A display name, a user-readable name identifying it. */
  val displayName: String = js.native

  /**
    * Return a VRPose containing the future predicted pose of the VRDisplay
    * when the current frame will be presented. The value returned will not
    * change until JavaScript has returned control to the browser.
    *
    * The VRPose will contain the position, orientation, velocity,
    * and acceleration of each of these properties.
    */
  def getPose(): VRPose = js.native

  /**
    * Return the current instantaneous pose of the VRDisplay, with no
    * prediction applied.
    */
  def getImmediatePose(): VRPose = js.native

  /**
    * Reset the pose for this display, treating its current position and
    * orientation as the "origin/zero" values. VRPose.position,
    * VRPose.orientation, and VRStageParameters.sittingToStandingTransform may be
    * updated when calling resetPose(). This should be called in only
    * sitting-space experiences.
    */
  def resetPose(): Unit = js.native

  /**
    * z-depth defining the near plane of the eye view frustum
    * enables mapping of values in the render target depth
    * attachment to scene coordinates. Initially set to 0.01.
    */
  var depthNear: Double = js.native
  /**
    * z-depth defining the far plane of the eye view frustum
    * enables mapping of values in the render target depth
    * attachment to scene coordinates. Initially set to 10000.0.
    */
  var depthFar: Double = js.native

  /**
    * The callback passed to `requestAnimationFrame` will be called
    * any time a new frame should be rendered. When the VRDisplay is
    * presenting the callback will be called at the native refresh
    * rate of the HMD. When not presenting this function acts
    * identically to how window.requestAnimationFrame acts. Content should
    * make no assumptions of frame rate or vsync behavior as the HMD runs
    * asynchronously from other displays and at differing refresh rates.
    */
  def requestAnimationFrame(callback: js.Function1[Number, Any]): Double = js.native

  /**
    * Passing the value returned by requestAnimationFrame to
    * cancelAnimationFrame will unregister the callback.
    */
  def cancelAnimationFrame(handle: Double): Unit = js.native

  /**
    * Begin presenting to the VRDisplay. Must be called in response to a user gesture.
    * Repeat calls while already presenting will update the VRLayers being displayed.
    */
  def requestPresent(layers: js.Array[VRLayer]): Promise[Unit] = js.native

  /** Stops presenting to the VRDisplay. */
  def exitPresent(): Promise[Unit] = js.native

  /** Get the layers currently being presented. */
  def getLayers(): js.Array[VRLayer] = js.native

  /**
    * The VRLayer provided to the VRDisplay will be captured and presented
    * in the HMD. Calling this function has the same effect on the source
    * canvas as any other operation that uses its source image, and canvases
    * created without preserveDrawingBuffer set to true will be cleared.
    */
  def submitFrame(pose: VRPose = ???): Unit = js.native
}

/**
  * The VRDisplayCapabilities interface describes the capabilities of a VRDisplay.
  * These are expected to be static per-device/per-user.
  */
@ScalaJSDefined
trait VRDisplayCapabilities extends js.Object {
  val hasPosition: Boolean
  val hasOrientation: Boolean
  val hasExternalDisplay: Boolean
  val canPresent: Boolean
  val maxLayers: Double
}

/**
  * The VRFieldOfView interface represents a field of view, as given by 4 degrees describing the view from a center point.
  */
@ScalaJSDefined
trait VRFieldOfView extends js.Object {
  val upDegrees: Double
  val rightDegrees: Double
  val downDegrees: Double
  val leftDegrees: Double
}

/**
  * The VRPose interface represents a sensor’s state at a given timestamp.
  */
@ScalaJSDefined
trait VRPose extends js.Object {
  val timestamp: Double
  val position: Float32Array
  val linearVelocity: Float32Array
  val linearAcceleration: Float32Array
  val orientation: Float32Array
  val angularVelocity: Float32Array
  val angularAcceleration: Float32Array
}

/**
  * The VREyeParameters interface represents all the information required to correctly render a scene for a given eye.
  */
@ScalaJSDefined
trait VREyeParameters extends js.Object {
  val offset: Float32Array
  val fieldOfView: VRFieldOfView
  val renderWidth: Double
  val renderHeight: Double
}

/**
  * The VRStageParameters interface represents the values describing the the stage/play area for devices that support room-scale experiences.
  */
@ScalaJSDefined
trait VRStageParameters extends js.Object {
  val sittingToStandingTransform: Float32Array
  val sizeX: Double
  val sizeZ: Double
}

/**
  * Navigator Interface extension
  */
@ScalaJSDefined
trait Navigator extends js.Object {
  def getVRDisplays(): Promise[js.Array[VRDisplay]]
  val activeVRDisplays: js.Array[VRDisplay]
}

/**
  * Window Interface extension
  */
@ScalaJSDefined
trait Window extends js.Object {
  var onvrdisplayconnected: js.Function1[Event, Any]
  var onvrdisplaydisconnected: js.Function1[Event, Any]
  var onvrdisplaypresentchange: js.Function1[Event, Any]
}

/**
  * Gamepad Interface extension
  */
@ScalaJSDefined
trait Gamepad extends js.Object {
  val displayId: Double
}

/**
  * represent the VREyeType as an enumeration
  */
object VREyeType extends Enumeration {
  type VREyeType = Value
  val Left = Value("left")
  val Right = Value("right")
}
