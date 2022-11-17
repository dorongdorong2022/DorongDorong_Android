package kr.co.younhwan.a9oormthon.view.splash

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.younhwan.a9oormthon.GlobalApplication
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

        // Get Key
        presenter.getKey()
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