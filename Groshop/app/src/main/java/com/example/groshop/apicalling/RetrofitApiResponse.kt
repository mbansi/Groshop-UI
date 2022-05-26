package com.example.groshop.apicalling

import com.example.groshop.utils.BASE_URL
import com.example.groshop.utils.LOGIN_URL
import com.example.groshop.utils.REGISTER_URL
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitApiResponse {

    @POST(REGISTER_URL)
    fun createUser(@Body requestBody: RequestBody): Call<UserRegisterSuccessModel>

    @FormUrlEncoded
    @POST(LOGIN_URL)
    fun loginUser(@Field("email") email: String, @Field("password") password: String): Call<UserLoginSuccessModel>

    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}