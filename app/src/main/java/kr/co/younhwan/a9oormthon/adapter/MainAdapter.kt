package kr.co.younhwan.a9oormthon.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import kr.co.younhwan.a9oormthon.data.item
import kr.co.younhwan.a9oormthon.databinding.RecyclerItemBinding
import kr.co.younhwan.a9oormthon.adapter.contract.MainAdapterContract
import kr.co.younhwan.a9oormthon.data.soundItem

class MainAdapter :
    RecyclerView.Adapter<MainViewHolder>(),
    MainAdapterContract.View,
    MainAdapterContract.Model {

    // 리사이클러뷰 아이템
    private var itemList: ArrayList<soundItem> = ArrayList()

    // 이벤트 리스너
    override var onClickFunOfLocation: ((soundItem) -> Unit)? = null

    override fun getItemCount() = itemList.size

    override fun notifyAdapter() = notifyDataSetChanged()

    override fun addItems(items: ArrayList<soundItem>) {
        itemList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(
            parent = parent,
            binding = binding,
            listenerFuncOfLocation = onClickFunOfLocation
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        itemList[position].let {
            holder.onBind(it)
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