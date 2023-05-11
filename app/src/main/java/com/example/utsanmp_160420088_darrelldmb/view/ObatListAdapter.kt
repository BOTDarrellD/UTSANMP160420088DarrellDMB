package com.example.utsanmp_160420088_darrelldmb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.utsanmp_160420088_darrelldmb.R
import com.example.utsanmp_160420088_darrelldmb.model.Doctor
import com.example.utsanmp_160420088_darrelldmb.model.Obat
import kotlinx.android.synthetic.main.doctor_list_item.view.*
import kotlinx.android.synthetic.main.obat_list_item.view.*

class ObatListAdapter(val ObatList:ArrayList<Obat>)
    :RecyclerView.Adapter<ObatListAdapter.ObatViewHolder>()
{
    class ObatViewHolder(var view: View) : RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int):ObatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.obat_list_item, parent, false)
        return ObatViewHolder(view)

    }
    override fun onBindViewHolder(holder: ObatViewHolder, position: Int) {
        holder.view.txtNamaObat.text = ObatList[position].nama

        holder.view.btnDetailObat.setOnClickListener {
            val action = ObatFragmentDirections.actionObatDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }
    override fun getItemCount(): Int {
        return ObatList.size
    }
    fun updateObatList(newObatList: ArrayList<Obat>) {
        ObatList.clear()
        ObatList.addAll(newObatList)
        notifyDataSetChanged()
    }
}