Paths.scala.js Demo application
=========================

This is an application to demonstrate the use of [Paths.scala.js](https://github.com/andreaferretti/paths-scala-js) with [Scalajs-React](https://github.com/japgolly/scalajs-react) to create interactive SVG graphics. See a [live example](http://andreaferretti.github.io/paths-scala-js-demo/).

Running
-------

The development version can be obtained with

    sbt ~fastOptJS

and then opening `index-dev-html`. A production version is obtained with

    sbt fullOptJS

and then opening `index.html`.


Status
------

The demo shows the various features of Paths.scala.js, but it is mostly static.
It is possible to make much more complex charts with animations, as shown in the
[original Paths.js demo](http://andreaferretti.github.io/paths-js-demo) or the
[React Paths.js demo](https://github.com/andreaferretti/paths-js-react-demo)