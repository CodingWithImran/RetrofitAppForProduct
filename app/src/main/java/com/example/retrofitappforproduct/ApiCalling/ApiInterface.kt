package com.example.retrofitappforproduct.ApiCalling

import androidx.lifecycle.LiveData
import com.example.retrofitappforproduct.Modals.MyData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
//    fun getProductData() : Call<MyData>
    suspend fun getProductData() : Response<MyData>
}