package kr.co.younhwan.a9oormthon.view.select.presenter

import com.airbnb.lottie.LottieAnimationView
import kr.co.younhwan.a9oormthon.GlobalApplication
import kr.co.younhwan.a9oormthon.adapter.contract.MainAdapterContract
import kr.co.younhwan.a9oormthon.data.item
import kr.co.younhwan.a9oormthon.data.voiceItem
import kr.co.younhwan.a9oormthon.view.select.adapter.contract.VoiceAdapterContract

class SelectPresenter(
    private val view: SelectContract.View,
    private val voiceAdapterModel: VoiceAdapterContract.Model,
    private val voiceAdapterView: VoiceAdapterContract.View
) : SelectContract.Model {
    val token = GlobalApplication.prefs.getString("token", "no token")

    init {
        voiceAdapterView.onClickFunOfBtn = {
            onClickListenerOfBtn(it)
        }
    }

    fun onClickListenerOfBtn(view: LottieAnimationView) {
        view.playAnimation()
    }

    override fun getData() {
        // temp
        val temp = ArrayList<voiceItem>()

        for (i in 0..1) {
            temp.add(
                voiceItem(
                    id = 11111,
                    name = "123",
                    selected = true,
                    audioFile = "12312",
                    type = 1
                )
            )
        }

        temp.add(
            voiceItem(
                id = 1111,
                name = "123",
                selected = true,
                audioFile = "123123",
                type = 2
            )
        )

        // Set data
        voiceAdapterModel.addItems(temp)
        voiceAdapterView.notifyAdapter()
    }
}