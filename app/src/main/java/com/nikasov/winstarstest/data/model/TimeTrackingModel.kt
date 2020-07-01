package com.nikasov.winstarstest.data.model

data class TimeTrackingModel(

    var type : TimeTrackingTypes


) {
    enum class TimeTrackingTypes (val type : String) {
        WORK("Work"),
        EDUCATION("Education"),
        RESEARCH("Research"),
        MEETING("Meeting"),
        TRAVEL_JOBS("Travel Jobs")
    }
}