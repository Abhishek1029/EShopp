package com.futurecoder.eshopp.di

import android.content.Context
import androidx.room.Room
import com.futurecoder.eshopp.dao.AddressDao
import com.futurecoder.eshopp.database.EShopDatabase
import com.futurecoder.eshopp.services.RoomDatabaseService
import com.futurecoder.eshopp.services.impls.RoomDatabaseServiceImpl
import com.futurecoder.eshopp.utils.CLConstants
import com.futurecoder.eshopp.utils.CLConstants.ESHOP_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesEShopDatabase(
        @ApplicationContext context: Context
    ): EShopDatabase = Room.databaseBuilder(
        context,
        EShopDatabase::class.java,
        ESHOP_DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun providesAddressDao(
        eShopDatabase: EShopDatabase
    ): AddressDao = eShopDatabase.getAddressDao()

    @Provides
    @Singleton
    fun providesRoomDatabaseService(
        addressDao: AddressDao
    ): RoomDatabaseService = RoomDatabaseServiceImpl(addressDao)
}