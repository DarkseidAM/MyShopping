package com.example.myshopping.model

sealed class ResponseStates

object Loading : ResponseStates()

object ServiceError : ResponseStates()

class Success(var response : ProductEntity?) : ResponseStates()
