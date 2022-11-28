package kr.co.younhwan.a9oormthon.view.main.voice

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import kr.co.younhwan.a9oormthon.R
import kr.co.younhwan.a9oormthon.databinding.FragmentTaleBinding
import kr.co.younhwan.a9oormthon.databinding.FragmentVoiceBinding
import kr.co.younhwan.a9oormthon.util.replace
import kr.co.younhwan.a9oormthon.view.main.MainActivity
import kr.co.younhwan.a9oormthon.view.main.record.RecordFragment
import kr.co.younhwan.a9oormthon.view.main.sound.SoundFragment
import kr.co.younhwan.a9oormthon.view.main.tale.presenter.TalePresenter
import kr.co.younhwan.a9oormthon.view.main.voice.presenter.VoiceContract
import kr.co.younhwan.a9oormthon.view.main.voice.presenter.VoicePresenter

class VoiceFragment : Fragment(), VoiceContract.View {
    // binding
    private lateinit var binding: FragmentVoiceBinding

    // presenter
    private val presenter: VoicePresenter by lazy {
        VoicePresenter(
            view = this
        )
    }

    // fragment
    private val recordFragment: RecordFragment by lazy {
        RecordFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVoiceBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 권한 요청


        // 버튼 이벤트
        binding.containedButton.setOnClickListener {
            (activity as MainActivity).replace(R.id.fragmentContainerView, recordFragment)
        }
    }
}