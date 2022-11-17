package kr.co.younhwan.a9oormthon.view.main

import kr.co.younhwan.a9oormthon.view.main.MainActivity

interface MainContract {
    interface View {
        fun getAct(): MainActivity

        fun changeBottomSheetVisibility(shown: Boolean)

        fun playAudio()
    }

    interface Model {

    }
}