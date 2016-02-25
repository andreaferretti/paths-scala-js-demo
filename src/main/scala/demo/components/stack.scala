package demo.components

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all.key
import japgolly.scalajs.react.vdom.svg.all._
import paths.high.Stack

import demo.colors._
import bar.Stats


object stack {
  private val palette = mix(Color(130, 140, 210), Color(180, 205, 150))
  private def below(p: js.Array[Double]) = s"translate(${ p(0) }, 320)"

  val StackChart = ReactComponentB[Stats]("Stack chart")
    .render(stats => {
      val stack = Stack[Double](
        data = stats.values,
        accessor = identity,
        width = 380,
        height = 300,
        gutter = 10
      )
      val groups = stats.values.length
      val middle = groups / 2
      val count = stats.values.head.length

      js.Dynamic.global.console.log(stack)

      val rectangles = stack.curves.zipWithIndex map { case (curve, i) =>
        if (curve.group == 0) g(
          path(d := curve.line.path.print, stroke := "none", fill := string(palette(curve.group))),
          text(transform := below(curve.line.centroid), textAnchor := "middle", stats.labels(curve.index))
        )
        else path(d := curve.line.path.print, stroke := "none", fill := string(palette(curve.group)))
      }

      svg(width := 460, height := 400,
        g(transform := "translate(80,50)", rectangles)
      )
    })
    .build
}