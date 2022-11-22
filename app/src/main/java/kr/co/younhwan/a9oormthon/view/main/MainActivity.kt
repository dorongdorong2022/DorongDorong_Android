package kr.co.younhwan.a9oormthon.view.main

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.window.SplashScreen
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import kotlinx.coroutines.*
import kr.co.younhwan.a9oormthon.R
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.databinding.ActivityMainBinding
import kr.co.younhwan.a9oormthon.util.ImageLoader
import kr.co.younhwan.a9oormthon.util.replace
import kr.co.younhwan.a9oormthon.view.main.coach.frag1.coach1Fragment
import kr.co.younhwan.a9oormthon.view.main.sound.SoundFragment
import kr.co.younhwan.a9oormthon.view.main.tale.TaleFragment
import kr.co.younhwan.a9oormthon.view.main.voice.VoiceFragment

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

    // fragment
    private val soundFragment: SoundFragment by lazy {
        SoundFragment()
    }

    private val taleFragment: TaleFragment by lazy {
        TaleFragment()
    }

    private val voiceFragment: VoiceFragment by lazy {
        VoiceFragment()
    }

    private val coach1Fragment: coach1Fragment by lazy {
        coach1Fragment()
    }

    // Audio
    var audio: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 오디오 선언 및 재생
        audio = MediaPlayer.create(this, R.raw.sound0)
        playAudio()

        // 전체 컨테이너 이벤트 설정
        binding.fragmentContainerView.setOnClickListener {
            playAudio()
        }

        // 초기 프래그먼트 설정
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, soundFragment)
            .commit()

        // 바텀 네비게이션 이벤트 설정
        binding.bottomNavigation.selectedItemId = R.id.option_sound
        binding.bottomNavigation.setOnItemSelectedListener {
            audio?.pause()

            when (it.itemId) {
                R.id.option_sound -> {
                    // 프래그먼트 변경
                    replace(R.id.fragmentContainerView, soundFragment)

                    // 오디오 변경 재생
                    audio = MediaPlayer.create(this, R.raw.main_sound)
                    playAudio()
                    true
                }

                R.id.option_tale -> {
                    // 프래그먼트 변경
                    replace(R.id.fragmentContainerView, taleFragment)

                    // 오디오 변경 및 재생
                    audio = MediaPlayer.create(this, R.raw.tale_sound)
                    playAudio()
                    true
                }

                R.id.option_voice -> {
                    // 프래그래먼트 변경
                    replace(R.id.fragmentContainerView, voiceFragment)

                    // 오디오 변경
                    audio = null
                    true
                }

                R.id.option_coaching -> {
                    // 프래그먼트 변경
                    replace(R.id.fragmentContainerView, coach1Fragment)

                    // 오디오 변경
                    audio = null
                    true
                }

                else -> false
            }
        }
    }

    override fun getAct() = this

    override fun onToggleBottomSheetVisibility(shown: Boolean) =
        if (shown) binding.fragmentContainerView.translationZ = 90f
        else binding.fragmentContainerView.translationZ = 0f


    override fun playAudio() {
        if (audio != null && audio?.isPlaying == true)
            audio?.pause()
        else
            audio?.start()
    }

    override fun setBackground(url: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val bitmap = withContext(Dispatchers.IO) {
                ImageLoader.loadImage(url)
            }

            val resources: Resources = resources
            val drawable = BitmapDrawable(resources, bitmap)

            binding.mainContainer.background = drawable
        }
    }

    override fun selectedIcon() {
        binding.bottomNavigation.selectedItemId = R.id.option_voice
    }
}