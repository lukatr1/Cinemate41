package com.example.movieappmad24.models

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MoviesViewModel : ViewModel() {




    private val _favoriteMovies = MutableStateFlow<Set<String>>(setOf())

    val favoriteMovies: List<Movie>  get() = _movies.filter { movie -> movie.isFavorite }


    private val _movies = getMovies().toMutableStateList()
    val movies: List<Movie>
        get() = _movies
    fun toggleFavorite(movieId: String) = _movies.find { it.id == movieId }?.apply {
        isFavorite = !isFavorite
    }
}