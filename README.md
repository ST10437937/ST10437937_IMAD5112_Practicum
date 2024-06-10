Senethemba Moshia 
ST10437937

the purpose of this app is to provide the average temperature for the week. the minimum and maximum tempertaures are recodred. 
the splash screen gives the option to nevigate to the next page or exit the app

![weather 1st page](https://github.com/ST10437937/ST10437937_IMAD5112_Practicum/assets/161305280/06af8f91-0d9a-4d6d-969b-bee36fe2a118)
![weather 1st page code](https://github.com/ST10437937/ST10437937_IMAD5112_Practicum/assets/161305280/c08d7ea9-a045-46e1-855a-2197c5e76cdd)


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
![weather splash](https://github.com/ST10437937/ST10437937_IMAD5112_Practicum/assets/161305280/950bd3af-cb97-46e7-b866-7d5b5530bb0a)
![fixed](https://github.com/ST10437937/ST10437937_IMAD5112_Practicum/assets/161305280/18cc3b92-a5bd-4ebb-861c-3de4cc179e1d)
![splash code 4](https://github.com/ST10437937/ST10437937_IMAD5112_Practicum/assets/161305280/b00241d8-f84e-4887-97e2-3ea32eb7f4f5)
![splash code3](https://github.com/ST10437937/ST10437937_IMAD5112_Practicum/assets/161305280/82f05491-6ca7-418a-8b93-af9479ea7256)


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
![details1](https://github.com/ST10437937/ST10437937_IMAD5112_Practicum/assets/161305280/fe31305a-8fd5-487c-b316-330f2004c6fd)
![details2](https://github.com/ST10437937/ST10437937_IMAD5112_Practicum/assets/161305280/8e8a49dd-a50a-4d1b-9bf4-97bbd1c12350)
![details3](https://github.com/ST10437937/ST10437937_IMAD5112_Practicum/assets/161305280/ecbe6fd1-2706-481e-b313-3944f91f524f)


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
![weather 1st page](https://github.com/ST10437937/ST10437937_IMAD5112_Practicum/assets/161305280/06af8f91-0d9a-4d6d-969b-bee36fe2a118)

reference list 
IIE modual amnual 2024
stackoverflow accessed on 10june 2024 available at  https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-aandroid/2115770#2115770
geekd for geeks accessed on 10 june 2024 avaiable at https://geeksforgeeks.com
