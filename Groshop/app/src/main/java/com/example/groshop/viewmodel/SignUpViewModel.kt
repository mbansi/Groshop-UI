package com.example.groshop.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.groshop.apicalling.ApiResponse
import com.example.groshop.apicalling.RetrofitApiResponse
import com.example.groshop.apicalling.RetrofitApiResponse.Companion.retrofit
import com.example.groshop.apicalling.UserFailModel
import com.example.groshop.apicalling.UserRegisterSuccessModel
import okhttp3.RequestBody
import org.json.JSONObject
import java.net.URL

class SignUpViewModel : BaseViewModel() {
    val userLogin: MutableLiveData<UserRegisterSuccessModel> = MutableLiveData()
    val userFail: MutableLiveData<UserFailModel> = MutableLiveData()

    fun signUpUser(url: URL, data: JSONObject) {
        makeApiCall(
            "POST",
            url,
            data,
            UserRegisterSuccessModel::class.java,
            object : ApiResponse {
                override fun <T> onSuccessfulResponse(data: T) {
                    userLogin.postValue(data as UserRegisterSuccessModel)
                }

                override fun <T> onError(message: T) {
                    userFail.postValue(message as UserFailModel)
                }
            }
        )
    }

    fun signUpRetrofit(requestBody: RequestBody ) {
        val data = retrofit.create(RetrofitApiResponse::class.java).createUser(requestBody)
        makeRetrofitApiCall(data,object : ApiResponse {
            override fun <T> onSuccessfulResponse(data: T) {
                userLogin.postValue(data as UserRegisterSuccessModel)
            }

            override fun <T> onError(message: T) {
                userFail.postValue(message as UserFailModel)
            }
        })
    }

}