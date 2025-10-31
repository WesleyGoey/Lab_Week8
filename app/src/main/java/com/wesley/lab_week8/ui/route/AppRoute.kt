package com.wesley.lab_week8.ui.route

import android.icu.util.Currency
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.preferences.protobuf.LazyStringList
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
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = AppView.HomeView.name
        ) {
//            composable(route = AppView.MovieList.name) {
//                MovieListView(navController = navController)
//            }
//
//            composable(route = AppView.MovieDetail.name + "/{title}") { backStackEntry ->
//                MovieDetailView(
//                    title = backStackEntry.arguments?.getString("title")!!
//                )
//            }

            composable(route = AppView.HomeView.name) {
                HomeView()
            }

//            composable(route = AppView.AlbumDetailView.name + "/{id}") { backStackEntry ->
////                AlbumDetailView(route = AppView.AlbumDetailView.name + "/{id}")
//            }

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
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = currentView?.title ?: AppView.HomeView.title)
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}