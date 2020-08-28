package com.kevinpinzon.administradorestacionamiento.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kevinpinzon.administradorestacionamiento.R
import com.kevinpinzon.administradorestacionamiento.data.model.Car
import com.kevinpinzon.administradorestacionamiento.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.recycler.*

class ListCarActivity : AppCompatActivity() {

    private lateinit var carViewModel: CarViewModel
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler)

        title = intent.getStringExtra("title")

        val actionbar = supportActionBar
        actionbar!!.title = title

        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        carViewModel = ViewModelProviders.of(this).get(CarViewModel::class.java)
        addObserver()

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        recyclerView.layoutManager = linearLayoutManager
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun addObserver() {
        val observer = Observer<List<Car>> { cars ->
            if (cars != null) {
                recyclerView.adapter =
                    com.kevinpinzon.administradorestacionamiento.view.adapters.RecyclerView(
                        cars
                    )
                println("TEST-cars:"+cars)
            }
        }

        if(title.equals("Residentes")){
                carViewModel.allResidentCars.observe(this, observer)
        }else {
            carViewModel.allOficialCars.observe(this, observer)
        }

    }

}
