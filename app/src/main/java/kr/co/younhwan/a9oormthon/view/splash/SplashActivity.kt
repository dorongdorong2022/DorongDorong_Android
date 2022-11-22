package kr.co.younhwan.a9oormthon.view.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.databinding.ActivitySplashBinding
import kr.co.younhwan.a9oormthon.view.main.MainActivity
import kr.co.younhwan.a9oormthon.view.splash.presenter.SplashContract
import kr.co.younhwan.a9oormthon.view.splash.presenter.SplashPresenter

@SuppressLint("CustomSplashScreen")
class SplashActivity :
    AppCompatActivity(),
    SplashContract.View {
    // binding
    private lateinit var binding: ActivitySplashBinding

    // presenter
    private val presenter: SplashPresenter by lazy {
        SplashPresenter(
            view = this,
            mainData = MainRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed(Runnable {
            presenter.getKey()
        }, 1000) //딜레이 타임 조절

    }

    override fun startMainAct() {
        // 메인 엑티비티 실행
        startActivity(Intent(this, MainActivity::class.java))

        // 현재 엑티비티 종료
        finish()
    }

    override fun getAct() = this
}