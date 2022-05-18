package com.codesquad.starbucks.di

import com.codesquad.starbucks.ui.event.EventViewModel
import com.codesquad.starbucks.ui.home.HomeViewModel
import com.codesquad.starbucks.ui.order.category.CategoryDetailViewModel
import com.codesquad.starbucks.ui.product.ProductDetailViewModel
import com.codesquad.starbucks.ui.whatNew.WhatNewViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Moshi>{
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    viewModel { EventViewModel(get()) }

    viewModel { HomeViewModel(get())}

    viewModel { WhatNewViewModel(get())}

    viewModel { CategoryDetailViewModel(get()) }

    viewModel { ProductDetailViewModel(get())}
}