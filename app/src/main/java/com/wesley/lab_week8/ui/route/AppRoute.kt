package com.wesley.lab_week8.ui.route

import android.icu.util.Currency
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.preferences.protobuf.LazyStringList
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
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
    LoadingView("Loading"),
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
    val currentView = AppView.entries.find { it.name == currentRoute }

    Scaffold(
        topBar = {
            MyTopAppBar(
                currentView = currentView,
                viewModel = viewModel(modelClass = ArtistViewModel::class.java)
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
                HomeView(navController = navController)
            }

            composable(route = AppView.AlbumDetailView.name + "/{id}") { backStackEntry ->
                val albumId = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
                AlbumDetailView(albumId = albumId)
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

    val title = when (currentView) {
        AppView.HomeView -> artist.nameArtist.takeIf { it.isNotBlank() } ?: currentView.title
        AppView.AlbumDetailView -> detailAlbum.nameAlbum.takeIf { it.isNotBlank() } ?: currentView.title
        AppView.LoadingView -> "Loading..."
        AppView.ErrorView -> "Error"
        else -> "Page Not Found"
    }
    CenterAlignedTopAppBar(
        title = { Text(text = title, color = Color(0xFFaeaa9e)) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF1c2021)
        )
    )
}
//fun MyTopAppBar(
//    currentView: com.wesley.lab_week6.ui.route.Soal1.AppView?,
//    canNavigateBack: Boolean,
//    navigateUp: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    if(currentView == com.wesley.lab_week6.ui.route.Soal1.AppView.PandamartView){
//        CenterAlignedTopAppBar(
//            title = {
//                Text(text = currentView?.title ?: com.wesley.lab_week6.ui.route.Soal1.AppView.PandamartView.title)
//            },
//            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                containerColor = Color(0xFFBE438E),
//                titleContentColor = Color.White
//            ),
//            modifier = modifier,
//            navigationIcon = {
//                if (canNavigateBack) {
//                    IconButton(onClick = navigateUp) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBackIos,
//                            contentDescription = "Back",
//                            tint = Color.White
//                        )
//                    }
//                }
//            },
//            actions = {
//                if (canNavigateBack) {
//                    IconButton(onClick = navigateUp) {
//                        Icon(
//                            imageVector = Icons.Default.ShoppingCart,
//                            contentDescription = "Back",
//                            tint = Color.White
//                        )
//                    }
//                }
//            }
//        )
//    }
//}