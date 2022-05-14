package com.example.myshopping.repository

import com.example.myshopping.OpenForTesting
import com.example.myshopping.model.ResponseStates
import com.example.myshopping.model.ProductEntity
import com.example.myshopping.model.ServiceError
import com.example.myshopping.model.Success
import com.example.myshopping.service.ApiService
import retrofit2.Response

@OpenForTesting
class ProductRepository(private val apiService: ApiService) {

    suspend fun getProducts(): ResponseStates {
        var response: Response<ProductEntity>? = null
        try {
            response = apiService.getProducts()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        return if (response != null && response.isSuccessful) {
            val apiResponse = response.body()
            Success(apiResponse)
        } else ServiceError
    }
}