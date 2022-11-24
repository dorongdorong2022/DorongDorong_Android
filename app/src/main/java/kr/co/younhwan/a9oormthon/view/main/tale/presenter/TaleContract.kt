package kr.co.younhwan.a9oormthon.view.main.tale.presenter

import kr.co.younhwan.a9oormthon.view.main.MainActivity

interface TaleContract {
    interface View {
        fun getAct(): MainActivity

        fun toggleBottomSheetVisibility()

        fun setVolumeImage(show: Boolean)
    }

    interface Model {
        fun getDate()
    }
}