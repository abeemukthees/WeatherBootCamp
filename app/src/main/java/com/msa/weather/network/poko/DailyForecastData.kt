package com.msa.weather.network.poko


import com.google.gson.annotations.SerializedName

data class DailyForecastData(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: List<Item>,
    @SerializedName("message")
    val message: Double
) {
    data class City(
        @SerializedName("country")
        val country: String,
        @SerializedName("geoname_id")
        val geonameId: Int,
        @SerializedName("iso2")
        val iso2: String,
        @SerializedName("lat")
        val lat: Double,
        @SerializedName("lon")
        val lon: Double,
        @SerializedName("name")
        val name: String,
        @SerializedName("population")
        val population: Int,
        @SerializedName("type")
        val type: String
    )

    data class Item(
        @SerializedName("clouds")
        val clouds: Double,
        @SerializedName("deg")
        val deg: Double,
        @SerializedName("dt")
        val dt: Long,
        @SerializedName("humidity")
        val humidity: Double,
        @SerializedName("pressure")
        val pressure: Double,
        @SerializedName("snow")
        val snow: Double,
        @SerializedName("speed")
        val speed: Double,
        @SerializedName("temp")
        val temp: Temp,
        @SerializedName("weather")
        val weather: List<Weather>
    ) {
        data class Temp(
            @SerializedName("day")
            val day: Double,
            @SerializedName("eve")
            val eve: Double,
            @SerializedName("max")
            val max: Double,
            @SerializedName("min")
            val min: Double,
            @SerializedName("morn")
            val morn: Double,
            @SerializedName("night")
            val night: Double
        )

        data class Weather(
            @SerializedName("description")
            val description: String,
            @SerializedName("icon")
            val icon: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("main")
            val main: String
        )
    }
}