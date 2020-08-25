package com.kevinpinzon.administradorestacionamiento.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.kevinpinzon.administradorestacionamiento.R
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
        pager.adapter = adapter
        val tabLayoutMediator = TabLayoutMediator(tab_layout, pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when(position){
                    0 -> {
                        tab.text = "Registros"
                        tab.setIcon(R.drawable.ic_parking_barrier)
                    }
                    1 -> {
                        tab.text = "Reporte"
                        tab.setIcon(R.drawable.ic_car)
                    }
                    2 -> {
                        tab.text = "Vehiculos"
                        tab.setIcon(R.drawable.ic_add_black_24dp)
                    }
                }
            })
        tabLayoutMediator.attach()

    }
}
