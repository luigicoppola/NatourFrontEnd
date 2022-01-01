package com.ingsftw.natourfrontend


import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3
import okhttp3.*
import android.view.WindowManager
import androidx.fragment.app.Fragment
import java.util.*
import kotlin.collections.ArrayList

import android.widget.EditText
import com.google.firebase.auth.UserInfo
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

import retrofit2.Response
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {


    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrazione)

/*
        var email = findViewById<EditText>(R.id.nomeCompleto)
        var passw = findViewById<EditText>(R.id.passwordText)
        var continua = findViewById<Button>(R.id.continua_button)

*/

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        val viewPager: ViewPager2 = findViewById(R.id.view_pager2)

        val fragments: ArrayList<Fragment> = arrayListOf(
            ItemUno(),
            tre()


        )

        val adapter = ViewPagerAdapter(fragments, this)
        viewPager.adapter = adapter


        val indicator = findViewById<CircleIndicator3>(R.id.indicator)

        val registrazioneButton = findViewById(R.id.continua_button) as Button

        var email = findViewById<EditText>(R.id.emailText)
        var password = findViewById<EditText>(R.id.passwordText)
        var nomeCompleto = findViewById<EditText>(R.id.nomeText)
        var dataNascita = findViewById<EditText>(R.id.Date)
        var continua = findViewById<Button>(R.id.continua_button)



        registrazioneButton.setOnClickListener {

          createUser()

            }
        }

    fun createUser() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .build()

        // Create Service
        val service = retrofit.create(RestApi::class.java)

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("email", "mimmo@gmail.com")
        jsonObject.put("password", "Androide3@2")
        jsonObject.put("nomeCompleto", "ciao bello")
        jsonObject.put("dataNascita", "23/11/2000")

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.createUser(requestBody)

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
                    Toast.makeText(this@MainActivity,"ok",Toast.LENGTH_LONG)
                    Log.d("Pretty Printed JSON :", prettyJson)


                } else {
                    Toast.makeText(this@MainActivity,"eRRORE",Toast.LENGTH_LONG)
                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }


}






/*
        val registrazioneButton = findViewById(R.id.registrazioneButton) as Button

        registrazioneButton.setOnClickListener {

            viewPager.apply {
                beginFakeDrag()
                fakeDragBy(-10f)
                endFakeDrag()

            }


        }
        val continua = findViewById(R.id.bottone) as Button
        var email = findViewById<EditText>(R.id.email)
        var psw = findViewById<EditText>(R.id.pass)



        continua.setOnClickListener{
            psw.setVisibility(View.VISIBLE)
            val layoutParams = continua.layoutParams as RelativeLayout.LayoutParams
            layoutParams.setMargins(520, 300, 520, 100)
            continua.layoutParams = layoutParams

            }

*/

                    



























/*


class MainActivity : AppCompatActivity() {


    private val client = OkHttpClient()


    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imageList = mutableListOf<Int>()



    private var titleList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrazione)



        //postToList()
        val viewPager: ViewPager2 = findViewById(R.id.view_pager2)


        viewPager.adapter = ViewPagerAdapter(titleList, descList, imageList)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL



     viewPager.adapter = ViewPagerAdapter(titleList)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL






        val fragments: ArrayList<Fragment> = arrayListOf(
           ItemUno(),
           fragment_item_due()


        )

        val adapter = ViewPagerAdapter(fragments,this)
        viewPager.adapter = adapter





        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(viewPager)

        val continua = findViewById(R.id.continua_button) as Button

        continua.setOnClickListener{

            viewPager.apply{
                beginFakeDrag()
                fakeDragBy(-10f)
                endFakeDrag()

        }


        }

    }



    private fun addToList(title: String) {
        titleList.add(title)
    }



    private fun postToList() {
        for (i in 1..3) {

            addToList("Titolo $i")

        }
    }











    private fun addToList(title: String, description: String, image: Int) {
        titleList.add(title)
        descList.add(description)
        imageList.add(image)
    }

    private fun postToList() {
        for (i in 1..3) {

            addToList("Titolo $i", "Descrizione $i", R.drawable.email_section)

        }
    }



}


       val email = findViewById(R.id.email) as EditText
        val continua = findViewById(R.id.continua) as Button

        val layoutuno = findViewById<View>(R.id.Uno) as RelativeLayout
        val layoutdue = findViewById<View>(R.id.Due) as RelativeLayout
        layoutdue.setVisibility(View.GONE)



        //val password = findViewById<View>(R.id.psw) as TextView
        //password.setVisibility(View.GONE);




        continua.setOnClickListener {
            layoutuno.setVisibility(View.INVISIBLE)
            layoutdue.setVisibility(View.VISIBLE)

        }




    fun register(view: View){
        run("http://10.0.2.2:8080/utente/all")
    }

fun run(url: String){

    var testo= findViewById(R.id.editTextTextEmailAddress2) as TextView
    val request = Request.Builder().url(url).build()
    client.newCall(request).enqueue(object : Callback{
        override fun onFailure(call: Call, e: IOException) {
            testo.setText("failure")
            e.printStackTrace()
        }
        override fun onResponse(call: Call, response: Response) =
            testo.setText(response.body()?.string())
    })

}


*/



