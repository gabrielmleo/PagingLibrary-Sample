package com.example.gleonardo.newssportspaginglibrary.presentation.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.gabri.myapplication.domain.usecase.base.BaseRequestValues
import com.example.gabri.myapplication.domain.usecase.base.BaseUseCase
import com.example.gleonardo.newssportspaginglibrary.data.datasource.NetworkService
import com.example.gleonardo.newssportspaginglibrary.data.enum.State
import com.example.gleonardo.newssportspaginglibrary.data.model.DomainNews
import com.example.gleonardo.newssportspaginglibrary.data.model.DomainResponse
import com.example.gleonardo.newssportspaginglibrary.data.model.News
import com.example.gleonardo.newssportspaginglibrary.domain.pagingcomponents.NewsDataSource
import com.example.gleonardo.newssportspaginglibrary.domain.pagingcomponents.NewsDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(getNewsUseCase: BaseUseCase<BaseRequestValues, DomainResponse>) : ViewModel() {

    lateinit var newsList: LiveData<PagedList<DomainNews>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private val newsDataSourceFactory: NewsDataSourceFactory

    init {
        newsDataSourceFactory = NewsDataSourceFactory(compositeDisposable, getNewsUseCase)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()
        newsList = LivePagedListBuilder<Int, DomainNews>(newsDataSourceFactory, config).build()
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