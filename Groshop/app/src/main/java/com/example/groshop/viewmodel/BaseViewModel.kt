package com.example.groshop.viewmodel

import androidx.lifecycle.ViewModel
import com.example.groshop.apicalling.ApiResponse
import com.example.groshop.apicalling.UserFailModel
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.URL

open class BaseViewModel : ViewModel() {

    fun <T> makeRetrofitApiCall(data: Call<T>, apiCallback: ApiResponse)  {

        data.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {


                if (response.body() != null) {
                    apiCallback.onSuccessfulResponse(response.body())
                }
                else {
                    response.errorBody()?.let {
                        val jsonObj = JSONObject(it.charStream().readText())
                        val errorResponse = jsonObj.getString("error")
                        apiCallback.onError(UserFailModel(errorResponse))
                    }

                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                apiCallback.onError(UserFailModel(t.toString()))
            }
        })
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