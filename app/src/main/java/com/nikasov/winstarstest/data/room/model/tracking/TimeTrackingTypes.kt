package com.nikasov.winstarstest.data.room.model.tracking

enum class TimeTrackingTypes (private val type : String) {
    WORK("Work"),
    EDUCATION("Education"),
    RESEARCH("Research"),
    MEETING("Meeting"),
    TRAVEL_JOBS("Travel Jobs");

    override fun toString(): String {
        return type
    }

    fun getRawType(): String {
        return super.toString()
    }
}