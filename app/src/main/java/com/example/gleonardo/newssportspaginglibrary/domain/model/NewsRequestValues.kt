package com.example.gleonardo.newssportspaginglibrary.domain.model

import com.example.gabri.myapplication.domain.usecase.base.BaseRequestValues

data class NewsRequestValues( val pageNumber: Int,
                              val pageSize:Int ): BaseRequestValues