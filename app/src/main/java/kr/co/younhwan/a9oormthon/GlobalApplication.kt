package kr.co.younhwan.a9oormthon

import android.app.Application
import kr.co.younhwan.a9oormthon.util.PreferenceUtil

class GlobalApplication : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}