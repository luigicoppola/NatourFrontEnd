package com.ingsftw.natourfrontend


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.util.*

import androidx.fragment.app.DialogFragment.STYLE_NO_FRAME
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3


class MainActivity : AppCompatActivity() {


    private val client = OkHttpClient()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // -------------------- GESTIONE LOGIN ---------------------------------

        var accedi = findViewById<TextView>(R.id.accediLinkText)
        accedi.setOnClickListener() {
            var dialog = PopupLogin()
            dialog.setStyle(STYLE_NO_FRAME, android.R.style.ThemeOverlay);
            dialog.show(supportFragmentManager, "PopupLogin")
        }

        // -------------------- FINE GESTIONE LOGIN --------------------------- //

        val viewPager: ViewPager2 = findViewById(R.id.view_pager2)

        val fragments: ArrayList<Fragment> = arrayListOf(
            SwipeItemUno(),
            SwipeItemDue()
        )

        val adapter = ViewPagerAdapter(fragments, this)
        viewPager.adapter = adapter

        val indicator = findViewById<CircleIndicator3>(R.id.indicator)







        /*val registrazioneButton = findViewById(R.id.accedi_button) as Button



        val email = findViewById<EditText>(R.id.emailText)
        val passw = findViewById<EditText>(R.id.passwordText)
        val nomeCompleto = findViewById<EditText>(R.id.nomeText)
        val dataNascita = findViewById<EditText>(R.id.Date)*/


        var continua = findViewById<Button>(R.id.accedi_button)

       continua.setOnClickListener{
           indicator.setViewPager(viewPager)
           viewPager.setCurrentItem(1, true)


       }








        viewPager?.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == 0) {
                        continua.setText("Continua")
                    } else {

                        continua.setText("Registrati")

                    }
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)


                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }
            })


        // -------------------- GESTIONE REGISTRAZIONE ------------------------ //

    }

















        // -------------------- FINE GESTIONE REGISTRAZIONE --------------------------- //

}







/*
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        val viewPager: ViewPager2 = findViewById(R.id.view_pager2)

        val fragments: ArrayList<Fragment> = arrayListOf(
            ItemUno(),
            tre()


        )




        val adapter = ViewPagerAdapter(fragments, this)

        viewPager.adapter = adapter


        val indicator = findViewById<CircleIndicator3>(R.id.indicator)

        val registrazioneButton = findViewById(R.id.accedi_button) as Button


        val email = findViewById<EditText>(R.id.emailText)
        val passw = findViewById<EditText>(R.id.passwordText)
        val nomeCompleto = findViewById<EditText>(R.id.nomeText)
        val dataNascita = findViewById<EditText>(R.id.Date)


        var continua = findViewById<Button>(R.id.accedi_button)


        if (viewPager.currentItem == 0)
            continua.setText("Continua")
        else
            continua.setText("Registrati")






        registrazioneButton.setOnClickListener {



        if (viewPager.currentItem == 0) {

            viewPager.setCurrentItem(1, true)
        } else
            createUser(
                email.text.toString(),
                passw.toString(),
                nomeCompleto.text.toString(),
                dataNascita.text.toString()
            )


    }







    viewPager?.registerOnPageChangeCallback(
    object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            if (position == 0) {

                continua.setText("Continua")
            } else {

                continua.setText("Registrati")

            }
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)


        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }
    })










    }

}

    private fun createUser(email: String, passw: String, nomeCompleto : String, dataNascita: String) {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .build()

        // Create Service
        val service = retrofit.create(RestApi::class.java)




        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("email",email)
        jsonObject.put("password", passw)
        jsonObject.put("nomeCompleto", nomeCompleto)
        jsonObject.put("dataNascita", dataNascita)

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
                    Log.d("Pretty Printed JSON :", prettyJson)


                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }



*/



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

