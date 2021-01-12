package com.example.melichallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.melichallenge.MainAdapter
import com.example.melichallenge.R
import com.example.melichallenge.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private lateinit var selectedItem: SelectedItemFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selectedItem = Fragment.instantiate(this@MainActivity, SelectedItemFragment::class.java.name) as SelectedItemFragment
        adapter = MainAdapter(this)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        inputText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                observeData(inputText.text.toString())
                return@OnKeyListener true
            }
            false
        })
//        observeData()
    }


    //Que se llame cuando haga enter en el editText
    fun observeData(dato: String) {
        shimmer_view_container.startShimmer()
        viewModel.fetchSearchData(dato).observe(this, Observer {
            shimmer_view_container.stopShimmer()
            shimmer_view_container.hideShimmer()
            shimmer_view_container.visibility = View.GONE //El hide no andaba.
            it.forEach{item->
                //Para pasar los items de a 1. Mandar la lista no funciona
                //solo muestraba el ultimo item.
                adapter.setListData(item.results)
                adapter.notifyDataSetChanged()
            }

        })
    }
}