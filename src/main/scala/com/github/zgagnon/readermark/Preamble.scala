package com.github.zgagnon.readermark

import com.firebase.client.Firebase
import com.github.zgagnon.readermark.firebase.RichFirebase

object Preamble {
implicit def firebaseToRichFirebase(firebase:Firebase) = new RichFirebase(firebase)
}
