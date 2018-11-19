package com.example.gleonardo.newssportspaginglibrary.data.model

import com.google.gson.annotations.SerializedName

data class Response(
        @SerializedName("articles") val news: List<News>
)