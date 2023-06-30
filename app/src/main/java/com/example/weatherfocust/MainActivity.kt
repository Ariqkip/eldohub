package com.example.weatherfocust

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWeather()

    }
    private fun getWeather() {
        val apiEndpoint = Weatherservice.buildService(weatherinterface::class.java)
        val call: Call<weather> = apiEndpoint.getWeather()
        call.enqueue(object : Callback<weather> {
            override fun onResponse(
                call: Call<weather>,
                response: Response<weather>
            ) {
                if(response.isSuccessful){
                    val weather = response.body()
                    var iconimage=findViewById<ImageView>(R.id.iconimage)
                    var location=findViewById<TextView>(R.id.titleTextView)
                    var continent=findViewById<TextView>(R.id.title)
                    var time=findViewById<TextView>(R.id.region)
                    var weathercondition=findViewById<ImageView>(R.id.dailyweather1)
                    var weathercondition1=findViewById<TextView>(R.id.dailyweather2)
                    var DetailPressure=findViewById<TextView>(R.id.crdDetailPressure2)
                    var DetailWind=findViewById<TextView>(R.id.crdDetailWind1)
                    var DetailHumidity=findViewById<TextView>(R.id.crdDetailHumidity1)
                    var DetailWind1=findViewById<TextView>(R.id.crdDetailWind2)


//                    var timeupdted= weather?.current?.last_updated
                    var locationupdated= weather?.location?.country
                    var continentupdated= weather?.location?.tz_id
                    var weathertype= weather?.current?.condition?.text
                    var pressure= weather?.current?.pressure_in
                    var wind= weather?.current?.wind_degree
                    var dirwind= weather?.current?.wind_dir
                    var humidity= weather?.current?.humidity



//                    time.text=timeupdted
                    DetailHumidity.text=humidity.toString()
                    DetailWind1.text=dirwind.toString()
                    DetailWind.text=wind.toString()
                    DetailPressure.text=pressure.toString()
                    location.text=locationupdated
                    continent.text=continentupdated
                    weathercondition1.text=weathertype
                    if (weather != null) {
//                        Picasso.with(this@MainActivity).load(weather.current.condition.icon).into(weathercondition)

                    }
                    Log.e("List of App", weather.toString())

                } else {
                    Log.e("response error", "Ooops! There was an error in getting your response ${response.errorBody()
                        ?.string()}")
                }
            }

            override fun onFailure(call: Call<weather>, t: Throwable) {
                Log.e("error", "Ooops! there is an error $t")
            }

        })

    }
}