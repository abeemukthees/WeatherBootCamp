package com.msa.weather.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.msa.weather.R

class DetailActivity : AppCompatActivity() {

    private lateinit var adapter: ForecastListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        adapter = ForecastListAdapter()

        val latitude = intent.getDoubleExtra("lat", -1.0)
        val longitude = intent.getDoubleExtra("lon", -1.0)

        if (latitude != -1.0 && longitude != -1.0) {
            TODO()

        } else {

            Toast.makeText(this, getString(R.string.toast_error_input_missing), Toast.LENGTH_SHORT)
                .show()
        }
    }
}
