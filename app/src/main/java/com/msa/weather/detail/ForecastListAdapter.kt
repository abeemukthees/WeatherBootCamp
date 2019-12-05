package com.msa.weather.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msa.weather.R
import com.msa.weather.network.poko.DailyForecastData
import java.text.DateFormat
import java.util.*

/**
 * Created by Abhi Muktheeswarar on 04-December-2019
 */

class ForecastListAdapter : RecyclerView.Adapter<ForecastItemViewHolder>() {

    private var items: List<DailyForecastData.Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastItemViewHolder {
        return ForecastItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_forecast,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ForecastItemViewHolder, position: Int) {
        val item = items[position]

        holder.textViewTemperature.text = "${item.temp.day} \u2103"

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = (item.dt * 1000)
        holder.textViewDateTime.text =
            DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.time)
    }

    fun setData(items: List<DailyForecastData.Item>) {
        TODO()
    }
}