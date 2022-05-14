package com.example.myshopping.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myshopping.adapter.ProductAdapter
import com.example.myshopping.databinding.FragmentProductBinding
import com.example.myshopping.model.Loading
import com.example.myshopping.model.Product
import com.example.myshopping.model.ServiceError
import com.example.myshopping.model.Success
import com.example.myshopping.viewmodel.CartViewModel
import com.example.myshopping.viewmodel.ProductViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding
    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    @Inject
    lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        handleClickEvents()
    }

    private fun handleClickEvents() {
        productAdapter.setOnInteractionListener(object : ProductAdapter.OnInteractionListener {
            override fun onItemAdded(product: Product) {
                product.added = true
                cartViewModel.addProductsToCart(product)
            }

            override fun onItemRemoved(product: Product) {
                product.added = false
                cartViewModel.removeProductsFromCart(product)
            }
        })
    }

    private fun setUpObservers() {
        productViewModel.getProductScreenStates().observe(viewLifecycleOwner) { state ->
            when (state) {
                is Loading -> {
                    binding.shimmerLayout.startShimmer()
                }
                is Success -> {
                    binding.shimmerLayout.visibility = View.GONE
                    cartViewModel.getCartProducts().observe(viewLifecycleOwner) { list ->
                        val productsList = state.response?.products?.toMutableList()
                        if (list != null) {
                            for (product in list) {
                                productsList?.find { it.name == product.name }?.added = true
                            }
                        }
                        productAdapter.products = productsList ?: emptyList()
                        binding.productRV.adapter = productAdapter
                        binding.productRV.visibility = View.VISIBLE
                    }
                }
                is ServiceError -> {
                    binding.shimmerLayout.visibility = View.GONE
                    Snackbar.make(
                        binding.root,
                        "There is some problem with your network",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }
}