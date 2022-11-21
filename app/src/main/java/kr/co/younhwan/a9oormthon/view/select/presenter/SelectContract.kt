package kr.co.younhwan.a9oormthon.view.select.presenter

interface SelectContract {
    interface View {
        fun startAudio()

        fun fin()

        fun initaudio()
    }

    interface Model {
        fun getData()
    }
}