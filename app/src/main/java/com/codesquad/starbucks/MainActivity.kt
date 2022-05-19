package com.codesquad.starbucks

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.codesquad.starbucks.databinding.ActivityMainBinding
import com.codesquad.starbucks.room.FavoriteDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        setupNav()
    }



    private fun setupNav() {
        val navController = supportFragmentManager.findFragmentById(R.id.fragment_container)?.findNavController()

        navController?.let {
            binding.bottomNavigationView.setupWithNavController(it)
        }

        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.eventFragment -> hideBottomNav()
                R.id.whatNewFragment->hideBottomNav()
                R.id.productDetailFragment->hideBottomNav()
                R.id.categoryDetailFragment->hideBottomNav()
                else -> showBottomNav()
            }
        }

    }

    private fun showBottomNav() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomNavigationView.visibility = View.GONE
    }
}