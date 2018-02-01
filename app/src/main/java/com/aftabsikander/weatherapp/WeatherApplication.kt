package com.aftabsikander.weatherapp

import android.app.Application
import com.aftabsikander.weatherapp.data.http.HttpUtil

/**
 * Created by aftabsikander on 2/1/2018.
 */

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        HttpUtil.initializeHttpService()
    }

}
