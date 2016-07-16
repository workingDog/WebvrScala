package com.kodekutters

import com.kodekutters.webvr.VREyeType.VREyeType

import language.implicitConversions
import org.scalajs.dom.raw.HTMLCanvasElement

/**
  * WebVRApi API, Editor’s Draft, 1 June 2016.
  *
  * [[https://w3c.github.io/webvr/]]
  */
package object webvr {

  /** convert VREyeType to its string representation, "left" or "right" */
  implicit def VREyeTypeToString(t: VREyeType): String = t.toString

  type VREye = String // "left" | "right"
  type VRSource = HTMLCanvasElement // or OffscreenCanvas  todo
}

