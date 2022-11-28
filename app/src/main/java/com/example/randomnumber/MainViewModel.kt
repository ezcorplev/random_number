package com.example.randomnumber

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainViewModel (app: Application) : AndroidViewModel(app) {



//    val numList = Call<Any?>() // if we try this method we get interface Call does not have constructors
//    val numList = Response<Int>().body()
//    val response = randomNumberRepo.getRandomNumber(min = 1, max = 100)

//    val numList = MutableLiveData<MutableList<Int>>().apply {
//        viewModelScope.launch(Dispatchers.IO) {
//            randomNumberRepo.getRandomNumber(min = 1, max = 100)
////                .collect() // wont let me .collect unless fun in RandomNumberService interface is of Flow<Int>
//        }
//    }

    val numList = MutableLiveData<List<Int>>() // this is an empty list, we will add numbers to this list,
    // which we will later present in a chart

    suspend fun getRandomNumber() {
        val randomNumber: Call<Int> = RepoFactory.randomNumberRepo.getRandomNumber(min = 1, max = 100)
        randomNumber.enqueue(object: Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) {
                    val tempNumList = numList.value?.toMutableList()
                    response.body()?.let {
                        tempNumList?.add(it)
                        numList.postValue(tempNumList) // numList.value = tempNumList, posts value to observers
                    }



                }
                Log.d("yoyo", response.body().toString())
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.d("yoyo", "BALAGAN")
            }

        })
    }

}