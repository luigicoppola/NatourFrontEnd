package com.ingsftw.natourfrontend.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.ingsftw.natourfrontend.R
import com.ingsftw.natourfrontend.RestApi
import com.ingsftw.natourfrontend.SearchActivity
import com.ingsftw.natourfrontend.adapters.HorizontalAdapter
import com.ingsftw.natourfrontend.dto.ItinerarioDto
import com.ingsftw.natourfrontend.utils.CommunicationInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Retrofit


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"





/**
 * A simple [Fragment] subclass.
 * Use the [homeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class homeFragment : DialogFragment(){
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var inflaterClass : LayoutInflater
    private lateinit var containerClass : ViewGroup



    private val gsonMapper = Gson()

    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton

    private lateinit var listener : CommunicationInterface



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }



    }








    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var rooterView: View = inflater.inflate(com.ingsftw.natourfrontend.R.layout.fragment_home_scroll, container, false)


        // ----------------- ITINERARI PREFERITI -----------------------------//




        //-------------FINE ITINERARI PREFERITI ----------------------------- //













        //creating a  arraylist of data


       itinerariApi(rooterView)


      //      GESTIONE POPUP FILTRI

        var filtro = rooterView.findViewById<ImageButton>(R.id.filterButton)


        filtro.setOnClickListener{
           //val sortRecipesBottomSheet = BottomSheetFiltri()
           // sortRecipesBottomSheet.show(childFragmentManager,sortRecipesBottomSheet.tag)
            val intent = Intent (context, SearchActivity::class.java)
            startActivity(intent)



        }






        // FINE POPUP FILTRI





        //GESTIONE CARDS





        //FINE GESTIONE CARDS



        val searchView= rooterView.findViewById<SearchView>(R.id.searchItinerari)
        val args = this.arguments
        val inputData= args?.get("data")
        searchView.setQueryHint(inputData.toString())


    searchView.setOnSearchClickListener(){
        var search_fragment = profiloFragment()
        getParentFragmentManager().beginTransaction().add(R.id.layout_profilo, search_fragment)
            .commit()
    }







        return rooterView
    }




    private fun createItinerari( listaItinerari : Array<ItinerarioDto> , rooterView: View) {

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
                //listaItinerari[i-1].coordinate,
                listaItinerari[i-1].utente
            )
            data.add(itinerario)


        }




        //setting recycler to horizontal scroll
        recycler.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
         //setting adapter to recycler
        recycler.adapter = HorizontalAdapter(data)

        //setting recycler to horizontal scroll
        recyclerVicinanze.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        //setting adapter to recycler
        recyclerVicinanze.adapter = HorizontalAdapter(data)


        //setting recycler to horizontal scroll
       recyclerPreferiti.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        //setting adapter to recycler
        recyclerPreferiti.adapter = HorizontalAdapter(data)





    }


    private fun itinerariApi(rootView: View) {
        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.18:8080/") // change this IP for testing by your actual machine IP
            //.baseUrl("http://192.168.1.3:8082/")

            .build()

        // Create Service
        val service = retrofit.create(RestApi::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val response = service.itinerariAPI()


            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )

                    Log.d("Pretty Printed JSON :", prettyJson)
                    val jsonArray = JSONObject(prettyJson).getJSONArray("data")
                    val itemType = object : TypeToken<Array<ItinerarioDto>>() {}.type
                    val itinerari = this@homeFragment.gsonMapper.fromJson<Array<ItinerarioDto>>(jsonArray.toString(), itemType)

                    var listener: ((itinerari: Array<ItinerarioDto>) -> Unit)? = null




                    println(itinerari.toString())
                    createItinerari(itinerari,rootView)



                   // Log.e("Errore qui --- : ",itinerari.toString())





                    // var titolo = findViewById<TextView>(R.layout.layout_card.titoloText)


                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }


            }




        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment homeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            homeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}