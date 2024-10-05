package com.example.fakevkhub.presentation.community_screen.adapters.viewholders

import com.example.fakevkhub.databinding.SectionBinding
import com.example.fakevkhub.presentation.community_screen.uimodels.SectionInfo

class SectionNameViewHolder(private val binding: SectionBinding): BaseViewHolder<SectionBinding, SectionInfo>(binding) {
    override fun onBind(item: SectionInfo) {
        super.onBind(item)
        binding.textViewSectionName.text = item.name
    }
}