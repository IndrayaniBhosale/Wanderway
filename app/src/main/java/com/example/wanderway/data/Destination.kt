package com.example.wanderway.data

import androidx.annotation.DrawableRes

data class Destination(
    val id: Int,
    val name: String,
    val country: String,
    val description: String,
    @DrawableRes val imageRes: Int,
    val attractions: List<String>,
    val isFavorite: Boolean = false
)
