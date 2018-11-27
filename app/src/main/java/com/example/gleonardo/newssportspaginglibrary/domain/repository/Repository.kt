package com.example.gleonardo.newssportspaginglibrary.domain.repository

import com.example.gleonardo.newssportspaginglibrary.data.model.DomainResponse
import io.reactivex.Flowable

interface Repository {
    fun getNews(pageNumber: Int, pageSize: Int): Flowable<DomainResponse>
}