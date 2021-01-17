package com.nerses.weather.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nerses.weather.R
import com.nerses.weather.model.HourlyModel
import com.nerses.weather.view.adapter.viewholder.HourlyViewHolder

class HourlyAdapter(val list: List<HourlyModel>): RecyclerView.Adapter<HourlyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_of_hourly, parent, false)
        return HourlyViewHolder(v)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.initData(list[position])
    }

    override fun getItemCount() = list.size

}