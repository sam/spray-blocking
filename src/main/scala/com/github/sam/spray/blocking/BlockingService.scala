package com.github.sam.spray.blocking

import spray.routing.HttpService
import spray.http.StatusCodes._
import akka.actor._

trait BlockingService extends HttpService {

  implicit def executionContext = actorRefFactory.dispatcher

  import scala.concurrent.duration._
  import akka.pattern.ask
  implicit val timeout = akka.util.Timeout(10 seconds)

  val waiter: ActorRef

  val routes = path("") {
    get {
      complete {
        (waiter ? Waiter.Wait("Hello World!")).mapTo[String]
      }
    }
  }
}