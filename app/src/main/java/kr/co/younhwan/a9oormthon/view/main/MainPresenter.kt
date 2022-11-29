package kr.co.younhwan.a9oormthon.view.main

import kr.co.younhwan.a9oormthon.data.source.main.MainRepository

class MainPresenter(
    private val view: MainContract.View,
    private val mainData: MainRepository,
) : MainContract.Model {
}