package kr.co.younhwan.a9oormthon.view.main.record.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.NonDisposableHandle.parent
import kr.co.younhwan.a9oormthon.adapter.MainViewHolder
import kr.co.younhwan.a9oormthon.data.item
import kr.co.younhwan.a9oormthon.databinding.RecyclerItemBinding
import kr.co.younhwan.a9oormthon.databinding.RecyclerRecordItemBinding
import kr.co.younhwan.a9oormthon.view.main.record.adapter.contract.RecordAdapterContract
import kr.co.younhwan.a9oormthon.view.main.record.presenter.RecordContract

class RecordAdapter :
    RecyclerView.Adapter<RecordViewHolder>(),
    RecordAdapterContract.Model,
    RecordAdapterContract.View {

    // 리사이클러뷰 아이템
    private var itemList: ArrayList<String> = ArrayList()

    override fun notifyAdapter() = notifyDataSetChanged()

    override fun addItems(items: ArrayList<String>) {
        itemList = items
    }

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerRecordItemBinding.inflate(inflater, parent, false)
        return RecordViewHolder(
            parent = parent,
            binding = binding
        )
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
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

            outRect.bottom = (10 * density).toInt()
        }
    }
}