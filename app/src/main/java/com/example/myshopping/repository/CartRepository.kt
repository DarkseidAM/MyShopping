package com.example.myshopping.repository

import com.example.myshopping.database.CartDao
import com.example.myshopping.model.Product
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartDao: CartDao) {

    suspend fun addProductToCart(product: Product) = cartDao.addToCart(product)

    suspend fun removeProductFromCart(product: Product) = cartDao.deleteFromCart(product)

    suspend fun getProductsFromCart() = cartDao.getAllProductsInCart()

    suspend fun deleteAllProductsFromCart() = cartDao.deleteAllProductsFromCart()
}