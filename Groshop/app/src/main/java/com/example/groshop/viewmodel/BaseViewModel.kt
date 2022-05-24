package com.example.groshop.viewmodel

import androidx.lifecycle.ViewModel
import com.example.groshop.apicalling.ApiResponse
import com.example.groshop.apicalling.RetrofitApiResponse
import com.example.groshop.apicalling.UserFailModel
import com.example.groshop.utils.BASE_URL
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.net.HttpURLConnection
import java.net.URL

open class BaseViewModel : ViewModel() {

    fun <T> makeRetrofitApiCall( data: T,response: RetrofitApiResponse)  {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun <T> makeApiCall(
        request: String,
        url: URL,
        data: JSONObject,
        model: Class<T>,
        context: ApiResponse
    ) {
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = request
            setRequestProperty("Content-Type", "application/json")
            outputStream.bufferedWriter().use {
                it.write(data.toString())
            }
            when (responseCode) {
                200 -> {
                    val response = inputStream.bufferedReader().use {
                        it.readText()
                    }
                    val user = Gson().fromJson(response, model)
                    context.onSuccessfulResponse(user)
                }
                else -> {
                    val response = errorStream.bufferedReader().use {
                        it.readText()
                    }
                    val user = Gson().fromJson(response, UserFailModel::class.java)
                    context.onError(user)
                }
            }
        }
    }
}