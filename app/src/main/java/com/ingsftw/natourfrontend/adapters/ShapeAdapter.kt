package com.ingsftw.natourfrontend.adapters

import android.R
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ingsftw.natourfrontend.dto.ItinerarioDto
import com.ingsftw.natourfrontend.fragments.BottomSheetAggiungi
import kotlinx.coroutines.NonDisposableHandle.parent


class ShapeAdapter(context: Context, resource: Int, shapeList: List<ItinerarioDto>) :
    ArrayAdapter<ItinerarioDto?>(context, resource, shapeList) {
   private lateinit var recv:RecyclerView
   private lateinit var layoutManager: LinearLayoutManager
   private lateinit var adapter: ShapeAdapter
   private lateinit var content: List<ItinerarioDto>







 /*

    fun getViewAdapter(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView: View? = convertView
        val shape: ItinerarioDto? = getItem(position)
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(com.ingsftw.natourfrontend.R.layout.layout_card, parent, false)
        }
        val titoloItinerario = convertView?.findViewById<TextView>(com.ingsftw.natourfrontend.R.id.titoloText)
        val durataItinerario = convertView?.findViewById<TextView>(com.ingsftw.natourfrontend.R.id.durataText)
        val difficoltaItinerario = convertView?.findViewById<TextView>(com.ingsftw.natourfrontend.R.id.difficoltaText)
        val punteggioItinerario = convertView?.findViewById<TextView>(com.ingsftw.natourfrontend.R.id.punteggioText)

        if (shape != null) {
            if (titoloItinerario != null) {
                titoloItinerario.setText(shape.nome)
            }
        }
        if (shape != null) {
            if (durataItinerario != null) {
                durataItinerario.setText(shape.nome)
            }
        }
        if (shape != null) {
            if (difficoltaItinerario != null) {
                difficoltaItinerario.setText(shape.nome)
            }
        }
        if (shape != null) {
            if (punteggioItinerario != null) {
                punteggioItinerario.setText(shape.nome)
            }
        }




        return convertView
    }
*/


}

