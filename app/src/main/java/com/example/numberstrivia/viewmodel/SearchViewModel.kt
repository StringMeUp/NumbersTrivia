package com.example.numberstrivia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.numberstrivia.model.NumberModel
import com.example.numberstrivia.networking.JsonApiInterface
import com.example.numberstrivia.networking.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    val searchAnswers = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    private val requestService = ServiceBuilder().buildService(JsonApiInterface::class.java)

    fun searchTriviaFacts(triviaQuery: String) {
        val callSearchTrivia =
            requestService.searchTrivia(number = triviaQuery.toInt(), json = "json")
        isLoading.value = true
        callSearchTrivia.enqueue(object : Callback<NumberModel> {
            override fun onFailure(call: Call<NumberModel>, t: Throwable) {
                isLoading.value = false
                println("CallSearchTrivia has failed")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<NumberModel>, response: Response<NumberModel>) {
                if (!response.isSuccessful) {
                    isLoading.value = false
                    println("CallSearchTrivia has failed ${response.code()}")
                    if (response.code() == 502) {
                        isError.value = true
                        isLoading.value = false
                    }
                } else {
                    val triviaList = response.body()
                    triviaList?.let {
                        searchAnswers.value = it.text
                        println(searchAnswers.value)
                        isLoading.value = false
                        isError.value = false
                    }
                }
            }
        })
    }

    fun searchYearFacts(searchYearQuery: String) {
        val callSearchYear = requestService.searchYear(searchYearQuery.toInt(), "json")
        isLoading.value = true
        callSearchYear.enqueue(object : Callback<NumberModel> {
            override fun onFailure(call: Call<NumberModel>, t: Throwable) {
                t.printStackTrace()
                println("CallSearchYear has failed")
                isLoading.value = false
            }

            override fun onResponse(call: Call<NumberModel>, response: Response<NumberModel>) {
                if (!response.isSuccessful) {
                    println("CallSearchYear has failed ${response.code()}")
                    if (response.code() == 502) {
                        isError.value = true
                        isLoading.value = false
                    }
                } else {
                    response.body()?.let {
                        searchAnswers.value = it.text
                        println(it.text)
                        isLoading.value = false
                        isError.value = false
                    }
                }
            }
        })
    }

    fun searchMathFacts(searchMathQuery: String) {
        val callSearchMath = requestService.searchMath(searchMathQuery.toInt(), "json")
        isLoading.value = true
        callSearchMath.enqueue(object : Callback<NumberModel> {
            override fun onFailure(call: Call<NumberModel>, t: Throwable) {
                t.printStackTrace()
                println("CallSearchYear has failed")
                isLoading.value = false
            }

            override fun onResponse(call: Call<NumberModel>, response: Response<NumberModel>) {
                if (!response.isSuccessful) {
                    println("CallSearchMath has failed ${response.code()}")
                    if (response.code() == 502) {
                        isError.value = true
                        isLoading.value = false
                    }
                } else {
                    response.body()?.let {
                        searchAnswers.value = it.text
                        println(it.text)
                        isLoading.value = false
                        isError.value = false
                    }
                }
            }
        })
    }

    fun searchDateFacts(searchDateQuery: String) {
        val callSearchMath = requestService.searchDate(searchDateQuery.toInt(), "json")
        isLoading.value = true
        callSearchMath.enqueue(object : Callback<NumberModel> {
            override fun onFailure(call: Call<NumberModel>, t: Throwable) {
                t.printStackTrace()
                println("CallSearchYear has failed")
                isLoading.value = false
            }

            override fun onResponse(call: Call<NumberModel>, response: Response<NumberModel>) {
                if (!response.isSuccessful) {
                    println("CallSearchMath has failed ${response.code()}")
                    if (response.code() == 502) {
                        isError.value = true
                        isLoading.value = false
                    }
                } else {
                    response.body()?.let {
                        searchAnswers.value = it.text
                        println(it.text)
                        isLoading.value = false
                        isError.value = false
                    }
                }
            }
        })
    }
}