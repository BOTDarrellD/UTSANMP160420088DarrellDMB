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
import com.example.utsanmp_160420088_darrelldmb.viewmodel.DoctorListViewModel
import kotlinx.android.synthetic.main.fragment_doctor.*


/**
 * A simple [Fragment] subclass.
 * Use the [DoctorFragment.newInstance] factory method to
 * create an instance of this fragment.s
 */
class DoctorFragment : Fragment() {
    private lateinit var doctorViewModel: DoctorListViewModel
    private val doctorListAdapter = DoctorListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doctorViewModel = ViewModelProvider(this).get(DoctorListViewModel::class.java)
        doctorViewModel.refresh()
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = doctorListAdapter
        observeViewModel()
        refreshLayoutDoctor.setOnRefreshListener {
            recView.visibility = View.GONE
            txtErrorDoktor.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            doctorViewModel.refresh()
            refreshLayoutDoctor.isRefreshing = false
        }

    }
    fun observeViewModel() {
        doctorViewModel.doctorsLD.observe(viewLifecycleOwner, Observer {
            doctorListAdapter.updateDoctorList(it)
        })
        doctorViewModel.doctorLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtErrorDoktor.visibility = View.VISIBLE
            } else {
                txtErrorDoktor.visibility = View.GONE
            }
        })
        doctorViewModel.loadingLD.observe(viewLifecycleOwner, Observer {
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