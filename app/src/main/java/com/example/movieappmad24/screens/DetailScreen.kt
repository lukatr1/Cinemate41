package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import com.example.movieappmad24.models.MoviesViewModel
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.widgets.HorizontalScrollableImageView
import com.example.movieappmad24.widgets.MovieRow
import com.example.movieappmad24.widgets.SimpleTopAppBar
import androidx.media3.ui.PlayerView


@Composable
fun DetailScreen(
    movieId: String?,
    moviesViewModel: MoviesViewModel,
    navController: NavController
) {

    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }
    DisposableEffect(key1 = context) {
        onDispose {
            player.release()
        }
    }


    movieId?.let {
        val movie = moviesViewModel.movies.filter { it.id == movieId }[0]

        Scaffold (
            topBar = {
                SimpleTopAppBar(title = movie.title) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }
            }

        ){ innerPadding ->

            Column {

                MovieRow(modifier = Modifier.padding(innerPadding),
                    movie = movie,
                    onFavoriteClick = {
                        movieId -> moviesViewModel.toggleFavorite(movieId) // MARK 1
                    })
                LaunchedEffect(key1 = movie.trailer) {
                    val resourceName = movie.trailer
                    val resourceId = context.resources.getIdentifier(resourceName, "raw", context.packageName)
                    val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/$resourceId")
                    player.setMediaItem(mediaItem)
                    player.prepare()
                    player.playWhenReady = true
                }

                AndroidView(
                    factory = { context ->
                        PlayerView(context).apply {
                            this.player = player
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                        .height(214.dp)
                )
                HorizontalScrollableImageView(movie = movie)
            }
        }
    }
}