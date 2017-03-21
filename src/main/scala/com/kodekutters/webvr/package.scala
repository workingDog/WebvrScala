package com.kodekutters

import org.scalajs.dom.html.IFrame
import org.scalajs.dom.{Event, Navigator, Window}

import language.implicitConversions
import org.scalajs.dom.raw.HTMLCanvasElement

import scala.scalajs.js
import scala.scalajs.js.{Any, |}

/**
  * WebVR API, Editor’s Draft, 16 March 2017
  *
  * [[https://w3c.github.io/webvr/]]
  */
package object webvr {

  type VRSource = HTMLCanvasElement | OffscreenCanvas

  type EventHandler = js.Function1[Event, _]

  implicit def navigatorExt(navigator: Navigator): NavigatorWebVR = navigator.asInstanceOf[NavigatorWebVR]

  implicit def windowExt(window: Window): WindowWebVR = window.asInstanceOf[WindowWebVR]

  implicit def gamepadExt(gamepad: Gamepad): GamepadWebVR = gamepad.asInstanceOf[GamepadWebVR]

  implicit def iframeExt(iframe: IFrame): IFrameWebVR = iframe.asInstanceOf[IFrameWebVR]
}
