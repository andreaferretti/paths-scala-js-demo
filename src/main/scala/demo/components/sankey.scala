package demo.components

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all.key
import japgolly.scalajs.react.vdom.svg.all._
import paths.high.Sankey


object sankey {
  case class Sector(id: String)
  case class SLink(start: String, end: String, weight: Double)
  case class Flow(sectors: Seq[Seq[Sector]], links: Seq[SLink])

  private def right(p: js.Array[Double]) = s"translate(${ p(0) + 7 }, ${ p(1) })"
  private def left(p: js.Array[Double]) = s"translate(${ p(0) - 7 }, ${ p(1) })"
  private val palette = List("#707B82", "#7881C2", "#3E90F0")

  val SankeyDiagram = ReactComponentB[Flow]("Sankey Diagram")
    .render(flow => {
      val sankey = Sankey[Sector, SLink](
        nodes = flow.sectors,
        links = flow.links,
        nodeaccessor = _.id,
        linkaccessor = identity,
        width = 420,
        height = 360,
        gutter = 15,
        rectWidth = 10
      )

      val rects = sankey.rectangles map { r =>
        val firstHalf = r.group <= flow.sectors.length / 2

        g(
          path(d := r.curve.path.print, fill := palette(r.group), stroke := "none", opacity := 0.7),
          if (firstHalf) text(transform := right(r.curve.centroid), textAnchor := "start", r.item.id)
          else text(transform := left(r.curve.centroid), textAnchor := "end", r.item.id)
        )
      }
      val flows = sankey.curvedRectangles map { r =>
        path(d := r.curve.path.print, fill := "#acd1e9", stroke := "none", opacity := 0.7)
      }

      svg(width := 460, height := 400,
        g(transform := "translate(30,30)",
          flows,
          rects
        )
      )
    })
    .build
}