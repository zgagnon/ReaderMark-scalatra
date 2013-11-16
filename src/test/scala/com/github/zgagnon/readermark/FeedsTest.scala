package com.github.zgagnon.readermark

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import org.specs2.ScalaCheck
import com.firebase.client.{Query, DataSnapshot, ValueEventListener, Firebase}
import com.github.zgagnon.readermark.firebase.FirebaseLike

class FeedsTest extends Specification with Mockito with ScalaCheck {
  "The Feeds manager " should {
    "return a url on next" in {
      check {
        (url: String) =>
          (!url.isEmpty) ==> {
            implicit val fireBase = new MockFirebase(url)

            val feeds = Feeds()
            fireBase.trigger(url)
            feeds.next must_== url
          }
      }
    }

  ""
  }

}

class MockFirebase(val value:String) extends FirebaseLike with Mockito {
  var listeners = List[ValueEventListener]()

  def addValueEventListener(listener: ValueEventListener) = {
    listeners = listener :: listeners
    val data = mock[DataSnapshot]
    data.getValue() returns value
    listener.onDataChange(data)
    listener
  }

  def trigger(value: AnyRef) = listeners map {
    (l: ValueEventListener) =>
      val data = mock[DataSnapshot]
      data.getValue() returns value
      l.onDataChange(data)
  }
}