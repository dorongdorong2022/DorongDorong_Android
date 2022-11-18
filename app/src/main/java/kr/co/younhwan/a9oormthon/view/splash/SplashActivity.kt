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
            val intent = Intent(this@SplashActivity, SplashActivity::class.java) //화면 전환
            presenter.getKey()
        }, 1500) //딜레이 타임 조절

    }

    override fun startMainAct() {
        // Pause splash image
        // binding.splashImage.pauseAnimation()

        // Start main act
        startActivity(Intent(this, MainActivity::class.java))

        // Finish splash act
        finish()
    }

    override fun getAct() = this
}