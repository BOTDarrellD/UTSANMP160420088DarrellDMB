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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DoctorListViewModel(application: Application): AndroidViewModel(application) {
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    val doctorsLD = MutableLiveData<ArrayList<Doctor>>()
    val doctorLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    fun refresh() {
        //val doctor1 =
            //Doctor("Ivan Gunawan","Onkologi","Senin-Jumat")

        //val doctor2 =
            //Doctor("Tupac Sakur","Mata","Setiap Hari")

        //val doctor3 =
            //Doctor("Eminem","Psikologi","Rabu-Minggu")
        //val doctorList:ArrayList<Doctor> = arrayListOf<Doctor>(doctor1, doctor2, doctor3)
        //doctorsLD.value = doctorList
        doctorLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Doctor>>() { }.type
                val result = Gson().fromJson<List<Doctor>>(it, sType)
                doctorsLD.value = result as ArrayList<Doctor>?
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                doctorLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}

