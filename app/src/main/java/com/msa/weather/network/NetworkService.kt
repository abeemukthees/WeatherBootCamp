package com.msa.weather.network

import com.msa.weather.BuildConfig
import com.msa.weather.network.poko.CurrentWeatherData
import com.msa.weather.network.poko.DailyForecastData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Abhi Muktheeswarar on 04-December-2019
 */

object NetworkService {

    private val networkApiService: NetworkApiService =
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkApiService::class.java)

    fun getCurrentWeatherData(cityName: String): Call<CurrentWeatherData> {
        return networkApiService.getCurrentWeatherData(
            cityName = cityName,
            apiKey = BuildConfig.API_KEY,
            units = "metric"
        )
    }

    fun getDailyForecastData(latitude: Double, longitude: Double): Call<DailyForecastData> {
        return networkApiService.getDailyForecastData(
            latitude = latitude,
            longitude = longitude,
            apiKey = BuildConfig.API_KEY,
            units = "metric"
        )
    }
}