package com.leon.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.antonioleiva.weatherapp.data.server.Forecast
import com.antonioleiva.weatherapp.data.server.ForecastByZipCodeRequest
import com.antonioleiva.weatherapp.data.server.ForecastResult
import com.leon.weatherapp.domain.model.ForecastList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.find
import java.util.*
import java.util.concurrent.TimeUnit
import com.leon.weatherapp.domain.model.Forecast as ModelForecast


class MainActivity : AppCompatActivity(), ToolbarManager{

    //委托 懒加载
    override val toolbar: Toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecastList.setHasFixedSize(true)
        forecastList.layoutManager = LinearLayoutManager(this)
        attachScroll(forecastList)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = async(UI) {
        val result = bg {
            val data = ForecastByZipCodeRequest(94043L).execute()
            convertToDomain(94043L, data)
        }
        updateUI(result.await())
    }

    fun convertToDomain(zipCode: Long, forecast: ForecastResult) = with(forecast) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(-1, dt, weather[0].description, temp.max.toInt(), temp.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"

    private fun updateUI(weekForecast : ForecastList) {
        forecastList.adapter = ForecastListAdapter(weekForecast)
        toolbarTitle = "${weekForecast.city} (${weekForecast.country})"
    }

}
