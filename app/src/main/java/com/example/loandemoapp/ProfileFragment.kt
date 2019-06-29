package com.example.loandemoapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)

        val name= arguments!!.getString("Name")
        val middleName=arguments!!.getString("MiddleName")
        val lastName=arguments!!.getString("LastName")
        val dob=arguments!!.getString("Dob")
        val gender=arguments!!.getString("Gender")
        val location=arguments!!.getString("Location")
        Log.d("Profilelllll","prorororo  "+name+middleName+lastName+dob+gender+location)


        view.tvName!!.text=name
        view.textView6!!.text = dob
        view.textView7!!.text=gender
        view.textView8!!.text=location

        view.button5.setOnClickListener()
        {

            val bundleProfile=Bundle()
            bundleProfile.putString("Name",name)
            bundleProfile.putString("MiddleName",middleName)
            bundleProfile.putString("LastName",lastName)
            bundleProfile.putString("Dob",dob)
            bundleProfile.putString("Gender",gender)
            bundleProfile.putString("Location",location)
           val fragment=SalaryFragment()
            fragment.arguments=bundleProfile
            val fragmentManager=requireFragmentManager()
            val fragmentTransaction=fragmentManager!!.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.frameLayout,fragment)
            fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            fragmentTransaction!!.commit()
        }

        return  view
    }

}