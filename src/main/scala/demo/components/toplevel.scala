package demo.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._

import piechart._


object toplevel {
  val countries = List(
    Country("Italy", 59859996),
    Country("Mexico", 118395054),
    Country("France", 65806000),
    Country("Argentina", 40117096),
    Country("Japan", 127290000)
  )

  val TopLevel = ReactComponentB[Unit]("Top level component")
    .render(_ =>
      div(className := "container",
        div(className := "row",
          div(className := "col-md-6", id := "pie",
            PieChart(countries)
          )
        )
      )
    )
    .build
}