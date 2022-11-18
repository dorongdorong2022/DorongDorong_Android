package kr.co.younhwan.a9oormthon.view.main.coach.frag5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.younhwan.a9oormthon.databinding.FragmentCoach1Binding
import kr.co.younhwan.a9oormthon.databinding.FragmentCoach4Binding
import kr.co.younhwan.a9oormthon.databinding.FragmentCoach5Binding
import kr.co.younhwan.a9oormthon.view.main.coach.frag1.coach1Contract
import kr.co.younhwan.a9oormthon.view.main.coach.frag1.coach1Presenter

class coach5Fragment : Fragment(), coach5Contract.View {
    // binding
    private lateinit var binding: FragmentCoach5Binding

    // presenter
    private val presenter: coach5Presenter by lazy {
        coach5Presenter(
            view = this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoach5Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}