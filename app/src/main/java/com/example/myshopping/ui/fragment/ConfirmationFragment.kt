package com.example.myshopping.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.myshopping.R
import com.example.myshopping.databinding.FragmentConfirmationBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.myshopping.interfaces.AppFlowListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConfirmationFragment : Fragment() {
    private lateinit var binding: FragmentConfirmationBinding
    private var listener: AppFlowListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? AppFlowListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfirmationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.confirmProgress
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            listener?.onProductsFragment()
            Navigation.findNavController(requireView())
                .navigate(R.id.action_confirmationFragment_to_productsFragment)
        }
        CoroutineScope(Dispatchers.Main).launch {
            delay(resources.getString(R.string.confirmation_timeout).toLong())
            binding.confirmProgress.visibility = View.GONE
            binding.confirmCL.visibility = View.VISIBLE
            binding.continueBtn.setOnClickListener {
                listener?.onProductsFragment()
                Navigation.findNavController(view)
                    .navigate(R.id.action_confirmationFragment_to_productsFragment)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        listener?.onConfirmationFragment()
    }
}