package com.nerses.weather.utiles

import java.text.SimpleDateFormat
import java.util.*

private const val DAY_FORMAT = "dd"
private const val HOUR_FORMAT = "kk:mm"
private const val MONTH_FORMAT = "MMMM"
private const val WEEK_FORMAT = "EE"

    fun millisToDate(milliSeconds: Long): String {
        val formatter = SimpleDateFormat("$WEEK_FORMAT $DAY_FORMAT $MONTH_FORMAT $HOUR_FORMAT", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }

