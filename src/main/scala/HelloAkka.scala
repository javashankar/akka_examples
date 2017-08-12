import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

class HelloAkka extends Actor {

  
  override def preStart() {
    
  }
  
  def receive = {
    case msg: String => callChild(msg)
    case _           => println("unknown")
  }

  def callChild(msg: String) {
    context.actorOf(Props[ChildActor], "childActor") ! msg 
  }
}

class ChildActor extends Actor {
  
  def receive = {
    case msg: String => println("Inside child Actor : " + msg)
    case _           => println("unknown")
  }

}