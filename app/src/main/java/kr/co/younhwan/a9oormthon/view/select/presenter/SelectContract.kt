package kr.co.younhwan.a9oormthon.view.select.presenter

interface SelectContract {
    interface View {
        fun isPlayingAudio(): Boolean

        fun startAudio()

        fun stopAudio()

        fun finishAct()

        fun setAudio(url: String)

        fun setLoadingView(shown: Boolean)

        fun getAudioDuration() : Int
    }

    interface Model {
        fun getData()
    }
}