package com.example.fakevkhub.presentation.community_manager_screen.uimodels

import android.os.Parcelable

data class SomePiecesOfAdvice(
    override val id: Int,
    val items: List<Advice>,
    var state: Parcelable? = null
) : ListedItem