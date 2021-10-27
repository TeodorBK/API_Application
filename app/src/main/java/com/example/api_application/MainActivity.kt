package com.example.api_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*spinner for sign*/
        val spinnerSigns: Spinner = findViewById(R.id.spinnerSigns)
        ArrayAdapter.createFromResource(
            this,
            R.array.signs,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerSigns.adapter = adapter
        }

        /*spinner for day*/
        val spinnerDays: Spinner = findViewById(R.id.spinnerDays)
        ArrayAdapter.createFromResource(
            this,
            R.array.days,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerDays.adapter = adapter
        }

        /*start button who makes horoskopet*/
        val start : Button = findViewById(R.id.startBtn)

        /*onClick event for sending over url and starting new activity*/
        start.setOnClickListener {
            val selectedSign : String = spinnerSigns.selectedItem.toString()
            val selectedDay : String = spinnerDays.selectedItem.toString()
            val url : String = createURL(selectedSign, selectedDay)

            /*creating url based on item selected on the spinners and sending it to the next activity*/
            val intent = Intent(this@MainActivity,HoroskopActivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        }
    }

    /*create url function for making a url for diffrent starsign and days*/
    private fun createURL(sign : String, date: String) : String  {
        return "https://aztro.sameerkumar.website/?sign=$sign&day=$date"
    }
}