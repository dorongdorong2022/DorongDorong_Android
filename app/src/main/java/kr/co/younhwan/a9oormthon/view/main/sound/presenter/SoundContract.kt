package kr.co.younhwan.a9oormthon.view.main.sound.presenter

interface SoundContract {
    interface View {
        fun setSound(url:String)

        fun clickCloseBtn()
    }

    interface Model {
        fun getData()
    }
}