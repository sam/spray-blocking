package com.github.sam.spray.blocking

import akka.actor._

class BlockingServiceActor extends Actor with BlockingService {
  def actorRefFactory = context

  val waiter: ActorRef = context.actorOf(Props[Waiter], "waiter")

  def receive = runRoute(routes)
}

object Waiter {
  case class Wait(message: String)
  case class Set(waiter: ActorRef)
}

class Waiter extends Actor {

  import scala.concurrent.duration._

  implicit val ec = context.dispatcher

  def receive = {
    case Waiter.Wait(message) =>
      val sender = context.sender
      context.system.scheduler.scheduleOnce(5 seconds) {
        sender ! message
      }
  }
}