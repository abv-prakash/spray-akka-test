package com.example.testrun

/**
  * Created by abhinavp on 10/5/16.
  */
object YieldTest extends App{

val testMap = scala.collection.mutable.Map[Int,String](1->"one" , 2->"two")
  println(testMap)
  println(for {
    x <- testMap
  } yield "Hello")
println(for (i  <- 1 to 5) yield i*2)
}
