package com.wesley.lab_week8.ui.route

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wesley.lab_week8.ui.view.AlbumDetailView
import com.wesley.lab_week8.ui.view.ErrorView
import com.wesley.lab_week8.ui.view.HomeView
import com.wesley.lab_week8.ui.view.LoadingView
import com.wesley.lab_week8.ui.viewmodel.ArtistViewModel


enum class AppView(
    val title: String
) {
    HomeView("Home"),
    AlbumDetailView("Album Detail"),
    LoadingView("Loading..."),
    ErrorView("Error")
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppRoute() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route
    val artistViewModel: ArtistViewModel = viewModel()
    val baseRoute = currentRoute?.substringBefore('/')
    val currentView = baseRoute?.let { name -> AppView.entries.find { it.name == name } }

    Scaffold(
        topBar = {
            MyTopAppBar(
                currentView = currentView,
                viewModel = artistViewModel
            )
        },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding()),
            navController = navController,
            startDestination = AppView.HomeView.name
        ) {
            composable(route = AppView.HomeView.name) {
                HomeView(navController = navController, viewModel = artistViewModel)
            }

            composable(route = AppView.AlbumDetailView.name + "/{id}") { backStackEntry ->
                val albumId = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
                AlbumDetailView(albumId = albumId,
                    viewModel = artistViewModel)
            }

            composable(route = AppView.LoadingView.name) {
                LoadingView()
            }

            composable(route = AppView.ErrorView.name) {
                ErrorView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    currentView: AppView?,
    viewModel: ArtistViewModel
) {
    val artist by viewModel.artist.collectAsState()
    val detailAlbum by viewModel.detailAlbum.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val title = when {
        isLoading -> AppView.LoadingView.title
        artist.isError || detailAlbum.isError -> AppView.ErrorView.title
        else -> when (currentView) {
            AppView.HomeView -> artist.nameArtist
            AppView.AlbumDetailView -> detailAlbum.nameAlbum
            AppView.ErrorView -> AppView.ErrorView.title
            else -> ""
        }
    }

    CenterAlignedTopAppBar(
        title = { Text(text = title, color = Color(0xFFaeaa9e)) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF1c2021)
        )
    )
}