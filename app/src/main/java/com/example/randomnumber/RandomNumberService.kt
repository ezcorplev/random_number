package com.example.randomnumber

import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RandomNumberService { // this API Interface will be used in the RetrofitInstance and implemented in the RandomNumberRepo

//    @GET("?num=1&min=1&max=100&col=1&base=10&format=plain&rnd=new")

    @GET(
        "?num={num}" +
            "&min={min}" +
            "&max={max}" +
            "&col={col}" +
            "&base={base}" +
            "&format={format}" +
            "&rnd={rnd}")

    fun getRandomNumber(
        @Path("num") num: Int = 1,
        @Path("min") min: Int,
        @Path("max") max: Int,
        @Path("col") col: Int = 1,
        @Path("base") base: Int = 10,
        @Path("format") format: String = "plain",
        @Path("rnd") rnd: String = "new",

        ): Call<Int>

//    fun getRandomNumber(
//        @Path("num") num: Int = 1,
//        @Path("min") min: Int,
//        @Path("max") max: Int,
//        @Path("col") col: Int = 1,
//        @Path("base") base: Int = 10,
//        @Path("format") format: String = "plain",
//        @Path("rnd") rnd: String = "new",
//
//        ): Flow<Int>
}