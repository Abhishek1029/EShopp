package com.futurecoder.eshopp.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.futurecoder.eshopp.data.Address
import com.futurecoder.eshopp.services.RoomDatabaseService
import com.futurecoder.eshopp.utils.ioDispatcher
import com.futurecoder.eshopp.utils.isValidPostalCode
import com.futurecoder.eshopp.utils.mainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "AddressViewModel"

@HiltViewModel
class AddressViewModel @Inject constructor(
    // TODO: need to deep dive on savedStateHandle
    private val savedStateHandle: SavedStateHandle,
    private val databaseService: RoomDatabaseService
) : EShoppViewModel() {

    val addressState = mutableStateOf(Address())

    init {
        getUserAddress()
    }

    init {
        val addressId = savedStateHandle.get<Long>("addressId")
        if (addressId != null && addressId != -1L) {
            getAddressWithId(addressId)
        }
    }

    private fun getAddressWithId(addressId: Long) {
        viewModelScope.launch(ioDispatcher) {
            // TODO: to replace this with kotlin.runCatching
            val address = databaseService.getAddressWithId(addressId)
            withContext(mainDispatcher) {
                addressState.value = address
            }
        }
    }

    private val addressMSF: MutableStateFlow<List<Address>> = MutableStateFlow(emptyList())
    val addressSF: StateFlow<List<Address>> = addressMSF.asStateFlow()

    val address: String
        get() = addressState.value.address

    private val city: String
        get() = addressState.value.city

    private val state: String
        get() = addressState.value.state

    private val country: String
        get() = addressState.value.country

    private val postalCode: String
        get() = addressState.value.postalCode

    fun onAddressChange(address: String) {
        addressState.value = addressState.value.copy(address = address)
    }

    fun onCityChange(city: String) {
        addressState.value = addressState.value.copy(city = city)
    }

    fun onStateChange(state: String) {
        addressState.value = addressState.value.copy(state = state)
    }

    fun onCountryChange(country: String) {
        addressState.value = addressState.value.copy(country = country)
    }

    fun onPostalCodeChange(postalCode: String) {
        addressState.value = addressState.value.copy(postalCode = postalCode)
    }

    fun addAddress() {
        Log.d(TAG, "Add Address called")
        if (address.isBlank()) {
            return
        }
        if (city.isBlank()) {
            return
        }
        if (state.isBlank()) {
            return
        }
        if (country.isBlank()) {
            return
        }
        if (!postalCode.isValidPostalCode()) {
            return
        }
        Log.d(TAG, "Add Address called Again")
        val address = Address(address, city, state, country, postalCode)
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                databaseService.insertUserAddress(address)
            }.onSuccess {
                Log.d(TAG, "Address Successfully Added")
            }.onFailure {
                Log.e(TAG, "Address Add Failed with Exception:- ${it.localizedMessage}")
            }
        }
    }

    private fun getUserAddress() {
        viewModelScope.launch(ioDispatcher) {
            kotlin.runCatching {
                databaseService.fetchUserAddresses()
            }.onSuccess {
                addressMSF.value = it
            }.onFailure {
                Log.e(TAG, "Fetching Address Failed with Exception:- ${it.localizedMessage}")
            }
        }
    }

    fun editAddress(addressId: Long, actualAddress: String) {

    }

    fun deleteAddress() {
        val addressId = savedStateHandle.get<Long>("addressId")
        viewModelScope.launch(ioDispatcher) {
            kotlin.runCatching {

                databaseService.deleteAddress(addressSF.value.first {
                    Log.d(TAG, "addressId1: ${it.id} addressId2:- $addressId")
                    it.id == addressId
                })
            }.onSuccess {
                addressMSF.value = addressSF.value.filter {
                    it.id != addressId
                }
            }.onFailure {
                Log.e(TAG, "Address Deletion Failed with Exception:- ${it.localizedMessage}")
            }
        }
    }
}