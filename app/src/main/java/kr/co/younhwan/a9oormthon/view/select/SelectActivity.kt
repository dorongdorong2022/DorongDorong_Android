package kr.co.younhwan.a9oormthon.view.select

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kr.co.younhwan.a9oormthon.adapter.MainAdapter
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
            view = this
        )
    }

    // adapter
    private val adapter: VoiceAdapter by lazy {
        VoiceAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}