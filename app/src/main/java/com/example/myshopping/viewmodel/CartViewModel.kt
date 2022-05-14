package com.example.myshopping.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshopping.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.myshopping.repository.CartRepository
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) :
    ViewModel() {
    private val cartProducts = MutableLiveData<List<Product>?>()
    fun getCartProducts() = cartProducts
    private val cartScreenStates = MutableLiveData<ResponseStates?>()
    fun getCartScreenStates() = cartScreenStates

    init {
        getProductsFromCart()
    }

    fun addProductsToCart(product: Product) {
        viewModelScope.launch {
            repository.addProductToCart(product)
        }
    }

    fun removeProductsFromCart(product: Product) {
        viewModelScope.launch {
            repository.removeProductFromCart(product)
        }
    }

    fun removeAllProductsFromCart() {
        viewModelScope.launch {
            repository.deleteAllProductsFromCart()
        }
    }

    private fun getProductsFromCart() {
        cartScreenStates.postValue(Loading)
        viewModelScope.launch {
            val products = repository.getProductsFromCart()
            cartProducts.postValue(products)
            cartScreenStates.postValue(Success(ProductEntity(products)))
        }
    }

}