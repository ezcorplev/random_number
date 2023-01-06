package com.example.randomnumber

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

const val GET_RANDOM_NUM_FREQUENT = 1000L
class MainViewModel(private val randomNumberRepo: RandomNumberRepo, app: Application) : AndroidViewModel(app) {

    private val TAG = MainViewModel::class.java.simpleName

    val tempFloatArray = FloatArray(10)

    private val _numFloatArray = MutableLiveData<FloatArray>()
    val numFloatArray: LiveData<FloatArray> = _numFloatArray

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    /**
     * Fetch random number in range from API
     */
    suspend fun getRandomNumber() {

        val randomNumber: Call<Int> = randomNumberRepo.getRandomNumber(1, 100)

        // async call for randomNumber 1-100
        randomNumber.enqueue(object: Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        // if response is not null
                        Log.d(TAG, response.body().toString())
                        removeFirstAddLastInArray(tempFloatArray, it)
                    }
                    // adds fetched number to the array
                    _numFloatArray.postValue(tempFloatArray)
                } else {
                    Log.e(TAG, "Error: ${response.code()}")
                    _errorLiveData.postValue(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.e(TAG, "Failure: Call Failed ${t.message}")
                _errorLiveData.postValue(t.message)
            }
        })
    }

    /**
     * Schedules a given task to run at a fixed rate
     * Logs the current Thread that is used to execute said task
     * period is used to decide the fixed rate - [GET_RANDOM_NUM_FREQUENT]
     */
    fun getRandomEveryPeriod() {
        Timer().scheduleAtFixedRate( object : TimerTask() {
            override fun run() {
                viewModelScope.launch(Dispatchers.IO) {
                    Log.d(TAG, "Current Thread: ${Thread.currentThread().name}")
                    getRandomNumber()
                }
            }
        }, 0, GET_RANDOM_NUM_FREQUENT)
    }

    /**
     * Queues (removes array[0] and adds num) Array, returns updated FloatArray
     * @param array - Input FloatArray of n elements
     * @param num - Input Int that is queued into array
     */
    fun removeFirstAddLastInArray(array: FloatArray, num: Int): FloatArray {
        val newNum = num.toFloat()

        for (index in 0 until array.size - 1) { // run through array (0, 0, 0 ... 0> 10 elements)
            array[index] = array[index + 1]
        }
        array[array.size - 1] = newNum

        return array
    }
}