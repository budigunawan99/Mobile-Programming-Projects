package com.bnawan.saferoute.ui.beranda

import androidx.recyclerview.widget.RecyclerView
import com.bnawan.saferoute.R
import com.bnawan.saferoute.databinding.ListKelasBinding
import com.bnawan.saferoute.entity.Ruangan
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListRuanganViewHolder(
    private val binding: ListKelasBinding,
    private val onRuanganClickCallback: OnRuanganClickCallback?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(ruangan: Ruangan) {
        with(binding) {
            Glide.with(itemView.context)
                .load(ruangan.gambar)
                .apply(RequestOptions().override(550, 350))
                .into(cardImg)
            cardTitle.text = ruangan.nama

            itemView.setOnClickListener {
                onRuanganClickCallback?.onItemClicked(ruangan)
            }
        }
    }
}