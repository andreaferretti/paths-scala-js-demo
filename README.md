Paths.scala.js Demo application
=========================

This is an application to demonstrate the use of [Paths.scala.js](https://github.com/andreaferretti/paths-scala-js) with [Scalajs-React](https://github.com/japgolly/scalajs-react) to create interactive SVG graphics. See a [live example](http://andreaferretti.github.io/paths-scala-js-demo/).

Running
=======

The development version can be obtained with

    sbt ~fastOptJS

and then opening `index-dev-html`. A production version is obtained with

    sbt fullOptJS

and then opening `index.html`.