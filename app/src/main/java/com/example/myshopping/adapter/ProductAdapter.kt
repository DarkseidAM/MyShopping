package com.example.myshopping.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myshopping.R
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
            Glide.with(binding.root).load(product.image_url)
                .placeholder(R.color.shimmer_placeholder).into(binding.productIV)
            binding.productNameTV.text = product.name
            binding.productPriceTV.text = "₹${product.price}"
            binding.productRatingRB.rating = product.rating.toFloat()
            if (product.added) {
                buttonAdded(binding.productAddBtn)
            } else {
                buttonToAdd(binding.productAddBtn)
            }
            binding.productAddBtn.setOnClickListener {
                if (product.added) {
                    buttonToAdd(binding.productAddBtn)
                    listener?.onItemRemoved(product)
                } else {
                    buttonAdded(binding.productAddBtn)
                    listener?.onItemAdded(product)
                }
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

    private fun buttonToAdd(button: AppCompatButton) {
        button.background = AppCompatResources.getDrawable(button.context, R.drawable.btn_bg)
        button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_add_24, 0, 0, 0)
        button.text = "Add To Cart"
    }

    private fun buttonAdded(button: AppCompatButton) {
        button.background = AppCompatResources.getDrawable(button.context, R.drawable.btn_bg_shimmer)
        button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_done_24, 0, 0, 0)
        button.text = "Added To Cart"
    }

    interface OnInteractionListener {
        fun onItemAdded(product: Product)
        fun onItemRemoved(product: Product)
    }
}