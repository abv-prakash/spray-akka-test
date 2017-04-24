package com.example.quiz.management

import spray.json.DefaultJsonProtocol

/**
  * Created by abhinavp on 8/9/16.
  */

// entity: quiz
case class Quiz(id: String, question: String, correctAnswer: String)

// message: quiz has been created
case object QuizCreated

// message: quiz cannot be created because it already exists
case object QuizAlreadyExists

// message: quiz has been deleted
case object QuizDeleted



object Quiz extends DefaultJsonProtocol {
  implicit val format = jsonFormat3(Quiz.apply)
}