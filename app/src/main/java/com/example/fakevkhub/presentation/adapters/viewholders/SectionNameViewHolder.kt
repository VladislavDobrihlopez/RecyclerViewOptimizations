package com.example.fakevkhub.presentation.adapters.viewholders

import com.example.fakevkhub.databinding.SectionBinding
import com.example.fakevkhub.presentation.uimodels.SectionInfo

class SectionNameViewHolder(private val binding: SectionBinding): BaseViewHolder<SectionBinding, SectionInfo>(binding) {
    override fun onBind(item: SectionInfo) {
        super.onBind(item)
        binding.textViewSectionName.text = item.name
    }
}