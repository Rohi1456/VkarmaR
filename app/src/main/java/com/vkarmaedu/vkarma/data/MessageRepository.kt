package com.vkarmaedu.vkarma.data

import androidx.annotation.WorkerThread

class MessageRepository(private val messageDao: MessageDao, channel :String){
    val allMessages = messageDao.getAllMessages(channel)

    @WorkerThread
    fun insert(message: Message){
        messageDao.insert(message)
    }
}