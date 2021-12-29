package com.ingsftw.natourfrontend


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3
import okhttp3.*
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


class MainActivity : AppCompatActivity() {


    private val client = OkHttpClient()


    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imageList = mutableListOf<Int>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrazione)



        postToList()
        val viewPager: ViewPager2 = findViewById(R.id.view_pager2)


        viewPager.adapter = ViewPagerAdapter(titleList, descList, imageList)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL



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
/*

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


*/

/*
    fun register(view: View){
        run("http://10.0.2.2:8080/utente/all")
    }
*/
/*
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




