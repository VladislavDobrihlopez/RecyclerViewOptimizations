package com.example.fakevkhub.presentation

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.recyclerview.widget.RecyclerView
import com.example.fakevkhub.R
import com.example.fakevkhub.presentation.uimodels.CommunitiesHolder
import com.example.fakevkhub.presentation.uimodels.CommunityUiModel
import com.example.fakevkhub.presentation.uimodels.DetailedCommunityUiModel
import com.example.fakevkhub.presentation.uimodels.Item
import kotlin.random.Random

fun RecyclerView.Adapter<*>.isPreviousViewOfTheSameType(currentAdapterPosition: Int, viewType: Int): Boolean {
    val prevPosition = currentAdapterPosition - 1
    if (prevPosition <= 0) {
        return false
    }
    return getItemViewType(prevPosition) == viewType
}

fun RecyclerView.Adapter<*>.isNextViewOfTheSameType(currentAdapterPosition: Int, viewType: Int): Boolean {
    val nextPosition = currentAdapterPosition + 1
    if (nextPosition >= itemCount) {
        return false
    }
    return getItemViewType(nextPosition) == viewType
}

fun getRandomCommunities(context: Context): List<Item> {
    val items = mutableListOf<Item>()
    (1..30).forEach { index ->
        items.add(getRandomUserPost(index, context))
    }
    return items
}

fun getRandomFeed(context: Context): List<Item> {
    val items = mutableListOf<Item>()
    (1..25).forEach { index ->
        val item = when (index) {
            1, 6, 7, 10, 15, 20 -> getRandomHorizontalItems(rvIndex = index, context)
            else -> getRandomUserPost(index, context)
        }
        items.add(item)
    }
    return items
}

fun getRandomHorizontalItems(rvIndex: Int, context: Context) = CommunitiesHolder(
    rvIndex,
    List(10) { index -> getRandomDetailedPost(rvIndex, index) }
)

fun getRandomUserPost(index: Int, context: Context) = CommunityUiModel(
    pictureUrl = logoImages.random().toString(),
    id = index,
    name = "Some name: $index",
    sphere = spheres.random(),
    participants = Random.nextInt().toString(),
    drawableResId = R.drawable.no_star,
    isFavorite = false,
)

fun getRandomDetailedPost(rvIndex: Int, index: Int) = DetailedCommunityUiModel(
    id = rvIndex * 1000 + index ,
    backgroundImageUrl = colors.random().toString(),
    groupImageUrl = logoImages.random().toString(),
    name = "Some name: $index",
    sphere = spheres.random(),
    followers = Random.nextInt().toString(),
    buttonDrawableResId = R.drawable.dadac,
    isFollowed = false,
)

private fun getPostDescription(nickName: String, comment: String) =
    SpannableStringBuilder("$nickName: $comment").apply {
        setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            nickName.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
    }

private val logoImages = listOf(R.drawable.dog1, R.drawable.dog2, R.drawable.default_logo)
private val spheres = listOf(
    "It",
    "Art",
    "Music",
    "Politics",
    "Economics"
)
private val colors = listOf(
    Color.LTGRAY,
    Color.CYAN,
    Color.BLACK,
    Color.BLUE
)