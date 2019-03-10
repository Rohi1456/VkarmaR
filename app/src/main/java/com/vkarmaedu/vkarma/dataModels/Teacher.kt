package com.vkarmaedu.vkarma.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Teacher(val UId : String, val name : String, val batch : String, val age : Int,
                   val email : String, val address: String, val phoneNo : String, val subjects : Array<String>,
                   val classes : Array<ClassTiming>) {
    constructor() : this("","","",0,"","", "", emptyArray(), emptyArray())
}