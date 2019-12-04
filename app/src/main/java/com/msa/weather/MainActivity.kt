package com.msa.weather

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.msa.weather.detail.DetailActivity
import com.msa.weather.network.NetworkService
import com.msa.weather.network.poko.CurrentWeatherData
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var currentWeatherData: CurrentWeatherData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_search.setOnClickListener { validateAndGetCurrentWeatherData() }

        edit_city_name.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                validateAndGetCurrentWeatherData()
                true
            }
            false
        }

        text_temperature.setOnClickListener { openDetailScreen() }
    }

    private fun validateAndGetCurrentWeatherData() {

        val cityName = edit_city_name.text.toString()

        if (cityName.isNotBlank() && cityName.length >= 3) {

            progressBar.visibility = View.VISIBLE
            getCurrentWeatherData(cityName)

        } else {

            Toast.makeText(
                this,
                getString(R.string.toast_error_invalid_city_name),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getCurrentWeatherData(cityName: String) {

        edit_city_name.isEnabled = false
        button_search.isEnabled = false

        NetworkService.getCurrentWeatherData(cityName)
            .enqueue(object : Callback<CurrentWeatherData> {

                override fun onResponse(
                    call: Call<CurrentWeatherData>,
                    response: Response<CurrentWeatherData>
                ) {

                    if (response.isSuccessful && response.body() != null) {
                        setupViews(response.body()!!)
                    } else {

                        handleError(response.errorBody())
                    }
                }

                override fun onFailure(call: Call<CurrentWeatherData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    @SuppressLint("SetTextI18n")
    private fun setupViews(currentWeatherData: CurrentWeatherData) {
        this.currentWeatherData = currentWeatherData
        progressBar.visibility = View.INVISIBLE
        text_city_name.text = currentWeatherData.name
        text_temperature.text = "${currentWeatherData.main.temp} \u2103"

        if (currentWeatherData.weather.isNotEmpty()) {
            text_weather_type_name.text = currentWeatherData.weather.first().main
            text_weather_type_description.text = currentWeatherData.weather.first().description
        }

        edit_city_name.isEnabled = true
        button_search.isEnabled = true
    }

    private fun handleError(error: ResponseBody?) {
        this.currentWeatherData = null
        progressBar.visibility = View.INVISIBLE

        edit_city_name.isEnabled = true
        button_search.isEnabled = true

        error?.let {
            val errorMessage = JSONObject(it.string())["message"] as String
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openDetailScreen() {

        currentWeatherData?.let {

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("lat", it.coord.lat)
            intent.putExtra("lon", it.coord.lon)
            startActivity(intent)
        }
    }
}
