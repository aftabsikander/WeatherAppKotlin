package com.aftabsikander.weatherapp.ui.adapters

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.aftabsikander.weatherapp.domain.model.ForecastList

/**
 * Created by aftabsikander on 12/18/2017.
 */
class ForecastAdapter(val weekForecast: ForecastList) :
        RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(AppCompatTextView(parent?.context))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(weekForecast.dailyForecast[position]) {
            holder.textView.text = "$date - $description - $high/$low"
        }
    }

    override fun getItemCount(): Int = weekForecast.dailyForecast.size
    class ViewHolder(val textView: AppCompatTextView) : RecyclerView.ViewHolder(textView)
}