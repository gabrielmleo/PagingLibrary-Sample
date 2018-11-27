package com.example.gabri.myapplication.data.datasource

import com.example.gleonardo.newssportspaginglibrary.data.datasource.ApiService
import com.example.gleonardo.newssportspaginglibrary.data.model.Response
import io.reactivex.Flowable

class NetworkDataSourceImpl(private val apiService: ApiService) : NetworkDataSource {
    override fun getNews(pageNumber: Int, pageSize: Int): Flowable<Response> {
        return apiService.getNews(pageNumber, pageSize)
    }
}