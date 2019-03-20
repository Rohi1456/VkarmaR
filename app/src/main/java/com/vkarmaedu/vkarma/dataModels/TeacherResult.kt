package com.vkarmaedu.vkarma.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties

public class TeacherResult(val id: String,val name: String,val marks:String, val total:String)
{
    constructor():this(id="",name = "",marks = "",total = "")
}