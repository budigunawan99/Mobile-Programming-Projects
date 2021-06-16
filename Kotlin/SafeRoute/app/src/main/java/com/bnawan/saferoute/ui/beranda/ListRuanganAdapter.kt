package com.bnawan.saferoute.ui.beranda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bnawan.saferoute.databinding.ListKelasBinding
import com.bnawan.saferoute.entity.Ruangan

class ListRuanganAdapter(private val listRuangan: ArrayList<Ruangan> ) : RecyclerView.Adapter<ListRuanganViewHolder>() {

    private var onRuanganClickCallback:OnRuanganClickCallback? = null

    fun setOnRuanganClickCallback(onRuanganClickCallback: OnRuanganClickCallback) {
        this.onRuanganClickCallback = onRuanganClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRuanganViewHolder {
        val binding = ListKelasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListRuanganViewHolder(binding, onRuanganClickCallback)
    }

    override fun getItemCount(): Int = listRuangan.size

    override fun onBindViewHolder(holder: ListRuanganViewHolder, position: Int) {
        holder.bind(listRuangan[position])
    }
}