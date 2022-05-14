package com.example.myshopping.database

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.myshopping.model.Product
import com.example.myshopping.model.ProductEntity

@Dao
interface CartDao {
    @Query("SELECT * FROM PRODUCT")
    suspend fun getAllProductsInCart(): List<Product>

    @Insert(onConflict = IGNORE)
    suspend fun addToCart(product: Product)

    @Delete
    suspend fun deleteFromCart(product: Product)

    @Query("DELETE FROM PRODUCT")
    suspend fun deleteAllProductsFromCart()
}