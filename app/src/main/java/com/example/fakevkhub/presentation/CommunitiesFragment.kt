package com.example.fakevkhub.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.fakevkhub.R
import com.example.fakevkhub.databinding.FragmentCommunitiesBinding
import com.example.fakevkhub.domain.Community
import com.example.fakevkhub.presentation.adapters.FollowedCommunitiesAdapter
import com.example.fakevkhub.presentation.adapters.decorations.CommunityItemDecoration
import com.example.fakevkhub.presentation.adapters.delegates.FollowedCommunitiesDelegateAdapter
import com.example.fakevkhub.presentation.mappers.CommunityMapper
import com.example.fakevkhub.presentation.uimodels.CommunityUiModel
import com.example.fakevkhub.presentation.uimodels.Item
import com.google.android.material.snackbar.Snackbar

class CommunitiesFragment : Fragment() {
    private lateinit var adapter1: FollowedCommunitiesAdapter
    private lateinit var adapter2: FollowedCommunitiesAdapter
    private var _binding: FragmentCommunitiesBinding? = null
    private val binding: FragmentCommunitiesBinding
        get() = _binding ?: throw IllegalStateException("FragmentCommunitiesBinding is null")

    private val responseCommunities = mutableListOf<CommunityUiModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("COMMUNITIES_FRAGMENT", "fragment created")
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
        doServerResponse()
        adapter1.submitList(responseCommunities.toList())
        adapter2.submitList(responseCommunities.toList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupScrollableLists() {
        adapter1 = FollowedCommunitiesAdapter(
            listOf(FollowedCommunitiesDelegateAdapter(::changeItemLikeStatus1))
        )
        adapter2 = FollowedCommunitiesAdapter(
            listOf(FollowedCommunitiesDelegateAdapter(::changeItemLikeStatus2))
        )
        binding.recyclerViewFollowedCommunities.addItemDecoration(CommunityItemDecoration(24))
        binding.recyclerViewFollowedCommunities.adapter = adapter1
        binding.recyclerViewTopOfTheDay.adapter = adapter2

        initSwipeToDelete()
//        fixBlinking()
    }

    //first solution
    private fun fixBlinking() {
        binding.recyclerViewFollowedCommunities.adapter = null
        binding.recyclerViewTopOfTheDay.itemAnimator = null
    }

    private fun initSwipeToDelete() {
        val swipeToDelete = CommunitySwipeToDismiss { idForItemGoingToBeRemoved ->
            val old = adapter2.currentList.toMutableList()
            val item = old.removeAt(idForItemGoingToBeRemoved)
            adapter2.submitList(old.toList())
            suggestRestoringItem(item, idForItemGoingToBeRemoved)
        }

        ItemTouchHelper(swipeToDelete).attachToRecyclerView(binding.recyclerViewTopOfTheDay)
    }

    private fun suggestRestoringItem(item: Item, position: Int) {
        Snackbar.make(binding.recyclerViewTopOfTheDay, "You can restore item", Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                val old = adapter2.currentList.toMutableList()
                old.add(element = item, index = position)
                adapter2.submitList(old.toList())
                binding.recyclerViewTopOfTheDay.smoothScrollToPosition(position)
            }
            .show()
    }

    private fun changeItemLikeStatus1(item: CommunityUiModel) {
        val index = adapter1.currentList.toMutableList().indexOf(item)
        requestItemStateChanges1(index, item)
//        adapter1.notifyItemChanged(index)
    }

    private fun changeItemLikeStatus2(item: CommunityUiModel) {
        requestItemStateChanges2(item)
//        adapter2.notifyItemChanged(index)
    }

    private fun doServerResponse() {
        responseCommunities.clear()
        repeat(25) { index ->
            responseCommunities.add(
                CommunityMapper.mapDomainEntityToUiModel(
                    entity = Community(
                        pictureUrl = "",
                        id = index,
                        name = "name $index",
                        sphere = "some_sphere",
                        participants = index,
                        isFavorite = false
                    )
                )
            )
        }
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
        Log.d("TEST_CRASH", "searching for: $item")

        val index = adapter2.currentList.indexOf(item)

        Log.d("TEST_CRASH", "$index \n$item")

        val newItem =
            item.copy(
                drawableResId = if (item.drawableResId == R.drawable.star) R.drawable.no_star else R.drawable.star,
                isFavorite = !item.isFavorite
            )
        val old = adapter2.currentList.toMutableList()
        old.removeAt(index)
        old.add(index, newItem)
        Log.d("TEST_CRASH", "before submitting: $newItem")

        adapter2.submitList(old)
    }

    companion object {
        fun newInstance(): CommunitiesFragment {
            return CommunitiesFragment()
        }
    }
}