package kr.co.younhwan.a9oormthon.view.select

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
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

        // 로딩 뷰 설정
        setLoadingView(true)

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
        audio?.seekTo(0)
        audio?.start()
    }

    override fun stopAudio() {
        audio?.stop()
    }

    override fun getAudioDuration(): Int {
        return audio?.duration ?: 0
    }

    override fun finishAct() {
        setResult(RESULT_OK)
        finish()
    }

    override fun setAudio(url: String) {
        audio = null
        audio = MediaPlayer.create(this, Uri.parse(url))
    }

    override fun setLoadingView(shown: Boolean) {
        if(shown){
            binding.recycler.visibility = View.GONE
            binding.textContainer.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE
            binding.loading.playAnimation()
        } else {
            binding.recycler.visibility = View.VISIBLE
            binding.textContainer.visibility = View.VISIBLE
            binding.loading.visibility = View.GONE
            binding.loading.pauseAnimation()
        }
    }

    override fun isPlayingAudio(): Boolean {
        return audio?.isPlaying ?: false
    }
}