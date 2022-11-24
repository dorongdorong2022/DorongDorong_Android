package kr.co.younhwan.a9oormthon.view.select.presenter

interface SelectContract {
    interface View {
        fun startAudio()

        fun finishAct()

        fun setAudio(url: String)

        fun setLoadingView(shown: Boolean)
    }

    interface Model {
        fun getData()
    }
}