package com.example.loandemoapp.adapter

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.loandemoapp.R
import kotlinx.android.synthetic.main.list_of_emimonths.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class emiMonthsAdapter(val items: ArrayList<String>,val months:ArrayList<String>, val context:Context, val numOfMonths :Int): RecyclerView.Adapter<emiMonthsAdapter.ViewHolder>() {
    var answer: String =""

    var currentDatePlusOne: Date? = null
    var currentDate:Date?= null
    var formatter:SimpleDateFormat?= null
    var c:Calendar?=null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val nameDate=months[position]
//        val newNameDate=formatter!!.format(nameDate)
//        val emiAmount=items[position]
        holder.tvMonthsName.text= nameDate
        holder.tvEmiAmount?.text = items.get(0)

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_of_emimonths, parent, false))
    }

    override fun getItemCount(): Int {
        return numOfMonths
//        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMonthsName = view.tvMonthsName
        val tvEmiAmount = view.tvEmiAmount

    }
}

