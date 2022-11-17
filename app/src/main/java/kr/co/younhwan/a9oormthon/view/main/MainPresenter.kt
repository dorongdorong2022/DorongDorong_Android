package kr.co.younhwan.a9oormthon.view.main

import kr.co.younhwan.a9oormthon.GlobalApplication
import kr.co.younhwan.a9oormthon.data.item
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.adapter.contract.MainAdapterContract

class MainPresenter(
    private val view: MainContract.View,
    private val mainData: MainRepository,
) : MainContract.Model {


}