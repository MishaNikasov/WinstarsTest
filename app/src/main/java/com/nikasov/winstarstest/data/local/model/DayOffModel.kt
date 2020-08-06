package com.nikasov.winstarstest.data.local.model

import java.util.*

data class DayOffModel (
    var dateRange: List<Date>,
    var state: DayOffState,
    var description: String,
    var isExpect: Boolean

)

enum class DayOffState(private val state : String) {
    SICKEN("Sicken"),
    REST("Rest"),
    BUSY("Busy"),
    STUDY("Study");

    override fun toString(): String {
        return state
    }

    fun getRawType(): String {
        return super.toString()
    }
}
