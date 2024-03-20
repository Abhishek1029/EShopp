package com.futurecoder.eshopp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.futurecoder.eshopp.dao.AddressDao
import com.futurecoder.eshopp.data.Address

@Database(entities = [Address::class], version = 1)
abstract class EShopDatabase : RoomDatabase() {
    abstract fun getAddressDao(): AddressDao
}