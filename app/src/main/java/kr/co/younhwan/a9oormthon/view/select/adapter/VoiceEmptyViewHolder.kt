package kr.co.younhwan.a9oormthon.view.select.adapter

import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import kr.co.younhwan.a9oormthon.data.voiceItem
import kr.co.younhwan.a9oormthon.databinding.RecyclerEmptyVoiceItemBinding
import kr.co.younhwan.a9oormthon.databinding.RecyclerVoiceItemBinding

class VoiceEmptyViewHolder(
    private val parent: ViewParent,
    binding: RecyclerEmptyVoiceItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val btn by lazy {
        binding.addBtn
    }



    fun onBind(voiceItem: voiceItem) {
        btn.setOnClickListener {

        }
    }
}