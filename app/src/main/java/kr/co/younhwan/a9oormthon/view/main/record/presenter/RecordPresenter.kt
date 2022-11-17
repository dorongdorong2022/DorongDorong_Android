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
        list.add("간장 공장 공장장은 강 공장장이고")
        list.add("된장 공장 공장장은 장 공장장이다.")
        list.add("")
        list.add("의료정보에 대한 교환이 중요하다라고 보는 거에요.")
        list.add("의사들이죠?")
//        list.add("그 다음에 환자 입장에서는 당연히")
//        list.add("의료의 질이 좋아져요.")
//        list.add("자신들의 필요에 의해서 그거야말로 ")
//        list.add("시장논리에서 그렇게 이미 하고 있는 것 아닌가요?")
//        list.add("무엇이 사람들을 이런 분명한 비이성적인")
//        list.add("의사결정을 하게 만들까요?")
        recordAdapterModel.addItems(list)
        recordAdapterView.notifyAdapter()
    }
}