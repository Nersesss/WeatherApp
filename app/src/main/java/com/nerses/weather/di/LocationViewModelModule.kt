package com.nerses.weather.di

import com.nerses.weather.viewmodel.LocationViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val locationViewModelModule = module {
    viewModel { LocationViewModel(get()) }
}