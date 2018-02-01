package com.aftabsikander.weatherapp.data.http

import com.aftabsikander.weatherapp.data.ForecastResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by aftabsikander on 12/29/2017.
 */
interface WeatherApiService {

    @GET("daily?mode=json&units=metric&cnt=7")
    fun getDailyForecast(
            @Query("APPID") apiSecretKey: String = HttpUtil.OPEN_WEATHER_ORG_KEY,
            @Query("q") cityName: String = "Karachi,PK"): Call<ForecastResult>

}