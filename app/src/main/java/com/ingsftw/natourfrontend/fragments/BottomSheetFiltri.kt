package com.ingsftw.natourfrontend.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ingsftw.natourfrontend.R


class BottomSheetFiltri: BottomSheetDialogFragment()  {
    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)


        var rooterView: View =
            inflater.inflate(R.layout.layout_bottomsheet_filtri, container, false)


      /*  var distanzaPopup = rooterView.findViewById<TextView>(R.id.AreaGeograficaTextModificabile)
        distanzaPopup.setOnClickListener() {
            var dialog = distanza()
            dialog.setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.ThemeOverlay);
            dialog.show(childFragmentManager, "distanza")


        }

*/
        return rooterView


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