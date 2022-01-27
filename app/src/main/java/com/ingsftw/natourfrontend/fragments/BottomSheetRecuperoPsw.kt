package com.ingsftw.natourfrontend.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetRecuperoPsw: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setStyle(DialogFragment.STYLE_NORMAL, com.ingsftw.natourfrontend.R.style.AppBottomSheetDialogTheme)


        var rooterView: View = inflater.inflate(com.ingsftw.natourfrontend.R.layout.fragment_popup_recupero_psw, container, false)





        return rooterView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): BottomSheetRecuperoPsw {
            val fragment = BottomSheetRecuperoPsw()
            fragment.arguments = bundle
            return fragment
        }
    }

}

