package com.example.bakinapplication.severdb

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServerBuilder {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://applepie.khjcode.com/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val serverApi = retrofit.create(ServerApi::class.java)
}