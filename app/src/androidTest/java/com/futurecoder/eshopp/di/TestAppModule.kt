package com.futurecoder.eshopp.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.futurecoder.eshopp.database.EShopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    @Provides
    @Named("test_db")
    fun providesRoomDbInMemory(@ApplicationContext context: Context) = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            EShopDatabase::class.java
        ).allowMainThreadQueries().build()
}