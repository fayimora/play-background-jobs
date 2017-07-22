package scheduler

import javax.inject.{ Inject, Named, Singleton }

import akka.actor.{ Actor, ActorRef, ActorSystem }
import play.api.Configuration

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._


@Singleton
class HelloJobActor @Inject()()(implicit ec: ExecutionContext) extends Actor {
  override def receive: Receive = {
    case s: String =>
      play.api.Logger.info(s"Hello $s!")
  }
}

class HelloScheduler @Inject()(val system: ActorSystem,
                               @Named("hello-actor") val schedulerActor: ActorRef,
                               configuration: Configuration)(implicit ec: ExecutionContext) {

  val frequency: Int =  configuration.get[Int]("schedulling.hello.frequency")
  val delay: Int =  configuration.get[Int]("schedulling.hello.initialDelay")
  system.scheduler.schedule(delay.microseconds, frequency.seconds, schedulerActor, "Fayi")
}
