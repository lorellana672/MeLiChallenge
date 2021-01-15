package com.example.melichallenge.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.melichallenge.R
import com.example.melichallenge.model.Pictures
import kotlinx.android.synthetic.main.item_row.view.*
import kotlinx.android.synthetic.main.picture_row.view.*

class PicturesAdapter(private val context: Context): RecyclerView.Adapter<PicturesAdapter.PicturesViewHolder>() {

   private var dataList = mutableListOf<Pictures>()

    fun setListData(data: List<Pictures>) {
        dataList = data as MutableList<Pictures>
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PicturesAdapter.PicturesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.picture_row, parent, false)
        return PicturesViewHolder(view)
    }

    override fun onBindViewHolder(holder: PicturesAdapter.PicturesViewHolder, position: Int) {
        val picture = dataList[position]
        holder.bindView(picture)
    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0) {
            dataList.size
        } else {
            0
        }
    }

    inner class PicturesViewHolder(pictureView: View): RecyclerView.ViewHolder(pictureView) {
        fun bindView(picture: Pictures) {
            Glide.with(context).load(picture.url.replaceFirst("http", "https"))
                .into(itemView.picture_view)
        }
    }

}