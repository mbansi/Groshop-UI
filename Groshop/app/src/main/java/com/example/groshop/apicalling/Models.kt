package com.example.groshop.apicalling

data class UserRegisterSuccessModel(val id: Int, val token: String)

data class UserLoginSuccessModel(val token: String)

data class UserFailModel(val error: String)