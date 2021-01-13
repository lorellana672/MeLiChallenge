package com.example.melichallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.melichallenge.R
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        if(intent.extras != null) {
            Glide.with(this).load(intent.getStringExtra("data")?.replaceFirst("http", "https"))
                .into(photo_view)
        }
    }
}