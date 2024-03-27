package com.futurecoder.eshopp.network

import com.futurecoder.eshopp.data.Product
import com.futurecoder.eshopp.utils.EShoppApiConstants.PRODUCTS
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET(PRODUCTS)
    suspend fun getAllProducts(): Response<List<Product>>
}