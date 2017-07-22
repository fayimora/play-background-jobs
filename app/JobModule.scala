import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport
import scheduler.{ HelloScheduler, HelloJobActor }

class JobModule extends AbstractModule with AkkaGuiceSupport {
  override def configure(): Unit = {
    bindActor[HelloJobActor]("hello-actor")
    bind(classOf[HelloScheduler]).asEagerSingleton()
  }
}
