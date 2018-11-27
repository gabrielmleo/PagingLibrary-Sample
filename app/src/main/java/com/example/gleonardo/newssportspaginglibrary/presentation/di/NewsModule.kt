package com.example.gabri.myapplication.presentation.di

import com.example.gabri.myapplication.domain.usecase.base.BaseUseCase
import com.example.gleonardo.newssportspaginglibrary.data.model.DomainResponse
import com.example.gleonardo.newssportspaginglibrary.domain.model.NewsRequestValues
import com.example.gleonardo.newssportspaginglibrary.domain.usecase.GetNewsUseCase
import org.koin.dsl.module.module

val newsModule = module {

    single<BaseUseCase<NewsRequestValues, DomainResponse>> { GetNewsUseCase(get()) }
}