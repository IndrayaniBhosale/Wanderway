package com.example.wanderway.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.wanderway.R
import com.example.wanderway.data.Destination

class WanderwayViewModel : ViewModel() {

    private val _destinations = mutableStateListOf(
        Destination(
            1, "Paris", "France",
            "City of lights, art, and romance.",
            R.drawable.paris,
            listOf("Eiffel Tower", "Louvre Museum", "Montmartre")
        ),
        Destination(
            2, "Tokyo", "Japan",
            "A blend of tradition and technology.",
            R.drawable.tokyo,
            listOf("Senso-ji Temple", "Tokyo Tower", "Shibuya Crossing")
        ),
        Destination(
            3, "Bali", "Indonesia",
            "Tropical paradise of beaches, temples, and rice terraces.",
            R.drawable.bali,
            listOf("Ubud Forest", "Tanah Lot", "Nusa Penida")
        ),
        Destination(
            4, "New York", "USA",
            "The city that never sleeps — full of energy and life.",
            R.drawable.newyork,
            listOf("Central Park", "Times Square", "Statue of Liberty")
        )
    )
    val destinations: List<Destination> get() = _destinations

    fun getDestinationById(id: Int): Destination? =
        _destinations.find { it.id == id }

    fun toggleFavorite(id: Int) {
        val index = _destinations.indexOfFirst { it.id == id }
        if (index != -1) {
            val current = _destinations[index]
            _destinations[index] = current.copy(isFavorite = !current.isFavorite)
        }
    }

    fun getFavorites(): List<Destination> = _destinations.filter { it.isFavorite }
}
