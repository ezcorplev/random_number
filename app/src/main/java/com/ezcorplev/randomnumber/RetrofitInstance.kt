package com.ezcorplev.randomnumber

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.random.org/"

object RetrofitInstance {

    // Singleton
    val randomNumberService: RandomNumberService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomNumberService::class.java)
    }
}