package com.example.lab72.retrofit
import retrofit2.Call
import retrofit2.http.GET

interface BitcoinAPI {
    @GET("v1/bpi/currentprice.json")
    fun getBitcoinPrice(): Call<BitcoinPrice>
}