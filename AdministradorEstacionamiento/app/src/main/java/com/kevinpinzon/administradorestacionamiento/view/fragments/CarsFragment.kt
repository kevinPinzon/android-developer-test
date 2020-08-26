package com.kevinpinzon.administradorestacionamiento.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kevinpinzon.administradorestacionamiento.R
import com.kevinpinzon.administradorestacionamiento.data.model.Car
import com.kevinpinzon.administradorestacionamiento.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.fragment_cars.*
import kotlinx.android.synthetic.main.dialog_addcar.*


/**
 * A simple [Fragment] subclass.
 */
class CarsFragment : Fragment() {

    private lateinit var carViewModel: CarViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

    }

    private fun addCar() {
        carViewModel.insert(Car("PVCD 22",1,0F))
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

        // Specify layout for recycler view
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        car_recyclerView.layoutManager = linearLayoutManager

        var modelDialog = AlertDialog.Builder(context)
        val dialogView = layoutInflater.inflate(R.layout.dialog_addcar, null)
        modelDialog.setView(dialogView)
        var dialogCustom = modelDialog.create()

        addObserver()

        btn_addCar.setOnClickListener {

            dialogCustom.show()

            /*textView_save.setOnClickListener {
                println("save")
            }

            textView_cancel.setOnClickListener {
                println("cancel")
                dialogCustom.cancel()
            }*/



            //editText_placa.text.toString()


        //    addCar()
        }

    }

    private fun addObserver() {
        val observer = Observer<List<Car>> { cars ->
            if (cars != null) {
                var text = ""

                car_recyclerView.adapter =
                    com.kevinpinzon.administradorestacionamiento.view.adapters.RecyclerView(
                        cars
                    )

                for (car in cars) {
                    println(car)
                }
            }
        }
        carViewModel.allCars.observe(this, observer)
    }
}
