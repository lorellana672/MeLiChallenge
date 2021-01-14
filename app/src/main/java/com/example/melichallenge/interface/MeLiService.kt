package com.example.melichallenge.`interface`

import com.example.melichallenge.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeLiService {

    @GET("/sites/MLA/search")
    fun itemSearch(@Query("q") query: String): Call<Search>

    @GET("/items/{item}")
    fun itemDescription(@Path("item") item: String): Call<Item>

    @GET("/items/{item}/description?attributes=plain_text")
    fun itemPlainText(@Path("item")item: String): Call<PlainText>

    @GET("/items/{item}?attributes=pictures")
    fun itemImages(@Path("item")item: String): Call<ListOfPictures>
}