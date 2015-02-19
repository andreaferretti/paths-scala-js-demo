package demo.components

import scala.scalajs.js
import japgolly.scalajs.react._
// import japgolly.scalajs.react.vdom.all._
import japgolly.scalajs.react.vdom.svg.all._
import paths.high.Pie

import demo.colors._


object piechart {
  case class Country(name: String, population: Int)

  private def move(p: js.Array[Double]) = s"translate(${ p(0) },${ p(1) })"
  private val palette = mix(Color(130, 140, 210), Color(180, 205, 150))

  val PieChart = ReactComponentB[List[Country]]("Item list")
    .render(countries => {
      val pie = Pie[Country](
        data = countries,
        accessor = _.population,
        r = 60,
        R = 140,
        center = (0, 0)
      )
      val slices = pie.curves map { curve =>
        g(
          lineargradient(
            id := s"grad-${ curve.index }",
            stop(stopColor := string(palette(curve.index)), offset := "0%"),
            stop(stopColor := string(lighten(palette(curve.index))), offset := "100%")
          ),
          path(d := curve.sector.path.print, fill := s"url(#grad-${ curve.index })"),
          text(
            transform := move(curve.sector.centroid),
            textAnchor := "middle",
            curve.item.name
          )
        )
      }

      svg(
        width := 400,
        height := 400,
        g(
          transform := move(js.Array(200, 200)),
          slices
        )
      )
    })
    .build
}