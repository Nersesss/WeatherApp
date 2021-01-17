package com.nerses.weather

import android.app.Application
import com.nerses.weather.data.AppDatabase
import com.nerses.weather.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule, locationViewModelModule, persistenceModule))
        }
    }
}