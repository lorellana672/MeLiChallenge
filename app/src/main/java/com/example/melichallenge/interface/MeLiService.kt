package com.example.melichallenge.`interface`

import com.example.melichallenge.model.Item
import com.example.melichallenge.model.Search
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeLiService {

    @GET("/sites/MLA/search")
    fun itemSearch(@Query("q") query: String): Call<Search>

    @GET("/items/{item}/description?attributes=plain_text")
    fun itemDescription(@Path("item") item: String): Call<Item>
}