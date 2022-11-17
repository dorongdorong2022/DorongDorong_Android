package kr.co.younhwan.a9oormthon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.younhwan.a9oormthon.data.item
import kr.co.younhwan.a9oormthon.databinding.RecyclerItemBinding
import kr.co.younhwan.a9oormthon.adapter.contract.MainAdapterContract

class MainAdapter :
    RecyclerView.Adapter<MainViewHolder>(),
    MainAdapterContract.View,
    MainAdapterContract.Model {

    // 리사이클러뷰 아이템
    private var itemList: ArrayList<item> = ArrayList()

    // 이벤트 리스너
    // ..
    // ..

    override fun getItemCount() = itemList.size

    override fun notifyAdapter() = notifyDataSetChanged()

    override fun addItems(items: ArrayList<item>) {
        itemList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(
            parent = parent,
            binding = binding
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        itemList[position].let {
            holder.onBind(it)
        }
    }
}