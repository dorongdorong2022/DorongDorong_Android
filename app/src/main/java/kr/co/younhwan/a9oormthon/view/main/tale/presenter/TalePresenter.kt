package kr.co.younhwan.a9oormthon.view.main.tale.presenter

import android.util.Log
import kr.co.younhwan.a9oormthon.GlobalApplication
import kr.co.younhwan.a9oormthon.adapter.contract.MainAdapterContract
import kr.co.younhwan.a9oormthon.data.soundItem
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.data.source.main.MainSource

class TalePresenter(
    private val view: TaleContract.View,
    private val mainData: MainRepository,
    private val mainAdapterModel: MainAdapterContract.Model,
    private val mainAdapterView: MainAdapterContract.View
) : TaleContract.Model {
    private val token = GlobalApplication.prefs.getString("token", "no token")

    override fun getDate() {
        if (token != "no token") {
            // 토큰이 존재할 때
            mainData.readTale(
                token = token,
                readTaleCallback = object : MainSource.ReadTaleCallback {
                    override fun onReadTale(list: ArrayList<soundItem>) {
                        mainAdapterModel.addItems(list)
                        mainAdapterView.notifyAdapter()
                    }
                }
            )
        } else {
            // 토큰이 존재하지 않을 때
        }
    }
}