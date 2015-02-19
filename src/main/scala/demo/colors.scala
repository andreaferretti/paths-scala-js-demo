package demo

object colors {
  case class Color(r: Double, g: Double, b: Double)

  def cut(x: Double) = x.floor min 255

  def multiply(factor: Double) = { c: Color =>
    Color(cut(factor * c.r), cut(factor * c.g), cut(factor * c.b))
  }

  def average(c1: Color, c2: Color) =
    Color(
      cut((c1.r + c2.r) / 2),
      cut((c1.g + c2.g) / 2),
      cut((c1.b + c2.b) / 2)
    )

  val lighten = multiply(1.2)
  val darken = multiply(0.8)

  def mix(c1: Color, c2: Color) = {
    val c3 = average(c1, c2)
    List(
      lighten(c1),
      c1,
      darken(c1),
      lighten(c3),
      c3,
      darken(c3),
      lighten(c2),
      c2,
      darken(c2)
    )
  }

  def string(c: Color) = s"rgb(${ c.r.floor },${ c.g.floor },${ c.b.floor })"
}