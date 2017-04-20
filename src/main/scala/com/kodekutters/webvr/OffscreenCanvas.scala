package com.kodekutters.webvr

/**
  * The OffscreenCanvas interface provides a canvas that can be rendered off screen.
  * It is available in both the window and worker contexts.
  *
  * see [[https://developer.mozilla.org/en-US/docs/Web/API/OffscreenCanvas]]
  * and [[https://wiki.whatwg.org/wiki/OffscreenCanvas]]
  *
  * see: Media Capture from DOM Element W3C Editor's Draft 03 November 2016
  */

import org.scalajs.dom.Blob
import org.scalajs.dom.webgl.RenderingContext

import scala.concurrent.Promise
import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal


/**
  * The ImageBitmap interface represents a bitmap image which can be drawn to a <canvas> without undue latency.
  * It can be created from a variety of source objects using the createImageBitmap() factory method.
  * ImageBitmap provides an asynchronous and resource efficient pathway to prepare textures for rendering in WebGL.
  *
  * [[https://developer.mozilla.org/en-US/docs/Web/API/ImageBitmap]]
  */
@js.native
trait ImageBitmap extends js.Object {
  /** Is an unsigned long representing the height, in CSS pixels, of the ImageData. */
  def width: Int = js.native
  /** Is an unsigned long representing the width, in CSS pixels, of the ImageData. */
  def height: Int = js.native
  /** Disposes of all graphical resources associated with an ImageBitmap. */
  def close(): Unit = js.native
}

/**
  * OffscreenCanvas constructor. Creates a new OffscreenCanvas object.
  *
  * @param width  The width of the offscreen canvas.
  * @param height The height of the offscreen canvas.
  */
@JSGlobal
@js.native
class OffscreenCanvas(width: Int, height: Int) extends js.Object {

  /**
    * The OffscreenCanvas.getContext() method returns a drawing context for an offscreen canvas,
    * or null if the context identifier is not supported.
    *
    * @param contextType       a DOMString containing the context identifier defining the drawing context associated to the canvas.
    *                          One off: "2d", "webgl", "webgl2" and "bitmaprenderer"
    * @param contextAttributes context attributes,
    *                          "2d" context attributes:
    *                          alpha: Boolean that indicates if the canvas contains an alpha channel. If set to false, the browser now knows that the backdrop is always opaque, which can speed up drawing of transparent content and images then.
    *                          (Gecko only) willReadFrequently: Boolean that indicates whether or not a lot of read-back operations are planned. This will force the use of a software (instead of hardware accelerated) 2D canvas and can save memory when calling getImageData() frequently. This option is only available, if the flag gfx.canvas.willReadFrequently.enable is set to true (which, by default, is only the case for B2G/Firefox OS).
    *                          (Blink only) storage: String that indicates which storage is used ("persistent" by default).
    *                          "WebGL" context attributes:
    *                          alpha: Boolean that indicates if the canvas contains an alpha buffer.
    *                          depth: Boolean that indicates that the drawing buffer has a depth buffer of at least 16 bits.
    *                          stencil: Boolean that indicates that the drawing buffer has a stencil buffer of at least 8 bits.
    *                          antialias: Boolean that indicates whether or not to perform anti-aliasing.
    *                          premultipliedAlpha: Boolean that indicates that the page compositor will assume the drawing buffer contains colors with pre-multiplied alpha.
    *                          preserveDrawingBuffer: If the value is true the buffers will not be cleared and will preserve their values until cleared or overwritten by the author.
    *                          failIfMajorPerformanceCaveat: Boolean that indicates if a context will be created if the system performance is low.
    * @return A RenderingContext which is either a
    *         CanvasRenderingContext2D for "2d",
    *         WebGLRenderingContext for "webgl" and "experimental-webgl",
    *         WebGL2RenderingContext for "webgl2" and "experimental-webgl2" , or
    *         ImageBitmapRenderingContext for "bitmaprenderer".
    */
  def getContext(contextType: String, contextAttributes: js.Any*): RenderingContext = js.native

  /** The OffscreenCanvas.toBlob() method creates a Blob object representing the image contained in the canvas
    *
    * @param `type`         type Optional, a DOMString indicating the image format.
    *                       The default type is image/png.
    * @param encoderOptions encoderOptions Optional
    *                       A Number between 0 and 1 indicating image quality if the requested type is
    *                       image/jpeg or image/webp. If this argument is anything else,
    *                       the default value for image quality is used. Other arguments are ignored.
    * @return A Promise returning a Blob object representing the image contained in the canvas.
    */
  def toBlob(`type`: Option[String], encoderOptions: js.Any*): Promise[Blob] = js.native

  /**
    * The OffscreenCanvas.transferToImageBitmap() method creates an ImageBitmap object from the most
    * recently rendered image of the OffscreenCanvas.
    */
  def transferToImageBitmap(): ImageBitmap = js.native

}


