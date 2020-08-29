package com.kevinpinzon.administradorestacionamiento.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevinpinzon.administradorestacionamiento.R
import com.kevinpinzon.administradorestacionamiento.data.model.Car
import kotlinx.android.synthetic.main.card_car.view.*


class RecyclerView(val cars: List<Car>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.card_car,parent,false)

        return ViewHolder(
            v
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.textvPlaca.text = "Placa: "+cars[position].placa
        if(cars[position].type == "residente"){

            var totalTopay: Double = cars[position].totalTime.toString().toDouble() * 0.05

            holder.itemView.textvType.visibility = View.VISIBLE
            holder.itemView.textvToPay.visibility = View.VISIBLE

            holder.itemView.textvType.text  = "Tiempo acumulado:" + cars[position].totalTime.toString() + " minutos"
            holder.itemView.textvToPay.text  = "Total acumulado a pagar a fin de mes: $" + totalTopay
        }else{
            holder.itemView.textvType.visibility = View.GONE
            holder.itemView.textvToPay.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return cars.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
    }

}