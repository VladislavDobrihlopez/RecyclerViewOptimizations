package com.example.fakevkhub.presentation.community_screen.uimodels

import android.os.Parcelable

data class CommunitiesHolder(
    val id: Int,
    val communities: List<DetailedCommunityUiModel>,
    var state: Parcelable? = null
): Item