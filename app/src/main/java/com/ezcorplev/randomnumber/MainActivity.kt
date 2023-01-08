package com.ezcorplev.randomnumber

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ezcorplev.randomnumber.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(this))[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        mainViewModel.getRandomEveryPeriod()
    }

    /**
     * Sets up observers
     */
    private fun setupObservers() {
        mainViewModel.numFloatArray.observe(this) {
            val sparkAdapter = SparkAdapter(it)
            binding.sparkView.adapter = sparkAdapter
            binding.tvNumList.text = it.joinToString(", ")
        }

        mainViewModel.errorLiveData.observe(this) {
            val snackbar: Snackbar = Snackbar.make(binding.root, it.toString(), Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }
}