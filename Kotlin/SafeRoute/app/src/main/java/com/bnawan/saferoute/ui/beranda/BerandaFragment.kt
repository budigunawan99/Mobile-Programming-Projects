package com.bnawan.saferoute.ui.beranda

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.bnawan.saferoute.CameraActivity
import com.bnawan.saferoute.DetectorActivity
import com.bnawan.saferoute.R
import com.bnawan.saferoute.databinding.FragmentBerandaBinding
import com.bnawan.saferoute.entity.Ruangan


class BerandaFragment : Fragment() {

    private val listRuangan = ArrayList<Ruangan>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBerandaBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_beranda, container, false)

        Log.i("isinya", "isi: " + resources.getString(R.string.text_title_pilihan_destinasi))
        val judulTeks: TextView? = activity?.findViewById(R.id.text_title)
        judulTeks?.text = resources.getString(R.string.text_title_pilihan_destinasi)

        binding.listKelasContainer.setHasFixedSize(true)
        listRuangan.addAll(getListRuangan())
        showRecyclerList(binding)
        return binding.root
    }

    private fun getListRuangan(): ArrayList<Ruangan> {
        val namaRuangan = resources.getStringArray(R.array.nama_ruangan)
        val gambarRuangan = resources.obtainTypedArray(R.array.gambar_ruangan)
        val list = ArrayList<Ruangan>()
        for (position in namaRuangan.indices) {
            val ruangan = Ruangan(
                namaRuangan[position],
                gambarRuangan.getResourceId(position, -1)
            )
            list.add(ruangan)
        }
        gambarRuangan.recycle()
        Log.i("list ruangan", "isi: " + list)
        return list
    }

    private fun showRecyclerList(binding: FragmentBerandaBinding) {
        binding.listKelasContainer.layoutManager = GridLayoutManager(this.context, 2)
        val listRuanganAdapter = ListRuanganAdapter(listRuangan)
        binding.listKelasContainer.adapter = listRuanganAdapter

        listRuanganAdapter.setOnRuanganClickCallback(object: OnRuanganClickCallback{
            override fun onItemClicked(data: Ruangan) {
                showSelectedRuangan(data)
            }
        })
    }

    private fun showSelectedRuangan(ruangan: Ruangan) {
        val detectorActivity = Intent(activity, DetectorActivity::class.java)
        detectorActivity.putExtra(DetectorActivity.CAMERA_HEADER_TITLE, ruangan)
        startActivity(detectorActivity)
    }
}