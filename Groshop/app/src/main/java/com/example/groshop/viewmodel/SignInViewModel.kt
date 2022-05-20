package com.example.groshop.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.groshop.apicalling.ApiResponse
import com.example.groshop.apicalling.UserFailModel
import com.example.groshop.apicalling.UserLoginSuccessModel
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
}