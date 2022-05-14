package com.example.myshopping.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myshopping.adapter.CartAdapter
import com.example.myshopping.databinding.FragmentCartBinding
import com.example.myshopping.model.Loading
import com.example.myshopping.model.ServiceError
import com.example.myshopping.model.Success
import com.example.myshopping.viewmodel.CartViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private val cartViewModel: CartViewModel by viewModels()

    @Inject
    lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        handleClickActions()
    }

    private fun handleClickActions() {
        binding.checkoutBtn.setOnClickListener {  }
    }

    private fun setUpObservers() {
        cartViewModel.getCartScreenStates().observe(viewLifecycleOwner){state->
            when(state) {
                is Loading -> {
                    binding.shimmerLayout.startShimmer()
                }
                is Success -> {
                    binding.shimmerLayout.visibility = View.GONE
                    cartAdapter.cartProducts = state.response?.products ?: emptyList()
                    binding.cartRV.adapter = cartAdapter
                    binding.cartRV.visibility = View.VISIBLE
                    binding.checkoutBtn.visibility = View.VISIBLE
                }
                is ServiceError -> {
                    binding.shimmerLayout.visibility = View.GONE
                    Snackbar.make(
                        binding.root,
                        "There is some problem with this page",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
        cartViewModel.getCartProducts().observe(viewLifecycleOwner) {products->
            cartAdapter.cartProducts = products ?: emptyList()
            binding.cartRV.adapter = cartAdapter
        }
    }
}