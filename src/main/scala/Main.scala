import akka.actor.ActorSystem
import akka.actor.Props


object Main {

  def main(args: Array[String]): Unit = {
    testInline(null)
  }

  type X = scala.beans.BeanDescription
  def testInline(msg: String) {
    require(msg ne null, "This is cool")
  }
  
  
}