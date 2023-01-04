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
import java.util.Timer
import java.util.TimerTask

class MainViewModel(private val randomNumberRepo: RandomNumberRepo, app: Application) : AndroidViewModel(app) {

    private val TAG = MainViewModel::class.java.simpleName

    val numFloatArray = MutableLiveData<FloatArray>()
    val tempFloatArray = FloatArray(10)
    val numFloatList = MutableLiveData<List<Float>>()
    val tempFloatList = mutableListOf<Float>()
//    val errorLiveData = MutableLiveData<List<String>>()
    val errorLiveData2 = MutableLiveData<Int>()

    /**
     * Calls RepoFactory.randomNumberRepo.getRandomNumber with values min = 1, max = 100
     * Enqueues Callback<Int> asynchronously and implements onResponse and onFailure functions
     * If response isSuccessful and response.body is not null -> updates tempFloatArray with
     * response.body(Int), postValue of numFloatArray as tempFloatArray (updated Array with Int)
     * If response is unsuccessful, adds error as String to errorLiveData, postValue of errorLiveData
     * with new error, and logs response.code as String
     * OnFailure logs @param t: Throwable as string
     */

    suspend fun getRandomNumber() {

        val randomNumber: Call<Int> = RepoFactory.randomNumberRepo.getRandomNumber(1, 100)

        randomNumber.enqueue(object: Callback<Int> { // async call for randomNumber 1-100
            override fun onResponse(call: Call<Int>, response: Response<Int>) { // set onResponse
                if (response.isSuccessful) {
                    response.body()?.let { // if response is not null
                        removeFirstAddLastInTenArray(tempFloatArray, it)
                        queueList(tempFloatList, it)
                    } // add (it) Int to the list
                    numFloatArray.postValue(tempFloatArray)
                    numFloatList.postValue(tempFloatList)
                    errorLiveData2.postValue(response.body())
                } else {
                    val error = mutableListOf<String>()
                    error.add("Error: ${response.code()}")
                    Log.d(TAG, "Error: ${response.code()}")
//                    errorLiveData.postValue(error)
                    // Handle not successful (maybe log?)

                }
                Log.d(TAG, response.body().toString())
            }
            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.d(TAG, "Failure: Call Failed ${t.message}")
            }
        })
    }

    /**
     * Schedules a given task to run at a fixed rate
     * Current task is set to getRandomNumber()
     * Logs the current Thread that is used to execute said task
     * period is used to decide the fixed rate (currently 1000ms)
     */

    fun getRandomEveryTen() {
        Timer().scheduleAtFixedRate( object : TimerTask() {
            override fun run() {
                viewModelScope.launch(Dispatchers.IO) {
                    Log.d(TAG, "Current Thread: ${Thread.currentThread().name}")
                    getRandomNumber()
                }
            }
        }, 0, 1000)
    }

    /**
     * Queues (removes array[0] and adds num) Array, returns updated FloatArray
     * @param array - Input FloatArray of 10 elements
     * @param num - Input Int that is queued into array
     */

    fun removeFirstAddLastInTenArray(array: FloatArray, num: Int): FloatArray {
        val newNum = num.toFloat()

        for (index in 0 until array.size - 1) { // run through array (0, 0, 0 ... 0> 10 elements)
            array[index] = array[index + 1]
        }
        array[array.size - 1] = newNum

        return array
    }

    /**
     * Queues (removes list[0] and adds num) List, returns updated List<Float>
     * @param list - Input List<Float> of n elements of type Float
     * @param num - Input Int that is queued into list
     */

    fun queueList(list: List<Float>, num: Int): List<Float> {
        val mutableList = mutableListOf<Float>()
        mutableList.addAll(list)

        if (list.size < 10) {
            mutableList.add(num.toFloat())
        } else {
            mutableList.removeAt(0)
            mutableList.add(num.toFloat())
        }

        return mutableList
    }
}

