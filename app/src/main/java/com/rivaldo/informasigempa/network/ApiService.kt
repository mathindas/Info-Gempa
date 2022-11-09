package com.rivaldo.informasigempa.network

import com.rivaldo.informasigempa.response.CurrentEarthquakeResponse
import com.rivaldo.informasigempa.response.HistoryEarthquakeResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService{

    @GET("gempadirasakan.json")
    fun getHistoryEarthquake() : Call<HistoryEarthquakeResponse>

    @GET("autogempa.json")
    fun getCurrentEarthquake() : Call<CurrentEarthquakeResponse>
}