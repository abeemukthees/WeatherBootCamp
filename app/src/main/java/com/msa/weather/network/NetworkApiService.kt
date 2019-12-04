package com.msa.weather.network

import com.msa.weather.network.poko.CurrentWeatherData
import com.msa.weather.network.poko.DailyForecastData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Abhi Muktheeswarar on 04-December-2019
 */
interface NetworkApiService {

    @GET("weather")
    fun getCurrentWeatherData(
        @Query("q") cityName: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Call<CurrentWeatherData>

    @GET("forecast/daily")
    fun getDailyForecastData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Call<DailyForecastData>
}