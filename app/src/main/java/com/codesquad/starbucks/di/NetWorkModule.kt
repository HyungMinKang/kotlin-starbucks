package com.codesquad.starbucks.di

import com.codesquad.starbucks.common.Constants
import com.codesquad.starbucks.data.EventRepositoryImpl
import com.codesquad.starbucks.data.HomeRepositoryImpl
import com.codesquad.starbucks.data.remote.event.EventApi
import com.codesquad.starbucks.data.remote.event.EventDataSource
import com.codesquad.starbucks.data.remote.event.EventRemoteDataSource
import com.codesquad.starbucks.data.remote.homeContent.HomeContentApi
import com.codesquad.starbucks.data.remote.homeContent.HomeContentDataSource
import com.codesquad.starbucks.data.remote.homeContent.HomeContentRemoteDataSource
import com.codesquad.starbucks.data.remote.homeContent.ProductContentApi
import com.codesquad.starbucks.domain.EventRepository
import com.codesquad.starbucks.domain.HomeRepository
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val NetWorkModule = module {

    single{ OkHttpClient.Builder().build() }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<Retrofit>(named("EventRetrofit")) {
        Retrofit.Builder()
            .baseUrl(Constants.EVENT_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
            .client(get())
            .build()
    }

    single<Retrofit>(named("HomeContentRetrofit")) {
        Retrofit.Builder()
            .baseUrl(Constants.HOME_CONTENT_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
            .client(get())
            .build()
    }

    single<Retrofit>(named("ProductRetrofit")) {
        Retrofit.Builder()
            .baseUrl(Constants.STARBUCKS_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
            .client(get())
            .build()
    }

    single<EventApi> {
        get<Retrofit>(named("EventRetrofit")).create(EventApi::class.java)
    }
    single<EventDataSource> { EventRemoteDataSource(get()) }
    single<EventRepository> { EventRepositoryImpl(get()) }

    single<HomeContentApi> {
        get<Retrofit>(named("HomeContentRetrofit")).create(HomeContentApi::class.java)
    }
    single<ProductContentApi>{
        get<Retrofit>(named("ProductRetrofit")).create(ProductContentApi::class.java)
    }

    single<HomeContentDataSource>{ HomeContentRemoteDataSource(get(), get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
}