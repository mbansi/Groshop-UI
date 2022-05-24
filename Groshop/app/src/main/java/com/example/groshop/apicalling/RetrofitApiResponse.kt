package com.example.groshop.apicalling

import retrofit2.Call
import retrofit2.http.POST

interface RetrofitApiResponse {
    @POST("api/register")
    fun createUser(): Call<UserRegisterSuccessModel>
}