package com.example.randomnumber

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

// TODO
// understand how to work with sparkAdapter to adapt data to UI
// sparkAdapter requires FloatArray
// understand how to change list from API into FloatArray


class MainViewModel (app: Application) : AndroidViewModel(app) {

    private val TAG = MainViewModel::class.java.simpleName

    val numList = MutableLiveData<List<Int>>() // this is an empty list, we will add numbers to this list,
    // which we will later present in a chart
    val numFloatArray = MutableLiveData<FloatArray>()
    val tempFloatArray = FloatArray(10) // new FloatArray (0, 0, 0...)


    suspend fun getRandomNumber() {
        val randomNumber: Call<Int> = RepoFactory.randomNumberRepo.getRandomNumber(min = 1, max = 100) // new val as randomNumber
        randomNumber.enqueue(object: Callback<Int> { // async call for randomNumber 1-100
            override fun onResponse(call: Call<Int>, response: Response<Int>) { // set onResponse
                if (response.isSuccessful) {
                    val tempNumList = numList.value?.toMutableList() ?: mutableListOf()// set up temp var for list<Int> (instead of Call<List<Int>>)
                    numFloatArray.value = tempFloatArray ?: FloatArray(10 )// now FloatArray -> (22, 17, 63...)
                    response.body()?.let { // if response is not null
                        tempNumList.addToListOrReplaceIfTenList(it)
                        removeFirstAddLastInTenArray(tempFloatArray, it)
                        } // add (it) Int to the list
                        numList.postValue(tempNumList) // numList.value = tempNumList, posts value to observers
                        numFloatArray.postValue(tempFloatArray)
                    }
                Log.d(TAG, response.body().toString())
                Log.d(TAG, "onResponse: ${numFloatArray.value}")
            }
            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.d(TAG, "BALAGAN")
            }
        })
    }

    fun MutableList<Int>.addToListOrReplaceIfTenList(num: Int): MutableList<Int> {

//        if (this.isNullOrEmpty()) this = emptyList<Int>().toMutableList()

        if (this.size > 9) { // if the list already contains 10 elements we need to replace [1] with [0] etc

            this.removeAt(0)
            this.add(num)

        } else {
            this.add(num)
        }
        return this
    }

    fun getRandomEveryTen() {

        Timer().scheduleAtFixedRate( object : TimerTask() {
            override fun run() {
                viewModelScope.launch(Dispatchers.IO) {
                    Log.d(TAG, "yoyo thread is ${Thread.currentThread().name}")
                    getRandomNumber()
                }
            }
        }, 0, 10000)
    }

    fun removeFirstAddLastInTenArray(array: FloatArray, num: Int): FloatArray {
        val newNum = num.toFloat()

        for (index in 0 until array.size - 1) { // run through array (0, 0, 0 ... 0> 10 elements)
            array[index] = array[index + 1]
        }
        array[array.size - 1] = newNum

        for (float in array) print("$float,").also { Log.d(TAG, "removeFirstAddLastInTenArray: $float") }
        println("")
        return array
    }

}

