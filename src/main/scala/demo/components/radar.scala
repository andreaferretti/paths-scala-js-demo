package demo.components

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all.{ key, div, h4, p, span, select, option, value, onChange, className }
import japgolly.scalajs.react.vdom.svg.all._
import paths.high.Radar

import demo.colors._
import demo.animate._

    import org.scalajs.dom.{ document, window }

object radar {
  case class Pokemon(
    name: String,
    hp: Double,
    attack: Double,
    defense: Double,
    spAttack: Double,
    spDefense: Double,
    speed: Double
  )
  trait State
  case class Still(index: Int) extends State
  case class Moving(from: Int, to: Int, step: Double) extends State
  implicit object InterpolableState extends Interpolable[State] {
    def mix(a: State, b: State, t: Double) = if (t == 1) b else {
      val Still(i) = a
      val Still(j) = b
      Moving(i, j, t)
    }
  }

  class Backend($: BackendScope[List[Pokemon], State]) {
    def onSelect(e: ReactEventI) = $.animateState(Still(e.target.value.toInt))
  }

  private def interpolate(p1: Pokemon, p2: Pokemon, t: Double) = Pokemon(
    name = p2.name,
    hp = p1.hp + t * (p2.hp - p1.hp),
    attack = p1.attack + t * (p2.attack - p1.attack),
    defense = p1.defense + t * (p2.defense - p1.defense),
    spAttack = p1.spAttack + t * (p2.spAttack - p1.spAttack),
    spDefense = p1.spDefense + t * (p2.spDefense - p1.spDefense),
    speed = p1.speed + t * (p2.speed - p1.speed)
  )
  private val palette = mix(Color(130, 140, 210), Color(180, 205, 150))

  val RadarChart = ReactComponentB[List[Pokemon]]("Radar chart")
    .initialState(Still(0): State)
    .backend(new Backend(_))
    .render((pokemons, state, backend) => {
      val pokemon = state match {
        case Still(i) => pokemons(i)
        case Moving(i, j, t) => interpolate(pokemons(i), pokemons(j), t)
      }

      val radar = Radar[Pokemon](
        data = List(pokemon),
        accessor = Map(
          "hp" -> (_.hp),
          "attack" -> (_.attack),
          "defense" -> (_.defense),
          "speed" -> (_.speed),
          "sp_attack" -> (_.spAttack),
          "sp_defense" -> (_.spDefense)
        ),
        max = Some(100),
        center = (0, 0),
        r = 130
      )
      val polygons = radar.curves map { curve =>
        path(d := curve.polygon.path.print, fill := string(palette(curve.index)))
      }
      val rings = radar.rings map { ring =>
        path(d := ring.path.print, fill := "none", stroke := "gray")
      }
      val choices = pokemons.zipWithIndex map { case (p, i) =>
        option(value := i, p.name)
      }

      div(
        select(onChange ==> backend.onSelect _, choices),
        div(className := "pokemon-info",
          h4(pokemon.name),
          p("HP: ", span(className := "label label-info", pokemon.hp.toInt)),
          p("Attack: ", span(className := "label label-info", pokemon.attack.toInt)),
          p("Defense: ", span(className := "label label-info", pokemon.defense.toInt)),
          p("Speed: ", span(className := "label label-info", pokemon.speed.toInt)),
          p("Sp. Attack: ", span(className := "label label-info", pokemon.spAttack.toInt)),
          p("Sp. Defense: ", span(className := "label label-info", pokemon.spDefense.toInt))
        ),
        svg(width := 375, height := 370,
          g(transform := "translate(200, 200)", rings, polygons)
        )
      )
    })
    .build
}