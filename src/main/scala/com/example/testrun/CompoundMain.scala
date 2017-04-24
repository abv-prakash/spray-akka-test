package com.example.testrun

/**
  * Created by abhinavp on 9/5/16.
  */
object CompoundMain extends App {

  case class Bird(val name :String) extends Object {
        def fly(height : Int) = {
          println(name + " :the bird, is flying now....")
        }
  }


  case class Plane(val callsign : String) extends Object {
    def fly(height : Int) = {
      println(callsign + " : the plane, is flying now....")
    }
  }


  def takeOff(runway: Int, r : { val callsign : String ; def fly(height : Int)}) = {
    println(r.callsign + "requests take off from the runway ->" + runway)
    println(r.callsign + "is clear for takeoff")
  }

  var parrot = new Bird("Parrot"){val callsign = name }
  var boeing777 = new Plane("Boeing777")
  takeOff(11, boeing777)
  takeOff(33, parrot)
}
