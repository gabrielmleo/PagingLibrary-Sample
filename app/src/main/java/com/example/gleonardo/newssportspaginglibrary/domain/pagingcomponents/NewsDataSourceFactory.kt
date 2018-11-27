package com.example.gleonardo.newssportspaginglibrary.domain.pagingcomponents

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.gabri.myapplication.domain.usecase.base.BaseRequestValues
import com.example.gabri.myapplication.domain.usecase.base.BaseUseCase
import com.example.gleonardo.newssportspaginglibrary.data.datasource.NetworkService
import com.example.gleonardo.newssportspaginglibrary.data.model.DomainNews
import com.example.gleonardo.newssportspaginglibrary.data.model.DomainResponse
import com.example.gleonardo.newssportspaginglibrary.data.model.News
import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory (
        private val compositeDisposable: CompositeDisposable,
        private val getNewsUseCase: BaseUseCase<BaseRequestValues, DomainResponse>): DataSource.Factory<Int,DomainNews>() {

    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, DomainNews> {
        val newsDataSource = NewsDataSource(getNewsUseCase, compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}