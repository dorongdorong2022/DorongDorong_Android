package kr.co.younhwan.a9oormthon.view.main.tale.presenter

interface TaleContract {
    interface View {
        fun toggleBottomSheetVisibility()

        fun setVolumeImage(show: Boolean)
    }

    interface Model {
        fun getDate()
    }
}