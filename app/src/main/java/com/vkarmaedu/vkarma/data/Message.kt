package com.vkarmaedu.vkarma.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messageTable")
data class Message(val senderName : String, val text: String, @PrimaryKey val timeStamp: Long, val attachment : String?,
                   val channelName : String) {
    constructor() : this( "", "", 0, null, "")
}
