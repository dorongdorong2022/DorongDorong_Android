package kr.co.younhwan.a9oormthon.view.select.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.younhwan.a9oormthon.adapter.MainViewHolder
import kr.co.younhwan.a9oormthon.adapter.contract.MainAdapterContract
import kr.co.younhwan.a9oormthon.data.item
import kr.co.younhwan.a9oormthon.data.voiceItem
import kr.co.younhwan.a9oormthon.databinding.RecyclerItemBinding
import kr.co.younhwan.a9oormthon.databinding.RecyclerVoiceItemBinding
import kr.co.younhwan.a9oormthon.view.select.adapter.contract.VoiceAdapterContract

class VoiceAdapter :
    RecyclerView.Adapter<VoiceViewHolder>(),
    VoiceAdapterContract.Model,
    VoiceAdapterContract.View {
    // 리사이클러뷰 아이템
    private var itemList: ArrayList<voiceItem> = ArrayList()

    override fun getItemCount() = itemList.size

    override fun notifyAdapter() = notifyDataSetChanged()

    override fun addItems(items: ArrayList<voiceItem>) {
        itemList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoiceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerVoiceItemBinding.inflate(inflater, parent, false)
        return VoiceViewHolder(
            parent = parent,
            binding = binding
        )
    }

    override fun onBindViewHolder(holder: VoiceViewHolder, position: Int) {
        itemList[position].let {
            holder.onBind(it)
        }
    }
}
