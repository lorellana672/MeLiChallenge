package com.example.melichallenge.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.melichallenge.Adapters.PicturesAdapter
import com.example.melichallenge.R
import com.example.melichallenge.viewmodel.ItemViewModel

class PictureFragment : DialogFragment() {

    private lateinit var id: String
    private val viewModel by lazy { ViewModelProvider(this).get(ItemViewModel::class.java) }
    private lateinit var adapter: PicturesAdapter
    private lateinit var mContext: Context
    private lateinit var globalView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        globalView = inflater.inflate(R.layout.fragment_picture, container, false)
        if (context != null) {
            initRecyclerView()
        }
        val disabled = globalView.findViewById<LinearLayoutCompat>(R.id.disabled)
        disabled.setOnClickListener {
            dismiss()
        }
        return globalView
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String) = PictureFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        arguments?.getString("id")?.let {
            id = it
        }
    }

    fun observeData(dato: String) {
        viewModel.fetchItemData(dato).observe(this, Observer { item ->
            viewModel.fetchItemPictures(item.id).observe(this, { picture ->
                picture.forEach {
                    adapter.setListData(it.pictures)
                    adapter.notifyDataSetChanged()
                }
            })
        })
    }

    fun initRecyclerView() {
        val hor_rv = globalView.findViewById<RecyclerView>(R.id.frag_rv)
        adapter = PicturesAdapter(context!!)
        hor_rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        hor_rv.adapter = adapter
        observeData(id)
    }

}