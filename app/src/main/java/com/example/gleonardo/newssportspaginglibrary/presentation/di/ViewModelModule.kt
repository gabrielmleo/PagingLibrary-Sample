package com.example.gabri.myapplication.presentation.di

import com.example.gleonardo.newssportspaginglibrary.presentation.view.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}