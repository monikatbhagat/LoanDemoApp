package com.example.loandemoapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_address.*
import kotlinx.android.synthetic.main.fragment_address.view.*

class AddressFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_address, container, false)


        val name=arguments!!.getString("Name")
        val salary=arguments!!.getString("Salary")
        view.button.setOnClickListener()
        {
            val nameBundle=Bundle()
            nameBundle.putString("Name",name)
            nameBundle.putString("Salary",salary)
            val fragment=ResidenceAddress()
            fragment.arguments=nameBundle
            val fragmentManager = requireFragmentManager()
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction!!.replace(R.id.frameLayout2, fragment)
            fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            fragmentTransaction!!.commit()
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numberOfyears = arrayOf("0-5", "6-10","11-15")
        val jobDuration = arrayOf("0-1", "2-3","4-5")
        val totalWorkExp = arrayOf("<1", "1-2","3-4")

        var yearsSelected:String?=null
        var jobDurationSelected:String?=null
        var totalWorkExpSeleced:String?=null


        if(edYearResidence!=null)
        {
            val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_dropdown_item_1line, numberOfyears)
            edYearResidence.adapter = arrayAdapter
            edYearResidence.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                    selectedCompanyType=companyType[position]
                    yearsSelected=numberOfyears.get(position)
//                    yearsSelected=numberOfyears[position] //also can use
                }
            }
        }
        if(edYearCity!=null)
        {
            val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, numberOfyears)
            edYearCity.adapter = arrayAdapter
            edYearCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    yearsSelected=numberOfyears[position]
                }
            }
        }

        if(edYearJob!=null)
        {
            val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, jobDuration)
            edYearJob.adapter = arrayAdapter
            edYearJob.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    jobDurationSelected=jobDuration[position]
                }
            }
        }
        if(edExp!=null)
        {
            val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, totalWorkExp)
            edExp.adapter = arrayAdapter
            edExp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    totalWorkExpSeleced=totalWorkExp[position]
                }
            }
        }

    }
}