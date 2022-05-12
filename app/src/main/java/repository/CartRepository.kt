package repository

import com.example.myshopping.database.CartDao
import com.example.myshopping.model.Product
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartDao: CartDao) {

    fun addProductToCart(product: Product) = cartDao.addToCart(product)

    fun getProductsFromCart() = cartDao.getAllProductsInCart()
}