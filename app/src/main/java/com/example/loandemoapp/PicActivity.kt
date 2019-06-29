package com.example.loandemoapp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Window

class PicActivity: AppCompatActivity() {
    var name=""
    var middleName:String?=""
    var lastName:String?=""
    var dob:String?=""
    var gender:String?=""
    var location:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar()!!.hide();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pic)

        val bundleMainActivity=intent.extras
        bundleMainActivity?.let{
           name= bundleMainActivity.getString("Name")
            middleName=bundleMainActivity.getString("MiddleName")
           lastName=bundleMainActivity.getString("LastName")
            dob=bundleMainActivity.getString("Dob")
           gender=bundleMainActivity.getString("Gender")
          location=bundleMainActivity.getString("Location")

            Log.d("eeee","detail"+name+middleName+lastName+dob+gender+location)
        }

        val manager = supportFragmentManager

        val bundlePicFragment=Bundle()
//        bundlePicFragment?.putString("Name",name)
        bundlePicFragment.putString("Name",name)
        bundlePicFragment.putString("MiddleName",middleName)
        bundlePicFragment.putString("LastName",lastName)
        bundlePicFragment.putString("Dob",dob)
        bundlePicFragment.putString("Gender",gender)
        bundlePicFragment.putString("Location",location)

        Log.d("bbbb","detail"+bundlePicFragment)

        val firstFragment = PicFragment()
//        firstFragment.arguments = intent.extras
        firstFragment.arguments=bundlePicFragment
        val transaction = manager!!.beginTransaction()
        transaction.addToBackStack(null)
        transaction!!.add(R.id.frameLayout,firstFragment)
        transaction.commit()

    }
//Reusable code to replace fragment to another fragment
    fun addFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        if (addToBackStack) {
            ft.addToBackStack(tag)
        }
        ft.replace(R.id.frameLayout, fragment, tag)
        ft.commitAllowingStateLoss()
    }
}