package kr.co.younhwan.a9oormthon.view.main

import android.graphics.drawable.Drawable

interface MainContract {
    interface View {
        fun getAct(): MainActivity

        fun onToggleBottomSheetVisibility(shown: Boolean)

        fun stopAudio()

        fun playAudio()

        fun pauseAudio()

        fun toggleAudio()

        fun setAudio(url: String?, filePath: Int?)

        fun setBackground(url: String)

        fun selectedIcon()
    }

    interface Model {

    }
}