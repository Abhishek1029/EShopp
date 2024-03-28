package com.futurecoder.eshopp.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.futurecoder.eshopp.data.Product
import com.futurecoder.eshopp.services.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ProductDetailViewModel"

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    savedStateHandle: SavedStateHandle
) : EShoppViewModel() {
    init {
        val productId = savedStateHandle.get<Long>("productId")
        getProductById(productId ?: 0)
    }

    private val productMutableStateFlow: MutableStateFlow<Product?> = MutableStateFlow(null)
    val productStateFlow = productMutableStateFlow.asStateFlow()

    private fun getProductById(productId: Long) {
        viewModelScope.launch {
            kotlin.runCatching {
                getProductUseCase.getProductById(productId)
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    Log.d(TAG, "Product title is:- ${response.body()?.title}")
                    productMutableStateFlow.value = response.body()
                } else {
                    Log.e(
                        TAG,
                        "Fetching Products failed with exception:- ${
                            response.errorBody()?.string()
                        }"
                    )
                }

            }.onFailure {
                Log.e(
                    TAG,
                    "onFailure Fetching Product failed with exception :- ${it.localizedMessage}"
                )
            }
        }
    }
}