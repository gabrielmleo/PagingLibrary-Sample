package com.example.gleonardo.newssportspaginglibrary.data.datasource

import com.example.gleonardo.newssportspaginglibrary.data.model.Response
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything?q=sports&apiKey=aa67d8d98c8e4ad1b4f16dbd5f3be348")
    fun getNews(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Flowable<Response>
}