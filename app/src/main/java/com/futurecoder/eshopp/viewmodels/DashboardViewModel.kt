package com.futurecoder.eshopp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.futurecoder.eshopp.data.Product
import com.futurecoder.eshopp.services.FirebaseAccountService
import com.futurecoder.eshopp.services.GetProductUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "DashboardViewModel"

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val firebaseAccountService: FirebaseAccountService,
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    init {
        fetchAllProducts()
    }

    private val productsMutableStateFlow = MutableStateFlow(emptyList<Product>())
    val productsStateFlow = productsMutableStateFlow.asStateFlow()

    fun isCurrentUser(): Boolean {
        return firebaseAccountService.hasUser
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAccountService.currentUser
    }

    private fun fetchAllProducts() {
        viewModelScope.launch {
            kotlin.runCatching {
                getProductUseCase.getAllProducts()
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    Log.d(TAG, "Total Product count is:- ${response.body()?.size}")
                    productsMutableStateFlow.value = response.body().orEmpty()
                } else {
                    Log.e(
                        TAG,
                        "Fetching Products failed with exception:- ${
                            response.errorBody()?.string()
                        }"
                    )
                }
            }.onFailure {
                Log.e(TAG, "Fetching Products failed with exception:- ${it.localizedMessage}")
            }
        }
    }
}