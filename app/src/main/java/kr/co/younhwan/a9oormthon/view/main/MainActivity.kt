package kr.co.younhwan.a9oormthon.view.main

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kr.co.younhwan.a9oormthon.R
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.databinding.ActivityMainBinding
import kr.co.younhwan.a9oormthon.adapter.MainAdapter
import kr.co.younhwan.a9oormthon.view.main.sound.SoundFragment
import kr.co.younhwan.a9oormthon.util.replace
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

    // Audio
    var playing: Boolean = true
    var startSound : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Play audio
        startSound = MediaPlayer.create(this, Uri.parse("https://dorongdorong.s3.ap-northeast-2.amazonaws.com/file/sound/%E1%84%89%E1%85%A2%E1%84%87%E1%85%A7%E1%86%AF%E1%84%8B%E1%85%A9%E1%84%85%E1%85%B3%E1%86%B7.wav"))
        startSound?.isLooping = true
        startSound?.start()

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
                    true
                }

                R.id.option_tale -> {
                    replace(R.id.fragmentContainerView, taleFragment)
                    true
                }

                R.id.option_voice -> {
                    replace(R.id.fragmentContainerView, voiceFragment)
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
        if (playing){
            startSound?.pause()
        } else {
            startSound?.start()
        }

        playing = !playing
    }
}