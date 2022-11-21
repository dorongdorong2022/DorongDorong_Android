package kr.co.younhwan.a9oormthon.view.select

import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.younhwan.a9oormthon.R
import kr.co.younhwan.a9oormthon.adapter.MainAdapter
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.databinding.ActivitySelectBinding
import kr.co.younhwan.a9oormthon.view.main.MainActivity
import kr.co.younhwan.a9oormthon.view.select.adapter.VoiceAdapter
import kr.co.younhwan.a9oormthon.view.select.presenter.SelectContract
import kr.co.younhwan.a9oormthon.view.select.presenter.SelectPresenter


class SelectActivity :
    AppCompatActivity(),
    SelectContract.View {
    // binding
    private lateinit var binding: ActivitySelectBinding

    // presenter
    private val presenter: SelectPresenter by lazy {
        SelectPresenter(
            view = this,
            mainData = MainRepository,
            voiceAdapterModel = adapter,
            voiceAdapterView = adapter
        )
    }

    // adapter
    private val adapter: VoiceAdapter by lazy {
        VoiceAdapter()
    }

    var state = 0
    var startSound: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Play audio
        startSound = MediaPlayer.create(
            this,
            Uri.parse("https://dorongdorong.s3.ap-northeast-2.amazonaws.com/file/sound/%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%E1%84%8B%E1%85%B3%E1%86%B7%E1%84%89%E1%85%A5%E1%86%BC.wav")
        )

        // Get data
        presenter.getData()

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollHorizontally() = false
            override fun canScrollVertically() = true
        }
        binding.recycler.addItemDecoration(adapter.RecyclerDecoration())

        // navigation
        binding.voiceToolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun startAudio() {
        if(state == 0){
            state = 1
            startSound?.start()
        } else{
            state = 0
            startSound?.stop()
            startSound = null
            startSound = MediaPlayer.create(
                this,
                R.raw.basic
            )
        }
    }

    override fun initaudio() {
        startSound = MediaPlayer.create(
            this,
            R.raw.basic
        )
    }

    override fun fin() {
        setResult(RESULT_OK)
        finish()
    }
}