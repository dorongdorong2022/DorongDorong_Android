package kr.co.younhwan.a9oormthon.view.select.adapter.contract

import com.airbnb.lottie.LottieAnimationView
import kr.co.younhwan.a9oormthon.data.item
import kr.co.younhwan.a9oormthon.data.voiceItem

interface VoiceAdapterContract {
    interface View {
        var onClickFunOfBtn: ((LottieAnimationView) -> Unit)?

        fun notifyAdapter()
    }

    interface Model {
        fun addItems(items: ArrayList<voiceItem>)
    }
}