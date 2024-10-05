package com.example.fakevkhub.presentation.community_manager_screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.postDelayed
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.fakevkhub.databinding.FragmentCommunityCreateManagerBinding
import com.example.fakevkhub.presentation.community_manager_screen.delegates.AdapterDelegate
import com.example.fakevkhub.presentation.community_manager_screen.delegates.AdviceOptionItemAdapterDelegate
import com.example.fakevkhub.presentation.community_manager_screen.delegates.InputInfoAdapterDelegate
import com.example.fakevkhub.presentation.community_manager_screen.delegates.PiecesOfAdviceAdapterDelegate
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.Advice
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.InputAdvice
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.ListedItem
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.SomePiecesOfAdvice
import kotlin.concurrent.thread

class CommunityCreateManagerFragment : Fragment() {
    private var _binding: FragmentCommunityCreateManagerBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommunityCreateManagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var data = listOf(
            SomePiecesOfAdvice(
                -1000,
                items = listOf(
                    Advice(-10001, "123You should download Jeza app in play market", "Let's do it"),
                    Advice(-10002, "Are you satisfied?", "I guess so"),
                    Advice(-10003, "You should download Jeza app in play market", "Let's do it"),
                    Advice(-10004, "123You should download Jeza app in play market", "Let's do it"),
                    Advice(-10005, "Are you satisfied?", "I guess so"),
                    Advice(-10006, "You should download Jeza app in play market", "Let's do it"),
                    Advice(-10007, "You should download Jeza app in play market", "Let's do it"),
                    Advice(-10008, "You should download Jeza app in play market", "Let's do it"),
                    Advice(-10009, "You should download Jeza app in play market", "Let's do it"),
                ),
            ),
            Advice(1, "You should download Jeza app in play market", "Let's do it"),
            Advice(2, "Are you satisfied?", "I guess so"),
            Advice(3, "You should download Jeza app in play market", "Let's do it"),
            Advice(4, "Are you satisfied?", "I guess so"),
            InputAdvice(-1, "Write real info", "Oops"),
            Advice(5, "You should download Jeza app in play market", "Let's do it"),
            Advice(6, "Are you satisfied?", "I guess so"),
            Advice(100, "You should download Jeza app in play market", "Let's do it"),
            Advice(200, "Are you satisfied?", "I guess so"),
            Advice(300, "You should download Jeza app in play market", "Let's do it"),
            Advice(400, "Are you satisfied?", "I guess so"),
            InputAdvice(-2, "Write real info", "Oops"),
            Advice(500, "You should download Jeza app in play market", "Let's do it"),
            Advice(600, "Are you satisfied?", "I guess so"),
        )

        var adapter: CommunityManagerAdapter? = null

        val adviceDelegate1 = AdviceOptionItemAdapterDelegate(onButtonClick = { item ->
            data =
                data.map { if (it.id == item.id) item.copy(isApplied = !item.isApplied) else it }
            adapter?.submitList(data.toList())
        })

        val adviceDelegate2 = AdviceOptionItemAdapterDelegate(onButtonClick = { item ->
            var bunchIndex = 0
            val updatedInnerBunch =
                data.filterIsInstance(SomePiecesOfAdvice::class.java)
                    .flatMapIndexed { index, it ->
                        bunchIndex = index
                        it.items
                    }
                    .map { if (it.id == item.id) item.copy(isApplied = !item.isApplied) else it }
            val updatedData = data.toMutableList()
            updatedData[bunchIndex] =
                (updatedData[bunchIndex] as SomePiecesOfAdvice).copy(items = updatedInnerBunch)
            data = updatedData
            adapter?.submitList(data.toList())
        })

        adapter = CommunityManagerAdapter(
            listOf(
                adviceDelegate1,
                InputInfoAdapterDelegate(),
                PiecesOfAdviceAdapterDelegate(
                    listOf(adviceDelegate2) as List<AdapterDelegate<ViewBinding, ListedItem>>,
                    listOf(FullScaleDecorator())
                )
            ) as List<AdapterDelegate<ViewBinding, ListedItem>>
        )

        binding.recyclerViewOptions.adapter = adapter
        binding.recyclerViewOptions.layoutManager = LinearLayoutManager(requireContext())

        adapter.submitList(data.toList())

//        thread {
//            Handler(Looper.getMainLooper()).postDelayed(15500) {
//                data = buildList {
//                    add(InputAdvice(-3, "New", "Oops"))
//                    add(InputAdvice(-4, "New", "Oops"))
//                    add(
//                        Advice(
//                            30,
//                            "You should download Jeza app in play market",
//                            "Let's do it"
//                        )
//                    )
//                    add(Advice(40, "Are you satisfied?", "I guess so"))
//                }
//                adapter.submitList(data.toList())
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_NAME = "CommunityCreateManagerFragment"
        fun newInstance() = CommunityCreateManagerFragment()
    }
}