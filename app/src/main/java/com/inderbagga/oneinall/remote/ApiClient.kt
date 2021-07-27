package com.inderbagga.oneinall.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val ENDPOINT="https://jsonplaceholder.typicode.com"

   private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: API by lazy {

        retrofit.create(API::class.java)
    }
}