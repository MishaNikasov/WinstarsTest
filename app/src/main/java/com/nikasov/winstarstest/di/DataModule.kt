package com.nikasov.winstarstest.di

import android.content.Context
import com.nikasov.winstarstest.data.local.ProfileRepository
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
}