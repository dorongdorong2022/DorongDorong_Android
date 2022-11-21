package kr.co.younhwan.a9oormthon.view.main

interface MainContract {
    interface View {
        fun getAct(): MainActivity

        fun onToggleBottomSheetVisibility(shown: Boolean)

        fun playAudio()

        fun setBackground(url: String)

        fun selectedIcon()
    }

    interface Model {

    }
}