package com.msa.weather.detail

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.msa.weather.R

/**
 * Created by Abhi Muktheeswarar on 04-December-2019
 */
class ForecastItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textViewTemperature = itemView.findViewById<TextView>(R.id.text_temperature)
    val textViewDateTime = itemView.findViewById<TextView>(R.id.text_dateTime)
}