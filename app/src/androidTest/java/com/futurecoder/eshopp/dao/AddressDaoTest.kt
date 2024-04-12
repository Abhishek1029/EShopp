package com.futurecoder.eshopp.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.futurecoder.eshopp.data.Address
import com.futurecoder.eshopp.database.EShopDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class AddressDaoTest {
    private lateinit var eShoppDatabase: EShopDatabase
    private lateinit var dao: AddressDao

    @Before
    fun setUp() {
        eShoppDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            EShopDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = eShoppDatabase.getAddressDao()
    }

    @After
    fun tearDown(){
        eShoppDatabase.close()
    }

    @Test
    fun insertAddressInDatabase() = runBlockingTest {
        val address = Address("heeraganj","Pratapgarh","UP","India","202222")
        dao.insertUserAddress(address)

        val allAddress = dao.fetchUserAddresses()
        assertThat(allAddress).contains(address)

    }
}