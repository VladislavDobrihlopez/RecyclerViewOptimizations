package com.example.fakevkhub.presentation.uimodels

data class CommunityUiModel(
    val pictureUrl: String,
    val id: Int,
    val name: String,
    val sphere: String,
    val participants: String,
    val drawableResId: Int,
    val isFavorite: Boolean,
) : Item