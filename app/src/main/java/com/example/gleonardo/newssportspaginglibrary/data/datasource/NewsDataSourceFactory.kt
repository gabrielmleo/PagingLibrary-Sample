package com.example.gleonardo.newssportspaginglibrary.data.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.gleonardo.newssportspaginglibrary.data.model.News
import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory (
        private val compositeDisposable: CompositeDisposable,
        private val networkService: NetworkService): DataSource.Factory<Int,News>() {

    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, News> {
        val newsDataSource = NewsDataSource(networkService, compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}