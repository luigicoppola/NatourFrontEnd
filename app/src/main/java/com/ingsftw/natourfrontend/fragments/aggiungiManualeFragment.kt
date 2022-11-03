package com.ingsftw.natourfrontend.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.ingsftw.natourfrontend.R
import com.ingsftw.natourfrontend.RestApi
import com.ingsftw.natourfrontend.activities.HomeActivity
import com.ingsftw.natourfrontend.activities.conferma
import com.ingsftw.natourfrontend.dto.ItinerarioDto
import com.ingsftw.natourfrontend.dto.UserDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [aggiungiManualeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class aggiungiManualeFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val gsonMapper = Gson()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setStyle(STYLE_NORMAL, R.style.DialogTheme)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var rooterView: View = inflater.inflate(R.layout.fragment_popup_manuale, container, false)

        //createItinerarioManuale

        var aggiungiButton = rooterView.findViewById<Button>(com.ingsftw.natourfrontend.R.id.aggiungiItinerarioButton)
        aggiungiButton.setOnClickListener {

            //Acquisisco i dati inseriti dell'itinerario
            var nomeItinerario = rooterView.findViewById<EditText>(com.ingsftw.natourfrontend.R.id.nomeItinerarioText).text.toString()
            var punteggioItinerario = rooterView.findViewById<RatingBar>(com.ingsftw.natourfrontend.R.id.ratingBar).rating.toDouble()
            var difficoltaItinerario = rooterView.findViewById<RatingBar>(com.ingsftw.natourfrontend.R.id.ratingBar2).rating.toDouble()
            var durataItinerario = rooterView.findViewById<EditText>(com.ingsftw.natourfrontend.R.id.durataItinerarioText).text.toString()



            createItinerarioManuale(nomeItinerario,punteggioItinerario,difficoltaItinerario,durataItinerario,rooterView)

            dismiss()



            val transaction = activity?.supportFragmentManager?.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.ScrollHome, homeFragment())
            }
            if (transaction != null) {
                transaction.disallowAddToBackStack()
            }
            if (transaction != null) {
                transaction.commit()
            }

            }









        return rooterView
    }







    private fun createItinerarioManuale(nomeItinerario: String, punteggioItinerario: Double, difficoltaItinerario: Double, durataItinerario: String,rooterView: View) {
        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.18:8080/") // change this IP for testing by your actual machine IP
            //.baseUrl("http://192.168.1.4:8080/")


            //.baseUrl("http://192.168.1.3:8082/")
            .build()

        // Create Service
        val service = retrofit.create(RestApi::class.java)


        // prova con credenziali inserite
        //val coordinate = JSONArray()
        //val coordinata = JSONObject()
        //coordinata.put("esempio",2)
        //coordinate.put(coordinata)


        // Create JSON using JSONObject

        val jsonObject = JSONObject()



//


       // jsonObject.put("coordinate",coordinate)


        jsonObject.put("name",nomeItinerario)
        jsonObject.put("score", punteggioItinerario)
        jsonObject.put("difficulty", difficoltaItinerario)
        jsonObject.put("duration", durataItinerario)
        jsonObject.put("idUser", 1)



        /*
        val user = JSONObject()
        user.put("id",1)
        user.put("email","gigi@gmail.com")
        user.put("password","Coppolina94@")
        user.put("nomeCompleto","luigino")
        user.put("dataNascita","2021-12-21")
        user.put("enable",true)
        user.put("nomeCompleto","luigino")
        user.put("token","c865842f-f073-4ae0-8622-963c5f39c7d3")
        jsonObject.put("utente",user)
*/









        Toast.makeText(context,"Nome is: "+nomeItinerario, Toast.LENGTH_SHORT).show()
        Toast.makeText(context,"Punteggio is: "+punteggioItinerario, Toast.LENGTH_SHORT).show()
        Toast.makeText(context,"Difficolta is: "+difficoltaItinerario, Toast.LENGTH_SHORT).show()
        Toast.makeText(context,"Minuti are: "+durataItinerario, Toast.LENGTH_SHORT).show()


        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()
        Log.d("STRINGA---> :", jsonObjectString)




        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        Log.d("REQUESTBODY---> :", requestBody.toString())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.createItinerarioManuale(requestBody)


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

/*                val jsonArray = JSONObject(prettyJson).getJSONObject("data")
                    val itemType = object : TypeToken<ItinerarioDto>() {}.type
                    val itinerario =
                        this@aggiungiManualeFragment.gsonMapper.fromJson<ItinerarioDto>(
                            jsonArray.toString(),
                            itemType
                        )
                    println(itinerario.toString())*/



                  //  Log.d("Itinerario creato: ", itinerario.toString())







                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())


                }



            }


        }}


















    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment aggiungiManualeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                aggiungiManualeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}