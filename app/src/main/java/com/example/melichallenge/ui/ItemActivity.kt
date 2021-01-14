package com.example.melichallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.melichallenge.R
import com.example.melichallenge.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.android.synthetic.main.activity_main.*

class ItemActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(ItemViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        if (intent.extras != null) {
            //Trae el id del item seleccionado
            intent.getStringExtra("id")?.let { observeData(it) }
        }
    }

    fun observeData(dato: String) {
        viewModel.fetchItemData(dato).observe(this, Observer {item->
            item_title.setText(item.title)
            item_price.setText("$" + item.price.toString())
            viewModel.fetchItemDescription(item.id).observe(this, {desc->
                item_des.setText(desc.plain_text)
            })
            viewModel.fetchItemPictures(item.id).observe(this, {picture->
                Log.d("PASDAS", ""+picture)
            })

        })
    }
}