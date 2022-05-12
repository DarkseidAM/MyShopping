package com.example.myshopping.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myshopping.databinding.ProductItemBinding
import com.example.myshopping.model.Product
import javax.inject.Inject

class ProductAdapter @Inject constructor() :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    var products: List<Product> = emptyList()
    var listener: OnInteractionListener? = null

    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            Glide.with(binding.root).load(product.image_url).into(binding.productIV)
            binding.productNameTV.text = product.name
            binding.productPriceTV.text = product.price
            binding.productRatingRB.rating = product.rating.toFloat()
            binding.productIV.setOnClickListener {
                listener?.onItemSelected(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(products[position])

    override fun getItemCount(): Int = products.size

    fun setOnInteractionListener(listener: OnInteractionListener) {
        this.listener = listener
    }

    interface OnInteractionListener {
        fun onItemSelected(product: Product)
    }
}