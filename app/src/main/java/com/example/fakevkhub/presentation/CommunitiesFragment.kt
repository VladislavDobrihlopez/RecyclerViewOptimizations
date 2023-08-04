package com.example.fakevkhub.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.fakevkhub.R
import com.example.fakevkhub.databinding.FragmentCommunitiesBinding
import com.example.fakevkhub.presentation.adapters.FollowedCommunitiesAdapter
import com.example.fakevkhub.presentation.adapters.decorations.CommunityFeedHorizontalItemDecoration
import com.example.fakevkhub.presentation.adapters.decorations.VerticalItemDecoration
import com.example.fakevkhub.presentation.adapters.delegates.DetailedCommunitiesDelegateAdapter
import com.example.fakevkhub.presentation.adapters.delegates.FollowedCommunitiesDelegateAdapter
import com.example.fakevkhub.presentation.adapters.delegates.HorizontalScrollDelegateAdapter
import com.example.fakevkhub.presentation.adapters.delegates.SectionNameDelegateAdapter
import com.example.fakevkhub.presentation.uimodels.CommunitiesHolder
import com.example.fakevkhub.presentation.uimodels.CommunityUiModel
import com.example.fakevkhub.presentation.uimodels.DetailedCommunityUiModel
import com.example.fakevkhub.presentation.uimodels.Item
import com.google.android.material.snackbar.Snackbar

class CommunitiesFragment : Fragment() {
    private lateinit var adapter1: FollowedCommunitiesAdapter
    private lateinit var adapter2: FollowedCommunitiesAdapter
    private var _binding: FragmentCommunitiesBinding? = null
    private val binding: FragmentCommunitiesBinding
        get() = _binding ?: throw IllegalStateException("FragmentCommunitiesBinding is null")

    private val myCommunities = mutableListOf<Item>()
    private val screenFeed = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenFeed.clear()
        myCommunities.clear()
        myCommunities.addAll(getRandomCommunities(requireContext()))
        screenFeed.addAll(getRandomFeed(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommunitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupScrollableLists()
        adapter1.submitList(myCommunities.toList())
        adapter2.submitList(screenFeed.toList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupScrollableLists() {
        val sharedPool = RecyclerView.RecycledViewPool()
        sharedPool.setMaxRecycledViews(R.layout.community_item, 7)
        sharedPool.setMaxRecycledViews(R.layout.communities_detailed, 3)
        sharedPool.setMaxRecycledViews(R.layout.detailed_community_item, 10)

        adapter1 = FollowedCommunitiesAdapter(
            listOf(FollowedCommunitiesDelegateAdapter(::changeItemLikeStatus1, 730))
        )

        adapter1.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        adapter2 = FollowedCommunitiesAdapter(
            listOf(
                FollowedCommunitiesDelegateAdapter(::changeItemLikeStatus2),
                HorizontalScrollDelegateAdapter(
                    listOf(
                        DetailedCommunitiesDelegateAdapter(
                            ::onFollowed,
                            900
                        )
                    )
                ),
                SectionNameDelegateAdapter()
            )
        )
        adapter2.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        binding.recyclerViewFollowedCommunities.setRecycledViewPool(sharedPool)
        binding.recyclerViewRecommendationFeed.setRecycledViewPool(sharedPool)
        binding.recyclerViewFollowedCommunities.apply {
            addItemDecoration(CommunityFeedHorizontalItemDecoration(36))
            addItemDecoration(VerticalItemDecoration(24, 36))
        }
        binding.recyclerViewRecommendationFeed.apply {
            addItemDecoration(
                CommunityFeedHorizontalItemDecoration(
                    72,
                    listOf(R.layout.communities_detailed)
                )
            )
            addItemDecoration(VerticalItemDecoration(24, 12))
        }
        binding.recyclerViewFollowedCommunities.adapter = adapter1
        binding.recyclerViewRecommendationFeed.adapter = adapter2

        initSwipeToDelete()
        //fixBlinking()
    }

    //first solution
    private fun fixBlinking() {
        binding.recyclerViewFollowedCommunities.adapter = null
        binding.recyclerViewRecommendationFeed.itemAnimator = null
    }

    private fun initSwipeToDelete() {
        val swipeToDelete = CommunitySwipeToDismiss { idForItemGoingToBeRemoved ->
            val item = screenFeed[idForItemGoingToBeRemoved]
            screenFeed.removeAt(idForItemGoingToBeRemoved)
            adapter2.submitList(screenFeed.toList())
            suggestRestoringItem(item, idForItemGoingToBeRemoved)
        }

        ItemTouchHelper(swipeToDelete).attachToRecyclerView(binding.recyclerViewRecommendationFeed)
    }

    private fun suggestRestoringItem(item: Item, position: Int) {
        Snackbar.make(binding.recyclerViewRecommendationFeed, "You can restore item", Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                screenFeed.add(position, item)
                adapter2.submitList(screenFeed.toList())
            }
            .show()
    }

    private fun changeItemLikeStatus1(item: CommunityUiModel) {
        val index = adapter1.currentList.toMutableList().indexOf(item)
        requestItemStateChanges1(index, item)
    }

    private fun changeItemLikeStatus2(item: CommunityUiModel) {
        requestItemStateChanges2(item)
    }

    private fun onFollowed(item: DetailedCommunityUiModel) {
        val holder = screenFeed
            .filterIsInstance<CommunitiesHolder>()
            .find { it.communities.contains(item) }
            ?: throw IllegalStateException("Internal error")

        val communityPosition = holder.communities.indexOf(item)
        val newCommunity = item.copy(
            isFollowed = !item.isFollowed,
            buttonDrawableResId = if (item.isFollowed) {
                R.drawable.dadac
            } else {
                R.drawable.galka
            }
        )
        val communities = holder.communities.toMutableList()
        communities[communityPosition] = newCommunity
        val newHolder = holder.copy(communities = communities)
        screenFeed[screenFeed.indexOf(holder)] = newHolder
        adapter2.submitList(screenFeed.toList())
    }

    private fun requestItemStateChanges1(index: Int, item: CommunityUiModel) {
        val newItem =
            item.copy(
                drawableResId = if (item.drawableResId == R.drawable.star) R.drawable.no_star else R.drawable.star,
                isFavorite = !item.isFavorite
            )
        val old = adapter1.currentList.toMutableList()
        old.removeAt(index)
        old.add(index, newItem)
        adapter1.submitList(old)
    }

    private fun requestItemStateChanges2(item: CommunityUiModel) {
        val index = screenFeed.indexOf(item)

        val newItem =
            item.copy(
                drawableResId = if (item.drawableResId == R.drawable.star) R.drawable.no_star else R.drawable.star,
                isFavorite = !item.isFavorite
            )

        screenFeed.removeAt(index)
        screenFeed.add(index, newItem)

        adapter2.submitList(screenFeed.toList())
    }

    companion object {
        fun newInstance(): CommunitiesFragment {
            return CommunitiesFragment()
        }
    }
}