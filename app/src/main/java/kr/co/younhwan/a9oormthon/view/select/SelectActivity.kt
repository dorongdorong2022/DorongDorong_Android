package kr.co.younhwan.a9oormthon.view.select

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
            view = this,
            voiceAdapterModel = adapter,
            voiceAdapterView = adapter
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

        // Get data
        presenter.getData()

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollHorizontally() = false
            override fun canScrollVertically() = true
        }
        binding.recycler.addItemDecoration(adapter.RecyclerDecoration())

        // navigation
        binding.voiceToolbar.setNavigationOnClickListener {
            finish()
        }
    }
}