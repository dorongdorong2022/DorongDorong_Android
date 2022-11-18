package kr.co.younhwan.a9oormthon.view.main.record

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kr.co.younhwan.a9oormthon.databinding.MyDialog2Binding
import kr.co.younhwan.a9oormthon.databinding.MyDialogBinding

class MyDialog2 : DialogFragment(){
    lateinit var binding : MyDialog2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyDialog2Binding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

//        binding.button2.setOnClickListener {
//            dismiss()
//        }
//
//        binding.button.setOnClickListener {
//            dismiss()
//        }

        return binding.root
    }
}
