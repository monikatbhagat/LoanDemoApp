package com.example.loandemoapp

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.text.DateFormat




class MainActivity : Activity(){
    var cal=Calendar.getInstance()

 override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE) //will hide the title

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gender = arrayOf("Male", "Female")
        val city = arrayOf("Mumbai", "Kota", "Pune", "Banglore")
        var selectedGender:String?=null
        var location:String?=null

        if (spGender!= null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, gender)
            spGender.adapter = arrayAdapter
            spGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedGender=gender[position]
                }
            }
        }
        if (spCity != null) {
            val arrayAdapterOfCity = ArrayAdapter(this, android.R.layout.simple_spinner_item, city)
            spCity.adapter = arrayAdapterOfCity
            spCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    location=city[position]
                }

            }
        }
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        etDob!!.setOnClickListener(object : OnClickListener {
            override fun onClick(view: View) {
                hideKeyboard()
                DatePickerDialog(
                    this@MainActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

        })

        button.setOnClickListener()
        {

            val name= editText!!.text
            val middleName=et_middle!!.text
            val lastName=et_last!!.text
            val dob=etDob!!.text


            if(name.isEmpty())
            {
                Toast.makeText(applicationContext,"Enter First Name",Toast.LENGTH_SHORT).show()
            }
            else if(middleName.isEmpty())
            {
                Toast.makeText(applicationContext,"Enter Middle Name",Toast.LENGTH_SHORT).show()
            } else if(lastName.isEmpty())
            {
                Toast.makeText(applicationContext,"Enter Last Name",Toast.LENGTH_SHORT).show()
            } else if(dob.isEmpty())
            {
                Toast.makeText(applicationContext,"Enter Dob Name",Toast.LENGTH_SHORT).show()
            } else{

                val intent = Intent(this, PicActivity::class.java)
                intent.putExtra("Name",name.toString())
                intent.putExtra("MiddleName",middleName.toString())
                intent.putExtra("LastName",lastName.toString())
                intent.putExtra("Dob",dob.toString())
                intent.putExtra("Gender",selectedGender)
                intent.putExtra("Location",location)
                startActivity(intent)
            }
        }

     val currentDate = Date()
     val formatter = SimpleDateFormat("dd.MM.yyyy")
     Log.d("test",formatter.format(currentDate))
    }

    fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        etDob!!.text = Editable.Factory.getInstance().newEditable(sdf.format(cal.getTime()))
    }


    fun Activity.hideKeyboard() {
        hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }




}
