package com.example.fakevkhub.domain

data class Community(
    val pictureUrl: String,
    val id: Int,
    val name: String,
    val sphere: String,
    val participants: Int,
    val isFavorite: Boolean
)