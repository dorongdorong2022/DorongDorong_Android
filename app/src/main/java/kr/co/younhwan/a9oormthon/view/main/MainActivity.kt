package kr.co.younhwan.a9oormthon.view.main

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
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
    var startSound: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Play audio
        startSound = MediaPlayer.create(
            this,
            R.raw.sound0
        )
        playAudio()

        binding.fragmentContainerView.setOnClickListener {
            playAudio()
        }


        // Set Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, soundFragment)
            .commit()

        // Bottom nav
        binding.bottomNavigation.selectedItemId = R.id.option_sound
        binding.bottomNavigation.setOnItemSelectedListener {
            startSound?.stop()

            when (it.itemId) {
                R.id.option_sound -> {
                    replace(R.id.fragmentContainerView, soundFragment)
                    binding.mainContainer.setBackgroundResource(R.drawable.frame)
                    startSound = MediaPlayer.create(
                        this,
                        R.raw.main_sound
                    )
                    true
                }

                R.id.option_tale -> {
                    replace(R.id.fragmentContainerView, taleFragment)
                    binding.mainContainer.setBackgroundResource(R.drawable.frame2)
                    startSound = MediaPlayer.create(
                        this,
                        R.raw.tale_sound
                    )
                    startSound?.start()
                    true
                }

                R.id.option_voice -> {
                    replace(R.id.fragmentContainerView, voiceFragment)
                    true
                }

                R.id.option_coaching -> {
                    replace(R.id.fragmentContainerView, coach1Fragment)
                    true
                }

                else -> false
            }
        }
    }

    override fun getAct() = this

    override fun changeBottomSheetVisibility(shown: Boolean) {
        if (shown) {
            binding.fragmentContainerView.translationZ = 90f
        } else {
            binding.fragmentContainerView.translationZ = 0f
        }
    }

    override fun playAudio() {
        if (startSound != null) {
            if (startSound?.isPlaying == true) {
                startSound?.pause()
            } else {
                startSound?.start()
            }
        }
    }

    override fun setBackground(url: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val bitmap = withContext(Dispatchers.IO) {
                ImageLoader.loadImage(url)
            }

            val resources: Resources = getResources()
            val drawable = BitmapDrawable(resources, bitmap)

            binding.mainContainer.background = drawable
        }
    }

    override fun selectedIcon() {
        binding.bottomNavigation.selectedItemId = R.id.option_voice
    }
}