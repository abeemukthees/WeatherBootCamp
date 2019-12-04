package com.msa.weather.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.msa.weather.R
import com.msa.weather.network.NetworkService
import com.msa.weather.network.poko.DailyForecastData
import kotlinx.android.synthetic.main.activity_detail.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var adapter: ForecastListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        adapter = ForecastListAdapter()
        recyclerView_forecast.adapter = adapter

        val latitude = intent.getDoubleExtra("lat", -1.0)
        val longitude = intent.getDoubleExtra("lon", -1.0)

        if (latitude != -1.0 && longitude != -1.0) {
            getHourlyForecastData(latitude, longitude)

        } else {

            Toast.makeText(this, getString(R.string.toast_error_input_missing), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun getHourlyForecastData(latitude: Double, longitude: Double) {

        NetworkService.getDailyForecastData(latitude, longitude).enqueue(object :
            retrofit2.Callback<DailyForecastData> {

            override fun onResponse(
                call: Call<DailyForecastData>,
                response: Response<DailyForecastData>
            ) {

                if (response.isSuccessful && response.body() != null) {
                    setupViews(response.body()!!)
                } else {
                    handleError(response.errorBody())
                }
            }

            override fun onFailure(call: Call<DailyForecastData>, t: Throwable) {
                progressBar_forecast.visibility = View.GONE
                t.printStackTrace()
            }
        })
    }

    private fun setupViews(forecastData: DailyForecastData) {
        supportActionBar?.title = forecastData.city.name
        progressBar_forecast.visibility = View.GONE
        adapter.setData(forecastData.list)
    }


    private fun handleError(error: ResponseBody?) {
        progressBar_forecast.visibility = View.GONE
        error?.let {
            val errorMessage = JSONObject(it.string())["message"] as String
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}
