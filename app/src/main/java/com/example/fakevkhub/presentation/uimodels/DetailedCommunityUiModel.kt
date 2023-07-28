package com.example.fakevkhub.presentation.uimodels

data class DetailedCommunityUiModel(
    val id: Int,
    val backgroundImageUrl: String,
    val groupImageUrl: String,
    val name: String,
    val sphere: String,
    val followers: String,
    val buttonDrawableResId: Int,
    val isFollowed: Boolean
): Item