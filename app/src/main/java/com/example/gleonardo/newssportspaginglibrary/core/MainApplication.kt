package com.example.gleonardo.newssportspaginglibrary.core

import android.app.Application
import com.example.gabri.myapplication.presentation.di.dataModule
import com.example.gabri.myapplication.presentation.di.networkModule
import com.example.gabri.myapplication.presentation.di.newsModule
import com.example.gabri.myapplication.presentation.di.viewModelModule
import org.koin.android.ext.android.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(dataModule, viewModelModule, newsModule, networkModule))
    }
}