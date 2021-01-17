package com.nerses.weather.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nerses.weather.data.entity.CurrentEntity
import com.nerses.weather.data.entity.DailyEntity
import com.nerses.weather.data.entity.HourlyEntity
import com.nerses.weather.data.entity.TempEntity

class Converters {

    @TypeConverter
    fun fromDailies(value: List<DailyEntity?>?): String? {
        return if (value == null) null else Gson().toJson(
            value,
            object : TypeToken<List<DailyEntity?>?>() {}.type
        )
    }

    @TypeConverter
    fun toDailies(value: String?): List<DailyEntity?>? {
        return if (value == null) null else Gson().fromJson<List<DailyEntity>>(
            value,
            object : TypeToken<List<DailyEntity?>?>() {}.type
        )
    }
    @TypeConverter
    fun fromTemp(value: TempEntity?): String? {
        return if (value == null) null else Gson().toJson(
            value,
            object : TypeToken<TempEntity?>() {}.type
        )
    }

    @TypeConverter
    fun toTemp(value: String?): TempEntity? {
        return if (value == null) null else Gson().fromJson<TempEntity>(
            value,
            object : TypeToken<TempEntity?>() {}.type
        )
    }
    @TypeConverter
    fun fromCurrent(value: CurrentEntity?): String? {
        return if (value == null) null else Gson().toJson(
            value,
            object : TypeToken<CurrentEntity?>() {}.type
        )
    }

    @TypeConverter
    fun toCurrent(value: String?): CurrentEntity? {
        return if (value == null) null else Gson().fromJson<CurrentEntity>(
            value,
            object : TypeToken<CurrentEntity?>() {}.type
        )
    }

    @TypeConverter
    fun fromHourly(value: List<HourlyEntity?>?): String? {
        return if (value == null) null else Gson().toJson(
            value,
            object : TypeToken<List<HourlyEntity?>?>() {}.type
        )
    }

    @TypeConverter
    fun toHourly(value: String?): List<HourlyEntity?>? {
        return if (value == null) null else Gson().fromJson<List<HourlyEntity?>>(
            value,
            object : TypeToken<List<HourlyEntity?>?>() {}.type
        )
    }
}