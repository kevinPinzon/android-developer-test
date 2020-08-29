package com.kevinpinzon.administradorestacionamiento.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.kevinpinzon.administradorestacionamiento.R
import com.kevinpinzon.administradorestacionamiento.view.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter by lazy {
        ViewPagerAdapter(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Administrador de estacionamiento"

        pager.adapter = adapter
        val tabLayoutMediator = TabLayoutMediator(tab_layout, pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when(position){
                    0 -> {
                        tab.text = "Estancias"
                        tab.setIcon(R.drawable.ic_barrier)
                    }
                    1 -> {
                        tab.text = "Vehiculos"
                        tab.setIcon(R.drawable.ic_car)
                    }
                }
            })
        tabLayoutMediator.attach()

    }
}
