package demo.components

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all.key
import japgolly.scalajs.react.vdom.svg.all._
import paths.high.Tree

import demo.colors._


object tree {
  case class Duck(name: String, descendants: List[Duck] = List())
  private def move(p: js.Array[Double]) = s"translate(${ p(0) },${ p(1) })"
  private def isLeaf(duck: Duck) = duck.descendants.length == 0

  val TreeChart = ReactComponentB[Duck]("Pie chart")
    .render(ducks => {
      val tree = Tree[Duck](
        data = ducks,
        children = _.descendants,
        width = 300,
        height = 300
      )

      val branches = tree.curves map { curve =>
        path(d := curve.connector.path.print)
      }
      val nodes = tree.nodes map { node =>
        g(transform := move(node.point),
          circle(r := 5, cx := 0, cy := 0),
          text(
            transform := (if (isLeaf(node.item)) "translate(10,0)" else "translate(-10,0)"),
            textAnchor := (if (isLeaf(node.item)) "start" else "end"),
            node.item.name
          )
        )
      }

      svg(width := 460, height := 400,
        g(transform := "translate(80,50)",
          branches,
          nodes
        )
      )
    })
    .build
}