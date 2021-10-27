package com.example.api_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class HoroskopActivity : AppCompatActivity() {

    //makes a variable for request-que
    private var requestQueue : RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoskop)

        /*geting the url from the main activity*/
        val getUrl : String? = intent.getStringExtra("url")
        val url : String = getUrl.toString()

        /*all the textViwes where i print the horoscope content*/
        val compability : TextView = findViewById(R.id.compability)
        val number : TextView = findViewById(R.id.number)
        val time : TextView = findViewById(R.id.time)
        val color : TextView = findViewById(R.id.color)
        val currentDate : TextView = findViewById(R.id.currentDate)
        val dateRange : TextView = findViewById(R.id.dateRange)
        val mood : TextView = findViewById(R.id.mood)
        val description : TextView = findViewById(R.id.description)

        /*list of all the textViw for the horoscop*/
        val textViewList : List<TextView> = listOf(
            compability,
            number,
            time,
            color,
            currentDate,
            dateRange,
            mood,
            description
        )

        /*calling the horoscope function*/
        APICall(textViewList, url)
    }

    /*API Call function*/
    private fun APICall(views: List<TextView>, url: String) {
        requestQueue = Volley.newRequestQueue(this)

        //makes an request to the horoskop api
        val request = StringRequest(
            Request.Method.POST, url,
            { response ->
                //edits the answer
                val listOfContent = formatResponse(response)
                for(i in views) {
                    val index = views.indexOf(i)
                    i.text = listOfContent[index]
                }
            },
            { error ->
                //handles if it is an error
                views[0].text = getString(R.string.kunne_ikke_laste_horoskop)
            }
        )
        //gives request an tag
        request.tag = "horoskop"

        //sets requesten in que
        requestQueue?.add(request)

    }

    override fun onStop() {
        super.onStop()
        requestQueue?.cancelAll("horoskop")
    }

    /*formating the respons from apiCall*/
    private fun formatResponse(resp : String) : List<String> {
        //trys the
        try {
            val list : List<String> = resp.split('"')

            /*returning an list of where it splits to get the content*/
            return listOf(
                list[3],
                list[7],
                list[11],
                list[15],
                list[19],
                list[23],
                list[27],
                list[31]
            )
        } catch (exception: ArrayIndexOutOfBoundsException) {
            return listOf("Kunne ikke laste horoskop!")
        }
    }
}


