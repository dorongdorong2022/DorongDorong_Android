package kr.co.younhwan.a9oormthon.view.select.presenter

import android.util.Log
import com.airbnb.lottie.LottieAnimationView
import kr.co.younhwan.a9oormthon.GlobalApplication
import kr.co.younhwan.a9oormthon.adapter.contract.MainAdapterContract
import kr.co.younhwan.a9oormthon.data.item
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.data.source.main.MainSource
import kr.co.younhwan.a9oormthon.data.voiceItem
import kr.co.younhwan.a9oormthon.view.select.adapter.contract.VoiceAdapterContract

class SelectPresenter(
    private val view: SelectContract.View,
    private val mainData: MainRepository,
    private val voiceAdapterModel: VoiceAdapterContract.Model,
    private val voiceAdapterView: VoiceAdapterContract.View
) : SelectContract.Model {
    val token = GlobalApplication.prefs.getString("token", "no token")

    init {
        voiceAdapterView.onClickFunOfBtn = {
            onClickListenerOfBtn(it)
        }

        voiceAdapterView.onClickFunOfBtn2 = {
            onClickListenerOfBtn2()
        }

        view.initaudio()
    }

    private fun onClickListenerOfBtn(view2: LottieAnimationView) {
        if (view2.isAnimating){
            view2.pauseAnimation()
        }else{
            view2.playAnimation()
        }
        view.startAudio()
    }

    private fun onClickListenerOfBtn2() {
        view.fin()
    }

    override fun getData() {
        mainData.read(
            token,
            object : MainSource.ReadCallback {
                override fun onRead(list: ArrayList<voiceItem>) {
                    list.add(voiceItem(
                        id = 0,
                        name = "",
                        selected = true,
                        audioFile = "",
                        type = 2
                    ))

                    voiceAdapterModel.addItems(list)
                    voiceAdapterView.notifyAdapter()
                }
            }
        )
    }
}