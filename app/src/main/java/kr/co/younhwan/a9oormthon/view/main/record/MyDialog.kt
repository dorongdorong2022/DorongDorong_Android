package kr.co.younhwan.a9oormthon.view.main.record

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import kr.co.younhwan.a9oormthon.databinding.MyDialogBinding
import kr.co.younhwan.a9oormthon.view.main.MainActivity

class MyDialog : DialogFragment(){
    lateinit var binding : MyDialogBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyDialogBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.button2.setOnClickListener {
            val dlg = MyDialog2()
            dlg.show((activity as MainActivity).supportFragmentManager, "CustomDialog")
            dismiss()
        }

        binding.button.setOnClickListener {
            dismiss()
        }

        return binding.root
    }
}
