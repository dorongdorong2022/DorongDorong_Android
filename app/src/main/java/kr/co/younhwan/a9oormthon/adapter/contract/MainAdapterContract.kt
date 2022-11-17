package kr.co.younhwan.a9oormthon.adapter.contract

import com.airbnb.lottie.LottieAnimationView
import kr.co.younhwan.a9oormthon.data.item
import kr.co.younhwan.a9oormthon.data.soundItem
import kr.co.younhwan.a9oormthon.data.taleItem

interface MainAdapterContract {
    interface View {
        var onClickFunOfLocation: ((String) -> Unit)?

        fun notifyAdapter()
    }

    interface Model {
        fun addItems(items: ArrayList<soundItem>)
    }
}

