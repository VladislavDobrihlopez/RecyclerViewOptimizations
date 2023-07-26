package com.example.fakevkhub.presentation.mappers

import com.example.fakevkhub.R
import com.example.fakevkhub.domain.Community
import com.example.fakevkhub.presentation.uimodels.CommunityUiModel

object CommunityMapper {
    fun mapDomainEntityToUiModel(entity: Community): CommunityUiModel {
        return CommunityUiModel(
            pictureUrl = entity.pictureUrl,
            id = entity.id,
            name = entity.name,
            sphere = entity.sphere,
            participants = entity.participants.toString(),
            drawableResId = if (entity.isFavorite) R.drawable.star else R.drawable.no_star,
            isFavorite = entity.isFavorite
        )
    }
}