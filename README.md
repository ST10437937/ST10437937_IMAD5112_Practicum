Senethemba Moshia 
ST10437937

the purpose of this app is to provide the average temperature for the week. the minimum and maximum tempertaures are recodred. 
the splash screen gives the option to nevigate to the next page or exit the app
package com.example.myweatherexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // background picture weather doodle vectorpattern background [online]available at https://www.vecteezy.com/vector-art/628675-weather-doodle-vector-pattern-background-vector-illustration accessed 10 June 2024

        val btnStart : Button = findViewById(R.id.btnStart)
        val btnExit : Button = findViewById(R.id.btnExit)

        //start button to continue to second screen
        btnStart.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java) //The IIE(2024)
            startActivity(intent)
        }

        // exit button to close the app
        btnExit.setOnClickListener {
            finishAffinity()
        }

    }
}

the main screen is about calculating the average temperature for the week, nevigate to detailed screen or exit
package com.example.myweatherexam

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.PrimitiveIterator
import kotlin.Any
import kotlin.Any as Any1

private fun Intent.putExtra(s: String, map: List<Double>) {


}

class MainScreen : AppCompatActivity() {

    // initialising
    private lateinit var  temperature: Array<Int>
    private lateinit var weatherConditions: Array<String>
    private lateinit var txtAverageTemp: TextView
    private lateinit var edtMonday: EditText
    private lateinit var edtTuesday: EditText
    private lateinit var edtWednesday: EditText
    private lateinit var edtThursday:EditText
    private lateinit var edtFriday : EditText
    private lateinit var edtSaturday:EditText
    private lateinit var edtSunday: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        edtMonday = findViewById(R.id.edtMonday)
        edtTuesday = findViewById(R.id.edtTuesday)
        edtWednesday = findViewById(R.id.edtWedneday)
        edtThursday = findViewById(R.id.edtThursday)
        edtFriday = findViewById(R.id.edtFriday)
        edtSaturday = findViewById(R.id.edtSaturday)
        edtSunday = findViewById(R.id.edtSunday)
        //
          // initialsing arrays
        temperature = arrayOf(33, 28,35,20,7,4,38,) // The IIe,2024
        weatherConditions = arrayOf("Sunny", "Sunny", "Sunny","Partly Cloudy", "Windy", "Heavy Rain","Humid")

        val txtAverageTemp: TextView =findViewById(R.id.txtAverageTemp)
        val btnErase: Button = findViewById(R.id.btnErase)
        val btnNext: Button = findViewById(R.id.btnNext)
        val btnCalAverage: Button = findViewById(R.id.btnCalcAverage)
        val btnGoodbye: Button = findViewById(R.id.btnGoodbye)

        calculateAveragetemperature()

        btnCalAverage.setOnClickListener {
            val Monday = edtMonday.text.toString().toDoubleOrNull() ?: 33.0
            val Tuesday = edtTuesday.text.toString().toDoubleOrNull() ?: 28.0
            val Wednesday = edtWednesday.text.toString().toDoubleOrNull() ?: 35.0
            val Thursday = edtThursday.text.toString().toDoubleOrNull() ?: 20.0
            val Friday = edtFriday.text.toString().toDoubleOrNull() ?: 7.0
            val Saturday = edtSaturday.text.toString().toDoubleOrNull() ?: 4.0
            val Sunday= edtSunday.text.toString().toDoubleOrNull() ?: 38.0

            val averageTemp = (Monday + Tuesday + Wednesday + Thursday + Friday + Saturday + Sunday) / 7
            txtAverageTemp.text = "Average Temperature: ${String.format("%2f",averageTemp)}°C"
        }


        btnNext.setOnClickListener {
            val intent = Intent(this, DetailedViewScreen::class.java)
            intent.putExtra("max temps", temperature.map { it.toDouble() }.toDoubleArray()) //(GeeksforGeeks,2024
            intent.putExtra("min temps", temperature.map { (it - 10).toDouble() }.toDoubleArray())
            intent.putExtra("weather conditions", weatherConditions)
            startActivity(intent)
        }

        // Button to erase mistakes
        btnErase.setOnClickListener {
            eraseData()
        }

        // Goodbye button to close app
        btnGoodbye.setOnClickListener {
            finishAffinity()
        }
    }
    private fun calculateAveragetemperature(){
        if (temperature.isEmpty()){
            txtAverageTemp.text = "Average Temperature is N/A"
            return
        }
        val sum = temperature.sum()
        val average = sum / temperature.size
        txtAverageTemp.text = "Average Temperature :$average°C "
    }


    private fun eraseData(){
        AlertDialog.Builder(this).apply {  // Stackoverflow, 2024 https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-aandroid/2115770#2115770
            setTitle("Erase Data")
            setMessage("Please re-input new data")
            setPositiveButton("Yes"){_,_ ->
                temperature = emptyArray()
                weatherConditions= emptyArray()
                txtAverageTemp.text= "Average Temperature: N/A"
            }
            setNegativeButton("No, null")
        }.show()
    }

    private fun setNegativeButton(s: String) {

    }

}

private operator fun Any.div(i: Int) {

}


private operator fun Unit.div(i: Int) {

}

private operator fun Any1.plus(wednesday: Number) {

}

private operator fun Number.plus(tuesday: Number) {

}


private fun setMessage(s: String, function: () -> Unit) {

    }

detailed screen is each days temerpatures 

package com.example.myweatherexam

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class DetailedViewScreen : AppCompatActivity() {


    class MainScreen : AppCompatActivity() {

        // initialising
        private lateinit var  temperature: Array<Int>
        private lateinit var weatherConditions: Array<String>
        private lateinit var txtAverageTemp: TextView
        private lateinit var edtMonday: EditText
        private lateinit var edtTuesday: EditText
        private lateinit var edtWednesday: EditText
        private lateinit var edtThursday: EditText
        private lateinit var edtFriday : EditText
        private lateinit var edtSaturday: EditText
        private lateinit var edtSunday: EditText

        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main_screen)

            edtMonday = findViewById(R.id.edtMonday)
            edtTuesday = findViewById(R.id.edtTuesday)
            edtWednesday = findViewById(R.id.edtWedneday)
            edtThursday = findViewById(R.id.edtThursday)
            edtFriday = findViewById(R.id.edtFriday)
            edtSaturday = findViewById(R.id.edtSaturday)
            edtSunday = findViewById(R.id.edtSunday)

            temperature = arrayOf(33, 28,35,20,7,4,38,)
            weatherConditions = arrayOf("Sunny", "Sunny", "Sunny","Partly Cloudy", "Windy", "Heavy Rain","Humid")

            val txtAverageTemp: TextView =findViewById(R.id.txtAverageTemp)
            val btnErase: Button = findViewById(R.id.btnErase)
            val btnNext: Button = findViewById(R.id.btnNext)
            val btnCalAverage: Button = findViewById(R.id.btnCalcAverage)
            val btnGoodbye: Button = findViewById(R.id.btnGoodbye)

            calculateAveragetemperature()

            btnCalAverage.setOnClickListener {
                val Monday = edtMonday.text.toString().toDoubleOrNull() ?: 33.0
                val Tuesday = edtTuesday.text.toString().toDoubleOrNull() ?: 28.0
                val Wednesday = edtWednesday.text.toString().toDoubleOrNull() ?: 35.0
                val Thursday = edtThursday.text.toString().toDoubleOrNull() ?: 20.0
                val Friday = edtFriday.text.toString().toDoubleOrNull() ?: 7.0
                val Saturday = edtSaturday.text.toString().toDoubleOrNull() ?: 4.0
                val Sunday= edtSunday.text.toString().toDoubleOrNull() ?: 38.0

                val averageTemp = (Monday + Tuesday + Wednesday + Thursday + Friday + Saturday + Sunday) / 7
                txtAverageTemp.text = "Average Temperature: ${String.format("%2f",averageTemp)}°C"
            }


            btnNext.setOnClickListener {
                val intent = Intent(this, DetailedViewScreen::class.java)
                intent.putExtra("temperatures", temperature) //( Stackoverflow,2024)
                intent.putExtra("weatherConditions", weatherConditions)
                startActivity(intent)
            }

            // Button to erase mistakes
            btnErase.setOnClickListener {
                eraseData()
            }

            // Goodbye button to close app
            btnGoodbye.setOnClickListener {
                finishAffinity()
            }
        }
        private fun calculateAveragetemperature(){
            if (temperature.isEmpty()){
                txtAverageTemp.text = "Average Temperature is N/A"
                return
            }
            val sum = temperature.sum()
            val average = sum / temperature.size
            txtAverageTemp.text = "Average Temperature :$average°C "
        }


        private fun eraseData(){
            AlertDialog.Builder(this).apply {  // Stackoverflow, 2024 https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-aandroid/2115770#2115770
                setTitle("Erase Data")
                setMessage("Please re-input new data")
                setPositiveButton("Yes"){_,_ ->
                    temperature = emptyArray()
                    weatherConditions= emptyArray()
                    txtAverageTemp.text= "Average Temperature: N/A"
                }
                setNegativeButton("No, null")
            }.show()
        }

        private fun setNegativeButton(s: String) {

        }

    }

    private operator fun Any.div(i: Int) {

    }


    private operator fun Unit.div(i: Int) {

    }

    private operator fun Any.plus(wednesday: Number) {

    }

    private operator fun Number.plus(tuesday: Number) {

    }


    private fun setMessage(s: String, function: () -> Unit) {

    }

reference list 
IIE modual amnual 2024
stackoverflow accessed on 10june 2024 available at  https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-aandroid/2115770#2115770
geekd for geeks accessed on 10 june 2024 avaiable at https://geeksforgeeks.com
