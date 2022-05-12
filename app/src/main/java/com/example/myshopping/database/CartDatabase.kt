package com.example.myshopping.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myshopping.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}