package com.example.melichallenge.model

data class Item(
    val id: String,
    val title: String,
    val price: Float,
    val thumbnail: String,
    val error: Boolean
)

data class Search(
    val site_id: String,
    val query: String,
    val results: List<Item>,
    val error: Boolean
)

data class PlainText(
    val plain_text: String
)

data class Pictures(
    val url: String
)

data class ListOfPictures(
    val pictures: List<Pictures>
)