package com.codesquad.starbucks.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.codesquad.starbucks.R
import com.codesquad.starbucks.databinding.FragmentFavoriteBinding
import com.codesquad.starbucks.room.FavoriteEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favoriteAdapter = FavoriteAdapter { favoriteItem ->
            deleteFavorite(favoriteItem)
        }
        binding.rvFavorites.adapter = favoriteAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favorites.collect {
                favoriteAdapter.submitEvents(it)
            }
        }
    }

    private fun deleteFavorite(favoriteItem: FavoriteEntity) {
        viewModel.deleteFavorite(favoriteItem)
        viewModel.getFavorites()
    }
}