package com.futurecoder.eshopp.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.futurecoder.eshopp.data.Address
import com.futurecoder.eshopp.database.EShopDatabase
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
class AddressDaoTestWithHilt {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var eShoppDatabase: EShopDatabase
    private lateinit var dao: AddressDao

    @Before
    fun setUp() {
        hiltRule.inject()
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