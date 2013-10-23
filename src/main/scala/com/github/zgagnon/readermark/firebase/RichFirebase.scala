package com.github.zgagnon.readermark.firebase

import com.firebase.client.{ValueEventListener, DataSnapshot, Firebase}
import scala.concurrent.Promise
import scala.util.Try

class RichFirebase(firebase: Firebase){
  def onValueChange[T](callback: (DataSnapshot => T)) = {
    val prom = Promise[T]
    val listener = new ValueEventListener() {
      def onCancelled() {}

      def onDataChange(p1: DataSnapshot) {
        prom complete Try(callback(p1))
      }
    }

    firebase.addValueEventListener(listener)
    prom.future
  }
}
