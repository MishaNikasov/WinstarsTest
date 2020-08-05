package com.nikasov.winstarstest.data.local.model

import java.util.*

data class BonusModel (
    var date: Date,
    var amount : Int,
    var description : String,
    var isExpect: Boolean
)