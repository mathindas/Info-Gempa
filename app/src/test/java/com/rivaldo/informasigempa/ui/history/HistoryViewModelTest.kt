package com.rivaldo.informasigempa.ui.history

import androidx.lifecycle.MutableLiveData
import com.rivaldo.informasigempa.response.Gempa
import com.rivaldo.informasigempa.response.GempaItem
import com.rivaldo.informasigempa.response.HistoryEarthquakeResponse
import com.rivaldo.informasigempa.response.InfoHistoryGempa
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mockito.mock

class HistoryViewModelTest {

    private lateinit var infoHistoryGempa : InfoHistoryGempa
    private lateinit var historyViewModel: HistoryViewModel

    @Before
    fun before(){
        historyViewModel = HistoryViewModel()
        infoHistoryGempa = mock(InfoHistoryGempa::class.java)
    }

    @Test
    fun observeHistoryGempa() {
    }

    @Test
    fun findGempaInfo() {
    }
}