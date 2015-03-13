package demo.components

import scala.scalajs.js
import org.scalajs.dom.window
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all.key
import japgolly.scalajs.react.vdom.svg.all._
import paths.high.Graph

import demo.colors._


object graph {
  case class Character(id: String)
  case class Link(start: String, end: String, weight: Double)
  case class Family(characters: List[Character], links: List[Link])
  case class State(initial: Double, current: Double)

  class Backend($: BackendScope[Graph[Character, Link], State]) {
    def tick(time: Double) = {
      val initial = $.state.initial
      $.props.tick()

      if (initial == 0) $.setState(State(time, time)) // record initial time
      else if (time - initial < 5000) $.modState(_.copy(current = time)) // stop animation after 5 seconds
    }
  }

  val InnerGraphChart = ReactComponentB[Graph[Character, Link]]("Graph chart")
    .initialState(State(0, 0))
    .backend(new Backend(_))
    .render((graph, _, backend) => {
      val edges = graph.curves map { curve =>
        path(d := curve.link.path.print)
      }
      val dots = graph.nodes map { node =>
        circle(cx := node.point(0), cy := node.point(1), r := 5)
      }
      window.requestAnimationFrame({ time: Double => backend.tick(time) })

      svg(width := 460, height := 400,
        edges,
        dots
      )

    })
    .build

  val GraphChart = ReactComponentB[Family]("Graph chart")
    .render(family => {
      val graph = Graph[Character, Link](
        nodes = family.characters,
        links = family.links,
        nodeaccessor = _.id,
        linkaccessor = identity,
        width = 500,
        height = 400,
        attraction = 1.1,
        repulsion = 1.3,
        threshold = 0.6
      )

      InnerGraphChart(graph)
    })
    .build

}