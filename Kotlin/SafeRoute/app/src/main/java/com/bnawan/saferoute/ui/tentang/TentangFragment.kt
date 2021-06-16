package com.bnawan.saferoute.ui.tentang

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bnawan.saferoute.R
import com.bnawan.saferoute.databinding.FragmentTentangBinding


class TentangFragment : Fragment() {

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTentangBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tentang, container, false)

        val judulTeks: TextView? = activity?.findViewById(R.id.text_title)
        judulTeks?.text = resources.getString(R.string.text_title_tentang_kami)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.cardTextTentang.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }
        return binding.root
    }

}