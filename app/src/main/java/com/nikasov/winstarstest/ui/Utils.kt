package com.nikasov.winstarstest.ui

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun formatDate(date : Date, type : String) : String {
        val simpleDateFormat = SimpleDateFormat(type, Locale.getDefault())
        return simpleDateFormat.format(date)
    }

}