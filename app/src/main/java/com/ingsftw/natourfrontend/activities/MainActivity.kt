package com.ingsftw.natourfrontend.activities


import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.util.*

import androidx.fragment.app.DialogFragment.STYLE_NO_FRAME
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import me.relex.circleindicator.CircleIndicator3
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.window.layout.WindowMetricsCalculator
import com.facebook.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.ingsftw.natourfrontend.*
import com.ingsftw.natourfrontend.adapters.ViewPagerAdapter
import com.ingsftw.natourfrontend.fragments.PopupLogin
import com.ingsftw.natourfrontend.fragments.SwipeItemDue
import com.ingsftw.natourfrontend.fragments.SwipeItemUno
import com.ingsftw.natourfrontend.utils.TwitterConstants
import kotlinx.coroutines.*
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder
import android.widget.RelativeLayout







class MainActivity : AppCompatActivity() {


    private val client = OkHttpClient()

    //GOOGLE DICHIARAZIONI
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001

    //FACEBOOK DICHIARAZIONI
    lateinit var callbackManager: CallbackManager

    enum class WindowSizeClass { COMPACT, MEDIUM, EXPANDED }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*val item = findViewById<View>(R.id.registrazioneLayout) as ConstraintLayout
        val schermata: View = layoutInflater.inflate(R.layout.activity_main, null)


        item.addView(object : View(this) {
            override fun onConfigurationChanged(newConfig: Configuration?) {
                super.onConfigurationChanged(newConfig)
                computeWindowSizeClasses()
            }
        })
*/



        // ---------------- FINE LAYOUT ---------------------







        // -----------------  ACCESSO CON GOOGLE ------------------------
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("909854952671-uj320lnridp9abd1scsd6307tro179nl.apps.googleusercontent.com")
            .requestEmail()
            .build()

       /* var googleButton = findViewById<ImageButton>(R.id.googleContinua)

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        googleButton.setOnClickListener {
            signIn()
        }

        // -----------------  FINE CON GOOGLE ------------------------


        // -------------------- ACCESSO CON TWITTER -----------------------
        var twitterButton = findViewById<ImageButton>(R.id.twitterContinua)

        twitterButton.setOnClickListener {
            getRequestToken()
        }

*/
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


        //val registrazioneButton = findViewById(R.id.accedi_button) as Button


        var email_input = findViewById<EditText>(R.id.passwordText)

        var continua = findViewById<Button>(R.id.accedi_button)

        var email = ""
        var passw = ""
        var nomeCompleto = ""
        var dataNascita = ""

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







        continua.setOnClickListener {


            if (findViewById<EditText>(R.id.passwordText) != null) {
                email = findViewById<EditText>(R.id.passwordText).text.toString()
                email_input = findViewById<EditText>(R.id.passwordText)

            }

            if (findViewById<EditText>(R.id.passwordText) != null)
                passw = findViewById<EditText>(R.id.passwordText).text.toString()


            if (findViewById<EditText>(R.id.nomeText) != null)
                nomeCompleto = findViewById<EditText>(R.id.nomeText).text.toString()



            if (findViewById<EditText>(R.id.dataNascita) != null)
                dataNascita = findViewById<EditText>(R.id.dataNascita).text.toString()




            indicator.setViewPager(viewPager)
            viewPager.setCurrentItem(1, true)



            if (continua.getText() == "Registrati") {


                if (email == "") {
                    //


                }

                if (passw == "") {
                    //

                }

                if (nomeCompleto == "") {
                    // Toast.makeText(this,"Email mancante.",Toast.LENGTH_LONG).show()

                }

                if (dataNascita == "") {
                    // Toast.makeText(this,"Email mancante.",Toast.LENGTH_LONG).show()

                }

                //                createUser(email,passw,nomeCompleto,dataNascita)

            }


        }


        //FUNZIONI FB


        //FUNZIONI GOOGLE

/*




    }

*/


        // -------------------- FUNZIONI REGISTRAZIONE ------------------------ //


        fun createUser(email: String, passw: String, nomeCompleto: String, dataNascita: String) {

            // Create Retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .build()

            // Create Service
            val service = retrofit.create(RestApi::class.java)


            // Create JSON using JSONObject
            val jsonObject = JSONObject()
            jsonObject.put("email", email)
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


    }

    private fun computeWindowSizeClasses() {
        val metrics = WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(this)

        val widthDp = metrics.bounds.width() /
                resources.displayMetrics.density
        val widthWindowSizeClass = when {
            widthDp < 600f -> WindowSizeClass.COMPACT
            widthDp < 840f -> WindowSizeClass.MEDIUM
            else -> WindowSizeClass.EXPANDED
        }

        val heightDp = metrics.bounds.height() /
                resources.displayMetrics.density
        val heightWindowSizeClass = when {
            heightDp < 480f -> WindowSizeClass.COMPACT
            heightDp < 900f -> WindowSizeClass.MEDIUM
            else -> WindowSizeClass.EXPANDED
        }
    }}

    // ------------- FUNZIONI TWITTER -------------------------
/*lateinit var twitter: Twitter

    private fun getRequestToken() {
        GlobalScope.launch(Dispatchers.Default) {
            val builder = ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(TwitterConstants.CONSUMER_KEY)
                .setOAuthConsumerSecret(TwitterConstants.CONSUMER_SECRET)
                .setIncludeEmailEnabled(true)
            val config = builder.build()
            val factory = TwitterFactory(config)
            twitter = factory.instance
            try {
                val requestToken = twitter.oAuthRequestToken
                withContext(Dispatchers.Main) {
                    setupTwitterWebviewDialog(requestToken.authorizationURL)
                }
            } catch (e: IllegalStateException) {
                Log.e("ERROR: ", e.toString())
            }
        }
    }

    lateinit var twitterDialog: Dialog
    var accToken: AccessToken? = null

    // Show twitter login page in a dialog
    @SuppressLint("SetJavaScriptEnabled")
    fun setupTwitterWebviewDialog(url: String) {
        twitterDialog = Dialog(this)
        val webView = WebView(this)
        webView.isVerticalScrollBarEnabled = false
        webView.isHorizontalScrollBarEnabled = false
        webView.webViewClient = TwitterWebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
        twitterDialog.setContentView(webView)
        twitterDialog.show()
    }

    // A client to know about WebView navigations
    // For API 21 and above
    @Suppress("OverridingDeprecatedMember")
    inner class TwitterWebViewClient : WebViewClient() {
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            if (request?.url.toString().startsWith(TwitterConstants.CALLBACK_URL)) {
                Log.d("Authorization URL: ", request?.url.toString())
                handleUrl(request?.url.toString())

                // Close the dialog after getting the oauth_verifier
                if (request?.url.toString().contains(TwitterConstants.CALLBACK_URL)) {
                    twitterDialog.dismiss()
                }
                return true
            }
            return false
        }

        // For API 19 and below
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url.startsWith(TwitterConstants.CALLBACK_URL)) {
                Log.d("Authorization URL: ", url)
                handleUrl(url)

                // Close the dialog after getting the oauth_verifier
                if (url.contains(TwitterConstants.CALLBACK_URL)) {
                    twitterDialog.dismiss()
                }
                return true
            }
            return false
        }

        // Get the oauth_verifier
        private fun handleUrl(url: String) {
            val uri = Uri.parse(url)
            val oauthVerifier = uri.getQueryParameter("oauth_verifier") ?: ""
            val launch = GlobalScope.launch(Dispatchers.Main) {
               // accToken =
              //      withContext(Dispatcher.IO) { twitter.getOAuthAccessToken(oauthVerifier) }
               // getUserProfile()
            }
        }

        suspend fun getUserProfile() {
            val usr = withContext(Dispatchers.IO) { twitter.verifyCredentials() }

            //Twitter Id
            val twitterId = usr.id.toString()
            Log.d("Twitter Id: ", twitterId)

            //Twitter Handle
            val twitterHandle = usr.screenName
            Log.d("Twitter Handle: ", twitterHandle)

            //Twitter Name
            val twitterName = usr.name
            Log.d("Twitter Name: ", twitterName)

            //Twitter Email
            val twitterEmail = usr.email
            Log.d("Twitter Email: ", twitterEmail ?: "'Request email address from users' on the Twitter dashboard is disabled")

            // Twitter Profile Pic URL
            val twitterProfilePic = usr.profileImageURLHttps.replace("_normal", "")
            Log.d("Twitter Profile URL: ", twitterProfilePic)

            // Twitter Access Token
            Log.d("Twitter Access Token", accToken?.token ?: "")

        }
    }


    // --------------------------- FINE ON CREATE ---------------------------

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(
            signInIntent, RC_SIGN_IN
        )
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                ApiException::class.java
            )
            // Signed in successfully
            val googleId = account?.id ?: ""
            Log.i("Google ID", googleId)

            val googleFirstName = account?.givenName ?: ""
            Log.i("Google First Name", googleFirstName)

            val googleLastName = account?.familyName ?: ""
            Log.i("Google Last Name", googleLastName)

            val googleEmail = account?.email ?: ""
            Log.i("Google Email", googleEmail)

            val googleProfilePicURL = account?.photoUrl.toString()
            Log.i("Google Profile Pic URL", googleProfilePicURL)

            val googleIdToken = account?.idToken ?: ""
            Log.i("Google ID Token", googleIdToken)



            // createUser(googleEmail,googleIdToken,googleFirstName+" "+googleLastName,"")


        } catch (e: ApiException) {
            // Sign in was unsuccessful
            Log.e(
                "failed code=", e.statusCode.toString()
            )
        }
    }


    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                // Update your UI here
            }
    }


    private fun revokeAccess() {
        mGoogleSignInClient.revokeAccess()
            .addOnCompleteListener(this) {
                // Update your UI here
            }
    }
}//--------------- FINE






*/


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

