package kr.co.younhwan.a9oormthon.adapter.contract

import kr.co.younhwan.a9oormthon.data.item

interface MainAdapterContract {
    interface View {
        fun notifyAdapter()
    }

    interface Model {
        fun addItems(items: ArrayList<item>)
    }
}

