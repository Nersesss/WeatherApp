package com.nerses.weather.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nerses.weather.R
import com.nerses.weather.model.WeatherModel
import com.nerses.weather.model.mapper.WeatherMapper
import com.nerses.weather.view.adapter.WeeklyAdapter
import com.nerses.weather.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class WeeklyFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weekly, container, false)

        val btnCurrent: Button = view.findViewById(R.id.btn_current)
        btnCurrent.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_weeklyFragment_to_currentFragment)
        }

        initRecyclerView(view)

        return view
    }

    private fun initRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.rec_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        mainViewModel.mainRepository.getLocalWeather()?.observe(viewLifecycleOwner, {
            val weatherModel = WeatherMapper().toModel(it)
            if (weatherModel != null) {
                initData(weatherModel)
            }
        })
    }

    private fun initData(weatherModel: WeatherModel) {
        val weathers = weatherModel.daily
        val adapter = context?.let { WeeklyAdapter(it, weathers) }

        recyclerView.adapter = adapter
    }

}