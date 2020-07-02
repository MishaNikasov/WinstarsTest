package com.nikasov.winstarstest.data.room.model.tracking

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikasov.winstarstest.common.Constants
import java.util.*

@Entity(tableName = Constants.TIME_TRACKING_TABLE_NAME)
data class TimeTrackingModel(
    var date : Date,
    var type : TimeTrackingTypes,
    var text : String,
    var time : Int
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}