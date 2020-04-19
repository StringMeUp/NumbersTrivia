package com.example.numberstrivia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.numberstrivia.model.NumberModel
import com.example.numberstrivia.networking.JsonApiInterface
import com.example.numberstrivia.networking.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomViewModel : ViewModel() {

    //retrofit call
    private val requestService = ServiceBuilder().buildService(JsonApiInterface::class.java)

    //observables
    val randomResults = MutableLiveData<NumberModel>()
    val loadingProgress = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    fun loadRemoteTrivia() {
        val callRandomTrivia = requestService.getRandomTrivia("json")
        loadingProgress.value = true
        callRandomTrivia.enqueue(object : Callback<NumberModel> {
            override fun onFailure(call: Call<NumberModel>, t: Throwable) {
                println("RandomTrivia call has failed")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<NumberModel>, response: Response<NumberModel>) {
                val trivia = response.body()
                if (response.isSuccessful && trivia != null) {
                    randomResults.value = trivia
                    loadingProgress.value = false
                    isError.value = false
                } else {
                    println("CallRandomTrivia has failed: $response.code()")
                    loadingProgress.value = false
                    if (response.code() == 502) {
                        isError.value = true
                    }
                }
            }
        })
    }

    fun loadRemoteYear() {
        loadingProgress.value = true
        val callRandomYear = requestService.getRandomYear("json")
        callRandomYear.enqueue(object : Callback<NumberModel> {
            override fun onFailure(call: Call<NumberModel>, t: Throwable) {
                println("CallRandomYear call has failed")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<NumberModel>, response: Response<NumberModel>) {
                if (!response.isSuccessful) {
                    println("CallRandomYear has failed: $response.code()")
                    loadingProgress.value = false
                    if (response.code() == 502) {
                        isError.value = true
                    }
                } else {
                    val randomYearFact = response.body()
                    randomYearFact?.let {
                        randomResults.value = randomYearFact
                        loadingProgress.value = false
                        isError.value = false
                    }
                }
            }
        })

    }

    fun loadRemoteDate() {
        loadingProgress.value = true
        val callRandomDate = requestService.getRandomDate("json")
        callRandomDate.enqueue(object : Callback<NumberModel> {
            override fun onFailure(call: Call<NumberModel>, t: Throwable) {
                println("CallRandomYear call has failed")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<NumberModel>, response: Response<NumberModel>) {
                if (!response.isSuccessful) {
                    println("CallRandomDate has failed: $response.code()")
                    loadingProgress.value = false
                    if (response.code() == 502) {
                        isError.value = true
                    }
                } else {
                    response.body()
                        ?.let {
                            randomResults.value = response.body()
                            loadingProgress.value = false
                            isError.value = false
                        }
                }
            }
        })
    }

    fun loadRemoteMath() {
        loadingProgress.value = true
        val callRandomMath = requestService.getRandomMath("json")
        callRandomMath.enqueue(object : Callback<NumberModel> {
            override fun onFailure(call: Call<NumberModel>, t: Throwable) {
                println("CallRandomMath has failed!")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<NumberModel>, response: Response<NumberModel>) {
                if (!response.isSuccessful) {
                    println(response.code())
                    loadingProgress.value = false
                    if (response.code() == 502) {
                        isError.value = true
                    }
                } else {
                    response.body()?.let {
                        randomResults.value = response.body()
                        loadingProgress.value = false
                        isError.value = false
                    }
                }
            }
        })
    }
}