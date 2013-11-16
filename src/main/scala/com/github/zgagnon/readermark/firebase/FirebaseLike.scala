package com.github.zgagnon.readermark.firebase

import com.firebase.client.ValueEventListener

trait FirebaseLike {
  def addValueEventListener(listener:ValueEventListener):ValueEventListener

}
