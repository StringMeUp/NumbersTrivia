package com.example.numberstrivia.networking

import com.example.numberstrivia.model.NumberModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonApiInterface {

    @GET("random/trivia")
    fun getRandomTrivia(
        @Query("json") json: String
    ): Call<NumberModel>

    @GET("random/year")
    fun getRandomYear(
        @Query("json") json: String
    ): Call<NumberModel>

    @GET("random/date")
    fun getRandomDate(
        @Query("json") json: String
    ): Call<NumberModel>

    @GET("random/math")
    fun getRandomMath(
        @Query("json") json: String
    ): Call<NumberModel>

    @GET("{number}/trivia")
    fun searchTrivia(
        @Path("number") number: Int?,
        @Query("json") json: String
    ): Call<NumberModel>

    @GET("{number}/year")
    fun searchYear(
        @Path("number") number: Int?,
        @Query("json") json: String
    ): Call<NumberModel>

    @GET("{number}/math")
    fun searchMath(
        @Path("number") number: Int?,
        @Query("json") json: String
    ): Call<NumberModel>

    @GET("{number}/date")
    fun searchDate(
        @Path("number") number: Int?,
        @Query("json") json: String
    ): Call<NumberModel>
}