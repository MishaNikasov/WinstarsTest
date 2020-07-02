package com.nikasov.winstarstest.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nikasov.winstarstest.common.Constants
import com.nikasov.winstarstest.data.room.Converters
import com.nikasov.winstarstest.data.room.model.profile.Profile
import com.nikasov.winstarstest.data.room.model.profile.ProfileDAO
import com.nikasov.winstarstest.data.room.model.tracking.TimeTrackingDAO
import com.nikasov.winstarstest.data.room.model.tracking.TimeTrackingModel

@Database(entities = [TimeTrackingModel::class, Profile::class], version = Constants.DATABASE_VERSION, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun getTimeTrackingDAO() : TimeTrackingDAO
    abstract fun getProfileDAO() : ProfileDAO
}