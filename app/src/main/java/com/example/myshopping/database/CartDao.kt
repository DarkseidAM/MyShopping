package com.example.myshopping.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myshopping.model.Product
import com.example.myshopping.model.ProductEntity

@Dao
interface CartDao {
    @Query("SELECT * FROM PRODUCT")
    fun getAllProductsInCart(): List<Product>

    @Insert
    fun addToCart(product: Product)
}