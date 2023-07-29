package com.example.fakevkhub.presentation

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Parcelable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.recyclerview.widget.RecyclerView
import com.example.fakevkhub.R
import com.example.fakevkhub.presentation.uimodels.CommunitiesHolder
import com.example.fakevkhub.presentation.uimodels.CommunityUiModel
import com.example.fakevkhub.presentation.uimodels.DetailedCommunityUiModel
import com.example.fakevkhub.presentation.uimodels.Item
import com.example.fakevkhub.presentation.uimodels.SectionInfo
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

fun RecyclerView.onRestoreState(state: Parcelable?) {
    state?.let {
        layoutManager?.onRestoreInstanceState(it)
    }
}

fun getRandomCommunities(context: Context): List<Item> {
    val items = mutableListOf<Item>()
    (1..25).forEach { index ->
        items.add(getRandomUserPost(index, context))
    }
    return items
}

fun getRandomFeed(context: Context): List<Item> {
    val items = mutableListOf<Item>()
    (1..25).forEach { index ->
        val item = when (index) {
            2, 5, 7, 10, 15, 20, 12, 25 -> getRandomHorizontalItems(rvIndex = index * 10, context)
            1, 4, 6, 9, 14, 19, 11, 24 -> getRandomTitle()
            else -> getRandomUserPost(-index, context)
        }
        items.add(item)
    }
    return items
}

fun getRandomTitle() = SectionInfo(name = sections.random())

fun getRandomHorizontalItems(rvIndex: Int, context: Context) = CommunitiesHolder(
    rvIndex,
    List(10) { index -> getRandomDetailedPost(rvIndex, index) }
)

fun getRandomUserPost(index: Int, context: Context) = CommunityUiModel(
    pictureUrl = logoImages.random().toString(),
    id = index,
    name = "Some name: $index",
    sphere = spheres.random(),
    participants = Math.abs(Random.nextInt()).toString(),
    drawableResId = R.drawable.no_star,
    isFavorite = false,
)

fun getRandomDetailedPost(rvIndex: Int, index: Int) = DetailedCommunityUiModel(
    id = rvIndex * 10000 + index ,
    backgroundImageUrl = colors.random().toString(),
    groupImageUrl = logoImages.random().toString(),
    name = "Some name: $index",
    sphere = spheres.random(),
    followers = Math.abs(Random.nextInt()).toString(),
    buttonDrawableResId = R.drawable.dadac,
    isFollowed = false,
)

private val logoImages = listOf(R.drawable.default_logo)
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
private val sections = listOf(
    "Similar to viewed",
    "Interesting events",
    "Education",
    "Science",
    "Popular",
    "For you"
)