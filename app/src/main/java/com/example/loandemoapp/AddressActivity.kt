package com.example.loandemoapp

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log

class AddressActivity: AppCompatActivity() {

    var name=""
    var salary=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)
        changeTitle("Final Eligibility Check")
        val nameFromIntent=intent.extras


        nameFromIntent?.let {
            name=nameFromIntent.getString("Name")
            salary=nameFromIntent.getString("Salary")
        }
        Log.d("saaaaa",salary)
        val budleName=Bundle()
        budleName.putString("Name",name)
        budleName.putString("Salary",salary)
        val fragmentManager=supportFragmentManager
        val firstFragment = PanFragment()
        firstFragment.arguments=budleName
        val transaction = fragmentManager!!.beginTransaction()
        transaction.addToBackStack(null)
        transaction!!.add(R.id.frameLayout2,firstFragment)
        transaction.commit()

    }

    fun  changeTitle(title:String)
    {
        var actionBar =supportActionBar
        actionBar!!.title = title
    }

   /* companion object titleBar {
        fun  changeTitle(title:String):titleBar
        {
            var actionBar =supportActionBar
            actionBar!!.title = title
            return  actionBar
        }

    }*/
}

