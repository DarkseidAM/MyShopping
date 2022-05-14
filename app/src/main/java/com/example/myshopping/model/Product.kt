package com.example.myshopping.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PRODUCT")
data class Product(
    val image_url: String,
    @PrimaryKey
    val name: String,
    val price: String,
    val rating: Int,
    var added: Boolean = false
)