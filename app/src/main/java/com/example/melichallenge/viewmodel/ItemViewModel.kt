package com.example.melichallenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.melichallenge.`interface`.MeLiService
import com.example.melichallenge.apicall.MeLiChallengeApiCall
import com.example.melichallenge.model.Item
import com.example.melichallenge.model.ListOfPictures
import com.example.melichallenge.model.Pictures
import com.example.melichallenge.model.PlainText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemViewModel : ViewModel() {

    fun fetchItemData(itemId: String): MutableLiveData<Item> {
        val mutableData = MutableLiveData<Item>()
        val itemService: MeLiService = MeLiChallengeApiCall.getMeLiChallengeApiCall().create(
            MeLiService::class.java
        )
        val result: Call<Item> = itemService.itemDescription(itemId)
        result.enqueue(object : Callback<Item> {
            override fun onFailure(call: Call<Item>, t: Throwable) {
                Log.d("ViewModel", "Error: " + t)
                mutableData.value = null
            }

            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                if (response.body() != null) {
                    response.body().let {
                        mutableData.value = it
                    }
                } else {
                    mutableData.value = null
                }
            }

        })
        return mutableData
    }

    fun fetchItemDescription(itemId: String): MutableLiveData<PlainText> {
        val mutableData = MutableLiveData<PlainText>()
        val itemService: MeLiService = MeLiChallengeApiCall.getMeLiChallengeApiCall().create(
            MeLiService::class.java
        )
        val result: Call<PlainText> = itemService.itemPlainText(itemId)
        result.enqueue(object : Callback<PlainText> {
            override fun onResponse(call: Call<PlainText>, response: Response<PlainText>) {
                response.body().let { mutableData.value = it }
            }

            override fun onFailure(call: Call<PlainText>, t: Throwable) {
                Log.d("ViewModel", "Error: " + t)
            }

        })
        return mutableData
    }

    fun fetchItemPictures(itemId: String): LiveData<MutableList<ListOfPictures>> {
        val mutableData = MutableLiveData<MutableList<ListOfPictures>>()
        val listData = mutableListOf<ListOfPictures>()
        val itemService: MeLiService = MeLiChallengeApiCall.getMeLiChallengeApiCall().create(
            MeLiService::class.java
        )
        val result: Call<ListOfPictures> = itemService.itemImages(itemId)
        result.enqueue(object : Callback<ListOfPictures> {
            override fun onResponse(
                call: Call<ListOfPictures>,
                response: Response<ListOfPictures>
            ) {
                listData.add(response.body()!!)
                mutableData.value = listData
            }

            override fun onFailure(call: Call<ListOfPictures>, t: Throwable) {
                Log.d("ViewModel", "Error: " + t)
            }

        })
        return mutableData

    }
}