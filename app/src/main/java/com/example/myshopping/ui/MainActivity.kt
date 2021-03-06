package com.example.myshopping.ui

import com.example.myshopping.interfaces.AppFlowListener
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.example.myshopping.R
import com.example.myshopping.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AppFlowListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.shopping_cart -> {
                onCartFragment()
                Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.action_productsFragment_to_cartFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onCartFragment() {
        supportActionBar?.show()
        binding.topAppBar.title = "Cart"
    }

    override fun onConfirmationFragment() {
        supportActionBar?.hide()
    }

    override fun onProductsFragment() {
        supportActionBar?.show()
        binding.topAppBar.title = "Gadgets"
    }
}