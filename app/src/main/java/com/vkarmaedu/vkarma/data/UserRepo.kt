package com.vkarmaedu.vkarma.data

object UserRepo {
    var name : String = "anonymous"
    var UId : String = "anonymous"
    var batch : String = "XII-A"
    var institute : String = "1"

    fun setCred(n : String, u : String){
        name = n
        UId = u
    }
}