package kr.co.younhwan.a9oormthon.view.splash.presenter

import kr.co.younhwan.a9oormthon.view.splash.SplashActivity

interface SplashContract {
    interface View {
        fun getAct() : SplashActivity

        fun startMainAct()
    }

    interface Model {
        fun getKey()
    }
}