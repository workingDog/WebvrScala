package com.kodekutters

import org.scalajs.dom.Event

import language.implicitConversions
import org.scalajs.dom.raw.HTMLCanvasElement

import scala.scalajs.js
import scala.scalajs.js.{Any, |}

/**
  * WebVR API, W3C Editor's Draft 2 December 2016
  *
  * [[https://w3c.github.io/webvr/]]
  */
package object webvr {

  type VRSource = HTMLCanvasElement | OffscreenCanvas

  type EventHandler = js.Function1[Event, _]

}
