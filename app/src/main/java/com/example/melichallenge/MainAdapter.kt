package com.example.melichallenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.melichallenge.model.Item
import kotlinx.android.synthetic.main.item_row.view.*

class MainAdapter(private val context: Context) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var dataList = mutableListOf<Item>()

    fun setListData(data: List<Item>) {
        dataList = data as MutableList<Item>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = dataList[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0) {
            dataList.size
        } else {
            0
        }
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Item) {
            Glide.with(context).load(item.thumbnail.replaceFirst("http", "https"))
                .into(itemView.image_view)
            itemView.txt_title.text = item.title
            itemView.txt_price.text = item.price.toString()
        }
    }
}
