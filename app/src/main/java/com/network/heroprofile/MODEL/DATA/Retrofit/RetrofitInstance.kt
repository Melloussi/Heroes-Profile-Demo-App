package com.network.heroprofile.MODEL.DATA.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://herocharacter.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api:RequestInt by lazy {
        retrofit.create(RequestInt::class.java)
    }
}