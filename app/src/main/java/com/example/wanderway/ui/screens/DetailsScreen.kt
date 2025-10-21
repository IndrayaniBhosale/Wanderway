package com.example.wanderway.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wanderway.viewmodel.WanderwayViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: WanderwayViewModel,
    id: Int
) {
    val destination = viewModel.getDestinationById(id) ?: return

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(destination.name) },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text("Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(destination.imageRes),
                contentDescription = destination.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Column(Modifier.padding(16.dp)) {
                Text(
                    text = destination.country,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = destination.description,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(Modifier.height(16.dp))
                Text("Top Attractions", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))

                destination.attractions.forEach {
                    Text("• $it", style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(Modifier.height(24.dp))

                val isFav = destination.isFavorite
                val favColor by animateColorAsState(
                    targetValue = if (isFav) Color.Red else Color.Gray,
                    animationSpec = spring()
                )

                Button(
                    onClick = { viewModel.toggleFavorite(destination.id) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = if (isFav) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = favColor
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        if (isFav) "Remove from Favorites" else "Add to Favorites"
                    )
                }

                Spacer(Modifier.height(40.dp))
            }
        }
    }
}
