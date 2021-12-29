package com.ingsftw.natourfrontend;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class ViewPagerAdapter(private var title: MutableList<String>, private var details: MutableList<String>, private var images: MutableList<Int>) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

     inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // val itemImage: ImageView = itemView.findViewById(R.id.imageView4)
         //val itemImage2: ImageView = itemView.findViewById(R.id.imageView5)

         val itemTitle: TextView = itemView.findViewById(R.id.email)

         val itemTitle2: TextView = itemView.findViewById(R.id.psw)
//        val itemDetails: TextView = itemView.findViewById(R.id.tvAbout)
   //     val itemImage: ImageView = itemView.findViewById(R.id.tvImage)

         /*init {

             itemImage.setOnClickListener { v: View ->
                 val position = adapterPosition
                 Toast.makeText(
                     itemView.context,
                     "You clicked on item #$(position+1)",
                     Toast.LENGTH_SHORT
                 ).show()

             }
         }*/
     }


         override fun onCreateViewHolder(parent: ViewGroup,ViewType: Int): ViewPagerAdapter.Pager2ViewHolder {
             return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))
         }


         override fun getItemCount(): Int {
             return title.size
         }

         override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
             //holder.itemTitle.text = title[position]
             /*holder.itemDetails.text = details[position]*/
             //holder.itemImage.setImageResource(images[1])
             //holder.itemImage2.setImageResource(images[2])
             //holder.itemTitle.text = title[position]
             //holder.itemTitle2.text = title[position]

         }




     }


