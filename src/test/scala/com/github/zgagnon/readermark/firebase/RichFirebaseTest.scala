package com.github.zgagnon.readermark.firebase

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import com.firebase.client.{ValueEventListener, DataSnapshot, Firebase}
import scala.concurrent.{Future, Await, Promise}
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import org.specs2.ScalaCheck

class RichFirebaseTest extends Specification with Mockito with ScalaCheck {
  "The RichFirebase" should {
    "take a function for value change and set it as a ValueEventListener" in {
      val firebase = mock[FirebaseLike]
      new RichFirebase(firebase).onValueChange({
        (p1: DataSnapshot) => p1
      })
      there was one(firebase).addValueEventListener(any[ValueEventListener])
    }

    "return a future for the type of the callback" in {
      val firebase = mock[FirebaseLike]
      val prom = new RichFirebase(firebase) onValueChange {
        (p1: DataSnapshot) => "test"
      }
      prom must beAnInstanceOf[Future[String]]
    }

    "return a future for the value of the callback" in {
      check {
        (result: String) =>
          val firebase = mock[FirebaseLike]
          val result = randomString(20)
          val future: Future[String] = new RichFirebase(firebase) onValueChange {
            (p1: DataSnapshot) => result
          }

          val listener = capture[ValueEventListener]
          there was one(firebase).addValueEventListener(listener.capture)
          listener.value.onDataChange(mock[DataSnapshot])
          val value: String = Await.result(future, 10 nanoseconds)
          value must be(result)
      }

    }
  }

  def randomString(length: Int) = Random.alphanumeric.take(length).mkString
}
