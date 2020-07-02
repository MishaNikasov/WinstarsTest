package com.nikasov.winstarstest.data.room.model.tracking

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface TimeTrackingDAO {
    @Insert
    suspend fun insertTimeTracking(trackingModel: TimeTrackingModel)
    @Query("SELECT * FROM TIME_TRACKING_TABLE WHERE date = :date ORDER BY date")
    suspend fun getTrackingByDate(date : Date) : List<TimeTrackingModel>
}