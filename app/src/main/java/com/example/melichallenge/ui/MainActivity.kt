package com.example.melichallenge.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melichallenge.MainAdapter
import com.example.melichallenge.R
import com.example.melichallenge.`interface`.OnItemClickListener
import com.example.melichallenge.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var adapter: MainAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = MainAdapter(this, this)
        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv.adapter = adapter

        inputText.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                observeData(inputText.text.toString())
                val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(search_bar?.rootView?.windowToken, 0)
                return@OnKeyListener true
            }
            false
        })
    }


    //Que se llame cuando haga enter en el editText
    fun observeData(dato: String) {
        shimmer_view_container.startShimmer()
        viewModel.fetchSearchData(dato).observe(this, Observer {
            shimmer_view_container.stopShimmer()
            shimmer_view_container.hideShimmer()
            shimmer_view_container.visibility = View.GONE //El hide no andaba.
            it.forEach { item ->
                //Para pasar los items de a 1. Mandar la lista no funciona
                //solo muestraba el ultimo item.
                adapter.setListData(item.results)
                adapter.notifyDataSetChanged()
            }

        })
    }

    override fun onItemClick(id: String) {
        val intent = Intent(this@MainActivity, ItemActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onImgClick(img: String) {
        val intent = Intent(this@MainActivity, ItemActivity::class.java)
        intent.putExtra("data", img)
        startActivity(intent)
    }
}