package com.nikasov.winstarstest.di

import android.content.Context
import com.nikasov.winstarstest.data.local.repository.BonusesRepository
import com.nikasov.winstarstest.data.local.repository.FeedbackRepository
import com.nikasov.winstarstest.data.local.repository.MessageRepository
import com.nikasov.winstarstest.data.local.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDataRepository (@ApplicationContext context: Context) =
        ProfileRepository(context)

    @Provides
    @Singleton
    fun provideMessageRepository (@ApplicationContext context: Context) =
        MessageRepository(context)

    @Provides
    @Singleton
    fun provideFeedbackRepository (@ApplicationContext context: Context) =
        FeedbackRepository(context)

    @Provides
    @Singleton
    fun provideBonusesRepository (@ApplicationContext context: Context) =
        BonusesRepository(context)

}