package demo.components

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._
import paths.high.Pie

import demo.colors._


object panel {
  case class PanelContent(id: Option[String], title: String, text: String)

  val Panel = ReactComponentB[PanelContent]("Panel")
    .render((content, children) => {
      div(className := "col-md-6", id := content.id,
        div(className := "panel panel-default",
          div(className := "panel-heading",
            h2(className := "panel-title", content.title)
          ),
          div(className := "panel-body",
            p(className := "alert alert-info", content.text),
            children
          )
        )
      )
    })
    .build
}