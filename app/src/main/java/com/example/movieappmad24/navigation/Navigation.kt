package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.models.MoviesViewModel
import com.example.movieappmad24.screens.DetailScreen
import com.example.movieappmad24.screens.HomeScreen
import com.example.movieappmad24.screens.WatchlistScreen

@Composable
fun Navigation() {
    val navController = rememberNavController() // create a NavController instance

    // MARK 1
    //val favoriteMoviesViewModel: MoviesViewModel = viewModel() // create a MoviesViewModel instance
    val moviesViewModel: MoviesViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController, moviesViewModel = moviesViewModel)
        }

        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType})
        ) { backStackEntry ->
            DetailScreen(
                navController = navController,
                movieId = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY),
                moviesViewModel = moviesViewModel
            )
        }

        composable(route = Screen.WatchlistScreen.route) {
            WatchlistScreen(navController = navController, moviesViewModel = moviesViewModel)
        }
    }
}