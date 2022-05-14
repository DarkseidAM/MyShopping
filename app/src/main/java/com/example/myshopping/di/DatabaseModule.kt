package com.example.myshopping.di

import android.content.Context
import androidx.room.Room
import com.example.myshopping.database.CartDao
import com.example.myshopping.database.CartDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.myshopping.repository.CartRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideCartDao(cartDatabase: CartDatabase): CartDao = cartDatabase.cartDao()

    @Singleton
    @Provides
    fun provideCartDatabase(@ApplicationContext context: Context): CartDatabase =
        Room.databaseBuilder(context, CartDatabase::class.java, "Cart").build()

    @Singleton
    @Provides
    fun provideCartRepository(cartDao: CartDao) = CartRepository(cartDao)
}