package com.example.utsanmp_160420088_darrelldmb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.utsanmp_160420088_darrelldmb.R
import com.example.utsanmp_160420088_darrelldmb.model.Doctor
import kotlinx.android.synthetic.main.doctor_list_item.view.*
import kotlinx.android.synthetic.main.obat_list_item.view.*

class DoctorListAdapter(val DoctorList:ArrayList<Doctor>)
    :RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>()
{
    class DoctorViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int):DoctorViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.doctor_list_item, parent, false)
        return DoctorViewHolder(view)
    }
    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.view.txtNamaDoktor.text = DoctorList[position].nama

        holder.view.btnDetailDoktor.setOnClickListener {
            val action = DoctorFragmentDirections.actionDoctorDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return DoctorList.size
    }
    fun updateDoctorList(newDoctorList: ArrayList<Doctor>) {
        DoctorList.clear()
        DoctorList.addAll(newDoctorList)
        notifyDataSetChanged()
    }
}