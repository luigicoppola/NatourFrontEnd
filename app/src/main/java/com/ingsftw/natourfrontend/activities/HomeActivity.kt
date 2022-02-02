package com.ingsftw.natourfrontend.activities

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ingsftw.natourfrontend.R
import com.ingsftw.natourfrontend.dto.UserDto
import com.ingsftw.natourfrontend.fragments.*


class HomeActivity : AppCompatActivity(){

    private val homeFragment = homeFragment()
    private val aggiungiFragment = BottomSheetAggiungi()
    private val profiloFragment = profiloFragment()
    private lateinit var userDto : UserDto

    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton




        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(R.layout.activity_home)

            this.userDto = intent.getParcelableExtra("data") ?: throw RuntimeException("Utente non trovato")
















            replaceFragment(homeFragment)

            val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNav) as BottomNavigationView
            bottomNavigationView.selectedItemId = R.id.menu_home

            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> replaceFragment(homeFragment)
                    R.id.menu_search -> replacePopup(aggiungiFragment)
                   R.id.menu_profile ->{
                        val bundle = Bundle()
                        bundle.putParcelable("user",this.userDto)
                        profiloFragment.arguments=bundle
                        replaceFragment(profiloFragment)}
                }

                true
            }
        }


    fun clicked(text: String?) {
        val b: homeFragment = fragmentManager.findFragmentById(R.id.fixedHome) as homeFragment
        b.changeText(text);
    }


    private fun replacePopup(aggiungiFragment: BottomSheetAggiungi) {
        val sortRecipesBottomSheet = BottomSheetAggiungi()
        sortRecipesBottomSheet.show(supportFragmentManager,sortRecipesBottomSheet.tag)
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_navigation, fragment)
            transaction.commit()
        }

    }








}