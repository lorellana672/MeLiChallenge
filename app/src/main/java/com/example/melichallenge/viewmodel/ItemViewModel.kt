package com.example.melichallenge.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.melichallenge.`interface`.MeLiService
import com.example.melichallenge.apicall.MeLiChallengeApiCall
import com.example.melichallenge.model.Item
import com.example.melichallenge.model.ListOfPictures
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
                Log.d("Busqueda", "Error: " + t)
            }

            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                Log.d("PASDAS", "response:  " + response.body())
                response.body().let {
                    mutableData.value = it
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
                TODO("Not yet implemented")
            }

        })
        return mutableData
    }

    fun fetchItemPictures(itemId: String): MutableLiveData<ListOfPictures> {
        val mutableData = MutableLiveData<ListOfPictures>()
        val itemService: MeLiService = MeLiChallengeApiCall.getMeLiChallengeApiCall().create(
            MeLiService::class.java
        )
        val result: Call<ListOfPictures> = itemService.itemImages(itemId)
        result.enqueue(object : Callback<ListOfPictures> {
            override fun onResponse(call: Call<ListOfPictures>, response: Response<ListOfPictures>) {
                response.body().let { mutableData.value = it }
            }

            override fun onFailure(call: Call<ListOfPictures>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return mutableData

    }
}