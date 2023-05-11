package com.example.utsanmp_160420088_darrelldmb.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.utsanmp_160420088_darrelldmb.model.Doctor
import com.example.utsanmp_160420088_darrelldmb.model.Obat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ObatListViewModel(application: Application): AndroidViewModel(application) {
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    val obatsLD = MutableLiveData<ArrayList<Obat>>()
    val obatLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    fun refresh() {
        //val obat1 =
            //Obat("Panadol ","100","5000")

        //val obat2 =
            //Obat("Penicilin","50","200000")

        //val obat3 =
            //Obat("Decadryl","200","10000")
        //val obatList:ArrayList<Obat> = arrayListOf<Obat>(obat1, obat2, obat3)
        //obatsLD.value = obatList
        obatLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://jsonformatter.org/6dbd1c"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Obat>>() { }.type
                val result = Gson().fromJson<List<Obat>>(it, sType)
                obatsLD.value = result as ArrayList<Obat>?
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                obatLoadErrorLD.value = false
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}