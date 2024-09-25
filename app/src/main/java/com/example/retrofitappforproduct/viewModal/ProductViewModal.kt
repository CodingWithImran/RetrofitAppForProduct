package com.example.retrofitappforproduct.viewModal

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitappforproduct.Adapters.MyAdapters
import com.example.retrofitappforproduct.ApiCalling.RetrofitInstance
import com.example.retrofitappforproduct.Modals.MyData
import com.example.retrofitappforproduct.Modals.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductViewModal : ViewModel(){
    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList

fun fetchData(){
    viewModelScope.launch (Dispatchers.IO){
        try{
            var retrofitBuild = RetrofitInstance.api.getProductData()
            if(retrofitBuild.isSuccessful){
             _productList.postValue(retrofitBuild.body()?.products!!)
            }
        }catch (exception : Exception){
            Log.d("Tag", "Exception")
        }
    }
}
}