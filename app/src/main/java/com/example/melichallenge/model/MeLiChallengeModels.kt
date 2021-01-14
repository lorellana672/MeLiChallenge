package com.example.melichallenge.model

data class Item(
    val id: String,
    val title: String,
    val price: Float,
    val thumbnail: String
)

data class Search(
    val site_id: String,
    val query: String,
    val paging: PagingInfo,
    val results: List<Item>
)

data class PagingInfo(
    val total: Int,
    val offset: Int,
    val limit: Int,
    val primary_results: Int
)

data class PlainText(
    val plain_text: String
)

data class Pictures(
    val url: String
)

data class ListOfPicturs(
    val pictures: List<Pictures>
)