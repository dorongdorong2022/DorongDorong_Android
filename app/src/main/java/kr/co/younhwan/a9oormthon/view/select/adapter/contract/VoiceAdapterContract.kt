package kr.co.younhwan.a9oormthon.view.select.adapter.contract

import kr.co.younhwan.a9oormthon.data.item
import kr.co.younhwan.a9oormthon.data.voiceItem

interface VoiceAdapterContract {
    interface View {
        fun notifyAdapter()
    }

    interface Model {
        fun addItems(items: ArrayList<voiceItem>)
    }
}