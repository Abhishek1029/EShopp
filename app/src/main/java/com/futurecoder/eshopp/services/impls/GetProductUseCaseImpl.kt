package com.futurecoder.eshopp.services.impls

import com.futurecoder.eshopp.data.Product
import com.futurecoder.eshopp.network.ApiInterface
import com.futurecoder.eshopp.services.GetProductUseCase
import retrofit2.Response
import javax.inject.Inject

class GetProductUseCaseImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : GetProductUseCase {
    override suspend fun getAllProducts(): Response<List<Product>> {
        return apiInterface.getAllProducts()
    }
}