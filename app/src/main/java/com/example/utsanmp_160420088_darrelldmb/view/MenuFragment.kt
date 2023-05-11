package com.example.utsanmp_160420088_darrelldmb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.utsanmp_160420088_darrelldmb.R
import kotlinx.android.synthetic.main.fragment_menu.*


/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnKembali.setOnClickListener {
            val action = MenuFragmentDirections.actionHelloFragment()
            Navigation.findNavController(it).navigate(action)
        }
        if(arguments != null) {
            val playerName =
                MenuFragmentArgs.fromBundle(requireArguments()).playerName
            txtNama.text = "Hello $playerName"
        }
    }

}