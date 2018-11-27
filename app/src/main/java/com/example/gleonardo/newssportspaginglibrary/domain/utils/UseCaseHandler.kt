package com.example.gleonardo.newssportspaginglibrary.domain.utils

import com.example.gabri.myapplication.domain.usecase.base.BaseRequestValues
import com.example.gabri.myapplication.domain.usecase.base.BaseUseCase
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object UseCaseHandler {

    fun <RV : BaseRequestValues, T> execute(useCase: BaseUseCase<RV, T>, values: RV? = null): Flowable<T> {
        useCase.setRequestValues(values)
        return useCase.run()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}