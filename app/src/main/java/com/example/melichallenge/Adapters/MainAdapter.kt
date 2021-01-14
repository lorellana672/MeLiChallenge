package com.example.melichallenge.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.melichallenge.R
import com.example.melichallenge.`interface`.OnItemClickListener
import com.example.melichallenge.model.Item
import kotlinx.android.synthetic.main.item_row.view.*

class MainAdapter(private val context: Context, private val itemClick: OnItemClickListener) :
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
            if (!item.error) {
                itemView.setOnClickListener { itemClick.onItemClick(item.id/*Aca va el id para referenciar al item en el fragment*/) }
                itemView.image_view.setOnClickListener {
                    itemClick.onImgClick(item.thumbnail/*Aca va la lista de imagenes*/)
                }
                Glide.with(context).load(item.thumbnail.replaceFirst("http", "https"))
                    .into(itemView.image_view)
                itemView.txt_title.text = item.title
                itemView.txt_price.text = item.price.toString()
            }
        }
    }
}
