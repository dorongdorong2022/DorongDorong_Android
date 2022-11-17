package kr.co.younhwan.a9oormthon.view.main.voice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.younhwan.a9oormthon.databinding.FragmentTaleBinding
import kr.co.younhwan.a9oormthon.databinding.FragmentVoiceBinding
import kr.co.younhwan.a9oormthon.view.main.tale.presenter.TalePresenter
import kr.co.younhwan.a9oormthon.view.main.voice.presenter.VoiceContract
import kr.co.younhwan.a9oormthon.view.main.voice.presenter.VoicePresenter

class VoiceFragment : Fragment(), VoiceContract.View {
    private lateinit var binding: FragmentVoiceBinding

    private val presenter: VoicePresenter by lazy {
        VoicePresenter(
            view = this
        )
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
    }
}