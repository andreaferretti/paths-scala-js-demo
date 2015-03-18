package demo.components

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all.key
import japgolly.scalajs.react.vdom.svg.all._
import paths.high.Stock

import demo.colors._


object line {
  case class Event(date: String, value: Double)

  private val palette = mix(Color(130, 140, 210), Color(180, 205, 150))
  private val months = List("Jan", "Feb", "Mar", "Apr", "May",
    "Jun", "Jul","Aug", "Sep", "Oct", "Nov", "Dec")

  private def parseDate(event: Event) = {
    val date = new js.Date
    val Array(month, year) = event.date split ' '
    date.setFullYear(year.toInt)
    date.setMonth(months.indexOf(month))

    date.getTime
  }

  val LineChart = ReactComponentB[Seq[Seq[Event]]]("Stock chart")
    .render(events => {
      val stock = Stock[Event](
        data = events,
        xaccessor = parseDate,
        yaccessor = _.value,
        width = 420,
        height = 360,
        closed = true
      )
      val lines = stock.curves map { curve =>
        g(transform := "translate(50,0)",
          path(d := curve.area.path.print, fill := string(transparent(palette(curve.index))), stroke := "none"),
          path(d := curve.line.path.print, fill := "none", stroke := string(palette(curve.index)))
        )
      }

      svg(width := 480, height := 400,
        lines
      )
    })
    .build
}