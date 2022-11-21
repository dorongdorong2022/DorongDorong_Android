package kr.co.younhwan.a9oormthon.view.main.tale

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kr.co.younhwan.a9oormthon.R
import kr.co.younhwan.a9oormthon.adapter.MainAdapter
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.databinding.FragmentTaleBinding
import kr.co.younhwan.a9oormthon.util.replace
import kr.co.younhwan.a9oormthon.view.main.MainActivity
import kr.co.younhwan.a9oormthon.view.main.tale.presenter.TaleContract
import kr.co.younhwan.a9oormthon.view.main.tale.presenter.TalePresenter
import kr.co.younhwan.a9oormthon.view.main.voice.VoiceFragment
import kr.co.younhwan.a9oormthon.view.select.SelectActivity

class TaleFragment : Fragment(), TaleContract.View {
    // binding
    private lateinit var binding: FragmentTaleBinding

    // presenter
    private val presenter: TalePresenter by lazy {
        TalePresenter(
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

    private val voiceFragment: VoiceFragment by lazy {
        VoiceFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaleBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val childForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        (activity as MainActivity).selectedIcon()
                        (activity as MainActivity).replace(R.id.fragmentContainerView, voiceFragment)
                    }

                    else ->{
                        (activity as MainActivity).audio?.start()
                    }
                }
            }


        // 데이터 요청
        presenter.getDate()

        // Set mic
        binding.mic.setOnClickListener {
            val intent = Intent(activity, SelectActivity::class.java)
            (activity as MainActivity).audio?.pause()
            childForResult.launch(intent)
        }

        // 리사이클러뷰 설정
        binding.recycler.adapter = adapter
        binding.recycler.addItemDecoration(adapter.RecyclerDecoration())
        binding.recycler.layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollHorizontally() = false
            override fun canScrollVertically() = true
        }

        // [설화변경] 버튼 이벤트 설정
        binding.changeTale.setOnClickListener {
            toggleBottomSheetVisibility()
        }

        // 바텀 시트 설정
        behavior = BottomSheetBehavior.from(binding.standardBottomSheet)
        behavior.isHideable = true
        behavior.isDraggable = false
        behavior.state = BottomSheetBehavior.STATE_HIDDEN

        binding.closeBtn2.setOnClickListener { toggleBottomSheetVisibility() }
        binding.closeBtnContainer2.setOnClickListener { toggleBottomSheetVisibility() }

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

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (behavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                    toggleBottomSheetVisibility()
                } else {
                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun toggleBottomSheetVisibility() {
        if (behavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            behavior.state = BottomSheetBehavior.STATE_HIDDEN
            binding.topContaier.animate().alpha(1.0f)
        } else {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            binding.topContaier.animate().alpha(0.0f)
        }
    }
}