package com.codesquad.starbucks.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.starbucks.domain.HomeRepository
import com.codesquad.starbucks.room.FavoriteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(private val homeRepository: HomeRepository):ViewModel() {

    private val _favorites = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val favorites: StateFlow<List<FavoriteEntity>> = _favorites

     fun getFavorites(){
        viewModelScope.launch {
            homeRepository.getAllFavoriteItem().collect {
                _favorites.value=it
            }
        }
    }

    fun deleteFavorite(favoriteEntity: FavoriteEntity) {
        CoroutineScope(Dispatchers.IO).launch  {
            homeRepository.deleteFavoriteItem(favoriteEntity)
        }
    }

    init {
        getFavorites()
    }
}