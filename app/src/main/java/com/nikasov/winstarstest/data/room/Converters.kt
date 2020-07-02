package com.nikasov.winstarstest.data.room

import androidx.room.TypeConverter
import com.nikasov.winstarstest.data.room.model.tracking.TimeTrackingTypes
import java.util.*

class Converters  {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? =  date?.time

    @TypeConverter
    fun toTimeTracking(value: String) = TimeTrackingTypes.valueOf(value)

    @TypeConverter
    fun fromTimeTracking(value: TimeTrackingTypes) = value.type
}