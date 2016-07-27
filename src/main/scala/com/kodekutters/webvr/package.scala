package com.kodekutters

import com.kodekutters.webvr.VREye.VREye

import language.implicitConversions
import org.scalajs.dom.raw.HTMLCanvasElement

import scala.scalajs.js.|

/**
  * WebVR API, Editor’s Draft, 1 June 2016.
  *
  * [[https://w3c.github.io/webvr/]]
  */
package object webvr {

  /** convert VREye type to its string representation, "left" or "right" */
  implicit def VREyeTypeToString(t: VREye): String = t.toString

  type VRSource = HTMLCanvasElement | OffscreenCanvas
}

