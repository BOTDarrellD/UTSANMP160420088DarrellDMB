package com.example.utsanmp_160420088_darrelldmb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utsanmp_160420088_darrelldmb.R
import com.example.utsanmp_160420088_darrelldmb.viewmodel.ObatListViewModel
import kotlinx.android.synthetic.main.fragment_doctor.*
import kotlinx.android.synthetic.main.fragment_doctor.recView
import kotlinx.android.synthetic.main.fragment_obat.*


/**
 * A simple [Fragment] subclass.
 * Use the [ObatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ObatFragment : Fragment() {
    private lateinit var obatViewModel: ObatListViewModel
    private val obatListAdapter = ObatListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_obat, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        obatViewModel = ViewModelProvider(this).get(ObatListViewModel::class.java)
        obatViewModel.refresh()
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = obatListAdapter
        observeViewModel()
        refreshLayoutObat.setOnRefreshListener {
            recView.visibility = View.GONE
            txtErrorDoktor.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            obatViewModel.refresh()
            refreshLayoutObat.isRefreshing = false
        }
    }
    fun observeViewModel() {
        obatViewModel.obatsLD.observe(viewLifecycleOwner, Observer {
            obatListAdapter.updateObatList(it)
        })
        obatViewModel.obatLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtErrorObat.visibility = View.VISIBLE
            } else {
                txtErrorObat.visibility = View.GONE
            }
        })
        obatViewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recView.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
            } else {
                recView.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
            }
        })
    }

}