package com.ingsftw.natourfrontend.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ItinerarioDto(
    @SerializedName("idItinerary") val id : Int,
    @SerializedName("name") val nome : String,
    @SerializedName("durata") val durata : Double,
    @SerializedName("difficulty") val difficolta: Double,
    @SerializedName("score") val punteggio : Double,
    @SerializedName("idUser") val utente : Long
    )

@Serializable
data class CoordinataDto(
    @SerializedName("idCoordinate") val id : Int,
    @SerializedName("langitude") val lat : Double,
    @SerializedName("longitude") val lon : Double,
    @SerializedName("createdAt") val createdAt : String,
    @SerializedName("tipology") val tipologia : String,
    @SerializedName("idUser") val utente : Long,
    @SerializedName("idItinerary") val itinerario : Long

){
}



@Serializable
@Parcelize
data class UserDto (
    @SerializedName("idUser") val id: Int,
    @SerializedName("email") val userEmail: String,
    @SerializedName("password") val userPassword: String,
    @SerializedName("fullName") val userFullName: String,
    @SerializedName("birthDate") val userAge: String,
    @SerializedName("enable") val enable: Boolean,
    @SerializedName("token") val token: String


 ) : Parcelable {}