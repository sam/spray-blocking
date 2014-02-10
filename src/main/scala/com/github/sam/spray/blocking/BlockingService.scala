package com.github.sam.spray.blocking

import spray.routing.HttpService
import akka.actor._
import scala.concurrent.Future
import akka.routing.RoundRobinRouter

trait BlockingService extends HttpService {

  implicit def executionContext = actorRefFactory.dispatcher

  import scala.concurrent.duration._
  import akka.pattern.ask
  implicit val timeout = akka.util.Timeout(10 seconds)

  val waiter = actorRefFactory.actorOf(Props[Waiter])

  val routes = path("") {
    get {
      complete {
        (waiter ? Waiter.Wait("Hello World!")).mapTo[String]
      }
    }
  }
}