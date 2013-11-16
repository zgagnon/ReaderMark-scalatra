package com.github.zgagnon.readermark

import org.scalatra.ActionResult._
import org.scalatra.TemporaryRedirect
import com.github.zgagnon.readermark.firebase.FirebaseLike
import com.firebase.client.Firebase

class ReaderMark extends ReadermarkScalatraStack {

  private val firebaseURL = "https://readmark.firebaseio.com/"
  implicit val firebase = new Firebase(firebaseURL) with FirebaseLike
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
    val feeds = Feeds()
    TemporaryRedirect(feeds.next)
  }
}
