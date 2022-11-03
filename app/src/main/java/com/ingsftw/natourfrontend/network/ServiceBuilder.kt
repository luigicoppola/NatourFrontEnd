package com.ingsftw.natourfrontend.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
      .baseUrl("http://192.168.1.18:8080/") // change this IP for testing by your actual machine IP
        //.baseUrl("http://192.168.1.3:8082/")
    // .baseUrl("http://192.168.1.4:8080/")


        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}