package com.example.myshopping.model

sealed class ApiResponseState

object Loading : ApiResponseState()

object ServiceError : ApiResponseState()

class Success(var apiResponse : ProductEntity?) : ApiResponseState()
