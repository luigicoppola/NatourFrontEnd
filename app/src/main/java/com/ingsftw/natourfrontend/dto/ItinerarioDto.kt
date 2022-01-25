package com.ingsftw.natourfrontend.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ItinerarioDto(
    @SerializedName("id") val id : Int,
    @SerializedName("nome") val nome : String,
    @SerializedName("durata") val durata : Double,
    @SerializedName("difficolta") val difficolta: Double,
    @SerializedName("punteggio") val punteggio : Double,
    @SerializedName("coordinate") val coordinate : List<CoordinataDto>,
    @SerializedName("utente") val utente : UserDto
    ) {}

@Serializable
data class CoordinataDto(
    @SerializedName("id") val id : Int,
    @SerializedName("lat") val lat : Double,
    @SerializedName("lon") val lon : Double,
    @SerializedName("createdAt") val createdAt : String,
){
}



@Serializable
data class UserDto (
    @SerializedName("id") val id: Int,
    @SerializedName("email") val userEmail: String,
    @SerializedName("password") val userPassword: String,
    @SerializedName("nomeCompleto") val userFullName: String,
    @SerializedName("dataNascita") val userAge: String,
    @SerializedName("enable") val enable: Boolean,
    @SerializedName("token") val token: String

 ){}