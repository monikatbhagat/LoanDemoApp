package com.example.loandemoapp

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_residence_address.*
import kotlinx.android.synthetic.main.fragment_residence_address.view.*

class ResidenceAddress: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view:View=inflater.inflate(R.layout.fragment_residence_address,container,false)
        val name=arguments!!.getString("Name")
        val salary=arguments!!.getString("Salary")
        view.button.setOnClickListener()
        {
            val nameHouse= edHouse!!.text
            val nameStreet=edStreet!!.text
            val nameLocality=edLocality!!.text
            val nameNearBy=edNearBy!!.text
            val pinCode=edPinCode!!.text
            val state=edState!!.text

            if(nameHouse.isEmpty())
            {
                Toast.makeText(context,"Enter House No.", Toast.LENGTH_SHORT).show()
            }
            else if(nameStreet.isEmpty())
            {
                Toast.makeText(context,"Enter street", Toast.LENGTH_SHORT).show()
            } else if(nameLocality.isEmpty())
            {
                Toast.makeText(context,"Enter locality", Toast.LENGTH_SHORT).show()
            }else if(nameNearBy.isEmpty())
            {
                Toast.makeText(context,"Enter nearby location", Toast.LENGTH_SHORT).show()
            }else if(pinCode.isEmpty())
            {
                Toast.makeText(context,"Enter pin code", Toast.LENGTH_SHORT).show()
            }else if(state.isEmpty())
            {
                Toast.makeText(context,"Enter state", Toast.LENGTH_SHORT).show()
            }else{
                val intent= Intent(activity,WelcomeActivity::class.java)
                intent.putExtra("Name",name)
                intent.putExtra("Salary",salary)
                startActivity(intent)
            }
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val city = arrayOf("Mumbai", "Kota", "Pune", "Banglore")
        var location:String?=null

        if (edCity != null) {
            val arrayAdapterOfCity = ArrayAdapter(context, android.R.layout.simple_spinner_item, city)
            edCity.adapter = arrayAdapterOfCity
            edCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    location=city[position]
                }

            }
        }
    }
}