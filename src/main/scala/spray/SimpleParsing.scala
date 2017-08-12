package spray

import spray.json._
import java.io.{ InputStream, FileNotFoundException }
import scala.util.Try

object SimpleParsing {

  object UserJsonProtocol extends DefaultJsonProtocol {
    implicit val userFormat = jsonFormat4(User)
  }

  def main(args: Array[String]): Unit = {
    import UserJsonProtocol._
    //val input = Try(readJson().convertTo[User]).recover { case e => throw new FileNotFoundException }
    val json = readJson()
    val input = json.convertTo[User]
    println(input)
  }

  private def readJson() = {
    val stream: InputStream = getClass.getResourceAsStream("/test.json")
    val json = scala.io.Source.fromInputStream(stream)("UTF-8").mkString.parseJson
    json
  }

}

case class User(userId: Long, id: Int, title: String, body: String)
