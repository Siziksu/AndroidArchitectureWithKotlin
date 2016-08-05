package com.siziksu.kotlin.ui.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.siziksu.kotlin.R
import com.siziksu.kotlin.common.extensions.applyToolBarStyleWithHome
import com.siziksu.kotlin.common.model.weather.response.WeatherResponse
import com.siziksu.kotlin.injector.WeatherModule
import com.siziksu.kotlin.presenter.weather.IWeatherView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IWeatherView {

    private val CITY = "Barcelona,Spain"

    private val presenter by lazy { WeatherModule.weather }
    private val toolbar by lazy { findViewById(R.id.defaultToolbar) as Toolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applyToolBarStyleWithHome(toolbar)
    }

    override fun onResume() {
        super.onResume()
        presenter.register(this)
        presenter.getWeather(CITY)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister()
    }

    override val activity: Activity
        get() = this

    override fun onWeather(weatherResponse: WeatherResponse) {
        textViewPlace.text = weatherResponse.place
        textViewTemperature.text = weatherResponse.temperature
        textViewUpdate.text = weatherResponse.time
        textViewError.visibility = View.GONE
    }

    override fun onWeatherError() {
        textViewError.visibility = View.VISIBLE
    }

    override fun showProgress(value: Boolean) {
        progressBar.visibility = if (value) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> presenter.getWeather(CITY)
            else -> {
            }
        }
        return false
    }
}

