package com.example.melichallenge.`interface`

import com.example.melichallenge.model.Search
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MeLiService {

    @GET("/sites/MLA/search")
    fun itemSearch(@Query("q") query: String): Call<Search>
}