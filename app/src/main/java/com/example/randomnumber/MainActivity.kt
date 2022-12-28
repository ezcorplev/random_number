package com.example.randomnumber

//import android.R
import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.robinhood.spark.SparkView
import com.robinhood.spark.SparkView.OnScrubListener


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

//    var yoyo2 : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app = Application()
        val viewModel = MainViewModel(app)
        val tvNumList = findViewById<TextView>(R.id.tv_num_list)
        val sparkView = findViewById<View>(R.id.spark_view) as SparkView
        val adapter = viewModel.numFloatArray.value?.let { SparkAdapter(yData = it) }
        val scrubInfoTextView = findViewById<TextView>(R.id.scrub_info_textview)

        sparkView.adapter = adapter
//        sparkView.isScrubEnabled
//        sparkView.scrubListener = OnScrubListener { value ->
//            if (value == null) {
//                scrubInfoTextView.text = "Tap and hold the graph to scrub"
//            } else {
//                scrubInfoTextView.text = "Scrubbing value: " + value.toString().toFloat()
//            }
//        }






//        viewModel.numList.observe(this) {
//                tvNumList.text = it.joinToString(", ")
//        }

        viewModel.numFloatArray.observe(this) {
            tvNumList.text = it.joinToString(", ")
            sparkView.adapter = com.example.randomnumber.SparkAdapter(it)
        }

        viewModel.getRandomEveryTen()






//        val myHandler = Handler()
//        myHandler.postDelayed(object : Runnable {
//            override fun run() {
//                //call function
//                myHandler.postDelayed(this, 10000)
//            }
//        }, 10000)
//
//        val yoyo = Handler().post(object: Runnable {
//            override fun run() {
//            }
//
//        })
//        yoyo.start()
//
//        val btn = findViewById<Button>(R.id.btn_get_random_number)
//
//        btn.setOnClickListener {
//            yoyo2 = 5
//        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putInt("number", yoyo2!!)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        val myInt = savedInstanceState.getInt("number")
//    }
}