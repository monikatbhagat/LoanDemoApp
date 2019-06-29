package com.example.loandemoapp

import android.content.Intent
import android.location.Address
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_salary.*
import kotlinx.android.synthetic.main.fragment_salary.view.*

class SalaryFragment: Fragment() {
    var salary:String?=null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view:View=inflater.inflate(R.layout.fragment_salary,container,false)
        val name= arguments!!.getString("Name")
        view.btnSubmit.setOnClickListener()
        {
           salary= editText3!!.text.toString()
            if(salary!!.isEmpty())
            {
                Toast.makeText(context,"Enter Salary", Toast.LENGTH_SHORT).show()

            }else{

                val intent=Intent(activity,AddressActivity::class.java)
                intent.putExtra("Name",name)
                Log.d("saaaaa",salary)
                intent.putExtra("Salary",salary)
                startActivity(intent)
            }
        }

        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radioGroup3.setOnCheckedChangeListener({group, checkedId -> val radioButton=view.findViewById<RadioButton>(checkedId)
            Toast.makeText(context,radioButton.text.toString()+"is checked",Toast.LENGTH_SHORT).show()})
    }


}