package com.futurecoder.eshopp.di

import com.futurecoder.eshopp.network.ApiInterface
import com.futurecoder.eshopp.services.GetProductUseCase
import com.futurecoder.eshopp.services.impls.GetProductUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesGetProductUseCase(apiInterface: ApiInterface): GetProductUseCase =
        GetProductUseCaseImpl(apiInterface)
}