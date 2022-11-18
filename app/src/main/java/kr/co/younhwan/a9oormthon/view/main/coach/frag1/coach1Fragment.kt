package kr.co.younhwan.a9oormthon.view.main.coach.frag1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import kr.co.younhwan.a9oormthon.util.replace
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.databinding.FragmentCoach1Binding
import kr.co.younhwan.a9oormthon.databinding.FragmentSoundBinding
import kr.co.younhwan.a9oormthon.view.main.MainActivity
import kr.co.younhwan.a9oormthon.view.main.tale.presenter.TalePresenter

class coach1Fragment : Fragment(), coach1Contract.View {
    // binding
    private lateinit var binding: FragmentCoach1Binding

    // presenter
    private val presenter: coach1Presenter by lazy {
        coach1Presenter(
            view = this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoach1Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = activity as MainActivity

        binding.frag2Container.setOnClickListener {

        }

        binding.frag3Container.setOnClickListener {

        }

        binding.frag4Container.setOnClickListener {

        }

        binding.frag5Container.setOnClickListener {

        }
    }
}