package kr.co.younhwan.a9oormthon.view.main

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        // (기본) 오디오 선언 및 재생
        setAudio(null, R.raw.main_sound)
        playAudio()

        // 초기 프래그먼트 설정
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, soundFragment)
            .commit()

        // 바텀 네비게이션 이벤트 설정
        binding.bottomNavigation.selectedItemId = R.id.option_sound
        binding.bottomNavigation.setOnItemSelectedListener {
            audio?.stop()

            when (it.itemId) {
                R.id.option_sound -> {
                    // 프래그먼트 변경
                    replace(R.id.fragmentContainerView, soundFragment)

                    // 오디오 변경 재생
                    setAudio(null, R.raw.main_sound)
                    playAudio()

                    true
                }

                R.id.option_tale -> {
                    // 프래그먼트 변경
                    replace(R.id.fragmentContainerView, taleFragment)

                    // 오디오 변경 및 재생
                    setAudio(null, R.raw.tale_sound)
                    playAudio()

                    true
                }

                R.id.option_voice -> {
                    // 프래그래먼트 변경
                    replace(R.id.fragmentContainerView, voiceFragment)

                    true
                }

                R.id.option_coaching -> {
                    // 프래그먼트 변경
                    replace(R.id.fragmentContainerView, coach1Fragment)

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

    override fun toggleAudio() {
        if (audio != null && audio?.isPlaying == true)
            audio?.pause()
        else
            audio?.start()
    }

    override fun playAudio() {
        audio?.start()
    }

    override fun stopAudio() {
        audio?.stop()
    }

    override fun pauseAudio() {
        audio?.pause()
    }

    override fun setAudio(url: String?, filePath: Int?) {
        audio = if (url == null && filePath != null) {
            MediaPlayer.create(this, filePath)
        } else if (url != null && filePath == null) {
            MediaPlayer.create(this, Uri.parse(url))
        } else {
            MediaPlayer.create(this, R.raw.main_sound)
        }
    }
}