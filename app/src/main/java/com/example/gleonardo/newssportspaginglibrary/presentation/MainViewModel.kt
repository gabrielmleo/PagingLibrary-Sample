package com.example.gleonardo.newssportspaginglibrary.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.gleonardo.newssportspaginglibrary.data.datasource.NetworkService
import com.example.gleonardo.newssportspaginglibrary.data.datasource.NewsDataSource
import com.example.gleonardo.newssportspaginglibrary.data.datasource.NewsDataSourceFactory
import com.example.gleonardo.newssportspaginglibrary.data.enum.State
import com.example.gleonardo.newssportspaginglibrary.data.model.News
import io.reactivex.disposables.CompositeDisposable

class MainViewModel: ViewModel() {

    private val networkService = NetworkService.getService()
    lateinit var newsList: LiveData<PagedList<News>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private val newsDataSourceFactory: NewsDataSourceFactory

    init {
        newsDataSourceFactory = NewsDataSourceFactory(compositeDisposable, networkService)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()
        newsList = LivePagedListBuilder<Int, News>(newsDataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> = Transformations.switchMap<NewsDataSource,
            State>(newsDataSourceFactory.newsDataSourceLiveData, NewsDataSource::state)

    fun retry() {
        newsDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}