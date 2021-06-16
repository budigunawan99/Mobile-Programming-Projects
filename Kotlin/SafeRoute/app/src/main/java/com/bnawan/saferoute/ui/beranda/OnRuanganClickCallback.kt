package com.bnawan.saferoute.ui.beranda

import com.bnawan.saferoute.entity.Ruangan

interface OnRuanganClickCallback {
    fun onItemClicked(data: Ruangan)
}