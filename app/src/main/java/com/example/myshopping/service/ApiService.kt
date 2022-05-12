package com.example.myshopping.service

import com.example.myshopping.model.ProductEntity
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("nancymadan/assignment/db")
    suspend fun getProducts(): Response<ProductEntity>
}