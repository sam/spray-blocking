package com.github.sam.spray.blocking

import akka.actor._
import scala.concurrent.Future

class BlockingServiceActor extends Actor with BlockingService {

  def actorRefFactory = context

  def receive = runRoute(routes)
}

object Waiter {
  case class Wait(message: String)
  case class Set(waiter: ActorRef)
}

class Waiter extends Actor {

  import scala.concurrent.duration._
  import context._

  var count = 0

  def receive = {
    case Waiter.Wait(message) =>
      count += 1
      context.system.scheduler.scheduleOnce(5 seconds, sender, s"$count ${self}: $message")
  }
}