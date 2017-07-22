package scheduler

import javax.inject.{ Inject, Named }

import akka.actor.{ ActorRef, ActorSystem, Cancellable }
import play.api.Configuration

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class HelloScheduler @Inject()(val system: ActorSystem,
                               @Named("hello-actor") val schedulerActor: ActorRef,
                               configuration: Configuration)(implicit ec: ExecutionContext) {

  val frequency: Int =  configuration.get[Int]("schedulling.hello.frequency")
  val delay: Int =  configuration.get[Int]("schedulling.hello.initialDelay")
  system.scheduler.schedule(delay.microseconds, frequency.seconds, schedulerActor, "Fayi")
}
