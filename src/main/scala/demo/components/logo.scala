package demo.components

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all.{ a, h1, h3, href, div, className }
import japgolly.scalajs.react.vdom.svg.all._
import paths.mid.Bezier

import demo.colors._


object logo {
  val Logo = ReactComponentB[Unit]("Paths.js")
    .render(_ => {
      val line = Bezier(List(
        (0, 50),
        (50, 70),
        (100, 40),
        (150, 30),
        (200, 60),
        (250, 80),
        (300, 50)
      ))
      val circles = line.path.points.map(p => circle(
        r := 5,
        cx := p(0),
        cy := p(1),
        stroke := "red",
        strokeWidth := 2,
        fill := "white"
      ))

      div(id := "logo", className := "col-md-12",
        h1(a(href := "https://github.com/andreaferretti/paths-scala-js", "Paths.scala.js")),
        h3("Generate SVG paths for geometric shapes"),
        svg(width := 800, height := 100,
          g(transform := "translate(100, 0)",
            path(d := line.path.print, stroke := "red", fill := "none"),
            circles
          )
        )
      )
    })
    .build
}