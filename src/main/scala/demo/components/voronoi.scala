package demo.components

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all.key
import japgolly.scalajs.react.vdom.svg.all._
import paths.high.Voronoi

import demo.colors._


object voronoi {
  private val palette = List("LightCoral", "NavajoWhite", "LemonChiffon",
    "PaleGreen", "CornflowerBlue", "Thistle", "Lavender", "#FFB347", "#A05FAB",
    "#E7D6B6", "#DE9AA4", "#AFCFAA", "#B3AF9C", "#C1C5C0","#1b85b8",
    "#ae5a41", "#c3cb71", "#FFD1DC", "#AEC6CF", "#E7D943", "#A3E743",
    "#FDFD96", "#836953", "#779ECB", "#D5B13B", "#66A0A5", "#E4DE12",
    "#B94BE4",  "#F2DE9C", "#3BBDD1", "#A3E8F3", "#ffff00")

  val VoronoiChart = ReactComponentB[Seq[(Double, Double)]]("Voronoi chart")
    .render(points => {
      val diagram = Voronoi[(Double, Double)](
        data = points,
        accessor = identity,
        width = 460,
        height = 400,
        xrange = (0, 1),
        yrange = (0, 1)
      )

      val areas = diagram.curves map { curve =>
        path(d := curve.line.path.print, stroke := "grey", fill := palette(curve.index))
      }
      val circles = diagram.nodes map { node =>
        val p = node.point
        circle(cx := p(0), cy := p(1), r := 3, stroke := "none", fill := "red")
      }

      svg(width := 460, height := 400,
        areas,
        circles
      )
    })
    .build
}