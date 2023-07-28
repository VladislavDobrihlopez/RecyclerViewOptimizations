package com.example.fakevkhub.presentation.uimodels

import android.os.Parcelable

data class CommunitiesHolder(
    val id: Int,
    val communities: List<DetailedCommunityUiModel>,
    var state: Parcelable? = null
): Item