package com.kodekutters

import com.kodekutters.webvr.VRDisplayEventReason.VRDisplayEventReason
import com.kodekutters.webvr.VREye.VREye
import org.scalajs.dom._

import language.implicitConversions
import org.scalajs.dom.raw.HTMLCanvasElement

import scala.scalajs.js
import scala.scalajs.js.{Any, |}

/**
  * WebVR API, Editor’s Draft, 27 July 2016.
  *
  * [[https://w3c.github.io/webvr/]]
  */
package object webvr {

  /** converts VREye type to its string representation, "left" or "right" */
  implicit def VREyeToString(t: VREye): String = t.toString

  /** converts VRDisplayEventReason type to its string representation */
  implicit def VRDisplayEventReasonToString(t: VRDisplayEventReason): String = t.toString

  type VRSource = HTMLCanvasElement | OffscreenCanvas

  type EventHandler = js.Function1[Event, _]

}

