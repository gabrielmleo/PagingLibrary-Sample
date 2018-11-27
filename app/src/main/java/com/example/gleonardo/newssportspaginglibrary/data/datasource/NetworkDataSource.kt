package com.example.gabri.myapplication.data.datasource

import com.example.gleonardo.newssportspaginglibrary.data.model.Response
import io.reactivex.Flowable

interface NetworkDataSource {
    fun getNews(pageNumber: Int, pageSize:Int): Flowable<Response>
}