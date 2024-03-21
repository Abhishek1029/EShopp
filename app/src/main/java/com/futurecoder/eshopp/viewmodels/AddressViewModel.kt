package com.futurecoder.eshopp.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.futurecoder.eshopp.data.Address
import com.futurecoder.eshopp.services.RoomDatabaseService
import com.futurecoder.eshopp.utils.ioDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AddressViewModel"

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val databaseService: RoomDatabaseService
) : EShoppViewModel() {
    init {
        getUserAddress()
    }

    private val addressMSF: MutableStateFlow<List<Address>> = MutableStateFlow(emptyList())
    val addressSF: StateFlow<List<Address>> = addressMSF.asStateFlow()

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

    fun deleteAddress(addressId: Long) {
        viewModelScope.launch(ioDispatcher) {
            kotlin.runCatching {
                databaseService.deleteAddress(addressSF.value.first {
                    it.id == addressId
                })
            }.onSuccess {
                Log.d(TAG, "Address Successfully Deleted")
            }.onFailure {
                Log.e(TAG, "Address Deletion Failed with Exception:- ${it.localizedMessage}")
            }
        }
    }
}