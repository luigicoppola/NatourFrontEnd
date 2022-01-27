package com.ingsftw.natourfrontend.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ingsftw.natourfrontend.R

class BottomSheetFiltri: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)

        return inflater.inflate(R.layout.layout_bottomsheet_filtri, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): BottomSheetFiltri {
            val fragment = BottomSheetFiltri()
            fragment.arguments = bundle
            return fragment
        }
    }

}