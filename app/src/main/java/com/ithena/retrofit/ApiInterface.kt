package com.ithena.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")

    fun getData():Call<List<MyProductClassItem>>
}