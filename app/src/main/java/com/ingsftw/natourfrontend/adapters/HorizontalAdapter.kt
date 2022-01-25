package com.ingsftw.natourfrontend.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ingsftw.natourfrontend.R
import com.ingsftw.natourfrontend.dto.ItinerarioDto


class HorizontalAdapter(val data: ArrayList<ItinerarioDto>) :
    RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: HorizontalAdapter.ViewHolder, position: Int) {
        holder.titolo.text = data[position].nome
        holder.durata.text = data[position].durata.toString()
        holder.punteggio.text = data[position].punteggio.toString()
        holder.difficolta.text = data[position].difficolta.toString()



    }





    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalAdapter.ViewHolder {
       val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_card,parent,false)
        return ViewHolder(view)
    }





    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titolo: TextView = itemView.findViewById(R.id.titoloText)
        val durata: TextView = itemView.findViewById(R.id.durataText)
        val punteggio: TextView = itemView.findViewById(R.id.punteggioText)
        val difficolta: TextView = itemView.findViewById(R.id.difficoltaText)

    }


}