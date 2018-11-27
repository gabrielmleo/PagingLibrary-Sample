package com.example.gabri.myapplication.presentation.di

import com.example.gabri.myapplication.data.datasource.NetworkDataSource
import com.example.gabri.myapplication.data.datasource.NetworkDataSourceImpl
import com.example.gleonardo.newssportspaginglibrary.data.repository.RepositoryImpl
import com.example.gleonardo.newssportspaginglibrary.domain.repository.Repository
import org.koin.dsl.module.module

val dataModule = module {

    single<NetworkDataSource> { NetworkDataSourceImpl(get()) }
    single<Repository> { RepositoryImpl(get()) }
}