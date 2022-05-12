package com.codesquad.starbucks

import android.app.Application
import com.codesquad.starbucks.di.NetWorkModule
import com.codesquad.starbucks.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class StarBucksApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // start Koin context
        startKoin {
            androidContext(this@StarBucksApplication)
            modules(appModule, NetWorkModule)
        }
    }

}