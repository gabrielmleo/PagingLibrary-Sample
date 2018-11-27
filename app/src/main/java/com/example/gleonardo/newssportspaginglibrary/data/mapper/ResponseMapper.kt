package com.example.gleonardo.newssportspaginglibrary.data.mapper

import com.example.gleonardo.newssportspaginglibrary.data.model.DomainNews
import com.example.gleonardo.newssportspaginglibrary.data.model.DomainResponse
import com.example.gleonardo.newssportspaginglibrary.data.model.News
import com.example.gleonardo.newssportspaginglibrary.data.model.Response
import com.example.gleonardo.newssportspaginglibrary.domain.mapper.BaseMapper

object ResponseMapper: BaseMapper<Response, DomainResponse>() {
    override fun transformFrom(source: DomainResponse): Response {
        val responseNews : MutableList<News> = mutableListOf()
        source.news?.forEach {
            it?.apply {
                responseNews.add(
                        NewsMapper.transformFrom(this)
                )
            }
        }
        return Response(
                news = responseNews
        )
    }

    override fun transformTo(source: Response): DomainResponse {
        val domainResponseNews : MutableList<DomainNews> = mutableListOf()
        source.news?.forEach {
            it?.apply {
                domainResponseNews.add(
                        NewsMapper.transformTo(this)
                )
            }
        }
        return DomainResponse(
                news = domainResponseNews
        )
    }
}