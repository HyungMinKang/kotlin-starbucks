package com.codesquad.starbucks.di

import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.data.EventRepositoryImpl
import com.codesquad.starbucks.data.remote.event.EventApi
import com.codesquad.starbucks.data.remote.event.EventDataSource
import com.codesquad.starbucks.data.remote.event.EventRemoteDataSource
import com.codesquad.starbucks.domain.EventRepository
import com.codesquad.starbucks.ui.event.EventViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {


    single{ OkHttpClient.Builder().addInterceptor(get<HttpLoggingInterceptor>()).build() }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<Moshi>{
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Constants.EVENT_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
            .client(get())
            .build()
    }

    single<EventApi> {
        get<Retrofit>().create(EventApi::class.java)
    }
    single<EventDataSource> { EventRemoteDataSource(get()) }
    single<EventRepository> { EventRepositoryImpl(get()) }


    viewModel { EventViewModel(get()) }
}