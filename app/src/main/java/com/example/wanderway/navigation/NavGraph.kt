package com.example.wanderway.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wanderway.ui.screens.*
import com.example.wanderway.viewmodel.WanderwayViewModel

@Composable
fun WanderwayNavGraph(navController: NavHostController, viewModel: WanderwayViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController, viewModel)
        }
        composable("details/{id}") { backStack ->
            val id = backStack.arguments?.getString("id")?.toIntOrNull() ?: 0
            DetailsScreen(navController, viewModel, id)
        }
        composable("favorites") {
            FavoritesScreen(navController, viewModel)
        }
    }
}
