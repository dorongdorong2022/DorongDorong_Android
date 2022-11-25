package kr.co.younhwan.a9oormthon.view.select.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import kr.co.younhwan.a9oormthon.adapter.MainViewHolder
import kr.co.younhwan.a9oormthon.adapter.contract.MainAdapterContract
import kr.co.younhwan.a9oormthon.data.item
import kr.co.younhwan.a9oormthon.data.voiceItem
import kr.co.younhwan.a9oormthon.databinding.RecyclerEmptyVoiceItemBinding
import kr.co.younhwan.a9oormthon.databinding.RecyclerItemBinding
import kr.co.younhwan.a9oormthon.databinding.RecyclerVoiceItemBinding
import kr.co.younhwan.a9oormthon.view.select.adapter.contract.VoiceAdapterContract

class VoiceAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    VoiceAdapterContract.Model,
    VoiceAdapterContract.View {
    // 리사이클러뷰 아이템
    private var itemList: ArrayList<voiceItem> = ArrayList()

    // 이벤트 리스너
    override var onClickFunOfBtn: ((voiceItem, LottieAnimationView) -> Unit)? = null

    override var onClickFunOfBtn2: ((Unit) -> Unit)? = null

    // 메서드 오버라이딩
    override fun getItemCount() = itemList.size

    override fun notifyAdapter() = notifyDataSetChanged()

    override fun addItems(items: ArrayList<voiceItem>) {
        itemList = items
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == 2) {
            val binding = RecyclerEmptyVoiceItemBinding.inflate(inflater, parent, false)
            VoiceEmptyViewHolder(
                parent = parent,
                binding = binding,
                listenerFuncOfBtn = onClickFunOfBtn2
            )
        } else {
            val binding = RecyclerVoiceItemBinding.inflate(inflater, parent, false)
            VoiceViewHolder(
                parent = parent,
                binding = binding,
                listenerFuncOfBtn = onClickFunOfBtn
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            1 -> {
                itemList[position].let {
                    (holder as VoiceViewHolder).onBind(it)
                }
            }

            2 -> {
                (holder as VoiceEmptyViewHolder).onBind()
            }

            else -> {
                itemList[position].let {
                    (holder as VoiceViewHolder).onBind(it)
                }
            }
        }
    }

    inner class RecyclerDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            val itemPosition = parent.getChildAdapterPosition(view)
            val density = parent.resources.displayMetrics.density

            outRect.bottom = (20 * density).toInt()
        }
    }
}
