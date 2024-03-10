package com.futurecoder.eshopp.di

import com.futurecoder.eshopp.services.FirebaseAccountService
import com.futurecoder.eshopp.services.impls.FirebaseAccountServiceImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    fun providesFirebaseAccountService(auth: FirebaseAuth): FirebaseAccountService =
        FirebaseAccountServiceImpl(auth)
}