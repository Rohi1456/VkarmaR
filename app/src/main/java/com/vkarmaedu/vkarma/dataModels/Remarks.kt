package com.vkarmaedu.vkarma.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Remarks(val subName : String, val teachName : String, val content : String, val date : Long) {
    constructor() : this("", "", "", 0)
}