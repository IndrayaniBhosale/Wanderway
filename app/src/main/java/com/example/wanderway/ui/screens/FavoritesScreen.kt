package com.example.wanderway.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wanderway.viewmodel.WanderwayViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController, viewModel: WanderwayViewModel) {
    val favorites = viewModel.getFavorites()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favorites") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (favorites.isEmpty()) {
            // Empty-state with icon
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = "Empty Favorites",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(72.dp)
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "No favorites added yet!",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(favorites) { dest ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { navController.navigate("details/${dest.id}") },
                        elevation = CardDefaults.cardElevation(6.dp),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Column(Modifier.padding(12.dp)) {
                            Text(dest.name, fontWeight = FontWeight.Bold)
                            Text(dest.country, color = MaterialTheme.colorScheme.secondary)
                        }
                    }
                }
            }
        }
    }
}
