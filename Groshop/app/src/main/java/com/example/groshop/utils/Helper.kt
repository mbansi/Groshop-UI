package com.example.groshop.utils

import com.example.groshop.apicalling.ApiResponse
import com.example.groshop.apicalling.UserFailModel
import com.google.gson.Gson
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

fun<T> makeApiCall( request: String, url: URL, context: ApiResponse, data: JSONObject,model: Class<T>) {
    with(url.openConnection() as HttpURLConnection) {
        requestMethod = request
        setRequestProperty("Content-Type", "application/json")
        outputStream.bufferedWriter().use {
            it.write(data.toString())
        }
        when(responseCode){
            200 -> {
                val response = inputStream.bufferedReader().use {
                    it.readText()
                }
                val user = Gson().fromJson(response,model)
                context.onSuccessfulResponse(user)
            }
            else -> {
                val response = errorStream.bufferedReader().use {
                    it.readText()
                }
                val user = Gson().fromJson(response,UserFailModel::class.java)
                context.onError(user)
            }
        }
    }
}