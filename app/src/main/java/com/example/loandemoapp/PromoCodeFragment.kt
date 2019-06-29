package com.example.loandemoapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_promocode.*
import kotlinx.android.synthetic.main.fragment_promocode.view.*

class PromoCodeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view:View=inflater.inflate(R.layout.fragment_promocode,container,false)

        val name= arguments!!.getString("Name")
        val middleName=arguments!!.getString("MiddleName")
        val lastName=arguments!!.getString("LastName")
        val dob=arguments!!.getString("Dob")
        val gender=arguments!!.getString("Gender")
        val location=arguments!!.getString("Location")

        Log.d("inPromo","prorororo  "+name+middleName+lastName+dob+gender+location)


        view.button3.setOnClickListener {


            if(editText2!!.text.isEmpty())
            {
                Toast.makeText(context,"Enter Code", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val bundleProfile=Bundle()
                bundleProfile.putString("Name",name)
                bundleProfile.putString("MiddleName",middleName)
                bundleProfile.putString("LastName",lastName)
                bundleProfile.putString("Dob",dob)
                bundleProfile.putString("Gender",gender)
                bundleProfile.putString("Location",location)

                val fragment = ProfileFragment()
                val fragmentManager = requireFragmentManager()
                fragment.arguments=bundleProfile
                val fragmentTransaction = fragmentManager!!.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction!!.replace(R.id.frameLayout, fragment)
                fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                fragmentTransaction!!.commit()
            }


        }

        return view
    }
}