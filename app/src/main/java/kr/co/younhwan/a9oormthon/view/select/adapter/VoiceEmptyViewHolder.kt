package kr.co.younhwan.a9oormthon.view.select.adapter

import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import kr.co.younhwan.a9oormthon.data.voiceItem
import kr.co.younhwan.a9oormthon.databinding.RecyclerEmptyVoiceItemBinding
import kr.co.younhwan.a9oormthon.databinding.RecyclerVoiceItemBinding

class VoiceEmptyViewHolder(
    private val parent: ViewParent,
    binding: RecyclerEmptyVoiceItemBinding,
    private val listenerFuncOfBtn: ((Unit) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    private val btn by lazy {
        binding.addBtn
    }

    fun onBind() {
        btn.setOnClickListener {
            listenerFuncOfBtn?.invoke(Unit)
        }

        itemView.setOnClickListener {
            listenerFuncOfBtn?.invoke(Unit)
        }
    }
}