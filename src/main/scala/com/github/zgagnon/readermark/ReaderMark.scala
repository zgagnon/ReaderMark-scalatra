package com.github.zgagnon.readermark


import com.firebase.client.{ValueEventListener, DataSnapshot, Firebase}
import scala.concurrent.promise

class ReaderMark extends ReadermarkScalatraStack {

  private val firebaseURL = "https://readmark.firebaseio.com/"

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say
        <a href="hello-scalate">hello to Scalate</a>
        .
      </body>
    </html>
  }

  get("/next") {
//    val firebase = new Firebase(firebaseURL + "/feeds")
//    val prom = promise[DataSnapshot]()
//    firebase.addListenerForSingleValueEvent(new ValueEventListener() {
//      def onDataChange(p1: DataSnapshot) = prom.success(p1)
//
//      def onCancelled() = prom.failure(new InterruptedException)
//    })
//    prom.future.map()
  }

}
