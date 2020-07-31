package com.nikasov.winstarstest.data.room.reposiitory

import com.nikasov.winstarstest.data.room.model.profile.Profile
import com.nikasov.winstarstest.data.room.model.profile.ProfileDAO
import com.nikasov.winstarstest.data.room.model.tracking.TimeTrackingDAO
import com.nikasov.winstarstest.data.room.model.tracking.TimeTrackingModel
import java.util.*
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val timeTrackingDAO: TimeTrackingDAO,
    private val profileDAO: ProfileDAO
) {
    suspend fun insertProfile (profile: Profile){
        profileDAO.insertProfile(profile)
    }
    suspend fun getProfile() : Profile {
        return profileDAO.getProfile()
    }
    suspend fun deleteProfile() {
        profileDAO.deleteProfile()
    }
    suspend fun insertTimeTracking(trackingModel: TimeTrackingModel){
        return timeTrackingDAO.insertTimeTracking(trackingModel)
    }
    suspend fun getTrackingByDate(date : Date) : List<TimeTrackingModel>{
        return timeTrackingDAO.getTrackingByDate(date)
    }
}