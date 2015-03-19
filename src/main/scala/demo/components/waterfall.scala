package demo.components

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all.key
import japgolly.scalajs.react.vdom.svg.all._
import paths.high.Waterfall

import demo.colors._


object waterfall {
  sealed trait WItem { def name: String }
  case class Expense(name: String, value: Double) extends WItem
  case class Total(name: String, value: Double) extends WItem
  case class RunningTotal(name: String) extends WItem

  private case class Stat(value: Option[Double], absolute: Boolean)
  private def prepare(item: WItem) = item match {
    case Expense(_, value) => Stat(Some(-value), false)
    case Total(_, value) => Stat(Some(value), true)
    case RunningTotal(_) => Stat(None, true)
  }
  private val palette = mix(Color(130, 140, 210), Color(180, 205, 150))
  private def move(p: js.Array[Double]) = s"translate(${p(0)},380)"

  val WaterfallChart = ReactComponentB[Seq[WItem]]("Waterfall chart")
    .render(items => {
      val waterfall = Waterfall[WItem](
        data = items,
        accessor = prepare,
        width = 420,
        height = 360,
        gutter = Some(10)
      )
      val rectangles = waterfall.curves map { curve =>
        path(d := curve.line.path.print, fill := string(palette(curve.index)), stroke := "none")
      }
      val labels = waterfall.curves map { curve =>
        text(transform := move(curve.line.centroid), textAnchor := "middle", curve.item.name)
      }

      svg(width := 500, height := 400,
        g(transform := "translate(50,0)", rectangles, labels)
      )
    })
    .build
}