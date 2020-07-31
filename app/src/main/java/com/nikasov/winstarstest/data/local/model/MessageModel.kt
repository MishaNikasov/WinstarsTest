package com.nikasov.winstarstest.data.local.model

import java.util.*

data class MessageModel (
    var title : String,
    var descriptor: String,
    var date : Date,
    var count : Int
)