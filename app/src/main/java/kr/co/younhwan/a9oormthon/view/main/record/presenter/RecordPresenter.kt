package kr.co.younhwan.a9oormthon.view.main.record.presenter

import kr.co.younhwan.a9oormthon.view.main.record.adapter.contract.RecordAdapterContract
import kr.co.younhwan.a9oormthon.view.select.adapter.contract.VoiceAdapterContract

class RecordPresenter(
    private val view: RecordContract.View,
    private val recordAdapterModel: RecordAdapterContract.Model,
    private val recordAdapterView: RecordAdapterContract.View
) : RecordContract.Model{
    override fun getData() {
        val list = ArrayList<String>()
        list.add("햇살도 둥글둥글하게 뭉치는 맑은 날")
        list.add("꽃초롱 불 밝히듯 눈을 밝힐까")
        list.add("흙에서 자란 내마음 파아란 하늘빛")
        list.add("하늘을 우러러 한 점 부끄럼 없기를")
        list.add("많고 많은 사람 중에 그대 한 사람?")

        recordAdapterModel.addItems(list)
        recordAdapterView.notifyAdapter()
    }
}