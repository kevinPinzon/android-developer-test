package com.kevinpinzon.administradorestacionamiento.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevinpinzon.administradorestacionamiento.R
import com.kevinpinzon.administradorestacionamiento.data.model.Car
import com.kevinpinzon.administradorestacionamiento.data.model.TypeCar
import kotlinx.android.synthetic.main.card_car.view.*


class RecyclerView(val cars: List<Car>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : RecyclerView.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_car,parent,false)
        return ViewHolder(
            v
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.textvPlaca.text = cars[position].placa.toString()
        holder.itemView.textvType.text = cars[position].type.toString()
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

    }
}