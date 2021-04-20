package com.apps.demotest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private var list: ArrayList<String> = ArrayList()
    private var mainHandler: Handler? = null
    private var adapter: ItemAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager1: RecyclerView.LayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        val rvData: RecyclerView = findViewById(R.id.rvData)
        rvData.layoutManager = layoutManager1
        adapter = ItemAdapter(list)
        rvData.adapter = adapter

        mainHandler = Handler(Looper.getMainLooper())
    }
    private val update: Runnable by lazy {
        object : Runnable {
            @SuppressLint("SimpleDateFormat")
            override fun run() {
                val dateFormat: DateFormat = SimpleDateFormat("ss")
                val cal: Calendar = Calendar.getInstance()
                Log.e("Second",dateFormat.format(cal.time))
                if(list.size >= dateFormat.format(cal.time).toInt()){
                    Toast.makeText(this@MainActivity, "Already added", Toast.LENGTH_SHORT).show()
                }else{
                    val dateFormat1: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    val cal1: Calendar = Calendar.getInstance()
                    list.add(dateFormat1.format(cal1.time))
                    Log.e("time",dateFormat.format(cal.time))
                }
                mainHandler!!.postDelayed(this, 1000)
                adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainHandler?.post(update)
    }

    override fun onPause() {
        super.onPause()
        mainHandler?.removeCallbacks(update)
    }
}