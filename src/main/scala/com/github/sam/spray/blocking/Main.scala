package com.github.sam.spray.blocking

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.routing.RoundRobinRouter

object Main extends App {

  implicit val system = ActorSystem()

  val service = system.actorOf(Props[BlockingServiceActor].withRouter(RoundRobinRouter(5)), "api")

  IO(Http) ! Http.Bind(service, "0.0.0.0", port = 9000)

}