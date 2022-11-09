package com.rivaldo.informasigempa.ui.current

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.rivaldo.informasigempa.databinding.FragmentCurrentEarthquakeBinding
import com.rivaldo.informasigempa.utils.Constant.Companion.API_URL


class CurrentEarthquake : Fragment(){

    private lateinit var binding : FragmentCurrentEarthquakeBinding
    private val viewModel : CurrentEarthquakeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentEarthquakeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCurrentEarthquakeSetup()
    }

    private fun initCurrentEarthquakeSetup(){
        viewModel.findCurrentGempa()
        viewModel.observeCurrentGempa().observe(requireActivity()) {
            binding.apply {
                val textJamTanggal = "${it.jam} - ${it.tanggal}"
                val textMagnitude = "${it.magnitude} SR"
                val textDirasakan = "(${it.dirasakan})"
                val textLintangBujur = "${it.lintang} ${it.bujur}"
                val coordinates = it.coordinates

                tvMagnitude.text = textMagnitude
                tvJamTanggal.text = textJamTanggal
                tvWilayah.text = it.wilayah
                tvKedalaman.text = it.kedalaman
                tvDirasakan.text = textDirasakan
                tvLintangBujur.text = textLintangBujur
                tvPotensi.text = it.potensi

                Glide.with (requireContext())
                    .load ( API_URL + it.shakemap)
                    .into (ivMap)

                btnLihatLokasi.setOnClickListener {
                    val url = "https://www.google.com/maps/place/${coordinates}"
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(url)
                    startActivity(i)
                }
            }
        }
    }
}