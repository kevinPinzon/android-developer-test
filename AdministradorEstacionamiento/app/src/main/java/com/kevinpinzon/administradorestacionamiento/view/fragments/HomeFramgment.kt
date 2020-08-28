package com.kevinpinzon.administradorestacionamiento.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.kevinpinzon.administradorestacionamiento.R
import com.kevinpinzon.administradorestacionamiento.viewmodel.CarViewModel
import com.kevinpinzon.administradorestacionamiento.viewmodel.RegisterViewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFramgment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var carViewModel: CarViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        carViewModel = ViewModelProviders.of(this).get(CarViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
