package kr.co.younhwan.a9oormthon.view.main.tale.presenter

import kr.co.younhwan.a9oormthon.GlobalApplication
import kr.co.younhwan.a9oormthon.adapter.contract.MainAdapterContract
import kr.co.younhwan.a9oormthon.data.item
import kr.co.younhwan.a9oormthon.data.soundItem
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.data.source.main.MainSource
import kr.co.younhwan.a9oormthon.data.taleItem

class TalePresenter(
    private val view: TaleContract.View,
    private val mainData: MainRepository,
    private val mainAdapterModel: MainAdapterContract.Model,
    private val mainAdapterView: MainAdapterContract.View
) : TaleContract.Model {
    val token = GlobalApplication.prefs.getString("token", "no token")

    override fun getDate() {
        mainData.readTale(
            token = token,
            readTaleCallback = object : MainSource.ReadTaleCallback {
                override fun onReadTale(list: ArrayList<soundItem>) {
                    mainAdapterModel.addItems(list)
                    mainAdapterView.notifyAdapter()
                }
            }
        )
    }
}