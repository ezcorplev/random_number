package com.ezcorplev.randomnumber

import retrofit2.Call

interface RandomNumberRepo {

    /**
     * Gets random number
     * @param min - The minimum value of the fetched random number
     * @param max - The maximum value of the fetched random number
     */
    suspend fun getRandomNumber(min: Int, max: Int): Call<Int>
}

internal object RandomNumberRepoImpl: RandomNumberRepo {
    override suspend fun getRandomNumber(min: Int, max: Int): Call<Int> =
        RetrofitInstance.randomNumberService.getRandomNumber(min = min, max = max)
}