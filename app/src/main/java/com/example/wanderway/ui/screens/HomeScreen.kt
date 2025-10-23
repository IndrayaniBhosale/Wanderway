package com.example.wanderway.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wanderway.viewmodel.WanderwayViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: WanderwayViewModel) {
    var query by remember { mutableStateOf("") }

    val filtered = viewModel.destinations.filter {
        it.name.contains(query, ignoreCase = true) ||
                it.country.contains(query, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Wanderway") },
                actions = {
                    IconButton(onClick = { navController.navigate("favorites") }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favorites")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    label = { Text("Search Destinations") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )
            }

            if (filtered.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillParentMaxSize()
                            .padding(top = 40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No destinations found!", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            } else {
                items(filtered) { dest ->
                    DestinationCard(
                        name = dest.name,
                        country = dest.country,
                        imageRes = dest.imageRes
                    ) {
                        navController.navigate("details/${dest.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun DestinationCard(name: String, country: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(6.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Column(Modifier.padding(12.dp)) {
                Text(name, fontWeight = FontWeight.Bold)
                Text(country, color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}
