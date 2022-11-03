package com.ingsftw.natourfrontend.activities

import android.R
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ingsftw.natourfrontend.dto.ItinerarioDto


class DetailActivity : AppCompatActivity() {
    var selectedShape: ItinerarioDto? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.ingsftw.natourfrontend.R.layout.activity_detail)
        getSelectedShape()
        setValues()
    }

    private fun getSelectedShape() {
        val previousIntent = intent
        val parsedStringID = previousIntent.getStringExtra("id")
      //  selectedShape = SearchActivity.shapeList.get(Integer.valueOf(parsedStringID))
    }

    private fun setValues() {



        val titoloItinerario = findViewById<TextView>(com.ingsftw.natourfrontend.R.id.titoloText)
        val durataItinerario = findViewById<TextView>(com.ingsftw.natourfrontend.R.id.durataText)
        val difficoltaItinerario = findViewById<TextView>(com.ingsftw.natourfrontend.R.id.difficoltaText)
        val punteggioItinerario = findViewById<TextView>(com.ingsftw.natourfrontend.R.id.punteggioText)

        titoloItinerario.setText(selectedShape!!.nome)
        durataItinerario.setText(selectedShape!!.durata.toString())
        difficoltaItinerario.setText(selectedShape!!.difficolta.toString())
        punteggioItinerario.setText(selectedShape!!.punteggio.toString())








    }
}