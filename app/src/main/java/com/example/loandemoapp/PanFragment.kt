package com.example.loandemoapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_pan.*
import kotlinx.android.synthetic.main.fragment_pan.view.*
import kotlinx.android.synthetic.main.fragment_salary.*

class PanFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_pan, container, false)
        val name=arguments!!.getString("Name")
        val salary=arguments!!.getString("Salary")
        view.button.setOnClickListener(){

            val panNumber=etPan!!.text
            val companyName=editText!!.text
            val designation=edDesignation!!.text
            val emailId=edMail!!.text


            if(panNumber.isEmpty())
            {
                Toast.makeText(context,"Enter PAN Number",Toast.LENGTH_SHORT).show()
            }
            else if(companyName.isEmpty())
            {
                Toast.makeText(context,"Enter Company Name",Toast.LENGTH_SHORT).show()
            }else if(designation.isEmpty())
            {
                Toast.makeText(context,"Enter designation",Toast.LENGTH_SHORT).show()
            }else if(emailId.isEmpty())
            {
                Toast.makeText(context,"Enter email id",Toast.LENGTH_SHORT).show()
            }else{
                val nameBundle=Bundle()
                nameBundle.putString("Name",name)
                nameBundle.putString("Salary",salary)
                val fragment=AddressFragment()
                fragment.arguments=nameBundle
                val fragmentManager = requireFragmentManager()
                val fragmentTransaction = fragmentManager!!.beginTransaction()
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction!!.replace(R.id.frameLayout2, fragment)
                fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                fragmentTransaction!!.commit()
            }
        }

        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val companyType = arrayOf("Service Based", "Product Based")
        val residenceType = arrayOf("type 1", "type 2","type 3")
        var selectedCompanyType:String?=null
        var selectedresidenceType:String?=null

        if(edSelect!=null)
        {
            val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_dropdown_item_1line, companyType)
           edSelect.adapter = arrayAdapter
           edSelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                    selectedCompanyType=companyType[position]
                    selectedCompanyType=companyType.get(position)
                }
            }
        }
        if(edResidence!=null)
        {
            val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, residenceType)
           edResidence.adapter = arrayAdapter
            edResidence.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedresidenceType=residenceType[position]
                }
            }
        }


    }

}