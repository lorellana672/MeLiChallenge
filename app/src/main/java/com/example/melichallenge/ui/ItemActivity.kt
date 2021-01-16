package com.example.melichallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melichallenge.Adapters.PicturesAdapter
import com.example.melichallenge.R
import com.example.melichallenge.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(ItemViewModel::class.java) }
    private lateinit var adapter: PicturesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        adapter = PicturesAdapter(this)
        hor_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        hor_rv.adapter = adapter
        if (intent.extras != null) {
            //Trae el id del item seleccionado
            intent.getStringExtra("id")?.let { observeData(it) }
        }
    }

    fun observeData(dato: String) {
        viewModel.fetchItemData(dato).observe(this, Observer { item ->
            if (item == null) {
                error_img.visibility = View.VISIBLE
                item_scroll_view.visibility = View.GONE
                error_img.setBackgroundResource(R.drawable.error_bg)
            } else {
                viewModel.fetchItemPictures(item.id).observe(this, { picture ->
                    picture.forEach {
                        adapter.setListData(it.pictures)
                        adapter.notifyDataSetChanged()
                    }
                })
                item_title.setText(item.title)
                item_price.setText("$" + item.price.toString())
                viewModel.fetchItemDescription(item.id).observe(this, { desc ->
                    item_des.setText(desc.plain_text)
                })
            }
        })
    }
}