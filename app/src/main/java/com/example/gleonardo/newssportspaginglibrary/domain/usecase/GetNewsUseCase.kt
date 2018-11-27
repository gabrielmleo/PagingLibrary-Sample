package com.example.gleonardo.newssportspaginglibrary.domain.usecase

import com.example.gabri.myapplication.domain.usecase.base.BaseUseCase
import com.example.gleonardo.newssportspaginglibrary.data.model.DomainResponse
import com.example.gleonardo.newssportspaginglibrary.domain.model.NewsRequestValues
import com.example.gleonardo.newssportspaginglibrary.domain.model.exception.RequestValuesNotImplementedException
import com.example.gleonardo.newssportspaginglibrary.domain.repository.Repository
import io.reactivex.Flowable

class GetNewsUseCase(val repository: Repository)
    : BaseUseCase<NewsRequestValues, DomainResponse>() {
    override fun executeUseCase(requestValues: NewsRequestValues?): Flowable<DomainResponse> {
        return requestValues?.let {
            repository.getNews(it.pageNumber, it.pageSize)
        } ?: Flowable.error(RequestValuesNotImplementedException())
    }
}