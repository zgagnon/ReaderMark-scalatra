package com.github.zgagnon.readermark


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

  }

}
