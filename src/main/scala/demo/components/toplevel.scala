package demo.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._

import panel._
import logo._
import piechart._
import radar._


object toplevel {
  val countries = List(
    Country("Italy", 59859996),
    Country("Mexico", 118395054),
    Country("France", 65806000),
    Country("Argentina", 40117096),
    Country("Japan", 127290000)
  )
  val pokemons = List(
    Pokemon("Bulbasaur", 45, 49, 49, 65, 65, 45),
    Pokemon("Ivysaur", 60, 62, 63, 80, 80, 60),
    Pokemon("Venusaur", 80, 82, 83, 100, 100, 80),
    Pokemon("Kakuna", 45, 25, 50, 25, 25, 35),
    Pokemon("Chameleon", 58, 64, 58, 80, 65, 80),
    Pokemon("Squirtle", 40, 48, 65, 50, 64, 43),
    Pokemon("Blastoise", 79, 83, 100, 85, 105, 78),
    Pokemon("Butterfree", 60, 45, 50, 90, 80, 70)
  )

  val TopLevel = ReactComponentB[Unit]("Top level component")
    .render(_ =>
      div(className := "container",
        div(className := "row", Logo(())),
        div(className := "row",
          Panel(PanelContent(
            id = Some("pie"),
            title = "Pie Chart",
            text = "Here is a pie chart example"
          ), PieChart(countries)),
          Panel(PanelContent(
            id = Some("radar"),
            title = "Radar Chart",
            text = "Here is a radar chart showing Pokémon stats. Try changing Pokémon."
          ), RadarChart(pokemons))
        )
      )
    )
    .build
}