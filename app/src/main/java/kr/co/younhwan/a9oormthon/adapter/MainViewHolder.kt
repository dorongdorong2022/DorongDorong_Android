package kr.co.younhwan.a9oormthon.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import kr.co.younhwan.a9oormthon.R
import kr.co.younhwan.a9oormthon.data.item
import kr.co.younhwan.a9oormthon.data.soundItem
import kr.co.younhwan.a9oormthon.databinding.RecyclerItemBinding

class MainViewHolder(
    private val parent: ViewParent,
    binding: RecyclerItemBinding,
    private val listenerFuncOfLocation: ((soundItem) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    private val itemImage by lazy {
        binding.image
    }

    private val itemDescription by lazy {
        binding.description
    }

    fun onBind(item: soundItem) {
        itemDescription.text = item.jejuSoundMsg

        Glide.with(this.itemView.context)
            .load(item.jejuSoundThumbnailImgUrl)
            .error(R.mipmap.ic_launcher)
            .into(itemImage)

        itemView.setOnClickListener {
            listenerFuncOfLocation?.invoke(item)
        }

        if(item.jejuSoundUrl.isBlank() || item.jejuSoundImgUrl.isBlank()){
            itemView.isEnabled = false
            itemImage.isEnabled = false
        }
    }
}