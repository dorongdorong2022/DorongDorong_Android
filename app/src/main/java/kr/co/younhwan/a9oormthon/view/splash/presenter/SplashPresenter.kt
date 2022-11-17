package kr.co.younhwan.a9oormthon.view.splash.presenter

import android.content.Context
import android.hardware.usb.UsbDevice.getDeviceId
import android.provider.Settings
import android.util.Log
import kr.co.younhwan.a9oormthon.GlobalApplication
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.data.source.main.MainSource
import java.util.*

class SplashPresenter(
    private val view: SplashContract.View,
    private val mainData: MainRepository,
) : SplashContract.Model {

    override fun getKey() {
        val token = GlobalApplication.prefs.getString("token", "no token")

        if (token == "no token") {
            val uuid = UUID.randomUUID().toString();
            mainData.readKey(
                uuid,
                object : MainSource.ReadKeyCallback {
                    override fun onReadKey(token: String) {
                        if (token != "") {
                            GlobalApplication.prefs.setString("token", token)
                        }

                        view.startMainAct()
                    }
                }
            )
        } else {
            view.startMainAct()
        }
    }
}