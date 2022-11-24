package kr.co.younhwan.a9oormthon.view.main.sound.presenter

import android.util.Log
import android.widget.Toast
import kr.co.younhwan.a9oormthon.GlobalApplication
import kr.co.younhwan.a9oormthon.adapter.contract.MainAdapterContract
import kr.co.younhwan.a9oormthon.data.soundItem
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.data.source.main.MainSource

class SoundPresenter(
    private val view: SoundContract.View,
    private val mainData: MainRepository,
    private val mainAdapterModel: MainAdapterContract.Model,
    private val mainAdapterView: MainAdapterContract.View
) : SoundContract.Model {

    init {
        mainAdapterView.onClickFunOfLocation = {
            if (it.jejuSoundUrl.isNotBlank() && it.jejuSoundUrl.isNotEmpty()) {
                view.toggleBottomSheetVisibility()
                view.setSound(it.jejuSoundUrl)
                view.setBackground(it.jejuSoundImgUrl)
            } else {
                Toast.makeText(view.getAct(), "준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private val token = GlobalApplication.prefs.getString("token", "no token")

    override fun getData() {
        if (token != "no token") {
            // 토근이 존재할 때
            mainData.readSound(
                token,
                object : MainSource.ReadSoundCallback {
                    override fun onReadSound(list: ArrayList<soundItem>) {

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