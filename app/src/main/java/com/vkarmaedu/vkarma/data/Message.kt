package com.vkarmaedu.vkarma.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.Exclude

@Entity(tableName = "messageTable")
data class Message(val senderName : String, val text: String, @PrimaryKey val timeStamp: Long, val attachment : String?,
                   @get:Exclude val channelName : String) {
    constructor() : this( "", "", 0, null, "")
}
