package com.example.groshop.apicalling

interface ApiResponse {
    fun<T> onSuccessfulResponse(data: T)
    fun<T> onError(message: T)
}