package com.example.myshopping.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshopping.model.ResponseStates
import com.example.myshopping.model.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import repository.ProductRepository
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {
    private val productScreenStates = MutableLiveData<ResponseStates?>()
    fun getProductScreenStates() = productScreenStates

    init {
        loadProducts()
    }

    private fun loadProducts() {
        productScreenStates.postValue(Loading)
        viewModelScope.launch {
            val state = repository.getProducts()
            productScreenStates.postValue(state)
        }
    }
}