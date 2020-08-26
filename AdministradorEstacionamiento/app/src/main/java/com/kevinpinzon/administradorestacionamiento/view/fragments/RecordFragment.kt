package com.kevinpinzon.administradorestacionamiento.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kevinpinzon.administradorestacionamiento.R
import kotlinx.android.synthetic.main.fragment_record.*

/**
 * A simple [Fragment] subclass.
 */
class RecordFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        card_view_in.setOnClickListener {
            Toast.makeText(context,"registrar entrada", Toast.LENGTH_LONG)
        }
        card_view_out.setOnClickListener {
            Toast.makeText(context,"registrar salida", Toast.LENGTH_LONG)
        }

    }


}
