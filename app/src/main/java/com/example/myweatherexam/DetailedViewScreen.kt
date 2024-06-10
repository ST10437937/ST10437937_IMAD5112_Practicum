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
