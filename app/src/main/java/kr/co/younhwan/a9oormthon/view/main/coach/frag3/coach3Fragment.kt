package kr.co.younhwan.a9oormthon.view.main.coach.frag3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.younhwan.a9oormthon.databinding.FragmentCoach1Binding
import kr.co.younhwan.a9oormthon.databinding.FragmentCoach3Binding
import kr.co.younhwan.a9oormthon.view.main.coach.frag1.coach1Contract
import kr.co.younhwan.a9oormthon.view.main.coach.frag1.coach1Presenter

class coach3Fragment : Fragment(), coach3Contract.View{
    // binding
    private lateinit var binding: FragmentCoach3Binding

    // presenter
    private val presenter: coach3Presenter by lazy {
        coach3Presenter(
            view = this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoach3Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}