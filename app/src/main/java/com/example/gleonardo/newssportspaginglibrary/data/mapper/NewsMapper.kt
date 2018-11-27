package com.example.gleonardo.newssportspaginglibrary.data.mapper

import com.example.gleonardo.newssportspaginglibrary.data.model.DomainNews
import com.example.gleonardo.newssportspaginglibrary.data.model.News
import com.example.gleonardo.newssportspaginglibrary.domain.mapper.BaseMapper

object NewsMapper: BaseMapper<News, DomainNews>() {
    override fun transformFrom(source: DomainNews): News {
        return News(
                title = source.title,
                image = source.image
        )
    }

    override fun transformTo(source: News): DomainNews {
        return DomainNews(
                title = source.title,
                image = source.image
        )
    }
}