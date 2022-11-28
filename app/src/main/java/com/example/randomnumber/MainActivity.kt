package com.example.randomnumber

import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


// Generic home assignment
// 1. You are provided with a backend API
// 2. Fetch the data using retrofit
// 3. Display the data in a recyclerview


// Please create the following files:
//
// 1. MainActivity
// 2. MainViewModel
// 3. Service Interface
//
// The following API returns a random number between 1-100
// https://www.random.org/integers/?num=1&min=1&max=100&col=1&base=10&format=plain&rnd=new
//
// Please hit the above API every 1 second, and display the latest 10 numbers in a robinhood chart https://github.com/robinhood/spark

// 1. Add a button to activity main xml
// 2. Once button is clicked, assign number the value of 5
// 3. Change orientation
// 4. make sure number value persists
class MainActivity : AppCompatActivity() {

    var yoyo2 : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val yoyo = Handler().post(object: Runnable {
            override fun run() {
                TODO("Not yet implemented")
            }

        })
        yoyo.start()

        val btn = findViewById<Button>(R.id.btn_get_random_number)

        btn.setOnClickListener {
            yoyo2 = 5
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("number", yoyo2!!)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val myInt = savedInstanceState.getInt("number")
    }
}