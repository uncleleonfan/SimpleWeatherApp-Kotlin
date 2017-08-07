package com.leon.weatherapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.antonioleiva.weatherapp.extensions.ctx

class ForecastListAdapter : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("onBindViewHolder")
    }

    override fun getItemCount(): Int = 100


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

}