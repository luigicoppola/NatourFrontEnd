package com.ingsftw.natourfrontend.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import com.facebook.FacebookSdk.getApplicationContext
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ingsftw.natourfrontend.R
import com.ingsftw.natourfrontend.utils.CommunicationInterface
import com.ingsftw.natourfrontend.utils.Communicator


class BottomSheetFiltri: BottomSheetDialogFragment() {
    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)


        var rooterView: View =
            inflater.inflate(R.layout.layout_bottomsheet_filtri, container, false)

        var filtriGroup = rooterView.findViewById<RadioGroup>(R.id.filtriGroup);

        var checkedRadioButton = rooterView.findViewById<RadioGroup>(R.id.filtriGroup);

//Prendo gli id di ciascun Radio Button
        var idAreaGeografica = rooterView.findViewById<RadioButton>(R.id.areaRadio).getId()
        var idDifficolta = rooterView.findViewById<RadioButton>(R.id.difficoltaRadio).getId()
        var idDurata = rooterView.findViewById<RadioButton>(R.id.durataRadio).getId()
        var idDisabili = rooterView.findViewById<RadioButton>(R.id.disabiliRadio).getId()

        var hintSearch = rooterView.findViewById<SearchView>(R.id.searchItinerari)


        var applica = rooterView.findViewById<Button>(R.id.applicaFiltriButton)
        applica.setOnClickListener {
            var text = checkedRadioButton.getCheckedRadioButtonId()







            if (text == idAreaGeografica) {
                Toast.makeText(getApplicationContext(), "Area Geografica", Toast.LENGTH_SHORT)
                    .show();
                val input = "Area Geografica"
                val bundle = Bundle()
                bundle.putString("data",input)
                val fragment = homeFragment()
                fragment.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.container,fragment)?.commit()



            }


            if (text == idDifficolta) {
                Toast.makeText(getApplicationContext(), "Difficoltà", Toast.LENGTH_SHORT).show();
                var hintSearch = rooterView.findViewById<SearchView>(R.id.searchItinerari)
                hintSearch.setQueryHint("Ricerca per difficoltà")

            }





            if (text == idDurata) {
                Toast.makeText(getApplicationContext(), "Durata", Toast.LENGTH_SHORT).show();
                var hintSearch = rooterView.findViewById<SearchView>(R.id.searchItinerari)
                hintSearch.setQueryHint("Ricerca per durata")


            }



            if (text == idDisabili) {
                Toast.makeText(getApplicationContext(), "Disabili", Toast.LENGTH_SHORT).show();
                var hintSearch = rooterView.findViewById<SearchView>(R.id.searchItinerari)
                hintSearch.setQueryHint("Ricerca per disabili")

            }

        }





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