package com.ingsftw.natourfrontend.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemAdapterVH>() {


    class ItemAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
            print("clic")        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterVH {
        TODO("Not yet implemented")


    }

    override fun onBindViewHolder(holder: ItemAdapterVH, position: Int) {
        TODO("Not yet implemented")





    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}