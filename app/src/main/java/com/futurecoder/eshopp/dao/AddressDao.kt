package com.futurecoder.eshopp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.futurecoder.eshopp.data.Address

@Dao
interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserAddress(address: Address)

    @Query("SELECT * FROM address")
    fun fetchUserAddresses(): List<Address>

    @Query("SELECT * FROM address WHERE id = :addressId")
    fun getAddressWithId(addressId: Long): Address

    @Delete
    fun deleteAddress(address: Address)
}