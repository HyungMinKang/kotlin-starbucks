package com.codesquad.starbucks.di

import com.codesquad.starbucks.room.FavoriteDataBase
import com.codesquad.starbucks.ui.event.EventViewModel
import com.codesquad.starbucks.ui.favorite.FavoriteViewModel
import com.codesquad.starbucks.ui.home.HomeViewModel
import com.codesquad.starbucks.ui.order.category.CategoryDetailViewModel
import com.codesquad.starbucks.ui.product.ProductDetailViewModel
import com.codesquad.starbucks.ui.whatNew.WhatNewViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    val applicationScope = CoroutineScope(SupervisorJob())
    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        FavoriteDataBase.getInstance(androidApplication(), applicationScope)
    }

    single {
        get<FavoriteDataBase>().favoriteDao()
    }

    viewModel { EventViewModel(get()) }

    viewModel { HomeViewModel(get()) }

    viewModel { WhatNewViewModel(get()) }

    viewModel { CategoryDetailViewModel(get()) }

    viewModel { ProductDetailViewModel(get()) }

    viewModel { FavoriteViewModel(get()) }
}