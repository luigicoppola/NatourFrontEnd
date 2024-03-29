package com.ingsftw.natourfrontend


import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi {

    @POST("auth/registrazione")
    suspend fun createUser(@Body requestBody: RequestBody): Response<ResponseBody>


    @POST("auth/login")
    suspend fun login(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("itinerario/all")
    suspend fun itinerariAPI(): Response<ResponseBody>

    @POST("itinerario/add")
    suspend fun createItinerarioManuale(@Body requestBody: RequestBody) : Response<ResponseBody>





}


