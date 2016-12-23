package demo.components

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all.key
import japgolly.scalajs.react.vdom.svg.all._
import paths.high.Bar

import demo.colors._


object bar {
  case class Stats(values: List[List[Double]], labels: List[String])
  private val palette = mix(Color(130, 140, 210), Color(180, 205, 150))
  private def below(p: js.Array[Double]) = s"translate(${ p(0) }, 320)"

  val BarChart = ReactComponentB[Stats]("Bar chart")
    .render(stats => {
      val bar = Bar[Double](
        data = stats.values,
        accessor = identity,
        width = 380,
        height = 300,
        gutter = 10,
        offset = (80, 50)
      )
      val groups = stats.values.length
      val middle = groups / 2
      val count = stats.values.head.length

      val rectangles = bar.curves.zipWithIndex map { case (curve, i) =>
        if (curve.index == middle) g(
          path(d := curve.line.path.print, stroke := "none", fill := string(palette(curve.index))),
          text(transform := below(curve.line.centroid), textAnchor := "middle", stats.labels(i / count))
        )
        else path(d := curve.line.path.print, stroke := "none", fill := string(palette(curve.index)))
      }

      svg(width := 460, height := 400,
        rectangles
      )
    })
    .build
}