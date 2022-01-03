package com.ingsftw.natourfrontend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import java.util.regex.Pattern

import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemUno.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemUno : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


   }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       var v = inflater.inflate(R.layout.fragment_item_uno, container, false)
        var continua = requireActivity().findViewById<View>(R.id.continua_button) as Button

        var email = v.findViewById<EditText>(R.id.emailText)
        var passw = v.findViewById<EditText>(R.id.passwordText)


        continua.setText("Continua")













       /* val lp = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(email.marginLeft,0,email.marginRight,email.marginBottom)


        val lp2 = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        lp2.setMargins(100,500,10,continua.marginBottom)


        passw.isFocusableInTouchMode =false


*/





        /*  continua.setOnClickListener {
              if(isEmailValid(email.text.toString())) {
                  passw.isFocusableInTouchMode =true

              }
              else
                  email.setText("errore")

          }
  */




        // Inflate the layout for this fragment
        return v
    }



    fun isEmailValid(email: String?): Boolean {
        val regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)*$";
        val p: Pattern = Pattern.compile(regex);
        return p.matcher(email.toString()).matches();
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ItemUno.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemUno().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }



}