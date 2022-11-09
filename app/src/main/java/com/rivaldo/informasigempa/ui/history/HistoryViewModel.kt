package com.rivaldo.informasigempa.ui.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivaldo.informasigempa.network.ApiConfig
import com.rivaldo.informasigempa.response.GempaItem
import com.rivaldo.informasigempa.response.HistoryEarthquakeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel : ViewModel() {

    private val infoHistoryGempa = MutableLiveData<List<GempaItem>>()

    fun observeHistoryGempa(): LiveData<List<GempaItem>> = infoHistoryGempa

    fun findGempaInfo() {
        val client = ApiConfig.api.getHistoryEarthquake()
        client.enqueue(object : Callback<HistoryEarthquakeResponse> {
            override fun onResponse(
                call: Call<HistoryEarthquakeResponse>,
                response: Response<HistoryEarthquakeResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        infoHistoryGempa.postValue(responseBody.infogempa.gempa)
                    }
                } else {
                    Log.e("History", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<HistoryEarthquakeResponse>, t: Throwable) {
                Log.e("History", "onFailure: ${t.message}")
            }
        })
    }
}