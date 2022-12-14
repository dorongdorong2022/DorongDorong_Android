package kr.co.younhwan.a9oormthon.view.main.record

import android.Manifest
import android.graphics.BitmapFactory
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kr.co.younhwan.a9oormthon.R
import kr.co.younhwan.a9oormthon.databinding.FragmentRecordBinding
import kr.co.younhwan.a9oormthon.util.replace
import kr.co.younhwan.a9oormthon.view.main.MainActivity
import kr.co.younhwan.a9oormthon.view.main.record.adapter.RecordAdapter
import kr.co.younhwan.a9oormthon.view.main.record.presenter.RecordContract
import kr.co.younhwan.a9oormthon.view.main.record.presenter.RecordPresenter
import kr.co.younhwan.a9oormthon.view.main.voice.VoiceFragment

class RecordFragment : Fragment(), RecordContract.View {
    // binding
    private lateinit var binding: FragmentRecordBinding

    // presenter
    private val presenter: RecordPresenter by lazy {
        RecordPresenter(
            view = this,
            recordAdapterModel = adapter,
            recordAdapterView = adapter
        )
    }

    // adapter
    private val adapter: RecordAdapter by lazy {
        RecordAdapter()
    }

    private val voiceFragment: VoiceFragment by lazy {
        VoiceFragment()
    }

    // record btn / state
    private var mediaRecorder: MediaRecorder? = null
    private var state = "ready"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecordBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getData()

        val permissions =
            arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(activity as MainActivity, permissions, 0)

        val output =
            "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}" + "/test.mp4"

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollHorizontally() = false
            override fun canScrollVertically() = true
        }
        binding.recycler.addItemDecoration(adapter.RecyclerDecoration())

        binding.imageButton.setOnClickListener {
            (activity as MainActivity).replace(R.id.fragmentContainerView, voiceFragment)
        }

        // record btn
        binding.recordBtn.setOnClickListener {
            when (state) {
                "ready" -> {
                    state = "recording"
                    binding.recordBtn.setImageBitmap(
                        BitmapFactory.decodeResource(
                            context?.resources,
                            R.drawable.record_2
                        )
                    )

                    Glide.with(this).load(R.raw.temp).fitCenter().into(binding.imageView6)
                    binding.equa.playAnimation()
                }

                "recording" -> {
                    state = "ready"
                    binding.recordBtn.setImageBitmap(
                        BitmapFactory.decodeResource(
                            context?.resources,
                            R.drawable.record_1
                        )
                    )

                    Glide.with(this).load(R.drawable.tmp3333333).fitCenter().into(binding.imageView6)
                    binding.equa.pauseAnimation()

                    val dlg = MyDialog()
                    dlg.show((activity as MainActivity).supportFragmentManager, "CustomDialog")
                }
            }
        }
    }

}