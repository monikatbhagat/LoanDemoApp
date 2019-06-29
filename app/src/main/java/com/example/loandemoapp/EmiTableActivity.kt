package com.example.loandemoapp

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Window
import android.widget.TextView
import com.example.loandemoapp.adapter.emiMonthsAdapter
import kotlinx.android.synthetic.main.activity_emi_table.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class EmiTableActivity: AppCompatActivity() {
    var loanFinal=0
    var numOfMonths=0
    var loanAmountToReturn=0
    var interest=0
    val emaiMonths: ArrayList<String> = ArrayList()
    var emi=0
    var emaiMonthsDates:ArrayList<String> = arrayListOf()
    var emiDates:Date?=null
    var answer: String =""
    var currentDate:Date?= null
    var formatter:SimpleDateFormat?= null
    var c:Calendar?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE) //will hide the title
        changeTitle("EMI")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emi_table)
        val intentFromWelcome: Bundle?=intent.extras

        intentFromWelcome?.let {
            loanFinal= intentFromWelcome.getInt("LoanAmount")
            numOfMonths=intentFromWelcome.getInt("NumOfMonths")

            Log.d("eeee","aamoougg"+loanFinal+numOfMonths)
        }
        interest=(loanFinal*10)/100
        loanAmountToReturn=loanFinal + interest
        tvTotalAmount.text= "Total amount to return: "+loanAmountToReturn.toString()
        textView13.text="No. of EMI: "+numOfMonths
        emi=loanAmountToReturn/numOfMonths



        addMonths(emi)
        emaiMonthsDates=addMonthsDate(numOfMonths)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager?
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.adapter= emiMonthsAdapter(emaiMonths,emaiMonthsDates,this,numOfMonths)
    }

    fun  changeTitle(title:String)
    {
        var actionBar =supportActionBar
        actionBar!!.title = title
    }


    fun addMonths( emi:Int) {

        emaiMonths.add(emi.toString())
    }
    fun addMonthsDate( emi:Int): ArrayList<String> {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//             currentDate = LocalDateTime.now()
            currentDate = Date()
//            formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            formatter = SimpleDateFormat("dd.MM.yyyy")
//            answer=  currentDate.format(formatter)
            Log.d("answer",answer)

            c = Calendar.getInstance()
            c!!.time = currentDate
            for( i in 1..numOfMonths)
            {
                c!!.add(Calendar.YEAR, 0)
                c!!.add(Calendar.MONTH, 1)
                c!!.add(Calendar.DATE, 0)
                var currentDatePlusOne = c!!.time
                var fff=formatter!!.format(currentDatePlusOne)
                Log.d("hii_1111", fff)
                emaiMonthsDates.add(fff)

            }

        } else {

            currentDate = Date()
            formatter = SimpleDateFormat("dd.MM.yyyy")
            // convert date to calendar
            c = Calendar.getInstance()
            c!!.time = currentDate

            for( i in 1..numOfMonths)
            {
                c!!.add(Calendar.YEAR, 0)
                c!!.add(Calendar.MONTH, 1)
                c!!.add(Calendar.DATE, 0)
                var currentDatePlusOne = c!!.time
                var fff=formatter!!.format(currentDatePlusOne)
                Log.d("hii_1111", fff)
                emaiMonthsDates.add(fff)

            }
            Log.d("2222222", emaiMonthsDates.toString())
        }

        return emaiMonthsDates

    }


}
