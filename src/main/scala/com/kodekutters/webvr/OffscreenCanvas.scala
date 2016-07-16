package com.kodekutters.webvr

import org.scalajs.dom.Blob
import org.scalajs.dom.webgl.RenderingContext

import scala.concurrent.Promise
import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined


// see https://developer.mozilla.org/en-US/docs/Web/API/OffscreenCanvas
// and https://wiki.whatwg.org/wiki/OffscreenCanvas

/**
  * It's crucial that there be a way to explicitly dispose of ImageBitmaps
  * since they refer to potentially large graphics resources. Some uses
  * of this API proposal will result in repeated allocations of ImageBitmaps,
  * and garbage collection will not reliably reclaim them quickly enough.Here we reuse close(), which also exists on another Transferable type,
  * MessagePort. Potentially, all Transferable types should inherit from a
  * new interface type "Closeable".
  */
@ScalaJSDefined
trait ImageBitmap extends js.Object {  // todo should inherit from Transferable but it is sealed
  // Dispose of all graphical resources associated with this ImageBitmap.
  def close(): Unit
}

/**
  * The OffscreenCanvas interface provides a canvas that can be rendered off screen.
  * It is available in both the window and worker contexts.
  *
  * @param width  The width of the offscreen canvas.
  * @param height The height of the offscreen canvas.
  */
@js.native
class OffscreenCanvas( width: Long, height: Long) extends js.Object {

  /**
    * The OffscreenCanvas.getContext() method returns a drawing context for an offscreen canvas,
    * or null if the context identifier is not supported.
    */
  def getContext(contextId: String, any: js.Any*): RenderingContext = js.native

  /** The OffscreenCanvas.toBlob() method creates a Blob object representing the image contained in the canvas */
  def toBlob(`type`: Option[String], any: js.Any*): Promise[Blob] = js.native

  /**
    * The OffscreenCanvas.transferToImageBitmap() method creates an ImageBitmap object from the most
    * recently rendered image of the OffscreenCanvas.
    */
  def transferToImageBitmap(): ImageBitmap = js.native

}


