package com.nikasov.winstarstest.data.room.model.profile

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikasov.winstarstest.common.Constants

@Entity(tableName = Constants.PROFILE_TABLE_NAME)
data class Profile (
    var name : String
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}