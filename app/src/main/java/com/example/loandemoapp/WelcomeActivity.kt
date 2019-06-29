package com.example.loandemoapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.SparseArray
import android.view.View
import android.view.Window
import android.widget.Toast
import com.example.loandemoapp.util.DiscreteSeekBar
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlin.math.round

class WelcomeActivity: AppCompatActivity() {
     var name=""
     var salary=""
    var salaryNum:Int=0
    var loan:Int=0
    var numOfMonths=0
    var loanFinal=0
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE) //will hide the title
        changeTitle("EasyLoan")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val nameIntent=intent.extras
        name=nameIntent.getString("Name")
        salary=nameIntent.getString("Salary")
        tvName.text=name

        salaryNum=salary.toInt()

        if(salaryNum> 5000)
        {
            textView5.text="Welcome"
            textView10.text="Your loan is approved.Loan amount is:"
            loan=salaryNum*3
            loanFinal=round(loan)
            textView11.text= "Rs."+loanFinal.toString()+"/-"
            imageView4.setImageResource(R.drawable.ok)
        }
        else
        {
            textView5.text="Sorry "
            textView10.text= "your loan is not approved"
            textView11.text="----"
            imageView4.setImageResource(R.drawable.cancel)


        }


        val tickMarkTextArr3 = SparseArray<String>()
        tickMarkTextArr3.append(0, "0")
        tickMarkTextArr3.append(6, "6")
        tickMarkTextArr3.append(12, "12")
        tickMarkTextArr3.append(18, "18")
        tickMarkTextArr3.append(24, "24")
        tickMarkTextArr3.append(30, "30")
        tickMarkTextArr3.append(36, "36")

        slider_3.getConfigBuilder()
            .setTrackTouchEnable(false)
            .setMinValue(0)
            .setMaxValue(36)
            .setValue(0)
            .setSectionCount(6)
            .setTickMarkTextArray(tickMarkTextArr3)
            .setOnValueChangedListener(object : DiscreteSeekBar.OnValueChangedListener {
                override fun onValueChanged(value: Int) {
                    Toast.makeText(applicationContext, "value= $value", Toast.LENGTH_SHORT).show()
                    numOfMonths=value
                }
            })
            .build()

        btnProceed.setOnClickListener()
        {
            val intent=Intent(this, EmiTableActivity::class.java)
            intent.putExtra("LoanAmount",loanFinal)
            intent.putExtra("NumOfMonths",numOfMonths)
            startActivity(intent)
        }



    }
    fun  changeTitle(title:String)
    {
        var actionBar =supportActionBar
        actionBar!!.title = title
    }
    fun round(num:Int): Int {
        return (Math.ceil((num/100).toDouble())*100).toInt();

    }




}