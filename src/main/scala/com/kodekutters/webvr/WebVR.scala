package com.kodekutters

/*
 * Façade to the WebVR API, Editor’s Draft, 17 April 2017
 *
 * [[https://w3c.github.io/webvr/spec/latest/]]
 *
 * [[https://w3c.github.io/webvr/]]
 *
 */

import com.kodekutters.webvr.{EventHandler, Gamepad, VRCanvasSource}
import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.html.IFrame

import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.scalajs.js.Promise
import scala.scalajs.js.typedarray.Float32Array
import scala.language.implicitConversions


/**
  * A VRLayer defines a source of bitmap images and a description of how the image is
  * to be rendered in the VRDevice.
  */
@js.native
trait VRLayer extends js.Object

/**
  * A VRCanvasLayer defines a source of bitmap images and a description of how the image is to be rendered
  * in the VRDevice.
  */
@js.native
trait VRCanvasLayer extends VRLayer {
  var source: Option[VRCanvasSource] = js.native

  def setLeftBounds(left: Double, bottom: Double, right: Double, top: Double): Unit = js.native

  def getLeftBounds(): Array[Double] = js.native

  def setRightBounds(left: Double, bottom: Double, right: Double, top: Double): Unit = js.native

  def getRightBounds(): Array[Double] = js.native

  def commit(): Promise[Double] = js.native  // todo ---> should be DOMHighResTimeStamp
}

object VRCanvasLayer {
  def apply(session: VRSession, source: Option[VRCanvasSource]): VRLayer = {
    js.Dynamic
      .literal(session = session, source = source.asInstanceOf[js.Any]) // todo --->
      .asInstanceOf[VRLayer]
  }
}

@js.native
trait VRDeviceEvent extends Event {
  val device: VRDevice
}

object VRDeviceEvent {
  def apply(eventType: String, eventInitDict: VRDeviceEventInit): VRDeviceEvent = {
    js.Dynamic
      .literal(`type` = eventType, eventInitDict = eventInitDict)
      .asInstanceOf[VRDeviceEvent]
  }
}

// todo ---> should be in DOM
@js.native
trait EventInit extends js.Object {
  val bubbles: Boolean = js.native
  val cancelable: Boolean = js.native
  val composed: Boolean = js.native
}

@js.native
trait VRDeviceEventInit extends EventInit {
  val device: VRDevice = js.native
}

@js.native
trait VRSessionEventInit extends EventInit {
  val session: VRSessionEvent
}

@js.native
trait VRSessionEvent extends Event {
  val session: VRSessionEvent
}

object VRSessionEvent {
  def apply(eventType: String, eventInitDict: VRSessionEventInit): VRSessionEvent = {
    js.Dynamic
      .literal(`type` = eventType, eventInitDict = eventInitDict)
      .asInstanceOf[VRSessionEvent]
  }
}

/**
  * The VRSourceProperties interface describes the ideal dimensions of a layer image for a VRDevice,
  * taking into account the native resolution of the device and the distortion required to
  * counteract an distortion introduced by the device’s optics.
  */
@js.native
trait VRSourceProperties extends js.Object {
  val scale: Double = js.native
  val width: Long = js.native
  val height: Long = js.native
}

@js.native
trait VRCoordinateSystem extends js.Object {
  def getTransformTo(other: VRCoordinateSystem): Float32Array = js.native
}

/**
  * The VRStageBounds interface describes a space known as a "Stage".
  */
@js.native
trait VRStageBounds extends js.Object {
  val minX: Double = js.native
  val maxX: Double = js.native
  val minZ: Double = js.native
  val maxZ: Double = js.native
}

@js.native
trait VRFrameOfReference extends VRCoordinateSystem {
  val bounds: VRStageBounds
}

sealed class VRFrameOfReferenceType(val frameType: String)

object VRFrameOfReferenceType {

  case object HeadModel extends VRFrameOfReferenceType("headModel")

  case object EyeLevel extends VRFrameOfReferenceType("eyeLevel")

  case object Stage extends VRFrameOfReferenceType("stage")

}

/**
  * A VRDevicePose describes the position and orientation of a VRDevice relative to
  * the VRCoordinateSystem it was queried with.
  */
@js.native
trait VRDevicePose extends js.Object {
  val leftProjectionMatrix: Float32Array

  val leftViewMatrix: Float32Array

  val rightProjectionMatrix: Float32Array

  val rightViewMatrix: Float32Array

  val poseModelMatrix: Float32Array
}

/**
  * The VRSessionCreateParametersInit dictionary provides a session description,
  * indicating the desired capabilities of a session to be returned from requestSession().
  */
@js.native
trait VRSessionCreateParametersInit extends js.Object {
  def exclusive: Boolean = js.native
}


@js.native
trait VRSessionCreateParameters extends js.Object {
  val exclusive: Boolean = js.native
}

/**
  * A VRSession is the interface through with most interaction with a VRDevice happens.
  */
@js.native
trait VRSession extends EventTarget {

  val device: VRDevice = js.native
  val createParameters: VRSessionCreateParameters = js.native

  var depthNear: Double = js.native
  var depthFar: Double = js.native
  var baseLayer: VRLayer = js.native

  def getSourceProperties(scale: Option[Double]): VRSourceProperties = js.native

  def createFrameOfReference(frameType: VRFrameOfReferenceType): Promise[VRFrameOfReference] = js.native

  def getDevicePose(coordinateSystem: VRCoordinateSystem): VRDevicePose = js.native

  def endSession(): Promise[Unit]

  var onblur: EventHandler = js.native
  var onfocus: EventHandler = js.native
  var onresetpose: EventHandler = js.native
}

/**
  * A VRDevice represents a physical unit of VR hardware
 */
@js.native
trait VRDevice extends EventTarget {

  def deviceName: String = js.native

  def isExternal: Boolean = js.native

  var activeSession: VRSession = js.native

  var onactivate: EventHandler = js.native
  var ondeactivate: EventHandler = js.native
  var onsessionchange: EventHandler = js.native

  def supportsSession(parameters: VRSessionCreateParametersInit): Boolean = js.native

  def requestSession(parameters: VRSessionCreateParametersInit): Promise[VRSession] = js.native

}

/**
  * VR Interface
  */
@js.native
trait VR extends EventTarget {

  /** Return a Promise which resolves to a list of available VRDevices. */
  def getDevices(): Promise[Array[VRDevice]] = js.native

  var ondeviceconnect: EventHandler = js.native
  var ondevicedisconnect: EventHandler = js.native
  var onnavigate: EventHandler = js.native
}

/**
  * Navigator Interface extension
  */
@js.native
trait NavigatorWebVR extends Navigator {
  def vr: VR = js.native
}

/**
  * Window Interface extension
  */
@js.native
trait WindowWebVR extends Window {
  /** A user agent MAY dispatch this event type to indicate that a VRDisplay has been connected. */
  var onvrdisplayconnected: EventHandler = js.native
  /** A user agent MAY dispatch this event type to indicate that a VRDisplay has been disconnected. */
  var onvrdisplaydisconnected: EventHandler = js.native
  /** A user agent MAY dispatch this event type to indicate that something has occured which suggests the VRDisplay should be presented to. For example, if the VRDisplay is capable of detecting when the user has put it on, this event SHOULD fire when they do so with the reason "mounted". */
  var onvrdisplayactivate: EventHandler = js.native
  /** A user agent MAY dispatch this event type to indicate that something has occured which suggests the VRDisplay should exit presentation. For example, if the VRDisplay is capable of detecting when the user has taken it off, this event SHOULD fire when they do so with the reason "unmounted". */
  var onvrdisplaydeactivate: EventHandler = js.native
  /** A user agent MAY dispatch this event type to indicate that presentation to the display by the page is paused by the user agent, OS, or VR hardware. While a VRDisplay is blurred it does not lose it’s presenting status (isPresenting continues to report true) but getFrameData() returns false without updating the provided VRFrameData and getPose() returns null. This is to prevent tracking while the user interacts with potentially sensitive UI. For example: A user agent SHOULD blur the presenting application when the user is typing a URL into the browser with a virtual keyboard, otherwise the presenting page may be able to guess the URL the user is entering by tracking their head motions. */
  var onvrdisplayblur: EventHandler = js.native
  /** A user agent MAY dispatch this event type to indicate that presentation to the display by the page has resumed after being blurred. */
  var onvrdisplayfocus: EventHandler = js.native
  /** A user agent MUST dispatch this event type to indicate that a VRDisplay has begun or ended VR presentation. This event should not fire on subsequent calls to requestPresent() after the VRDisplay has already begun VR presentation. */
  var onvrdisplaypresentchange: EventHandler = js.native
}

/**
  * Gamepad Interface extension
  */
@js.native
trait GamepadWebVR extends Gamepad {
  def displayId: Long = js.native
}

@js.native
trait IFrameWebVR extends IFrame {
  /** The allowvr attribute is a boolean attribute. When specified, it indicates that Document objects
    * in the iframe element’s browsing context are to be allowed to access VR devices
    * (if it’s not blocked for other reasons, e.g. there is another ancestor iframe
    * without this attribute set). Document objects in an iframe element without
    * this attribute should reject calls to getVRDisplays() and should not fire any VRDisplayEvent. */
  var allowvr: Boolean
}