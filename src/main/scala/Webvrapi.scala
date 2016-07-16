/*
 * Façade to the WebVRApi API, Editor’s Draft, 1 June 2016.
 *
 * [[https://w3c.github.io/webvr/]]
 *
 */

import VREyeType.VREyeType
import WebVRApi.{VREye, VRLayer, VRSource}

import org.scalajs.dom._

import scala.scalajs.js
import js.|
import scala.concurrent._
import scala.scalajs.js.annotation._
import scala.scalajs.js.{Promise => _, _}
import org.scalajs.dom.raw.HTMLCanvasElement

import scala.scalajs.js.typedarray.Float32Array
import js.JSConverters._


/**
  * the WebVRApi
  */
@js.native
object WebVRApi extends js.Object {
  type VREye = String   // "left" | "right"
  type VRSource = HTMLCanvasElement  // or OffscreenCanvas  todo
  type VRLayer = js.Any // todo see ref: 2.2
}

/**
  * The VRLayer interface is provided to a VRDisplay and presented in the HMD.
  */
@js.native
trait VRLayer extends js.Object {
  var source: VRSource = js.native
  var leftBounds: js.Array[Float] = js.native
  var rightBounds: js.Array[Float] = js.native
}

/**
  * The VRDisplay interface forms the base of all VR devices supported by this API.
  * It includes generic information such as device IDs and descriptions.
  */
@js.native
class VRDisplay extends EventTarget {
  var isConnected: Boolean = js.native
  var isPresenting: Boolean = js.native
  /** Dictionary of capabilities describing the VRDisplay. */
  var capabilities: VRDisplayCapabilities = js.native
  /**
    * If this VRDisplay supports room-scale experiences, the optional
    * stage attribute contains details on the room-scale parameters.
    */
  var stageParameters: VRStageParameters = js.native

  /* Return the current VREyeParameters for the given eye. */
  def getEyeParameters(whichEye: String): VREyeParameters = js.native

  /**
    * An identifier for this distinct VRDisplay. Used as an
    * association point in the Gamepad API.
    */
  var displayId: Double = js.native
  /** A display name, a user-readable name identifying it. */
  var displayName: String = js.native

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
@js.native
trait VRDisplayCapabilities extends js.Object {
  var hasPosition: Boolean = js.native
  var hasOrientation: Boolean = js.native
  var hasExternalDisplay: Boolean = js.native
  var canPresent: Boolean = js.native
  var maxLayers: Double = js.native
}

/**
  * The VRFieldOfView interface represents a field of view, as given by 4 degrees describing the view from a center point.
  */
@js.native
trait VRFieldOfView extends js.Object {
  var upDegrees: Double = js.native
  var rightDegrees: Double = js.native
  var downDegrees: Double = js.native
  var leftDegrees: Double = js.native
}

/**
  * The VRPose interface represents a sensor’s state at a given timestamp.
  */
@js.native
trait VRPose extends js.Object {
  var timestamp: Double = js.native
  var position: Float32Array = js.native
  var linearVelocity: Float32Array = js.native
  var linearAcceleration: Float32Array = js.native
  var orientation: Float32Array = js.native
  var angularVelocity: Float32Array = js.native
  var angularAcceleration: Float32Array = js.native
}

/**
  * The VREyeParameters interface represents all the information required to correctly render a scene for a given eye.
  */
@js.native
trait VREyeParameters extends js.Object {
  var offset: Float32Array = js.native
  var fieldOfView: VRFieldOfView = js.native
  var renderWidth: Double = js.native
  var renderHeight: Double = js.native
}

/**
  * The VRStageParameters interface represents the values describing the the stage/play area for devices that support room-scale experiences.
  */
@js.native
trait VRStageParameters extends js.Object {
  var sittingToStandingTransform: Float32Array = js.native
  var sizeX: Double = js.native
  var sizeZ: Double = js.native
}

/**
  * Navigator Interface extension
  */
@js.native
trait Navigator extends js.Object {
  def getVRDisplays(): Promise[js.Array[VRDisplay]] = js.native

  var activeVRDisplays: js.Array[VRDisplay] = js.native
}

/**
  * Window Interface extension
  */
@js.native
trait Window extends js.Object {
  var onvrdisplayconnected: js.Function1[Event, Any] = js.native
  var onvrdisplaydisconnected: js.Function1[Event, Any] = js.native
  var onvrdisplaypresentchange: js.Function1[Event, Any] = js.native
}

/**
  * Gamepad Interface extension
  */
@js.native
trait Gamepad extends js.Object {
  var displayId: Double = js.native
}

//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

/**
  * represent the VREyeType as an enumeration
  */
object VREyeType extends Enumeration {
  type VREyeType = Value
  val Left = Value("left")
  val Right = Value("right")
}

/**
  * a set of implicit conversions
  */
object WebVRApiImplicits {

  /** convert VREyeType to its string representation, "left" or "right" */
  implicit def VREyeTypeToString(t: VREyeType): String = t.toString
}
