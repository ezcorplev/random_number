package com.example.randomnumber

import kotlinx.coroutines.flow.Flow
import retrofit2.Call


interface RandomNumberRepo {

    suspend fun getRandomNumber(min: Int, max: Int): Call<Int>

}

internal object RandomNumberRepoImpl: RandomNumberRepo {

    override suspend fun getRandomNumber(min: Int, max: Int): Call<Int> =

        RetrofitInstance.randomNumberService.getRandomNumber(min = 1, max = 100) // this implements the getRandomNumber
    // from the RandomNumberInterface on the only instance of retrofit, which is a companion object in Retrofit Instance

}