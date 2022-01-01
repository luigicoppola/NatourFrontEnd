package com.ingsftw.natourfrontend

import com.google.gson.annotations.SerializedName
import java.util.*

class UserInfo {
    data class UserInfo (
        @SerializedName("email") val userEmail: Int?,
        @SerializedName("password") val userPassword: String?,
        @SerializedName("nomeCompleto") val userFullName: String?,
        @SerializedName("dataNascita") val userAge: Date?,

    )
}
