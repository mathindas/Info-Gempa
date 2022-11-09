package com.rivaldo.informasigempa.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rivaldo.informasigempa.databinding.FragmentHistoryEarthquakeBinding

class HistoryEarthquake : Fragment(){

    private lateinit var binding: FragmentHistoryEarthquakeBinding
    private val viewModel: HistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryEarthquakeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHistoryViewSetup()
    }

    private fun initHistoryViewSetup(){
        viewModel.findGempaInfo()
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
        }
        viewModel.observeHistoryGempa().observe(requireActivity()) { gempaItem ->
            binding.rvHistory.adapter = context?.let {
                HistoryEarthquakeAdapter(
                    gempaItem, it
                )
            }
        }
    }
}