package com.nikasov.winstarstest.data.local.repository

import android.content.Context
import com.nikasov.winstarstest.data.local.model.MessageModel
import java.util.*
import javax.inject.Inject

class MessageRepository @Inject constructor (
    private var application: Context
) {

    fun getAllMessages() : List<MessageModel> {
        val messageList = arrayListOf<MessageModel>()
        messageList.add(
                MessageModel(
                "Tasks",
                "Something very important needs to be done if the work is to be appreciated.",
                Calendar.getInstance().time,
                1
            )
        )
        messageList.add(
                MessageModel(
                "Job offer",
                "Something very important needs to be done if the work is to be appreciated.",
                Calendar.getInstance().time,
                1
            )
        )
        messageList.add(
                MessageModel(
                "Winstars Open",
                "Something very important needs to be done if the work is to be appreciated.",
                Calendar.getInstance().time,
                1
            )
        )
        return messageList
    }

    fun getAllClosedMessages() : List<MessageModel> {
        val messageList = arrayListOf<MessageModel>()
        messageList.add(
                MessageModel(
                "Tasks",
                "Something very important needs to be done if the work is to be appreciated.",
                Calendar.getInstance().time,
                -1,
                true
            )
        )
        messageList.add(
                MessageModel(
                "Job offer",
                "Something very important needs to be done if the work is to be appreciated.",
                Calendar.getInstance().time,
                -1,
                true
            )
        )
        messageList.add(
                MessageModel(
                "Winstars Open",
                "Something very important needs to be done if the work is to be appreciated.",
                Calendar.getInstance().time,
                -1,
                true
            )
        )
        messageList.add(
                MessageModel(
                "Winstars Open",
                "Something very important needs to be done if the work is to be appreciated.",
                Calendar.getInstance().time,
                -1,
                true
            )
        )
        return messageList
    }

}