package com.rivaldo.informasigempa.ui.current

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivaldo.informasigempa.network.ApiConfig
import com.rivaldo.informasigempa.response.CurrentEarthquakeResponse
import com.rivaldo.informasigempa.response.Gempa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentEarthquakeViewModel: ViewModel() {

    private val infoCurrentGempa = MutableLiveData<Gempa>()

    fun observeCurrentGempa(): LiveData<Gempa> = infoCurrentGempa

    fun findCurrentGempa(){
        val client = ApiConfig.api.getCurrentEarthquake()
        client.enqueue(object : Callback<CurrentEarthquakeResponse> {
            override fun onResponse(
                call: Call<CurrentEarthquakeResponse>,
                response: Response<CurrentEarthquakeResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        infoCurrentGempa.postValue(responseBody.infogempa?.gempa!!)
                    }
                } else {
                    Log.e("History", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<CurrentEarthquakeResponse>, t: Throwable) {
                Log.e("History", "onFailure: ${t.message}")
            }
        })
    }
}