package com.example.randomnumber

import com.robinhood.spark.SparkAdapter

class SparkAdapter(private var yData: FloatArray) : SparkAdapter() {

    override fun getCount() = yData.size

    override fun getItem(index: Int): Float = yData[index]

    override fun getY(index: Int): Float = yData[index]
}