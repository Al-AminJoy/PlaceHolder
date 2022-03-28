package com.alamin.placeholder.model.network

interface OnResponseCall {
    fun onSuccess(message: String);
    fun onFailed(message: String)
}