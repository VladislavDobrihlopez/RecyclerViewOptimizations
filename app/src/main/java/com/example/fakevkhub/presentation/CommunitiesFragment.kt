package com.example.fakevkhub.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fakevkhub.R
import com.example.fakevkhub.databinding.FragmentCommunitiesBinding
import com.example.fakevkhub.domain.Community
import com.example.fakevkhub.presentation.adapters.FollowedCommunitiesAdapter
import com.example.fakevkhub.presentation.delegates.FollowedCommunitiesDelegateAdapter
import com.example.fakevkhub.presentation.mappers.CommunityMapper
import com.example.fakevkhub.presentation.uimodels.CommunityUiModel

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
        adapter1.items = responseCommunities.toMutableList()
        doServerResponse()
        adapter2.items = responseCommunities.toMutableList()
        super.onViewCreated(view, savedInstanceState)
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
        binding.recyclerViewFollowedCommunities.adapter = adapter1
        binding.recyclerViewTopOfTheDay.adapter = adapter2
    }

    private fun changeItemLikeStatus1(item: CommunityUiModel) {
        val index = adapter1.items.indexOf(item)
        requestItemStateChanges1(index, item)
        adapter1.notifyItemChanged(index)
    }

    private fun changeItemLikeStatus2(item: CommunityUiModel) {
        val index = adapter2.items.indexOf(item)
        requestItemStateChanges2(index, item)
        adapter2.notifyItemChanged(index)
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
            item.copy(drawableResId = if (item.drawableResId == R.drawable.star) R.drawable.no_star else R.drawable.star)
        adapter1.items.removeAt(index)
        adapter1.items.add(index, newItem)
    }

    private fun requestItemStateChanges2(index: Int, item: CommunityUiModel) {
        val newItem =
            item.copy(drawableResId = if (item.drawableResId == R.drawable.star) R.drawable.no_star else R.drawable.star)
        adapter2.items.removeAt(index)
        adapter2.items.add(index, newItem)
    }

    companion object {
        fun newInstance(): CommunitiesFragment {
            return CommunitiesFragment()
        }
    }
}