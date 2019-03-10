package com.vkarmaedu.vkarma.data

object UserRepo {
    var name : String = "anonymous"
    var uID : String = "anonymous"

    fun setCred(n : String, u : String){
        name = n
        uID = u
    }
}