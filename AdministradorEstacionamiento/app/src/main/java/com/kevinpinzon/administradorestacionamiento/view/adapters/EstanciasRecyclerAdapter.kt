package com.kevinpinzon.administradorestacionamiento.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevinpinzon.administradorestacionamiento.R
import com.kevinpinzon.administradorestacionamiento.data.model.Register
import kotlinx.android.synthetic.main.card_estancia.view.*
import java.time.LocalDateTime

class EstanciasRecyclerAdapter(val estancias: List<Register>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : RecyclerView.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_estancia,parent,false)
        return ViewHolder(
            v
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.textvPlaca.text = "Placa: "+estancias[position].placa
        holder.itemView.textvDateIn.text = "Hora de entrada: "+estancias[position].timeIn
        if(estancias[position].timeOut.equals("N")){
            holder.itemView.textvDateOut.text = "Hora de salida: no registrada"
        }else{
            holder.itemView.textvDateOut.text = "Hora de salida: "+estancias[position].timeOut
        }
    }


    override fun getItemCount(): Int {
        return estancias.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

    }

    private fun strToDateTime(dateTimeStr: String): LocalDateTime {

        var dateAndTimeIn = dateTimeStr.split(" ")

        var dateInStr = dateAndTimeIn.get(0).split("/")
        var timeInStr = dateAndTimeIn.get(1).split(":")

        val year    = dateInStr[2].toInt()
        val month   = dateInStr[1].toInt()
        val day     = dateInStr[0].toInt()

        val hour    = timeInStr[0].toInt()
        val minute  = timeInStr[1].toInt()
        val seconds = timeInStr[2].toInt()

        val dateTime = LocalDateTime.of(year, month, day, hour, minute, seconds)

        println("TEST-"+dateTimeStr)
        println("TEST-"+dateTime)

        return dateTime
    }


}