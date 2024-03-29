package com.ingsftw.natourfrontend.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser

import com.ingsftw.natourfrontend.RestApi
import com.ingsftw.natourfrontend.activities.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import androidx.fragment.app.*
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.ingsftw.natourfrontend.dto.ItinerarioDto
import com.ingsftw.natourfrontend.dto.UserDto
import com.ingsftw.natourfrontend.utils.Communicator


class PopupLogin: DialogFragment() {

    private val gsonMapper = Gson()
    private lateinit var comm: Communicator




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var rooterView: View = inflater.inflate(com.ingsftw.natourfrontend.R.layout.activity_login, container, false)


        var closeLink = rooterView.findViewById<ImageButton>(com.ingsftw.natourfrontend.R.id.closeButton)

        var accedi = rooterView.findViewById<Button>(com.ingsftw.natourfrontend.R.id.loginButton)
        closeLink.setOnClickListener{
            dismiss()
        }



         var recupero_psw = rooterView.findViewById<TextView>(com.ingsftw.natourfrontend.R.id.intestazioneDimenticata)



        recupero_psw.setOnClickListener{

            PopupRecuperoPsw().show(
                childFragmentManager, "fragment")



        }


        accedi.setOnClickListener{
            var email = rooterView.findViewById<EditText>(com.ingsftw.natourfrontend.R.id.emailText).text.toString()
            var passw = rooterView.findViewById<EditText>(com.ingsftw.natourfrontend.R.id.passwordText).text.toString()

            login(email,passw,rooterView)


        }


        return rooterView

    }






    private fun login(email: String, passw : String, rooterView: View) {
        // Create Retrofit
        val retrofit = Retrofit.Builder()


             .baseUrl("http://192.168.1.18:8080/") // change this IP for testing by your actual machine IP
            //.baseUrl("http://192.168.1.4:8080")


             //.baseUrl("http://192.168.1.3:8082/")
            .build()

        // Create Service
        val service = retrofit.create(RestApi::class.java)


        // prova con credenziali inserite



        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("email",email)
        jsonObject.put("password", passw)


        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.login(requestBody)

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

                    val jsonArray = JSONObject(prettyJson).getJSONObject("data")
                    val itemType = object : TypeToken<UserDto>() {}.type



                    val utente = this@PopupLogin.gsonMapper.fromJson<UserDto>(jsonArray.toString(), itemType)



                   // Log.e("UTENTE---->",UserDto.)




                    var emailUserRegistrato = utente

                    println(emailUserRegistrato.toString())






                    activity?.let{
                        val intent = Intent (it, HomeActivity::class.java)
                        intent.putExtra("data",emailUserRegistrato)
                        it.startActivity(intent)

                    }


                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())


                }





            }

        }

    }




}

