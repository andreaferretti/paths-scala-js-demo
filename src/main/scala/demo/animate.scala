package demo

import scala.scalajs.js.Date
import org.scalajs.dom.window
import japgolly.scalajs.react.BackendScope


object animate {
  trait Interpolable[A] {
    def mix(a: A, b: A, t: Double): A
  }

  case class AnimateOptions(
    duration: Double = 500,
    easing: Double => Double = identity
  )

  implicit val interpolateNumbers = new Interpolable[Double] {
    def mix(a: Double, b: Double, t: Double) = a + (b - a) * t
  }

  implicit class AnimateBackendScope[P, S](val $: BackendScope[P, S]) extends AnyVal {
    def animateState(finalState: S, options: AnimateOptions = AnimateOptions())(implicit i: Interpolable[S]) = {
      val start = Date.now()
      val initialState = $._state

      def update(d: Double = 0): Unit = {
        val now = Date.now()
        val t = ((now - start) / options.duration) min 1
        $.setState(i.mix(initialState, finalState, options.easing(t)))

        if (t < 1) window.requestAnimationFrame(update _)
      }

      update()
    }

    def animateModState(mod: S => S, options: AnimateOptions = AnimateOptions())(implicit i: Interpolable[S]) =
      animateState(mod($._state), options)
  }
}