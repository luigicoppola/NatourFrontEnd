package com.ingsftw.natourfrontend.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetAggiungi: BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setStyle(DialogFragment.STYLE_NORMAL, com.ingsftw.natourfrontend.R.style.AppBottomSheetDialogTheme)


        var rooterView: View = inflater.inflate(com.ingsftw.natourfrontend.R.layout.layout_bottomsheet_aggiungi, container, false)

        var manuale = rooterView.findViewById<TextView>(com.ingsftw.natourfrontend.R.id.manualeTextAggiungi)






        manuale.setOnClickListener{

            aggiungiManualeFragment().show(
                childFragmentManager, "fragment")
        }





        return rooterView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): BottomSheetAggiungi {
            val fragment = BottomSheetAggiungi()
            fragment.arguments = bundle
            return fragment
        }
    }

}