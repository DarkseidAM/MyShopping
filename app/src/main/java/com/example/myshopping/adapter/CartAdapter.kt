package com.example.myshopping.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myshopping.R
import com.example.myshopping.databinding.ProductItemBinding
import com.example.myshopping.model.Product
import javax.inject.Inject

class CartAdapter @Inject constructor() :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    var cartProducts: List<Product> = emptyList()

    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.productAddBtn.visibility = View.GONE
            Glide.with(binding.root).load(product.image_url)
                .placeholder(R.color.shimmer_placeholder).into(binding.productIV)
            binding.productNameTV.text = product.name
            binding.productPriceTV.text = "₹${product.price}"
            binding.productRatingRB.rating = product.rating.toFloat()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ProductItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(cartProducts[position])

    override fun getItemCount() = cartProducts.size
}