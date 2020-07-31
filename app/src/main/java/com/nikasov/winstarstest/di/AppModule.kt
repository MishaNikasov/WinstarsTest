package com.nikasov.winstarstest.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun providePrefs(@ApplicationContext context: Context) : Prefs =
        Prefs(context)


    @Provides
    @Singleton
    fun provideSignInOption(@ApplicationContext context: Context) : GoogleSignInClient {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.resources.getString(R.string.default_web_client_id))
            .requestEmail()
            .requestProfile()
            .build()
        return GoogleSignIn.getClient(context, options)
    }

    @Singleton
    @Provides
    fun provideFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()
}