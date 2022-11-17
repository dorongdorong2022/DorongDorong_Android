package kr.co.younhwan.a9oormthon.view.main.record.adapter

import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import kr.co.younhwan.a9oormthon.databinding.RecyclerRecordItemBinding

class RecordViewHolder(
    private val parent: ViewParent,
    binding: RecyclerRecordItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val recordText by lazy {
        binding.recordText
    }


    fun onBind(text: String) {
        recordText.text = text
    }
}