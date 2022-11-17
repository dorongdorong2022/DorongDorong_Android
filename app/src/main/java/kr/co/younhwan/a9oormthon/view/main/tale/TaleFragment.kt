package kr.co.younhwan.a9oormthon.view.main.tale

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.younhwan.a9oormthon.adapter.MainAdapter
import kr.co.younhwan.a9oormthon.data.source.main.MainRepository
import kr.co.younhwan.a9oormthon.databinding.FragmentSoundBinding
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
    }
}