package com.example.randomnumber

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.randomnumber.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

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
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(this))[MainViewModel::class.java]
    }
    private val sparkView by lazy { binding.sparkView }
    private val tvNumList by lazy { binding.tvNumList }
    private lateinit var sparkAdapter: SparkAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        mainViewModel.getRandomEveryTen()

    }

    /**
     * Sets up observers for MutableLiveData from MainViewModel
     * For numFloatArray, connects it to sparkView via sparkAdapter and displays it in MainActivity
     * For errorLiveData2, creates a Snackbar and displays it (currently Int) in said Snackbar
     */

    private fun setupObservers() {

        mainViewModel.numFloatArray.observe(this) {
            if (!::sparkAdapter.isInitialized) {
                sparkAdapter = SparkAdapter(it)
                sparkView.adapter = sparkAdapter
            } else {
                sparkAdapter.updateData(it)
                tvNumList.text = it.joinToString(", ")
            }
        }

        mainViewModel.errorLiveData2.observe(this) {
            val snackbar: Snackbar = Snackbar.make(binding.root, it.toString(), Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }
}