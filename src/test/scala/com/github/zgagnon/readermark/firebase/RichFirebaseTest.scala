package com.github.zgagnon.readermark.firebase

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import com.firebase.client.{ValueEventListener, DataSnapshot, Firebase}
import scala.concurrent.{Await, Promise}
import scala.util.{Success, Random}
import org.specs2.mock.mockito.CapturedArgument
import org.mockito.ArgumentCaptor
import scala.concurrent.duration.Duration

class RichFirebaseTest extends Specification with Mockito {
  "The RichFirebase" should {
    "take a function for value change and set it as a ValueEventListener" in {
      val firebase = mock[Firebase]
      new RichFirebase(firebase).onValueChange({
        (p1: DataSnapshot) => p1
      })
      there was one(firebase).addValueEventListener(any[ValueEventListener])
    }

    "return a promise for the type of the callback" in {
      val firebase = mock[Firebase]
      val prom = new RichFirebase(firebase) onValueChange {
        (p1: DataSnapshot) => "test"
      }
      prom must beAnInstanceOf[Promise[String]]
    }

    "return a promise for the value of the callback" in {
      val firebase = mock[Firebase]
      val result = randomString(20)
      val prom = new RichFirebase(firebase) onValueChange {
        (p1: DataSnapshot) => result
      }

      val listener = capture[ValueEventListener]
      there was one(firebase).addValueEventListener(listener.capture)
      listener.value.onDataChange(mock[DataSnapshot])
      val callback = Await.ready(prom.future, Duration(10, "seconds"))
      callback.value match {
        case Some(Success(string)) => string must be(result)
        case None => ko
      }
    }
  }

  def randomString(length: Int) = Random.alphanumeric.take(length).mkString
}
