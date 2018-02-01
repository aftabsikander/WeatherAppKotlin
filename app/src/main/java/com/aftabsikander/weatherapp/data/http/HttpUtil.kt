package com.aftabsikander.weatherapp.data.http

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by afali on 12/29/2017.
 * Copyright (c) 2017 Creative Chaos Software Services. All rights reserved.
 */
class HttpUtil {

    companion object {
        private val BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/"
        private val READ_TIME_OUT = 180L
        private val CONNECTION_TIME_OUT = 180L
        private var retrofitInstance: Retrofit? = null
        private var webServiceInstance: WeatherApiService? = null
        val OPEN_WEATHER_ORG_KEY = "15646a06818f61f7b8d7823ca833e1ce"


        /***
         * Configures {@link OkHttpClient} client with read timeout, write timeout and logging
         * interceptor.
         * @return returns {@link OkHttpClient} object
         */
        private fun getDefaultHttpClient(): OkHttpClient {
            val builder = Builder()
            builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            builder.connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.interceptors().add(loggingInterceptor)
            return builder.build()
        }

        fun initializeHttpService() {
            val gson = GsonBuilder().setLenient().create()
            retrofitInstance = Retrofit.Builder()
                    .baseUrl(BASE_URL).client(getDefaultHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            if (webServiceInstance == null)
                webServiceInstance = retrofitInstance?.create(WeatherApiService::class.java)


        }

        fun getHttpService(): WeatherApiService {
            if (webServiceInstance == null)
                throw NullPointerException("Weather Api not initialized")
            else
                return webServiceInstance as WeatherApiService
        }
    }
}