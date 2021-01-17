package com.nerses.weather.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nerses.weather.R
import com.nerses.weather.model.DailyModel
import com.nerses.weather.view.adapter.viewholder.WeeklyViewHolder

class WeeklyAdapter(val context: Context, var list: List<DailyModel>) :
    RecyclerView.Adapter<WeeklyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_of_weekly, parent, false)
        return WeeklyViewHolder(v)
    }

    override fun onBindViewHolder(holder: WeeklyViewHolder, position: Int) {
        holder.initData(list[position])
    }

    override fun getItemCount() = list.size

}