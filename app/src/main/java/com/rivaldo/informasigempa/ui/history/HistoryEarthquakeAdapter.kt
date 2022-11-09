package com.rivaldo.informasigempa.ui.history

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rivaldo.informasigempa.R
import com.rivaldo.informasigempa.databinding.ItemHistoryEarthquakeBinding
import com.rivaldo.informasigempa.response.GempaItem


class HistoryEarthquakeAdapter(private val listHistoryEarthquake: List<GempaItem>, private val context : Context) :
    RecyclerView.Adapter<HistoryEarthquakeAdapter.HistoryEarthquakeViewHolder>() {

    inner class HistoryEarthquakeViewHolder(itemView: View, val binding: ItemHistoryEarthquakeBinding) :
        RecyclerView.ViewHolder(itemView) {
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryEarthquakeViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_history_earthquake, parent, false)
        val binding = ItemHistoryEarthquakeBinding.bind(view)
        return  HistoryEarthquakeViewHolder(view, binding)
    }

    override fun onBindViewHolder(holder: HistoryEarthquakeViewHolder, position: Int) {
        val item : GempaItem = listHistoryEarthquake[position]
        holder.binding.apply {
            val textJamTanggal = "${item.jam} - ${item.tanggal}"
            val textMagnitude = "${item.magnitude} SR"
            val textDirasakan = "(${item.dirasakan})"
            val textLintangBujur = "${item.lintang} ${item.bujur}"

            tvMagnitude.text = textMagnitude
            tvJamTanggal.text = textJamTanggal
            tvWilayah.text = item.wilayah
            tvKedalaman.text = item.kedalaman
            tvDirasakan.text = textDirasakan
            tvLintangBujur.text = textLintangBujur
            btnLihatLokasi.setOnClickListener {
                val url = "https://www.google.com/maps/place/${item.coordinates}"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                context.startActivity(i)
            }
        }
    }

    override fun getItemCount(): Int {
        return listHistoryEarthquake.size
    }

}