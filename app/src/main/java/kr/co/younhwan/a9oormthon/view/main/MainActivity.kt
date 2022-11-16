package kr.co.younhwan.a9oormthon.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.databinding.ActivityMainBinding
import kr.co.younhwan.a9oormthon.view.main.presenter.MainContract
import kr.co.younhwan.a9oormthon.view.main.presenter.MainPresenter

class MainActivity :
    AppCompatActivity(),
    MainContract.View {
    // binding
    private lateinit var binding: ActivityMainBinding

    // presenter
    private val presenter: MainPresenter by lazy {
        MainPresenter(
            view = this,
            mainData = MainRepository
        )
    }

    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}