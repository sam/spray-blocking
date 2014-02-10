package com.github.sam.spray.blocking

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http

object Main extends App {

  implicit val system = ActorSystem()

  val service = system.actorOf(Props[BlockingServiceActor], "service")

  IO(Http) ! Http.Bind(service, "0.0.0.0", port = 9000)

}