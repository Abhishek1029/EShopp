package com.futurecoder.eshopp.services

import com.futurecoder.eshopp.data.Address

interface RoomDatabaseService {
    suspend fun insertUserAddress(address: Address)
    suspend fun fetchUserAddresses(): List<Address>
}