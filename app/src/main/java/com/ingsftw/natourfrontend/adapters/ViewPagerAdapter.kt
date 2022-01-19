package com.ingsftw.natourfrontend.adapters;

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (val items : ArrayList<Fragment> , activity: AppCompatActivity) : FragmentStateAdapter(activity){



    override fun getItemCount(): Int {
       return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }

//commento
}