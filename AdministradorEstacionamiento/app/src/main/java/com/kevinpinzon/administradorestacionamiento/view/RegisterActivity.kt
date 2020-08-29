package com.kevinpinzon.administradorestacionamiento.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kevinpinzon.administradorestacionamiento.R
import com.kevinpinzon.administradorestacionamiento.data.model.Register
import com.kevinpinzon.administradorestacionamiento.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.recycler.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var rgisterViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler)

        val actionbar = supportActionBar
        actionbar!!.title = "Estancias"

        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        rgisterViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        addObserver()

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        recyclerView.layoutManager = linearLayoutManager
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun addObserver() {
        val observer = Observer<List<Register>> { registers ->
            if (registers != null) {
                recyclerView.adapter =
                    com.kevinpinzon.administradorestacionamiento.view.adapters.EstanciasRecyclerAdapter(
                        registers
                    )
            }
        }

        rgisterViewModel.allRegisters.observe(this, observer)


    }
}
