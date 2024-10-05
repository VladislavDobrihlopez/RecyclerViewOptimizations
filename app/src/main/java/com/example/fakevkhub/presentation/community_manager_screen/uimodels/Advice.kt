package com.example.fakevkhub.presentation.community_manager_screen.uimodels

data class Advice(
    override val id: Int,
    val text: String,
    val buttonText: String,
    val isApplied: Boolean = false
) : ListedItem