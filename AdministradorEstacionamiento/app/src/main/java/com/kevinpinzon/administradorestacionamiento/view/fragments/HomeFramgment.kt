package com.kevinpinzon.administradorestacionamiento.view.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import com.kevinpinzon.administradorestacionamiento.R
import com.kevinpinzon.administradorestacionamiento.data.model.Car
import com.kevinpinzon.administradorestacionamiento.data.model.TypeCar
import com.kevinpinzon.administradorestacionamiento.view.ListCarActivity
import com.kevinpinzon.administradorestacionamiento.viewmodel.CarViewModel
import com.kevinpinzon.administradorestacionamiento.viewmodel.RegisterViewModel
import com.kevinpinzon.administradorestacionamiento.viewmodel.TypeCarViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class HomeFramgment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var carViewModel: CarViewModel
    private lateinit var typeCarViewModel: TypeCarViewModel

    val residentsGlobal: ArrayList<Car> = ArrayList()
    val oficialsGlobal: ArrayList<Car> = ArrayList()

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

        addObserverCars()

        cardv_residentes.setOnClickListener{
            val intent = Intent(context, ListCarActivity::class.java)
            intent.putExtra("title", "Residentes")
            startActivity(intent)
        }

        cardv_oficiales.setOnClickListener{
            val intent = Intent(context, ListCarActivity::class.java)
            // To pass any data to next activity
            intent.putExtra("title", "Oficiales")
            // start your next activity
            startActivity(intent)
        }

        var modelDialog = AlertDialog.Builder(context)
        val dialogView = layoutInflater.inflate(R.layout.dialog_addcar, null)

        modelDialog.setView(dialogView)
        var dialogCustom = modelDialog.create()

        typeCarViewModel = ViewModelProviders.of(this).get(TypeCarViewModel::class.java)
        addObserverTypes(dialogView)

        btn_addCar.setOnClickListener {

            dialogCustom.show()

            val saveTextV = dialogView.findViewById<TextView>(R.id.textView_save)
            val cancelTextV = dialogView.findViewById<TextView>(R.id.textView_cancel)

            val placaEditText = dialogView.findViewById<EditText>(R.id.editText_placa)

            val rg = dialogView.findViewById<RadioGroup>(R.id.radiog_types)

            saveTextV.setOnClickListener {

                val rbSelected = dialogView.findViewById<RadioButton>(rg.checkedRadioButtonId)

                if( placaEditText.text.length > 0){
                    addCar(placaEditText.text.toString(), rbSelected.text.toString(), dialogCustom)
                    placaEditText.text.clear()
                }else{
                    placaEditText.error = "Debe agregar numero de placa"
                }
            }

            cancelTextV.setOnClickListener {

                placaEditText.text.clear()
                dialogCustom.cancel()
            }

        }

    }

    private fun addCar(placa:String, type:String, dialog:AlertDialog) {
        carViewModel.insert(Car(placa, type,0))
        Toast.makeText(dialog.context,
            "Vehiculo guardado exitosamente", Toast.LENGTH_SHORT).show()
        dialog.cancel()
    }

    private fun addObserverTypes(dialogView: View) {
        val observer = Observer<List<TypeCar>> { types ->
            if (types != null) {

                if (types.isEmpty() ){
                    typeCarViewModel.insert(TypeCar(1,"oficial"))
                    typeCarViewModel.insert(TypeCar(2,"residente"))
                }

                val rg = dialogView.findViewById<RadioGroup>(R.id.radiog_types)
                rg.orientation = RadioGroup.VERTICAL

                for (type in types) {
                    val rb  = RadioButton(dialogView.context)
                    rb.text = type.name
                    rb.id   = type.id
                    rg.addView(rb)
                    rb.isChecked = true
                }
            }
        }
        typeCarViewModel.alltypeCars.observe(this, observer)
    }

    private fun addObserverCars() {
        val observer = Observer<List<Car>> { cars ->
            if (cars != null) {

                residentsGlobal.clear()
                oficialsGlobal.clear()

                for (itemCar in cars) {
                    if(itemCar.type == "residente"){
                        residentsGlobal.add(itemCar)
                    }else if (itemCar.type == "oficial"){
                        oficialsGlobal.add(itemCar)
                    }
                }

                textv_residentes.text = residentsGlobal.size.toString()
                textv_oficiales.text = oficialsGlobal.size.toString()
            }else{
                textv_residentes.text = "0'"
                textv_oficiales.text = "0'"
            }
        }

        carViewModel.allCars.observe(this, observer)
    }


}
