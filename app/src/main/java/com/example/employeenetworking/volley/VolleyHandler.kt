package com.example.employeenetworking.volley

interface VolleyHandler {
    fun onSuccess(response:String?)
    fun onError(error: String?)
}