package com.github.zgagnon.readermark

import com.github.zgagnon.readermark.firebase.FirebaseLike
import com.firebase.client.DataSnapshot
import Preamble._
import com.sun.javaws.exceptions.InvalidArgumentException
import scala.concurrent.Await
import scala.concurrent.duration._


class Feeds private(firebase: FirebaseLike) {
  def next: String = {
    val future = firebase.onValueChange {
      _.getValue match {
        case a: String => a
        case b => throw new InvalidArgumentException(Array(b.toString))
      }
    }

    Await.result(future, 10 seconds)
  }

}

object Feeds {
  def apply()(implicit firebase: FirebaseLike) = new Feeds(firebase);
}

