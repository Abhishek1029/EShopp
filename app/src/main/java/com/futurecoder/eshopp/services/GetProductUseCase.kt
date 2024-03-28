package com.futurecoder.eshopp.services

import com.futurecoder.eshopp.data.Product
import retrofit2.Response

interface GetProductUseCase {
    suspend fun getAllProducts(): Response<List<Product>>
    suspend fun getProductById(productId: Long): Response<Product>
}