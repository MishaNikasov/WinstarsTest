package com.nikasov.winstarstest.data.room.model.tracking

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikasov.winstarstest.common.Constants
import com.nikasov.winstarstest.data.room.model.Util
import java.util.*

@Entity(tableName = Constants.TIME_TRACKING_TABLE_NAME)
data class TimeTrackingModel(
    var date : Date,
    var type : String,
    var text : String,
    var time : Int
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}