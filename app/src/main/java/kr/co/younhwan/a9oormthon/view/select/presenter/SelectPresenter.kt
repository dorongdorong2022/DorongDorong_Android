package kr.co.younhwan.a9oormthon.view.select.presenter

import android.util.Log
import com.airbnb.lottie.LottieAnimationView
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
        voiceAdapterView.onClickFunOfBtn = { voiceItem: voiceItem, lottieAnimationView: LottieAnimationView ->
            // 오디오 재생 상태를 확인
            val isPlaying = lottieAnimationView.isAnimating

            // (오디오가 재생중이라면) 오디오 재생을 멈춤
            if(isPlaying) view.stopAudio()

            // 애니메이션 설정
            if (lottieAnimationView.isAnimating){
                lottieAnimationView.pauseAnimation()
            }else{
                lottieAnimationView.playAnimation()
            }

            // 오디오 및 애니메이션 재생 속도 설정
            view.setAudio(voiceItem.audioFile)
            lottieAnimationView.speed = lottieAnimationView.duration.toFloat() / view.getAudioDuration()

            // (오디오가 재생중이지 않았다면) 오디오 재생
            if(!isPlaying) view.startAudio()
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
                        Log.d("temp", list.toString())

                        list.add(voiceItem(
                            id = 0,
                            name = "기본2",
                            selected = false,
                            audioFile =  "https://dorongdorong.s3.ap-northeast-2.amazonaws.com/file/sound/%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%E1%84%8B%E1%85%B3%E1%86%B7%E1%84%89%E1%85%A5%E1%86%BC.wav",
                            type = 1
                        ))

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