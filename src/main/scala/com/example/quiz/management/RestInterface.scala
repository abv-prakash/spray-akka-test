package com.example.quiz.management

/**
  * Created by abhinavp on 8/9/16.
  */

import akka.actor._
import akka.util.Timeout
import spray.http.StatusCodes
import spray.routing._

import scala.concurrent.duration._
import scala.language.postfixOps


class RestInterface extends HttpServiceActor
  with RestApi {

  def receive = runRoute(routes)
}

trait RestApi extends HttpService with ActorLogging { actor: Actor =>

  implicit val timeout = Timeout(10 seconds)

  var quizzes = Vector[Quiz]()

  def routes: Route = pathPrefix("quizzes") {
    import Quiz._
    import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller

    pathEnd {
      post {
        entity(as[Quiz]) { quiz => requestContext:RequestContext =>
          val responder = createResponder(requestContext)
          createQuiz(quiz) match {
            case true => responder ! QuizCreated
            case _ => responder ! QuizAlreadyExists
          }
        }
      }
    } ~
      path(Segment) { id =>
        delete { requestContext:RequestContext =>
          val responder = createResponder(requestContext)
          deleteQuiz(id)
          responder ! QuizDeleted
        }
      }
  }

  private def createQuiz(quiz: Quiz): Boolean = {
    val doesNotExist = !quizzes.exists(_.id == quiz.id)
    if (doesNotExist) quizzes = quizzes :+ quiz
    println("Quiz created:"+quizzes)
    doesNotExist
  }

  private def deleteQuiz(id: String): Unit = {
    quizzes = quizzes.filterNot(_.id == id)
    println("Quiz deleted:"+id)
  }

  private def createResponder(requestContext:RequestContext) = {
    context.actorOf(Props(new Responder(requestContext)))
  }
}

class Responder(requestContext:RequestContext) extends Actor with ActorLogging {

  def receive = {

    case QuizCreated =>
      requestContext.complete(StatusCodes.Created)
      killYourself

    case QuizDeleted =>
      requestContext.complete(StatusCodes.OK)
      killYourself

    case QuizAlreadyExists =>
      requestContext.complete(StatusCodes.Conflict)
      killYourself

  }

  private def killYourself = self ! PoisonPill

}
