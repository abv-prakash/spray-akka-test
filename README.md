activator-akka-scala-seed
=========================

A minimal seed template for an Akka with Scala build

URLs :

http://127.0.0.1:9998/quizzes
Headers -
Content-Type application/json
Body-
{"id": "1", "question": "what is spray?", "correctAnswer": "spray is lightweight suite of scala libraries for client server side REST/HTTP support on top of Akka"}

– POST to URI /quizzes with JSON body: {"id": "my_quiz_id", "question": "my_question", "correctAnswer": "my_answer"}
– DELETE to URI /quizzes/my_quiz_id with no body 
