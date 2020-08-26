package com.kevinpinzon.administradorestacionamiento.view.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kevinpinzon.administradorestacionamiento.R
import com.kevinpinzon.administradorestacionamiento.data.model.Car
import com.kevinpinzon.administradorestacionamiento.data.model.TypeCar
import com.kevinpinzon.administradorestacionamiento.viewmodel.CarViewModel
import com.kevinpinzon.administradorestacionamiento.viewmodel.TypeCarViewModel
import kotlinx.android.synthetic.main.fragment_cars.*

/**
 * A simple [Fragment] subclass.
 */
class CarsFragment : Fragment() {

    private lateinit var carViewModel: CarViewModel
    private lateinit var typeCarViewModel: TypeCarViewModel

    //private lateinit var typesCarsG: List<TypeCar>


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    private fun addCar(placa:String, type:String, dialog:AlertDialog) {
        carViewModel.insert(Car(placa, type,0F))
        dialog.cancel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carViewModel = ViewModelProviders.of(this).get(CarViewModel::class.java)
        typeCarViewModel = ViewModelProviders.of(this).get(TypeCarViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        car_recyclerView.layoutManager = linearLayoutManager

        var modelDialog = AlertDialog.Builder(context)
        val dialogView = layoutInflater.inflate(R.layout.dialog_addcar, null)

        modelDialog.setView(dialogView)
        var dialogCustom = modelDialog.create()

        addObserver()
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

    private fun addObserver() {
        val observer = Observer<List<Car>> { cars ->
            if (cars != null) {
                car_recyclerView.adapter =
                    com.kevinpinzon.administradorestacionamiento.view.adapters.RecyclerView(
                        cars
                    )
            }
        }
        carViewModel.allCars.observe(this, observer)
    }

    private fun addObserverTypes(dialogView: View) {
        val observer = Observer<List<TypeCar>> { types ->
            if (types != null) {

                if (types.isEmpty() ){
                    typeCarViewModel.insert(TypeCar(0,"oficial"))
                    typeCarViewModel.insert(TypeCar(1,"residente"))
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
}
