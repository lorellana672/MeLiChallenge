package com.example.melichallenge.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.melichallenge.R

class PicturesAdapter(private val context: Context, private val picturesList: List<String>): PagerAdapter() {

    private var layoutInflater: LayoutInflater? = null
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater!!.inflate(R.layout.pictures_layout, container, false)

        return super.instantiateItem(container, position)
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        return picturesList.size
    }

}