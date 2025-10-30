//package com.wesley.lab_week8.ui.route
//
//import android.icu.util.Currency
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material.icons.filled.AddCircle
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.datastore.preferences.protobuf.LazyStringList
//import androidx.navigation.NavDestination
//import androidx.navigation.NavDestination.Companion.hierarchy
//import androidx.navigation.NavGraph.Companion.findStartDestination
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//
//enum class AppView(
//    val title: String,
//    val icon: ImageVector? = null
//) {
//    AddMovie("Add Movie", Icons.Filled.AddCircle),
//    Login("Login"),
//    MovieDetail("Movie Detail"),
//    MovieList("Movie List", Icons.Filled.Home),
//    Register("Register")
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AppRoute() {
//    val navController = rememberNavController()
//
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//    val currentRoute = currentDestination?.route
//    val currentView = AppView.entries.find { it.name == currentRoute }
//
//    val bottomNavItem = listOf(
//        BottomNavItem(AppView.MovieList, "Movies"),
//        BottomNavItem(AppView.AddMovie, "Add")
//    )
//
//    Scaffold(
//        topBar = {
//            MyTopAppBar(
//                currentView = currentView,
//                canNavigateBack = navController.previousBackStackEntry != null,
//                navigateUp = { navController.navigateUp() }
//            )
//        },
//        bottomBar = {
//            MyBottomNavigationBar(
//                navController = navController,
//                currentDestination = currentDestination,
//                items = bottomNavItem
//            )
//        }
//    ) { innerPadding ->
//        NavHost(
//            modifier = Modifier.padding(innerPadding),
//            navController = navController,
//            startDestination = AppView.MovieList.name
//        ) {
//            composable(route = AppView.MovieList.name) {
//                MovieListView(navController = navController)
//            }
//
//            composable(route = AppView.MovieDetail.name + "/{title}") { backStackEntry ->
//                MovieDetailView(
//                    title = backStackEntry.arguments?.getString("title")!!
//                )
//            }
//
//            composable(route = AppView.AddMovie.name) {
//                AddMovieView()
//            }
//
//            composable(route = AppView.Register.name) {
//                RegisterView()
//            }
//
//            composable(route = AppView.Login.name) {
//                LoginView()
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyTopAppBar(
//    currentView: AppView?,
//    canNavigateBack: Boolean,
//    navigateUp: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    TopAppBar(
//        title = {
//            Text(text = currentView?.title ?: AppView.MovieList.title)
//        },
//        modifier = modifier,
//        navigationIcon = {
//            if (canNavigateBack) {
//                IconButton(onClick = navigateUp) {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                        contentDescription = "Back"
//                    )
//                }
//            }
//        }
//    )
//}
//
//@Composable
//fun MyBottomNavigationBar(
//    navController: NavHostController,
//    currentDestination: NavDestination?,
//    items: List<BottomNavItem>
//) {
//    if (items.any { it.view.name == currentDestination?.route }) {
//        NavigationBar {
//            items.forEach { item ->
//                NavigationBarItem(
//                    icon = { Icon(item.view.icon!!, contentDescription = item.label) },
//                    label = { Text(item.label) },
//                    selected = currentDestination?.hierarchy?.any { it.route == item.view.name } == true,
//                    onClick = {
//                        navController.navigate(item.view.name) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                )
//            }
//        }
//    }
//}
//
//data class BottomNavItem(
//    val view: AppView,
//    val label: String
//)