package com.example.randomnumber

import retrofit2.Call
import retrofit2.http.*


interface RandomNumberService { // this API Interface will be used in the RetrofitInstance and implemented in the RandomNumberRepo

//    @GET("?num=1&min=1&max=100&col=1&base=10&format=plain&rnd=new")

//    @GET(
//        "?num={num}" +
//            "&min={min}" +
//            "&max={max}" +
//            "&col={col}" +
//            "&base={base}" +
//            "&format={format}" +
//            "&rnd={rnd}")

//        "?num={num}&min={min}&max={max}&col={col}&base={base}&format={format}&rnd={rnd}")

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

    @POST("integer/")
    fun getRandom(@Body number: Int)

    // talech
    // base url - www.talech.com
    // get list of invoices - www.talech.com/invoices
    // add quesry to get a specific list of invoices - www.talech.com/invoices?userId=123456&status="completed"
//    @GET("today.json")
//    fun getSalahTiming(
//        @Query("latitude") latitude: Double,
//        @Query("longitude") longitude: Double,
//        @Query("elevation") elevation: Int,
//        @Query("timeformat") timeformat: Int
//    ): Call<Int?>?

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