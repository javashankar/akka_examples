package http.client

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling._
import akka.stream.ActorMaterializer

import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
import spray.json._
import DefaultJsonProtocol._
import java.io.IOException

object SimpleHttpClient {

  object UserJsonProtocol extends DefaultJsonProtocol {
    implicit val userFormat = jsonFormat4(User)
  }

  implicit val system = ActorSystem()
  implicit val dispatcher = system.dispatcher
  implicit val materializer = ActorMaterializer()

  def isValidContentType(response: HttpResponse) = response.entity.contentType == ContentTypes.`application/json`

  def simpleHttpRequest(request: HttpRequest): Future[HttpResponse] = Http().singleRequest(request)

  def main(args: Array[String]): Unit = {
    //val user: Future[User] = getUser()
    //println(user.onComplete { x => println(x) })
  }

  def getUser(userId: String = ""): Future[User] = {
    //val input = scala.io.Source.fromFile("test.json")("UTF-8").mkString.parseJson
    val httpRequest = HttpRequest(uri = "https://jsonplaceholder.typicode.com/posts/1")
    simpleHttpRequest(httpRequest).flatMap { response =>
      val nonBinaryType = ContentTypes.`application/json`
      def responseEntity: HttpEntity = response.entity
      response.status match {
        case StatusCodes.OK if (isValidContentType(response)) => unmarshall(response)
        //case StatusCodes.BadRequest => Future.successful(Left("I'm not really sure what to do here"))
        case _ => Unmarshal(response.entity).to[String].flatMap(error(response, _))
      }
    }
  }

  def unmarshall(response: HttpResponse) = {
    import UserJsonProtocol._
    val users = Unmarshal(response.entity).to[String].map(s => {s.parseJson.convertTo[User]})
    users
  }

  def error(response: HttpResponse, entity: String) = {
    val error = s"Simple HttpRequest has failed with status code :  ${response.status} and entity : $entity"
    Future.failed(new IOException(error))
  }
}

case class User(userId: Int, id: Int, title: String, body: String)

