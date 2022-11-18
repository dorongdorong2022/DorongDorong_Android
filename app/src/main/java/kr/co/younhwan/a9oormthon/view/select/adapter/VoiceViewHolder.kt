package kr.co.younhwan.a9oormthon.view.select.adapter

import android.animation.Animator
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import kr.co.younhwan.a9oormthon.data.voiceItem
import kr.co.younhwan.a9oormthon.databinding.RecyclerVoiceItemBinding

class VoiceViewHolder(
    private val parent: ViewParent,
    binding: RecyclerVoiceItemBinding,
    private val listenerFuncOfBtn: ((LottieAnimationView) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    private val name by lazy {
        binding.name
    }

    private val btn by lazy {
        binding.playBtn
    }

    private val equalizer by lazy {
        binding.volumeEqualizer
    }

    fun onBind(voiceItem: voiceItem) {
        name.text = voiceItem.name

        btn.setOnClickListener {
            it.isSelected = !it.isSelected
            listenerFuncOfBtn?.invoke(equalizer)
        }

        equalizer.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationCancel(p0: Animator?) {
                btn.isSelected = false
            }

            override fun onAnimationEnd(p0: Animator?) {
                btn.isSelected = false
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationStart(p0: Animator?) {
                btn.isSelected = true
            }
        })
    }
}


