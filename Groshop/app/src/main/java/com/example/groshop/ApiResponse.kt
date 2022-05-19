package com.example.groshop

interface ApiResponse {
    fun onSuccessfulResponse(data: UserModel)
    fun onError(message: String)
}