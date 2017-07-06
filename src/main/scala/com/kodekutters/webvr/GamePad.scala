package com.kodekutters.webvr

/*
 * Implements the Gamepad API. W3C Editor's Draft 03 December 2016
 *
 * [[https://w3c.github.io/gamepad/]]
 */

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, ScalaJSDefined}

//@js.native
//trait GamepadMappingType extends js.Any

sealed class GamepadMappingType(val `type`: String)
object GamepadMappingType {
  case object Empty extends GamepadMappingType("")
  case object Standard extends GamepadMappingType("standard")
}

/**
  * This interface defines the state of an individual button on a gamepad device.
  */
@ScalaJSDefined
trait GamepadButton extends js.Any {

  /**
    * True if the button is pressed, false otherwise.
    */
  val pressed: Boolean

  /**
    * True if the button is touched, false otherwise.
    */
  val touched: Boolean

  /**
    * The value of this gamepad button, 0 is fully unpressed, 1 is
    * fully pressed.
    */
  val value: Double
}

/**
  * This interface defines an individual gamepad device.
  */
@ScalaJSDefined
trait Gamepad extends js.Any  {

  /** The identification string for the gamepad. */
  val id: String

  /** The index of the gamepad as returned by Navigator.getGamepads */
  val index: Int

  /** True if this gamepad is currently connected. */
  val connected: Boolean

  /** Last time the data for this gamepad was updated. */
  val timestamp: Double

  /**
    * An array containing the state of all of the gamepad's axes, from
    * -1 .. 1.
    */
  val axes: js.Array[Double]

  /**
    * The current state of all the gamepad's buttons.
    */
  val buttons: js.Array[GamepadButton]

  /**
    * The layout of the gamepad.  Either "standard" or unknown ("").
    */
  val mapping: GamepadMappingType

}

@ScalaJSDefined
trait GamepadEventInit extends js.Any {
  val gamepad: Gamepad
}

object GamepadEventInit {
  def apply(gamepad: Gamepad): GamepadEventInit =
    js.Dynamic.literal("gamepad" -> gamepad).asInstanceOf[GamepadEventInit]
}

@JSGlobal("GamepadEvent")
@js.native
class GamepadEvent(init: GamepadEventInit) extends dom.Event {
  val gamepad: Gamepad = js.native
}

@js.native
trait NavigatorGamepad extends js.Any {
  def getGamepads(): js.Array[Gamepad] = js.native
}