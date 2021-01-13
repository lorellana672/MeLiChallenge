package com.example.melichallenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.melichallenge.`interface`.MeLiService
import com.example.melichallenge.apicall.MeLiChallengeApiCall
import com.example.melichallenge.model.Search
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    fun fetchSearchData(query: String): LiveData<MutableList<Search>> {
        val mutableData = MutableLiveData<MutableList<Search>>()

        val listData = mutableListOf<Search>()
        //query para traer los datos
        val itemService: MeLiService = MeLiChallengeApiCall.getMeLiChallengeApiCall().create(
            MeLiService::class.java)
        val result: Call<Search> = itemService.itemSearch(query)
        result.enqueue(object : Callback<Search> {
            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                listData.add(response.body()!!)
                mutableData.value = listData
            }
            override fun onFailure(call: Call<Search>, t: Throwable) {
                Log.d("Busqueda", "Error: " + t)
            }
        })
//            listData.add()
        Log.d("Busqueda", "retorna al main" + mutableData)
        return mutableData
    }
}