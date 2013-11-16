package com.github.zgagnon.readermark

import com.firebase.client.{ValueEventListener, Firebase}
import com.github.zgagnon.readermark.firebase.{FirebaseLike, RichFirebase}

object Preamble {
  implicit def firebaseToRichFirebase(firebase: FirebaseLike) = new RichFirebase(firebase)
  implicit def firebaseToFireBaseLike(firebase: Firebase) = new Object with FirebaseLike {
    val fire = firebase

    def addValueEventListener(listener: ValueEventListener): ValueEventListener = fire.addValueEventListener(listener)
  }
}
