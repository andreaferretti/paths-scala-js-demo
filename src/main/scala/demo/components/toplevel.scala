package demo.components

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._

import panel._
import logo._
import piechart._
import radar._
import tree._
import bar._
import graph._
import sankey._
import line._
import smoothline._
import waterfall._


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
  val tree = Duck("Grandma", List(
    Duck("Eider", List(
      Duck("Fethry"), Duck("Abner")
    )),
    Duck("Daphne", List(
      Duck("Gladstone")
    )),
    Duck("Quackmore", List(
      Duck("Donald"),
      Duck("Della", List(
        Duck("Huey"), Duck("Dewey"), Duck("Louie")
      ))
    ))
  ))
  val stats = Stats(
    values = List(
      List(1, 2.0, 3, 4),
      List(2, 3.0, 1, 4),
      List(2, 2.5, 3, 3)
    ),
    labels = List("2009", "2010", "2011", "2012")
  )
  def randomGraph(n: Int, density: Double) = {
    val characters = (1 to n) map { x => Character(x.toString) }
    val links = characters.combinations(2) filter { _ =>
      js.Math.random() < density
    } map { case Vector(Character(a), Character(b)) =>
      Link(a, b, 3 + 5 * js.Math.random())
    }

    Family(characters = characters.toList, links = links.toList)
  }
  val flow = Flow(
    sectors = List(
      List(
        Sector("Energy"),
        Sector("Agriculture"),
        Sector("Minerals")
      ),
      List(
        Sector("Transportation"),
        Sector("Harvest"),
        Sector("Fuel")
      ),
      List(
        Sector("Road"),
        Sector("Chemicals")
      )
    ),
    links = List(
      SLink(start = "Energy", end = "Harvest", weight = 10),
      SLink(start = "Energy", end = "Fuel", weight = 30),
      SLink(start = "Agriculture", end = "Road", weight = 10),
      SLink(start = "Agriculture", end = "Transportation", weight = 10),
      SLink(start = "Agriculture", end = "Harvest", weight = 10),
      SLink(start = "Minerals", end = "Fuel", weight = 30),
      SLink(start = "Transportation", end = "Chemicals", weight =  20),
      SLink(start = "Harvest", end = "Chemicals", weight =  10),
      SLink(start = "Fuel", end = "Road", weight =  30),
      SLink(start = "Minerals", end = "Chemicals", weight =  25)
    )
  )
  val tickers = List(
    List(
      Event("Jan 2000", 39.81),
      Event("Feb 2000", 36.35),
      Event("Mar 2000", 43.22),
      Event("Apr 2000", 28.37),
      Event("May 2000", 25.45),
      Event("Jun 2000", 32.54),
      Event("Jul 2000", 28.4),
      Event("Aug 2000", 28.4),
      Event("Sep 2000", 24.53),
      Event("Oct 2000", 28.02),
      Event("Nov 2000", 23.34),
      Event("Dec 2000", 17.65),
      Event("Jan 2001", 24.84),
      Event("Feb 2001", 24.0),
      Event("Mar 2001", 22.25),
      Event("Apr 2001", 27.56),
      Event("May 2001", 28.14),
      Event("Jun 2001", 29.7),
      Event("Jul 2001", 26.93),
      Event("Aug 2001", 23.21),
      Event("Sep 2001", 20.82),
      Event("Oct 2001", 23.65),
      Event("Nov 2001", 26.12),
      Event("Dec 2001", 26.95),
      Event("Jan 2002", 25.92),
      Event("Feb 2002", 23.73),
      Event("Mar 2002", 24.53),
      Event("Apr 2002", 21.26),
      Event("May 2002", 20.71),
      Event("Jun 2002", 22.25),
      Event("Jul 2002", 19.52),
      Event("Aug 2002", 19.97),
      Event("Sep 2002", 17.79),
      Event("Oct 2002", 21.75),
      Event("Nov 2002", 23.46),
      Event("Dec 2002", 21.03),
      Event("Jan 2003", 19.31),
      Event("Feb 2003", 19.34),
      Event("Mar 2003", 19.76),
      Event("Apr 2003", 20.87),
      Event("May 2003", 20.09),
      Event("Jun 2003", 20.93),
      Event("Jul 2003", 21.56),
      Event("Aug 2003", 21.65),
      Event("Sep 2003", 22.69),
      Event("Oct 2003", 21.45),
      Event("Nov 2003", 21.1),
      Event("Dec 2003", 22.46),
      Event("Jan 2004", 22.69),
      Event("Feb 2004", 21.77),
      Event("Mar 2004", 20.46),
      Event("Apr 2004", 21.45),
      Event("May 2004", 21.53),
      Event("Jun 2004", 23.44),
      Event("Jul 2004", 23.38),
      Event("Aug 2004", 22.47),
      Event("Sep 2004", 22.76),
      Event("Oct 2004", 23.02),
      Event("Nov 2004", 24.6),
      Event("Dec 2004", 24.52),
      Event("Jan 2005", 24.11),
      Event("Feb 2005", 23.15),
      Event("Mar 2005", 22.24),
      Event("Apr 2005", 23.28),
      Event("May 2005", 23.82),
      Event("Jun 2005", 22.93),
      Event("Jul 2005", 23.64),
      Event("Aug 2005", 25.35),
      Event("Sep 2005", 23.83),
      Event("Oct 2005", 23.8),
      Event("Nov 2005", 25.71),
      Event("Dec 2005", 24.29),
      Event("Jan 2006", 26.14),
      Event("Feb 2006", 25.04),
      Event("Mar 2006", 25.36),
      Event("Apr 2006", 22.5),
      Event("May 2006", 21.19),
      Event("Jun 2006", 21.8),
      Event("Jul 2006", 22.51),
      Event("Aug 2006", 24.13),
      Event("Sep 2006", 25.68),
      Event("Oct 2006", 26.96),
      Event("Nov 2006", 27.66),
      Event("Dec 2006", 28.13),
      Event("Jan 2007", 29.07),
      Event("Feb 2007", 26.63),
      Event("Mar 2007", 26.35),
      Event("Apr 2007", 28.3),
      Event("May 2007", 29.11),
      Event("Jun 2007", 27.95),
      Event("Jul 2007", 27.5),
      Event("Aug 2007", 27.34),
      Event("Sep 2007", 28.04),
      Event("Oct 2007", 35.03),
      Event("Nov 2007", 32.09),
      Event("Dec 2007", 34.0),
      Event("Jan 2008", 31.13),
      Event("Feb 2008", 26.07),
      Event("Mar 2008", 27.21),
      Event("Apr 2008", 27.34),
      Event("May 2008", 27.25),
      Event("Jun 2008", 26.47),
      Event("Jul 2008", 24.75),
      Event("Aug 2008", 26.36),
      Event("Sep 2008", 25.78),
      Event("Oct 2008", 21.57),
      Event("Nov 2008", 19.66),
      Event("Dec 2008", 18.91)
    ),
    List(
      Event("Jan 2000", 25.94),
      Event("Feb 2000", 28.66),
      Event("Mar 2000", 33.95),
      Event("Apr 2000", 31.01),
      Event("May 2000", 21.0),
      Event("Jun 2000", 26.19),
      Event("Jul 2000", 25.41),
      Event("Aug 2000", 30.47),
      Event("Sep 2000", 12.88),
      Event("Oct 2000", 9.78),
      Event("Nov 2000", 8.25),
      Event("Dec 2000", 7.44),
      Event("Jan 2001", 10.81),
      Event("Feb 2001", 9.12),
      Event("Mar 2001", 11.03),
      Event("Apr 2001", 12.74),
      Event("May 2001", 9.98),
      Event("Jun 2001", 11.62),
      Event("Jul 2001", 9.4),
      Event("Aug 2001", 9.27),
      Event("Sep 2001", 7.76),
      Event("Oct 2001", 8.78),
      Event("Nov 2001", 10.65),
      Event("Dec 2001", 10.95),
      Event("Jan 2002", 12.36),
      Event("Feb 2002", 10.85),
      Event("Mar 2002", 11.84),
      Event("Apr 2002", 12.14),
      Event("May 2002", 11.65),
      Event("Jun 2002", 8.86),
      Event("Jul 2002", 7.63),
      Event("Aug 2002", 7.38),
      Event("Sep 2002", 7.25),
      Event("Oct 2002", 8.03),
      Event("Nov 2002", 7.75),
      Event("Dec 2002", 7.16),
      Event("Jan 2003", 7.18),
      Event("Feb 2003", 7.51),
      Event("Mar 2003", 7.07),
      Event("Apr 2003", 7.11),
      Event("May 2003", 8.98),
      Event("Jun 2003", 9.53),
      Event("Jul 2003", 10.54),
      Event("Aug 2003", 11.31),
      Event("Sep 2003", 10.36),
      Event("Oct 2003", 11.44),
      Event("Nov 2003", 10.45),
      Event("Dec 2003", 10.69),
      Event("Jan 2004", 11.28),
      Event("Feb 2004", 11.96),
      Event("Mar 2004", 13.52),
      Event("Apr 2004", 12.89),
      Event("May 2004", 14.03),
      Event("Jun 2004", 16.27),
      Event("Jul 2004", 16.17),
      Event("Aug 2004", 17.25),
      Event("Sep 2004", 19.38),
      Event("Oct 2004", 26.2),
      Event("Nov 2004", 33.53),
      Event("Dec 2004", 32.2),
      Event("Jan 2005", 38.45),
      Event("Feb 2005", 44.86),
      Event("Mar 2005", 41.67),
      Event("Apr 2005", 36.06),
      Event("May 2005", 39.76),
      Event("Jun 2005", 36.81),
      Event("Jul 2005", 42.65),
      Event("Aug 2005", 46.89),
      Event("Sep 2005", 53.61),
      Event("Oct 2005", 57.59),
      Event("Nov 2005", 67.82),
      Event("Dec 2005", 71.89),
      Event("Jan 2006", 75.51),
      Event("Feb 2006", 68.49),
      Event("Mar 2006", 62.72),
      Event("Apr 2006", 70.39),
      Event("May 2006", 59.77),
      Event("Jun 2006", 57.27),
      Event("Jul 2006", 67.96),
      Event("Aug 2006", 67.85),
      Event("Sep 2006", 76.98),
      Event("Oct 2006", 81.08),
      Event("Nov 2006", 91.66),
      Event("Dec 2006", 84.84),
      Event("Jan 2007", 85.73),
      Event("Feb 2007", 84.61),
      Event("Mar 2007", 92.91),
      Event("Apr 2007", 99.8),
      Event("May 2007", 121.19),
      Event("Jun 2007", 122.04),
      Event("Jul 2007", 131.76),
      Event("Aug 2007", 138.48),
      Event("Sep 2007", 153.47),
      Event("Oct 2007", 189.95),
      Event("Nov 2007", 182.22),
      Event("Dec 2007", 198.08),
      Event("Jan 2008", 135.36),
      Event("Feb 2008", 125.02),
      Event("Mar 2008", 143.5),
      Event("Apr 2008", 173.95),
      Event("May 2008", 188.75),
      Event("Jun 2008", 167.44),
      Event("Jul 2008", 158.95),
      Event("Aug 2008", 169.53),
      Event("Sep 2008", 113.66),
      Event("Oct 2008", 107.59),
      Event("Nov 2008", 92.67),
      Event("Dec 2008", 85.35)
    ),
    List(
      Event("Jan 2000", 64.56),
      Event("Feb 2000", 68.87),
      Event("Mar 2000", 67.0),
      Event("Apr 2000", 55.19),
      Event("May 2000", 48.31),
      Event("Jun 2000", 36.31),
      Event("Jul 2000", 30.12),
      Event("Aug 2000", 41.5),
      Event("Sep 2000", 38.44),
      Event("Oct 2000", 36.62),
      Event("Nov 2000", 24.69),
      Event("Dec 2000", 15.56),
      Event("Jan 2001", 17.31),
      Event("Feb 2001", 10.19),
      Event("Mar 2001", 10.23),
      Event("Apr 2001", 15.78),
      Event("May 2001", 16.69),
      Event("Jun 2001", 14.15),
      Event("Jul 2001", 12.49),
      Event("Aug 2001", 8.94),
      Event("Sep 2001", 5.97),
      Event("Oct 2001", 6.98),
      Event("Nov 2001", 11.32),
      Event("Dec 2001", 10.82),
      Event("Jan 2002", 14.19),
      Event("Feb 2002", 14.1),
      Event("Mar 2002", 14.3),
      Event("Apr 2002", 16.69),
      Event("May 2002", 18.23),
      Event("Jun 2002", 16.25),
      Event("Jul 2002", 14.45),
      Event("Aug 2002", 14.94),
      Event("Sep 2002", 15.93),
      Event("Oct 2002", 19.36),
      Event("Nov 2002", 23.35),
      Event("Dec 2002", 18.89),
      Event("Jan 2003", 21.85),
      Event("Feb 2003", 22.01),
      Event("Mar 2003", 26.03),
      Event("Apr 2003", 28.69),
      Event("May 2003", 35.89),
      Event("Jun 2003", 36.32),
      Event("Jul 2003", 41.64),
      Event("Aug 2003", 46.32),
      Event("Sep 2003", 48.43),
      Event("Oct 2003", 54.43),
      Event("Nov 2003", 53.97),
      Event("Dec 2003", 52.62),
      Event("Jan 2004", 50.4),
      Event("Feb 2004", 43.01),
      Event("Mar 2004", 43.28),
      Event("Apr 2004", 43.6),
      Event("May 2004", 48.5),
      Event("Jun 2004", 54.4),
      Event("Jul 2004", 38.92),
      Event("Aug 2004", 38.14),
      Event("Sep 2004", 40.86),
      Event("Oct 2004", 34.13),
      Event("Nov 2004", 39.68),
      Event("Dec 2004", 44.29),
      Event("Jan 2005", 43.22),
      Event("Feb 2005", 35.18),
      Event("Mar 2005", 34.27),
      Event("Apr 2005", 32.36),
      Event("May 2005", 35.51),
      Event("Jun 2005", 33.09),
      Event("Jul 2005", 45.15),
      Event("Aug 2005", 42.7),
      Event("Sep 2005", 45.3),
      Event("Oct 2005", 39.86),
      Event("Nov 2005", 48.46),
      Event("Dec 2005", 47.15),
      Event("Jan 2006", 44.82),
      Event("Feb 2006", 37.44),
      Event("Mar 2006", 36.53),
      Event("Apr 2006", 35.21),
      Event("May 2006", 34.61),
      Event("Jun 2006", 38.68),
      Event("Jul 2006", 26.89),
      Event("Aug 2006", 30.83),
      Event("Sep 2006", 32.12),
      Event("Oct 2006", 38.09),
      Event("Nov 2006", 40.34),
      Event("Dec 2006", 39.46),
      Event("Jan 2007", 37.67),
      Event("Feb 2007", 39.14),
      Event("Mar 2007", 39.79),
      Event("Apr 2007", 61.33),
      Event("May 2007", 69.14),
      Event("Jun 2007", 68.41),
      Event("Jul 2007", 78.54),
      Event("Aug 2007", 79.91),
      Event("Sep 2007", 93.15),
      Event("Oct 2007", 89.15),
      Event("Nov 2007", 90.56),
      Event("Dec 2007", 92.64),
      Event("Jan 2008", 77.7),
      Event("Feb 2008", 64.47),
      Event("Mar 2008", 71.3),
      Event("Apr 2008", 78.63),
      Event("May 2008", 81.62),
      Event("Jun 2008", 73.33),
      Event("Jul 2008", 76.34),
      Event("Aug 2008", 80.81),
      Event("Sep 2008", 72.76),
      Event("Oct 2008", 57.24),
      Event("Nov 2008", 42.7),
      Event("Dec 2008", 51.28)
    )
  )
  val balance = List(
    Total("Gross income", 30),
    Expense("Transport", 6),
    Expense("Distribution", 3),
    RunningTotal("Detail income"),
    Expense("Taxes", 8),
    RunningTotal("Net income")
  )

  val TopLevel = ReactComponentB[Unit]("Top level component")
    .render(_ =>
      div(className := "container",
        div(className := "row", Logo(())),
        div(className := "row",
          div(className := "col-md-12",
            p(className := "alert alert-success",
              "For simplicity, many animations have been stripped out from the Scala.js demo. See ",
              a(href := "http://andreaferretti.github.io/paths-js-demo/", "this demo"),
              " or ",
              a(href := "http://andreaferretti.github.io/paths-js-react-demo/", "this demo"),
              " for an overview of the possibilities."
            )
          )
        ),
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
        ),
        div(className := "row",
          Panel(PanelContent(
            id = Some("tree"),
            title = "Tree Chart",
            text = "Here is part of the duck family tree."
          ), TreeChart(tree)),
          Panel(PanelContent(
            id = Some("bar"),
            title = "Bar Chart",
            text = "Here is a bar chart example."
          ), BarChart(stats))
        ),
        div(className := "row",
          Panel(PanelContent(
            id = Some("graph"),
            title = "Graph Chart",
            text = "A preliminary example of force-directed graph."
          ), GraphChart(randomGraph(30, 0.15))),
          Panel(PanelContent(
            id = Some("sankey"),
            title = "Sankey Diagram",
            text = "Displays flow among entities."
          ), SankeyDiagram(flow))
        ),
        div(className := "row",
          Panel(PanelContent(
            id = Some("stock"),
            title = "Stock Chart",
            text = "Here is a line chart example."
          ), LineChart(tickers)),
          Panel(PanelContent(
            id = Some("smoothline"),
            title = "Smooth Line Chart",
            text = "And here is the same example drawn smoothly."
          ), SmoothLineChart(tickers))
        ),
        div(className := "row",
          Panel(PanelContent(
            id = Some("waterfall"),
            title = "Waterfall Chart",
            text = "A breakdown of incomes and costs."
          ), WaterfallChart(balance))
        )
      )
    )
    .build
}