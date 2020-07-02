package com.nikasov.winstarstest.data.room.model.profile

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProfileDAO {
    @Insert
    suspend fun insertProfile (profile: Profile)
    @Query("SELECT * FROM PROFILE_TABLE")
    suspend fun getProfile() : Profile
    @Query("DELETE FROM PROFILE_TABLE")
    suspend fun deleteProfile()
}