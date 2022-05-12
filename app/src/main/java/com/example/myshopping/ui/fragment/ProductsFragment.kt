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
import com.example.myshopping.model.ServiceError
import com.example.myshopping.model.Success
import com.example.myshopping.viewmodel.ProductViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding
    private val viewModel: ProductViewModel by viewModels()

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
    }

    private fun setUpObservers() {
        viewModel.getProductScreenStates().observe(viewLifecycleOwner) { state ->
            when (state) {
                is Loading -> {
                    binding.shimmerLayout.startShimmer()
                }
                is Success -> {
                    binding.shimmerLayout.visibility = View.GONE
                    productAdapter.products = state.apiResponse?.products ?: emptyList()
                    binding.productRV.adapter = productAdapter
                    binding.productRV.visibility = View.VISIBLE
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