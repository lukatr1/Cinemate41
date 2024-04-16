package com.example.movieappmad24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.movieappmad24.models.MoviesViewModel
import com.example.movieappmad24.navigation.Navigation

class MainActivity : ComponentActivity() {
    //private val favoriteMoviesViewModel: MoviesViewModel by viewModels() // Instantiate the ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation() // Pass the ViewModel to Navigation
        }
    }
}

