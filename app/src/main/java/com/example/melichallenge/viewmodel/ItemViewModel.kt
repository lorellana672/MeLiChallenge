package com.example.melichallenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.melichallenge.`interface`.MeLiService
import com.example.melichallenge.apicall.MeLiChallengeApiCall
import com.example.melichallenge.model.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemViewModel: ViewModel() {

    fun fetchItemData(itemId: String): MutableLiveData<Item> {
        val mutableData = MutableLiveData<Item>()
        val itemService: MeLiService = MeLiChallengeApiCall.getMeLiChallengeApiCall().create(
            MeLiService::class.java)
        val result: Call<Item> = itemService.itemDescription(itemId)
        result.enqueue(object: Callback<Item> {
            override fun onFailure(call: Call<Item>, t: Throwable) {
                Log.d("Busqueda", "Error: " + t)
            }

            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                response.body().let {
                    mutableData.value = it
                }
            }

        })
        return mutableData
    }
}