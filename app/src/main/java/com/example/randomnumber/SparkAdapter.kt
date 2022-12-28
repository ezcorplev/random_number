package com.example.randomnumber

import com.robinhood.spark.SparkAdapter


class SparkAdapter(private val yData: FloatArray) : SparkAdapter() {

    override fun getCount() = yData.size

    override fun getItem(index: Int): Any = yData[index]

    override fun getY(index: Int): Float {
        return yData[index]
    }

}