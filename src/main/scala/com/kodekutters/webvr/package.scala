package com.kodekutters

import org.scalajs.dom.html.IFrame
import org.scalajs.dom.{Event, Navigator, Window}

import language.implicitConversions
import org.scalajs.dom.raw.WebGLRenderingContext
import io.scalajs.dom.html.canvas.webgl.WebGL2RenderingContext

import scala.scalajs.js
import scala.scalajs.js.|

/**
  * WebVR API, Editor’s Draft, Editor’s Draft, 8 August 2017
  *
  * [[https://w3c.github.io/webvr/]]
  */
package object webvr {

  type VRWebGLRenderingContext = WebGLRenderingContext | WebGL2RenderingContext

  type EventHandler = js.Function1[Event, _]

  type VRFrameRequestCallback = js.Function1[VRPresentationFrame, _]

  implicit def navigatorExt(navigator: Navigator): NavigatorWebVR = navigator.asInstanceOf[NavigatorWebVR]

  implicit def windowExt(window: Window): WindowWebVR = window.asInstanceOf[WindowWebVR]

  implicit def gamepadExt(gamepad: Gamepad): GamepadWebVR = gamepad.asInstanceOf[GamepadWebVR]

  implicit def iframeExt(iframe: IFrame): IFrameWebVR = iframe.asInstanceOf[IFrameWebVR]

}
