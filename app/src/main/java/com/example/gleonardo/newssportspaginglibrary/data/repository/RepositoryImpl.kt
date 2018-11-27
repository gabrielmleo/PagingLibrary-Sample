package com.example.gleonardo.newssportspaginglibrary.data.repository

import com.example.gabri.myapplication.data.datasource.NetworkDataSource
import com.example.gleonardo.newssportspaginglibrary.data.mapper.ResponseMapper
import com.example.gleonardo.newssportspaginglibrary.data.model.DomainResponse
import com.example.gleonardo.newssportspaginglibrary.domain.repository.Repository
import io.reactivex.Flowable

class RepositoryImpl(var networkDataSource: NetworkDataSource) : Repository {
    override fun getNews(pageNumber: Int, pageSize: Int): Flowable<DomainResponse> {
        return networkDataSource.getNews(pageNumber, pageSize).map {
            ResponseMapper.transformTo(it)
        }
    }
}