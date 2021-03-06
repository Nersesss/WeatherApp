package com.nerses.weather.view.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nerses.weather.MIN_IN_MILLISECOND
import com.nerses.weather.R
import com.nerses.weather.model.DailyModel
import com.nerses.weather.utiles.millisToDate

class WeeklyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun initData(weather: DailyModel) {
        val textDate = itemView.findViewById(R.id.date) as TextView
        val textTemp  = itemView.findViewById(R.id.temp) as TextView
        val textHumidity  = itemView.findViewById(R.id.humidity) as TextView
        textDate.text = millisToDate(weather.dt * MIN_IN_MILLISECOND)
        val temp = "${weather.temp.max.toInt()} / ${weather.temp.min.toInt()}"
        textTemp.text = temp
        textHumidity.text = weather.humidity.toString()
    }
}
