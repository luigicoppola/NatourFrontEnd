package com.ingsftw.natourfrontend.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ingsftw.natourfrontend.R
import com.ingsftw.natourfrontend.dto.UserDto
import com.ingsftw.natourfrontend.fragments.*
import java.lang.RuntimeException

class HomeActivity : AppCompatActivity(){

    private val homeFragment = homeFragment()
    private val aggiungiFragment = BottomSheetAggiungi()
    private val profiloFragment = profiloFragment()
    private lateinit var userDto : UserDto






        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_home)

            this.userDto = intent.getParcelableExtra("data") ?: throw RuntimeException("Utente non trovato")




            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)

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