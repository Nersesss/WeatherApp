package com.nerses.weather.di

import com.nerses.weather.api.ApiHelper
import com.nerses.weather.api.ApiHelperImpl
import com.nerses.weather.data.repository.LocationRepository
import com.nerses.weather.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get(), get())
    }
    single {
        LocationRepository(get())
    }

    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }

}
