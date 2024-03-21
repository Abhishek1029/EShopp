package com.futurecoder.eshopp.services.impls

import com.futurecoder.eshopp.dao.AddressDao
import com.futurecoder.eshopp.data.Address
import com.futurecoder.eshopp.services.RoomDatabaseService
import javax.inject.Inject

class RoomDatabaseServiceImpl @Inject constructor(
    private val addressDao: AddressDao
) : RoomDatabaseService {
    override suspend fun insertUserAddress(address: Address) {
        addressDao.insertUserAddress(address)
    }

    override suspend fun fetchUserAddresses(): List<Address> {
        return addressDao.fetchUserAddresses()
    }

    override suspend fun deleteAddress(address: Address) {
        addressDao.deleteAddress(address)
    }
}