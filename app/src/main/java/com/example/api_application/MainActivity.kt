package com.example.api_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*spinner for sign*/
        val spinner_signs: Spinner = findViewById(R.id.spinner_signs)
        ArrayAdapter.createFromResource(
            this,
            R.array.signs,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_signs.adapter = adapter
        }

        /*spinner for day*/
        val spinner_days: Spinner = findViewById(R.id.spinner_days)
        ArrayAdapter.createFromResource(
            this,
            R.array.days,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_days.adapter = adapter
        }

        /*start button who makes horoskopet*/
        val start : Button = findViewById(R.id.startBtn)

        /*onClick when start Button is clicken*/
        start.setOnClickListener {
            val selectedSign = spinner_signs.selectedItem.toString()
            val selectedDay = spinner_days.selectedItem.toString()
            val url = createURL(selectedSign, selectedDay)

            /*creating url based on item selected on the spinners and sending it to the next activity*/
            val intent = Intent(this@MainActivity,HoroskopActivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        }
    }

    /*create url function*/
    fun createURL(sign : String, date: String) : String  {
        return "https://aztro.sameerkumar.website/?sign=$sign&day=$date"
    }
}