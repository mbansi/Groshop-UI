package com.example.groshop.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.groshop.apicalling.ApiResponse
import com.example.groshop.apicalling.UserFailModel
import com.example.groshop.apicalling.UserRegisterSuccessModel
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
}