package com.example.randomnumber

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomNumberService { // this API Interface is used in retrofit singleton

    @GET("integers/")
    fun getRandomNumber(
        @Query("num") num: Int = 1,
        @Query("min") min: Int,
        @Query("max") max: Int,
        @Query("col") col: Int = 1,
        @Query("base") base: Int = 10,
        @Query("format") format: String = "plain",
        @Query("rnd") rnd: String = "new"
        ): Call<Int>
}