package scheduler

import javax.inject.{ Inject, Singleton }

import akka.actor.Actor

import scala.concurrent.ExecutionContext

@Singleton
class HelloJobActor @Inject()()(implicit ec: ExecutionContext) extends Actor {
  override def receive: Receive = {
    case s: String =>
      play.api.Logger.info(s"Hello $s!!!!")
  }
}
