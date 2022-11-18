package kr.co.younhwan.a9oormthon.view.main.sound.presenter

import android.util.Log
import kr.co.younhwan.a9oormthon.GlobalApplication
import kr.co.younhwan.a9oormthon.adapter.contract.MainAdapterContract
import kr.co.younhwan.a9oormthon.data.item
import kr.co.younhwan.a9oormthon.data.soundItem
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.data.source.main.MainSource

class SoundPresenter(
    private val view: SoundContract.View,
    private val mainData: MainRepository,
    private val mainAdapterModel: MainAdapterContract.Model,
    private val mainAdapterView: MainAdapterContract.View
) : SoundContract.Model {
    val token = GlobalApplication.prefs.getString("token", "no token")

    init {
        mainAdapterView.onClickFunOfLocation = {
            onClickFunOfLocation(it)
        }
    }

    fun onClickFunOfLocation(soundItem: soundItem) {
        view.clickCloseBtn()
        view.setSound(soundItem.jejuSoundUrl)
        view.setBackground(soundItem.jejuSoundImgUrl)
    }

    override fun getData() {
        mainData.readSound(
            token,
            object : MainSource.ReadSoundCallback {
                override fun onReadSound(list: ArrayList<soundItem>) {
                    mainAdapterModel.addItems(list)
                    mainAdapterView.notifyAdapter()
                }
            }
        )
    }
}