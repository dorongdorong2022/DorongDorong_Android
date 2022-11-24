package kr.co.younhwan.a9oormthon.view.select

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.younhwan.a9oormthon.R
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.databinding.ActivitySelectBinding
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

    // Audio
    var state = 0
    var audio: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 데이터 요청
        presenter.getData()

        // 리사이클러뷰 설정
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollHorizontally() = false
            override fun canScrollVertically() = true
        }
        binding.recycler.addItemDecoration(adapter.RecyclerDecoration())

        // 네비게이션 설정
        binding.voiceToolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun startAudio() {
        if(audio?.isPlaying == true){
            audio?.pause()
        } else {
            audio?.seekTo(0)
            audio?.start()
        }
    }

    override fun finishAct() {
        setResult(RESULT_OK)
        finish()
    }

    override fun setAudio(url: String) {
        audio = null
        audio = MediaPlayer.create(this, Uri.parse(url))
    }
}