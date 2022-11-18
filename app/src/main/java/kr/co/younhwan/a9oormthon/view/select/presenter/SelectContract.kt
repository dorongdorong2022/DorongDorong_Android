package kr.co.younhwan.a9oormthon.view.select.presenter

interface SelectContract {
    interface View {
        fun startAudio()

        fun fin()
    }

    interface Model {
        fun getData()
    }
}