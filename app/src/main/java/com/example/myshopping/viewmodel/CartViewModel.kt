package com.example.myshopping.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshopping.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import repository.CartRepository
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {

    fun addProductsToCart(product: Product) {
        viewModelScope.launch {
            repository.addProductToCart(product)
        }
    }

}