package kr.co.younhwan.a9oormthon.view.main.sound

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.media.MediaPlayer
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.co.younhwan.a9oormthon.adapter.MainAdapter
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.databinding.FragmentSoundBinding
import kr.co.younhwan.a9oormthon.util.ImageLoader
import kr.co.younhwan.a9oormthon.view.main.MainActivity
import kr.co.younhwan.a9oormthon.view.main.sound.presenter.SoundContract
import kr.co.younhwan.a9oormthon.view.main.sound.presenter.SoundPresenter

class SoundFragment : Fragment(), SoundContract.View {
    // binding
    private lateinit var binding: FragmentSoundBinding

    // presenter
    private val presenter: SoundPresenter by lazy {
        SoundPresenter(
            view = this,
            mainData = MainRepository,
            mainAdapterModel = adapter,
            mainAdapterView = adapter
        )
    }

    // behavior
    private lateinit var behavior: BottomSheetBehavior<View>

    // adapter
    private val adapter: MainAdapter by lazy {
        MainAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSoundBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 데이터 요청
        presenter.getData()

        // 리사이클러뷰 설정
        binding.recycler.adapter = adapter
        binding.recycler.addItemDecoration(adapter.RecyclerDecoration())
        binding.recycler.layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollHorizontally() = false
            override fun canScrollVertically() = true
        }

        // [장소변경] 버튼 이벤트 설정
        binding.changeLocation.setOnClickListener {
            toggleBottomSheetVisibility()
        }

        // 바텀 시트 설정
        behavior = BottomSheetBehavior.from(binding.standardBottomSheet)
        behavior.isHideable = true
        behavior.isDraggable = false
        behavior.state = BottomSheetBehavior.STATE_HIDDEN

        binding.closeBtn.setOnClickListener { toggleBottomSheetVisibility() }
        binding.closeBtnContainer.setOnClickListener { toggleBottomSheetVisibility() }

        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN)
                    (activity as MainActivity).onToggleBottomSheetVisibility(shown = false)
                else
                    (activity as MainActivity).onToggleBottomSheetVisibility(shown = true)
            }
        })
    }

    override fun setSound(url: String) {
        val activity = activity as MainActivity

        activity.audio.stop()
        activity.audio = MediaPlayer.create(activity, Uri.parse(url))
        activity.playAudio()
    }

    override fun setBackground(url: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val bitmap = withContext(Dispatchers.IO) { ImageLoader.loadImage(url) }

            val resources: Resources = resources
            val drawable = BitmapDrawable(resources, bitmap)

            binding.soundContainer.background = drawable
        }
    }

    override fun toggleBottomSheetVisibility() {
        if (behavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            behavior.state = BottomSheetBehavior.STATE_HIDDEN
            binding.changeLocation.animate().alpha(1.0f)
        } else {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            binding.changeLocation.animate().alpha(0.0f)
        }
    }
}