package demo

import scala.scalajs.js
import js.annotation.JSExport
import org.scalajs.dom.{ document, window }

import demo.components.toplevel._


object Main extends js.JSApp {
  def defer(F: => Unit) = window.setTimeout({ () => F }, 0)

  def main = defer(TopLevel(()).render(document.body))
}