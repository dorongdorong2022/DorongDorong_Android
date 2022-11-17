package kr.co.younhwan.a9oormthon.view.main.record.adapter.contract

import kr.co.younhwan.a9oormthon.data.item

interface RecordAdapterContract {
    interface View {
        fun notifyAdapter()
    }

    interface Model {
        fun addItems(items: ArrayList<String>)
    }
}