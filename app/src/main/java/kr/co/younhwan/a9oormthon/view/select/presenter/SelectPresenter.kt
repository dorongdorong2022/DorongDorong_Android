package kr.co.younhwan.a9oormthon.view.select.presenter

import android.util.Log
import kr.co.younhwan.a9oormthon.GlobalApplication
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
    private val token = GlobalApplication.prefs.getString("token", "no token")

    init {
        voiceAdapterView.onClickFunOfBtn = {
            if (it.isAnimating){
                it.pauseAnimation()
            }else{
                it.playAnimation()
            }
            view.startAudio()
        }

        voiceAdapterView.onClickFunOfBtn2 = {
            view.finishAct()
        }
    }

    override fun getData() {
        if(token != "no token"){
            // 토큰이 존재할 때
            mainData.read(
                token,
                object : MainSource.ReadCallback {
                    override fun onRead(list: ArrayList<voiceItem>) {
                        // 기본 오디오 셋팅
                        for(item in list){
                            if(item.selected){
                                view.setAudio(item.audioFile)
                                Log.d("temp", item.audioFile)
                                break
                            }
                        }

                        // 음성 추가를 위한 리사이클러뷰 아이템을 추가
                        list.add(voiceItem(
                            id = 0,
                            name = "",
                            selected = true,
                            audioFile = "",
                            type = 2
                        ))

                        voiceAdapterModel.addItems(list)
                        voiceAdapterView.notifyAdapter()
                        view.setLoadingView(false)
                    }
                }
            )
        } else {
            // 토큰이 존재하지 않을 때
            view.setLoadingView(false)
        }
    }
}