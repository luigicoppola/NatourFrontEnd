package com.ingsftw.natourfrontend.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ingsftw.natourfrontend.R
import com.squareup.picasso.Picasso




class HorizontalAdapter(val data: ArrayList<String>) :
    RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: HorizontalAdapter.ViewHolder, position: Int) {
      holder.firstName.text = data[position]
    }





    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalAdapter.ViewHolder {
       val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_card,parent,false)
        return ViewHolder(view)
    }







    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstName: TextView = itemView.findViewById(R.id.textView2)
    }


}