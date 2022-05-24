package com.example.groshop.apicalling

import com.google.gson.annotations.SerializedName

data class UserRegisterModel(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?
)
data class UserRegisterSuccessModel(val id: Int, val token: String)

data class UserLoginSuccessModel(val token: String)

data class UserFailModel(val error: String)