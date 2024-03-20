package com.futurecoder.eshopp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.futurecoder.eshopp.data.Address

@Dao
interface AddressDao {
    @Insert
    fun insertUserAddress(address: Address)

    @Query("SELECT * FROM address")
    fun fetchUserAddresses(): List<Address>
}