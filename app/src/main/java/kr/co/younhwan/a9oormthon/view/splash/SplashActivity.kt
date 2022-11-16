package kr.co.younhwan.a9oormthon.view.splash

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.result.contract.ActivityResultContracts
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
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Play splash image
        // binding.splashImage.playAnimation()

        // Check permission
        checkPermission()

        // Temp delay
        Handler().postDelayed({
            startMainAct()
        }, 2000)
    }

    override fun checkPermission() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }

                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                }
                else -> {
                    // No location access granted.
                }
            }
        }

        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    override fun startMainAct() {
        // Pause splash image
        // binding.splashImage.pauseAnimation()

        // Start main act
        startActivity(Intent(this, MainActivity::class.java))

        // Finish splash act
        finish()
    }
}