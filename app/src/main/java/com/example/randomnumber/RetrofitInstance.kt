package com.example.randomnumber

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.random.org/"

object RetrofitInstance {

    val randomNumberService: RandomNumberService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomNumberService::class.java)
    }
}

//abstract class MyRetrofit { // the instance of this retrofit will be used in RandomNumberRepo
//
//    abstract val randomNumberService: RandomNumberService // connects the interface to Retrofit
//
//    companion object RetrofitInstance {
//
//        private var instance: MyRetrofit? = null // instantiates the retrofit instance to null
//
//        fun getInstance() = instance ?: kotlin.run { // makes sure there is only one instance of retrofit
//            Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                    .create(RandomNumberService::class.java)
//            instance!!
//        }
//        fun getInstance(): Retrofit = RandomNumberService {
//            Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(RandomNumberService::class.java)
//        }
//        val instance: RandomNumberService by lazy {
//
//            Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(RandomNumberService::class.java)
//
//        }
//
//    }
//}

