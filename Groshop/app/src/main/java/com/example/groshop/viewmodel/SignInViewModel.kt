package com.example.groshop.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.groshop.apicalling.*
import com.example.groshop.apicalling.RetrofitApiResponse.Companion.retrofit
import org.json.JSONObject
import java.net.URL

class SignInViewModel : BaseViewModel() {

    val userLogin: MutableLiveData<UserLoginSuccessModel> = MutableLiveData()
    val userFail: MutableLiveData<UserFailModel> = MutableLiveData()

    fun signInUser(url: URL, data: JSONObject) {
        makeApiCall(
            "POST",
            url,
            data,
            UserLoginSuccessModel::class.java,
            object : ApiResponse {
                override fun <T> onSuccessfulResponse(data: T) {
                    userLogin.postValue(data as UserLoginSuccessModel)
                }

                override fun <T> onError(message: T) {
                    userFail.postValue(message as UserFailModel)
                }
            }
        )
    }

    fun signInUserRetrofit(email: String,password: String) {
        val data = retrofit.create(RetrofitApiResponse::class.java).loginUser(email,password)
        makeRetrofitApiCall(data,object : ApiResponse {
            override fun <T> onSuccessfulResponse(data: T) {
                userLogin.postValue(data as UserLoginSuccessModel)
            }

            override fun <T> onError(message: T) {
                userFail.postValue(message as UserFailModel)
            }
        })
    }
}