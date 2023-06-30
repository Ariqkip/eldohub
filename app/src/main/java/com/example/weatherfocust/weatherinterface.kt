package com.example.weatherfocust

import com.example.weatherfocust.weather
import retrofit2.Call
import retrofit2.http.*

interface weatherinterface {
    @GET("/current.json?q=53.1%2C-0.13/")
    fun getWeather(): Call<weather>



}