package com.nikasov.winstarstest.data.room.reposiitory

import com.nikasov.winstarstest.data.room.model.tracking.TimeTrackingDAO
import com.nikasov.winstarstest.data.room.model.tracking.TimeTrackingModel
import java.util.*
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val timeTrackingDAO: TimeTrackingDAO
) {
    suspend fun insertTimeTracking(trackingModel: TimeTrackingModel){
        return timeTrackingDAO.insertTimeTracking(trackingModel)
    }
    suspend fun getTrackingByDate(date : Date) : List<TimeTrackingModel>{
        return timeTrackingDAO.getTrackingByDate(date)
    }
}