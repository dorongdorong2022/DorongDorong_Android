package kr.co.younhwan.a9oormthon.view.select.adapter

import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import kr.co.younhwan.a9oormthon.data.voiceItem
import kr.co.younhwan.a9oormthon.databinding.RecyclerVoiceItemBinding

class VoiceViewHolder(
    private val parent: ViewParent,
    binding: RecyclerVoiceItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(voiceItem: voiceItem) {

    }
}


//class MainViewHolder(
//    private val parent: ViewParent,
//    binding: RecyclerItemBinding,
//) : RecyclerView.ViewHolder(binding.root) {
//
//    private val itemImage by lazy {
//        binding.image
//    }
//
//    private val itemDescription by lazy {
//        binding.description
//    }
//
//    fun onBind(item: item) {
//        itemDescription.text = item.description
//
//        Glide.with(this.itemView.context)
//            .load(item.imageUrl)
//            .error(R.mipmap.ic_launcher)
//            .into(itemImage)
//
//
//    }
//}