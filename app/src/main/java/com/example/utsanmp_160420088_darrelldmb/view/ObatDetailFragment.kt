package com.example.utsanmp_160420088_darrelldmb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.utsanmp_160420088_darrelldmb.R


/**
 * A simple [Fragment] subclass.
 * Use the [ObatDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ObatDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_obat_detail, container, false)
    }

}