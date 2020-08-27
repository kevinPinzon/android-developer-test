package com.kevinpinzon.administradorestacionamiento.view.fragments

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import com.kevinpinzon.administradorestacionamiento.R
import com.kevinpinzon.administradorestacionamiento.data.model.Register
import com.kevinpinzon.administradorestacionamiento.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_record.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class RecordFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var modelDialogIn = AlertDialog.Builder(context)
        val dialogViewIn = layoutInflater.inflate(R.layout.dialog_inregister, null)

        modelDialogIn.setView(dialogViewIn)
        var dialogInRegister = modelDialogIn.create()

        card_view_in.setOnClickListener {
            dialogInRegister.show()

            val saveTextV = dialogViewIn.findViewById<TextView>(R.id.textView_save)
            val cancelTextV = dialogViewIn.findViewById<TextView>(R.id.textView_cancel)

            val placaEditText = dialogViewIn.findViewById<EditText>(R.id.editText_placa)

            cancelTextV.setOnClickListener {
                placaEditText.text.clear()
                dialogInRegister.cancel()
            }

            saveTextV.setOnClickListener {

                if( placaEditText.text.length > 0){
                    registerIn(placaEditText.text.toString(), dialogInRegister)
                    placaEditText.text.clear()
                }else{
                    placaEditText.error = "Debe agregar numero de placa"
                }
            }
        }

        val dialogViewOut = layoutInflater.inflate(R.layout.dialog_outregister, null)

        var modelDialogOut = AlertDialog.Builder(context)
        modelDialogOut.setView(dialogViewOut)
        var dialogOutRegister = modelDialogOut.create()

        card_view_out.setOnClickListener {

            dialogOutRegister.show()

            val saveTextV = dialogViewOut.findViewById<TextView>(R.id.textView_save)
            val cancelTextV = dialogViewOut.findViewById<TextView>(R.id.textView_cancel)
            val placaSpinner = dialogViewOut.findViewById<Spinner>(R.id.spinner_placa)

            val placas: ArrayList<String> = ArrayList()


            val observer = Observer<List<Register>> { registrosIn ->

                println("REGISTROS IN")
                for (register in registrosIn) {
                    println(register)
                    placas.add(register.placa)
                }

            }
            registerViewModel.allPlacasIn.observe(this, observer)

            var adapter = ArrayAdapter(dialogOutRegister.context,
                android.R.layout.simple_spinner_item,placas)

            placaSpinner.adapter = adapter

            placaSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(dialogOutRegister.context,
                        placas[position], Toast.LENGTH_SHORT).show()
                    println(placas[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

            cancelTextV.setOnClickListener {
                dialogOutRegister.cancel()
            }

            saveTextV.setOnClickListener {

            }
        }

    }

    private fun registerIn(placa:String, dialog:AlertDialog) {

        val date = getCurrentDateTime()
        val dateInString = date.toString("dd/MM/yyyy HH:mm:ss")

        registerViewModel.registerIn(Register(0,placa, dateInString, "N"))

        Toast.makeText(dialog.context,
            "Registro guardado exitosamente", Toast.LENGTH_SHORT).show()
        dialog.cancel()
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }


}
