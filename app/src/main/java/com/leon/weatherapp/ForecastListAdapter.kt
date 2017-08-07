package com.leon.weatherapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.antonioleiva.weatherapp.extensions.ctx
import com.antonioleiva.weatherapp.extensions.toDateString
import com.leon.weatherapp.domain.model.Forecast
import com.leon.weatherapp.domain.model.ForecastList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastListAdapter(val dataList: ForecastList) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindForecast(dataList[position])

    override fun getItemCount(): Int = dataList.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun  bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date.toDateString()
                itemView.description.text = description
                itemView.maxTemperature.text = "${high}º"
                itemView.minTemperature.text = "${low}º"
            }
        }
    }

}