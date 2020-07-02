package com.nikasov.winstarstest.di

import com.nikasov.winstarstest.data.room.Database
import android.content.Context
import androidx.room.Room
import com.nikasov.winstarstest.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(  @ApplicationContext app : Context
    ) = Room.databaseBuilder(
        app,
        Database::class.java,
        Constants.DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideTimeTrackingDAO(db : Database) = db.getTimeTrackingDAO()

    @Provides
    @Singleton
    fun provideProfileDAO(db : Database) = db.getProfileDAO()
}