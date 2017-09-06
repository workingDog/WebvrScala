package com.kodekutters

/*
 * Façade to the WebVR API, Editor’s Draft, 6 September 2017
 *
 * [[https://w3c.github.io/webvr/spec/latest/]]
 *
 * [[https://w3c.github.io/webvr/]]
 *
 */

import com.kodekutters.webvr.{EventHandler, Gamepad, VRFrameRequestCallback, VRWebGLRenderingContext}
import org.scalajs.dom._
import org.scalajs.dom.html.IFrame
import org.scalajs.dom.raw.WebGLFramebuffer

import scala.scalajs.js
import scala.scalajs.js.Promise
import scala.scalajs.js.typedarray.Float32Array

/**
  * A VRLayer defines a source of bitmap images and a description of how the image is
  * to be rendered in the VRDevice.
  */
@js.native
trait VRLayer extends js.Object

@js.native
trait WebGLContextAttributes extends js.Object {
  def compatibleVRDevice: VRDevice = js.native
}

@js.native
trait WebGLRenderingContextBase extends js.Object {
  def setCompatibleVRDevice(device: VRDevice): Promise[Unit] = js.native
}

/**
  * The VRWebGLLayerInit dictionary indicates the desired properites of a VRWebGLLayer's framebuffer.
  */
@js.native
trait VRWebGLLayerInit extends js.Object {
  val antialias: Boolean = js.native
  val depth: Boolean = js.native
  val stencil: Boolean = js.native
  val alpha: Boolean = js.native
  val multiview: Boolean = js.native
  val framebufferScaleFactor: Double = js.native
}

@js.native
trait VRWebGLLayer extends VRLayer {

  var context: VRWebGLRenderingContext = js.native

  def requestViewportScaling(viewportScaleFactor: Double): Unit = js.native

  val antialias: Boolean = js.native
  val depth: Boolean = js.native
  val stencil: Boolean = js.native
  val alpha: Boolean = js.native
  val multiview: Boolean = js.native

  val framebuffer: WebGLFramebuffer = js.native
  val framebufferWidth: Long = js.native
  val framebufferHeight: Long = js.native

}

object VRWebGLLayer {
  def apply(session: VRSession, context: VRWebGLRenderingContext, layerInit: Option[VRWebGLLayerInit]): VRWebGLLayer = {
    js.Dynamic
      .literal(session = session, context = context.asInstanceOf[js.Any], layerInit = layerInit.asInstanceOf[js.Any])
      .asInstanceOf[VRWebGLLayer]
  }
}

@js.native
trait VRDeviceEventInit extends EventInit {
  val device: VRDevice = js.native
}

@js.native
trait VRDeviceEvent extends Event {
  val device: VRDevice = js.native
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
trait VRSessionEventInit extends EventInit {
  val session: VRSession = js.native
}

@js.native
trait VRSessionEvent extends Event {
  val session: VRSession = js.native
}

object VRSessionEvent {
  def apply(eventType: String, eventInitDict: VRSessionEventInit): VRSessionEvent = {
    js.Dynamic
      .literal(`type` = eventType, eventInitDict = eventInitDict)
      .asInstanceOf[VRSessionEvent]
  }
}

@js.native
trait VRCoordinateSystemEventInit extends EventInit {
  val coordinateSystem: VRCoordinateSystem = js.native
}

@js.native
trait VRCoordinateSystemEvent extends Event {
  val coordinateSystem: VRCoordinateSystem = js.native
}

object VRCoordinateSystemEvent {
  def apply(eventType: String, eventInitDict: VRCoordinateSystemEventInit): VRCoordinateSystemEvent = {
    js.Dynamic
      .literal(`type` = eventType, eventInitDict = eventInitDict)
      .asInstanceOf[VRCoordinateSystemEvent]
  }
}

@js.native
trait VRCoordinateSystem extends EventTarget {
  def getTransformTo(other: VRCoordinateSystem): Float32Array = js.native
}

@js.native
trait VRStageBoundsPoint extends js.Object {
  val x: Double = js.native
  val z: Double = js.native
}

/**
  * The VRStageBounds interface describes a space known as a "Stage".
  */
@js.native
trait VRStageBounds extends js.Object {
  val geometry: Array[VRStageBoundsPoint] = js.native
}

@js.native
trait VRFrameOfReference extends VRCoordinateSystem {
  val bounds: VRStageBounds = js.native
  var onboundschange: EventHandler = js.native
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

  def getViewMatrix(view: VRView): Float32Array = js.native

  def poseModelMatrix: Float32Array = js.native
}

/**
  * The VRSessionCreateParametersInit dictionary provides a session description,
  * indicating the desired capabilities of a session to be returned from requestSession().
  */
@js.native
trait VRSessionCreateParametersInit extends js.Object {
  val exclusive: Boolean = js.native
}

/**
  * interface
  */
@js.native
trait VRSessionCreateParameters extends js.Object {
  def exclusive: Boolean = js.native
}

sealed class VREye(val eye: String)

object VREye {

  case object Left extends VREye("left")

  case object Right extends VREye("right")

}

@js.native
trait VRViewport extends js.Object {
  var x: Long = js.native
  var y: Long = js.native
  var width: Long = js.native
  var height: Long = js.native
}

@js.native
trait VRView extends js.Object {
  val eye: VREye = js.native
  val projectionMatrix: Float32Array = js.native

  def getViewPort(layer: VRLayer): VRViewport = js.native
}

@js.native
trait VRPresentationFrame extends js.Object {
  val views: Array[VRView] = js.native

  def getDevicePose(coordinateSystem: VRCoordinateSystem): VRDevicePose = js.native
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

  def requestFrameOfReference(frameType: VRFrameOfReferenceType): Promise[VRFrameOfReference] = js.native

  def requestFrame(callback: VRFrameRequestCallback): Long = js.native

  def cancelFrame(handle: Long): Unit = js.native

  def end(): Promise[Unit] = js.native

  /** an Event handler IDL attribute for the blur event type. */
  var onblur: EventHandler = js.native
  /** an Event handler IDL attribute for the focus event type. */
  var onfocus: EventHandler = js.native
  /** an Event handler IDL attribute for the resetpose event type. */
  var onresetpose: EventHandler = js.native
  /** an Event handler IDL attribute for the ended event type. */
  var onend: EventHandler = js.native
}

/**
  * A VRDevice represents a physical unit of VR hardware that can present imagery to the user
  */
@js.native
trait VRDevice extends EventTarget {
  /** a human readable string describing the VRDevice. */
  def deviceName: String = js.native

  /** true if the VRDevice hardware is a separate physical device from the system’s main device. */
  def isExternal: Boolean = js.native

  /** an Event handler IDL attribute for the deactivate event type. */
  var ondeactivate: EventHandler = js.native

  /** If the requested session description is supported by the device, resolve promise with true else false */
  def supportsSession(parameters: VRSessionCreateParametersInit): Promise[Boolean] = js.native

  /** to set or retrieve the active session a page must request a session from the device  */
  def requestSession(parameters: VRSessionCreateParametersInit): Promise[VRSession] = js.native

}

/**
  * VR Interface
  */
@js.native
trait VR extends EventTarget {
  /** Return a Promise which resolves to a list of available VRDevices. */
  def getDevices(): Promise[Array[VRDevice]] = js.native

  /** an Event handler IDL attribute for the deviceconnect event type. */
  var ondeviceconnect: EventHandler = js.native
  /** an Event handler IDL attribute for the devicedisconnect event type. */
  var ondevicedisconnect: EventHandler = js.native
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