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