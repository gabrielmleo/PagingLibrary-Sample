package com.example.gleonardo.newssportspaginglibrary.domain.pagingcomponents

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.example.gabri.myapplication.domain.usecase.base.BaseRequestValues
import com.example.gabri.myapplication.domain.usecase.base.BaseUseCase
import com.example.gleonardo.newssportspaginglibrary.data.enum.State
import com.example.gleonardo.newssportspaginglibrary.data.model.DomainNews
import com.example.gleonardo.newssportspaginglibrary.data.model.DomainResponse
import com.example.gleonardo.newssportspaginglibrary.data.model.News
import com.example.gleonardo.newssportspaginglibrary.domain.model.NewsRequestValues
import com.example.gleonardo.newssportspaginglibrary.domain.utils.UseCaseHandler
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class NewsDataSource(
        private val getNewsUseCase: BaseUseCase<BaseRequestValues, DomainResponse>,
        private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, DomainNews>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, DomainNews>) {
        updateState(State.LOADING)
        val requestValues = NewsRequestValues(1, 5)
        val useCaseExecute = UseCaseHandler.execute(getNewsUseCase, requestValues)
        compositeDisposable.add(useCaseExecute.subscribe(
                { response ->
                    updateState(State.DONE)
                    response.news?.let {
                        callback.onResult(it, null, 2)
                    }
                },
                {
                    updateState(State.ERROR)
                    setRetry(Action { loadInitial(params, callback) })
                }
        )

        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DomainNews>) {
        updateState(State.LOADING)
        val requestValues = NewsRequestValues(params.key, 5)
        val useCaseExecute = UseCaseHandler.execute(getNewsUseCase, requestValues)
        compositeDisposable.addAll(useCaseExecute.subscribe(
                { response ->
                    updateState(State.DONE)
                    response.news?.let {
                        callback.onResult(it, params.key + 1)
                    }
                },
                {
                    updateState(State.ERROR)
                    setRetry(Action { loadAfter(params, callback) })
                })

        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DomainNews>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    private fun setRetry(action: Action?) {
        retryCompletable = action?.let { Completable.fromAction(it) }
    }

    fun retry() {
        retryCompletable?.apply {
            compositeDisposable.add(this
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe())
        }
    }


}