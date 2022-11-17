package kr.co.younhwan.a9oormthon.view.main.tale

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kr.co.younhwan.a9oormthon.adapter.MainAdapter
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.databinding.FragmentTaleBinding
import kr.co.younhwan.a9oormthon.view.main.MainActivity
import kr.co.younhwan.a9oormthon.view.main.sound.presenter.SoundPresenter
import kr.co.younhwan.a9oormthon.view.main.tale.presenter.TaleContract
import kr.co.younhwan.a9oormthon.view.main.tale.presenter.TalePresenter
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

        // Set mic
        binding.mic.setOnClickListener {
            val intent = Intent(activity, SelectActivity::class.java)
            startActivity(intent)
        }

        // Set bottom sheet recycler view
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollHorizontally() = false
            override fun canScrollVertically() = true
        }

        // Change location
        binding.changeTale.setOnClickListener {
            if (behavior.state != BottomSheetBehavior.STATE_HIDDEN) {
                behavior.state = BottomSheetBehavior.STATE_HIDDEN
            } else {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        // Bottom sheet
        binding.closeBtnContainer2.setOnClickListener {
            if (behavior.state != BottomSheetBehavior.STATE_HIDDEN) {
                behavior.state = BottomSheetBehavior.STATE_HIDDEN
            } else {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        binding.closeBtn2.setOnClickListener {
            if (behavior.state != BottomSheetBehavior.STATE_HIDDEN) {
                behavior.state = BottomSheetBehavior.STATE_HIDDEN
            } else {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        behavior = BottomSheetBehavior.from<View>(binding.standardBottomSheet)
        behavior.isHideable = true
        behavior.state = BottomSheetBehavior.STATE_HIDDEN
        behavior.isDraggable = false

        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                val parent = activity as MainActivity

                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    parent.changeBottomSheetVisibility(false)
                } else {
                    parent.changeBottomSheetVisibility(true)
                }
            }
        })
    }
}