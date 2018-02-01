package com.aftabsikander.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.aftabsikander.weatherapp.R
import com.aftabsikander.weatherapp.data.ForecastResult
import com.aftabsikander.weatherapp.data.http.HttpUtil
import com.aftabsikander.weatherapp.domain.mappers.ForecastDataMapper
import com.aftabsikander.weatherapp.domain.model.ForecastList
import com.aftabsikander.weatherapp.ui.adapters.ForecastAdapter
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var forecastRecycle: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastRecycle = findViewById(R.id.recycleViewForecast)
        forecastRecycle?.layoutManager = LinearLayoutManager(this)


        callWebAPI("Lahore,PK")
    }

    private fun setupAdapter(forecastList: ForecastList) {
        forecastRecycle?.adapter = ForecastAdapter(forecastList)
    }

    private fun callWebAPI(cityNameValue: String) {
        var request = HttpUtil.getHttpService().getDailyForecast(cityName = cityNameValue)
        request.enqueue(object : Callback<ForecastResult> {
            override fun onResponse(call: Call<ForecastResult>, response: Response<ForecastResult>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        setupAdapter(ForecastDataMapper().convertFromDataModel(response.body()!!))
                    } else {
                        toast("Error From API")
                    }

                }
            }

            override fun onFailure(call: Call<ForecastResult>, t: Throwable) {
            }
        })
    }
}
