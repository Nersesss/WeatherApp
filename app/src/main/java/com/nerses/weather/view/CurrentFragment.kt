package com.nerses.weather.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nerses.weather.*
import com.nerses.weather.model.HourlyModel
import com.nerses.weather.model.WeatherModel
import com.nerses.weather.model.mapper.WeatherMapper
import com.nerses.weather.utiles.getAddress
import com.nerses.weather.utiles.millisToDate
import com.nerses.weather.view.adapter.HourlyAdapter
import com.nerses.weather.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class CurrentFragment : Fragment() {
    private lateinit var textHumidity: TextView
    private lateinit var textLocation: TextView
    private lateinit var textTemp: TextView
    private lateinit var textDate: TextView
    private lateinit var recyclerView: RecyclerView
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_current, container, false)

        val btnCurrent: Button = view.findViewById(R.id.btn_weekly)
        btnCurrent.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_currentFragment_to_weeklyFragment)
        }
        initViews(view)
        configureData()

        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.rec_view)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        textDate = view.findViewById(R.id.date)
        textTemp = view.findViewById(R.id.temp)
        textHumidity = view.findViewById(R.id.humidity)
        textLocation = view.findViewById(R.id.location)
    }

    private fun configureData() {
        mainViewModel.mainRepository.getLocalWeather()?.observe(viewLifecycleOwner, {
            val weatherModel = WeatherMapper().toModel(it)
            if (weatherModel == null) {
                showViews(GONE)
            } else {
                initData(weatherModel)
                showViews(VISIBLE)
            }
        })
    }

    private fun initData(it: WeatherModel) {
        val temp = it.current.temp.toString()
        val hum = it.current.humidity.toString()
        val location = context?.let { context ->
            getAddress(context, it.lat, it.lon)
        }
        val date = it.current.dt.times(MIN_IN_MILLISECOND)
            .let { millisecond -> millisToDate(millisecond) }
        val weathers: List<HourlyModel> = it.hourly
        val adapter = HourlyAdapter(weathers)

        textDate.text = date
        textTemp.text = temp
        textHumidity.text = hum
        textLocation.text = location
        recyclerView.adapter = adapter
    }

    private fun showViews(visibility: Int) {
        textDate.visibility = visibility
        textTemp.visibility = visibility
        textHumidity.visibility = visibility
        textLocation.visibility = visibility
    }


}