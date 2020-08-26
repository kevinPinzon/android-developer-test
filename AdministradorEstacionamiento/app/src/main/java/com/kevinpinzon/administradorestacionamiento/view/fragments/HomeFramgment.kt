package com.kevinpinzon.administradorestacionamiento.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kevinpinzon.administradorestacionamiento.R

/**
 * A simple [Fragment] subclass.
 */
class HomeFramgment : Fragment() {

    companion object{
        private const val ARG_OBJECT = "object"
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            //textView3.text = "Fragmento #" + getInt(ARG_OBJECT).toString()
        }*/
    }

}
