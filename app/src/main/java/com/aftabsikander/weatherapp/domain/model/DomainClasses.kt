package com.aftabsikander.weatherapp.domain.model

/**
 * Created by aftabsikander on 1/29/2018.
 */

data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>)

data class Forecast(val date: String, val description: String, val high: Float, val low: Float)
