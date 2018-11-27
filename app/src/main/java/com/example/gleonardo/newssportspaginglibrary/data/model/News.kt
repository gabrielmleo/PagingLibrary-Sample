package com.example.gleonardo.newssportspaginglibrary.data.model

import com.google.gson.annotations.SerializedName

data class News (
        @SerializedName("title") val title: String? = null,
        @SerializedName("urlToImage") val image: String? = null
)