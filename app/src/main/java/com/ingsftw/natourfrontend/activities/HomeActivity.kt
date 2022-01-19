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
import com.ingsftw.natourfrontend.fragments.PopupLogin
import com.ingsftw.natourfrontend.fragments.aggiungiFragment
import com.ingsftw.natourfrontend.fragments.homeFragment
import com.ingsftw.natourfrontend.fragments.profiloFragment

class HomeActivity : AppCompatActivity(){

    private val homeFragment = homeFragment()
    private val aggiungiFragment = aggiungiFragment()
    private val profiloFragment = profiloFragment()







        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_home)
















            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> replaceFragment(homeFragment)
                    R.id.menu_search -> replaceFragment(aggiungiFragment)
                    R.id.menu_profile -> replaceFragment(profiloFragment)
                }

                true
            }







        }
    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_navigation, fragment)
            transaction.commit()
        }

    }


}