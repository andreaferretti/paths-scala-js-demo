package demo

import scala.scalajs.js.{ Date, Math }
import org.scalajs.dom.window
import japgolly.scalajs.react.BackendScope


object animate {
  trait Interpolable[A] {
    def mix(a: A, b: A, t: Double): A
  }

  case class AnimateOptions(
    duration: Double = 500,
    easing: Double => Double = Easing.easeInQuad,
    done: Unit => Unit = { _ => () }
  )


  object Easing {
    private val s = 1.70158

    def linear(t: Double) = t

    def easeInQuad(t: Double) = t * t

    def easeOutQuad(t: Double) = -t * (t - 2)

    def easeInOutQuad(t: Double) =
      if (t < 1/2) 2 * t * t else -2 * t * t + 4 * t - 1

    def easeInElastic(t: Double) = {
      val q = t - 1
      -Math.pow(2, 10 * q) * Math.sin((2 * q / 0.3 - 0.5) * Math.PI)
    }

    def easeOutElastic(t: Double) =
      Math.pow(2, -10 * t) * Math.sin( (2 * t / 0.3 - 0.5)* Math.PI) + 1

    def easeInOutElastic(t: Double) = {
      val q = 2 * t - 1

      if (t < 1/2) -0.5 * Math.pow(2, 10 * q) * Math.sin((q / 0.225 - 0.5) * Math.PI)
      else Math.pow(2,-10 * q) * Math.sin((q / 0.225 - 0.5) * Math.PI) * 0.5 + 1
    }

    def easeInBack(t: Double) = t * t * ((s + 1) * t - s)

    def easeOutBack(t: Double) = {
      val q = t - 1

      q * q * ((s + 1) * q + s) + 1
    }

    def easeInOutBack(t: Double) = {
      val r = s * 1.525
      if (t < 1 / 2) 2 * t * t * ((r + 1) * 2 * t - r)
      else {
        val q = t - 1
        2 * q * q * ((r + 1) * 2 * q + r) + 1
      }
    }

    def easeInBounce(t: Double) = 1 - easeOutBounce(1 - t)

    def easeOutBounce(t: Double) = {
      val q = 2.75 * t
      val l = 7.5625
      if (q < 1) l * t * t
      else if (q < 2) {
        val p = t - 1.5 / 2.75
        l * p * p + 0.75
      }
      else if (q < 2.5) {
        val p = t - 2.25 / 2.75
        l * p * p + 0.9375
      }
      else {
        val p = t - 2.625 / 2.75
        l * p * p + 0.984375
      }
    }

    def easeInOutBounce(t: Double) =
      if (t < 1/2) easeInBounce(2 * t) / 2 else (easeOutBounce(2 * t - 1) + 1) / 2
  }

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

        if (t < 1) window.requestAnimationFrame(update _) else options.done(())
      }

      update()
    }

    def animateModState(mod: S => S, options: AnimateOptions = AnimateOptions())(implicit i: Interpolable[S]) =
      animateState(mod($._state), options)
  }
}