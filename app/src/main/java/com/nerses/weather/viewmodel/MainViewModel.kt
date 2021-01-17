package com.nerses.weather.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.nerses.weather.utiles.NetworkHelper
import com.nerses.weather.ErrorType
import com.nerses.weather.R
import com.nerses.weather.data.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(
    val mainRepository: MainRepository,
    val networkHelper: NetworkHelper,
    private val c: Context
) : ViewModel() {
    private val _weather = MutableLiveData<String>()
    val weatherModel: LiveData<String>
        get() = _weather

    fun fetchWeather(
        lat: String?,
        lon: String?,
        exclude: String?,
        units: String?,
        appID: String?
    ) {
        viewModelScope.launch {

            mainRepository.getWeather(
                networkHelper.isNetworkConnected(),
                lat, lon, exclude, units, appID
            )
                .let {
                    when(it){
                        ErrorType.SUCCESS -> {
                            Log.i("TAG", "fetchWeather: all right")
                        }
                        ErrorType.NETWORK_ERROR -> {
                            _weather.postValue(c.getString(R.string.network_error))
                        }
                        ErrorType.NETWORK_UNAVELABLE -> {
                            _weather.postValue(c.getString(R.string.connection_message))
                        }
                    }
                }
        }
    }
}