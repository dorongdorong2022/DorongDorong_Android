package kr.co.younhwan.a9oormthon.view.main.sound.presenter

import kr.co.younhwan.a9oormthon.view.main.MainActivity

interface SoundContract {
    interface View {
        fun getAct(): MainActivity

        fun setSound(url:String)

        fun setBackground(url: String)

        fun setVolumeImage(show: Boolean)

        fun toggleBottomSheetVisibility()
    }

    interface Model {
        fun getData()
    }
}