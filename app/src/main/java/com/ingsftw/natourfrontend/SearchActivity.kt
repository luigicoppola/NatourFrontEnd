package com.ingsftw.natourfrontend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.ingsftw.natourfrontend.adapters.HorizontalAdapter
import com.ingsftw.natourfrontend.dto.ItinerarioDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Retrofit

class SearchActivity : AppCompatActivity() {

    // on below line we are
    // creating variables for listview
    lateinit var programmingLanguagesLV: ListView

    // creating array adapter for listview
    lateinit var listAdapter: ArrayAdapter<String>

    // creating array list for listview
    lateinit var programmingLanguagesList: ArrayList<String>;

    // creating variable for searchview
    lateinit var searchView: SearchView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_name_activity)



    // initializing variables of list view with their ids.
    programmingLanguagesLV = findViewById(R.id.idLVProgrammingLanguages)
    searchView = findViewById(R.id.idSV)

    // initializing list and adding data to list
    programmingLanguagesList = ArrayList()
    programmingLanguagesList.add("C")
    programmingLanguagesList.add("C#")
    programmingLanguagesList.add("Java")
    programmingLanguagesList.add("Javascript")
    programmingLanguagesList.add("Python")
    programmingLanguagesList.add("Dart")
    programmingLanguagesList.add("Kotlin")
    programmingLanguagesList.add("Typescript")

    // initializing list adapter and setting layout
    // for each list view item and adding array list to it.
    listAdapter = ArrayAdapter<String>(
    this,
    android.R.layout.simple_list_item_1,
    programmingLanguagesList
    )

    // on below line setting list
    // adapter to our list view.
    programmingLanguagesLV.adapter = listAdapter

    // on below line we are adding on query
    // listener for our search view.
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            // on below line we are checking
            // if query exist or not.
            if (programmingLanguagesList.contains(query)) {
                // if query exist within list we
                // are filtering our list adapter.
                listAdapter.filter.filter(query)
            } else {
                // if query is not present we are displaying
                // a toast message as no data found..
                Toast.makeText(this@SearchActivity, "No Language found..", Toast.LENGTH_LONG)
                    .show()
            }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            // if query text is change in that case we
            // are filtering our adapter with
            // new text on below line.
            listAdapter.filter.filter(newText)
            return false
        }
    })
}


    private fun createItinerari(listaItinerari : Array<ItinerarioDto>, rooterView: View) {

        val recycler = rooterView.findViewById<RecyclerView>(R.id.recyclerInEvidenza)
        val recyclerVicinanze = rooterView.findViewById<RecyclerView>(R.id.recyclerVicinanze)
        val recyclerPreferiti = rooterView.findViewById<RecyclerView>(R.id.recyclerPreferiti)



        val data: ArrayList<ItinerarioDto> = ArrayList()






        for(i in 1..listaItinerari.size){


            var itinerario = ItinerarioDto(
                listaItinerari[i-1].id,
                listaItinerari[i-1].nome,
                listaItinerari[i-1].durata,
                listaItinerari[i-1].difficolta,
                listaItinerari[i-1].punteggio,
              //  listaItinerari[i-1].coordinate,
                listaItinerari[i-1].utente
            )
            data.add(itinerario)


        }




        //setting recycler to horizontal scroll
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        //setting adapter to recycler
        recycler.adapter = HorizontalAdapter(data)




    }






}

